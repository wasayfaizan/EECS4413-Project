# SoleMate E-commerce Store

## Team Members
David, Yusuf, 218914879  
 Abdul Wasay Faizan, 218513788  
 Shaiyan Azim 216308991


## Docker Pull Command:
docker pull davidy03/solemate:1.0

## Docker Create Image Steps (in terminal)
docker pull tomcat:9.0-jdk21-openjdk  
docker container run -d -p 8080:8080 tomcat:9.0-jdk21- openjdk  
docker stop "container-name" && docker rm "container-name" (we need port 8080)    

Create Dockerfile:  
FROM tomcat:9.0-jdk21-openjdk  
COPY ./SoleMate-EECS4413.war /usr/local/tomcat/webapps  

Build the Project:  
docker build -t solemate  
docker run -d -p 8080:8080 solemate  
curl http://localhost:8080/SoleMate-EECS4413/index.jsp (open the web project running on port 8080)  

