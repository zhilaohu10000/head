Ext.define('XBSF.view.graduate.GraduateChart', {
    //毕业证领取状态查询
    extend:'Ext.Panel',
    alias:'widget.graduatechart',
    xtype: 'basic-column',
    width : 500,
    height : 500,
    layout : 'fit',
    initComponent:function(){
        Ext.apply(this,{
            store:'GraduateChart',
            items :[{
                xtype: 'chart',
                width: '100%',
                height: 410,
                style: 'background: #fff',
                padding: '10 0 0 0',
                insetPadding: 40,
                animate: true,
                shadow: false,
                store: 'GraduateChart',
                items: [{
                    type  : 'text',
                    text  : '2018年度毕业证领取状况统计',
                    font  : '30px Helvetica',
                    width : 100,
                    height: 40,
                    x : 340, //the sprite x position
                    y : 28
                    //the sprite y position
                }, {
                    type: 'text',
                    text: '数量',
                    font: '10px Helvetica',
                    x: 12,
                    y: 12
                }, {
                    type: 'text',
                    text: '日期',
                    font: '10px Helvetica',
                    x: 1130,
                    y: 490
                }],
                axes: [{
                    type: 'Numeric',
                    position: 'left',
                    minimum: 0,
                    fields: ['total'],
                    label: {
                        values: [],
                        renderer: function(value) {
                            var temValue = Math.round(value);
                            if (temValue >= value) {
                                if ((temValue - value) > 0.00001) {
                                    return "";
                                } else {
                                    return temValue;
                                }
                            } else {
                                return "";
                            }
                        }
                    },
                    grid: true
                }, {
                    type: 'Category',
                    position: 'bottom',
                    fields: ['day'],
                    grid: true,
                    label: {
                        rotate: {
                            degrees: -45
                        }
                    }
                }],
                series: [{
                    type: 'column',
                    axis: 'left',
                    xField: 'day',
                    yField: 'total',
                    style: {
                        opacity: 0.80
                    },
                    highlight: {
                        fill: '#000',
                        'stroke-width': 20,
                        stroke: '#fff'
                    },
                    tips: {
                        trackMouse: true,
                        style: 'background: #FFF',
                        height: 20,
                        renderer: function(storeItem, item) {
                            this.setTitle(storeItem.get('day') + ': ' + storeItem.get('total'));
                        }
                    }
                }]
            }]
        });
        this.callParent(arguments);
    }

});