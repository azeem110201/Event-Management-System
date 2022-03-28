package com.azeem.demo.services;

import com.azeem.demo.entity.Speakers;

import java.util.List;

public interface SpeakerService {
    List<Speakers> listSpeakers();
    Speakers getSpeakerById(int id);
    Speakers saveSpeaker(Speakers speaker);
    void deleteSpeaker(int id);
}
