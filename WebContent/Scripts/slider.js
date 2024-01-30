let slideIndex = 1;

function showSlide(n) {
  let slides = document.getElementsByClassName("slide");

  if (n > slides.length) {
    slideIndex = 1;
  }

  if (n < 1) {
    slideIndex = slides.length;
  }

  for (let i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }

  let offset = -(slideIndex - 1) * 100 + "%";

  for (let i = 0; i < slides.length; i++) {
    slides[i].style.left = offset;
  }

  slides[slideIndex - 1].style.display = "block";
}

function nextSlide() {
  showSlide(slideIndex += 1);
}

function prevSlide() {
  showSlide(slideIndex -= 1);
}

window.onload = function() {
  showSlide(slideIndex);
};
