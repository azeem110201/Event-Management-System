package com.azeem.demo.services;

import com.azeem.demo.entity.Events;
import com.azeem.demo.entity.Speakers;
import com.azeem.demo.repository.EventsRepository;
import com.azeem.demo.repository.SpeakersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpeakerServiceImplementationTest {
    @Autowired
    private SpeakerService speakerService;

    @MockBean
    private SpeakersRepository speakersRepository;

    @Test
    void listSpeakers() {
        when(speakersRepository.findAll()).thenReturn(Stream
                .of(new Speakers("XYZ", "Metaverse expert")).collect(Collectors.toList()));

        assertEquals(1, speakerService.listSpeakers().size());
    }

    @Test
    void saveSpeaker() {
        Speakers speakers = new Speakers("XYZ", "Metaverse expert");
        when(speakersRepository.save(speakers)).thenReturn(speakers);

        assertEquals(speakers, speakerService.saveSpeaker(speakers));
    }

    @Test
    void deleteSpeaker() {
        Speakers speakers = new Speakers("XYZ", "Metaverse expert");

        speakerService.deleteSpeaker(speakers.getId());
        verify(speakersRepository, times(1)).deleteById(speakers.getId());
    }
}