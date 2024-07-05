package odevOS; // Bu sınıfın odevOS paketi içinde bulunduğunu belirtir.

import javax.swing.*; // Java'nın GUI (Graphical User Interface - Grafiksel Kullanıcı Arayüzü) elemanlarını içeren javax.swing paketinden sınıfları içeri alır.
import javax.swing.table.DefaultTableModel; // DefaultTableModel sınıfını içeri alır. Bu sınıf, tabloları yönetmek için kullanılır.
import java.awt.*; // AWT (Abstract Window Toolkit) paketinden sınıfları içeri alır. Bu sınıflar, grafiksel kullanıcı arayüzü oluşturmak için kullanılır.
import java.awt.event.ActionEvent; // AWT olayları için ActionEvent sınıfını içeri alır.
import java.awt.event.ActionListener; // AWT olay dinleyicileri için ActionListener arabirimini içeri alır.
import java.util.Arrays; // Dizilerle ilgili işlemler için Arrays sınıfını içeri alır.
import java.util.Random; // Rastgele sayı üretmek için Random sınıfını içeri alır.

//ProblemC1UI sınıfı, alt küme işlemlerini kullanıcı arayüzü üzerinde gerçekleştiren bir sınıftır.
public class ProblemC1UI extends JFrame {
	private JTextField nValueField; // N değerini almak için metin alanı
	private JTextField mValueField; // M değerini almak için metin alanı
	private JTextArea subsetsArea; // Alt kümeleri göstermek için metin alanı
	private JTable table; // Sonuçları göstermek için tablo
	private JLabel durationLabel; // Programın çalışma süresini göstermek için etiket

	// Constructor (Yapıcı metot)
	public ProblemC1UI() {
		setTitle("Problem C.1"); // Başlık
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kapatma davranışı
		setSize(800, 600); // Boyut
		getContentPane().setLayout(new BorderLayout()); // Layout

		JPanel topPanel = new JPanel(); // Üst panel oluşturuluyor
		JLabel nLabel = new JLabel("N: "); // N değeri için etiket
		nValueField = new JTextField(10); // N değeri için metin alanı
		JLabel mLabel = new JLabel("M: "); // M değeri için etiket
		mValueField = new JTextField(10); // M değeri için metin alanı
		JButton calculateButton = new JButton("Hesapla"); // Hesaplama butonu
		durationLabel = new JLabel("Programın Çalışma Süresi: "); // Çalışma süresi etiketi
		topPanel.add(nLabel); // N etiketini panele ekle
		topPanel.add(nValueField); // N metin alanını panele ekle
		topPanel.add(mLabel); // M etiketini panele ekle
		topPanel.add(mValueField); // M metin alanını panele ekle
		topPanel.add(calculateButton); // Hesaplama butonunu panele ekle
		topPanel.add(durationLabel); // Çalışma süresi etiketini panele ekle
		calculateButton.addActionListener(new ActionListener() { // Hesaplama butonuna tıklanınca çalışacak olan olay dinleyicisi
			@Override
			public void actionPerformed(ActionEvent e) {
				calculate(); // Hesaplama metodunu çağır
			}
		});
		getContentPane().add(topPanel, BorderLayout.NORTH); // Üst paneli pencerenin kuzey yönüne ekle

		subsetsArea = new JTextArea(); // Alt kümeleri göstermek için bir metin alanı oluştur
		subsetsArea.setEditable(false); // Metin alanının düzenlenemez özellikte olmasını sağla
		JScrollPane subsetsScrollPane = new JScrollPane(subsetsArea); // Metin alanını sürüklenebilir hale getir
		getContentPane().add(subsetsScrollPane, BorderLayout.CENTER); // Alt kümeleri gösteren metin alanını pencerenin ortasına ekle

		String[] columnNames = {"Alt Küme", "Max Eleman", "Min Eleman", "Toplam", "Ortalama"}; // Tablo sütun başlıklarını tanımla
		DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Tablo modelini oluştur
		table = new JTable(model); // Tabloyu oluştur
		JScrollPane tableScrollPane = new JScrollPane(table); // Tabloyu sürüklenebilir hale getir
		getContentPane().add(tableScrollPane, BorderLayout.SOUTH); // Tabloyu pencerenin güney yönüne ekle
	}

	private void calculate() {
		long startTime = System.nanoTime(); // Hesaplama başlangıç zamanını al

		int N = Integer.parseInt(nValueField.getText()); // N değerini metin alanından al
		int M = Integer.parseInt(mValueField.getText()); // M değerini metin alanından al

		Random random = new Random(); // Rastgele sayı üretmek için nesne oluştur
		int[] array = new int[N]; // Dizi oluştur
		StringBuilder subsetsString = new StringBuilder(); // Alt kümeleri göstermek için bir StringBuilder oluştur
		for (int i = 0; i < N; i++) { // Diziyi doldur
			array[i] = random.nextInt(10) + 1; // 1 ile 10 arasında rastgele sayılarla doldur
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel(); // Tablonun modelini al
		model.setRowCount(0); // Tabloyu temizle

		for (int i = 0; i < N / M; i++) { // Alt kümeleri oluşturmak için döngü başlatılıyor
			int[] subset = Arrays.copyOfRange(array, i * M, (i + 1) * M); // Dizinin belirli bir alt kümesini oluştur
			StringBuilder subsetString = new StringBuilder(); // Alt küme metnini göstermek için bir StringBuilder oluştur
			subsetString.append((i + 1)).append(". Alt Küme: ").append(Arrays.toString(subset)).append("\n"); // Alt küme metnini oluştur
			subsetsString.append(subsetString); // Alt küme metnini ana metin olarak ekle
			int max = subset[0]; // Max değeri tutmak için ilk elemanı ata
			int min = subset[0]; // Min değeri tutmak için ilk elemanı ata
			int sum = 0; // Toplamı tutmak için sıfırla
			for (int j = 0; j < M; j++) { // Alt kümedeki elemanları gez
				if (subset[j] > max) { // Max değeri güncelle
					max = subset[j];
				}
				if (subset[j] < min) { // Min değeri güncelle
					min = subset[j];
				}
				sum += subset[j]; // Toplamı güncelle
			}
			double average = (double) sum / M; // Ortalamayı hesapla
			model.addRow(new Object[]{(i + 1), max, min, sum, average}); // Tabloya alt küme bilgilerini ekle
		}

		subsetsArea.setText(subsetsString.toString()); // Alt kümeleri gösteren metin alanını güncelle

		long endTime = System.nanoTime(); // Bitiş zamanını al
		long durationInMicroseconds = (endTime - startTime) / 1000; // Mikrosaniye cinsinden süreyi hesapla
		durationLabel.setText("Programın Çalışma Süresi: " + durationInMicroseconds + " mikrosaniye"); // Çalışma süresini gösteren etiketi güncelle
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ProblemC1UI().setVisible(true); // UI'yi görünür yap
			}
		});
	}
} 