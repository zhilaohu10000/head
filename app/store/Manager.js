Ext.define("XBSF.store.Manager", {
    extend: "Ext.data.Store",
    model: "XBSF.model.Manager",
    autoLoad:true,
    proxy:{
        type:'ajax',
        url:'fzr/queryFzr.do?flbh=2',
        reader:'json'
    }
});