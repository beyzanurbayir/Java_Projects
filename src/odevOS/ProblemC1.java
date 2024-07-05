package odevOS; // odevOS isimli paket tanımlanıyor.

import java.util.Random; // Rastgele sayılar üretmek için gerekli olan Random kütüphanesi ekleniyor.
import java.util.Scanner; // Kullanıcıdan veri alabilmek için gerekli olan Scanner kütüphanesi ekleniyor.

public class ProblemC1 { // ProblemC1 adında bir sınıf tanımlanıyor.
	public static void main(String[] args) { // Ana program başlangıcı
		long startTime = System.nanoTime(); // Başlangıç zamanını kaydeder

		Scanner scanner = new Scanner(System.in); // Kullanıcıdan veri alabilmek için Scanner sınıfından bir nesne oluşturuluyor.

		System.out.print("N: "); // Kullanıcıya N değerini girmesi isteniyor.
		int N = scanner.nextInt(); // Kullanıcıdan alınan N değeri saklanıyor.
		System.out.print("M: "); // Kullanıcıya M değerini girmesi isteniyor.
		int M = scanner.nextInt(); // Kullanıcıdan alınan M değeri saklanıyor.
		System.out.println(); // Boş satır ekleniyor.

		Random random = new Random(); // Rastgele sayılar üretebilmek için Random sınıfından bir nesne oluşturuluyor.

		int[] array = new int[N]; // N uzunluğunda bir dizi oluşturuluyor.
		for (int i = 0; i < N; i++) { // Dizi elemanları rastgele değerlerle dolduruluyor.
			array[i] = random.nextInt(10) + 1; // Her bir elemana 1 ile 10 arasında rastgele bir değer atanıyor.
		}

		int subSetCount = N / M; // Alt küme sayısı hesaplanıyor.

		int startIndex = 0; // Alt küme hesaplama için başlangıç indisini tutacak değişken tanımlanıyor.
		for (int i = 1; i <= subSetCount; i++) { // Alt kümeler üzerinde döngü başlatılıyor.
			System.out.print("Alt Küme " + i + ": ["); // Alt kümenin başlangıcı ekrana yazdırılıyor.
			for (int j = startIndex; j < startIndex + M; j++) { // Alt küme elemanları üzerinde döngü başlatılıyor.
				System.out.print(array[j]); // Alt küme elemanı ekrana yazdırılıyor.
				if (j < startIndex + M - 1) { // Eğer son eleman değilse
					System.out.print(", "); // Virgül ekleniyor.
				}
			}
			System.out.println("]"); // Alt kümenin sonu ekrana yazdırılıyor.
			startIndex += M; // Başlangıç indisinde ilerleme sağlanıyor.
		}
		System.out.println(); // Boş satır ekleniyor.

		startIndex = 0; // Alt küme hesaplama için başlangıç indisini sıfırla.
		for (int i = 1; i <= subSetCount; i++) { // Alt kümeler üzerinde döngü başlatılıyor.
			int max = array[startIndex]; // Alt kümenin maksimum değeri
			int min = array[startIndex]; // Alt kümenin minimum değeri
			int total = 0; // Alt kümenin elemanları toplamı

			for (int j = startIndex; j < startIndex + M; j++) { // Alt küme elemanları üzerinde döngü başlatılıyor.
				if (array[j] > max) { // Eğer eleman maksimum değerden büyükse
					max = array[j]; // Maksimum değeri güncelle
				}
				if (array[j] < min) { // Eğer eleman minimum değerden küçükse
					min = array[j]; // Minimum değeri güncelle
				}
				total += array[j]; // Elemanları topla
			}

			double average = (double) total / M; // Alt kümenin elemanlarının ortalaması hesaplanıyor.

			System.out.println(i+ ". Alt Kümenin En Büyük Elemanı: " + max); // Alt kümenin en büyük elemanı ekrana yazdırılıyor.
			System.out.println(i+ ". Alt Kümenin En Küçük Elemanı: " + min); // Alt kümenin en küçük elemanı ekrana yazdırılıyor.
			System.out.println(i+ ". Alt Kümenin Elemanları Toplamı: " + total); // Alt kümenin elemanları toplamı ekrana yazdırılıyor.
			System.out.println(i+ ". Alt Kümenin Elemanlarının Ortalaması: " + average); // Alt kümenin elemanlarının ortalaması ekrana yazdırılıyor.
			System.out.println(); // Boş satır ekleniyor.

			startIndex += M; // Başlangıç indisinde ilerleme sağlanıyor.
		}

		scanner.close(); // Kullanılan Scanner nesnesi kapatılıyor.

		long endTime = System.nanoTime(); // Bitiş zamanını kaydediyor.
		long durationInMicroseconds = (endTime - startTime) / 1000; // Süreyi mikrosaniye cinsinden hesaplıyor.
		System.out.println("Programın çalışma süresi: " + durationInMicroseconds + " mikrosaniye"); // Çalışma süresini ekrana yazdırıyor.
	}
}