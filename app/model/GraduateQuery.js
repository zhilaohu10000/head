Ext.define('XBSF.model.GraduateQuery',{
    extend:'Ext.data.Model',
    fields:[
        {name:'xn',type:'string'},
        {name:'xh',type:'string'},
        {name:'xm',type:'string'},
        {name:'yx',type:'string'},
        {name:'zy',type:'string'},
        {name:'byzlqzt',type:'string'},
        {name:'byzlqsj',type:'date',dateReadFormat: 'n/j'}
    ]
});