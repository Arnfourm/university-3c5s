package application;

import application_archetype.Mini_calc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
  @Test
  public void testWorkingTest() {
    assertTrue(true, "true test to test tests");
  }

  @Test
  public void testSummEquals() {
    int expected = 8;
    int a = 3;
    int b = 5;
    assertEquals(expected, Mini_calc.calc_summ(a, b));
  }
}
