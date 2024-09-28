package com.github.ring_ding_dong.list;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.*;

public class MemoryEfficientList<E extends Serializable> implements List<E> {
    private static final int BUFFER_SIZE = 1000;
    private final List<E> buffer;
    private final Path tempFilePath;
    private final AtomicInteger size;

    public MemoryEfficientList() throws IOException {
        this.buffer = new ArrayList<>(BUFFER_SIZE);
        this.tempFilePath = Files.createTempFile("memefflist", ".tmp");
        this.size = new AtomicInteger(0);
    }

    @Override
    public boolean add(E e) {
        synchronized (buffer) {
            if (buffer.size() >= BUFFER_SIZE) {
                flushBufferToDisk();
            }
            buffer.add(e);
        }
        size.incrementAndGet();
        return true;
    }

    private void flushBufferToDisk() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new BufferedOutputStream(
                new GZIPOutputStream(
                    Files.newOutputStream(tempFilePath, StandardOpenOption.APPEND))))) {
            oos.writeObject(new ArrayList<>(buffer));
            buffer.clear();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to flush buffer to disk", e);
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size.get()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        synchronized (buffer) {
            if (index >= size.get() - buffer.size()) {
                return buffer.get(index - (size.get() - buffer.size()));
            }
        }
        try (ObjectInputStream ois = new ObjectInputStream(
            new BufferedInputStream(
                new GZIPInputStream(
                    Files.newInputStream(tempFilePath))))) {
            int skipChunks = index / BUFFER_SIZE;
            for (int i = 0; i < skipChunks; i++) {
                ois.readObject(); // Skip earlier chunks
            }
            @SuppressWarnings("unchecked")
            List<E> chunk = (List<E>) ois.readObject();
            return chunk.get(index % BUFFER_SIZE);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read from disk", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }
    }

    @Override
    public int size() {
        return size.get();
    }

    // 기타 List 메서드 구현...

    @Override
    public void clear() {
        synchronized (buffer) {
            buffer.clear();
        }
        size.set(0);
        try {
            Files.deleteIfExists(tempFilePath);
            Files.createFile(tempFilePath);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to clear temporary file", e);
        }
    }

    // finalize() 대신 사용
    public void close() {
        try {
            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
            // 로그 처리 또는 예외 처리
        }
    }
}