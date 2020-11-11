# appsettings.json 값 불러오기

```c#
IConfiguration appsettings = new ConfigurationBuilder()
                            .AddJsonFile("appsettings.json")
                            .Build();
// [] 안에 원하는 value의 key값을 넣으면 됨.
var VCS = appsettings["Folders:VCS"];
```

