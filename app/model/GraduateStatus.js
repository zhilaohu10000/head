Ext.define('XBSF.model.GraduateStatus',{
    extend:'Ext.data.Model',
    fields:[
        {name:'yx',type:'string'},
        {name:'allNum',type:'int'},
        {name:'okNum',type:'int'},
        {name:'noNum',type:'int'},
        {name:'errorNum',type:'int'}
    ]
});