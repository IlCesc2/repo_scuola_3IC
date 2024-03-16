javac -classpath .\zuclib3.2.jar -d .\out\production\BoardGame -sourcepath .\src\ .\src\com\company\Main.java
javac -classpath .\zuclib3.2.jar -d .\out\production\BoardGame -sourcepath .\src\ .\src\com\company\Agents\used\*.java
java -Dfile.encoding=UTF-8 -classpath .\out\production\BoardGame;.\zuclib3.2.jar com.company.Main