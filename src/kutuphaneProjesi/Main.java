package kutuphaneProjesi;

import javax.naming.InvalidNameException;
import java.time.LocalDate;
import java.util.*;

import static kutuphaneProjesi.Kutuphane.*;
import static kutuphaneProjesi.Ogrenci.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    static ArrayList<Ogrenci> uyeList = new ArrayList<>();
    static Ogrenci ogrenci = new Ogrenci();

    public static void anaProgram() {//Zerrin

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\nKÜTÜPHANEMİZE HOŞ GELDİNİZ" +
                "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("1-Öğrenci işlemleri\n2-Kütüphane işlemleri");
        int anaMenuSecim = input.nextInt();

        if (anaMenuSecim == 1) {
            ogrenciMenu();
        } else if (anaMenuSecim == 2) {
            input.nextLine();
            Kutuphane.kutuphaneMenu();
        } else
            System.out.println("Hatalı giriş");
    }

    static void ogrenciMenu() {//Zerrin
        System.out.println("******Ogrenci İşlemlerine Hoşgeldiniz******");
        System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz = " + "\n1-Öğrenci Kaydı oluşturma \n2-kitapOduncAl " +
                "\n3-kitapIadeEt \n4-kitaplarimiListele \n5-Kütühanede Kitap ara\n6-AnaMenu  \n0-Çıkış");

        switch (new Scanner(System.in).nextInt()) {
            case 1:
                ogrenciKaydi();// Zeynep
                ogrenciMenu();
                break;
            case 2:
                kitapOduncAl();// Serap
                ogrenciMenu();
                break;
            case 3:
                kitapIadeEt();// Sena
                ogrenciMenu();
                break;
            case 4:
                kitaplarimiListele();
                ogrenciMenu();
                break;
            case 5:
                Kutuphane.kitapAra();
                ogrenciMenu();
                break;
            case 6:
                anaProgram();
                break;
            case 0:
                cikis();
                break;
            default:
                System.out.println("Hatalı giriş. Tekrar deneyiniz.");
                break;
        }
    }

    public static void cikis() {
        System.out.println("Kütüphaneden çıkış yaptınız, yine bekleriz...");
    }

    private static void ogrenciKaydi() {
        String ogrenciAd = null;

        while (true) {
            try {//Ogrencinin adı sadece harf ve boşluk içermesi kontrolu try-catch ile yapıldı
                System.out.print("Kaydı yapılacak öğrencinin adını giriniz: ");
                ogrenciAd = input.nextLine();
                if (!ogrenciAd.matches("[A-Za-z\\s]+")) throw new InvalidNameException();//
                else break;
            } catch (InvalidNameException e) {
                System.out.println("İsim sadece harflerden olmalı.");
            }
        }

        int ogrenciNo = 0;
        while (true) {
            try {//Ogrencinin numarası sadece rakamlardan ve 4 haneli olması kontrolu try-catch ile yapıldı
                System.out.println("Öğrencinin numarasını giriniz : ");

                if (input.hasNextInt()) {
                    ogrenciNo = input.nextInt();
                    if ((ogrenciNo >= 9999 || ogrenciNo <= 1000)) {
                        System.out.println("Ogrenci No 4 haneli olmalı");
                    } else
                        break;
                } else {
                    input.next();//dummy
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Öğrenci no sadece rakamlardan oluşmalı.");
            }
        }
        uyeList.add(new Ogrenci(ogrenci.getOgrenciAd(), ogrenci.getOgrenciNo()));
        System.out.println("Öğrenci Kütüphaneye başarıyla kaydedildi :)");

    }

    private static void kitaplarimiListele() {

        System.out.println(Ogrenci.oduncKitaplarList);//Odunc alınan kitapları listeler
    }

    private static void kitapIadeEt() {

        System.out.println("İade etmek istediğiniz kitabın ismini giriniz");
        String kitapAdi = input.next();

        if (oduncKitaplarListKontrol(kitapAdi)) {// İade etmek istenilen kitap oduncKitaplarList te varmı kontrolu
            for (Kitap each : oduncKitaplarList) {
                if (each.getKitapAdi().equalsIgnoreCase(kitapAdi)) {// İade etmek istenilen kitap oduncKitaplarList te bulundu
                    System.out.println("Kitabi iade ettiniz");
                    Ogrenci.oduncKitaplarList.remove(oduncKitaplarList.indexOf(each));// İade etmek istenilen kitap oduncKitaplarListten silindi
                    Kutuphane.mevcutKitaplar.add(each);// İade etmek istenilen kitap mevcutKitap Listine eklendi
                    break;
                }
            }
        } else if (!mevcutKitaplarKontrol(kitapAdi)) {// İade etmek istenilen kitap kütüphanede yoksa
            System.out.println("Bu kitap kitap kütüphanemize kayıtlı değildir.");
            System.out.println("Bu kitabı kütüphanemize bağışlamak ister misiniz? \nE- Evet \nH- Hayır");

           

            if (input.next().equalsIgnoreCase("E")) {
                input.nextLine();//dummy
                Kutuphane.kitapEkle();
            } else {
                System.out.println("Bir dahaki sefere...");
            }
        }else
            System.out.println(kitapAdi+" isimli kitap ödünc aldığınız kitaplar arasında değil.");
    }

    private static void kitapOduncAl() {
        System.out.println("Odunc almak istediğiniz kitabın ismini giriniz:");
        input.nextLine();//dummy
        String kitapAdi = input.nextLine();
        Kitap istenenKitap;

        if (mevcutKitaplarKontrol(kitapAdi)) {//Odunc almak istenilen kitap Kütüphanede ise
            System.out.println("Aranan Kitap: " + kitapAdi + " ismi ile kütüphanemizdedir");
            istenenKitap = Kutuphane.odunc;//Kütüphanede ki odunc kitap istenen kitaba atandı
            if (oduncKitaplarList.size() < 3) {//Ogrenci en fazla 3 kitap ödünç alabilir
                Ogrenci.oduncKitaplarList.add(istenenKitap);//istenenkitap ödünç alındı

            } else {
                System.out.println("Kütüphanemizden maximum 3 kitap ödünç alabilirsiniz...");
            }
            oduncAlmaTarihi = LocalDate.now();//Odunc alma tarihi belirlendi

            Ogrenci.kitapIadeTarihi = oduncAlmaTarihi.plusDays(5);//İade tarihi 5 gün sonra olarak atandı

            System.out.println(oduncKitaplarList.size() + " tane kitap ödünç aldınız. Bu kitapları " +
                    kitapIadeTarihi + " tarihinde iade etmelisiniz.");

            Kutuphane.mevcutKitaplar.remove(istenenKitap);//odunc alınan kitap mencut listten silindi

        } else if (oduncKitaplarListKontrol(kitapAdi)) {
            System.out.println("Kitap başkasına ödünc verildi.  " + kitapIadeTarihi + " tarihinde odunc alınabilir");

        } else
            System.out.println("Bu kitap kütüphanemize kayıtlı değildir.");

    }

}
