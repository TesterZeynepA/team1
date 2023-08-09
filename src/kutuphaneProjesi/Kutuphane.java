package kutuphaneProjesi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static kutuphaneProjesi.Main.*;
import static kutuphaneProjesi.Ogrenci.kitapIadeTarihi;
import static kutuphaneProjesi.Ogrenci.oduncKitaplarList;

public class Kutuphane extends Kitap {

    static List<Kitap> mevcutKitaplar = new ArrayList<>();

    static {//ilk önce mevcut kitapların olduğu list oluşturuldu.
        Kitap kitap1 = new Kitap("Kukla", "Ahmet Ümit", "Kelebek", "2010", "1111", "Roman");
        Kitap kitap2 = new Kitap("Devrim", "Deniz Tokay", "Can", "2020", "2222", "Deneme");
        Kitap kitap3 = new Kitap("Zübük", "Aziz Nesin", "Okyanus", "1976", "3333", "Hikaye");

        mevcutKitaplar.add(kitap1);
        mevcutKitaplar.add(kitap2);
        mevcutKitaplar.add(kitap3);
    }

    static List<Kitap> silinecekKitaplar = new ArrayList<>();
    static Kitap istenenKitap = null;

    public static void kutuphaneMenu() {//Zerrin

        System.out.println("**********KUTUPHANEMİMİZE  HOŞGELDİNİZ**********\n" + "\t" +
                "1- Kitap Ekle\n" + "\t"
                + "2- Kitap Sil\n" + "\t" + "3- Kitap Listele\n" + "\t" +
                "4- Kitap Ara\n" + "\t" + "5- Ana Menu\n" + "\t" + "0- Cıkıs");
        String tercih = input.nextLine();
        switch (tercih) {
            case "1":
                kitapEkle();//Emine
                kutuphaneMenu();
                break;
            case "2":
                kitapSil();// Mustafa
                kutuphaneMenu();
                break;
            case "3":
                katalogListele();
                kutuphaneMenu();
                break;
            case "4":
                kitapAra();//Derya
                kutuphaneMenu();
                break;
            case "5":
                anaProgram();
                break;
            case "0":
                cikis();
                break;
            default:
                System.out.println("Lütfen geçerli bir giriş yapınız");
                kutuphaneMenu();
                break;
        }
    }

    public static void kitapEkle() {//Emine

        System.out.println("Kitabın ismini giriniz");
        String kitapAdi = input.nextLine();

        System.out.println("Kitabın yazarini giriniz");
        String yazar = input.nextLine();

        System.out.println("Kitabın yayinevini giriniz");
        String yayinevi = input.nextLine();

        System.out.println("Kitabın yayin tarihini giriniz");
        String yayinTarihi = input.nextLine();

        System.out.println("Kitabın isbn numarasını giriniz");
        String isbn = input.nextLine();

        System.out.println("Kitabın turunu giriniz");
        String kitapTuru = input.nextLine();

        mevcutKitaplar.add(new Kitap(kitapAdi, yazar, yayinevi, yayinTarihi, isbn, kitapTuru));
        System.out.println("Kitap kütüphaneye başarıyla kaydedildi.");
    }

    private static void kitapSil() {// Mustafa //Kütüphaneden kitap siler
        System.out.println("Silinecek kitabın isbn numarasını giriniz");
        String isbn = input.nextLine();
        boolean flag = false;
        for (Kitap kitap : mevcutKitaplar) {//silmek istenen kitap mevcutlistte mi
            if (kitap.getIsbn().equals(isbn)) {
                silinecekKitaplar.add(kitap);//silinecekkitaplistte eklendi
                flag = true;
                break;
            }
        }
        for (Kitap each : oduncKitaplarList) {//silmek istenen kitap ödünçlistte mi
            if (each.getIsbn().equals((isbn))) {
                System.out.println("Silmek istediğiniz kitap öğrenciye ödünç verildi. " + kitapIadeTarihi + " tarihinde kütüphaneye iade edilecek");
                flag = true;
                break;
            }
        }
        if (!silinecekKitaplar.isEmpty()) {//silinecekkitap list boş değilse
            mevcutKitaplar.removeAll(silinecekKitaplar);
            System.out.println("Silmek istediğniz kitap: " + silinecekKitaplar.get(0).getKitapAdi() + " kütüphaneden silindi");
            silinecekKitaplar.clear();//silnecekkitap list temizlendi
        } else if (!flag) {//Eğer silenecek kitap mevcut ve odunc listte yoksa kütüphaneye kayıtlı değildir.
            System.out.println("Silmek istediğiniz kitap kütüphanemize kayıtlı değildir.");
        }
    }

    private static void katalogListele() {//Mustafa

        System.out.println("*********KUTUPHANE KİTAP LİSTESİ*********\n");
        System.out.printf("%-15s %-15s %-15s %-15s %-9s %s\n", "KİTAP İSMİ", "YAZAR", "YAYINEVİ", "YAYIN TARIHI", "ISBN NO", "TUR");
        for (int i = 0; i < mevcutKitaplar.size(); i++) {

            System.out.printf("%-15s %-15s %-15s %-15s %-9s %s\n", mevcutKitaplar.get(i).getKitapAdi(),
                    mevcutKitaplar.get(i).getYazar(), mevcutKitaplar.get(i).getYayinevi(), mevcutKitaplar.get(i).getYayinTarihi(),
                    mevcutKitaplar.get(i).getIsbn(), mevcutKitaplar.get(i).getKitapTuru());
        }
    }

    static void kitapAra() {//Derya

        System.out.println("Aradığınız kitabın ismini giriniz: ");
        String kitapAdi = new Scanner(System.in).nextLine();

        if (mevcutKitaplarKontrol(kitapAdi)) {//aranan kitap mevcutlistte mi
            System.out.println("Aranan Kitap: " + kitapAdi + " ismi ile kütüphanemizdedir");
        } else if (oduncKitaplarListKontrol(kitapAdi)) {//aranan kitap odunclistte  mi
            System.out.println("Kitap başkasına ödünc verildi.  " + kitapIadeTarihi + " tarihinde odunc alınabilir");
        } else
            System.out.println("Bu kitap kütüphanemize kayıtlı değildir.");
    }

    public static boolean mevcutKitaplarKontrol(String kitapAdi) {//Başka metodların kullanması için kitap adı ile kütüphaneden kitabın varlığı kontrol eder
        boolean flag = false;
        for (Kitap kitap : mevcutKitaplar) {
            if (kitap.getKitapAdi().equalsIgnoreCase(kitapAdi)) {
                istenenKitap = kitap;
                flag = true;
                break;
            }
        }
        return flag;//kütüphanede(mevcutlistte) olup olmadığını return eder
    }

    public static boolean oduncKitaplarListKontrol(String kitapAdi) {//Başka metodların kullanması için kitap adı ile ödunc alınıp alınmadığını kontrol eder
        boolean flag2 = false;
        for (Kitap each : oduncKitaplarList) {
            if (each.getKitapAdi().equalsIgnoreCase(kitapAdi)) {
                flag2 = true;
                break;
            }
        }
        return flag2;////odunclistte olup olmadığını return eder
    }


    @Override
    public String toString() {
        return "Kutuphane{} " + super.toString();
    }

}