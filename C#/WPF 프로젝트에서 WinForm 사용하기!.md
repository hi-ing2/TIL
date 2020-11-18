

# WPF 프로젝트에서 WinForm 사용하기!

> 해당 WPF 프로젝트 우 클릭후 **'프로젝트 파일 편집'**을 클릭하면, 아래와 같은 파일이 출력된다.

```c#
<Project Sdk="Microsoft.NET.Sdk.WindowsDesktop">

  <PropertyGroup>
    <OutputType>WinExe</OutputType>
    <TargetFramework>netcoreapp3.1</TargetFramework>
    <UseWPF>true</UseWPF>
	<UseWindowsForms>true</UseWindowsForms> //해당 구문을 추가하면, 원활한 WinForm 기능 사용이 가능하다.
    <StartupObject></StartupObject>
  </PropertyGroup>

  <ItemGroup>
    <None Remove="appsettings.json" />
  </ItemGroup>

  <ItemGroup>
    <Content Include="appsettings.json">
      <CopyToOutputDirectory>PreserveNewest</CopyToOutputDirectory>
      <CopyToPublishDirectory>PreserveNewest</CopyToPublishDirectory>
    </Content>
  </ItemGroup>

  <ItemGroup>
    <PackageReference Include="IOptionsWriter" Version="4.7.0.1" />
    <PackageReference Include="Microsoft.Extensions.Hosting" Version="3.1.8" />
    <PackageReference Include="Microsoft.Extensions.Hosting.WindowsServices" Version="3.1.8" />
    <PackageReference Include="Microsoft.TestPlatform.TestHost" Version="16.7.1" />
    <PackageReference Include="Serilog.AspNetCore" Version="3.4.0" />
    <PackageReference Include="Serilog.Sinks.RollingFile" Version="3.3.0" />
    <PackageReference Include="SuperSocket.ClientEngine.Core" Version="0.10.0" />
    <PackageReference Include="System.IO.FileSystem" Version="4.3.0" />
    <PackageReference Include="WindowsAPICodePack-Shell" Version="1.1.0" />
  </ItemGroup>

</Project>
```

