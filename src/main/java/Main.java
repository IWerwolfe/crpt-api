import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        CrptApi crptApi = new CrptApi(TimeUnit.SECONDS, 5);
        CrptApi.RequestDoc doc = createDoc();

        for (int y = 0; y <= 5; y++) {
            int finalY = y;
            Thread thread = new Thread(() -> startThreadHandler(crptApi, doc, finalY));
            thread.start();
        }
    }

    private static void startThreadHandler(CrptApi crptApi, CrptApi.RequestDoc doc, int finalY) {

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread " + finalY);

        for (int i = 0; i <= 10; i++) {
            crptApi.createDoc(doc, "qwerwe123wwewer_" + i + " - " + finalY);
        }
    }

    private static CrptApi.RequestDoc createDoc() {
        CrptApi.RequestDoc doc = new CrptApi.RequestDoc();
        doc.setDescription(new CrptApi.Description("test description"));
        doc.setDocId("12345");
        doc.setDocStatus("pending");
        doc.setDocType("LP_INTRODUCE_GOODS");
        doc.setImportRequest(true);
        doc.setOwnerInn("7704211203");
        doc.setParticipantInn("7704211200");
        doc.setProducerInn("7704211202");
        doc.setProductionDate(new Date());
        doc.setProductionType("Simple");
        doc.setRegDate(new Date());
        doc.setRegNumber("12345");
        doc.setProducts(createTestProducts(3));

        return doc;
    }

    private static List<CrptApi.Product> createTestProducts(int count) {

        List<CrptApi.Product> productList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            CrptApi.Product product = new CrptApi.Product();
            product.setProductionDate(new Date());
            product.setCertificateDocument("ORDER-" + i);
            product.setCertificateDocumentDate(new Date());
            product.setCertificateDocumentNumber(String.valueOf(i));
            product.setTnvedCode("123");
            product.setOwnerInn("220322598212");
            product.setUitCode("312");
            product.setProducerInn("770423122444");

            productList.add(product);
        }

        return productList;
    }
}
