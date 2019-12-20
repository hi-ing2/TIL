# chat_bot

## api 활용

### https://core.telegram.org/bots/api

- telegram api 주소

### https://api.telegram.org/bot<토큰>/<메소드명>

- 발급받은 내 토큰을 사용하여 '코드가 적용되는 주소'로 이동할 수 있다.



### 1. getMe

> https://api.telegram.org/<토큰>/getMe

 ``` python
 {
     "ok": true,
     "result": {
         "id": 1234567890,			# <-봇의 아이디
         "is_bot": true,
         "first_name": "hi",
         "username": "hi_bbot"
     }
 }
 
 ```

### 2. getupdates

> https://api.telegram.org/<토큰>/getupdates

```python
{
    "ok": true,
    "result": [
        {
            "update_id": 1234567890,			
            "message": {
                "message_id": 2,
                "from": {
                    "id": 1234567890,				# 표면적으로는 나의 아이디 이지만,
                    								# 개념적으로는 보낸 사람을 의미한다.
                    "is_bot": false,
                    "first_name": "니수",
                    "language_code": "ko"
                },
                "chat": {
                    "id": 1234567890, 				# <-나의 아이디
                    "first_name": "니수",
                    "type": "private"
                },
                "date": 1234567890,
                "text": "/hi",
                "entities": [
                    {
                        "offset": 0,
                        "length": 3,
                        "type": "bot_command"
                    }
                ]
            }
        }
    ]
}

```

### 3. sendMessage

> https://api.telegram.org/<토큰>/getMessage

```python

{
    "ok": true,
    "result": {
        "message_id": 3,
        "from": {
            "id": 1234567890,			# <-봇의 아이디
            "is_bot": true,
            "first_name": "hi",
            "username": "hi_bbot"
        },
        "chat": {
            "id": 1234567890,			# <-나의 아이디
            "first_name": "니수",
            "type": "private"
        },
        "date": 1234567890,
        "text": "안녕하세요"
    }
}

```

- ### ngrok(webhook)

> ngrok을 통하여 webhook 기능을 이용한다. (가상의 공용 인트라 서버를 만들어줌)

```cmd
C:\Users\mhs34\Desktop>ngrok http 5000 //개인주소(flask) 뒤에 포트번호(5000)를 입력해서 가상
									   //서버를 오픈함 (ngrok.exe가 저장된 곳에서 입력해야함)

|
|
v

ngrok by @inconshreveable                                            (Ctrl+C to quit)                                   

Session Status                online
Session Expires               7 hours, 56 minutes
Version                       2.3.35
Region                        United States (us)
Web Interface                 http://127.0.0.1:4040
Forwarding                    http://abcd1234.ngrok.io -> http://localhost:5000
Forwarding                    https://abcd1234.ngrok.io -> http://localhost:5000

Connections                   ttl     opn     rt1     rt5     p50     p90
                              0       0       0.00    0.00    0.00    0.00


// abcd1234 <- 이 부분이 ngrok의 고유 주소 번호임. (위와 같으면 열린 상태)
```

