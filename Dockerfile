
########################## other 1 ##############################
#FROM openjdk:11-jdk
#VOLUME /tmp
## The application's jar file
#ARG JAR_FILE=build/libs/hc-tracking-server-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} tracking-server-springboot.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tracking-server-springboot.jar"]

########################## other 2 ##############################
FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


########################## other 3 ##############################
#FROM openjdk:11-jdk
#VOLUME /tmp
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.tracking.server.HcTrackingServerApplication"]