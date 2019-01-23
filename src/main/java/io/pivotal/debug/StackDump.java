package io.pivotal.debug;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StackDump {
  /**
   * Format a stream of stack frames as a newline-separated string.
   */
  public static String asString(final Stream<StackTraceElement> stack) {
    return stack.map(frame->frame.toString()).collect(Collectors.joining("\n"));
  }

  /**
   * A stream of the caller's stack frames, ordered nearest to farthest.
   *
   * Because it's a stream it's easy to limit the number of frames via {@link Stream#limit(long)},
   * and it's easy to skip frames in infrastructure methods that wish to remain
   * "invisible" via {@link Stream#skip(long)}.
   */
  public static Stream<StackTraceElement> stack() {
    final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
    return Arrays.stream(stackTrace).skip(1);
  }
}
