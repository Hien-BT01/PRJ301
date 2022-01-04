<%-- 
    Document   : index
    Created on : Oct 17, 2021, 4:32:45 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hoholive's Shop </title>
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
        <link rel="stylesheet" href="./css/index.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'US' && sessionScope.LOGIN_USER ne null}">
            <c:redirect url="admin.jsp"></c:redirect>
        </c:if>
        <c:if test="${sessionScope.LIST_PRODUCT eq null}">
            <jsp:forward page="ShowProducts"></jsp:forward>
        </c:if>
        <jsp:include page="header.jsp" flush="true"/>
        <main>
            <h2 class="welcome-page">
                <span>Chào mừng đến với Cửa hàng Rau, Củ, Quả của Hoholive. Với chất lượng rau chất lượng, chúng tôi mang đến cho quý khách những lại rau tươi, sạch và ngon chất cho bữa ăn hằng ngày của quý khách.</span
                >
            </h2>
            <div class="content">
                <h2 class="list-title">Danh sách các sản phẩm</h2>
                <div class="list-product container">
                    <div class="category">
                        <div class="category__wrapper row">
                            <div class="col-lg-3 mb-4">
                                <div class="category__header">
                                    <div class="category__header-icon">
                                        <i class="fas fa-bars"></i>
                                    </div>
                                    <h3 class="category__header-text">Loại</h3>
                                </div>
                                <div class="category-content">
                                    <div class="category-item">
                                        <a href="MainController?action=listProduct&category=RAU" class="category-link <c:if test="${sessionScope.CATEGORY eq 'RAU'}">active-link</c:if>">Rau</a>
                                        </div>
                                        <div class="category-item">
                                            <a href="MainController?action=listProduct&category=CU" class="category-link <c:if test="${sessionScope.CATEGORY eq 'CU'}">active-link</c:if>">Củ</a>
                                        </div>
                                        <div class="category-item">
                                            <a href="MainController?action=listProduct&category=QUA" class="category-link <c:if test="${sessionScope.CATEGORY eq 'QUA'}">active-link</c:if>">Quả</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-9 mb-5">
                                    <div class="row">
                                    <c:if test="${requestScope.SEARCH_FAIL ne null}">
                                        <div class="col-lg-12 mb-4">
                                            <h2>${requestScope.SEARCH_FAIL}</h2>
                                        </div>
                                    </c:if>
                                    <c:if test="${sessionScope.LIST_PRODUCT.size() != 0}">
                                        <c:forEach items="${sessionScope.LIST_PRODUCT}" var="product">
                                            <div class="col-lg-4 mb-4">
                                                <form class="product h-100" action="MainController" method="POST">
                                                    <div class="product-image">
                                                        <img src="${product.image}" alt=" product-image">
                                                        <input type="hidden" name="productId" value="${product.id}"/>
                                                        <input type="hidden" name="productImage" value="${product.image}"/>
                                                    </div>
                                                    <div class="product-content">
                                                        <h4 class="product-name">
                                                            ${product.name}
                                                        </h4>
                                                        <h3 class="product-price">
                                                            <span class="price">${product.price}</span>
                                                            <input type="hidden" name="productPrice" value="${product.price}"/>
                                                        </h3>
                                                        <h3 class="product-quantity">
                                                            <span>Số lượng: ${product.quantity}</span>
                                                        </h3>
                                                        <div class="quantity-to-cart">
                                                            <button type="button" class="button descending" onclick="descendingQuantity(event)"><i class="fas fa-minus"></i></button>
                                                            <input type="number" min="1" max="200" name="quantity" class="quantity-num" value="1" onblur="setDefaultValue(event, 1)">
                                                            <button type="button" class="button ascending" onclick="ascendingQuantity(event)"><i class="fas fa-plus"></i></button>
                                                        </div>
                                                        <div class="submit-product">
                                                            <input type="hidden" name="productName" value="${product.name}"/>
                                                            <c:if test="${product.quantity ne 0}">
                                                                <input type="submit" value="Add To Cart" name="action">
                                                            </c:if>
                                                        </div>
                                                        <c:if test="${requestScope.MESSAGE ne null}">
                                                            <c:if test="${param.productId eq product.id}">
                                                                <div class="status-add">${requestScope.MESSAGE}</div>
                                                            </c:if>
                                                        </c:if>
                                                    </div>
                                                </form>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="footer.jsp" flush="true"/>
        <script src="js/script.js"></script>
    </body>
</html>
