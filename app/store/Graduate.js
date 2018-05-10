Ext.define('XBSF.store.Graduate', {
    extend: 'Ext.data.Store',
    /*alias: "store.Graduate",*/
    model: 'XBSF.model.Graduate',
    autoLoad: {start: 0, limit: 5},
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method : "POST",
        url: 'bysxx/queryBysxx.do',
        reader: {
            type:'json',
            rootProperty: "root",
            totalProperty: 'total'
        }
    },
    listeners : {
        'load' : function(store, records, options) {
            //var st = Ext.getCmp('毕业生信息').getStore();
            //st.load({params: {start: page, limit: store.getCount()}, bysxx: new_params});
            //store.reload();
        }
    }
});