<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/
loose.dtd">
<html>
<head>
<title>Creer professeur</title>
</head>
<body>
	<form:form method="post" modelAttribute="creation"
		action="creerProfesseur">
		<spring:message code="creation.professeur.libelle.nom" />
		<form:input path="nom"/>
		<%-- <b><i><form:errors path="nom" cssclass="error" /></i></b> --%>
		<br>
		<spring:message code="creation.professeur.libelle.prenom" />
		<form:input path="prenom" />
		<b><i><form:errors path="prenom" cssclass="error" /></i></b>
		<br>
		<spring:message code="creation.professeur.libelle.date" />
		<form:input path="datenaissance"/>
		<b><i><form:errors path="datenaissance" cssclass="error" /></i></b>
		<br>
		<spring:message code="creation.professeur.libelle.adresse" />
		<form:input path="adresse" />
		<b><i><form:errors path="adresse" cssclass="error" /></i></b>
		<br>
		<spring:message code="creation.professeur.libelle.sexe" />
		<form:input path="sexe"/>
		<b><i><form:errors path="sexe" cssclass="error" /></i></b>
		<br>
		
		<input type="submit" />
	</form:form>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Adresse</th>
				<th>Sexe</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listeProfesseurs}" var="prof">
				<tr>
					<td><c:out value="${prof.id}" /></td>
					<td><c:out value="${prof.nom}" /></td>
					<td><c:out value="${prof.prenom}" /></td>
					<td><c:out value="${prof.datenaissance}" /></td>
					<td><c:out value="${prof.adresse}" /></td>
					<td><c:out value="${prof.sexe}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>