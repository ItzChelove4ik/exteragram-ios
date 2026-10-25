package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.view.Surface;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class MediaCodecPlayer {
    private final MediaCodec codec;
    private boolean done;
    private final MediaExtractor extractor;
    private boolean first;
    private final int h;
    private long lastPositionUs;
    private final int o;
    private final Surface outputSurface;
    private final int w;

    public MediaCodecPlayer(String r8, Surface r9) {
        this.first = true;
        this.lastPositionUs = 0;
        this.outputSurface = r9;
        MediaExtractor r0 = new MediaExtractor();
        this.extractor = r0;
        r0.setDataSource(r8);
        int r02 = 0;
    L4:
        if (r02 >= this.extractor.getTrackCount()) goto L9;
        MediaFormat r1 = this.extractor.getTrackFormat(r02);
        if (r1.getString("mime").startsWith("video/") == true) goto L10;
        r02 = r02 + 1;
    L10:
        if (r02 == (-1)) goto L18;
        if (r1 == null) goto L18;
        this.extractor.selectTrack(r02);
        this.w = r1.getInteger("width");
        this.h = r1.getInteger("height");
        if (r1.containsKey("rotation-degrees") == false) goto L15;
        this.o = r1.getInteger("rotation-degrees");
    L16:
        MediaCodec r03 = MediaCodec.createDecoderByType(r1.getString("mime"));
        this.codec = r03;
        r03.configure(r1, r9, null, 0);
        r03.start();
        return;
    L15:
        this.o = 0;
    L18:
        f$$ExternalSyntheticBUOutline1.m("No video track found in file.");
        throw null;
    L9:
        r1 = null;
        r02 = -1;
        goto L10
    }

    public boolean ensure(long r12) {
        if (this.done == false) goto L5;
        return false;
    L5:
        boolean r0 = this.first;
        this.first = false;
        long r122 = r12 * 1000;
        if (r0 == true) goto L11;
        if (r122 > this.lastPositionUs) goto L11;
        return false;
    L11:
        if (this.extractor.getSampleTime() > r122) goto L15;
        if (r0 == false) goto L16;
        if (r122 > 1000000) goto L15;
    L16:
        int r5 = this.codec.dequeueInputBuffer(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        if (r5 < 0) goto L25;
        ByteBuffer r02 = this.codec.getInputBuffer(r5);
        if (r02 == null) goto L25;
        int r7 = this.extractor.readSampleData(r02, 0);
        if (r7 <= 0) goto L23;
        long r8 = this.extractor.getSampleTime();
        this.codec.queueInputBuffer(r5, 0, r7, r8, this.extractor.getSampleFlags());
        this.extractor.advance();
        goto L25
    L23:
        this.codec.queueInputBuffer(r5, 0, 0, 0, 4);
        release();
        return false;
    L25:
        MediaCodec.BufferInfo r03 = new MediaCodec.BufferInfo();
        int r2 = this.codec.dequeueOutputBuffer(r03, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        if (r2 < 0) goto L16;
        long r3 = r03.presentationTimeUs;
        if (r3 >= (r122 - 16000)) goto L29;
        this.codec.releaseOutputBuffer(r2, false);
        goto L16
    L29:
        this.lastPositionUs = r3;
        this.codec.releaseOutputBuffer(r2, true);
        return true;
    L15:
        this.extractor.seekTo(r122, 0);
        goto L16
    }

    public int getHeight() {
        return this.h;
    }

    public int getOrientation() {
        return this.o;
    }

    public int getOrientedHeight() {
        if (((this.o / 90) % 2) != 1) goto L7;
        return this.w;
    L7:
        return this.h;
    }

    public int getOrientedWidth() {
        if (((this.o / 90) % 2) != 1) goto L7;
        return this.h;
    L7:
        return this.w;
    }

    public int getWidth() {
        return this.w;
    }

    public void release() {
        if (this.done == true) goto L13;
        this.done = true;
        MediaCodec r0 = this.codec;
        if (r0 == null) goto L8;
        r0.stop();
        this.codec.release();
    L8:
        MediaExtractor r1 = this.extractor;
        if (r1 == null) goto L12;
        r1.release();
        return;
    L12:
        return;
    }
}
