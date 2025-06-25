import java.util.Scanner;

public class nyoba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("masukkan harga awal: ");
        int hargaAwal = scanner.nextInt();

        System.out.print("masukkan diskon(dalam %): ");
        int persenDiskon = scanner.nextInt();

        hargaAwal -= hargaAwal * persenDiskon/100;


        System.out.println(hargaAwal);
        scanner.close();
    }
}