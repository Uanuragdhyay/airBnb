package com.anurag.projects.airBnbApp.Repositories;

import com.anurag.projects.airBnbApp.Entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
