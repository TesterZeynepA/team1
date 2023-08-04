package ZeynepA.bankaHesabı;

import java.util.ArrayList;
import java.util.Scanner;

import static ZeynepA.bankaHesabı.Main.anaMenu;
import static ZeynepA.bankaHesabı.Main.input;

public class Bank extends Account {

    static ArrayList<Account> customerAccounts = new ArrayList<>();

    public Bank() {
    }

    public Bank(String hesapNumarasi, int bakiye) {

        super(hesapNumarasi, bakiye);
    }

    static void hesapAc() {
        System.out.println("Lütfen adınızı, soy adınızı giriniz: ");
        String customerName = new Scanner(System.in).nextLine();

        System.out.println("1-Vadeli Hesap\n2-Vadesiz Hesap");
        boolean vadeliMi;
        int vadeTercih = input.nextInt();
        while (true) {
            try {
                if (vadeTercih == 1) {
                    vadeliMi = true;
                    break;
                } else if (vadeTercih == 2) {
                    vadeliMi = false;
                    break;
                } else
                    throw new IllegalArgumentException("Hatalı giriş tekrar deneyiniz");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        Account cstmr1 = new Account(customerName, vadeliMi);
        customerAccounts.add(cstmr1);
        System.out.println("Yeni hesabınız oluşturulmuştur. iyi günlerde kullanın!");
        System.out.println("Hesap numaranız = " + cstmr1.getHesapNumarasi());
    }

    static void hesapKapat() {
        System.out.println("Lütfen silmek istediğiniz hesap numarasini giriniz: ");
        String silinecekHesapNo = new Scanner(System.in).nextLine();

        for (Account hesap : customerAccounts) {
            if ((hesap.getHesapNumarasi()).equals(silinecekHesapNo)) {
                System.out.println("Hesap sahibi isim= " + hesap.getCustomerName());
                customerAccounts.remove(hesap);
                System.out.println("hesabınız silinmiştir...");
            } else System.out.println("Yanlış giriş yaptınız! Böyle bir hesap numarası sistemimizde bulunmamaktadır.");

        }

    }

    static void hesapListele() {

        System.out.println("Hesap Listesi = " + customerAccounts);
    }

    static Account hesapVarMi() {
        System.out.println("Lütfen aramak istediğiniz hesap numarasini giriniz: ");
        String arananHesapNo = new Scanner(System.in).nextLine();

        for (Account arananHesap : customerAccounts) {
            if ((arananHesap.getHesapNumarasi()).equals(arananHesapNo)) {
                System.out.println("Hesap sahibi isim= " + arananHesap.getCustomerName());
                return arananHesap;
            }
        }
        return null;
    }

    static void krediCekme() {
        System.out.println("Lütfen çekmek istediğiniz kredi miktarını giriniz: ");
        double krediTutari = input.nextInt();

        System.out.println("Kredi süresini ay olarak giriniz: ");
        int krediSuresi = input.nextInt();

        System.out.println("Çektiğiniz krediyi " + krediSuresi + " ay süresince geri ödeyebilirsiniz...");
        System.out.println("Toplam geri ödemeniz " + krediFaiziHesapla(krediTutari, 1.9, krediSuresi));
    }

    static void cikis() {
        System.out.println("Bankamızdan çıkış yaptınız, iyi günler dileriz...");
    }

}
