<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${event.name}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        h1{
            margin-top:50px;
        }
        h1, #info p, #joiners{
            margin-left:50px;
        }
        #info p{
            font-size:20px;
        }
        #container{
            display:flex;
            justify-content:space-between;
        }
        #messages{
            margin-right:200px;
        }
        #wall{
            width:500px;
            overflow:scroll;
            border:2px solid black;
            height:300px;
        }
        .error{
            color:red;
            font-weight:bold;
        }
    </style>
</head>
<body>
    <a href="/dashboard">Home page</a>
    <h1>${event.name}</h1>
    <br>
    <div id="container">
        <div id="info">
            <p><b>Host:</b> ${event.hoster.fname} ${event.hoster.lname}</p>
            <p><b>Date:</b> <fmt:formatDate pattern="MMMMMMM dd, yyyy" value="${event.eventDate}"/></p>
            <p><b>Location:</b> ${event.city}, ${event.state}</p>
            <p><b>Number of people attending this event:</b> <c:out value="${event.attendees.size()}"/></p>
            <br>
            <c:if test="${event.attendees.size() == 0}">
            <p>No joiners for this event yet</p>
            </c:if>
            <c:if test="${event.attendees.size() > 0}">
            <table class="table table-bordered" id="joiners">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${event.attendees}" var="joiner">
                    <tr>
                        <td>${joiner.fname} ${joiner.lname}</td>
                        <td>${joiner.city}, ${joiner.state}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            </c:if>
        </div>
    </div>
</body>
</html>