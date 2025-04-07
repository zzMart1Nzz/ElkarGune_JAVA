package eragiketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import metodoak.DBKonexioa;



public class fakturaEskaera {

    public void sortuFaktura(int fakturaZenbakia) {
        String fraZkia = "";
        int idBazkidea = 0;
        double totala = 0.0;

        // Datos del cliente
        String nanEdoNif = "", izena = "", abizena = "";
        // Obtener datos de la factura
        String sqlEskaera = "SELECT idFaktura, idBazkidea, totala FROM fakturak WHERE idFaktura = ?";
        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sqlEskaera)) {
            pst.setInt(1, fakturaZenbakia);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    fraZkia = rs.getString("idFaktura");
                    idBazkidea = rs.getInt("idBazkidea");
                    totala = rs.getDouble("totala");

                } else {
                    System.out.println("Ez da eskaerarik aurkitu.");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Obtener datos del cliente
        String sqlBezeroa = "SELECT nan, izena, abizenak FROM bazkidea WHERE idBazkidea = ?";
        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sqlBezeroa)) {
            pst.setInt(1, idBazkidea);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    nanEdoNif = rs.getString("nan");
                    izena = rs.getString("izena");
                    abizena = rs.getString("abizenak");
                    
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la consulta SQL: " + e.getMessage());
            return;
        }

        // Crear el PDF
        try {
            PDDocument dokumentua = new PDDocument();
            PDPage orria = new PDPage(PDRectangle.A4);
            dokumentua.addPage(orria);
            PDPageContentStream edukia = new PDPageContentStream(dokumentua, orria);

            edukia.beginText();
            edukia.setFont(PDType1Font.COURIER_BOLD, 32);
            edukia.newLineAtOffset(50, orria.getMediaBox().getHeight() - 50);
            edukia.showText("FAKTURA " + fraZkia);
            edukia.endText();

            // Información del cliente
            edukia.beginText();
            edukia.setFont(PDType1Font.COURIER, 14);
            edukia.newLineAtOffset(50, orria.getMediaBox().getHeight() - 100);
            edukia.showText(izena + " " + abizena);
            edukia.newLineAtOffset(0, -20);
            edukia.showText(nanEdoNif);
            edukia.endText();

            float pageWidth = orria.getMediaBox().getWidth();
            String[] empresaInfo = {"LarrunArri elkartea ", "C23456789", "Jose Arana Kalea, 9", "Ordizia (20240)"};

            float yOffset = orria.getMediaBox().getHeight() - 100;
            for (String line : empresaInfo) {
                float textWidth = PDType1Font.COURIER.getStringWidth(line) / 1000 * 14;
                float xOffset = pageWidth - textWidth - 50;

                edukia.beginText();
                edukia.setFont(PDType1Font.COURIER, 14);
                edukia.newLineAtOffset(xOffset, yOffset);
                edukia.showText(line);
                edukia.endText();

                yOffset -= 20;
            }

            // Productos del pedido
            float yPosition = orria.getMediaBox().getHeight() - 200;
            edukia.beginText();
            edukia.setFont(PDType1Font.COURIER_BOLD, 12);
            edukia.newLineAtOffset(50, yPosition);
            edukia.showText(String.format("%-35.35s %10.10s %10.10s %10.10s",
                    "Produktua", "Kopurua", "Prezioa/U", "Totala"));
            edukia.endText();
            String sqlProduktuak = "SELECT k.idProduktua, p.izena, k.kopurua, p.salmentaPrezioa, k.totala FROM kontsumizioak k JOIN produktua p ON k.idProduktua = p.idProduktua WHERE k.idFaktura = ?";
            try (Connection conn = DBKonexioa.konexioaEgin();
                 PreparedStatement pst = conn.prepareStatement(sqlProduktuak)) {
                pst.setString(1, fraZkia);
                try (ResultSet rs = pst.executeQuery()) {
                    yPosition -= 30;
                    while (rs.next()) {
                        edukia.beginText();
                        edukia.setFont(PDType1Font.COURIER, 12);
                        edukia.newLineAtOffset(50, yPosition);
                        edukia.showText(String.format("%-35.35s %10.10s %10.2f€ %10.2f€",
                                rs.getString("izena"),
                                rs.getInt("kopurua"),
                                rs.getDouble("salmentaPrezioa"),
                                rs.getDouble("totala")));
                        edukia.endText();
                        yPosition -= 20;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Total de la factura
            DecimalFormat df = new DecimalFormat("#.00");

            double bruto = totala / 1.21;
            double bez = totala - bruto;

            // Formatea las variables
            String formattedBruto = df.format(bruto);
            String formattedBez = df.format(bez);
            String formattedTotala = df.format(totala);

            pageWidth = orria.getMediaBox().getWidth();
            yOffset = 120;

            // Información de totales
            String[] totalesInfo = {
                "Zerga oinarria: " + formattedBruto + " €",
                "BEZ-a(%21): " + formattedBez + " €",
                "Totala: " + formattedTotala + " €"
            };

            for (int i = 0; i < totalesInfo.length; i++) {
                String line = totalesInfo[i];
                float textWidth = PDType1Font.COURIER.getStringWidth(line) / 1000 * 14;
                float xOffset = pageWidth - textWidth - 50;

                edukia.beginText();
                if (i == 2) { // Si es la última línea (Totala), usa COURIER_BOLD
                    edukia.setFont(PDType1Font.COURIER_BOLD, 14);
                } else { // Para las otras líneas, usa COURIER normal
                    edukia.setFont(PDType1Font.COURIER, 14);
                }
                edukia.newLineAtOffset(xOffset, yOffset);
                edukia.showText(line);
                edukia.endText();

                yOffset -= 20;
            }

            edukia.close();
            String currentDir = Paths.get("").toAbsolutePath().toString();
            String localFilePath = Paths.get(currentDir, "Fakturak", "faktura_" + fraZkia + ".pdf").toString();
            dokumentua.save(localFilePath);
            dokumentua.close();            
            dokumentua.close();
            //System.out.println("Faktura sortu da: faktura_" + fraZkia + ".pdf"+ localFilePath +".");

         // 
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el PDF: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        fakturaEskaera faktura = new fakturaEskaera();
        faktura.sortuFaktura(1);
    }
}
