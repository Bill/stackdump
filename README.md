# stackdump

A couple static member functions for making stack traces, usually for debugging, in your Java project.

## Maven Usage

Clone this repo and then build+install the package into your local Maven repository with:

```bash
mvn install
```

Add this to your `pom.xml`:

```xml
<dependency>
  <groupId>io.pivotal.debug</groupId>
  <artifactId>stackdump</artifactId>
  <version>0.1.0</version>
  <scope>test</scope>
</dependency>
```

## Generating Stack Traces

To generate a newline-separated string of stack frames, skipping the nearest one and including the next four:

```java
asString(a(() -> stack().skip(1).limit(4))))
```

See the JavaDoc for the `StackDump` class and see the JUnit tests for examples.

