package com.fiveamsolutions.connect.adapter.docquery;

import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType.DocumentResponse;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingType;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.IdentifiableType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotListType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;

import org.hl7.v3.HIPMessageServer;
import org.hl7.v3.HIPMessageServerService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AdapterDocQueryPortTypeImpl implements gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQueryPortType {

    public oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse respondingGatewayCrossGatewayQuery(
            gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType respondingGatewayCrossGatewayQueryRequest) {


        HIPMessageServerService   service = new HIPMessageServerService();
        HIPMessageServer client = service.getHIPMessageServerPort();

        String messageTemplate = "<![CDATA[\n" +
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

        
        AdhocQueryRequest request = respondingGatewayCrossGatewayQueryRequest.getAdhocQueryRequest();        
        String patientId = getPatientId(request);
        //TODO: Get rid of test message
        String message = TEST_MESSAGE;
        if (patientId != null) {
            message = String.format(messageTemplate, patientId);
        }

        String response = client.hipMessageServer("GetDocumentSetRetrieveInfo", message);
        
        ObjectFactory queryObjFactory = new ObjectFactory();
        AdhocQueryResponse adhocQueryResponse = queryObjFactory.createAdhocQueryResponse();                
        
        List<SlotType1> responseSlots = setUpResponseSlots(request,
                adhocQueryResponse);        
        NodeList nodes = getResponseNodes(response);        
        populateSlots(responseSlots, nodes);
                
        adhocQueryResponse.setStatus("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success");

        return adhocQueryResponse;
    }

    private void populateSlots(List<SlotType1> responseSlots, NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node docNode = nodes.item(i);
            NodeList docChildren = docNode.getChildNodes();
            
            for (int j = 0; j < docChildren.getLength(); j++) {
                Node child = docChildren.item(j);
                SlotType1 slot = createSlot(child.getNodeName(), child.getTextContent());
                responseSlots.add(slot);
            }
        }
    }

    private NodeList getResponseNodes(String response) {
        Document doc = getDocumentFromXml(response);
        Element el = doc.getDocumentElement();
        NodeList nodes = el.getElementsByTagName("DocumentSet");
        return nodes;
    }

    private List<SlotType1> setUpResponseSlots(AdhocQueryRequest request,
            AdhocQueryResponse adhocQueryResponse) {
        adhocQueryResponse.setRequestId(request.getId());
        RegistryObjectListType registryObjectList = new RegistryObjectListType();
        adhocQueryResponse.setRegistryObjectList(registryObjectList);
        
        oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory oRimObjectFactory = new oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory();
        ExtrinsicObjectType extrinsicObject = new ExtrinsicObjectType();
        JAXBElement<? extends IdentifiableType> extrinsicObjectElement = oRimObjectFactory.createExtrinsicObject(extrinsicObject);
        adhocQueryResponse.getRegistryObjectList().getIdentifiable().add(extrinsicObjectElement);
        
        List<SlotType1> responseSlots = extrinsicObject.getSlot();
        return responseSlots;
    }

    private String getPatientId(AdhocQueryRequest request) {
        List<SlotType1> slots = request.getAdhocQuery().getSlot();
        String patientId = null;
        for (SlotType1 slot : slots) {
            //TODO: Is this the correct lable for the patient id?
            if (slot.getName().equals("$XDSDocumentEntryPatientId")) {
                patientId = slot.getValueList().getValue().get(0);
            }
        }
        return patientId;
    }

    /**
     * @param request - AdhocQUery Request i/p parameter.
     * @return SlotType1 - This method returns all the slots from Request.
     */
    protected List<SlotType1> getSlotsFromAdhocQueryRequest(AdhocQueryRequest request) {
        AdhocQueryType adhocQuery = request.getAdhocQuery();
        List<SlotType1> slots = null;
        if (adhocQuery != null) {
            slots = adhocQuery.getSlot();
        }

        return slots;
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
    
    private SlotType1 createSlot(String name, String value) {
        SlotType1 oSlot = new SlotType1();
        oSlot.setName(name);
        ValueListType oValueList = new ValueListType();
        oSlot.setValueList(oValueList);
        List<String> olValue = oValueList.getValue();
        olValue.add(value);
        return oSlot;
    }
}
