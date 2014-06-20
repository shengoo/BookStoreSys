<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*,com.cuc.model.*"%>
<%@ include file="header.jsp"%>
<script language="javascript" >
function previewImg(filepath){
	
	var image1 = document.getElementById("image1");
		image1.style.display = "block";
		image1.src = filepath;
		
	}
</script>
<%
   Admin admin1=(Admin)request.getAttribute("admin");

 %>
<form action="<%=basePath%>servlet/AdminServlet?method=update" method="post"  enctype="multipart/form-data">
<input type="hidden" name="id" value=<%=admin1.getId() %>>	
<table width="298" border="0" align="center"
		cellpading="2"
		cellspacing="1">
		<tr>
			<td align="right">
				用户名：
			</td>
			<td align="left">
				<input type="text" name="username" value=<%=admin1.getUsername() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				密&nbsp;&nbsp;码：
			</td>
			<td>
				<input type="password" name="password" value=<%=admin1.getPassword() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				真实姓名：
			</td>
			<td align="left">
				<input type="text" name="name" value=<%=admin1.getName() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				电话号码：
			</td>
			<td>
				<input type="text" name="phone" value=<%=admin1.getPhone() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				身份证号码：
			</td>
			<td>
				<input type="text" name="idcard" value=<%=admin1.getIdcard() %>>
			</td>
		</tr>
		<tr>
			<td align="right">
				照片：
			</td>
			<td>
				<img id="image1" src="<%=basePath%>uploadFiles/<%=admin1.getPhoto()%>" width="180" height="140"/>
				<input type="hidden" name="oldphoto" value=<%=admin1.getPhoto() %>>
			</td>
		</tr>

		<tr>
			<td align="right">
				修改照片：
			</td>
			<td>
				<input type="file" id="simg" name="simg"  onchange="previewImg(this.value);"/> 
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="更新">
				&nbsp;
				<input type="reset" value="取消">
			</td>
		</tr>
	</table>
</form>
<%@ include file="footer.jsp"%>