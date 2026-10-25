package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import me.vkryl.core.reference.ReferenceList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimatedFileDrawableStream;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.DispatchQueuePoolBackground;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.utils.BitmapsCache;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final class AnimatedFileDrawable extends BitmapDrawable implements Animatable, BitmapsCache.Cacheable {
    private static final Xfermode SRC_XFERMODE = null;
    private static int activeChoreographersCount;
    private static final ScheduledThreadPoolExecutor executor = null;
    private static final float[] radii = null;
    private final int MAX_TRIES;
    private boolean PRERENDER_FRAME;
    private final RectF actualDrawRect;
    private boolean applyTransformation;
    private AnimatedFileBuffer backgroundBuffer;
    private final Paint[] backgroundPaint;
    private final BitmapsCache bitmapsCache;
    Runnable cacheGenRunnable;
    AnimatedFileNative cacheGenerateDecoder;
    long cacheGenerateTimestamp;
    BitmapsCache.Metadata cacheMetadata;
    private Runnable cancelCache;
    private final int currentAccount;
    public long currentTime;
    private DispatchQueue decodeQueue;
    private boolean decodeSingleFrame;
    private boolean decoderCreated;
    private int decoderTryCount;
    private boolean destroyWhenDone;
    private final TLRPC.Document document;
    private final RectF dstRect;
    private final RectF[] dstRectBackground;
    private float endTime;
    private boolean forceDecodeAfterNextFrame;
    boolean generatingCache;
    Bitmap generatingCacheBitmap;
    private int invalidateAfter;
    private boolean invalidateParentViewWithSecond;
    private boolean invalidatePath;
    private boolean isChoreographerRegistered;
    private volatile boolean isPaused;
    private volatile boolean isRecycled;
    private boolean isRestarted;
    private volatile boolean isRunning;
    private boolean isStaticVideoDetected;
    public boolean isWebmSticker;
    private long lastFrameDecodeTime;
    private long lastFrameTime;
    int lastMetadata;
    private int lastTimeStamp;
    private boolean limitFps;
    private final Runnable loadFrameRunnable;
    private Runnable loadFrameTask;
    private final boolean loop;
    private volatile AnimatedFileNative mDecoder;
    private final Runnable mStartTask;
    private final Choreographer60FpsContent.FrameCallback mUiThreadChoreographerCallback;
    private final int[] metaData;
    private AnimatedFileBuffer nextRenderingBuffer;
    private AnimatedFileBuffer nextRenderingBuffer2;
    private WeakReference<View> parentView;
    private final ReferenceList<ImageReceiver> parents;
    private final File path;
    private boolean pendingRemoveLoading;
    private int pendingRemoveLoadingFramesReset;
    private volatile long pendingSeekTo;
    private volatile long pendingSeekToUI;
    private final boolean precache;
    private boolean ptrFail;
    private boolean recycleWithSecond;
    private AnimatedFileBuffer renderingBuffer;
    private int renderingHeight;
    private int renderingWidth;
    public int repeatCount;
    private final Path[] roundPath;
    private final int[] roundRadius;
    private int[] roundRadiusBackup;
    private float scaleFactor;
    private float scaleX;
    private float scaleY;
    private boolean scheduledForSeek;
    private final ReferenceList<View> secondParentViews;
    private final Matrix[] shaderMatrix;
    private boolean singleFrameDecoded;
    public boolean skipFrameUpdate;
    private float startTime;
    private AnimatedFileDrawableStream stream;
    private final long streamFileSize;
    private final int streamLoadingPriority;
    private boolean swapBuffersAllowedByChoreographer;
    private final Object sync;
    private int ticksWithoutDraw;
    int tryCount;
    private final Runnable uiRunnable;
    private final Runnable uiRunnableGenerateCache;
    private final Runnable uiRunnableNoFrame;
    private final ArrayList<AnimatedFileBuffer> unusedBuffers;
    private boolean useSharedQueue;

    public static /* synthetic */ void $r8$lambda$7XyqitCX1RPC1GaE8gQcWiyx12w(AnimatedFileDrawable r0) {
        r0.checkChoreographerInternal();
    }

    public static /* synthetic */ void $r8$lambda$7yr_MGVzyG_RDH3DSsvpseHOSjM(AnimatedFileDrawable r0) {
        r0.uiRunnableGenerateCacheImpl();
    }

    /* renamed from: $r8$lambda$F59r5AYSWCAUzMPOpIhjrE-Urn8, reason: not valid java name */
    public static /* synthetic */ void m8805$r8$lambda$F59r5AYSWCAUzMPOpIhjrEUrn8(AnimatedFileDrawable r0) {
        r0.lambda$checkCacheCancel$2();
    }

    public static /* synthetic */ void $r8$lambda$UuWqhbnk75slzAJLELsDjXgMRX0(AnimatedFileDrawable r0) {
        r0.uiRunnableImpl();
    }

    public static /* synthetic */ void $r8$lambda$V1fg5JaybgorNfsljQYZGNSQ8KY(AnimatedFileDrawable r0) {
        r0.uiRunnableNoFrameImpl();
    }

    public static /* synthetic */ void $r8$lambda$Z1wkJyQyzSdZwEpNAxmgDa3jMCk(AnimatedFileDrawable r0, long r1) {
        r0.onChoreographerFrame(r1);
    }

    public static /* synthetic */ void $r8$lambda$Zy1IJUlv58E_7pvbsfDgz0d4wPc(AnimatedFileDrawable r0) {
        r0.uiStartTaskImpl();
    }

    /* renamed from: $r8$lambda$bCe03kEQ-6qz8c1scP5kVFG1e0w, reason: not valid java name */
    public static /* synthetic */ void m8806$r8$lambda$bCe03kEQ6qz8c1scP5kVFG1e0w(AnimatedFileDrawable r0) {
        r0.lambda$uiRunnableGenerateCacheImpl$1();
    }

    public static /* synthetic */ void $r8$lambda$dEadfVNoMJaHtgpUGVFUtphuFEI(AnimatedFileDrawable r0) {
        r0.lambda$uiRunnableGenerateCacheImpl$0();
    }

    /* renamed from: $r8$lambda$pJ8twr51ciIjI-LINpmQ9jDGVok, reason: not valid java name */
    public static /* synthetic */ void m8807$r8$lambda$pJ8twr51ciIjILINpmQ9jDGVok(AnimatedFileDrawable r0) {
        r0.loadFrameRunnableImpl();
    }

    static {
        radii = new float[8];
        executor = new ScheduledThreadPoolExecutor(8, new ThreadPoolExecutor.DiscardPolicy());
        SRC_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    }

    public AnimatedFileDrawable(File r20, boolean r21, long r22, int r24, TLRPC.Document r25, ImageLocation r26, Object r27, long r28, int r30, boolean r31, int r32, int r33, BitmapsCache.CacheOptions r34) {
        if (r25 == null) goto L5;
        int r0 = 1;
    L6:
        this(r20, r21, r22, r24, r25, r26, r27, r28, r30, r31, r32, r33, r34, r0, true);
        return;
    L5:
        r0 = 0;
        goto L6
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r19v0, types: [android.graphics.drawable.BitmapDrawable, org.telegram.messenger.utils.BitmapsCache$Cacheable, org.telegram.ui.Components.AnimatedFileDrawable] */
    /* JADX WARN: Type inference failed for: r4v3, types: [boolean] */
    public AnimatedFileDrawable(File r20, boolean r21, long r22, int r24, TLRPC.Document r25, ImageLocation r26, Object r27, long r28, int r30, boolean r31, int r32, int r33, BitmapsCache.CacheOptions r34, int r35, boolean r36) {
        this.invalidateAfter = 50;
        int[] r3 = new int[8];
        this.metaData = r3;
        this.unusedBuffers = new ArrayList();
        this.pendingSeekTo = -1;
        this.pendingSeekToUI = -1;
        this.sync = new Object();
        this.actualDrawRect = new RectF();
        this.roundRadius = new int[4];
        this.shaderMatrix = new Matrix[3];
        this.roundPath = new Path[3];
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dstRect = new RectF();
        this.scaleFactor = 1.0f;
        this.dstRectBackground = new RectF[2];
        this.backgroundPaint = new Paint[2];
        this.secondParentViews = new ReferenceList(true);
        this.parents = new ReferenceList(true);
        this.invalidatePath = true;
        this.uiRunnableNoFrame = new AnimatedFileDrawable$$ExternalSyntheticLambda2(this);
        this.uiRunnableGenerateCache = new AnimatedFileDrawable$$ExternalSyntheticLambda3(this);
        this.uiRunnable = new AnimatedFileDrawable$$ExternalSyntheticLambda4(this);
        this.decoderTryCount = 0;
        this.MAX_TRIES = 15;
        this.loadFrameRunnable = new AnimatedFileDrawable$$ExternalSyntheticLambda5(this);
        this.mStartTask = new AnimatedFileDrawable$$ExternalSyntheticLambda6(this);
        this.mUiThreadChoreographerCallback = new AnimatedFileDrawable$$ExternalSyntheticLambda7(this);
        this.path = r20;
        this.PRERENDER_FRAME = SharedConfig.deviceIsAboveAverage();
        this.streamFileSize = r22;
        this.streamLoadingPriority = r24;
        this.currentAccount = r30;
        this.renderingHeight = r33;
        this.renderingWidth = r32;
        this.loop = r36;
        if (r34 == null) goto L7;
        if (r32 <= 0) goto L7;
        if (r33 <= 0) goto L7;
        boolean r1 = true;
    L8:
        this.precache = r1;
        this.document = r25;
        getPaint().setFlags(3);
        if (r22 == 0) goto L13;
        if (r25 != null) goto L14;
        if (r26 == null) goto L13;
    L14:
        boolean r2 = true;
        int r15 = 15;
        ?? r14 = 0;
        this.stream = new AnimatedFileDrawableStream(r25, r26, r27, r30, r31, r24, r35);
    L15:
        BitmapsCache r8 = null;
        if (r21 == false) goto L34;
        if (r1 == true) goto L34;
        boolean r16 = r1;
        boolean r10 = r2;
        int[] r23 = r3;
        this.mDecoder = AnimatedFileNative.createDecoderFrom(r20.getAbsolutePath(), r23, r30, r22, this.stream, r31);
        if (this.mDecoder == null) goto L21;
    L25:
        boolean r12 = r14;
    L26:
        this.ptrFail = r12;
        if (this.mDecoder != null) goto L29;
    L33:
        adaptRenderingSize();
        updateScaleFactor();
        this.decoderCreated = r10;
        ?? r102 = r10;
    L35:
        if (r16 == false) goto L52;
        this.mDecoder = AnimatedFileNative.createDecoderFrom(r20.getAbsolutePath(), r23, r30, r22, this.stream, r31);
        if (this.mDecoder == null) goto L39;
    L43:
        boolean r13 = r14;
    L44:
        this.ptrFail = r13;
        if (this.mDecoder != null) goto L47;
    L51:
        r8 = new BitmapsCache(r20, this, r34, this.renderingWidth, this.renderingHeight, (this.limitFps ? 1 : 0) ^ r102);
        goto L52
    L47:
        if (r23[r14] <= 3840) goto L49;
    L50:
        this.mDecoder.recycle();
        this.mDecoder = null;
        goto L52
    L49:
        if (r23[r102] <= 3840) goto L51;
    L39:
        if (this.isWebmSticker == true) goto L41;
    L42:
        r13 = r102;
        goto L44
    L41:
        if (this.decoderTryCount <= r15) goto L43;
    L52:
        this.bitmapsCache = r8;
        if (r28 == 0) goto L56;
        seekTo(r28, r14);
        return;
    L56:
        return;
    L29:
        if (r23[r14] <= 3840) goto L31;
    L32:
        this.mDecoder.recycle();
        this.mDecoder = null;
        goto L33
    L31:
        if (r23[r10 ? 1 : 0] <= 3840) goto L33;
    L21:
        if (this.isWebmSticker == true) goto L23;
    L24:
        r12 = r10 ? 1 : 0;
        goto L26
    L23:
        if (this.decoderTryCount <= r15) goto L25;
    L34:
        r16 = r1;
        r102 = r2;
        r23 = r3;
    L13:
        r2 = true;
        r15 = 15;
        r14 = 0;
    L7:
        r1 = false;
        goto L8
    }

    public AnimatedFileDrawable(File r17, boolean r18, long r19, int r21, TLRPC.Document r22, ImageLocation r23, Object r24, long r25, int r27, boolean r28, BitmapsCache.CacheOptions r29) {
        this(r17, r18, r19, r21, r22, r23, r24, r25, r27, r28, 0, 0, r29);
    }

    private void adaptRenderingSize() {
        if (this.renderingWidth == 0) goto L5;
        return;
    L5:
        if (this.renderingHeight != 0) goto L19;
        int[] r0 = this.metaData;
        int r1 = r0[0];
        if (r1 > 3000) goto L16;
        int r4 = r0[1];
        if (r4 > 3000) goto L16;
        if (r1 > 2200) goto L14;
        if (r4 > 2200) goto L14;
        return;
    L14:
        this.renderingWidth = r1 / 2;
        this.renderingHeight = r4 / 2;
        return;
    L16:
        this.renderingWidth = r1 / 4;
        this.renderingHeight = r0[1] / 4;
        return;
    }

    private boolean canLoadFrames() {
        if (this.precache == false) goto L9;
        if (this.bitmapsCache == null) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (this.mDecoder == null) goto L11;
    L14:
        return true;
    L11:
        if (this.decoderCreated == false) goto L14;
        return false;
    }

    private void checkChoreographer() {
        AndroidUtilities.executeOnUIThread(new AnimatedFileDrawable$$ExternalSyntheticLambda0(this));
    }

    private void checkChoreographerAfterDrawCall() {
        this.ticksWithoutDraw = 0;
        if (this.isPaused == false) goto L6;
        this.isPaused = false;
        checkChoreographer();
        return;
    }

    private void checkChoreographerAfterFrameCall() {
        int r0 = this.ticksWithoutDraw + 1;
        this.ticksWithoutDraw = r0;
        if (r0 <= 10) goto L5;
        this.isPaused = true;
    L5:
        checkChoreographerInternal();
    }

    private void checkChoreographerInternal() {
        if (this.isRunning == false) goto L16;
        if (this.isPaused == true) goto L16;
        if (this.isStaticVideoDetected == true) goto L16;
        if (this.isChoreographerRegistered == true) goto L19;
        int r0 = this.metaData[5];
        if (r0 <= 0) goto L21;
        activeChoreographersCount++;
        this.isChoreographerRegistered = true;
        this.ticksWithoutDraw = 0;
        Choreographer60FpsContent.getInstance().addFrameCallback(this.mUiThreadChoreographerCallback, r0);
        return;
    L21:
        return;
    L19:
        return;
    L16:
        if (this.isChoreographerRegistered == false) goto L20;
        activeChoreographersCount--;
        this.isChoreographerRegistered = false;
        this.ticksWithoutDraw = 0;
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.mUiThreadChoreographerCallback);
        return;
    }

    private void chekDestroyDecoder() {
        if (this.loadFrameTask != null) goto L12;
        if (this.destroyWhenDone == false) goto L12;
        if (this.mDecoder == null) goto L12;
        if (this.generatingCache == true) goto L12;
        this.mDecoder.recycle();
        this.mDecoder = null;
    L12:
        if (canLoadFrames() == true) goto L29;
        AnimatedFileBuffer r0 = this.renderingBuffer;
        if (r0 == null) goto L16;
        r0.recycle();
        this.renderingBuffer = null;
    L16:
        AnimatedFileBuffer r02 = this.backgroundBuffer;
        if (r02 == null) goto L19;
        r02.recycle();
        this.backgroundBuffer = null;
    L19:
        DispatchQueue r03 = this.decodeQueue;
        if (r03 == null) goto L22;
        r03.recycle();
        this.decodeQueue = null;
    L22:
        int r04 = 0;
    L23:
        int r1 = this.unusedBuffers.size();
        ArrayList<AnimatedFileBuffer> r2 = this.unusedBuffers;
        if (r04 >= r1) goto L26;
        r2.get(r04).recycle();
        r04 = r04 + 1;
        goto L23
    L26:
        r2.clear();
        invalidateInternal();
        return;
    }

    private void drawBitmap(RectF r4, Paint r5, Canvas r6, float r7, float r8) {
        r6.save();
        r6.translate(r4.left, r4.top);
        int r0 = this.metaData[2];
        if (r0 != 90) goto L6;
        r6.rotate(90.0f);
        r6.translate(0.0f, -r4.width());
    L11:
        r6.scale(r7, r8);
        r6.drawBitmap(this.renderingBuffer.bitmap, 0.0f, 0.0f, r5);
        r6.restore();
        return;
    L6:
        if (r0 != 180) goto L9;
        r6.rotate(180.0f);
        r6.translate(-r4.width(), -r4.height());
        goto L11
    L9:
        if (r0 != 270) goto L11;
        r6.rotate(270.0f);
        r6.translate(-r4.height(), 0.0f);
        goto L11
    }

    private boolean hasRoundRadius() {
        int[] r4 = this.roundRadius;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] != 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private boolean isRoundRadiusSame() {
        int[] r4 = this.roundRadius;
        int r1 = r4[0];
        int r3 = r4[1];
        if (r1 != r3) goto L9;
        int r12 = r4[2];
        if (r3 != r12) goto L9;
        if (r12 != r4[3]) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$checkCacheCancel$2() {
        BitmapsCache r0 = this.bitmapsCache;
        if (r0 == null) goto L6;
        r0.cancelCreate();
        return;
    }

    private /* synthetic */ void lambda$uiRunnableGenerateCacheImpl$0() {
        if (this.cacheGenRunnable == null) goto L5;
        BitmapsCache.decrementTaskCounter();
        this.cacheGenRunnable = null;
    L5:
        this.generatingCache = false;
        chekDestroyDecoder();
        scheduleNextGetFrame();
    }

    private /* synthetic */ void lambda$uiRunnableGenerateCacheImpl$1() {
        this.bitmapsCache.createCache();
        AndroidUtilities.runOnUIThread(new AnimatedFileDrawable$$ExternalSyntheticLambda9(this));
    }

    private void loadFrameRunnableImpl() {
        if (this.isRecycled == false) goto L6;
        AndroidUtilities.runOnUIThread(this.uiRunnable);
        return;
    L6:
        boolean r1 = false;
        if (this.decoderCreated == false) goto L9;
    L123:
    L42:
        th = move-exception;
        FileLog.e(th);
    L116:
        AndroidUtilities.runOnUIThread(this.uiRunnable);
        return;
    L36:
        if (this.bitmapsCache == null) goto L62;
        if (this.backgroundBuffer != null) goto L46;
        if (this.unusedBuffers.isEmpty() == true) goto L44;
        this.backgroundBuffer = this.unusedBuffers.remove(0);     // Catch: Throwable -> L42
        goto L46
    L44:
        this.backgroundBuffer = AnimatedFileBuffer.of(this.renderingWidth, this.renderingHeight);     // Catch: Throwable -> L42
    L46:
        if (this.cacheMetadata != null) goto L48;
        this.cacheMetadata = new BitmapsCache.Metadata();     // Catch: Throwable -> L42
    L48:
        this.lastFrameDecodeTime = System.currentTimeMillis();     // Catch: Throwable -> L42
        BitmapsCache.Metadata r0 = this.cacheMetadata;     // Catch: Throwable -> L42
        int r4 = r0.frame;     // Catch: Throwable -> L42
        int r02 = this.bitmapsCache.getFrame(this.backgroundBuffer.bitmap, r0);     // Catch: Throwable -> L42
        if (r02 != (-1)) goto L51;
    L53:
        int[] r42 = this.metaData;     // Catch: Throwable -> L42
        AnimatedFileBuffer r6 = this.backgroundBuffer;     // Catch: Throwable -> L42
        int r7 = this.cacheMetadata.frame * Math.max(16, r42[4] / Math.max(1, this.bitmapsCache.getFrameCount()));     // Catch: Throwable -> L42
        r6.time = r7;     // Catch: Throwable -> L42
        r42[3] = r7;     // Catch: Throwable -> L42
        this.backgroundBuffer.opaque = false;     // Catch: Throwable -> L42
        if (this.bitmapsCache.needGenCache() == false) goto L56;
        AndroidUtilities.runOnUIThread(this.uiRunnableGenerateCache);     // Catch: Throwable -> L42
    L56:
        if (r02 != (-1)) goto L59;
        AndroidUtilities.runOnUIThread(this.uiRunnableNoFrame);     // Catch: Throwable -> L42
        return;
    L59:
        AndroidUtilities.runOnUIThread(this.uiRunnable);     // Catch: Throwable -> L42
        return;
    L51:
        if (this.cacheMetadata.frame >= r4) goto L53;
        this.isRestarted = true;     // Catch: Throwable -> L42
        goto L53
    L62:
        if (this.mDecoder != null) goto L71;
        int[] r03 = this.metaData;     // Catch: Throwable -> L42
        if (r03[0] == 0) goto L71;
        if (r03[1] == 0) goto L71;
        AndroidUtilities.runOnUIThread(this.uiRunnableNoFrame);     // Catch: Throwable -> L42
        return;
    L71:
        if (this.backgroundBuffer != null) goto L85;
        int[] r04 = this.metaData;     // Catch: Throwable -> L42
        if (r04[0] <= 0) goto L85;
        if (r04[1] <= 0) goto L85;
    L79:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L42
        goto L85
    L77:
        if (this.unusedBuffers.isEmpty() == true) goto L81;
        this.backgroundBuffer = this.unusedBuffers.remove(0);     // Catch: Throwable -> L79
    L118:
    L81:
        float r43 = this.metaData[0];     // Catch: Throwable -> L79
        float r5 = this.scaleFactor;     // Catch: Throwable -> L79
        this.backgroundBuffer = AnimatedFileBuffer.of((int) (r43 * r5), (int) (r0[1] * r5));     // Catch: Throwable -> L79
    L85:
        if (this.pendingSeekTo < 0) goto L100;
        this.metaData[3] = (int) this.pendingSeekTo;     // Catch: Throwable -> L42
        long r05 = this.pendingSeekTo;     // Catch: Throwable -> L42
        Object r44 = this.sync;     // Catch: Throwable -> L42
        monitor-enter(r44);     // Catch: Throwable -> L42
        this.pendingSeekTo = -1;     // Catch: Throwable -> L96
        monitor-exit(r44);     // Catch: Throwable -> L96
        AnimatedFileDrawableStream r45 = this.stream;     // Catch: Throwable -> L42
        if (r45 == null) goto L94;
        r45.reset();     // Catch: Throwable -> L42
    L94:
        this.mDecoder.seekToMs(r05, true);     // Catch: Throwable -> L42
        r1 = true;
        goto L100
    L96:
        th = move-exception;
        throw th;     // Catch: Throwable -> L42
    L100:
        if (this.backgroundBuffer == null) goto L116;
        this.lastFrameDecodeTime = System.currentTimeMillis();     // Catch: Throwable -> L42
        if (this.mDecoder.getVideoFrame(this.backgroundBuffer.bitmap, false, this.startTime, this.endTime, this.loop) != 0) goto L106;
        AndroidUtilities.runOnUIThread(this.uiRunnableNoFrame);     // Catch: Throwable -> L42
        return;
    L106:
        if (this.isStaticVideoDetected == true) goto L108;
        this.isStaticVideoDetected = this.mDecoder.isStaticVideoDetected();     // Catch: Throwable -> L42
    L108:
        int r06 = this.metaData[3];     // Catch: Throwable -> L42
        if (r06 >= this.lastTimeStamp) goto L111;
        this.isRestarted = true;     // Catch: Throwable -> L42
    L111:
        if (r1 == false) goto L113;
        this.lastTimeStamp = r06;     // Catch: Throwable -> L42
    L113:
        AnimatedFileBuffer r12 = this.backgroundBuffer;     // Catch: Throwable -> L42
        r12.time = r06;     // Catch: Throwable -> L42
        r12.opaque = this.mDecoder.isLastFrameOpaque();     // Catch: Throwable -> L42
        goto L116
    L9:
        if (this.mDecoder != null) goto L123;
        this.mDecoder = AnimatedFileNative.createDecoderFrom(this.path.getAbsolutePath(), this.metaData, this.currentAccount, this.streamFileSize, this.stream, false);
        if (this.mDecoder == null) goto L13;
    L17:
        boolean r07 = false;
    L18:
        this.ptrFail = r07;
        if (this.mDecoder == null) goto L25;
        int[] r08 = this.metaData;
        if (r08[0] <= 3840) goto L23;
    L24:
        this.mDecoder.recycle();
        this.mDecoder = null;
        goto L25
    L23:
        if (r08[1] > 3840) goto L24;
    L25:
        adaptRenderingSize();
        updateScaleFactor();
        if (this.isWebmSticker == true) goto L28;
    L33:
        boolean r09 = true;
    L34:
        this.decoderCreated = r09;
        AndroidUtilities.runOnUIThread(new AnimatedFileDrawable$$ExternalSyntheticLambda0(this));
        goto L123
    L28:
        if (this.mDecoder != null) goto L33;
        int r010 = this.decoderTryCount;
        this.decoderTryCount = r010 + 1;
        if (r010 > 15) goto L33;
        r09 = false;
        goto L34
    L13:
        if (this.isWebmSticker == true) goto L15;
    L16:
        r07 = true;
        goto L18
    L15:
        if (this.decoderTryCount <= 15) goto L17;
        goto L16
    }

    private void onChoreographerFrame(long r1) {
        checkChoreographerAfterFrameCall();
        if (this.isChoreographerRegistered == false) goto L6;
        this.swapBuffersAllowedByChoreographer = true;
        invalidateInternal();
        return;
    }

    private void scheduleNextGetFrame() {
        scheduleNextGetFrame(true, false);
    }

    private void scheduleNextGetFrame(boolean r5, boolean r6) {
        if (this.loadFrameTask == null) goto L6;
        if (r6 == true) goto L6;
        return;
    L6:
        if (this.PRERENDER_FRAME == false) goto L14;
        if (this.nextRenderingBuffer2 == null) goto L16;
        if (this.scheduledForSeek == true) goto L14;
        if (this.pendingSeekToUI < 0) goto L14;
    L16:
        if (this.renderingBuffer == null) goto L20;
        if (this.isStaticVideoDetected == false) goto L20;
        return;
    L20:
        if (canLoadFrames() == true) goto L22;
        return;
    L22:
        if (this.destroyWhenDone == false) goto L24;
        return;
    L24:
        if (this.isRunning == true) goto L30;
        if (this.decodeSingleFrame == true) goto L28;
        return;
    L28:
        if (this.singleFrameDecoded == false) goto L30;
        return;
    L30:
        if (this.parents.isEmpty() == false) goto L32;
        return;
    L32:
        if (this.generatingCache == false) goto L35;
        return;
    L35:
        if (this.useSharedQueue == false) goto L45;
        if (this.limitFps == false) goto L39;
        Runnable r52 = this.loadFrameRunnable;
        this.loadFrameTask = r52;
        DispatchQueuePoolBackground.execute(r52);
    L52:
        this.scheduledForSeek = true;
        return;
    L39:
        if (r6 == false) goto L43;
        Runnable r53 = this.loadFrameTask;
        if (r53 == null) goto L43;
        executor.remove(r53);
    L43:
        ScheduledThreadPoolExecutor r54 = executor;
        Runnable r62 = this.loadFrameRunnable;
        this.loadFrameTask = r62;
        r54.execute(r62);
        goto L52
    L45:
        if (this.decodeQueue != null) goto L47;
        this.decodeQueue = new DispatchQueue("decodeQueue" + this);
    L47:
        if (r6 == false) goto L51;
        Runnable r55 = this.loadFrameTask;
        if (r55 == null) goto L51;
        this.decodeQueue.cancelRunnable(r55);
    L51:
        DispatchQueue r56 = this.decodeQueue;
        Runnable r63 = this.loadFrameRunnable;
        this.loadFrameTask = r63;
        r56.postRunnable(r63, 0);
    L14:
        if (this.nextRenderingBuffer == null) goto L16;
    }

    private void swapBuffers(long r3) {
        AnimatedFileBuffer r0 = this.renderingBuffer;
        if (r0 == null) goto L5;
        this.unusedBuffers.add(r0);
    L5:
        this.renderingBuffer = this.nextRenderingBuffer;
        this.nextRenderingBuffer = this.nextRenderingBuffer2;
        this.nextRenderingBuffer2 = null;
        this.lastFrameTime = r3;
        this.swapBuffersAllowedByChoreographer = false;
    }

    private void uiRunnableGenerateCacheImpl() {
        if (this.isRecycled == false) goto L5;
        return;
    L5:
        if (this.destroyWhenDone == false) goto L7;
        return;
    L7:
        if (this.generatingCache == false) goto L9;
        return;
    L9:
        if (this.cacheGenRunnable != null) goto L18;
        this.startTime = System.currentTimeMillis();
        if (RLottieDrawable.lottieCacheGenerateQueue != null) goto L13;
        RLottieDrawable.createCacheGenQueue();
    L13:
        this.generatingCache = true;
        this.loadFrameTask = null;
        BitmapsCache.incrementTaskCounter();
        DispatchQueue r0 = RLottieDrawable.lottieCacheGenerateQueue;
        AnimatedFileDrawable$$ExternalSyntheticLambda8 r1 = new AnimatedFileDrawable$$ExternalSyntheticLambda8(this);
        this.cacheGenRunnable = r1;
        r0.postRunnable(r1);
        return;
    }

    private void uiRunnableImpl() {
        this.loadFrameTask = null;
        chekDestroyDecoder();
        if (this.stream != null) goto L5;
    L7:
        int r1 = this.pendingRemoveLoadingFramesReset;
        if (r1 > 0) goto L10;
        this.pendingRemoveLoading = true;
    L12:
        if (this.forceDecodeAfterNextFrame == true) goto L14;
        this.singleFrameDecoded = true;
    L16:
        if (this.pendingSeekToUI < 0) goto L19;
        this.nextRenderingBuffer = this.backgroundBuffer;
        this.nextRenderingBuffer2 = null;
    L29:
        this.backgroundBuffer = null;
        if (this.isRestarted == false) goto L32;
        this.isRestarted = false;
        this.repeatCount++;
        checkRepeat();
    L32:
        int r0 = this.metaData[3];
        if (r0 >= this.lastTimeStamp) goto L39;
        float r3 = this.startTime;
        if (r3 <= 0.0f) goto L37;
        int r32 = (int) (r3 * 1000.0f);
    L38:
        this.lastTimeStamp = r32;
        goto L39
    L37:
        r32 = 0;
    L39:
        int r33 = this.lastTimeStamp;
        if ((r0 - r33) == 0) goto L47;
        int r02 = r0 - r33;
        this.invalidateAfter = r02;
        if (this.limitFps == false) goto L47;
        if (r02 >= 32) goto L47;
        this.invalidateAfter = 32;
    L47:
        if (this.pendingSeekToUI >= 0) goto L49;
    L51:
        this.lastTimeStamp = this.metaData[3];
        Iterator<View> r03 = this.secondParentViews.iterator();
    L53:
        if (r03.hasNext() == false) goto L56;
        r03.next().invalidate();
        goto L53
    L56:
        if (this.isRunning == true) goto L60;
        if (this.decodeSingleFrame == false) goto L60;
    L63:
        invalidateInternal();
    L64:
        scheduleNextGetFrame();
        return;
    L60:
        if (this.renderingBuffer != null) goto L64;
        if (this.nextRenderingBuffer == null) goto L64;
    L49:
        if (this.pendingSeekTo != (-1)) goto L51;
        this.pendingSeekToUI = -1;
        this.invalidateAfter = 0;
        goto L51
    L19:
        if (this.PRERENDER_FRAME == true) goto L21;
        this.nextRenderingBuffer = this.backgroundBuffer;
        goto L29
    L21:
        AnimatedFileBuffer r12 = this.nextRenderingBuffer;
        if (r12 == null) goto L24;
    L26:
        if (r12 != null) goto L28;
        this.nextRenderingBuffer = this.nextRenderingBuffer2;
        this.nextRenderingBuffer2 = this.backgroundBuffer;
        goto L29
    L28:
        this.nextRenderingBuffer2 = this.backgroundBuffer;
        goto L29
    L24:
        if (this.nextRenderingBuffer2 != null) goto L26;
        this.nextRenderingBuffer = this.backgroundBuffer;
        goto L29
    L14:
        this.forceDecodeAfterNextFrame = false;
        goto L16
    L10:
        this.pendingRemoveLoadingFramesReset = r1 - 1;
        goto L12
    L5:
        if (this.pendingRemoveLoading == false) goto L7;
        FileLoader.getInstance(this.currentAccount).removeLoadingVideo(this.stream.getDocument(), false, false);
        goto L7
    }

    private void uiRunnableNoFrameImpl() {
        this.loadFrameTask = null;
        chekDestroyDecoder();
        if (this.pendingSeekToUI >= 0) goto L5;
    L7:
        scheduleNextGetFrame();
        invalidateInternal();
        return;
    L5:
        if (this.pendingSeekTo != (-1)) goto L7;
        this.pendingSeekToUI = -1;
        this.invalidateAfter = 0;
        goto L7
    }

    private void uiStartTaskImpl() {
        Iterator<View> r0 = this.secondParentViews.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().invalidate();
        goto L4
    L6:
        WeakReference<View> r02 = this.parentView;
        if (r02 == null) goto L9;
        View r03 = r02.get();
    L11:
        if (this.secondParentViews.isEmpty() == false) goto L13;
    L14:
        if (r03 == null) goto L19;
        r03.invalidate();
        return;
    L19:
        return;
    L13:
        if (this.invalidateParentViewWithSecond == true) goto L14;
        return;
    L9:
        r03 = null;
        goto L11
    }

    private void updateCurrentFrameInternal(long r5, boolean r7) {
        if (this.swapBuffersAllowedByChoreographer == false) goto L5;
    L10:
        boolean r72 = true;
    L12:
        if (this.isRunning == false) goto L30;
        AnimatedFileBuffer r0 = this.renderingBuffer;
        if (r0 != null) goto L20;
        if (this.nextRenderingBuffer != null) goto L20;
        scheduleNextGetFrame();
        return;
    L20:
        if (this.nextRenderingBuffer == null) goto L38;
        if (r0 == null) goto L27;
        if (r72 == true) goto L24;
        return;
    L24:
        if (this.skipFrameUpdate == false) goto L26;
        return;
    L26:
        if (this.pendingSeekToUI < 0) goto L27;
        return;
    L27:
        swapBuffers(r5);
        scheduleNextGetFrame();
        return;
    L38:
        return;
    L30:
        if (this.isRunning == false) goto L32;
        return;
    L32:
        if (this.decodeSingleFrame == false) goto L43;
        if (r72 == true) goto L35;
        return;
    L35:
        if (this.nextRenderingBuffer == null) goto L45;
        swapBuffers(r5);
        scheduleNextGetFrame();
        return;
    L45:
        return;
    L43:
        return;
    L5:
        if (this.isRunning == false) goto L7;
    L9:
        r72 = false;
        goto L12
    L7:
        if (this.decodeSingleFrame == false) goto L9;
        goto L9
    }

    private void updateScaleFactor() {
        if (this.isWebmSticker == true) goto L20;
        int r0 = this.renderingHeight;
        if (r0 <= 0) goto L20;
        int r2 = this.renderingWidth;
        if (r2 <= 0) goto L20;
        int[] r3 = this.metaData;
        int r4 = r3[0];
        if (r4 <= 0) goto L20;
        int r32 = r3[1];
        if (r32 <= 0) goto L20;
        float r02 = Math.max(r2 / r4, r0 / r32);
        this.scaleFactor = r02;
        if (r02 > 0.0f) goto L15;
    L18:
        this.scaleFactor = 1.0f;
        return;
    L15:
        if (r02 > 0.7d) goto L18;
        return;
    L20:
        this.scaleFactor = 1.0f;
    }

    public void addParent(ImageReceiver r2) {
        if (r2 != null) goto L4;
    L8:
        checkCacheCancel();
        return;
    L4:
        if (this.parents.add(r2) == false) goto L8;
        if (this.isRunning == false) goto L8;
        scheduleNextGetFrame();
        goto L8
    }

    public void addSecondParentView(View r1) {
        if (r1 == null) goto L5;
        this.secondParentViews.add(r1);
        return;
    }

    public void checkCacheCancel() {
        if (this.bitmapsCache == null) goto L18;
        boolean r0 = this.parents.isEmpty();
        if (r0 == true) goto L8;
    L11:
        if (r0 == true) goto L16;
        Runnable r02 = this.cancelCache;
        if (r02 == null) goto L17;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.cancelCache = null;
        return;
    L17:
        return;
    L16:
        return;
    L8:
        if (this.cancelCache != null) goto L11;
        AnimatedFileDrawable$$ExternalSyntheticLambda1 r03 = new AnimatedFileDrawable$$ExternalSyntheticLambda1(this);
        this.cancelCache = r03;
        AndroidUtilities.runOnUIThread(r03, 600);
        return;
    }

    public void checkRepeat() {
        Iterator<ImageReceiver> r0 = this.parents.iterator();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r0.hasNext() == false) goto L14;
        ImageReceiver r3 = r0.next();
        if (r3.isAttachedToWindow() == true) goto L8;
        this.parents.remove(r3);
    L9:
        int r32 = r3.animatedFileDrawableRepeatMaxCount;
        if (r32 <= 0) goto L4;
        if (this.repeatCount < r32) goto L4;
        r2 = r2 + 1;
        goto L4
    L8:
        r1 = r1 + 1;
        goto L9
    L14:
        if (r1 != r2) goto L17;
        stop();
        return;
    L17:
        start();
    }

    public boolean decoderFailed() {
        if (this.decoderCreated == true) goto L5;
        return false;
    L5:
        if (this.ptrFail == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas r7) {
        drawInternal(r7, false, System.currentTimeMillis(), 0);
    }

    public void drawInBackground(Canvas r7, float r8, float r9, float r10, float r11, int r12, ColorFilter r13, int r14) {
        RectF[] r0 = this.dstRectBackground;
        if (r0[r14] != null) goto L5;
        r0[r14] = new RectF();
        this.backgroundPaint[r14] = new Paint();
        this.backgroundPaint[r14].setFilterBitmap(true);
    L5:
        this.backgroundPaint[r14].setAlpha(r12);
        this.backgroundPaint[r14].setColorFilter(r13);
        this.dstRectBackground[r14].set(r8, r9, r10 + r8, r11 + r9);
        drawInternal(r7, true, 0, r14);
    }

    public void drawInternal(Canvas r17, boolean r18, long r19, int r21) {
        if (canLoadFrames() == true) goto L5;
        return;
    L5:
        if (this.destroyWhenDone == false) goto L8;
        return;
    L8:
        if (r19 != 0) goto L10;
        long r1 = System.currentTimeMillis();
    L11:
        if (r18 == false) goto L13;
        RectF r4 = this.dstRectBackground[r21];
    L14:
        if (r18 == false) goto L16;
        Paint r5 = this.backgroundPaint[r21];
    L18:
        if (r18 == true) goto L21;
        updateCurrentFrame(r1, false);
    L21:
        if (this.renderingBuffer == null) goto L96;
        boolean r12 = hasRoundRadius();
        if (r18 == true) goto L35;
        if (r12 == false) goto L27;
    L31:
        Xfermode r2 = null;
    L33:
        if (r5.getXfermode() == r2) goto L35;
        r5.setXfermode(r2);
        goto L35
    L27:
        if (this.renderingBuffer.opaque == false) goto L31;
        if (r5.getAlpha() != 255) goto L31;
        r2 = SRC_XFERMODE;
    L35:
        float r22 = this.scaleX;
        float r7 = this.scaleY;
        if (r18 == false) goto L44;
        AnimatedFileBuffer r23 = this.renderingBuffer;
        int r72 = r23.width;
        int r24 = r23.height;
        int r11 = this.metaData[2];
        if (r11 == 90) goto L40;
        if (r11 == 270) goto L40;
    L41:
        float r73 = r4.width() / r72;
        float r25 = r4.height() / r24;
    L42:
        float r15 = r73;
        r7 = r25;
        r22 = r15;
    L50:
        if (r12 == false) goto L91;
        if (r18 == false) goto L53;
        int r13 = r21 + 1;
    L54:
        BitmapShader r112 = this.renderingBuffer.getShader(r13);
        r5.setShader(r112);
        Matrix[] r122 = this.shaderMatrix;
        Matrix r132 = r122[r13];
        if (r132 != null) goto L57;
        r132 = new Matrix();
        r122[r13] = r132;
    L57:
        r132.reset();
        r132.setTranslate(r4.left, r4.top);
        int r10 = this.metaData[2];
        if (r10 != 90) goto L61;
        r132.preRotate(90.0f);
        r132.preTranslate(0.0f, -r4.width());
    L65:
        r132.preScale(r22, r7);
        r112.setLocalMatrix(r132);
        Path[] r26 = this.roundPath;
        Path r74 = r26[r13];
        if (r74 != null) goto L69;
        r74 = new Path();
        r26[r13] = r74;
    L69:
        if (this.invalidatePath == true) goto L71;
        if (r18 == true) goto L71;
    L83:
        if (isRoundRadiusSame() == false) goto L89;
        if (r18 == true) goto L87;
        r4 = this.actualDrawRect;
    L87:
        int r0 = this.roundRadius[0];
        r17.drawRoundRect(r4, r0, r0, r5);
        return;
    L89:
        r17.drawPath(r74, r5);
        return;
    L71:
        if (r18 == true) goto L73;
        this.invalidatePath = false;
    L73:
        int r14 = 0;
    L74:
        int[] r27 = this.roundRadius;
        if (r14 >= r27.length) goto L77;
        float[] r8 = radii;
        int r9 = r14 * 2;
        int r28 = r27[r14];
        r8[r9] = r28;
        r8[r9 + 1] = r28;
        r14 = r14 + 1;
        goto L74
    L77:
        r74.rewind();
        if (r18 == false) goto L80;
        RectF r16 = r4;
    L81:
        r74.addRoundRect(r16, radii, Path.Direction.CW);
        goto L83
    L80:
        r16 = this.actualDrawRect;
        goto L81
    L61:
        if (r10 != 180) goto L63;
        r132.preRotate(180.0f);
        r132.preTranslate(-r4.width(), -r4.height());
        goto L65
    L63:
        if (r10 != 270) goto L65;
        r132.preRotate(270.0f);
        r132.preTranslate(-r4.height(), 0.0f);
        goto L65
    L53:
        r13 = 0;
        goto L54
    L91:
        drawBitmap(r4, r5, r17, r22, r7);
        return;
    L40:
        r72 = r24;
        r24 = r72;
        goto L41
    L44:
        if (this.applyTransformation == false) goto L50;
        AnimatedFileBuffer r29 = this.renderingBuffer;
        int r75 = r29.width;
        int r210 = r29.height;
        int r113 = this.metaData[2];
        if (r113 == 90) goto L48;
        if (r113 == 270) goto L48;
    L49:
        r4.set(getBounds());
        r73 = r4.width() / r75;
        this.scaleX = r73;
        r25 = r4.height() / r210;
        this.scaleY = r25;
        this.applyTransformation = false;
    L48:
        r75 = r210;
        r210 = r75;
        goto L49
    L96:
        return;
    L16:
        r5 = getPaint();
        goto L18
    L13:
        r4 = this.dstRect;
        goto L14
    L10:
        r1 = r19;
        goto L11
    }

    public int estimateSizeInCache() {
        return Math.max(getIntrinsicWidth() * getIntrinsicHeight(), this.renderingWidth * this.renderingHeight) * 12;
    }

    public void finalize() {
        this.secondParentViews.clear();     // Catch: Throwable -> L5
        recycle();     // Catch: Throwable -> L5
        super.finalize();
        return;
    L5:
        th = move-exception;
        super.finalize();
        throw th;
    }

    public Bitmap getAnimatedBitmap() {
        AnimatedFileBuffer r0 = this.renderingBuffer;
        if (r0 != null) goto L5;
        AnimatedFileBuffer r02 = this.nextRenderingBuffer;
        if (r02 != null) goto L9;
        AnimatedFileBuffer r1 = this.nextRenderingBuffer2;
        if (r1 != null) goto L13;
        return null;
    L13:
        return r1.bitmap;
    L9:
        return r02.bitmap;
    L5:
        return r0.bitmap;
    }

    public Bitmap getBackgroundBitmap() {
        AnimatedFileBuffer r0 = this.backgroundBuffer;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.bitmap;
    }

    public float getCurrentProgress() {
        if (this.metaData[4] != 0) goto L7;
        return 0.0f;
    L7:
        if (this.pendingSeekToUI < 0) goto L11;
        float r0 = this.pendingSeekToUI;
        int r6 = this.metaData[4];
    L10:
        return r0 / r6;
    L11:
        int[] r62 = this.metaData;
        r0 = r62[3];
        r6 = r62[4];
        goto L10
    }

    public int getCurrentProgressMs() {
        if (this.pendingSeekToUI >= 0) goto L5;
        AnimatedFileBuffer r0 = this.nextRenderingBuffer;
        if (r0 == null) goto L11;
        int r02 = r0.time;
        if (r02 == 0) goto L11;
        return r02;
    L11:
        AnimatedFileBuffer r4 = this.renderingBuffer;
        if (r4 != null) goto L14;
        return 0;
    L14:
        return r4.time;
    L5:
        return (int) this.pendingSeekToUI;
    }

    public int getDurationMs() {
        return this.metaData[4];
    }

    public Bitmap getFirstFrame(Bitmap r18) {
        if (r18 != null) goto L5;
        Bitmap r1 = Bitmap.createBitmap(this.renderingWidth, this.renderingHeight, Bitmap.Config.ARGB_8888);
    L6:
        Canvas r2 = new Canvas(r1);
        AnimatedFileNative r11 = AnimatedFileNative.createDecoderFrom(this.path.getAbsolutePath(), this.metaData, this.currentAccount, this.streamFileSize, this.stream, false);
        if (r11 != null) goto L10;
        return r1;
    L10:
        if (this.generatingCacheBitmap != null) goto L12;
        this.generatingCacheBitmap = Bitmap.createBitmap(Math.max(1, this.metaData[0]), Math.max(1, this.metaData[1]), Bitmap.Config.ARGB_8888);
    L12:
        r11.getVideoFrame(this.generatingCacheBitmap, false, this.startTime, this.endTime, true);
        r11.recycle();
        r1.eraseColor(0);
        r2.save();
        float r3 = this.renderingWidth / this.generatingCacheBitmap.getWidth();
        r2.scale(r3, r3);
        r2.drawBitmap(this.generatingCacheBitmap, 0.0f, 0.0f, null);
        r2.restore();
        return r1;
    L5:
        r1 = r18;
        goto L6
    }

    public int getFps() {
        return this.metaData[5];
    }

    public Bitmap getFrameAtTime(long r2) {
        return getFrameAtTime(r2, false);
    }

    public Bitmap getFrameAtTime(long r11, boolean r13) {
        if (this.decoderCreated == true) goto L5;
    L19:
        return null;
    L5:
        if (this.mDecoder == null) goto L19;
        AnimatedFileDrawableStream r0 = this.stream;
        if (r0 == null) goto L10;
        r0.cancel(false);
        this.stream.reset();
    L10:
        if (r13 == true) goto L12;
        this.mDecoder.seekToMs(r11, r13);
    L12:
        int[] r02 = this.metaData;
        Bitmap r5 = Bitmap.createBitmap(r02[0], r02[1], Bitmap.Config.ARGB_8888);
        AnimatedFileNative r4 = this.mDecoder;
        if (r13 == false) goto L15;
        int r10 = r4.getFrameAtTime(r11, r5);
    L16:
        if (r10 == 0) goto L18;
        return r5;
    L18:
        r5.recycle();
        goto L19
    L15:
        r10 = r4.getVideoFrame(r5, true, 0.0f, 0.0f, true);
        goto L16
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int r1 = 0;
        if (this.decoderCreated == false) goto L11;
        int[] r0 = this.metaData;
        int r2 = r0[2];
        if (r2 != 90) goto L7;
    L10:
        r1 = r0[0];
        goto L11
    L7:
        if (r2 == 270) goto L10;
        r1 = r0[1];
    L11:
        if (r1 != 0) goto L15;
        return AndroidUtilities.dp(100.0f);
    L15:
        return (int) (r1 * this.scaleFactor);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int r1 = 0;
        if (this.decoderCreated == false) goto L11;
        int[] r0 = this.metaData;
        int r2 = r0[2];
        if (r2 != 90) goto L7;
    L10:
        r1 = r0[1];
        goto L11
    L7:
        if (r2 == 270) goto L10;
        r1 = r0[0];
    L11:
        if (r1 != 0) goto L15;
        return AndroidUtilities.dp(100.0f);
    L15:
        return (int) (r1 * this.scaleFactor);
    }

    public long getLastFrameTimestamp() {
        return this.lastTimeStamp;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        int r1 = 0;
        if (this.decoderCreated == false) goto L11;
        int[] r3 = this.metaData;
        int r0 = r3[2];
        if (r0 != 90) goto L7;
    L10:
        r1 = r3[0];
        goto L11
    L7:
        if (r0 == 270) goto L10;
        r1 = r3[1];
    L11:
        if (r1 == 0) goto L13;
        return r1;
    L13:
        return AndroidUtilities.dp(100.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        int r1 = 0;
        if (this.decoderCreated == false) goto L11;
        int[] r3 = this.metaData;
        int r0 = r3[2];
        if (r0 != 90) goto L7;
    L10:
        r1 = r3[1];
        goto L11
    L7:
        if (r0 == 270) goto L10;
        r1 = r3[0];
    L11:
        if (r1 == 0) goto L13;
        return r1;
    L13:
        return AndroidUtilities.dp(100.0f);
    }

    @Override // org.telegram.messenger.utils.BitmapsCache.Cacheable
    public int getNextFrame(Bitmap r11) {
        if (this.cacheGenerateDecoder != null) goto L6;
        return -1;
    L6:
        Canvas r0 = new Canvas(r11);
        if (this.generatingCacheBitmap != null) goto L9;
        int[] r1 = this.metaData;
        this.generatingCacheBitmap = Bitmap.createBitmap(r1[0], r1[1], Bitmap.Config.ARGB_8888);
    L9:
        this.cacheGenerateDecoder.getVideoFrame(this.generatingCacheBitmap, false, this.startTime, this.endTime, this.loop);
        long r4 = this.cacheGenerateTimestamp;
        if (r4 == 0) goto L16;
        int r12 = this.metaData[3];
        if (r12 != 0) goto L14;
    L15:
        return 0;
    L14:
        if (r4 > r12) goto L15;
    L16:
        int r13 = this.lastMetadata;
        int r42 = this.metaData[3];
        if (r13 != r42) goto L21;
        int r14 = this.tryCount + 1;
        this.tryCount = r14;
        if (r14 <= 5) goto L21;
        return 0;
    L21:
        this.lastMetadata = r42;
        r11.eraseColor(0);
        r0.save();
        float r112 = this.renderingWidth / this.generatingCacheBitmap.getWidth();
        r0.scale(r112, r112);
        r0.drawBitmap(this.generatingCacheBitmap, 0.0f, 0.0f, null);
        r0.restore();
        this.cacheGenerateTimestamp = this.metaData[3];
        return 1;
    }

    public Bitmap getNextFrame(boolean r8) {
        AnimatedFileNative r0 = this.mDecoder;
        AnimatedFileBuffer r1 = this.backgroundBuffer;
        if (r0 != null) goto L9;
        if (r1 != null) goto L6;
        return null;
    L6:
        return r1.bitmap;
    L9:
        if (r1 == null) goto L11;
    L14:
        this.mDecoder.getVideoFrame(this.backgroundBuffer.bitmap, false, this.startTime, this.endTime, r8);
        return this.backgroundBuffer.bitmap;
    L11:
        if (this.unusedBuffers.isEmpty() == true) goto L13;
        this.backgroundBuffer = this.unusedBuffers.remove(0);
        goto L14
    L13:
        float r12 = this.metaData[0];
        float r2 = this.scaleFactor;
        this.backgroundBuffer = AnimatedFileBuffer.of((int) (r12 * r2), (int) (r0[1] * r2));
        goto L14
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int getOrientation() {
        return this.metaData[2];
    }

    public int getProgressMs() {
        return this.metaData[3];
    }

    public long getStartTime() {
        return (long) (this.startTime * 1000.0f);
    }

    public boolean hasBitmap() {
        if (canLoadFrames() == true) goto L5;
        return false;
    L5:
        if (this.renderingBuffer == null) goto L7;
        return true;
    L7:
        if (this.nextRenderingBuffer == null) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean hasParents() {
        return !this.parents.isEmpty();
    }

    public void invalidateInternal() {
        Iterator<ImageReceiver> r1 = this.parents.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().invalidate();
        goto L4
    }

    public boolean isLoadingStream() {
        AnimatedFileDrawableStream r0 = this.stream;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isWaitingForLoad() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isRecycled() {
        if (this.isRecycled == false) goto L5;
        return true;
    L5:
        if (this.decoderTryCount >= 15) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.isRunning;
    }

    public AnimatedFileDrawable makeCopy() {
        AnimatedFileDrawableStream r1 = this.stream;
        if (r1 == null) goto L11;
        File r5 = this.path;
        long r7 = this.streamFileSize;
        int r9 = this.streamLoadingPriority;
        TLRPC.Document r10 = r1.getDocument();
        ImageLocation r11 = this.stream.getLocation();
        Object r12 = this.stream.getParentObject();
        long r13 = this.pendingSeekToUI;
        int r15 = this.currentAccount;
        AnimatedFileDrawableStream r14 = this.stream;
        if (r14 != null) goto L7;
    L9:
        boolean r16 = false;
    L10:
        AnimatedFileDrawable r4 = new AnimatedFileDrawable(r5, false, r7, r9, r10, r11, r12, r13, r15, r16, null);
    L12:
        int[] r17 = r4.metaData;
        int[] r0 = this.metaData;
        r17[0] = r0[0];
        r17[1] = r0[1];
        return r4;
    L7:
        if (r14.isPreview() == false) goto L9;
        r16 = true;
        goto L10
    L11:
        r4 = new AnimatedFileDrawable(this.path, false, this.streamFileSize, this.streamLoadingPriority, this.document, null, null, this.pendingSeekToUI, this.currentAccount, false, null);
        goto L12
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        this.applyTransformation = true;
    }

    @Override // org.telegram.messenger.utils.BitmapsCache.Cacheable
    public void prepareForGenerateCache() {
        this.cacheGenerateDecoder = AnimatedFileNative.createDecoderFrom(this.path.getAbsolutePath(), this.metaData, this.currentAccount, this.streamFileSize, this.stream, false);
    }

    public void recycle() {
        if (this.secondParentViews.isEmpty() == true) goto L6;
        this.recycleWithSecond = true;
        return;
    L6:
        int r0 = 0;
        this.isRunning = false;
        this.isRecycled = true;
        checkChoreographer();
        if (this.cacheGenRunnable == null) goto L10;
        BitmapsCache.decrementTaskCounter();
        RLottieDrawable.lottieCacheGenerateQueue.cancelRunnable(this.cacheGenRunnable);
        this.cacheGenRunnable = null;
    L10:
        if (this.loadFrameTask == null) goto L12;
        this.destroyWhenDone = true;
    L36:
        AnimatedFileDrawableStream r02 = this.stream;
        if (r02 == null) goto L39;
        r02.cancel(true);
        this.stream = null;
    L39:
        invalidateInternal();
        return;
    L12:
        if (this.mDecoder == null) goto L14;
        this.mDecoder.recycle();
        this.mDecoder = null;
    L14:
        ArrayList r2 = new ArrayList();
        AnimatedFileBuffer r4 = this.renderingBuffer;
        if (r4 == null) goto L17;
        r2.add(r4.bitmap);
    L17:
        AnimatedFileBuffer r42 = this.nextRenderingBuffer;
        if (r42 == null) goto L20;
        r2.add(r42.bitmap);
    L20:
        AnimatedFileBuffer r43 = this.nextRenderingBuffer2;
        if (r43 == null) goto L23;
        r2.add(r43.bitmap);
    L23:
        AnimatedFileBuffer r44 = this.backgroundBuffer;
        if (r44 == null) goto L26;
        r2.add(r44.bitmap);
    L26:
        ArrayList<AnimatedFileBuffer> r45 = this.unusedBuffers;
        int r5 = r45.size();
    L27:
        if (r0 >= r5) goto L31;
        AnimatedFileBuffer r6 = r45.get(r0);
        r0 = r0 + 1;
        AnimatedFileBuffer r62 = r6;
        if (r62 == null) goto L27;
        r2.add(r62.bitmap);
        goto L27
    L31:
        this.unusedBuffers.clear();
        this.renderingBuffer = null;
        this.nextRenderingBuffer = null;
        this.nextRenderingBuffer2 = null;
        this.backgroundBuffer = null;
        DispatchQueue r03 = this.decodeQueue;
        if (r03 == null) goto L34;
        r03.recycle();
        this.decodeQueue = null;
    L34:
        getPaint().setShader(null);
        AndroidUtilities.recycleBitmaps(r2);
        goto L36
    }

    @Override // org.telegram.messenger.utils.BitmapsCache.Cacheable
    public void releaseForGenerateCache() {
        AnimatedFileNative r0 = this.cacheGenerateDecoder;
        if (r0 == null) goto L6;
        r0.recycle();
        this.cacheGenerateDecoder = null;
        return;
    }

    public void removeParent(ImageReceiver r2) {
        this.parents.remove(r2);
        if (this.parents.isEmpty() == false) goto L5;
        this.repeatCount = 0;
    L5:
        checkCacheCancel();
    }

    public void removeSecondParentView(View r2) {
        this.secondParentViews.remove(r2);
        if (this.secondParentViews.isEmpty() == true) goto L5;
        return;
    L5:
        if (this.recycleWithSecond == false) goto L8;
        recycle();
        return;
    L8:
        int[] r22 = this.roundRadiusBackup;
        if (r22 == null) goto L13;
        setRoundRadius(r22);
        return;
    }

    public void replaceAnimatedBitmap(Bitmap r3) {
        AnimatedFileBuffer r0 = this.renderingBuffer;
        if (r0 == null) goto L5;
        this.unusedBuffers.add(r0);
    L5:
        AnimatedFileBuffer r02 = this.nextRenderingBuffer;
        if (r02 == null) goto L8;
        this.unusedBuffers.add(r02);
    L8:
        AnimatedFileBuffer r03 = this.nextRenderingBuffer2;
        if (r03 == null) goto L11;
        this.unusedBuffers.add(r03);
    L11:
        this.renderingBuffer = AnimatedFileBuffer.of(r3);
        this.nextRenderingBuffer = null;
        this.nextRenderingBuffer2 = null;
    }

    public void resetStream(boolean r3) {
        AnimatedFileDrawableStream r0 = this.stream;
        if (r0 == null) goto L6;
        r0.cancel(true);
    L6:
        if (this.mDecoder == null) goto L13;
        AnimatedFileNative r2 = this.mDecoder;
        if (r3 == false) goto L11;
        r2.stopDecoder();
        return;
    L11:
        r2.prepareToSeek();
        return;
    }

    public void seekTo(long r2, boolean r4) {
        seekTo(r2, r4, false);
    }

    public void seekTo(long r2, boolean r4, boolean r5) {
        Object r0 = this.sync;
        monitor-enter(r0);
        this.pendingSeekTo = r2;     // Catch: Throwable -> L7
        this.pendingSeekToUI = r2;     // Catch: Throwable -> L7
        this.scheduledForSeek = false;     // Catch: Throwable -> L7
        if (this.mDecoder == null) goto L10;
        this.mDecoder.prepareToSeek();     // Catch: Throwable -> L7
    L10:
        if (this.decoderCreated == false) goto L18;
        AnimatedFileDrawableStream r3 = this.stream;     // Catch: Throwable -> L7
        if (r3 == null) goto L18;
        r3.cancel(r4);     // Catch: Throwable -> L7
        this.pendingRemoveLoading = r4;     // Catch: Throwable -> L7
        if (r4 == false) goto L16;
        int r32 = 0;
    L17:
        this.pendingRemoveLoadingFramesReset = r32;     // Catch: Throwable -> L7
        goto L18
    L16:
        r32 = 10;
    L18:
        if (r5 == true) goto L20;
    L25:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L20:
        if (this.decodeSingleFrame == false) goto L25;
        this.singleFrameDecoded = false;     // Catch: Throwable -> L7
        if (this.loadFrameTask != null) goto L24;
        scheduleNextGetFrame(false, true);     // Catch: Throwable -> L7
        goto L25
    L24:
        this.forceDecodeAfterNextFrame = true;     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public void seekToSync(long r2) {
        if (this.mDecoder != null) goto L5;
        return;
    L5:
        this.mDecoder.seekToMs(r2, true);
    }

    public void setActualDrawRect(float r3, float r4, float r5, float r6) {
        float r62 = r6 + r4;
        float r52 = r5 + r3;
        RectF r0 = this.actualDrawRect;
        if (r0.left == r3) goto L5;
    L12:
        r0.set(r3, r4, r52, r62);
        this.invalidatePath = true;
        return;
    L5:
        if (r0.top != r4) goto L12;
        if (r0.right != r52) goto L12;
        if (r0.bottom != r62) goto L12;
    }

    public void setAllowDecodeSingleFrame(boolean r1) {
        this.decodeSingleFrame = r1;
        if (r1 == false) goto L6;
        scheduleNextGetFrame();
        return;
    }

    public void setInvalidateParentViewWithSecond(boolean r1) {
        this.invalidateParentViewWithSecond = r1;
    }

    public void setIsWebmSticker(boolean r1) {
        this.isWebmSticker = r1;
        if (r1 == false) goto L6;
        this.PRERENDER_FRAME = false;
        this.useSharedQueue = true;
        return;
    }

    public void setLimitFps(boolean r1) {
        this.limitFps = r1;
        if (r1 == false) goto L6;
        this.PRERENDER_FRAME = false;
        return;
    }

    public void setParentView(View r2) {
        if (this.parentView == null) goto L5;
        return;
    L5:
        this.parentView = new WeakReference(r2);
    }

    public void setRoundRadius(int[] r6) {
        int r2 = 0;
        if (this.secondParentViews.isEmpty() == true) goto L8;
        if (this.roundRadiusBackup != null) goto L7;
        this.roundRadiusBackup = new int[4];
    L7:
        int[] r0 = this.roundRadius;
        int[] r3 = this.roundRadiusBackup;
        System.arraycopy(r0, 0, r3, 0, r3.length);
    L8:
        if (r2 >= 4) goto L15;
        if (this.invalidatePath == true) goto L14;
        if (r6[r2] == this.roundRadius[r2]) goto L14;
        this.invalidatePath = true;
    L14:
        this.roundRadius[r2] = r6[r2];
        r2 = r2 + 1;
        goto L8
    }

    public void setStartEndTime(long r3, long r5) {
        this.startTime = r3 / 1000.0f;
        this.endTime = r5 / 1000.0f;
        if (r3 >= 0) goto L5;
        return;
    L5:
        if (getCurrentProgressMs() >= r3) goto L9;
        seekTo(r3, true);
        return;
    }

    public void setUseSharedQueue(boolean r2) {
        if (this.isWebmSticker == false) goto L5;
        return;
    L5:
        this.useSharedQueue = r2;
    }

    public void skipNextFrame(boolean r8) {
        if (this.mDecoder != null) goto L5;
        return;
    L5:
        this.mDecoder.getVideoFrame(null, false, this.startTime, this.endTime, r8);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.isRunning == false) goto L5;
        return;
    L5:
        if (this.parents.isEmpty() == true) goto L10;
        this.isRunning = true;
        this.isPaused = false;
        scheduleNextGetFrame();
        AndroidUtilities.runOnUIThread(this.mStartTask);
        checkChoreographer();
        return;
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.isRunning = false;
        checkChoreographer();
    }

    public void updateCurrentFrame(long r1, boolean r3) {
        checkChoreographerAfterDrawCall();
        updateCurrentFrameInternal(r1, r3);
    }
}
