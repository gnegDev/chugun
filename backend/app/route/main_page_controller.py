from flask import Blueprint, render_template

main_page_route = Blueprint("main_page_route", __name__, template_folder="../static")
@main_page_route.route("/")
def render_main_page():
    return render_template("preindex.html")
