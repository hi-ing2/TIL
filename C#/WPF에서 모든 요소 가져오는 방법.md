# WPF에서 모든 요소 가져오는 방법

```c#
//전체 요소찾는 메소드 
public static IEnumerable<T> FindVisualChildren<T>(DependencyObject depObj) where T : DependencyObject
        {
            if (depObj != null)
            {
                for (int i = 0; i < VisualTreeHelper.GetChildrenCount(depObj); i++)
                {
                    DependencyObject child = VisualTreeHelper.GetChild(depObj, i);
                    if (child != null && child is T)
                    {
                        yield return (T)child;
                    }

                    foreach (T childOfChild in FindVisualChildren<T>(child))
                    {
                        yield return childOfChild;
                    }
                }
            }
        }
```

```c#
 foreach (Button bt in FindVisualChildren<Button>(App.mainWindow)) 
 //(요소타입클래스 변수 in FindVisualChildren<요소타입클래스>(App.mainWindow))
            {
                bt.IsEnabled = false;
            }
```

