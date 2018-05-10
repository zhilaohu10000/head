Ext.define('XBSF.store.menuStore', {
    //功能树远程加载数据
    extend: 'Ext.data.TreeStore',
    defaultRoodId: 'root',
    //requires: 'XBSF.model.menuModel',
    //model: 'XBSF.model.menuModel',
    proxy: {
        type: 'ajax',
        url:'login/change.do',
        reader: 'json',
        autoLoad: true
    }
});