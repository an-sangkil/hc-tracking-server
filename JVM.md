

```shell script
    # -Dcom.sun.management.jmxremote.local.only=false : localOnly가 true 켜져있으면 remote 연결이 안될 수 있다.
    # -Dcom.sun.management.jmxremote.port=1099 : remote port설정
    # -Dcom.sun.management.jmxremote.ssl=false : ssl접속설정
    # -Dcom.sun.management.jmxremote.authenticate=false : 접속인증 설정. true하면 인증관련된 추가 설정 필요
    # -Djava.rmi.server.hostname=210.221.235.208 : remote서버 ip, ifconfig로 확인
    # -Dcom.sun.management.jmxremote.rmi.port=1099 : rmi포트 설정

    nohup java -jar -Xmx3g -Xms3g -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=210.221.235.208 -Dcom.sun.management.jmxremote.rmi.port=1099 ./hc-tracking-server-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
    nohup java -jar -Xmx3g -Xms3g -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.rmi.port=1099 ./hc-tracking-server-0.0.1-SNAPSHOT.jar

    nohup java -jar -Xmx3g -Xms3g ./hc-tracking-server-0.0.1-SNAPSHOT.jar
```
