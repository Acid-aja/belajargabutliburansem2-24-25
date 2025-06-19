// main program in here

const countLabel = document.getElementById("countLabel");
const decreaseBtn = document.getElementById("decreaseBtn");
const resetBtn = document.getElementById("resetBtn");
const increaseBtn = document.getElementById("increaseBtn");

let count = 0;

// increase button
increaseBtn.onclick = function() {
    count++;
    countLabel.textContent = count
}

// decrease button
decreaseBtn.onclick = function() {
    count--;
    countLabel.textContent = count
}

// reset button
resetBtn.onclick = function() {
    count = 0;
    countLabel.textContent = count
}