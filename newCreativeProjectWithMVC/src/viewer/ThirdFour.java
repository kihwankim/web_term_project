package viewer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThirdFour
 */
@WebServlet("/TF")
public class ThirdFour extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] _subs;
	private int _numberOfSubjects;

	private void setNumberOfSubjects(int numberOfSubjects) {
		this._numberOfSubjects = numberOfSubjects;
	}

	private int numberOfSubjects() {
		return this._numberOfSubjects;
	}

	private void setSubs(String[] str) {
		this._subs = str;
	}

	private String[] subs() {
		return this._subs;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThirdFour() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("loading....");
		response.setContentType("text/html; charset = \"EUC-KR\"");
		PrintWriter writer = response.getWriter();
		writer.println("<html");
		writer.println("<head>");
		writer.println("<meta charset=\"EUC-KR\">");
		writer.println("</head>");
		writer.println("<body>");

		writer.println("<jsp:forward page = \"fourthPage.jsp\">");
		writer.println("<jsp:param name = \"theNumberOfSubjects\" value = \"" + this.numberOfSubjects() + "\"/>");
		for (int index = 1; index <= this.numberOfSubjects(); index++) {
			writer.println("<jsp:param name = \"\" value = \"" + this.subs()[index - 1] + "\"/>");
		}
		writer.println("</jsp:forward>");

		writer.println("</body>");
		writer.println("</html>");

		writer.close();
		request.setAttribute("theNumberOfSubjects", this.numberOfSubjects());
		for (int index = 1; index <= this.numberOfSubjects(); index++) {
			request.setAttribute(Integer.toString(index), this.subs()[index - 1]);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/fourthPage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");
		Cookie[] cookies = request.getCookies();
		String numberOfSubjects = null;
		for (int i = 0; i < cookies.length; i++) {
			String str = cookies[i].getName();
			if (str.equals("numberOfSubs")) {
				numberOfSubjects = cookies[i].getValue();
			}
		}
		if (numberOfSubjects != null) {
			this.setNumberOfSubjects(Integer.parseInt(numberOfSubjects));
			System.out.println(this.numberOfSubjects());
		}

		this.setSubs(new String[this.numberOfSubjects()]);
		for (int index = 1; index <= this.numberOfSubjects(); index++) {
			this.subs()[index - 1] = request.getParameter(Integer.toString(index));
			System.out.println(this.subs()[(index - 1)]);
		}
		this.doGet(request, response);
	}
}
