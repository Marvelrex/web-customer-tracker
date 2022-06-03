<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.jialin.springdemo.Utility.SortUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: marve
  Date: 2022/5/28
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%-- Construct a sort link for first name --%>
<c:url var = "sortLinkFirstName" value="/customer/list">
    <c:param name="sort" value="<%=Integer.toString(SortUtils.FIRST_NAME)%>"/>
</c:url>
<c:url var = "sortLinkLastName" value="/customer/list">
    <c:param name="sort" value="<%=Integer.toString(SortUtils.LAST_NAME)%>"/>
</c:url>
<c:url var = "sortLinkEmail" value="/customer/list">
    <c:param name="sort" value="<%=Integer.toString(SortUtils.EMAIL)%>"/>
</c:url>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List All Customers</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>


    <div id="wrapper">
        <div id = "header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <div id = "content">

            <!-- Put New Button: Add customer -->
            <input type="button" value="Add customer" onclick="window.location.href='showFormForAdd' ; return false;"
            class="add-button"/>


            <form:form action="search" method="get">
                Search Customer: <input type="text" name="SearchName"/>
                <input type="submit" value="Search" class="add-button">
            </form:form>
            <!-- add out html table -->
            <table>
                <tr>
                    <th><a href="${sortLinkFirstName}"> First Name </a> </th>
                    <th><a href="${sortLinkLastName}"> Last Name</a></th>
                    <th><a href="${sortLinkEmail}">  Email </a> </th>
                    <th>Action</th>
                    <!-- loop over and print customers -->
                    <c:forEach var="tempCustomer" items="${customers}">

                        <!-- construct an update link with customer id-->
                        <c:url var="updateLink" value="/customer/showFormForUpdate">
                            <c:param name="customerId" value="${tempCustomer.id}"/>
                        </c:url>
                        <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                        </c:url>
                        <tr>
                            <td>${tempCustomer.firstName}</td>
                            <td>${tempCustomer.lastName}</td>
                            <td>${tempCustomer.email}</td>
                            <td>
                                <!-- Display the update link -->
                                <a href="${updateLink}">Update</a>
                            </td>
                            <td>
                                <a href="${deleteLink}"
                                onclick="if(!(confirm('Are you sure to delete this Customer?')))return false">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tr>
            </table>
        </div>
    </div>

</body>
</html>
