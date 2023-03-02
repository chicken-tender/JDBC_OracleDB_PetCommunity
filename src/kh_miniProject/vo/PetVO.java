package kh_miniProject.vo;
import java.sql.Date;

public class PetVO {
    private String petName;
    private String id;
    private String petGender;
    private Date petBday;
    private String petSpec;

    public PetVO(String petName, String id, String petGender, Date petBday, String petSpec) {
        this.petName = petName;
        this.id = id;
        this.petGender = petGender;
        this.petBday = petBday;
        this.petSpec = petSpec;
    }
    public PetVO(String petName, String id, String petGender, String petSpec) {
        this.petName = petName;
        this.id = id;
        this.petGender = petGender;
        this.petSpec = petSpec;
    }


    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public Date getPetBday() {
        return petBday;
    }

    public void setPetBday(Date petBday) {
        this.petBday = petBday;
    }

    public String getPetSpec() {
        return petSpec;
    }

    public void setPetSpec(String petSpec) {
        this.petSpec = petSpec;
    }
}


