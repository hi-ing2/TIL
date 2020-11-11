# WPF에서 트레이 아이콘 클릭 시, 창 맨위로 오게하는 방법.md

```C#
this.Topmost = true; // 가장 위로 올린뒤 (항상 맨위에)
this.Focus(); // 활성화
this.Topmost = false; // '항상 맨위에' 풀기
```

