package com.tfg_janda.stages.java


import com.tfg_janda.stages.DockerBuildBaseStage

class JavaBuildStage extends DockerBuildBaseStage {

    JavaBuildStage(String stageName, String dockerRegistry, String projectName) {
        super(stageName, dockerRegistry, projectName)
    }


    def  dockerImage(){
        return "FROM openjdk:8 as builder\n" +
                "\n" +
                "COPY . /home/gradle/src\n" +
                "WORKDIR /home/gradle/src\n" +
                "RUN ./gradlew build\n" +
                "\n" +
                "FROM openjdk:8-jre-slim\n" +
                "EXPOSE 8080\n" +
                "COPY --from=builder /home/gradle/src/build/libs/artifact.jar /app/\n" +
                "WORKDIR /app\n" +
                "ENTRYPOINT [\"java\",\"-jar\",\"artifact.jar\"]"
    }
}
