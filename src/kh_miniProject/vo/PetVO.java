package kh_miniProject.vo;

public class PetVO {
    private String petName;
    private String id;
    private String petGender;
    private String petBday;
    private String petSpec;

    public PetVO(String petName, String petBday) {
        this.petName = petName;
        this.petBday = petBday;

    }
    public PetVO(String petName, String id, String petGender, String petBday, String petSpec) {
        this.petName = petName;
        this.id = id;
        this.petGender = petGender;
        this.petBday = petBday;
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

    public String getPetBday() {
        return petBday;
    }

    public void setPetBday(String petBday) {
        this.petBday = petBday;
    }

    public String getPetSpec() {
        return petSpec;
    }

    public void setPetSpec(String petSpec) {
        this.petSpec = petSpec;
    }
}