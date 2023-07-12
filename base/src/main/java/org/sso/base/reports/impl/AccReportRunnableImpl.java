package org.sso.base.reports.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sso.base.reports.AccReportRunnable;

import java.util.HashMap;

public abstract class AccReportRunnableImpl implements AccReportRunnable {

    private String filter;
    private String pdfTemplate;
    private String excelTemplate;
    private HashMap<String, Object> resultParameters = new HashMap<>();

    public AccReportRunnableImpl(String pdfTemplate, String excelTemplate) {
        this.pdfTemplate = pdfTemplate;
        this.excelTemplate = excelTemplate;
    }

    @Override
    public String getPdfTemplate() {
        return pdfTemplate;
    }

    public void setPdfTemplate(String pdfTemplate) {
        this.pdfTemplate = pdfTemplate;
    }

    @Override
    public String getExcelTemplate() {
        return excelTemplate;
    }

    public void setExcelTemplate(String excelTemplate) {
        this.excelTemplate = excelTemplate;
    }

    @Override
    public HashMap<String, Object> getResultParameters() {
        return resultParameters;
    }

    public void setResultParameters(HashMap<String, Object> resultParameters) {
        this.resultParameters = resultParameters;
    }

    public void addResultParameter(String key, Object object) {
        resultParameters.put(key, object);
    }

    public String getFilter() {
        return filter;
    }

    @Override
    public void setFilter(String filter) {
        this.filter = filter;
    }

    public JsonNode getJsonFilter(String filter) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(filter != null ? filter : this.filter);
    }
}

