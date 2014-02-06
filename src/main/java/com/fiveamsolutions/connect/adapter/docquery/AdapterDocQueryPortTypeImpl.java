package com.fiveamsolutions.connect.adapter.docquery;

import com.fiveamsolutions.connect.docrepository.Document;
import com.fiveamsolutions.connect.docrepository.DocumentQueryParameters;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ObjectFactory;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.IdentifiableType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.inject.Inject;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.BindingType;
import java.util.List;

import com.fiveamsolutions.connect.docrepository.DocRepository;

@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class AdapterDocQueryPortTypeImpl implements gov.hhs.fha.nhinc.adapterdocquery.AdapterDocQueryPortType {

    @Inject
    DocRepository documentRepository;

    public oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse respondingGatewayCrossGatewayQuery(
            gov.hhs.fha.nhinc.common.nhinccommonadapter.RespondingGatewayCrossGatewayQueryRequestType respondingGatewayCrossGatewayQueryRequest) {

        AdhocQueryRequest request = respondingGatewayCrossGatewayQueryRequest.getAdhocQueryRequest();

        DocumentQueryParameters queryParameters = new DocumentQueryParameters();
        queryParameters.setPatientId(getPatientId(request));

        List<Document> documents = documentRepository.findDocuments(queryParameters);

        ObjectFactory queryObjFactory = new ObjectFactory();
        AdhocQueryResponse adhocQueryResponse = queryObjFactory.createAdhocQueryResponse();                
        
        List<SlotType1> responseSlots = setUpResponseSlots(request,
                adhocQueryResponse);

        populateSlots(responseSlots, documents);

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

    private void populateSlots(List<SlotType1> responseSlots, List<Document> documents) {
        for (Document document : documents) {

            responseSlots.add(createSlot("URI", document.getURI()));
            responseSlots.add(createSlot("repositoryUniqueId", document.getRepositoryUniqueId()));

        }
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
