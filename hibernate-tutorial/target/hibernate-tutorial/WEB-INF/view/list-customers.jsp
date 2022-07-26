<%@ page import="com.dzmitry.web_customer_tracker.util.SortUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<header>
    <title>List Customers</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css" />
</header>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <input type="button" value="Add Customer"
                onclick="window.location.href='showFormForAdd'"
               class="add-button"
            />
            <br>
            <form:form action="search" method="GET">
                Search Customer: <input type="text" name="searchName" placeholder="Enter first name or last name"/>
                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <table>
                <tr>
                    <c:url var="sortByFirstName" value="/customer/list">
                        <c:param name="sort"  value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"/>
                    </c:url>

                    <c:url var="sortByLastName" value="/customer/list">
                        <c:param name="sort"  value="<%= Integer.toString(SortUtils.LAST_NAME) %>"/>
                    </c:url>

                    <c:url var="sortByEmail" value="/customer/list">
                        <c:param name="sort"  value="<%= Integer.toString(SortUtils.EMAIL) %>"/>
                    </c:url>

                    <th><a href="${sortByFirstName}">First Name</a></th>
                    <th><a href="${sortByLastName}">Last Name</a></th>
                    <th><a href="${sortByEmail}">Email</a></th>
                    <th>Action</th>
                </tr>

                <c:forEach var="tempCustomer" items="${customers}">

                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/customer/showFormForDelete">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>

                    <tr>
                        <td>${tempCustomer.firstName}</td>
                        <td>${tempCustomer.lastName}</td>
                        <td>${tempCustomer.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}"
                               onclick="if(!(confirm('Are you sure you want to delete this?'))) return false">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>