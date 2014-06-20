<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*,com.cuc.model.*,com.cuc.dao.*,java.util.*"%>
<%@ include file="header.jsp"%>
<script type="text/javascript">
	function del() {
		var msg = "您真的确定要删除吗？\n\n请确认！";
		if (confirm(msg) == true) {
			return true;
		} else {
			return false;
		}
	}
</script>

<%
	ArrayList<Admin> adminList = (ArrayList<Admin>) request
			.getAttribute("adminList");
	if (adminList.size() == 0) {
		out.println("数据库操作失败！");
	} else {

		out.println("<center><table width='100%' border='1'>");
		out
				.println("<tr><td>照片</td><td>帐户信息</td><td>操作</td></tr>");
		for (int i = 0; i < adminList.size(); i++) {
			Admin admin1 = adminList.get(i);
			out
					.println("<tr><td width=180><img src='"
							+basePath
							+"uploadFiles/"
							+admin1.getPhoto()
							+"' width=180 height=140/>"
							+"</td><td>记录号ID："
							+ admin1.getId()
							+ "<br>账号："
							+ admin1.getUsername()
							+ "<br>姓名："
							+ admin1.getName()
							+ "<br>电话号码："
							+ admin1.getPhone()
							+ "<br>身份证："
							+ admin1.getIdcard()
							+ "</td><td><a href='"
							+ basePath
							+ "servlet/AdminServlet?method=preupdate&id="
							+ admin1.getId()
							+ "'><img src='"
							+ basePath
							+ "admin/images/edit.gif ' border='0' alt='修改'/></a>  <a href='"
							+ basePath
							+ "servlet/AdminServlet?method=delete&id="
							+ admin1.getId()
							+ "&username="
							+ request.getAttribute("condition")
							+ "' onclick='javascript:return del()'><img src='"
							+ basePath
							+ "admin/images/delete.gif ' border='0' alt='删除'/></a></td></tr>");
		}
		out.println("</table></center>");
	}
%>
<%@ include file="footer.jsp"%>