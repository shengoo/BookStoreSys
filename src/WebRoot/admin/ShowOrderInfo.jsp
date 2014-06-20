<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.cuc.model.*,java.util.*"%>
<%@ include file="header.jsp"%>
		<%
ArrayList orderinfo=(ArrayList)	request.getAttribute("orderinfo");		
ArrayList<String[]> content = (ArrayList<String[]>)orderinfo.get(0);
int totalNum=(Integer)orderinfo.get(1);
float totalPrice=(Float)orderinfo.get(2);
		%>
		<table width="88%" border="0" align="center" cellspacing="0">
			<tr align="center" valign="middle" bgcolor="#006699">
				<td>
					<font color="#FFFFFF">ISBN</font>
				</td>
				<td>
					<font color="#FFFFFF">书名</font>
				</td>
				<td>
					<font color="#FFFFFF">定购数量</font>
				</td>
				<td>
					<font color="#FFFFFF">小计</font>
				</td>
			</tr>

			<%
				for (int i=0;i<content.size();i++) {
					String[] str = (String[]) content.get(i);
			%>
			<tr align="center">
				<td height="26"><%=str[0]%></td>
				<td height="26"><%=str[1]%></td>
				<td height="26"><%=str[2]%></td>
				<td height="26"><%=str[3]%></td>
			</tr>
			<%
				}
			%>
		</table>
			<br><br><br>  
            <center>总计：<%=totalNum%>本，<%=totalPrice %>元。<input type=button value="返回" onclick="javascript: history.back(-1);"/></center>
<%@ include file="footer.jsp"%>
