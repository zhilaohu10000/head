Ext.define('XBSF.store.GraduateStatus',{
    extend:'Ext.data.Store',
    model:'XBSF.model.GraduateStatus',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url:'bysxx/byzTj.do',
        reader:'json'
    }
});