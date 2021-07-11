<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <title>Ссылка</title>
    <style>
        .c {
            border: 1px solid #333;
            display: inline-block;
            padding: 5px 15px;
            text-decoration: none;
            color: #000;
        }

        .c:hover {
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
            background: linear-gradient(to bottom, #fcfff4, #e9e9ce);
            color: #a00;
        }
    </style>
</head>

<body>
<a href="/RandomCat" class="c">RandomCatDB</a>
<p><img src="${cat}"></p>

<img src="data:image/jpg;base64,${catDB.base64Image}" >
</body>
</html>