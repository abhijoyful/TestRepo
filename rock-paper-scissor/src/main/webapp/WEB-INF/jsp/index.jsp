<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Rock Paper Scissor</title>
<script>
        function fetch() {
	    var request = new XMLHttpRequest();
	    request.onreadystatechange = function() {
	       if (this.readyState == 4 && this.status == 200) {
	          var response = this.responseText;
		      console.log(response);
	       }
        };
        request.open("POST", "", true);
	    request.send();
         }
      </script>
</head>
<body>

	<form name="bmiForm">
		<p style="font-weight: bold">Choose game type and click on play</p>
		<table>
			<tr>
				<td>Computer vs Computer:</td>
				<td><input type="radio" name="gameType" value="cc" /></td>
			</tr>
			<tr>
				<td>You vs Computer:</td>
				<td><input type="radio" name="gameType" value="yc" /></td>
			</tr>
		</table>
		<input type="button" onclick="fetch()" value="Play" />
		<input type="reset"	value="Reset Game" name="reset" />
		<h2>${gameType}</h2>
	</form>

</body>
</html>