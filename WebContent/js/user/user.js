function changeValidateCode(obj) {   
	//��ȡ��ǰ��ʱ����Ϊ�������޾�������   
	var timenow = new Date().getTime();   
	//ÿ��������Ҫһ����ͬ�Ĳ�����������ܻ᷵��ͬ������֤��   
	//���������Ļ�������й�ϵ��Ҳ���԰�ҳ������Ϊ�����棬�����Ͳ�����������ˡ�   
	obj.src="rand.action?d="+timenow;   
} 

function checkForm() {
	document.getElementById("errEmail").innerHTML = "&nbsp;";
	document.getElementById("errPassword").innerHTML = "&nbsp;";
	document.getElementById("errConfirmPassword").innerHTML = "&nbsp;";
	document.getElementById("errCaptcha").innerHTML = "&nbsp;";

	if((document.getElementById("email").value)=="") {
		document.getElementById("errEmail").innerHTML = "&nbsp;����������";
		//document.getElementById("email").onfocus();
		return false
	}
	
	if(checkEmail(document.getElementById("email").value)==0) {
		document.getElementById("errEmail").innerHTML = "&nbsp;�����ʽ����ȷ";
		//document.getElementById("email").onfocus();
		return false
	}	
	if((document.getElementById("password").value)=="") {
		document.getElementById("errPassword").innerHTML = "&nbsp;����������";
		//document.getElementById("password").onfocus();
		return false
	}	
	if((document.getElementById("confirmPassword").value)=="") {
		document.getElementById("errConfirmPassword").innerHTML = "&nbsp;��ȷ������";
		//document.getElementById("confirmPassword").onfocus();
		return false
	}
	if((document.getElementById("password").value)!=document.getElementById("confirmPassword").value) {
		document.getElementById("errConfirmPassword").innerHTML = "&nbsp;������������벻һ��";
		//document.getElementById("confirmPassword").onfocus();
		return false
	}
	
	if((document.getElementById("captcha").value)=="") {
		document.getElementById("errCaptcha").innerHTML = "&nbsp;��������֤��";
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
		document.getElementById("errEmail").innerHTML = "&nbsp;����������";
		return false
	}
	if(checkEmail(document.getElementById("email").value)==0) {
		document.getElementById("errEmail").innerHTML = "&nbsp;�����ʽ����ȷ";
		return false
	}
	if((document.getElementById("password").value)=="") {
		document.getElementById("errPassword").innerHTML = "&nbsp;����������";
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