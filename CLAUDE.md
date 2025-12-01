# JWT 기반 인증 시스템 - 코드 수정 사항

## 1. build.gradle 수정

**위치**: `build.gradle` 라인 54-58

**변경사항**:
```groovy
// JWT
implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.3'      // ← implementation에서 runtimeOnly로 변경
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.3'   // ← implementation에서 runtimeOnly로 변경
implementation 'org.springframework.boot:spring-boot-configuration-processor'
```

**이유**: jjwt-impl과 jjwt-jackson은 런타임에만 필요

---

## 2. JwtUtil.java 임포트 수정

**위치**: `global/security/JwtUtil.java` 라인 1-15

**변경사항**:
```java
import io.jsonwebtoken.JwtException;  // ← 이 임포트 추가됨
```

**이유**: JWT 예외 처리를 위해 필요

---

## 3. application.yml 설정 추가

**위치**: `application.yml` 파일 끝

**추가내용**:
```yaml
jwt:
  token:
    secretKey: ZGh3YWlkc2F2ZXdhZXZ3b2EgMTM5ZXUgMDMxdWMyIHEyMiBAIDAgKTJFVio=
    expiration:
      access: 14400000
```

---

## 4. SecurityConfig.java 주요 변경사항

### 4-1. allowUris 수정 (라인 31-37)
```java
// 변경 전
private final String[] allowUris = {
    "/sign-up",
};

// 변경 후
private final String[] allowUris = {
    "/login",              // ← 추가
    "/sign-up",
    "/swagger-ui/**",      // ← 추가
    "/swagger-resources/**", // ← 추가
    "/v3/api-docs/**",     // ← 추가
};
```

### 4-2. 의존성 주입 변경 (라인 28-29)
```java
// 변경 전
private final CustomUserDetailsService userDetailsService;

// 변경 후
private final JwtUtil jwtUtil;                              // ← 추가
private final CustomUserDetailsService customUserDetailsService; // ← 이름 변경
```

### 4-3. 폼 로그인 비활성화 (라인 47)
```java
// 변경 전
.formLogin(form -> form
    .defaultSuccessUrl("/swagger-ui/index.html", true)
    .permitAll()
)

// 변경 후
.formLogin(AbstractHttpConfigurer::disable)  // JWT로 전환하므로 폼 로그인 비활성화
```

### 4-4. JWT 필터 추가 (라인 48)
```java
.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
```

### 4-5. 예외 처리 추가 (라인 55-57)
```java
.exceptionHandling(exception -> exception
    .authenticationEntryPoint(authenticationEntryPoint())
)
```

### 4-6. 새 Bean 메서드 추가 (라인 62-75)
```java
@Bean
public JwtAuthFilter jwtAuthFilter() {
    return new JwtAuthFilter(jwtUtil, customUserDetailsService);
}

@Bean
public AuthenticationEntryPoint authenticationEntryPoint() {
    return new AuthenticationEntryPointImpl();
}
```

---

## 5. 새로 생성된 파일들

### 5-1. CustomUserDetails.java
- 위치: `global/security/CustomUserDetails.java`
- Spring Security UserDetails 구현
- JWT 토큰 생성 시 사용

### 5-2. JwtUtil.java
- 위치: `global/security/JwtUtil.java`
- 토큰 생성, 검증, 이메일 추출

### 5-3. JwtAuthFilter.java
- 위치: `global/security/JwtAuthFilter.java`
- 요청 헤더에서 JWT 토큰 추출 및 검증

### 5-4. AuthenticationEntryPointImpl.java
- 위치: `global/security/AuthenticationEntryPointImpl.java`
- 인증 실패 시 응답 처리

---

## 6. UserReqDTO.java 수정

**위치**: `domain/user/dto/req/UserReqDTO.java`

**추가된 코드**:
```java
public record LoginDTO(
    @NotBlank
    String email,
    @NotBlank
    String password
) {}
```

---

## 7. UserResDTO.java 수정

**위치**: `domain/user/dto/res/UserResDTO.java`

**추가된 코드**:
```java
@Builder
public record LoginDTO(
    Long userId,
    String accessToken
) {}
```

---

## 8. UserController.java 수정

**위치**: `domain/user/controller/UserController.java`

**추가사항**:
- 의존성: `MemberQueryService` 주입
- 엔드포인트: `@PostMapping("/login")`

---

## 9. MemberQueryService.java 수정

**위치**: `domain/user/service/query/MemberQueryService.java`

**추가된 메서드**:
```java
UserResDTO.LoginDTO login(UserReqDTO.LoginDTO dto);
```

---

## 10. MemberQueryServiceImpl.java 수정

**위치**: `domain/user/service/query/MemberQueryServiceImpl.java`

**구현 내용**:
- 이메일로 사용자 조회
- 비밀번호 검증
- JWT 토큰 생성 및 반환

---

## 11. UserConverter.java 수정

**위치**: `domain/user/converter/UserConverter.java`

**추가된 메서드**:
```java
public static UserResDTO.LoginDTO toLoginDTO(Users user, String accessToken) {
    return UserResDTO.LoginDTO.builder()
            .userId(user.getUserId())
            .accessToken(accessToken)
            .build();
}
```

---

## 테스트 방법

### 1단계: DB에 테스트 사용자 추가

```sql
-- USER 역할 사용자
INSERT INTO users (
  email, password, name, social_type, social_id,
  birth, address, gender, role, created_at, updated_at, deleted_at
) VALUES (
  'test@example.com',
  '1111',
  '테스트유저',
  'GENERAL',
  'test-user-id',
  '2000-01-01',
  '서울시 강남구',
  'OTHER',
  'ROLE_USER',
  NOW(),
  NOW(),
  NULL
);

-- ADMIN 역할 사용자
INSERT INTO users (
  email, password, name, social_type, social_id,
  birth, address, gender, role, created_at, updated_at, deleted_at
) VALUES (
  'admin@example.com',
  '1111',
  '관리자',
  'GENERAL',
  'admin-user-id',
  '1990-01-01',
  '서울시 강남구',
  'OTHER',
  'ROLE_ADMIN',
  NOW(),
  NOW(),
  NULL
);
```

### 2단계: 프로젝트 빌드 및 실행

```bash
./gradlew build
./gradlew bootRun
```

### 3단계: Swagger에서 로그인

1. http://localhost:8080/swagger-ui/index.html 접속
2. `/login` 엔드포인트 찾기
3. "Try it out" 클릭
4. 요청 본문:
```json
{
  "email": "test@example.com",
  "password": "1111"
}
```
5. "Execute" 클릭
6. 응답에서 `accessToken` 복사

### 4단계: Swagger에 토큰 등록

1. 상단의 "Authorize" 버튼 클릭
2. Value에 입력:
```
Bearer {복사한_accessToken}
```
3. "Authorize" 클릭
4. "Close" 클릭

### 5단계: 다른 API 테스트

토큰이 자동으로 모든 요청에 포함됨

---

## 완료!
