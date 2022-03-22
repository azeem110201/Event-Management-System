package com.azeem.demo.dto;

import com.azeem.demo.entity.Events;
import lombok.Data;

import java.util.List;

@Data
public class SpeakersDTO {
    private int id;
    private String speakerName;
    private String speakerDesignation;
}
