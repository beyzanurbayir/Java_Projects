package odevOS;// odevOS isimli paket tanımlanıyor.

import javax.swing.*; // Swing bileşenlerini kullanmak için gerekli paketi ekliyor
import javax.swing.table.DefaultTableModel; // Tabloları yönetmek için gerekli sınıfı ekliyor
import java.awt.*; // Grafiksel kullanıcı arayüzü oluşturmak için gerekli paketi ekliyor
import java.awt.event.ActionEvent; // Olaylar için gerekli sınıfı ekliyor
import java.awt.event.ActionListener; // Olay dinleyicileri için gerekli arabirimi oluşturur
import java.util.Random; // Rastgele sayı üretmek için gerekli sınıfı ekliyor

// ProblemBUI sınıfı, matris işlemlerini kullanıcı arayüzü üzerinde gerçekleştiren bir sınıf
public class ProblemBUI extends JFrame {

    private static final long serialVersionUID = -3200125781555618130L;
    private JTextField nValueField; // Kullanıcıdan N değerini almak için metin alanı oluşturuyor
    private JTextArea matrixArea; // Matrisi göstermek için metin alanı oluşturuyor
    private JTable table; // Sonuçları göstermek için tablo oluşturuyor
    private JLabel durationLabel; // Programın çalışma süresini göstermek için etiket oluşturuyor

    // Constructor
    public ProblemBUI() {
        setTitle("Problem B"); // Başlık oluşturuyor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kapatma davranışı oluşturuyor
        setSize(800, 600); // Boyut oluşturuyor
        setLayout(new BorderLayout()); // Layout oluşturuyor

        JPanel topPanel = new JPanel(); // Üst panel oluşturuyor
        JLabel nLabel = new JLabel("N değerini giriniz: "); // Etiket oluşturuyor
        nValueField = new JTextField(10); // Metin alanı oluşturuyor
        JButton calculateButton = new JButton("Hesapla"); // Hesaplama butonu oluşturuyor
        durationLabel = new JLabel("Programın çalışma süresi: "); // Etiket oluşturuyor
        topPanel.add(nLabel); // Etiketi panele ekliyor
        topPanel.add(nValueField); // Metin alanını panele ekliyor
        topPanel.add(calculateButton); // Hesaplama butonunu panele ekliyor
        topPanel.add(durationLabel); // Çalışma süresi etiketini panele ekliyor
        calculateButton.addActionListener(new ActionListener() { // Hesaplama butonuna tıklanınca çalışacak olan olayları belirliyor
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate(); // Hesaplama metodunu çağırıyor
            }
        });
        add(topPanel, BorderLayout.NORTH); // Üst paneli pencereye ekliyor

        matrixArea = new JTextArea(); // Metin alanı oluşturuyor
        matrixArea.setEditable(false); // Metin alanını düzenlenemez yapıyor
        JScrollPane matrixScrollPane = new JScrollPane(matrixArea); // Metin alanını kaydırılabilir yapıyor
        add(matrixScrollPane, BorderLayout.CENTER); // Metin alanını pencereye ekliyor

        String[] columnNames = {"Satır Sayısı", "Max Eleman", "Min Eleman", "Toplam", "Ortalama"}; // Tablo sütun başlıkları oluşturuyor
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Tablo modeli oluşturuyor
        table = new JTable(model); // Tabloyu oluşturuyor
        JScrollPane tableScrollPane = new JScrollPane(table); // Tabloyu kaydırılabilir yapıyor
        add(tableScrollPane, BorderLayout.SOUTH); // Tabloyu pencereye ekliyor
    }

    // Hesaplama metodunu tanımlıyor
    private void calculate() {
        long startTime = System.nanoTime(); // Başlangıç zamanını alıyor

        int N = Integer.parseInt(nValueField.getText()); // Kullanıcıdan N değerini alıyor

        int[][] matris = new int[N][N]; // N x N boyutunda bir matris oluşturuyor
        Random random = new Random(); // Rastgele sayı üretmek için Random nesnesi oluşturuyor
        StringBuilder matrixString = new StringBuilder(); // Matrisi göstermek için bir StringBuilder oluşturuyor
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matris[i][j] = random.nextInt(10) + 1; // Matrisin elemanlarına rastgele değerler atıyor
                matrixString.append(matris[i][j]).append("\t"); // Matrisi göstermek için metin ekleyerek oluşturuyor
            }
            matrixString.append("\n"); // Her satırın sonuna yeni satır karakteri ekliyor
        }
        matrixArea.setText(matrixString.toString()); // Matrisi metin alanına ekliyor

        DefaultTableModel model = (DefaultTableModel) table.getModel(); // Tablo modelini alıyor
        model.setRowCount(0); // Tabloyu temizliyor

        for (int i = 0; i < N; i++) {
            int[] satir = matris[i]; // Matrisin i. satırını alıyor
            int max = satir[0]; // Maksimum değeri ilk eleman olarak atıyor
            int min = satir[0]; // Minimum değeri ilk eleman olarak atıyor
            int toplam = 0; // Toplamı sıfırlıyor

            for (int j = 0; j < N; j++) {
                if (satir[j] > max) {
                    max = satir[j]; // Maksimum değeri güncelliyor
                }
                if (satir[j] < min) {
                    min = satir[j]; // Minimum değeri güncelliyor
                }
                toplam += satir[j]; // Elemanları topluyor
            }

            double ortalama = (double) toplam / N; // Ortalamayı hesaplıyor

            model.addRow(new Object[]{i + 1, max, min, toplam, ortalama}); // Satırı modele ekliyor
        }

        long endTime = System.nanoTime(); // Bitiş zamanını alıyor
        long durationInMicroseconds = (endTime - startTime) / 1000; // Çalışma süresini hesaplıyor (mikrosaniye cinsinden)
        durationLabel.setText("Programın çalışma süresi: " + durationInMicroseconds + " mikrosaniye"); // Çalışma süresini etikete yazıyor
    }

    // Ana metot
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Arayüz işlemlerini ana thread dışında yapmak için SwingUtilities kullanılıyor
            public void run() { // Yeni bir runnable nesnesi oluşturuluyor ve run metodu tanımlanıyor
                new ProblemBUI().setVisible(true); // ProblemBUI sınıfından yeni bir nesne oluşturuluyor ve görünür hale getiriliyor
            }
        });
    }
}