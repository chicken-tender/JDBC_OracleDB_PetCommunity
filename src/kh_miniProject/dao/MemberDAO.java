package kh_miniProject.dao;
import kh_miniProject.util.Common;
import kh_miniProject.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class MemberDAO {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    boolean isLogIn = false;

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
            System.out.println("회원가입이 완료되었습니다.");
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
}