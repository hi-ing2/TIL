from flask import Flask, render_template, request
import requests
from bs4 import BeautifulSoup

app = Flask(__name__)


@app.route('/search') #송신
def search():
    return render_template('search.html')


@app.route('/opgg') #수신
def opgg():
    userName = request.args.get('userName')
    url = f"http://www.op.gg/summoner/userName={userName}"
    req = requests.get(url).text #text가 없으면 응답값만 불러줌
    data = BeautifulSoup(req, 'html.parser')
    rank = data.select_one("#SummonerLayoutContent > div.tabItem.Content.SummonerLayoutContent.summonerLayout-summary > div.SideContent > div.TierBox.Box > div > div.TierRankInfo > div.TierRank")
    win = data.select_one("#SummonerLayoutContent > div.tabItem.Content.SummonerLayoutContent.summonerLayout-summary > div.SideContent > div.TierBox.Box > div > div.TierRankInfo > div.TierInfo > span.WinLose > span.wins")
    return render_template('opgg.html', userName = userName, rank = rank.text, win = win.text)
    


if __name__== ("__main__"):
    app.run(debug=True)

