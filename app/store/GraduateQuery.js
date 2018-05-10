Ext.define('XBSF.store.GraduateQuery',{
    extend:'Ext.data.Store',
    alias: "store.GraduateQuery",
    model:'XBSF.model.GraduateQuery',
    autoLoad:true,
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method : "POST",
        url: 'bysxx/queryBysxx.do',
        reader: {
            type:'json',
            root: 'root',
            totalProperty: 'total'
        }
    }
});
