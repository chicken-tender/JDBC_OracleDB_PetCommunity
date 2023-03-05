package kh_miniProject.dao;
import kh_miniProject.util.Common;
import kh_miniProject.vo.BoardVO;
import kh_miniProject.vo.ReplyVO;
import kh_miniProject.vo.WriteVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    MemberDAO mdao = new MemberDAO();

    // 게시글 목록 조회
    public void viewPostList(List<WriteVO> list) {
        for(WriteVO e : list) {
            System.out.println("------------------------------");
            System.out.println("글번호 : " + e.getBoardNum());
            System.out.println("게시판명 : " + e.getBoardName());
            System.out.println("작성자 : " + e.getId());
            System.out.println("제목 : " + e.getTitle());
            System.out.println("작성날짜 : " + e.getRegDate());
            System.out.println("------------------------------");
        }
    }

    // 게시글 조회(본문 포함)
    public void viewPost(List<WriteVO> list) {
        for(WriteVO e : list) {
            System.out.println("------------------------------");
            System.out.println("글번호 : " + e.getBoardNum());
            System.out.println("게시판명 : " + e.getBoardName());
            System.out.println("작성자 : " + e.getId());
            System.out.println("제목 : " + e.getTitle());
            System.out.println("본문 : " + e.getBodyText());
            System.out.println("작성날짜 : " + e.getRegDate());
            System.out.println("------------------------------");
        }
    }

    // 게시글 선택 후 조회
    public void viewSelectPost(List<WriteVO> list) {
        for(WriteVO e : list) {
            System.out.println("------------------------------");
            System.out.println("글번호 : " + e.getBoardNum());
            System.out.println("작성자 : " + e.getId());
            System.out.println("제목 : " + e.getTitle());
            System.out.println("본문 : " + e.getBodyText());
            System.out.println("------------------------------");
        }
    }
    // 게시글 댓글 조회
    public void viewReply(List<WriteVO> list) {
        for(WriteVO e : list) {
            System.out.println("댓글 번호 : " + e.getReplyNum());
            System.out.println("댓글 사용자 : " + e.getrUserId());
            System.out.println("댓글 : " + e.getReplyWrite());
            System.out.println("------------------------------");
        }
    }

    // 본문 미리보기 조회
    public void preViewBodyTextList(List<WriteVO> list) {
        for(WriteVO e : list) {
            System.out.println("------------------------------");
            System.out.println("글번호 : " + e.getBoardNum());
            System.out.println("게시판명 : " + e.getBoardName());
            System.out.println("작성자 : " + e.getId());
            System.out.println("제목 : " + e.getTitle());
            System.out.println("본문 미리보기 : " + e.getBodyText());
            System.out.println("작성날짜 : " + e.getRegDate());
            System.out.println("------------------------------");
        }
    }

    // 전체 게시글 목록 불러오기
    public List<WriteVO> getEntireList() {
        List<WriteVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, REG_DATE FROM WRITE ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, id, title, date);
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

    // 게시글 조회(댓글 제외)
    public List<WriteVO> getPostExtractReply(int num) {
        List<WriteVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM WRITE WHERE BOARD_NUM = " + num + " ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                String bodyText = rs.getString("BODY_TEXT");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, title, bodyText, date, id);
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

    // 게시판 선택 -> 해당 게시판 글 목록 불러오기
    public List<WriteVO> getSelectBoardList() {
        BoardDAO bdao = new BoardDAO();
        List<BoardVO> bo = bdao.getEntireBoard();
        bdao.viewAllBoardList(bo);

        List<WriteVO> list = new ArrayList<>();
        System.out.print("메뉴를 선택하세요. : ");
        String board = sc.nextLine();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, REG_DATE FROM WRITE WHERE BOARD_NAME = '"
                    + board + "' ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, id, title, date);
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

    // 작성자 검색
    public List<WriteVO> getIdSearchList() {
        List<WriteVO> list = new ArrayList<>();
        System.out.print("검색할 ID : ");
        String id = sc.nextLine();
        String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, BODY_TEXT, REG_DATE FROM WRITE WHERE USER_ID = ? " +
                "ORDER BY REG_DATE DESC";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String userId = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                String bodyText = rs.getString("BODY_TEXT");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, title,bodyText,date,userId);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 제목 키워드 검색
    public List<WriteVO> getTitleSearchList() {
        List<WriteVO> list = new ArrayList<>();
        System.out.print("제목 검색 : ");
        String title = sc.nextLine();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, REG_DATE FROM WRITE WHERE TITLE LIKE '%" +
                    title + "%' ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                title = rs.getString("TITLE");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, id, title, date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 본문 키워드 검색
    public List<WriteVO> getBodyTextSearchList() {
        List<WriteVO> list = new ArrayList<>();
        System.out.print("내용 검색 : ");
        String text = sc.nextLine();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, SUBSTR(BODY_TEXT,1,10), REG_DATE FROM WRITE" +
                    " WHERE BODY_TEXT LIKE '%" + text + "%' ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                text = rs.getString("SUBSTR(BODY_TEXT,1,10)");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, title, text, date, id);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 특정 기간 검색
    public List<WriteVO> getPeriodSearchList() {
        List<WriteVO> list = new ArrayList<>();
        System.out.print("시작 날짜 : ");
        String start = sc.next();
        System.out.print("종료 날짜 : ");
        String end = sc.next();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, SUBSTR(BODY_TEXT,1,10), REG_DATE FROM WRITE" +
                    " WHERE REG_DATE BETWEEN '" + start + "' AND '" + end + "' ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                String bodyText = rs.getString("SUBSTR(BODY_TEXT,1,10)");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, title, bodyText, date, id);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 내가 작성한 글 불러오기
    public List<WriteVO> getMyPostList(String userId) {
        List<WriteVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NUM, BOARD_NAME, USER_ID, TITLE, BODY_TEXT, REG_DATE FROM WRITE WHERE USER_ID = '" +
                    userId + "' ORDER BY REG_DATE DESC";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int boardNum = rs.getInt("BOARD_NUM");
                String boardName = rs.getString("BOARD_NAME");
                String id = rs.getString("USER_ID");
                String title = rs.getString("TITLE");
                String bodyText = rs.getString("BODY_TEXT");
                Date date = rs.getDate("REG_DATE");
                WriteVO vo = new WriteVO(boardNum, boardName, title, bodyText, date, id);
                list.add(vo);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 글 작성(로그인 한 상태)
    public void writePost(String userId) {
        System.out.println("글 작성중...");
        System.out.print("게시판 선택 [자유 게시판] [정보 공유] : ");
        String boardName = sc.nextLine();
        System.out.print("제목 : ");
        String title = sc.nextLine();
        System.out.print("본문 : ");
        String bodyText = sc.nextLine();

        String sql = "INSERT INTO WRITE VALUES(BOARD_SEQ.NEXTVAL, ?,?,?,SYSDATE,?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardName);
            pstmt.setString(2,title);
            pstmt.setString(3,bodyText);
            pstmt.setString(4,userId);
            pstmt.executeUpdate();
            System.out.println("글 작성이 완료되었습니다.");
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    // 내가 작성한 글 제목 수정
    public void editMyPostTitle(String userId) {
        System.out.print("변경할 글 번호 입력 : ");
        int boardNum = sc.nextInt();
        sc.nextLine();
        System.out.print("제목 수정 : ");
        String title = sc.nextLine();

        String sql = "UPDATE WRITE SET TITLE = ? WHERE BOARD_NUM = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.setInt(2,boardNum);
            pstmt.executeUpdate();
            System.out.println("글 수정이 완료되었습니다.");
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    // 내가 작성한 글 본문 수정
    public void editMyPostBodyText(String userId) {
        System.out.print("변경할 글 번호 입력 : ");
        int boardNum = sc.nextInt();
        sc.nextLine();
        System.out.print("본문 수정 : ");
        String bodyText = sc.nextLine();

        String sql = "UPDATE WRITE SET BODY_TEXT = ? WHERE BOARD_NUM = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,bodyText);
            pstmt.setInt(2,boardNum);
            pstmt.executeUpdate();
            System.out.println("글 수정이 완료되었습니다.");
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    // 작성 글 삭제
    public void deleteMyPost(String userId) {
        System.out.print("삭제할 글 번호 입력 : ");
        int boardNum = sc.nextInt();
        String sql = "DELETE FROM WRITE WHERE BOARD_NUM = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,boardNum);
            pstmt.executeUpdate();
            System.out.println("글 삭제가 완료되었습니다.");
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    // 게시글 선택하여 조회(댓글 포함)
    public List<WriteVO> getSelectPost(int num) {
        List<WriteVO> list = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT R.REPLY_NUM AS R_REPLY_NUM, W.BOARD_NUM, W.USER_ID, TITLE, BODY_TEXT, R.USER_ID AS R_USER_ID, REPLY_WRITE " +
                    "FROM WRITE W, REPLY R WHERE W.BOARD_NUM = R.BOARD_NUM AND W.BOARD_NUM = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                list = new ArrayList<>();
                while(rs.next()) {
                    int replyNum = rs.getInt("R_REPLY_NUM");
                    int boardNum = rs.getInt("BOARD_NUM");
                    String id = rs.getString("USER_ID");
                    String title = rs.getString("TITLE");
                    String bodyText = rs.getString("BODY_TEXT");
                    String rUserId = rs.getString("R_USER_ID");
                    String reply = rs.getString("REPLY_WRITE");
                    WriteVO vo = new WriteVO(boardNum, id, title, bodyText, rUserId, reply, replyNum);
                    list.add(vo);
                }
            }
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int selectBoardNum() {
        System.out.print("조회할 글 번호 입력 : ");
        int selectNum = sc.nextInt();

        return selectNum;
    }
}
