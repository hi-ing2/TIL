# 파일명 추출(string[] -> List<string>)

```c#
try
{
    //Directory 클래스 사용하면 string 배열 타입으로 밖에 못 뽑음. 경로 포함 파일명 추출됨
    string[] files = Directory.GetFiles(VCSConfigFilePath, "*");
    
    foreach (var file in files)
    {
        //파일명 추출, configFileName은 string 타입으로 추출됨. 
        var configFileName = file.Substring(file.LastIndexOf("\\"), file.Length - file.LastIndexOf("\\")).Remove(0, 1);
        //ConfigFiles(string 리스트)에 추가
        ConfigFiles.Add(configFileName);
    }
} 
catch (Exception ex)
{
    ConfigFiles = _configuration.GetSection("Configs").Get<List<string>>();
}

```

