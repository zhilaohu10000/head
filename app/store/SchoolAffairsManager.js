Ext.define('XBSF.store.SchoolAffairsManager',{
    extend:'Ext.data.Store',
    model:'XBSF.model.SchoolAffairsManager',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url:'swxx/querySwxx.do?xh=0101101',
        reader:'json'
    }
});
