<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ include file="header.jsp"%>
<SCRIPT LANGUAGE="JavaScript">
	function validate(myform) {
		if (myform.oldpassword.value.length == 0) {
			alert("请填写密码！");
			myform.oldpassword.focus();
			return false;
		}
		if (myform.oldpassword.value!=<%=admin.getPassword()%> ) {
			alert("密码不正确！");
			myform.oldpassword.focus();
			return false;
		}
		if (myform.newpassword.value.length == 0) {
			alert("请填写新密码！");
			myform.newpassword.focus();
			return false;
		}
		if (myform.againpassword.value.length == 0) {
			alert("请再次填写新密码！");
			myform.againpassword.focus();
			return false;
		}
		if (myform.newpassword.value != myform.againpassword.value) {
			alert("两次密码不一样！");
			myform.newpassword.focus();
			return false;
		}
		return true;
	}
</SCRIPT>
<center>
	<table>
		<tr>
			<td>
				<form method="post"
					action="<%=basePath%>servlet/AdminServlet?method=change&id=<%=admin.getId()%>"
					id="addaccount" onSubmit="return validate(this);">
					<p>
						&nbsp;输入旧密码：
						<input type="password" maxlength="6" size="6" name="oldpassword">
					</p>
					<p>
						输入新密码:
						<input type="password" maxlength="6" size="6" name="newpassword">
					</p>
					<p>
						再次输入新密码：
						<input type="password" maxlength="6" size="6" name="againpassword">
					</p>
					<p>
						&nbsp;
						<input type="submit" value="修改" name="button1">
						<input type="reset" value="重填" name="button2">
						<br>
						<br>
					</p>
				</form>
			</td>
		</tr>
	</table>
</center>
<%@ include file="footer.jsp"%>
