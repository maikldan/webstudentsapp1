<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/resources.jsp"%>

<html>

<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/StudentPage" method="get" class="well form-horizontal">
        <div class="row">
        <div class="form-group">
            <div  class="col-xs-6  addStudentLabel col-xs-offset-5"><h2>Search Student</h2></div>
        </div>
        <div class="col-xs-6">
        <div class="form-group">
            <label  class="col-md-4 control-label">Name : </label>
            <div class="col-md-6 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input path="person.name" id="" class="form-control"  type="text" name ="person.name" placeholder="Person Name" value=""/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label">Address :</label>
            <div class="col-md-6 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <input  path="1person.address" name="person.address" placeholder="Partial address" class="form-control" type="text">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-md-4 control-label">Date of Birth(Start) : </label>
            <div class="col-md-6 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input path="person.dob" id="" class="form-control"  type="date" name ="person.dobStart"/>
                </div>
            </div>
        </div>
            <div class="form-group">
                <label  class="col-md-4 control-label">Date of Birth(End) : </label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input path="person.dob" id="" class="form-control"  type="date" name ="person.dobEnd"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label">Group : </label>
                <div class="col-md-6 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <select name="groups" id="" class="form-control selectpicker">
                            <option disabled selected value> -- select an option -- </option>
                            <c:forEach items="${groups}" var="group">
                                <option value="<c:out value="${group.id}"/>">${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6">

            <div class="form-group">
                <label class="col-md-4 control-label">Gender :</label>
                <div class="col-md-4">
                    <div class="radio">
                        <label><input type="radio" path="person.gender" value="Male" name="gender">Male</label>
                        <label><input type="radio" path="person.gender" value="Female" name="gender">Female</label>
                        <label><input type="radio" path="person.gender" value="" name="gender" checked>All</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label">Discipline : </label>
                <div class="col-md-6 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                        <select name="discipline" id="" class="form-control selectpicker">
                            <option disabled selected value> -- select an option -- </option>
                            <c:forEach items="${disciplines}" var="discipline">
                                <option value="<c:out value="${discipline.id}"/>">${discipline.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <%--<div class="form-group">--%>
                <%--<label class="col-md-4 control-label">Mark(s) : </label>--%>
                <%--<div class="col-md-6 selectContainer">--%>
                    <%--<div class="input-group">--%>
                        <%--<span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>--%>
                        <%--<input path="1mark" id="" value=""  class="form-control" name="discipline_average"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <label class="col-md-4 control-label">TotalAverage : </label>
                <div class="col-md-6 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span>
                        <input path="1mark" id="" value="" class="form-control" name="total_average"/>
                    </div>
                </div>
            </div>
        </div>
            <div class="form-group">
                <div class="col-xs-4 col-xs-offset-2">
                    <button type="submit" value="submit"  name="search" class="btn btn-warning " >Search <span class="glyphicon glyphicon-search"></span></button>
                    <a href="${pageContext.request.contextPath}/StudentPage" class="btn btn-warning ">Reset <span class="glyphicon glyphicon-refresh"></span></a>
                </div>
            </div>
        </div>
    </form>
    <div class="table-title text-center">
        <h3>Students</h3>
    </div>
    <form action="${pageContext.request.contextPath}/StudentPage" method="post">
    <table class = "table-fill" id="student_table">
    <thead>
        <tr>
            <th class="text-left">Check</th>
            <th class="text-left">Picture</th>
            <th class="text-left">Name</th>
            <th class="text-left">Birth Day</th>
            <th class="text-left">Gender</th>
            <th class="text-left">Address</th>
            <th class="text-left">Phone</th>
            <th class="text-left">Library Abonament</th>
            <th class="text-left">Marks</th>
            <th class="text-left">Action</th>
        </tr>
    </thead>
    <tbody class="table-hover">
        <c:forEach var="student" items="${students}">
            <tr>
                <td class="text-center"><input type="checkbox" value="${student.id}" id="chk" name="delete[]"></td>
                <td class="text-left"><img width="75" height="75" src="<c:out value="/resources/image/${student.picture}"/>" alt=""></td>
                <td class="text-left"><c:out value="${student.firstname}"/>
                    <c:out value="${student.lastname}"/>
                </td>
                <td class="text-left"><c:out value="${student.dob}"/></td>
                <td class="text-left"><c:out value="${student.gender}"/></td>
                <td class="text-left"><c:out value="${student.address.address}"/></td>
                <td class="text-left">
                    <c:forEach var="phone" items="${student.phone}">
                        <c:out value="${phone.number}"/>
                        <c:out value="${phone.phoneType.name}"/>
                        </br>
                    </c:forEach>
                </td>
                <td class="text-left"> <a href="${pageContext.request.contextPath}/editStatus?id=${student.id}" class="btn btn-default"><c:out value="Status: ${student.librarySubscription.status}" /> <span class="glyphicon glyphicon-pencil"></span></a><br><c:out value="Start Date: ${student.librarySubscription.startdate}" /><br><c:out value="End Date: ${student.librarySubscription.enddate}" /></td>
                <td class="text-left">
                    <c:forEach var="mark" items="${student.marks}">
                        <c:out value="${mark.discipline.title}"/>
                        <c:out value="${mark.mark}"/>
                        </br>
                    </c:forEach>
                </td>
                <td class="text-left"><a href="${pageContext.request.contextPath}/editStudent?id=${student.id}" class="btn btn-success">Edit <span class="glyphicon glyphicon-pencil"></span></a> <a href="${pageContext.request.contextPath}/addMark?id=${student.id}" class="btn btn-success">Add Mark <span class="glyphicon glyphicon-plus"></span></a>
                </td>

            </tr>



        </c:forEach>
    </tbody>
    </table>
        <div class="form-group addStudentButton">
            <a href="${pageContext.request.contextPath}/addStudentServlet" class="btn btn-warning">Add New One <span class="glyphicon glyphicon-plus"></span></a>
            <button id="button" type="submit" value="submit" class="btn btn-warning">Delete Student <span class="glyphicon glyphicon-minus"></span></button>
         </div>

    </form>
</div>
</body>

</html>
