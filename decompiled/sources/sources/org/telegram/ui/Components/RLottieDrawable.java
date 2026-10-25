package org.telegram.ui.Components;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.JsonReader;
import android.view.View;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.File;
import java.io.FileReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import me.vkryl.core.reference.ReferenceList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.DispatchQueuePoolBackground;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.ResLottieMeta;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.BitmapsCache;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public class RLottieDrawable extends BitmapDrawable implements Animatable, BitmapsCache.Cacheable {
    private static int activeChoreographersCount;
    private static final Executor loadFrameRunnableQueue = null;
    private static final Executor loadFrameRunnableQueueLimitFps = null;
    public static DispatchQueue lottieCacheGenerateQueue;
    private static final AtomicInteger threadId = null;
    private static final AtomicInteger threadId2 = null;
    private boolean allowDrawFramesWhileCacheGenerating;
    private boolean allowVibration;
    private boolean applyTransformation;
    private boolean applyingLayerColors;
    private NativePtrArgs args;
    protected int autoRepeat;
    protected int autoRepeatCount;
    protected int autoRepeatPlayCount;
    protected long autoRepeatTimeout;
    private volatile Bitmap backgroundBitmap;
    private final Paint[] backgroundPaint;
    BitmapsCache bitmapsCache;
    private Runnable cacheGenerateTask;
    private boolean createdForFirstFrame;
    protected int currentFrame;
    protected int customEndFrame;
    private boolean decodeSingleFrame;
    protected boolean destroyWhenDone;
    private boolean doNotRemoveInvalidOnFrameReady;
    private final RectF dstRect;
    private final RectF[] dstRectBackground;
    private boolean fallbackCache;
    private File file;
    private int finishFrame;
    private boolean forceFrameRedraw;
    private CountDownLatch frameWaitSync;
    private boolean genCacheSend;
    int generateCacheFramePointer;
    private RLottieNative generateCacheNative;
    boolean generatingCache;
    protected final int height;
    private boolean invalidateOnProgressSet;
    private boolean isChoreographerRegistered;
    protected int isDice;
    private boolean isInvalid;
    private volatile boolean isPaused;
    protected volatile boolean isRecycled;
    protected volatile boolean isRunning;
    private final boolean isSingleChannel;
    private final HashMap<String, Integer> layerColors;
    protected final Runnable loadFrameRunnable;
    protected Runnable loadFrameTask;
    private final Choreographer60FpsContent.FrameCallback mUiThreadChoreographerCallback;
    private WeakReference<View> masterParent;
    protected final int[] metaData;
    protected volatile RLottieNative nativePtr;
    private boolean needScale;
    private final HashMap<String, Integer> newColorUpdates;
    private int[] newReplaceColors;
    protected volatile boolean nextFrameIsLast;
    private volatile Bitmap nextRenderingBitmap;
    private Runnable onAnimationEndListener;
    protected WeakReference<Runnable> onFinishCallback;
    private final ReferenceList<ImageReceiver> parentViews;
    private final HashMap<String, Integer> pendingColorUpdates;
    private boolean pendingNativeInit;
    private int[] pendingReplaceColors;
    protected boolean playInDirectionOfCustomEndFrame;
    private boolean precache;
    private volatile Bitmap renderingBitmap;
    protected boolean resetVibrationAfterRestart;
    private int retryDelay;
    private float scaleX;
    private float scaleY;
    private boolean shouldLimitFps;
    private boolean singleFrameDecoded;
    public boolean skipFrameUpdate;
    private float speedMultiply;
    private boolean swapBuffersAllowedByChoreographer;
    private int ticksWithoutDraw;
    private final Runnable uiRunnable;
    private final Runnable uiRunnableCacheFinished;
    private final Runnable uiRunnableGenerateCache;
    private final Runnable uiRunnableNoFrame;
    protected HashMap<Integer, Integer> vibrationPattern;
    protected boolean waitingForNextTask;
    public Runnable whenCacheDone;
    protected final int width;

    public static class NativePtrArgs {
        public int[] colorReplacement;
        File file;
        public int fitzModifier;
        String json;
        public int resId;

        private NativePtrArgs() {
        }

        public /* synthetic */ NativePtrArgs(RLottieDrawableIA r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$2ABkjmlrjebumFif-ywZddDm8EM, reason: not valid java name */
    public static /* synthetic */ void m12287$r8$lambda$2ABkjmlrjebumFifywZddDm8EM(RLottieDrawable r0) {
        r0.loadFrameRunnableInternal();
    }

    public static /* synthetic */ void $r8$lambda$AA5BFvhTq4rFKQMJ2CCte1UBRrQ(RLottieDrawable r0) {
        r0.uiRunnableGenerateCacheImpl();
    }

    public static /* synthetic */ void $r8$lambda$BswysXn6TXiGRLusjVjaxdHpWi0(RLottieDrawable r0) {
        r0.checkChoreographerInternal();
    }

    public static /* synthetic */ Thread $r8$lambda$SDPla3uMDH_CxSS3qXRsmIRexjM(Runnable r3) {
        return new Thread(r3, "Lottie-" + threadId.getAndIncrement());
    }

    public static /* synthetic */ void $r8$lambda$SymTkeebPYGt6M3g14OpAtSAnYQ(RLottieDrawable r0) {
        r0.uiRunnableCacheFinishedImpl();
    }

    public static /* synthetic */ void $r8$lambda$anPcIKdRhWbBQ0kQpOG36YEtVKc(RLottieDrawable r0, long r1) {
        r0.onChoreographerFrame(r1);
    }

    public static /* synthetic */ void $r8$lambda$mWzQIxZe5UwKrT7f_n_QTDoZ49M(RLottieDrawable r0) {
        r0.uiRunnableImpl();
    }

    public static /* synthetic */ void $r8$lambda$rzf_1ysUC7eqxr5RYEKWj5A0LPI(RLottieDrawable r0) {
        r0.uiRunnableNoFrameImpl();
    }

    public static /* synthetic */ Thread $r8$lambda$tC1mWcNZZzGahHI8lHqSQzgxU0s(Runnable r3) {
        return new Thread(r3, "LottieLow-" + threadId2.getAndIncrement());
    }

    /* renamed from: $r8$lambda$xO_Q_N-oZ9S6XpvLyPRP5XOCeAo, reason: not valid java name */
    public static /* synthetic */ void m12288$r8$lambda$xO_Q_NoZ9S6XpvLyPRP5XOCeAo(RLottieDrawable r0) {
        r0.lambda$uiRunnableGenerateCacheImpl$2();
    }

    static {
        threadId = new AtomicInteger();
        threadId2 = new AtomicInteger();
        loadFrameRunnableQueue = Executors.newFixedThreadPool(4, new RLottieDrawable$$ExternalSyntheticLambda8());
        loadFrameRunnableQueueLimitFps = Executors.newFixedThreadPool(2, new RLottieDrawable$$ExternalSyntheticLambda9());
    }

    public RLottieDrawable(int r5, int r6) {
        this.metaData = new int[3];
        this.customEndFrame = -1;
        this.newColorUpdates = new HashMap();
        this.pendingColorUpdates = new HashMap();
        this.layerColors = new HashMap();
        this.resetVibrationAfterRestart = false;
        this.allowVibration = true;
        this.speedMultiply = 1.0f;
        this.autoRepeat = 1;
        this.autoRepeatCount = -1;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dstRect = new RectF();
        this.dstRectBackground = new RectF[2];
        this.backgroundPaint = new Paint[2];
        this.uiRunnableNoFrame = new RLottieDrawable$$ExternalSyntheticLambda1(this);
        this.uiRunnable = new RLottieDrawable$$ExternalSyntheticLambda2(this);
        this.uiRunnableGenerateCache = new RLottieDrawable$$ExternalSyntheticLambda3(this);
        this.uiRunnableCacheFinished = new RLottieDrawable$$ExternalSyntheticLambda4(this);
        this.loadFrameRunnable = new RLottieDrawable$$ExternalSyntheticLambda5(this);
        this.parentViews = new ReferenceList(true);
        this.mUiThreadChoreographerCallback = new RLottieDrawable$$ExternalSyntheticLambda6(this);
        this.width = r5;
        this.height = r6;
        this.isSingleChannel = false;
    }

    public RLottieDrawable(int r7, int r8, int r9) {
        this(r7, r8, r9, true, null);
    }

    public RLottieDrawable(int r10, int r11, int r12, boolean r13, int[] r14) {
        int[] r0 = new int[3];
        this.metaData = r0;
        this.customEndFrame = -1;
        this.newColorUpdates = new HashMap();
        this.pendingColorUpdates = new HashMap();
        HashMap<String, Integer> r2 = new HashMap();
        this.layerColors = r2;
        this.resetVibrationAfterRestart = false;
        this.allowVibration = true;
        this.speedMultiply = 1.0f;
        this.autoRepeat = 1;
        this.autoRepeatCount = -1;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dstRect = new RectF();
        this.dstRectBackground = new RectF[2];
        this.backgroundPaint = new Paint[2];
        this.uiRunnableNoFrame = new RLottieDrawable$$ExternalSyntheticLambda1(this);
        this.uiRunnable = new RLottieDrawable$$ExternalSyntheticLambda2(this);
        this.uiRunnableGenerateCache = new RLottieDrawable$$ExternalSyntheticLambda3(this);
        this.uiRunnableCacheFinished = new RLottieDrawable$$ExternalSyntheticLambda4(this);
        this.loadFrameRunnable = new RLottieDrawable$$ExternalSyntheticLambda5(this);
        this.parentViews = new ReferenceList(true);
        this.mUiThreadChoreographerCallback = new RLottieDrawable$$ExternalSyntheticLambda6(this);
        this.width = r11;
        this.height = r12;
        this.autoRepeat = 0;
        getPaint().setFlags(2);
        NativePtrArgs r112 = new NativePtrArgs(null);
        this.args = r112;
        if (r14 != null) goto L5;
        int[] r142 = null;
    L6:
        r112.colorReplacement = r142;
        long r5 = ResLottieMeta.find(r10);
        if (r5 == (-1)) goto L9;
        this.pendingNativeInit = true;
        this.args.resId = r10;
        this.isSingleChannel = ResLottieMeta.isMonoColorOf(r5);
        r0[0] = ResLottieMeta.frameCountOf(r5);
        r0[1] = ResLottieMeta.fpsOf(r5);
    L17:
        if (this.isSingleChannel == false) goto L19;
        setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L19:
        if (r13 == false) goto L24;
        setAllowDecodeSingleFrame(true);
        return;
    L24:
        return;
    L9:
        this.isSingleChannel = false;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L22;
        String r102 = AndroidUtilities.readRes(r10);
        if (TextUtils.isEmpty(r102) == false) goto L15;
        this.args = null;
        return;
    L15:
        NativePtrArgs r113 = this.args;
        r113.json = r102;
        this.nativePtr = RLottieNative.createFromRawJson(r102, r0, r113.colorReplacement, r2);
        goto L17
    L22:
        f$$ExternalSyntheticBUOutline1.m("rawRes not found");
        throw null;
    L5:
        r142 = (int[]) r14.clone();
        goto L6
    }

    public RLottieDrawable(File r15, String r16, int r17, int r18, BitmapsCache.CacheOptions r19, boolean r20, int[] r21, int r22, boolean r23) {
        int[] r4 = new int[3];
        this.metaData = r4;
        this.customEndFrame = -1;
        this.newColorUpdates = new HashMap();
        this.pendingColorUpdates = new HashMap();
        HashMap<String, Integer> r6 = new HashMap();
        this.layerColors = r6;
        this.resetVibrationAfterRestart = false;
        this.allowVibration = true;
        this.speedMultiply = 1.0f;
        this.autoRepeat = 1;
        this.autoRepeatCount = -1;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dstRect = new RectF();
        this.dstRectBackground = new RectF[2];
        this.backgroundPaint = new Paint[2];
        this.uiRunnableNoFrame = new RLottieDrawable$$ExternalSyntheticLambda1(this);
        this.uiRunnable = new RLottieDrawable$$ExternalSyntheticLambda2(this);
        this.uiRunnableGenerateCache = new RLottieDrawable$$ExternalSyntheticLambda3(this);
        this.uiRunnableCacheFinished = new RLottieDrawable$$ExternalSyntheticLambda4(this);
        this.loadFrameRunnable = new RLottieDrawable$$ExternalSyntheticLambda5(this);
        this.parentViews = new ReferenceList(true);
        this.mUiThreadChoreographerCallback = new RLottieDrawable$$ExternalSyntheticLambda6(this);
        this.width = r17;
        this.height = r18;
        this.shouldLimitFps = r20;
        this.isSingleChannel = r23;
        if (r19 == null) goto L5;
        boolean r11 = true;
    L6:
        this.precache = r11;
        if (r16 != null) goto L12;
        if (r19 == null) goto L12;
        if (r19.fallback == false) goto L12;
        boolean r112 = true;
    L13:
        this.fallbackCache = r112;
        if (r19 != null) goto L16;
    L18:
        boolean r113 = false;
    L19:
        this.createdForFirstFrame = r113;
        int[] r12 = null;
        NativePtrArgs r114 = new NativePtrArgs(null);
        this.args = r114;
        r114.file = r15.getAbsoluteFile();
        NativePtrArgs r115 = this.args;
        r115.json = r16;
        if (r21 == null) goto L23;
        r12 = (int[]) r21.clone();
    L23:
        r115.colorReplacement = r12;
        this.args.fitzModifier = r22;
        getPaint().setFlags(2);
        if (r16 != null) goto L27;
        this.file = r15;
    L27:
        if (this.precache == false) goto L32;
        if (lottieCacheGenerateQueue != null) goto L32;
        createCacheGenQueue();
    L32:
        if (this.precache == true) goto L34;
        this.nativePtr = RLottieNative.createFromFile(r15.getAbsolutePath(), r16, r4, this.args.colorReplacement, r22, r6);
        if (this.nativePtr != null) goto L47;
        FileLog.d("RLottieDrawable nativePtr == 0 " + r15.getAbsolutePath() + " remove file");
        r15.delete();
    L47:
        if (this.shouldLimitFps == true) goto L49;
        return;
    L49:
        if (r4[1] >= 60) goto L53;
        this.shouldLimitFps = false;
        return;
    L53:
        return;
    L34:
        if (this.createdForFirstFrame == true) goto L54;
        parseLottieMetadata(r15, r16, r4);
        if (this.shouldLimitFps == true) goto L39;
    L41:
        this.bitmapsCache = new BitmapsCache(r15, this, r19, r17, r18, !r20, r22);
        return;
    L39:
        if (r4[1] >= 60) goto L41;
        this.shouldLimitFps = false;
        goto L41
    L54:
        return;
    L16:
        if (r19.firstFrame == false) goto L18;
        r113 = true;
    L12:
        r112 = false;
        goto L13
    L5:
        r11 = false;
        goto L6
    }

    private void applyPendingColorsUpdates() {
        RLottieNative r0 = this.nativePtr;
        if (r0 == null) goto L33;
        if (this.pendingColorUpdates.isEmpty() == true) goto L8;
    L9:
        this.layerColors.putAll(this.pendingColorUpdates);     // Catch: Exception -> L27
        int[] r1 = this.pendingReplaceColors;     // Catch: Exception -> L27
        if (r1 == null) goto L12;
        NativePtrArgs r2 = this.args;     // Catch: Exception -> L27
        r2.colorReplacement = (int[]) r1.clone();     // Catch: Exception -> L27
    L12:
        NativePtrArgs r12 = this.args;     // Catch: Exception -> L27
        File r22 = r12.file;     // Catch: Exception -> L27
        if (r22 == null) goto L15;
        String r3 = r22.getAbsolutePath();     // Catch: Exception -> L27
        NativePtrArgs r13 = this.args;     // Catch: Exception -> L27
        RLottieNative r14 = RLottieNative.createFromFile(r3, r13.json, this.metaData, r13.colorReplacement, r13.fitzModifier, this.layerColors);     // Catch: Exception -> L27
    L24:
        if (r14 == null) goto L32;
        this.nativePtr = r14;     // Catch: Exception -> L27
        r0.recycle();     // Catch: Exception -> L27
        this.pendingColorUpdates.clear();     // Catch: Exception -> L27
        this.pendingReplaceColors = null;     // Catch: Exception -> L27
        return;
    L32:
        return;
    L15:
        int r23 = r12.resId;     // Catch: Exception -> L27
        if (r23 != 0) goto L18;
    L23:
        r14 = RLottieNative.createFromRawJson(r12.json, this.metaData, r12.colorReplacement, this.layerColors);     // Catch: Exception -> L27
        goto L24
    L18:
        if (r12.json != null) goto L23;
        String r15 = AndroidUtilities.readRes(r23);     // Catch: Exception -> L27
        if (TextUtils.isEmpty(r15) == true) goto L31;
        NativePtrArgs r24 = this.args;     // Catch: Exception -> L27
        r24.json = r15;     // Catch: Exception -> L27
        r14 = RLottieNative.createFromRawJson(r15, this.metaData, r24.colorReplacement, this.layerColors);     // Catch: Exception -> L27
        goto L24
    L31:
        return;
    L8:
        if (this.pendingReplaceColors != null) goto L9;
        return;
    L34:
        return;
    }

    private boolean canLoadFrames() {
        if (this.precache == false) goto L12;
        if (this.bitmapsCache == null) goto L7;
    L10:
        return true;
    L7:
        if (this.fallbackCache == true) goto L10;
        return false;
    L12:
        if (this.nativePtr == null) goto L14;
    L17:
        return true;
    L14:
        if (this.pendingNativeInit == true) goto L17;
        return false;
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
        if (this.isRunning == false) goto L22;
        if (this.isPaused == true) goto L22;
        if (this.isChoreographerRegistered == true) goto L25;
        float r0 = this.metaData[1];
        if (this.shouldLimitFps == false) goto L11;
        float r3 = 2.0f;
    L12:
        int r02 = Math.round((r0 / r3) * this.speedMultiply);
        if (r02 > 0) goto L15;
        return;
    L15:
        if (this.metaData[0] == 1) goto L17;
    L19:
        activeChoreographersCount++;
        this.isChoreographerRegistered = true;
        this.ticksWithoutDraw = 0;
        Choreographer60FpsContent.getInstance().addFrameCallback(this.mUiThreadChoreographerCallback, r02);
        invalidateInternal();
        return;
    L17:
        if (this.isDice != 0) goto L19;
        return;
    L11:
        r3 = 1.0f;
        goto L12
    L25:
        return;
    L22:
        if (this.isChoreographerRegistered == false) goto L27;
        activeChoreographersCount--;
        this.isChoreographerRegistered = false;
        this.ticksWithoutDraw = 0;
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.mUiThreadChoreographerCallback);
        return;
    }

    private void checkDispatchOnAnimationEnd() {
        Runnable r0 = this.onAnimationEndListener;
        if (r0 == null) goto L6;
        r0.run();
        this.onAnimationEndListener = null;
        return;
    }

    public static void createCacheGenQueue() {
        lottieCacheGenerateQueue = new DispatchQueue("cache generator queue");
    }

    private /* synthetic */ void lambda$uiRunnableGenerateCacheImpl$2() {
        BitmapsCache r0 = this.bitmapsCache;     // Catch: Throwable -> L7
        if (r0 == null) goto L5;
        r0.createCache();     // Catch: Throwable -> L7
    L5:
        AndroidUtilities.runOnUIThread(this.uiRunnableCacheFinished);
    }

    private int loadFrameRunnableImpl() {
        int r0 = beforeLoadFrameImpl();     // Catch: Exception -> L17
        if (r0 == 1) goto L6;
        return r0;
    L6:
        if (this.backgroundBitmap == null) goto L9;
        Bitmap r02 = this.backgroundBitmap;     // Catch: Exception -> L17
        boolean r2 = true;
    L13:
        int r22 = loadFrameRunnableImpl(r02, r2);     // Catch: Exception -> L17
        if (r22 != 1) goto L16;
        this.nextRenderingBitmap = r02;     // Catch: Exception -> L17
        afterLoadFrameImpl();     // Catch: Exception -> L17
    L16:
        return r22;
    L9:
        if (this.isSingleChannel == false) goto L11;
        Bitmap.Config r03 = Bitmap.Config.ALPHA_8;     // Catch: Exception -> L17
    L12:
        r02 = Bitmap.createBitmap(this.width, this.height, r03);     // Catch: Exception -> L17
        this.backgroundBitmap = r02;     // Catch: Exception -> L17
        r2 = false;
        goto L13
    L11:
        r03 = Bitmap.Config.ARGB_8888;     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        return 2;
    }

    private void loadFrameRunnableInternal() {
        int r0 = loadFrameRunnableImpl();
        if (r0 != 1) goto L6;
        this.retryDelay = 0;
        AndroidUtilities.runOnUIThread(this.uiRunnable);
    L10:
        CountDownLatch r5 = this.frameWaitSync;
        if (r5 == null) goto L14;
        r5.countDown();
        return;
    L14:
        return;
    L6:
        if (r0 != 2) goto L8;
        AndroidUtilities.runOnUIThread(this.uiRunnableNoFrame, this.retryDelay);
        this.retryDelay = Math.min((Math.max(this.retryDelay, 2) * 3) / 2, CastStatusCodes.AUTHENTICATION_FAILED);
        goto L10
    L8:
        if (r0 != 3) goto L10;
        AndroidUtilities.runOnUIThread(this.uiRunnableNoFrame);
        goto L10
    }

    private void onChoreographerFrame(long r1) {
        checkChoreographerAfterFrameCall();
        if (this.isChoreographerRegistered == false) goto L6;
        this.swapBuffersAllowedByChoreographer = true;
        invalidateInternal();
        return;
    }

    private void parseLottieMetadata(File r11, String r12, int[] r13) {
        JsonReader r1 = new JsonReader(new FileReader(r11.getAbsoluteFile()));     // Catch: Exception -> L28
        r1.beginObject();     // Catch: Throwable -> L16
        double r2 = 0.0d;
        double r6 = 30.0d;
        double r4 = 0.0d;
    L5:
        if (r1.hasNext() == false) goto L25;
        String r0 = r1.nextName();     // Catch: Throwable -> L16
        int r8 = r0.hashCode();     // Catch: Throwable -> L16
        if (r8 != 3276) goto L9;
        if (r0.equals("fr") == false) goto L24;
        r6 = r1.nextDouble();     // Catch: Throwable -> L16
    L24:
        r1.skipValue();     // Catch: Throwable -> L16
        goto L5
    L9:
        if (r8 != 3367) goto L11;
        if (r0.equals("ip") == false) goto L24;
        r4 = r1.nextDouble();     // Catch: Throwable -> L16
        goto L5
    L11:
        if (r8 != 3553) goto L24;
        if (r0.equals("op") == false) goto L24;
        r2 = r1.nextDouble();     // Catch: Throwable -> L16
        goto L5
    L25:
        r1.endObject();     // Catch: Throwable -> L16
        r1.close();     // Catch: Exception -> L28
        r13[0] = (int) (r2 - r4);     // Catch: Exception -> L28
        r13[1] = (int) r6;     // Catch: Exception -> L28
        return;
    L16:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L32
    L61:
        throw th;     // Catch: Exception -> L28
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L28
        throw th;     // Catch: Exception -> L28
    L28:
        e = move-exception;
        FileLog.e(e);
        String r14 = r11.getAbsolutePath();
        NativePtrArgs r112 = this.args;
        RLottieNative r10 = RLottieNative.createFromFile(r14, r12, r13, r112.colorReplacement, r112.fitzModifier, this.layerColors);
        if (r10 == null) goto L62;
        r10.recycle();
        return;
    }

    private void performVibration() {
        HashMap<Integer, Integer> r0 = this.vibrationPattern;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.allowVibration == false) goto L21;
        Integer r2 = r0.get(Integer.valueOf(this.currentFrame - 1));
        if (r2 == null) goto L22;
        Activity r02 = LaunchActivity.instance;     // Catch: Exception -> L17
        if (r02 != null) goto L11;
        r02 = BubbleActivity.instance;     // Catch: Exception -> L17
    L11:
        View r03 = r02.getWindow().getDecorView();     // Catch: Exception -> L17
        if (r2.intValue() != 1) goto L14;
        int r22 = 0;
    L15:
        r03.performHapticFeedback(r22, 2);     // Catch: Exception -> L17
        return;
    L14:
        r22 = 3;
    L23:
        return;
    L22:
        return;
    }

    private void requestRedrawColors() {
        if (this.applyingLayerColors == false) goto L5;
    L14:
        invalidateInternal();
        return;
    L5:
        if (this.isRunning == true) goto L14;
        if (this.decodeSingleFrame == false) goto L14;
        if (this.currentFrame > 2) goto L11;
        this.currentFrame = 0;
    L11:
        this.nextFrameIsLast = false;
        this.singleFrameDecoded = false;
        if (scheduleNextGetFrame() == true) goto L14;
        this.forceFrameRedraw = true;
        goto L14
    }

    private void setCurrentFrame(boolean r6) {
        swapBuffers();
        if (this.isDice != 2) goto L13;
        WeakReference<Runnable> r0 = this.onFinishCallback;
        if (r0 == null) goto L13;
        if ((this.currentFrame - 1) < this.finishFrame) goto L13;
        Runnable r02 = r0.get();
        if (r02 == null) goto L11;
        r02.run();
    L11:
        this.onFinishCallback = null;
    L13:
        if (this.nextFrameIsLast == false) goto L15;
    L18:
        stop();
    L19:
        this.loadFrameTask = null;
        if (this.doNotRemoveInvalidOnFrameReady == false) goto L23;
        this.doNotRemoveInvalidOnFrameReady = false;
    L25:
        this.singleFrameDecoded = true;
        this.waitingForNextTask = false;
        if (r6 == false) goto L31;
        if (this.forceFrameRedraw == false) goto L31;
        this.singleFrameDecoded = false;
        this.forceFrameRedraw = false;
    L31:
        if (this.isDice != 0) goto L39;
        WeakReference<Runnable> r62 = this.onFinishCallback;
        if (r62 == null) goto L39;
        if (this.currentFrame < this.finishFrame) goto L39;
        Runnable r63 = r62.get();
        if (r63 == null) goto L39;
        r63.run();
    L39:
        scheduleNextGetFrame();
        return;
    L23:
        if (this.isInvalid == false) goto L25;
        this.isInvalid = false;
        goto L25
    L15:
        if (this.autoRepeatCount != 0) goto L19;
        if (this.autoRepeat != 1) goto L19;
        goto L18
    }

    private void swapBuffers() {
        this.backgroundBitmap = this.renderingBitmap;
        this.renderingBitmap = this.nextRenderingBitmap;
        this.nextRenderingBitmap = null;
        this.swapBuffersAllowedByChoreographer = false;
    }

    private void uiRunnableCacheFinishedImpl() {
        if (this.cacheGenerateTask == null) goto L5;
        BitmapsCache.decrementTaskCounter();
        this.cacheGenerateTask = null;
    L5:
        this.generatingCache = false;
        decodeFrameFinishedInternal();
        Runnable r0 = this.whenCacheDone;
        if (r0 == null) goto L9;
        r0.run();
        this.whenCacheDone = null;
        return;
    }

    private void uiRunnableGenerateCacheImpl() {
        if (this.isRecycled == false) goto L5;
        return;
    L5:
        if (this.destroyWhenDone == false) goto L7;
        return;
    L7:
        if (canLoadFrames() == true) goto L9;
        return;
    L9:
        if (this.cacheGenerateTask != null) goto L18;
        this.generatingCache = true;
        if (lottieCacheGenerateQueue != null) goto L13;
        createCacheGenQueue();
    L13:
        BitmapsCache.incrementTaskCounter();
        DispatchQueue r0 = lottieCacheGenerateQueue;
        RLottieDrawable$$ExternalSyntheticLambda10 r1 = new RLottieDrawable$$ExternalSyntheticLambda10(this);
        this.cacheGenerateTask = r1;
        r0.postRunnable(r1);
        return;
    }

    private void uiRunnableImpl() {
        this.singleFrameDecoded = true;
        if (this.isRunning == true) goto L7;
        if (this.decodeSingleFrame == false) goto L7;
    L10:
        invalidateInternal();
    L11:
        decodeFrameFinishedInternal();
        return;
    L7:
        if (this.renderingBitmap != null) goto L11;
        if (this.nextRenderingBitmap == null) goto L11;
        goto L10
    }

    private void uiRunnableNoFrameImpl() {
        this.loadFrameTask = null;
        decodeFrameFinishedInternal();
    }

    private void updateCurrentFrameInternal() {
        if (this.swapBuffersAllowedByChoreographer == false) goto L5;
    L10:
        boolean r0 = true;
    L12:
        if (this.isRunning == false) goto L29;
        if (this.renderingBitmap != null) goto L20;
        if (this.nextRenderingBitmap != null) goto L20;
        scheduleNextGetFrame();
        return;
    L20:
        if (this.nextRenderingBitmap != null) goto L22;
        return;
    L22:
        if (this.renderingBitmap == null) goto L26;
        if (r0 == true) goto L25;
        return;
    L25:
        if (this.skipFrameUpdate == false) goto L26;
        return;
    L26:
        performVibration();
        setCurrentFrame(false);
        return;
    L29:
        if (this.forceFrameRedraw == true) goto L34;
        if (this.decodeSingleFrame == false) goto L40;
        if (r0 == true) goto L34;
        return;
    L40:
        return;
    L34:
        if (this.nextRenderingBitmap == null) goto L42;
        setCurrentFrame(true);
        return;
    L42:
        return;
    L5:
        if (this.isRunning == false) goto L7;
    L9:
        r0 = false;
        goto L12
    L7:
        if (this.decodeSingleFrame == false) goto L9;
        goto L9
    }

    public final void addParentView(ImageReceiver r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        this.parentViews.add(r1);
    }

    public void afterLoadFrameImpl() {
        if (this.shouldLimitFps == false) goto L5;
        int r0 = 2;
    L6:
        int r3 = this.customEndFrame;
        if (r3 >= 0) goto L9;
    L24:
        int r5 = this.currentFrame;
        int r6 = r5 + r0;
        if (r3 >= 0) goto L28;
        r3 = this.metaData[0];
    L28:
        int r7 = this.autoRepeat;
        if (r6 >= r3) goto L37;
        if (r7 != 3) goto L34;
        this.nextFrameIsLast = true;
        this.autoRepeatPlayCount++;
        return;
    L34:
        this.currentFrame = r5 + r0;
        this.nextFrameIsLast = false;
        return;
    L37:
        if (r7 != 1) goto L45;
        this.currentFrame = 0;
        this.nextFrameIsLast = false;
        if (this.resetVibrationAfterRestart == false) goto L41;
        this.vibrationPattern = null;
        this.resetVibrationAfterRestart = false;
    L41:
        int r02 = this.autoRepeatCount;
        if (r02 <= 0) goto L52;
        this.autoRepeatCount = r02 - 1;
        return;
    L52:
        return;
    L45:
        if (r7 != 2) goto L50;
        this.currentFrame = 0;
        this.nextFrameIsLast = true;
        this.autoRepeatPlayCount++;
        if (this.resetVibrationAfterRestart == false) goto L53;
        this.vibrationPattern = null;
        this.resetVibrationAfterRestart = false;
        return;
    L53:
        return;
    L50:
        this.nextFrameIsLast = true;
        checkDispatchOnAnimationEnd();
        return;
    L9:
        if (this.playInDirectionOfCustomEndFrame == false) goto L24;
        int r1 = this.currentFrame;
        if (r1 <= r3) goto L19;
        if ((r1 - r0) < r3) goto L16;
        this.currentFrame = r1 - r0;
        this.nextFrameIsLast = false;
        return;
    L16:
        this.nextFrameIsLast = true;
        checkDispatchOnAnimationEnd();
        return;
    L19:
        if ((r1 + r0) >= r3) goto L22;
        this.currentFrame = r1 + r0;
        this.nextFrameIsLast = false;
        return;
    L22:
        this.nextFrameIsLast = true;
        checkDispatchOnAnimationEnd();
        return;
    L5:
        r0 = 1;
        goto L6
    }

    public int beforeLoadFrameImpl() {
        if (this.isRecycled == false) goto L7;
        return 3;
    L7:
        if (canLoadFrames() == true) goto L10;
        return 2;
    L10:
        if (this.nativePtr == null) goto L12;
        return 1;
    L12:
        if (this.pendingNativeInit == false) goto L20;
        String r0 = AndroidUtilities.readRes(this.args.resId);
        if (TextUtils.isEmpty(r0) == false) goto L16;
        return 2;
    L16:
        this.nativePtr = RLottieNative.createFromRawJson(r0, this.metaData, this.args.colorReplacement, this.layerColors);
        this.pendingNativeInit = false;
        return 1;
    L20:
        return 1;
    }

    public final void beginApplyLayerColors() {
        this.applyingLayerColors = true;
    }

    public boolean bothRenderingBitmapsAreNull() {
        if (this.renderingBitmap == null) goto L5;
        return false;
    L5:
        if (this.nextRenderingBitmap != null) goto L10;
        return true;
    L10:
        return false;
    }

    public final void checkCacheCancel() {
        if (this.bitmapsCache != null) goto L5;
        return;
    L5:
        if (lottieCacheGenerateQueue != null) goto L7;
        return;
    L7:
        if (this.cacheGenerateTask == null) goto L30;
        WeakReference<View> r0 = this.masterParent;
        if (r0 == null) goto L12;
        View r02 = r0.get();
    L14:
        if (this.parentViews.isEmpty() == true) goto L16;
        return;
    L16:
        if (getCallback() != null) goto L28;
        if (r02 != null) goto L19;
    L20:
        Runnable r03 = this.cacheGenerateTask;
        if (r03 == null) goto L23;
        lottieCacheGenerateQueue.cancelRunnable(r03);
        BitmapsCache.decrementTaskCounter();
        this.cacheGenerateTask = null;
    L23:
        this.generatingCache = false;
        this.genCacheSend = false;
        return;
    L19:
        if (r02.isAttachedToWindow() == false) goto L20;
        return;
    L28:
        return;
    L12:
        r02 = null;
        goto L14
    }

    public final void checkChoreographer() {
        AndroidUtilities.executeOnUIThread(new RLottieDrawable$$ExternalSyntheticLambda7(this));
    }

    public final void checkRunningTasks() {
        Runnable r0 = this.cacheGenerateTask;
        if (r0 == null) goto L6;
        lottieCacheGenerateQueue.cancelRunnable(r0);
        BitmapsCache.decrementTaskCounter();
        this.cacheGenerateTask = null;
    L6:
        if (hasParentView() == false) goto L8;
        return;
    L8:
        if (this.nextRenderingBitmap != null) goto L10;
        return;
    L10:
        if (this.loadFrameTask == null) goto L15;
        this.loadFrameTask = null;
        this.nextRenderingBitmap = null;
        return;
    }

    public final void commitApplyLayerColors() {
        if (this.applyingLayerColors == true) goto L5;
        return;
    L5:
        this.applyingLayerColors = false;
        if (this.isRunning == false) goto L8;
    L15:
        invalidateInternal();
        return;
    L8:
        if (this.decodeSingleFrame == false) goto L15;
        if (this.currentFrame > 2) goto L12;
        this.currentFrame = 0;
    L12:
        this.nextFrameIsLast = false;
        this.singleFrameDecoded = false;
        if (scheduleNextGetFrame() == true) goto L15;
        this.forceFrameRedraw = true;
        goto L15
    }

    public void decodeFrameFinishedInternal() {
        if (this.destroyWhenDone == false) goto L12;
        checkRunningTasks();
        if (this.loadFrameTask != null) goto L12;
        if (this.cacheGenerateTask != null) goto L12;
        if (this.nativePtr == null) goto L12;
        recycleNativePtr(true);
    L12:
        if (this.nativePtr == null) goto L16;
        if (this.fallbackCache == true) goto L16;
    L19:
        this.waitingForNextTask = true;
        if (hasParentView() == true) goto L23;
        stop();
    L23:
        if (this.isRunning == false) goto L26;
        scheduleNextGetFrame();
        return;
    L26:
        return;
    L16:
        if (this.bitmapsCache != null) goto L19;
        recycleResources();
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final void draw(Canvas r8) {
        drawInternal(r8, null, false, 0, 0);
    }

    public final void draw(Canvas r8, Paint r9) {
        drawInternal(r8, r9, false, 0, 0);
    }

    public final void drawInBackground(Canvas r8, float r9, float r10, float r11, float r12, int r13, ColorFilter r14, int r15) {
        RectF[] r0 = this.dstRectBackground;
        if (r0[r15] != null) goto L5;
        r0[r15] = new RectF();
        this.backgroundPaint[r15] = new Paint(1);
        this.backgroundPaint[r15].setFilterBitmap(true);
    L5:
        this.backgroundPaint[r15].setAlpha(r13);
        this.backgroundPaint[r15].setColorFilter(r14);
        this.dstRectBackground[r15].set(r9, r10, r11 + r9, r12 + r10);
        drawInternal(r8, null, true, 0, r15);
    }

    public final void drawInternal(Canvas r5, Paint r6, boolean r7, long r8, int r10) {
        if (canLoadFrames() == true) goto L5;
        return;
    L5:
        if (this.destroyWhenDone == true) goto L50;
        boolean r0 = false;
        if (r7 == true) goto L10;
        updateCurrentFrame(r8, false);
    L10:
        if (r7 == false) goto L12;
        RectF r82 = this.dstRectBackground[r10];
    L13:
        if (r6 != null) goto L19;
        if (r7 == false) goto L17;
        r6 = this.backgroundPaint[r10];
        goto L19
    L17:
        r6 = getPaint();
    L19:
        if (r6.getAlpha() != 0) goto L22;
        return;
    L22:
        if (this.isInvalid == false) goto L24;
        return;
    L24:
        if (this.renderingBitmap != null) goto L27;
        return;
    L27:
        if (r7 == true) goto L37;
        r82.set(getBounds());
        if (this.applyTransformation == false) goto L36;
        this.scaleX = r82.width() / this.width;
        this.scaleY = r82.height() / this.height;
        this.applyTransformation = false;
        if (Math.abs(r82.width() - this.width) < AndroidUtilities.dp(1.0f)) goto L33;
    L34:
        r0 = true;
    L35:
        this.needScale = r0;
        goto L36
    L33:
        if (Math.abs(r82.height() - this.height) < AndroidUtilities.dp(1.0f)) goto L35;
    L36:
        float r72 = this.scaleX;
        float r9 = this.scaleY;
        boolean r102 = this.needScale;
    L43:
        if (r102 == true) goto L46;
        r5.drawBitmap(this.renderingBitmap, r82.left, r82.top, r6);
        return;
    L46:
        r5.save();
        r5.translate(r82.left, r82.top);
        r5.scale(r72, r9);
        r5.drawBitmap(this.renderingBitmap, 0.0f, 0.0f, r6);
        r5.restore();
        return;
    L37:
        r72 = r82.width() / this.width;
        float r1 = r82.height() / this.height;
        if (Math.abs(r82.width() - this.width) < AndroidUtilities.dp(1.0f)) goto L40;
    L41:
        r0 = true;
    L42:
        r102 = r0;
        r9 = r1;
        goto L43
    L40:
        if (Math.abs(r82.height() - this.height) < AndroidUtilities.dp(1.0f)) goto L42;
    L12:
        r82 = this.dstRect;
        goto L13
    }

    public int estimateSizeInCache() {
        int r0 = getIntrinsicWidth() * getIntrinsicHeight();
        if (this.isSingleChannel == false) goto L7;
        return r0 * 2;
    L7:
        return r0 * 8;
    }

    public void finalize() {
        recycle(false);     // Catch: Throwable -> L6
        super.finalize();
        return;
    L6:
        th = move-exception;
        super.finalize();
        throw th;
    }

    public final Bitmap getAnimatedBitmap() {
        if (this.renderingBitmap == null) goto L7;
        return this.renderingBitmap;
    L7:
        if (this.nextRenderingBitmap != null) goto L9;
        return null;
    L9:
        return this.nextRenderingBitmap;
    }

    public final int getCurrentFrame() {
        return this.currentFrame;
    }

    public final int getCustomEndFrame() {
        return this.customEndFrame;
    }

    public final long getDuration() {
        int[] r2 = this.metaData;
        return (long) ((r2[0] / r2[1]) * 1000.0f);
    }

    public final int getFramesCount() {
        return this.metaData[0];
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.height;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        return this.height;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        return this.width;
    }

    @Override // org.telegram.messenger.utils.BitmapsCache.Cacheable
    public final int getNextFrame(Bitmap r5) {
        RLottieNative r0 = this.generateCacheNative;
        if (r0 != null) goto L7;
        return -1;
    L7:
        if (this.generateCacheFramePointer < r0.getFrameCount()) goto L11;
        return 0;
    L11:
        if (this.shouldLimitFps == false) goto L13;
        int r02 = 2;
    L15:
        if (this.generateCacheNative.getFrame(this.generateCacheFramePointer, r5, true) == (-5)) goto L25;
        this.generateCacheFramePointer += r02;
        return 1;
    L25:
        Thread.sleep(100);     // Catch: InterruptedException -> L19
    L22:
        return getNextFrame(r5);
    L19:
        e = move-exception;
        e.printStackTrace();
        goto L22
    L13:
        r02 = 1;
        goto L15
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    public final float getProgress() {
        return this.currentFrame / this.metaData[0];
    }

    public final boolean hasBitmap() {
        if (this.isRecycled == false) goto L5;
        return false;
    L5:
        if (this.renderingBitmap != null) goto L9;
        if (this.nextRenderingBitmap != null) goto L9;
        return false;
    L9:
        if (this.isInvalid == true) goto L15;
        return true;
    L15:
        return false;
    }

    public final boolean hasParentView() {
        if (this.parentViews.isEmpty() == false) goto L13;
        WeakReference<View> r0 = this.masterParent;
        if (r0 == null) goto L9;
        if (r0.get() == null) goto L9;
        return true;
    L9:
        if (getCallback() != null) goto L16;
        return false;
    L16:
        return true;
    L13:
        return true;
    }

    public final boolean hasVibrationPattern() {
        if (this.vibrationPattern == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean ignoreScheduleNextGetFrame() {
        if (this.renderingBitmap != null) goto L5;
        return false;
    L5:
        if (getFramesCount() != 1) goto L9;
        return true;
    L9:
        return false;
    }

    public void invalidateInternal() {
        if (this.isRecycled == true) goto L21;
        Iterator<ImageReceiver> r0 = this.parentViews.iterator();
    L7:
        if (r0.hasNext() == false) goto L9;
        r0.next().invalidate();
        goto L7
    L9:
        WeakReference<View> r02 = this.masterParent;
        if (r02 == null) goto L12;
        View r03 = r02.get();
    L13:
        if (r03 == null) goto L16;
        r03.invalidate();
    L16:
        if (getCallback() == null) goto L20;
        invalidateSelf();
        return;
    L20:
        return;
    L12:
        r03 = null;
        goto L13
    }

    public final boolean isGeneratingCache() {
        if (this.cacheGenerateTask == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isHeavyDrawable() {
        return true;
    }

    public final boolean isLastFrame() {
        if (this.currentFrame != (getFramesCount() - 1)) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.isRunning;
    }

    public int loadFrameRunnableImpl(Bitmap r13, boolean r14) {
        applyPendingColorsUpdates();
        RLottieNative r0 = this.nativePtr;
        if (this.shouldLimitFps == false) goto L5;
        int r1 = 2;
    L7:
        if (this.precache == false) goto L21;
        BitmapsCache r4 = this.bitmapsCache;
        if (r4 == null) goto L21;
        int r5 = 0;
        r5 = r4.getFrame(this.currentFrame / r1, r13);     // Catch: Exception -> L19
        if (this.bitmapsCache.needGenCache() == false) goto L14;
    L22:
        BitmapsCache r02 = this.bitmapsCache;
        if (r02 != null) goto L25;
    L37:
        if (r5 >= 0) goto L39;
        return 2;
    L39:
        return 1;
    L25:
        if (r02.needGenCache() == false) goto L37;
        if (this.genCacheSend == true) goto L29;
        this.genCacheSend = true;
        AndroidUtilities.runOnUIThread(this.uiRunnableGenerateCache);
    L29:
        r5 = -1;
        if (this.allowDrawFramesWhileCacheGenerating == false) goto L37;
        if (this.nativePtr != null) goto L35;
        String r6 = this.args.file.toString();
        NativePtrArgs r03 = this.args;
        this.nativePtr = RLottieNative.createFromFile(r6, r03.json, null, r03.colorReplacement, r03.fitzModifier, this.layerColors);
    L35:
        if (this.nativePtr == null) goto L37;
        r5 = this.nativePtr.getFrame(this.currentFrame, r13, r14);
        goto L37
    L14:
        if (this.allowDrawFramesWhileCacheGenerating == false) goto L22;
        if (this.nativePtr == null) goto L22;
        this.nativePtr.recycle();     // Catch: Exception -> L19
        this.nativePtr = null;     // Catch: Exception -> L19
    L19:
        e = move-exception;
        FileLog.e(e);
    L21:
        r5 = r0.getFrame(this.currentFrame, r13, r14);
        goto L22
    L5:
        r1 = 1;
        goto L7
    }

    public final void multiplySpeed(float r2) {
        this.speedMultiply *= r2;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        this.applyTransformation = true;
    }

    @Override // org.telegram.messenger.utils.BitmapsCache.Cacheable
    public final void prepareForGenerateCache() {
        File r0 = this.args.file;
        int[] r1 = null;
        if (r0 == null) goto L5;
        String r2 = r0.toString();
    L6:
        NativePtrArgs r02 = this.args;
        String r3 = r02.json;
        if (this.createdForFirstFrame == false) goto L9;
        r1 = this.metaData;
    L9:
        RLottieNative r03 = RLottieNative.createFromFile(r2, r3, r1, r02.colorReplacement, r02.fitzModifier, this.layerColors);
        this.generateCacheNative = r03;
        this.generateCacheFramePointer = 0;
        if (r03 != null) goto L15;
        File r8 = this.file;
        if (r8 == null) goto L16;
        r8.delete();
        return;
    L16:
        return;
    L15:
        return;
    L5:
        r2 = null;
        goto L6
    }

    public void recycle(boolean r3) {
        this.isRunning = false;
        this.isRecycled = true;
        checkChoreographer();
        checkRunningTasks();
        if (this.loadFrameTask == null) goto L5;
    L13:
        this.destroyWhenDone = true;
        return;
    L5:
        if (this.cacheGenerateTask != null) goto L13;
        if (this.generatingCache == true) goto L13;
        recycleNativePtr(r3);
        BitmapsCache r32 = this.bitmapsCache;
        if (r32 == null) goto L11;
        r32.recycle();
        this.bitmapsCache = null;
    L11:
        recycleResources();
    }

    public void recycleNativePtr(boolean r3) {
        RLottieNative r0 = this.nativePtr;
        this.nativePtr = null;
        if (r0 == null) goto L10;
        RLottieDrawable$$ExternalSyntheticLambda0 r2 = new RLottieDrawable$$ExternalSyntheticLambda0(r0);
        if (r3 == false) goto L8;
        DispatchQueuePoolBackground.execute(r2);
        return;
    L8:
        Utilities.globalQueue.postRunnable(r2);
        return;
    }

    public final void recycleResources() {
        ArrayList r0 = new ArrayList();
        r0.add(this.renderingBitmap);
        r0.add(this.backgroundBitmap);
        r0.add(this.nextRenderingBitmap);
        this.nextRenderingBitmap = null;
        this.renderingBitmap = null;
        this.backgroundBitmap = null;
        AndroidUtilities.recycleBitmaps(r0);
        if (this.onAnimationEndListener == null) goto L6;
        this.onAnimationEndListener = null;
        return;
    }

    @Override // org.telegram.messenger.utils.BitmapsCache.Cacheable
    public final void releaseForGenerateCache() {
        RLottieNative r0 = this.generateCacheNative;
        if (r0 == null) goto L6;
        r0.recycle();
        this.generateCacheNative = null;
        return;
    }

    public final void removeParentView(ImageReceiver r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        this.parentViews.remove(r2);
        checkCacheCancel();
    }

    public final void replaceColors(int[] r1) {
        this.newReplaceColors = r1;
        requestRedrawColors();
    }

    public final void resetVibrationAfterRestart(boolean r1) {
        this.resetVibrationAfterRestart = r1;
    }

    public final boolean restart() {
        return restart(false);
    }

    public final boolean restart(boolean r3) {
        if (r3 == false) goto L5;
    L11:
        this.autoRepeatPlayCount = 0;
        this.autoRepeat = 2;
        start();
        return true;
    L5:
        if (this.autoRepeat < 2) goto L9;
        if (this.autoRepeatPlayCount != 0) goto L11;
    L9:
        if (this.autoRepeatCount >= 0) goto L11;
        return false;
    }

    public final boolean scheduleNextGetFrame() {
        if (this.loadFrameTask == null) goto L5;
        return false;
    L5:
        if (this.nextRenderingBitmap == null) goto L7;
        return false;
    L7:
        if (canLoadFrames() == true) goto L9;
        return false;
    L9:
        if (ignoreScheduleNextGetFrame() == false) goto L11;
        return false;
    L11:
        if (this.destroyWhenDone == false) goto L13;
        return false;
    L13:
        if (this.isRecycled == false) goto L15;
        return false;
    L15:
        if (this.isRunning == true) goto L21;
        if (this.decodeSingleFrame == true) goto L19;
        return false;
    L19:
        if (this.singleFrameDecoded == false) goto L21;
        return false;
    L21:
        if (this.generatingCache == false) goto L26;
        if (this.allowDrawFramesWhileCacheGenerating == true) goto L26;
        return false;
    L26:
        if (this.newColorUpdates.isEmpty() == true) goto L28;
        this.pendingColorUpdates.putAll(this.newColorUpdates);
        this.newColorUpdates.clear();
    L28:
        int[] r0 = this.newReplaceColors;
        if (r0 == null) goto L31;
        this.pendingReplaceColors = r0;
        this.newReplaceColors = null;
    L31:
        Runnable r02 = this.loadFrameRunnable;
        this.loadFrameTask = r02;
        if (this.shouldLimitFps == false) goto L34;
        Executor r2 = loadFrameRunnableQueueLimitFps;
    L35:
        r2.execute(r02);
        return true;
    L34:
        r2 = loadFrameRunnableQueue;
        goto L35
    }

    public final void setAllowDecodeSingleFrame(boolean r1) {
        this.decodeSingleFrame = r1;
        if (r1 == false) goto L6;
        scheduleNextGetFrame();
        return;
    }

    public final void setAllowDrawFramesWhileCacheGenerating(boolean r1) {
        this.allowDrawFramesWhileCacheGenerating = r1;
    }

    public final void setAllowVibration(boolean r1) {
        this.allowVibration = r1;
    }

    public final void setAutoRepeat(int r3) {
        if (this.autoRepeat == 2) goto L5;
    L9:
        this.autoRepeat = r3;
        return;
    L5:
        if (r3 != 3) goto L9;
        if (this.currentFrame == 0) goto L9;
    }

    public final void setAutoRepeatCount(int r1) {
        this.autoRepeatCount = r1;
    }

    public final void setAutoRepeatTimeout(long r1) {
        this.autoRepeatTimeout = r1;
    }

    public final void setCurrentFrame(int r2) {
        setCurrentFrame(r2, true);
    }

    public final void setCurrentFrame(int r2, boolean r3) {
        setCurrentFrame(r2, r3, false);
    }

    public final void setCurrentFrame(int r4, boolean r5, boolean r6) {
        if (r4 >= 0) goto L4;
        return;
    L4:
        if (r4 <= this.metaData[0]) goto L6;
        return;
    L6:
        if (this.currentFrame != r4) goto L9;
        if (r6 == true) goto L9;
        return;
    L9:
        this.currentFrame = r4;
        this.nextFrameIsLast = false;
        this.singleFrameDecoded = false;
        if (this.invalidateOnProgressSet == false) goto L15;
        this.isInvalid = true;
        if (this.loadFrameTask == null) goto L15;
        this.doNotRemoveInvalidOnFrameReady = true;
    L15:
        if (r5 == false) goto L18;
        if (r6 == true) goto L18;
    L22:
        if (r5 == false) goto L24;
    L26:
        if (r6 == false) goto L31;
        if (this.isRunning == true) goto L31;
        this.isRunning = true;
        checkChoreographer();
    L31:
        if (scheduleNextGetFrame() == false) goto L38;
        if (r5 == false) goto L41;
    L39:
        invalidateSelf();
        return;
    L41:
        this.frameWaitSync.await();     // Catch: Exception -> L35
    L37:
        this.frameWaitSync = null;
    L35:
        e = move-exception;
        FileLog.e(e);
        goto L37
    L38:
        this.forceFrameRedraw = true;
        goto L39
    L24:
        if (this.loadFrameTask != null) goto L26;
        this.frameWaitSync = new CountDownLatch(1);
    L18:
        if (this.waitingForNextTask == false) goto L22;
        if (this.nextRenderingBitmap == null) goto L22;
        this.backgroundBitmap = this.nextRenderingBitmap;
        this.nextRenderingBitmap = null;
        this.loadFrameTask = null;
        this.waitingForNextTask = false;
        goto L22
    }

    public final boolean setCustomEndFrame(int r3) {
        if (this.customEndFrame != r3) goto L5;
    L9:
        return false;
    L5:
        if (r3 > this.metaData[0]) goto L9;
        this.customEndFrame = r3;
        return true;
    }

    public final void setGeneratingFrame(int r1) {
        this.generateCacheFramePointer = r1;
    }

    public final void setInvalidateOnProgressSet(boolean r1) {
        this.invalidateOnProgressSet = r1;
    }

    public final void setLayerColor(String r2, int r3) {
        this.newColorUpdates.put(r2, Integer.valueOf(r3));
        requestRedrawColors();
    }

    public final void setMasterParent(View r2) {
        this.masterParent = new WeakReference(r2);
    }

    public final void setOnAnimationEndListener(Runnable r1) {
        this.onAnimationEndListener = r1;
    }

    public final void setOnFinishCallback(Runnable r2, int r3) {
        if (r2 == null) goto L6;
        this.onFinishCallback = new WeakReference(r2);
        this.finishFrame = r3;
        return;
    L6:
        if (this.onFinishCallback == null) goto L9;
        this.onFinishCallback = null;
        return;
    }

    public final void setPlayInDirectionOfCustomEndFrame(boolean r1) {
        this.playInDirectionOfCustomEndFrame = r1;
    }

    public final void setProgress(float r2) {
        setProgress(r2, true);
    }

    public final void setProgress(float r3, boolean r4) {
        float r0 = 0.0f;
        if (r3 >= 0.0f) goto L5;
    L4:
        r3 = r0;
    L8:
        setCurrentFrame((int) (this.metaData[0] * r3), r4);
        return;
    L5:
        r0 = 1.0f;
        if (r3 <= 1.0f) goto L8;
        goto L8
    }

    public final void setProgressMs(long r7) {
        int[] r0 = this.metaData;
        if (r0[0] == 0) goto L9;
        int r02 = r0[1];
        if (r02 == 0) goto L10;
        setCurrentFrame(Math.round(Math.max(0, r7) / (1000.0f / r02)) % this.metaData[0], true, true);
        return;
    L10:
        return;
    }

    public final void setVibrationPattern(HashMap<Integer, Integer> r1) {
        this.vibrationPattern = r1;
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        if (this.isRunning == false) goto L5;
        return;
    L5:
        if (this.autoRepeat < 2) goto L9;
        if (this.autoRepeatPlayCount == 0) goto L9;
        return;
    L9:
        if (this.customEndFrame == this.currentFrame) goto L20;
        this.isRunning = true;
        this.isPaused = false;
        if (this.invalidateOnProgressSet == false) goto L16;
        this.isInvalid = true;
        if (this.loadFrameTask == null) goto L16;
        this.doNotRemoveInvalidOnFrameReady = true;
    L16:
        scheduleNextGetFrame();
        invalidateInternal();
        checkChoreographer();
        return;
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.isRunning = false;
        checkChoreographer();
    }

    public void updateCurrentFrame(long r1, boolean r3) {
        checkChoreographerAfterDrawCall();
        updateCurrentFrameInternal();
    }
}
