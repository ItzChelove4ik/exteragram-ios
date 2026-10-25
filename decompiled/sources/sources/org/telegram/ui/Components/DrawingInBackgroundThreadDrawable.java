package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class DrawingInBackgroundThreadDrawable implements NotificationCenter.NotificationCenterDelegate {
    public static DispatchQueuePool queuePool;
    boolean attachedToWindow;
    Bitmap backgroundBitmap;
    Canvas backgroundCanvas;
    private final DispatchQueue backgroundQueue;
    Bitmap bitmap;
    Canvas bitmapCanvas;
    private final Runnable bitmapCreateTask;
    private boolean bitmapUpdating;
    private int currentLayerNum;
    private int currentOpenedLayerFlags;
    boolean error;
    int frameGuid;
    int height;
    private int lastFrameId;
    boolean needSwapBitmaps;
    int padding;
    private Paint paint;
    protected boolean paused;
    private boolean reset;
    public final int threadIndex;
    Runnable uiFrameRunnable;
    int width;

    /* renamed from: org.telegram.ui.Components.DrawingInBackgroundThreadDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DrawingInBackgroundThreadDrawable this$0;

        public AnonymousClass1(DrawingInBackgroundThreadDrawable r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            DrawingInBackgroundThreadDrawable r0 = this.this$0;     // Catch: Exception -> L9
            int r1 = r0.height + r0.padding;     // Catch: Exception -> L9
            Bitmap r02 = r0.backgroundBitmap;     // Catch: Exception -> L9
            if (r02 == null) goto L11;
            int r03 = r02.getWidth();     // Catch: Exception -> L9
            DrawingInBackgroundThreadDrawable r2 = this.this$0;     // Catch: Exception -> L9
            if (r03 != r2.width) goto L11;
            if (r2.backgroundBitmap.getHeight() != r1) goto L11;
        L15:
            this.this$0.backgroundBitmap.eraseColor(0);     // Catch: Exception -> L9
            this.this$0.backgroundCanvas.save();     // Catch: Exception -> L9
            this.this$0.backgroundCanvas.translate(0.0f, r0.padding);     // Catch: Exception -> L9
            DrawingInBackgroundThreadDrawable r04 = this.this$0;     // Catch: Exception -> L9
            r04.drawInBackground(r04.backgroundCanvas);     // Catch: Exception -> L9
            this.this$0.backgroundCanvas.restore();     // Catch: Exception -> L9
            this.this$0.backgroundBitmap.prepareToDraw();     // Catch: Exception -> L9
        L18:
            AndroidUtilities.runOnUIThread(this.this$0.uiFrameRunnable);
            return;
        L11:
            Bitmap r05 = this.this$0.backgroundBitmap;     // Catch: Exception -> L9
            if (r05 == null) goto L14;
            r05.recycle();     // Catch: Exception -> L9
        L14:
            DrawingInBackgroundThreadDrawable r06 = this.this$0;     // Catch: Exception -> L9
            r06.backgroundBitmap = Bitmap.createBitmap(r06.width, r1, Bitmap.Config.ARGB_8888);     // Catch: Exception -> L9
            this.this$0.backgroundCanvas = new Canvas(this.this$0.backgroundBitmap);     // Catch: Exception -> L9
        L9:
            e = move-exception;
            FileLog.e(e);
            this.this$0.error = true;
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.Components.DrawingInBackgroundThreadDrawable$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ DrawingInBackgroundThreadDrawable this$0;

        public AnonymousClass2(DrawingInBackgroundThreadDrawable r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            DrawingInBackgroundThreadDrawable.m10131$$Nest$fputbitmapUpdating(this.this$0, false);
            this.this$0.onFrameReady();
            DrawingInBackgroundThreadDrawable r0 = this.this$0;
            if (r0.attachedToWindow == true) goto L7;
            DrawingInBackgroundThreadDrawable.m10132$$Nest$mrecycleBitmaps(r0);
            return;
        L7:
            if (r0.frameGuid == DrawingInBackgroundThreadDrawable.m10130$$Nest$fgetlastFrameId(r0)) goto L9;
            return;
        L9:
            this.this$0.needSwapBitmaps = true;
        }
    }

    public static class DispatchQueuePool {
        int pointer;
        public final DispatchQueue[] pool;
        final int size;

        private DispatchQueuePool(int r1) {
            this.size = r1;
            this.pool = new DispatchQueue[r1];
        }

        public /* synthetic */ DispatchQueuePool(int r1, DrawingInBackgroundThreadDrawableIA r2) {
            this(r1);
        }

        public DispatchQueue getNextQueue() {
            int r0 = this.pointer + 1;
            this.pointer = r0;
            if (r0 <= (this.size - 1)) goto L5;
            this.pointer = 0;
        L5:
            DispatchQueue[] r02 = this.pool;
            int r1 = this.pointer;
            DispatchQueue r2 = r02[r1];
            if (r2 != null) goto L9;
            DispatchQueue r22 = new DispatchQueue("draw_background_queue_" + this.pointer);
            r02[r1] = r22;
            return r22;
        L9:
            return r2;
        }
    }

    /* renamed from: -$$Nest$fgetlastFrameId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10130$$Nest$fgetlastFrameId(DrawingInBackgroundThreadDrawable r0) {
        return r0.lastFrameId;
    }

    /* renamed from: -$$Nest$fputbitmapUpdating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10131$$Nest$fputbitmapUpdating(DrawingInBackgroundThreadDrawable r0, boolean r1) {
        r0.bitmapUpdating = r1;
    }

    /* renamed from: -$$Nest$mrecycleBitmaps, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10132$$Nest$mrecycleBitmaps(DrawingInBackgroundThreadDrawable r0) {
        r0.recycleBitmaps();
    }

    public DrawingInBackgroundThreadDrawable() {
        this.currentLayerNum = 1;
        this.paint = new Paint(1);
        this.bitmapCreateTask = new AnonymousClass1(this);
        this.uiFrameRunnable = new AnonymousClass2(this);
        if (queuePool != null) goto L5;
        queuePool = new DispatchQueuePool(2, null);
    L5:
        this.backgroundQueue = queuePool.getNextQueue();
        this.threadIndex = queuePool.pointer;
    }

    private void recycleBitmaps() {
        ArrayList r0 = new ArrayList();
        Bitmap r1 = this.bitmap;
        if (r1 == null) goto L5;
        r0.add(r1);
    L5:
        Bitmap r12 = this.backgroundBitmap;
        if (r12 == null) goto L8;
        r0.add(r12);
    L8:
        this.bitmap = null;
        this.backgroundBitmap = null;
        this.backgroundCanvas = null;
        this.bitmapCanvas = null;
        AndroidUtilities.recycleBitmaps(r0);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.stopAllHeavyOperations) goto L18;
        Integer r22 = (Integer) r4[0];
        if (this.currentLayerNum < r22.intValue()) goto L7;
        return;
    L7:
        if (r22.intValue() == 512) goto L9;
    L11:
        int r32 = this.currentOpenedLayerFlags;
        int r23 = r22.intValue() | r32;
        this.currentOpenedLayerFlags = r23;
        if (r23 != 0) goto L14;
        return;
    L14:
        if (this.paused == true) goto L32;
        this.paused = true;
        onPaused();
        return;
    L32:
        return;
    L9:
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L11;
        return;
    L18:
        if (r2 != NotificationCenter.startAllHeavyOperations) goto L33;
        Integer r24 = (Integer) r4[0];
        if (this.currentLayerNum >= r24.intValue()) goto L34;
        int r33 = this.currentOpenedLayerFlags;
        if (r33 == 0) goto L38;
        int r25 = (~r24.intValue()) & r33;
        this.currentOpenedLayerFlags = r25;
        if (r25 == 0) goto L27;
        return;
    L27:
        if (this.paused == false) goto L36;
        this.paused = false;
        onResume();
        return;
    L36:
        return;
    L38:
        return;
    L34:
        return;
    }

    public void draw(Canvas r4, long r5, int r7, int r8, float r9) {
        if (this.error == true) goto L5;
        this.height = r8;
        this.width = r7;
        if (this.needSwapBitmaps == false) goto L11;
        this.needSwapBitmaps = false;
        Bitmap r72 = this.bitmap;
        Canvas r0 = this.bitmapCanvas;
        this.bitmap = this.backgroundBitmap;
        this.bitmapCanvas = this.backgroundCanvas;
        this.backgroundBitmap = r72;
        this.backgroundCanvas = r0;
    L11:
        Bitmap r73 = this.bitmap;
        if (r73 != null) goto L14;
    L15:
        this.reset = false;
        if (r73 == null) goto L18;
        ArrayList r74 = new ArrayList();
        r74.add(this.bitmap);
        AndroidUtilities.recycleBitmaps(r74);
        this.bitmap = null;
    L18:
        int r75 = this.height + this.padding;
        Bitmap r1 = this.bitmap;
        if (r1 != null) goto L21;
    L26:
        this.bitmap = Bitmap.createBitmap(this.width, r75, Bitmap.Config.ARGB_8888);
        this.bitmapCanvas = new Canvas(this.bitmap);
    L27:
        this.bitmapCanvas.save();
        this.bitmapCanvas.translate(0.0f, this.padding);
        drawInUiThread(this.bitmapCanvas, r9);
        this.bitmapCanvas.restore();
    L29:
        if (this.bitmapUpdating == false) goto L31;
    L33:
        Bitmap r52 = this.bitmap;
        if (r52 == null) goto L38;
        this.paint.setAlpha((int) (r9 * 255.0f));
        r4.save();
        r4.translate(0.0f, -this.padding);
        drawBitmap(r4, r52, this.paint);
        r4.restore();
        return;
    L38:
        return;
    L31:
        if (this.paused == true) goto L33;
        this.bitmapUpdating = true;
        prepareDraw(r5);
        this.lastFrameId = this.frameGuid;
        this.backgroundQueue.postRunnable(this.bitmapCreateTask);
        goto L33
    L21:
        if (r1.getHeight() != r75) goto L26;
        if (this.bitmap.getWidth() != this.width) goto L26;
        this.bitmap.eraseColor(0);
        goto L27
    L14:
        if (this.reset == false) goto L29;
    L5:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L37;
        r4.drawRect(0.0f, 0.0f, r7, r8, Theme.DEBUG_RED);
        return;
    }

    public void drawBitmap(Canvas r1, Bitmap r2, Paint r3) {
        r1.drawBitmap(r2, 0.0f, 0.0f, r3);
    }

    public abstract void drawInBackground(Canvas r1);

    public abstract void drawInUiThread(Canvas r1, float r2);

    public void onAttachToWindow() {
        if (this.attachedToWindow == false) goto L5;
        return;
    L5:
        this.attachedToWindow = true;
        this.error = false;
        int r1 = NotificationCenter.getGlobalInstance().getCurrentHeavyOperationFlags() & (~this.currentLayerNum);
        this.currentOpenedLayerFlags = r1;
        if (r1 == 0) goto L8;
    L10:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.stopAllHeavyOperations);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.startAllHeavyOperations);
        return;
    L8:
        if (this.paused == false) goto L10;
        this.paused = false;
        onResume();
        goto L10
    }

    public void onDetachFromWindow() {
        if (this.attachedToWindow == true) goto L6;
        return;
    L6:
        if (this.bitmapUpdating == true) goto L8;
        recycleBitmaps();
    L8:
        this.attachedToWindow = false;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.stopAllHeavyOperations);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.startAllHeavyOperations);
    }

    public void onFrameReady() {
    }

    public void onPaused() {
    }

    public void onResume() {
    }

    public abstract void prepareDraw(long r1);

    public void reset() {
        this.reset = true;
        this.frameGuid++;
        if (this.bitmap == null) goto L6;
        ArrayList r0 = new ArrayList();
        r0.add(this.bitmap);
        this.bitmap = null;
        AndroidUtilities.recycleBitmaps(r0);
        return;
    }

    public void setLayerNum(int r2) {
        this.currentLayerNum = r2;
        if (this.attachedToWindow == false) goto L6;
        this.currentOpenedLayerFlags = NotificationCenter.getGlobalInstance().getCurrentHeavyOperationFlags() & (~this.currentLayerNum);
        return;
    }
}
