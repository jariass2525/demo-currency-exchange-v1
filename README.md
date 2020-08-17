# DEMO Currency Exchange v1

Service for currency exchange calculation.


## WARNING 

This service manages decimal data. Make sure you are using the correct Decimal Separator on request, depending of Where is it deployed.


## Run this demo, having the source code:

1-Compile with maven:
	
	mvn clean install

2-This is an executable fat JAR, so to run:

	java -jar [JAR_FILE_NAME]



## Repository:



## Images used in this project:

1-jarias1515/java8

Image based on [phusion/baseimage], with the installation of Java8 and all that you need to execute a simple Spring Boot JAR

https://hub.docker.com/r/jarias1515/java8


2-jarias1515/demoexchange

Image based on [jarias1515/java8], that runs this demo (JAR is inside) and expose it by port 9090

https://hub.docker.com/r/jarias1515/demoexchange

*You can find these images inside of [this_project]/docker] folder.



## Using this demo through Docker:

1-Define a folder, by example: /home/ubuntu/Desktop/dockerFiles

**If you're not interested on build java8 image on local, you always can use the one pushed to DockerHub. If so, skip to "5".


2-If you want to build java8 image from docker file, create a subfolder "java8":

	/home/ubuntu/Desktop/dockerFiles/java8

3-Copy "[this_project]/docker/DockerfileJava8" file to "java8", changing the name to just "Dockerfile"

4-Build the image:

	docker build -t jarias1515/java8 /home/ubuntu/Desktop/dockerFiles/java8


5-If you want to build demo image from docker file, create a subfolder "demo":

	/home/ubuntu/Desktop/dockerFiles/demo

6-Compile this project with maven and copy the JAR file to "demo":
	
	mvn clean install

7-Copy "[this_project]/docker/DockerfileDemo" file to "demo", changing the name to just "Dockerfile"

8-Build the demo imagen:

	docker build -t jarias1515/demoexchange /home/ubuntu/Desktop/dockerFiles/demo

9-Run it:

    docker run -it -p 9090:9090 --name demoexchange jarias1515/demoexchange

10-There is an endpoint to test if this service is alive (it's a GET one, so you can test it by browser):

    GET localhost:9090/currency-exchange/test


11-If you want to delete the container:

   docker rm demoexchange

12-If you want to delete any of these images:

   docker rmi jarias1515/java8

   docker rmi jarias1515/demoexchange











