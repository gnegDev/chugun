from flask import Blueprint, request, make_response
from datetime import datetime
import requests
from werkzeug.utils import redirect

from config import api_host

user_auth_route = Blueprint("user_auth_route", __name__, template_folder="../static")
@user_auth_route.route("/user/<action>", methods=["POST"])
def handle_user(action):
    form = request.form.to_dict()

    user = {"party_rk": "0"}

    if action == "login":
        party_rk = form["party_rk"]

        user = requests.get(f"http://{api_host}/api/user/get/{party_rk}")

    if action == "create":
        first_session_dttm = datetime.now().strftime('%Y-%m-%dT%H:%M:%S')
        form["first_session_dttm"] = first_session_dttm

        user = requests.post(f"http://{api_host}/api/user/create", json=form)

    if user.status_code == 200:
        user = user.json()
    else:
        return f"<a href=\"/\">назад</a> error getting user: {user.status_code}"

    response = make_response(redirect("/user/account"))
    response.set_cookie("party_rk", str(user["party_rk"]))
    return response
