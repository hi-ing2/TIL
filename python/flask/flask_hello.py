from flask import Flask, escape, request, render_template #flask는 html 파일을 불러올 수 있는 패키지(render_template)
import random
app = Flask(__name__)

@app.route('/') #메인 홈 루트 지정
def hello(): 
    n = request.args.get("name", "World") #"name"은 "world"를 담고 있음.
    return f'Hello, {escape(n)}!' #hello, + name 출력

@app.route('/fstring')
def fstring():
    fstring = "문희수"
 
    return f"제 이름은 {fstring} 입니다."   
    #return "제 이름은 {} 입니다.".format(fstring)


@app.route('/hi') # http://127.0.0.1:5000/hi 접속
def hi():
    name = "문희수"
    return render_template('hi.html', html_name = name) #주소 ./hi 에서 표시, hi.html을 렌더링, html_name에 name을 입력(hi.html과 연동)

@app.route('/greeting/<string:name>/') #string선언의 name변수
def greeting(name):
    def_name = name #def hi안에 name과는 다름
    return render_template('greeting.html', html_name = def_name) #주소 ./greeting/* 에서 표시, greeting.html을 렌더링, html_name에 def_name을 입력(greeting.html과 연동)


@app.route('/cube/<int:num>')
def cube(num):
    def_num = num
    def_num2 = num**3 
    return render_template('cube.html' , html_num = def_num, html_num2 = def_num2) #주소 ./cube/* 에서 표시, cube.html을 렌더링, html_num에 def_num을 입력(cube.html과 연동)
                                                                                   #html_num2에 def_num2를 입력(cube.html과 연동)
                                                                                   #a = b / a는 html상 변수명, b는 현 파이썬 상 변수명


@app.route('/dinner')
def dinner():
    menu = ['삼각김밥','컵라면','스테이크','훠궈','마라탕']
    dinner = random.choice(menu)
    menu_img = {
        '삼각김밥' : 'https://file.mk.co.kr/meet/neds/2019/07/image_readtop_2019_483806_15621353303812011.jpg',
        '컵라면' : 'https://ww.namu.la/s/2c562be36dadf354ef28ecc5739bc7a18cbee68e5526143d3db3a16919582f2cb59ef8a8e567fbb84271d037a3c4e9b2367941c8d241c3acb947e3124626ff679e2eec94d67925a31c6c08a3c10d7ddef0ccc7d8e211445d4c0e9df944ea1e09',
        '스테이크' : 'http://recipe1.ezmember.co.kr/cache/recipe/2017/07/09/6741acc7f6bf0f7d04245851fb365c311.jpg',
        '훠궈' : 'https://mp-seoul-image-production-s3.mangoplate.com/1250135_1547430500643161.jpg?fit=around|738:738&crop=738:738;*,*&output-format=jpg&output-quality=80',
        '마라탕' : 'http://img1.tmon.kr/cdn3/deals/2019/05/24/2099407062/original_2099407062_front_935dc_1558688230production.jpg'
    }
    img_url = menu_img[dinner]
    return render_template('dinner.html', dinner = dinner, img_url = img_url)

@app.route('/movies')
def movies():
    movie = ['조커','겨울왕국2','터미네이터','어벤져스']
    return render_template('movies.html', movies = movie)



if __name__== "__main__":
    app.run(debug=True) #python파일 실행 명령어(python *.py)로 바로 실행시키기 위해서 추가함 (env FLASK_APP=*.py flask run 역할)
                        #debug (개발자)모드를 on시켜줌. 수정할때마다 서버 재부팅이 됨.
