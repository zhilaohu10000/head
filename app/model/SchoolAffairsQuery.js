Ext.define('XBSF.model.SchoolAffairsQuery',{
    extend:'Ext.data.Model',
    fields:[
        {name:'xn',type:'string'},
        {name:'xh',type:'string'},
        {name:'xm',type:'string'},
        {name:'yx',type:'string'},
        {name:'zy',type:'string'},
        {name:'swbm',type:'string'},
        {name:'sw',type:'string'},
        {name:'swblzt',type:'string'},
        {name:'swblsj',type: 'date', dateReadFormat: 'n/j'}
    ]
});