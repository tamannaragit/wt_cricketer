package com.cricket.service;

import com.cricket.entity.PlayerStats;
import com.cricket.repository.PlayerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsService {

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    public List<PlayerStats> getAllPlayerStats() {
        return playerStatsRepository.findAll();
    }

    public PlayerStats getPlayerStatsById(Long id) {
        return playerStatsRepository.findById(id).orElse(null);
    }

    public PlayerStats updatePlayerStats(Long id, PlayerStats updatedStats) {
        PlayerStats existing = playerStatsRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setPlayerName(updatedStats.getPlayerName());
            existing.setTeam(updatedStats.getTeam());
            existing.setRole(updatedStats.getRole());
            existing.setMatchesPlayed(updatedStats.getMatchesPlayed());
            existing.setRunsScored(updatedStats.getRunsScored());
            existing.setWicketsTaken(updatedStats.getWicketsTaken());
            existing.setStrikeRate(updatedStats.getStrikeRate());
            existing.setEconomyRate(updatedStats.getEconomyRate());
            return playerStatsRepository.save(existing);
        }
        return null;
    }
}