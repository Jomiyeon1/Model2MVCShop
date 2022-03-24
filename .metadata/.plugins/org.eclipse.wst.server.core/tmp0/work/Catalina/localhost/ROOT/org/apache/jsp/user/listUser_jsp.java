/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.58
 * Generated at: 2022-03-24 14:48:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import com.model2.mvc.service.user.vo.*;
import com.model2.mvc.common.*;

public final class listUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.model2.mvc.service.user.vo");
    _jspx_imports_packages.add("com.model2.mvc.common");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=euc-kr");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<UserVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<UserVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>회원 목록조회</title>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/css/admin.css\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function fncGetUserList(){\r\n");
      out.write("	document.detailForm.submit();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body bgcolor=\"#ffffff\" text=\"#000000\">\r\n");
      out.write("\r\n");
      out.write("<div style=\"width:98%; margin-left:10px;\">\r\n");
      out.write("\r\n");
      out.write("<form name=\"detailForm\" action=\"/listUser.do\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" height=\"37\" border=\"0\" cellpadding=\"0\"	cellspacing=\"0\">\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td width=\"15\" height=\"37\">\r\n");
      out.write("			<img src=\"/images/ct_ttl_img01.gif\" width=\"15\" height=\"37\">\r\n");
      out.write("		</td>\r\n");
      out.write("		<td background=\"/images/ct_ttl_img02.gif\" width=\"100%\" style=\"padding-left:10px;\">\r\n");
      out.write("			<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td width=\"93%\" class=\"ct_ttl01\">회원 목록조회</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</table>\r\n");
      out.write("		</td>\r\n");
      out.write("		<td width=\"12\" height=\"37\">\r\n");
      out.write("			<img src=\"/images/ct_ttl_img03.gif\" width=\"12\" height=\"37\">\r\n");
      out.write("		</td>\r\n");
      out.write("	</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top:10px;\">\r\n");
      out.write("	<tr>\r\n");
      out.write("	");

		if(searchVO.getSearchCondition() != null) {
	
      out.write("\r\n");
      out.write("		<td align=\"right\">\r\n");
      out.write("			<select name=\"searchCondition\" class=\"ct_input_g\" style=\"width:80px\">\r\n");
      out.write("		");

				if(searchVO.getSearchCondition().equals("0")){
		
      out.write("\r\n");
      out.write("				<option value=\"0\" selected>회원ID</option>\r\n");
      out.write("				<option value=\"1\">회원명</option>\r\n");
      out.write("		");

				}else {
		
      out.write("\r\n");
      out.write("				<option value=\"0\">회원ID</option>\r\n");
      out.write("				<option value=\"1\" selected>회원명</option>\r\n");
      out.write("		");

				}
		
      out.write("\r\n");
      out.write("			</select>\r\n");
      out.write("			<input 	type=\"text\" name=\"searchKeyword\"  value=\"");
      out.print(searchVO.getSearchKeyword() );
      out.write("\" \r\n");
      out.write("							class=\"ct_input_g\" style=\"width:200px; height:19px\" >\r\n");
      out.write("		</td>\r\n");
      out.write("	");

		}else{
	
      out.write("\r\n");
      out.write("		<td align=\"right\">\r\n");
      out.write("			<select name=\"searchCondition\" class=\"ct_input_g\" style=\"width:80px\">\r\n");
      out.write("				<option value=\"0\">회원ID</option>\r\n");
      out.write("				<option value=\"1\">회원명</option>\r\n");
      out.write("			</select>\r\n");
      out.write("			<input type=\"text\" name=\"searchKeyword\"  class=\"ct_input_g\" style=\"width:200px; height:19px\" >\r\n");
      out.write("		</td>\r\n");
      out.write("	");

		}
	
      out.write("\r\n");
      out.write("		<td align=\"right\" width=\"70\">\r\n");
      out.write("			<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td width=\"17\" height=\"23\">\r\n");
      out.write("						<img src=\"/images/ct_btnbg01.gif\" width=\"17\" height=\"23\">\r\n");
      out.write("					</td>\r\n");
      out.write("					<td background=\"/images/ct_btnbg02.gif\" class=\"ct_btn01\" style=\"padding-top:3px;\">\r\n");
      out.write("						<a href=\"javascript:fncGetUserList();\">검색</a>\r\n");
      out.write("					</td>\r\n");
      out.write("					<td width=\"14\" height=\"23\">\r\n");
      out.write("						<img src=\"/images/ct_btnbg03.gif\" width=\"14\" height=\"23\">\r\n");
      out.write("					</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</table>\r\n");
      out.write("		</td>\r\n");
      out.write("	</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top:10px;\">\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td colspan=\"11\" >전체  ");
      out.print( total);
      out.write(" 건수, 현재 ");
      out.print(currentPage );
      out.write(" 페이지</td>\r\n");
      out.write("	</tr>\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td class=\"ct_list_b\" width=\"100\">No</td>\r\n");
      out.write("		<td class=\"ct_line02\"></td>\r\n");
      out.write("		<td class=\"ct_list_b\" width=\"150\">회원ID</td>\r\n");
      out.write("		<td class=\"ct_line02\"></td>\r\n");
      out.write("		<td class=\"ct_list_b\" width=\"150\">회원명</td>\r\n");
      out.write("		<td class=\"ct_line02\"></td>\r\n");
      out.write("		<td class=\"ct_list_b\">이메일</td>		\r\n");
      out.write("	</tr>\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td colspan=\"11\" bgcolor=\"808285\" height=\"1\"></td>\r\n");
      out.write("	</tr>\r\n");
      out.write("	");
 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			UserVO vo = (UserVO)list.get(i);
	
      out.write("\r\n");
      out.write("	<tr class=\"ct_list_pop\">\r\n");
      out.write("		<td align=\"center\">");
      out.print(no--);
      out.write("</td>\r\n");
      out.write("		<td></td>\r\n");
      out.write("		<td align=\"left\">\r\n");
      out.write("			<a href=\"/getUser.do?userId=");
      out.print(vo.getUserId() );
      out.write('"');
      out.write('>');
      out.print( vo.getUserId() );
      out.write("</a>\r\n");
      out.write("		</td>\r\n");
      out.write("		<td></td>\r\n");
      out.write("		<td align=\"left\">");
      out.print( vo.getUserName() );
      out.write("</td>\r\n");
      out.write("		<td></td>\r\n");
      out.write("		<td align=\"left\">");
      out.print( vo.getEmail() );
      out.write("\r\n");
      out.write("		</td>		\r\n");
      out.write("	</tr>\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td colspan=\"11\" bgcolor=\"D6D7D6\" height=\"1\"></td>\r\n");
      out.write("	</tr>\r\n");
      out.write("	");
 } 
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top:10px;\">\r\n");
      out.write("	<tr>\r\n");
      out.write("		<td align=\"center\">\r\n");
      out.write("		");

			for(int i=1;i<=totalPage;i++){
		
      out.write("\r\n");
      out.write("			<a href=\"/listUser.do?page=");
      out.print(i);
      out.write('"');
      out.write('>');
      out.print(i );
      out.write("</a>\r\n");
      out.write("		");

			}
		
      out.write("	\r\n");
      out.write("    	</td>\r\n");
      out.write("	</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<!--  페이지 Navigator 끝 -->\r\n");
      out.write("</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
