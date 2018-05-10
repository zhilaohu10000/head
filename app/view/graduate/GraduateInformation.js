Ext.define("XBSF.view.graduate.GraduateInformation", {
    extend: "Ext.panel.Panel",
    alias: 'widget.GraduateInformation',
    //store: "User",
    layout: 'form',
    bodyPadding: 5,
    items: [
        {
            xtype: 'textfield',
            name: 'xn',
            height:20,
            //anchor: '100%',
            fieldLabel: "年度",
            value:'2018',
            editable: false
            //width:50
        },{
            xtype: 'textfield',
            name: 'xm',
            height:20,
            value:'张思',
            //anchor: '20%',
            fieldLabel: "姓名",
            editable: false
           // width: 50
        },{
            xtype: 'textfield',
            name: 'xh',
            id:'bysid',
           // anchor: '20%',
            value:'0101101',
            fieldLabel: "学号",
            editable: false
           // width: 50
        },{
            xtype: 'textfield',
            name: 'yx',
            value:'计算机系',
           // anchor: '100%',
            fieldLabel: "院系",
            editable: false
           // width: 100
        },{
            xtype: 'textfield',
            name: 'zy',
            anchor: '100%',
            value:'电子商务',
            fieldLabel: "专业",
            editable: false
            //width: 100
        },{
            xtype: 'textfield',
            name: 'bj',
            //anchor: '100%',
            value:'电子181',
            fieldLabel: "班级",
            editable: false
           // width: 100
        }
    ],
    listeners: {
        render: function() {
            //Ext.MessageBox.alert('查询', '正在检索中。。。');
          /*  Ext.Msg.show({
                title: '正在查询 ',
                msg: '成功！ ',
                buttons: Ext.MessageBox.OK,
                icon: Ext.MessageBox.WARNING,
                listeners: {
                    'render': function(cmp, opts) {
                        Ext.TaskManager.start({
                            run: function(){
                                cmp.hide();
                            },
                            duration: 3000 //单位毫秒
                        });
                    }
                }
            });*/
            //开始加载右侧

         /*   var schoolAffairsManager = new Ext.data.Store({
                /!* extend:'Ext.data.Store',
                 model:'XBSF.model.SchoolAffairsManager',
                 autoLoad:true,
                 proxy:{
                     type:'ajax',
                     url:'swxx/querySwxx.do?xh=0123012',
                     reader:'json'
                 }*!/
                proxy: new Ext.data.HttpProxy({
                    //改成自己的action，_前缀是struts配置名，后缀是action层对应方法名
                    url: 'swxx/querySwxx.do?xh=0123012'
                }),
                model:'XBSF.model.SchoolAffairsManager',
                //Grid列表返回对象集合名，set、get方法对应集合名
              /!*  root: 'items_StockIn',
                //Grid列表返回集合条数，set、get方法对应其名
                totalProperty: 'totalProperty',*!/
                //Grid列表对应对象的属性，注意是Grid对象属性值，参考对象实体表
               /!* fields: ['pkInId', 'fkGoodId', 'inNum',
                    'inDate', 'fkInOperatorId', 'inDesc', 'state']*!/
                 autoLoad : true
            });*/

        }
    }
});
/*initComponent: function () {
    Ext.apply(this, {
        layout: 'fit',
        id: 'SchoolAffairsDepartmentId',
        forceFit: true,
        store: Ext.create('XBSF.store.SchoolAffairsDepartment'),
        tbar: [{
            xtype: "container",
            id: 'serachFormDepartment',
            border: true,
            items: [{
                //tbar第一行工具栏
                xtype: "toolbar",
                items: [
                    /!*{xtype:'label',text:'年度'},
                    {xtype:'combo',id:'xn',width:150, defaultValue : null,
                        useNull : true,store : new Ext.data.Store({
                        fields : ['code', 'name'],
                        autoLoad: true,
                        proxy: {
                            type: "ajax",
                            actionMethods: { read: "POST" },
                            url: 'data/xn.json',
                            reader: {
                                type: "json",
                                root: "data"
                            }
                        }
                    }),
                        displayField : 'name',
                        valueField : 'code',
                        editable : false
                    },*!/
                    {xtype: 'label', text: '学号'},
                    {xtype: 'textfield', id: 'xh', width: 150}
                    /!* {xtype:'label',text:'姓名'},
                     {xtype:'textfield',id:'xm',width:150},
                     {xtype:'label',text:'院系'},
                     {xtype:'combo',id:'yx',width:150,
                         store : new Ext.data.Store({
                             fields: ['code', 'name'],
                             autoLoad: true,
                             proxy: {
                                 type: "ajax",
                                 actionMethods: { read: "POST" },
                                 url: 'data/yx.json',
                                 reader: {
                                     type: "json",
                                     root: "data"
                                 }
                             }
                         }),
                         displayField: 'name',
                         valueField: 'code',
                         editable: false},
                     {xtype:'label',text:'专业'},
                     {xtype:'textfield',id:'zy',width:150},
                     {xtype:'label',text:'班级'},
                     {xtype:'textfield',id:'bj',width:150}*!/
                ]
            }, {
                //tbar第二行工具栏
                xtype: "toolbar",
                items: [{
                    text: '查询',
                    xtype: 'button',
                    width: 150,
                    margin: '0 0 0 10',
                    listeners: {
                        click: function () {
                            var xh = Ext.getCmp('xh').getValue();
                            /!* var xh = Ext.getCmp('xh').getValue();
                             var yx = Ext.getCmp('yx').getValue();
                             var xm = Ext.getCmp('xm').getValue();
                             var zy = Ext.getCmp('zy').getValue();
                             var bj = Ext.getCmp('bj').getValue();*!/
                            var new_params = {
                                'xh': xh
                                /!* 'xm':xm,
                                 'xh':xh,
                                 'yx':yx,
                                 'zy':zy,
                                 'bj':bj*!/
                            };
                            var st = Ext.getCmp('SchoolAffairsDepartmentId').getStore();
                            st.on('beforeload', function (st, options) {
                                Ext.apply(st.getProxy().extraParams, new_params);
                            });
                            st.reload();
                        }
                    }
                }, {
                    text: '办理', xtype: 'button', width: 150, margin: '0 0 0 10', handler: function () {
                        /!* var xn = Ext.getCmp('xn').getValue();*!/
                        var xh = Ext.getCmp('xh').getValue();
                        /!*var yx = Ext.getCmp('yx').getValue();
                        var xm = Ext.getCmp('xm').getValue();
                        var zy = Ext.getCmp('zy').getValue();
                        var bj = Ext.getCmp('bj').getValue();*!/
                        //ajax请求修改事务信息，根据学号，事务部门名称修改
                        Ext.Ajax.request({
                            url: 'swxx/modifySwxxCX.do',
                            method: 'POST',
                            params: {
                                'bysbh': xh
                                /!*'swbm': rec.get('swbm'),
                                'sw': rec.get('sw'),
                                'swblzt': rec.get('swblzt')*!/
                            },
                            success: function (response) {
                                console.log('chenggong+ 办理离校事项信息');
                                //重新加载grid表格
                                Ext.getCmp('SchoolAffairsDepartmentId').getStore().reload();
                                //新建一个panel
                                Ext.MessageBox.show({
                                    title: '提示框',
                                    msg: '办理成功',
                                    buttons: Ext.MessageBox.OK,
                                    icon: Ext.MessageBox.Ok
                                });
                            }
                        });
                    }
                }, {
                    text: '撤销', xtype: 'button', width: 150, margin: '0 0 0 10', handler: function () {
                        var xh = Ext.getCmp('xh').getValue();
                        /!*var yx = Ext.getCmp('yx').getValue();
                        var xm = Ext.getCmp('xm').getValue();
                        var zy = Ext.getCmp('zy').getValue();
                        var bj = Ext.getCmp('bj').getValue();*!/
                        //ajax请求修改事务信息，根据学号，事务部门名称修改
                        Ext.Ajax.request({
                            url: 'swxx/modifySwxxCX.do',
                            method: 'POST',
                            params: {
                                'bysbh': xh
                                /!*'swbm': rec.get('swbm'),
                                'sw': rec.get('sw'),
                                'swblzt': rec.get('swblzt')*!/
                            },
                            success: function (response) {
                                console.log('chenggong+ 办理离校事项信息');
                                //重新加载grid表格
                                Ext.getCmp('SchoolAffairsDepartmentId').getStore().reload();
                                //新建一个panel
                                Ext.MessageBox.show({
                                    title: '提示框',
                                    msg: '撤销成功',
                                    buttons: Ext.MessageBox.OK,
                                    icon: Ext.MessageBox.Ok
                                });
                            }
                        });
                    }
                }]
            }]
        }],
        columns: [{xtype: "rownumberer"},
            {text: "年度", dataIndex: "xn", width: 100},
            {text: "学号", dataIndex: "xh", width: 100},
            {text: "姓名", dataIndex: "xm", width: 100},
            {text: "专业", dataIndex: "zy", width: 100},
            {text: "毕业证领取状态", dataIndex: "byzlqzt", width: 100},
            {text: "职能部门", dataIndex: "swbm", width: 100},
            {text: "事项", dataIndex: "sw", width: 100},
            {text: "状态", dataIndex: "swblzt", width: 100}]
    });
    this.callParent(arguments);
}*/
