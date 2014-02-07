package com.fiveamsolutions.connect.adapter.docquery;

import com.fiveamsolutions.connect.docrepository.DocRepository;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class AdapterDocQueryTest extends AbstractAdapterDocQueryTest {


    @Inject
    DocRepository mockRepository;


    @Before
    public void initMocks() {

    }


    @Test
    public void testAddBookWithProxyDirectDispatch() {

        RespondingGatewayCrossGatewayQueryRequestType request = new RespondingGatewayCrossGatewayQueryRequestType();
        AdhocQueryResponse response = client.respondingGatewayCrossGatewayQuery(request);

    }
}
