package br.com.alangaraujo.infrastructure.util;

import br.com.alangaraujo.domain.model.Document;

import java.util.ArrayList;
import java.util.List;

public abstract class DocumentUtil {

    protected List<Document> groupDocuments(List<Document> documents) {

        List<Document> grouper = new ArrayList<>();

        for (Document document : documents) {

            Document temporaryDocument = grouper.stream()
                    .filter(value -> value.getExtension().equals(document.getExtension()))
                    .findFirst()
                    .orElse(null);

            if (temporaryDocument != null) {
                Integer index = grouper.indexOf(temporaryDocument);
                grouper.get(index).setLines(grouper.get(index).getLines() + document.getLines());
                grouper.get(index).setBytes(grouper.get(index).getBytes() + document.getBytes());
                grouper.get(index).setCount(grouper.get(index).getCount() + 1);
            } else {
                document.setCount(1);
                grouper.add(document);
            }

        }

        return grouper;
    }

}
