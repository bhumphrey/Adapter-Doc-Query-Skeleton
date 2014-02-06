package com.fiveamsolutions.connect.adapter.docquery;

import com.fiveamsolutions.connect.docrepository.DocRepository;
import gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;

import javax.inject.Inject;

public class AdapterDocQueryTest extends AbstractAdapterDocQueryTest {


    @Mock
    DocRepository mockRepository;

    @InjectMocks
    @Inject
    AdapterDocQueryPortTypeImpl webservice;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testAddBookWithProxyDirectDispatch() {

        RespondingGatewayCrossGatewayQueryRequestType request = new RespondingGatewayCrossGatewayQueryRequestType();
        AdhocQueryResponse response = client.respondingGatewayCrossGatewayQuery(request);

    }
}
