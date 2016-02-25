echo "running PA3 with input: " %1
@echo off
javac -d bin Assignment4/*.java
java -cp bin Assignment4/A4Driver %1 