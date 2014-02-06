package com.fiveamsolutions.connect.adapter.docquery;

import gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQuery;
import gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQueryPortType;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ApacheCXFTestExecutionListener.class})
@ContextConfiguration(locations={"/cxf-test.xml"})
public abstract class AbstractAdapterDocQueryTest {

    protected final static String ENDPOINT_ADDRESS = "local://AdapterDocQueryUnsecured";

    protected AdapterDocQueryPortType client;

    private AdapterDocQueryPortType createSampleWebServiceClient() {

        QName serviceName = new QName("urn:gov:hhs:fha:nhinc:adapterdocquery", "AdapterDocQueryUnsecuredService");

        QName portName = new QName("urn:gov:hhs:fha:nhinc:adapterdocquery", "AdapterDocQueryUnsecuredPort");
        Service service = AdapterDocQuery.create(serviceName);

        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);

        AdapterDocQueryPortType client = service.getPort(portName, AdapterDocQueryPortType.class);

        return client;

    }

    @Before
    public void setUpClient() {
        client = createSampleWebServiceClient();

    }


}