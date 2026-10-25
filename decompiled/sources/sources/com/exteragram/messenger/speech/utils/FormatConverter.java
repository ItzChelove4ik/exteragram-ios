package com.exteragram.messenger.speech.utils;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import kotlin.UByte;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class FormatConverter {

    public static class LazyPcmInputStream extends InputStream implements InputStreamRetargetInterface {
        private final MediaCodec.BufferInfo bufferInfo;
        private final MediaCodec codec;
        private ByteBuffer currentOutputBuffer;
        private final MediaExtractor extractor;
        private final ByteBuffer[] inputBuffers;
        private boolean isEOS;
        private final boolean limitToOneMinute;
        private final long oneMinuteUs;
        private ByteBuffer[] outputBuffers;
        private long totalDecodedDurationUs;

        public LazyPcmInputStream(String r5, boolean r6) {
            this.oneMinuteUs = 60000000;
            this.limitToOneMinute = r6;
            this.bufferInfo = new MediaCodec.BufferInfo();
            MediaExtractor r62 = new MediaExtractor();
            this.extractor = r62;
            r62.setDataSource(r5);
            MediaFormat r0 = r62.getTrackFormat(0);
            String r1 = r0.getString("mime");
            if (r1.startsWith("audio/") == false) goto L6;
            MediaCodec r12 = MediaCodec.createDecoderByType(r1);
            this.codec = r12;
            r12.configure(r0, null, null, 0);
            r12.start();
            this.inputBuffers = r12.getInputBuffers();
            this.outputBuffers = r12.getOutputBuffers();
            r62.selectTrack(0);
            return;
        L6:
            Model$$ExternalSyntheticBUOutline0.m("Not an audio file");
            throw null;
        }

        private ByteBuffer getNextOutputBuffer() {
        L3:
            if (this.isEOS == true) goto L23;
            int r4 = this.codec.dequeueInputBuffer(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            if (r4 < 0) goto L10;
            ByteBuffer r3 = this.inputBuffers[r4];
            int r6 = this.extractor.readSampleData(r3, 0);
            if (r6 >= 0) goto L9;
            this.codec.queueInputBuffer(r4, 0, 0, 0, 4);
            this.isEOS = true;
            goto L10
        L9:
            long r7 = this.extractor.getSampleTime();
            this.codec.queueInputBuffer(r4, 0, r6, r7, 0);
            this.extractor.advance();
        L10:
            int r1 = this.codec.dequeueOutputBuffer(this.bufferInfo, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            if (r1 != (-3)) goto L13;
            this.outputBuffers = this.codec.getOutputBuffers();
            goto L3
        L13:
            if (r1 == (-2)) goto L3;
            if (r1 == (-1)) goto L3;
            ByteBuffer r2 = this.outputBuffers[r1];
            ByteBuffer r32 = ByteBuffer.allocate(this.bufferInfo.size);
            r32.put(r2);
            r32.flip();
            this.codec.releaseOutputBuffer(r1, false);
            long r12 = this.totalDecodedDurationUs;
            long r13 = r12 + (this.bufferInfo.presentationTimeUs - r12);
            this.totalDecodedDurationUs = r13;
            if (this.limitToOneMinute == true) goto L19;
        L21:
            return r32;
        L19:
            if (r13 < 60000000) goto L21;
            this.isEOS = true;
            goto L21
        L23:
            return null;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.codec.stop();
            this.codec.release();
            this.extractor.release();
            super.close();
        }

        @Override // java.io.InputStream
        public int read() {
            byte[] r1 = new byte[1];
            if (read(r1, 0, 1) != (-1)) goto L6;
            return -1;
        L6:
            return r1[0] & UByte.MAX_VALUE;
        }

        @Override // java.io.InputStream
        public int read(byte[] r6, int r7, int r8) {
            if (this.isEOS == false) goto L5;
            return -1;
        L5:
            int r0 = 0;
        L6:
            if (r0 >= r8) goto L17;
            if (this.isEOS == true) goto L17;
            ByteBuffer r2 = this.currentOutputBuffer;
            if (r2 != null) goto L12;
        L13:
            ByteBuffer r22 = getNextOutputBuffer();
            this.currentOutputBuffer = r22;
            if (r22 == null) goto L17;
        L16:
            int r23 = Math.min(r8 - r0, this.currentOutputBuffer.remaining());
            this.currentOutputBuffer.get(r6, r7 + r0, r23);
            r0 = r0 + r23;
            goto L6
        L12:
            if (r2.hasRemaining() == true) goto L16;
        L17:
            if (r0 <= 0) goto L19;
            return r0;
        L19:
            return -1;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public static InputStream extractAndConvertToPcm(String r1, boolean r2) {
        return new LazyPcmInputStream(r1, r2);
    }

    public static int getSampleRate(String r7) {
        MediaExtractor r1 = new MediaExtractor();
        r1.setDataSource(r7);     // Catch: Throwable -> L13 IOException -> L15
        int r72 = r1.getTrackCount();     // Catch: Throwable -> L13 IOException -> L15
        int r3 = 0;
    L4:
        if (r3 >= r72) goto L18;
        MediaFormat r4 = r1.getTrackFormat(r3);     // Catch: Throwable -> L13 IOException -> L15
        String r5 = r4.getString("mime");     // Catch: Throwable -> L13 IOException -> L15
        if (r5 == null) goto L17;
        if (r5.startsWith("audio/") == false) goto L17;
        if (r4.containsKey("sample-rate") == false) goto L17;
        int r73 = r4.getInteger("sample-rate");     // Catch: Throwable -> L13 IOException -> L15
    L19:
        r1.release();
    L22:
        if (r73 != (-1)) goto L35;
        return 48000;
    L35:
        return r73;
    L17:
        r3 = r3 + 1;
        goto L4
    L18:
        r73 = -1;
    L13:
        th = move-exception;
        r1.release();
        throw th;
    L15:
        e = move-exception;
        Log.e("exteraGram", "Error detecting sample rate", e);     // Catch: Throwable -> L13
        r1.release();
        r73 = -1;
        goto L22
    }
}
