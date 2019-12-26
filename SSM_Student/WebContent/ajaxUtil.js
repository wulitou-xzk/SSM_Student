//创建异步对象的函数
function createAjax(){
	var ajax = null;
	try {
		ajax = new ActiveXObject("microsoft.xmlhttp");
	} catch (e) {
		ajax = new XMLHttpRequest();
	}
	return ajax;
}