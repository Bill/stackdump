# stackdump

A couple static member functions for making stack traces, usually for debugging, in your Java project.

## Build

Clone this repo and then build+install the package into your local Maven repository with:

```bash
mvn install
```

## Maven

Add this to your `pom.xml`:

```xml
<dependency>
  <groupId>io.pivotal.debug</groupId>
  <artifactId>stackdump</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Gradle

Add this to your `build.gradle`:

```gradle
compile('io.pivotal.debug:stackdump:0.1.0')
```

## Generating Stack Traces

To generate a newline-separated string of the first four (nearest four) stack frames use an expression like:

```java
asString(a(() -> stack().limit(4))))
```

Say you've got a logging method you'd like to call from multiple places, but you don't want the frame for the logging method to appear in your output. Easy&mdash;just skip a frame:

```java
private void logTxClosed() {
  logger.info("TXState closed txid: {}\n{}",
      getTransactionId(),
      StackDump.asString(StackDump.stack().skip(1).limit(8)));
}
```

See the JavaDoc for the `StackDump` class and see the JUnit tests for examples.

