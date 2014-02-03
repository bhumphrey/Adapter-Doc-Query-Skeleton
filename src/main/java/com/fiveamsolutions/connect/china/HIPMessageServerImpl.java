package com.fiveamsolutions.connect.china;


import org.hl7.v3.HIPMessageServer;

import javax.jws.WebParam;

public class HIPMessageServerImpl implements HIPMessageServer {


    @Override
    public String hipMessageServer(@WebParam(partName = "action", name = "action") String action, @WebParam(partName = "message", name = "message") String message) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

