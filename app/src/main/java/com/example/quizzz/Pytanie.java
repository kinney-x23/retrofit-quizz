package com.example.quizzz;
import com.google.gson.annotations.SerializedName;

public class Pytanie {

    @SerializedName("tresc")
    private String trescPytania;
    @SerializedName("odp_a")
    private String odpA;
    @SerializedName("odp_b")
    private String odpB;
    @SerializedName("odp_c")
    private String odpC;
    private int poprawna;

    public String getTrescPytania() {
        return trescPytania;
    }

    public void setTrescPytania(String trescPytania) {
        this.trescPytania = trescPytania;
    }

    public String getOdpA() {
        return odpA;
    }

    public void setOdpA(String odpA) {
        this.odpA = odpA;
    }

    public String getOdpB() {
        return odpB;
    }
    public void setOdpB(String odpB) {
        this.odpB = odpB;
    }

    public void setOdpC(String odpC) {
        this.odpC = odpC;
    }
    public String getOdpC() {
        return odpC;
    }

    public int getPoprawna() {
        return poprawna;
    }

    public void setPoprawna(int poprawna) {
        this.poprawna = poprawna;
    }

    public Pytanie(String trescPytania, String odpA, String odpC, int poprawna) {
        this.trescPytania = trescPytania;
        this.odpA = odpA;
        this.odpC = odpC;
        this.poprawna = poprawna;
    }
}
