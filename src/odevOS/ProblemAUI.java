package odevOS; // odevOS isimli paket tanımlanıyor.

import javax.swing.*; // Swing kütüphanesinden sınıflar ekleniyor.
import javax.swing.table.DefaultTableModel; // Swing'in tablo model sınıfı ekleniyor.
import java.awt.*; // AWT kütüphanesinden sınıflar ekleniyor.
import java.awt.event.ActionEvent; // ActionEvent sınıfı ekleniyor.
import java.awt.event.ActionListener; // ActionListener sınıfı ekleniyor.
import java.util.Arrays; // Diziler üzerinde işlem yapmak için gerekli olan Arrays kütüphanesi ekleniyor.
import java.util.Random; // Rastgele sayılar üretmek için gerekli olan Random kütüphanesi ekleniyor.

public class ProblemAUI extends JFrame { // ProblemA2 adında bir JFrame sınıfı tanımlanıyor.
	private JTextField nValueField; // N değerini alacak JTextField nesnesi tanımlanıyor.
	private JTextField mValueField; // M değerini alacak JTextField nesnesi tanımlanıyor.
	private JTextArea subsetsArea; // Alt kümeleri gösterecek JTextArea nesnesi tanımlanıyor.
	private JTable table; // Sonuçları gösterecek JTable nesnesi tanımlanıyor.
	private JLabel durationLabel; // Programın çalışma süresini gösterecek JLabel nesnesi tanımlanıyor

	public ProblemAUI() { // Yapıcı metot başlangıcı
		setTitle("Problem A"); // Pencere başlığı belirleniyor.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Kapatma işlemi tanımlanıyor.
		setSize(800, 600); // Pencere boyutu ayarlanıyor.
		getContentPane().setLayout(new BorderLayout()); // İçerik düzeni BorderLayout olarak belirleniyor.

		JPanel topPanel = new JPanel(); // Üst panel oluşturuluyor.
		JLabel nLabel = new JLabel("N: "); // N değeri için etiket oluşturuluyor.
		nValueField = new JTextField(10); // N değeri için JTextField oluşturuluyor.
		JLabel mLabel = new JLabel("M: "); // M değeri için etiket oluşturuluyor.
		mValueField = new JTextField(10); // M değeri için JTextField oluşturuluyor.
		JButton calculateButton = new JButton("Hesapla"); // Hesapla butonu oluşturuluyor.
		durationLabel = new JLabel("Programın çalışma süresi: "); // Çalışma süresi etiketi oluşturuluyor.
		topPanel.add(nLabel); // N etiketi üst panele ekleniyor.
		topPanel.add(nValueField); // N değeri alanı üst panele ekleniyor.
		topPanel.add(mLabel); // M etiketi üst panele ekleniyor.
		topPanel.add(mValueField); // M değeri alanı üst panele ekleniyor.
		topPanel.add(calculateButton); // Hesapla butonu üst panele ekleniyor.
		topPanel.add(durationLabel); // Çalışma süresi etiketi üst panele ekleniyor.
		calculateButton.addActionListener(new ActionListener() {  // Hesapla butonuna action listener ekleniyor
			@Override
			public void actionPerformed(ActionEvent e) {// Butona tıklandığında yapılacak işlemler tanımlanıyor.
				calculate(); // Hesaplama metodunu çağırıyor.
			}
		});
		getContentPane().add(topPanel, BorderLayout.NORTH); // Üst panel içeriğe NORTH konumuna ekleniyor

		subsetsArea = new JTextArea(); // Alt kümeleri göstermek için JTextArea oluşturuluyor.
		subsetsArea.setEditable(false); // Alt kümelerin düzenlenemez olmasını sağlıyor.
		JScrollPane subsetsScrollPane = new JScrollPane(subsetsArea); // Alt kümeleri gösteren bileşen JScrollPane içerisine ekleniyor.
		getContentPane().add(subsetsScrollPane, BorderLayout.CENTER); // Alt kümelerin gösterileceği panel içeriğe CENTER konumuna ekleniyor.

		String[] columnNames = {"Alt Küme", "Max Eleman", "Min Eleman", "Toplam", "Ortalama"}; // Tablo sütun başlıkları belirleniyor.
		DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Tablo modeli oluşturuluyor.
		table = new JTable(model); // Tablo oluşturuluyor.
		JScrollPane tableScrollPane = new JScrollPane(table); // Tabloyu gösteren bileşen JScrollPane içerisine ekleniyor.
		getContentPane().add(tableScrollPane, BorderLayout.SOUTH); // Tablonun gösterileceği panel içeriğe SOUTH konumuna ekleniyor.
	}

	private void calculate() { // Hesaplama metodu tanımlanıyor.
		long startTime = System.nanoTime(); // Başlangıç zamanı alınıyor.

		int N = Integer.parseInt(nValueField.getText()); // Kullanıcıdan alınan N değeri alınıyor.
		int M = Integer.parseInt(mValueField.getText()); // Kullanıcıdan alınan M değeri alınıyor.

		Random random = new Random(); // Rastgele sayılar üretmek için Random sınıfından bir nesne oluşturuluyor.
		int[] array = new int[N]; // N elemanlı bir dizi oluşturuluyor.
		StringBuilder subsetsString = new StringBuilder(); // Alt kümeleri göstermek için bir StringBuilder oluşturuluyor.
		for (int i = 0; i < N; i++) { // Dizi elemanlarına rastgele değerler atanıyor.
			array[i] = random.nextInt(10) + 1; // 1 ile 10 arasında rastgele sayılar atanıyor.
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel(); // Tablo modeli alınıyor.
		model.setRowCount(0); // Tablo temizleniyor.

		for (int i = 0; i < N / M; i++) { // Alt kümeler için döngü başlatılıyor.
			int[] subset = Arrays.copyOfRange(array, i * M, (i + 1) * M); // Alt küme oluşturuluyor.
			StringBuilder subsetString = new StringBuilder(); // Alt küme metnini göstermek için bir StringBuilder

			// Alt kümenin metinsel gösterimi oluşturuluyor
			subsetString.append((i + 1)).append(". Alt Küme: ").append(Arrays.toString(subset)).append("\n");
			subsetsString.append(subsetString);

			int max = subset[0];
			int min = subset[0];
			int sum = 0;

			// Alt kümenin maksimum, minimum, toplam ve ortalama değerleri hesaplanıyor
			for (int j = 0; j < M; j++) {
				if (subset[j] > max) {
					max = subset[j];
				}
				if (subset[j] < min) {
					min = subset[j];
				}
				sum += subset[j];
			}

			double average = (double) sum / M;

			// Tabloya alt kümenin bilgileri ekleniyor
			model.addRow(new Object[]{(i + 1), max, min, sum, average});
		}

		subsetsArea.setText(subsetsString.toString()); // Alt kümelerin metinsel gösterimi JTextArea'ya ekleniyor

		long endTime = System.nanoTime(); // Bitiş zamanı alınıyor
		long durationInMicroseconds = (endTime - startTime) / 1000; // Çalışma süresi hesaplanıyor
		durationLabel.setText("Programın çalışma süresi: " + durationInMicroseconds + " mikrosaniye"); // Çalışma süresi etikete yazdırılıyor

	}

	public static void main(String[] args) { // Ana başlatıcı metot
		SwingUtilities.invokeLater(new Runnable() { // Arayüz işlemlerini ana thread dışında yapmak için SwingUtilities kullanılıyor
			public void run() { // Yeni bir runnable nesnesi oluşturuluyor ve run metodu tanımlanıyor
				new ProblemAUI().setVisible(true); // ProblemAUI sınıfından yeni bir nesne oluşturuluyor ve görünür hale getiriliyor
			}
		});
	}
}