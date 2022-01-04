
window.addEventListener("load", e =>{
    const productPrices = document.querySelectorAll(".price");
    productPrices.forEach(productPrice => {
        let price = productPrice.innerText.substring(0,productPrice.innerText.indexOf("."));
        price = parseInt(price);
        productPrice.innerText = `${price.toLocaleString()} VND`;
    });
});


function ascendingQuantity(event){
    let textFiled = event.target.previousElementSibling.value;
    textFiled = parseInt(textFiled) + 1;
    event.target.previousElementSibling.value = textFiled;
}

function descendingQuantity(event){
    let textFiled = event.target.nextElementSibling.value;
    if(parseInt(textFiled) > 1){
        textFiled = parseInt(textFiled) - 1;
        event.target.nextElementSibling.value = textFiled;
    }
}
function setDefaultValue(event, number){
    if(event.target.value.length === 0){
        event.target.value = number;
    }
}