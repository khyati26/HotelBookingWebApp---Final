FROM tomcat:8.5.31-jre8
RUN rm -rvf /usr/local/tomcat/webapps/ROOT
ADD  /target/*.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
EXPOSE 8080
#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]