# 🧩 Core Module - 공통 유틸리티 모듈

MSA 환경에서 공통적으로 사용하는 예외 처리, 응답 포맷, 직렬화 설정, 권한 검증 등을 제공하는 **경량화 공통 모듈**입니다.

---

## 📚 목차

- [📁 프로젝트 구조](#-프로젝트-구조)
- [📦 사용 라이브러리](#-사용-라이브러리)
- [⚙️ 주요 기능 설명](#️-주요-기능-설명)
    - [🔐 RoleVerifier](#-roleverifier)
    - [⚙️ JacksonConfig](#️-jacksonconfig)
    - [🚫 CustomException & ErrorCode](#-customexception--errorcode)
    - [📡 ApiResponse & ErrorResponse](#-apiresponse--errorresponse)
- [🧾 ErrorCode 목록](#-errorcode-목록)
- [✅ 사용 예시](#-사용-예시)
- [🧪 테스트 구성](#-테스트-구성)
- [🔧 확장 가능 항목](#-확장-가능-항목)
- [📄 License](#-license)

---

## 📁 프로젝트 구조

```plaintext
core
└── src
    ├── main
    │   ├── java
    │   │   └── com.leebak.core
    │   │       ├── config
    │   │       │   └── JacksonConfig.java
    │   │       ├── auth
    │   │       │   └── RoleVerifier.java
    │   │       ├── exception
    │   │       │   ├── CustomException.java
    │   │       │   └── ErrorCode.java
    │   │       └── response
    │   │           ├── ApiResponse.java
    │   │           └── ErrorResponse.java
```

---

## 📦 사용 라이브러리

| 목적        | 라이브러리                                         |
|-----------|-----------------------------------------------|
| Spring 기반 | `spring-context`, `spring-boot-autoconfigure` |
| JSON 직렬화  | `jackson-databind`, `jackson-datatype-jsr310` |
| 애너테이션 처리  | `lombok`                                      |
| 테스트       | `junit-jupiter-api`, `junit-jupiter-engine`   |

---

## ⚙️ 주요 기능 설명

### 🔐 RoleVerifier

- 주어진 역할이 요구되는 역할과 일치하지 않을 경우 `403 Forbidden` 예외를 발생시킵니다.
- Spring MVC와 WebFlux 컨트롤러에서 모두 사용 가능합니다.

---

### ⚙️ JacksonConfig

- `LocalDate`, `LocalDateTime`을 ISO 8601 포맷으로 직렬화합니다.
- `null` 필드는 JSON 응답에서 제외됩니다.
- 알 수 없는 JSON 필드는 무시 처리됩니다.

---

### 🚫 CustomException & ErrorCode

- `CustomException`은 공통 예외 클래스로 `ErrorCode`를 통해 예외 상태와 메시지를 전달합니다.
- `ErrorCode`는 각 예외에 대한 HTTP 상태 코드와 설명 메시지를 정의합니다.

---

### 📡 ApiResponse & ErrorResponse

- API 응답을 성공/실패 구분을 포함한 통합된 형태로 반환합니다.
- 실패 시 `ErrorResponse`를 통해 에러 코드와 메시지를 포함한 정보를 제공합니다.
- byte 형태의 JSON 에러 응답도 제공합니다 (`errorJsonBytes()`).

---

## 🧾 ErrorCode 목록

| 이름                      | 상태 코드 | 메시지                                     |
|-------------------------|-------|-----------------------------------------|
| `INVALID_REQUEST`       | 400   | Invalid request                         |
| `UNAUTHORIZED`          | 401   | Unauthorized                            |
| `FORBIDDEN`             | 403   | Access denied: insufficient permissions |
| `NOT_FOUND`             | 404   | Resource not found                      |
| `INTERNAL_SERVER_ERROR` | 500   | Internal server error                   |

---

## ✅ 사용 예시

```java

@GetMapping("/admin")
public ResponseEntity<?> admin(@RequestHeader("user-role") String role) {
    RoleVerifier.require(role, "ROLE_ADMIN");
    return ResponseEntity.ok("관리자 페이지");
}
```
---

## 🧪 테스트 구성

- `src/test/java` 디렉토리는 기본적으로 유지됩니다.
- 기본 테스트 클래스 예시로 `RoleVerifierTest` 등을 포함할 수 있습니다.
- 현재는 테스트 실행을 비활성화하여 빌드 속도에 영향을 주지 않도록 구성되어 있습니다:

```groovy
tasks.named('test') {
    enabled = false
}
```
---
## 🔧 확장 가능 항목

- `checkAny(String actual, String... allowed)`
- `ErrorCode` → 다국어 메시지(i18n) 연동
- JWT 기반 역할 추출기 (`JwtRoleExtractor` 등)

---
## 🔍 참고 자료

- [Jackson에서 날짜를 ISO 8601 형식으로 직렬화하기 (Baeldung)](https://www.baeldung.com/jackson-serialize-dates)
- [Spring Boot에서 커스텀 ObjectMapper 구성하기 (Baeldung)](https://www.baeldung.com/spring-boot-customize-jackson-objectmapper)
- [REST API 응답 구조 설계 베스트 프랙티스 (Vinay Sahni)](https://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api)
- [REST API에서 커스텀 예외 처리 설계하기 (Baeldung)](https://www.baeldung.com/global-error-handler-in-a-spring-rest-api)
- [HTTP 상태 코드 및 메시지 구조 참고 (MDN Web Docs)](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)

---
## 📄 License

MIT License © 2025 leebak