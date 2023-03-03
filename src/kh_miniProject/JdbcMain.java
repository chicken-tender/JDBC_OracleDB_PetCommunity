package kh_miniProject;
import kh_miniProject.dao.*;
import kh_miniProject.vo.*;

import java.net.IDN;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    static MemberDAO mdao = new MemberDAO();
    static WriteDAO wdao = new WriteDAO();
    static ReplyDAO rdao = new ReplyDAO();
    static PetDAO pdao = new PetDAO();
    static PetPageDAO ppdao = new PetPageDAO();
    static String UserId;
    static String petName;
    static String petDiary;
    public static void main(String[] args) {

        System.out.println("[고양이 집사 커뮤니티]");
        int rst = mainMenu();
        if(rst == 1) {
            List<MemberVO> mlist = mdao.getLogInInfo();
            String loginUser = mdao.login(mlist);
            userMenu(loginUser);
        }
        else adminMenu();
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
                    myPage(id);
                case 2 :
                    petMenu(id);
                case 3 :
                    boardMenu(id);
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
                    List<MemberVO> list = mdao.memMY();
                    int petCnt = mdao.rplSelect(id);
                    mdao.memMYPrint(list, petCnt);
                    break;
                case 2 :
                    editMyInfo(id);
                    break;
                case 3 :
                    myPostPage(id);
                case 4 :
                    List<Map<String,String>> list1 = rdao.rplSelect(id);
                    rdao.rplSelectPrint(list1);
                    break;
                case 5 :
                    List<ReplyVO> list2 = rdao.rplMyWrite(id);
                    if(list2 == null) {
                        System.out.println(rdao.rplMyWritePrint());
                    }
                    break;
                case 6 :
                    userMenu(id);
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
            System.out.print("[1] 이메일 변경 [2] 비밀번호 변경 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    mdao.emailUpdate(id);
                    break;
                case 2 :
                    mdao.pwUpdate(id);
                    break;
                case 3 :
<<<<<<< HEAD
                    myPage(id);
=======
                    mdao.idUpdate(id);
                    break;
                case 4 :
                    JdbcMain.myPage(id);
>>>>>>> 837d5fe39301dfdac3b3b0e7a539a1315f1fd10b
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
                    myPage(id);
            }
        }
    }

    public static boolean petMenu (String id) { // 펫 페이지!!!
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[" + id + "] 님의 펫 페이지");
        while(true) {
            System.out.print("[1] 반려묘 조회 [2] 반려묘 추가 등록 [3] 반려묘 정보 수정 [4] 반려묘 정보 삭제 [5] 이전 단계 [6] 종료 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    petInfoInquire(id);
                case 2 :
                    pdao.enrollPet(id);
                    break;
                case 3 :
                    pdao.editPetInfo(petName);
                    break;
                case 4 :
                    pdao.PetDelete();
                case 5 :
                    userMenu(id);
                case 6 :
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
                    petAllInfo(id);
                case 2 :
                    petSelectInfo(id);
                case 3 :
                    petMenu(id);
            }
        }
    }

//    public static boolean editpetInfo(String id) {
//        System.out.println("[반려묘 정보 수정]을 선택하셨습니다.");
//        System.out.println("변경을 원하는 반려묘 이름을 입력하세요 : ");
//        String etName = sc.next();
//        while(true) {
//            System.out.print("[1] 이름 변경  [2] 성별 변경 [3] 생년월일 변경 [4] 종 변경  [5] 이전 단계로: ");
//            int sel = sc.nextInt();
//            switch(sel) {
//                case 1 :
//                    break;
//                case 2 :
//                    break;
//                case 3 :
//                    break;
//                case 4 :
//                    break;
//                case 5 :
//                    JdbcMain.petMenu(id);
//            }
//        }
//    }

    /* 3/3 수민 - 수정 필요 */
    public static boolean petAllInfo(String id) { // 반려묘 전체 정보
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[전체 정보 확인]을 선택하셨습니다.");
        while(true) {
            System.out.print("[1] 전체 반려묘 정보 [2] 전체 반려묘 일지 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    List<PetVO> list1 = pdao.petAll(id);
                    pdao.petAllPrint(list1);
                    break;
                case 2 :
                    List<PetPageVO> list2 = ppdao.petDiaryAll(id);
                    ppdao.petDiaryAllPrint(list2);
                    break;
                case 3 :
                    petInfoInquire(id);
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
                    pdao.petSelect(id);
                    break;
                case 2 :
                    List<PetPageVO> list = ppdao.petSelect(id);
                    ppdao.petSelectPrint(list);
                case 3 :
                    petDailyDiary(id);
                case 4 :
                    petAllInfo(id);
            }
        }
    }
    public static boolean petSelectDiary(String id) { // 반려묘 별 일지 확인
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[날짜별 일지 확인]을 선택하셨습니다.");
        while(true) {
            System.out.print("[1] 날짜 선택 [2] 일지 추가 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    editPetDiary(id);
                case 2 :
                    ppdao.uploadPetDiary(id);
                    break;
                case 3:
                    petSelectInfo(id);
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
                    List<PetPageVO> list = ppdao.petDiarySelectName(id);
                    ppdao.petDiarySelectNamePrint(list);
                    editPetDiary(id);
                case 2 :
                    ppdao.uploadPetDiary(id);
                    break;
                case 3:
                    petSelectInfo(id);
            }
        }

    }

    public static boolean editPetDiary(String id) { // 날짜 선택
        System.out.println("----------------------------------------------------------------------------");
        List<PetPageVO> list = ppdao.petDiarySelect(id);
        ppdao.petDiarySelectPrint(list);

        ppdao.petDiarySelectName(id);

        while(true) {
            System.out.print("[1] 수정 [2] 삭제 [3] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    ppdao.editPetDiary();
                    break;
                case 2 :
                    ppdao.DiaryDelete(petName, petDiary);
                    break;
                case 3 :
                    petDailyDiary(id);
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
                    wdao.viewPostList(list);
                    break;
                case 2 :
                    List<WriteVO> list2 = wdao.getEntireList();
                    wdao.viewPostList(list2);
                    int num = wdao.selectBoardNum();
                    List<WriteVO> list3 = wdao.getSelectPost(num);
                    List<WriteVO> list4 = wdao.getPostExtractReply(num);
                    if(list3 != null) {
                        wdao.viewSelectPost(list3);
                        wdao.viewReply(list3);
                        replyMenu(id);
                    } else {
                        wdao.viewPost(list4);
                        replyMenu(id);
                    }
                    break;
                case 3 :
                    wdao.writePost(id);
                    break;
                case 4 :
                    boardSearchMenu(id);
                case 5 :
                    userMenu(id);
            }
        }
    }

    public static boolean replyMenu(String id) {
        System.out.println("----------------------------------------------------------------------------");
        while (true) {
            System.out.print("[1] 댓글 작성 [2] 댓글 수정 [3] 댓글 삭제 [4] 이전 단계 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    rdao.rplWrite(id);
                    break;
                case 2:
                    rdao.editReply(id);
                    break;
                case 3:
                    rdao.rplDelete();
                    break;
                case 4:
                    boardMenu(id);
                    break;
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
                    wdao.viewPostList(list2);
                    break;
                case 2 :
                    List<WriteVO> list3 = wdao.getBodyTextSearchList();
                    wdao.preViewBodyTextList(list3);
                    break;
                case 3 :
                    List<WriteVO> list4 = wdao.getPeriodSearchList();
                    wdao.preViewBodyTextList(list4);
                    break;
                case 4 :
                    List<WriteVO> list5 = wdao.getIdSearchList();
                    wdao.preViewBodyTextList(list5);
                    break;
                case 5 :
                    boardMenu(id);
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
                    List<MemberVO> list = mdao.memAll();
                    mdao.memAllPrint(list);
                    break;
                case 2 :
                    adminSelectMember();
                case 3 :
                    int cnt = mdao.specificJoinDateCount();
                    mdao.specificJoinDateCountPrint(cnt);
                    break;
                case 4 :
                    mdao.memDelete();
                    break;
                case 5 :
                    mainMenu();
            }
        }
    }

    public static boolean adminSelectMember() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("[특정 회원 정보 조회]");
        System.out.println("메뉴를 선택하세요.");
        while(true) {
            System.out.print("[1] 특정 회원 전체 정보 [2] 특정 회원 가입일 [3] 특정 회원 아이디 [4] 특정 회원 비밀번호 " +
                    "[5] 특정 회원 댓글 조회 [6] 이전 단계 : ");
            int sel = sc.nextInt();
            switch(sel) {
                case 1 :
                    List<MemberVO> list = mdao.memSpecific();
                    mdao.memSpecificPrint(list);
                    break;
                case 2 :
                    List<MemberVO> list1 = mdao.memJoinDateSpecific();
                    mdao.memJoinDateSpecificPrint(list1);
                    break;
                case 3 :
                    List<MemberVO> list2 = mdao.memIDSpecific();
                    mdao.memIDSpecificPrint(list2);
                    break;
                case 4 :
                    List<MemberVO> list3 = mdao.memPwSpecific();
                    mdao.memPwSpecificPrint(list3);
                    break;
                case 5 :
                    List<ReplyVO> list4 = rdao.rplUserWrite();
                    rdao.rplUserWritePrint(list4);
                case 6 :
                    adminMenu();
            }
        }
    }
}
