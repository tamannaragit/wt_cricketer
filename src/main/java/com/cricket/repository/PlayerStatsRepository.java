package com.cricket.repository;

import com.cricket.entity.PlayerStats;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PlayerStatsRepository {

    private final Map<Long, PlayerStats> players = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public PlayerStatsRepository() {
        save(PlayerStats.builder().id(1L).playerName("Virat Kohli").team("India").matchesPlayed(250).runsScored(12000).wicketsTaken(4).strikeRate(138.2).economyRate(6.5).build());
        save(PlayerStats.builder().id(2L).playerName("Rohit Sharma").team("India").matchesPlayed(240).runsScored(10500).wicketsTaken(8).strikeRate(140.0).economyRate(7.1).build());
        save(PlayerStats.builder().id(3L).playerName("Jasprit Bumrah").team("India").matchesPlayed(120).runsScored(200).wicketsTaken(250).strikeRate(80.5).economyRate(6.6).build());
        save(PlayerStats.builder().id(4L).playerName("Pat Cummins").team("Australia").matchesPlayed(110).runsScored(800).wicketsTaken(190).strikeRate(110.0).economyRate(7.8).build());
    }

    public List<PlayerStats> findAll() {
        return new ArrayList<>(players.values());
    }

    public Optional<PlayerStats> findById(Long id) {
        return Optional.ofNullable(players.get(id));
    }

    public PlayerStats save(PlayerStats player) {
        if (player.getId() == null) {
            player.setId(sequence.getAndIncrement());
        }
        players.put(player.getId(), player);
        return player;
    }
}