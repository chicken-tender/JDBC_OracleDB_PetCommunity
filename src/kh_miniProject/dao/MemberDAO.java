package kh_miniProject.dao;
import kh_miniProject.JdbcMain;
import kh_miniProject.util.Common;
import kh_miniProject.vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    boolean isLogIn = false;
    public String loginId = null;

    public void signUp() {
        System.out.println("[회원가입]");
        System.out.print("ID : ");
        String id = sc.next();
        System.out.print("PW : ");
        String pw = sc.next();
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.print("메일 : ");
        String mail = sc.next();
        System.out.print("핸드폰 : ");
        String phone = sc.next();

        String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,SYSDATE)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pw);
            pstmt.setString(3,name);
            pstmt.setString(4,mail);
            pstmt.setString(5,phone);
            int ret = pstmt.executeUpdate();
            System.out.println("가입이 완료되었습니다.");
            if(ret != 0) isLogIn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void memberSelectPrint(List<MemberVO> list) {
        for(MemberVO e : list) {
            System.out.println("ID : " + e.getId());
            System.out.println("비밀번호 : " + e.getPw());
            System.out.println("이름 : " + e.getName());
            System.out.println("메일 : " + e.getMail());
            System.out.println("핸드폰 : " + e.getPhone());
            System.out.println("--------------------------");
        }
    }

    // 로그인 구현
    public String login(List<MemberVO> ml) {
        System.out.println("[로그인]");
        while (true) {
            System.out.print("ID : ");
            String id = sc.next();
            System.out.print("PW : ");
            String pw = sc.next();
            boolean match = false;
            for(MemberVO e : ml) {
                if(e.getId().equals(id) && e.getPw().equals(pw)) {
                    System.out.println("[로그인] 성공!!");
                    loginId = e.getId();
                    JdbcMain.userMenu(loginId);
                    JdbcMain.myPage(loginId);
                    JdbcMain.petMenu(loginId);
                    JdbcMain.petInfoInquire(loginId);
                    JdbcMain.petInfoQuery(loginId);
                    JdbcMain.petDiary(loginId);
                    JdbcMain.boardMenu(loginId);
                    JdbcMain.myPostPage(loginId);
                    return loginId;
                } else {
                    match = true;
                }
            }
            if (match) {
                System.out.println("계정정보가 일치하지 않습니다.");
                System.out.println("다시 시도해주십시오.");
            }
        }
    }
    public List<MemberVO> getLogInInfo() {
        List<MemberVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT USER_ID, USER_PW FROM MEMBER";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("USER_ID");
                String pw = rs.getString("USER_PW");
                MemberVO vo = new MemberVO(id, pw);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}