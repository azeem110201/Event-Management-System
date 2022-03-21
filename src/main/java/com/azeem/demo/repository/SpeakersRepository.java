package com.azeem.demo.repository;

import com.azeem.demo.entity.Speakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakersRepository extends JpaRepository<Speakers, Integer> {
}
