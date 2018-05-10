Ext.define('XBSF.view.graduate.GraduateStatusList',{
    //毕业证领取状况
    extend:'Ext.grid.Panel',
    alias:'widget.graduatestatuslist',
    //store:'GraduateStatus',
    //layout:'fit',
    initComponent:function(){
        Ext.apply(this,{
            id:'毕业证领取状况',
            layout:'fit',
            store:Ext.create('XBSF.store.GraduateStatus'),
            forceFit: true,
            align: "center",
            tbar:[{xtype:'label',text:'年度'},
                {xtype:'combo',id:'xn3',width:170,
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
                    editable : false},
                {xtype:'label',text:'院系'},
                {xtype:'combo',id:'yx3',width:170,
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
                    editable : false},
                {   text:'查询',
                    xtype : 'button',
                    scale: 'large',
                    margin : '0 0 0 10',handler:function(){
                    var xn = Ext.getCmp('xn3').getValue();
                    var yx = Ext.getCmp('yx3').getValue();
                    Ext.Ajax.request({
                        url: 'bysxx/byzTj.do',
                        method: 'POST',
                        params:{
                            //发送bysSwxx毕业生事务信息的json对象
                            'xn':xn,
                            'yx':yx
                        },
                        success: function(response) {
                            //重新加载grid表格
                            var new_params={
                                'xn': xn,
                                'yx': yx
                            };
                            //重新加载grid表格
                            //Ext.getCmp('SchoolAffairs').getStore().reload();
                            var st=Ext.getCmp('毕业证领取状况').getStore();
                            st.on('beforeload',function(st,options){
                                Ext.apply(st.getProxy().extraParams,new_params);
                            });
                            st.reload();
                        }

                    });
                },width:150},{
                    xtype: "button",
                    text: "重置",
                    width: 150,
                    scale: 'large',
                    cls:'fa-arrows',
                    handler: function(){
                        var new_params = {} ,
                            store = Ext.getCmp("毕业证领取状况").getStore();
                        Ext.getCmp("xn3").setValue('');
                        Ext.getCmp("yx3").setValue('');
                      /*  Ext.getCmp("xh1").setValue('');
                        Ext.getCmp("xm1").setValue('');
                        Ext.getCmp("zy1").setValue('');
                        Ext.getCmp("bj1").setValue('');*/
                        //new_params['reserveNo'] = -1;
                        store.on('beforeload', function (store, options) {
                            var proxy = store.getProxy();
                            proxy.extraParams = {};
                            Ext.apply(store.proxy.extraParams, new_params);
                        });
                        store.loadPage(1);
                    },
                    margin:'0 0 0 10'
                }],
            columns:[
                { xtype: "rownumberer" },
                {text:"院系",dataIndex:"yx",width:180},
                {text:"年度毕业人数",dataIndex:"allNum",width:180},
                {text:"已领取毕业证人数",dataIndex:"okNum",width:180},
                {text:"处理完成未领证人数",dataIndex:"noNum",width:180},
                {text:"有未处理事项人数",dataIndex:"errorNum",width:180}
            ]
        });
        this.callParent(arguments);
    }
    /*initComponent: function(){
        this.id='GraduateStatus';
        this.forceFit=true;
        this.align= "center";
        this.store=Ext.create('XBSF.store.GraduateStatus');
        this.tbar=[
            {xtype:'label',text:'年度'},
            {xtype:'combo',id:'xn',width:130,
                store : new Ext.data.Store({
                    fields: ['code', 'name'],
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
                editable : false},
            {xtype:'label',text:'院系'},
            {xtype:'combo',id:'yx',width:130,
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
                displayField : 'name',
                valueField : 'code',
                editable : false},
            {   text:'查询',
                xtype : 'button',
                margin : '0 0 0 10',handler:function(){
                    var xn = Ext.getCmp('xn').getValue();
                    var yx = Ext.getCmp('yx').getValue();
                Ext.Ajax.request({
                    url: 'bysxx/byzTj.do',
                    method: 'POST',
                    params:{
                        //发送bysSwxx毕业生事务信息的json对象
                        'xn':xn,
                        'yx':yx
                    },
                    success: function(response) {
                        //重新加载grid表格
                        var new_params={
                            'xn': xn,
                            'yx': yx
                        };
                        //重新加载grid表格
                        //Ext.getCmp('SchoolAffairs').getStore().reload();
                        var st=Ext.getCmp('GraduateStatus').getStore();
                        st.on('beforeload',function(st,options){
                            Ext.apply(st.getProxy().extraParams,new_params);
                        });
                        st.reload();
                    }

                });
            },width:100}
        ];
        this.columns=[
            { xtype: "rownumberer" },
            {text:"院系",dataIndex:"yx",width:180},
            {text:"年度毕业人数",dataIndex:"allNum",width:180},
            {text:"已领取毕业证人数",dataIndex:"okNum",width:180},
            {text:"处理完成未领证人数",dataIndex:"noNum",width:180},
            {text:"有未处理事项人数",dataIndex:"errorNum",width:180}
        ];
        /!*this.bbar=[
            { xtype: "pagingtoolbar", store: 'GraduateStatus',width:1000}
        ];*!/
        this.callParent(arguments);
    }*/
});

