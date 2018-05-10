Ext.define('XBSF.store.SchoolAffairsManagerQuery', {
    extend: 'Ext.data.Store',
    alias: "store.SchoolAffairsManagerQuery",
    model: 'XBSF.model.SchoolAffairsManagerQuery',
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
