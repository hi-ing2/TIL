# WPF에서 모달 창을 메인 창 한가운데 show하기

```c#
this.WindowStartupLocation = WindowStartupLocation.CenterOwner;
```

in Childwindow.xaml.cs(생성자 부분)



```c#
Childwindow childwnidow = new Childwindow();
childwindow.Owner = this;
```

in Parentwindow.xaml.cs(Mainwindow.xaml.cs)