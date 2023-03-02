package kh_miniProject;
import kh_miniProject.dao.MemberDAO;
import kh_miniProject.dao.ReplyDAO;
import kh_miniProject.dao.WriteDAO;
import kh_miniProject.vo.MemberVO;
import kh_miniProject.vo.WriteVO;

import java.net.IDN;
import java.util.List;
import java.util.Scanner;
public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    static MemberDAO mdao = new MemberDAO();
    static WriteDAO wdao = new WriteDAO();
    static ReplyDAO rdao = new ReplyDAO();
    public static void main(String[] args) {

        System.out.println("[고양이 집사 커뮤니티]");
        int rst = mainMenu();
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
                    mdao.login(m);
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
        System.out.println("----------------------------------------------------------------------------");
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

    public static boolean editMyInfo(String id) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[" + id + "] 님의 정보 수정");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 이메일 변경 [2] 비밀번호 변경 [3] 아이디 변경 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    JdbcMain.myPage(id);
            }
        }
    }

    public static boolean myPostPage(String id) {
        System.out.println("----------------------------------------------------------------------------");
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

    public static boolean petMenu (String id) { // 펫 페이지!!!
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[" + id + "] 님의 펫 페이지");
        while(true) {
            System.out.print("[1] 반려묘 조회 [2] 반려묘 추가 등록 [3] 반려묘 정보 수정 [4] 이전 단계 [5] 종료 : ");
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

    public static boolean petInfoInquire(String id) { // 반려묘 조회
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[반려묘 조회]를 선택하셨습니다.");
        while(true) {
            System.out.print("[1] 전체 정보 확인 [2] 선택 조회 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    JdbcMain.petAllInfo(id);
                case 2 :
                    JdbcMain.petSelectInfo(id);
                case 3 :
                    JdbcMain.petMenu(id);
            }
        }
    }

    public static boolean petAllInfo(String id) { // 반려묘 전체 정보
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[전체 정보 확인]을 선택하셨습니다.");
        while(true) {
            System.out.print("[1] 반려묘 전체 정보 [2] 반려묘 전체 일지 [3] 선택 조회 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    JdbcMain.petSelectInfo(id);
                case 4 :
                    JdbcMain.petInfoInquire(id);
            }
        }
    }

    public static boolean petSelectInfo(String id) { // 반려묘 선택 조회
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[선택 조회]를 선택하셨습니다.");
        while(true) {
            System.out.print("[1] 반려묘 별 정보 확인 [2] 반려묘 별 일지 확인 [3] 날짜별 일지 확인 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    JdbcMain.petDailyDiary(id);
                case 3 :
                    JdbcMain.petDailyDiary(id);
                case 4 :
                    JdbcMain.petAllInfo(id);
            }
        }
    }

    public static boolean petDailyDiary(String id) { // 반려묘 별 일지 확인
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[날짜별 일지 확인]을 선택하셨습니다.");
        while(true) {
            System.out.print("[1] 날짜 선택 [2] 일지 추가 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    JdbcMain.editPetDiary(id);
                case 2 :
                    break;
                case 3:
                    JdbcMain.petSelectInfo(id);
            }
        }

    }

    public static boolean editPetDiary(String id) { // 날짜 선택
        System.out.println("----------------------------------------------------------------------------");
        while(true) {
            System.out.print("[1] 수정 [2] 삭제 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    break;
                case 3 :
                    JdbcMain.petDailyDiary(id);
            }
        }
    }

    public static boolean boardMenu(String id) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[게시판 보기]를 선택하셨습니다.");
        System.out.println("메뉴를 선택하세요.");
        while (true) {
            System.out.print("[1] 게시판 선택 [2] 글읽기 [3] 글쓰기 [4] 검색 [5] 이전 단계 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<WriteVO> list = wdao.getSelectBoardList();
                    JdbcMain.wdao.viewPostList(list);
                    break;
                case 2 :
                    List<WriteVO> list2 = wdao.getEntireList();
                    JdbcMain.wdao.viewPostList(list2);
                    int num = wdao.selectBoardNum();
                    List<WriteVO> list3 = wdao.getSelectPost(num);
                    List<WriteVO> list4 = wdao.getPostExtractReply(num);
                    if(list3 != null) {
                        JdbcMain.wdao.viewSelectPost(list3);
                        JdbcMain.wdao.viewReply(list3);
                        JdbcMain.replyMenu(id);
                    } else {
                        JdbcMain.wdao.viewPost(list4);
                    }
                    break;
                case 3 :
                    wdao.writePost(id);
                    break;
                case 4 :
                    JdbcMain.boardSearchMenu(id);
                case 5 :
                    JdbcMain.userMenu(id);
            }
        }
    }

    public static boolean replyMenu(String id) {
        System.out.println("----------------------------------------------------------------------------");
        while (true) {
            System.out.print("[1] 댓글 작성 [2] 이전 단계 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    rdao.rplWrite(id);
                case 2:
                    JdbcMain.boardMenu(id);
            }
        }
    }

    public static boolean boardSearchMenu(String id) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[검색]을 선택하셨습니다.");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 제목 검색 [2] 본문 검색 [3] 특정 기간 검색 [4] 작성자 검색 [5] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    List<WriteVO> list2 = wdao.getTitleSearchList();
                    JdbcMain.wdao.viewPostList(list2);
                    break;
                case 2 :
                    List<WriteVO> list3 = wdao.getBodyTextSearchList();
                    JdbcMain.wdao.preViewBodyTextList(list3);
                    break;
                case 3 :
                    List<WriteVO> list4 = wdao.getPeriodSearchList();
                    JdbcMain.wdao.preViewBodyTextList(list4);
                    break;
                case 4 :
                    List<WriteVO> list5 = wdao.getIdSearchList();
                    JdbcMain.wdao.preViewBodyTextList(list5);
                    break;
                case 5 :
                    JdbcMain.boardMenu(id);
            }
        }
    }

    public static boolean adminMenu() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[관리자 페이지]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 전체 회원정보 [2] 특정 회원정보 조회 [3] 특정 날짜 가입 인원 조회 [4] 회원 삭제 [5] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    break;
                case 2 :
                    JdbcMain.adminSelectMember();
                case 3 :
                    break;
                case 4 :
                    break;
                case 5 :
                    JdbcMain.mainMenu();
            }
        }
    }

    public static boolean adminSelectMember() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[특정 회원 정보 조회]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 특정 회원 전체 정보 [2] 특정 회원 가입일 [3] 특정 회원 아이디 [4] 특정 회원 비밀번호 " +
                    "[5] 이전 단계 : ");
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
                    JdbcMain.adminMenu();
            }
        }
    }
}
