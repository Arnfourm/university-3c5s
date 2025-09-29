package application;

import application_archetype.App;
import application_archetype.Mini_calc;

public class Application {
  public static void main (String[] args) {
    System.out.println("Hello world!");
    App.newMethod();
    System.out.println("Result of summing is: " + Mini_calc.calc_summ(1, 2));
  }
}
