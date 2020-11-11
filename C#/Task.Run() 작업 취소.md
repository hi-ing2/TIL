# Task.Run() 작업 취소

```c#
// (1) CancellationTokenSource 필드
private CancellationTokenSource cancelTokenSource;

private void btnStart_Click(object sender, EventArgs e)
{
    Run();
}

private async void Run()
{            
    // (2) CancellationTokenSource 객체 생성
    cancelTokenSource = new CancellationTokenSource();
    CancellationToken token = cancelTokenSource.Token;

    // (Optional) 여기서의 StartNew 메서드는 StartNew(Func, CancellationToken)를 호출함.
    // 여기서 Token이 Cancel 상태(토큰이 false)이면
    // LongCalcAsync() 메서드 자체가 실행되지 않는다는 의미
    
    // 정의하는 순간부터 쓰레드 활성화
    // var task1 = Task.Run(()=>LongCalcAsync(), token)
    var task1 = Task.Factory.StartNew<object>(LongCalcAsync, token);             
       
    dynamic res = await task1;
    
    if (res != null)
    {
        this.label1.Text = "Sum: " + res.Sum;
    }
    else
    {
        this.label1.Text = "Cancelled ";
    }
}

private void btnCancel_Click(object sender, EventArgs e)
{
    // (4) 작업 취소 (IsCancellationRequested = true)
    cancelTokenSource.Cancel();
}

private object LongCalcAsync() 
{
    int sum = 0;
    for (int i = 0; i < 100; i++)
    {
        /// (3) 작업 취소인지 체크
        if (cancelTokenSource.Token.IsCancellationRequested)
        {
            return null;
        }
        sum += i;
        Thread.Sleep(1000);
    }
    return new { Sum = sum };
```

> 쓰레드를 원하는 순간부터 돌리고 죽이는 방법 vs 쓰레드를 계속 돌려놓고 플래그값으로 진행 및 중지 하는 방법 중 전자에 해당함.
>
> 프로그램 성향에 따라서 사용하는 방법은 다름.