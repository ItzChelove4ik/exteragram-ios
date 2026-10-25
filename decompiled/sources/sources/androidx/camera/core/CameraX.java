package androidx.camera.core;

import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.RetryPolicy;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraPresenceProvider;
import androidx.camera.core.impl.CameraProviderExecutionState;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.CameraValidator;
import androidx.camera.core.impl.MetadataHolderService;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.QuirkSettingsHolder;
import androidx.camera.core.impl.QuirkSettingsLoader;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.StreamSpecsCalculator;
import androidx.camera.core.internal.StreamSpecsCalculatorImpl;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.HandlerCompat;
import androidx.core.util.Preconditions;
import androidx.tracing.Trace;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class CameraX {
    private static final Object MIN_LOG_LEVEL_LOCK = null;
    private static final SparseArray<Integer> sMinLogLevelReferenceCountMap = null;
    private final Executor mCameraExecutor;
    private CameraFactory mCameraFactory;
    private final CameraPresenceProvider mCameraPresenceProvider;
    final CameraRepository mCameraRepository;
    private CameraUseCaseAdapterProvider mCameraUseCaseAdapterProvider;
    private final CameraXConfig mCameraXConfig;
    private UseCaseConfigFactory mDefaultConfigFactory;
    private final ListenableFuture<Void> mInitInternalFuture;
    private InternalInitState mInitState;
    private final Object mInitializeLock;
    private final Integer mMinLogLevel;
    private final RetryPolicy mRetryPolicy;
    private final Lazy<RotationProvider> mRotationProvider;
    private final Handler mSchedulerHandler;
    private final HandlerThread mSchedulerThread;
    private ListenableFuture<Void> mShutdownInternalFuture;
    private StreamSpecsCalculator mStreamSpecsCalculator;
    private CameraDeviceSurfaceManager mSurfaceManager;

    public enum InternalInitState extends Enum<InternalInitState> {
        private static final /* synthetic */ InternalInitState[] $VALUES = null;
        public static final InternalInitState INITIALIZED = null;
        public static final InternalInitState INITIALIZING = null;
        public static final InternalInitState INITIALIZING_ERROR = null;
        public static final InternalInitState SHUTDOWN = null;
        public static final InternalInitState UNINITIALIZED = null;

        private static /* synthetic */ InternalInitState[] $values() {
            return new InternalInitState[]{UNINITIALIZED, INITIALIZING, INITIALIZING_ERROR, INITIALIZED, SHUTDOWN};
        }

        static {
            UNINITIALIZED = new InternalInitState("UNINITIALIZED", 0);
            INITIALIZING = new InternalInitState("INITIALIZING", 1);
            INITIALIZING_ERROR = new InternalInitState("INITIALIZING_ERROR", 2);
            INITIALIZED = new InternalInitState("INITIALIZED", 3);
            SHUTDOWN = new InternalInitState("SHUTDOWN", 4);
            $VALUES = $values();
        }

        InternalInitState(String r1, int r2) {
        }

        public static InternalInitState valueOf(String r1) {
            return (InternalInitState) Enum.valueOf(InternalInitState.class, r1);
        }

        public static InternalInitState[] values() {
            return (InternalInitState[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$8_otjOOyRRm_C0r2Zpi3kJMB9Ok(CameraX r2, CallbackToFutureAdapter.Completer r3) {
        r2.mCameraFactory.shutdown();
        if (r2.mSchedulerThread == null) goto L8;
        Executor r0 = r2.mCameraExecutor;
        if ((r0 instanceof CameraExecutor) == false) goto L7;
        ((CameraExecutor) r0).deinit();
    L7:
        r2.mSchedulerThread.quit();
    L8:
        r3.set(null);
    }

    /* renamed from: $r8$lambda$AUcoLk-VxZPZPQwDfvjk9zGYTHA, reason: not valid java name */
    public static /* synthetic */ RotationProvider m583$r8$lambda$AUcoLkVxZPZPQwDfvjk9zGYTHA(Context r1) {
        return new RotationProvider(r1);
    }

    public static /* synthetic */ Object $r8$lambda$V_m79wpNBseSethatrHxRovylmc(CameraX r2, CallbackToFutureAdapter.Completer r3) {
        r2.mCameraPresenceProvider.shutdown();
        if (r2.mRotationProvider.isInitialized() == false) goto L5;
        r2.mRotationProvider.getValue().shutdown();
    L5:
        r2.mCameraRepository.deinit().addListener(new CameraX$$ExternalSyntheticLambda3(r2, r3), r2.mCameraExecutor);
        return "CameraX shutdownInternal";
    }

    public static /* synthetic */ Object $r8$lambda$gLtygYQ6ncqgp9GL3DZrb6RJSyE(CameraX r7, Context r8, CallbackToFutureAdapter.Completer r9) {
        r7.initAndRetryRecursively(r7.mCameraExecutor, SystemClock.elapsedRealtime(), 1, r8, r9);
        return "CameraX initInternal";
    }

    public static /* synthetic */ void $r8$lambda$gWQLJvFf0jycoyEFmkMCIA6OUZI(CameraX r0, Executor r1, long r2, int r4, Context r5, CallbackToFutureAdapter.Completer r6) {
        r0.initAndRetryRecursively(r1, r2, r4 + 1, r5, r6);
    }

    /* renamed from: $r8$lambda$rcLbpxfditYP58cYRR-HyT98STc, reason: not valid java name */
    public static /* synthetic */ void m584$r8$lambda$rcLbpxfditYP58cYRRHyT98STc(CameraX r16, Context r17, Executor r18, int r19, CallbackToFutureAdapter.Completer r20, long r21) {
        r16.getClass();
        Trace.beginSection("CX:initAndRetryRecursively");
        CameraFactory.Provider r2 = r16.mCameraXConfig.getCameraFactoryProvider(null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        if (r2 == null) goto L33;
        CameraThreadConfig r4 = CameraThreadConfig.create(r16.mCameraExecutor, r16.mSchedulerHandler);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        CameraSelector r5 = r16.mCameraXConfig.getAvailableCamerasLimiter(null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        CameraValidator r0 = CameraValidator.create(r17, r5);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        long r6 = r16.mCameraXConfig.getCameraOpenRetryMaxTimeoutInMillisWhileResuming();     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        UseCaseConfigFactory.Provider r8 = r16.mCameraXConfig.getUseCaseConfigFactoryProvider(null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        if (r8 == null) goto L31;
        r16.mDefaultConfigFactory = r8.newInstance(r17);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        StreamSpecsCalculatorImpl r9 = new StreamSpecsCalculatorImpl(r16.mDefaultConfigFactory, null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mStreamSpecsCalculator = r9;     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mCameraFactory = r2.newInstance(r17, r4, r5, r6, r16.mCameraXConfig, r9);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        CameraDeviceSurfaceManager.Provider r22 = r16.mCameraXConfig.getDeviceSurfaceManagerProvider(null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        if (r22 == null) goto L29;
        CameraDeviceSurfaceManager r23 = r22.newInstance(r17, r16.mCameraFactory.getCameraManager(), r16.mCameraFactory.getAvailableCameraIds());     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mSurfaceManager = r23;     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mStreamSpecsCalculator.setCameraDeviceSurfaceManager(r23);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        if ((r18 instanceof CameraExecutor) == false) goto L18;
        ((CameraExecutor) r18).init(r16.mCameraFactory);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
    L18:
        r16.mCameraRepository.init(r16.mCameraFactory);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        CameraCoordinator r24 = r16.mCameraFactory.getCameraCoordinator();     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r24.init(r16.mCameraRepository);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mCameraUseCaseAdapterProvider = new CameraUseCaseAdapterProviderImpl(r16.mCameraRepository, r24, r16.mDefaultConfigFactory, r16.mStreamSpecsCalculator);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        Iterator<CameraInternal> r25 = r16.mCameraRepository.getCameras().iterator();     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
    L20:
        if (r25.hasNext() == false) goto L22;
        r25.next().getCameraInfoInternal().setCameraUseCaseAdapterProvider(r16.mCameraUseCaseAdapterProvider);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        goto L20
    L22:
        r16.mCameraPresenceProvider.startup(r0, r16.mCameraFactory, r16.mCameraRepository);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mCameraPresenceProvider.addDependentInternalListener(r16.mSurfaceManager);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r16.mCameraPresenceProvider.addDependentInternalListener(r16.mCameraFactory.getCameraCoordinator());     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r0.validateOnFirstInit(r16.mCameraRepository);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        if (r19 <= 1) goto L25;
        r16.traceExecutionState(null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
    L25:
        r16.setStateToInitialized();     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        r20.set(null);     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
        Trace.endSection();
        return;
    L29:
        throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraDeviceSurfaceManager."));     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
    L31:
        throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing UseCaseConfigFactory."));     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
    L33:
        throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraFactory."));     // Catch: RuntimeException -> L12 InitializationException -> L14 CameraValidator.CameraIdListIncorrectException -> L16 Throwable -> L59
    L59:
        th = move-exception;
        Trace.endSection();
        throw th;
    L14:
        e = e;
    L34:
        CameraProviderExecutionState r26 = new CameraProviderExecutionState(r21, r19, e);     // Catch: Throwable -> L59
        RetryPolicy.RetryConfig r82 = r16.mRetryPolicy.onRetryDecisionRequested(r26);     // Catch: Throwable -> L59
        r16.traceExecutionState(r26);     // Catch: Throwable -> L59
        if (r82.shouldRetry() == true) goto L37;
    L39:
        Object r27 = r16.mInitializeLock;     // Catch: Throwable -> L59
        monitor-enter(r27);     // Catch: Throwable -> L59
        r16.mInitState = InternalInitState.INITIALIZING_ERROR;     // Catch: Throwable -> L56
        monitor-exit(r27);     // Catch: Throwable -> L56
    L44:
        if (r82.shouldCompleteWithoutFailure() == false) goto L47;
        r16.setStateToInitialized();     // Catch: Throwable -> L59
        r20.set(null);     // Catch: Throwable -> L59
    L54:
        Trace.endSection();
        return;
    L47:
        if ((e instanceof CameraValidator.CameraIdListIncorrectException) == false) goto L50;
        String r28 = "Device reporting less cameras than anticipated. On real devices: Retrying initialization might resolve temporary camera errors. On emulators: Ensure virtual camera configuration matches supported camera features as reported by PackageManager#hasSystemFeature. Available cameras: " + ((CameraValidator.CameraIdListIncorrectException) e).getAvailableCameraCount();     // Catch: Throwable -> L59
        Logger.e("CameraX", r28, e);     // Catch: Throwable -> L59
        r20.setException(new InitializationException(new CameraUnavailableException(3, r28)));     // Catch: Throwable -> L59
    L53:
        r16.mCameraPresenceProvider.shutdown();     // Catch: Throwable -> L59
        goto L54
    L50:
        if ((e instanceof InitializationException) == false) goto L52;
        r20.setException(e);     // Catch: Throwable -> L59
        goto L53
    L52:
        r20.setException(new InitializationException(e));     // Catch: Throwable -> L59
    L56:
        th = move-exception;
        throw th;     // Catch: Throwable -> L59
    L37:
        if (r19 >= Integer.MAX_VALUE) goto L39;
        Logger.w("CameraX", "Retry init. Start time " + r21 + " current time " + SystemClock.elapsedRealtime(), e);     // Catch: Throwable -> L59
        HandlerCompat.postDelayed(r16.mSchedulerHandler, new CameraX$$ExternalSyntheticLambda5(r16, r18, r21, r19, r17, r20), "retry_token", r82.getRetryDelayInMillis());     // Catch: Throwable -> L59
    L16:
        e = e;
    L12:
        e = e;
        goto L34
    }

    static {
        MIN_LOG_LEVEL_LOCK = new Object();
        sMinLogLevelReferenceCountMap = new SparseArray();
    }

    public CameraX(Context r2, CameraXConfig.Provider r3) {
        this(r2, r3, new QuirkSettingsLoader());
    }

    public CameraX(Context r4, CameraXConfig.Provider r5, Function<Context, QuirkSettings> r6) {
        this.mCameraRepository = new CameraRepository();
        this.mInitializeLock = new Object();
        this.mInitState = InternalInitState.UNINITIALIZED;
        this.mShutdownInternalFuture = Futures.immediateFuture(null);
        Context r1 = ContextUtil.getPersistentApplicationContext(r4);
        if (r5 == null) goto L5;
        this.mCameraXConfig = r5.getCameraXConfig();
    L8:
        updateQuirkSettings(r1, this.mCameraXConfig.getQuirkSettings(), r6);
        Executor r42 = this.mCameraXConfig.getCameraExecutor(null);
        Handler r52 = this.mCameraXConfig.getSchedulerHandler(null);
        if (r42 != null) goto L11;
        r42 = new CameraExecutor();
    L11:
        this.mCameraExecutor = r42;
        if (r52 != null) goto L14;
        HandlerThread r53 = new HandlerThread("CameraX-scheduler", 10);
        this.mSchedulerThread = r53;
        r53.start();
        this.mSchedulerHandler = HandlerCompat.createAsync(r53.getLooper());
    L15:
        Integer r54 = (Integer) this.mCameraXConfig.retrieveOption(CameraXConfig.OPTION_MIN_LOGGING_LEVEL, null);
        this.mMinLogLevel = r54;
        increaseMinLogLevelReference(r54);
        this.mRetryPolicy = new RetryPolicy.Builder(this.mCameraXConfig.getCameraProviderInitRetryPolicy()).build();
        this.mCameraPresenceProvider = new CameraPresenceProvider(r42, CameraXExecutors.newHandlerExecutor(this.mSchedulerHandler));
        this.mRotationProvider = LazyKt.lazy(new CameraX$$ExternalSyntheticLambda0(r1));
        this.mInitInternalFuture = initInternal(r1);
        return;
    L14:
        this.mSchedulerThread = null;
        this.mSchedulerHandler = r52;
        goto L15
    L5:
        CameraXConfig.Provider r43 = getConfigProvider(r4);
        if (r43 == null) goto L17;
        this.mCameraXConfig = r43.getCameraXConfig();
        goto L8
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("CameraX is not configured properly. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");
        throw null;
    }

    private static void decreaseMinLogLevelReference(Integer r3) {
        Object r0 = MIN_LOG_LEVEL_LOCK;
        monitor-enter(r0);
        if (r3 != null) goto L9;
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        SparseArray<Integer> r1 = sMinLogLevelReferenceCountMap;     // Catch: Throwable -> L7
        int r2 = r1.get(r3.intValue()).intValue() - 1;
        if (r2 != 0) goto L12;
        r1.remove(r3.intValue());     // Catch: Throwable -> L7
    L13:
        updateOrResetMinLogLevel();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L12:
        r1.put(r3.intValue(), Integer.valueOf(r2));     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    private static CameraXConfig.Provider getConfigProvider(Context r5) {
        ComponentCallbacks2 r1 = ContextUtil.getApplication(r5);
        if ((r1 instanceof CameraXConfig.Provider) == false) goto L32;
        return (CameraXConfig.Provider) r1;
    L32:
        Context r52 = ContextUtil.getPersistentApplicationContext(r5);     // Catch: NullPointerException -> L10 IllegalAccessException -> L12 NoSuchMethodException -> L14 InvocationTargetException -> L16 InstantiationException -> L18 ClassNotFoundException -> L20 PackageManager.NameNotFoundException -> L22
        Bundle r53 = r52.getPackageManager().getServiceInfo(new ComponentName(r52, MetadataHolderService.class), 640).metaData;     // Catch: NullPointerException -> L10 IllegalAccessException -> L12 NoSuchMethodException -> L14 InvocationTargetException -> L16 InstantiationException -> L18 ClassNotFoundException -> L20 PackageManager.NameNotFoundException -> L22
        if (r53 == null) goto L24;
        String r54 = r53.getString("androidx.camera.core.impl.MetadataHolderService.DEFAULT_CONFIG_PROVIDER");     // Catch: NullPointerException -> L10 IllegalAccessException -> L12 NoSuchMethodException -> L14 InvocationTargetException -> L16 InstantiationException -> L18 ClassNotFoundException -> L20 PackageManager.NameNotFoundException -> L22
    L25:
        if (r54 != null) goto L29;
        Logger.e("CameraX", "No default CameraXConfig.Provider specified in meta-data. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");     // Catch: NullPointerException -> L10 IllegalAccessException -> L12 NoSuchMethodException -> L14 InvocationTargetException -> L16 InstantiationException -> L18 ClassNotFoundException -> L20 PackageManager.NameNotFoundException -> L22
        return null;
    L29:
        return (CameraXConfig.Provider) Class.forName(r54).getDeclaredConstructor(null).newInstance(null);
    L24:
        r54 = null;
    L22:
        e = e;
    L30:
        Logger.e("CameraX", "Failed to retrieve default CameraXConfig.Provider from meta-data", e);
        return null;
    L20:
        e = e;
    L12:
        e = e;
    L18:
        e = e;
    L14:
        e = e;
    L10:
        e = e;
    L16:
        e = e;
        goto L30
    }

    private static void increaseMinLogLevelReference(Integer r5) {
        Object r0 = MIN_LOG_LEVEL_LOCK;
        monitor-enter(r0);
        if (r5 != null) goto L9;
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L9:
        Preconditions.checkArgumentInRange(r5.intValue(), 3, 6, "minLogLevel");     // Catch: Throwable -> L7
        SparseArray<Integer> r1 = sMinLogLevelReferenceCountMap;     // Catch: Throwable -> L7
        int r3 = 1;
        if (r1.get(r5.intValue()) == null) goto L12;
        r3 = 1 + r1.get(r5.intValue()).intValue();     // Catch: Throwable -> L7
    L12:
        r1.put(r5.intValue(), Integer.valueOf(r3));     // Catch: Throwable -> L7
        updateOrResetMinLogLevel();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void initAndRetryRecursively(Executor r9, long r10, int r12, Context r13, CallbackToFutureAdapter.Completer<Void> r14) {
        r9.execute(new CameraX$$ExternalSyntheticLambda4(this, r13, r9, r12, r14, r10));
    }

    private ListenableFuture<Void> initInternal(Context r4) {
        Object r0 = this.mInitializeLock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.mInitState != InternalInitState.UNINITIALIZED) goto L7;
        boolean r1 = true;
    L8:
        Preconditions.checkState(r1, "CameraX.initInternal() should only be called once per instance");     // Catch: Throwable -> L11
        this.mInitState = InternalInitState.INITIALIZING;     // Catch: Throwable -> L11
        ListenableFuture<Void> r3 = CallbackToFutureAdapter.getFuture(new CameraX$$ExternalSyntheticLambda1(this, r4));     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r3;
    L7:
        r1 = false;
        goto L8
    }

    private void setStateToInitialized() {
        Object r0 = this.mInitializeLock;
        monitor-enter(r0);
        this.mInitState = InternalInitState.INITIALIZED;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private ListenableFuture<Void> shutdownInternal() {
        Object r0 = this.mInitializeLock;
        monitor-enter(r0);
        this.mSchedulerHandler.removeCallbacksAndMessages("retry_token");     // Catch: Throwable -> L17
        int r1 = this.mInitState.ordinal();     // Catch: Throwable -> L17
        if (r1 != 0) goto L7;
        this.mInitState = InternalInitState.SHUTDOWN;     // Catch: Throwable -> L17
        ListenableFuture<Void> r3 = Futures.immediateFuture(null);     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        return r3;
    L7:
        if (r1 == 1) goto L20;
        if (r1 != 2) goto L11;
    L13:
        this.mInitState = InternalInitState.SHUTDOWN;     // Catch: Throwable -> L17
        decreaseMinLogLevelReference(this.mMinLogLevel);     // Catch: Throwable -> L17
        this.mShutdownInternalFuture = CallbackToFutureAdapter.getFuture(new CameraX$$ExternalSyntheticLambda2(this));     // Catch: Throwable -> L17
    L14:
        ListenableFuture<Void> r32 = this.mShutdownInternalFuture;     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        return r32;
    L11:
        if (r1 == 3) goto L13;
    L20:
        throw new IllegalStateException("CameraX could not be shutdown when it is initializing.");     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        throw th;
    }

    private void traceExecutionState(RetryPolicy.ExecutionState r1) {
        if (Trace.isEnabled() == false) goto L9;
        if (r1 == null) goto L6;
        int r0 = r1.getStatus();
    L7:
        Trace.setCounter("CX:CameraProvider-RetryStatus", r0);
        return;
    L6:
        r0 = -1;
        goto L7
    }

    private static void updateOrResetMinLogLevel() {
        SparseArray<Integer> r0 = sMinLogLevelReferenceCountMap;
        if (r0.size() != 0) goto L7;
        Logger.resetMinLogLevel();
        return;
    L7:
        if (r0.get(3) == null) goto L11;
        Logger.setMinLogLevel(3);
        return;
    L11:
        if (r0.get(4) == null) goto L15;
        Logger.setMinLogLevel(4);
        return;
    L15:
        if (r0.get(5) == null) goto L19;
        Logger.setMinLogLevel(5);
        return;
    L19:
        if (r0.get(6) == null) goto L22;
        Logger.setMinLogLevel(6);
        return;
    }

    private static void updateQuirkSettings(Context r1, QuirkSettings r2, Function<Context, QuirkSettings> r3) {
        if (r2 == null) goto L5;
        Logger.d("CameraX", "QuirkSettings from CameraXConfig: " + r2);
    L6:
        if (r2 != null) goto L8;
        r2 = QuirkSettingsHolder.DEFAULT;
        Logger.d("CameraX", "QuirkSettings by default: " + r2);
    L8:
        QuirkSettingsHolder.instance().set(r2);
        return;
    L5:
        r2 = r3.apply(r1);
        Logger.d("CameraX", "QuirkSettings from app metadata: " + r2);
        goto L6
    }

    public CameraPresenceProvider getCameraAvailabilityProvider() {
        return this.mCameraPresenceProvider;
    }

    public CameraFactory getCameraFactory() {
        CameraFactory r0 = this.mCameraFactory;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("CameraX not initialized yet.");
        return null;
    }

    public CameraRepository getCameraRepository() {
        return this.mCameraRepository;
    }

    public CameraUseCaseAdapterProvider getCameraUseCaseAdapterProvider() {
        CameraUseCaseAdapterProvider r0 = this.mCameraUseCaseAdapterProvider;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("CameraX not initialized yet.");
        return null;
    }

    public ListenableFuture<Void> getInitializeFuture() {
        return this.mInitInternalFuture;
    }

    public RotationProvider getRotationProvider() {
        return this.mRotationProvider.getValue();
    }

    public ListenableFuture<Void> shutdown() {
        return shutdownInternal();
    }
}
