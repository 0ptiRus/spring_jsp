<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<style>
			<%@include file="/style.css"%>
		</style>
	</head>	
	<body>
		<div>
			<form action="/add" method="post">
				<div>
					<label for="money">Amount:</label>
					<input type="number" name="money" id="money">	
					<label for="reason">Reason:</label>
					<input name="reason" id="reason">
					<button>Send</button>
				</div>
			</form>
			<div>
			 <h2>All Expenses</h2>
			    <ul>
			        <c:forEach var="expense" items="${expenses}">
			            <li>${expense.reason} - ${expense.amount}</li>
			        </c:forEach>
			    </ul>
				
			</div>
		</div>
	</body>
</html>
