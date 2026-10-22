package com.cricket.controller;

import com.cricket.entity.Match;
import com.cricket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/active")
    public List<Match> getActiveMatches() {
        return matchService.getActiveMatches();
    }

    @GetMapping("/recent")
    public List<Match> getRecentMatches() {
        return matchService.getRecentMatches();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @PostMapping("/{id}/update")
    public Match updateMatchScore(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int runs,
            @RequestParam(defaultValue = "0") int wickets,
            @RequestParam(defaultValue = "Batsman") String batsman,
            @RequestParam(defaultValue = "Bowler") String bowler) {
        return matchService.updateMatchScore(id, runs, wickets, batsman, bowler);
    }

    @PutMapping("/{id}/status")
    public Match updateMatchStatus(@PathVariable Long id, @RequestParam String status) {
        return matchService.updateMatchStatus(id, status);
    }
}