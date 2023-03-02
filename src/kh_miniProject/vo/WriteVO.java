package kh_miniProject.vo;
import java.sql.Date;

public class WriteVO {
    private int boardNum;
    private String boardName;
    private String title;
    private String bodyText;
    private Date regDate;
    private String id;
    private int rBoardNum;
    private String rUserId;
    private String replyWrite;
    private int replyNum;

    public WriteVO(int boardNum, String id, String title, String bodyText, String rUserId, String replyWrite, int replyNum) {
        this.boardNum = boardNum;
        this.title = title;
        this.bodyText = bodyText;
        this.id = id;
        this.rBoardNum = rBoardNum;
        this.rUserId = rUserId;
        this.replyWrite = replyWrite;
        this.replyNum = replyNum;
    }

    public WriteVO(int boardNum, String boardName, String title, String bodyText, Date regDate, String id) {
        this.boardNum = boardNum;
        this.boardName = boardName;
        this.title = title;
        this.bodyText = bodyText;
        this.regDate = regDate;
        this.id = id;
    }

    public WriteVO(int boardNum, String boardName, String id, String title, Date regDate) {
        this.boardNum = boardNum;
        this.boardName = boardName;
        this.title = title;
        this.regDate = regDate;
        this.id = id;
    }

    public WriteVO(int boardNum, String boardName, String title, Date regDate, String bodyText) {
        this.boardNum = boardNum;
        this.boardName = boardName;
        this.title = title;
        this.bodyText = bodyText;
        this.regDate = regDate;
    }

    public WriteVO(int boardNum, String id, String title, String bodyText) {
        this.boardNum = boardNum;
        this.id = id;
        this.title = title;
        this.bodyText = bodyText;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getrBoardNum() {
        return rBoardNum;
    }

    public void setrBoardNum(int rBoardNum) {
        this.rBoardNum = rBoardNum;
    }

    public String getrUserId() {
        return rUserId;
    }

    public void setrUserId(String rUserId) {
        this.rUserId = rUserId;
    }

    public String getReplyWrite() {
        return replyWrite;
    }

    public void setReplyWrite(String replyWrite) {
        this.replyWrite = replyWrite;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
