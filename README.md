# Jobserv: Java Observability Toolkit

Jobserv is a lightweight, pure Java-based toolkit designed to enhance application observability. It provides a comprehensive suite of tools for logging, performance monitoring, and system diagnostics.

## Key Features:
1. **Efficient Logging**: A flexible logging system that allows for easy tracking of application events and errors.
2. **Performance Monitoring**: Tools to measure and analyze method execution times and system performance.
3. **Resource Tracking**: Monitors system resources such as CPU usage, memory consumption, and disk I/O.
4. **Minimalist Design**: Built with pure Java, requiring no external libraries or frameworks.
5. **Easy Integration**: Designed to be easily integrated into existing Java applications with minimal setup.

## Use Cases:
- Debugging complex systems
- Identifying performance bottlenecks
- Monitoring application health in production environments
- Gathering insights for system optimization

Jobserv aims to provide developers and system administrators with the tools they need to gain deep insights into their Java applications, all while maintaining simplicity and ease of use.

## Getting Started:
To get started with Jobserv, simply download the JAR file from the [releases page](


# Package Structure

```plaintext
jobserv/
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── api/
│   ├── src/
│   │   ├── main/java/com/ring_ding_dong/jobserv/api/
│   │   │   ├── JobservManager.java
│   │   │   ├── logging/
│   │   │   │   └── Logger.java
│   │   │   ├── performance/
│   │   │   │   └── PerformanceTracker.java
│   │   │   └── monitoring/
│   │   │       └── SystemMonitor.java
│   │   └── test/java/com/ring_ding_dong/jobserv/api/
│   └── build.gradle
├── core/
│   ├── src/
│   │   ├── main/java/com/ring_ding_dong/jobserv/core/
│   │   │   ├── ConfigManager.java
│   │   │   ├── util/
│   │   │   │   ├── TimeUtil.java
│   │   │   │   └── StringUtil.java
│   │   │   └── exception/
│   │   │       └── JobservException.java
│   │   └── test/java/com/ring_ding_dong/jobserv/core/
│   └── build.gradle
├── logging/
│   ├── src/
│   │   ├── main/java/com/ring_ding_dong/jobserv/logging/
│   │   │   ├── LogLevel.java
│   │   │   ├── FileLogger.java
│   │   │   └── ConsoleLogger.java
│   │   └── test/java/com/ring_ding_dong/jobserv/logging/
│   └── build.gradle
├── performance/
│   ├── src/
│   │   ├── main/java/com/ring_ding_dong/jobserv/performance/
│   │   │   ├── MethodTimer.java
│   │   │   └── ExecutionStatistics.java
│   │   └── test/java/com/ring_ding_dong/jobserv/performance/
│   └── build.gradle
├── monitoring/
│   ├── src/
│   │   ├── main/java/com/ring_ding_dong/jobserv/monitoring/
│   │   │   ├── CpuMonitor.java
│   │   │   ├── MemoryMonitor.java
│   │   │   └── DiskMonitor.java
│   │   └── test/java/com/ring_ding_dong/jobserv/monitoring/
│   └── build.gradle
├── annotation/
│   ├── src/
│   │   ├── main/java/com/ring_ding_dong/jobserv/annotation/
│   │   │   └── MeasureTime.java
│   │   └── test/java/com/ring_ding_dong/jobserv/annotation/
│   └── build.gradle
├── sample/
│   ├── src/
│   │   └── main/java/com/ring_ding_dong/jobserv/sample/
│   │       └── SampleApplication.java
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── LICENSE

```

이 구조의 주요 특징 및 설명:

1. `api` 모듈:
   - 주요 인터페이스와 추상 클래스를 포함합니다.
   - `JobservManager`, `Logger`, `PerformanceTracker`, `SystemMonitor` 등 핵심 API를 정의합니다.

2. `core` 모듈:
   - 공통 유틸리티, 설정 관리, 예외 처리 등 핵심 기능을 포함합니다.
   - `ConfigManager`, `TimeUtil`, `StringUtil`, `JobservException` 등이 여기에 위치합니다.

3. `logging`, `performance`, `monitoring` 모듈:
   - 각 기능의 구체적인 구현을 포함합니다.
   - API 모듈의 인터페이스를 구현합니다.

4. `annotation` 모듈:
   - `MeasureTime` 등의 커스텀 어노테이션을 정의합니다.

5. `sample` 모듈:
   - 라이브러리 사용 예제를 제공합니다.

6. 루트 디렉토리:
   - `build.gradle`: 전체 프로젝트 설정
   - `settings.gradle`: 모듈 정의
   - `README.md`: 프로젝트 설명 및 사용 방법
   - `LICENSE`: 오픈 소스 라이선스

7. 각 모듈의 `src/main/resources`:
   - `jobserv.properties` 등의 설정 파일을 포함할 수 있습니다.

8. 각 모듈의 `src/test`:
   - 해당 모듈의 단위 테스트를 포함합니다.

이 구조의 장점:

1. 모듈화: 각 기능이 독립적인 모듈로 분리되어 있어 유지보수와 확장이 용이합니다.
2. API 분리: 공개 API가 별도의 모듈로 분리되어 있어 사용자 인터페이스가 명확합니다.
3. 의존성 관리: 각 모듈 간의 의존성을 명확히 제어할 수 있습니다.
4. 테스트 용이성: 각 모듈별로 독립적인 테스트가 가능합니다.
5. 샘플 제공: 별도의 샘플 모듈을 통해 사용 예제를 제공합니다.