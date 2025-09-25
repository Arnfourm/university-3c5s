package com.simple_java_application;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.Period;

public class Application extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      String birthDay = request.getParameter("birthday-date");
      
      LocalDate date = LocalDate.parse(birthDay);
      LocalDate today = LocalDate.now();

      Period age = Period.between(date, today);

      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title> Calculate some values </title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h3> Количество полных дней, месяцев и лет: </h3>");
      out.println("<p>" + age.getYears() + " - полных лет, " + age.getMonths() + " - полных месяцев, " + age.getDays() + " - полных дней"  + "</p>");
      out.println("</body>");
      out.println("</html>");
  }
}
