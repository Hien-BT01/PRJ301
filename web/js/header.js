


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

function checkEmptySearch(event){
    const searchField = document.querySelector(".search-field");
    if(searchField.value.trim().length == 0){
        event.preventDefault();
    }
}