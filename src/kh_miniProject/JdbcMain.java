package kh_miniProject;
import kh_miniProject.dao.MemberDAO;
import java.util.Scanner;

public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    static MemberDAO dao = new MemberDAO();
    public static void main(String[] args) {
        System.out.println("[고양이 집사 커뮤니티]");
        int rst = kh_miniProject.JdbcMain.mainMenu();
        if(rst == 1) kh_miniProject.JdbcMain.userMenu();
        else kh_miniProject.JdbcMain.adminMenu();
    }

    public static int mainMenu() {
        System.out.println("로그인이 필요합니다.");
        while(true) {
            System.out.print("[1] 회원가입 [2] 로그인 [3] 관리자 모드 [4] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    dao.signUp();
                case 2 :
                    return 1;
                case 3 :
                    System.out.print("비밀번호를 입력하세요. : ");
                    String admin = sc.next();
                    if(admin.equals("admin")) return 2;
                case 4 :
                    System.exit(0);
            }
        }
    }

    public static boolean userMenu() {
        System.out.println("환영합니다. 메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 마이페이지 [2] 펫 페이지 [3] 게시판 보기 [4] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    kh_miniProject.JdbcMain.myPage();
                case 2 :
                    kh_miniProject.JdbcMain.petMenu();
                case 3 :
                    kh_miniProject.JdbcMain.boardMenu();
                case 4 :
                    System.exit(0);
            }
        }
    }

    public static boolean myPage() {
        System.out.println("[닉네임] 님의 마이 페이지"); // [닉네임] 코드 수정 예정
        System.out.println("메뉴를 선택하세요.");
        while (true) {
            System.out.print("[1] 내 정보 [2] 정보 수정 [3] 내가 쓴 글 확인 [4] 내가 쓴 댓글 확인 " +
                    "[5] 내가 쓴 글에 달린 댓글 확인 [6] 이전 단계 [7] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    break;
                case 5 :
                    break;
                case 6 :
                    kh_miniProject.JdbcMain.userMenu();
                case 7 :
                    System.exit(0);
            }
        }
    }

    public static boolean petMenu () {
        System.out.println("[닉네임] 님의 펫 페이지"); // 닉네임 부분은 추후 수정 예정
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 반려묘 정보 조회 [2] 반려묘 추가 [3] 반려묘 정보 수정 [4] 이전 단계 [5] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    kh_miniProject.JdbcMain.petInfoInquire();
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    kh_miniProject.JdbcMain.userMenu();
                case 5 :
                    System.exit(0);
            }
        }
    }

    public static boolean petInfoInquire() { // 반려묘 정보 조회
        System.out.println("[반려묘 정보 조회]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 반려묘 정보 확인 [2] 반려묘 일지 확인 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    kh_miniProject.JdbcMain.petMenu();
            }
        }
    }

    public static boolean petInfoQuery() { // 반려묘 정보 확인
        System.out.println("[반려묘 정보 확인]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 조회할 반려묘 이름 입력 [2] 정보 수정 [3] 삭제 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    kh_miniProject.JdbcMain.petInfoInquire();
            }
        }
    }

    public static boolean petDiary() { // 반려묘 일지 확인
        System.out.println("[반려묘 일지 확인]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 날짜 선택 [2] 일지 추가 [3] 이전 단계 [4] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    kh_miniProject.JdbcMain.petInfoQuery();
                case 4 :
                    System.exit(0);
            }
        }
    }

    public static boolean boardMenu() {
        System.out.println("[게시판 보기]를 선택하셨습니다.");
        System.out.println("메뉴를 선택하세요.");
        while (true) {
            System.out.print("[1] 게시판 선택 [2] 게시글 작성 [3] 게시글 조회 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    kh_miniProject.JdbcMain.userMenu();
            }
        }
    }

    public static boolean adminMenu() {
        System.out.println("[관리자 페이지]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 전체 회원정보 조회 [2] 특정 회원정보 조회 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    kh_miniProject.JdbcMain.mainMenu();
            }
        }
    }
}
