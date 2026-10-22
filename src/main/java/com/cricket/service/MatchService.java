package com.cricket.service;

import com.cricket.entity.Match;
import com.cricket.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Match> getActiveMatches() {
        return matchRepository.findAll().stream()
                .filter(m -> "LIVE".equalsIgnoreCase(m.getMatchStatus()))
                .sorted(Comparator.comparing(m -> m.getLastBallTime() != null ? m.getLastBallTime() : LocalDateTime.MIN, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public List<Match> getRecentMatches() {
        return matchRepository.findAll().stream()
                .filter(m -> !"LIVE".equalsIgnoreCase(m.getMatchStatus()))
                .sorted(Comparator.comparing(m -> m.getMatchStartedAt() != null ? m.getMatchStartedAt() : LocalDateTime.MIN, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public Match updateMatchScore(Long id, int runs, int wickets, String batsman, String bowler) {
        Match match = matchRepository.findById(id).orElse(null);
        if (match != null) {
            match.updateScore(runs, wickets, batsman, bowler);
            match.setLastBallTime(LocalDateTime.now());
            return matchRepository.save(match);
        }
        return null;
    }

    public Match updateMatchStatus(Long id, String status) {
        Match match = matchRepository.findById(id).orElse(null);
        if (match != null) {
            match.setMatchStatus(status);
            return matchRepository.save(match);
        }
        return null;
    }
}