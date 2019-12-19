import requests
from bs4 import BeautifulSoup
#네이버 실시간 검색어

url = "http://www.naver.com/"

req = requests.get(url).text

soup = BeautifulSoup(req, 'html.parser')

search = soup.select("#PM_ID_ct > div.header > div.section_navbar > div.area_hotkeyword.PM_CL_realtimeKeyword_base > div.ah_roll.PM_CL_realtimeKeyword_rolling_base > div > ul > li > a > span.ah_k")


for item in search:
    print(item.text)
num = int(input("실시간 순위를 입력하세요\n"))
print('{}위 검색어는 {}입니다.' .format(num, search[num-1].text))
