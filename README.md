# Servlet2Spring
Servlet2Spring은 기존의 Servlet/JSP 기반 웹 애플리케이션을 Spring 프레임워크로 전환하는 과정을 다루는 프로젝트입니다.

이 프로젝트에서는 톰캣을 활용한 JSP 기반 웹 애플리케이션 개발부터 시작하여, 점진적으로 Spring MVC 및 Spring Boot로 마이그레이션하는 과정을 경험할 예정입니다.


## 🚀 프로젝트 목표
- Servlet/JSP와 Spring 프레임워크의 구조 및 차이 이해
- 점진적 마이그레이션을 통한 안정적 전환 방법 학습
- 실무 적용 가능한 Spring Boot 애플리케이션 구현


## 🛠️ 기술 스택
- OS: macOS Sonoma 14.5 (Apple M2)
- Java: JDK 21
- Backend: Java, Servlet, JSP, Spring MVC, Spring Boot
- Build Tool: Maven
- Application Server: Tomcat 10 (Jakarta EE 9+)
- Database: MySQL 9.2.0 
- Others: Lombok, Log4j2
- Frameworks & Libraries:
  - Spring Framework 6.2.3
  - Spring Boot 3.2.6
  - Hibernate Validator 8.0.1.Final

## 📂 마이그레이션 단계
1. JSP 기반 버전
   - MVC 패턴 적용
   - 기본 CRUD 기능 구현
2. Spring MVC 전환
   - DispatcherServlet 활용
   - DI, AOP 도입
3. Spring Boot 마이그레이션
   - 의존성 관리 단순화
   - 내장 Tomcat 환경 구성
   - 환경 설정 및 배포 자동화

## 📈 성과
- Servlet/JSP와 Spring의 아키텍처 차이 이해
- 환경 설정, 의존성 관리, 빌드 자동화 경험
- 유지보수성과 확장성이 향상된 구조 설계

## 🔮 향후 계획
- Spring Security 적용 
- API 서버와 JWT 적용
- AWS를 활용한 배포 환경 구성