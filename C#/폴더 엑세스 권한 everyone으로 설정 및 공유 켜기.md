# 폴더 엑세스 권한 everyone으로 설정 및 공유 켜기

### 1. 클라이언트 쪽

- 권한 설정(권한만 설정되며, 공유되기 직전 상태만 됨.)

```c#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Principal;
using System.Security.AccessControl;
using System.IO;

namespace RSSetDirPermissionsCS
{
    public class RsDirPermissions
    {

        string _lastError = "";
        
        /// <summary>
        /// Set Everyone Full Control permissions for selected directory
        /// </summary>
        /// <param name="dirName"></param>
        /// <returns></returns>
       bool SetEveryoneAccess(string dirName)
        {

            try
            {
                // Make sure directory exists
                if (Directory.Exists(dirName) == false)
                    throw new Exception(string.Format("Directory {0} does not exist, so permissions cannot be set.", dirName));

                // Get directory access info
                DirectoryInfo dinfo = new DirectoryInfo(dirName);
                DirectorySecurity dSecurity = dinfo.GetAccessControl();

                // Add the FileSystemAccessRule to the security settings. 
                dSecurity.AddAccessRule(new FileSystemAccessRule(new SecurityIdentifier(WellKnownSidType.WorldSid, null), 						FileSystemRights.FullControl, InheritanceFlags.ObjectInherit | InheritanceFlags.ContainerInherit, 								PropagationFlags.NoPropagateInherit, AccessControlType.Allow));

                // Set the access control
                dinfo.SetAccessControl(dSecurity);

                _lastError = String.Format("Everyone FullControl Permissions were set for directory {0}", dirName));

                return true;

            } catch (Exception ex)
            {
                _lastError = ex.Message;
                return false;
            }


        }

    }
}
```



- 공유 켜기

```c#
private static void QshareFolder(string FolderPath, string ShareName, string Description)
{
    try
    {
        // Create a ManagementClass object

        ManagementClass managementClass = new ManagementClass("Win32_Share");

        // Create ManagementBaseObjects for in and out parameters

        ManagementBaseObject inParams = managementClass.GetMethodParameters("Create");

        ManagementBaseObject outParams;

        // Set the input parameters

        inParams["Description"] = Description;
		//디렉토리 이름이 아닌, 생성해줄 서버쪽에 공유해줄 이름이다.
        inParams["Name"] = ShareName;

        inParams["Path"] = FolderPath;

        inParams["Type"] = 0x0; // Disk Drive

        //Another Type:

        // DISK_DRIVE = 0x0

        // PRINT_QUEUE = 0x1

        // DEVICE = 0x2

        // IPC = 0x3

        // DISK_DRIVE_ADMIN = 0x80000000

        // PRINT_QUEUE_ADMIN = 0x80000001

        // DEVICE_ADMIN = 0x80000002

        // IPC_ADMIN = 0x8000003

        //inParams["MaximumAllowed"] = int maxConnectionsNum;

        // Invoke the method on the ManagementClass object

        outParams = managementClass.InvokeMethod("Create", inParams, null);

        // Check to see if the method invocation was successful

        if ((uint) (outParams.Properties["ReturnValue"].Value) != 0)

        {

            throw new Exception("Unable to share directory.");

        }

    }
	catch (Exception ex)
    {
        //MessageBox.Show(ex.Message, "error!");
    }
}

```



### 2. 서버 쪽

```c#
public string password = "";
private void menuitemOpenVCSFolder_Click(object sender, RoutedEventArgs e)
{
    //공유 네트워크 만들기
    NetworkConnector networkConnector = new NetworkConnector();
    while (true)
    {
        //에러가 없으면 result = 0, 디렉토리 실행
        var result = networkConnector.TryConnectNetwork(@$"\\{CTRL.DB.Vehicles[v.Id].VCSIp}\vcs", 													CTRL.DB.Vehicles[v.Id].LocalName, textBox_InputBox.Text);
        if (result == 0)
        {
            Process.Start("explorer.exe", @$"\\{CTRL.DB.Vehicles[v.Id].VCSIp}\vcs");
            return;
        }
        //에러가 있을 시, 비밀번호 입력
        else
        {
            if (InputBox("Input Password", @$"Please Input the Password of this IPC", ref password) == (DialogResult)1)
            {
                continue;
                //inputFlag = true;
            }
            else
            {
                //inputFlag = false;
                textBox_InputBox.Text = "";
                return;
            }
        }
    }
```

in Form UI

```c#
public static System.Windows.Forms.DialogResult InputBox(string title, string content, ref string text)
{
    System.Windows.Forms.Form form = new System.Windows.Forms.Form();
    System.Windows.Forms.PictureBox picture = new System.Windows.Forms.PictureBox();
    System.Windows.Forms.Label label = new System.Windows.Forms.Label();
    System.Windows.Forms.Button buttonOk = new System.Windows.Forms.Button();
    System.Windows.Forms.Button buttonCancel = new System.Windows.Forms.Button();

    form.ClientSize = new System.Drawing.Size(350, 100);
    form.Controls.AddRange(new System.Windows.Forms.Control[] { label, picture, textBox_InputBox, buttonOk, buttonCancel });
    form.FormBorderStyle = FormBorderStyle.FixedDialog;
    form.StartPosition = FormStartPosition.CenterScreen;
    form.MaximizeBox = false;
    form.MinimizeBox = false;
    form.AcceptButton = buttonOk;
    form.CancelButton = buttonCancel;

    form.Text = title;
    //picture.Image = Properties.Resources.synustech;
    picture.SizeMode = PictureBoxSizeMode.StretchImage;
    label.Text = content;
    textBox_InputBox.PasswordChar = '*';
    textBox_InputBox.Text = text;
    buttonOk.Text = "OK";
    buttonCancel.Text = "Cancel";

    buttonOk.DialogResult = (DialogResult)1;
    buttonCancel.DialogResult = (DialogResult)2;

    picture.SetBounds(10, 10, 50, 50);
    label.SetBounds(65, 17, 220, 20);
    textBox_InputBox.SetBounds(65, 40, 100, 20);
    buttonOk.SetBounds(135, 70, 70, 20);
    buttonCancel.SetBounds(215, 70, 70, 20);

    DialogResult dialogResult = form.ShowDialog();

    text = textBox_InputBox.Text;
    return dialogResult;
}
```

in InputBox 메소드

```c#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace VCSControlServer
{
    class NetworkConnector
    {
        public NETRESOURCE NetResource = new NETRESOURCE();
        [DllImport("mpr.dll", CharSet = CharSet.Auto)]
        public static extern int WNetUseConnection(
                    IntPtr hwndOwner,
                    [MarshalAs(UnmanagedType.Struct)] ref NETRESOURCE lpNetResource,
                    string lpPassword,
                    string lpUserID,
                    uint dwFlags,
                    StringBuilder lpAccessName,
                    ref int lpBufferSize,
                    out uint lpResult);
        [DllImport("mpr.dll", EntryPoint = "WNetCancelConnection2", CharSet = CharSet.Auto)]
        public static extern int WNetCancelConnection2A(String lpName, int dwFlags, int fForce);
        [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Auto)]
        public struct NETRESOURCE
        {
            public uint dwScope;
            public uint dwType;
            public uint dwDisplayType;
            public uint dwUsage;
            public string lpLocalName;
            public string lpRemoteName;
            public string lpComment;
            public string lpProvider;

        }

        public int TryConnectNetwork(string remotePath, string userID, string pwd)
        {
            
            int result;
            int capacity = 1028;
            uint resultFlags = 0;
            uint flags = 0;
            StringBuilder sb = new StringBuilder(capacity);
            NetResource.dwType = 1; // 공유 디스크
            NetResource.lpLocalName = null;   // 로컬 드라이브 지정하지 않음
            NetResource.lpRemoteName = remotePath;
            NetResource.lpProvider = null;
            result = WNetUseConnection(IntPtr.Zero, ref NetResource, pwd,
                userID, flags, sb, ref capacity, out resultFlags);
            return result;
        }

        private void InputPasswordWindow_PasswordClickEvent(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }

        public void DisconnectNetwork()
        {
            WNetCancelConnection2A(NetResource.lpRemoteName, 1, 0);
        }
    }
}
```

in NetworkConnector 클래스