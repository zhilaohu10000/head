Ext.define('XBSF.store.SchoolAffairsChart', {
    extend : 'Ext.data.JsonStore',
    model : 'XBSF.model.SchoolAffairsChart',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url:'swxx/swxxMtTj.do',
        data:'json'
    }
});