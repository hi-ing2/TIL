import requests
from bs4 import BeautifulSoup
url = "https://finance.naver.com/sise/"

#request = requests.get(url)
request = requests.get(url).text
# print(request)

soup = BeautifulSoup(request, 'html.parser') # request 형식에 있는 html을 파싱해줘(기준에 대해서 쪼개줘)
# print(soup)
kospi = soup.select_one("#KOSPI_now") # soup안에서 #KOSPI_now 부분 하나만 골라줘
print(kospi.text) #kospi안에서 text부분만 골라서 / 출력해줘