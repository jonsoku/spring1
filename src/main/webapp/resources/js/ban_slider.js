const slider = document.querySelector('#slider');
const sliderUl = document.querySelector('#slider ul');
const sliderLi = sliderUl.querySelectorAll('li');
const length = sliderLi.length;
const dot = document.querySelector('.dot');
const rightArrow = document.querySelector('.rightArrow');
const leftArrow = document.querySelector('.leftArrow');




// var width = 1600;
// const height = 420;

// var media = window.matchMedia("screen and (max-width : 1024px)");
// if(media.matches == true){
//     width = 1024;
// }


let page = 0;

// /* slider */
// slider.style.width = width +"px";
// slider.style.height = height + "px";
// slider.style.margin ="0 auto";

// /* slider ul */
// sliderUl.style.width = width * length +"px";
// sliderUl.style.height = height + "px";

// /* slider li */
// for(let i = 0 ; i < length ; i++){
//     var item = sliderLi.item(i);
//     item.style.width = width +"px";
//     item.style.height = height + "px";
// }

/* create dot btn */
for(let i = 0 ; i < length ; i++){
    const btn = document.createElement('div');
    dot.appendChild(btn);
    btn.classList.add('dots');
}

const dots = document.querySelectorAll('.dots');

for(let i = 0 ; i < length ; i++){
    dots[i].addEventListener("click",function(){
        moveTo(i);
        page = i;        
    })
    
}



rightArrow.addEventListener('click', function(){
    if(page === length){
        page = 0;
    }
    rightSlide();
})

function rightSlide(){
    moveTo(page+1);
    page++;
}

leftArrow.addEventListener('click', function(){
    if(page < 1){
        page = length;
    }
    leftSlide();
})

function leftSlide(){
    moveTo(page-1);
    page--;
}


function moveTo(index){
    index = index || 0;
    index = index % length;

    //slider 부분
    for(let i = 0 ; i < length ; i++){
        for(let j = 0 ; j < length ; j++){
            if(sliderLi[j].classList.contains = "slider_active"){
                sliderLi[j].classList.remove("slider_active");
            }
        }
        sliderLi[index].classList.add("slider_active");
    }
    

    // sliderUl.style.marginLeft = "-"+width*index+"px";
    for(let i = 0 ; i < length ; i++){
        for(let j = 0 ; j < length ; j++){
            if(dots[j].classList.contains = "dots_active"){
                dots[j].classList.remove("dots_active");
            }
        }
        dots[index].classList.add("dots_active");
    }
}
function init(){
    moveTo(0);
    setInterval(function(){
        moveTo(page+1);
        page++;
        if(page === length){
            page = 0;
        }
    }, 1000 * 2)
}
init();