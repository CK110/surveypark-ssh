function changeValidateCode(obj) {   
	//获取当前的时间作为参数，无具体意义   
	var timenow = new Date().getTime();   
	//每次请求需要一个不同的参数，否则可能会返回同样的验证码   
	//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
	obj.src="rand.action?d="+timenow;   
} 

function checkForm() {
	document.getElementById("errEmail").innerHTML = "&nbsp;";
	document.getElementById("errPassword").innerHTML = "&nbsp;";
	document.getElementById("errConfirmPassword").innerHTML = "&nbsp;";
	document.getElementById("errCaptcha").innerHTML = "&nbsp;";

	if((document.getElementById("email").value)=="") {
		document.getElementById("errEmail").innerHTML = "&nbsp;请输入邮箱";
		//document.getElementById("email").onfocus();
		return false
	}
	
	if(checkEmail(document.getElementById("email").value)==0) {
		document.getElementById("errEmail").innerHTML = "&nbsp;邮箱格式不正确";
		//document.getElementById("email").onfocus();
		return false
	}	
	if((document.getElementById("password").value)=="") {
		document.getElementById("errPassword").innerHTML = "&nbsp;请输入密码";
		//document.getElementById("password").onfocus();
		return false
	}	
	if((document.getElementById("confirmPassword").value)=="") {
		document.getElementById("errConfirmPassword").innerHTML = "&nbsp;请确认密码";
		//document.getElementById("confirmPassword").onfocus();
		return false
	}
	if((document.getElementById("password").value)!=document.getElementById("confirmPassword").value) {
		document.getElementById("errConfirmPassword").innerHTML = "&nbsp;两次输入的密码不一致";
		//document.getElementById("confirmPassword").onfocus();
		return false
	}
	
	if((document.getElementById("captcha").value)=="") {
		document.getElementById("errCaptcha").innerHTML = "&nbsp;请输入验证码";
		//document.getElementById("captcha").onfocus();
		return false
	}	
	//document.form1.submit();
	return true
}

function checkForm2() {
	document.getElementById("errEmail").innerHTML = "&nbsp;";
	document.getElementById("errPassword").innerHTML = "&nbsp;";
	if((document.getElementById("email").value)=="") {
		document.getElementById("errEmail").innerHTML = "&nbsp;请输入邮箱";
		return false
	}
	if(checkEmail(document.getElementById("email").value)==0) {
		document.getElementById("errEmail").innerHTML = "&nbsp;邮箱格式不正确";
		return false
	}
	if((document.getElementById("password").value)=="") {
		document.getElementById("errPassword").innerHTML = "&nbsp;请输入密码";
		return false
	}	
}

function checkEmail(str){
   var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
   if(re.test(str)){
      return 1;
   }else{
     return 0;
   }
}