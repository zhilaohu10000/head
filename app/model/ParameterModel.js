Ext.define('XBSF.model.ParameterModel',{
    extend:'Ext.data.Model',
    fields:[
        {
            selection: "rowmodel",
            mode: "MULTI"
        },
        {name:'nf',type:'string'},
        {name:'kqsj',type:'string'},
        {name:'jssj',type:'string'},
        {name:'xtzt',type:'string'}
    ]
});