FROM java:openjdk-8-jdk-alpine 

COPY target/RPNCalculator-1.0.jar /RPNCalculator-1.0.jar

ENTRYPOINT ["java","-jar","/RPNCalculator-1.0.jar"]

