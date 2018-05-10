    //创建一个菜单组建
Ext.define('XBSF.view.menuTree',{
    extend:'Ext.tree.Panel',
    //定义别名
    alias:'widget.menutree',
    initComponent:function(){
        Ext.apply(this,{
            id:"menuStore",
            border:true,
            width:200,
            height:650,
            //layout:'fit',
            //规定链接地址的显示区域
            hrefTarget:'mainContent',
            //是否显示跟节点
            rootVisible:false,
            //是否显示竖线
            lines:true,
            //数据集，获取数据
            store:'menuStore',
            //菜单样式
            bodyStyle:{
                // background:'#ffc',
                // padding:'10px'
            }
        });
        this.callParent(arguments);
    }

});