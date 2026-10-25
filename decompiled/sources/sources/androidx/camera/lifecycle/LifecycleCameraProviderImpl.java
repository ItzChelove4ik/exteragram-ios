package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraPresenceListener;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.ConcurrentCamera;
import androidx.camera.core.LegacySessionConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraPresenceProvider;
import androidx.camera.core.impl.ExtendedCameraConfigProviderStore;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.LifecycleCameraRepository;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import androidx.tracing.Trace;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0003¢\u0006\u0004\b\u0006\u0010\u0004J#\u0010\u000b\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0003¢\u0006\u0004\b\u000b\u0010\fJG\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J7\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001a2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\"\u0010!J\u001f\u0010'\u001a\u00020&2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020$H\u0002¢\u0006\u0004\b'\u0010(J)\u00100\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010)\u001a\u00020\t2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*H\u0000¢\u0006\u0004\b.\u0010/J\u0017\u00103\u001a\u00020\u00052\u0006\u0010+\u001a\u00020*H\u0000¢\u0006\u0004\b1\u00102J\u001f\u00107\u001a\b\u0012\u0004\u0012\u00020-0,2\b\b\u0002\u00104\u001a\u00020\u001fH\u0000¢\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u00020\u0005H\u0017¢\u0006\u0004\b8\u0010\u0004J\u0017\u00109\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000fH\u0016¢\u0006\u0004\b9\u0010:J7\u0010=\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u000f2\u0016\u0010<\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001d0;\"\u0004\u0018\u00010\u001dH\u0017¢\u0006\u0004\b=\u0010>J'\u0010=\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0017¢\u0006\u0004\b=\u0010?J\u001f\u0010=\u001a\u00020C2\u000e\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010A0@H\u0017¢\u0006\u0004\b=\u0010DJ\u0017\u0010E\u001a\u00020$2\u0006\u0010#\u001a\u00020\u000fH\u0016¢\u0006\u0004\bE\u0010FJ\u001d\u0010J\u001a\u00020\u00052\f\u0010I\u001a\b\u0012\u0004\u0012\u00020H0GH\u0017¢\u0006\u0004\bJ\u0010KJ\u001d\u0010M\u001a\u00020\u00052\f\u0010L\u001a\b\u0012\u0004\u0012\u00020H0GH\u0017¢\u0006\u0004\bM\u0010KR\u0014\u0010N\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR*\u0010Q\u001a\u0004\u0018\u00010P8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\bQ\u0010R\u0012\u0004\bW\u0010\u0004\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001e\u0010X\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bX\u0010YR$\u0010[\u001a\u0010\u0012\f\u0012\n Z*\u0004\u0018\u00010-0-0,8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b[\u0010YR\u0018\u0010\\\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0018\u0010_\u001a\u0004\u0018\u00010^8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R*\u0010)\u001a\u0004\u0018\u00010\t8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b)\u0010a\u0012\u0004\bf\u0010\u0004\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR \u0010i\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020h0g8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bi\u0010jR$\u0010n\u001a\u0012\u0012\u0004\u0012\u00020l0kj\b\u0012\u0004\u0012\u00020l`m8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bn\u0010oR\u0014\u0010p\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bp\u0010qR$\u0010s\u001a\u00020r2\u0006\u0010s\u001a\u00020r8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR0\u0010}\u001a\b\u0012\u0004\u0012\u00020$0@2\f\u0010x\u001a\b\u0012\u0004\u0012\u00020$0@8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u001a\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020$0@8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b~\u0010zR\"\u0010\u0081\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0@0@8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0080\u0001\u0010z¨\u0006\u0082\u0001"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraPresenceListener;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "shutdownInternal", "Landroidx/camera/core/CameraX;", "newCameraX", "Landroid/content/Context;", "newContext", "initInternal", "(Landroidx/camera/core/CameraX;Landroid/content/Context;)V", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Landroidx/camera/core/CameraSelector;", "primaryCameraSelector", "secondaryCameraSelector", "Landroidx/camera/core/CompositionSettings;", "primaryCompositionSettings", "secondaryCompositionSettings", "Landroidx/camera/core/SessionConfig;", "sessionConfig", "Landroidx/camera/core/Camera;", "bindToLifecycleInternal", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/CompositionSettings;Landroidx/camera/core/CompositionSettings;Landroidx/camera/core/SessionConfig;)Landroidx/camera/core/Camera;", "Lkotlin/Pair;", "getSelectorsWithSessionFilter", "(Landroidx/camera/core/SessionConfig;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/CameraSelector;)Lkotlin/Pair;", "Landroidx/camera/core/UseCase;", "useCase", _UrlKt.FRAGMENT_ENCODE_SET, "isVideoCapture", "(Landroidx/camera/core/UseCase;)Z", "isPreview", "cameraSelector", "Landroidx/camera/core/CameraInfo;", "cameraInfo", "Landroidx/camera/core/impl/CameraConfig;", "getCameraConfig", "(Landroidx/camera/core/CameraSelector;Landroidx/camera/core/CameraInfo;)Landroidx/camera/core/impl/CameraConfig;", "context", "Landroidx/camera/core/CameraXConfig;", "cameraXConfig", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "initAsync$camera_lifecycle", "(Landroid/content/Context;Landroidx/camera/core/CameraXConfig;)Lcom/google/common/util/concurrent/ListenableFuture;", "initAsync", "configure$camera_lifecycle", "(Landroidx/camera/core/CameraXConfig;)V", "configure", "clearConfigProvider", "shutdownAsync$camera_lifecycle", "(Z)Lcom/google/common/util/concurrent/ListenableFuture;", "shutdownAsync", "unbindAll", "hasCamera", "(Landroidx/camera/core/CameraSelector;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "useCases", "bindToLifecycle", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/SessionConfig;)Landroidx/camera/core/Camera;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/ConcurrentCamera$SingleCameraConfig;", "singleCameraConfigs", "Landroidx/camera/core/ConcurrentCamera;", "(Ljava/util/List;)Landroidx/camera/core/ConcurrentCamera;", "getCameraInfo", "(Landroidx/camera/core/CameraSelector;)Landroidx/camera/core/CameraInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraIdentifier;", "addedCameraIds", "onCamerasAdded", "(Ljava/util/Set;)V", "removedCameraIds", "onCamerasRemoved", "lock", "Ljava/lang/Object;", "Landroidx/camera/core/CameraXConfig$Provider;", "cameraXConfigProvider", "Landroidx/camera/core/CameraXConfig$Provider;", "getCameraXConfigProvider$camera_lifecycle", "()Landroidx/camera/core/CameraXConfig$Provider;", "setCameraXConfigProvider$camera_lifecycle", "(Landroidx/camera/core/CameraXConfig$Provider;)V", "getCameraXConfigProvider$camera_lifecycle$annotations", "cameraXInitializeFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "kotlin.jvm.PlatformType", "cameraXShutdownFuture", "cameraX", "Landroidx/camera/core/CameraX;", "Landroidx/camera/lifecycle/LifecycleCameraRepository;", "lifecycleCameraRepository", "Landroidx/camera/lifecycle/LifecycleCameraRepository;", "Landroid/content/Context;", "getContext$camera_lifecycle", "()Landroid/content/Context;", "setContext$camera_lifecycle", "(Landroid/content/Context;)V", "getContext$camera_lifecycle$annotations", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/AdapterCameraInfo;", "cameraInfoMap", "Ljava/util/Map;", "Ljava/util/HashSet;", "Landroidx/camera/lifecycle/LifecycleCameraRepository$Key;", "Lkotlin/collections/HashSet;", "lifecycleCameraKeys", "Ljava/util/HashSet;", "isInitialized", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "cameraOperatingMode", "getCameraOperatingMode", "()I", "setCameraOperatingMode", "(I)V", "cameraInfos", "getActiveConcurrentCameraInfos", "()Ljava/util/List;", "setActiveConcurrentCameraInfos", "(Ljava/util/List;)V", "activeConcurrentCameraInfos", "getAvailableCameraInfos", "availableCameraInfos", "getAvailableConcurrentCameraInfos", "availableConcurrentCameraInfos", "camera-lifecycle"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLifecycleCameraProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LifecycleCameraProviderImpl.kt\nandroidx/camera/lifecycle/LifecycleCameraProviderImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Trace.kt\nandroidx/tracing/TraceKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,850:1\n1#2:851\n27#3,5:852\n27#3,5:857\n27#3,5:862\n27#3,5:867\n27#3,5:872\n27#3,5:877\n27#3,5:882\n27#3,5:887\n27#3,5:892\n27#3,5:897\n27#3,5:902\n27#3,3:907\n31#3:912\n27#3,5:913\n1869#4,2:910\n774#4:918\n865#4,2:919\n*S KotlinDebug\n*F\n+ 1 LifecycleCameraProviderImpl.kt\nandroidx/camera/lifecycle/LifecycleCameraProviderImpl\n*L\n163#1:852,5\n228#1:857,5\n245#1:862,5\n259#1:867,5\n267#1:872,5\n283#1:877,5\n306#1:882,5\n329#1:887,5\n349#1:892,5\n495#1:897,5\n506#1:902,5\n605#1:907,3\n605#1:912\n729#1:913,5\n650#1:910,2\n784#1:918\n784#1:919,2\n*E\n"})
/* loaded from: classes3.dex */
public final class LifecycleCameraProviderImpl implements CameraPresenceListener {
    private final Map<CameraIdentifier, AdapterCameraInfo> cameraInfoMap;
    private CameraX cameraX;
    private CameraXConfig.Provider cameraXConfigProvider;
    private ListenableFuture<Void> cameraXInitializeFuture;
    private ListenableFuture<Void> cameraXShutdownFuture;
    private Context context;
    private final HashSet<LifecycleCameraRepository.Key> lifecycleCameraKeys;
    private LifecycleCameraRepository lifecycleCameraRepository;
    private final Object lock;

    /* renamed from: $r8$lambda$1d6fK4V_iwXm_23qvpSvEc-Bbqc, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m627$r8$lambda$1d6fK4V_iwXm_23qvpSvEcBbqc(Function1 r0, Object r1) {
        return initAsync$lambda$0$2(r0, r1);
    }

    public static void $r8$lambda$cqEKuAajCjqIPjMA5N0LIeIbAfA(LifecycleCameraProviderImpl r1) {
        if (r1.isInitialized() == false) goto L6;
        r1.unbindAll();
        r1.lifecycleCameraRepository.removeLifecycleCameras(r1.lifecycleCameraKeys);
        return;
    }

    public static /* synthetic */ ListenableFuture $r8$lambda$eAdXOiGg6aakeLuyk8LcYwz6m9A(CameraX r0, Void r1) {
        return initAsync$lambda$0$1(r0, r1);
    }

    public static /* synthetic */ Void $r8$lambda$fGSKVcSoAXbK7NEn8opAT2ioUoU(LifecycleCameraProviderImpl r0, CameraX r1, Context r2, Void r3) {
        return initAsync$lambda$0$3(r0, r1, r2, r3);
    }

    public static /* synthetic */ Void $r8$lambda$yTT8jiXuM53WIUjzfHGHYzzmhkI(Function1 r0, Object r1) {
        return initAsync$lambda$0$4(r0, r1);
    }

    public LifecycleCameraProviderImpl() {
        this.lock = new Object();
        this.cameraXShutdownFuture = Futures.immediateFuture(null);
        this.cameraInfoMap = new HashMap();
        this.lifecycleCameraKeys = new HashSet();
    }

    public static final /* synthetic */ Camera access$bindToLifecycleInternal(LifecycleCameraProviderImpl r0, LifecycleOwner r1, CameraSelector r2, CameraSelector r3, CompositionSettings r4, CompositionSettings r5, SessionConfig r6) {
        return r0.bindToLifecycleInternal(r1, r2, r3, r4, r5, r6);
    }

    public static final /* synthetic */ List access$getActiveConcurrentCameraInfos(LifecycleCameraProviderImpl r0) {
        return r0.getActiveConcurrentCameraInfos();
    }

    public static final /* synthetic */ CameraConfig access$getCameraConfig(LifecycleCameraProviderImpl r0, CameraSelector r1, CameraInfo r2) {
        return r0.getCameraConfig(r1, r2);
    }

    public static final /* synthetic */ Map access$getCameraInfoMap$p(LifecycleCameraProviderImpl r0) {
        return r0.cameraInfoMap;
    }

    public static final /* synthetic */ int access$getCameraOperatingMode(LifecycleCameraProviderImpl r0) {
        return r0.getCameraOperatingMode();
    }

    public static final /* synthetic */ CameraX access$getCameraX$p(LifecycleCameraProviderImpl r0) {
        return r0.cameraX;
    }

    public static final /* synthetic */ HashSet access$getLifecycleCameraKeys$p(LifecycleCameraProviderImpl r0) {
        return r0.lifecycleCameraKeys;
    }

    public static final /* synthetic */ LifecycleCameraRepository access$getLifecycleCameraRepository$p(LifecycleCameraProviderImpl r0) {
        return r0.lifecycleCameraRepository;
    }

    public static final /* synthetic */ Object access$getLock$p(LifecycleCameraProviderImpl r0) {
        return r0.lock;
    }

    public static final /* synthetic */ Pair access$getSelectorsWithSessionFilter(LifecycleCameraProviderImpl r0, SessionConfig r1, CameraSelector r2, CameraSelector r3) {
        return r0.getSelectorsWithSessionFilter(r1, r2, r3);
    }

    public static final /* synthetic */ boolean access$isPreview(LifecycleCameraProviderImpl r0, UseCase r1) {
        return r0.isPreview(r1);
    }

    public static final /* synthetic */ boolean access$isVideoCapture(LifecycleCameraProviderImpl r0, UseCase r1) {
        return r0.isVideoCapture(r1);
    }

    public static final /* synthetic */ void access$setActiveConcurrentCameraInfos(LifecycleCameraProviderImpl r0, List r1) {
        r0.setActiveConcurrentCameraInfos(r1);
    }

    public static final /* synthetic */ void access$setCameraOperatingMode(LifecycleCameraProviderImpl r0, int r1) {
        r0.setCameraOperatingMode(r1);
    }

    private final Camera bindToLifecycleInternal(LifecycleOwner r17, CameraSelector r18, CameraSelector r19, CompositionSettings r20, CompositionSettings r21, SessionConfig r22) {
        Trace.beginSection("CX:bindToLifecycle-internal");
        Threads.checkMainThread();     // Catch: Throwable -> L28
        Pair r3 = access$getSelectorsWithSessionFilter(this, r22, r18, r19);     // Catch: Throwable -> L28
        CameraSelector r4 = (CameraSelector) r3.component1();     // Catch: Throwable -> L28
        CameraSelector r32 = (CameraSelector) r3.component2();     // Catch: Throwable -> L28
        CameraInternal r7 = r4.select(access$getCameraX$p(this).getCameraRepository().getCameras());     // Catch: Throwable -> L28
        r7.setPrimary(true);     // Catch: Throwable -> L28
        AdapterCameraInfo r9 = (AdapterCameraInfo) getCameraInfo(r4);     // Catch: Throwable -> L28
        if (r32 == null) goto L6;
        CameraInternal r42 = r32.select(access$getCameraX$p(this).getCameraRepository().getCameras());     // Catch: Throwable -> L28
        r42.setPrimary(false);     // Catch: Throwable -> L28
        AdapterCameraInfo r10 = (AdapterCameraInfo) getCameraInfo(r32);     // Catch: Throwable -> L28
        CameraInternal r8 = r42;
    L7:
        CameraIdentifier r33 = CameraIdentifier.Factory.fromAdapterInfos(r9, r10);     // Catch: Throwable -> L28
        LifecycleCamera r43 = access$getLifecycleCameraRepository$p(this).getLifecycleCamera(r17, r33);     // Catch: Throwable -> L28
        Collection<LifecycleCamera> r6 = access$getLifecycleCameraRepository$p(this).getLifecycleCameras();     // Catch: Throwable -> L28
        Iterator<T> r11 = r22.getUseCases().iterator();     // Catch: Throwable -> L28
    L9:
        if (r11.hasNext() == false) goto L20;
        UseCase r12 = (UseCase) r11.next();     // Catch: Throwable -> L28
        Iterator<LifecycleCamera> r13 = r6.iterator();     // Catch: Throwable -> L28
    L12:
        if (r13.hasNext() == false) goto L9;
        LifecycleCamera r14 = r13.next();     // Catch: Throwable -> L28
        if (r14.isBound(r12) == false) goto L12;
        if (Intrinsics.areEqual(r14.getLifecycleOwner(), r17) == true) goto L12;
        StringCompanionObject r1 = StringCompanionObject.INSTANCE;     // Catch: Throwable -> L28
        throw new IllegalStateException(String.format("Use case %s already bound to a different lifecycle.", Arrays.copyOf(new Object[]{r12}, 1)));     // Catch: Throwable -> L28
    L20:
        if (r43 != null) goto L23;
        r43 = access$getLifecycleCameraRepository$p(this).createLifecycleCamera(r17, access$getCameraX$p(this).getCameraUseCaseAdapterProvider().provide(r7, r8, r9, r10, r20, r21), access$getCameraX$p(this).getRotationProvider());     // Catch: Throwable -> L28
    L23:
        if (r22.getUseCases().isEmpty() == true) goto L26;
        access$getLifecycleCameraRepository$p(this).bindToLifecycleCamera(r43, r22, access$getCameraX$p(this).getCameraFactory().getCameraCoordinator());     // Catch: Throwable -> L28
        access$getLifecycleCameraKeys$p(this).add(LifecycleCameraRepository.Key.create(r17, r33));     // Catch: Throwable -> L28
    L26:
        Trace.endSection();
        return r43;
    L6:
        r8 = null;
        r10 = null;
    L28:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static /* synthetic */ Camera bindToLifecycleInternal$default(LifecycleCameraProviderImpl r7, LifecycleOwner r8, CameraSelector r9, CameraSelector r10, CompositionSettings r11, CompositionSettings r12, SessionConfig r13, int r14, Object r15) {
        if ((r14 & 4) == 0) goto L5;
        r10 = null;
    L5:
        CameraSelector r3 = r10;
        if ((r14 & 8) == 0) goto L8;
        r11 = CompositionSettings.DEFAULT;
    L8:
        CompositionSettings r4 = r11;
        if ((r14 & 16) == 0) goto L12;
        r12 = CompositionSettings.DEFAULT;
    L12:
        return r7.bindToLifecycleInternal(r8, r9, r3, r4, r12, r13);
    }

    private final List<CameraInfo> getActiveConcurrentCameraInfos() {
        if (isInitialized() == false) goto L7;
        return this.cameraX.getCameraFactory().getCameraCoordinator().getActiveConcurrentCameraInfos();
    L7:
        return CollectionsKt.emptyList();
    }

    private final CameraConfig getCameraConfig(CameraSelector r6, CameraInfo r7) {
        Iterator<CameraFilter> r62 = r6.getCameraFilterSet().iterator();
        CameraConfig r1 = null;
    L4:
        if (r62.hasNext() == false) goto L14;
        CameraFilter r2 = r62.next();
        if (Intrinsics.areEqual(r2.getIdentifier(), CameraFilter.DEFAULT_ID) == true) goto L4;
        CameraConfig r22 = ExtendedCameraConfigProviderStore.getConfigProvider(r2.getIdentifier()).getConfig(r7, this.context);
        if (r22 == null) goto L4;
        if (r1 != null) goto L12;
        r1 = r22;
        goto L4
    L12:
        f$$ExternalSyntheticBUOutline1.m("Cannot apply multiple extended camera configs at the same time.");
        return null;
    L14:
        if (r1 == null) goto L16;
        return r1;
    L16:
        return CameraConfigs.defaultConfig();
    }

    private final int getCameraOperatingMode() {
        if (isInitialized() == true) goto L5;
        return 0;
    L5:
        return this.cameraX.getCameraFactory().getCameraCoordinator().getCameraOperatingMode();
    }

    private final Pair<CameraSelector, CameraSelector> getSelectorsWithSessionFilter(SessionConfig r1, CameraSelector r2, CameraSelector r3) {
        CameraFilter r0 = r1.getCameraFilter();
        if (r0 == null) goto L5;
        CameraSelector r12 = CameraSelector.Builder.fromSelector(r2).addCameraFilter(r0).build();
        if (r3 == null) goto L9;
        CameraSelector r02 = CameraSelector.Builder.fromSelector(r3).addCameraFilter(r0).build();
    L11:
        return TuplesKt.to(r12, r02);
    L9:
        r02 = null;
        goto L11
    L5:
        return TuplesKt.to(r2, r3);
    }

    private static final ListenableFuture initAsync$lambda$0$1(CameraX r0, Void r1) {
        return r0.getInitializeFuture();
    }

    private static final ListenableFuture initAsync$lambda$0$2(Function1 r0, Object r1) {
        return (ListenableFuture) r0.invoke(r1);
    }

    private static final Void initAsync$lambda$0$3(LifecycleCameraProviderImpl r0, CameraX r1, Context r2, Void r3) {
        r0.initInternal(r1, ContextUtil.getPersistentApplicationContext(r2));
        return r3;
    }

    private static final Void initAsync$lambda$0$4(Function1 r0, Object r1) {
        return (Void) r0.invoke(r1);
    }

    private final void initInternal(CameraX r2, Context r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.cameraX = r2;     // Catch: Throwable -> L10
        this.context = r3;     // Catch: Throwable -> L10
        if (r2 == null) goto L12;
        CameraPresenceProvider r22 = r2.getCameraAvailabilityProvider();     // Catch: Throwable -> L10
        if (r22 == null) goto L12;
        r22.addCameraPresenceListener(this, CameraXExecutors.mainThreadExecutor());     // Catch: Throwable -> L10
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L10
    L12:
        monitor-exit(r0);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    private final boolean isInitialized() {
        if (this.cameraX == null) goto L6;
        return true;
    L6:
        return false;
    }

    private final boolean isPreview(UseCase r1) {
        return r1 instanceof Preview;
    }

    private final boolean isVideoCapture(UseCase r2) {
        if (r2.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) == true) goto L5;
        return false;
    L5:
        if (r2.getCurrentConfig().getCaptureType() != UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE) goto L10;
        return true;
    L10:
        return false;
    }

    private final void setActiveConcurrentCameraInfos(List<? extends CameraInfo> r2) {
        if (isInitialized() == false) goto L6;
        this.cameraX.getCameraFactory().getCameraCoordinator().setActiveConcurrentCameraInfos(r2);
        return;
    }

    private final void setCameraOperatingMode(int r2) {
        if (isInitialized() == false) goto L6;
        this.cameraX.getCameraFactory().getCameraCoordinator().setCameraOperatingMode(r2);
        return;
    }

    private final void shutdownInternal() {
        initInternal(null, null);
    }

    public Camera bindToLifecycle(LifecycleOwner r11, CameraSelector r12, SessionConfig r13) {
        Trace.beginSection("CX:bindToLifecycle-SessionConfig");
    L10:
        th = move-exception;
        Trace.endSection();
        throw th;
    L4:
        if (access$getCameraOperatingMode(this) == 2) goto L9;
        access$setCameraOperatingMode(this, 1);     // Catch: Throwable -> L10
        Camera r10 = bindToLifecycleInternal$default(this, r11, r12, null, null, null, r13, 28, null);     // Catch: Throwable -> L10
        Trace.endSection();
        return r10;
    L9:
        throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first.");     // Catch: Throwable -> L10
    }

    public Camera bindToLifecycle(LifecycleOwner r11, CameraSelector r12, UseCase... r13) {
        Trace.beginSection("CX:bindToLifecycle");
    L10:
        th = move-exception;
        Trace.endSection();
        throw th;
    L4:
        if (access$getCameraOperatingMode(this) == 2) goto L9;
        access$setCameraOperatingMode(this, 1);     // Catch: Throwable -> L10
        Camera r10 = bindToLifecycleInternal$default(this, r11, r12, null, null, null, new LegacySessionConfig(ArraysKt.filterNotNull(r13), null, null, 6, null), 28, null);     // Catch: Throwable -> L10
        Trace.endSection();
        return r10;
    L9:
        throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first");     // Catch: Throwable -> L10
    }

    public ConcurrentCamera bindToLifecycle(List<ConcurrentCamera.SingleCameraConfig> r14) {
        Trace.beginSection("CX:bindToLifecycle-Concurrent");
    L77:
        th = move-exception;
        Trace.endSection();
        throw th;
    L4:
        if (r14.size() < 2) goto L76;
        if (r14.size() > 2) goto L74;
        ConcurrentCamera.SingleCameraConfig r4 = r14.get(0);     // Catch: Throwable -> L77
        ConcurrentCamera.SingleCameraConfig r6 = r14.get(1);     // Catch: Throwable -> L77
        ArrayList r9 = new ArrayList();     // Catch: Throwable -> L77
        if (Intrinsics.areEqual(r4.getCameraSelector().getLensFacing(), r6.getCameraSelector().getLensFacing()) == false) goto L34;
        if (access$getCameraOperatingMode(this) == 2) goto L32;
        if (Intrinsics.areEqual(r4.getLifecycleOwner(), r6.getLifecycleOwner()) == false) goto L30;
        r4.getUseCaseGroup().getViewPort();     // Catch: Throwable -> L77
        r6.getUseCaseGroup().getViewPort();     // Catch: Throwable -> L77
        if (Intrinsics.areEqual(null, null) == false) goto L30;
        if (Intrinsics.areEqual(r4.getUseCaseGroup().getEffects(), r6.getUseCaseGroup().getEffects()) == false) goto L30;
        LifecycleOwner r1 = r4.getLifecycleOwner();     // Catch: Throwable -> L77
        CameraSelector r3 = r4.getCameraSelector();     // Catch: Throwable -> L77
        r4.getUseCaseGroup().getViewPort();     // Catch: Throwable -> L77
        List<CameraEffect> r42 = r4.getUseCaseGroup().getEffects();     // Catch: Throwable -> L77
        ArrayList r62 = new ArrayList();     // Catch: Throwable -> L77
        Iterator<ConcurrentCamera.SingleCameraConfig> r7 = r14.iterator();     // Catch: Throwable -> L77
    L20:
        if (r7.hasNext() == false) goto L28;
        ConcurrentCamera.SingleCameraConfig r8 = r7.next();     // Catch: Throwable -> L77
        Iterator<UseCase> r10 = r8.getUseCaseGroup().getUseCases().iterator();     // Catch: Throwable -> L77
    L23:
        if (r10.hasNext() == false) goto L27;
        UseCase r11 = r10.next();     // Catch: Throwable -> L77
        String r12 = r8.getCameraSelector().getPhysicalCameraId();     // Catch: Throwable -> L77
        if (r12 == null) goto L23;
        r11.setPhysicalCameraId(r12);     // Catch: Throwable -> L77
        goto L23
    L27:
        r62.addAll(r8.getUseCaseGroup().getUseCases());     // Catch: Throwable -> L77
        goto L20
    L28:
        access$setCameraOperatingMode(this, 1);     // Catch: Throwable -> L77
        r9.add(bindToLifecycleInternal$default(this, r1, r3, null, null, null, new LegacySessionConfig(r62, null, r42), 28, null));     // Catch: Throwable -> L77
    L64:
        ConcurrentCamera r0 = new ConcurrentCamera(r9);     // Catch: Throwable -> L77
        Trace.endSection();
        return r0;
    L30:
        throw new IllegalArgumentException("Two camera configs need to have the same lifecycle owner, view port and effects.");     // Catch: Throwable -> L77
    L32:
        throw new UnsupportedOperationException("Camera is already running, call unbindAll() before binding more cameras.");     // Catch: Throwable -> L77
    L34:
        if (getContext$camera_lifecycle().getPackageManager().hasSystemFeature("android.hardware.camera.concurrent") == false) goto L72;
        if (access$getCameraOperatingMode(this) == 1) goto L70;
        ArrayList r102 = new ArrayList();     // Catch: Throwable -> L77
        CameraInfo r13 = getCameraInfo(r4.getCameraSelector());     // Catch: IllegalArgumentException -> L67 Throwable -> L77
        CameraInfo r72 = getCameraInfo(r6.getCameraSelector());     // Catch: IllegalArgumentException -> L67 Throwable -> L77
        r102.add(r13);     // Catch: Throwable -> L77
        r102.add(r72);     // Catch: Throwable -> L77
        if (access$getActiveConcurrentCameraInfos(this).isEmpty() == false) goto L42;
    L46:
        access$setCameraOperatingMode(this, 2);     // Catch: Throwable -> L77
        if (Objects.equals(r4.getUseCaseGroup().getUseCases(), r6.getUseCaseGroup().getUseCases()) == true) goto L49;
    L59:
        Iterator<ConcurrentCamera.SingleCameraConfig> r112 = r14.iterator();     // Catch: Throwable -> L77
    L61:
        if (r112.hasNext() == false) goto L63;
        ConcurrentCamera.SingleCameraConfig r02 = r112.next();     // Catch: Throwable -> L77
        r9.add(bindToLifecycleInternal$default(this, r02.getLifecycleOwner(), r02.getCameraSelector(), null, null, null, new LegacySessionConfig(r02.getUseCaseGroup()), 28, null));     // Catch: Throwable -> L77
    L63:
        access$setActiveConcurrentCameraInfos(this, r102);     // Catch: Throwable -> L77
        goto L64
    L49:
        if (r4.getUseCaseGroup().getUseCases().size() != 2) goto L59;
        UseCase r15 = r4.getUseCaseGroup().getUseCases().get(0);     // Catch: Throwable -> L77
        UseCase r2 = r4.getUseCaseGroup().getUseCases().get(1);     // Catch: Throwable -> L77
        if (access$isVideoCapture(this, r15) == false) goto L55;
        if (access$isPreview(this, r2) == false) goto L55;
    L58:
        r9.add(access$bindToLifecycleInternal(this, r4.getLifecycleOwner(), r4.getCameraSelector(), r6.getCameraSelector(), r4.getCompositionSettings(), r6.getCompositionSettings(), new LegacySessionConfig(r4.getUseCaseGroup())));     // Catch: Throwable -> L77
    L55:
        if (access$isPreview(this, r15) == false) goto L59;
        if (access$isVideoCapture(this, r2) == false) goto L59;
    L42:
        if (Intrinsics.areEqual(r102, access$getActiveConcurrentCameraInfos(this)) == true) goto L46;
        throw new UnsupportedOperationException("Cameras are already running, call unbindAll() before binding more cameras.");     // Catch: Throwable -> L77
    L68:
        throw new IllegalArgumentException("Invalid camera selectors in camera configs.");     // Catch: Throwable -> L77
    L70:
        throw new UnsupportedOperationException("Camera is already running, call unbindAll() before binding more cameras.");     // Catch: Throwable -> L77
    L72:
        throw new UnsupportedOperationException("Concurrent camera is not supported on the device.");     // Catch: Throwable -> L77
    L74:
        throw new IllegalArgumentException("Concurrent camera is only supporting two cameras at maximum.");     // Catch: Throwable -> L77
    L76:
        throw new IllegalArgumentException("Concurrent camera needs two camera configs.");     // Catch: Throwable -> L77
    }

    public final void configure$camera_lifecycle(CameraXConfig r4) {
        Trace.beginSection("CX:configureInstanceInternal");
        Object r0 = access$getLock$p(this);     // Catch: Throwable -> L16
        monitor-enter(r0);     // Catch: Throwable -> L16
        Preconditions.checkNotNull(r4);     // Catch: Throwable -> L13
        if (getCameraXConfigProvider$camera_lifecycle() != null) goto L8;
        boolean r1 = true;
    L9:
        Preconditions.checkState(r1, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");     // Catch: Throwable -> L13
        setCameraXConfigProvider$camera_lifecycle(new LifecycleCameraProviderImpl$configure$1$1$1(r4));     // Catch: Throwable -> L13
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L16
        Trace.endSection();
        return;
    L8:
        r1 = false;
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public List<CameraInfo> getAvailableCameraInfos() {
        Trace.beginSection("CX:getAvailableCameraInfos");
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L10
        Iterator<CameraInternal> r2 = access$getCameraX$p(this).getCameraRepository().getCameras().iterator();     // Catch: Throwable -> L10
    L4:
        if (r2.hasNext() == false) goto L8;
        r0.add(r2.next().getCameraInfo());     // Catch: Throwable -> L10
        goto L4
    L8:
        Trace.endSection();
        return r0;
    L10:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public List<List<CameraInfo>> getAvailableConcurrentCameraInfos() {
        Trace.beginSection("CX:getAvailableConcurrentCameraInfos");
        Objects.requireNonNull(access$getCameraX$p(this));     // Catch: Throwable -> L16
        Objects.requireNonNull(access$getCameraX$p(this).getCameraFactory().getCameraCoordinator());     // Catch: Throwable -> L16
        List<List<CameraSelector>> r0 = access$getCameraX$p(this).getCameraFactory().getCameraCoordinator().getConcurrentCameraSelectors();     // Catch: Throwable -> L16
        ArrayList r1 = new ArrayList();     // Catch: Throwable -> L16
        Iterator<List<CameraSelector>> r02 = r0.iterator();     // Catch: Throwable -> L16
    L4:
        if (r02.hasNext() == false) goto L14;
        List<CameraSelector> r2 = r02.next();     // Catch: Throwable -> L16
        ArrayList r3 = new ArrayList();     // Catch: Throwable -> L16
        Iterator<CameraSelector> r22 = r2.iterator();     // Catch: Throwable -> L16
    L8:
        if (r22.hasNext() == false) goto L12;
        r3.add(getCameraInfo(r22.next()));     // Catch: Throwable -> L16
        goto L8
    L12:
        r1.add(r3);     // Catch: Throwable -> L16
        goto L4
    L14:
        Trace.endSection();
        return r1;
    L16:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public CameraInfo getCameraInfo(CameraSelector r5) {
        Trace.beginSection("CX:getCameraInfo");
        CameraInfoInternal r0 = r5.select(access$getCameraX$p(this).getCameraRepository().getCameras()).getCameraInfoInternal();     // Catch: Throwable -> L17
        CameraConfig r52 = access$getCameraConfig(this, r5, r0);     // Catch: Throwable -> L17
        CameraIdentifier r1 = CameraIdentifier.Factory.create(r0.getCameraId(), null, r52.getCompatibilityId());     // Catch: Throwable -> L17
        Object r2 = access$getLock$p(this);     // Catch: Throwable -> L17
        monitor-enter(r2);     // Catch: Throwable -> L17
        Object r3 = access$getCameraInfoMap$p(this).get(r1);     // Catch: Throwable -> L8
        if (r3 != null) goto L10;
        r3 = new AdapterCameraInfo(r0, r52);     // Catch: Throwable -> L8
        access$getCameraInfoMap$p(this).put(r1, r3);     // Catch: Throwable -> L8
    L10:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r2);     // Catch: Throwable -> L17
        AdapterCameraInfo r32 = (AdapterCameraInfo) r3;     // Catch: Throwable -> L17
        Trace.endSection();
        return r32;
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public final CameraXConfig.Provider getCameraXConfigProvider$camera_lifecycle() {
        return this.cameraXConfigProvider;
    }

    public final Context getContext$camera_lifecycle() {
        return this.context;
    }

    public boolean hasCamera(CameraSelector r2) {
        Trace.beginSection("CX:hasCamera");
        r2.select(access$getCameraX$p(this).getCameraRepository().getCameras());     // Catch: Throwable -> L5 IllegalArgumentException -> L8
        boolean r1 = true;
    L9:
        Trace.endSection();
        return r1;
    L8:
        r1 = false;
    L5:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public final ListenableFuture<Void> initAsync$camera_lifecycle(Context r5, CameraXConfig r6) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.lifecycleCameraRepository = LifecycleCameraRepositories.getInstance$camera_lifecycle(ContextUtil.getDeviceId(r5));     // Catch: Throwable -> L10
        ListenableFuture<Void> r1 = this.cameraXInitializeFuture;     // Catch: Throwable -> L10
        if (r1 == null) goto L8;
        monitor-exit(r0);
        return r1;
    L8:
        if (r6 == null) goto L12;
        configure$camera_lifecycle(r6);     // Catch: Throwable -> L10
    L12:
        CameraX r62 = new CameraX(r5, this.cameraXConfigProvider);     // Catch: Throwable -> L10
        FutureChain r52 = FutureChain.from(this.cameraXShutdownFuture).transformAsync(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda1(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda0(r62)), CameraXExecutors.directExecutor()).transform(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda3(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda2(this, r62, r5)), CameraXExecutors.directExecutor());     // Catch: Throwable -> L10
        this.cameraXInitializeFuture = r52;     // Catch: Throwable -> L10
        Futures.addCallback(r52, new LifecycleCameraProviderImpl$initAsync$1$2(this), CameraXExecutors.directExecutor());     // Catch: Throwable -> L10
        ListenableFuture<Void> r4 = Futures.nonCancellationPropagating(r52);     // Catch: Throwable -> L10
        monitor-exit(r0);
        return r4;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.core.CameraPresenceListener
    public void onCamerasAdded(Set<CameraIdentifier> r1) {
    }

    @Override // androidx.camera.core.CameraPresenceListener
    public void onCamerasRemoved(Set<CameraIdentifier> r8) {
        Threads.checkMainThread();
        Object r0 = this.lock;
        monitor-enter(r0);
        Iterator<CameraIdentifier> r82 = r8.iterator();     // Catch: Throwable -> L13
    L6:
        if (r82.hasNext() == false) goto L18;
        CameraIdentifier r1 = r82.next();     // Catch: Throwable -> L13
        Set<CameraIdentifier> r2 = this.cameraInfoMap.keySet();     // Catch: Throwable -> L13
        ArrayList r3 = new ArrayList();     // Catch: Throwable -> L13
        Iterator<T> r22 = r2.iterator();     // Catch: Throwable -> L13
    L9:
        if (r22.hasNext() == false) goto L15;
        Object r4 = r22.next();     // Catch: Throwable -> L13
        if (Intrinsics.areEqual(((CameraIdentifier) r4).getCameraIds(), r1.getCameraIds()) == false) goto L9;
        r3.add(r4);     // Catch: Throwable -> L13
        goto L9
    L15:
        int r12 = r3.size();     // Catch: Throwable -> L13
        int r23 = 0;
    L16:
        if (r23 >= r12) goto L6;
        Object r42 = r3.get(r23);     // Catch: Throwable -> L13
        r23 = r23 + 1;     // Catch: Throwable -> L13
        Map<CameraIdentifier, AdapterCameraInfo> r5 = this.cameraInfoMap;     // Catch: Throwable -> L13
        r5.remove((CameraIdentifier) r42);     // Catch: Throwable -> L13
        goto L16
    L18:
        Unit r7 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r0);
        return;
    L13:
        th = move-exception;
        throw th;
    }

    public final void setCameraXConfigProvider$camera_lifecycle(CameraXConfig.Provider r1) {
        this.cameraXConfigProvider = r1;
    }

    public final ListenableFuture<Void> shutdownAsync$camera_lifecycle(boolean r4) {
        Threads.runOnMainSync(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda4(this));
        if (isInitialized() == false) goto L5;
        this.cameraX.getCameraAvailabilityProvider().removeCameraPresenceListener(this);
        ListenableFuture<Void> r0 = this.cameraX.shutdown();
    L6:
        Object r2 = this.lock;
        monitor-enter(r2);
        if (r4 == true) goto L18;
    L12:
        this.cameraXInitializeFuture = null;     // Catch: Throwable -> L10
        this.cameraXShutdownFuture = r0;     // Catch: Throwable -> L10
        this.cameraInfoMap.clear();     // Catch: Throwable -> L10
        this.lifecycleCameraKeys.clear();     // Catch: Throwable -> L10
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r2);
        shutdownInternal();
        return r0;
    L10:
        th = move-exception;
        throw th;
    L18:
        this.cameraXConfigProvider = null;     // Catch: Throwable -> L10
        goto L12
    L5:
        r0 = Futures.immediateFuture(null);
        goto L6
    }

    public void unbindAll() {
        Trace.beginSection("CX:unbindAll");
        Threads.checkMainThread();     // Catch: Throwable -> L6
        access$setCameraOperatingMode(this, 0);     // Catch: Throwable -> L6
        access$getLifecycleCameraRepository$p(this).unbindAll(access$getLifecycleCameraKeys$p(this));     // Catch: Throwable -> L6
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }
}
