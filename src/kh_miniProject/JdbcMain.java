package kh_miniProject;
import kh_miniProject.dao.MemberDAO;
import kh_miniProject.dao.WriteDAO;
import kh_miniProject.vo.MemberVO;
import kh_miniProject.vo.WriteVO;
import java.util.List;
import java.util.Scanner;
public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    static MemberDAO mdao = new MemberDAO();
    static WriteDAO wdao = new WriteDAO();
    public static void main(String[] args) {
//        List<WriteVO> w = wdao.getPeriodSearchList();
//        Practice.wdao.preViewBodyTextList(w);
        System.out.println("[고양이 집사 커뮤니티]");
        int rst = JdbcMain.mainMenu();
        if(rst == 1) {
            List<MemberVO> mlist = JdbcMain.mdao.getLogInInfo();
            String loginUser = JdbcMain.mdao.login(mlist);
            JdbcMain.userMenu(loginUser);
        }
        else JdbcMain.adminMenu();
    }

    public static int mainMenu() {
        while(true) {
            System.out.print("[1] 회원가입 [2] 로그인 [3] 관리자 모드 [4] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    mdao.signUp();
                case 2 :
                    List<MemberVO> m = mdao.getLogInInfo();
                    JdbcMain.mdao.login(m);
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

    public static boolean userMenu(String id) {
        System.out.println("로그인이 완료되었습니다.");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 마이페이지 [2] 펫 페이지 [3] 게시판 보기 [4] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    JdbcMain.myPage(id);
                case 2 :
                    JdbcMain.petMenu(id);
                case 3 :
                    JdbcMain.boardMenu(id);
                case 4 :
                    System.exit(0);
            }
        }
    }

    public static boolean myPage(String id) {
        System.out.println("[" + id + "] 님의 마이 페이지");
        System.out.println("메뉴를 선택하세요.");
        while (true) {
            System.out.print("[1] 내 정보 [2] 정보 수정 [3] 내가 쓴 글 확인 [4] 내가 쓴 댓글 " +
                    "[5] 내가 쓴 글에 달린 댓글 [6] 이전 단계 [7] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    JdbcMain.myPostPage(id);
                case 4 :
                    break;
                case 5 :
                    break;
                case 6 :
                    JdbcMain.userMenu(id);
                case 7 :
                    System.exit(0);
            }
        }
    }

    public static boolean myPostPage(String id) {
        System.out.println("[" + id + "] 님의 작성글");
        List<WriteVO> list = wdao.getMyPostList(id);
        JdbcMain.wdao.viewPost(list);
        while(true) {
            System.out.print("[1] 제목 수정 [2] 본문 수정 [3] 삭제 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    wdao.editMyPostTitle(id);
                    break;
                case 2 :
                    wdao.editMyPostBodyText(id);
                    break;
                case 3 :
                    wdao.deleteMyPost(id);
                    break;
                case 4 :
                    JdbcMain.myPage(id);
            }
        }
    }

    public static boolean petMenu (String id) {
        System.out.println("[" + id + "] 님의 펫 페이지");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 반려묘 정보 조회 [2] 반려묘 추가 [3] 반려묘 정보 수정 [4] 이전 단계 [5] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    JdbcMain.petInfoInquire(id);
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    JdbcMain.userMenu(id);
                case 5 :
                    System.exit(0);
            }
        }
    }

    public static boolean petInfoInquire(String id) { // 반려묘 정보 조회
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
                    JdbcMain.petMenu(id);
            }
        }
    }

    public static boolean petInfoQuery(String id) { // 반려묘 정보 확인
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
                    JdbcMain.petInfoInquire(id);
            }
        }
    }

    public static boolean petDiary(String id) { // 반려묘 일지 확인
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
                    JdbcMain.petInfoQuery(id);
                case 4 :
                    System.exit(0);
            }
        }
    }

    public static boolean boardMenu(String id) {
        System.out.println("[게시판 보기]를 선택하셨습니다.");
        System.out.println("메뉴를 선택하세요.");
        while (true) {
            System.out.print("[1] 게시판 선택 [2] 글읽기 [3] 글쓰기 [4] 제목 검색 [5] 본문 검색 [6] 이전 단계 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<WriteVO> list = wdao.getSelectBoardList();
                    JdbcMain.wdao.viewPostList(list);
                    break;
                case 2 :

                    break;
                case 3 :
                    wdao.writePost(id);
                    break;
                case 4 :
                    List<WriteVO> list2 = wdao.getTitleSearchList();
                    JdbcMain.wdao.viewPostList(list2);
                    break;
                case 5 :
                    List<WriteVO> list3 = wdao.getBodyTextSearchList();
                    JdbcMain.wdao.preViewBodyTextList(list3);
                case 6 :
                    JdbcMain.userMenu(id);
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
                    JdbcMain.mainMenu();
            }
        }
    }
}
