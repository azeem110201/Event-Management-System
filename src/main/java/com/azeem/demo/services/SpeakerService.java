package com.azeem.demo.services;

import com.azeem.demo.dto.SpeakersDTO;
import com.azeem.demo.entity.Speakers;

import java.util.List;

public interface SpeakerService {
    List<Speakers> listSpeakers();
    Speakers getSpeakerById(int id);
    void saveSpeaker(Speakers speaker);
    void deleteSpeaker(int id);
    List<SpeakersDTO> getAllSpeakers();
}
