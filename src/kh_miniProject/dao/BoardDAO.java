package kh_miniProject.dao;
import kh_miniProject.util.Common;
import kh_miniProject.vo.BoardVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 전체 게시판 이름 불러오기
    public List<BoardVO> getEntireBoard() {
        List<BoardVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT BOARD_NAME FROM BOARD ORDER BY BOARD_NAME";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String boardName = rs.getString("BOARD_NAME");
                BoardVO vo = new BoardVO(boardName);
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
    // 게시판 이름 조회
    public void viewAllBoardList(List<BoardVO> list) {
        for(BoardVO e : list) {
            System.out.print("[" + e.getBoardName()+ "]" + " ");
        }
    }
}
