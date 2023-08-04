package ZeynepA.bankaHesabı;

import java.util.Scanner;

import static ZeynepA.bankaHesabı.Bank.customerAccounts;
import static ZeynepA.bankaHesabı.Bank.hesapVarMi;
import static ZeynepA.bankaHesabı.Main.input;

public class Account extends Customer{

    private String hesapNumarasi;

    private  double bakiye;

    private boolean vadeliMi;


    public Account() {
    }

    public Account(String customerName,boolean vadeliMi) {
        super(customerName);
        this.vadeliMi=vadeliMi;
        setHesapNumarasi(4);
    }

    public Account(String customerName,int customerNumber,boolean vadeliMi) {
        super(customerName,customerNumber);
        setHesapNumarasi(4);
        this.vadeliMi = vadeliMi;
    }

    public Account(String hesapNumarasi, int bakiye) {
        super();
        setHesapNumarasi(4);
        this.bakiye = bakiye;
    }

    public Account(String hesapNumarasi, int bakiye, String customerName) {
        super(customerName);
        setHesapNumarasi(4);
    }
    public boolean isVadeliMi() {
        return vadeliMi;
    }

    public void setVadeliMi(boolean vadeliMi) {
        this.vadeliMi = vadeliMi;
    }
    public void setHesapNumarasi(String hesapNumarasi) {
        this.hesapNumarasi = hesapNumarasi;
    }

    public void bakiyeSorgula() {

        if (getBakiye()!=0 && isVadeliMi()) {
            System.out.println("Mevduat süresini gun olarak giriniz: ");
            int sure= input.nextInt();
            System.out.println(mevduatFaiziHesapla(35, sure));
        } else {
            System.out.println(getBakiye());
        }
    }

    public  void paraYatir(){
        System.out.println("Yatırmak istediğiniz miktarı giriniz: ");
        double yatirilanPara= input.nextDouble();
        double bakiye=getBakiye();
        bakiye+=yatirilanPara;
        setBakiye(bakiye);
    }

    public void paraCek(){

        System.out.println("Çekmek isteğinizi miktarı girin: ");
        double cekılecekPara=input.nextDouble();
        double bakiye=getBakiye();
        if (cekılecekPara>bakiye){
            System.out.println("Yetersiz bakiye! İşlem iptal edildi.");
        } else if (cekılecekPara<0) {
            System.out.println("Hatalı işlem! Geçerli bir miktar giriniz.");

        }else if (cekılecekPara<bakiye){
            bakiye-=cekılecekPara;
            setBakiye(bakiye);
            System.out.println("Para çekme işlemi başarılı. Güncel bakiye: " + bakiye);
        }
      }

    public double mevduatFaiziHesapla(double faizOrani, int mevduatSuresi){
        double bakiye=getBakiye();
        bakiye+=bakiye*faizOrani*mevduatSuresi/36500;
        setBakiye(bakiye);
        return bakiye;
    }
    public static double krediFaiziHesapla(double krediTutari, double faizOrani, int krediSuresi) {

        double aylıkFaizOrani = faizOrani / 12;

        double aylıkOdeme = krediTutari * aylıkFaizOrani / (1 - Math.pow(1 + aylıkFaizOrani, -krediSuresi));

        return   aylıkOdeme * krediSuresi;
    }

    public void setHesapNumarasi (int partLength) {

        String dataSource = "0123456789";

        String hesapNumarasi = "";

        for (int i = 0; i < 4; i++) {

            char[] part1 = new char[partLength];

            for (int j = 0; j < partLength; j++) {

                int random = (int) (Math.random() * dataSource.length());
                part1[j] = dataSource.charAt(random);
            }
            hesapNumarasi += new String(part1) + " ";
        }
        this.hesapNumarasi= hesapNumarasi.trim();
    }
     public static void musterihesapAcma() {
         System.out.println("Müşteri numaranızı giriniz: ");
         int customerNumber = new Scanner(System.in).nextInt();
         boolean vadeliMi = false;
         Account cstmr1 = null;
         for (Account arananHesap : customerAccounts) {
             if ((arananHesap.getCustomerNumber()) == (customerNumber)) {
                 System.out.println("Hesap sahibi isim= " + arananHesap.getCustomerName());
                 System.out.println("1-Vadeli Hesap\n2-Vadesiz Hesap");

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
                 cstmr1 = new Account(arananHesap.getCustomerName(), arananHesap.getCustomerNumber(), vadeliMi);

             }
         }
         customerAccounts.add(cstmr1);

         System.out.println("Yeni hesabınız oluşturulmuştur. iyi günlerde kullanın!");
         System.out.println("Hesap numaranız = " + cstmr1.getHesapNumarasi());

     }

    public String getHesapNumarasi() {
        return hesapNumarasi;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    @Override
    public String toString() {
        return "Account{" + getCustomerName()+" "+ getCustomerNumber()+" "+"VadeliMi="+isVadeliMi()+" "+
                "hesapNumarasi=" + hesapNumarasi +
                ", bakiye=" + bakiye +
                '}';
    }

}
