package edu.cecar.modelo;

/**
 *  Clase que modeloa un objeto de tipo Example.
 **/

public class Examples{
    private String dateOfStop;
    private String fatal;
    private String alcohol;
    private String gender;

    public Examples(String dateOfStop, String fatal, String alcohol, String gender) {
        this.dateOfStop = dateOfStop;
        this.fatal = fatal;
        this.alcohol = alcohol;
        this.gender = gender;
    }

    public String getDateOfStop() {
        return dateOfStop;
    }

    public void setDateOfStop(String dateOfStop) {
        this.dateOfStop = dateOfStop;
    }

    public String getFatal() {
        return fatal;
    }

    public void setFatal(String fatal) {
        this.fatal = fatal;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
