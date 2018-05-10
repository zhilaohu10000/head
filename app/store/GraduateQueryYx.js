Ext.define('XBSF.store.GraduateQueryYx',{
    extend:'Ext.data.Store',
    alias: "store.GraduateQueryYx",
    model:'XBSF.model.GraduateQueryYx',
    autoLoad:true,
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method : "POST",
        url: 'bysxx/queryBysxxByYx.do',
        reader: {
            type:'json',
            root: 'root',
            totalProperty: 'total'
        }
    }
});
