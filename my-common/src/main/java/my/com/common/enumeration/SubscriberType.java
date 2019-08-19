package my.com.common.enumeration;

import lombok.Getter;

/**
 * SubscriberType
 */
public enum SubscriberType {
    CONTRACT("C", "Contract"),
    NON_CONTRACT("N" , "Non Contract");

    @Getter
    private String code;

    @Getter
    private String description;

    SubscriberType(String code, String description){
        this.code = code;
        this.description = description;
    }
}