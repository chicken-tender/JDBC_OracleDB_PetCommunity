package kh_miniProject.dao;
import kh_miniProject.util.Common;
import kh_miniProject.vo.MemberVO;
import kh_miniProject.vo.ReplyVO;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class ReplyDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    //1. 특정 게시물에 달린 댓글 조회
    public List<ReplyVO> rplSpecificWrite(){
        List<ReplyVO> list = new ArrayList<>();
        System.out.print("게시글 제목 입력 : ");
        String title = sc.next();

        String sql = "SELECT R.USER_ID, REPLY_WRITE" +
                "FROM WRITE W JOIN REPLY R" +
                "ON W.BOARD_NUM = R.BOARD_NUM" +
                "WHERE TITLE = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,title);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("USER_ID");
                String replyWrite = rs.getString("REPLY_WRITE");
                ReplyVO vo = new ReplyVO();
                vo.setId(id);
                vo.setReplyWrite(replyWrite);
                list.add(vo);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void rplSpecificWritePrint(List<ReplyVO> list) {
        for(ReplyVO e : list) {
            System.out.println("아이디 :" + e.getId());
            System.out.println("댓글 : " + e.getReplyWrite());
            System.out.println("----------------------");
        }
    }

    //2. 내가 작성한 댓글 조회 - MemberVO 에서 가져오는 방식(임시)
    public List<Map<String,String>> rplSelect(MemberVO user) {
        List<Map<String,String>> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT W.BOARD_NUM, W.TITLE, R.REPLY_WRITE, W.REG_DATE" +
                    "FROM REPLY R JOIN WRITE W" +
                    "ON R.BOARD_NUM = W.BOARD_NUM" +
                    "WHERE R.USER_ID = ?" +
                    "ORDER BY W.REG_DATE DESC";
            pStmt =conn.prepareStatement(sql);
            pStmt.setString(1, user.getId());
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                int BOARD_NUM = rs.getInt("BOARD_NUM");
                String TITLE = rs.getString("TITLE");
                String REPLY_WRITE = rs.getString("REPLY_WRITE");
                Date REG_DATE = rs.getDate("REG_DATE");
                Map<String,String> vo = new HashMap<String,String>();
                vo.put("BOARD_NUM",String.valueOf(BOARD_NUM));
                vo.put("TITLE",TITLE);
                vo.put("REPLY_WRITE",REPLY_WRITE);
                vo.put("REG_DATE",String.valueOf(REG_DATE));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        }catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public void rplSelectPrint(List<Map<String,String>> list) {
        for(Map<String,String> e : list) {
            System.out.println("글번호 :" + e.get("BOARD_NUM"));
            System.out.println("제목 : " + e.get("TITLE"));
            System.out.println("댓글 : " + e.get("REPLY_WRITE"));
            System.out.println("게시글 작성날짜 : " + e.get("REG_DATE"));
            System.out.println("----------------------");
        }
    }

    //3. 내가 작성한 글에 달린 댓글 확인 - String 으로 받아오는 방식
    public List<ReplyVO> rplMyWrite(String id){
        List<ReplyVO> list = new ArrayList<>();

        String sql = "SELECT REPLY_WRITE" +
                "FROM REPLY" +
                "WHERE BOARD_NUM = (SELECT BOARD_NUM" +
                "FROM WRITE" +
                "WHERE USER_ID = ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,id);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String replyWrite = rs.getString("REPLY_WRITE");
                ReplyVO vo = new ReplyVO();
                vo.setReplyWrite(replyWrite);
                list.add(vo);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void rplMyWritePrint(List<ReplyVO> list) {
        for(ReplyVO e : list) {
            System.out.println("댓글 : " + e.getReplyWrite());
            System.out.println("----------------------");
        }
    }

    // 4. 내가 작성한 댓글 카운트
    public int rplMyWriteCount(String id){
        String sql = "SELECT COUNT(*) AS cnt" +
                "FROM REPLY" +
                "WHERE USER_ID = ?";
        int cnt = 0;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                cnt = rs.getInt("cnt");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return cnt;
    }

    public void rplMyWriteCountPrint(int cnt) {
        System.out.println("댓글 총 개수 :" + cnt);
        System.out.println("----------------------");
    }

    // 5. 특정 USER_ID가 작성한 댓글 조회
    public List<ReplyVO> rplUserWrite(String id){
        List<ReplyVO> list = new ArrayList<>();
        System.out.print("USER_ID : ");
        String userId = sc.next();

        String sql = "SELECT REPLY_WRITE 댓글" +
                "FROM REPLY" +
                "WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String replyWrite = rs.getString("REPLY_WRITE");
                ReplyVO vo = new ReplyVO();
                vo.setReplyWrite(replyWrite);
                list.add(vo);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return list;
    }

    public void rplUserWritePrint(List<ReplyVO> list) {
        for(ReplyVO e : list) {
            System.out.println("작성한 댓글 : " + e.getReplyWrite());
            System.out.println("----------------------");
        }
    }
    // 6. 댓글 수정
    public void editReply(String id) {
        System.out.print("수정할 댓글 번호를 입력하세요 : ");
        int replyNum = sc.nextInt();
        sc.nextLine();
        System.out.print("댓글 수정 : ");
        String replyEdit = sc.nextLine();

        String sql = "UPDATE REPLY" +
                "SET REPLY_WRITE = ?" +
                "WHERE REPLY_NUM = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,replyEdit);
            pStmt.setInt(2,replyNum);
            pStmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } System.out.println("댓글 수정 완료");
        Common.close(pStmt);
        Common.close(conn);
    }
    // 7. 댓글 작성
    public void rplWrite(String id) {
        System.out.print("작성할 댓글 : ");
        String replyWrite = sc.nextLine();
        System.out.print("작성할 게시글 번호 : ");
        int boardNum = sc.nextInt();

        String sql = "INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,replyWrite);
            pStmt.setString(2,id);
            pStmt.setInt(3,boardNum);
            pStmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }System.out.println("댓글 작성 완료");
        Common.close(pStmt);
        Common.close(conn);
    }

    // 8. 댓글 삭제
    public void rplDelete() {
        System.out.println("삭제할 댓글 번호 입력 : ");
        int replyNum = sc.nextInt();
        String sql = "DELETE FROM REPLY WHERE REPLY_NUM = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, replyNum);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

}
