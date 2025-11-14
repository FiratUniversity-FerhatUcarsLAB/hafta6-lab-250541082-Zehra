Zehra Özdemir 
250541082
14.11.2025  


  import java.util.Scanner;

public class NotSistemi {
    public static double calculateAverage(int vize_notu, int final_notu, int odev_notu){
        double ortalama = vize_notu * 0.3
                          + final_notu * 0.4
                          +odev_notu * 0.3;
        return ortalama;
    }
    public static boolean isPassingGrade(double ortalama) {
        if (ortalama >= 50) {
            return true;
        }else{
            return false;
        }
    }
    public static void getLetterGrade(double ortalama) {
        String harf_notu = "GEÇERSİZ";
        if (ortalama >= 90 && ortalama <= 100) {
            harf_notu = "A";
        } else if (ortalama >= 80 && ortalama <= 89) {
            harf_notu = "B";

        } else if (ortalama >= 70 && ortalama <= 79) {
            harf_notu = "C";

        } else if (ortalama >= 60 && ortalama <= 79) {
            harf_notu = "D";
        } else {
            harf_notu = "F";
        }
        return ;
    }

    public static boolean isHonorList(double ortalama,
                                  int vize_notu,
                                  int final_notu,
                                  int odev_notu) {
    if (ortalama >= 85 && vize_notu >= 70 && final_notu >= 70 && odev_notu >= 70) {
        return true;
    } else {
        return false;
    }
}
public static boolean hasRetakeRight(double ortalama){
    if (ortalama >= 40 && ortalama < 50) {
        return true;
    } else {
        return false;
    }
}
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int vize_notu,final_notu,odev_notu;
    System.out.print("vize notunu girin:");
    vize_notu=scanner.nextInt();
    System.out.print("final notunu girin:");
    final_notu=scanner.nextInt();
    System.out.print("ödev notunu girin:");
    odev_notu=scanner.nextInt();

      System.out.println("=====OGRENCİ NOT RAPORU=====");
      System.out.println("VİZE NOTU:" + vize_notu);
      System.out.println("FINAL NOTU:" + final_notu);
      System.out.println("ODEV NOTU:" + odev_notu);
      System.out.println("--------------------------");
      double ortalama = calculateAverage(vize_notu, final_notu, odev_notu);

      System.out.println("Ortalama:" + ortalama);
    System.out.println("Harf Notu: ");
     getLetterGrade(ortalama);
      if (isPassingGrade(ortalama)) {
          System.out.println("Durum: GEÇTİ");

      }else{
          System.out.println("Durum: KALDI");
      }

    }

}
