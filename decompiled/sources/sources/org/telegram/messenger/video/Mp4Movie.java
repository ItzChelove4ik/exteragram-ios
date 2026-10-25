package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.googlecode.mp4parser.util.Matrix;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class Mp4Movie {
    private File cacheFile;
    private int height;
    private Matrix matrix;
    private ArrayList<Track> tracks;
    private int width;

    public Mp4Movie() {
        this.matrix = Matrix.ROTATE_0;
        this.tracks = new ArrayList();
    }

    public void addSample(int r2, long r3, MediaCodec.BufferInfo r5) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 >= this.tracks.size()) goto L9;
        this.tracks.get(r2).addSample(r3, r5);
        return;
    }

    public int addTrack(MediaFormat r4, boolean r5) {
        this.tracks.add(new Track(this.tracks.size(), r4, r5));
        return this.tracks.size() - 1;
    }

    public File getCacheFile() {
        return this.cacheFile;
    }

    public int getHeight() {
        return this.height;
    }

    public long getLastFrameTimestamp(int r2) {
        if (r2 >= 0) goto L4;
        return 0;
    L4:
        if (r2 < this.tracks.size()) goto L7;
        return 0;
    L7:
        return this.tracks.get(r2).getLastFrameTimestamp();
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

    public int getWidth() {
        return this.width;
    }

    public void setCacheFile(File r1) {
        this.cacheFile = r1;
    }

    public void setRotation(int r2) {
        if (r2 != 0) goto L6;
        this.matrix = Matrix.ROTATE_0;
        return;
    L6:
        if (r2 != 90) goto L10;
        this.matrix = Matrix.ROTATE_90;
        return;
    L10:
        if (r2 != 180) goto L14;
        this.matrix = Matrix.ROTATE_180;
        return;
    L14:
        if (r2 != 270) goto L17;
        this.matrix = Matrix.ROTATE_270;
        return;
    }

    public void setSize(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }
}
