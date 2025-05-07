@echo off
set JAR=json-20230618.jar
javac -cp ".;%JAR%" *.java
java -cp ".;%JAR%" Main %*
