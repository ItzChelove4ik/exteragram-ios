package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class VideoTimelineView extends View {
    private static final Object sync = null;
    private final Paint backgroundGrayPaint;
    private AsyncTask<Integer, Integer, Bitmap> currentTask;
    private VideoTimelineViewDelegate delegate;
    private int frameHeight;
    private long frameTimeOffset;
    private int frameWidth;
    private ArrayList<Bitmap> frames;
    private boolean framesLoaded;
    private int framesToLoad;
    private boolean isRoundFrames;
    private ArrayList<Bitmap> keyframes;
    private float maxProgressDiff;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private float minProgressDiff;
    private final Paint paint2;
    Path path;
    private float pressDx;
    private boolean pressedLeft;
    private boolean pressedRight;
    private float progressLeft;
    private float progressRight;
    private Rect rect1;
    private Rect rect2;
    private Bitmap roundCornerBitmap;
    private int roundCornersSize;
    Paint thumbPaint;
    private TimeHintView timeHintView;
    boolean useClip;
    private long videoLength;

    /* renamed from: org.telegram.ui.Components.VideoTimelineView$1, reason: invalid class name */
    public class AnonymousClass1 extends AsyncTask<Integer, Integer, Bitmap> {
        private int frameNum;
        final /* synthetic */ VideoTimelineView this$0;

        public AnonymousClass1(VideoTimelineView r1) {
            this.this$0 = r1;
            this.frameNum = 0;
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Bitmap doInBackground2(Integer... r11) {
            this.frameNum = r11[0].intValue();
            Bitmap r1 = null;
            if (isCancelled() == false) goto L20;
            return null;
        L20:
            Bitmap r112 = VideoTimelineView.m13531$$Nest$fgetmediaMetadataRetriever(this.this$0).getFrameAtTime((VideoTimelineView.m13527$$Nest$fgetframeTimeOffset(this.this$0) * this.frameNum) * 1000, 2);     // Catch: Exception -> L15
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
            Bitmap r2 = Bitmap.createBitmap(VideoTimelineView.m13528$$Nest$fgetframeWidth(this.this$0), VideoTimelineView.m13526$$Nest$fgetframeHeight(this.this$0), r112.getConfig());     // Catch: Exception -> L12
            Canvas r3 = new Canvas(r2);     // Catch: Exception -> L12
            float r5 = Math.max(VideoTimelineView.m13528$$Nest$fgetframeWidth(this.this$0) / r112.getWidth(), VideoTimelineView.m13526$$Nest$fgetframeHeight(this.this$0) / r112.getHeight());     // Catch: Exception -> L12
            int r6 = (int) (r112.getWidth() * r5);     // Catch: Exception -> L12
            int r52 = (int) (r112.getHeight() * r5);     // Catch: Exception -> L12
            r3.drawBitmap(r112, new Rect(0, 0, r112.getWidth(), r112.getHeight()), new Rect((VideoTimelineView.m13528$$Nest$fgetframeWidth(this.this$0) - r6) / 2, (VideoTimelineView.m13526$$Nest$fgetframeHeight(this.this$0) - r52) / 2, r6, r52), null);     // Catch: Exception -> L12
            r112.recycle();     // Catch: Exception -> L12
            return r2;
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
        public void onPostExecute2(Bitmap r4) {
            if (isCancelled() == true) goto L10;
            VideoTimelineView.m13529$$Nest$fgetframes(this.this$0).add(r4);
            this.this$0.invalidate();
            int r42 = this.frameNum;
            int r0 = VideoTimelineView.m13530$$Nest$fgetframesToLoad(this.this$0);
            VideoTimelineView r1 = this.this$0;
            if (r42 >= r0) goto L8;
            VideoTimelineView.m13533$$Nest$mreloadFrames(r1, this.frameNum + 1);
            return;
        L8:
            VideoTimelineView.m13532$$Nest$fputframesLoaded(r1, true);
            return;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Bitmap r1) {
            onPostExecute2(r1);
        }
    }

    public static class TimeHintView extends View {
        private float cx;
        private long lastTime;
        private float scale;
        private boolean show;
        private Drawable tooltipBackground;
        private Drawable tooltipBackgroundArrow;
        private StaticLayout tooltipLayout;
        private TextPaint tooltipPaint;

        public TimeHintView(Context r4) {
            super(r4);
            TextPaint r0 = new TextPaint(1);
            this.tooltipPaint = r0;
            this.lastTime = -1;
            r0.setTextSize(AndroidUtilities.dp(14.0f));
            this.tooltipBackgroundArrow = ContextCompat.getDrawable(r4, R.drawable.tooltip_arrow);
            this.tooltipBackground = Theme.createRoundRectDrawable(AndroidUtilities.dp(5.0f), Theme.getColor(Theme.key_chat_gifSaveHintBackground));
            updateColors();
            setTime(0);
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (this.tooltipLayout == null) goto L28;
            boolean r0 = this.show;
            float r1 = this.scale;
            if (r0 == false) goto L14;
            if (r1 == 1.0f) goto L22;
            float r12 = r1 + 0.12f;
            this.scale = r12;
            if (r12 <= 1.0f) goto L12;
            this.scale = 1.0f;
        L12:
            invalidate();
        L22:
            float r02 = this.scale;
            if (r02 <= 0.5f) goto L25;
            float r03 = 1.0f;
        L26:
            int r04 = (int) (r03 * 255.0f);
            r10.save();
            float r13 = this.scale;
            r10.scale(r13, r13, this.cx, getMeasuredHeight());
            r10.translate(this.cx - (this.tooltipLayout.getWidth() / 2.0f), 0.0f);
            this.tooltipBackground.setBounds(-AndroidUtilities.dp(8.0f), 0, this.tooltipLayout.getWidth() + AndroidUtilities.dp(8.0f), (int) (this.tooltipLayout.getHeight() + AndroidUtilities.dpf2(4.0f)));
            this.tooltipBackgroundArrow.setBounds((this.tooltipLayout.getWidth() / 2) - (this.tooltipBackgroundArrow.getIntrinsicWidth() / 2), (int) (this.tooltipLayout.getHeight() + AndroidUtilities.dpf2(4.0f)), (this.tooltipLayout.getWidth() / 2) + (this.tooltipBackgroundArrow.getIntrinsicWidth() / 2), ((int) (this.tooltipLayout.getHeight() + AndroidUtilities.dpf2(4.0f))) + this.tooltipBackgroundArrow.getIntrinsicHeight());
            this.tooltipBackgroundArrow.setAlpha(r04);
            this.tooltipBackground.setAlpha(r04);
            this.tooltipPaint.setAlpha(r04);
            this.tooltipBackgroundArrow.draw(r10);
            this.tooltipBackground.draw(r10);
            r10.translate(0.0f, AndroidUtilities.dpf2(1.0f));
            this.tooltipLayout.draw(r10);
            r10.restore();
            return;
        L25:
            r03 = r02 / 0.5f;
            goto L26
        L14:
            if (r1 == 0.0f) goto L20;
            float r14 = r1 - 0.12f;
            this.scale = r14;
            if (r14 >= 0.0f) goto L18;
            this.scale = 0.0f;
        L18:
            invalidate();
        L20:
            if (this.scale != 0.0f) goto L22;
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((this.tooltipLayout.getHeight() + AndroidUtilities.dp(4.0f)) + this.tooltipBackgroundArrow.getIntrinsicHeight(), TLObject.FLAG_30));
        }

        public void setCx(float r1) {
            this.cx = r1;
            invalidate();
        }

        public void setTime(int r12) {
            long r0 = r12;
            if (r0 == this.lastTime) goto L6;
            this.lastTime = r0;
            String r4 = AndroidUtilities.formatShortDuration(r12);
            TextPaint r5 = this.tooltipPaint;
            this.tooltipLayout = new StaticLayout(r4, r5, (int) r5.measureText(r4), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            return;
        }

        public void show(boolean r1) {
            this.show = r1;
            invalidate();
        }

        public void updateColors() {
            this.tooltipPaint.setColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
            int r0 = AndroidUtilities.dp(5.0f);
            int r1 = Theme.key_chat_gifSaveHintBackground;
            this.tooltipBackground = Theme.createRoundRectDrawable(r0, Theme.getColor(r1));
            this.tooltipBackgroundArrow.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r1), PorterDuff.Mode.MULTIPLY));
        }
    }

    public interface VideoTimelineViewDelegate {
        void didStartDragging();

        void didStopDragging();

        void onLeftProgressChanged(float r1);

        void onRightProgressChanged(float r1);
    }

    /* renamed from: -$$Nest$fgetframeHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13526$$Nest$fgetframeHeight(VideoTimelineView r0) {
        return r0.frameHeight;
    }

    /* renamed from: -$$Nest$fgetframeTimeOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13527$$Nest$fgetframeTimeOffset(VideoTimelineView r2) {
        return r2.frameTimeOffset;
    }

    /* renamed from: -$$Nest$fgetframeWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13528$$Nest$fgetframeWidth(VideoTimelineView r0) {
        return r0.frameWidth;
    }

    /* renamed from: -$$Nest$fgetframes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13529$$Nest$fgetframes(VideoTimelineView r0) {
        return r0.frames;
    }

    /* renamed from: -$$Nest$fgetframesToLoad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13530$$Nest$fgetframesToLoad(VideoTimelineView r0) {
        return r0.framesToLoad;
    }

    /* renamed from: -$$Nest$fgetmediaMetadataRetriever, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaMetadataRetriever m13531$$Nest$fgetmediaMetadataRetriever(VideoTimelineView r0) {
        return r0.mediaMetadataRetriever;
    }

    /* renamed from: -$$Nest$fputframesLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13532$$Nest$fputframesLoaded(VideoTimelineView r0, boolean r1) {
        r0.framesLoaded = r1;
    }

    /* renamed from: -$$Nest$mreloadFrames, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13533$$Nest$mreloadFrames(VideoTimelineView r0, int r1) {
        r0.reloadFrames(r1);
    }

    static {
        sync = new Object();
    }

    public VideoTimelineView(Context r3) {
        super(r3);
        this.progressRight = 1.0f;
        Paint r0 = new Paint();
        this.paint2 = r0;
        this.backgroundGrayPaint = new Paint();
        this.frames = new ArrayList();
        this.maxProgressDiff = 1.0f;
        this.minProgressDiff = 0.0f;
        this.keyframes = new ArrayList();
        this.thumbPaint = new Paint(1);
        r0.setColor(2130706432);
        this.thumbPaint.setColor(-1);
        this.thumbPaint.setStrokeWidth(AndroidUtilities.dpf2(2.0f));
        this.thumbPaint.setStyle(Paint.Style.STROKE);
        this.thumbPaint.setStrokeCap(Paint.Cap.ROUND);
        updateColors();
    }

    private void drawCorners(Canvas r8, int r9, int r10, int r11, int r12) {
        if (AndroidUtilities.dp(6.0f) == this.roundCornersSize) goto L5;
        this.roundCornersSize = AndroidUtilities.dp(6.0f);
        this.roundCornerBitmap = Bitmap.createBitmap(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Bitmap.Config.ARGB_8888);
        Canvas r0 = new Canvas(this.roundCornerBitmap);
        Paint r1 = new Paint(1);
        r1.setColor(0);
        r1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        r0.drawColor(Theme.getColor(Theme.key_chat_messagePanelBackground));
        int r2 = this.roundCornersSize;
        r0.drawCircle(r2, r2, r2, r1);
    L5:
        int r02 = this.roundCornersSize >> 1;
        r8.save();
        float r22 = r11;
        float r3 = r12;
        r8.drawBitmap(this.roundCornerBitmap, r22, r3, null);
        float r5 = (r9 + r12) - r02;
        r8.rotate(-90.0f, r11 + r02, r5);
        r8.drawBitmap(this.roundCornerBitmap, r22, r9 - this.roundCornersSize, null);
        r8.restore();
        r8.save();
        float r102 = (r11 + r10) - r02;
        r8.rotate(180.0f, r102, r5);
        Bitmap r13 = this.roundCornerBitmap;
        int r23 = this.roundCornersSize;
        r8.drawBitmap(r13, r11 - r23, r9 - r23, null);
        r8.restore();
        r8.save();
        r8.rotate(90.0f, r102, r12 + r02);
        r8.drawBitmap(this.roundCornerBitmap, r11 - this.roundCornersSize, r3, null);
        r8.restore();
    }

    private void reloadFrames(int r6) {
        if (this.mediaMetadataRetriever == null) goto L17;
        int r0 = 0;
        if (r6 == 0) goto L8;
    L18:
        this.framesLoaded = false;
        AnonymousClass1 r02 = new AnonymousClass1(this);
        this.currentTask = r02;
        r02.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[]{Integer.valueOf(r6), null, null});
        return;
    L8:
        if (this.isRoundFrames == false) goto L10;
        int r1 = AndroidUtilities.dp(56.0f);
        this.frameWidth = r1;
        this.frameHeight = r1;
        this.framesToLoad = Math.max(1, (int) Math.ceil((getMeasuredWidth() - AndroidUtilities.dp(16.0f)) / (this.frameHeight / 2.0f)));
    L11:
        this.frameTimeOffset = this.videoLength / this.framesToLoad;
        if (this.keyframes.isEmpty() == true) goto L18;
        float r62 = this.keyframes.size() / this.framesToLoad;
        float r12 = 0.0f;
    L15:
        if (r0 >= this.framesToLoad) goto L21;
        this.frames.add(this.keyframes.get((int) r12));
        r12 = r12 + r62;
        r0 = r0 + 1;
        goto L15
    L21:
        return;
    L10:
        this.frameHeight = AndroidUtilities.dp(40.0f);
        this.framesToLoad = Math.max(1, (getMeasuredWidth() - AndroidUtilities.dp(16.0f)) / this.frameHeight);
        this.frameWidth = (int) Math.ceil((getMeasuredWidth() - AndroidUtilities.dp(16.0f)) / this.framesToLoad);
        goto L11
    }

    public void clearFrames() {
        if (this.keyframes.isEmpty() == false) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= this.frames.size()) goto L11;
        Bitmap r1 = this.frames.get(r0);
        if (r1 == null) goto L10;
        r1.recycle();
    L10:
        r0 = r0 + 1;
    L11:
        this.frames.clear();
        AsyncTask<Integer, Integer, Bitmap> r02 = this.currentTask;
        if (r02 == null) goto L14;
        r02.cancel(true);
        this.currentTask = null;
    L14:
        invalidate();
    }

    public void destroy() {
        destroy(true);
    }

    public void destroy(boolean r4) {
        Object r0 = sync;
        monitor-enter(r0);
        MediaMetadataRetriever r2 = this.mediaMetadataRetriever;     // Catch: Throwable -> L9 Exception -> L11
        if (r2 == null) goto L13;
        r2.release();     // Catch: Throwable -> L9 Exception -> L11
        this.mediaMetadataRetriever = null;     // Catch: Throwable -> L9 Exception -> L11
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r4 == false) goto L29;
        int r02 = 0;
        if (this.keyframes.isEmpty() == true) goto L24;
    L18:
        if (r02 >= this.keyframes.size()) goto L29;
        Bitmap r42 = this.keyframes.get(r02);
        if (r42 == null) goto L22;
        r42.recycle();
    L22:
        r02 = r02 + 1;
    L24:
        if (r02 >= this.frames.size()) goto L29;
        Bitmap r43 = this.frames.get(r02);
        if (r43 == null) goto L28;
        r43.recycle();
    L28:
        r02 = r02 + 1;
    L29:
        this.keyframes.clear();
        this.frames.clear();
        AsyncTask<Integer, Integer, Bitmap> r44 = this.currentTask;
        if (r44 == null) goto L44;
        r44.cancel(true);
        this.currentTask = null;
        return;
    L44:
        return;
    L11:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public float getLeftProgress() {
        return this.progressLeft;
    }

    public float getRightProgress() {
        return this.progressRight;
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        if (this.useClip == false) goto L7;
        r15.save();
        Path r2 = this.path;
        if (r2 == null) goto L7;
        r15.clipPath(r2);
    L7:
        float r22 = getMeasuredWidth() - AndroidUtilities.dp(24.0f);
        int r7 = ((int) (this.progressLeft * r22)) + AndroidUtilities.dp(12.0f);
        int r8 = ((int) (r22 * this.progressRight)) + AndroidUtilities.dp(12.0f);
        int r10 = (getMeasuredHeight() - AndroidUtilities.dp(32.0f)) >> 1;
        int r11 = 0;
        if (this.frames.isEmpty() == false) goto L13;
        if (this.currentTask != null) goto L13;
        reloadFrames(0);
    L13:
        if (this.frames.isEmpty() == true) goto L39;
        if (this.framesLoaded == true) goto L17;
        r15.drawRect(0.0f, r10, getMeasuredWidth(), getMeasuredHeight() - r10, this.backgroundGrayPaint);
    L17:
        int r23 = 0;
    L19:
        if (r11 >= this.frames.size()) goto L32;
        Bitmap r3 = this.frames.get(r11);
        if (r3 == null) goto L31;
        if (r3.isRecycled() == true) goto L31;
        boolean r4 = this.isRoundFrames;
        int r5 = this.frameWidth;
        if (r4 == false) goto L27;
        r5 = r5 / 2;
    L27:
        int r52 = r5 * r23;
        if (r4 == false) goto L30;
        this.rect2.set(r52, r10, AndroidUtilities.dp(28.0f) + r52, AndroidUtilities.dp(32.0f) + r10);
        r15.drawBitmap(r3, this.rect1, this.rect2, null);
        goto L31
    L30:
        r15.drawBitmap(r3, r52, r10, null);
    L31:
        r23 = r23 + 1;
        r11 = r11 + 1;
        goto L19
    L32:
        float r32 = r10;
        r15.drawRect(0.0f, r32, r7, getMeasuredHeight() - r10, this.paint2);
        r15.drawRect(r8, r32, getMeasuredWidth(), getMeasuredHeight() - r10, this.paint2);
        r15.drawLine(r7 - AndroidUtilities.dp(4.0f), AndroidUtilities.dp(10.0f) + r10, r7 - AndroidUtilities.dp(4.0f), (getMeasuredHeight() - AndroidUtilities.dp(10.0f)) - r10, this.thumbPaint);
        r15.drawLine(AndroidUtilities.dp(4.0f) + r8, AndroidUtilities.dp(10.0f) + r10, r8 + AndroidUtilities.dp(4.0f), (getMeasuredHeight() - AndroidUtilities.dp(10.0f)) - r10, this.thumbPaint);
        if (this.useClip == false) goto L36;
        r15.restore();
        return;
    L36:
        drawCorners(r15, getMeasuredHeight() - (r10 * 2), getMeasuredWidth(), 0, r10);
        return;
    L39:
        if (this.useClip == false) goto L47;
        r15.restore();
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(r4, r5);
        if (this.useClip == true) goto L5;
        return;
    L5:
        if (this.path != null) goto L7;
        this.path = new Path();
    L7:
        this.path.rewind();
        int r42 = (getMeasuredHeight() - AndroidUtilities.dp(32.0f)) >> 1;
        RectF r52 = AndroidUtilities.rectTmp;
        r52.set(0.0f, r42, getMeasuredWidth(), getMeasuredHeight() - r42);
        this.path.addRoundRect(r52, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), Path.Direction.CCW);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r15) {
        if (r15 != null) goto L5;
        return false;
    L5:
        float r1 = r15.getX();
        float r2 = r15.getY();
        int r3 = getMeasuredWidth() - AndroidUtilities.dp(24.0f);
        float r5 = r3;
        int r6 = ((int) (this.progressLeft * r5)) + AndroidUtilities.dp(12.0f);
        int r8 = ((int) (this.progressRight * r5)) + AndroidUtilities.dp(12.0f);
        if (r15.getAction() != 0) goto L38;
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.mediaMetadataRetriever != null) goto L10;
        return false;
    L10:
        int r152 = AndroidUtilities.dp(24.0f);
        if ((r6 - r152) > r1) goto L24;
        if (r1 > (r6 + r152)) goto L24;
        if (r2 < 0.0f) goto L24;
        if (r2 > getMeasuredHeight()) goto L24;
        VideoTimelineViewDelegate r153 = this.delegate;
        if (r153 == null) goto L21;
        r153.didStartDragging();
    L21:
        this.pressedLeft = true;
        this.pressDx = (int) (r1 - r6);
        this.timeHintView.setTime((int) ((this.videoLength / 1000.0f) * this.progressLeft));
        this.timeHintView.setCx((r6 + getLeft()) + AndroidUtilities.dp(4.0f));
        this.timeHintView.show(true);
        invalidate();
        return true;
    L24:
        if ((r8 - r152) <= r1) goto L26;
    L36:
        this.timeHintView.show(false);
    L105:
        return false;
    L26:
        if (r1 > (r152 + r8)) goto L36;
        if (r2 < 0.0f) goto L36;
        if (r2 > getMeasuredHeight()) goto L36;
        VideoTimelineViewDelegate r154 = this.delegate;
        if (r154 == null) goto L34;
        r154.didStartDragging();
    L34:
        this.pressedRight = true;
        this.pressDx = (int) (r1 - r8);
        this.timeHintView.setTime((int) ((this.videoLength / 1000.0f) * this.progressRight));
        this.timeHintView.setCx((r8 + getLeft()) - AndroidUtilities.dp(4.0f));
        this.timeHintView.show(true);
        invalidate();
        return true;
    L38:
        if (r15.getAction() == 1) goto L92;
        if (r15.getAction() == 3) goto L92;
        if (r15.getAction() != 2) goto L105;
        if (this.pressedLeft == false) goto L68;
        int r155 = (int) (r1 - this.pressDx);
        if (r155 >= AndroidUtilities.dp(16.0f)) goto L49;
        r8 = AndroidUtilities.dp(16.0f);
    L52:
        float r156 = (r8 - AndroidUtilities.dp(16.0f)) / r5;
        this.progressLeft = r156;
        float r0 = this.progressRight;
        float r12 = r0 - r156;
        float r22 = this.maxProgressDiff;
        if (r12 <= r22) goto L55;
        this.progressRight = r156 + r22;
    L62:
        this.timeHintView.setCx((((r5 * this.progressLeft) + AndroidUtilities.dpf2(12.0f)) + getLeft()) - AndroidUtilities.dp(4.0f));
        this.timeHintView.setTime((int) ((this.videoLength / 1000.0f) * this.progressLeft));
        this.timeHintView.show(true);
        VideoTimelineViewDelegate r157 = this.delegate;
        if (r157 == null) goto L65;
        r157.onLeftProgressChanged(this.progressLeft);
    L65:
        invalidate();
        return true;
    L55:
        float r13 = this.minProgressDiff;
        if (r13 == 0.0f) goto L62;
        if ((r0 - r156) >= r13) goto L62;
        float r02 = r0 - r13;
        this.progressLeft = r02;
        if (r02 >= 0.0f) goto L62;
        this.progressLeft = 0.0f;
        goto L62
    L49:
        if (r155 > r8) goto L52;
        r8 = r155;
        goto L52
    L68:
        if (this.pressedRight == false) goto L105;
        int r158 = (int) (r1 - this.pressDx);
        if (r158 >= r6) goto L73;
    L76:
        float r159 = (r6 - AndroidUtilities.dp(16.0f)) / r5;
        this.progressRight = r159;
        float r03 = this.progressLeft;
        float r14 = r159 - r03;
        float r23 = this.maxProgressDiff;
        if (r14 <= r23) goto L79;
        this.progressLeft = r159 - r23;
    L86:
        this.timeHintView.setCx((((r5 * this.progressRight) + AndroidUtilities.dpf2(12.0f)) + getLeft()) + AndroidUtilities.dp(4.0f));
        this.timeHintView.show(true);
        this.timeHintView.setTime((int) ((this.videoLength / 1000.0f) * this.progressRight));
        VideoTimelineViewDelegate r1510 = this.delegate;
        if (r1510 == null) goto L89;
        r1510.onRightProgressChanged(this.progressRight);
    L89:
        invalidate();
        return true;
    L79:
        float r16 = this.minProgressDiff;
        if (r16 == 0.0f) goto L86;
        if ((r159 - r03) >= r16) goto L86;
        float r04 = r03 + r16;
        this.progressRight = r04;
        if (r04 <= 1.0f) goto L86;
        this.progressRight = 1.0f;
        goto L86
    L73:
        if (r158 <= (AndroidUtilities.dp(16.0f) + r3)) goto L75;
        r6 = r3 + AndroidUtilities.dp(16.0f);
        goto L76
    L75:
        r6 = r158;
    L92:
        if (this.pressedLeft == false) goto L99;
        VideoTimelineViewDelegate r1511 = this.delegate;
        if (r1511 == null) goto L96;
        r1511.didStopDragging();
    L96:
        this.pressedLeft = false;
        invalidate();
        this.timeHintView.show(false);
        return true;
    L99:
        if (this.pressedRight == false) goto L105;
        VideoTimelineViewDelegate r1512 = this.delegate;
        if (r1512 == null) goto L103;
        r1512.didStopDragging();
    L103:
        this.pressedRight = false;
        invalidate();
        this.timeHintView.show(false);
        return true;
    }

    public void setDelegate(VideoTimelineViewDelegate r1) {
        this.delegate = r1;
    }

    public void setKeyframes(ArrayList<Bitmap> r2) {
        this.keyframes.clear();
        this.keyframes.addAll(r2);
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

    public void setRoundFrames(boolean r5) {
        this.isRoundFrames = r5;
        if (r5 == false) goto L6;
        this.rect1 = new Rect(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(42.0f), AndroidUtilities.dp(42.0f));
        this.rect2 = new Rect();
        return;
    }

    public void setTimeHintView(TimeHintView r1) {
        this.timeHintView = r1;
    }

    public void setTrimProgress(float r3, float r4) {
        float r32 = Math.max(0.0f, Math.min(1.0f, r3));
        this.progressLeft = r32;
        this.progressRight = Math.max(r32, Math.min(1.0f, r4));
        invalidate();
    }

    public void setVideoPath(String r3) {
        destroy(false);
        MediaMetadataRetriever r0 = new MediaMetadataRetriever();
        this.mediaMetadataRetriever = r0;
        this.progressLeft = 0.0f;
        this.progressRight = 1.0f;
        r0.setDataSource(r3);     // Catch: Exception -> L5
        this.videoLength = Long.parseLong(this.mediaMetadataRetriever.extractMetadata(9));     // Catch: Exception -> L5
    L7:
        invalidate();
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    public void updateColors() {
        this.backgroundGrayPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.roundCornersSize = 0;
        TimeHintView r2 = this.timeHintView;
        if (r2 == null) goto L6;
        r2.updateColors();
        return;
    }
}
