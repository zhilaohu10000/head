Ext.define("XBSF.store.User", {
    extend: "Ext.data.Store",
    model: "XBSF.model.User",
    autoLoad:true,
    proxy:{
        type:'ajax',
        url:'fzr/queryFzr.do?flbh=1',
        reader:'json'
    }
});