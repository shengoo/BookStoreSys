<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>
	<SCRIPT LANGUAGE="JavaScript">
	function validate(myform) {
		if (myform.webuser.value.length == 0) {
			alert("请填写帐号！");
			myform.webuser.focus();
			return false;
		}
		if (myform.password.value.length == 0) {
			alert("请填写密码！");
			myform.password.focus();
			return false;
		}

		return true;
	}

	function isSameUser(webuser){
                     if(webuser.value.length!=0){
	  		var xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	  		var url = "<%=basePath%>servlet/WebUserServlet?method=sameuser&webuser="+webuser.value;
			xmlHttp.open("POST", url, true);				
			xmlHttp.onreadystatechange=function() {
				if (xmlHttp.readyState==4) {
					userDiv.innerHTML = xmlHttp.responseText;
				}				
			}
			xmlHttp.send();	
                      }
  		}

</SCRIPT>
		<form method="post" action="<%=basePath%>servlet/WebUserServlet?method=insert" name="register"
			onSubmit="return validate(this)">
			<p>
				&nbsp;
			</p>
			<table width="200" border="1" align="center">
				<tbody width="200">
					<tr>
						<td>
							帐号：
							&nbsp;
							<input type="text" maxlength="6" size="6" name="webuser" onblur="isSameUser(this)"><span id="userDiv"></span>
						</td>
					</tr>
					<tr>
						<td>
							密码：
							&nbsp;
							<input type="password" maxlength="6" size="6" name="password">
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
							<input type="submit" value="提交">
							&nbsp;
							<input type="reset" value="重输">
						</td>
					</tr>
				</tbody>
			</table>
			<p>
				&nbsp;
			</p>
		</form>
<%@ include file="footer.jsp"%>