package io.pivotal.debug.test;

import static io.pivotal.debug.StackDump.stack;
import static io.pivotal.debug.StackDump.asString;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * This class won't compile unless methods we expect to be public, are public.
 */
public class AccessTest {

  @Test
  public void testStackMethodVisible() {
    final Stream<StackTraceElement> stack = stack();
  }

  @Test
  public void testStackStringMethodVisible() {
    final String trace = asString(stack());
  }
}
