<%-- 
    Document   : view_cart
    Created on : Oct 21, 2021, 9:45:09 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="./css/view.css" />
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
            integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER eq null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER ne null && sessionScope.LOGIN_USER.roleID ne 'US'}">
            <c:redirect url="admin.jsp"></c:redirect>
        </c:if>
        <header>
            <div class="header-nav">
                <div class="container">
                    <div class="cart-wrapper__header">
                        <div class="left-header">
                            <a class="logo" href="MainController?action=goHome">
                                <img src="./images/logo.png" alt="logo" />
                            </a>
                            <h2 class="cart-name">Giỏ hàng</h2>
                        </div>
                        <div class="right-header">
                            <h2 class="user-name" onclick="showUserFunction(event)">${sessionScope.LOGIN_USER.fullName}</h2>
                            <ul class="list-user__function">
                                <li>
                                    <a href="MainController?action=Logout" class="user-choice">Log out</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="cart-content">
            <div class="cart-wrapper">
                <h2 class="title-list">Sản phẩm trong giỏ hàng</h2>
                <c:if test="${sessionScope.CART eq null or (sessionScope.CART ne null and sessionScope.CART.getSize() eq 0)}">
                    <div class="content-empty">
                        <div class="status-img">
                            <img src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/cart/9bdd8040b334d31946f49e36beaf32db.png" alt="status-image">
                        </div>
                        <span class="empty-text">Giỏ hàng của bạng còn trống</span>
                        <a href="MainController?action=goHome" class="go-back"> Mua </a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.CART ne null}">
                    <c:if test="${sessionScope.CART.getSize() ne 0}">
                        <a href="MainController?action=goHome" class="go-back home"> Mua Thêm </a>
                        <h2 class="total" style="margin-top: 20px; color:#5fb65e;">${sessionScope.CART.getTotalPrice()}</h2>
                        <c:forEach items="${sessionScope.CART.getCart().values()}" var="product">
                            <div class="content-products mt-5">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="image">
                                                    <img src="${product.image}" alt="image">
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="product-desc">
                                                    <h2>${product.getName()}.</h2>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="product-desc">
                                                    <h2 class="product-price">
                                                        <span class="price">${product.price}</span>
                                                    </h2>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="product-desc">
                                                    <form class="quantity-product" method="POST" action="MainController">
                                                        <div class="change-value">
                                                            <button type="button" class="button descending" onclick="descendingQuantity(event,${product.price})">
                                                                <i class="fas fa-minus"></i>
                                                            </button>
                                                            <input type="number" min="1" name="quantity" value="${product.quantity}" class="quantity-num" max="200" onblur="setDefaultValue(event,${product.quantity})">
                                                            <button type="button" class="button ascending" onclick="ascendingQuantity(event,${product.price})">
                                                                <i class="fas fa-plus"></i>
                                                            </button>
                                                        </div>
                                                        <div class="save-change">
                                                            <input type="hidden" name="id" value="${product.id}"/>
                                                            <button type="submit" name="action" value="Save" class="save-button">Save</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="product-desc">
                                                    <h2 class="total-price">
                                                        <span>${product.price * product.quantity}</span>
                                                    </h2>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div class="product-desc">
                                                    <a href="MainController?action=DeleteProductInCart&productId=${product.id}" class="action">
                                                        <i class="fas fa-times"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="check-out">
                            <a onclick="showMess(event)" style="cursor: pointer;">Thanh Toán</a>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <script>
            function showMess(event) {
                var option = confirm('Bạn có chắc chắn muốn mua các loại mặt hàng này?');
                if (option === true) {
                    event.target.setAttribute("href", "MainController?action=Checkout");
                }
            }
        </script>
        <c:if test="${requestScope.BUY_MESS ne null}">
            <script>
                alert("${requestScope.BUY_MESS}")
            </script>
        </c:if>
        <c:if test="${requestScope.BUY_ERROR ne null}">
            <script>
                alert("${requestScope.BUY_ERROR}")
            </script>
        </c:if>
        <jsp:include page="footer.jsp" flush="true"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="./js/view_cart.js"></script>
    </body>
</html>
