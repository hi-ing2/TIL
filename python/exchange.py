# kospi.py 참조

import requests
from bs4 import BeautifulSoup

url ="https://finance.naver.com/marketindex/"

req = requests.get(url).text

soup = BeautifulSoup(req, 'html.parser')

exchange = soup.select_one('#exchangeList > li.on > a.head.usd > div > span.value') #고유 id값(exchangeList)에서 고유 value값(span.value) 하나만 골라줘

print(exchange.text)