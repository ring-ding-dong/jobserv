![Coverage](https://img.shields.io/badge/coverage-0%25-red)


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

- 여러 모듈에서 사용될 가능성이 높은 인터페이스
- 구체적인 구현보다는 높은 수준의 추상화를 제공하는 인터페이스
- 자주 변경되지 않을 것으로 예상되는 인터페이스
- 특정 모듈에 한정되지 않고, 일반적인 개념을 표현하는 인터페이스

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

- 여러 기능 모듈에서 공통으로 사용되는 핵심 로직
- 비즈니스 규칙이나 도메인 모델
- 기능별 모듈을 조정하고 통합하는 로직

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
