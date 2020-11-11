# mstsc 바로가기 만들어서 바로 원격접속하기

```c#
 private void menuitemRemote_Click(object sender, RoutedEventArgs e)
 {
     if (dataGrid.SelectedItems.Count == 1)
     {
         foreach (Vehicle v in dataGrid.SelectedItems)
         {
             if (v.Connected)
             {
                 //var pwstr = BitConverter.ToString(DataProtection.ProtectData(Encoding.Unicode.GetBytes(info.Password), "")).Replace("-", "");
                 //templateRPD 파일에 필요한 변수값들을 넣어준다. 이 파일을 바탕으로 rpd파일을 생성하기 때문임.(VCSControlServer참조) 
                 var rdpInfo = String.Format(File.ReadAllText(RdpConstant.templatePath), CTRL.DB.Vehicles[v.Id].VCSIp, 											CTRL.DB.Vehicles[v.Id].LocalName );
                 File.WriteAllText(RdpConstant.RdpPath, rdpInfo);
                 ConnectMstsc("mstsc " + RdpConstant.RdpPath);
             }
             else
             {
                 MessageBox.Show("This VCS is disconnected. Please check this VCS.");
             }
         }
     }
     else if(dataGrid.SelectedItems.Count > 1)
     {
         MessageBox.Show("Please select only one VCS.");
         return;
     }
     else
     {
         MessageBox.Show("Please select one VCS.");
         return;
     }
 }
// 콘솔창으로 rpd파일 실행하는 로직
public static void ConnectMstsc(String cmd)
{
    Process p = new Process();
    p.StartInfo.FileName = "cmd.exe";
    p.StartInfo.UseShellExecute = false;
    p.StartInfo.RedirectStandardInput = true;
    p.StartInfo.CreateNoWindow = true;
    p.Start();
    p.StandardInput.WriteLine(cmd);
}
```

