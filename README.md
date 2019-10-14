# springboot.webflux.apiserver
고성능 API 서버 (high performance API server)

### Spring boot docker : docker file other 1 방식 
build argument 지정 방식

1. docker command 접속
2. gradle build 실행
3. docker build 및 실행
```bash
  sudo gradle build bootJar -x
  sudo docker build -t server-other1/hc-tracking-server .
  sudo docker images
  sudo docker run --name tracking.server.other1 -p 80:8080 -t server-other1/hc-tracking-server
  
  # docker option 순서는 -- > - 순으로 데쉬가 많은것 부터 선언하여 사용할것.
  # docker build -t {imageName}:{tagName} .
  
  docker {start/stop} {container name /container id}
  
```   

### Spring boot docker : docker file other 2 방식
build argument 명령어 외부 주입 방식 
 
1. docker command 접속
2. gradle build 실행
3. docker build 및 실행
```bash
  gradle build bootJar -x test --info
  docker build --build-arg JAR_FILE=build/libs/hc-tracking-server-0.0.1-SNAPSHOT.jar -t server-other2/hc-tracking-server .
  sudo docker images
  docker run --name tracking.server.other2 -p 80:8080 -t server-other2/hc-tracking-server
  
  docker {start/stop} {container name /container id}
  
```   


### Spring boot docker : docker file other 3 방식
최신 방법으로써 여러가지 레이러로 분리하여 종속성 단위로 패키징 하는 방식

1. docker command 접속
2. gradle build docker 명령어 실행  
3. docker image 확인
4. docker file 실행 
```bash
  # gradle docker build 실행   
  #sudo env "PATH=$PATH" gradle build docker -x test
  gradle build docker --info
  
  # docker image  확인
  sudo docker images
  
  # docker file 실행 
  # -t 옵션을 repository 는 build.gradle 의 docker task 에 선언되어 있다.
  sudo docker run -e "SPRING_PROFILES_ACTIVE=prod" --name "hc-tracking-server" -p 80:8080 -t com.cjenm.tracking/hc-tracking-server
  
  # option 
  # --name : container name
  # -t : repository 저장소 명 (tag name)
  # -e : 외부 JVM 옵션 설정
  # -d : 백그라운드 모드 
  # -p : port 설정 외부:내부
    
  docker {start/stop} {container name /container id} 
```


### docker hub image upload
```bash
# docker push ${repositoryName}:${tagName}


```