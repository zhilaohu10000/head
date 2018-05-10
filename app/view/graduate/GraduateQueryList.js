Ext.define('XBSF.view.graduate.GraduateQueryList',{
    //离校事务管理员毕业生领取查询
    extend:'Ext.grid.Panel',
    alias:'widget.graduateQuerylist',
    initComponent: function(){
        Ext.apply(this,{
            id: '毕业证领取查询',
            store:'GraduateQuery',
            layout: 'fit',
            forceFit: true,
            align: "center",
            tbar:[{
                xtype:"container",
                id:'serachFormGraduate',
                width:'100%',
                border:false,
                items:[{
                    //tbar第一行工具栏
                    xtype:"toolbar",
                    items : [
                        {xtype:'label',text:'年度',width:40},
                        {xtype:'combo',id:'xn2',width:170,
                            emptyText: '--请选择年份--',
                            store : new Ext.data.Store({
                                fields: ['code', 'name'],
                                autoLoad: true,
                                proxy: {
                                    type: "ajax",
                                    actionMethods: { read: "POST" },
                                    url: 'lxnf/queryNd.do',
                                    reader: {
                                        type: "json",
                                        root: "data"
                                    }
                                }
                            }),
                            displayField : 'name',
                            valueField : 'code',
                            editable : true
                        },
                        {xtype:'label',text:'学号',width:40},
                        {xtype:'textfield',id:'xh2',width:170},
                        {xtype:'label',text:'状态',width:40},
                        {xtype:'combo',id:'byzlqzt2',width:170,
                            emptyText: '--请选择状态--',
                            store : new Ext.data.Store({
                                fields : ['code', 'name'],
                                data : [{
                                    name : "已办理",
                                    code : "已办理"
                                }, {
                                    name : "未办理",
                                    code : "未办理"
                                }]
                            }),
                            displayField : 'name',
                            valueField : 'code',
                            editable : false
                        },
                        {xtype:'label',text:'院系',width:40},
                        {xtype:'combo',id:'yx2',width:170,
                            emptyText: '--请选择院系--',
                            store : new Ext.data.Store({
                                fields: ['code', 'name'],
                                autoLoad: true,
                                proxy: {
                                    type: "ajax",
                                    actionMethods: { read: "POST" },
                                    url: 'bysxx/queryAllYxMc.do',
                                    reader: {
                                        type: "json",
                                        root: "data"
                                    }
                                }
                            }),
                            displayField : 'name',
                            valueField : 'code',
                            editable : false
                        },
                        {xtype:'label',text:'专业',width:40},
                        {xtype:'textfield',id:'zy2',width:170}
                    ]
                },{
                    //tbar第二行工具栏
                    xtype:"toolbar",
                    items : [
                        {xtype:'label',text:'班级',width:40},
                        {xtype:'textfield',id:'bj2',width:170},
                        {xtype:'label',text:'办理日',width:40},
                        {xtype:'datefield',id:'byzblsj2',width:170,
                            emptyText: '--请选择办理日期--',
                            name: "disableTimes",
                            format: 'Y/m/d',
                            editable : false,
                            maxValue: new Date(new Date().getTime()+24*60*60*1000),
                            labelWidth: 65
                        },
                        {text:'查询', xtype : 'button',scale: 'large',
                            margin : '0 0 0 10',
                            listeners: {
                                click: function () {
                                    var xn = Ext.getCmp('xn2').getValue();
                                    var xh = Ext.getCmp('xh2').getValue();
                                    var yx = Ext.getCmp('yx2').getValue();
                                    var zy = Ext.getCmp('zy2').getValue();
                                    var byzlqzt = Ext.getCmp('byzlqzt2').getValue();
                                    var bj = Ext.getCmp('bj2').getValue();
                                    var date = Ext.getCmp('byzblsj2');
                                    var newdate = date.formatDate(date.getValue());
                                    //重新加载grid表格
                                    if (null == newdate) {
                                        var new_params={
                                            'xn':xn,
                                            'xh':xh,
                                            'yx':yx,
                                            'zy':zy,
                                            'bj':bj,
                                            'byzlqzt':byzlqzt,
                                            //'byzlqsj': newdate
                                        };
                                    } else {
                                        var new_params={
                                            'xn':xn,
                                            'xh':xh,
                                            'yx':yx,
                                            'zy':zy,
                                            'bj':bj,
                                            'byzlqzt':byzlqzt,
                                            'byzlqsj': newdate
                                        };
                                    }

                                    //重新加载grid表格
                                    var st=Ext.getCmp('毕业证领取查询').getStore();
                                    st.on('beforeload',function(st,options){
                                        Ext.apply(st.getProxy().extraParams,new_params);
                                    });
                                    var toobar = Ext.getCmp("gridpagingtoobar");
                                    toobar.moveFirst();
                                    st.loadPage(1);
                                }
                            },width:150},
                        {
                            xtype: "button",
                            text: "重置",
                            width: 150,
                            scale: 'large',
                            cls:'fa-arrows',
                            handler: function(){
                                var new_params = {} ,
                                    store = Ext.getCmp("毕业证领取查询").getStore();
                                Ext.getCmp("xn2").setValue('');
                                Ext.getCmp("xh2").setValue('');
                                Ext.getCmp("yx2").setValue('');
                                Ext.getCmp("byzlqzt2").setValue('');
                                Ext.getCmp("byzblsj2").setValue('');
                                Ext.getCmp("zy2").setValue('');
                                Ext.getCmp("bj2").setValue('');
                                store.on('beforeload', function (store, options) {
                                    var proxy = store.getProxy();
                                    proxy.extraParams = {};
                                    Ext.apply(store.proxy.extraParams, new_params);
                                });
                                store.loadPage(1);
                            },
                            margin:'0 0 0 10'
                        }]
                }]
            }
            ],
            columns:[
                { xtype: "rownumberer" },
                {text:"年度",dataIndex:"xn",width:100},
                {text:"学号",dataIndex:"xh",width:100},
                {text:"姓名",dataIndex:"xm",width:100},
                {text:"院系",dataIndex:"yx",width:100},
                {text:"专业",dataIndex:"zy",width:100},
                {text:"状况",dataIndex:"byzlqzt",width:100},
                {text:"办理日",dataIndex:"byzlqsj",width:100}
            ],
            bbar: Ext.create('Ext.PagingToolbar', {
                xtype: 'pagingtoolbar',
                id:'gridpagingtoobar',
                displayInfo: true,
                displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
                emptyMsg: "没有记录",
                store: 'GraduateQuery'
            })
        });
        this.callParent(arguments);
    }
});

