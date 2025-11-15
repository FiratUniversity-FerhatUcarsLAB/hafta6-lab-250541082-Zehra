Zehra ÖZDEMİR 
  250541082
  15.11.2025
  Açıklama: Hocam kodu geminiden yapıştırdım ama bugün sabahtan beri saat 7.30 da başaldım bu saate kadar uğraştım bi yerinde bi hata vardı geminiye sordum 
  baya oğraştık düzeltemedim ondan burdan yapıştırmak zorunda kaldım özür dilerim.


import java.util.Scanner;

public class SinemaBileti {

    // Sabit Fiyatlar
    public static final double FIYAT_HAFTAICI_MATINE = 45.0;
    public static final double FIYAT_HAFTAICI_NORMAL = 65.0;
    public static final double FIYAT_HAFTASONU_MATINE = 55.0;
    public static final double FIYAT_HAFTASONU_NORMAL = 85.0;

    // 1. Yardımcı Metotlar
    
    /** Gün numarasının hafta sonu (6=Cmt, 7=Paz) olup olmadığını kontrol eder. */
    public static boolean isWeekend(int gunSecimi) {
        return gunSecimi == 6 || gunSecimi == 7;
    }

    /** Saat bilgisinin matine (12:00 öncesi) olup olmadığını kontrol eder. */
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    /** Gün ve saat bilgisine göre temel bilet fiyatını hesaplar. */
    public static double calculateBasePrice(int gunSecimi, int saat) {
        boolean isHaftasonu = isWeekend(gunSecimi);
        boolean isMatine = isMatinee(saat);
        
        // Ternary operatörleri ile daha kısa yazım
        if (isHaftasonu) {
            return isMatine ? FIYAT_HAFTASONU_MATINE : FIYAT_HAFTASONU_NORMAL;
        } else { // Hafta içi
            return isMatine ? FIYAT_HAFTAICI_MATINE : FIYAT_HAFTAICI_NORMAL;
        }
    }

    /** Yaş, meslek ve güne göre indirim miktarını (TL) hesaplar. */
    public static double calculateDiscount(int meslekSecimi, int yas, int gunSecimi, double temelFiyat) {
        double indirimYuzdesi = 0.0;
        
        // Yaş İndirimleri (Öncelikli)
        if (yas < 12) {
            indirimYuzdesi = 0.25; // %25
        } else if (yas >= 65) {
            indirimYuzdesi = 0.30; // %30
        }
        
        // Meslek İndirimleri (Eğer yaş indirimi uygulanmadıysa)
        if (indirimYuzdesi == 0.0) {
            switch (meslekSecimi) {
                case 1: // Öğrenci
                    if (gunSecimi >= 1 && gunSecimi <= 4) { // Pzt-Per (%20)
                        indirimYuzdesi = 0.20;
                    } else if (gunSecimi >= 5 && gunSecimi <= 7) { // Cuma-Paz (%15)
                        indirimYuzdesi = 0.15;
                    }
                    break;
                case 2: // Öğretmen
                    if (gunSecimi == 4) { // Sadece Çarşamba (%35)
                        indirimYuzdesi = 0.35;
                    }
                    break;
                // case 3 (Diğer) için 0.0
            }
        }
        
        // İndirim Yüzdesi değil, indirim MİKTARINI döndürüyoruz.
        return temelFiyat * indirimYuzdesi;
    }

    /** Film türüne (3D, IMAX, 4DX) göre ekstra ücreti hesaplar. */
    public static double getFormatExtra(int filmTuruSecimi) {
        double ekstraUcret = 0.0;
        
        switch (filmTuruSecimi) {
            case 2: // 3D
                ekstraUcret = 25.0;
                break;
            case 3: // IMAX
                ekstraUcret = 35.0;
                break;
            case 4: // 4DX
                ekstraUcret = 50.0;
                break;
            default: // 1 (2D) veya geçersiz için
                ekstraUcret = 0.0;
                break;
        }
        return ekstraUcret;
    }

    /** Tüm hesaplamaları birleştirerek toplam fiyatı bulur. */
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimMiktari = calculateDiscount(meslek, yas, gun, temelFiyat);
        double indirimliFiyat = temelFiyat - indirimMiktari;
        double ekstraUcret = getFormatExtra(filmTuru);
        
        return indirimliFiyat + ekstraUcret;
    }

    /** Bilet bilgilerini formatlayıp ekrana yazdırır. */
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru, double toplamFiyat) {
        
        // Rapor detayları için hesaplamaları tekrar yapıyoruz
        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimMiktari = calculateDiscount(meslek, yas, gun, temelFiyat);
        double indirimliFiyat = temelFiyat - indirimMiktari;
        double ekstraUcret = getFormatExtra(filmTuru);
        
        System.out.println("\n=== SİNEMA BİLETİ RAPORU ===");
        // Fiyatları 2 ondalık basamak formatında yazdırıyoruz
        System.out.println("1. TEMEL FİYAT: \t\t" + String.format("%.2f", temelFiyat) + " TL");
        System.out.println("2. İNDİRİM MİKTARI: \t-" + String.format("%.2f", indirimMiktari) + " TL");
        System.out.println("3. İNDİRİMLİ FİYAT: \t" + String.format("%.2f", indirimliFiyat) + " TL");
        System.out.println("4. FORMAT EK ÜCRETİ: \t+" + String.format("%.2f", ekstraUcret) + " TL");
        System.out.println("------------------------------------");
        System.out.println("TOPLAM FİYAT: \t\t" + String.format("%.2f", toplamFiyat) + " TL");
        System.out.println("================================");
    }

    // 2. Ana Çalışma Metodu (main)
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===== SİNEMA BİLETİ HESAPLAMA =====");
        
        // Girdileri Al
        System.out.print("Gün Seçimi (1=Pzt, ..., 7=Paz): ");
        int gunSecimi = scanner.nextInt();
        
        System.out.print("Saat (0-23): ");
        int saat = scanner.nextInt();
        
        System.out.print("Yaşınız: ");
        int yas = scanner.nextInt();
        
        System.out.print("Meslek Seçimi (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslekSecimi = scanner.nextInt();
        
        System.out.print("Film Türü Seçimi (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuruSecimi = scanner.nextInt();

        // Toplam Fiyatı Hesapla
        double toplamFiyat = calculateFinalPrice(gunSecimi, saat, yas, meslekSecimi, filmTuruSecimi);
        
        // Bilgileri Ekrana Yazdır
        generateTicketInfo(gunSecimi, saat, yas, meslekSecimi, filmTuruSecimi, toplamFiyat);

        scanner.close();
    }
}
