const hamburger = document.querySelector(".hamburger");
const navMenu = document.querySelector(".nav-menu");

hamburger.addEventListener("click", mobileMenu);

function mobileMenu() {
    hamburger.classList.toggle("active");
    navMenu.classList.toggle("active");
}


// when we click on hamburger icon its close 

const navLink = document.querySelectorAll(".nav-link");

navLink.forEach(n => n.addEventListener("click", closeMenu));

function closeMenu() {
    hamburger.classList.remove("active");
    navMenu.classList.remove("active");
}



// student reviws section
// script.js
// student registration page start
    // let firstname = document.forms["std-login"]["firstname"].value;
 
   
   function stdreg(){
    
    let Firstname = document.getElementById('firstName').value;
    document.getElementById('person').innerHTML= Firstname;
    
   } 


//student registration page start




