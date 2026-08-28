package com.masa.recepti;

public class Recept {
    private String nazivRecepta;
    private int trajanjePripreme;
    private boolean omiljeno;

    public Recept(String nazivRecepta, int trajanjePripreme, boolean omiljeno) {
        this.nazivRecepta = nazivRecepta;
        this.trajanjePripreme = trajanjePripreme;
        this.omiljeno = omiljeno;
    }

    public String getNazivRecepta() {
        return nazivRecepta;
    }

    public int getTrajanjePripreme() {
        return trajanjePripreme;
    }

    public boolean isOmiljeno() {
        return omiljeno;
    }
}
