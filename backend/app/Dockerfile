FROM python:3.14-rc
LABEL authors="gnegDev"

COPY requirements.txt .
COPY . .

RUN pip3 install --upgrade pip -r requirements.txt

ENTRYPOINT ["python3", "__init__.py"]