import requests
from flask import Blueprint, render_template, request
from config import api_host, model_host

advice_route = Blueprint("advice_route", __name__, template_folder="../static")
@advice_route.route("/user/account/advice", methods=["GET", "POST"])
def render_advice_page():
    cookies = request.cookies
    party_rk = cookies.get("party_rk")

    advice = ""

    if request.method == "POST":
        # Получение введеной транзакции
        transaction = request.form.to_dict()

        # Получение пользователя целиком
        # user = requests.get(f"http://{api_host}/api/user/get/{party_rk}").json()


        # Пример получения совета с использованием введеной транзакции
        # advice = requests.get(f"http://{model_host}/api/get", json=transaction).json()

        # Пока что так
        advice = "Покупайте деньги"

    # Рендер страницы с советом
    return render_template("advice.html", advice=advice)
