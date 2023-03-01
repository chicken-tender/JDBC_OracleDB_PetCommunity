package kh_miniProject.vo;

public class ReplyVO {
    private int replyNum;
    private String replyWrite;
    private String id;
    private int boardNum;

    public ReplyVO() { }

    public ReplyVO(int replyNum, String replyWrite, String id, int boardNum) {
        this.replyWrite = replyWrite;
        this.replyNum = replyNum;
        this.id = id;
        this.boardNum = boardNum;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public String getReplyWrite() {
        return replyWrite;
    }

    public void setReplyWrite(String replyWrite) {
        this.replyWrite = replyWrite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }
}