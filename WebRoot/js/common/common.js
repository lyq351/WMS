

//获取定位
function getAbsPosition(o){

    o = $(o);
    if (o.length == 0) {
        return false;
    }
    
    o = o[0];
    
    var left, top;
    left = o.offsetLeft;
    top = o.offsetTop;
    
    while (o = o.offsetParent) {
        left += o.offsetLeft;
        top += o.offsetTop;
    }
    
    return {
        left: left,
        top: top
    };
}

//=========================================================================
//string操作工具
//=========================================================================
//去除前后空格
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
//
String.prototype.startWith = function(str){
    if (typeof(str) === "undefined") {
        return false;
    }
    str = str.toString();
    if (this.substr(0, str.length) == str) {
        return true;
    }
    else {
        return false;
    }
}

String.prototype.endWith = function(otherStr){
    if (typeof(otherStr) === "undefined") {
        return false;
    }
    otherStr = otherStr.toString();
    var startPos = this.length - otherStr.length;
    if (startPos >= 0) {
        var tmp = this.substr(startPos);
        if (tmp === otherStr) {
            return true;
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }
}

//包含
String.prototype.contains = function(otherStr){
    if (typeof(otherStr) === "undefined") {
        return false;
    }
    if (this.indexOf(otherStr.toString()) == -1) {
        return false;
    }
    else {
        return true;
    }
}


/**
 * 将表单中各域的值自动封装成参数对象
 * @param {Object} oFrm 表单对象(jQuery包装的或者原始DOM格式的)
 */
function getFormPara(oFrm){
	    oFrm = $(oFrm)[0];
	    var len = oFrm.elements.length;
	    var ret = {};
	    for (var i = 0; i < len; i++) {
	    
	        var oEle = oFrm.elements[i];
	        
	        if (oEle.type === "radio") {
	            if (oEle.checked) {
	                ret[oEle.name] = oEle.value.trim();
	            }
	        } else if (oEle.type === "checkbox") {
	            var curVal = ret[oEle.name];
	            if (curVal === undefined) {
	                ret[oEle.name] = [];
	                if (oEle.checked) {
	                    ret[oEle.name].push(oEle.value.trim());
	                }
	            } else {
	                if (oEle.checked) {
	                    ret[oEle.name].push(oEle.value.trim());
	                }
	            }
	        } else {
	            ret[oEle.name] = oEle.value.trim();
	        }
	    }
	    return ret;
	}
//=========================================================================
//Browser操作工具
//=========================================================================
var Browser = {
 isIE: function(version){
 
     if (navigator.userAgent.toLowerCase().indexOf("msie") == -1) {
         return false;
     }
     else {
         if (version) {
             var regexpr = new RegExp("msie\\s*" + version, "g");
             if (navigator.userAgent.toLowerCase().match(regexpr)) {
                 return true;
             }
             else {
                 return false;
             }
         }
         else {
             return true;
         }
     }
 }
 
};