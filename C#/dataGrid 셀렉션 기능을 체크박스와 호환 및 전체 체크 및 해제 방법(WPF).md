# dataGrid 셀렉션 기능을 체크박스와 호환 및 전체 체크 및 해제 방법(WPF)

> C# 코드

``` c#
// xaml 파일의 Selector.SelectionChanged 값이 바뀔때마다 호출(셀 선택부분이 달라질때 마다 호출)
private void CheckandUncheck(object sender, RoutedEventArgs e)
        {   
            foreach (Vehicle v in dataGrid.Items)
            {
                v.Checker = false;
            }
            SelectedCheck(sender, e);
			// 전체 체크 박스 상태 true(checked) && 전체 체크 박스를 체크 안하고 다른 곳을 눌렀을 때
    		// 이 부분 어떻게 조건 넣는지 3시간동안 해맴 주의필요!!!!
    		// .IsFoucsed : 해당 요소를 건들였는지 안건들였는지 파악한다고 보면됨.
            if (((ToggleButton)dataGrid.Columns[0].Header).IsChecked == true 
                && ((ToggleButton)dataGrid.Columns[0].Header).IsFocused == false)
                //전체 체크 박스 상태 false(unchecked)로 변경
                ((ToggleButton)dataGrid.Columns[0].Header).IsChecked = false;
        }

//전체 체크 박스를 체크 및 해제 할때마다 전체 체크하는 메소드 
private void Allcheck(object sender, RoutedEventArgs e)
        {
    		//selectall,unselectall 하는 순간(selection이 change)
    		//CheckandUncheck 메소드 호출함.
            if (((ToggleButton)dataGrid.Columns[0].Header).IsChecked == false)
            {	
                dataGrid.UnselectAll();
            }
            else
            {
                dataGrid.SelectAll();

            }
        }
// 셀렉트된 부분을 체크하는 메소드
private void SelectedCheck(object sender, RoutedEventArgs e) 
        {
            foreach (Vehicle v in dataGrid.SelectedItems)
            {
                v.Checker = true;
            }
        }
```



> xaml 코드

```xaml
<DataGrid x:Name="dataGrid" Grid.Row="1" AutoGenerateColumns="False" 
          SelectionMode="Extended" CanUserAddRows="False" 
          CanUserDeleteRows="False" CanUserResizeRows="False"
          RowHeaderWidth="0" Selector.SelectionChanged="CheckandUncheck"
          Margin="10,5,10,5" FrozenColumnCount="2" >
    <DataGrid.Columns>
        <DataGridCheckBoxColumn CanUserSort="False" IsReadOnly="True"
                                Binding="{Binding Path=Checker}" >
            <DataGridCheckBoxColumn.Header>
                <CheckBox Height="15" Width="15" >
                </CheckBox >
            </DataGridCheckBoxColumn.Header>
        </DataGridCheckBoxColumn>
        <DataGridTextColumn Header="VCS ID" Binding="{Binding Path=Id}"
                            IsReadOnly="True"></DataGridTextColumn>
        <DataGridTextColumn Header="Connected" Binding="{Binding Path=Connected}" 									IsReadOnly="True">
            <DataGridTextColumn.ElementStyle>
                <Style TargetType="{x:Type TextBlock}">
                    <Style.Triggers>
                        <Trigger Property="Text" Value="False">
                            <Setter Property="Background" Value="Red"/>
                        </Trigger>
                    </Style.Triggers>
                </Style>
            </DataGridTextColumn.ElementStyle>
        </DataGridTextColumn>
        <DataGridTextColumn Header="VCS Run" Binding="{Binding Path=VCSRun}"
                            IsReadOnly="True"></DataGridTextColumn>
        <DataGridTextColumn Header="Update Ready" Binding="{Binding Path=ReadyToUpdate}" 
                            IsReadOnly="True"></DataGridTextColumn>
        <DataGridTextColumn Header="Version" Binding="{Binding Path=Version}" 
                            IsReadOnly="True"></DataGridTextColumn>
        <DataGridTextColumn Header="IP" Binding="{Binding Path=IP}" IsReadOnly="True">
        </DataGridTextColumn>
        <DataGridTextColumn Header="Last Cmd" Binding="{Binding Path=LastCmd}" 
                            IsReadOnly="True"></DataGridTextColumn>
        <DataGridTextColumn Header="Last Status" Binding="{Binding Path=LastStatus}" 
                            IsReadOnly="True"></DataGridTextColumn>
    </DataGrid.Columns>
</DataGrid>
```

