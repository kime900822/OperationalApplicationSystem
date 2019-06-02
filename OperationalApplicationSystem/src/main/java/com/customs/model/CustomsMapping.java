package com.customs.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "t_customs_mapping")
public class CustomsMapping {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    String id;

    @Column
    String customsSerialNumber;
    @Column
    String customsCode;
    @Column
    String customsMaterialDescription;
    @Column
    String JDEItemCode;
    @Column
    String JDEItemDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomsSerialNumber() {
        return customsSerialNumber;
    }

    public void setCustomsSerialNumber(String customsSerialNumber) {
        this.customsSerialNumber = customsSerialNumber;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCustomsMaterialDescription() {
        return customsMaterialDescription;
    }

    public void setCustomsMaterialDescription(String customsMaterialDescription) {
        this.customsMaterialDescription = customsMaterialDescription;
    }

    public String getJDEItemCode() {
        return JDEItemCode;
    }

    public void setJDEItemCode(String JDEItemCode) {
        this.JDEItemCode = JDEItemCode;
    }

    public String getJDEItemDescription() {
        return JDEItemDescription;
    }

    public void setJDEItemDescription(String JDEItemDescription) {
        this.JDEItemDescription = JDEItemDescription;
    }
}
