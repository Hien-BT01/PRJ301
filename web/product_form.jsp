<%-- 
    Document   : product_form
    Created on : Oct 27, 2021, 9:14:45 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ad Product Page</title>
        <link rel="stylesheet" href="./css/product_form.css"/>
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
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD' && sessionScope.LOGIN_USER ne null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <div class="card-container">
            <div class="card">
                <div class="card__content">
                    <c:if test="${requestScope.VEGETABLE.id eq null and requestScope.REMAIN_INFOR.id eq null}">
                        <h2 class="card__title">
                            <a href="MainController?action=goHome" class="go-back">
                                <i class="fas fa-arrow-left"></i>
                            </a>
                            <span>
                                Add new product
                            </span>
                        </h2>
                    </c:if>
                    <c:if test="${requestScope.VEGETABLE.id ne null or requestScope.REMAIN_INFOR.id ne null}">
                        <h2 class="card__title">
                            <a href="MainController?action=goHome" class="go-back">
                                <i class="fas fa-arrow-left"></i>
                            </a>
                            <span>Update product</span>
                        </h2>
                    </c:if>
                    <div class="form">
                        <form action="MainController" method="POST" autocomplete="off">
                            <div class="card__box">
                                <h3 class="box-title">Name of Product</h3>
                                <div class="input-field">
                                    <input type="text" name="productName" placeholder="Your Product's Name" required value="${requestScope.VEGETABLE.name}">
                                </div>
                                <c:if test="${requestScope.NAME_ERROR ne null}">
                                    <span style="display: block; margin-top: 8px; color: red;">${requestScope.NAME_ERROR}</span>
                                </c:if>
                            </div>
                            <div class="card__box">
                                <h3 class="box-title">Image of Product (URL)</h3>
                                <div class="input-field">
                                    <input type="url" name="productImage" placeholder="Your Product's Image url" pattern="^(http(s)?:\/\/)+[\w\-\._~:\/?#[\]@!\$&'\(\)\*\+,;=.]+$" required value="${requestScope.VEGETABLE.image}${requestScope.REMAIN_INFOR.image}">
                                </div>
                            </div>
                            <div class="card__box">
                                <h3 class="box-title">Price of Product</h3>
                                <div class="input-field">
                                    <input type="number" name="productPrice" min="1000" step="100" placeholder="Your Product's Price" onblur="setDefaultValue(event, 1000)" value="${requestScope.VEGETABLE.price}${requestScope.REMAIN_INFOR.price}" id="price">
                                </div>
                            </div>
                            <div class="card__box">
                                <h3 class="box-title">Quantity of Product</h3>
                                <div class="input-field">
                                    <input type="number" name="productQuantity" min="1" placeholder="Your Product's Quantity" onblur="setDefaultValue(event, 1)" value="${requestScope.VEGETABLE.quantity}${requestScope.REMAIN_INFOR.quantity}">
                                </div>
                            </div>
                            <div class="card__box">
                                <h3 class="box-title">Category of Product</h3>
                                <select name="categoryProduct" class="category">
                                    <option value="RAU"<c:if test="${requestScope.VEGETABLE.category eq 'RAU' or requestScope.REMAIN_INFOR.category eq 'RAU'}">selected</c:if>>RAU</option>
                                    <option value="CU"<c:if test="${requestScope.VEGETABLE.category eq 'CU' or requestScope.REMAIN_INFOR.category eq 'CU'}">selected</c:if>>CỦ</option>
                                    <option value="QUA"<c:if test="${requestScope.VEGETABLE.category eq 'QUA' or requestScope.REMAIN_INFOR.category eq 'QUA'}">selected</c:if>>QUẢ</option>
                                    </select>
                                </div>
                            <c:if test="${requestScope.VEGETABLE.id ne null or requestScope.REMAIN_INFOR.id ne null}">
                                <input type="hidden" value="${requestScope.VEGETABLE.id}${requestScope.REMAIN_INFOR.id}" name="productId"/>
                            </c:if>   
                            <c:if test="${requestScope.VEGETABLE.id eq null and requestScope.REMAIN_INFOR.id eq null}">
                                <input type="submit" value="AddProduct" name="action" class="submit-button">
                            </c:if>
                            <c:if test="${requestScope.VEGETABLE.id ne null or requestScope.REMAIN_INFOR.id ne null}">
                                <input type="submit" name="action" value="Update" class="submit-button"/>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/script.js"></script>
        <script>
                                        window.addEventListener("load", e => {
                                            const productPrice = document.querySelector("#price");
                                            let formatPrice = productPrice.value.substring(0, productPrice.value.indexOf("."));
                                            productPrice.value = formatPrice;
                                        });
        </script>
    </body>
</html>
