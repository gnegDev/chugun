document.addEventListener('DOMContentLoaded', function() {
    // API для валют (ЦБ РФ)
    const CBR_API = 'https://www.cbr-xml-daily.ru/daily_json.js';
    
    // API для акций (Мосбиржа через Tinkoff API)
    const TINKOFF_API_PROXY = 'https://api.tinkoff.ru/v1/';
    
    // API для криптовалют (Binance)
    const BINANCE_API = 'https://api.binance.com/api/v3/ticker/price';

    // Форматирование чисел
    function formatNumber(num) {
        return num.toLocaleString('ru-RU', {maximumFractionDigits: 2});
    }

    // Обновление курсов валют (рубль)
    async function updateCurrencyRates() {
        const currencyCards = document.querySelectorAll('.currency-card[data-type="currency"]');
        
        try {
            const response = await fetch(CBR_API);
            const data = await response.json();
            
            if (data?.Valute) {
                currencyCards.forEach(card => {
                    const symbol = card.getAttribute('data-symbol').split('/')[0];
                    const rate = data.Valute[symbol]?.Value;
                    
                    if (rate) {
                        const value = rate.toFixed(2);
                        const previous = parseFloat(card.querySelector('h3').textContent) || 0;
                        
                        card.querySelector('h3').textContent = `${value} ₽`;
                        card.classList.remove('loading', 'up', 'down');
                        card.classList.add(previous > 0 ? (value > previous ? 'up' : 'down') : 'up');
                    }
                });
            }
        } catch (error) {
            console.error('Ошибка валют:', error);
        }
    }

    // Обновление акций через Tinkoff API (российские акции)
    async function updateStocks() {
        const stockCards = document.querySelectorAll('.currency-card[data-type="stock"]');
        
        try {
            // Для российских акций (тикеры MOEX)
            const moexResponse = await fetch(`${TINKOFF_API_PROXY}stocks`);
            const moexData = await moexResponse.json();
            
            if (moexData?.payload?.instruments) {
                moexData.payload.instruments.forEach(stock => {
                    const card = document.querySelector(`.currency-card[data-symbol="${stock.ticker}"]`);
                    if (!card) return;
                    
                    const price = stock.price?.value;
                    const previous = stock.closePrice?.value;
                    
                    if (price && previous) {
                        card.querySelector('h3').textContent = `${formatNumber(price)} ₽`;
                        card.classList.remove('loading', 'up', 'down');
                        card.classList.add(price >= previous ? 'up' : 'down');
                    }
                });
            }
        } catch (error) {
            console.error('Ошибка акций:', error);
        }
    }

    // Обновление криптовалют через Binance
    async function updateCrypto() {
        const cryptoCards = document.querySelectorAll('.currency-card[data-type="crypto"]');
        
        for (const card of cryptoCards) {
            const symbol = card.getAttribute('data-symbol').split('-')[0];
            try {
                const response = await fetch(
                    `${BINANCE_API}?symbol=${symbol}USDT`
                );
                const data = await response.json();
                
                if (data?.price) {
                    const price = parseFloat(data.price);
                    const previous = parseFloat(card.querySelector('h3').textContent) || 0;
                    
                    card.querySelector('h3').textContent = `${formatNumber(price)} $`;
                    card.classList.remove('loading', 'up', 'down');
                    card.classList.add(previous > 0 ? (price > previous ? 'up' : 'down') : 'up');
                }
            } catch (error) {
                console.error(`Ошибка ${symbol}:`, error);
            }
        }
    }

    // Загрузка всех данных
    async function loadAllData() {
        await Promise.allSettled([
            updateCurrencyRates(),
            updateStocks(),
            updateCrypto()
        ]);
    }

    // Первая загрузка
    loadAllData();
    
    // Обновление каждые 5 минут
    setInterval(loadAllData, 5 * 60 * 1000);
});