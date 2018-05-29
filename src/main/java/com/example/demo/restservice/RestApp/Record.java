package com.example.demo.restservice.RestApp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String type;
    private String slotContent;
    private String slotDesc;
    private String slotSentence;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlotContent() {
        return slotContent;
    }

    public void setSlotContent(String slotContent) {
        this.slotContent = slotContent;
    }

    public String getSlotDesc() {
        return slotDesc;
    }

    public void setSlotDesc(String slotDesc) {
        this.slotDesc = slotDesc;
    }

    public String getSlotSentence() {
        return slotSentence;
    }

    public void setSlotSentence(String slotSentence) {
        this.slotSentence = slotSentence;
    }
}
