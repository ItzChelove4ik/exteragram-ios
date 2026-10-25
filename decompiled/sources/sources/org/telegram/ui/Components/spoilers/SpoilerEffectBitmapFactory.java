package org.telegram.ui.Components.spoilers;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.Choreographer;
import java.util.ArrayList;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class SpoilerEffectBitmapFactory {
    private static SpoilerEffectBitmapFactory factory;
    private Bitmap backgroundBitmap;
    private Canvas backgroundCanvas;
    private final Buffer[] bitmapBuffers;
    private final PointsBuffer[] buffers;
    private final Rect clipRegion;
    private final Rect clipRegionDump;
    private int currentBitmapBuffer;
    private final DispatchQueue dispatchQueue;
    private boolean invalidated;
    private boolean isDrawnWithClipRegion;
    private boolean isRunning;
    private long lastUpdateTime;
    private final Choreographer.FrameCallback postFrameCallback;
    private Paint shaderPaint;
    private ArrayList<SpoilerEffect> shaderSpoilerEffects;
    final int size;

    public static class Buffer {
        private final Bitmap bitmap;
        private final BitmapShader shader;

        /* renamed from: -$$Nest$fgetbitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap m13798$$Nest$fgetbitmap(Buffer r0) {
            return r0.bitmap;
        }

        /* renamed from: -$$Nest$fgetshader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapShader m13799$$Nest$fgetshader(Buffer r0) {
            return r0.shader;
        }

        public Buffer(int r3) {
            Bitmap r32 = Bitmap.createBitmap(r3, r3, Bitmap.Config.ALPHA_8);
            this.bitmap = r32;
            Shader.TileMode r1 = Shader.TileMode.REPEAT;
            this.shader = new BitmapShader(r32, r1, r1);
        }
    }

    public static class PointsBuffer {
        private float[] buffer;
        private int length;

        public PointsBuffer() {
            this(64);
        }

        public PointsBuffer(int r2) {
            this.buffer = new float[Math.max(r2, 2)];
            this.length = 0;
        }

        private void ensureCapacity(int r3) {
            float[] r0 = this.buffer;
            if (r3 > r0.length) goto L5;
            return;
        L5:
            int r32 = Math.max(r3, r0.length * 2);
            this.buffer = Arrays.copyOf(this.buffer, r32);
        }

        public void addPoints(float[] r3, int r4, int r5) {
            ensureCapacity(this.length + r5);
            System.arraycopy(r3, r4, this.buffer, this.length, r5);
            this.length += r5;
        }

        public void draw(Canvas r3, Paint r4) {
            int r0 = this.length;
            if (r0 <= 0) goto L6;
            r3.drawPoints(this.buffer, 0, r0, r4);
            return;
        }

        public void reset() {
            this.length = 0;
        }
    }

    /* renamed from: $r8$lambda$hdA4qpKoPh-IRfUCJtZWWrYWPco, reason: not valid java name */
    public static /* synthetic */ void m13797$r8$lambda$hdA4qpKoPhIRfUCJtZWWrYWPco(SpoilerEffectBitmapFactory r0, int r1) {
        r0.lambda$checkUpdateImpl$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$ikCZB_gpmZYK8lWObqJLPYc6M94(SpoilerEffectBitmapFactory r0, long r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jydXJkLfuzhVm7U3d2OKtT55zBo(SpoilerEffectBitmapFactory r0, int r1) {
        r0.lambda$checkUpdateImpl$1(r1);
    }

    private SpoilerEffectBitmapFactory() {
        this.dispatchQueue = new DispatchQueue("SpoilerEffectBitmapFactory", true, 3);
        this.buffers = new PointsBuffer[SpoilerEffect.ALPHAS.length];
        this.bitmapBuffers = new Buffer[2];
        int r1 = 0;
        this.currentBitmapBuffer = 0;
        this.clipRegion = new Rect();
        this.postFrameCallback = new SpoilerEffectBitmapFactory$$ExternalSyntheticLambda0(this);
        this.clipRegionDump = new Rect();
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L6;
        float r0 = 150.0f;
    L5:
        int r02 = AndroidUtilities.dp(r0);
        Point r2 = AndroidUtilities.displaySize;
        int r03 = (int) Math.min(Math.min(r2.x, r2.y) * 0.5f, r02);
        if (r03 >= AndroidUtilities.dp(80.0f)) goto L10;
        r03 = AndroidUtilities.dp(80.0f);
    L10:
        this.size = r03;
    L11:
        PointsBuffer[] r04 = this.buffers;
        if (r1 >= r04.length) goto L14;
        r04[r1] = new PointsBuffer();
        r1 = r1 + 1;
        goto L11
    L14:
        return;
    L6:
        r0 = 100.0f;
        goto L5
    }

    private void applyClip(Rect r8) {
        int r0 = r8.left;
        int r1 = this.size;
        int r02 = ((r0 % r1) + r1) % r1;
        int r2 = ((r8.top % r1) + r1) % r1;
        int r12 = Math.min(r8.width(), this.size);
        int r13 = r12 + r02;
        int r82 = Math.min(r8.height(), this.size) + r2;
        this.clipRegion.union(r02, r2, Math.min(r13, this.size), Math.min(r82, this.size));
        int r3 = this.size;
        if (r13 <= r3) goto L5;
        this.clipRegion.union(0, r2, r13 - r3, Math.min(r82, r3));
    L5:
        int r22 = this.size;
        if (r82 <= r22) goto L8;
        this.clipRegion.union(r02, 0, Math.min(r13, r22), r82 - this.size);
    L8:
        int r03 = this.size;
        if (r13 <= r03) goto L13;
        if (r82 <= r03) goto L14;
        this.clipRegion.union(0, 0, r13 - r03, r82 - r03);
        return;
    L14:
        return;
    }

    private void checkUpdateImpl() {
        long r0 = System.currentTimeMillis();
        if ((r0 - this.lastUpdateTime) > 32) goto L5;
        return;
    L5:
        if (this.isRunning == false) goto L7;
        return;
    L7:
        if (this.clipRegion.isEmpty() == true) goto L12;
        this.lastUpdateTime = r0;
        this.isRunning = true;
        this.clipRegionDump.set(this.clipRegion);
        int r1 = (this.currentBitmapBuffer + 1) % 2;
        this.dispatchQueue.postRunnable(new SpoilerEffectBitmapFactory$$ExternalSyntheticLambda1(this, r1));
        return;
    }

    private void doDraw(Canvas r6, Rect r7) {
        PointsBuffer[] r0 = this.buffers;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r0[r3].reset();
        r3 = r3 + 1;
        goto L3
    L5:
        int r02 = 0;
    L6:
        ArrayList<SpoilerEffect> r12 = this.shaderSpoilerEffects;
        if (r02 >= 100) goto L12;
        SpoilerEffect r13 = r12.get(r02);
        if (Rect.intersects(r13.getBounds(), r7) == false) goto L11;
        r13.addPoints(this.buffers, r7);
    L11:
        r02 = r02 + 1;
        goto L6
    L12:
        r12.get(0).drawPoints(r6, this.buffers);
    }

    public static SpoilerEffectBitmapFactory getInstance() {
        if (factory != null) goto L6;
        factory = new SpoilerEffectBitmapFactory();
    L6:
        return factory;
    }

    private /* synthetic */ void lambda$checkUpdateImpl$1(int r3) {
        this.currentBitmapBuffer = r3;
        this.shaderPaint.setShader(Buffer.m13799$$Nest$fgetshader(this.bitmapBuffers[r3]));
        this.isRunning = false;
        this.isDrawnWithClipRegion = true;
    }

    private /* synthetic */ void lambda$checkUpdateImpl$2(int r4) {
        Buffer[] r0 = this.bitmapBuffers;
        if (r0[r4] != null) goto L5;
        r0[r4] = new Buffer(this.size);
    L5:
        Bitmap r02 = this.backgroundBitmap;
        if (r02 != null) goto L8;
        int r03 = this.size;
        this.backgroundBitmap = Bitmap.createBitmap(r03, r03, Bitmap.Config.ALPHA_8);
        this.backgroundCanvas = new Canvas(this.backgroundBitmap);
    L9:
        doDraw(this.backgroundCanvas, this.clipRegionDump);
        Utilities.copyBitmaps(this.backgroundBitmap, Buffer.m13798$$Nest$fgetbitmap(this.bitmapBuffers[r4]));
        AndroidUtilities.runOnUIThread(new SpoilerEffectBitmapFactory$$ExternalSyntheticLambda2(this, r4));
        return;
    L8:
        r02.eraseColor(0);
        goto L9
    }

    private /* synthetic */ void lambda$new$0(long r1) {
        checkUpdateImpl();
        this.clipRegion.set(0, 0, 0, 0);
        this.invalidated = false;
    }

    public void checkUpdate(Rect r1) {
        applyClip(r1);
        if (this.invalidated == false) goto L5;
        return;
    L5:
        if (this.clipRegion.isEmpty() == true) goto L9;
        this.invalidated = true;
        Choreographer.getInstance().postFrameCallback(this.postFrameCallback);
        return;
    }

    public Paint getPaint() {
        Buffer[] r0 = this.bitmapBuffers;
        if (r0[0] != null) goto L13;
        r0[0] = new Buffer(this.size);
        this.shaderPaint = new Paint();
        this.shaderSpoilerEffects = new ArrayList(100);
        int r02 = this.size;
        int r2 = (int) (r02 / 10.0f);
        int r03 = (int) ((r02 / AndroidUtilities.dp(200.0f)) * 60.0f);
        int r3 = 0;
    L6:
        if (r3 >= 10) goto L11;
        int r5 = 0;
    L8:
        if (r5 >= 10) goto L10;
        SpoilerEffect r6 = new SpoilerEffect();
        r6.setSize(this.size);
        int r7 = r2 * r3;
        int r8 = r2 * r5;
        r6.setBounds(r7, r8 - AndroidUtilities.dp(5.0f), (r7 + r2) + AndroidUtilities.dp(3.0f), (r8 + r2) + AndroidUtilities.dp(5.0f));
        r6.setMaxParticlesCount(Math.min(SpoilerEffect.MAX_PARTICLES_PER_ENTITY * 5, r03));
        r6.setColor(-1);
        this.shaderSpoilerEffects.add(r6);
        r5 = r5 + 1;
        goto L8
    L10:
        r3 = r3 + 1;
        goto L6
    L11:
        Canvas r04 = new Canvas(Buffer.m13798$$Nest$fgetbitmap(this.bitmapBuffers[0]));
        int r32 = this.size;
        doDraw(r04, new Rect(0, 0, r32, r32));
        this.shaderPaint.setShader(Buffer.m13799$$Nest$fgetshader(this.bitmapBuffers[0]));
        this.lastUpdateTime = System.currentTimeMillis();
    L18:
        return this.shaderPaint;
    L13:
        if (this.isDrawnWithClipRegion == false) goto L18;
        if (LiteMode.isEnabled(128) == true) goto L18;
        this.currentBitmapBuffer = 0;
        Canvas r05 = new Canvas(Buffer.m13798$$Nest$fgetbitmap(this.bitmapBuffers[0]));
        int r33 = this.size;
        doDraw(r05, new Rect(0, 0, r33, r33));
        this.shaderPaint.setShader(Buffer.m13799$$Nest$fgetshader(this.bitmapBuffers[0]));
        this.lastUpdateTime = System.currentTimeMillis();
        this.isDrawnWithClipRegion = false;
        goto L18
    }
}
