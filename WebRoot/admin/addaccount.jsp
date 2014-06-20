<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="header.jsp"%>
<script language="javascript">
function alarm(){
		alert("只允许上传jpg,gif,bmp类型的文件");
}
function checkExt(){
		var allowArray = new Array("jpg","gif","bmp");
		var simg = document.getElementById("simg").value;
		var fileExt = simg.substr(simg.lastIndexOf(".")+1);
		var flag = false;
		for(var i = 0;i<allowArray.length;i++){
			if (allowArray[i] == fileExt.toLowerCase()){
				flag = true;
				break;
			}
		}
		if (flag == false){
			alert("不允许上传的文件类型，只允许上传jpg,gif,bmp类型的文件");
		}
		return flag;
	}
</script>
<form action="<%=basePath%>servlet/AdminServlet?method=insert" method="post" enctype="multipart/form-data" onSubmit="return checkExt();">
	<table width="298" border="0" align="center"
		cellpading="2"
		cellspacing="1">
		<tr>
			<td align="right">
				用户名：
			</td>
			<td align="left">
				<input type="text" name="username">
			</td>
		</tr>
		<tr>
			<td align="right">
				密&nbsp;&nbsp;码：
			</td>
			<td>
				<input type="password" name="password">
			</td>
		</tr>
		<tr>
			<td align="right">
				真实姓名：
			</td>
			<td align="left">
				<input type="text" name="name">
			</td>
		</tr>
		<tr>
			<td align="right">
				电话号码：
			</td>
			<td>
				<input type="text" name="phone">
			</td>
		</tr>
		<tr>
			<td align="right">
				身份证号码：
			</td>
			<td>
				<input type="text" name="idcard">
			</td>
		</tr>
		<tr>
			<td align="right">
				照片：
			</td>
			<td>
				<input type="file" id="simg" name="simg" onblur="return alarm();" >
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="提交">
				&nbsp;
				<input type="reset" value="取消">
			</td>
		</tr>
	</table>
</form>
<%@ include file="footer.jsp"%>