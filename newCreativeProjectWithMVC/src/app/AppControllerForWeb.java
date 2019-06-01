package app;

import GUI.Schedule;
import database.dataBaseList;
import list.ArrayList;
import list.Iterator;
import list.LinkedList;
import timeTableTree.AdjacencyTreeNode;
import timeTableTree.MakeTheNodeList;
import timeTableTree.TimeTableTree;

public class AppControllerForWeb {

	private int _numberOfSubjects;
	private String[] _subjects;
	private LinkedList<String>[] _professor;
	private MakeTheNodeList<AdjacencyTreeNode> _makeTheNodeList;
	private dataBaseList _dataBaseList;
	private TimeTableTree<AdjacencyTreeNode> _timeTableTree;
	private String _emptyDay;
	private Schedule _schedule;

	private void setSchedule(Schedule newSchedule) {
		this._schedule = newSchedule;
	}

	private Schedule schedule() {
		return this._schedule;
	}

	private void setEmptyDay(String emptyDay) {
		this._emptyDay = emptyDay;
	}

	private String emptyDay() {
		return this._emptyDay;
	}

	private void setTimeTableTree(TimeTableTree<AdjacencyTreeNode> tree) {
		this._timeTableTree = tree;
	}

	private TimeTableTree<AdjacencyTreeNode> timeTableTree() {
		return this._timeTableTree;
	}

	private void setDataBaseList(dataBaseList dataBaseList) {
		this._dataBaseList = dataBaseList;
	}

	private dataBaseList dataBaseList() {
		return this._dataBaseList;
	}

	private void setMakeTheNodeList(MakeTheNodeList<AdjacencyTreeNode> nodeList) {
		this._makeTheNodeList = nodeList;
	}

	private MakeTheNodeList<AdjacencyTreeNode> makeTheNodeList() {
		return this._makeTheNodeList;
	}

	private void setProfessor(LinkedList<String>[] str) {// 교수님 명 입력받은곳
		this._professor = str;
	}

	private LinkedList<String>[] professor() {
		return this._professor;
	}

	private int numberOfSubjects() {
		return this._numberOfSubjects;
	}

	private void setNumberOfSubject(int subs) {
		this._numberOfSubjects = subs;
	}

	private String[] subjects() {
		return this._subjects;
	}

	private void setSubjects(String[] _Subjects) {
		this._subjects = _Subjects;
	}

	private LinkedList<AdjacencyTreeNode[]> outputOfNodeList() {
		this.setMakeTheNodeList(new MakeTheNodeList<AdjacencyTreeNode>(this.dataBaseList().dataBase(), this.subjects(),
				this.numberOfSubjects()));
		this.makeTheNodeList().makeTheListAndAddLevel();
		this.makeTheNodeList().swapFromTheChoice(this.professor());
		return this.makeTheNodeList().treeList();
	}

	private LinkedList<LinkedList<AdjacencyTreeNode>> outputOfTree() {
		this.setTimeTableTree(new TimeTableTree<AdjacencyTreeNode>(this.outputOfNodeList(), this.numberOfSubjects()));
		this.timeTableTree().useAllOfRoot(this.emptyDay());
		return this.timeTableTree().listOfpaths();
	}

	public ArrayList<Object[][]> scheduleList() {// 모든 시간표가 저장되어있는 ArrayList
		LinkedList<LinkedList<AdjacencyTreeNode>> aList = this.outputOfTree();
		this.setSchedule(new Schedule(aList));
		this.schedule().makeTimeTableForShowing();
		return this.schedule().arrayForWebShow();
	}

	public AppControllerForWeb(int numberOfSubject, String[] subjects, LinkedList<String>[] aList, String emptyDay) {// 객체받아오기
		this.setNumberOfSubject(numberOfSubject);
		this.setSubjects(subjects);// 과목명 입력받기
		this.setProfessor(aList); // 교수님 명 입력 받음
		this.setEmptyDay(emptyDay);// 공강 날자 설정
		this.setDataBaseList(new dataBaseList("컴퓨터공학과"));
	}

	public AppControllerForWeb() {
		this.setDataBaseList(new dataBaseList("컴퓨터공학과"));// 객체 만들기 용도
	}

	public boolean checkTheDateBaseAnd(String[] subjectNames) {// 과목 예외 처리 해 주는 역할
		if (this.isThereSameSubjet(subjectNames)) {
			return false;
		} // 동일한 값을 입력했는지 확인 해 주는 코드

		for (int index = 0; index < subjectNames.length; index++) {
			Iterator<AdjacencyTreeNode[]> iterator = this.dataBaseList().dataBase().listIterator();// 다시 동적 할당
			boolean isNotThere = false;
			while (iterator.hasNext()) {
				AdjacencyTreeNode[] arrayNode = iterator.next();
				if (arrayNode[0].subject().equals(subjectNames[index])) {
					isNotThere = true;// 내가 지정한 곳에 data가 존재 할 경우
					break;
				} // 코드 문제 다시 작성하기
			}
			if (!isNotThere) {
				return false;
			}
		}
		return true;
	}

	private boolean isThereSameSubjet(String[] subjects) {// 중첩된 것이 있는지 판별
		for (int i = 0; i < subjects.length; i++) {
			for (int j = i + 1; j < subjects.length; j++) {
				if (subjects[i].equals(subjects[j])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isThereNoProfessOrNot() {
		this.setMakeTheNodeList(new MakeTheNodeList<AdjacencyTreeNode>(this.dataBaseList().dataBase(), this.subjects(),
				this.numberOfSubjects()));
		this.makeTheNodeList().makeTheListAndAddLevel();
		Iterator<AdjacencyTreeNode[]> iterator = this.makeTheNodeList().treeList().listIterator();
		int index = 0;
		while (iterator.hasNext()) {
			AdjacencyTreeNode[] node = iterator.next();
			Iterator<String> secondIterator = this.professor()[index++].listIterator();
			boolean isThere = false;
			while (secondIterator.hasNext()) {
				String professName = secondIterator.next();
				for (int lengthOfNode = 0; lengthOfNode < node.length; lengthOfNode++) {
					if (node[lengthOfNode].professor().equals(professName)) {
						isThere = true;
						break;// 동일 한거 존재
					}
				}
				if (!isThere) {
					return false;
				}
				isThere = false;
			}
		}
		return true;
	}

	public void test() {
		LinkedList<String>[] alt = new LinkedList[3];
		String[] subject = new String[3];
		subject[0] = "웹프로그래밍";
		subject[1] = "데이터통신";
		subject[2] = "운영체제및실습";

		String a = subject[0];
		String b = subject[1];
		String c = subject[2];
		for (int i = 0; i < 3; i++) {
			alt[i] = new LinkedList();
		}
		this.setProfessor(alt);
		this.setSubjects(subject);
		if (this.isThereNoProfessOrNot())
			System.out.println("true");
		else
			System.out.println("false");
	}
}
