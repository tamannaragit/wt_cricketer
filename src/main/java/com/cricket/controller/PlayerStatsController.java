package com.cricket.controller;

import com.cricket.entity.PlayerStats;
import com.cricket.service.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-stats")
public class PlayerStatsController {

    @Autowired
    private PlayerStatsService playerStatsService;

    @GetMapping
    public List<PlayerStats> getAllPlayerStats() {
        return playerStatsService.getAllPlayerStats();
    }

    @GetMapping("/{id}")
    public PlayerStats getPlayerStatsById(@PathVariable Long id) {
        return playerStatsService.getPlayerStatsById(id);
    }

    @PutMapping("/{id}")
    public PlayerStats updatePlayerStats(@PathVariable Long id, @RequestBody PlayerStats playerStats) {
        return playerStatsService.updatePlayerStats(id, playerStats);
    }
}