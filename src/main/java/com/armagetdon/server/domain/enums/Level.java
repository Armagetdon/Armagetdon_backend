package com.armagetdon.server.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {
    TROPOSPHERE("대류권", 0, 10000),
    STRATOSPHERE("성층권", 10000, 50000),
    MESOSPHERE("중간권", 50000, 80000),
    THERMOSPHERE("열권", 80000, 100000),
    WILDERNESS("광야", 100000, 100000);

    private final String name;
    private final int lowPoint;
    private final int highPoint;

    public static Level getLevelByAltitude(int altitude) {
        for (Level level : values()) {
            if (altitude >= level.getLowPoint() && altitude < level.getHighPoint()) {
                return level;
            }
        }
        return WILDERNESS;
    }

    public int getLeftAltitude(int altitude) {
        return highPoint - altitude;
    }
}
