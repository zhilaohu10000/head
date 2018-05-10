Ext.define('XBSF.store.SwxxError', {
    extend: 'Ext.data.Store',
    model: 'XBSF.model.SwxxError',
    autoLoad: true,
   // defaultRoodId:'bysxx',
   /* root:'bysxx',
    fields:['xh','xm','yx','zy','bj'],*/
    proxy: {
        type: 'ajax',
        url: 'upload/bysxxError.do',
        reader: 'json'
    }
});
