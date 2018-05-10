Ext.define('XBSF.controller.DepartmentController', {
    extend: 'Ext.app.Controller',
    views: ['Viewport','Top', 'menuTree', 'parameter.List', 'user.List', 'user.Edit', 'user.ManagerList', 'user.ManagerEdit', 'user.ManagerView', 'graduate.List', 'graduate.GraduateQueryList', 'graduate.GraduateQueryListYx', 'graduate.GraduateInformation', 'graduate.GraduateChart', 'graduate.GraduateStatusList', 'schoolAffairs.SchoolAffairsList', 'schoolAffairs.SchoolAffairsQueryList', 'schoolAffairs.SchoolAffairsManagerQuery', 'schoolAffairs.SchoolAffairsManager', 'schoolAffairs.SchoolAffairsNoManager', 'schoolAffairs.SchoolAffairsStatusList', 'schoolAffairs.SchoolAffairsChart', 'schoolAffairs.SchoolAffairsManagerPhone', 'schoolAffairs.SchoolAffairsDepartment'],
    stores: ['menuStore', 'ParameterStore', 'User', 'Manager', 'Graduate', 'GraduateError', 'SwxxError', 'SchoolAffairs', 'SchoolAffairsQuery', 'SchoolAffairsStatus', 'GraduateQuery', 'GraduateQueryYx', 'GraduateStatus', 'GraduateChart', 'SchoolAffairsManagerQuery', 'SchoolAffairsManager', 'SchoolAffairsChart', 'SchoolAffairsDepartment'],
    model: ['menuModel', 'ParameterModel', 'User', 'Manager', 'Graduate', 'GraduateError', 'SwxxError', 'SchoolAffairs', 'SchoolAffairsQuery', 'SchoolAffairsStatus', 'GraduateQuery', 'GraduateQueryYx', 'GraduateStatus', 'GraduateChart', 'SchoolAffairsManagerQuery', 'SchoolAffairsManager', 'SchoolAffairsChart', 'SchoolAffairsDepartment'],
    //通过init函数来监听视图事件，控制视图与控制器的交互
    init: function () {
        //init函数通过this.control来负责监听
        this.control({
            //被监听的组件别名
            'menutree': {
                //监听鼠标事件，点击后调用changPage方法
                itemclick: this.changPage
            },
            //设置权限赋予
            'userlist': {
                itemdblclick: this.editUser
            },
            'useredit button[action=save]': {
                click: this.usersaveUser
            },
            'managerlist': {
                itemdblclick: this.editManager
            },
            'manageredit button[action=save]': {
                click: this.managersaveUser
            }
        });
    },
    //编辑院系
    editUser: function (grid, record) {
        var win = Ext.widget("useredit");
        win.down("form").loadRecord(record);
        win.show();
    },
    //编辑职能部门
    editManager: function (grid, record) {
        var win = Ext.widget("manageredit");
        win.down("form").loadRecord(record);
        win.show();
    },
    //保存新增院系
    usersaveUser: function (btn) {
        var win = btn.up("window"),
            form = win.down("form"),
            value = form.getForm().getFieldValues();
        Ext.Ajax.request({
            url: 'fzr/addYxFzr.do',
            method: 'POST',
            params: value,
            success: function (response) {
                /* form.updateRecord();
                 record.commit();
                 win.close();*/
                Ext.getCmp('userlist').getStore().reload();
                win.close();
            }
        });
    },
    //保存新增职能部门
    managersaveUser: function (btn) {
        var win = btn.up("window"),
            form = win.down("form"),
            value = form.getForm().getFieldValues();
        Ext.Ajax.request({
            url: 'fzr/addZnbmFzr.do',
            method: 'POST',
            params: value,
            success: function (response) {
                Ext.getCmp('managerlist').getStore().reload();
                win.close();
            }
        });
    },
    //触发执行的函数
    changPage: function (view, rec, item, index, e) {
        //获取url
        var url = rec.get('url');
        //  var id = rec.get('id');
        //获取当前节点的信息
        console.log(url);
        //console.log(id);
        var title = rec.get('text');
        //console.log(title);
        var leaf = rec.get('leaf');
        //console.log(leaf + " leaf");
        var tabPanel = Ext.getCmp('mainContent');
        var title2 = tabPanel.title;
        //子节点才能打开，父节点不设置响应
        if (leaf == false) {
            return;
        }
        //将主体的内容部门的url指向为获取的url
        //Ext.getDom('contentIframe').src=url;
        //将主体内容框的标题设置为我们获取的信息
        // Ext.getCmp('mainContent').setTitle(title);
        var activeTab = tabPanel.getActiveTab();
        var newTab = tabPanel.getChildByElement(title);
        if (newTab == null) {
            newTab = tabPanel.add({
                id: title,
                title: title,
                //html:'<iframe  frameborder="no" style=\'height: 100%; width: 100%\' src="'+'http://localhost:8888/xbsf_lxxt1/parameter.html'+'"></iframe>',
                //html:'<iframe  frameborder="no" style=\'height: 100%; width: 100%\' src="'+'http://localhost:8888/xbsf_lxxt1/parameter.html'+'"></iframe>',
                xtype: url,
                closable: true
            }).show();
        }
        tabPanel.setActiveTab(newTab);
        tabPanel.doLayout();
    }
});