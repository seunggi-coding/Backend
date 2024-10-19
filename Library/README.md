# 도서관 관리 시스템 📚

## 소개

이 프로젝트는 **도서관 관리 시스템**으로, 도서 대여 관련 기능을 중심으로 회원 정보와 도서 정보를 관리할 수 있는 시스템입니다. 회원가입, 로그인, 도서 목록 조회 및 상세 정보 확인 등의 기능을 포함하고 있으며, MongoDB와 Node.js를 기반으로 구축되었습니다. 클라이언트와 서버 간의 데이터 전송을 위해 Express.js와 REST API 구조를 사용하였습니다.

본 프로젝트는 아래와 같은 기능을 제공합니다:
- 회원가입
- 로그인
- 메인 페이지 이동
- 도서 목록 보기
- 도서 상세정보 보기

---

<br>

## 기능 설명 및 화면 구성

### 1. 회원가입
- 새로운 사용자를 등록할 수 있는 기능입니다. 회원 정보는 MongoDB에 저장되며, 각 회원의 정보를 관리할 수 있습니다.

  ![join](https://github.com/user-attachments/assets/8c770dee-0cd5-49a7-a727-955f980614bb)

### 2. 로그인
- 등록된 사용자는 로그인 기능을 통해 도서관 시스템에 접속할 수 있습니다. 로그인된 사용자는 도서 목록과 상세 정보를 조회할 수 있습니다.

  ![login](https://github.com/user-attachments/assets/29dd2719-0c50-407f-8e38-1def80c472b6)

### 3. 메인 페이지
- 로그인 후 메인 페이지로 이동하여 도서 목록 및 기타 기능을 사용할 수 있는 인터페이스를 제공합니다.

  ![main](https://github.com/user-attachments/assets/173b0637-fba1-43d7-9e58-474e83da55f5)

### 4. 도서 목록 보기
- 등록된 도서들의 목록을 확인할 수 있으며, 각 도서의 정보는 MongoDB에서 불러옵니다.

  ![list](https://github.com/user-attachments/assets/b9489bc5-5bca-4d36-b22a-3ed08a4981da)

### 5. 도서 상세정보 보기
- 선택한 도서의 상세 정보를 조회할 수 있습니다. 도서의 제목, 저자, 대여 상태 등을 포함하여 자세한 정보를 확인할 수 있습니다.

  ![detail](https://github.com/user-attachments/assets/8482329a-09af-4680-a2e3-cb945d59ab79)

---

<br>

## 사용 기술 스택

### Backend
<img src="https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white">
<img src="https://img.shields.io/badge/Express.js-000000?style=for-the-badge&logo=Express&logoColor=white">
<img src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white">
<img src="https://img.shields.io/badge/EJS-ffbf00?style=for-the-badge&logo=EJS&logoColor=black">

### Tools
<img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Github&logoColor=white">

---

<br>

## 프로젝트 팀원

| Backend Developer |
| :----------------: |
| ![문승기](https://github.com/seunggi-coding.png?size=120) |
| [문승기](https://github.com/seunggi-coding) |

---

이 README 파일은 도서관 관리 시스템의 핵심 내용을 다루고 있으며, 유지보수 및 확장을 위한 기본 자료로 활용될 수 있습니다.
