package com.cricket.repository;

import com.cricket.entity.Match;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MatchRepository {

    private final Map<Long, Match> matches = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public MatchRepository() {
        save(Match.builder()
                .id(1L)
                .teamA("India")
                .teamB("Australia")
                .matchStatus("LIVE")
                .totalRuns(185)
                .wickets(4)
                .overs(18.2)
                .currentRunRate(10.09)
                .matchStartedAt(LocalDateTime.now().minusHours(2))
                .lastBallTime(LocalDateTime.now())
                .commentary(new ArrayList<>(List.of("18.2: 4 runs by Virat Kohli", "18.1: 1 run")))
                .build());

        save(Match.builder()
                .id(2L)
                .teamA("England")
                .teamB("Pakistan")
                .matchStatus("LIVE")
                .totalRuns(142)
                .wickets(6)
                .overs(15.0)
                .currentRunRate(9.47)
                .matchStartedAt(LocalDateTime.now().minusHours(1))
                .lastBallTime(LocalDateTime.now().minusMinutes(5))
                .commentary(new ArrayList<>(List.of("15.0: WICKET! Bowled")))
                .build());

        save(Match.builder()
                .id(3L)
                .teamA("South Africa")
                .teamB("New Zealand")
                .matchStatus("COMPLETED")
                .totalRuns(210)
                .wickets(8)
                .overs(20.0)
                .currentRunRate(10.50)
                .matchStartedAt(LocalDateTime.now().minusDays(1))
                .lastBallTime(LocalDateTime.now().minusDays(1).plusHours(3))
                .commentary(new ArrayList<>(List.of("Match finished. South Africa won.")))
                .build());
    }

    public List<Match> findAll() {
        return new ArrayList<>(matches.values());
    }

    public Optional<Match> findById(Long id) {
        return Optional.ofNullable(matches.get(id));
    }

    public Match save(Match match) {
        if (match.getId() == null) {
            match.setId(sequence.getAndIncrement());
        }
        matches.put(match.getId(), match);
        return match;
    }
}