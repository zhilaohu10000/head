Ext.define('XBSF.store.SchoolAffairsDepartment', {
    extend: 'Ext.data.Store',
    alias: "store.SchoolAffairsDepartment",
    model: 'XBSF.model.SchoolAffairsDepartment',
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
