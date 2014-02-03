package com.fiveamsolutions.connect.adapter.docquery;

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

        //TODO this needs to actually work
        return null;
    }

}
