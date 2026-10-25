package androidx.camera.camera2.adapter;

import android.content.Context;
import android.os.Trace;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraInteropStateCallbackRepository;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.CameraPipeKt;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.SystemTimeSource;
import androidx.camera.camera2.pipe.core.Timestamps;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.StreamSpecsCalculator;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ<\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J'\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\b\u001bR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/camera/camera2/adapter/CameraFactoryProvider;", "Landroidx/camera/core/impl/CameraFactory$Provider;", "sharedCameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "sharedAppContext", "Landroid/content/Context;", "sharedThreadConfig", "Landroidx/camera/core/impl/CameraThreadConfig;", "<init>", "(Landroidx/camera/camera2/pipe/CameraPipe;Landroid/content/Context;Landroidx/camera/core/impl/CameraThreadConfig;)V", "sharedInteropCallbacks", "Landroidx/camera/camera2/impl/CameraInteropStateCallbackRepository;", "newInstance", "Landroidx/camera/core/impl/CameraFactory;", "context", "threadConfig", "availableCamerasLimiter", "Landroidx/camera/core/CameraSelector;", "cameraOpenRetryMaxTimeoutInMs", _UrlKt.FRAGMENT_ENCODE_SET, "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "streamSpecsCalculator", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "createCameraPipe", "openRetryMaxTimeout", "Landroidx/camera/camera2/pipe/core/DurationNs;", "createCameraPipe-ck8WKOA", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraFactoryProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraFactoryProvider.kt\nandroidx/camera/camera2/adapter/CameraFactoryProvider\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 3 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 4 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 5 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n*L\n1#1,119:1\n48#2,2:120\n71#2,4:122\n50#2:126\n52#2:136\n78#2,4:137\n70#3:127\n83#3:130\n70#3:131\n74#3,2:133\n85#4,2:128\n88#4:135\n85#4,4:141\n29#5:132\n*S KotlinDebug\n*F\n+ 1 CameraFactoryProvider.kt\nandroidx/camera/camera2/adapter/CameraFactoryProvider\n*L\n87#1:120,2\n87#1:122,4\n87#1:126\n87#1:136\n87#1:137,4\n89#1:127\n114#1:130\n114#1:131\n114#1:133,2\n113#1:128,2\n113#1:135\n64#1:141,4\n114#1:132\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraFactoryProvider implements CameraFactory.Provider {
    private final Context sharedAppContext;
    private final CameraPipe sharedCameraPipe;
    private final CameraInteropStateCallbackRepository sharedInteropCallbacks;
    private final CameraThreadConfig sharedThreadConfig;

    public static CameraPipe $r8$lambda$ZlHettYjxZNyriDnsgQO8y2N0Rk(CameraFactoryProvider r1, Context r2, CameraThreadConfig r3, DurationNs r4) {
        if (r1.sharedCameraPipe == null) goto L10;
        Camera2Logger r22 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L8;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Using shared a " + access$getSharedCameraPipe$p(r1) + " instance.");
    L8:
        return r1.sharedCameraPipe;
    L10:
        return r1.m4createCameraPipeck8WKOA(r2, r3, r4);
    }

    public CameraFactoryProvider(CameraPipe r1, Context r2, CameraThreadConfig r3) {
        this.sharedCameraPipe = r1;
        this.sharedAppContext = r2;
        this.sharedThreadConfig = r3;
        this.sharedInteropCallbacks = new CameraInteropStateCallbackRepository();
    }

    public static final /* synthetic */ CameraPipe access$getSharedCameraPipe$p(CameraFactoryProvider r0) {
        return r0.sharedCameraPipe;
    }

    public static final /* synthetic */ CameraInteropStateCallbackRepository access$getSharedInteropCallbacks$p(CameraFactoryProvider r0) {
        return r0.sharedInteropCallbacks;
    }

    /* renamed from: createCameraPipe-ck8WKOA, reason: not valid java name */
    private final CameraPipe m4createCameraPipeck8WKOA(Context r17, CameraThreadConfig r18, DurationNs r19) {
        Debug r1 = Debug.INSTANCE;
        Trace.beginSection("Create CameraPipe");     // Catch: Throwable -> L8
        SystemTimeSource r12 = new SystemTimeSource();     // Catch: Throwable -> L8
        Timestamps r2 = Timestamps.INSTANCE;     // Catch: Throwable -> L8
        long r22 = r12.mo506nowvQl9yQU();     // Catch: Throwable -> L8
        CameraPipe r4 = CameraPipeKt.CameraPipe(new CameraPipe.Config(ContextUtil.getPersistentApplicationContext(r17), new CameraPipe.ThreadConfig(null, null, null, CameraXExecutors.newSequentialExecutor(r18.getCameraExecutor()), null, null, null, 119, null), null, null, new CameraPipe.CameraInteropConfig(access$getSharedInteropCallbacks$p(this).getDeviceStateCallback(), access$getSharedInteropCallbacks$p(this).getSessionStateCallback(), r19, null), null, null, null, 236, null));     // Catch: Throwable -> L8
        Camera2Logger r5 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L8
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Created CameraPipe in ".concat(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(DurationNs.m498constructorimpl(r12.mo506nowvQl9yQU() - r22) / 1000000.0d)}, 1))));     // Catch: Throwable -> L8
    L6:
        Trace.endSection();
        return r4;
    L8:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.core.impl.CameraFactory.Provider
    public CameraFactory newInstance(Context r9, CameraThreadConfig r10, CameraSelector r11, long r12, CameraXConfig r14, StreamSpecsCalculator r15) {
        if (r12 != (-1)) goto L5;
        DurationNs r122 = null;
    L6:
        Lazy r1 = LazyKt.lazy(new CameraFactoryProvider$$ExternalSyntheticLambda0(this, r9, r10, r122));
        Context r123 = this.sharedAppContext;
        if (r123 != null) goto L9;
        Context r2 = r9;
    L10:
        CameraThreadConfig r92 = this.sharedThreadConfig;
        if (r92 != null) goto L13;
        CameraThreadConfig r3 = r10;
    L14:
        CameraInteropStateCallbackRepository r4 = this.sharedInteropCallbacks;
        if (r14 != null) goto L18;
        r14 = new CameraXConfig.Builder().build();
    L18:
        return new CameraFactoryAdapter(r1, r2, r3, r4, r11, r15, r14);
    L13:
        r3 = r92;
        goto L14
    L9:
        r2 = r123;
        goto L10
    L5:
        r122 = DurationNs.m496boximpl(DurationNs.m498constructorimpl(r12));
        goto L6
    }
}
