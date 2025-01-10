<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>500 - Error Page</title>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        ul {
            list-style-type: disc;
            padding-left: 20px;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>500 - Error Page</h1>
<ul>
    <li>Request from ${pageContext.errorData.requestURI} failed</li>
    <li>Servlet name: ${pageContext.errorData.servletName}</li>
    <li>Status code: ${pageContext.errorData.statusCode}</li>
    <li>Exception: ${pageContext.exception}</li>
    <li>Message from exception: ${pageContext.exception.message}</li>
</ul>
<p><a href="${pageContext.request.contextPath}/controller">Return to Main page</a></p>
</body>
</html>