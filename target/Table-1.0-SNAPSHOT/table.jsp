<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Админ
  Date: 29.04.2022
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<style>
  th{
    background: #324960;
    color: white;
    padding: 2px;
  }
  td{
    border: slategray 1px solid;
    vertical-align: top;
    background: #f8f8f8;
    padding: 2px;
    display: table-cell;
  }
  table{
    border-spacing: 0;
    border-collapse: collapse;
    display: block;
  }
  .textVer{
    writing-mode: vertical-lr;
    text-orientation: mixed;
    background: #324960;
    padding-left: 2px;
    padding-right: 2px;
    padding-top: 2px;
    color: white;
    width: 20px;
    padding: 0;
  }
  .lecture{
    color: crimson;
  }
  .practice{
    color: darkslateblue;
  }
  .laboratory{
    color: darkseagreen;
  }
</style>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Schedule</title>
  </head>
  <body>
<%
  ArrayList<ArrayList<String>> fullTable = (ArrayList<ArrayList<String>>) request.getAttribute("table");
  ArrayList<Integer> timeMax = (ArrayList<Integer>) request.getAttribute("timeMax");
  ArrayList<String> timeName = (ArrayList<String>) request.getAttribute("timeName");

  ArrayList<String> Monday = fullTable.get(0);
  ArrayList<String> Tuesday = fullTable.get(1);
  ArrayList<String> Wednesday = fullTable.get(2);
  ArrayList<String> Thursday = fullTable.get(3);
  ArrayList<String> Friday = fullTable.get(4);
  ArrayList<String> Saturday = fullTable.get(5);

  int clas = 0;
  out.println("<table>");
  // The row of time
  out.println("<tr>");
  out.println("<th>");
  out.println("");
  out.println("</th>");
  for (int i = 0; i < timeMax.size(); i++){
    for (int j = 0; j < timeMax.get(i); j++){
      out.println("<th style="+ "\"width:250px\"" +">");
      out.println(timeName.get(i));
      out.println("</th>");
    }
  }
  out.println("</tr>");
  // Row of Monday
  out.println("<tr>");
  out.println("<td class = "+ "\"textVer\"" +">");
  out.println("Monday");
  out.println("</td>");
  for (int i = 0; i < timeMax.size(); i++){
    int m = 0;
    for (int j = 0; j < timeMax.get(i); j++){
      for (int n = 0; n < Monday.size(); n++){
        if (Monday.get(n).contains(timeName.get(i))){
          if (Monday.get(n).contains("online lecture")){
            out.println("<td class = "+ "\"lecture\"" +">");
          }else if (Monday.get(n).contains("online practice")){
            out.println("<td class = "+ "\"practice\"" +">");
          }else if (Monday.get(n).contains("laboratory")){
            out.println("<td class = "+ "\"laboratory\"" +">");
          }else{
            out.println("<td style="+ "\"width:250px\"" +">");
          }
          out.println(Monday.get(n).substring(12));
          out.println("</td>");
          Monday.remove(Monday.get(n));
          m++;
          break;
        }
      }
      if (j == m){
        for (int t = 0; t < timeMax.get(i) - m; t++){
          out.println("<td style="+ "\"width:250px\"" +">");
          out.println("");
          out.println("</td>");
        }
        break;
      }
    }
  }
  out.println("</tr>");
  // Row of Tuesday
  out.println("<tr>");
  out.println("<td class = "+ "\"textVer\"" +">");
  out.println("Tuesday");
  out.println("</td>");
  for (int i = 0; i < timeMax.size(); i++){
    int m = 0;
    for (int j = 0; j < timeMax.get(i); j++){
      for (int n = 0; n < Tuesday.size(); n++){
        if (Tuesday.get(n).contains(timeName.get(i))){
          if (Tuesday.get(n).contains("online lecture")){
            out.println("<td class = "+ "\"lecture\"" +">");
          }else if (Tuesday.get(n).contains("online practice")){
            out.println("<td class = "+ "\"practice\"" +">");
          }else if (Tuesday.get(n).contains("laboratory")){
            out.println("<td class = "+ "\"laboratory\"" +">");
          }else{
            out.println("<td style="+ "\"width:250px\"" +">");
          }
          out.println(Tuesday.get(n).substring(12));
          out.println("</td>");
          Tuesday.remove(Tuesday.get(n));
          m++;
          break;
        }
      }
      if (j == m){
        for (int t = 0; t < timeMax.get(i) - m; t++){
          out.println("<td style="+ "\"width:250px\"" +">");
          out.println("");
          out.println("</td>");
        }
        break;
      }
    }
  }
  out.println("</tr>");
  // Row of Wednesday
  out.println("<tr>");
  out.println("<td class = "+ "\"textVer\"" +">");
  out.println("Wednesday");
  out.println("</td>");
  for (int i = 0; i < timeMax.size(); i++){
    int m = 0;
    for (int j = 0; j < timeMax.get(i); j++){
      for (int n = 0; n < Wednesday.size(); n++){
        if (Wednesday.get(n).contains(timeName.get(i))){
          if (Wednesday.get(n).contains("online lecture")){
            out.println("<td class = "+ "\"lecture\"" +">");
          }else if (Wednesday.get(n).contains("online practice")){
            out.println("<td class = "+ "\"practice\"" +">");
          }else if (Wednesday.get(n).contains("laboratory")){
            out.println("<td class = "+ "\"laboratory\"" +">");
          }else{
            out.println("<td style="+ "\"width:250px\"" +">");
          }
          out.println(Wednesday.get(n).substring(12));
          out.println("</td>");
          Wednesday.remove(Wednesday.get(n));
          m++;
          break;
        }
      }
      if (j == m){
        for (int t = 0; t < timeMax.get(i) - m; t++){
          out.println("<td style="+ "\"width:250px\"" +">");
          out.println("");
          out.println("</td>");
        }
        break;
      }
    }
  }
  out.println("</tr>");
  // Row of Thursday
  out.println("<tr>");
  out.println("<td class = "+ "\"textVer\"" +">");
  out.println("Thursday");
  out.println("</td>");
  for (int i = 0; i < timeMax.size(); i++){
    int m = 0;
    for (int j = 0; j < timeMax.get(i); j++){
      for (int n = 0; n < Thursday.size(); n++){
        if (Thursday.get(n).contains(timeName.get(i))){
          if (Thursday.get(n).contains("online lecture")){
            out.println("<td class = "+ "\"lecture\"" +">");
          }else if (Thursday.get(n).contains("online practice")){
            out.println("<td class = "+ "\"practice\"" +">");
          }else if (Thursday.get(n).contains("laboratory")){
            out.println("<td class = "+ "\"laboratory\"" +">");
          }else{
            out.println("<td style="+ "\"width:250px\"" +">");
          }
          out.println(Thursday.get(n).substring(12));
          out.println("</td>");
          Thursday.remove(Thursday.get(n));
          m++;
          break;
        }
      }
      if (j == m){
        for (int t = 0; t < timeMax.get(i) - m; t++){
          out.println("<td style="+ "\"width:250px\"" +">");
          out.println("");
          out.println("</td>");
        }
        break;
      }
    }
  }
  out.println("</tr>");
  // Row of Friday
  out.println("<tr>");
  out.println("<td class = "+ "\"textVer\"" +">");
  out.println("Friday");
  out.println("</td>");
  for (int i = 0; i < timeMax.size(); i++){
    int m = 0;
    for (int j = 0; j < timeMax.get(i); j++){
      for (int n = 0; n < Friday.size(); n++){
        if (Friday.get(n).contains(timeName.get(i))){
          if (Friday.get(n).contains("online lecture")){
            out.println("<td class = "+ "\"lecture\"" +">");
          }else if (Friday.get(n).contains("online practice")){
            out.println("<td class = "+ "\"practice\"" +">");
          }else if (Friday.get(n).contains("laboratory")){
            out.println("<td class = "+ "\"laboratory\"" +">");
          }else{
            out.println("<td style="+ "\"width:250px\"" +">");
          }
          out.println(Friday.get(n).substring(12));
          out.println("</td>");
          Friday.remove(Friday.get(n));
          m++;
          break;
        }
      }
      if (j == m){
        for (int t = 0; t < timeMax.get(i) - m; t++){
          out.println("<td style="+ "\"width:250px\"" +">");
          out.println("");
          out.println("</td>");
        }
        break;
      }
    }
  }
  out.println("</tr>");
  // Row of Saturday
  out.println("<tr>");
  out.println("<td class = "+ "\"textVer\"" +">");
  out.println("Saturday");
  out.println("</td>");
  for (int i = 0; i < timeMax.size(); i++){
    int m = 0;
    for (int j = 0; j < timeMax.get(i); j++){
      for (int n = 0; n < Saturday.size(); n++){
        if (Saturday.get(n).contains(timeName.get(i))){
          if (Saturday.get(n).contains("online lecture")){
            out.println("<td class = "+ "\"lecture\"" +">");
          }else if (Saturday.get(n).contains("online practice")){
            out.println("<td class = "+ "\"practice\"" +">");
          }else if (Saturday.get(n).contains("laboratory")){
            out.println("<td class = "+ "\"laboratory\"" +">");
          }else{
            out.println("<td style="+ "\"width:250px\"" +">");
          }
          out.println(Saturday.get(n).substring(12));
          out.println("</td>");
          Saturday.remove(Saturday.get(n));
          m++;
          break;
        }
      }
      if (j == m){
        for (int t = 0; t < timeMax.get(i) - m; t++){
          out.println("<td style="+ "\"width:250px\"" +">");
          out.println("");
          out.println("</td>");
        }
        break;
      }
    }
  }
  out.println("</tr>");
  out.println("</table>");
%>

<%--  <form method="post" action="Servlet">--%>
<%--    Choose Day: <select name="week" >--%>
<%--      <option value="Monday">Monday</option>--%>
<%--      <option value="Tuesday">Tuesday</option>--%>
<%--      <option value="Wednesday">Wednesday</option>--%>
<%--      <option value="Thursday">Thursday</option>--%>
<%--      <option value="Friday">Friday</option>--%>
<%--      <option value="Saturday">Saturday</option>--%>
<%--    </select><br>--%>
<%--    Time: <input type="text" name="time" /><br>--%>
<%--    Teacher: <input type="text" name="teacher"/><br>--%>
<%--    Room: <input type="text" name="room"/><br>--%>
<%--    <input type="submit" value="Add" name="Add">--%>
<%--  </form>--%>

  </body>
</html>
