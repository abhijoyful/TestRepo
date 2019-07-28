<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rock Paper Scissor</title>
</head>
<body>

<form name="bmiForm" action="rockPaperScissor" method="POST">
<p style="font-weight: bold">Choose game type and click on play</p>
    <table>
        <tr>
            <td>Computer vs Computer:</td>
            <td><input type="radio" name="gameType" value="cc"/></td>
        </tr>
        <tr>
            <td>You vs Computer:</td>
            <td><input type="radio" name="gameType" value="yc"/></td>
        </tr>
    </table>
    <input type="submit" value="Play" name="find"/>
    <input type="reset" value="Reset Game" name="reset" />
    <h2>${result}</h2>
</form>

</body>
</html>