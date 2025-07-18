from flask import Blueprint, render_template

investments_route = Blueprint("investments_route", __name__, template_folder="../static")
@investments_route.route("/investments")
def render_credit_page():
    return render_template("invest.html")