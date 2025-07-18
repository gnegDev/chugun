// Прогресс-бар: измените progressPercent и он обновит ширину и значение %
let progressPercent = 60; // <--- Здесь измените процент
document.addEventListener("DOMContentLoaded", function() {
    // Прогресс-бар "Активные долги"
    document.getElementById('progressBar').style.width = progressPercent + "%";
    document.getElementById('progressValue').textContent = progressPercent;

    // Дни до даты (для ближайших платежей)
    document.querySelectorAll('.op-days').forEach(function(el) {
        let dateStr = el.getAttribute('data-date');
        let now = new Date();
        let payDate = new Date(dateStr);
        let diffDays = Math.max(0, Math.ceil((payDate - now) / (1000 * 60 * 60 * 24)));
        el.querySelector('span').textContent = diffDays;
    });
});
