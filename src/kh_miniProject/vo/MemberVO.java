package kh_miniProject.vo;

public class MemberVO {
    private String id;
    private String pw;
    private String name;
    private String mail;
    private String phone;

    public MemberVO(String id, String pw, String name, String mail, String phone) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}