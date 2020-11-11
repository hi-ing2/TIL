# C# 레지스트리에 시작프로그램 등록하는 방법 적용 후 정상적으로 동작안될 때 대처법

> 직접 Startup 폴더에 exe 파일 바로가기를 넣어주는 방법이 있다.

```c#
private void btnAddStartUp_Click(object sender, EventArgs e)
{
    try
    {
        string pathToVCSControlServiceExe = Environment.CurrentDirectory + "\\" + AppDomain.CurrentDomain.FriendlyName + ".exe";
        string commonStartMeunPath = Environment.GetFolderPath(Environment.SpecialFolder.CommonStartMenu);
        string appStartMenuPath = System.IO.Path.Combine(commonStartMeunPath, "Programs", "StartUp");


        if (!Directory.Exists(appStartMenuPath))
            Directory.CreateDirectory(appStartMenuPath);

        string shortcutLocation = System.IO.Path.Combine(appStartMenuPath, "ShortcutToVCSApp" + ".lnk");
        if (System.IO.File.Exists(shortcutLocation))
        {
            System.IO.File.Delete(shortcutLocation);
        }

        WshShell shell = new WshShell();
        IWshShortcut shortcut = (IWshShortcut)shell.CreateShortcut(shortcutLocation);
        shortcut.Description = "Test App Des";
        shortcut.TargetPath = pathToVCSControlServiceExe;
        //'시작 위치'가 레지스트리 등록 방식에서는 참조되지 않기 때문에 정상적으로 실행되지 않는 문제라 판단된다.
        shortcut.WorkingDirectory = Environment.CurrentDirectory;
        shortcut.Save();

        MessageBox.Show("Add Startup Success");
        SLog.Write(ModuleType.SYSTEM, "Add Startup Success");
    }
    catch (Exception ex)
    {
        MessageBox.Show("Add Startup Fail");
        SLog.Exception("btnAddStartUp_Click");
    }
}
```

