package com.fiveamsolutions.connect.adapter.docquery;

import org.apache.cxf.Bus;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ApacheCXFTestExecutionListener extends AbstractTestExecutionListener {


    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {

        LocalTransportFactory localTransportFactory =  testContext.getApplicationContext().getBean(LocalTransportFactory.class);

        ConduitInitiatorManager cim = testContext.getApplicationContext().getBean(Bus.class).getExtension(ConduitInitiatorManager.class);

        cim.registerConduitInitiator("http://cxf.apache.org/transports/local",  localTransportFactory);
        cim.registerConduitInitiator("http://schemas.xmlsoap.org/wsdl/soap/http", localTransportFactory);
        cim.registerConduitInitiator("http://schemas.xmlsoap.org/soap/http", localTransportFactory);
        cim.registerConduitInitiator("http://cxf.apache.org/bindings/xformat", localTransportFactory);
    }
}
