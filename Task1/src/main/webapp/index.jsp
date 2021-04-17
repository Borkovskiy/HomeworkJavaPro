<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Questionary</title>
</head>
<body>
<h1>Hello, please select the answers in the questionnaire</h1>
<form action="StatisticServlet" method="POST" >
    <p><b>How are you?</b></p>
    <p><input type="radio" name="mood" value="moodGood" checked>Good<br></p>
    <p><input type="radio" name="mood" value="moodSad">Sad<br></p>

    <p><b>Have you studied JS?</b></p>
    <p><input type="radio" name="js" value="jsYes" checked>Yes<Br></p>
    <p><input type="radio" name="js" value="jsNo">No<Br></p>



    <p><b>Do you want to learn java?</b></p>
    <p><input type="radio" name="java" value="javaYes" checked>Yes<Br></p>
    <p><input type="radio" name="java" value="javaNo">No<Br></p>

        <p><input type="submit" value="Submit"></p>
    </form>
</body>
</html>