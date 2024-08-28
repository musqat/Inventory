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
- [ ] 회원 가입
- [ ] 인증 (링크)
- [ ] 로그인 (JWT 발행)
- [ ] 로그아웃 (리프레쉬 토큰 무효화)

## [Inventory 기능]

### 인벤토리 추가, 수정, 삭제(관리자)
- [ ] 인벤토리 추가
- [ ] 인벤토리 수정
- [ ] 인벤토리 삭제

### 사용자 초대 및 권한(관리자)
- [ ] 사용자 초대 (링크 생성)
- [ ] 사용자 권한 설정 (일반, 관리자)

### 카테고리 관리(관리자)
- [ ] 카테고리 추가
- [ ] 카테고리 수정
- [ ] 카테고리 삭제

### 재고 관리(관리자)
- [ ] 재고 품목 추가(제품 명, 카테고리, 설명, 수량, 가격, 최소수량, 공급자정보)
- [ ] 재고 품목 수정
- [ ] 재고 품목 삭제

### 전체 조회 및 필터링
- [ ] 재고 조회
- [ ] 제품 명, 카테고리, 설명, 수량, 가격, 최소수량, 공급자정보 조건으로 필터링

### 단건 조회
- [ ] 특정 제품의 상세 정보(카테고리, 설명, 수량, 가격, 최소수량, 공급자정보)

### 수량 경고
- [ ] 수량 경고 설정
- [ ] 실시간 알람 (SSE)
- [ ] 관리자 알람 전송

### 수량 조정
- [ ] 일반 수량 조정
- [ ] 특별 상황 수량 조정(관리자)
- [ ] 수량 변화 기록

### 레포트 관리
- [ ] 주간/월간 레포트 생성
- [ ] 레포트 Excel 다운로드

### ERD
![ims](https://github.com/user-attachments/assets/9912f5a1-24c5-4a98-9a94-ad7b5db37536)
