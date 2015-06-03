package systemy.bankowe.services.pdf;


public interface IPdfGeneratorService {
    /**
     * Towrzy zestawienie pdfa z zestawieniem przelewów.
     * 
     * @param data dane do zestawienia.
     * @return wygenerowany pdf.
     * @throws PdfGenerationException gdy wystąpił błąd podczas generowania pdfa.
     */
    byte[] generateTransferSummaryPdf(TransferSummaryData data) throws PdfGenerationException;
}
