package org.telegram.utils.camera.roundvideo;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import org.telegram.tgnet.TLObject;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
abstract class RoundVideoRemuxer {

    public static final class Result {
        final long durationMs;
        final long startMs;

        public Result(long r1, long r3) {
            this.startMs = r1;
            this.durationMs = r3;
        }
    }

    public static Result copyRange(File r14, RoundVideoMp4Writer r15, long r16, long r18, boolean r20) {
        long r2 = getDurationUs(r14);
        long r4 = Math.max(0, r16 * 1000);
        long r22 = Math.min(r2, Math.max(r4, r18 * 1000));
        long r42 = findVideoSyncStart(r14, r4, r22);
        if (r42 >= 0) goto L5;
    L26:
        Model$$ExternalSyntheticBUOutline0.m("Trim range has no video sync sample");
        return null;
    L5:
        if (r42 >= r22) goto L26;
        MediaExtractor r6 = new MediaExtractor();
        r6.setDataSource(r14.getAbsolutePath());     // Catch: Throwable -> L15
        int r7 = findTrack(r6, true);     // Catch: Throwable -> L15
        int r11 = findTrack(r6, false);     // Catch: Throwable -> L15
        if (r7 < 0) goto L23;
        if (r20 == false) goto L11;
        if (r11 < 0) goto L23;
    L11:
        r15.setTrackFormat(true, r6.getTrackFormat(r7));     // Catch: Throwable -> L15
        if (r20 == false) goto L17;
        r15.setTrackFormat(false, r6.getTrackFormat(r11));     // Catch: Throwable -> L15
    L17:
        r6.release();
        ByteBuffer r72 = ByteBuffer.allocateDirect(TLObject.FLAG_21);
        copyTrack(r14, r15, true, r42, r22, r72);
        if (r20 == false) goto L21;
        copyTrack(r14, r15, false, r42, r22, r72);
    L21:
        return new Result(r42 / 1000, (r22 - r42) / 1000);
    L23:
        throw new IOException("Source file has no required tracks");     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        r6.release();
        throw th;
    }

    private static void copyTrack(File r17, RoundVideoMp4Writer r18, boolean r19, long r20, long r22, ByteBuffer r24) {
        boolean r1 = r19;
        MediaExtractor r8 = new MediaExtractor();
        MediaCodec.BufferInfo r9 = new MediaCodec.BufferInfo();
        r8.setDataSource(r17.getAbsolutePath());     // Catch: Throwable -> L15
        int r0 = findTrack(r8, r1);     // Catch: Throwable -> L15
        if (r0 < 0) goto L29;
        r8.selectTrack(r0);     // Catch: Throwable -> L15
        if (r1 == false) goto L8;
        int r02 = 1;
    L9:
        r8.seekTo(r20, r02);     // Catch: Throwable -> L15
    L11:
        if (r8.getSampleTime() < 0) goto L18;
        if (r8.getSampleTime() >= r20) goto L18;
        r8.advance();     // Catch: Throwable -> L15
    L18:
        if (r8.getSampleTime() < 0) goto L26;
        if (r8.getSampleTime() >= r22) goto L26;
        r24.clear();     // Catch: Throwable -> L15
        int r11 = r8.readSampleData(r24, 0);     // Catch: Throwable -> L15
        if (r11 < 0) goto L26;
        r9.set(0, r11, r8.getSampleTime(), r8.getSampleFlags());     // Catch: Throwable -> L15
        r18.writeSample(r1, r24, r9, -r20);     // Catch: Throwable -> L15
        r8.advance();     // Catch: Throwable -> L15
        r1 = r19;
    L26:
        r8.release();
        return;
    L8:
        r02 = 2;
        goto L9
    L29:
        throw new IOException("Source track disappeared");     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        r8.release();
        throw th;
    }

    public static void erase(File r3) {
        RandomAccessFile r0 = new RandomAccessFile(r3, "rw");     // Catch: IOException -> L17
        r0.setLength(0);     // Catch: Throwable -> L7
        r0.close();     // Catch: IOException -> L17
        goto L14
    L7:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L10
    L12:
        throw th;     // Catch: IOException -> L17
    L10:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L17
    L14:
        if (r3.exists() == false) goto L23;
        r3.delete();
        return;
    }

    private static int findTrack(MediaExtractor r3, boolean r4) {
        if (r4 == false) goto L4;
        String r42 = "video/";
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r3.getTrackCount()) goto L14;
        String r1 = r3.getTrackFormat(r0).getString("mime");
        if (r1 == null) goto L13;
        if (r1.startsWith(r42) == false) goto L13;
        return r0;
    L13:
        r0 = r0 + 1;
        goto L7
    L14:
        return -1;
    L4:
        r42 = "audio/";
        goto L5
    }

    private static long findVideoSyncStart(File r6, long r7, long r9) {
        MediaExtractor r0 = new MediaExtractor();
        r0.setDataSource(r6.getAbsolutePath());     // Catch: Throwable -> L16
        int r1 = findTrack(r0, true);     // Catch: Throwable -> L16
        long r2 = -1;
        if (r1 >= 0) goto L8;
        r0.release();
        return -1;
    L8:
        r0.selectTrack(r1);     // Catch: Throwable -> L16
        r0.seekTo(r7, 1);     // Catch: Throwable -> L16
        long r62 = r0.getSampleTime();     // Catch: Throwable -> L16
        if (r62 >= 0) goto L12;
    L14:
        r0.release();
        return r2;
    L12:
        if (r62 >= r9) goto L14;
        r2 = r62;
    L16:
        th = move-exception;
        r0.release();
        throw th;
    }

    public static long getDurationMs(File r4) {
        return getDurationUs(r4) / 1000;
    }

    private static long getDurationUs(File r6) {
        MediaExtractor r1 = new MediaExtractor();
        r1.setDataSource(r6.getAbsolutePath());     // Catch: Throwable -> L10
        long r2 = 0;
        int r62 = 0;
    L4:
        if (r62 >= r1.getTrackCount()) goto L13;
        MediaFormat r4 = r1.getTrackFormat(r62);     // Catch: Throwable -> L10
        if (r4.containsKey("durationUs") == false) goto L12;
        r2 = Math.max(r2, r4.getLong("durationUs"));     // Catch: Throwable -> L10
    L12:
        r62 = r62 + 1;
        goto L4
    L13:
        r1.release();
        return r2;
    L10:
        th = move-exception;
        r1.release();
        throw th;
    }
}
