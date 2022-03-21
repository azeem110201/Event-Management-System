package com.azeem.demo.services;

import com.azeem.demo.entity.Speakers;
import com.azeem.demo.repository.SpeakersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeakerServiceImplementation implements SpeakerService{
    private SpeakersRepository speakersRepository;

    @Autowired
    public SpeakerServiceImplementation(SpeakersRepository speakersRepository){
        this.speakersRepository = speakersRepository;
    }

    @Override
    public List<Speakers> listSpeakers() {
        return speakersRepository.findAll();
    }

    @Override
    public Speakers getSpeakerById(int id) {
        Optional<Speakers> result = Optional.of(speakersRepository.getById(id));

        Speakers theSpeaker = null;

        if (result.isPresent()) {
            theSpeaker = result.get();
        }
        else {
            // we didn't find the speaker
            throw new RuntimeException("Did not find speaker id - " + id);
        }

        return theSpeaker;
    }

    @Override
    public void saveSpeaker(Speakers speaker) {
        speakersRepository.save(speaker);
    }

    @Override
    public void deleteSpeaker(int id) {
        speakersRepository.deleteById(id);
    }
}
