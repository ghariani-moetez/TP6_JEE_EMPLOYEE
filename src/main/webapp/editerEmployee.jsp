<%@ page language="java" contentType="text/html; charset=windows-1256"
pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="header.jsp" %>
<p></p>
<div class="container">
<div class="card">
<div class="card-header">
Modification des Employees
</div>
<div class="card-body">
<form action="update.do" method="post">
<div class="form-group">
<label class="control-label">ID Employee :</label>
<input type="text" name="id" class="form-control"
value="${employee.idEmployee}"/>
</div>

<div class="form-group">
<label class="control-label">Nom Employee :</label>
<input type="text" name="nom" class="form-control"
value="${employee.nomEmployee}"/>
</div>


<div class="form-group">
<label class="control-label">Prenom Employee :</label>
<input type="text" name="prenom" class="form-control"
value="${employee.prenomEmployee}"/>
</div>

<div class="form-group">
<label class="control-label">salaire :</label>
<input type="text" name="salaire" class="form-control" value="${employee.salaire}"/>
</div>
<div class="form-group">
    <label class="control-label">Département :</label>
    <select name="departement" class="form-control">
        <option value="${employee.departement.idDep}" selected>${employee.departement.nomDep}</option>
        <c:forEach items="${depModel.departements}" var="dep">
            <c:if test="${dep.idDep != employee.departement.idDep}">
                <option value="${dep.idDep}">${dep.nomDep}</option>
            </c:if>
        </c:forEach>
    </select>
</div>

<div>
<button type="submit" class="btn btn-primary">Modifier</button>
</div>
</div>
</form>
</div>
</div>
</div>
</body>
</html>
