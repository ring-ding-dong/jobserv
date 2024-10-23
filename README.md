![Coverage](https://img.shields.io/badge/coverage-0%25-red)

# Jobserv: Java Observability Toolkit

Jobserv is a lightweight, pure Java-based toolkit designed to enhance application observability. It
provides a comprehensive suite of tools for logging, performance monitoring, and system diagnostics.

## Key Features:

1. **Efficient Logging**: A flexible logging system that allows for easy tracking of application
   events and errors.
2. **Performance Monitoring**: Tools to measure and analyze method execution times and system
   performance.
3. **Resource Tracking**: Monitors system resources such as CPU usage, memory consumption, and disk
   I/O.
4. **Minimalist Design**: Built with pure Java, requiring no external libraries or frameworks.
5. **Easy Integration**: Designed to be easily integrated into existing Java applications with
   minimal setup.

## Use Cases:

- Debugging complex systems
- Identifying performance bottlenecks
- Monitoring application health in production environments
- Gathering insights for system optimization

Jobserv aims to provide developers and system administrators with the tools they need to gain deep
insights into their Java applications, all while maintaining simplicity and ease of use.

## Getting Started:

To get started with Jobserv, simply download the JAR file from the [releases page](

## 프로젝트 구조

```
jobserv/
├── api/
│   └── src/main/java/com/ringdingdong/jobserv/api/
│       ├── annotation/        # 애노테이션 정의
│       │   ├── Logged.java
│       │   ├── Measured.java
│       │   └── Traced.java
│       └── exception/        # 예외 클래스
├── core/
│   └── src/main/java/com/ringdingdong/jobserv/core/
│       ├── JobServEngine.java   # 메인 엔진
│       ├── ProxyFactory.java    # 프록시 생성
│       └── handler/            # 애노테이션 처리
│           ├── LoggingHandler.java
│           ├── MetricsHandler.java
│           └── TracingHandler.java
├── impl/
│   └── src/main/java/com/ringdingdong/jobserv/impl/
│       ├── logging/          # 로깅 구현
│       ├── metrics/          # 메트릭 구현
│       └── tracing/         # 트레이싱 구현
├── example/
│   └── src/main/java/com/ringdingdong/jobserv/example/
│       └── SimpleExample.java    # 사용 예제
├── gradle/
├── build.gradle
├── settings.gradle
├── .gitignore
├── README.md
└── LICENSE
```

## 서브모듈 설명

각 서브모듈은 아래와 같은 기본 구조를 따릅니다.

```
jobserv.[module]/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.ring-ding-dong.jobserv.[module]/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com.ring-ding-dong.jobserv.[module]/
│       └── resources/
└── build.gradle

```

## 모듈별 상세 설명

### 1. api

**역할**

- 외부에 공개되는 모든 API와 애노테이션을 정의
- 공통 인터페이스와 기본 클래스 제공
- 전체 프로젝트의 안정적인 API 계약 정의

**주의사항**

- 최소한의 의존성만 포함
- 구현 로직을 포함하지 않음
- 모든 API에 상세한 JavaDoc 작성 필수
- API 변경 시 하위 호환성 고려

### 2. core

**역할**

- 프로젝트의 핵심 엔진 로직 구현
- 프록시 생성 및 처리 메커니즘 제공
- 애노테이션 처리 로직 구현

**주의사항**

- 순환 참조 금지
- 성능 최적화 필수
- 철저한 단위 테스트 작성
- 스레드 안전성 보장

### 3. impl

**역할**

- 각 기능(로깅, 메트릭, 트레이싱)의 실제 구현 제공
- 확장 가능한 구현체 정의
- 성능 최적화된 구현 제공

**주의사항**

- 구현체는 독립적으로 동작해야 함
- 메모리 사용 최적화 필수
- 성능에 영향을 최소화
- 충분한 테스트 케이스 작성

### 4. example

**역할**

- 라이브러리 사용 예제 제공
- 다양한 시나리오별 샘플 코드 제공
- 실제 사용 사례 데모

**주의사항**

- 실제 실행 가능한 예제만 포함
- 명확한 주석과 설명 필수
- 프로덕션 코드는 포함하지 않음

## 개발 가이드라인

1. 각 서브모듈은 자체적인 테스트를 포함해야 합니다. 테스트는 `src/test/java` 디렉토리에 위치합니다.

2. 모든 public API에는 JavaDoc을 작성해야 합니다.

3. 코드 스타일은 Google Java Style Guide를 따릅니다.

4. 새로운 기능을 추가하거나 기존 기능을 수정할 때는 반드시 관련 테스트를 작성/수정해야 합니다.

5. 각 모듈의 build.gradle은 필요한 의존성만 포함

6. 공통 설정은 루트 프로젝트의 build.gradle에서 관리


## 협업 워크플로우 가이드라인

### 1. ISSUE 생성 및 관리

#### 1.1 ISSUE 생성

- 새로운 기능, 버그 수정, 개선 사항 등 모든 작업은 ISSUE로 시작합니다.
- ISSUE 템플릿을 사용하여 일관된 형식으로 작성합니다.

#### 1.2 ISSUE 템플릿

```markdown
### 설명

[이슈에 대한 간단한 설명]

### 목적

[이 이슈를 해결함으로써 얻을 수 있는 이점]

### 세부 작업

- [ ] 작업 1
- [ ] 작업 2
- [ ] 작업 3

### 관련 문서

[관련된 문서나 외부 리소스 링크]

### 추가 정보

[기타 참고할 만한 정보]
```

#### 1.3 ISSUE 라벨 사용

- `bug`, `enhancement`, `documentation` 등의 라벨을 사용하여 ISSUE의 성격을 명확히 합니다.
- `priority-high`, `priority-medium`, `priority-low` 등으로 우선순위를 표시합니다.

#### 1.4 ISSUE 할당

- 팀 회의나 개별적인 판단에 따라 적절한 담당자에게 ISSUE를 할당합니다.

### 2. 브랜치 관리

#### 2.1 브랜치 구조

- `main`: 제품 출시 버전
- `develop`: 개발 중인 다음 버전
- `feature/*`: 새로운 기능 개발
- `bugfix/*`: 버그 수정
- `hotfix/*`: 긴급 버그 수정

#### 2.2 브랜치 생성 규칙

- 기능 개발: `feature/issue-[ISSUE_NUMBER]-short-description`
- 버그 수정: `bugfix/issue-[ISSUE_NUMBER]-short-description`
- 핫픽스: `hotfix/v[VERSION_NUMBER].[PATCH]`

### 3. 개발 프로세스

#### 3.1 로컬 개발

1. ISSUE 분석 및 `develop` 브랜치에서 새 브랜치 생성
2. 코드 작성 및 단위 테스트 구현
3. 정기적으로 커밋 (작은 단위로 자주 커밋)

#### 3.2 코드 품질 관리

- 커밋 전 로컬에서 lint 및 테스트 실행
- 코드 컨벤션 준수 (Google Java Style Guide)

### 4. Pull Request (PR) 프로세스

#### 4.1 PR 생성

1. 작업 완료 후 GitHub에서 PR 생성
2. PR 템플릿 작성

#### 4.2 PR 템플릿

```markdown
### 관련 ISSUE

- #[ISSUE_NUMBER]

### 변경 사항

[주요 변경 사항 요약]

### 테스트 방법

[변경 사항을 테스트할 수 있는 방법 설명]

### 스크린샷 (UI 변경 시)

[변경된 UI 스크린샷]

### 체크리스트

- [ ] 코드 컨벤션을 준수하였습니다.
- [ ] 관련 문서를 업데이트하였습니다.
- [ ] 새로운 테스트를 추가하였습니다.
- [ ] 기존의 테스트가 모두 통과합니다.
```

#### 4.3 코드 리뷰

- 최소 1명 이상의 리뷰어 지정
- 리뷰어는 48시간 이내에 리뷰 완료
- 모든 코멘트에 대해 해결 또는 논의

#### 4.4 CI/CD

- PR 생성 시 자동으로 CI 파이프라인 실행
- 모든 테스트 통과 및 리뷰 승인 후 병합 가능

### 5. 배포 프로세스

#### 5.1 배포 준비

1. `develop` 브랜치의 상태가 안정적인지 확인
2. 버전 번호 업데이트 및 최종 테스트

#### 5.2 배포

1. `develop`에서 `main` 브랜치로 PR 생성 및 병합
2. `main` 브랜치에 태그 생성 (`v[VERSION_NUMBER]`)
3. CI/CD 파이프라인을 통한 자동 배포

### 6. 문서화

- 코드 변경 시 관련 문서 동시 업데이트
- API 문서, 사용자 가이드, README 등 지속적인 관리
