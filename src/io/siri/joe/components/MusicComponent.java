package io.siri.joe.components;

import io.siri.joe.*;

/**
 * Implements playing music directly to a GameObject near you!
 * @author Siri
 */
public class MusicComponent extends Component {
    String id;
    MusicControls musicControls;

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
    }

    /**
     * Handles User Control of Music Playback through Keybinds.
     * @author Siri
     */
    @Override
    public void tic(int[] inputs){
        if (!enabled) return;
        for (int keyCode : inputs){
            //this can't be a switch bc music controls aren't constant.
            if (keyCode == musicControls.play) {
                Core.c.music.play(id);
            }
            if (keyCode == musicControls.resume) {
                Core.c.music.resume(id);
            }
            if (keyCode == musicControls.pause) {
                Core.c.music.pause(id);
            }
            if (keyCode == musicControls.stop) {
                Core.c.music.stop(id);
            }
            if (keyCode == musicControls.restart) {
                Core.c.music.restart(id);
            }
        }
    }

    /**
     * User Keybindings for MusicComponents, using KeyCodes from {@link java.awt.event.KeyEvent}.
     * @apiNote All bindings default to -1, which does not match any KeyCode.
     *          This way, you can effectively unbind every control that you don't want players touching.
     * @author Siri
     */
    //this has to be a full class and not just a struct because fuck java.
    //because this is here, todo: make a pause/resume toggle.
    public static class MusicControls {
        public int play = -1;
        public int resume = -1;
        public int pause = -1;
        public int stop = -1;
        public int restart = -1;
    }
}
