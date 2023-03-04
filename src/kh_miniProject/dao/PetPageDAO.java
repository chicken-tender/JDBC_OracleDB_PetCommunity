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

        String sql = "SELECT * FROM PET_PAGE WHERE USER_ID = ? ORDER BY PET_DIARY, PET_NAME ASC";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                String petDiary = rs.getString("PET_DIARY");
                String petWalk = rs.getString("PET_WALK");
                String petMedi = rs.getString("PET_MEDI");
                PetPageVO vo = new PetPageVO(petName, petDiary, petWalk, petMedi);

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
            String tmp = e.getPetDiary();

            System.out.println("날짜 : " + tmp.substring(0, 10));
            System.out.println("냥이 이름 : " + e.getPetName());
            System.out.printf("[산책 여부 : %s ] [약 복용 여부 : %s ]", e.getPetWalk(), e.getPetMedi());
            System.out.println("\n--------------------------------");
        }
    }

    // 2. 모든 날짜의 일지 (간단히)

    public void petDiarySimplePrint(List<PetPageVO> listDateAll) {
        System.out.println("기록이 등록되어 있는 날짜를 조회합니다..");
        for (PetPageVO e : listDateAll) {
            String tmp = e.getPetDiary();

            System.out.printf("[%s] %s \n", tmp.substring(0, 10), e.getPetName());
        }
    }

    // 3. 날짜의 모든 반려묘 일지 출력
    public List<PetPageVO> petDiarySelectDate(String petDiary) {
        List<PetPageVO> list = new ArrayList<>();
        System.out.println("----------------------------");
        System.out.printf("[%s]의 모든 기록\n", petDiary);

        String sql = "SELECT * FROM PET_PAGE WHERE PET_DIARY = ? ORDER BY PET_DIARY, PET_NAME ASC";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, petDiary);
            rs = pStmt.executeQuery();

            while (rs.next()) {

                String petName = rs.getString("PET_NAME");
                String petIMG = rs.getString("PET_IMG");
                String petMemo = rs.getString("PET_MEMO");
                String petWalk = rs.getString("PET_WALK");
                String petMedi = rs.getString("PET_MEDI");
                PetPageVO vo = new PetPageVO(petName, petDiary, petIMG, petMemo, petWalk, petMedi);

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

    public void petDiarySelectPrint(List<PetPageVO> listPetSelectDate) {

        for (PetPageVO e : listPetSelectDate) {
            System.out.println("----------------------------");
            System.out.println("반려묘 이름 : " + e.getPetName());
            System.out.println("[사진]");
            System.out.println(e.getPetIMG());
            System.out.println("기록 : " + e.getPetMemo());
            System.out.println("산책 여부 : " + e.getPetWalk());
            System.out.println("약 복용 여부 : " + e.getPetMedi());
        }
        System.out.println("----------------------------");
    }


    // 4. 반려묘 별 일지 확인
    public List<PetPageVO> petSelect(String userId) {
        List<PetPageVO> listPetSelect = new ArrayList<>();

        System.out.print("조회를 원하는 반려묘 이름을 입력하세요 : ");
        String petName = sc.next();

        String sql = "SELECT * FROM PET_PAGE WHERE USER_ID = ? AND PET_NAME = ? ORDER BY PET_DIARY, PET_NAME ASC";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            pStmt.setString(2, petName);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                String petDiary = rs.getString("PET_DIARY");
                String petImg = rs.getString("PET_IMG");
                String petMemo = rs.getString("PET_MEMO");
                String petWalk = rs.getString("PET_WALK");
                String petMedi = rs.getString("PET_MEDI");
                PetPageVO vo = new PetPageVO(petDiary, petImg, petMemo, petWalk, petMedi);

                listPetSelect.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);

        return listPetSelect;
    }

    public void petSelectPrint(List<PetPageVO> listPetSelect) {
        for (PetPageVO e : listPetSelect) {
            String tmp = e.getPetDiary();

            System.out.println("날짜 : " + tmp.substring(0, 10));
            System.out.println("[사진]");
            System.out.println(e.getPetIMG());
            System.out.println("기록 : " + e.getPetMemo());
            System.out.println("산책 여부 : " + e.getPetWalk());
            System.out.println("약 복용 여부 : " + e.getPetMedi());
            System.out.println("----------------------------");
        }
    }



    // 4. 날짜별 개별 반려묘 일지 확인
    public List<PetPageVO> petDiarySelectName(String userId) {
        List<PetPageVO> list = new ArrayList<>();

        String sql = "SELECT * FROM PET_PAGE WHERE PET_DIARY = ?, PET_NAME = ? ORDER BY PET_NAME";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                String petDiary = rs.getString("PET_DIARY");
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
            System.out.println("\n----------------------------");
        }
    }


    // 5. 일지 추가
    public void uploadPetDiary(String userId) {
        System.out.println("반려묘 일지를 추가합니다.");
        System.out.print("반려묘 이름 : ");
        String petName = sc.next();
        System.out.print("등록할 날짜 : ");
        String petDate = sc.next();
        System.out.print("사진 등록 (링크를 등록하거나 등록을 원하지 않으면 'N'를 입력해주세요) : ");
        String petImg = sc.next();
        System.out.print("기록 (등록을 원하지 않으면 'N'를 입력해주세요 : ");
        String petMemo = sc.nextLine();
        sc.nextLine();
        System.out.print("산책 여부 (Y/N): ");
        String petWalk = sc.next();
        System.out.print("약 복용 여부 (Y/N) : ");
        String petMedi = sc.next();

        String sql = "INSERT INTO PET_PAGE VALUES(?,?,?,?,?,?,?)";

        String updateImg = null, updateMemo = null;
        if(petImg.equalsIgnoreCase("n")) petImg = updateImg;
        if(petMemo.equalsIgnoreCase("n")) petMemo = updateMemo;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            pStmt.setString(2, petName);
            pStmt.setString(3, petDate);
            pStmt.setString(4, petImg);
            pStmt.setString(5, petMemo);
            pStmt.setString(6, petWalk);
            pStmt.setString(7, petMedi);
            pStmt.executeUpdate();
            System.out.println("반려묘 일지 등록이 완료되었습니다..");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    // 6. 일지 수정
    public void editPetDiary(String petDiary) {

        System.out.print("일지를 수정 할 반려묘 선택 : ");
        String petName = sc.next();
        System.out.printf("%s 의 일지를 수정합니다.\n", petName);
        System.out.print("사진 등록 (링크를 등록하거나 등록을 원하지 않으면 'N'를 입력해주세요) : ");
        String petImg = sc.next();
        System.out.print("기록 (등록을 원하지 않으면 'N'를 입력해주세요 : ");
        String petMemo = sc.nextLine();
        sc.nextLine();
        System.out.print("산책 여부 (Y/N): ");
        String petWalk = sc.next();
        System.out.print("약 복용 여부 (Y/N) : ");
        String petMedi = sc.next();

        String sql = "UPDATE PET_PAGE SET PET_IMG = ?, PET_MEMO = ?, PET_WALK = ?, PET_MEDI = ? WHERE PET_NAME = ? AND PET_DIARY = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, petImg);
            pStmt.setString(2, petMemo);
            pStmt.setString(3, petWalk);
            pStmt.setString(4, petMedi);
            pStmt.setString(5, petName);
            pStmt.setString(6, petDiary);
            pStmt.executeUpdate();
            System.out.println("반려묘 정보 수정이 완료되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    // 7. 일지 삭제
    public void DiaryDelete(String petDiary) {
        System.out.print("일지를 삭제 할 반려묘 선택 : ");
        String petName = sc.next();
        System.out.printf("%s 의 일지를 삭제합니다.\n", petName);

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