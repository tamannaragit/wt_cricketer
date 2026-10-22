package com.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerStats {
    private Long id;
    private String playerName;
    private String team;
    private String role;
    private int matchesPlayed;
    private int runsScored;
    private int wicketsTaken;
    private double strikeRate;
    private double economyRate;

    public int matchPlayed() {
        return matchesPlayed;
    }

    public void setEconomy(double economyRate) {
        this.economyRate = economyRate;
    }

    public double getEconomy() {
        return economyRate;
    }
}