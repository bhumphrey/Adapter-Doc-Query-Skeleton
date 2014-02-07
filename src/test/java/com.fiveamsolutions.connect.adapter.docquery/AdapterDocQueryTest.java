package com.fiveamsolutions.connect.adapter.docquery;

import gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQueryPortType;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class AdapterDocQueryTest {



    AdapterDocQueryPortTypeImpl endpoint =  new AdapterDocQueryPortTypeImpl();


    AdapterDocQueryPortType client;

    @Before
    public void setupAdapterWS() {

        MockitoAnnotations.initMocks(this);

        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(AdapterDocQueryPortType.class);
        svrFactory.setAddress("local://AdapterDocQueryUnsecured");
        svrFactory.setServiceBean(endpoint);
        svrFactory.create();

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(AdapterDocQueryPortType.class);
        factory.setAddress("local://AdapterDocQueryUnsecured");
        client = (AdapterDocQueryPortType) factory.create();
    }

    @Test
    public void verifyTheRetrievalOfThePatientId() {

        RespondingGatewayCrossGatewayQueryRequestType request = createAdhocQueryRequest("urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d","", "543797436^^^&1.2.840.113619.6.197&ISO");

        AdhocQueryResponse response = client.respondingGatewayCrossGatewayQuery(request);

        assertNotNull(response);
    }


    private RespondingGatewayCrossGatewayQueryRequestType createAdhocQueryRequest(String id, String documentEntryTypeValue, String patientId) {
        RespondingGatewayCrossGatewayQueryRequestType request = new RespondingGatewayCrossGatewayQueryRequestType();
        AdhocQueryRequest query = new AdhocQueryRequest();

        AdhocQueryType adhocQuery = new AdhocQueryType();

        request.setAdhocQueryRequest(query);
        request.getAdhocQueryRequest().setAdhocQuery(adhocQuery);
        request.getAdhocQueryRequest().setId(id);


        SlotType1 slot = new SlotType1();
        slot.setName("$XDSDocumentEntryType");
        ValueListType valList = new ValueListType();
        valList.getValue().add(documentEntryTypeValue);
        slot.setValueList(valList);
        adhocQuery.getSlot().add(slot);

        SlotType1 slot2 = new SlotType1();
        slot2.setName("$XDSDocumentEntryPatientId");
        ValueListType valList2 = new ValueListType();
        valList2.getValue().add(patientId);
        slot2.setValueList(valList2);
        adhocQuery.getSlot().add(slot2);

        return request;
   }


}
