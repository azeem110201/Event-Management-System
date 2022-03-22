package com.azeem.demo.services;

import com.azeem.demo.dto.SpeakersDTO;
import com.azeem.demo.dto.UsersDTO;
import com.azeem.demo.entity.Speakers;
import com.azeem.demo.entity.Users;
import com.azeem.demo.repository.SpeakersRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeakerServiceImplementation implements SpeakerService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SpeakersRepository speakersRepository;


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

    @Override
    public List<SpeakersDTO> getAllSpeakers() {
        return speakersRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private SpeakersDTO convertEntityToDto(Speakers speakers){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SpeakersDTO speakersDTO;
        speakersDTO = modelMapper.map(speakers, SpeakersDTO.class);
        return speakersDTO;
    }

    private Speakers convertDtoToEntity(SpeakersDTO speakersDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Speakers speakers;
        speakers = modelMapper.map(speakersDTO, Speakers.class);
        return speakers;
    }
}
