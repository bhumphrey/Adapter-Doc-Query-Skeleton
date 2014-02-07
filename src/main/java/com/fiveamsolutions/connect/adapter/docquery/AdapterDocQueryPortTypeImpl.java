package com.fiveamsolutions.connect.adapter.docquery;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryErrorList;
import javax.xml.ws.BindingType;

@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AdapterDocQueryPortTypeImpl implements gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQueryPortType {


    public oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse respondingGatewayCrossGatewayQuery(
            gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType respondingGatewayCrossGatewayQueryRequest) {
        return createErrorResponse("XDSUnknownStoredQuery", "Not Yet Implemented");
    }


    private oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse createErrorResponse(String errorCode,
                                                                                            String codeContext) {
        ObjectFactory queryObjFactory = new ObjectFactory();
        AdhocQueryResponse response = queryObjFactory.createAdhocQueryResponse();
        RegistryErrorList regErrList = new RegistryErrorList();
        response.setRegistryErrorList(regErrList);
        response.setStatus("urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Failure");
        RegistryObjectListType regObjectList = new RegistryObjectListType();
        response.setRegistryObjectList(regObjectList);

        RegistryError regErr = new RegistryError();
        regErrList.getRegistryError().add(regErr);
        regErr.setCodeContext(codeContext);
        regErr.setErrorCode(errorCode);
        regErr.setSeverity("urn:oasis:names:tc:ebxml-regrep:ErrorSeverityType:Error");
        return response;
    }
}
