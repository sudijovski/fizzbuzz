# This Dockerfile is integrated with maven. The image will be built and tagged during the 'package' phase.
# to build the image you either need to run: mvn clean package OR
# JAR_FILE=<<path_to_jar>> docker build ...

FROM azul/zulu-openjdk-alpine:11

ARG JAR_FILE
ADD ${JAR_FILE} /sudijovski/fizzbuzz.jar

CMD ["java", "-jar", "/sudijovski/fizzbuzz.jar"]
