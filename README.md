# 재고 관리 서비스

## 주제
재고 관리 서비스는 상점이나 창고에서 보유하고 있는 제품들의 재고를 관리하고 추적하는 시스템입니다. 
사용자는 제품을 등록, 수정, 삭제하며, 재고의 수량을 관리할 수 있고, 재고가 특정 수준 이하로 떨어지면 알림을 받을 수 있습니다.

### Tech Stack
<div> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
</div>

### [User 기능]
- **로그인 및 회원가입**
    - 사용자 등록 및 인증 기능 제공.
    - 비밀번호 암호화 및 저장.

- **JWT (JSON Web Token)**
    - JWT를 사용하여 사용자 인증 및 인가 구현.

- **My Profile**
    - 사용자 이름, 등록한 재고 목록 관리 기능 제공.

### [Inventory 기능]

- **전체 조회 및 필터링**
    - 등록된 모든 재고 품목을 조회할 수 있음.
    - 제품명, 카테고리, 수량 등의 조건으로 필터링 가능.
    - 페이지네이션을 통해 데이터 분할 조회 지원.

- **단건 조회**
    - 특정 제품의 상세 정보 반환.
    - 제품의 이름, 설명, 수량, 가격 등의 정보 제공.

- **재고 등록, 수정, 삭제**
    - 새로운 재고 품목 등록 기능.
    - 등록된 재고 품목 수정 기능.
    - 재고 품목 삭제 기능.

- **수량 경고**
    - 재고 수량이 설정한 수치 이하로 떨어질 경우 알림을 발송.

- **수량 조정**
    - 재고 입출고에 따라 수량을 조정할 수 있는 기능.
    - 수동으로 수량을 추가하거나 제거할 수 있음.

- **레포트 생성**
    - 재고 상태와 변동 내역을 바탕으로 주간 또는 월간 레포트 생성.
    - 레포트를 Excel 형식으로 다운로드 가능

### [관리 기능]

- **Inventory History (수량 변화 이력)**
    - 재고 수량 변동에 대한 이력 관리.
    - 수량 변경 시 변경된 수량, 변경 전후의 수량, 변경 이유, 변경한 사용자 및 변경 일자를 기록.
    - 수량 변화 이력 조회 기능 제공.

- **관리자 기능**
    - 여러 관리자가 재고를 관리할 수 있도록 지원.
    - 특정 재고 품목의 수량이 최소 수량 이하로 떨어질 경우, 모든 관리자에게 알림을 전송.

- **알림 기능**
    - 수량 경고 기능은 수량 조절 기능을 통해서만 활성화.
    - SSE(Server-Sent Events)를 통해 실시간으로 알람을 전달.
