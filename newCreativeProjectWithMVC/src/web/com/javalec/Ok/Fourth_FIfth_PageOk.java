package web.com.javalec.Ok;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.AppControllerForWeb;
import list.LinkedList;

/**
 * Servlet implementation class Fourth_FIfth_Page
 */
@WebServlet("/FFOk")
public class Fourth_FIfth_PageOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Fourth_FIfth_PageOk() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		this.actionDo(request, response);
	}

	@SuppressWarnings("unchecked")
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		HttpSession session = request.getSession();// 섹션 객체 생성
		int numberOfSubjects = Integer.parseInt((String) session.getAttribute("numberOfSubs"));
		LinkedList<String>[] listOfProfess = new LinkedList[numberOfSubjects];// 배열 생성
		for (int frontIndex = 0; frontIndex < numberOfSubjects; frontIndex++) {// 교수님 명 저장하는 코드
			listOfProfess[frontIndex] = new LinkedList<String>();// 객체 생성
			for (int lastIndex = 0; lastIndex < 4; lastIndex++) {
				String professName = request
						.getParameter("profess" + Integer.toString(frontIndex) + Integer.toString(lastIndex));
				if (professName.length() != 0) {
					listOfProfess[frontIndex].add(professName);// 데이터 추가
				}
			}
		}

		String[] subjectsName = this.subjectsName((ArrayList<String>) session.getAttribute("List"), numberOfSubjects);
		String emptyDay = request.getParameter("emptyDay");
		AppControllerForWeb controller = new AppControllerForWeb(numberOfSubjects, subjectsName, listOfProfess,
				emptyDay);// 객체 생성

		if (emptyDay != null && controller.isThereNoProfessOrNot()) {// 예외 처리하고 이동한느 부분
			System.out.println("success");
			session.setAttribute("outputOfTheTimeTable", controller.scheduleList());
			session.setAttribute("professNames", listOfProfess);// db에 저장 용로 일단 session에 저장
			session.setAttribute("emptyDay", emptyDay);// 공강인 요일을 String 으로 저장해서 넘김 db저장 때문에 넘김
			response.sendRedirect("fifthPage.jsp");
		} else {
			System.out.println("fail");
			response.sendRedirect("fourthPage.jsp");
		}
	}

	private String[] subjectsName(ArrayList<String> subjectsName, int numberOfSubjects) {
		String[] subjectsNameInStr = new String[numberOfSubjects];
		Iterator<String> iterator = subjectsName.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			subjectsNameInStr[index++] = iterator.next();
		}
		return subjectsNameInStr;
	}
}
