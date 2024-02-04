		<%@ include file="common/header.jspf" %>
		<%@ include file="common/navigation.jspf" %>
		<div class="container">
			<h1>Update Todo Page</h1>
			<p style="color: red">${error}</p>
			<form:form modelAttribute="todo" class="form" method="post" action="/update-todo">
				<fieldset>
					<form:label path="">Description: </form:label>
					<form:input class="form-control" required="required" type="text" path="description" />
					<form:errors  class="text-warning form-label" path="description" />
				</fieldset>
				<fieldset>
					<form:label path="">Target Date: </form:label>
					<form:input class="form-control" type="date" path="targetDate"/>
					<form:errors class="text-warning form-label" path="targetDate" />
				</fieldset>
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="done"/>
				<input class="btn btn-primary mt-2" type="submit"/>
			</form:form>
		</div>
		<%@ include file="common/footer.jspf" %>