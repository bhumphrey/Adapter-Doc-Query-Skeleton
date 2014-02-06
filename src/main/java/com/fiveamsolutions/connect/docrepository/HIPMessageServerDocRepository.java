package com.fiveamsolutions.connect.docrepository;


import org.hl7.v3.HIPMessageServer;
import org.hl7.v3.HIPMessageServerService;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

public class HIPMessageServerDocRepository implements DocRepository {

    final String messageTemplate = "<![CDATA[\n" +
            "<GetDocumentStroedInfoRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:hl7-org:v3\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/GetDocumentStroedInfoRequest.xsd\">\n" +
            "\t<Id root=\"请求消息OID\" extension=\"DDD34F6E-2E89-435A-9A62-F4CD3D4DE23B\"/>\n" +
            "\t<HealthCardId>0085008509175450</HealthCardId>\n" +
            "\t<IdentityId>%s</IdentityId>\n" +
            "\t<DocumentTitle>住院摘要</DocumentTitle>\n" +
            "</GetDocumentStroedInfoRequest>\n" +
            "  ]]>";
    final String TEST_MESSAGE =  "<![CDATA[\n" +
            "<GetDocumentStroedInfoRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:hl7-org:v3\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/GetDocumentStroedInfoRequest.xsd\">\n" +
            "\t<Id root=\"请求消息OID\" extension=\"DDD34F6E-2E89-435A-9A62-F4CD3D4DE23B\"/>\n" +
            "\t<HealthCardId>0085008509175450</HealthCardId>\n" +
            "\t<IdentityId>101010190001010006</IdentityId>\n" +
            "\t<DocumentTitle>住院摘要</DocumentTitle>\n" +
            "</GetDocumentStroedInfoRequest>\n" +
            "  ]]>";

    final String TEST_RESPONSE = "<![CDATA[\n" +
            "<GetDocumentStroedInfoResponse xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:hl7-org:v3\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/GetDocumentStroedInfoResponse.xsd\" status=\"AA\">\n" +
            "<Id root=\"请求消息OID\" extension=\"2e35854b-ba01-4bb1-9c93-2faa8c94ffa8\" />\n" +
            "<TargetId root=\"请求消息OID\" extension=\"DDD34F6E-2E89-435A-9A62-F4CD3D4DE23B\" />\n" +
            "<DocumentSet>\n" +
            "<DocumentUniqueId>35d78eed-2b01-47dc-aafd-493b097b994a</DocumentUniqueId>\n" +
            "<RepositoryUniqueId>9526bf5a-bd6b-47d1-93a5-bdbf6a4de618</RepositoryUniqueId>\n" +
            "<DocumentTitle />\n" +
            "<CreateTime>2012-12-01T13:32:15Z</CreateTime>\n" +
            "<AuthorName>刘欢</AuthorName>\n" +
            "<PatientID>451134681</PatientID>\n" +
            "<PatientName>洗衣机</PatientName>\n" +
            "<ServerOrganization>人民医院</ServerOrganization>\n" +
            "<EpisodeID>129087332</EpisodeID>\n" +
            "<InTime>1925-04-07T10:32:15Z</InTime>\n" +
            "<OutTime>1925-04-17T12:32:15Z</OutTime>\n" +
            "<AdmissionDepart />\n" +
            "<AdmissionDoctor>周裕凯</AdmissionDoctor>\n" +
            "<AdmissionType>HSDC00.03</AdmissionType>\n" +
            "<DiagnosisResult />\n" +
            "<DocUrl>http://10.8.252.93:10065/GetCDA/?FileCode=35d78eed-2b01-47dc-aafd-493b097b994a&amp;amp;doc_tp=HSDC00.03</DocUrl>\n" +
            "</DocumentSet>\n" +
            "<Detail>success</Detail>\n" +
            "</GetDocumentStroedInfoResponse>\n" +
            "]]>";


    public List<com.fiveamsolutions.connect.docrepository.Document> findDocuments(DocumentQueryParameters parameters) {

        String patientId = parameters.getPatientId();
        //TODO: Get rid of test message
        String message = TEST_MESSAGE;
        if (patientId != null) {
            message = String.format(messageTemplate, patientId);
        }


        HIPMessageServerService service = new HIPMessageServerService();
        HIPMessageServer client = service.getHIPMessageServerPort();

        String response = client.hipMessageServer("GetDocumentSetRetrieveInfo", TEST_MESSAGE);

        return Collections.EMPTY_LIST;
    }

    private NodeList getResponseNodes(String response) {
        org.w3c.dom.Document doc = getDocumentFromXml(response);
        Element el = doc.getDocumentElement();
        NodeList nodes = el.getElementsByTagName("DocumentSet");
        return nodes;
    }

    private Document getDocumentFromXml(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try
        {
            builder = factory.newDocumentBuilder();
            document = builder.parse( new InputSource( new StringReader( xmlString ) ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

}
