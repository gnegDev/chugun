from flask import Blueprint, render_template

credit_route = Blueprint("credit_route", __name__, template_folder="../static")
@credit_route.route("/credits")
def render_credit_page():
    return render_template("credits.html")