package web.com.javalec.Ok;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.AppControllerForWeb;

/**
 * Servlet implementation class frist_second_PageOk
 */
@WebServlet("/TFOk") // 3번째 페이지 4번째 페이지 연장
public class third_fourth_PageOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public third_fourth_PageOk() {
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

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AppControllerForWeb controller = new AppControllerForWeb();
		ArrayList<String> arrayList = new ArrayList<String>();
		request.setCharacterEncoding("EUC-KR");
		HttpSession session = request.getSession();// 섹션 객체 생성
		int numberOfSubjects = Integer.parseInt((String) session.getAttribute("numberOfSubs"));// 과목수 불러옴
		String[] subjects = new String[numberOfSubjects];// 과목이름을 저장하는 배열 형성
		System.out.println("과목 수 :" + numberOfSubjects);

		for (int index = 0; index < numberOfSubjects; index++) {
			subjects[index] = request.getParameter(Integer.toString(index + 1));
			System.out.println(subjects[index]);
			arrayList.add(subjects[index]);
		}

		if (controller.checkTheDateBaseAnd(subjects) && this.isThisEmpty(subjects)) {// 예외 처리하고 이동한느 부분
			session.setAttribute("List", arrayList);
			System.out.println("success");
			response.sendRedirect("fourthPage.jsp");
		} else {
			System.out.println("fail please write right things");
			response.sendRedirect("thirdPage_If_Error.jsp");
		}
	}

	private boolean isThisEmpty(String[] subjectName) {
		for (int index = 0; index < subjectName.length; index++) {
			if (subjectName[index] == null) {
				return false;
			}
		}
		return true;
	}
}
