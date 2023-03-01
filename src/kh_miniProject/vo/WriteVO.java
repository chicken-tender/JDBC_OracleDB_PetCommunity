package kh_miniProject.vo;
import java.sql.Date;

public class WriteVO {
    private int boardNum;
    private String boardName;
    private String title;
    private String bodyText;
    private Date regDate;
    private String id;

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
