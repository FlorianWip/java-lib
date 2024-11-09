# Java-Lib
This is a simple collection of useful utilities for java.
It will be expanded in the future. I have to move old code into this library and use it. If you find something useful to add, feel free to create a PR or find something useful, feel free to use this
## Table of Contents

- [Includes](#includes)
    - [Utils](#utils)
    - [Features](#features)
    - [Language](#language)
- [Dependency](#dependency)
    - [Maven](#maven)
    - [Gradle](#gradle)
- [Migration](#migration)
    - [from v1.x.x to v2](#from-v1xx-to-v2)
    - [from v2.1.0 to v2.2.0](#from-v210-to-v220)

## Includes

### Utils
- FileUtil
- MathUtil
- StringUtil
- ReflectionUtil

### Features
- Config-System with JSON implementation
- ClassScanner

### Language
- Table (2 Keys, 1 Value)
- Tuple
- Triple

This library needs gson `v2.10.1`.
## Dependency
### Maven
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
    <version>2.3.0</version>
</dependency>
````
### Gradle
```
maven {
	url "https://repo.flammenfuchs.de/public"
}
```
```
implementation("de.flammenfuchs:java-lib:2.3.0")
```

## Migration
> Unspecified versions are not affecting your code and don't need migration
### from v1.x.x to v2
**Guava**<br>
In v1 the library used guava `v32.1.1-jre`.<br>
If you used guava from this library, it could happen, that you have to add it manually
into your project
<br><br>
**ClassScanner**<br>
The method `scan()` returned
``
List<? extends Class<?>>
``
before. It returns now
``
List<Class<?>>
``
.This is only a slight change. It should not affect your code normally
### from v2.1.0 to v2.2.0
**ClassScanner**<br>
ClassScanner now supports multiple implementations and is moved into a separate package.<br>
The public methods are not heavily affected. 