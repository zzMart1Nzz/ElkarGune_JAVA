package eragiketak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;



import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import metodoak.DBKonexioa;



public class fakturaEskaera {

    public void sortuFaktura(int eskaera) {
        String fraZkia = "";
        int bezeroaId = 0;
        double totala = 0.0;

        // Datos del cliente
        String nanEdoNif = "", izena = "", abizena = "", helbidea = "", herria = "", postaKodea = "";
        // Obtener datos de la factura
        String sqlEskaera = "SELECT idFaktura, idBazkidea, totala FROM faktura WHERE idFaktura = ?";
        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sqlEskaera)) {
            pst.setInt(1, eskaera);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    fraZkia = rs.getString("idFaktura");
                    bezeroaId = rs.getInt("idBazkidea");
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
        String sqlBezeroa = "SELECT nanEdoNif, izena, abizena, helbidea, herria, postaKodea FROM bezeroa WHERE idBezeroa = ?";
        try (Connection conn = DBKonexioa.konexioaEgin();
             PreparedStatement pst = conn.prepareStatement(sqlBezeroa)) {
            pst.setInt(1, bezeroaId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    nanEdoNif = rs.getString("nanEdoNif");
                    izena = rs.getString("izena");
                    abizena = rs.getString("abizena");
                    helbidea = rs.getString("helbidea");
                    herria = rs.getString("herria");
                    postaKodea = rs.getString("postaKodea");
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
            edukia.newLineAtOffset(0, -20);
            edukia.showText(helbidea);
            edukia.newLineAtOffset(0, -20);
            edukia.showText(herria + " (" + postaKodea + ")");
            edukia.endText();

            float pageWidth = orria.getMediaBox().getWidth();
            String[] empresaInfo = {"LarrunArri elkartea ", "B12345678", "Jose Arana Kalea, 9", "Ordizia (20240)"};

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
            String sqlProduktuak = "SELECT ep.idProduktua, ep.kopurua, ep.prezioa, ep.totala, p.marka, p.modeloa FROM eskaeraproduktua ep JOIN produktua p ON ep.idProduktua = p.idProduktua WHERE ep.fraZkia = ?";
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
                                rs.getString("marka") + " " + rs.getString("modeloa"),
                                rs.getInt("kopurua"),
                                rs.getDouble("prezioa"),
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
            String localFilePath = "C:\\Users\\anere\\OneDrive - Goierri Eskola\\Escritorio\\UTechJava\\JAVA\\2Erronka\\resources\\fakturak\\faktura_" + fraZkia.replace("/", "_") + ".pdf";
            dokumentua.save(localFilePath);
            dokumentua.close();            
            dokumentua.close();
            System.out.println("Faktura sortu da: faktura_" + fraZkia + ".pdf");

         // Subir el archivo al servidor FTP
            String server = "172.16.237.119";
            int port = 21;
            String user = "ERRONKA";
            String pass = "3Taldea3";
            String remoteFilePath = "ERRONKA";
            

            boolean uploadSuccess = FTPUploader.uploadFile(server, port, user, pass, localFilePath, remoteFilePath, fraZkia);
            if (uploadSuccess) {
                System.out.println("✅ Archivo subido correctamente al servidor FTP.");
            } else {
                System.out.println("❌ Error al subir el archivo al servidor FTP.");
            }
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
