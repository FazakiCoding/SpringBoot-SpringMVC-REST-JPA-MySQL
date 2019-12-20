<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Employees List</h1>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Salary</th>
		<th>Fonction</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="employeeVo" items="${list}">
		<tr>
			<td>${empVo.id}</td>
			<td>${empVo.name}</td>
			<td>${empVo.salary}</td>
			<td>${empVo.fonction}</td>
			<td><a href="editemployee/${employeeVo.id}">Edit</a></td>
			<td><a href="deleteemployee/${employeeVo.id}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<br />
<a href="employeeform">Add New Employee</a>
