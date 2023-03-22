// This file is from the jQuery tutorials at 
// http://courses.coreservlets.com/Course-Materials/jquery.html

$(function() {
    $("#button1").click(makeLiBlue);
    $("#button2").click(revertLi);
});

function makeLiBlue() {
  $("li").addClass("blue");
}

function revertLi() {
  $("li").removeClass("blue");
}