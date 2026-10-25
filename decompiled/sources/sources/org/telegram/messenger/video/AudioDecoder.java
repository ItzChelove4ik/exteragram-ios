package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import java.nio.ByteBuffer;
import org.telegram.messenger.FileLog;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class AudioDecoder {
    private static final int TIMEOUT_USEC = 0;
    private boolean allInputExtracted;
    private int audioIndex;
    private MediaCodec decoder;
    private boolean decodingDone;
    private long endTimeUs;
    private final MediaExtractor extractor;
    private boolean loopingEnabled;
    private long startTimeUs;
    private int trackIndex;

    public static class DecodedBufferData {
        public ByteBuffer byteBuffer;
        public int flags;
        public int index;
        public int offset;
        public long presentationTimeUs;
        public int size;

        public DecodedBufferData() {
            this.byteBuffer = null;
            this.index = -1;
            this.size = 0;
            this.presentationTimeUs = 0;
            this.flags = 0;
            this.offset = 0;
        }
    }

    public AudioDecoder(String r2) {
        this.audioIndex = -1;
        MediaExtractor r0 = new MediaExtractor();
        this.extractor = r0;
        r0.setDataSource(r2);
        init();
    }

    public AudioDecoder(String r2, int r3) {
        this.audioIndex = -1;
        MediaExtractor r0 = new MediaExtractor();
        this.extractor = r0;
        r0.setDataSource(r2);
        this.audioIndex = r3;
        init();
    }

    private void init() {
        selectTrack();
        MediaFormat r0 = this.extractor.getTrackFormat(this.trackIndex);
        MediaCodec r1 = MediaCodec.createDecoderByType(r0.getString("mime"));
        this.decoder = r1;
        r1.configure(r0, null, null, 0);
        this.startTimeUs = 0;
        this.endTimeUs = this.extractor.getTrackFormat(this.trackIndex).getLong("durationUs");     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        this.endTimeUs = -1;
    }

    private void selectTrack() {
        int r0 = this.audioIndex;
        this.trackIndex = r0;
        if (r0 != (-1)) goto L12;
        int r02 = this.extractor.getTrackCount();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L12;
        String r2 = this.extractor.getTrackFormat(r1).getString("mime");
        if (r2 == null) goto L11;
        if (r2.startsWith("audio/") == false) goto L11;
        this.trackIndex = r1;
    L11:
        r1 = r1 + 1;
    L12:
        int r03 = this.trackIndex;
        if (r03 < 0) goto L16;
        this.extractor.selectTrack(r03);
        return;
    L16:
        GlShader$$ExternalSyntheticBUOutline1.m("No audio track found in source");
    }

    public DecodedBufferData decode() {
        DecodedBufferData r0 = new DecodedBufferData();
        boolean r2 = false;
    L3:
        if (r2 == true) goto L39;
        if (this.decodingDone == true) goto L39;
        if (this.allInputExtracted == true) goto L19;
        int r8 = this.decoder.dequeueInputBuffer(0);
        if (r8 < 0) goto L19;
        int r10 = this.extractor.readSampleData(this.decoder.getInputBuffer(r8), 0);
        if (r10 >= 0) goto L13;
    L15:
        boolean r3 = this.loopingEnabled;
        MediaCodec r7 = this.decoder;
        if (r3 == false) goto L18;
        r7.flush();
        this.extractor.seekTo(this.startTimeUs, 0);
        goto L19
    L18:
        r7.queueInputBuffer(r8, 0, 0, 0, 4);
        this.allInputExtracted = true;
        goto L19
    L13:
        if (this.extractor.getSampleTime() > this.endTimeUs) goto L15;
        this.decoder.queueInputBuffer(r8, 0, r10, this.extractor.getSampleTime(), this.extractor.getSampleFlags());
        this.extractor.advance();
    L19:
        MediaCodec.BufferInfo r32 = new MediaCodec.BufferInfo();
        int r4 = this.decoder.dequeueOutputBuffer(r32, 0);
        if (r4 < 0) goto L3;
        r0.byteBuffer = this.decoder.getOutputBuffer(r4);
        r0.index = r4;
        r0.size = r32.size;
        long r42 = r32.presentationTimeUs;
        r0.presentationTimeUs = r42;
        r0.flags = r32.flags;
        r0.offset = r32.offset;
        long r72 = this.startTimeUs;
        if (r42 >= r72) goto L26;
        int r5 = r0.byteBuffer.position() + AudioConversions.usToBytes(r72 - r42, getSampleRate(), getChannelCount());
        if (r5 > r0.byteBuffer.limit()) goto L26;
        r0.byteBuffer.position(r5);
    L26:
        long r43 = r0.presentationTimeUs + AudioConversions.bytesToUs(r0.size, getSampleRate(), getChannelCount());
        long r73 = this.endTimeUs;
        if (r43 <= r73) goto L34;
        int r44 = AudioConversions.usToBytes(r43 - r73, getSampleRate(), getChannelCount());
        if (r44 <= 0) goto L34;
        int r52 = r0.byteBuffer.limit() - r44;
        if (r52 < r0.byteBuffer.position()) goto L34;
        r0.byteBuffer.limit(r52);
    L34:
        if ((r32.flags & 4) == 0) goto L37;
        this.decodingDone = true;
    L37:
        if (r0.byteBuffer.remaining() <= 0) goto L3;
        r2 = true;
    L39:
        return r0;
    }

    public int getBitrateRate() {
        return getOutputMediaFormat().getInteger("bitrate");
    L5:
        return this.getInputMediaFormat().getInteger("bitrate");
    L7:
        return -1;
    }

    public int getChannelCount() {
        return getOutputMediaFormat().getInteger("channel-count");
    L5:
        e = move-exception;
        FileLog.e(e);
        return this.getInputMediaFormat().getInteger("channel-count");
    L9:
        e = move-exception;
        FileLog.e(e);
        return -1;
    }

    public long getDurationUs() {
        return getOutputMediaFormat().getLong("durationUs");
    L5:
        e = move-exception;
        FileLog.e(e);
        return getInputMediaFormat().getLong("durationUs");
    L9:
        e = move-exception;
        FileLog.e(e);
        return -1;
    }

    public long getEndTimeUs() {
        return this.endTimeUs;
    }

    public MediaFormat getInputMediaFormat() {
        return this.extractor.getTrackFormat(this.trackIndex);
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public MediaFormat getMediaFormat() {
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    L3:
        if (getOutputMediaFormat() == null) goto L7;
        return getOutputMediaFormat();
    L7:
        return getInputMediaFormat();
    }

    public MediaFormat getOutputMediaFormat() {
        return this.decoder.getOutputFormat();
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public int getSampleRate() {
        return getOutputMediaFormat().getInteger("sample-rate");
    L5:
        e = move-exception;
        FileLog.e(e);
        return this.getInputMediaFormat().getInteger("sample-rate");
    L9:
        e = move-exception;
        FileLog.e(e);
        return -1;
    }

    public long getStartTimeUs() {
        return this.startTimeUs;
    }

    public boolean isDecodingDone() {
        return this.decodingDone;
    }

    public boolean isLoopingEnabled() {
        return this.loopingEnabled;
    }

    public void release() {
        stop();
        this.decoder.release();
        this.extractor.release();
    }

    public void releaseOutputBuffer(int r2) {
        this.decoder.releaseOutputBuffer(r2, false);
    }

    public void setEndTimeUs(long r6) {
        this.endTimeUs = r6;
        long r0 = getDurationUs();
        if (r6 >= 0) goto L7;
        this.endTimeUs = 0;
        return;
    L7:
        if (r6 <= r0) goto L10;
        this.endTimeUs = r0;
        return;
    }

    public void setLoopingEnabled(boolean r1) {
        this.loopingEnabled = r1;
    }

    public void setStartTimeUs(long r6) {
        this.startTimeUs = r6;
        long r0 = getDurationUs();
        if (r6 >= 0) goto L7;
        this.startTimeUs = 0;
        return;
    L7:
        if (r6 <= r0) goto L10;
        this.startTimeUs = r0;
        return;
    }

    public void start() {
        long r0 = this.startTimeUs;
        if (r0 > this.endTimeUs) goto L7;
        this.extractor.seekTo(r0, 0);
        this.decoder.start();
        this.allInputExtracted = false;
        this.decodingDone = false;
        return;
    L7:
        throw new RuntimeException("StartTimeUs(" + this.startTimeUs + ") must be less than or equal to EndTimeUs(" + this.endTimeUs + ")");
    }

    public void stop() {
        this.decoder.stop();
        this.decodingDone = true;
    }
}
