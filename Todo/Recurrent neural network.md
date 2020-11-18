# Recurrent neural network

### 시퀀스 데이터를 응용한 방법

### 타임시리즈 데이터

### RNN

### 같은 선상의 hidden에서는 같은 activation을 쓴다



### 뒤로 갈수록 Loss를 줄여간다





## ex) Character-level Language Model

### hello가 트레이닝 데이터

### 다음에 무슨 단어/철자가 나올지 예측하는 모델

### 초록 숫자는 커지길 바라고, 빨간 숫자는 작아지길 바란다. (지속적인 미분 및 activation을 통해?)



## ex) char-RNN

### input 만들어진 창작물 - ouput 새로만든 창작물



## Long Short-Term Memory

3 gates(input, forget, output)

더하기를 이용하여 미분값의 손실이 적어짐

잊을건 잊고, 인풋할 건 인풋하여, 효율성 증대

##  GRU(Gated Recurrent Units) 

2 gates(update <= forget + input> , reset)

LSTM에 비해 간소화 (2개의 게이트만 사용) 



## bi-directional (양방향)

## seq 2 seq (encoder - V - decoder)

 

## ex) quick, draw! 

그리는 순서에 따라 예측 (RNN 방식)  



## Multimodal applications

CNN/RNN 둘 다 사용

image(CNN) -> captions(단어들, 설명)(RNN)