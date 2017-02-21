<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 2/13/2017
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="<c:url value="resources/css/bootstrap.min.css" />"  rel="stylesheet" media="screen" />
<script src="<c:url value="resources/js/bootstrap.min.js" />" ></script>
<script src="<c:url value="resources/js/main.js" />" ></script>
<link href="<c:url value="resources/css/main.css" />"  rel="stylesheet" media="screen" />

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
<form action="${pageContext.request.contextPath}/addStudentServlet" method="post" commandName="addStudent " class="well form-horizontal" id="addStudent_form" enctype="multipart/form-data">
    <div class="form-group">
        <div  class="col-md-4  addStudentLabel col-md-offset-3"><h2>Add Student</h2></div>
    </div>

    <div class="form-group">
        <label  class="col-md-4 control-label">First Name : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
        <input path="person.firstname" id="" class="form-control"  type="text" name ="person.firstname" placeholder="First Name"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Last Name : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input path="person.lastname" id="" class="form-control"  type="text" name ="person.lastname" placeholder="Last Name"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Date of Birth : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input path="person.dob" id="" class="form-control"  type="date" name ="person.dob"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Country :</label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input  path="1person.address.country" name="person.address.country" placeholder="Country" class="form-control" type="text">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">City :</label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input  path="1person.address.city" name="person.address.city" placeholder="City" class="form-control" type="text">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Address :</label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input  path="1person.address.address" name="person.address.address" placeholder="Address" class="form-control" type="text">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Phone : </label>
        <div class="col-md-4 selectContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
    <select name="phone.phoneType" id="" class="form-control selectpicker">
        <c:forEach items="${phonesType}" var="phoneType">
            <option value="<c:out value="${phoneType.id}"/>">${phoneType.name}</option>
        </c:forEach>
    </select>

    <input path="1person.phone.number" id="" type="" name="person.phone.number" placeholder="0785xxxxxx" class="form-control" />
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Gender :</label>
        <div class="col-md-4">
            <div class="radio">
        <label><input type="radio" path="person.gender" value="Male" name="gender">Male</label>
        <label><input type="radio" path="person.gender" value="Female" name="gender">Female</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Group : </label>
        <div class="col-md-4 selectContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
    <select name="groups" id="" class="form-control selectpicker">

        <c:forEach items="${groups}" var="group">
            <option value="<c:out value="${group.id}"/>">${group.name}</option>
        </c:forEach>
    </select>
            </div>
        </div>
    </div>
    <!-- Success message -->
    <div class="form-group">
        <label  class="col-md-4 control-label">Student Picture : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-file"></i></span>
                <input type="file" name="file" class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-4">
            <button type="submit" value="submit"  class="btn btn-warning " >Send<span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>


</form>
</div>
</body>
</html>
