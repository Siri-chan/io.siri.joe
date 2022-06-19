package io.siri.joe;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.net.URI;

/**
 * An asset-handling class for Sound Clips
 */
public class SoundAsset extends Asset {
    /**
     * Checks the Validity of the SoundAsset
     *
     * @return True if the Audio File is Valid
     * @author Siri
     */
    @Override
    public boolean checkValidity(){
        try {
            var q = AudioSystem.getAudioInputStream( this);
            var c = AudioSystem.getClip();
            c.open(q);
            c.stop();
            c.close();
            return true;
        } catch (Exception _e){
            return false;
        }
    }

    /**
     * Creates a new {@code SoundAsset} instance by converting the given
     * pathname string into an abstract pathname.  If the given string is
     * the empty string, then the result is the empty abstract pathname.
     *
     * @param pathname A pathname string
     * @throws InvalidAssetFileException the invalid asset file exception
     */
    public SoundAsset(String pathname) throws InvalidAssetFileException {
        super(pathname);
    }

    /**
     * Creates a new {@code File} instance from a parent pathname string
     * and a child pathname string.
     *
     * <p> If {@code parent} is {@code null} then the new
     * {@code File} instance is created as if by invoking the
     * single-argument {@code File} constructor on the given
     * {@code child} pathname string.
     *
     * <p> Otherwise the {@code parent} pathname string is taken to denote
     * a directory, and the {@code child} pathname string is taken to
     * denote either a directory or a file.  If the {@code child} pathname
     * string is absolute then it is converted into a relative pathname in a
     * system-dependent way.  If {@code parent} is the empty string then
     * the new {@code File} instance is created by converting
     * {@code child} into an abstract pathname and resolving the result
     * against a system-dependent default directory.  Otherwise each pathname
     * string is converted into an abstract pathname and the child abstract
     * pathname is resolved against the parent.
     *
     * @param parent The parent pathname string
     * @param child  The child pathname string
     * @throws InvalidAssetFileException the invalid asset file exception
     */
    public SoundAsset(String parent, String child) throws InvalidAssetFileException {
        super(parent, child);
    }

    /**
     * Creates a new {@code File} instance from a parent abstract
     * pathname and a child pathname string.
     *
     * <p> If {@code parent} is {@code null} then the new
     * {@code File} instance is created as if by invoking the
     * single-argument {@code File} constructor on the given
     * {@code child} pathname string.
     *
     * <p> Otherwise the {@code parent} abstract pathname is taken to
     * denote a directory, and the {@code child} pathname string is taken
     * to denote either a directory or a file.  If the {@code child}
     * pathname string is absolute then it is converted into a relative
     * pathname in a system-dependent way.  If {@code parent} is the empty
     * abstract pathname then the new {@code File} instance is created by
     * converting {@code child} into an abstract pathname and resolving
     * the result against a system-dependent default directory.  Otherwise each
     * pathname string is converted into an abstract pathname and the child
     * abstract pathname is resolved against the parent.
     *
     * @param parent The parent abstract pathname
     * @param child  The child pathname string
     * @throws InvalidAssetFileException the invalid asset file exception
     */
    public SoundAsset(File parent, String child) throws InvalidAssetFileException {
        super(parent, child);
    }

    /**
     * Creates a new {@code File} instance by converting the given
     * {@code file:} URI into an abstract pathname.
     *
     * <p> The exact form of a {@code file:} URI is system-dependent, hence
     * the transformation performed by this constructor is also
     * system-dependent.
     *
     * <p> For a given abstract pathname <i>f</i> it is guaranteed that
     *
     * <blockquote><code>
     * new File(</code><i>&nbsp;f</i><code>.{@link #toURI()
     * toURI}*()).equals(</code><i>&nbsp;f</i><code>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </code></blockquote>
     * <p>
     * so long as the original abstract pathname, the URI, and the new abstract
     * pathname are all created in (possibly different invocations of) the same
     * Java virtual machine.  This relationship typically does not hold,
     * however, when a {@code file:} URI that is created in a virtual machine
     * on one operating system is converted into an abstract pathname in a
     * virtual machine on a different operating system.
     *
     * @param uri An absolute, hierarchical URI with a scheme equal to            {@code "file"}, a non-empty path component, and undefined            authority, query, and fragment components
     * @throws InvalidAssetFileException the invalid asset file exception
     * @see #toURI() #toURI()
     * @see URI
     * @since 1.4
     */
    public SoundAsset(URI uri) throws InvalidAssetFileException {
        super(uri);
    }
}
