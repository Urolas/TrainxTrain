package com.sn.trainxtrain.model;

public class TrainResult
{
    private String nhits;
    private Facet_groups[] facet_groups;
    private Records[] records;
    private Parameters parameters;

    public String getNhits () {
        return nhits;
    }

    public Facet_groups[] getFacet_groups () {
        return facet_groups;
    }

    public Records[] getRecords () {
        return records;
    }

    public Parameters getParameters () {
        return parameters;
    }

    @Override
    public String toString() {
        return "ClassPojo [nhits = "+nhits+", facet_groups = "+facet_groups+", records = "+records+", parameters = "+parameters+"]";
    }
}