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
<form action="${pageContext.request.contextPath}/editStatus" method="post" commandName="editStatus" class="well form-horizontal" id="editStatus_form">
    <input type="hidden" name="id" value="<c:out value="${student.librarySubscription.id}"></c:out>"/>
    <div class="form-group">
        <div  class="col-md-4  addStudentLabel col-md-offset-3"><h2> Edit Status To: <c:out value="${student.firstname} ${student.lastname} "/></h2></div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Status : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-stats"></i></span>
    <select name="status" id=""  class="form-control selectpicker">
    <c:forEach items="${Status}" var="status">
        <option value="<c:out value="${status}"/>">${status}</option>
    </c:forEach>
    </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Start Date : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
        <input path="person.firstname" id="" name ="startdate" value="<c:out value="${student.librarySubscription.startdate}"></c:out>" placeholder="Stat Date" class="form-control" type="date"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">End Date : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
        <input path="1person.lastname" id="" name="enddate" value="<c:out value="${student.librarySubscription.enddate}"></c:out>" placeholder="End Date" class="form-control" type="date"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-4">
            <button type="submit" value="submit"  class="btn btn-warning" >Edit<span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>

</form>
</div>
</body>
</html>
