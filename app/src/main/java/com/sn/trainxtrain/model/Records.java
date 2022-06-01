package com.sn.trainxtrain.model;

public class Records
{
    private String recordid;
    private String datasetid;
    private Fields fields;
    private String record_timestamp;

    public String getRecordid() {
        return recordid;
    }

    public String getDatasetid() {
        return datasetid;
    }

    public Fields getFields() {
        return fields;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    @Override
    public String toString() {
        return "ClassPojo [recordid = "+recordid+", datasetid = "+datasetid+", fields = "+fields+", record_timestamp = "+record_timestamp+"]";
    }
}