Ext.define('XBSF.store.SchoolAffairsQuery',{
    extend:'Ext.data.Store',
    alias: "store.SchoolAffairsQuery",
    model:'XBSF.model.SchoolAffairsQuery',
    autoLoad:true,
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
