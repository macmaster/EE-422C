echo "running PA3 with input: " %1
@echo off
javac -d bin Assignment3/*.java
java -cp bin Assignment3/A3Driver %1 