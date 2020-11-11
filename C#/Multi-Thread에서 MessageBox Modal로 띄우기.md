# Multi-Thread에서 MessageBox Modal로 띄우기

```c#
//따로 돌리는 쓰레드에서 Dispatcher.Invoke 메소드를 적용시켜주면 된다.
Dispatcher.Invoke(System.Windows.Threading.DispatcherPriority.Normal, new Action(delegate
{
	MessageBox.Show($@"Timeout! {vl.Count}, {des}");
}));
```

