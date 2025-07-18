from flask import Blueprint, render_template, request
import requests

from config import api_host

account_route = Blueprint("account_route", __name__, template_folder="../static")
@account_route.route("/user/account", methods=["GET"])
def render_account_page():
    cookies = request.cookies
    party_rk = cookies.get("party_rk")
    user = requests.get(f"http://{api_host}/api/user/get/{party_rk}").json()

    user_id = user["party_rk"]
    transactions = user["transactions"]
    balances = user["balances"]

    charts_data = dict()

    for transaction in transactions:
        transaction_amount = transaction["transaction_amt_rur"]
        category = transaction["brand_nm"].strip()

        if category not in charts_data and transaction_amount > 0:
            charts_data[category] = 0

        charts_data[category] += transaction_amount

    charts_data = [[category, amount] for category, amount in charts_data.items()]

    total_balance = sum([balance["balance_amt"] for balance in balances])

    return render_template("index.html", user_id=user_id, total_balance=total_balance, balances=balances, charts_data=charts_data)