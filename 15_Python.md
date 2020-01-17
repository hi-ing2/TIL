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



## 예외처리

## 내장함수

### map vs filter

> map은 적용! filter는 제거!

## 외장함수

## 정규식

## 크롤링

### Selenium

- find_element

  > 요소를 찾는 함수는 모두 상호작용이 가능하다.

  ``` python
  trs = driver.find_element_by_css_selector('#table01 > tbody > tr:nth-child(5))
  obj = trs.find_element_by_class_name('some') #trs안에서 또 찾음
  ```

  

  - find_element_by_css_selector

    > 경로를 표시해줄 때, 바로 아래 자식은 '>' 표시 / 모든 자식들은 ' '(여백)으로 준다.

    ``` python
    driver.find_elements_by_css_selector('body > div.container > div > div > div.panelZone > div.oTravelBox > ul > li > div > div.title-row > div > h5') # 전자 경우
    ```

driver.find_elements_by_css_selector('#tab_con1 > .first tr') #후자 경우
```

## DB


```