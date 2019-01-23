package io.pivotal.debug;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;


import static io.pivotal.debug.StackDump.stack;
import static io.pivotal.debug.StackDump.asString;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class StackDumpTest {

  /**
   * These helpers come first because the expected results contain source line numbers.
   * By putting these first, test changes are less likely to change those line numbers.
   */
  private static Stream<StackTraceElement> a(final Supplier<Stream<StackTraceElement>> dumper) {
    return b(dumper);
  }

  private static Stream<StackTraceElement> b(final Supplier<Stream<StackTraceElement>> dumper) {
    return c(dumper);
  }

  private static Stream<StackTraceElement> c(final Supplier<Stream<StackTraceElement>> dumper) {
    return dumper.get();
  }

  @Test
  public void testStackString() {
    assertThat(asString(a(() -> stack()).limit(4))).contains("io.pivotal.debug.StackDumpTest.lambda$testStackString$0(StackDumpTest.java:36)\n"
        + "io.pivotal.debug.StackDumpTest.c(StackDumpTest.java:31)\n"
        + "io.pivotal.debug.StackDumpTest.b(StackDumpTest.java:27)\n"
        + "io.pivotal.debug.StackDumpTest.a(StackDumpTest.java:23)");
  }

  @Test
  public void testTop() {
    stack().limit(1).forEach(frame->assertThat(frame.getMethodName()).isEqualTo("testTop"));
  }

  @Test
  public void testDeepLimit() {
    assertIterableEquals(
        Arrays.asList("lambda$testDeepLimit$2","c","b","a"),
        asIterable(a(() -> stack().limit(4)).map(frame->frame.getMethodName()))
    );
  }

  @Test
  public void testDeepSkip() {
    assertIterableEquals(
        Arrays.asList("b","a"),
        asIterable(a(() -> stack().skip(2).limit(2)).map(frame->frame.getMethodName()))
    );
  }

  /**
   * Treat a Stream as an Iterable.
   *
   * I did not make this up. See: <a href="http://www.lambdafaq.org/how-do-i-turn-a-stream-into-an-iterable/">How can I turn a Stream into an Iterable?</a>
   *
   * @param s is the Stream
   * @param <T>
   * @return the stream as an Iterable
   */
  private static <T> Iterable<T> asIterable(final Stream<T> s) {
    return s::iterator;
  }
}