package org.telegram.utils.camera.roundvideo;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.telegram.messenger.video.MP4Builder;
import org.telegram.messenger.video.Mp4Movie;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
final class RoundVideoMp4Writer {
    private MediaFormat audioFormat;
    private long audioSamples;
    private final TrackTimeline audioTimeline;
    private int audioTrack;
    private long availableSize;
    private MP4Builder builder;
    private final MediaCodec.BufferInfo convertedVideoInfo;
    private final RoundVideoDiagnostics diagnostics;
    private final File file;
    private boolean finished;
    private final boolean includeAudio;
    private final Listener listener;
    private long liveSegmentBaseUs;
    private long liveSegmentOffsetUs;
    private final int outputSize;
    private final ArrayList<PendingSample> pendingSamples;
    private boolean suppressAvailableNotifications;
    private ByteBuffer videoConversionBuffer;
    private MediaFormat videoFormat;
    private long videoSamples;
    private final TrackTimeline videoTimeline;
    private int videoTrack;

    public interface Listener {
        void onBytesAvailable(long r1);
    }

    public static final class PendingSample {
        final ByteBuffer data;
        final MediaCodec.BufferInfo info;
        final boolean video;

        public PendingSample(boolean r1, ByteBuffer r2, MediaCodec.BufferInfo r3) {
            this.video = r1;
            this.data = r2;
            this.info = r3;
        }
    }

    public static final class TrackTimeline {
        final long defaultDurationUs;
        long firstSourcePresentationTimeUs;
        long lastPresentationTimeUs;
        long previousSourcePresentationTimeUs;
        long sampleDurationUs;

        public TrackTimeline(long r3) {
            this.firstSourcePresentationTimeUs = Long.MIN_VALUE;
            this.previousSourcePresentationTimeUs = Long.MIN_VALUE;
            this.lastPresentationTimeUs = Long.MIN_VALUE;
            this.defaultDurationUs = r3;
            this.sampleDurationUs = r3;
        }

        public void add(long r5, long r7) {
            long r0 = this.previousSourcePresentationTimeUs;
            if (r0 == Long.MIN_VALUE) goto L9;
            long r02 = r5 - r0;
            if (r02 <= 0) goto L9;
            if (r02 >= 1000000) goto L9;
            this.sampleDurationUs = r02;
        L9:
            this.previousSourcePresentationTimeUs = r5;
            this.lastPresentationTimeUs = Math.max(this.lastPresentationTimeUs, r7);
        }

        public long nextPresentationTimeUs() {
            long r0 = this.lastPresentationTimeUs;
            if (r0 != Long.MIN_VALUE) goto L7;
            return 0;
        L7:
            return r0 + Math.max(1, this.sampleDurationUs);
        }

        public void startSegment() {
            this.firstSourcePresentationTimeUs = Long.MIN_VALUE;
            this.previousSourcePresentationTimeUs = Long.MIN_VALUE;
        }
    }

    public RoundVideoMp4Writer(File r4, int r5, boolean r6, RoundVideoDiagnostics r7, Listener r8) {
        this.pendingSamples = new ArrayList();
        this.videoTimeline = new TrackTimeline(33333);
        this.audioTimeline = new TrackTimeline(21333);
        this.videoTrack = -1;
        this.audioTrack = -1;
        this.liveSegmentOffsetUs = Long.MIN_VALUE;
        this.convertedVideoInfo = new MediaCodec.BufferInfo();
        this.file = r4;
        this.outputSize = r5;
        this.includeAudio = r6;
        this.diagnostics = r7;
        this.listener = r8;
    }

    private static IOException asIOException(String r1, Exception r2) {
        if ((r2 instanceof IOException) == false) goto L7;
        return (IOException) r2;
    L7:
        return new IOException(r1, r2);
    }

    private static int codecSpecificDataOffset(ByteBuffer r6) {
        if (r6.limit() < 4) goto L14;
        if (r6.get(0) != 0) goto L14;
        if (r6.get(1) != 0) goto L14;
        if (r6.get(2) != 0) goto L14;
        if (r6.get(3) != 1) goto L14;
        return 4;
    L14:
        if (r6.limit() >= 3) goto L16;
    L22:
        return 0;
    L16:
        if (r6.get(0) != 0) goto L22;
        if (r6.get(1) != 0) goto L22;
        if (r6.get(2) != 1) goto L22;
        return 3;
    }

    private MediaCodec.BufferInfo copyInfo(boolean r7, MediaCodec.BufferInfo r8, long r9) {
        if (r7 == false) goto L4;
        TrackTimeline r72 = this.videoTimeline;
    L6:
        if (r9 >= 0) goto L10;
        long r92 = Math.max(0, r9 + r8.presentationTimeUs);
    L8:
        long r3 = r92;
        r72.add(r8.presentationTimeUs, r3);
        MediaCodec.BufferInfo r0 = new MediaCodec.BufferInfo();
        r0.set(r8.offset, r8.size, r3, r8.flags);
        return r0;
    L10:
        if (this.liveSegmentOffsetUs == r9) goto L13;
        this.liveSegmentOffsetUs = r9;
        this.liveSegmentBaseUs = Math.max(r9, Math.max(this.videoTimeline.nextPresentationTimeUs(), this.audioTimeline.nextPresentationTimeUs()));
        this.videoTimeline.startSegment();
        this.audioTimeline.startSegment();
    L13:
        if (r72.firstSourcePresentationTimeUs != Long.MIN_VALUE) goto L15;
        r72.firstSourcePresentationTimeUs = r8.presentationTimeUs;
    L15:
        r92 = this.liveSegmentBaseUs + Math.max(0, r8.presentationTimeUs - r72.firstSourcePresentationTimeUs);
        goto L8
    L4:
        r72 = this.audioTimeline;
        goto L6
    }

    private static long elapsedMs(long r2) {
        return (System.nanoTime() - r2) / 1000000;
    }

    private void ensureVideoConversionCapacity(int r2) {
        ByteBuffer r0 = this.videoConversionBuffer;
        if (r0 != null) goto L5;
    L7:
        this.videoConversionBuffer = ByteBuffer.allocateDirect(r2);
        return;
    L5:
        if (r0.capacity() < r2) goto L7;
    }

    private static int findStartCode(ByteBuffer r3, int r4, int r5) {
    L2:
        int r0 = r4 + 2;
        if (r0 >= r5) goto L20;
        if (r3.get(r4) != 0) goto L19;
        if (r3.get(r4 + 1) != 0) goto L19;
        if (r3.get(r0) == 1) goto L18;
        int r1 = r4 + 3;
        if (r1 >= r5) goto L19;
        if (r3.get(r0) != 0) goto L19;
        if (r3.get(r1) != 1) goto L19;
    L18:
        return r4;
    L19:
        r4 = r4 + 1;
        goto L2
    L20:
        return -1;
    }

    private void initializeIfReady() {
        if (this.videoFormat != null) goto L5;
        return;
    L5:
        if (this.includeAudio == true) goto L7;
    L9:
        Mp4Movie r1 = new Mp4Movie();
        r1.setCacheFile(this.file);
        int r2 = this.outputSize;
        r1.setSize(r2, r2);
        int r3 = 0;
        MP4Builder r12 = new MP4Builder().createMovie(r1, true, false);     // Catch: Exception -> L20
        this.builder = r12;     // Catch: Exception -> L20
        this.videoTrack = r12.addTrack(this.videoFormat, false);     // Catch: Exception -> L20
        if (this.includeAudio == false) goto L13;
        this.audioTrack = this.builder.addTrack(this.audioFormat, true);     // Catch: Exception -> L20
    L13:
        this.diagnostics.log("MP4 initialized: file=" + this.file.getName() + ", output=" + this.outputSize + "x" + this.outputSize + ", includeAudio=" + this.includeAudio + ", pendingSamples=" + this.pendingSamples.size());     // Catch: Exception -> L20
    L14:
        int r0 = this.pendingSamples.size();     // Catch: Exception -> L20
        ArrayList<PendingSample> r13 = this.pendingSamples;
        if (r3 >= r0) goto L18;
        PendingSample r02 = r13.get(r3);     // Catch: Exception -> L20
        writeSampleInternal(r02.video, r02.data, r02.info);     // Catch: Exception -> L20
        r3 = r3 + 1;     // Catch: Exception -> L20
        goto L14
    L18:
        r13.clear();     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        throw asIOException("Unable to initialize MP4", e);
    L7:
        if (this.audioFormat != null) goto L9;
    }

    private int normalizeVideoSample(ByteBuffer r9, MediaCodec.BufferInfo r10) {
        int r0 = r10.offset;
        int r1 = r10.size + r0;
        int r02 = findStartCode(r9, r0, r1);
        if (r02 == r10.offset) goto L5;
        return 0;
    L5:
        int r2 = startCodeLength(r9, r02, r1);
        int r4 = findStartCode(r9, r02 + r2, r1);
        if (r2 != 4) goto L10;
        if (r4 >= 0) goto L10;
        return 1;
    L10:
        int r22 = r02;
        int r42 = 0;
        int r5 = 0;
    L11:
        if (r22 < 0) goto L19;
        int r23 = r22 + startCodeLength(r9, r22, r1);
        int r6 = findStartCode(r9, r23, r1);
        if (r6 >= 0) goto L15;
        int r7 = r1;
    L16:
        if (r7 <= r23) goto L18;
        r42 = r42 + 1;
        r5 = r5 + ((r7 + 4) - r23);
    L18:
        r22 = r6;
        goto L11
    L15:
        r7 = r6;
        goto L16
    L19:
        if (r42 != 0) goto L21;
        return 0;
    L21:
        ensureVideoConversionCapacity(r5);
        this.videoConversionBuffer.clear();
        int r24 = r9.position();
        int r3 = r9.limit();
    L22:
        if (r02 < 0) goto L35;
        int r03 = r02 + startCodeLength(r9, r02, r1);     // Catch: Throwable -> L30
        int r43 = findStartCode(r9, r03, r1);     // Catch: Throwable -> L30
        if (r43 >= 0) goto L26;
        int r52 = r1;
    L27:
        if (r52 <= r03) goto L32;
        this.videoConversionBuffer.putInt(r52 - r03);     // Catch: Throwable -> L30
        r9.position(r03);     // Catch: Throwable -> L30
        r9.limit(r52);     // Catch: Throwable -> L30
        this.videoConversionBuffer.put(r9);     // Catch: Throwable -> L30
    L32:
        r02 = r43;
        goto L22
    L26:
        r52 = r43;
    L30:
        th = move-exception;
        r9.limit(r3);
        r9.position(r24);
        throw th;
    L35:
        r9.limit(r3);
        r9.position(r24);
        this.videoConversionBuffer.flip();
        this.convertedVideoInfo.set(0, this.videoConversionBuffer.remaining(), r10.presentationTimeUs, r10.flags);
        return 2;
    }

    private void notifyAvailable(long r3) {
        if (r3 <= this.availableSize) goto L10;
        this.availableSize = r3;
        if (this.suppressAvailableNotifications == false) goto L8;
        return;
    L8:
        this.listener.onBytesAvailable(r3);
        return;
    }

    private void requireBuilder() {
        if (this.builder == null) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("MP4 tracks are not initialized");
    }

    private static boolean sameBytes(ByteBuffer r8, ByteBuffer r9) {
        if (r8 == null) goto L16;
        if (r9 == null) goto L16;
        int r2 = codecSpecificDataOffset(r8);
        int r3 = codecSpecificDataOffset(r9);
        int r4 = r8.limit() - r2;
        if (r4 == (r9.limit() - r3)) goto L9;
        return false;
    L9:
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L15;
        if (r8.get(r2 + r5) != r9.get(r3 + r5)) goto L13;
        r5 = r5 + 1;
        goto L10
    L13:
        return false;
    L15:
        return true;
    L16:
        if (r8 != r9) goto L18;
        return true;
    L18:
        return false;
    }

    private static boolean sameCodecSpecificData(MediaFormat r5, MediaFormat r6, String... r7) {
        if (r5 != null) goto L5;
        return false;
    L5:
        int r1 = r7.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        String r3 = r7[r2];
        if (sameBytes(r5.getByteBuffer(r3), r6.getByteBuffer(r3)) == false) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    private static int startCodeLength(ByteBuffer r1, int r2, int r3) {
        if ((r2 + 3) < r3) goto L5;
        return 3;
    L5:
        if (r1.get(r2 + 2) != 0) goto L10;
        return 4;
    L10:
        return 3;
    }

    private void writeSampleInternal(boolean r5, ByteBuffer r6, MediaCodec.BufferInfo r7) {
        if (r5 == true) goto L24;
    L8:
        boolean r2 = r5;
    L9:
        MP4Builder r0 = this.builder;     // Catch: Exception -> L21
        if (r5 == false) goto L12;
        int r1 = this.videoTrack;     // Catch: Exception -> L21
    L13:
        long r62 = r0.writeSampleData(r1, r6, r7, r2);     // Catch: Exception -> L21
        if (r5 == false) goto L16;
        this.videoSamples++;
    L18:
        if (r62 <= 0) goto L26;
        notifyAvailable(r62);     // Catch: Exception -> L21
        return;
    L21:
        e = move-exception;
        throw asIOException("Unable to write MP4 sample", e);
    L26:
        return;
    L16:
        this.audioSamples++;
        goto L18
    L12:
        r1 = this.audioTrack;     // Catch: Exception -> L21
        goto L13
    L24:
        int r02 = normalizeVideoSample(r6, r7);     // Catch: Exception -> L21
        r2 = false;
        if (r02 != 2) goto L6;
        r6 = this.videoConversionBuffer;     // Catch: Exception -> L21
        r7 = this.convertedVideoInfo;     // Catch: Exception -> L21
        goto L9
    L6:
        if (r02 != 0) goto L8;
        goto L8
    }

    public synchronized void closeForReplacement() {
        monitor-enter(this);
        this.suppressAvailableNotifications = true;     // Catch: Throwable -> L10
        finish();     // Catch: Throwable -> L12
        this.suppressAvailableNotifications = false;     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L12:
        th = move-exception;
        this.suppressAvailableNotifications = false;     // Catch: Throwable -> L10
        throw th;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public synchronized void createPreview(File r8) {
        monitor-enter(this);
        requireBuilder();     // Catch: Throwable -> L8
        long r1 = System.nanoTime();     // Catch: Throwable -> L8
        this.builder.finishMovie(r8);     // Catch: Throwable -> L8 Exception -> L10
        this.diagnostics.log("MP4 preview written: file=" + r8.getName() + ", size=" + r8.length() + ", elapsedMs=" + elapsedMs(r1));     // Catch: Throwable -> L8 Exception -> L10
        monitor-exit(this);
        return;
    L10:
        e = move-exception;
        throw asIOException("Unable to create preview MP4", e);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized void finish() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (this.finished == false) goto L8;
        monitor-exit(this);
        return;
    L8:
        requireBuilder();     // Catch: Throwable -> L12
        long r1 = System.nanoTime();     // Catch: Throwable -> L12
        this.builder.finishMovie();     // Catch: Throwable -> L12 Exception -> L14
        this.finished = true;     // Catch: Throwable -> L12 Exception -> L14
        notifyAvailable(this.file.length());     // Catch: Throwable -> L12 Exception -> L14
        this.diagnostics.log("MP4 finalized: file=" + this.file.getName() + ", size=" + this.file.length() + ", videoSamples=" + this.videoSamples + ", audioSamples=" + this.audioSamples + ", elapsedMs=" + elapsedMs(r1));     // Catch: Throwable -> L12 Exception -> L14
        monitor-exit(this);
        return;
    L14:
        e = move-exception;
        throw asIOException("Unable to finish MP4", e);     // Catch: Throwable -> L12
    }

    public File getFile() {
        return this.file;
    }

    public synchronized void setTrackFormat(boolean r5, MediaFormat r6) {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (this.finished == false) goto L9;
        monitor-exit(this);
        return;
    L9:
        if (this.builder == null) goto L31;
        if (r5 == false) goto L14;
        MediaFormat r1 = this.videoFormat;     // Catch: Throwable -> L12
    L15:
        if (r5 == true) goto L17;
    L18:
        if (r5 == false) goto L20;
    L29:
        monitor-exit(this);
        return;
    L20:
        if (this.includeAudio == false) goto L29;
        if (sameCodecSpecificData(r1, r6, new String[]{"csd-0"}) == true) goto L29;
    L24:
        if (r5 == false) goto L26;
        String r52 = "Video";
    L28:
        throw new IOException(r52.concat(" codec configuration changed between segments"));     // Catch: Throwable -> L12
    L26:
        r52 = "Audio";
        goto L28
    L17:
        if (sameCodecSpecificData(r1, r6, new String[]{"csd-0", "csd-1"}) == false) goto L24;
    L14:
        r1 = this.audioFormat;     // Catch: Throwable -> L12
        goto L15
    L31:
        if (r5 == false) goto L34;
        this.videoFormat = r6;     // Catch: Throwable -> L12
    L36:
        initializeIfReady();     // Catch: Throwable -> L12
        monitor-exit(this);
        return;
    L34:
        if (this.includeAudio == false) goto L36;
        this.audioFormat = r6;     // Catch: Throwable -> L12
        goto L36
    }

    public synchronized void writeSample(boolean r2, ByteBuffer r3, MediaCodec.BufferInfo r4, long r5) {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (this.finished == true) goto L22;
        if (r2 == true) goto L12;
        if (this.includeAudio == false) goto L22;
    L12:
        if (r4.size <= 0) goto L22;
        MediaCodec.BufferInfo r52 = copyInfo(r2, r4, r5);     // Catch: Throwable -> L9
        if (this.builder != null) goto L19;
        ByteBuffer r6 = ByteBuffer.allocateDirect(r4.size);     // Catch: Throwable -> L9
        r3.position(r4.offset);     // Catch: Throwable -> L9
        r3.limit(r4.offset + r4.size);     // Catch: Throwable -> L9
        r6.put(r3).flip();     // Catch: Throwable -> L9
        r52.offset = 0;     // Catch: Throwable -> L9
        this.pendingSamples.add(new PendingSample(r2, r6, r52));     // Catch: Throwable -> L9
        monitor-exit(this);
        return;
    L19:
        writeSampleInternal(r2, r3, r52);     // Catch: Throwable -> L9
        monitor-exit(this);
        return;
    L22:
        monitor-exit(this);
    }
}
