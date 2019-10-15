package com.alessandro.calimero.utils.config.parts;

import com.alessandro.calimero.utils.config.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupAddress {

    @JsonProperty("label")
    private String label;

    @JsonProperty("address")
    private String address;

    @JsonProperty("addressStatus")
    private String addressStatus;

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

    public String getAddressStatus() {
        return addressStatus;
    }

    public DataType getDpt() {
        return dpt;
    }
}
