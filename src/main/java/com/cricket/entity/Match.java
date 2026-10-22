package com.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    private Long id;
    private String teamA;
    private String teamB;
    private String matchStatus; // "LIVE", "COMPLETED", "SCHEDULED"
    private int totalRuns;
    private int wickets;
    private double overs;
    private double currentRunRate;
    private LocalDateTime matchStartedAt;
    private LocalDateTime lastBallTime;

    @Builder.Default
    private List<String> commentary = new ArrayList<>();

    public String getStatus() {
        return matchStatus;
    }

    public void setStatus(String status) {
        this.matchStatus = status;
    }

    public void updateScore(int runs, int newWickets, String batsman, String bowler) {
        this.totalRuns += runs;
        this.wickets += newWickets;
        
        // Overs logic (e.g., 0.5 -> 1.0)
        double currentOvers = this.overs;
        int balls = (int) Math.round((currentOvers - Math.floor(currentOvers)) * 10) + 1;
        int completedOvers = (int) Math.floor(currentOvers);
        
        if (balls >= 6) {
            completedOvers++;
            balls = 0;
        }
        this.overs = completedOvers + (balls / 10.0);
        
        // Run rate logic
        double totalOversDecimal = completedOvers + (balls / 6.0);
        if (totalOversDecimal > 0) {
            this.currentRunRate = Math.round((this.totalRuns / totalOversDecimal) * 100.0) / 100.0;
        }

        this.lastBallTime = LocalDateTime.now();
        if (this.commentary == null) {
            this.commentary = new ArrayList<>();
        }
        this.commentary.add(0, String.format("%.1f: %d runs (%s to %s)%s", 
            this.overs, runs, bowler, batsman, newWickets > 0 ? " - WICKET!" : ""));
    }
}