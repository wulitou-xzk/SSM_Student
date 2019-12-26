/*
* @Author: Administrator
* @Date:   2019-06-01 10:32:23
* @Last Modified by:   Administrator
* @Last Modified time: 2019-06-29 11:28:09
*/

'use strict';
$(function(){
	var Tab=$("table");
	var Tr=Tab.find('tr')
	Tr.click(function(){
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
	})
})

