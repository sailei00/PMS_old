
/**
 * 
 */
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {};

// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
var zNodes = [ {
	name : "机械类",
	open : true,
	children : [ {
		name : "掘进机",
	} ]
}, {
	name : "运输类",
	open : true,
	children : [ {
		name : "钢丝绳头插接法"
	}, {
		name : "事故案例"
	} ]
}, {
	name : "电气类",
	open : true,
	children : [ {
		name : "基础知识"
	}, {
		name : "400馈电"
	}, {
		name : "综合保护器"
	}, {
		name : "移动变电站"
	}, {
		name : "接线工艺"
	}, {
		name : "事故案例"
	} ]
}, {
	name : "液压类",
	open : true,
	children : [ {
		name : "液压支架"
	}, {
		name : "事故案例"
	} ]
}, {
	name : "其他类"
} ];

