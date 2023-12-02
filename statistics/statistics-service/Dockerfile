FROM amazoncorretto:11-alpine-jdk
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n
COPY target/*.jar appStatistics.jar
CMD ["java","-jar","/appStatistics.jar"]