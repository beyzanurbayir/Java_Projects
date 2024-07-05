package odevOS; // Bu sınıfın odevOS paketi içinde bulunduğunu belirtiyor

import javax.swing.*; // Java'nın GUI (Graphical User Interface - Grafiksel Kullanıcı Arayüzü) elemanlarını içeren javax.swing paketinden sınıfları içeri alıyor
import javax.swing.table.DefaultTableModel; // DefaultTableModel sınıfını içeri alıyor. Bu sınıf, tabloları yönetmek için kullanılıyor
import java.awt.*; // AWT (Abstract Window Toolkit) paketinden sınıfları içeri alıyor. Bu sınıflar, grafiksel kullanıcı arayüzü oluşturmak için kullanılıyor
import java.awt.event.ActionEvent; // AWT olayları için ActionEvent sınıfını içeri alıyor
import java.awt.event.ActionListener; // AWT olay dinleyicileri için ActionListener arabirimini içeri alıyor
import java.util.Arrays; // Dizilerle ilgili işlemler için Arrays sınıfını içeri alıyor
import java.util.Random; // Rastgele sayı üretmek için Random sınıfını içeri alıyor

public class ProblemC2UI extends JFrame {
    private JTextField nValueField; // N değerini almak için metin alanı oluşturuyor
    private JTextArea matrixArea; // Matrisi göstermek için metin alanı oluşturuyor
    private JTable table; // Tablo oluşturuyor
    private JLabel durationLabel; // Çalışma süresini gösteren etiket oluşturuyor

    public ProblemC2UI() {
        setTitle("Problem C.2"); // Pencere başlığı oluşturuyor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Pencere kapatıldığında programın sonlanması sağlıyor
        setSize(800, 600); // Pencere boyutu ayarlıyor
        setLayout(new BorderLayout()); // Pencere düzeni ayarlıyor

        JPanel topPanel = new JPanel(); // Üst panel oluşturuyor
        JLabel nLabel = new JLabel("N değerini giriniz: "); // N değerini girmek için etiket olusturuyor
        nValueField = new JTextField(10); // N değerini girmek için metin alanı oluşturuyor
        JButton calculateButton = new JButton("Hesapla"); // Hesaplama düğmesi oluşturuyor
        durationLabel = new JLabel("Programın çalışma süresi: "); // Çalışma süresini göstermek için etiket oluşturuyor
        topPanel.add(nLabel); // Üst panelin içine N etiketini ekliyor
        topPanel.add(nValueField); // Üst panelin içine N değerini almak için metin alanını ekliyor
        topPanel.add(calculateButton); // Üst panelin içine hesaplama düğmesini ekliyor
        topPanel.add(durationLabel); // Üst panelin içine çalışma süresini gösteren etiketi ekliyor
        calculateButton.addActionListener(new ActionListener() { // Hesaplama düğmesine tıklanınca yapılacak işlemleri tanımlıyor
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate(); // Hesaplama metodunu çağıryor
            }
        });
        add(topPanel, BorderLayout.NORTH); // Üst paneli pencerenin kuzey yönüne ekliyor

        matrixArea = new JTextArea(); // Matrisi göstermek için metin alanı oluşturuyor
        matrixArea.setEditable(false); // Metin alanının düzenlenemez özellikte olmasını sağlıyor
        JScrollPane matrixScrollPane = new JScrollPane(matrixArea); // Metin alanını sürüklenebilir hale getiriyor
        add(matrixScrollPane, BorderLayout.CENTER); // Matrisi gösteren metin alanını pencerenin ortasına ekliyor

        String[] columnNames = {"Satır Sayısı", "Max Eleman", "Min Eleman", "Toplam", "Ortalama"}; // Tablo sütun başlıklarını yazıyor
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Tablo modelini oluşturuyor
        table = new JTable(model); // Tabloyu oluşturuyor
        JScrollPane tableScrollPane = new JScrollPane(table); // Tabloyu sürüklenebilir hale getiriyor
        add(tableScrollPane, BorderLayout.SOUTH); // Tabloyu pencerenin güney yönüne ekliyor
    }

    private void calculate() {
        long startTime = System.nanoTime(); // Hesaplama başlangıç zamanını alıyor

        int N = Integer.parseInt(nValueField.getText()); // N değerini metin alanından alıyor

        int[][] matrix = new int[N][N]; // Matris oluşturuyor
        Random random = new Random(); // Rastgele sayı üretmek için nesne oluşturuyor
        StringBuilder matrixString = new StringBuilder(); // Matrisi göstermek için bir StringBuilder oluşturuyor
        for (int i = 0; i < N; i++) { // Matrisi dolduruyor
            for (int j = 0; j < N; j++) {
                matrix[i][j] = random.nextInt(10) + 1; // 1 ile 10 arasında rastgele sayılarla dolduruyor
                matrixString.append(matrix[i][j]).append("\t"); // Matrisi metin olarak oluşturuyor
            }
            matrixString.append("\n"); // Satır sonu karakteri ekliyor
        }
        matrixArea.setText(matrixString.toString()); // Matrisi gösteren metin alanını güncelliyor

        DefaultTableModel model = (DefaultTableModel) table.getModel(); // Tablonun modelini alıyor
        model.setRowCount(0); // Tabloyu temizliyor

        for (int i = 0; i < N; i++) { // Her satır için
            int[] row = matrix[i]; // Satırı alıyor
            int max = row[0]; // Max değeri tutmak için ilk elemanı atar
            int min = row[0]; // Min değeri tutmak için ilk elemanı atar
            int sum = 0; // Toplamı tutmak için sıfırlar

            for (int j = 0; j < N; j++) { // Her satır için maksimum, minimum ve toplam değerler hesaplıyor
                if (row[j] > max) {
                    max = row[j]; // Maksimum değer güncellenir
                }
                if (row[j] < min) {
                    min = row[j]; // Minimum değer güncellenir
                }
                sum += row[j]; // Satırın toplamı hesaplanır
            }

            double average = (double) sum / N; // Ortalama hesaplanır

            model.addRow(new Object[]{i + 1, max, min, sum, average});  // Tabloya satırın indeksi, maksimum değer, minimum değer, toplam ve ortalama eklenir
        }

        long endTime = System.nanoTime(); // Bitiş zamanını al
        long durationInMicroseconds = (endTime - startTime) / 1000; // Mikrosaniye cinsinden süreyi hesapla
        durationLabel.setText("Programın Çalışma Süresi: " + durationInMicroseconds + " mikrosaniye"); // Çalışma süresini gösteren etiketi güncelle
        }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProblemC2UI().setVisible(true); // Arayüzü oluştur ve görünür yapıyor
            }
        });
    }
}