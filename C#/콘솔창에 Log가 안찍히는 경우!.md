# 콘솔창에 Log가 안찍히는 경우!

``` c#
Log.Logger = new LoggerConfiguration()
            .MinimumLevel.Information()
            //.MinimumLevel.Override("Microsoft", Serilog.Events.LogEventLevel.Warning)
            .Enrich.FromLogContext()
            .WriteTo.RollingFile(@"Log\LogFile-{Date}.txt")
            .WriteTo.Console(Serilog.Events.LogEventLevel.Information)
            .CreateLogger();


try
{
	//콘솔창에 로그 찍혀나오게 하도록 쓰레드 돌리는 로직
    Log.Information(Environment.CurrentDirectory);
    Task.Run(() => ConsoleRunning(e.Args));
    //보통 consolerunning 밑에다가 필요한 메인코드 추가.(콘솔창이 쓰레드 상 돌아가고 있는 상태에서 다음 작업 진행)
}
catch (Exception ex)
{
    Log.Fatal(ex, "VCS control service has the problem.");
    return;
}
finally
{
	//Log 종료(콘솔창은 계속실행되고있음.주의!!!! 종료 된지모르고.. 계속 삽질함..)
    Log.CloseAndFlush();
}
```