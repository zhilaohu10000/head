Ext.define('XBSF.model.Graduate',{
    extend:'Ext.data.Model',
    fields:[
        {
            selection: "rowmodel",
            mode: "MULTI"
        },
        {name:'xn',type:'string'},
        {name:'xh',type:'string'},
        {name:'xm',type:'string'},
        {name:'yx',type:'string'},
        {name:'zy',type:'string'},
        {name:'bj',type:'string'}
    ]
});