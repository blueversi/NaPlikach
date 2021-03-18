package ppj_p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class S18311_p03 {

	public static void main(String[] args) {
		
		int liczbaRekordow = 0;
		try {
			BufferedReader licznikDanych1 = new BufferedReader(new FileReader("dane1.txt"));
			BufferedReader licznikDanych2 = new BufferedReader(new FileReader("dane2.txt"));
			
			while(licznikDanych1.readLine() != null) {
				liczbaRekordow++;
			}
			while(licznikDanych2.readLine() != null) {
				liczbaRekordow++;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Osoba listaOsob[] = new Osoba[liczbaRekordow];
		int indexListy = 0;
		 try {
			 BufferedReader odczytDanych1 = new BufferedReader(new FileReader("dane1.txt"));
			 BufferedReader odczytDanych2 = new BufferedReader(new FileReader("dane2.txt"));
			 String wiersz = "";
			 
			while((wiersz = odczytDanych1.readLine()) != null){
				String tmp[] = wiersz.split(" ");
				int tmpRokUr = Integer.parseInt(tmp[3]);
				Student tmpStud = new Student(tmp[1], tmp[2], tmpRokUr, tmp[0]);
				listaOsob[indexListy] = tmpStud;
				indexListy++;
			}
			
			while((wiersz = odczytDanych2.readLine()) != null){
				String tmp[] = wiersz.split(" ");
				int tmpRokUr = Integer.parseInt(tmp[3]);
				Student tmpStud = new Student(tmp[1], tmp[2], tmpRokUr, tmp[0]);
				listaOsob[indexListy] = tmpStud;
				indexListy++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		sortowankoEseczka(listaOsob, liczbaRekordow);
		
		try {
			BufferedWriter sortSk = new BufferedWriter(new FileWriter("sortSka.txt"));
			BufferedWriter sortImie = new BufferedWriter(new FileWriter("sortName.txt"));
			
			for(int i=0; i<liczbaRekordow; i++) {
				sortSk.write(listaOsob[i].toString());
				sortSk.newLine();
			}
			sortSk.close();
			
			sortowankoImieniem(listaOsob, liczbaRekordow);
			for(int i=0; i<liczbaRekordow; i++) {
				sortImie.write(listaOsob[i].toString());
				sortImie.newLine();
			}
			sortImie.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int dajMniejsza(int a, int b) {
		if(a>b) {
			return b;
		} else if(a<b) {
			return a;
		} else {
			return a;
		}
	}
		
	public static int porownaj(String imie1, String imie2) {		
		int rozmiar1 = imie1.length();
		int rozmiar2 = imie2.length();

        int val = dajMniejsza(rozmiar1, rozmiar2);
        int i = 0;
        while (i < val) {
            
        	char znak1 = imie1.charAt(i);
            char znak2 = imie2.charAt(i);
            
            if (znak1 != znak2) {
                return znak1 - znak2;
            }
            
            i++;
        }
        return rozmiar1 - rozmiar2;
		
	}
	
	public static void zamien(Osoba tab[], int index1, int index2) {
		Osoba tmp;
		tmp = tab[index1];
		tab[index1] = tab[index2];
		tab[index2] = tmp;
	}
	
	public static void sortowankoImieniem(Osoba osoby[], int rozmiar) {			
		for(int i = 0; i<rozmiar; i++) {
			for(int j = i+1; j<rozmiar; j++) {
				if(porownaj(osoby[i].getImie() ,osoby[j].getImie()) > 0) {
					zamien(osoby, i, j);
				}
			}
		}
		
	}
	
	public static void sortowankoEseczka(Osoba osoby[], int rozmiar) {			
		for(int i = 0; i<rozmiar; i++) {
			for(int j = i+1; j<rozmiar; j++) {
				if(porownaj(((Student) osoby[i]).getSka() ,((Student) osoby[j]).getSka()) > 0) {
					zamien(osoby, i, j);
				}
			}
		}
		
	}
	
	static class Osoba {
		
		// ====Pola====
		private String imie;
		private String nazwisko;
		private int rokUrodzenia;
		
		// ====Konstruktory====
		public Osoba(String imie, String nazwisko, int rokUrodzenia) {
			this.imie = imie;
			this.nazwisko = nazwisko;
			this.rokUrodzenia = rokUrodzenia;
		}
		
		// ====Metody====
		
		public String getImie() {
			return this.imie;
		}
		
		public String getNazwisko() {
			return this.nazwisko;
		}
		
		public int getRokUrodzenia() {
			return this.rokUrodzenia;
		}
		
		@Override
		public String toString() {
			return this.imie + " " + this.nazwisko + " " + this.rokUrodzenia;
		}
	}
	
	static class Student extends Osoba {
		
		// ====Pola====
		private String numerIndeksu;

		// ====Konstruktory====
		public Student(String imie, String nazwisko, int rokUrodzenia, String numerIndeksu) {
			super(imie, nazwisko, rokUrodzenia);
			this.numerIndeksu = numerIndeksu;
			
		}
		
		public String getSka() {
			return this.numerIndeksu;
		}

		// ====Metody====
		@Override
		public String toString() {
			return this.numerIndeksu + " " + this.getImie() + " " + this.getNazwisko() + " " + this.getRokUrodzenia();
		}
	}
}
