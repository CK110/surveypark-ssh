<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改用户授权</title>
<link rel="stylesheet" type="text/css" href='<s:url value="css/styles.css" />'>
<link rel="stylesheet" type="text/css" href='<s:url value="css/bootstrap.min.css" />'>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#one1').click(function(){
			var size = $('#left>option:selected').size();
			if(size != 0){
				$('#left > option:selected').appendTo($('#right'));
			}
			else{
				$('#left>option:first-child').appendTo($('#right'));
			}
		});	
		$('#all1').click(function(){
			$('#left > option').appendTo($('#right'));
		});	
		$('#one2').click(function(){
			var size = $('#right>option:selected').size();
			if(size != 0){
				$('#right > option:selected').appendTo($('#left'));
			}
			else{
				$('#right>option:first-child').appendTo($('#left'));
			}
		});	
		$('#all2').click(function(){
			$('#right > option').appendTo($('#left'));
		});	
	});
	
	function submitForm1(){
		$('#left > option').attr('selected','selected');
		return true ;
	}
</script>
</head>
<body>
<s:include value="../manager/manager_header.jsp" />
<div class = "container">
<table>
	<tr>
		<td class="tdHeader">修改用户授权:</td>
	</tr>
	<tr>
		<td style="vertical-align: top;">
			<table>
				<tr>
					<td>
						<s:form action="UserAuthorizeAction_updateAuthorize" namespace="/" method="post">
						<s:hidden name="id" />
						<table>
							<tr>
								<td class="tdFormLabel" width="200px">email:</td>
								<td class="tdFormControl"><s:textfield name="email" disabled="true"/></td>
							</tr>
							<tr>
								<td class="tdFormLabel" width="200px">nickName:</td>
								<td class="tdFormControl"><s:textfield name="nickName"  disabled="true"/></td>
							</tr>
							<tr>
								<td class="tdFormLabel" colspan="2">
									<table>
										<tr>
											<td width="32%" align="right">
												<s:select id="left" 
													name="ownRoleIds" 
													list="roles" 
													listKey="id" 
													listValue="roleName"
													multiple="true"
													size="15"
													cssStyle="width:100px">
												</s:select>
											</td>
											<td width="10%" valign="middle" align="center">
												<input type="button" id="one1" value="&gt;" class="btn"></input><br><br>
												<input type="button" id="one2" value="&lt;" class="btn"></input><br><br>
												<input type="button" id="all1" value="&gt;&gt;" class="btn"></input><br><br>
												<input type="button" id="all2" value="&lt;&lt;" class="btn"></input><br><br>
											</td>
											<td width="45%" align="left">
												<s:select id="right" 
													list="noOwnRoles" 
													name="noOwnRoleIds"
													listKey="id" 
													listValue="roleName"
													multiple="true"
													size="15"
													cssStyle="width:100px">
												</s:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="tdFormLabel"></td>
								<td class="tdFormControl"><s:submit value="确定" cssClass="btn" onclick="javascript:return submitForm1()"/></td>
							</tr>
						</table>
						</s:form>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</body>
</html>