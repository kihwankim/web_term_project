package viewer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.AppControllerForWeb;
import list.ArrayList;
import list.Iterator;
import list.LinkedList;
import timeTableTree.AdjacencyTreeNode;

/**
 * Servlet implementation class fifthPageServ
 */
@WebServlet("/FP")
public class fifthPageServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int _numberOfSubjects;
	private String[] _sub;
	private AppControllerForWeb _controller;
	private LinkedList<String>[] _profess;
	private ArrayList<Object[][]> _aList;

	private void setFinalList(ArrayList<Object[][]> aList) {
		this._aList = aList;
	}

	private ArrayList<Object[][]> finalList() {
		return this._aList;
	}

	private void setProfess(LinkedList<String>[] pro) {
		this._profess = pro;
	}

	private LinkedList<String>[] profess() {
		return this._profess;
	}

	private void setController(AppControllerForWeb controller) {
		this._controller = controller;
	}

	private AppControllerForWeb controller() {
		return this._controller;
	}

	private void setSub(String[] str) {
		this._sub = str;
	}

	private String[] sub() {
		return this._sub;
	}

	private void setNumberOfSubjects(int numberOfSubjects) {
		this._numberOfSubjects = numberOfSubjects;
	}

	private int numberSubjects() {
		return this._numberOfSubjects;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public fifthPageServ() {
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
		response.setContentType("text/html; charset = \"EUC-KR\"");
		PrintWriter writer = response.getWriter();
		writer.println("<html");
		writer.println("<head>");
		writer.println("<meta charset=\"EUC-KR\">");
		writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"fifthPageCSS.css\">");
		writer.println("<title>TimeTables</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<form method=\"post\" action=\"RC\">");
		writer.println("<div class=\"TimeTable\">");
		Iterator<Object[][]> iteratorOfArray = this.finalList().listIterator();
		int a = 5;
		while (iteratorOfArray.hasNext()) {
			Object[][] array = iteratorOfArray.next();
			writer.println("<table class = \"times\" boder = \"1\">");
			writer.println("<thead>");
			writer.println("<tr>");
			writer.println("<th width = \"10\"> </th>");
			writer.println("<th width = \"50\">월</th>");
			writer.println("<th width = \"50\">화</th>");
			writer.println("<th width = \"50\">수</th>");
			writer.println("<th width = \"50\">목</th>");
			writer.println("<th width = \"50\">금</th>");
			writer.println("</tr>");
			writer.println("</thead>");
			writer.println("<tbody>");
			for (int j = 1; j < 28; j++) {
				writer.println("<tr height = 25px>");
				for (int i = 0; i < 6; i++) {
					if (array[i][j] != null) {
						writer.println("<td heigth = \"25\">" + array[i][j] + "</td>");
					} else {
						writer.println("<td heigth = \"25\"></td>");
					}
				}
				writer.println("</tr>");
			}
			writer.println("</tbody>");
			writer.println("</table>");
		}
		writer.println("</div>");
		writer.println("<span>시간표를 선택 해 주세요 : </span>");
		writer.println("<input class=\"selectedTimeTable\" type=\"text\" name=\"selectedTimeTable\" size=\"10\">");
		writer.println("<br /><input type=\"submit\" value='Recomand The Liberalture Arts'>");
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");

		writer.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");// 한글이 깨지지 않도록 하기위한 코드
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			String str = cookies[i].getName();
			if (str.equals("numberOfSubs")) {
				this.setNumberOfSubjects(Integer.parseInt(cookies[i].getValue()));
				System.out.println(this.numberSubjects());
			}
		}

		this.setSub(new String[this.numberSubjects()]);

		for (int index = 0; index < cookies.length; index++) {
			String str = cookies[index].getName();// 이름
			for (int nameOfCookie = 1; nameOfCookie <= this.numberSubjects(); nameOfCookie++) {
				String nameOfSubject = Integer.toString(nameOfCookie);
				if (str.equals(nameOfSubject)) {
					this.sub()[nameOfCookie - 1] = cookies[index].getValue();
				}
			}
		}

		String emptyDay = request.getParameter("emptyDay");
		this.setProfess(new LinkedList[this.numberSubjects()]);
		for (int index = 0; index < this.numberSubjects(); index++) {
			LinkedList<String> aList = new LinkedList<String>();
			for (int i = 0; i < 4; i++) {
				String str = request.getParameter("profess" + Integer.toString(index + 1) + Integer.toString(i));
				if (str != null && !str.equals("")) {
					System.out.println(str);
					aList.add(str);
				}
			}
			this.profess()[index] = aList;
		}
		System.out.println(emptyDay);
		for (int i = 0; i < this.sub().length; i++) {
			System.out.println(this.sub()[i]);
		}
		this.setController(new AppControllerForWeb(this.numberSubjects(), this.sub(), this.profess(), emptyDay));
		this.setFinalList(this.controller().scheduleList());// 결과
		doGet(request, response);
	}
}
