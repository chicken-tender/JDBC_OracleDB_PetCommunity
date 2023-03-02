package kh_miniProject.dao;
import kh_miniProject.util.Common;
import kh_miniProject.vo.MemberVO;
import kh_miniProject.vo.PetVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    // 1. 반려묘 정보 등록
    public void enrollPet(String userId) {
        System.out.println("추가할 반려묘의 정보를 입력하세요.");
        System.out.print("이름 : ");
        String petName = sc.next();
        System.out.print("성별 (F/M) : ");
        String petGender = sc.next();
        System.out.print("생년월일 (yyyy/mm/dd) : ");
        String petBday = sc.next();
        System.out.print("종류 : ");
        String petSpec = sc.next();

        String sql = "INSERT INTO PET VALUES(?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, petName);
            pStmt.setString(2, userId);
            pStmt.setString(3, petGender);
            pStmt.setString(4, petBday);
            pStmt.setString(5, petSpec);
            int ret = pStmt.executeUpdate();
            System.out.println("반려묘 등록이 완료되었습니다..");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    // 2. 반려묘 정보 수정
    public void editPetInfo() {

        System.out.print("정보를 변경 할 반려묘 이름 입력 : ");
        String petName = sc.next();
        System.out.print("변경 할 성별 (F/M) : ");
        String petGender = sc.next();
        System.out.print("변경 할 생년월일 (yyyy/mm/dd) : ");
        String petBday = sc.next();
        System.out.print("변경 할 종류 : ");
        String petSpec = sc.next();

        String sql = "UPDATE PET SET PET_NAME = ?, PET_GENDER = ?, PET_BDAY = ?, PET_SPEC = ? WHERE PET_NAME = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, petName);
            pStmt.setString(2, petGender);
            pStmt.setString(3, petBday);
            pStmt.setString(4, petSpec);
            pStmt.setString(5, petName);
            pStmt.executeUpdate();
            System.out.println("반려묘 정보 수정이 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    // 3. 반려묘 정보 삭제
    public void PetDelete() {
        System.out.println("삭제를 원하는 반려묘 이름 입력 : ");
        String petName = sc.next();
        String sql = " DELETE FROM PET WHERE PET_NAME = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, petName);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    // 4. 전체 반려묘 정보 조회
    public List<PetVO> petAll(String userId) {
        List<PetVO> list = new ArrayList<>();

        String sql = "SELECT *" +
                "FROM PET" +
                "WHERE USER_ID = ?" +
                "ORDER BY PET_BDAY ASC, PET_GENDER DESC";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                String petGender = rs.getString("PET_GENDER");
                String petBday = rs.getString("PET_BDAY");
                String petSpec = rs.getString("PET_SPEC");
                PetVO vo = new PetVO(petName, petGender, petBday, petSpec);
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
    public void petAllPrint(List<PetVO> list) {
        for (PetVO e : list) {
            System.out.printf("고양이 이름 : %s 고양이 생일 : %s  ", e.getPetName(), e.getPetBday());
            System.out.println("--------------------------");
        }
    }

    // 5. 반려묘 별 정보 조회
    public List<PetVO> petSelect(String userId) {
        List<PetVO> list = new ArrayList<>();

        System.out.print("정보를 조회할 반려묘 이름 입력 : ");
        String pet = sc.next();

        String sql = "SELECT *" +
                "FROM PET" +
                "WHERE PET_NAME = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pet);
            rs = pStmt.executeQuery(sql);

            while (rs.next()) {
                String petName = rs.getString("PET_NAME");
                String petGender = rs.getString("PET_GENDER");
                String petBday = rs.getString("PET_BDAY");
                String petSpec = rs.getString("PET_SPEC");
                PetVO vo = new PetVO(petName, petGender, petBday, petSpec);
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

    public void petSelectPrint(List<PetVO> list) {
        for (PetVO e : list) {
            System.out.println("고양이 이름 : " + e.getPetName());
            System.out.println("고양이 성별 : " + e.getPetGender());
            System.out.println("고양이 생일 : " + e.getPetBday());
            System.out.println("고양이 종류 : " + e.getPetSpec());
            System.out.println("--------------------------");
        }
    }

}
