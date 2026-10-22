package com.cricket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BallByBall {

    private String batsman;
    private String bowler;
    private int runs;
    private String result;
    private String extraType;
    private LocalDateTime ballTime;

    public BallByBall(String batsman, String bowler, int runs, String result, String extraType) {
        this.batsman = batsman;
        this.bowler = bowler;
        this.runs = runs;
        this.result = result;
        this.extraType = extraType;
        this.ballTime = LocalDateTime.now();
    }
}
