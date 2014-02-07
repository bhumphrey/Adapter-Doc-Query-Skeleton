package com.fiveamsolutions.connect.docrepository;


import java.util.List;

public interface DocRepository {

    public List<Document> findDocuments(DocumentQueryParameters parameters);

}
