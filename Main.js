const timerDisplay = document.getElementById("timer-display");

let time = 1500; // 25 menit dalam detik

if (timerDisplay) {
    const minutes = Math.floor(time / 60);
    const second = time % 60;
    timerDisplay.textContent = `${minutes.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
}

const timer = setInterval(function () {
    time--;

    const minutes = Math.floor(time / 60);
    const second = time % 60;

    if (timerDisplay) {
        timerDisplay.textContent = `${minutes.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
    }

    if (time <= 0) {
        clearInterval(timer);
        if (timerDisplay) {
            timerDisplay.textContent = "Waktu habis!";
        }
    }
}, 1000);
