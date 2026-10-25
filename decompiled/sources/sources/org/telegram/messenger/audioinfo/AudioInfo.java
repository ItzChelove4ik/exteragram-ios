package org.telegram.messenger.audioinfo;

import android.graphics.Bitmap;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import org.telegram.messenger.audioinfo.m4a.M4AInfo;
import org.telegram.messenger.audioinfo.mp3.MP3Info;

/* loaded from: classes5.dex */
public abstract class AudioInfo {
    protected String album;
    protected String albumArtist;
    protected String artist;
    protected String brand;
    protected String comment;
    protected boolean compilation;
    protected String composer;
    protected String copyright;
    protected Bitmap cover;
    private File coverFile;
    protected short disc;
    protected short discs;
    protected long duration;
    protected String genre;
    protected String grouping;
    protected String lyrics;
    protected Bitmap smallCover;
    protected String title;
    protected short track;
    protected short tracks;
    protected String version;
    protected short year;

    public AudioInfo() {
    }

    public static AudioInfo getAudioInfo(File r8) {
        byte[] r0 = new byte[12];     // Catch: Exception -> L46
        RandomAccessFile r2 = new RandomAccessFile(r8, "r");     // Catch: Exception -> L46
        r2.readFully(r0, 0, 8);     // Catch: Exception -> L46
        r2.close();     // Catch: Exception -> L46
        BufferedInputStream r22 = new BufferedInputStream(new FileInputStream(r8));     // Catch: Exception -> L46
        if (r0[4] != 102) goto L14;
        if (r0[5] != 116) goto L14;
        if (r0[6] != 121) goto L14;
        if (r0[7] != 112) goto L14;
        return new M4AInfo(r22);
    L14:
        if (r0[0] != 102) goto L26;
        if (r0[1] != 76) goto L26;
        if (r0[2] != 97) goto L26;
        if (r0[3] != 99) goto L26;
        OtherAudioInfo r02 = new OtherAudioInfo(r8);     // Catch: Exception -> L46
        if (r02.failed == false) goto L24;
        return null;
    L24:
        return r02;
    L26:
        if (r8.getAbsolutePath().endsWith(HlsSegmentFormat.MP3) == true) goto L45;
        byte r3 = r0[0];     // Catch: Exception -> L46
        if (r3 != 73) goto L34;
        if (r0[1] != 68) goto L34;
        if (r0[2] == 51) goto L45;
    L34:
        if (r3 == 84) goto L36;
    L40:
        OtherAudioInfo r03 = new OtherAudioInfo(r8);     // Catch: Exception -> L46
        if (r03.failed == false) goto L43;
        return null;
    L43:
        return r03;
    L36:
        if (r0[1] != 65) goto L40;
        if (r0[2] != 71) goto L40;
    L45:
        return new MP3Info(r22, r8.length());
    L46:
        return null;
    }

    public String getAlbum() {
        return this.album;
    }

    public String getAlbumArtist() {
        return this.albumArtist;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getComment() {
        return this.comment;
    }

    public String getComposer() {
        return this.composer;
    }

    public String getCopyright() {
        return this.copyright;
    }

    public Bitmap getCover() {
        return this.cover;
    }

    public File getCoverFile() {
        return this.coverFile;
    }

    public short getDisc() {
        return this.disc;
    }

    public short getDiscs() {
        return this.discs;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getGrouping() {
        return this.grouping;
    }

    public String getLyrics() {
        return this.lyrics;
    }

    public Bitmap getSmallCover() {
        return this.smallCover;
    }

    public String getTitle() {
        return this.title;
    }

    public short getTrack() {
        return this.track;
    }

    public short getTracks() {
        return this.tracks;
    }

    public short getYear() {
        return this.year;
    }

    public boolean isCompilation() {
        return this.compilation;
    }

    public void setCoverFile(File r1) {
        this.coverFile = r1;
    }
}
