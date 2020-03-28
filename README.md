# MWOHAE

 [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/chohanjoo/mwohae/blob/master/LICENSE) [![Docker Images Version][npm-image]][npm-url]

> **Kubernetes Microservice SNS Project**
>
> 쿠버네티스로 관리하는 마이크로서비스 아키텍처의 Social Networking Service이다.
> Frontend는 `ReactJS` , Backend는 `springboot`,`mysql`,`spring cloud Netflix Oss` 를 사용하여 개발했다.



## Project Architecture

### Face

:link: Port : 3000

`ReactJS` 로 개발된 UI 이다. `MATERIAL-UI` 라이브러리를 사용하여 UI를 그렸으며 REST API를 호출하여 백엔드와 커뮤니케이션 한다. 

### Auth

:link: Port: 8080

`springboot` 로 개발된 Authentitation REST API Server 이다. stateless 를 유지하기 위해 `JWT` 토큰을 사용한다. 후에 Gateway와 User Project로 합칠 예정이다.

### User

:link: Port: 8081

`springboot` 로 개발된 User REST API Server 이다. 

### Post

:link: Port: 8082

`springboot` 로 개발된 Post REST API Server 이다.

### Generator

:link: Port: 8079

`springboot` 로 개발된 랜덤 유저, 포스트, 친구 생성기 REST API Server 이다. `Admin` 계정만 접근 가능하다.

### API Gateway

:link: Port: 8000

Spring Cloud Netflix API Gateway인 `Zuul` API Gateway 이다. 

### Service-registry

:link: Port: 8761

Spring Cloud Netflix Service Registry인 `Eureka` Service registry 이다.



## How to Install or Run

### [ Dev ]

`node` , `java 8`, `Mysql` 이 설치되었음을 가정한다.

개발환경은 Mac OS임을 밝힌다.

#### Mysql

~~~shell
$ mysql.server run
~~~

#### Generator

- Add External Library

  (1). Intellij - File - Project Structure - Libraries - (+) 을 누른다.

  (2). oss/Oss External Libraries/Markov.jar 과 wordnet-random-name.jar 를 추가한다.

#### Run

~~~shell
$ cd shell_script

$ ./run.sh
~~~

:small_red_triangle: Generator는 Intellij 나 Eclipse 로 라이브러리를 추가후 실행한다. 



### REST API Document [Swagger]

- 1> Eureka - localhost:8761
- 2> Generator - localhost:8079/swagger-ui.html
- 3> Auth - localhost:8080/swagger-ui.html
- 4> User - localhost:8081/swagger-ui.html
- 5> Post - localhost:8082/swagger-ui.html

## 

### [ Live ]

`Docker` 및 `Kubernetes` 가 설치되었음을 가정한다.

#### Maven build

~~~shell
$ cd shell_script

$ ./mvn_install.sh
~~~



#### Docker build

~~~shell
$ cd shell_script

$ vim make_docker_images.sh
VERSION='the version you want'

$ ./make_docker_images.sh
~~~



#### Pod, Deployment, Service Create

~~~shell
$ cd shell_script

$ ./apply.sh
~~~



#### Mysql Pod Create

~~~shell
$ cd shell_script

$ kubectl apply -f mysql-pv-volume.yaml
$ kubectl apply -f mysql-deployment.yaml
~~~



#### Verification

~~~shell
$ kubectl get all
~~~

![kubectl_get_all](/assets/kubectl_get_all.png)



#### port forwarding

~~~shell
$ kubectl port-forward svc/gateway 8000:8000

$ kubectl port-forward svc/face 3000:3000
~~~

:heavy_dollar_sign: **localhost:3000 으로 접속한다.**



## Documentation 

[Database Schema](https://chohanjoo.github.io/project/2020/02/25/데이터베이스-설계/)

[Used Open Source](https://chohanjoo.github.io/project/2020/03/04/단어,-문장-랜덤-생성기/)

[도커 이미지 생성 및 쿠버네티스 배포](https://chohanjoo.github.io/project/cloud/2020/03/14/Docker-이미지-생성-및-배포/)

[Kubernetes Mysql Pod 생성](https://chohanjoo.github.io/project/cloud/2020/03/18/kubernetes-mysql-pod-생성/)

[Spring Cloud Netflix](https://chohanjoo.github.io/project/cloud/2020/03/26/Netflix-Cloud-Oss/)



## Examples



## License

Mwohae is [MIT licensed](./LICENSE) .



## Todo

### 1. Jwt 토큰 만료후 페이지 접근하면 오류 발생 (Jwt 토큰 만료 체크)

### ~~2.  Profile - 추천 친구 리스트에서 친구 리스트 제외	[feature/#7]~~

#### 	~~2-1. 추천 친구 알고리즘 추가 - 함께 아는 친구 순~~

### 3. Login 후 token 저장 실패시 오류 발생

### ~~4. Post - 최근 내 게시글 + 친구들 게시글	[feature/#7]~~

### 5. 3개의 서비스에 중복되는 코드 존재 (mysql query 중복 문제 해결)

### 6. [ 스프링 부트를 이용한 마이크로 서비스 개발 ] 책 p.195 인스턴스 종료시 에러 해결하는 로드 밸런싱 전략 미세 조정 및 하이스트릭스로 서킷 브레이커 구현 시 에러 발생

### ~~7. 간헐적인 CORS 문제 발생 [feature/#15]~~

### 8. 현재 Spring Security가 모든 MSA service에 포함되어 있지만 API Gateway로 변경

### 9. LIve에서 Service registry - url 를 사용함 ==> serviceId 로 변경



 <!-- Markdown link & img dfn's -->

[npm-image]: https://img.shields.io/docker/v/chohanjoo/mwohae-face/v0.1.2?logoColor=green&amp;style=flat-square
[npm-url]: https://hub.docker.com/repository/docker/chohanjoo/mwohae-face

