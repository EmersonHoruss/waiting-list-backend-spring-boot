package com.ownprojects.backend.utils.specification;

import lombok.ToString;

@ToString
public class Connector {
    private String connector;
    private Integer startIndex;
    private Integer endIndex;

    public Connector(String connector, Integer startIndex, Integer endIndex) {
        this.connector = connector;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public boolean isNegation() {
        return connector == "not";
    }

    public String getConnector(){
        return this.connector;
    }

    public Integer getStartIndex() {
        return this.startIndex;
    }

    public Integer getEndIndex() {
        return this.endIndex;
    }
}
