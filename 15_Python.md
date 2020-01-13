# Python

## 자료형

### List

- append 함수 vs expend 함수

  - append 함수

    ``` cmd
    >>> list.append(['가', '나'])
    [1, 'a', ['가', '나']]
    ```
    > 중첩리스트 형태로 넣고 싶을때
  - append 함수

    ``` cmd
    >>> list.extend(['가', '나'])
    [1, 'a', '가', '나']
    ```
    > 리스트 안에 여러개 요소를 넣고 싶을때

###  Dictionary

- get() : key를 이용하여 value를 조회

  ``` cmd
  >>> dict = { 'name':'ggoreb', 'age':20, 'hobby':['당구', '배드민턴'] }
  >>> result = dict.get('id')
  >>> print(result)
  None # 해당 key가 있는지 없는지도 친절히 출력
  >>> result = dict['id']
  error # 해당 key가 없으면 불친절하게 에러
  ```




## 제어문

### and / or

> 영어로 쳐야한다. & / % 사용 불가
>
> and 가 or보다 우선순위가 높음 (괄호를 잘 넣어줘야함)



## jupyter

주피터 노트북 설정파일 만들기

``` cmd
jupyter notebook --generate-config
```



설정파일 생성 위치

> config 파일 내에 c.NotebookApp.browser='주피터 크롬브라우저 위치' 로 변경 해주면 된다. 

## 함수

### def vs lamda

> 코딩 도중 즉석으로 간단한 함수를 만들어야 될때, lambda를 자주 이용한다.