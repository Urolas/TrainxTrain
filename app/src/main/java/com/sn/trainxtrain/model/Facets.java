package com.sn.trainxtrain.model;

public class Facets
{
    private String path;

    private String name;

    private String count;

    private String state;

    public String getPath ()
    {
        return path;
    }

    public String getName ()
    {
        return name;
    }

    public String getCount ()
    {
        return count;
    }

    public String getState ()
    {
        return state;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [path = "+path+", name = "+name+", count = "+count+", state = "+state+"]";
    }
}
