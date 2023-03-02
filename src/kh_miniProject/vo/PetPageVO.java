package kh_miniProject.vo;
import java.sql.Date;


public class PetPageVO {
    private String id;
    private String petName;
    private Date petDiary;
    private String petIMG;
    private String petWalk;
    private String  petMedi;

    public PetPageVO(String petName, Date petDiary, String petIMG, String petWalk, String petMedi) {
        this.petName = petName;
        this.petDiary = petDiary;
        this.petIMG = petIMG;
        this.petWalk = petWalk;
        this.petMedi = petMedi;
    }
    public PetPageVO(String id, String petName, Date petDiary, String petIMG, String petWalk, String petMedi) {
        this.id = id;
        this.petName = petName;
        this.petDiary = petDiary;
        this.petIMG = petIMG;
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

    public Date getPetDiary() {
        return petDiary;
    }

    public void setPetDiary(Date petDiary) {
        this.petDiary = petDiary;
    }

    public String getPetIMG() {
        return petIMG;
    }

    public void setPetIMG(String petIMG) {
        this.petIMG = petIMG;
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

