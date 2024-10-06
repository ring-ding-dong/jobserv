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


## 프로젝트 구조

```
jobserv/
├── gradle/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ring_ding_dong/
│                   └── jobserv/
│                       ├── annotation/
│                       ├── api/
│                       ├── common/
│                       ├── core/
│                       ├── docs/
│                       ├── logging/
│                       ├── monitoring/
│                       ├── performance/
│                       └── sample/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── README.md
└── LICENSE
```

## 서브모듈 설명

### 1. annotation [jobserv.annotation]

**역할:** 프로젝트에서 사용되는 커스텀 애노테이션을 정의합니다.

**주의사항:**
- 애노테이션은 간단하고 명확하게 정의해야 합니다.
- 애노테이션 처리 로직은 이 모듈에 포함하지 않습니다.

### 2. api [jobserv.api]

**역할:** 프로젝트 전체에서 사용되는 공통 인터페이스와 기본 클래스를 정의합니다.

**주의사항:**
- API는 안정적이어야 하며, 변경 시 하위 호환성을 고려해야 합니다.
- 구체적인 구현은 이 모듈에 포함하지 않습니다.

### 3. common [jobserv.common]

**역할:** 여러 모듈에서 공통으로 사용되는 유틸리티 클래스와 헬퍼 함수를 제공합니다.

**주의사항:**
- 이 모듈은 다른 모듈에 대한 의존성을 최소화해야 합니다.
- 범용적으로 사용될 수 있는 코드만 포함해야 합니다.

### 4. core [jobserv.core]

**역할:** 프로젝트의 핵심 로직을 구현합니다.

**주의사항:**
- 다른 모듈과의 순환 의존성을 피해야 합니다.
- 성능에 민감한 코드이므로 최적화에 주의를 기울여야 합니다.

### 5. docs [jobserv.docs]

**역할:** 프로젝트의 문서화를 담당합니다. JavaDoc 생성 및 사용 가이드 등을 포함합니다.

**주의사항:**
- 문서는 항상 최신 상태를 유지해야 합니다.
- 코드 변경 시 관련 문서도 함께 업데이트해야 합니다.

### 6. logging [jobserv.logging]

**역할:** 로깅 관련 기능을 구현합니다.

**주의사항:**
- 로깅 성능이 전체 애플리케이션 성능에 큰 영향을 미치지 않도록 해야 합니다.
- 로그 레벨 설정 및 로그 파일 관리 기능을 고려해야 합니다.

### 7. monitoring [jobserv.monitoring]

**역할:** 애플리케이션 모니터링 관련 기능을 구현합니다.

**주의사항:**
- 모니터링으로 인한 오버헤드를 최소화해야 합니다.
- 민감한 정보가 로그에 남지 않도록 주의해야 합니다.

### 8. performance [jobserv.performance]

**역할:** 성능 분석 및 최적화 관련 기능을 구현합니다.

**주의사항:**
- 성능 측정 자체가 애플리케이션 성능에 큰 영향을 미치지 않도록 해야 합니다.
- 대량의 성능 데이터 처리 시 메모리 사용에 주의해야 합니다.

### 9. sample [jobserv.sample]

**역할:** 라이브러리 사용 예제를 제공합니다.

**주의사항:**
- 실제 프로덕션 코드를 이 모듈에 포함하지 않습니다.
- 예제는 간단하면서도 라이브러리의 주요 기능을 잘 보여줄 수 있어야 합니다.

## 개발 가이드라인

1. 각 서브모듈은 자체적인 테스트를 포함해야 합니다. 테스트는 `src/test/java` 디렉토리에 위치합니다.

2. 모든 public API에는 JavaDoc을 작성해야 합니다.

3. 코드 스타일은 Google Java Style Guide를 따릅니다.

4. 새로운 기능을 추가하거나 기존 기능을 수정할 때는 반드시 관련 테스트를 작성/수정해야 합니다.

5. 성능에 민감한 부분은 반드시 성능 테스트를 수행해야 합니다.

6. 공통으로 사용되는 유틸리티 함수는 'common' 모듈에 추가하고, 다른 모듈에서 재사용합니다.

7. 문서 업데이트는 'docs' 모듈에서 관리하며, 코드 변경 시 관련 문서도 함께 업데이트해야 합니다.

## 빌드 및 테스트

프로젝트 빌드:
```
./gradlew build
```

모든 테스트 실행:
```
./gradlew test
```

Javadoc 생성:
```
./gradlew aggregateJavadoc
```
