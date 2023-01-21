/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.twothousandandfourtyeight;

import java.io.Serializable;

public class ScoreData implements Serializable {
    Score bestScores[] = new Score[10];
    Time bestTimes[] = new Time[10];

    public static class Score {
        String playerName;
        long score;
    }
    public static class Time {
        //list of best times for various numbers
        //todo figure out how this datatype should work
    }
}
