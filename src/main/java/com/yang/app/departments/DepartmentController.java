package com.yang.app.departments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepartmentController
 */
@WebServlet("/DepartmentController")
public class DepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 서버 요청시
	 * 1. URL(ip:port/경로)
	 * 2. 메서드 형식
	 * 		1)GET 
	 * 			* 단순 요청 시
	 * 			* 파라미터가 주소창에 노출(파라미터가 Header에 담김)
	 * 			* 작은 용량을 보낼 때 사용
	 * 		2)POST
	 * 			* 보안이 요구 되는 요청시
	 * 			* 파라미터가 주소창에 노출이 되지 않음! => 파라미터가 Body에 담긴다.
	 * 			* 큰 용량을 보낼 대 사용
	 * 
	 * 3. 메서드 결정
	 * 		1) 주소창에 URL 직접 입력 => GET
	 * 		2) a 태그 사용 => GET
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try {
			// TODO Auto-generated method stub
			String method = request.getMethod();
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			
			//parameter
			String department_id = request.getParameter("department_id");
			
	//		System.out.println(url.toString());
	//		System.out.println(uri);
	//		System.out.println(method);
			DepartmentDAO departmentDAO = new DepartmentDAO();
			
			uri = this.useSubString(uri);
			if(uri.equals("list.do")) {
				List<DepartmentDTO> dtos = departmentDAO.getList();
				
				PrintWriter p = response.getWriter();
				p.println("<h1>Department List</h1>");
//				p.println("<h3>");
//				p.println(dtos.get(0).getDepartment_id());
//				p.println("</h3>");
//				p.println("<h3>" + dtos.get(0).getDepartment_name()+ "</h3>");
				
				p.println("<table border = 1px>");
				p.println("<thead>");
				p.println("<tr><th>Department ID</th><th>Department Name</th><tr>");
				p.println("<tbody>");
				for(DepartmentDTO dto : dtos) {
					p.println("<tr>");
					p.println("<td>" + dto.getDepartment_id()+ "</td>");
					p.println("<td>" + dto.getDepartment_name()+ "</td>");
					p.println("</tr>");
				}
				p.println("</tbody>");
				p.println("</table>");
				
				p.close();
			}
			//else if(uri.equals("detail.do")) departmentDAO.getDetail();
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String useSubString(String data) {
		String result = data.substring(data.lastIndexOf("/") + 1);
		System.out.println(result);
		return result;
	}
	private void useSplit(String data) {
		String[] datas = data.split("/");
		System.out.println(datas[datas.length-1]);
	}
	private void useToken(String data) {
		StringTokenizer st = new StringTokenizer(data, "/");
		String result = "";
		while(st.hasMoreTokens()) {
			result = st.nextToken();
		}
		System.out.println(result);
	}
}
