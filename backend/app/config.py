from os import getenv

api_host = getenv("CHUGUN_HOST", "localhost:8080")
model_host = "model-1:1234" #example!

print(api_host)