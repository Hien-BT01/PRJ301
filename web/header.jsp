<%-- 
    Document   : header
    Created on : Oct 17, 2021, 3:40:12 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/header.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <div class="header-nav">
                <div class="container">
                    <div class="header-infor">
                        <div class="logo-section">
                            <img src="./images/logo.png" alt="logo" style="width: 100%;">
                        </div>
                        <div class="search-section">
                            <form action="MainController" method="GET" class="form-search" onsubmit="checkEmptySearch(event)">
                                <input type="text" name="search" placeholder="Tìm sản phẩm" class="search-field" value="${param.search}">
                                <button class="submit" type="submit" value="Search" name="action">
                                    <i class="fas fa-search"></i>
                                </button>
                            </form>
                        </div>
                        <div class="account-section">
                            <div class="account-infor">
                                <div class="account-icon">
                                    <i class="fas fa-user"></i>
                                </div>
                                <div class="account-access">
                                    <c:if test="${sessionScope.LOGIN_USER eq null}">
                                        <c:url var="loginLink" value="MainController">
                                            <c:param name="action" value="ShowLogin"></c:param>
                                        </c:url>
                                        <a href="${loginLink}" class="sign in">Đăng nhập</a>
                                        <span>  /  </span>
                                        <c:url var="signupLink" value="MainController">
                                            <c:param name="action" value="ShowSignup"></c:param>
                                        </c:url>
                                        <a href="${signupLink}" class="sign up">Đăng ký</a>
                                    </c:if>
                                    <c:if test="${sessionScope.LOGIN_USER ne null}">
                                        <h2 class="user-name" onclick="showUserFunction(event)">${sessionScope.LOGIN_USER.fullName}</h2>
                                        <ul class="list-user__function">
                                            <li>
                                                <c:url var="logoutLink" value="MainController">
                                                    <c:param name="action" value="Logout"></c:param>
                                                </c:url>
                                                <a href="${logoutLink}" class="user-choice">Đăng xuất</a>
                                            </li>
                                        </ul>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD'}">
                            <div class="cart-section">
                                <a href="view_cart.jsp" class="cart-wrapper__content">
                                    <div class="cart-wrapper__icon">
                                        <div class="cart-access">
                                            <i class="fas fa-shopping-cart"></i>
                                        </div>
                                        <div class="cart-quantity">
                                            <span>
                                                <c:if test="${sessionScope.CART eq null or sessionScope.CART.getSize() eq 0}">0</c:if>
                                                <c:if test="${sessionScope.CART ne null and sessionScope.CART.getSize() ne 0}">${sessionScope.CART.getSize()}</c:if>
                                                </span>
                                            </div>
                                        </div>
                                        <span class="cart-text">Giỏ hàng</span>
                                    </a>
                                </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </header>
        <script src="./js/header.js"></script>
    </body>
</html>
