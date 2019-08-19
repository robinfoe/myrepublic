package my.com.common.enumeration;

import lombok.Getter;

/**
 * ServiceCategoryEnum
 */
public enum ServiceCategoryEnum {
    MOBILE("M", "Mobile / Mobile Number"),
    TALK_TIME("T", "Talk Time"),
    DATA("D" , "Data Size"),
    DATA_BOOST("B", "Upsize Data");


    @Getter
    private String code;

    @Getter
    private String description;

    ServiceCategoryEnum(String code, String description){
        this.code = code;
        this.description = description;
    }


    
}