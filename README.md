# Java-Lib
This is a simple collection of useful utilities for java.
It will be expanded in the future. I have to move old code into this library and use it. If you find something useful to add, feel free to create a PR or find something useful, feel free to use this

## Includes
This library includes guava `v32.1.1-jre`.

- FileUtil
- Formatter (e.g. Duration)
- JSON Config System (uses/needs gson)
- ClassScanner (for reflections)
- ReflectionUtil
- Simple Logging

## Maven
````
<repository>
  <id>flammenfuchs-repo-public</id>
  <name>Flammenfuchs_YT's Repository</name>
  <url>https://repo.flammenfuchs.de/public</url>
</repository>
````
````
<dependency>
    <groupId>de.flammenfuchs</groupId>
    <artifactId>java-lib</artifactId>
    <version>1.4.3</version>
</dependency>
````
## Gradle
```
maven {
	url "https://repo.flammenfuchs.de/public"
}
```
```
implementation("de.flammenfuchs:java-lib:1.4.3")
```