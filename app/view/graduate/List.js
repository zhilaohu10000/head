//毕业生信息
Ext.define('XBSF.view.graduate.List', {
    extend: 'Ext.grid.GridPanel',
    alias: 'widget.graduatelist',
    initComponent: function () {
        Ext.apply(this, {
            id: '毕业生信息',
            xtype: 'gridpanel',
            forceFit: true,
            store:'Graduate',
            layout: 'fit',
            selModel: Ext.create("Ext.selection.CheckboxModel", {//设置列属性，显示选择框
                injectCheckbox: 1,//checkbox位于哪一列，默认值为0
                mode: "single",//multi,simple,single；默认为多选multi
                //checkOnly: true,//如果值为true，则只用点击checkbox列才能选中此条记录
                allowDeselect: true,//如果值true，并且mode值为单选（single）时，可以通过点击checkbox取消对其的选择
                enableKeyNav: true
            }),
            align: "center",
            tbar: [
                {
                    xtype: "container",
                    id: 'serachFormStu',
                    border: false,
                    width: '100%',
                    items: [{
                        //tbar第一行工具栏
                        xtype: "toolbar",
                        id: 'StuToobarOne',
                        items: [
                            {xtype: 'label', text: '年度'},
                            {
                                xtype: 'combo', id: 'xn1', width: 150, defaultValue: null,
                                useNull: true,
                                labelWidth: 35,
                                value: "",
                                emptyText: '--请选择年份--',
                                maxWidth: 200,
                                minPickerHeight: 200,
                                store: new Ext.data.Store({
                                    fields: ['code', 'name'],
                                    autoLoad: true,
                                    proxy: {
                                        async: false,
                                        type: "ajax",
                                        actionMethods: {read: "POST"},
                                        url: 'lxnf/queryNd.do',
                                        reader: {
                                            type: "json",
                                            root: "data"
                                        },
                                        extraParams: {
                                            'checked': false
                                        }
                                    }
                                }),
                                displayField: 'name',
                                valueField: 'code',
                                editable: true
                            },
                            {xtype: 'label', text: '学号'},
                            {xtype: 'textfield', id: 'xh1', width: 150},
                            {xtype: 'label', text: '姓名'},
                            {xtype: 'textfield', id: 'xm1', width: 150},
                            {xtype: 'label', text: '院系'},
                            {
                                xtype: 'combo', id: 'yx1', width: 150,

                                emptyText: '--请选择院系--',
                                store: new Ext.data.Store({
                                    fields: ['code', 'name'],
                                    autoLoad: true,
                                    proxy: {
                                        type: "ajax",
                                        actionMethods: {read: "POST"},
                                        url: 'bysxx/queryAllYxMc.do',
                                        reader: {
                                            type: "json",
                                            root: "data"
                                        }
                                    }
                                }),
                                displayField: 'name',
                                valueField: 'code',
                                editable: false
                            },
                            {xtype: 'label', text: '专业'},
                            {xtype: 'textfield', id: 'zy1', width: 150},
                            {xtype: 'label', text: '班级'},
                            {xtype: 'textfield', id: 'bj1', width: 150}
                        ]
                    }, {
                        //tbar第二行工具栏
                        xtype: "toolbar",
                        id: 'StuToobarTwo',
                        items: [{
                            text: '查询',
                            xtype: 'button',
                            scale: 'large',
                            width: 150,
                            margin: '0 0 0 10',
                            listeners: {
                                click: function () {
                                    var xn = Ext.getCmp('xn1').getValue();
                                    var xh = Ext.getCmp('xh1').getValue();
                                    var yx = Ext.getCmp('yx1').getValue();
                                    var xm = Ext.getCmp('xm1').getValue();
                                    var zy = Ext.getCmp('zy1').getValue();
                                    var bj = Ext.getCmp('bj1').getValue();
                                    var new_params = {
                                        'xn': xn,
                                        'xm': xm,
                                        'xh': xh,
                                        'yx': yx,
                                        'zy': zy,
                                        'bj': bj
                                    };
                                    var st = Ext.getCmp('毕业生信息').getStore();
                                    st.on('beforeload', function (st, options) {
                                        //st.reload({params:{start:0,limit:5},bysxx:new_params});
                                        Ext.apply(st.getProxy().extraParams, new_params);
                                    });
                                    var pagingbar = Ext.getCmp('pagingToolbar');
                                    pagingbar.moveFirst();
                                    st.loadPage(1);
                                }
                            }
                        }, '->', '->', {
                            xtype: "button",
                            text: "重置",
                            width: 150,
                            scale: 'large',
                            cls: 'fa-arrows',
                            handler: function () {
                                var new_params = {},
                                    store = Ext.getCmp("毕业生信息").getStore();
                                Ext.getCmp("xn1").setValue('');
                                Ext.getCmp("xh1").setValue('');
                                Ext.getCmp("yx1").setValue('');
                                Ext.getCmp("xm1").setValue('');
                                Ext.getCmp("zy1").setValue('');
                                Ext.getCmp("bj1").setValue('');
                                //new_params['reserveNo'] = -1;
                                store.on('beforeload', function (store, options) {
                                    var proxy = store.getProxy();
                                    proxy.extraParams = {};
                                    Ext.apply(store.proxy.extraParams, new_params);
                                });
                                store.loadPage(1);
                            },
                            margin: '0 0 0 10'
                        }, "->", "->",
                            {
                                text: 'excel表格导入', xtype: 'button', scale: 'large', margin: '0 0 0 10',
                                width: 150, handler: upload
                            }, "->", "->",
                            {
                                text: '自动数据导入', xtype: 'button', scale: 'large', margin: '0 0 0 10',id:'zdsjdr',
                                width: 150, handler: autoUploadBysxx
                            }, "->", "->",
                            {
                                text: '增加毕业生信息', xtype: 'button', scale: 'large', margin: '0 0 0 10',
                                width: 150, handler: showAlert1
                            }, "->", "->",
                            {
                                text: '修改毕业生信息', xtype: 'button', scale: 'large', margin: '0 0 0 10',
                                width: 150,
                                handler: showAlert2
                            }]
                    }]
                }
            ],
            columns: [
                {xtype: "rownumberer", text: "标号", width: 100},
                {text: "年度", dataIndex: "xn", width: 150},
                {text: "学号", dataIndex: "xh", width: 150},
                {text: "姓名", dataIndex: "xm", width: 150},
                {text: "院系", dataIndex: "yx", width: 150},
                {text: "专业", dataIndex: "zy", width: 150},
                {text: "班级", dataIndex: "bj", width: 150}
            ],
            bbar: {
                xtype: 'pagingtoolbar',
                id: 'pagingToolbar',
                displayInfo: true,
                displayMsg: '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
                emptyMsg: "没有记录",
                store: 'Graduate'
            }
        });
        this.callParent(arguments);
    }
});

//修改毕业生信息
function showAlert2() {
    //修改毕业生信息
    if (Ext.getCmp('毕业生信息').getSelection()[0] == null) {
        Ext.Msg.alert('警告', '请选择一条数据');
    } else {
        var store = Ext.getCmp('毕业生信息').getSelection()[0];
        //console.log(store.get('xn'));
        //新建一个panel，修改毕业生信息
        var formStu = new Ext.FormPanel({
            //labelAlign: 'top',
            layout: 'form',
            items: [
                {
                    name: 'xn',
                    anchor: '100%',
                    value: store.get('xn'),
                    fieldLabel: "年度",
                    width: 100,
                    xtype: 'combo',
                    defaultValue: null,
                    useNull: true,
                    labelWidth: 35,
                    emptyText: '--请选择年份--',
                    maxWidth: 200,
                    minPickerHeight: 200,
                    store: new Ext.data.Store({
                        fields: ['code', 'name'],
                        autoLoad: true,
                        proxy: {
                            async: false,
                            type: "ajax",
                            actionMethods: {read: "POST"},
                            url: 'lxnf/queryNd.do',
                            reader: {
                                type: "json",
                                root: "data"
                            },
                            extraParams: {
                                'checked': false
                            }
                        }
                    }),
                    displayField: 'name',
                    valueField: 'code',
                    editable: true

                }, {
                    xtype: 'textfield',
                    name: 'xm',
                    value: store.get('xm'),
                    anchor: '100%',
                    fieldLabel: "姓名",
                    editable: true,
                    width: 100
                }, {
                    xtype: 'textfield',
                    name: 'xh',
                    value: store.get('xh'),
                    anchor: '100%',
                    fieldLabel: "学号",
                    editable: false,
                    width: 100
                }, {
                    name: 'yx',
                    anchor: '100%',
                    value: store.get('yx'),
                    fieldLabel: "院系",
                    width: 100,
                    xtype: 'combo',
                    emptyText: '--请选择院系--',
                    store: new Ext.data.Store({
                        fields: ['code', 'name'],
                        autoLoad: true,
                        proxy: {
                            type: "ajax",
                            actionMethods: {read: "POST"},
                            url: 'bysxx/queryAllYxMc.do',
                            reader: {
                                type: "json",
                                root: "data"
                            }
                        }
                    }),
                    displayField: 'name',
                    valueField: 'code',
                    editable: false

                }, {
                    xtype: 'textfield',
                    name: 'zy',
                    anchor: '100%',
                    value: store.get('zy'),
                    fieldLabel: "专业",
                    editable: true,
                    width: 100
                }, {
                    xtype: 'textfield',
                    name: 'bj',
                    anchor: '100%',
                    value: store.get('bj'),
                    fieldLabel: "班级",
                    editable: true,
                    width: 100
                }
            ],
            buttonAlign: 'center',
            buttons: [
                {
                    text: '保存',
                    handler: function () {
                        var text = formStu.getForm().getValues();
                        console.log(text);
                        Ext.Ajax.request({
                            url: 'bysxx/madfiyBysxx.do',
                            method: 'POST',
                            params: text,
                            success: function (response) {
                                console.log('chenggong+ 修改毕业生信息');
                                //重新加载grid表格
                                Ext.getCmp('毕业生信息').getStore().reload();

                            }

                        });
                        winStu.close(this);
                    }
                }, {
                    text: '关闭',
                    handler: function () {
                        winStu.close(this);
                    }
                }
            ]
        });
        var winStu = Ext.create("Ext.window.Window", {
            alias: 'widget.graduatefrom',
            title: "修改毕业生信息",       //标题
            // draggable: false,
            height: 400,                          //高度
            width: 400,                           //宽度
            layout: "fit",                        //窗口布局类型

            //modal: false, //是否模态窗口，默认为false
            //resizable: false,
            items: [formStu]
        });
        winStu.show();
    }
}

//新增毕业生信息
function showAlert1() {
    var cmp = Ext.getCmp('毕业生信息').getStore();
    //新建一个panel新增毕业生信息
    var formStu2 = new Ext.FormPanel({
        //labelAlign: 'top',
        layout: 'form',
        items: [
            {
                name: 'xn',
                anchor: '100%',
                allowBlank: false,
                fieldLabel: "年度",
                width: 100,
                xtype: 'combo',
                defaultValue: null,
                useNull: true,
                labelWidth: 35,
                value: "",
                emptyText: '--请选择年份--',
                maxWidth: 200,
                minPickerHeight: 200,
                store: new Ext.data.Store({
                    fields: ['code', 'name'],
                    autoLoad: true,
                    proxy: {
                        async: false,
                        type: "ajax",
                        actionMethods: {read: "POST"},
                        url: 'lxnf/queryNd.do',
                        reader: {
                            type: "json",
                            root: "data"
                        },
                        extraParams: {
                            'checked': false
                        }
                    }
                }),
                displayField: 'name',
                valueField: 'code',
                editable: true
            }, {
                xtype: 'textfield',
                name: 'xm',
                anchor: '100%',
                fieldLabel: "姓名",
                allowBlank: false,
                editable: true,
                width: 100
            }, {
                xtype: 'textfield',
                name: 'xh',
                anchor: '100%',
                allowBlank: false,
                fieldLabel: "学号",
                editable: true,
                width: 100
            }, {
                name: 'yx',
                anchor: '100%',
                fieldLabel: "院系",
                xtype: 'combo', width: 150,
                emptyText: '--请选择院系--',
                store: new Ext.data.Store({
                    fields: ['code', 'name'],
                    autoLoad: true,
                    proxy: {
                        type: "ajax",
                        actionMethods: {read: "POST"},
                        url: 'bysxx/queryAllYxMc.do',
                        reader: {
                            type: "json",
                            root: "data"
                        }
                    }
                }),
                displayField: 'name',
                valueField: 'code',
                editable: false
            }, {
                xtype: 'textfield',
                name: 'zy',
                anchor: '100%',
                fieldLabel: "专业",
                editable: true,
                width: 100
            }, {
                xtype: 'textfield',
                name: 'bj',
                anchor: '100%',
                fieldLabel: "班级",
                editable: true,
                width: 100
            }
        ],
        buttonAlign: 'center',
        buttons: [
            {
                text: '保存',
                handler: function () {
                    var text = formStu2.getForm().getValues();
                    Ext.Ajax.request({
                        url: 'bysxx/addBysxx.do',
                        method: 'POST',
                        params: text,
                        success: function (response) {
                            //重新加载grid表格
                            Ext.getCmp('毕业生信息').getStore().reload();
                        }
                    });
                    winStu2.close(this);
                }
            }, {
                text: '关闭',
                handler: function () {
                    winStu2.close(this);
                }
            }
        ]
    });
    var winStu2 = Ext.create("Ext.window.Window", {
        title: "增加毕业生信息",       //标题
        // draggable: false,
        height: 400,                          //高度
        width: 400,                           //宽度
        layout: "fit",                        //窗口布局类型
        //modal: false, //是否模态窗口，默认为false
        //resizable: false,
        items: [formStu2]
    });
    winStu2.show();
}

//excel表格数据导入
function upload() {
    var _this = this;
    var filePath = new Ext.form.FileUploadField({
        fieldLabel: "导入毕业生信息表<font color = 'red'><b>*</b></font>",
        buttonText: "选择...",
        name: "upFile",
        emptyText: "请选择导入文件...",
        labelStyle: "text-align : right"
    });
    var importExcel = new Ext.Button({
        text: "提交",
        handler: function () {
            var fileType = ".xlsx|.xls|"
            var filePathValue = filePath.getValue();
            if (filePathValue == null || filePathValue == "") {
                Ext.Msg.alert("提示", "请选择要导入的excel文件");
                return false;
            }
            var v = filePathValue.substring(filePathValue
                .lastIndexOf("."));
            if (fileType.indexOf(v + "|") == -1) {
                Ext.Msg.alert("提示", "您上传的文件格式不兼容，请选择excel格式！");
                return;
            }
            batch_panel.getForm().standardSubmit = false;
            batch_panel.getForm().submit({
                method: "POST",
                async: true,
                waitMsg: '正在上传文件,请稍候...',
                success: function (form, action) {
                    //Ext.MessageBox.confirm('Confirm', '确认导入吗？', this.showResult, this);
                    Ext.MessageBox.alert('成功', action.result.data);
                    //返回一个window，点击导入，开始导入，点击查看失败条数，展示失败条数，点击取消可以取消。
                    // batch_win.close(this);
                    /* var data = action.result.data;
                     var new_params = {},
                         store = Ext.getCmp("cityareaGrid").getStore();
                     if ('2' == data) {
                         new_params['value'] = 1;
                         Ext.getCmp("dataError").setHidden(false);
                     } else {
                         Ext.MessageBox.alert('成功', '所在城区信息全部导入');
                         batch_win.close();
                     }
                     store.on('beforeload', function (store, options) {
                         Ext.apply(store.proxy.extraParams, new_params);
                     });
                     store.reload();*/
                },
                failure: function (form, action) {
                    Ext.Msg.alert("提示2", action.result.data);
                    return;
                }
            });
        }
    });

    var closeBtn = new Ext.Button({
        text: "取消",
        handler: function () {
            batch_win.close();
        }
    });
    var errorLisBtn = new Ext.Button({
        text: "<font color = 'red'><b>查看错误信息</b></font>",
        handler: function () {
            var formStu12 = new Ext.grid.GridPanel({
                id: 'graduateId12',
                forceFit: true,
                layout: 'fit',
                align: "center",
                store: Ext.create('XBSF.store.GraduateError'),
                columns: [
                    {text: "年度", dataIndex: "xn", width: 150},
                    {text: "学号", dataIndex: "xh", width: 150},
                    {text: "姓名", dataIndex: "xm", width: 150},
                    {text: "院系", dataIndex: "yx", width: 150},
                    {text: "专业", dataIndex: "zy", width: 150},
                    {text: "班级", dataIndex: "bj", width: 150},
                    {text: "错误原因", dataIndex: "cw", width: 150}
                ]
            });
            var winStu12 = Ext.create("Ext.window.Window", {
                alias: 'widget.graduatefrom12',
                title: "错误毕业生信息",       //标题
                // draggable: false,
                height: 500,                          //高度
                width: 1200,                           //宽度
                layout: "fit",                        //窗口布局类型
                items: [formStu12]
            });
            winStu12.show();
        }
    });
    var batch_panel = Ext.create("Ext.form.Panel", {
        labelAlign: "right",
        border: false,
        autoHeight: true,
        frame: true,
        fileUpload: true,
        enctype: "multipart/form-data",
        url: 'upload/bysxxUpload.do',
        items: [{
            layout: "column",
            border: false,
            items: [{
                columnWidth: .80,
                layout: 'form',
                border: false,
                items: [filePath]
            }, {
                xtype: "container",
                columnWidth: .80,
                layout: {
                    type: "hbox",
                    align: "stretch"
                }
            }]
        }],
        buttons: [importExcel, closeBtn, errorLisBtn],
        buttonAlign: "center"
    });
    var batch_win = new Ext.Window({
        title: "毕业生信息导入",
        border: false,
        autoDestroy: false,
        closeAction: "close",
        hideMode: "destory",
        resizable: false,
        modal: true,
        layout: "fit",
        autoScroll: true,// 自动显示滚动条
        width: 550,
        height: 250,
        items: [batch_panel]
    });
    batch_win.show();
}

//自动毕业生数据导入
function autoUploadBysxx() {
    //请求自动数据导入的action，导入成功重新刷新store、禁止按钮

    Ext.Ajax.request({
        url: 'bysxx/autoUpload.do',
        method: 'POST',
        success: function (response) {
            Ext.Msg.alert("提示", response.responseText);
            //重新加载grid表格
            Ext.getCmp('毕业生信息').getStore().reload();
        }
    });
}

