package com.fiveamsolutions.connect.adapter.docquery;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;
import org.hl7.v3.HIPMessageServer;
import org.hl7.v3.HIPMessageServerService;

import javax.xml.ws.BindingType;


@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AdapterDocQueryPortTypeImpl implements gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQueryPortType {

    public oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse respondingGatewayCrossGatewayQuery(
            gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType respondingGatewayCrossGatewayQueryRequest) {


        HIPMessageServerService   service = new HIPMessageServerService();
        HIPMessageServer client = service.getHIPMessageServerPort();

        final String TEST_MESSAGE =  "<![CDATA[\n" +
                "<GetDocumentStroedInfoRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:hl7-org:v3\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/GetDocumentStroedInfoRequest.xsd\">\n" +
                "\t<Id root=\"请求消息OID\" extension=\"DDD34F6E-2E89-435A-9A62-F4CD3D4DE23B\"/>\n" +
                "\t<HealthCardId>0085008509175450</HealthCardId>\n" +
                "\t<IdentityId>101010190001010006</IdentityId>\n" +
                "\t<DocumentTitle>住院摘要</DocumentTitle>\n" +
                "</GetDocumentStroedInfoRequest>\n" +
                "  ]]>";

        String response = client.hipMessageServer("GetDocumentSetRetrieveInfo", TEST_MESSAGE);

        final String TEST_RESPONSE = "<![CDATA[<RetrieveDocumentSetResponse xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:hl7-org:v3\" xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/RetrieveDocumentSetResponse.xsd\" status=\"AA\"><Id root=\"响应消息OID\" extension=\"fe9984ab-fb74-49ed-b996-f1e27b45a412\" /><TargetId root=\"响应消息OID\" extension=\"A566407F-827E-4E96-9D75-87ABC2EC5BC6\" /><DocumentResponse><RepositoryUniqueId>686e691e-0172-4233-a256-6c7b416b803a</RepositoryUniqueId><DocumentUniqueId>B4382279D36940B39B5E525BFC57E9AB</DocumentUniqueId><MimeType>text/xml</MimeType><Document>base64内容</Document></DocumentResponse><Detail>successed</Detail></RetrieveDocumentSetResponse>]]>";


        AdhocQueryResponse adhocQueryResponse = new AdhocQueryResponse();

        RegistryObjectListType regObjList = new RegistryObjectListType();
        adhocQueryResponse.setRegistryObjectList(regObjList);

        adhocQueryResponse.setStatus("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success");

        oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory oRimObjectFactory = new oasis.names.tc.ebxml_regrep.xsd.rim._3.ObjectFactory();

        //see gov.hhs.fha.nhinc.docregistry.adapter.AdapterComponentDocRegistryOrchImpl

        return adhocQueryResponse;
    }

}
