window.addEventListener("load", e =>{
    const totalPriceEachProduct = document.querySelectorAll(".total-price span");
    const productPrice = document.querySelectorAll(".price");
    const total = document.querySelector(".total");
    totalPriceEachProduct.forEach(productPrice => {
        let price = productPrice.innerText.substring(0,productPrice.innerText.indexOf("."));
        price = parseInt(price);
        productPrice.innerText = `${price.toLocaleString()} VND`;
    });
    productPrice.forEach(productPrice => {
        let price = productPrice.innerText.substring(0,productPrice.innerText.indexOf("."));
        price = parseInt(price);
        productPrice.innerText = `${price.toLocaleString()} VND`;
    });
    let price = total.innerText.substring(0,total.innerText.indexOf("."));
    console.log(price)
    price = parseInt(price);
    total.innerText = `Tổng giá: ${price.toLocaleString()} VND`;
});

function showUserFunction(event){
    const userFunction = document.querySelector(".list-user__function");
    userFunction.classList.toggle("appear");
}
document.addEventListener("click", event => {
    const userFunction = document.querySelector(".list-user__function");
    if(userFunction !== null){
        if(!event.target.closest(".list-user__function") && !event.target.closest(".user-name")){
            userFunction.classList.remove("appear");
        }
    }
});

function ascendingQuantity(event,price){
    const target = $(event.target);
    const totalPrice = target.parent().parent().parent().parent().next().find(".total-price");
    let textFiled = event.target.previousElementSibling.value;
    textFiled = parseInt(textFiled) + 1;
    event.target.previousElementSibling.value = textFiled;
    totalPrice.html(`${(parseInt(textFiled) * price).toLocaleString()}VND`);
}

function descendingQuantity(event,price){
    let textFiled = event.target.nextElementSibling.value;
    const target = $(event.target);
    const totalPrice = target.parent().parent().parent().parent().next().find(".total-price");
    if(parseInt(textFiled) > 1){
        textFiled = parseInt(textFiled) - 1;
        event.target.nextElementSibling.value = textFiled;
    }
    totalPrice.html(`${(parseInt(textFiled) * price).toLocaleString()} VND`);
}

function setDefaultValue(event, number){
    if(event.target.value.length === 0){
        event.target.value = number;
    }
}
