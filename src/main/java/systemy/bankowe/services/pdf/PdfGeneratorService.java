package systemy.bankowe.services.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;

import systemy.bankowe.annotations.InjectLogger;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGeneratorService implements Serializable, IPdfGeneratorService {
    
    private static final long serialVersionUID = -3813502569252710669L;
    @InjectLogger
    private static final Logger LOGGER = null;
    
    private static final NumberFormat NUMBER_FORMATTER;
    
    static {
        NUMBER_FORMATTER = NumberFormat.getCurrencyInstance();
        NUMBER_FORMATTER.setMinimumFractionDigits(2);
        NUMBER_FORMATTER.setMaximumFractionDigits(2);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] generateTransferSummaryPdf(TransferSummaryData data) throws PdfGenerationException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = createNewDocument(outputStream);
            document.open();
            addImage(document);
            addTitle(document, data);
            addGeneralSummary(document, data);
            addDetailsTitle(document);
            addTransfersDetails(document, data);
            addIssueTime(document);
            document.close();
            return outputStream.toByteArray();
        }
        catch (Exception e) {
            LOGGER.error("generateTransferSummaryPdf|Wystąpił błąd podczas tworzenia dokumentu.", e);
            throw new PdfGenerationException(e);
        }
    }
    
    private Document createNewDocument(final ByteArrayOutputStream outputStream) throws DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        return document;
    }
    
    private void addImage(final Document document) throws DocumentException, MalformedURLException, IOException {
        Image image = Image.getInstance(getClass().getResource("logo.png"));
        image.scalePercent(40f);
        document.add(image);
    }
    
    private void addTitle(final Document document, TransferSummaryData data) throws DocumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(Fonts.getFont(FontTypes.BOLD));
        paragraph.setSpacingBefore(75f);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        Chunk chunk = new Chunk();
        chunk.append("Zestawienie operacji od dnia " + sdf.format(data.getBegin()) + " do " + sdf.format(data.getEnd()));
        paragraph.add(chunk);
        document.add(paragraph);
    }
    
    private void addGeneralSummary(final Document document, TransferSummaryData data) throws DocumentException {
        float indent = 5.0f;
        Font normalFont = Fonts.getFont(FontTypes.NORMAL);
        Font boldFont = Fonts.getFont(FontTypes.BOLD);
        PdfPTable table = new PdfPTable(new float[]{50f, 50f});
        
        table.setWidthPercentage(100f);
        table.setSpacingBefore(15f);
        table.addCell(createCellWithoutBorder("Suma uznań:", Element.ALIGN_RIGHT, normalFont, 0.0f));
        table.addCell(createCellWithoutBorder(formatAmount(data.getCredits()), Element.ALIGN_LEFT, normalFont, indent));
        table.addCell(createCellWithoutBorder("Suma obciążeń:", Element.ALIGN_RIGHT, normalFont, 0.0f));
        table.addCell(createCellWithoutBorder(formatAmount(data.getDebits()), Element.ALIGN_LEFT, normalFont, indent));
        table.addCell(createCellWithoutBorder("Początkowy stan konta:", Element.ALIGN_RIGHT, boldFont, 0.0f));
        table.addCell(createCellWithoutBorder(formatAmount(data.getBeginAmount()), Element.ALIGN_LEFT, boldFont, indent));
        table.addCell(createCellWithoutBorder("Końcowy stan konta:", Element.ALIGN_RIGHT, boldFont, 0.0f));
        table.addCell(createCellWithoutBorder(formatAmount(data.getEndAmount()), Element.ALIGN_LEFT, boldFont, indent));
        table.addCell(createCellWithoutBorder("Saldo:", Element.ALIGN_RIGHT, boldFont, 0.0f));
        table.addCell(createCellWithoutBorder(formatAmount(data.getSaldo()), Element.ALIGN_LEFT, boldFont, indent));
        document.add(table);
    }
    
    private PdfPCell createCellWithoutBorder(String value, int alignment, Font font, float indent) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setHorizontalAlignment(alignment);
        cell.setIndent(indent);
        cell.disableBorderSide(Rectangle.BOX);
        return cell;
    }
    
    String formatAmount(double amount) {
        return NUMBER_FORMATTER.format(amount);
    }
    
    private void addDetailsTitle(final Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingBefore(10f);
        paragraph.setFont(Fonts.getFont(FontTypes.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        Chunk chunk = new Chunk();
        chunk.append("Szczegółowe zestawienie operacji");
        paragraph.add(chunk);
        document.add(paragraph);
    }
    
    private void addTransfersDetails(final Document document, TransferSummaryData data) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{40f, 15f, 15f, 15f, 15f});
        table.setWidthPercentage(100f);
        table.setSpacingBefore(15f);
        fillTableWithTransferDetails(table, data);
        document.add(table);
    }
    
    private void fillTableWithTransferDetails(PdfPTable table, TransferSummaryData data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (SingleTransferDetails t : data.getTranferDerails()) {
            addRow(table, t.getRecipientName() + "\n" + t.getAccountNumber(), t.getTitle(), sdf.format(t.getDate()), 
                    (t.isDebit() ? "-" : "") + formatAmount(t.getAmount()), formatAmount(t.getSaldo()));
        }
    }
    
    private void addRow(PdfPTable table, String recipient, String title, String date, String amount, String saldo) {
        table.addCell(createCellWithBorder(recipient, Element.ALIGN_LEFT, Fonts.getFont(FontTypes.NORMAL), 0f));
        table.addCell(createCellWithBorder(title, Element.ALIGN_LEFT, Fonts.getFont(FontTypes.NORMAL), 0f));
        table.addCell(createCellWithBorder(date, Element.ALIGN_LEFT, Fonts.getFont(FontTypes.NORMAL), 0f));
        table.addCell(createCellWithBorder(amount, Element.ALIGN_RIGHT, Fonts.getFont(FontTypes.NORMAL), 0f));
        table.addCell(createCellWithBorder(saldo, Element.ALIGN_RIGHT, Fonts.getFont(FontTypes.NORMAL), 0f));
    }
    
    private PdfPCell createCellWithBorder(String value, int alignment, Font font, float indent) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setHorizontalAlignment(alignment);
        cell.setIndent(indent);
        return cell;
    }
    
    private void addIssueTime(final Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingBefore(10f);
        paragraph.setFont(Fonts.getFont(FontTypes.SMALL));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        Chunk chunk = new Chunk();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        chunk.append("Zestawienie wygenerowano dnia " + sdf.format(new Date()) + ".");
        paragraph.add(chunk);
        document.add(paragraph);
    }
}