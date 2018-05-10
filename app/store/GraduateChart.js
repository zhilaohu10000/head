Ext.define('XBSF.store.GraduateChart', {
    extend : 'Ext.data.JsonStore',
    model : 'XBSF.model.GraduateChart',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url:'bysxx/byzMtTj.do',
        data:'json'
    }
});