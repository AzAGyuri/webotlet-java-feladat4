package com.company;

import java.util.ArrayList;
import java.util.List;

public class Nevek {
    String vezeteknev;
    String elsoKeresztnev;
    String masodikKeresztnev;


    public Nevek(String sor) {
        String[] szeletek = sor.split(" ");
        this.vezeteknev = szeletek[0];
        this.elsoKeresztnev = szeletek[1];

        //ha nincs 2. keresztneve, akkor az " "-nak lesz eltárolva
        List<Character> karakterek = new ArrayList<>();
        int spacekSzama = 0;


        for (int i = 0; i < sor.length(); i++) {
            //a sor szövege karakterekké alakítva
            karakterek.add(sor.charAt(i));
        }

        for (int i = 0; i < sor.length(); i++) {
            //ha a sorban a karakterek között van " ", akkor nő a változó
            if (karakterek.get(i).equals(' ')) {
                spacekSzama++;
            }
        }


        if (spacekSzama > 1) {
            //ha több space van benne, vagyis több, mint 2 tagból áll a neve,
            //akkor tekinti egy keresztnevesnek és ad a második keresztnevének " " értéket
            this.masodikKeresztnev = szeletek[2];
        } else {
            masodikKeresztnev = "";
        }
    }

    @Override
    public String toString() {
        return "Nevek{" +
                "vezeteknev='" + vezeteknev + '\'' +
                ", elsoKeresztnev='" + elsoKeresztnev + '\'' +
                ", masodikKeresztnev='" + masodikKeresztnev + '\'' +
                '}';
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getElsoKeresztnev() {
        return elsoKeresztnev;
    }

    public void setElsoKeresztnev(String elsoKeresztnev) {
        this.elsoKeresztnev = elsoKeresztnev;
    }

    public String getMasodikKeresztnev() {
        return masodikKeresztnev;
    }

    public void setMasodikKeresztnev(String masodikKeresztnev) {
        this.masodikKeresztnev = masodikKeresztnev;
    }
}
