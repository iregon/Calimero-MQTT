package com.alessandro.calimero.utils.config.parts;

import com.alessandro.calimero.utils.config.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupAddress {

    @JsonProperty("label")
    private String label;

    @JsonProperty("address")
    private String address;

    @JsonProperty("dpt")
    private DataType dpt;

    public GroupAddress() {
    }

    public String getLabel() {
        return label;
    }

    public String getAddress() {
        return address;
    }

    public DataType getDpt() {
        return dpt;
    }
}
