package odevOS; // odevOS isimli paket tanımlanıyor.

import java.util.Arrays; // Java'da diziler üzerinde işlem yapabilmek için gerekli olan Arrays kütüphanesi ekleniyor.
import java.util.Random;// Rastgele sayılar üretmek için gerekli olan Random kütüphanesi ekleniyor.
import java.util.Scanner; // Kullanıcıdan veri alabilmek için gerekli olan Scanner kütüphanesi ekleniyor

class SatirIsParcasi extends Thread { // SatirIsParcasi adında bir sınıf tanımlanıyor, Thread sınıfından türetiliyor
	private int[] satir; // İşlenecek satırı temsil eden dizi tanımlanıyor
	private int satirIndex; // Satırın indeksini tutan değişken tanımlanıyor
	private int[][] sonuclar; // Sonuçların tutulacağı matris tanımlanıyor

	// Constructor (Yapıcı metot)
	public SatirIsParcasi(int[] satir, int satirIndex, int[][] sonuclar) {
		this.satir = satir; // Parametre olarak gelen satir dizisi sınıfın satir değişkenine atanıyor.
		this.satirIndex = satirIndex; // Parametre olarak gelen satirIndex değeri sınıfın satirIndex değişkenine atanıyor.
		this.sonuclar = sonuclar; // Parametre olarak gelen sonuclar dizisi sınıfın sonuclar değişkenine atanıyor.
	}

	// Thread sınıfından türetilmiş sınıf olduğu için run metodu override ediliyor.
	public void run() {
		System.out.println("Satır " + (satirIndex + 1) + ": " + Arrays.toString(satir)); // Satırın içeriğini ekrana yazdırır

		int max = satir[0]; // Satırın maksimum değerini tutacak değişken
		int min = satir[0]; // Satırın minimum değerini tutacak değişken
		int toplam = 0; // Satırın elemanlarının toplamını tutacak değişken

		// Satır üzerindeki işlemleri yapar
		for (int i = 0; i < satir.length; i++) {
			if (satir[i] > max) {
				max = satir[i]; // Maksimum değeri günceller
			}
			if (satir[i] < min) {
				min = satir[i]; // Minimum değeri günceller
			}
			toplam += satir[i]; // Elemanları toplar
		}


		// Sonuçları sonuclar dizisine kaydeder
		sonuclar[satirIndex][0] = max; // Maksimum değeri sonuçlara kaydeder
		sonuclar[satirIndex][1] = min; // Minimum değeri sonuçlara kaydeder
		sonuclar[satirIndex][2] = toplam; // Toplamı sonuçlara kaydeder
		sonuclar[satirIndex][3] = toplam / satir.length; // Ortalamayı sonuçlara kaydeder
	}
}

public class ProblemB { // ProblemB adında bir sınıf tanımlanıyor
	public static void main(String[] args) { // Ana program başlangıcı
		long startTime = System.nanoTime(); // Başlangıç zamanını kaydeder
		Scanner scanner = new Scanner(System.in); // Kullanıcıdan veri alabilmek için Scanner sınıfından bir nesne oluşturuluyor.
		System.out.print("N: "); // Kullanıcıya N değerini girmesi isteniyor.
		int N = scanner.nextInt(); // Kullanıcıdan alınan N değeri saklanıyor.

		System.out.println(); // Boş satır ekleniyor.

		// NxN'lik bir matris oluşturur 
		int[][] matris = new int[N][N];
		Random random = new Random();// Rastgele sayılar üretebilmek için Random sınıfından bir nesne oluşturuluyor.
		for (int i = 0; i < N; i++) { // Matrisin satırları üzerinde döngü başlatılıyor.
			for (int j = 0; j < N; j++) { // Matrisin sütunları üzerinde döngü başlatılıyor.
				matris[i][j] = random.nextInt(10) + 1; // Her bir elemana 1 ile 10 arasında rastgele bir değer atanıyor.
			}
		}

		// Sonuçları tutmak için dizi oluşturur
		int[][] sonuclar = new int[N][4]; // Sonuçları tutmak için N'e 4'lük bir dizi oluşturuluyor.

		// Her satır için bir iş parçası oluşturuluyor ve çalıştırılıyor
		for (int i = 0; i < N; i++) { // Aynı zamanda bu kod parçası sayesinde indis aralığını takip edilir
			Thread isParcasi = new SatirIsParcasi(matris[i], i, sonuclar); // Her satır için iş parçası oluşturur
			isParcasi.start(); // İş parçasını çalıştırır
			try {
				isParcasi.join(); // İş parçasının tamamlanmasını bekler
			} catch (InterruptedException e) {
				e.printStackTrace(); // Kesintiye uğrayan thread'in hatasını konsola yazdırır.
			}
		}
		System.out.println();

		// Sonuçları ekrana yazdırır
		for (int i = 0; i < N; i++) {
			System.out.println((i+1)+". Satır İçin En Büyük Eleman: " + sonuclar[i][0]);
			System.out.println((i+1)+". Satır İçin En Küçük Eleman: " + sonuclar[i][1]);
			System.out.println((i+1)+". Satır İçin Elemanların Toplamı: " + sonuclar[i][2]);
			System.out.println((i+1)+". Satır İçin Elemanların Ortalaması: " + sonuclar[i][3]);
			System.out.println();
		}

		scanner.close(); // Scanner'ı kapatır

		long endTime = System.nanoTime(); // Bitiş zamanını kaydeder
		long durationInMicroseconds = (endTime - startTime) / 1000; // Mikrosaniye cinsinden süreyi hesaplar
		System.out.println("Programın çalışma süresi: " + durationInMicroseconds + " mikrosaniye"); // Mikrosaniye cinsinden hesaplanan süreyi ekrana yazdırır

	}
}