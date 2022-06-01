package com.sn.trainxtrain.model;

public class Parameters
{
    private String timezone;
    private String start;
    private String format;
    private String[] sort;
    private String rows;
    private String[] dataset;
    private String[] facet;

    public String getTimezone() {
        return timezone;
    }

    public String getStart() {
        return start;
    }

    public String getFormat() {
        return format;
    }

    public String[] getSort() {
        return sort;
    }

    public String getRows() {
        return rows;
    }

    public String[] getDataset() {
        return dataset;
    }

    public String[] getFacet() {
        return facet;
    }

    @Override
    public String toString() {
        return "ClassPojo [timezone = "+timezone+", start = "+start+", format = "+format+", sort = "+sort+", rows = "+rows+", dataset = "+dataset+", facet = "+facet+"]";
    }
}
