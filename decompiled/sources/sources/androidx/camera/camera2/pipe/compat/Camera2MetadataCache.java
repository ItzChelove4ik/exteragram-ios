package androidx.camera.camera2.pipe.compat;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Trace;
import android.util.ArrayMap;
import androidx.camera.camera2.impl.DisplayInfoManager$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraExtensionMetadata;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.DoNotDisturbException;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Permissions;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.TimeSource;
import androidx.camera.camera2.pipe.core.Timestamps;
import java.util.Arrays;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B3\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0016H\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u000eH\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u0010H\u0002¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b$\u0010%J\u0018\u0010)\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b'\u0010(J\u0017\u0010,\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b*\u0010+J\u001f\u00100\u001a\u00020-2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b.\u0010/J\u001d\u00104\u001a\b\u0012\u0004\u0012\u00020\u0016012\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b2\u00103R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00105R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00106R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00107R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u00108R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u00109R \u0010<\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020&0:8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b<\u0010=R \u0010>\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020-0:8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b>\u0010=R \u0010?\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u001c0:8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b?\u0010=¨\u0006@"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2MetadataCache;", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", "Landroid/content/Context;", "cameraPipeContext", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/core/Permissions;", "permissions", "Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", "cameraMetadataConfig", "Landroidx/camera/camera2/pipe/core/TimeSource;", "timeSource", "<init>", "(Landroid/content/Context;Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/core/Permissions;Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;Landroidx/camera/camera2/pipe/core/TimeSource;)V", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "redacted", "Landroidx/camera/camera2/pipe/compat/Camera2CameraMetadata;", "createCameraMetadata-0r8Bogc", "(Ljava/lang/String;Z)Landroidx/camera/camera2/pipe/compat/Camera2CameraMetadata;", "createCameraMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "extension", "Landroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata;", "createCameraExtensionMetadata-RzXb1QE", "(Ljava/lang/String;ZI)Landroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata;", "createCameraExtensionMetadata", "Landroid/hardware/camera2/CameraExtensionCharacteristics;", "getCameraExtensionCharacteristics-EfqyGwQ", "(Ljava/lang/String;)Landroid/hardware/camera2/CameraExtensionCharacteristics;", "getCameraExtensionCharacteristics", "isMetadataRedacted", "()Z", "Landroid/hardware/camera2/CameraCharacteristics;", "characteristics", "shouldBlockSensorOrientationCache", "(Landroid/hardware/camera2/CameraCharacteristics;)Z", "Landroidx/camera/camera2/pipe/CameraMetadata;", "getCameraMetadata-0r8Bogc", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCameraMetadata", "awaitCameraMetadata-EfqyGwQ", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitCameraMetadata", "Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "awaitCameraExtensionMetadata-0r8Bogc", "(Ljava/lang/String;I)Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "awaitCameraExtensionMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "getSupportedCameraExtensions-EfqyGwQ", "(Ljava/lang/String;)Ljava/util/Set;", "getSupportedCameraExtensions", "Landroid/content/Context;", "Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/core/Permissions;", "Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", "Landroidx/camera/camera2/pipe/core/TimeSource;", "Landroid/util/ArrayMap;", _UrlKt.FRAGMENT_ENCODE_SET, "cache", "Landroid/util/ArrayMap;", "extensionCache", "extensionCharacteristicsCache", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2MetadataCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2MetadataCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2MetadataCache\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 3 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 5 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n*L\n1#1,292:1\n48#2,2:293\n71#2,4:295\n50#2,3:299\n78#2,4:302\n48#2,2:306\n71#2,4:308\n50#2,3:312\n78#2,4:315\n48#2,2:320\n71#2,4:322\n50#2:326\n52#2:335\n78#2,4:336\n48#2,2:341\n71#2,4:343\n50#2:347\n52#2:356\n78#2,4:357\n70#3:319\n70#3:330\n74#3,2:332\n70#3:340\n70#3:351\n74#3,2:353\n50#4,2:327\n59#4:329\n60#4:334\n50#4,2:348\n59#4:350\n60#4:355\n50#4,2:361\n29#5:331\n29#5:352\n*S KotlinDebug\n*F\n+ 1 Camera2MetadataCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2MetadataCache\n*L\n100#1:293,2\n100#1:295,4\n100#1:299,3\n100#1:302,4\n120#1:306,2\n120#1:308,4\n120#1:312,3\n120#1:315,4\n152#1:320,2\n152#1:322,4\n152#1:326\n152#1:335\n152#1:336,4\n222#1:341,2\n222#1:343,4\n222#1:347\n222#1:356\n222#1:357,4\n150#1:319\n193#1:330\n199#1:332,2\n220#1:340\n238#1:351\n245#1:353,2\n154#1:327,2\n192#1:329\n192#1:334\n224#1:348,2\n237#1:350\n237#1:355\n268#1:361,2\n193#1:331\n238#1:352\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2MetadataCache implements Camera2MetadataProvider {
    private final ArrayMap<String, CameraMetadata> cache;
    private final CameraPipe.CameraMetadataConfig cameraMetadataConfig;
    private final Context cameraPipeContext;
    private final ArrayMap<String, CameraExtensionMetadata> extensionCache;
    private final ArrayMap<String, CameraExtensionCharacteristics> extensionCharacteristicsCache;
    private final Permissions permissions;
    private final Threads threads;
    private final TimeSource timeSource;

    public Camera2MetadataCache(Context r1, Threads r2, Permissions r3, CameraPipe.CameraMetadataConfig r4, TimeSource r5) {
        this.cameraPipeContext = r1;
        this.threads = r2;
        this.permissions = r3;
        this.cameraMetadataConfig = r4;
        this.timeSource = r5;
        this.cache = new ArrayMap();
        this.extensionCache = new ArrayMap();
        this.extensionCharacteristicsCache = new ArrayMap();
    }

    /* renamed from: access$createCameraExtensionMetadata-RzXb1QE, reason: not valid java name */
    public static final /* synthetic */ Camera2CameraExtensionMetadata m449access$createCameraExtensionMetadataRzXb1QE(Camera2MetadataCache r0, String r1, boolean r2, int r3) {
        return r0.m452createCameraExtensionMetadataRzXb1QE(r1, r2, r3);
    }

    /* renamed from: access$createCameraMetadata-0r8Bogc, reason: not valid java name */
    public static final /* synthetic */ Camera2CameraMetadata m450access$createCameraMetadata0r8Bogc(Camera2MetadataCache r0, String r1, boolean r2) {
        return r0.m453createCameraMetadata0r8Bogc(r1, r2);
    }

    public static final /* synthetic */ ArrayMap access$getCache$p(Camera2MetadataCache r0) {
        return r0.cache;
    }

    /* renamed from: access$getCameraExtensionCharacteristics-EfqyGwQ, reason: not valid java name */
    public static final /* synthetic */ CameraExtensionCharacteristics m451access$getCameraExtensionCharacteristicsEfqyGwQ(Camera2MetadataCache r0, String r1) {
        return r0.m454getCameraExtensionCharacteristicsEfqyGwQ(r1);
    }

    public static final /* synthetic */ CameraPipe.CameraMetadataConfig access$getCameraMetadataConfig$p(Camera2MetadataCache r0) {
        return r0.cameraMetadataConfig;
    }

    public static final /* synthetic */ Context access$getCameraPipeContext$p(Camera2MetadataCache r0) {
        return r0.cameraPipeContext;
    }

    public static final /* synthetic */ ArrayMap access$getExtensionCache$p(Camera2MetadataCache r0) {
        return r0.extensionCache;
    }

    public static final /* synthetic */ TimeSource access$getTimeSource$p(Camera2MetadataCache r0) {
        return r0.timeSource;
    }

    public static final /* synthetic */ boolean access$isMetadataRedacted(Camera2MetadataCache r0) {
        return r0.isMetadataRedacted();
    }

    public static final /* synthetic */ boolean access$shouldBlockSensorOrientationCache(Camera2MetadataCache r0, CameraCharacteristics r1) {
        return r0.shouldBlockSensorOrientationCache(r1);
    }

    /* renamed from: createCameraExtensionMetadata-RzXb1QE, reason: not valid java name */
    private final Camera2CameraExtensionMetadata m452createCameraExtensionMetadataRzXb1QE(String r14, boolean r15, int r16) {
        Timestamps r1 = Timestamps.INSTANCE;
        long r9 = this.timeSource.mo506nowvQl9yQU();
        Debug r12 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(r14) + "#readCameraExtensionMetadata");     // Catch: Throwable -> L24
        Log r11 = Log.INSTANCE;     // Catch: Throwable -> L8
        if (r11.getDEBUG_LOGGABLE() == false) goto L10;
        android.util.Log.d("CXCP", "Loading extension metadata for " + CameraId.m232toStringimpl(r14));     // Catch: Throwable -> L8
    L10:
        Camera2CameraExtensionMetadata r0 = new Camera2CameraExtensionMetadata(r14, r15, r16, m451access$getCameraExtensionCharacteristicsEfqyGwQ(this, r14), MapsKt.emptyMap(), null);     // Catch: Throwable -> L8
        if (r11.getINFO_LOGGABLE() == false) goto L20;
        long r3 = DurationNs.m498constructorimpl(access$getTimeSource$p(this).mo506nowvQl9yQU() - r9);     // Catch: Throwable -> L8
        if (r15 == true) goto L15;
        String r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        android.util.Log.i("CXCP", "Loaded extension metadata for " + CameraId.m232toStringimpl(r14) + " in " + String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r3 / 1000000.0d)}, 1)) + r13);     // Catch: Throwable -> L8
        goto L20
    L15:
        if (r15 != true) goto L19;
        r13 = " (redacted)";
        goto L17
    L19:
        throw new NoWhenBranchMatchedException();     // Catch: Throwable -> L8
    L20:
        Trace.endSection();
        return r0;
    L8:
        th = move-exception;
        throw new IllegalStateException("Failed to load extension metadata for " + CameraId.m232toStringimpl(r14) + '!', th);     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    /* renamed from: createCameraMetadata-0r8Bogc, reason: not valid java name */
    private final Camera2CameraMetadata m453createCameraMetadata0r8Bogc(String r16, boolean r17) {
        Timestamps r2 = Timestamps.INSTANCE;
        long r10 = this.timeSource.mo506nowvQl9yQU();
        Debug r22 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(r16) + "#readCameraMetadata");     // Catch: Throwable -> L44
        Log r13 = Log.INSTANCE;     // Catch: Throwable -> L9
        if (r13.getDEBUG_LOGGABLE() == false) goto L11;
        android.util.Log.d("CXCP", "Loading metadata for " + CameraId.m232toStringimpl(r16));     // Catch: Throwable -> L9
    L11:
        CameraCharacteristics r3 = ((CameraManager) access$getCameraPipeContext$p(this).getSystemService("camera")).getCameraCharacteristics(r16);     // Catch: Throwable -> L9
        if (r3 == null) goto L37;
        if (access$shouldBlockSensorOrientationCache(this, r3) == false) goto L19;
        Set<CameraCharacteristics.Key<?>> r0 = access$getCameraMetadataConfig$p(this).getCameraCacheBlocklist().get(CameraId.m227boximpl(r16));     // Catch: Throwable -> L9
        if (r0 != null) goto L18;
        r0 = SetsKt.emptySet();     // Catch: Throwable -> L9
    L18:
        Set<CameraCharacteristics.Key<?>> r02 = SetsKt.plus(r0, CameraCharacteristics.SENSOR_ORIENTATION);     // Catch: Throwable -> L9
    L20:
        if (r02 != null) goto L23;
        Set<CameraCharacteristics.Key<?>> r03 = access$getCameraMetadataConfig$p(this).getCacheBlocklist();     // Catch: Throwable -> L9
    L24:
        Camera2CameraMetadata r04 = new Camera2CameraMetadata(r16, r17, r3, this, MapsKt.emptyMap(), r03, null);     // Catch: Throwable -> L9
        if (r13.getINFO_LOGGABLE() == false) goto L34;
        long r32 = DurationNs.m498constructorimpl(access$getTimeSource$p(this).mo506nowvQl9yQU() - r10);     // Catch: Throwable -> L9
        if (r17 == true) goto L29;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L31:
        android.util.Log.i("CXCP", "Loaded metadata for " + CameraId.m232toStringimpl(r16) + " in " + String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r32 / 1000000.0d)}, 1)) + r1);     // Catch: Throwable -> L9
        goto L34
    L29:
        if (r17 != true) goto L33;
        r1 = " (redacted)";
        goto L31
    L33:
        throw new NoWhenBranchMatchedException();     // Catch: Throwable -> L9
    L34:
        Trace.endSection();
        return r04;
    L23:
        r03 = SetsKt.plus(access$getCameraMetadataConfig$p(this).getCacheBlocklist(), r02);     // Catch: Throwable -> L9
        goto L24
    L19:
        r02 = access$getCameraMetadataConfig$p(this).getCameraCacheBlocklist().get(CameraId.m227boximpl(r16));     // Catch: Throwable -> L9
        goto L20
    L37:
        throw new IllegalStateException(("Failed to get CameraCharacteristics for " + CameraId.m232toStringimpl(r16) + '!').toString());     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        if (CameraError.Companion.shouldHandleDoNotDisturbException$camera_camera2_pipe(th) == false) goto L43;
        throw new DoNotDisturbException("Failed to load metadata: Do Not Disturb mode is on!");     // Catch: Throwable -> L44
    L43:
        throw new IllegalStateException("Failed to load metadata for " + CameraId.m232toStringimpl(r16) + '!', th);     // Catch: Throwable -> L44
    L44:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    /* renamed from: getCameraExtensionCharacteristics-EfqyGwQ, reason: not valid java name */
    private final CameraExtensionCharacteristics m454getCameraExtensionCharacteristicsEfqyGwQ(String r4) {
        ArrayMap<String, CameraExtensionCharacteristics> r0 = this.extensionCharacteristicsCache;
        monitor-enter(r0);
        CameraExtensionCharacteristics r1 = Camera2MetadataCache$$ExternalSyntheticApiModelOutline0.m(this.extensionCharacteristicsCache.get(r4));     // Catch: Throwable -> L18
        if (r1 == null) goto L8;
        monitor-exit(r0);
        return r1;
    L8:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L18
        monitor-exit(r0);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L13;
        android.util.Log.d("CXCP", "Retrieving CameraExtensionCharacteristics for " + CameraId.m232toStringimpl(r4));
    L13:
        CameraExtensionCharacteristics r3 = Api31Compat.getCameraExtensionCharacteristics((CameraManager) this.cameraPipeContext.getSystemService("camera"), r4);
        if (r3 == null) goto L16;
        return r3;
    L16:
        DisplayInfoManager$$ExternalSyntheticBUOutline0.m("Failed to get CameraExtensionCharacteristics for ", CameraId.m232toStringimpl(r4));
        return null;
    L18:
        th = move-exception;
        throw th;
    }

    private final boolean isMetadataRedacted() {
        return !this.permissions.getHasCameraPermission();
    }

    private final boolean shouldBlockSensorOrientationCache(CameraCharacteristics r2) {
        if (Build.VERSION.SDK_INT >= 32) goto L5;
        return false;
    L5:
        if (r2.get(Camera2MetadataCache$$ExternalSyntheticApiModelOutline1.m()) == null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2MetadataProvider
    /* renamed from: awaitCameraExtensionMetadata-0r8Bogc, reason: not valid java name */
    public CameraExtensionMetadata mo455awaitCameraExtensionMetadata0r8Bogc(String r3, int r4) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L27;
        Debug r02 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(r3) + "#awaitExtensionMetadata");     // Catch: Throwable -> L23
        ArrayMap r03 = access$getExtensionCache$p(this);     // Catch: Throwable -> L23
        monitor-enter(r03);     // Catch: Throwable -> L23
        CameraExtensionMetadata r1 = (CameraExtensionMetadata) access$getExtensionCache$p(this).get(r3);     // Catch: Throwable -> L14
        if (r1 == null) goto L12;
    L9:
        monitor-exit(r03);     // Catch: Throwable -> L23
    L19:
        Trace.endSection();
        return r1;
    L12:
        if (access$isMetadataRedacted(this) == true) goto L16;
        r1 = m449access$createCameraExtensionMetadataRzXb1QE(this, r3, false, r4);     // Catch: Throwable -> L14
        access$getExtensionCache$p(this).put(r3, r1);     // Catch: Throwable -> L14
        goto L9
    L16:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r03);     // Catch: Throwable -> L23
        r1 = m449access$createCameraExtensionMetadataRzXb1QE(this, r3, true, r4);     // Catch: Throwable -> L23
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L23
    L23:
        th = move-exception;
        Trace.endSection();
        throw th;
    L27:
        throw new Exception("Extension sessions are only supported on Android S or higher. Device SDK is " + r0);
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2MetadataProvider
    /* renamed from: awaitCameraMetadata-EfqyGwQ, reason: not valid java name */
    public CameraMetadata mo456awaitCameraMetadataEfqyGwQ(String r3) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(CameraId.m232toStringimpl(r3) + "#awaitMetadata");     // Catch: Throwable -> L21
        ArrayMap r02 = access$getCache$p(this);     // Catch: Throwable -> L21
        monitor-enter(r02);     // Catch: Throwable -> L21
        CameraMetadata r1 = (CameraMetadata) access$getCache$p(this).get(r3);     // Catch: Throwable -> L12
        if (r1 == null) goto L10;
    L7:
        monitor-exit(r02);     // Catch: Throwable -> L21
    L17:
        Trace.endSection();
        return r1;
    L10:
        if (access$isMetadataRedacted(this) == true) goto L14;
        r1 = m450access$createCameraMetadata0r8Bogc(this, r3, false);     // Catch: Throwable -> L12
        access$getCache$p(this).put(r3, r1);     // Catch: Throwable -> L12
        goto L7
    L14:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r02);     // Catch: Throwable -> L21
        r1 = m450access$createCameraMetadata0r8Bogc(this, r3, true);     // Catch: Throwable -> L21
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2MetadataProvider
    /* renamed from: getCameraMetadata-0r8Bogc, reason: not valid java name */
    public Object mo457getCameraMetadata0r8Bogc(String r4, Continuation<? super CameraMetadata> r5) {
        ArrayMap<String, CameraMetadata> r0 = this.cache;
        monitor-enter(r0);
        CameraMetadata r1 = this.cache.get(r4);     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        monitor-exit(r0);
        return r1;
    L8:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r0);
        return BuildersKt.withContext(this.threads.getBackgroundDispatcher(), new Camera2MetadataCache$getCameraMetadata$3(this, r4, null), r5);
    L12:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2MetadataProvider
    /* renamed from: getSupportedCameraExtensions-EfqyGwQ, reason: not valid java name */
    public Set<Integer> mo458getSupportedCameraExtensionsEfqyGwQ(String r3) {
        if (Build.VERSION.SDK_INT < 31) goto L7;
        return CollectionsKt.toSet(Api31Compat.getSupportedExtensions(m454getCameraExtensionCharacteristicsEfqyGwQ(r3)));
    L7:
        return SetsKt.emptySet();
    }
}
