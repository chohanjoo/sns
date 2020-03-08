# MWOHAE
Microservice SNS Project



## face
UI project



## Mwohae
REST API Server project



## 서비스 포트

- `/auth` [인증] :heavy_minus_sign: localhost:8080
- `/user` [유저정보] :heavy_minus_sign: localhost:8081
- `/post` [뉴스피드] :heavy_minus_sign: localhost:8082
- `/generator` [생성기] :heavy_minus_sign: localhost:8079 [Admin 계정만 접속 가능]

## 실행방법

- Generator

  * Intellij - File - Project Structure - Libraries - (+) 을 눌러

    oss/Oss External Libraries/Markov.jar 과 wordnet-random-name.jar 를 추가해준다.

## project Plan

1. 모노리틱 아키텍처 sns 개발
2. 마이크로서비스 아키텍처로 분리
3. 도커로 관리
4. 쿠버네티스로 관리



## Todo

### 1. Jwt 토큰 만료후 페이지 접근하면 오류 발생 (Jwt 토큰 만료 체크)

### ~~2.  Profile - 추천 친구 리스트에서 친구 리스트 제외	[feature/#7]~~

#### 	~~2-1. 추천 친구 알고리즘 추가 - 함께 아는 친구 순~~

### 3. Login 후 token 저장 실패시 오류 발생

### ~~4. Post - 최근 내 게시글 + 친구들 게시글	[feature/#7]~~

### 5. 3개의 서비스에 중복되는 코드 존재 (mysql query 중복 문제 해결)

