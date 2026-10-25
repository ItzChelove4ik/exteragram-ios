package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.view.MotionEvent;
import android.view.View;
import java.io.File;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public abstract class VideoTimelinePlayView extends View {
    public static int TYPE_LEFT = 0;
    public static int TYPE_PROGRESS = 2;
    public static int TYPE_RIGHT = 1;
    private static final Object sync = null;
    Paint bitmapPaint;
    private Path clipPath;
    private int currentMode;
    private AsyncTask<Integer, Integer, Bitmap> currentTask;
    private final Paint cutPaint;
    private VideoTimelineViewDelegate delegate;
    private final Paint dimPaint;
    private ArrayList<Rect> exclusionRects;
    private Rect exclustionRect;
    private ParcelFileDescriptor fd;
    private int frameHeight;
    private long frameTimeOffset;
    private int frameWidth;
    private ArrayList<BitmapFrame> frames;
    private int framesToLoad;
    private final Paint handlePaint;
    private boolean hasBlur;
    private boolean isLivePhoto;
    private int lastWidth;
    private final AnimatedFloat loopProgress;
    private float maxProgressDiff;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private float minProgressDiff;
    private float playProgress;
    private float pressDx;
    private boolean pressedLeft;
    private boolean pressedPlay;
    private boolean pressedRight;
    private float progressLeft;
    private float progressPreview;
    private float progressRight;
    private RectF rect3;
    private final Paint shadowPaint;
    private int videoHeight;
    private long videoLength;
    private int videoPathGeneration;
    private int videoWidth;
    private final Paint whitePaint;
    private final Paint yellowPaint;

    /* renamed from: org.telegram.ui.Components.VideoTimelinePlayView$1, reason: invalid class name */
    public class AnonymousClass1 extends AsyncTask<Integer, Integer, Bitmap> {
        private int frameNum;
        private final Paint paint;
        final /* synthetic */ VideoTimelinePlayView this$0;

        public AnonymousClass1(VideoTimelinePlayView r2) {
            this.this$0 = r2;
            this.frameNum = 0;
            this.paint = new Paint(3);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Bitmap doInBackground2(Integer... r11) {
            this.frameNum = r11[0].intValue();
            Bitmap r1 = null;
            if (isCancelled() == false) goto L18;
            return null;
        L18:
            Bitmap r112 = VideoTimelinePlayView.m13524$$Nest$fgetmediaMetadataRetriever(this.this$0).getFrameAtTime((VideoTimelinePlayView.m13520$$Nest$fgetframeTimeOffset(this.this$0) * this.frameNum) * 1000, 2);     // Catch: Exception -> L15
        L12:
            e = e;
            r1 = r112;
        L16:
            FileLog.e(e);
            return r1;
        L7:
            if (isCancelled() == false) goto L9;
            return null;
        L9:
            if (r112 == null) goto L14;
            Bitmap r12 = Bitmap.createBitmap(VideoTimelinePlayView.m13521$$Nest$fgetframeWidth(this.this$0), VideoTimelinePlayView.m13519$$Nest$fgetframeHeight(this.this$0), r112.getConfig());     // Catch: Exception -> L12
            Canvas r2 = new Canvas(r12);     // Catch: Exception -> L12
            float r3 = Math.max(VideoTimelinePlayView.m13521$$Nest$fgetframeWidth(this.this$0) / r112.getWidth(), VideoTimelinePlayView.m13519$$Nest$fgetframeHeight(this.this$0) / r112.getHeight());     // Catch: Exception -> L12
            int r5 = (int) (r112.getWidth() * r3);     // Catch: Exception -> L12
            int r32 = (int) (r112.getHeight() * r3);     // Catch: Exception -> L12
            r2.drawBitmap(r112, new Rect(0, 0, r112.getWidth(), r112.getHeight()), new Rect((VideoTimelinePlayView.m13521$$Nest$fgetframeWidth(this.this$0) - r5) / 2, (VideoTimelinePlayView.m13519$$Nest$fgetframeHeight(this.this$0) - r32) / 2, (VideoTimelinePlayView.m13521$$Nest$fgetframeWidth(this.this$0) + r5) / 2, (VideoTimelinePlayView.m13519$$Nest$fgetframeHeight(this.this$0) + r32) / 2), this.paint);     // Catch: Exception -> L12
            r112.recycle();     // Catch: Exception -> L12
            return r12;
        L14:
            return r112;
        L15:
            e = e;
            goto L16
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ Bitmap doInBackground(Integer[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(Bitmap r3) {
            if (isCancelled() == true) goto L8;
            VideoTimelinePlayView.m13522$$Nest$fgetframes(this.this$0).add(new BitmapFrame(r3));
            this.this$0.invalidate();
            if (this.frameNum >= VideoTimelinePlayView.m13523$$Nest$fgetframesToLoad(this.this$0)) goto L9;
            VideoTimelinePlayView.m13525$$Nest$mreloadFrames(this.this$0, this.frameNum + 1);
            return;
        L9:
            return;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Bitmap r1) {
            onPostExecute2(r1);
        }
    }

    public static class BitmapFrame {
        float alpha;
        Bitmap bitmap;

        public BitmapFrame(Bitmap r1) {
            this.bitmap = r1;
        }
    }

    public interface VideoTimelineViewDelegate {
        void didStartDragging(int r1);

        void didStopDragging(int r1);

        void onLeftProgressChanged(float r1);

        void onPlayProgressChanged(float r1);

        void onRightProgressChanged(float r1);
    }

    /* renamed from: $r8$lambda$TdFTZdk_uZyDE-8W9IjP3_zUc70, reason: not valid java name */
    public static /* synthetic */ void m13517$r8$lambda$TdFTZdk_uZyDE8W9IjP3_zUc70(VideoTimelinePlayView r0, int r1, MediaMetadataRetriever r2, ParcelFileDescriptor r3, long r4, int r6, int r7, long r8) {
        r0.lambda$setVideoPath$0(r1, r2, r3, r4, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$YvCtA-VpNP_AzL5iHnHaRivqe_s, reason: not valid java name */
    public static /* synthetic */ void m13518$r8$lambda$YvCtAVpNP_AzL5iHnHaRivqe_s(VideoTimelinePlayView r0, long r1, String r3, int r4, long r5) {
        r0.lambda$setVideoPath$1(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$lO28n9U7HZZR_HTVz9YpJoaNosk(ParcelFileDescriptor r1, MediaMetadataRetriever r2) {
        Object r0 = sync;
        monitor-enter(r0);
        if (r1 != null) goto L20;
    L11:
        if (r2 != null) goto L23;
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    L23:
        r2.release();     // Catch: Throwable -> L7 Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        goto L16
    L20:
        r1.close();     // Catch: Throwable -> L7 Exception -> L9
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        goto L11
    }

    /* renamed from: -$$Nest$fgetframeHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13519$$Nest$fgetframeHeight(VideoTimelinePlayView r0) {
        return r0.frameHeight;
    }

    /* renamed from: -$$Nest$fgetframeTimeOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13520$$Nest$fgetframeTimeOffset(VideoTimelinePlayView r2) {
        return r2.frameTimeOffset;
    }

    /* renamed from: -$$Nest$fgetframeWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13521$$Nest$fgetframeWidth(VideoTimelinePlayView r0) {
        return r0.frameWidth;
    }

    /* renamed from: -$$Nest$fgetframes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13522$$Nest$fgetframes(VideoTimelinePlayView r0) {
        return r0.frames;
    }

    /* renamed from: -$$Nest$fgetframesToLoad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13523$$Nest$fgetframesToLoad(VideoTimelinePlayView r0) {
        return r0.framesToLoad;
    }

    /* renamed from: -$$Nest$fgetmediaMetadataRetriever, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaMetadataRetriever m13524$$Nest$fgetmediaMetadataRetriever(VideoTimelinePlayView r0) {
        return r0.mediaMetadataRetriever;
    }

    /* renamed from: -$$Nest$mreloadFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13525$$Nest$mreloadFrames(VideoTimelinePlayView r0, int r1) {
        r0.reloadFrames(r1);
    }

    static {
        sync = new Object();
    }

    public VideoTimelinePlayView(Context r15) {
        super(r15);
        this.progressRight = 1.0f;
        this.playProgress = 0.5f;
        this.frames = new ArrayList();
        this.maxProgressDiff = 1.0f;
        this.minProgressDiff = 0.0f;
        this.rect3 = new RectF();
        this.currentMode = 0;
        this.bitmapPaint = new Paint(3);
        this.exclusionRects = new ArrayList();
        this.exclustionRect = new Rect();
        Paint r152 = new Paint(1);
        this.whitePaint = r152;
        Paint r1 = new Paint(1);
        this.yellowPaint = r1;
        Paint r2 = new Paint(1);
        this.shadowPaint = r2;
        Paint r3 = new Paint(1);
        this.dimPaint = r3;
        Paint r4 = new Paint(1);
        this.cutPaint = r4;
        Paint r5 = new Paint(1);
        this.handlePaint = r5;
        this.loopProgress = new AnimatedFloat(0.0f, this, 0, 200, CubicBezierInterpolator.EASE_BOTH);
        this.clipPath = new Path();
        r152.setColor(-1);
        r1.setColor(-256);
        r2.setColor(637534208);
        r3.setColor(1291845632);
        r4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r5.setColor(-16777216);
        this.exclusionRects.add(this.exclustionRect);
    }

    private void drawProgress(Canvas r8, float r9, float r10, Paint r11) {
        float r0 = AndroidUtilities.dpf2(12.0f);
        float r3 = AndroidUtilities.dp(2.0f);
        float r4 = AndroidUtilities.dp(46.0f) + r3;
        float r5 = ((r4 - r3) / 2.0f) * (1.0f - r10);
        float r32 = r3 + r5;
        float r42 = r4 - r5;
        this.shadowPaint.setAlpha((int) (38.0f * r10));
        r11.setAlpha((int) (r10 * 255.0f));
        float r02 = (r0 + AndroidUtilities.dp(10.0f)) + (((getMeasuredWidth() - (r0 * 2.0f)) - AndroidUtilities.dp(20.0f)) * r9);
        this.rect3.set(r02 - AndroidUtilities.dpf2(1.5f), r32, AndroidUtilities.dpf2(1.5f) + r02, r42);
        this.rect3.inset(-AndroidUtilities.dpf2(0.66f), -AndroidUtilities.dpf2(0.66f));
        r8.drawRoundRect(this.rect3, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.shadowPaint);
        this.rect3.set(r02 - AndroidUtilities.dpf2(1.5f), r32, r02 + AndroidUtilities.dpf2(1.5f), r42);
        r8.drawRoundRect(this.rect3, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), r11);
    }

    private /* synthetic */ void lambda$setVideoPath$0(int r2, MediaMetadataRetriever r3, ParcelFileDescriptor r4, long r5, int r7, int r8, long r9) {
        if (r2 == this.videoPathGeneration) goto L6;
        releaseRetriever(r3, r4);
        return;
    L6:
        this.mediaMetadataRetriever = r3;
        this.fd = r4;
        this.videoLength = r5;
        this.videoWidth = r7;
        this.videoHeight = r8;
        if (this.isLivePhoto == false) goto L9;
        this.progressPreview = (float) ((r9 / 1000.0d) / r5);
    L9:
        invalidate();
    }

    private /* synthetic */ void lambda$setVideoPath$1(long r13, String r15, int r16, long r17) {
        MediaMetadataRetriever r1 = new MediaMetadataRetriever();
        long r7 = 0;
        ParcelFileDescriptor r2 = null;
        int r9 = 0;
        if (r13 <= 0) goto L12;
        File r0 = new File(r15);     // Catch: Exception -> L10
        ParcelFileDescriptor r152 = ParcelFileDescriptor.open(r0, 268435456);     // Catch: Exception -> L10
        r1.setDataSource(r152.getFileDescriptor(), r13, r0.length() - r13);     // Catch: Exception -> L7
        r2 = r152;
    L13:
        String r132 = r1.extractMetadata(9);     // Catch: Exception -> L10
        if (r132 == null) goto L16;
        r7 = Long.parseLong(r132);     // Catch: Exception -> L10
    L16:
        String r133 = r1.extractMetadata(18);     // Catch: Exception -> L10
        if (r133 == null) goto L20;
        int r134 = Integer.parseInt(r133);     // Catch: Exception -> L10
    L42:
        String r14 = r1.extractMetadata(19);     // Catch: Exception -> L25
        if (r14 == null) goto L27;
        r9 = Integer.parseInt(r14);     // Catch: Exception -> L25
    L27:
        String r142 = r1.extractMetadata(24);     // Catch: Exception -> L25
        if (r142 == null) goto L35;
        int r143 = Integer.parseInt(r142);     // Catch: Exception -> L25
        if (r143 != 90) goto L33;
    L34:
        int r11 = r9;
        r9 = r134;
        r134 = r11;
        goto L35
    L33:
        if (r143 == 270) goto L34;
    L35:
        long r5 = r7;
        int r8 = r9;
        int r72 = r134;
    L38:
        AndroidUtilities.runOnUIThread(new VideoTimelinePlayView$$ExternalSyntheticLambda2(this, r16, r1, r2, r5, r72, r8, r17));
        return;
    L25:
        e = move-exception;
        int r112 = r9;
        r9 = r134;
        Exception r135 = e;
        int r144 = r112;
    L37:
        FileLog.e(r135);
        r5 = r7;
        r72 = r9;
        r8 = r144;
        goto L38
    L20:
        r134 = 0;
    L7:
        e = move-exception;
        r135 = e;
        r2 = r152;
    L9:
        r144 = 0;
        goto L37
    L12:
        r1.setDataSource(r15);     // Catch: Exception -> L10
    L10:
        e = move-exception;
        r135 = e;
        goto L9
    }

    private static void releaseRetriever(MediaMetadataRetriever r2, ParcelFileDescriptor r3) {
        Utilities.globalQueue.postRunnable(new VideoTimelinePlayView$$ExternalSyntheticLambda1(r3, r2));
    }

    private void reloadFrames(int r5) {
        if (this.mediaMetadataRetriever != null) goto L5;
        return;
    L5:
        if (r5 != 0) goto L13;
        this.frameHeight = AndroidUtilities.dp(38.0f);
        int r0 = this.videoWidth;
        if (r0 == 0) goto L11;
        int r1 = this.videoHeight;
        if (r1 == 0) goto L11;
        float r02 = r0 / r1;
    L12:
        float r03 = Utilities.clamp(r02, 1.3333334f, 0.5625f);
        this.framesToLoad = Math.max(1, (int) Math.ceil((getMeasuredWidth() - AndroidUtilities.dp(32.0f)) / (this.frameHeight * r03)));
        this.frameWidth = (int) Math.ceil((getMeasuredWidth() - AndroidUtilities.dp(32.0f)) / this.framesToLoad);
        this.frameTimeOffset = this.videoLength / this.framesToLoad;
    L11:
        r02 = 1.0f;
    L13:
        AnonymousClass1 r04 = new AnonymousClass1(this);
        this.currentTask = r04;
        r04.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[]{Integer.valueOf(r5), null, null});
    }

    public void clearFrames() {
        int r0 = 0;
    L3:
        int r1 = this.frames.size();
        ArrayList<BitmapFrame> r2 = this.frames;
        if (r0 >= r1) goto L11;
        BitmapFrame r12 = r2.get(r0);
        if (r12 == null) goto L10;
        Bitmap r13 = r12.bitmap;
        if (r13 == null) goto L10;
        r13.recycle();
    L10:
        r0 = r0 + 1;
        goto L3
    L11:
        r2.clear();
        AsyncTask<Integer, Integer, Bitmap> r02 = this.currentTask;
        if (r02 == null) goto L14;
        r02.cancel(true);
        this.currentTask = null;
    L14:
        invalidate();
    }

    public abstract boolean customBlur();

    public void destroy() {
        this.videoPathGeneration++;
        MediaMetadataRetriever r0 = this.mediaMetadataRetriever;
        if (r0 == null) goto L5;
    L6:
        releaseRetriever(r0, this.fd);
        this.mediaMetadataRetriever = null;
        this.fd = null;
    L7:
        int r02 = 0;
    L8:
        int r3 = this.frames.size();
        ArrayList<BitmapFrame> r4 = this.frames;
        if (r02 >= r3) goto L16;
        BitmapFrame r32 = r4.get(r02);
        if (r32 == null) goto L15;
        Bitmap r33 = r32.bitmap;
        if (r33 == null) goto L15;
        r33.recycle();
    L15:
        r02 = r02 + 1;
        goto L8
    L16:
        r4.clear();
        AsyncTask<Integer, Integer, Bitmap> r03 = this.currentTask;
        if (r03 == null) goto L24;
        r03.cancel(true);
        this.currentTask = null;
        return;
    L24:
        return;
    L5:
        if (this.fd == null) goto L7;
        goto L6
    }

    public abstract void drawBlur(Canvas r1, RectF r2);

    public float getLeftProgress() {
        return this.progressLeft;
    }

    public long getLength() {
        return Math.max(1, this.videoLength);
    }

    public float getProgress() {
        return this.playProgress;
    }

    public float getRightProgress() {
        return this.progressRight;
    }

    public void invalidateBlur() {
        if (customBlur() == true) goto L5;
        return;
    L5:
        if (this.hasBlur == false) goto L9;
        invalidate();
        return;
    }

    public boolean isDragging() {
        return this.pressedPlay;
    }

    @Override // android.view.View
    public void onDraw(Canvas r21) {
        float r7 = AndroidUtilities.dpf2(12.0f);
        float r2 = getMeasuredWidth() - (r7 * 2.0f);
        float r3 = (AndroidUtilities.dp(10.0f) + r7) + ((int) ((r2 - AndroidUtilities.dp(20.0f)) * this.progressLeft));
        float r4 = ((int) ((r2 - AndroidUtilities.dp(20.0f)) * this.progressRight)) + (AndroidUtilities.dp(10.0f) + r7);
        float r10 = 6.0f;
        float r5 = AndroidUtilities.dp(6.0f);
        float r6 = AndroidUtilities.dp(38.0f) + r5;
        float r12 = 1.0f;
        int r13 = 0;
        if (this.frames.isEmpty() == true) goto L5;
    L11:
        r21.save();
        this.clipPath.rewind();
        RectF r11 = AndroidUtilities.rectTmp;
        float r14 = r7 + r2;
        r11.set(r7, r5, r14, r6);
        float r16 = 2.0f;
        float r17 = 10.0f;
        this.clipPath.addRoundRect(r11, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Path.Direction.CW);
        r21.clipPath(this.clipPath);
        if (this.frames.size() >= this.framesToLoad) goto L14;
        boolean r22 = true;
    L15:
        this.hasBlur = r22;
        if (r22 == true) goto L25;
        int r23 = 0;
    L19:
        if (r23 >= this.frames.size()) goto L25;
        if (this.frames.get(r23).bitmap == null) goto L22;
        r23 = r23 + 1;
        goto L19
    L22:
        this.hasBlur = true;
    L25:
        if (this.hasBlur == true) goto L27;
    L29:
        float r24 = r3;
        float r8 = r4;
        float r32 = r5;
        float r9 = r6;
    L31:
        int r42 = 0;
    L33:
        if (r13 >= this.frames.size()) goto L46;
        BitmapFrame r52 = this.frames.get(r13);
        if (r52.bitmap == null) goto L44;
        float r62 = (this.frameWidth * r42) + r7;
        float r112 = AndroidUtilities.dp(r10);
        float r15 = r52.alpha;
        if (r15 == r12) goto L43;
        float r152 = r15 + 0.045714285f;
        r52.alpha = r152;
        if (r152 <= r12) goto L41;
        r52.alpha = r12;
    L42:
        float r18 = r10;
        float r19 = r12;
        this.bitmapPaint.setAlpha((int) (CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r52.alpha) * 255.0f));
        r21.drawBitmap(r52.bitmap, r62, r112, this.bitmapPaint);
    L45:
        r42 = r42 + 1;
        r13 = r13 + 1;
        r10 = r18;
        r12 = r19;
        goto L33
    L41:
        invalidate();
        goto L42
    L43:
        r18 = r10;
        r19 = r12;
        r21.drawBitmap(r52.bitmap, r62, r112, null);
        goto L45
    L44:
        r18 = r10;
        r19 = r12;
        goto L45
    L46:
        float r182 = r10;
        float r192 = r12;
        float r43 = r24;
        r21.drawRect(r7, r32, r43, AndroidUtilities.dp(46.0f), this.dimPaint);
        float r102 = r43;
        float r53 = r9;
        r21.drawRect(r8, r32, r14, r53, this.dimPaint);
        float r92 = r32;
        float r113 = r53;
        r21.restore();
    L48:
        if (this.isLivePhoto == true) goto L50;
        Canvas r1 = r21;
        r1.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        this.rect3.set(r102 - AndroidUtilities.dpf2(r17), r92, AndroidUtilities.dpf2(r17) + r8, r113);
        this.whitePaint.setAlpha(255);
        r1.drawRoundRect(this.rect3, AndroidUtilities.dpf2(r182), AndroidUtilities.dpf2(r182), this.whitePaint);
        this.rect3.set(r102, r92 + AndroidUtilities.dpf2(r16), r8, r113 - AndroidUtilities.dpf2(r16));
        r1.drawRect(this.rect3, this.cutPaint);
        r1.restore();
        float r25 = AndroidUtilities.dp(r16);
        float r33 = AndroidUtilities.dp(r17);
        float r44 = r102 - ((AndroidUtilities.dpf2(r17) - r25) / r16);
        float r54 = r92 + (((r113 - r92) - r33) / r16);
        float r34 = r33 + r54;
        this.rect3.set(r44, r54, r44 - r25, r34);
        r1.drawRoundRect(this.rect3, AndroidUtilities.dpf2(r182), AndroidUtilities.dpf2(r182), this.handlePaint);
        float r45 = ((AndroidUtilities.dpf2(r17) - r25) / r16) + r8;
        this.rect3.set(r45, r54, r25 + r45, r34);
        r1.drawRoundRect(this.rect3, AndroidUtilities.dpf2(r182), AndroidUtilities.dpf2(r182), this.handlePaint);
    L51:
        float r26 = this.loopProgress.set(0.0f);
        if (r26 <= 0.0f) goto L54;
        drawProgress(r1, this.progressRight, r26, this.whitePaint);
    L54:
        drawProgress(r1, this.playProgress, r192 - r26, this.whitePaint);
        if (this.isLivePhoto == false) goto L64;
        drawProgress(r1, this.progressPreview, r192, this.yellowPaint);
        return;
    L64:
        return;
    L50:
        r1 = r21;
        goto L51
    L27:
        if (customBlur() == false) goto L30;
        RectF r27 = AndroidUtilities.rectTmp;
        r27.set(r7, r5, AndroidUtilities.dp(4.0f) + r14, r6);
        drawBlur(r21, r27);
        goto L29
    L30:
        r24 = r3;
        r32 = r5;
        r21.drawRect(r24, r32, r4, r6, this.dimPaint);
        r8 = r4;
        r9 = r6;
        goto L31
    L14:
        r22 = false;
        goto L15
    L5:
        if (this.currentTask != null) goto L11;
        RectF r114 = AndroidUtilities.rectTmp;
        r114.set(r7, r5, r2 + r7, r6);
        if (customBlur() == false) goto L9;
        r21.save();
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r114, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Path.Direction.CW);
        r21.clipPath(this.clipPath);
        drawBlur(r21, r114);
        r21.restore();
    L10:
        reloadFrames(0);
        r113 = r6;
        r16 = 2.0f;
        r17 = 10.0f;
        r182 = 6.0f;
        r192 = 1.0f;
        r102 = r3;
        r8 = r4;
        r92 = r5;
        goto L48
    L9:
        r21.drawRoundRect(r114, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.dimPaint);
        goto L10
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (Build.VERSION.SDK_INT < 29) goto L6;
        this.exclustionRect.set(r2, 0, r4, getMeasuredHeight());
        setSystemGestureExclusionRects(this.exclusionRects);
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        int r12 = View.MeasureSpec.getSize(r1);
        if (this.lastWidth == r12) goto L6;
        clearFrames();
        this.lastWidth = r12;
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r13) {
        if (r13 != null) goto L5;
        return false;
    L5:
        float r1 = r13.getX();
        float r2 = r13.getY();
        int r3 = getMeasuredWidth() - AndroidUtilities.dp(44.0f);
        float r4 = r3;
        int r5 = ((int) (this.progressLeft * r4)) + AndroidUtilities.dp(22.0f);
        int r7 = ((int) (this.playProgress * r4)) + AndroidUtilities.dp(22.0f);
        int r8 = ((int) (this.progressRight * r4)) + AndroidUtilities.dp(22.0f);
        if (r13.getAction() != 0) goto L68;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.mediaMetadataRetriever != null) goto L10;
        return false;
    L10:
        int r132 = AndroidUtilities.dp(16.0f);
        int r0 = AndroidUtilities.dp(8.0f);
        if (r8 == r5) goto L26;
        if ((r7 - r0) > r1) goto L26;
        if (r1 > (r0 + r7)) goto L26;
        if (r2 < 0.0f) goto L26;
        if (r2 > getMeasuredHeight()) goto L26;
        VideoTimelineViewDelegate r133 = this.delegate;
        if (r133 == null) goto L23;
        r133.didStartDragging(TYPE_PROGRESS);
    L23:
        this.pressedPlay = true;
        this.pressDx = (int) (r1 - r7);
        invalidate();
        return true;
    L26:
        if ((r5 - r132) > r1) goto L39;
        if (r1 > Math.min(r5 + r132, r8)) goto L39;
        if (r2 < 0.0f) goto L39;
        if (r2 > getMeasuredHeight()) goto L39;
        VideoTimelineViewDelegate r134 = this.delegate;
        if (r134 == null) goto L36;
        r134.didStartDragging(TYPE_LEFT);
    L36:
        this.pressedLeft = true;
        this.pressDx = (int) (r1 - r5);
        invalidate();
        return true;
    L39:
        if ((r8 - r132) > r1) goto L52;
        if (r1 > (r132 + r8)) goto L52;
        if (r2 < 0.0f) goto L52;
        if (r2 > getMeasuredHeight()) goto L52;
        VideoTimelineViewDelegate r135 = this.delegate;
        if (r135 == null) goto L49;
        r135.didStartDragging(TYPE_RIGHT);
    L49:
        this.pressedRight = true;
        this.pressDx = (int) (r1 - r8);
        invalidate();
        return true;
    L52:
        if (r5 <= r1) goto L54;
    L166:
        return true;
    L54:
        if (r1 > r8) goto L166;
        if (r2 < 0.0f) goto L166;
        if (r2 > getMeasuredHeight()) goto L166;
        VideoTimelineViewDelegate r136 = this.delegate;
        if (r136 == null) goto L62;
        r136.didStartDragging(TYPE_PROGRESS);
    L62:
        this.pressedPlay = true;
        float r12 = (r1 - AndroidUtilities.dp(16.0f)) / r4;
        this.playProgress = r12;
        VideoTimelineViewDelegate r137 = this.delegate;
        if (r137 == null) goto L65;
        r137.onPlayProgressChanged(r12);
    L65:
        this.pressDx = 0.0f;
        invalidate();
        return true;
    L68:
        if (r13.getAction() == 1) goto L147;
        if (r13.getAction() == 3) goto L147;
        if (r13.getAction() != 2) goto L166;
        if (this.pressedPlay == false) goto L88;
        float r138 = (((int) (r1 - this.pressDx)) - AndroidUtilities.dp(16.0f)) / r4;
        this.playProgress = r138;
        float r02 = this.progressLeft;
        if (r138 >= r02) goto L79;
        this.playProgress = r02;
    L82:
        VideoTimelineViewDelegate r139 = this.delegate;
        if (r139 == null) goto L85;
        r139.onPlayProgressChanged(this.playProgress);
    L85:
        invalidate();
        return true;
    L79:
        float r03 = this.progressRight;
        if (r138 <= r03) goto L82;
        this.playProgress = r03;
        goto L82
    L88:
        if (this.pressedLeft == false) goto L117;
        int r1310 = (int) (r1 - this.pressDx);
        if (r1310 >= AndroidUtilities.dp(16.0f)) goto L92;
        r8 = AndroidUtilities.dp(16.0f);
    L95:
        float r1311 = (r8 - AndroidUtilities.dp(16.0f)) / r4;
        this.progressLeft = r1311;
        float r04 = this.progressRight;
        float r14 = r04 - r1311;
        float r22 = this.maxProgressDiff;
        if (r14 <= r22) goto L98;
        this.progressRight = r1311 + r22;
    L105:
        float r1312 = this.progressLeft;
        float r05 = this.playProgress;
        if (r1312 <= r05) goto L108;
        this.playProgress = r1312;
    L111:
        VideoTimelineViewDelegate r06 = this.delegate;
        if (r06 == null) goto L114;
        r06.onLeftProgressChanged(r1312);
    L114:
        invalidate();
        return true;
    L108:
        float r15 = this.progressRight;
        if (r15 >= r05) goto L111;
        this.playProgress = r15;
        goto L111
    L98:
        float r16 = this.minProgressDiff;
        if (r16 == 0.0f) goto L105;
        if ((r04 - r1311) >= r16) goto L105;
        float r07 = r04 - r16;
        this.progressLeft = r07;
        if (r07 >= 0.0f) goto L105;
        this.progressLeft = 0.0f;
        goto L105
    L92:
        if (r1310 > r8) goto L95;
        r8 = r1310;
        goto L95
    L117:
        if (this.pressedRight == false) goto L166;
        int r1313 = (int) (r1 - this.pressDx);
        if (r1313 >= r5) goto L122;
    L125:
        float r1314 = (r5 - AndroidUtilities.dp(16.0f)) / r4;
        this.progressRight = r1314;
        float r08 = this.progressLeft;
        float r17 = r1314 - r08;
        float r23 = this.maxProgressDiff;
        if (r17 <= r23) goto L128;
        this.progressLeft = r1314 - r23;
    L135:
        float r1315 = this.progressLeft;
        float r09 = this.playProgress;
        if (r1315 <= r09) goto L138;
        this.playProgress = r1315;
    L141:
        VideoTimelineViewDelegate r1316 = this.delegate;
        if (r1316 == null) goto L144;
        r1316.onRightProgressChanged(this.progressRight);
    L144:
        invalidate();
        return true;
    L138:
        float r1317 = this.progressRight;
        if (r1317 >= r09) goto L141;
        this.playProgress = r1317;
        goto L141
    L128:
        float r18 = this.minProgressDiff;
        if (r18 == 0.0f) goto L135;
        if ((r1314 - r08) >= r18) goto L135;
        float r010 = r08 + r18;
        this.progressRight = r010;
        if (r010 <= 1.0f) goto L135;
        this.progressRight = 1.0f;
        goto L135
    L122:
        if (r1313 <= (AndroidUtilities.dp(16.0f) + r3)) goto L124;
        r5 = r3 + AndroidUtilities.dp(16.0f);
        goto L125
    L124:
        r5 = r1313;
    L147:
        if (this.pressedLeft == false) goto L154;
        VideoTimelineViewDelegate r1318 = this.delegate;
        if (r1318 == null) goto L151;
        r1318.didStopDragging(TYPE_LEFT);
    L151:
        this.pressedLeft = false;
        return true;
    L154:
        if (this.pressedRight == false) goto L161;
        VideoTimelineViewDelegate r1319 = this.delegate;
        if (r1319 == null) goto L158;
        r1319.didStopDragging(TYPE_RIGHT);
    L158:
        this.pressedRight = false;
        return true;
    L161:
        if (this.pressedPlay == false) goto L166;
        VideoTimelineViewDelegate r1320 = this.delegate;
        if (r1320 == null) goto L165;
        r1320.didStopDragging(TYPE_PROGRESS);
    L165:
        this.pressedPlay = false;
        goto L166
    }

    public void setDelegate(VideoTimelineViewDelegate r1) {
        this.delegate = r1;
    }

    public void setMaxProgressDiff(float r3) {
        this.maxProgressDiff = r3;
        float r0 = this.progressRight;
        float r1 = this.progressLeft;
        if ((r0 - r1) <= r3) goto L6;
        this.progressRight = r1 + r3;
        invalidate();
        return;
    }

    public void setMinProgressDiff(float r1) {
        this.minProgressDiff = r1;
    }

    public void setMode(int r2) {
        if (this.currentMode != r2) goto L5;
        return;
    L5:
        this.currentMode = r2;
        invalidate();
    }

    public void setProgress(float r8) {
        float r2 = 0.0f;
        if (this.isLivePhoto == true) goto L5;
    L9:
        long r3 = this.videoLength;
        if (r3 == 0) goto L13;
        r2 = 240.0f / r3;
    L13:
        float r0 = this.playProgress;
        if (r8 < r0) goto L16;
    L20:
        this.playProgress = r8;
        invalidate();
        return;
    L16:
        if (r8 > (this.progressLeft + r2)) goto L20;
        if ((r0 + r2) < this.progressRight) goto L20;
        this.loopProgress.set(1.0f, true);
        goto L20
    L5:
        if (r8 > 0.0f) goto L7;
    L8:
        r8 = this.progressPreview;
        goto L9
    L7:
        if (r8 < 1.0f) goto L9;
        goto L8
    }

    public void setRightProgress(float r2) {
        this.progressRight = r2;
        VideoTimelineViewDelegate r22 = this.delegate;
        if (r22 == null) goto L5;
        r22.didStartDragging(TYPE_RIGHT);
    L5:
        VideoTimelineViewDelegate r23 = this.delegate;
        if (r23 == null) goto L8;
        r23.onRightProgressChanged(this.progressRight);
    L8:
        VideoTimelineViewDelegate r24 = this.delegate;
        if (r24 == null) goto L11;
        r24.didStopDragging(TYPE_RIGHT);
    L11:
        invalidate();
    }

    public void setVideoPath(String r7, long r8, float r10, float r11, long r12) {
        destroy();
        boolean r1 = true;
        int r0 = this.videoPathGeneration + 1;
        this.videoPathGeneration = r0;
        if (r8 > 0) goto L6;
        r1 = false;
    L6:
        this.isLivePhoto = r1;
        this.videoLength = 0;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.progressLeft = r10;
        this.progressRight = r11;
        float r13 = this.playProgress;
        if (r13 >= r10) goto L10;
        this.playProgress = r10;
    L12:
        Utilities.globalQueue.postRunnable(new VideoTimelinePlayView$$ExternalSyntheticLambda0(this, r8, r7, r0, r12));
        invalidate();
        return;
    L10:
        if (r13 <= r11) goto L12;
        this.playProgress = r11;
        goto L12
    }
}
