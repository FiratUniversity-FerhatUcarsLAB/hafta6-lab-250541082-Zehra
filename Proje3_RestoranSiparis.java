Zehra ÖZDEMİR
  250541082
  16.11.2025

  import java.util.Scanner;

public class RestoranSiparis {

    public static double getMainDishPrice(int secim) {
        double price = 0.0;
        switch (secim) {
            case 1: price = 85.0; break; // Izgara Tavuk
            case 2: price = 120.0; break; // Adana Kebap
            case 3: price = 110.0; break; // Levrek
            case 4: price = 65.0; break; // Mantı
            default: price = 0.0; break;
        }
        return price;
    }

    public static double getAppetizerPrice(int secim) {
        double price = 0.0;
        switch (secim) {
            case 1: price = 25.0; break; // Çorba
            case 2: price = 45.0; break; // Humus
            case 3: price = 55.0; break; // Sigara Böreği
            default: price = 0.0; break;
        }
        return price;
    }

    public static double getDrinkPrice(int secim) {
        double price = 0.0;
        switch (secim) {
            case 1: price = 15.0; break; // Kola
            case 2: price = 12.0; break; // Ayran
            case 3: price = 35.0; break; // Taze Meyve Suyu
            case 4: price = 25.0; break; // Limonata
            default: price = 0.0; break;
        }
        return price;
    }

    public static double getDessertPrice(int secim) {
        double price = 0.0;
        switch (secim) {
            case 1: price = 65.0; break; // Künefe
            case 2: price = 55.0; break; // Baklava
            case 3: price = 35.0; break; // Sütlaç
            default: price = 0.0; break;
        }
        return price;
    }

    
    public static boolean isComboOrder(int anaYemekSecim, int icecekSecim, int tatliSecim) {
        if (anaYemekSecim >= 1 && icecekSecim >= 1 && tatliSecim >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHappyHour(int saat) {
        if (saat >= 14 && saat < 17) {
            return true;
        } else {
            return false;
        }
    }


    public static double calculateAraToplam(int aYemek, int baslangıc, int icecek, int tatli) {
        double araToplam = getMainDishPrice(aYemek) +
                getAppetizerPrice(baslangıc) +
                getDrinkPrice(icecek) +
                getDessertPrice(tatli);
        return araToplam;
    }

    public static double calculateDiscount(double araToplam,int baslangıc, int aYemek, int icecek, int tatli, int saat, char ogrenci, int gun) {
        double toplamIndirim = 0.0;

        if (isComboOrder(aYemek, icecek, tatli)) {
            toplamIndirim = toplamIndirim + (araToplam * 0.15);
        }

        if (isHappyHour(saat)) {
            double icecekFiyati = getDrinkPrice(icecek);
            toplamIndirim = toplamIndirim + (icecekFiyati * 0.20);
        }

        double araToplamIceceksiz = getMainDishPrice(aYemek) +
                getAppetizerPrice(aYemek) +
                getDessertPrice(tatli);

        if (araToplamIceceksiz > 200) {
            toplamIndirim = toplamIndirim + (araToplamIceceksiz * 0.10);
        }

        if (ogrenci == 'E' || ogrenci == 'e') {
            if (gun >= 1 && gun <= 5) {
                toplamIndirim = toplamIndirim + (araToplam * 0.10);
            }
        }

        return toplamIndirim;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- 🍽️ RESTORAN SİPARİŞİ ---");
        System.out.println("Menü: Ana Yemek (1-4), Başlangıç (1-3), İçecek (1-4), Tatlı (1-3). Seçim yoksa 0 girin.");

        System.out.print("Ana Yemek seçiminizi girin: ");
        int anaYemekSecim = scanner.nextInt();

        System.out.print("Başlangıç seçiminizi girin: ");
        int baslangicSecim = scanner.nextInt();

        System.out.print("İçecek seçiminizi girin: ");
        int icecekSecim = scanner.nextInt();

        System.out.print("Tatlı seçiminizi girin: ");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat bilgisini girin (8-23): ");
        int saat = scanner.nextInt();

        System.out.print("Öğrenci misiniz? (E/H): ");
        char ogrenciMisiniz = scanner.next().charAt(0);

        System.out.print("Hangi gün (1:Pazartesi, ..., 7:Pazar): ");
        int hangiGun = scanner.nextInt();

        scanner.close();

        double araToplam = calculateAraToplam(anaYemekSecim, baslangicSecim, icecekSecim, tatliSecim);

        double toplamIndirim = calculateDiscount(araToplam, anaYemekSecim,baslangicSecim, icecekSecim, tatliSecim, saat, ogrenciMisiniz, hangiGun);

        double toplamIndirimliTutar = araToplam - toplamIndirim;

        double bahsisOnerisi = calculateServiceTip(toplamIndirimliTutar);

        double odenecekTutar = toplamIndirimliTutar + bahsisOnerisi;

        System.out.println("\n--- 💰 HESAP FİŞİ ---");
        System.out.printf("📋 Ara Toplam: %.2f TL\n", araToplam);
        System.out.printf("💸 Toplam İndirim: -%.2f TL\n", toplamIndirim);
        System.out.println("---------------------------------");
        System.out.printf("💵 İndirimli Tutar: %.2f TL\n", toplamIndirimliTutar);
        System.out.printf("🥂 Bahşiş Önerisi (%10): +%.2f TL\n", bahsisOnerisi);
        System.out.println("=================================");
        System.out.printf("**🧾 ÖDENECEK TOPLAM TUTAR: %.2f TL**\n", odenecekTutar);
        System.out.println("=================================");
    }
}
