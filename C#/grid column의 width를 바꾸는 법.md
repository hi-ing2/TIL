# grid column의 width를 바꾸는 법

```c#
//GridDefintion 태그의 x:Name이 gridProgress일 때 예시
gridProgress.Width = (GridLength)gridLengthConverter.ConvertFrom(0); //GridLenth 형식으로 바꾸어 주어야 한다.
```

 

