# 재고 관리 서비스

## 주제
재고 관리 서비스는 상점이나 창고에서 보유하고 있는 제품들의 재고를 관리하고 추적하는 시스템입니다. 사용자는 제품을 등록, 수정, 삭제하며, 재고의 수량을 관리할 수 있고, 재고가 특정 수준 이하로 떨어지면 알림을 받을 수 있습니다.

## 목표
인벤토리 관리, 재고 관리, 수량 경고 및 이력 추적 기능을 통해 효율적인 재고 관리 환경을 제공합니다.

### Tech Stack
<div> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
</div>

## [User 기능]

### 로그인 및 회원가입
- 사용자 등록 및 인증 기능 제공.
- 비밀번호 암호화 및 저장.

### JWT (JSON Web Token)
- JWT를 사용하여 사용자 인증 및 인가를 구현.

## [Inventory 기능]

### 인벤토리 추가, 수정, 삭제
- 새로운 인벤토리 추가 기능 (추가한 유저가 관리자).
- 기존 인벤토리 정보 수정 기능.
- 인벤토리 삭제 기능.

### 사용자 초대 및 권한(관리자)
- 관리자가 링크를 통해 다른 사용자를 초대.
- 역할 지정 (보기 전용, 수정, 관리자 등).

### 카테고리 관리(관리자)
- 새로운 카테고리 등록, 수정, 삭제 기능.

### 재고 관리(관리자)
- 새로운 재고 품목 등록 기능 (재고 품목명, 카테고리, 가격, 수량, 최소 수량, 공급자 정보).
- 등록된 재고 품목 수정 기능.
- 재고 품목 삭제 기능.

### 전체 조회 및 필터링
- 등록된 모든 재고 품목을 조회할 수 있음.
- 제품명, 카테고리, 수량 등의 조건으로 필터링 가능.
- 페이지네이션을 통해 데이터 분할 조회를 지원.

### 단건 조회
- 특정 제품의 상세 정보 반환.
- 제품의 이름, 카테고리, 수량, 가격, 기록 정보 제공.

### 수량 경고
- 재고 수량이 설정한 수치 이하로 떨어질 경우 알림을 발송.
- SSE(Server-Sent Events)를 통해 실시간으로 알람을 전달.
- 특정 재고 품목의 수량이 설정된 최소 수량 이하로 떨어질 경우, 해당 재고 알람을 관리자에게 전송.

### 수량 조정
- 재고 입출고에 따라 수량을 조정할 수 있는 기능.
- 일반적인 수량 조정은 해당 인벤토리에 접근 권한이 있는 유저가 수행할 수 있음.
- 특별한 상황(예: 손실, 반품 등)에서의 수량 조정은 관리자만 수행할 수 있으며, 변경 이유를 기록.

### 수량 변화 기록
- 수량 변화 이력 조회 기능 제공.
- 재고 상태와 변동 내역을 바탕으로 주간 또는 월간 레포트 생성.
- 레포트를 Excel 형식으로 다운로드 가능.