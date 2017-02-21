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
<form action="${pageContext.request.contextPath}/editStudent" method="post" commandName="editStudent" class="well form-horizontal" id="addStudent_form" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<c:out value="${student.id}"></c:out>"/>
    <input type="hidden" name="address_id" value="<c:out value="${student.address.id}"></c:out>"/>
    <div class="form-group">
        <div  class="col-md-4  addStudentLabel col-md-offset-3"><h2> Edit Student : <c:out value="${student.firstname} ${student.lastname} "/></h2></div>
    </div>
    <input type="text" hidden value="${person.picture}" name="existsImage">
    <div class="form-group">
        <label  class="col-md-4 control-label">First Name : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input path="person.firstname" id="" class="form-control"  type="text" name ="person.firstname" placeholder="First Name"  value="${student.firstname}"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Last Name : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input path="person.lastname" id="" class="form-control"  type="text" name ="person.lastname" placeholder="Last Name"  value="${student.lastname}"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label  class="col-md-4 control-label">Date of Birth : </label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-date"></i></span>
                <input path="person.dob" id="" class="form-control"  type="date" name ="person.dob"  value="${student.dob}"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Country :</label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input  path="1person.address.country" name="person.address.country" placeholder="Country" class="form-control" type="text" value="${student.address.country}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">City :</label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input  path="1person.address.city" name="person.address.city" placeholder="City" class="form-control" type="text" value="${student.address.city}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Address :</label>
        <div class="col-md-4 inputGroupContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input  path="1person.address.address" name="person.address.address" placeholder="Address" class="form-control" type="text" value="${student.address.address}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-4 control-label">Phone : </label>
        <div class="col-md-4 selectContainer">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
        <c:forEach items="${phones}" var="phone">
            <select name="phone.phoneType[]" id="" class="form-control selectpicker">
                <c:forEach items="${phonesType}" var="type">
                    <c:choose>
                        <c:when test="${phone.phoneType.name == type.name}">
                            <option value="<c:out value="${type.id}"/>" selected> <c:out value="${type.name}"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${type.id}"/>"> <c:out value="${type.name}"/></option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>

            <input path="1person.phone.number" name="person.phone.number[]"  placeholder="Phone number" value="<c:out value="${phone.number}"/>" placeholder="0785xxxxxx" class="form-control" />
            <input hidden path="1person.phone.number" name="person.phone.id[]"  placeholder="Phone number" value="<c:out value="${phone.id}"/>"/>
        </c:forEach>
            </div>
        </div>
    </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Gender :</label>
            <div class="col-md-4">
                <div class="radio">
        <c:choose>
            <c:when test="${student.gender == 'Male'}">
                <label for=""> <input type="radio" path="person.gender" name="gender" value="Male" checked >Male</label>
                <label for=""> <input type="radio" path="person.gender" name="gender" value="Female" >Female</label>
            </c:when>
            <c:otherwise>
                <label for=""> <input type="radio" path="person.gender" name="gender" value="Male">Male</label>
                <label for=""> <input type="radio" path="person.gender" name="gender" value="Female" checked>Female</label>
            </c:otherwise>
        </c:choose>
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

            <option value="<c:out value="${group.id}"/>" ${student.group.id == group.id ? "selected" : ""}>${group.name}</option>
        </c:forEach>
    </select>
            </div>
        </div>
    </div>
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
            <button type="submit" value="submit"  class="btn btn-warning" >Edit<span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>


</form>
</div>
</body>
</html>
