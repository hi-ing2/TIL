#  WPF프로젝트에서 MainWindow.xaml의 함수 호출 방법



app.xaml에서

```xaml
<Application x:Class="VCSControlService.App"
             xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
             xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"
             xmlns:local="clr-namespace:VCSControlService"
             Startup="Application_Startup"> <!--기존의 StartUri="MainWindow.xaml" 부분을 좌측과 같이 수정(Main메소드 역할부분)-->
    <Application.Resources>
    </Application.Resources>
</Application>

```

app.xaml.cs에서

```c#
 public partial class App : Application
    {
        public static MainWindow mainWindow = new MainWindow();// MainWindow를 static으로 새로 정의하여 new 생성
        public void Application_Startup(object sender, StartupEventArgs e)
        {
            mainWindow.Show(); 
        }
    }
```

다른 클래스에서

```c#
App.mainwindow.mwfunc(); // 이렇게 호출 가능
```

mainwindow.xaml에서

```c#
 // 스레드 충돌 가능성을 배제하기 위해 비동기 스레드 방식인 Dispatcher 메소드를 사용	한 메소드임.
 public void myfunc(){
     Dispatcher.Invoke(DispatcherPriority.Normal, new Action(delegate
                {
                    iconIsConnected.Fill = Brushes.Red;
                }));
 }
```

> 1번 문제점 : 메소드를 바로 호출하니,
>
> System.InvalidOperationException: 다른 스레드가 이 개체를 소유하고 있어 호출 스레드가 해당 개체에 액세스할 수 없습니다.
>    at System.Windows.Threading.Dispatcher.VerifyAccess()
>    at System.Windows.Application.get_MainWindow()
>    at VCSControlService.Worker.ExecuteAsync(CancellationToken stoppingToken) in D:\vcscontrolservice\VCSControlService\Worker.cs:line 293
>
> 와 같은 문제점 발생
>
> 
>
> 2번 문제점 :1번을 해결하기 위해 Dispatcher 메소드를 이용해 비동기 방식으로 진행하려 하였으나, Dispatcher 메소드(다른 클래스에서 사용할 때)에서는 static으로 정의한 변수(생성한 변수가 아닌, MainWindow UI 요소에서 이미 정의된 변수)나 메소드를 사용할 수 없는 문제가 발생.

- 해결방안 - 위메인윈도우를 바로 호출하지 않고,  코드와 같이 메인 메소드를 새로 추가하여 메인윈도우를 static 방식으로 재정의하여 호출. static으로 정의되면서 스레드 오류가 생기지 않음.