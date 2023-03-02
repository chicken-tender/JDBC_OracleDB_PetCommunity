package kh_miniProject.dao;
import kh_miniProject.JdbcMain;
import kh_miniProject.util.Common;
import kh_miniProject.vo.MemberVO;
import kh_miniProject.vo.ReplyVO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class MemberDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
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
        System.out.print("핸드폰 번호 : ");
        String phone = sc.next();

        String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,SYSDATE)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,id);
            pStmt.setString(2,pw);
            pStmt.setString(3,name);
            pStmt.setString(4,mail);
            pStmt.setString(5,phone);
            int ret = pStmt.executeUpdate();
            System.out.println("회원가입이 완료되었습니다.");
            if(ret != 0) isLogIn = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void memberSelectPrint(List<MemberVO> list) {
        for(MemberVO e : list) {
            System.out.println("ID : " + e.getId());
            System.out.println("비밀번호 : " + e.getPw());
            System.out.println("이름 : " + e.getName());
            System.out.println("메일 : " + e.getMail());
            System.out.println("핸드폰 번호 : " + e.getPhone());
            System.out.println("--------------------------");
        }
    }

    // 전체 회원 정보 조회
    public List<MemberVO> memAll() {
        List<MemberVO> list = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM MEMBER" +
                "ORDER BY USER_ID";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("USER_ID");
                String pw = rs.getString("USER_PW");
                String name = rs.getString("USER_PW");
                String mail = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                Date joinDate = rs.getDate("JOIN_DATE");
                MemberVO vo = new MemberVO(id, pw, name, mail, phone, joinDate);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void memAllPrint(List<MemberVO> list) {
        for (MemberVO e : list) {
            System.out.println("ID : " + e.getId());
            System.out.println("비밀번호 : " + e.getPw());
            System.out.println("이름 : " + e.getName());
            System.out.println("메일 : " + e.getMail());
            System.out.println("핸드폰 : " + e.getPhone());
            System.out.println("가입 날짜 : " + e.getJoinDate());
            System.out.println("--------------------------");
        }
    }

    //가입일 순으로 회원 조회
    public List<MemberVO> memJoinDate() {
        List<MemberVO> list = new ArrayList<>();

        String sql = "SELECT * FROM MEMBER ORDER BY JOIN_DATE";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("USER_ID");
                String pw = rs.getString("USER_PW");
                String name = rs.getString("USER_NAME");
                String mail = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                Date joinDate = rs.getDate("JOIN_DATE");
                MemberVO vo = new MemberVO(id, pw, name, mail, phone, joinDate);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void memJoinDatePrint(List<MemberVO> list) {
        for (MemberVO e : list) {
            System.out.println("ID : " + e.getId());
            System.out.println("비밀번호 : " + e.getPw());
            System.out.println("이름 : " + e.getName());
            System.out.println("메일 : " + e.getMail());
            System.out.println("핸드폰 : " + e.getPhone());
            System.out.println("가입 날짜 : " + e.getJoinDate());
            System.out.println("--------------------------");
        }
    }

    // 특정 날짜 가입 인원 조회
    public int specificJoinDateCount(String id) {
        String sql = "SELECT JOIN_DATE, COUNT(*) FROM MEMBER GROUP BY JOIN_DATE";
        int cnt = 0;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);

            cnt = rs.getInt("cnt");

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return cnt;
    }

    public void specificJoinDateCountPrint(int cnt) {
        System.out.println("가입 인원 :" + cnt);
        System.out.println("----------------------");
    }

    // 특정 회원정보 조회
    public List<MemberVO> memSpecific () {
        List<MemberVO> list = new ArrayList<>();
        System.out.print("USER_ID : ");
        String userId = sc.next();

        String sql = "SELECT * " +
                "FROM MEMBER WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("USER_ID");
                String pw = rs.getString("USER_PW");
                String name = rs.getString("USER_NAME");
                String mail = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                Date joinDate = rs.getDate("JOIN_DATE");
                MemberVO vo = new MemberVO(id, pw, name, mail, phone, joinDate);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void memSpecificPrint (List < MemberVO > list) {
        for (MemberVO e : list) {
            System.out.println("ID : " + e.getId());
            System.out.println("비밀번호 : " + e.getPw());
            System.out.println("이름 : " + e.getName());
            System.out.println("메일 : " + e.getMail());
            System.out.println("핸드폰 : " + e.getPhone());
            System.out.println("가입 날짜 : " + e.getJoinDate());
            System.out.println("--------------------------");
        }
    }

    // 특정 회원 아이디 조회
    public List<MemberVO> memIDSpecific () {
        List<MemberVO> list = new ArrayList<>();
        System.out.print("핸드폰 번호 : ");
        String phone = sc.next();

        String sql = "SELECT USER_ID" +
                "FROM MEMBER WHERE PHONE = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, phone);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("USER_ID");
                MemberVO vo = new MemberVO();
                vo.setId(id);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void memIDSpecificPrint (List < MemberVO > list) {
        for (MemberVO e : list) {
            System.out.println("ID : " + e.getId());
            System.out.println("--------------------------");
        }
    }

    // 특정 회원 비밀번호 조회
    public List<MemberVO> memPwSpecific () {
        List<MemberVO> list = new ArrayList<>();
        System.out.print("USER_ID : ");
        String id = sc.next();

        String sql = "SELECT USER_PW" +
                "FROM MEMBER WHERE USER_ID = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String pw = rs.getString("USER_PW");
                MemberVO vo = new MemberVO();
                vo.setPw(pw);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void memPwSpecificPrint (List < MemberVO > list) {
        for (MemberVO e : list) {
            System.out.println("비밀번호 : " + e.getPw());
            System.out.println("--------------------------");
        }
    }

    // 특정 회원 가입일 조회
    public List<MemberVO> memJoinDateSpecific () {
        List<MemberVO> list = new ArrayList<>();
        System.out.print("USER_ID : ");
        String id = sc.next();

        String sql = "SELECT JOIN_DATE FROM MEMBER WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                Date joinDate = rs.getDate("JOIN_DATE");
                MemberVO vo = new MemberVO();
                vo.setJoinDate(joinDate);
                list.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void memJoinDateSpecificPrint (List < MemberVO > list) {
        for (MemberVO e : list) {
            System.out.println("가입 날짜 : " + e.getJoinDate());
            System.out.println("--------------------------");
        }
    }

    /*회원*/
    // 이메일 변경
    public void emailUpdate(String id){
        System.out.print("변경할 이메일을 입력하세요. : ");
        String email = sc.next();

        String sql = "UPDATE MEMBER SET EMAIL = ? WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, email);
            pStmt.setString(2, id);
            pStmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("변경 완료");
        Common.close(pStmt);
        Common.close(conn);
    }

    // 비밀번호 변경
    public void pwUpdate(String id){
        System.out.print("변경할 비밀번호를 입력하세요. : ");
        String pw = sc.next();

        String sql = "UPDATE MEMBER SET PW = ? WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pw);
            pStmt.setString(2, id);
            pStmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("변경 완료");
        Common.close(pStmt);
        Common.close(conn);
    }

    // 아이디 변경
    public void idUpdate(String id){
        System.out.print("변경할 아이디를 입력하세요. : ");
        String updateId = sc.next();

        String sql = "UPDATE MEMBER SET USER_ID = ? WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, updateId);
            pStmt.setString(2, id);
            pStmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("변경 완료");
        Common.close(pStmt);
        Common.close(conn);
    }

    // 반려동물 유무 (0마리 일 때, 예비 집사 / 1마리 이상일 때, 집사) - 오류 가능성 있음
    public int rplSelect() {
        int cnt = 0;
        try {
            conn = Common.getConnection();
            String sql = "SELECT COUNT(PET_NAME) AS cnt, M.USER_ID" +
                    "FROM MEMBER M LEFT OUTER JOIN PET P" +
                    "ON M.USER_ID = P.USER_ID" +
                    "group by M.USER_ID" +
                    "ORDER BY USER_ID;";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("USER_ID");
                cnt = rs.getInt("cnt");
                MemberVO vo = new MemberVO();
                vo.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return cnt;
    }

    public void rplSelectPrint(int cnt) {
        if (cnt == 0) {
            System.out.println("당신은 현재 고양이가 없는 예비 집사입니다. 고양이가 있다면 펫페이지에 당신의 고양이를 등록해주세요!");
        }else {
            System.out.println("당신은 " + cnt + "마리의 반려 냥을 가진 집사입니다.");
        }
        System.out.println("----------------------");
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
                    JdbcMain.editMyInfo(loginId);
                    JdbcMain.myPostPage(loginId);
                    JdbcMain.petMenu(loginId);
                    JdbcMain.petInfoInquire(loginId);
                    JdbcMain.petAllInfo(loginId);
                    JdbcMain.petSelectInfo(loginId);
                    JdbcMain.petDailyDiary(loginId);
                    JdbcMain.boardMenu(loginId);
                    JdbcMain.boardSearchMenu(loginId);
                    JdbcMain.editPetDiary(loginId);
                    JdbcMain.replyMenu(loginId);
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