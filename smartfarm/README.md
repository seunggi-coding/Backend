# SmartFarm 관리 시스템 🌱

## 소개

이 프로젝트는 **대학교 '고급웹프로그래밍' 강의**에서 진행한 **SmartFarm 관리 시스템**입니다. 작물 관리와 사용자 관리 기능을 중심으로, 회원 정보와 작물 정보를 효율적으로 관리할 수 있는 시스템입니다. 회원가입, 로그인, 작물 정보 조회, 문의 게시판, 공지사항 등의 다양한 기능을 제공하며, Spring Boot와 MySQL을 기반으로 구축되었습니다.

본 프로젝트는 다음과 같은 기능을 제공합니다:
- 회원가입
- 로그인
- 메인 페이지 이동
- 작물 정보 확인
- 문의 게시판
- 공지사항
- 회원 정보 수정
- 관리자 기능과 회원 기능 분류

---

<br>

## 기능 설명 및 화면 구성

### 1. 메인 페이지
- 로그인 후 메인 페이지로 이동하여 작물 정보, 공지사항, 문의 게시판 등의 기능을 사용할 수 있는 인터페이스를 제공합니다.

  ![main](https://github.com/user-attachments/assets/990504a9-5bf6-4327-8c28-3c53dd547016)

### 2. 회원가입
- 새로운 사용자를 등록할 수 있는 기능입니다. 회원 정보는 MySQL에 저장되며, 각 회원의 정보를 관리할 수 있습니다.

  ![join](https://github.com/user-attachments/assets/5a849c91-42a1-484f-82a2-7c2470db5f2a)

### 3. 로그인
- 등록된 사용자는 로그인 기능을 통해 시스템에 접속할 수 있으며, 각 기능에 접근할 수 있는 권한을 부여받습니다.

  ![login](https://github.com/user-attachments/assets/9f8d2500-b5ad-4cf8-b200-0e0eb204896b)

### 4. 작물 정보 확인
- 사용자는 등록된 작물 정보를 확인할 수 있습니다. 작물의 이름, 생육 상태, 필요한 관리 작업 등을 확인할 수 있습니다.

  ![list](https://github.com/user-attachments/assets/18537da2-a08b-4059-8929-05f7980bb29c)
  ![list_add](https://github.com/user-attachments/assets/e621d139-6e5b-498e-b868-6d0c99fd9b23)

### 5. 문의 게시판
- 사용자는 문의 게시판을 통해 질문을 올려 관리자에게 답변을 받을 수 있습니다.

  ![qna](https://github.com/user-attachments/assets/b77273d3-fb27-47fc-acbc-96d3c44b23bf)
  ![qna_add](https://github.com/user-attachments/assets/afd181d6-1fdc-4216-b814-190d07782853)

### 6. 공지사항
- 관리자는 공지사항을 작성하여 사용자들에게 중요한 정보를 전달할 수 있습니다.

  ![notice](https://github.com/user-attachments/assets/66247fce-a308-47f7-b762-3ba1ae4b128e)
  ![notice_add](https://github.com/user-attachments/assets/49da3f13-50e9-4e74-a341-1ce82e731c1c)

### 7. 회원 정보 수정
- 사용자는 자신의 회원 정보를 수정할 수 있습니다.

  ![mypage](https://github.com/user-attachments/assets/a4663615-1c7f-4c19-ae4f-9b2f02e2f995)

---

<br>

## 사용 기술 스택

### Backend
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=Hibernate&logoColor=white">
<img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">

### Tools
<img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Github&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=IntelliJIDEA&logoColor=white">

---

<br>

## 프로젝트 팀원

| Backend Developer |
| :----------------: |
| ![문승기](https://github.com/seunggi-coding.png?size=120) |
| [문승기](https://github.com/seunggi-coding) |

---

이 README 파일은 **고급웹프로그래밍 강의** 중 개발된 SmartFarm 관리 시스템의 핵심 내용을 다루고 있으며, 유지보수 및 확장을 위한 기본 자료로 활용될 수 있습니다.
