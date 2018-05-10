Ext.define('XBSF.store.GraduateError', {
    extend: 'Ext.data.Store',
    model: 'XBSF.model.GraduateError',
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
