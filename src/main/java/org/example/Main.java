import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Bilgiler
            System.out.print("Ad Soyad: Hannah MCKAY ");
            String name = scanner.nextLine();

            System.out.print("E-posta: enghannahmckay@outlook.com ");
            String email = scanner.nextLine();

            System.out.print("Telefon: 05999999999 ");
            String phone = scanner.nextLine();

            System.out.print("Eğitim Bilgisi: Yüksek Lisans Bilgisayar Mühnedisliği ");
            String education = scanner.nextLine();

            System.out.println("3 İş Deneyimi Giriniz: ");
            System.out.print("1. İş: Freelance-Mobil Uygulama Geliştiricisi ");
            String exp1 = scanner.nextLine();

            System.out.print("2. İş: Netver-Stajyer ");
            String exp2 = scanner.nextLine();

            System.out.print("3. İş: Oracle-Junior Developer ");
            String exp3 = scanner.nextLine();

            System.out.print("Fotoğraf dosya yolu (örnek: C:\\Users\\Esma\\Desktop\\foto.jpg): C:\\\\Users\\\\Esma\\\\Desktop\\\\pngtree-corporate-headshot-female-png-image_15953148.png\n ");
            String photoPath = scanner.nextLine();

            System.out.print("PDF dosya adı (örnek: ozgecmis.pdf): ozgecmis.pdf" +
                    " ");
            String pdfFileName = scanner.nextLine();
            if (!pdfFileName.toLowerCase().endsWith(".pdf")) pdfFileName += ".pdf";

            // PDF oluştur
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFileName));
            document.open();

            // Türkçe karakter desteği
            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(bf, 18, Font.BOLD);
            Font boldFont = new Font(bf, 12, Font.BOLD);
            Font normalFont = new Font(bf, 12);

            // Başlık ve fotoğraf
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{3, 1});

            // Sol taraf (bilgiler)
            PdfPCell infoCell = new PdfPCell();
            infoCell.setBorder(Rectangle.NO_BORDER);
            infoCell.addElement(new Paragraph("ÖZGEÇMİŞ", titleFont));
            infoCell.addElement(new Paragraph("\nAd Soyad: " + name, normalFont));
            infoCell.addElement(new Paragraph("E-posta: " + email, normalFont));
            infoCell.addElement(new Paragraph("Telefon: " + phone, normalFont));
            infoCell.addElement(new Paragraph("Eğitim: " + education, normalFont));
            headerTable.addCell(infoCell);

            // Sağ taraf (fotoğraf)
            PdfPCell photoCell = new PdfPCell();
            photoCell.setBorder(Rectangle.NO_BORDER);
            try {
                Image photo = Image.getInstance(photoPath);
                photo.scaleToFit(120, 120);
                photo.setAlignment(Element.ALIGN_RIGHT);
                photoCell.addElement(photo);
            } catch (Exception e) {
                photoCell.addElement(new Paragraph("(Fotoğraf bulunamadı)", normalFont));
            }
            headerTable.addCell(photoCell);

            document.add(headerTable);
            document.add(Chunk.NEWLINE);

            // Deneyimler
            Paragraph expHeader = new Paragraph("DENEYİMLER", boldFont);
            expHeader.setSpacingAfter(10f);
            document.add(expHeader);

            com.itextpdf.text.List expList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
            expList.add(new ListItem(exp1, normalFont));
            expList.add(new ListItem(exp2, normalFont));
            expList.add(new ListItem(exp3, normalFont));
            document.add(expList);

            document.close();
            System.out.println("PDF başarıyla oluşturuldu: " + pdfFileName);

        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }

        scanner.close();
    }
}
