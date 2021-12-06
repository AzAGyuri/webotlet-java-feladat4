package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Nevek> nevek = new ArrayList<>();

    public static void main(String[] args) {
        beolvasas("nevek.txt");
        feltoltes(beolvasas("nevek.txt"));
        kiiratas(); //nem tartozik a feladathoz, csak az embereket írja ki
        leghosszabbVezeteknev();
        ketKeresztneveVan();
        leghosszabbNevHanyadikHelyen();
        legtobbNBetuEgyNevben();
        vanEAzonosVezeteknevuEmber();
        vanEMindketAzonosKeresztnev();
        vanEBarmelyikAzonosKeresztnev();
    }

    private static void vanEBarmelyikAzonosKeresztnev() {
        System.out.println("");
        boolean vane = false;

        //ha bármelyik név megegyezik bármelyikkel, akkor lesz igaz
        for (int i = 0; i < nevek.size(); i++) {
            for (int j = 0; j < nevek.size(); j++) {
                if ((nevek.get(i).getElsoKeresztnev().equals(nevek.get(j).getElsoKeresztnev()) ||
                        nevek.get(i).getElsoKeresztnev().equals(nevek.get(j).getMasodikKeresztnev()) ||
                        nevek.get(i).getMasodikKeresztnev().equals(nevek.get(j).getElsoKeresztnev()) ||
                        nevek.get(i).getMasodikKeresztnev().equals(nevek.get(j).getMasodikKeresztnev())) &&
                        (i != j)) {
                    vane = true;
                }
            }
        }

        if (vane == true) {
            System.out.println("Vannak olyan emberek, akiknek megegyezik valamelyik keresztnevük");
        } else {
            System.out.println("Nincsenek olyan emberek, akiknek megegyezik valamelyik keresztnevük");
        }

    }

    private static void vanEMindketAzonosKeresztnev() {
        System.out.println("");
        boolean vane = false;

     //ha mind a 2 keresztneve megegyezik és nem ugyanazon a helyen szerepel a tömbben (nem ugyanaz az ember)
        for (int i = 0; i < nevek.size(); i++) {
            for (int j = 0; j < nevek.size(); j++) {
                if (nevek.get(i).masodikKeresztnev.concat(nevek.get(i).getMasodikKeresztnev()).equals(nevek.get(j).masodikKeresztnev.concat(nevek.get(j).getMasodikKeresztnev()))
                        && (i != j) && (!nevek.get(i).masodikKeresztnev.equals("") && !nevek.get(j).masodikKeresztnev.equals(""))) {
                    vane = true;
                }
            }
        }


        if (vane == true) {
            System.out.println("Vannak olyan emberek, akiknek mind a 2 keresztneve azonos");
        } else {
            System.out.println("Nincsenek olyan emberek, akiknek mind a 2 keresztneve azonos");
        }
    }

    private static void vanEAzonosVezeteknevuEmber() {
        System.out.println("");
        boolean vane = false;

        //ha a vezetéknevük megegyezik
        for (int i = 0; i < nevek.size(); i++) {
            for (int j = 0; j < nevek.size(); j++) {
                if (nevek.get(i).getVezeteknev().equals(nevek.get(j).getVezeteknev()) && (i != j)) {
                    vane = true;
                }
            }
        }
        if (vane == true) {
            System.out.println("Van azonos vezetéknevű ember a fájlban");
        } else {
            System.out.println("Nincs azonos vezetéknevű ember a fájlban");
        }
    }

    private static void legtobbNBetuEgyNevben() {
        System.out.println("");
        int nMennyiseg;
        int maxNBetuk = 0;
        String neve = "";
        List<Integer> nMennyisegTomb = new ArrayList<>();

        //ha a neveken átmenő ciklusban egy név hossza méretű ciklus
        for (Nevek nev : nevek) {
            nMennyiseg = 0; //a névben az n-hez tartozó érték minden névnél nullázódik
            for (int j = 0; j < nev.toString().length(); j++) {
                //ha a név karaktereinek valamelyikre 'n/N' betű, akkor növekszik az n mennyisége a névhez
                if (nev.toString().toLowerCase().charAt(j) == 'n') {
                    nMennyiseg++;
                }
            }
            //az adott névhez tatozó n mennyiségét egy tömbbe pakolja, ami akkora méretű, ahány név van
            nMennyisegTomb.add(nMennyiseg);
        }

        for (Integer tomb : nMennyisegTomb) {
            //megkeresi, hogy melyik a legnagyobb szám az n-nes tömbben és annak az értékét tárolja el
            if (tomb > maxNBetuk) {
                maxNBetuk = tomb;
            }
        }

        for (int i = 0; i < nevek.size(); i++) {
            //ha ugyanazon az indexen/helyen található a tömbben a név és a legnagyobb
            //n betűk száma, akkor az a név lesz a legtöbb n-hez tartozó
            for (int j = 0; j < nMennyisegTomb.size(); j++) {
                if (nMennyisegTomb.get(i) == maxNBetuk && (i == j)) {
                    neve = nevek.get(i).getVezeteknev() + " " + nevek.get(i).getElsoKeresztnev() + " " + nevek.get(i).getMasodikKeresztnev();
                }
            }
        }
        System.out.println("A legtöbb n betű " + neve + " nevében szerepel");

    }

    private static void kiiratas() {
        //kiírja a neveket egyessével
        for (Nevek nev : nevek) {
            System.out.println(nev);
        }

    }

    private static void leghosszabbNevHanyadikHelyen() {
        System.out.println("");
        int osszhossz = 0;
        int hossz = 0;
        int tempHossz = 0;
        String neve = "";
        int hanyadik = 0;

        for (Nevek nev : nevek) {
            //a nevek hossza
            osszhossz = nev.getVezeteknev().length() +
                    nev.getElsoKeresztnev().length() + nev.getMasodikKeresztnev().length();
            if (osszhossz > hossz) {
                //leghosszabb név mérete
                hossz = osszhossz;
            }
        }

        for (Nevek nev : nevek) {
            tempHossz = nev.getVezeteknev().length() +
                    nev.getElsoKeresztnev().length() + nev.getMasodikKeresztnev().length();
            if (hossz == tempHossz) {
                //leghosszabb név méretével egyezik-e valamelyik név mérete
                neve = nev.getVezeteknev() + " " + nev.getElsoKeresztnev() +
                        " " + nev.getMasodikKeresztnev();
            }
        }

        for (int i = 0; i < nevek.size(); i++) {
            String helyNev = nevek.get(i).getVezeteknev() + " " +
                    nevek.get(i).getElsoKeresztnev() + " " + nevek.get(i).masodikKeresztnev;
            if (helyNev.equals(neve)) {
                hanyadik = i + 1;
            }
        }

        System.out.println(neve + "-nak a leghosszabb a neve és ő a " + hanyadik + ". helyen van");
    }

    private static void ketKeresztneveVan() {
        System.out.println("");
        System.out.println("2 keresztneve van: ");
        String vanKetKeresztneve = "";
        for (Nevek nev : nevek) {
            //ha nem "" vagy " " az értéke a második keresztnevének, vagyis van neki
            //akkor azt az embert úgy tekinti, hogy van 2 keresztneve
            //hatásosabb megoldás Listtel, mint concattal, de ez is megfelel
            if (!nev.getMasodikKeresztnev().isBlank()) {
                vanKetKeresztneve = vanKetKeresztneve.concat(nev.getVezeteknev() + " " +
                        nev.getElsoKeresztnev() + " " + nev.getMasodikKeresztnev() + ", ");
            }
        }
        System.out.println(vanKetKeresztneve);
    }

    private static void leghosszabbVezeteknev() {
        System.out.println("");
        int hossz = 0;
        String neve = "";
        for (Nevek nev : nevek) {
            //maximum kiválasztás tétele
            if (nev.getVezeteknev().length() > hossz) {
                hossz = nev.getVezeteknev().length();
                neve = nev.getVezeteknev() + " " + nev.elsoKeresztnev + " " + nev.getMasodikKeresztnev();
            }
        }
        System.out.println("");
        System.out.println(neve + "-nak van a leghosszabb vezetékneve");
    }

    private static void feltoltes(List<String> sorok) {
    //az Objektum lista feltöltése az adatokkal
        for (String sor : sorok) {
            nevek.add(new Nevek(sor));
        }
    }

    private static List<String> beolvasas(String uri) {
        List<String> sor = new ArrayList<>();
        //a fájlból való beolvasás és egy listában való eltárolás
        //ebből a listából töltjük majd fel az objektum listánkat

        try {
            sor = Files.readAllLines(Paths.get(uri), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Hiba a beolvasásnál: " + e.getMessage());
        }
        return sor;
    }


}
