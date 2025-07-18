from flask import Blueprint, render_template, request
import requests

from config import api_host

transactions_route = Blueprint("transactions_route", __name__, template_folder="../static")
@transactions_route.route("/user/account/transactions", methods=["GET", "POST"])
def render_transactions_page():
    cookies = request.cookies
    party_rk = cookies.get("party_rk")

    if request.method == "POST":
        if request.files:
            file = request.files["file"].read().decode("utf-8")

            for line in file.split("\n"):
                line = line.strip()

                keys = [
                    "party_rk",
                    "account_rk",
                    "financial_account_type_cd",
                    "financial_account_subtype_cd",
                    "transaction_type_cd",
                    "transaction_amt_rur",
                    "real_transaction_dttm",
                    "brand_nm",
                    "loyalty_cashback_category_nm",
                    "loyalty_accrual_rub_amt",
                    "utilization_flg"
                ]
                if line:
                    values = [float(x.replace(",", ".")) if x.replace(",", "").isdigit() else x for x in line.split(";")]
                    values.insert(0, party_rk)
                    transaction = dict(zip(keys, values))
                    transaction["real_transaction_dttm"] = transaction["real_transaction_dttm"].replace(" ", "T")
                    res = requests.post(f"http://{api_host}/api/transaction/create", json=transaction)
        else:
            transaction = request.form.to_dict()
            transaction["party_rk"] = party_rk

            res = requests.post(f"http://{api_host}/api/transaction/create", json=transaction)

    user = requests.get(f"http://{api_host}/api/user/get/{party_rk}").json()

    transactions = user["transactions"]


    charts_data = dict()

    for transaction in transactions:
        transaction_amount = transaction["transaction_amt_rur"]
        category = transaction["brand_nm"].strip()

        if category not in charts_data and transaction_amount > 0:
            charts_data[category] = 0

        charts_data[category] += transaction_amount

    charts_data = [[category, amount] for category, amount in charts_data.items()]

    transactions = user["transactions"][:10:-1]

    return render_template("finance.html", charts_data=charts_data, transactions=transactions)