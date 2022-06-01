package com.sn.trainxtrain.model;

public class Facet_groups
{
    private String name;
    private Facets[] facets;

    public String getName ()
    {
        return name;
    }

    public Facets[] getFacets ()
    {
        return facets;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = "+name+", facets = "+facets+"]";
    }
}
