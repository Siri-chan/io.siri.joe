package io.siri.joe.audio;

/**
 * @apiNote Stopped: at now == 0, where Paused: at now == Clip.microsecondlength
 */
public enum MusicStatus {
    Playing,
    Paused,
    Stopped,
}
