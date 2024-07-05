package odevOS; // odevOS isimli paket tanımlanıyor.

import java.util.Arrays; // Java'da diziler üzerinde işlem yapabilmek için gerekli olan Arrays kütüphanesi ekleniyor.
import java.util.Random; // Rastgele sayılar üretmek için gerekli olan Random kütüphanesi ekleniyor.
import java.util.Scanner; // Kullanıcıdan veri alabilmek için gerekli olan Scanner kütüphanesi ekleniyor

public class ProblemA { // ProblemA adında bir sınıf tanımlanıyor.
	static class AltKumeIsParcasi extends Thread { // AltKumeIsParcasi adında bir iç içe sınıf tanımlanıyor, Thread sınıfından miras alınıyor.
		private int[] altKume; // Alt kümenin tutulacağı dizi tanımlanıyor.
		private int altKumeIndex; // Alt kümenin indis numarası tanımlanıyor.
		private int[][] sonuclar; // Sonuçların tutulacağı 2 boyutlu dizi tanımlanıyor.

		public AltKumeIsParcasi(int[] altKume, int altKumeIndex, int[][] sonuclar) { // AltKumeIsParcasi sınıfının kurucu metodu tanımlanıyor.
			this.altKume = altKume; // Parametre olarak gelen altKume değeri sınıfın altKume değişkenine atanıyor.
			this.altKumeIndex = altKumeIndex; // Parametre olarak gelen altKumeIndex değeri sınıfın altKumeIndex değişkenine atanıyor.
			this.sonuclar = sonuclar; // Parametre olarak gelen sonuclar değeri sınıfın sonuclar değişkenine atanıyor.
		}

		public void run() { // Thread'in çalışma metodu tanımlanıyor.
			int max = altKume[0]; // Max değeri, alt kümenin ilk elemanıyla başlatılıyor.
			int min = altKume[0]; // Min değeri, alt kümenin ilk elemanıyla başlatılıyor.
			int toplam = 0; // Toplam değeri sıfırlanıyor.

			for (int i = 0; i < altKume.length; i++) { // Alt küme üzerinde döngü başlatılıyor.
				if (altKume[i] > max) { // Eğer alt küme elemanı max değerinden büyükse
					max = altKume[i]; // max değeri güncelleniyor.
				}
				if (altKume[i] < min) { // Eğer alt küme elemanı min değerinden küçükse
					min = altKume[i]; // min değeri güncelleniyor.
				}
				toplam += altKume[i]; // Alt küme elemanları toplanıyor
			}

			sonuclar[altKumeIndex][0] = max; // Sonuçlar dizisinin ilgili indisine max değeri atanıyor.
			sonuclar[altKumeIndex][1] = min; // Sonuçlar dizisinin ilgili indisine min değeri atanıyor.
			sonuclar[altKumeIndex][2] = toplam; // Sonuçlar dizisinin ilgili indisine toplam değeri atanıyor.
			sonuclar[altKumeIndex][3] = toplam / altKume.length; // Sonuçlar dizisinin ilgili indisine ortalama değeri atanıyor.
		}
	}

	public static void main(String[] args) { // Ana program başlangıcı
		Scanner scanner = new Scanner(System.in); // Kullanıcıdan veri alabilmek için Scanner sınıfından bir nesne oluşturuluyor.

		long startTime = System.nanoTime(); // Program başlangıç zamanı kaydediliyor.

		System.out.print("N: "); // Kullanıcıya N değerini girmesi isteniyor.
		int N = scanner.nextInt(); // Kullanıcıdan alınan N değeri saklanıyor.

		System.out.print("M: "); // Kullanıcıya M değerini girmesi isteniyor.
		int M = scanner.nextInt(); // Kullanıcıdan alınan M değeri saklanıyor

		System.out.println(); // Boş satır ekleniyor.

		int[] dizi = new int[N]; // N uzunluğunda bir dizi oluşturuluyor.
		Random random = new Random(); // Rastgele sayılar üretebilmek için Random sınıfından bir nesne oluşturuluyor.
		for (int i = 0; i < N; i++) { // Dizi elemanları rastgele değerlerle dolduruluyor.
			dizi[i] = random.nextInt(10) + 1; // Her bir elemana 1 ile 10 arasında rastgele bir değer atanıyor.
		}

		int[][] sonuclar = new int[N / M][4]; // Sonuçları tutmak için bir dizi oluşturuluyor.
		AltKumeIsParcasi[] threads = new AltKumeIsParcasi[N / M]; // Threadlerin tutulacağı dizi oluşturuluyor.

		for (int i = 0; i < N / M; i++) { // Alt kümeleri hesaplamak için threadler oluşturuluyor.
			int[] altKume = Arrays.copyOfRange(dizi, i * M, (i + 1) * M); // Ana diziden alt kume dizisi oluşturuluyor
			threads[i] = new AltKumeIsParcasi(altKume, i, sonuclar); // Thread oluşturuluyor ve başlatılıyor.
			threads[i].start(); // Thread başlatılıyor
		}

		for (int i = 0; i < N / M; i++) { // Threadlerin tamamlanması bekleniyor.
			try { 
				threads[i].join(); // Threadlerin tamamlanmasını bekliyoruz
			} catch (InterruptedException e) { // Thread'in beklenmedik bir şekilde kesilmesi durumunda bu blok çalışıyor.
				e.printStackTrace(); // Kesintiye uğrayan thread'in hatasını konsola yazdırıyor. Hata izleme ve hata ayıklama için kullanılır.
			}
		}

		// Sonuçlar ekrana yazdırılıyor.
		for (int i = 0; i < N / M; i++) {
			System.out.println((i+1)+". Alt Kümenin En Büyük Elemanı: " + sonuclar[i][0]);
			System.out.println((i+1)+". Alt Kümenin En Küçük Elemanı: " + sonuclar[i][1]);
			System.out.println((i+1)+". Alt Kümenin Elemanlarının Toplamı: " + sonuclar[i][2]);
			System.out.println((i+1)+". Alt Kümenin Elemanlarının Ortalaması: " + sonuclar[i][3]);
			System.out.println();
		}

		scanner.close(); // Kullanılan Scanner nesnesini kapatıyor, bellek kaynaklarını serbest bırakıyor.

		long endTime = System.nanoTime(); // Programın bitiş zamanını alıyor.
		long durationInMicroseconds = (endTime - startTime) / 1000; // Programın çalışma süresini mikrosaniye cinsinden hesaplıyor.
		System.out.println("Programın çalışma süresi: " + durationInMicroseconds + " mikrosaniye"); //Programın çalışma süresini ekrana yazdırıyor.

	}
}