package odevOS; // odevOS isimli paket tanımlanıyor.

import java.util.Arrays; // Diziler üzerinde işlem yapmak için gerekli olan Arrays kütüphanesi ekleniyor.
import java.util.Random; // Rastgele sayılar üretmek için gerekli olan Random kütüphanesi ekleniyor.
import java.util.Scanner; // Kullanıcıdan veri alabilmek için gerekli olan Scanner kütüphanesi ekleniyor.

public class ProblemC2 { // ProblemC2 adında bir sınıf tanımlanıyor.
	public static void main(String[] args) { // Ana program başlangıcı
		long startTime = System.nanoTime(); // Başlangıç zamanını kaydediyor.

		Scanner scanner = new Scanner(System.in); // Kullanıcıdan veri alabilmek için Scanner sınıfından bir nesne oluşturuluyor.
		System.out.print("N: "); // Kullanıcıya N değerini girmesi isteniyor.
		int N = scanner.nextInt(); // Kullanıcıdan alınan N değeri saklanıyor.
		System.out.println(); // Boş satır ekleniyor.

		int[][] matris = new int[N][N]; // NxN'lik bir matris oluşturuluyor.
		Random random = new Random(); // Rastgele sayılar üretebilmek için Random sınıfından bir nesne oluşturuluyor.
		for (int i = 0; i < N; i++) { // Matrisin satırları üzerinde döngü başlatılıyor.
			for (int j = 0; j < N; j++) { // Matrisin sütunları üzerinde döngü başlatılıyor.
				matris[i][j] = random.nextInt(10) + 1; // Her bir elemana 1 ile 10 arasında rastgele bir değer atanıyor.
			}
		}

		for (int i = 0; i < N; i++) { // Matrisin satırları üzerinde döngü başlatılıyor.
			System.out.println("Satır " + (i + 1) + ": " + Arrays.toString(matris[i])); // Her satırın içeriği ekrana yazdırılıyor.
		}
		System.out.println(); // Boş satır ekleniyor.

		for (int i = 0; i < N; i++) { // Matrisin satırları üzerinde döngü başlatılıyor.
			int[] satir = matris[i]; // İlgili satır matristen alınıyor.
			System.out.println((i+1)+". Satır İçin En Büyük Eleman: " + findMax(satir)); // Satırın en büyük elemanı ekrana yazdırılıyor.
			System.out.println((i+1)+". Satır İçin En Küçük Eleman: " + findMin(satir)); // Satırın en küçük elemanı ekrana yazdırılıyor.
			System.out.println((i+1)+". Satır İçin Elemanların Toplamı: " + calculateSum(satir)); // Satırın elemanlarının toplamı ekrana yazdırılıyor.
			System.out.println((i+1)+". Satır İçin Elemanların Ortalaması: " + calculateAverage(satir)); // Satırın elemanlarının ortalaması ekrana yazdırılıyor.
			System.out.println(); // Boş satır ekleniyor.
		}

		scanner.close(); // Kullanılan Scanner nesnesi kapatılıyor.

		long endTime = System.nanoTime(); // Bitiş zamanını alır.
		long durationInMicroseconds = (endTime - startTime) / 1000; // Süreyi mikrosaniye cinsinden hesaplar.
		System.out.println("Programın çalışma süresi: " + durationInMicroseconds + " mikrosaniye"); // Çalışma süresini ekrana yazdırır.
	}

	// Bir dizideki maksimum değeri bulan metot
	public static int findMax(int[] arr) {
		int max = arr[0]; // İlk elemanı başlangıçta maksimum kabul ediyoruz.
		for (int i = 1; i < arr.length; i++) { // Dizinin geri kalan elemanları üzerinde döngü başlatılıyor.
			if (arr[i] > max) { // Eğer dizinin o anki elemanı mevcut maksimumdan büyükse
				max = arr[i]; // Maksimum değeri güncelliyoruz.
			}
		}
		return max; // Bulunan maksimum değeri döndürüyoruz.
	}

	// Bir dizideki minimum değeri bulan metot
	public static int findMin(int[] arr) {
		int min = arr[0]; // İlk elemanı başlangıçta minimum kabul ediyoruz.
		for (int i = 1; i < arr.length; i++) { // Dizinin geri kalan elemanları üzerinde döngü başlatılıyor.
			if (arr[i] < min) { // Eğer dizinin o anki elemanı mevcut minimumdan küçükse
				min = arr[i]; // Minimum değeri güncelliyoruz.
			}
		}
		return min; // Bulunan minimum değeri döndürüyoruz.
	}

	// Bir dizideki elemanların toplamını bulan metot
	public static int calculateSum(int[] arr) {
		int sum = 0; // Toplamı saklayacak değişkeni başlangıçta sıfırlıyoruz.
		for (int num : arr) { // Dizi üzerinde for-each döngüsü ile geziniyoruz.
			sum += num; // Her elemanı toplam değişkenine ekliyoruz.
		}
		return sum; // Bulunan toplamı döndürüyoruz.
	}

	// Bir dizideki elemanların ortalamasını bulan metot
	public static double calculateAverage(int[] arr) {
		int sum = calculateSum(arr); // Elemanların toplamını hesaplıyoruz.
		return (double) sum / arr.length; // Toplamı eleman sayısına bölerek ortalamayı buluyoruz.
	}
}