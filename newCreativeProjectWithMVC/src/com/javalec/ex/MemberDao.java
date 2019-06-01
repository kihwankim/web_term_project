package com.javalec.ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class MemberDao {

	private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final String uid = "scott";
	private final String upw = "tiger";

	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;

	private static MemberDao instance = new MemberDao();

	private MemberDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberDao getInstance() {
		return instance;
	}// sington

	public int insertMember(MemberDto dto) {// 회원 가입
		int ri = 0;

		Connection connection = null;
		Statement pstmt = null;
		String query = "insert into member values ('" + dto.getId() + "','" + dto.getPw() + "','" + dto.getName()
				+ "','" + dto.geteMail() + "')";
		try {
			connection = getConnection();
			pstmt = connection.createStatement();
			pstmt.executeUpdate(query);
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	public int confirmId(String id) {// 회원가입을 위한 아이디 검색
		int ri = 0;

		Connection connection = null;
		Statement pstmt = null;
		ResultSet set = null;
		String query = "select id from member where id = '" + id + "'";

		try {
			connection = getConnection();
			pstmt = connection.createStatement();
			set = pstmt.executeQuery(query);
			if (set.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ri;
	}

	public int userCheck(String id, String pw) {// 로그인 확인
		int ri = 0;
		String dbPw;

		Connection connection = null;
		Statement pstmt = null;
		ResultSet set = null;
		String query = "select * from member where id = '" + id + "'";
		try {
			connection = getConnection();
			pstmt = connection.createStatement();
			set = pstmt.executeQuery(query);

			if (set.next()) {
				dbPw = set.getString("pw");
				if (dbPw.equals(pw)) {
					System.out.println("equals");
					ri = MemberDao.MEMBER_LOGIN_SUCCESS; // 로그인
				} else {
					System.out.println("not Equal");
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD; // 비밀번호가 틀림
				}
			} else {
				System.out.println("fail ID");
				ri = MemberDao.MEMBER_LOGIN_IS_NOT; // 존재하지 않는아이디
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}

	public MemberDto getMember(String id) {
		Connection connection = null;
		Statement pstmt = null;
		ResultSet set = null;
		String query = "select * from member where id = '" + id + "'";
		MemberDto dto = null;

		try {
			connection = getConnection();
			pstmt = connection.createStatement();
			set = pstmt.executeQuery(query);

			if (set.next()) {
				dto = new MemberDto();
				dto.setId(set.getString("id"));
				dto.setPw(set.getString("pw"));
				dto.setName(set.getString("name"));
				dto.seteMail(set.getString("eMail"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dto;

	}

	public int updateMember(MemberDto dto) {// 모든 정보를 가져와서 회원 수정

		Connection connection = null;
		Statement pstmt = null;
		String query = "update member set pw='" + dto.getPw() + "', eMail=" + dto.geteMail()
				+ "' , address=? where id='" + dto.getId() + "'";

		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return 1;
	}

	private Connection getConnection() throws Exception {
		return DriverManager.getConnection(this.url, this.uid, this.upw);
	}

}
