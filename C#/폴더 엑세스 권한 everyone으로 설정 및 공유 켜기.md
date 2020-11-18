# 폴더 엑세스 권한 everyone으로 설정 및 공유 켜기

### 1. 클라이언트 쪽

- 접근 권한 설정(접근 권한만 설정되며, 공유되기 직전 상태만 됨.)

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

- 공유 권한 설정(공유에 대한 읽기 및 쓰기 권한도 부여 필요!)

```C#
//위의 로직들과 함께 공유 로직안에 넣어주면 됨.
AddSharedPermissionHelper addSharedPermission = new AddSharedPermissionHelper();
addSharedPermission.AddPermissions("VCS", Domain, "Everyone");
////

class AddSharedPermissionHelper
{
    public void AddPermissions(string sharedFolderName, string domain, string userName)
    {

        // Step 1 - Getting the user Account Object
        ManagementObject sharedFolder = GetSharedFolderObject(sharedFolderName);
        if (sharedFolder == null)
        {
            System.Diagnostics.Trace.WriteLine("The shared folder with given name does not exist");
            return;
        }

        ManagementBaseObject securityDescriptorObject = sharedFolder.InvokeMethod("GetSecurityDescriptor", null, null);
        if (securityDescriptorObject == null)
        {
            System.Diagnostics.Trace.WriteLine(string.Format(CultureInfo.InvariantCulture, "Error extracting security descriptor of the shared path {0}.", sharedFolderName));
            return;
        }
        int returnCode = Convert.ToInt32(securityDescriptorObject.Properties["ReturnValue"].Value);
        if (returnCode != 0)
        {
            System.Diagnostics.Trace.WriteLine(string.Format(CultureInfo.InvariantCulture, "Error extracting security descriptor of the shared path {0}. Error Code{1}.", sharedFolderName, returnCode.ToString()));
            return;
        }

        ManagementBaseObject securityDescriptor = securityDescriptorObject.Properties["Descriptor"].Value as ManagementBaseObject;

        // Step 2 -- Extract Access Control List from the security descriptor
        int existingAcessControlEntriesCount = 0;
        ManagementBaseObject[] accessControlList = securityDescriptor.Properties["DACL"].Value as ManagementBaseObject[];

        if (accessControlList == null)
        {
            // If there aren't any entries in access control list or the list is empty - create one
            accessControlList = new ManagementBaseObject[1];
        }
        else
        {
            // Otherwise, resize the list to allow for all new users.
            existingAcessControlEntriesCount = accessControlList.Length;
            Array.Resize(ref accessControlList, accessControlList.Length + 1);
        }


        // Step 3 - Getting the user Account Object
        ManagementObject userAccountObject = GetUserAccountObject(domain, userName);
        ManagementObject securityIdentfierObject = new ManagementObject(string.Format("Win32_SID.SID='{0}'", (string)userAccountObject.Properties["SID"].Value));
        securityIdentfierObject.Get();

        // Step 4 - Create Trustee Object
        ManagementObject trusteeObject = CreateTrustee(domain, userName, securityIdentfierObject);

        // Step 5 - Create Access Control Entry
        ManagementObject accessControlEntry = CreateAccessControlEntry(trusteeObject, false);

        // Step 6 - Add Access Control Entry to the Access Control List
        accessControlList[existingAcessControlEntriesCount] = accessControlEntry;

        // Step 7 - Assign access Control list to security desciptor 
        securityDescriptor.Properties["DACL"].Value = accessControlList;

        // Step 8 - Assign access Control list to security desciptor 
        ManagementBaseObject parameterForSetSecurityDescriptor = sharedFolder.GetMethodParameters("SetSecurityDescriptor");
        parameterForSetSecurityDescriptor["Descriptor"] = securityDescriptor;
        sharedFolder.InvokeMethod("SetSecurityDescriptor", parameterForSetSecurityDescriptor, null);
    }

    /// <summary>
    /// The method returns ManagementObject object for the shared folder with given name
    /// </summary>
    /// <param name="sharedFolderName">string containing name of shared folder</param>
    /// <returns>Object of type ManagementObject for the shared folder.</returns>

    private static ManagementObject GetSharedFolderObject(string sharedFolderName)
    {
        ManagementObject sharedFolderObject = null;

        //Creating a searcher object to search 
        ManagementObjectSearcher searcher = new ManagementObjectSearcher("Select * from Win32_LogicalShareSecuritySetting where Name = '" + sharedFolderName + "'");
        ManagementObjectCollection resultOfSearch = searcher.Get();
        if (resultOfSearch.Count > 0)
        {
            //The search might return a number of objects with same shared name. I assume there is just going to be one
            foreach (ManagementObject sharedFolder in resultOfSearch)
            {
                sharedFolderObject = sharedFolder;
                break;
            }
        }
        return sharedFolderObject;
    }

    /// <summary>
    /// The method returns ManagementObject object for the user folder with given name
    /// </summary>
    /// <param name="domain">string containing domain name of user </param>
    /// <param name="alias">string containing the user's network name </param>
    /// <returns>Object of type ManagementObject for the user folder.</returns>

    private static ManagementObject GetUserAccountObject(string domain, string alias)
    {
        ManagementObject userAccountObject = null;
        ManagementObjectSearcher searcher = new ManagementObjectSearcher(string.Format("select * from Win32_Account where Name = '{0}' and Domain='{1}'", alias, domain));
        ManagementObjectCollection resultOfSearch = searcher.Get();
        if (resultOfSearch.Count > 0)
        {
            foreach (ManagementObject userAccount in resultOfSearch)
            {
                userAccountObject = userAccount;
                break;
            }
        }
        return userAccountObject;
    }

    /// <summary>
    /// Returns the Security Identifier Sid of the given user
    /// </summary>
    /// <param name="userAccountObject">The user object who's Sid needs to be returned</param>
    /// <returns></returns>

    private static ManagementObject GetAccountSecurityIdentifier(ManagementBaseObject userAccountObject)
    {
        ManagementObject securityIdentfierObject = new ManagementObject(string.Format("Win32_SID.SID='{0}'", (string)userAccountObject.Properties["SID"].Value));
        securityIdentfierObject.Get();
        return securityIdentfierObject;
    }

    /// <summary>
    /// Create a trustee object for the given user
    /// </summary>
    /// <param name="domain">name of domain</param>
    /// <param name="userName">the network name of the user</param>
    /// <param name="securityIdentifierOfUser">Object containing User's sid</param>
    /// <returns></returns>

    private static ManagementObject CreateTrustee(string domain, string userName, ManagementObject securityIdentifierOfUser)
    {
        ManagementObject trusteeObject = new ManagementClass("Win32_Trustee").CreateInstance();
        trusteeObject.Properties["Domain"].Value = domain;
        trusteeObject.Properties["Name"].Value = userName;
        trusteeObject.Properties["SID"].Value = securityIdentifierOfUser.Properties["BinaryRepresentation"].Value;
        trusteeObject.Properties["SidLength"].Value = securityIdentifierOfUser.Properties["SidLength"].Value;
        trusteeObject.Properties["SIDString"].Value = securityIdentifierOfUser.Properties["SID"].Value;
        return trusteeObject;
    }


    /// <summary>
    /// Create an Access Control Entry object for the given user
    /// </summary>
    /// <param name="trustee">The user's trustee object</param>
    /// <param name="deny">boolean to say if user permissions should be assigned or denied</param>
    /// <returns></returns>

    private static ManagementObject CreateAccessControlEntry(ManagementObject trustee, bool deny)
    {
        ManagementObject aceObject = new ManagementClass("Win32_ACE").CreateInstance();

        aceObject.Properties["AccessMask"].Value = 0x1U | 0x2U | 0x4U | 0x8U | 0x10U | 0x20U | 0x40U | 0x80U | 0x100U | 0x10000U | 0x20000U | 0x40000U | 0x80000U | 0x100000U; // all permissions
        aceObject.Properties["AceFlags"].Value = 0x0U; // no flags
        aceObject.Properties["AceType"].Value = deny ? 1U : 0U; // 0 = allow, 1 = deny
        aceObject.Properties["Trustee"].Value = trustee;
        return aceObject;
    }
}
```



### 2. 서버 쪽

```c#
public string password = "";
private void menuitemOpenVCSFolder_Click(object sender, RoutedEventArgs e)
{
    if (dataGrid.SelectedItems.Count == 1)
    {
        var v = ((Vehicle)dataGrid.SelectedItem);
        if (v.Connected)
        {
            SharingConnect(CTRL.DB.Vehicles[v.Id].VCSIp, "vcs", CTRL.DB.Vehicles[v.Id].LocalName);
        }
        else
        {
            MessageBox.Show("This VCS is disconnected. Please check this VCS.");
        }
    }
    else if (dataGrid.SelectedItems.Count > 1)
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

 public void InitSharingStatus(string ip, string path)
 {
     ProcessStartInfo proinfo = new ProcessStartInfo();
     Process pro = new Process();

     proinfo.FileName = "cmd";
     proinfo.UseShellExecute = false;
     proinfo.CreateNoWindow = true;
     proinfo.RedirectStandardOutput = true;
     proinfo.RedirectStandardInput = true;
     proinfo.RedirectStandardError = true;
     pro.StartInfo = proinfo;
     pro.Start();

     pro.StandardInput.Write($@"net use \\{ip}\{path} /delete");
     pro.StandardInput.Close();

     string outputResult = pro.StandardOutput.ReadToEnd();
     SLog.Write(ModuleType.SYSTEM, pro.StandardOutput.ReadToEnd());
     pro.Close();
 }

public void SharingConnect(string ip, string path, string localname)
{
    //1. 공유드라이브 만들기
    NetworkConnector networkConnector = new NetworkConnector();
    networkConnector.DisconnectNetwork(@$"\\{ip}\{path}");
    InputPasswordWindow inputPasswordWindow = new InputPasswordWindow();
    inputPasswordWindow.Owner = this;

    //2.result == '에러가 없으면 0, 비밀번호 공백 에러는 1327로 판단됨.' 이면 공유 디렉토리 실행
    var result = networkConnector.TryConnectNetwork(@$"\\{ip}\{path}", localname, password);
    if (result == 0 || result == 1327)
    {
        Process.Start("explorer.exe", @$"\\{ip}\{path}");
        return;
    }
    //에러가 있을 시
    else
    {
        // 이미 연결된 공유 정보 초기화(삭제)
        InitSharingStatus(ip, path);

        // 계속 진행
        while (true)
        {
            inputPasswordWindow.ShowDialog();
            ///if (InputBox("Input Password", @$"Please Input the Password of this IPC", ref password) == (DialogResult)1)
            if (inputPasswordWindow.DialogResult == true)
            {
                result = networkConnector.TryConnectNetwork(@$"\\{ip}\{path}", localname, password);
                if (result == 0 || result == 1327)
                {
                    Process.Start("explorer.exe", @$"\\{ip}\{path}");
                    return;
                }
                else
                {
                    MessageBox.Show("Please enter the password again ");
                    continue;
                }
            }
            else
            {
                password = "";
                return;
            }
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



in