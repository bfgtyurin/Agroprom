<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome!</title>
</head>
<body>
<h1>Welcome!</h1>
<p>${message}
    <br>
<#list categories as category>
    ${category.id}
    ${category.name}
<br>
</#list>
</body>
</html>  