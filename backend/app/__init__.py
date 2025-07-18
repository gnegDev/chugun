from flask import Flask, url_for, redirect

from route.advice_controller import advice_route
from route.account_controller import account_route
from route.main_page_controller import main_page_route
from route.transactions_controller import transactions_route
from route.user_auth_controller import user_auth_route
from route.credit_controller import credit_route
from route.investments_controller import investments_route


# def create_app():
#
#     app = Flask(__name__)
#     #db = SQLAlchemy(app)
#
#     @app.route("/")
#     def initial_view():
#         return redirect(url_for("static", filename="index.html"))
#
#     return app

app = Flask(__name__)

app.register_blueprint(main_page_route)
app.register_blueprint(user_auth_route)
app.register_blueprint(account_route)
app.register_blueprint(transactions_route)
app.register_blueprint(advice_route)
app.register_blueprint(credit_route)
app.register_blueprint(investments_route)


if __name__ == "__main__":
    app.run("0.0.0.0", debug=True, port=5000)