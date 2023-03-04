package kh_miniProject.vo;

public class PetPageVO {
    private String id;
    private String petName;
    private String petDiary;
    private String petIMG;
    private String petMemo;
    private String petWalk;
    private String  petMedi;

    public PetPageVO(String petName, String petDiary, String petWalk, String petMedi) {
        this.petName = petName;
        this.petDiary = petDiary;
        this.petWalk = petWalk;
        this.petMedi = petMedi;
    }

    public PetPageVO(String petDiary, String petIMG, String petMemo, String petWalk, String petMedi) {
        this.petDiary = petDiary;
        this.petIMG = petIMG;
        this.petMemo = petMemo;
        this.petWalk = petWalk;
        this.petMedi = petMedi;
    }

    public PetPageVO(String petName, String petDiary, String petIMG, String petMemo, String petWalk, String petMedi) {
        this.petName = petName;
        this.petDiary = petDiary;
        this.petIMG = petIMG;
        this.petMemo = petMemo;
        this.petWalk = petWalk;
        this.petMedi = petMedi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetDiary() {
        return petDiary;
    }

    public void setPetDiary(String petDiary) {
        this.petDiary = petDiary;
    }

    public String getPetIMG() {
        return petIMG;
    }

    public void setPetIMG(String petIMG) {
        this.petIMG = petIMG;
    }

    public String getPetMemo() {
        return petMemo;
    }

    public void setPetMemo(String petMemo) {
        this.petMemo = petMemo;
    }

    public String getPetWalk() {
        return petWalk;
    }

    public void setPetWalk(String petWalk) {
        this.petWalk = petWalk;
    }

    public String getPetMedi() {
        return petMedi;
    }

    public void setPetMedi(String petMedi) {
        this.petMedi = petMedi;
    }
}