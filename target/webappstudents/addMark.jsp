<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/resources.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
<form action="${pageContext.request.contextPath}/addMark" method="post" commandName="addMark" class="well form-horizontal" id="addMark_form">
    <input type="hidden" name="id" value="<c:out value="${student.id}"></c:out>"/>
    <div class="form-group">
        <div  class="col-md-4  addStudentLabel col-md-offset-3"><h2> Add Mark To: <c:out value="${student.firstname} ${student.lastname} "/></h2></div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Profesor : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
    <select name="person.profesor" id="" class="form-control selectpicker">
    <c:forEach items="${profesors}" var="profesor">
        <option value="<c:out value="${profesor.id}"/>">${profesor.firstname} ${profesor.lastname}</option>
    </c:forEach>
    </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Discipline : </label>
        <div class="col-md-4 selectContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
    <select name="discipline" id="" class="form-control selectpicker">
    <c:forEach items="${disciplines}" var="discipline">
        <option value="<c:out value="${discipline.id}"/>">${discipline.title}</option>
    </c:forEach>
    </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Mark(s) : </label>
        <div class="col-md-4 selectContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
    <input path="1mark" id="" type="" name="mark"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-4">
            <button type="submit" value="submit"  class="btn btn-warning" >Send<span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>
</form>
</div>
</body>
</html>
