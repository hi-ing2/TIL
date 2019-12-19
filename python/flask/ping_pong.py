from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/ping')
def ping():
    return render_template('ping.html') #데이터 ping(전송)용

@app.route('/pong')
def pong():
    data = request.args.get('keyword') #requests랑 다름, flask에서 지원해줌/ ping.html에서 text로 입력되었던 keyword값을 들고옴 
    return render_template('pong.html', data = data) #데이터 pong(수신)용/ templates 폴더 경로를 기본적으로 자동 인식

@app.route('/naver')
def naver():
    return render_template('naver.html') # 네이버는 수신받을 수 있는 방법이 있기에 get방식/data를 입력해 주지 않아도 됨.


if __name__== ("__main__"):
    app.run(debug=True)
