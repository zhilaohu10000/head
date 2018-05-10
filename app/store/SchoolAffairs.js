Ext.define('XBSF.store.SchoolAffairs',{
    extend:'Ext.data.Store',
    alias: "store.SchoolAffairs",
    model:'XBSF.model.SchoolAffairs',
    autoLoad: true,
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method : "POST",
        url: 'swxx/querySwxx.do',
        reader: {
            type:'json',
            root: 'root',
            totalProperty: 'total'
        }
    }
});
