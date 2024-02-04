<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1>Your Todos</h1>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Done?</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.id}</td>
				<td>${todo.description}</td>
				<td>${todo.targetDate}</td>
				<td>${todo.done}</td>
				<td>
					<a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a>
					<a href="update-todo?id=${todo.id}" class="btn btn-light">Update</a>
				</td>
			</tr>
		</c:forEach>
			</tbody>	
		</table>
		
		<a class="btn btn-primary" href="add-todo">Add Todo</a>
	</div>
<%@ include file="common/footer.jspf" %>