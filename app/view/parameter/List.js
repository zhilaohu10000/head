Ext.define("XBSF.view.parameter.List", {
    //离校事务管理员的毕业生信息功能
    extend: "Ext.grid.Panel",
    //xtype: 'form-customfields',
    alias: 'widget.parameterlist',
    initComponent:function(){
        Ext.apply(this,{
            xtype: 'form-customfields',
            id:"参数设置",
            forceFit:true,
            store:'ParameterStore',
            selModel: Ext.create("Ext.selection.CheckboxModel", {
                injectCheckbox: 0,//checkbox位于哪一列，默认值为0
                mode: "single",//multi,simple,single；默认为多选multi
                //checkOnly: true,//如果值为true，则只用点击checkbox列才能选中此条记录
                allowDeselect: true,//如果值true，并且mode值为单选（single）时，可以通过点击checkbox取消对其的选择
                enableKeyNav: true
            }),
            layout:"fit",
            tbar : [  {xtype:'label',text:'毕业年度'},
                {xtype:'textfield',id:'year',name:'year',width:150,blankText: 'Year is required',allowBlank: false},
                {text:'设置',scale: 'large',handler:function(){
                    var cmp = Ext.getCmp('year').getValue();
                    if(cmp==null||cmp==''){
                        Ext.Msg.alert('警告','请输入年份');
                    }else{
                    Ext.Ajax.request({
                        url: 'lxnf/addNf.do',
                        method: 'POST',
                        params:{
                            year:cmp
                        },
                        success: function(response) {
                            var store = Ext.getStore('ParameterStore');
                            store.reload();
                        }
                    });}
                },width:150},"->", "->",
                {text:'开启',xtype:'button',width:150,scale: 'large',margin : '0 0 0 10',listeners : {
                    click : function() {
                        if (Ext.getCmp('参数设置').getSelection()[0]==null) {
                            Ext.Msg.alert('警告','请选择一条数据');
                        } else {
                            var store = Ext.getCmp('nfse').getSelection()[0];
                            Ext.Ajax.request({
                                url: 'lxnf/updateNf.do?',
                                method: 'POST',
                                params:{
                                    year:store.get('nf'),
                                    xtzt:'已开启'
                                },
                                success: function(response) {
                                    console.log(response);
                                    Ext.Msg.alert('提示', response.responseText);
                                    //重新加载store数据
                                    var store = Ext.getStore('ParameterStore');
                                    store.reload();
                                }
                            });
                        }

                    }
                }},"->", "->",
                {text:'中止',xtype:'button',width:150,scale: 'large',margin : '0 0 0 10',listeners : {
                    click : function() {
                        if (Ext.getCmp('参数设置').getSelection()[0]==null) {
                            Ext.Msg.alert('警告','请选择一条数据');
                        } else {
                            var store = Ext.getCmp('参数设置').getSelection()[0];
                            Ext.Ajax.request({
                                url: 'lxnf/updateNf.do?',
                                method: 'POST',
                                params:{
                                    year:store.get('nf'),
                                    xtzt:'已中止'
                                },
                                success: function(response) {
                                    console.log(response);
                                    Ext.Msg.alert('提示', response.responseText);
                                    //重新加载store数据
                                    var store = Ext.getStore('ParameterStore');
                                    store.reload();
                                }
                            });
                        }
                    }
                }},"->", "->",
                {text:'结束',xtype:'button',scale: 'large',width:150,margin : '0 0 0 10',listeners : {
                    click : function() {
                        if (Ext.getCmp('参数设置').getSelection()[0]==null) {
                            Ext.Msg.alert('警告','请选择一条数据');
                        } else {
                            var store = Ext.getCmp('参数设置').getSelection()[0];
                            Ext.Ajax.request({
                                url: 'lxnf/updateNf.do?',
                                method: 'POST',
                                params:{
                                    year:store.get('nf'),
                                    xtzt:'已结束'
                                },
                                success: function(response) {
                                    console.log(response);
                                    Ext.Msg.alert('提示', response.responseText);
                                    //重新加载store数据
                                    var store = Ext.getStore('ParameterStore');
                                    store.reload();
                                }
                            });
                        }
                    }
                }}],
            columns : [
                {text: '年度', dataIndex: 'nf', width: 210},
                {text: '开启时间', dataIndex: 'kqsj', width: 210},
                {text: '结束时间', dataIndex: 'jssj', width: 210},
                {text: '当前状态', dataIndex: 'xtzt', width: 210}
            ]
        });
        this.callParent(arguments);
    }
});

