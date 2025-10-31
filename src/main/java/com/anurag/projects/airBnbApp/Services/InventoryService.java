package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.Entities.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
