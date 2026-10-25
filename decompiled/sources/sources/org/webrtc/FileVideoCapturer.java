package org.webrtc;

import android.content.Context;
import android.os.SystemClock;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class FileVideoCapturer implements VideoCapturer {
    private static final String TAG = "FileVideoCapturer";
    private CapturerObserver capturerObserver;
    private final TimerTask tickTask;
    private final Timer timer;
    private final VideoReader videoReader;

    /* renamed from: org.webrtc.FileVideoCapturer$1, reason: invalid class name */
    public class AnonymousClass1 extends TimerTask {
        final /* synthetic */ FileVideoCapturer this$0;

        public AnonymousClass1(FileVideoCapturer r1) {
            this.this$0 = r1;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.this$0.tick();
        }
    }

    public interface VideoReader {
        void close();

        VideoFrame getNextFrame();
    }

    public static class VideoReaderY4M implements VideoReader {
        private static final int FRAME_DELIMETER_LENGTH = 6;
        private static final String TAG = "VideoReaderY4M";
        private static final String Y4M_FRAME_DELIMETER = "FRAME";
        private final int frameHeight;
        private final int frameWidth;
        private final RandomAccessFile mediaFile;
        private final FileChannel mediaFileChannel;
        private final long videoStart;

        static {
        }

        public VideoReaderY4M(String r12) {
            RandomAccessFile r0 = new RandomAccessFile(r12, "r");
            this.mediaFile = r0;
            this.mediaFileChannel = r0.getChannel();
            StringBuilder r02 = new StringBuilder();
        L3:
            int r1 = this.mediaFile.read();
            if (r1 == (-1)) goto L37;
            if (r1 == 10) goto L7;
            r02.append((char) r1);
            goto L3
        L7:
            this.videoStart = this.mediaFileChannel.position();
            String[] r122 = r02.toString().split("[ ]");
            int r03 = r122.length;
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            int r4 = 0;
            int r5 = 0;
            int r6 = 0;
        L9:
            if (r4 >= r03) goto L21;
            String r8 = r122[r4];
            char r9 = r8.charAt(0);
            if (r9 != 'C') goto L13;
            r2 = r8.substring(1);
        L20:
            r4 = r4 + 1;
            goto L9
        L13:
            if (r9 != 'H') goto L15;
            r6 = Integer.parseInt(r8.substring(1));
            goto L20
        L15:
            if (r9 != 'W') goto L20;
            r5 = Integer.parseInt(r8.substring(1));
            goto L20
        L21:
            Logging.d(TAG, "Color space: " + r2);
            if (r2.equals("420") == true) goto L29;
            if (r2.equals("420mpeg2") == true) goto L29;
            f$$ExternalSyntheticBUOutline1.m("Does not support any other color space than I420 or I420mpeg2");
            throw null;
        L29:
            if ((r5 % 2) != 1) goto L31;
        L34:
            f$$ExternalSyntheticBUOutline1.m("Does not support odd width or height");
            throw null;
        L31:
            if ((r6 % 2) == 1) goto L34;
            this.frameWidth = r5;
            this.frameHeight = r6;
            Logging.d(TAG, "frame dim: (" + r5 + ", " + r6 + ")");
            return;
        L37:
            MVEL$$ExternalSyntheticBUOutline0.m("Found end of file before end of header for file: ", r12);
            throw null;
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public void close() {
            this.mediaFile.close();     // Catch: IOException -> L4
            return;
        L4:
            e = move-exception;
            Logging.e(TAG, "Problem closing file", e);
        }

        @Override // org.webrtc.FileVideoCapturer.VideoReader
        public VideoFrame getNextFrame() {
            long r1 = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
            JavaI420Buffer r3 = JavaI420Buffer.allocate(this.frameWidth, this.frameHeight);
            ByteBuffer r4 = r3.getDataY();
            ByteBuffer r5 = r3.getDataU();
            ByteBuffer r6 = r3.getDataV();
            r3.getStrideY();
            r3.getStrideU();
            r3.getStrideV();
            int r7 = FRAME_DELIMETER_LENGTH;     // Catch: IOException -> L17
            ByteBuffer r8 = ByteBuffer.allocate(r7);     // Catch: IOException -> L17
            if (this.mediaFileChannel.read(r8) >= r7) goto L10;
            this.mediaFileChannel.position(this.videoStart);     // Catch: IOException -> L17
            if (this.mediaFileChannel.read(r8) >= r7) goto L10;
            throw new RuntimeException("Error looping video");     // Catch: IOException -> L17
        L10:
            String r72 = new String(r8.array(), Charset.forName("US-ASCII"));     // Catch: IOException -> L17
            if (r72.equals("FRAME\n") == false) goto L16;
            this.mediaFileChannel.read(r4);     // Catch: IOException -> L17
            this.mediaFileChannel.read(r5);     // Catch: IOException -> L17
            this.mediaFileChannel.read(r6);     // Catch: IOException -> L17
            return new VideoFrame(r3, 0, r1);
        L16:
            throw new RuntimeException("Frames should be delimited by FRAME plus newline, found delimter was: '" + r72 + "'");     // Catch: IOException -> L17
        L17:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
            return null;
        }
    }

    public FileVideoCapturer(String r3) {
        this.timer = new Timer();
        this.tickTask = new AnonymousClass1(this);
        this.videoReader = new VideoReaderY4M(r3);     // Catch: IOException -> L5
        return;
    L5:
        e = move-exception;
        Logging.d(TAG, "Could not open video file: " + r3);
        throw e;
    }

    @Override // org.webrtc.VideoCapturer
    public void changeCaptureFormat(int r1, int r2, int r3) {
    }

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        this.videoReader.close();
    }

    @Override // org.webrtc.VideoCapturer
    public void initialize(SurfaceTextureHelper r1, Context r2, CapturerObserver r3) {
        this.capturerObserver = r3;
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return false;
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int r7, int r8, int r9) {
        this.timer.schedule(this.tickTask, 0, 1000 / r9);
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() {
        this.timer.cancel();
    }

    public void tick() {
        VideoFrame r0 = this.videoReader.getNextFrame();
        this.capturerObserver.onFrameCaptured(r0);
        r0.release();
    }
}
