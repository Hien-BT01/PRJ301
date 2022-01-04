<%-- 
    Document   : signup
    Created on : Oct 14, 2021, 9:30:46 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" href="./css/signup.css">
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h2 class="title">Registration</h2>
                <a href="index.jsp">Home</a>
            </div>
            <form action="MainController" method="POST" autocomplete="off">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="userName" placeholder="Enter your username" required value="${REMAIN_USER_INFOR.username}"/>
                        <span style="display: inline-block; margin-top: 10px; color: #ce3939; font-size: 14px">${requestScope.USER_ERROR.userName}</span>
                    </div>
                    <div class="input-box">
                        <span class="details">Full Name</span>
                        <input type="text" name="fullName" placeholder="Enter your full name" required value="${REMAIN_USER_INFOR.fullName}"/>
                        <span style="display: inline-block; margin-top: 10px; color: #ce3939; font-size: 14px">${requestScope.USER_ERROR.fullName}</span>
                    </div>
                    <div class="input-box">
                        <span class="details">Phone Number</span>
                        <input type="text" name="phoneNumber" placeholder="Enter your number, Ex: 094826...." required value="${REMAIN_USER_INFOR.phone}" onkeypress="inputOnlyNumber(event)"/>
                        <span style="display: inline-block; margin-top: 10px; color: #ce3939; font-size: 14px">${requestScope.USER_ERROR.phoneNumber}</span>
                    </div>
                    <div class="input-box">
                        <span class="details">Address</span>
                        <input type="text" name="address" placeholder="Enter your address" required value="${REMAIN_USER_INFOR.address}"/>
                        <span style="display: inline-block; margin-top: 10px; color: #ce3939; font-size: 14px">${requestScope.USER_ERROR.address}</span>
                    </div>
                    <div class="input-box">
                        <span class="details">Password</span>
                        <input type="password" name="password" placeholder="Enter your password" required value="${REMAIN_USER_INFOR.password}"/>
                        <span style="display: inline-block; margin-top: 10px; color: #ce3939; font-size: 14px">${requestScope.USER_ERROR.password}</span>
                    </div>
                    <div class="input-box">
                        <span class="details">Confirm Password</span>
                        <input type="password" name="confirm" placeholder="Enter your password" required/>
                        <span style="display: inline-block; margin-top: 10px; color: #ce3939; font-size: 14px">${requestScope.USER_ERROR.confirm}</span>
                    </div>
                </div>
                <div class="gender-details">
                    <h2 class="gender-title">Gender</h2>
                    <div class="category-gender">
                        <div class="gender-choose">
                            <input type="radio" name="gender" value="Female" id="female" required <c:if test="${REMAIN_USER_INFOR.gender eq 'Female'}">check</c:if>>
                                <label for="female">Female</label>
                            </div>
                            <div class="gender-choose">
                                <input type="radio" name="gender" value="Male" id="male" required <c:if test="${REMAIN_USER_INFOR.gender eq 'Male'}">check</c:if>>
                            <label for="male">Male</label>
                        </div>
                    </div>
                </div>
                <div class="button">
                    <input type="submit" name="action" value="Register" class="submit">
                </div>
            </form>
        </div>
        <script>
            function inputOnlyNumber(event) {
                let char = String.fromCharCode(event.which);
                if (!/[0-9]/.test(char)) {
                    event.preventDefault();
                }
            }
        </script>
    </body>
</html>
