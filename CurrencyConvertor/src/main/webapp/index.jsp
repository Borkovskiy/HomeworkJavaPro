<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Value</title>

</head>
<body>
<p><b>Select currency and enter value</b></p>
<form action="/CurrencyServlet" method="POST">
    <select name="currency">
        <c:forEach var="allCurrency" items="${allCurrency}">
            <option value="${allCurrency.value}">${allCurrency.key}
            </option>
        </c:forEach>
    </select>
    <br/><br/>
    <p><input type="text" name="value"><br></p>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>


