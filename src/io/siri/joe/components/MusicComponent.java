/*
 * Copyright (c) 2022-2024. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.joe.components;

import io.siri.joe.*;

import java.util.Arrays;

/**
 * Implements playing music directly to a GameObject near you!
 * @author Siri
 */
public class MusicComponent extends Component {
    String id;
    MusicControls musicControls;
    boolean playState;

    /**
     * Initialises the Music Component without any Player Keybindings.
     * @param parent The parent GameObject. In 99% of cases, use the keyword `this`
     * @param id An ID used to access the sound.
     * @param path A path to the Sound Asset to play
     * @author Siri
     * @apiNote Begins playing instantly. Use the constructor with {@link MusicControls} in it to let the user
     *          play/pause the music, or use the methods in {@link MusicHandler} with `id` to control playback.
     */
    public MusicComponent(GameObject parent, String id, /*eventually this will take SoundAsset, but for now just gimme string path*/String path){
        super(parent);
        this.id = id;
        musicControls = new MusicControls();
        try {
            Core.c.music.add(id, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Core.c.music.play(id);
        playState = true;
    }
    /**
     * Initialises the Music Component without any Player Keybindings.
     * @param parent The parent GameObject. In 99% of cases, use the keyword `this`
     * @param id An ID used to access the sound.
     * @param path A path to the Sound Asset to play
     * @param controls User Keybindings from {@link java.awt.event.KeyEvent} to control music.
     * @author Siri
     * @apiNote Begins playing only as the Player starts it. Use the constructor without {@link MusicControls} in it to autoplay the music,
     *          or use the methods in {@link MusicHandler} with `id` to control playback.
     * @see MusicControls
     */
    public MusicComponent(GameObject parent, String id, /*todo: eventually this will take Asset, but for now just gimme string path*/String path, MusicControls controls){
        super(parent);
        this.id = id;
        musicControls = controls;
        try {
            Core.c.music.add(id, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Core.c.music.play(id);
        playState = true;
    }

    /**
     * Handles User Control of Music Playback through Keybinds.
     * @apiNote Toggling can be broken when not controlled through one of these components
     * @author Siri
     */
    @Override
    public void tic(double delta, int[] inputs){
        if (!enabled) return;
        for (int keyCode : inputs){
            //this can't be a switch bc music controls aren't constant.
            if (keyCode == musicControls.play && !musicControls.playDown) {
                Core.c.music.play(id);
                playState = true;
            }
            if (keyCode == musicControls.resume && !musicControls.resumeDown) {
                Core.c.music.resume(id);
                playState = true;
            }
            if (keyCode == musicControls.pause && !musicControls.pauseDown) {
                Core.c.music.pause(id);
                playState = false;
            }
            if (keyCode == musicControls.stop && !musicControls.stopDown) {
                Core.c.music.stop(id);
                Core.c.music.play(id);
                playState = false;
            }
            if (keyCode == musicControls.restart && !musicControls.restartDown) {
                Core.c.music.restart(id);
                playState = true;
            }
            if (keyCode == musicControls.toggle && !musicControls.toggleDown) {
                if (playState) {
                    Core.c.music.pause(id);
                } else {
                    Core.c.music.resume(id);
                }
            }
        }
        musicControls.updInputState(inputs);
    }

    /**
     * User Keybindings for MusicComponents, using KeyCodes from {@link java.awt.event.KeyEvent}.
     * @apiNote All bindings default to -1, which does not match any KeyCode.
     *          This way, you can effectively unbind every control that you don't want players touching.
     * @author Siri
     */
    //this has to be a full class and not just a struct because fuck java.
    public static class MusicControls {
        public int play = -1;
        public int resume = -1;
        public int pause = -1;
        public int stop = -1;
        public int restart = -1;
        public int toggle = -1;
        protected boolean playDown = false, resumeDown = false, pauseDown = false, stopDown = false, restartDown = false, toggleDown = false;
        void updInputState(int[] inputs) {
            playDown = Arrays.stream(inputs).anyMatch(el -> el == play);
            resumeDown = Arrays.stream(inputs).anyMatch(el -> el == resume);
            pauseDown = Arrays.stream(inputs).anyMatch(el -> el == pause);
            stopDown = Arrays.stream(inputs).anyMatch(el -> el == stop);
            restartDown = Arrays.stream(inputs).anyMatch(el -> el == restart);
            toggleDown = Arrays.stream(inputs).anyMatch(el -> el == toggle);
        }
    }
}
