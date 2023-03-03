package kh_miniProject.dao;
import kh_miniProject.util.Common;
import kh_miniProject.vo.PetPageVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetPageDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 1. 사용자의 전체 일지 확인
    public List<PetPageVO> petDiaryAll(String userId) {
        List<PetPageVO> list = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM PETPAGE" +
                "WHERE USER_ID = ?" +
                "ORDER BY PET_DIARY, PET_NAME ASC";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                Date petDiary = rs.getDate("PET_DIARY");
                String petImg = rs.getString("PET_IMG");
                String petWalk = rs.getString("PET_WALK");
                String petMedi = rs.getString("PET_MEDI");
                PetPageVO vo = new PetPageVO(petName, petDiary, petImg, petWalk, petMedi);

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

    public void petDiaryAllPrint(List<PetPageVO> list) {
        for (PetPageVO e : list) {
            System.out.println("날짜 : " + e.getPetDiary());
            System.out.println("고양이 이름 : " + e.getPetName());
            System.out.printf("[산책 여부 : %s ] [약 복용 여부 : %s ]", e.getPetWalk(), e.getPetMedi());
            System.out.println("--------------------------");
        }
    }


    // 2. 반려묘 별 일지 확인
    public List<PetPageVO> petSelect(String userId) {
        List<PetPageVO> list = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM PETPAGE" +
                "WHERE USER_ID = ?" +
                "ORDER BY PET_DIARY, PET_NAME ASC";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                Date petDiary = rs.getDate("PET_DIARY");
                String petImg = rs.getString("PET_IMG");
                String petWalk = rs.getString("PET_WALK");
                String petMedi = rs.getString("PET_MEDI");
                PetPageVO vo = new PetPageVO(petName, petDiary, petImg, petWalk, petMedi);

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

    public void petSelectPrint(List<PetPageVO> list) {
        for (PetPageVO e : list) {
            System.out.println("날짜 : " + e.getPetDiary());
            System.out.println("고양이 이름 : " + e.getPetName());
            System.out.println(e.getPetIMG());
            System.out.println("산책 여부 : " + e.getPetWalk());
            System.out.println("약 복용 여부 : " + e.getPetMedi());
            System.out.println("--------------------------");
        }
    }

// 3. 날짜별 모든 일지 확인
public List<PetPageVO> petDiarySelect(String userId) {
    List<PetPageVO> list = new ArrayList<>();

    String sql = "SELECT *" +
            "FROM PETPAGE" +
            "WHERE PET_DIARY = ?" +
            "ORDER BY PET_NAME";

    try {
        conn = Common.getConnection();
        pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, userId);
        rs = pStmt.executeQuery(sql);

        while (rs.next()) {
            String petName = rs.getString("PET_NAME");
            Date petDiary = rs.getDate("PET_DIARY");
            String petImg = rs.getString("PET_IMG");
            String petWalk = rs.getString("PET_WALK");
            String petMedi = rs.getString("PET_MEDI");
            PetPageVO vo = new PetPageVO(petName, petDiary, petImg, petWalk, petMedi);

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

    public void petDiarySelectPrint(List<PetPageVO> list) {
        for (PetPageVO e : list) {
            System.out.println("날짜 : " + e.getPetDiary());
            System.out.println("고양이 이름 : " + e.getPetName());
            System.out.println(e.getPetIMG());
            System.out.println("산책 여부 : " + e.getPetWalk());
            System.out.println("약 복용 여부 : " + e.getPetMedi());
            System.out.println("--------------------------");
        }
    }
    // 4. 날짜별 개별 반려묘 일지 확인
    public List<PetPageVO> petDiarySelectName(String userId) {
        List<PetPageVO> list = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM PETPAGE" +
                "WHERE PET_DIARY = ?, PET_NAME = ?" +
                "ORDER BY PET_NAME";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                Date petDiary = rs.getDate("PET_DIARY");
                String petImg = rs.getString("PET_IMG");
                String petWalk = rs.getString("PET_WALK");
                String petMedi = rs.getString("PET_MEDI");
                PetPageVO vo = new PetPageVO(petName, petDiary, petImg, petWalk, petMedi);

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

    public void petDiarySelectNamePrint(List<PetPageVO> list) {
        for (PetPageVO e : list) {
            System.out.println("날짜 : " + e.getPetDiary());
            System.out.println("고양이 이름 : " + e.getPetName());
            System.out.println(e.getPetIMG());
            System.out.println("산책 여부 : " + e.getPetWalk());
            System.out.println("약 복용 여부 : " + e.getPetMedi());
            System.out.println("--------------------------");
        }
    }

// 5. 일지 추가
public void uploadPetDiary(String Id) {
    System.out.println("반려묘 일지를 추가합니다.");
    System.out.print("이름 : ");
    String petName = sc.next();
    System.out.print("사진 등록 : ");
    String petImg = sc.next();
    System.out.print("산책 여부 (Y/N): ");
    String petWalk = sc.next();
    System.out.print("약 복용 여부 (Y/N) : ");
    String petMedi = sc.next();

    String sql = "INSERT INTO PET VALUES(?,?,sysdate,?,?,?)";

    try {
        conn = Common.getConnection();
        pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, Id);
        pStmt.setString(2, petName);
        pStmt.setString(3, petImg);
        pStmt.setString(4, petWalk);
        pStmt.setString(5, petMedi);
        int ret = pStmt.executeUpdate();
        System.out.println("반려묘 일지 등록이 완료되었습니다..");
    } catch (Exception e) {
        e.printStackTrace();
    }
    Common.close(pStmt);
    Common.close(conn);
}

// 6. 일지 수정
public void editPetDiary() {

    System.out.print("변경 할 일지 날짜 입력 (yyyy/mm/dd) : ");
    String petDiary = sc.next();
    System.out.print("변경 할 이미지 : ");
    String petImg = sc.next();
    System.out.print("산책 여부 (Y/N): ");
    String petWalk = sc.next();
    System.out.print("약 복용 여부 (Y/N) : ");
    String petMedi = sc.next();

    String sql = "UPDATE PET_PAGE SET PET_IMG = ?, PET_WALK = ?, PET_MEDI = ? WHERE PET_DIARY = ?";

    try {
        conn = Common.getConnection();
        pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, petImg);
        pStmt.setString(2, petWalk);
        pStmt.setString(3, petMedi);
        pStmt.setString(4, petDiary);
        pStmt.executeUpdate();
        System.out.println("반려묘 정보 수정이 완료되었습니다.");
    } catch (Exception e) {
        e.printStackTrace();
    }
    Common.close(pStmt);
    Common.close(conn);
}

 // 7. 일지 삭제
public void DiaryDelete(String petName, String petDiary) {
    System.out.printf("%s 의 일지를 삭제합니다.", petDiary);

    String sql = " DELETE FROM PET_PAGE WHERE PET_NAME =? AND PET_DIARY =?";

    try {
        conn = Common.getConnection();
        pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, petName);
        pStmt.setString(2, petDiary);

        pStmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    Common.close(pStmt);
    Common.close(conn);
    }


}

