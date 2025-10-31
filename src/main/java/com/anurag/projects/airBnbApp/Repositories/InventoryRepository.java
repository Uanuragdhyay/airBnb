package com.anurag.projects.airBnbApp.Repositories;

import com.anurag.projects.airBnbApp.Entities.Inventory;
import com.anurag.projects.airBnbApp.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
