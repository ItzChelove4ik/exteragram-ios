package org.telegram.messenger.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.AnimatedFileNative;

/* loaded from: classes6.dex */
public class VideoFramesRewinder {
    private Frame currentFrame;
    private boolean destroyAfterPrepare;
    private final TreeSet<Frame> frames;
    private final ArrayList<Frame> freeFrames;
    int h;
    private boolean isPreparing;
    private long lastSeek;
    private float lastSpeed;
    private AnimatedFileNative mDecoder;
    private int maxFrameSide;
    private int maxFramesCount;
    private final int[] meta;
    private final Paint paint;
    private View parentView;
    private Runnable prepareRunnable;
    private long prepareToMs;
    private float prepareWithSpeed;
    private AtomicBoolean stop;
    private AtomicLong until;
    int w;

    public class Frame {
        Bitmap bitmap;
        long position;
        final /* synthetic */ VideoFramesRewinder this$0;

        private Frame(VideoFramesRewinder r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Frame(VideoFramesRewinder r1, VideoFramesRewinderIA r2) {
            this(r1);
        }
    }

    public static /* synthetic */ int $r8$lambda$Gp5VzuvDbaeLquWZKjlA_BCZs5Q(Frame r2, Frame r3) {
        return (int) (r2.position - r3.position);
    }

    /* renamed from: $r8$lambda$UxQ_u8-PglRaOQ5T0SZzWs2HRHU, reason: not valid java name */
    public static /* synthetic */ void m5237$r8$lambda$UxQ_u8PglRaOQ5T0SZzWs2HRHU(VideoFramesRewinder r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$lsLfemWk_hxR0MI2yKUXCVIUoU4(VideoFramesRewinder r0, ArrayList r1, long r2) {
        r0.lambda$new$1(r1, r2);
    }

    public VideoFramesRewinder() {
        this.paint = new Paint(2);
        this.meta = new int[8];
        this.freeFrames = new ArrayList();
        this.frames = new TreeSet(new VideoFramesRewinder$$ExternalSyntheticLambda1());
        this.stop = new AtomicBoolean(false);
        this.until = new AtomicLong(0);
        this.lastSpeed = 1.0f;
        this.prepareRunnable = new VideoFramesRewinder$$ExternalSyntheticLambda2(this);
        int r0 = SharedConfig.getDevicePerformanceClass();
        if (r0 == 1) goto L9;
        if (r0 == 2) goto L7;
        this.maxFramesCount = 100;
        this.maxFrameSide = 480;
        return;
    L7:
        this.maxFramesCount = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        this.maxFrameSide = 720;
        return;
    L9:
        this.maxFramesCount = 200;
        this.maxFrameSide = 580;
    }

    private void invalidate() {
        View r0 = this.parentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$new$1(ArrayList r6, long r7) {
        FileLog.d("[VideoFramesRewinder] total prepare of " + r6.size() + " took " + (System.currentTimeMillis() - r7) + "ms");
        if (r6.isEmpty() == true) goto L5;
        FileLog.d("[VideoFramesRewinder] prepared from " + ((Frame) r6.get(0)).position + "ms to " + ((Frame) r6.get(r6.size() - 1)).position + "ms (requested up to " + this.prepareToMs + "ms)");
    L5:
        this.isPreparing = false;
        Iterator<Frame> r72 = this.frames.iterator();
    L7:
        if (r72.hasNext() == false) goto L18;
        Frame r0 = r72.next();
        if (this.currentFrame == r0) goto L7;
        if (r0.position <= this.lastSeek) goto L7;
        if (this.freeFrames.size() <= 20) goto L15;
        AndroidUtilities.recycleBitmap(r0.bitmap);
    L16:
        r72.remove();
        goto L7
    L15:
        this.freeFrames.add(r0);
    L18:
        if (r6.isEmpty() == true) goto L23;
        if (this.frames.size() >= this.maxFramesCount) goto L23;
        this.frames.add((Frame) r6.remove(r6.size() - 1));
    L23:
        if (r6.size() <= 0) goto L26;
        FileLog.d("[VideoFramesRewinder] prepared " + r6.size() + " more frames than I could fit :(");
    L26:
        if (this.destroyAfterPrepare == false) goto L38;
        release();
        this.stop.set(false);
        return;
    }

    private /* synthetic */ void lambda$new$2() {
        ArrayList r1 = new ArrayList();
        long r2 = System.currentTimeMillis();
        int[] r4 = this.meta;
        char r5 = 4;
        int r6 = r4[4];
        int r8 = 0;
        int r42 = Math.min(this.w / 4, r4[0]);
        int r7 = Math.min(this.h / 4, this.meta[1]);
        int r9 = this.maxFrameSide;
        if (r42 > r9) goto L5;
        if (r7 > r9) goto L5;
    L6:
        this.mDecoder.seekToMs(this.prepareToMs - ((long) (this.prepareWithSpeed * 350.0f)), false);
        char r10 = 3;
        long r11 = this.meta[3];
        int r92 = 0;
        int r13 = 0;
    L7:
        char r16 = r5;
        if (this.meta[r10] > this.until.get()) goto L36;
        if (r92 >= this.maxFramesCount) goto L36;
        if (this.stop.get() == true) goto L36;
        float r14 = 1000.0f / r6;
        char r15 = r10;
        long r17 = r11;
        long r102 = (long) (r11 + (this.prepareWithSpeed * r14));
        if (this.freeFrames.isEmpty() == true) goto L16;
        Frame r52 = this.freeFrames.remove(r8);
    L17:
        Bitmap r12 = r52.bitmap;
        if (r12 != null) goto L20;
    L23:
        AndroidUtilities.recycleBitmap(r52.bitmap);
        r52.bitmap = Bitmap.createBitmap(r42, r7, Bitmap.Config.ARGB_8888);     // Catch: OutOfMemoryError -> L35
    L25:
        int r19 = r92;
        int r122 = r6;
        int r20 = r7;
        long r82 = this.meta[r15] + ((long) Math.ceil(r14));
        AnimatedFileNative r72 = this.mDecoder;
        if (r82 >= r102) goto L29;
        r72.getVideoFrame(null, true, 0.0f, this.meta[r16], false);
        r6 = r122;
        r92 = r19;
        r7 = r20;
        goto L25
    L29:
        if (r72.getVideoFrame(r52.bitmap, true, 0.0f, this.meta[r16], false) != 0) goto L33;
        r13 = r13 + 1;
        if (r13 > 6) goto L36;
    L34:
        r92 = r19 + 1;
        r6 = r122;
        r10 = r15;
        r5 = r16;
        r11 = r17;
        r7 = r20;
        r8 = 0;
        goto L7
    L33:
        long r62 = this.meta[r15];
        r52.position = r62;
        r1.add(r52);
        r17 = r62;
    L35:
        FileLog.d("[VideoFramesRewinder] failed to create bitmap: out of memory");
        goto L36
    L20:
        if (r12.getWidth() != r42) goto L23;
        if (r52.bitmap.getHeight() == r7) goto L25;
    L16:
        r52 = new Frame(this, null);
    L36:
        AndroidUtilities.runOnUIThread(new VideoFramesRewinder$$ExternalSyntheticLambda0(this, r1, r2));
        return;
    L5:
        float r93 = r9 / Math.max(r42, r7);
        r42 = (int) (r42 * r93);
        r7 = (int) (r7 * r93);
        goto L6
    }

    private void prepare(long r3) {
        if (this.isPreparing == false) goto L5;
        return;
    L5:
        FileLog.d("[VideoFramesRewinder] starting preparing " + r3 + "ms");
        this.isPreparing = true;
        this.prepareToMs = r3;
        this.prepareWithSpeed = this.lastSpeed;
        Utilities.themeQueue.postRunnable(this.prepareRunnable);
    }

    public void clearCurrent() {
        if (this.currentFrame == null) goto L6;
        this.currentFrame = null;
        invalidate();
        return;
    }

    public void draw(Canvas r2, int r3, int r4) {
        this.w = r3;
        this.h = r4;
        if (this.mDecoder != null) goto L5;
        return;
    L5:
        if (this.currentFrame == null) goto L9;
        r2.save();
        r2.scale(r3 / this.currentFrame.bitmap.getWidth(), r4 / this.currentFrame.bitmap.getHeight());
        r2.drawBitmap(this.currentFrame.bitmap, 0.0f, 0.0f, this.paint);
        r2.restore();
        return;
    }

    public boolean isReady() {
        if (this.mDecoder == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void release() {
        if (this.isPreparing == false) goto L6;
        this.stop.set(true);
        this.destroyAfterPrepare = true;
        return;
    L6:
        AnimatedFileNative r0 = this.mDecoder;
        if (r0 == null) goto L9;
        r0.recycle();
        this.mDecoder = null;
    L9:
        int r02 = 0;
        this.destroyAfterPrepare = false;
        clearCurrent();
        this.until.set(0);
        Iterator<Frame> r1 = this.frames.iterator();
    L11:
        if (r1.hasNext() == false) goto L13;
        AndroidUtilities.recycleBitmap(r1.next().bitmap);
        goto L11
    L13:
        this.frames.clear();
        ArrayList<Frame> r12 = this.freeFrames;
        int r2 = r12.size();
    L14:
        if (r02 >= r2) goto L16;
        Frame r3 = r12.get(r02);
        r02 = r02 + 1;
        AndroidUtilities.recycleBitmap(r3.bitmap);
        goto L14
    L16:
        this.freeFrames.clear();
    }

    public void seek(long r10, float r12) {
        if (this.mDecoder != null) goto L5;
        return;
    L5:
        this.lastSeek = r10;
        this.lastSpeed = r12;
        this.until.set(r10);
        Iterator<Frame> r0 = this.frames.iterator();
        ArrayList r1 = new ArrayList();
    L7:
        if (r0.hasNext() == false) goto L27;
        Frame r2 = r0.next();
        r1.add(Long.valueOf(r2.position));
        float r6 = 25.0f * r12;
        if (Math.abs(r2.position - r10) >= r6) goto L7;
        if (this.currentFrame == r2) goto L18;
        FileLog.d("[VideoFramesRewinder] found a frame " + r2.position + "ms to fit to " + r10 + "ms from " + this.frames.size() + " frames");
        this.currentFrame = r2;
        invalidate();
        int r102 = 0;
    L14:
        if (r0.hasNext() == false) goto L16;
        r0.next();
        r0.remove();
        r102 = r102 + 1;
        goto L14
    L16:
        if (r102 <= 0) goto L18;
        FileLog.d("[VideoFramesRewinder] also deleted " + r102 + " frames after this frame");
    L18:
        int r103 = r1.size() - 2;
    L19:
        if (r103 < 0) goto L25;
        long r11 = ((Long) r1.get(r103 + 1)).longValue();
        long r7 = ((Long) r1.get(r103)).longValue();
        if (Math.abs(r11 - r7) > r6) goto L22;
        r103 = r103 - 1;
        goto L19
    L22:
        prepare(r7);
        return;
    L25:
        prepare(Math.max(0, this.frames.first().position - 20));
        return;
    L27:
        FileLog.d("[VideoFramesRewinder] didn't find a frame, wanting to prepare " + r10 + "ms");
        prepare(Math.max(0, r10));
    }

    public void setParentView(View r1) {
        this.parentView = r1;
    }

    public void setup(File r10) {
        if (r10 != null) goto L5;
        release();
        return;
    L5:
        this.stop.set(false);
        this.mDecoder = AnimatedFileNative.createDecoderFrom(r10.getAbsolutePath(), this.meta, UserConfig.selectedAccount, 0, null, true);
    }
}
