<%-- 
Document   : login
Created on : Oct 14, 2021, 8:02:21 PM
Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./css/login.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER ne null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <header>
            <div class="header-nav">
                <div class="container">
                    <div class="infor">
                        <a class="logo" href="MainController?action=goHome">
                            <img src="./images/logo.png" alt="logo"/>
                        </a>
                        <h2>Đăng Nhập</h2>
                    </div>
                </div>
            </div>
        </header>
        <div class="box-wrapper">
            <div class="box-form">
                <div class="form">
                    <h2 class="form__title">
                        <span>Đăng Nhập</span>
                    </h2>
                    <form action="MainController" method="POST">
                        <div class="input-box">
                            <input
                                name="userAccount"
                                type="text"
                                placeholder="username"
                                class="input-box__text"
                                />
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="input-box">
                            <input
                                name="userPassword"
                                type="password"
                                placeholder="password"
                                class="input-box__text"
                                />
                            <i class="fas fa-lock"></i>
                        </div>
                        <div class="input-box">
                            <input type="submit" value="Login" name="action"/>
                        </div>
                    </form>
                    <c:if test="${requestScope.ERROR_LOGIN ne null}">
                        <span class="error">
                            ${requestScope.ERROR_LOGIN}
                        </span>
                    </c:if>
                    <a class="Create-account" href="MainController?action=ShowSignup"><span>Need an account?</span></a>
                </div>
            </div>
        </div>'
        <script src="./js/script.js"></script>
    </body>
</html>
