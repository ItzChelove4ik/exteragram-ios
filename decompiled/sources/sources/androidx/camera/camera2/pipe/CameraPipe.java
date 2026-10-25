package androidx.camera.camera2.pipe;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import android.os.Trace;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraInterop;
import androidx.camera.camera2.pipe.config.CameraPipeComponent;
import androidx.camera.camera2.pipe.config.CameraPipeConfigModule;
import androidx.camera.camera2.pipe.config.DaggerCameraPipeComponent;
import androidx.camera.camera2.pipe.config.ThreadConfigModule;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.graph.SurfaceGraph$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.media.ImageSources;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\bg\u0018\u0000 \u00192\u00020\u0001:\u0007\u001a\u001b\u001c\u001d\u001e\u001f\u0019J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0003\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH&¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0016H&¢\u0006\u0004\b\u0017\u0010\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006 À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "config", "Landroidx/camera/camera2/pipe/CameraGraph;", "createCameraGraph", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;)Landroidx/camera/camera2/pipe/CameraGraph;", "Landroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "createCameraGraphs", "(Landroidx/camera/camera2/pipe/CameraGraph$ConcurrentConfig;)Ljava/util/List;", "Landroidx/camera/camera2/pipe/CameraDevices;", "cameras", "()Landroidx/camera/camera2/pipe/CameraDevices;", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "cameraSurfaceManager", "()Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "graphConfig", "Landroidx/camera/camera2/pipe/ConfigQueryResult;", "isConfigSupported-NpXggIU", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isConfigSupported", _UrlKt.FRAGMENT_ENCODE_SET, "shutdown", "()V", "Companion", "Config", "Flags", "CameraInteropConfig", "ThreadConfig", "CameraMetadataConfig", "CameraBackendConfig", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraPipe {
    public static final Companion Companion = null;

    @kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$CameraBackendConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "internalBackend", "Landroidx/camera/camera2/pipe/CameraBackend;", "defaultBackend", "Landroidx/camera/camera2/pipe/CameraBackendId;", "cameraBackends", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraBackendFactory;", "<init>", "(Landroidx/camera/camera2/pipe/CameraBackend;Ljava/lang/String;Ljava/util/Map;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getInternalBackend", "()Landroidx/camera/camera2/pipe/CameraBackend;", "getDefaultBackend-AKmI2lo", "()Ljava/lang/String;", "Ljava/lang/String;", "getCameraBackends", "()Ljava/util/Map;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CameraBackendConfig {
        private final Map<CameraBackendId, CameraBackendFactory> cameraBackends;
        private final String defaultBackend;
        private final CameraBackend internalBackend;

        /* JADX WARN: Multi-variable type inference failed */
        private CameraBackendConfig(CameraBackend r1, String r2, Map<CameraBackendId, ? extends CameraBackendFactory> r3) {
            this.internalBackend = r1;
            this.defaultBackend = r2;
            this.cameraBackends = r3;
            if (r2 != null) goto L5;
            return;
        L5:
            if (r2 == null) goto L7;
            CameraBackendId r12 = CameraBackendId.m151boximpl(r2);
        L9:
            if (r3.containsKey(r12) == true) goto L18;
            StringBuilder r13 = new StringBuilder();
            if (r2 != null) goto L14;
            String r22 = "null";
        L15:
            r13.append(r22);
            SurfaceGraph$$ExternalSyntheticBUOutline0.m(r13, " does not exist in cameraBackends! Available backends are: ", r3.keySet());
            throw null;
        L14:
            r22 = CameraBackendId.m156toStringimpl(r2);
            goto L15
        L18:
            return;
        L7:
            r12 = null;
            goto L9
        }

        public /* synthetic */ CameraBackendConfig(CameraBackend r2, String r3, Map r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 1) == 0) goto L6;
            r2 = null;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r3 = null;
        L9:
            if ((r5 & 4) == 0) goto L11;
            r4 = MapsKt.emptyMap();
        L11:
            this(r2, r3, r4, null);
        }

        public /* synthetic */ CameraBackendConfig(CameraBackend r1, String r2, Map r3, DefaultConstructorMarker r4) {
            this(r1, r2, r3);
        }

        public final Map<CameraBackendId, CameraBackendFactory> getCameraBackends() {
            return this.cameraBackends;
        }

        /* renamed from: getDefaultBackend-AKmI2lo, reason: not valid java name */
        public final String m239getDefaultBackendAKmI2lo() {
            return this.defaultBackend;
        }

        public final CameraBackend getInternalBackend() {
            return this.internalBackend;
        }
    }

    @kotlin.Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CameraDevice$StateCallback;", "cameraDeviceStateCallback", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "cameraCaptureSessionListener", "Landroidx/camera/camera2/pipe/core/DurationNs;", "cameraOpenRetryMaxTimeoutNs", "<init>", "(Landroid/hardware/camera2/CameraDevice$StateCallback;Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;Landroidx/camera/camera2/pipe/core/DurationNs;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "getCameraDeviceStateCallback", "()Landroid/hardware/camera2/CameraDevice$StateCallback;", "Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "getCameraCaptureSessionListener", "()Landroidx/camera/camera2/pipe/CameraInterop$CaptureSessionListener;", "Landroidx/camera/camera2/pipe/core/DurationNs;", "getCameraOpenRetryMaxTimeoutNs-QWez1Bs", "()Landroidx/camera/camera2/pipe/core/DurationNs;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CameraInteropConfig {
        private final CameraInterop.CaptureSessionListener cameraCaptureSessionListener;
        private final CameraDevice.StateCallback cameraDeviceStateCallback;
        private final DurationNs cameraOpenRetryMaxTimeoutNs;

        private CameraInteropConfig(CameraDevice.StateCallback r1, CameraInterop.CaptureSessionListener r2, DurationNs r3) {
            this.cameraDeviceStateCallback = r1;
            this.cameraCaptureSessionListener = r2;
            this.cameraOpenRetryMaxTimeoutNs = r3;
        }

        public /* synthetic */ CameraInteropConfig(CameraDevice.StateCallback r2, CameraInterop.CaptureSessionListener r3, DurationNs r4, int r5, DefaultConstructorMarker r6) {
            if ((r5 & 1) == 0) goto L6;
            r2 = null;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r3 = null;
        L9:
            if ((r5 & 4) == 0) goto L11;
            r4 = null;
        L11:
            this(r2, r3, r4, null);
        }

        public /* synthetic */ CameraInteropConfig(CameraDevice.StateCallback r1, CameraInterop.CaptureSessionListener r2, DurationNs r3, DefaultConstructorMarker r4) {
            this(r1, r2, r3);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof CameraInteropConfig) == true) goto L8;
            return false;
        L8:
            CameraInteropConfig r52 = (CameraInteropConfig) r5;
            if (Intrinsics.areEqual(this.cameraDeviceStateCallback, r52.cameraDeviceStateCallback) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.cameraCaptureSessionListener, r52.cameraCaptureSessionListener) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.cameraOpenRetryMaxTimeoutNs, r52.cameraOpenRetryMaxTimeoutNs) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final CameraInterop.CaptureSessionListener getCameraCaptureSessionListener() {
            return this.cameraCaptureSessionListener;
        }

        public final CameraDevice.StateCallback getCameraDeviceStateCallback() {
            return this.cameraDeviceStateCallback;
        }

        /* renamed from: getCameraOpenRetryMaxTimeoutNs-QWez1Bs, reason: not valid java name */
        public final DurationNs m240getCameraOpenRetryMaxTimeoutNsQWez1Bs() {
            return this.cameraOpenRetryMaxTimeoutNs;
        }

        public int hashCode() {
            CameraDevice.StateCallback r0 = this.cameraDeviceStateCallback;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r03 = r02 * 31;
            CameraInterop.CaptureSessionListener r2 = this.cameraCaptureSessionListener;
            if (r2 != null) goto L9;
            int r22 = 0;
        L10:
            int r04 = (r03 + r22) * 31;
            DurationNs r3 = this.cameraOpenRetryMaxTimeoutNs;
            if (r3 == null) goto L15;
            r1 = DurationNs.m500hashCodeimpl(r3.m502unboximpl());
        L15:
            return r04 + r1;
        L9:
            r22 = r2.hashCode();
            goto L10
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public String toString() {
            return "CameraInteropConfig(cameraDeviceStateCallback=" + this.cameraDeviceStateCallback + ", cameraCaptureSessionListener=" + this.cameraCaptureSessionListener + ", cameraOpenRetryMaxTimeoutNs=" + this.cameraOpenRetryMaxTimeoutNs + ')';
        }
    }

    @kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B;\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u001e\b\u0002\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00030\u0006¢\u0006\u0004\b\b\u0010\tR\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR'\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "cacheBlocklist", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CameraCharacteristics$Key;", "cameraCacheBlocklist", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "<init>", "(Ljava/util/Set;Ljava/util/Map;)V", "getCacheBlocklist", "()Ljava/util/Set;", "getCameraCacheBlocklist", "()Ljava/util/Map;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CameraMetadataConfig {
        private final Set<CameraCharacteristics.Key<?>> cacheBlocklist;
        private final Map<CameraId, Set<CameraCharacteristics.Key<?>>> cameraCacheBlocklist;

        /* JADX WARN: Multi-variable type inference failed */
        public CameraMetadataConfig(Set<? extends CameraCharacteristics.Key<?>> r1, Map<CameraId, ? extends Set<? extends CameraCharacteristics.Key<?>>> r2) {
            this.cacheBlocklist = r1;
            this.cameraCacheBlocklist = r2;
        }

        public /* synthetic */ CameraMetadataConfig(Set r1, Map r2, int r3, DefaultConstructorMarker r4) {
            if ((r3 & 1) == 0) goto L6;
            r1 = SetsKt.emptySet();
        L6:
            if ((r3 & 2) == 0) goto L8;
            r2 = MapsKt.emptyMap();
        L8:
            this(r1, r2);
        }

        public final Set<CameraCharacteristics.Key<?>> getCacheBlocklist() {
            return this.cacheBlocklist;
        }

        public final Map<CameraId, Set<CameraCharacteristics.Key<?>>> getCameraCacheBlocklist() {
            return this.cameraCacheBlocklist;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/camera/camera2/pipe/CameraPipe;", "config", "Landroidx/camera/camera2/pipe/CameraPipe$Config;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraPipe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPipe.kt\nandroidx/camera/camera2/pipe/CameraPipe$Companion\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,439:1\n48#2,2:440\n71#2,4:442\n50#2,3:446\n78#2,4:449\n*S KotlinDebug\n*F\n+ 1 CameraPipe.kt\nandroidx/camera/camera2/pipe/CameraPipe$Companion\n*L\n238#1:440,2\n238#1:442,4\n238#1:446,3\n238#1:449,4\n*E\n"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }

        public final CameraPipe create(Config r2) {
            Debug r1 = Debug.INSTANCE;
            Trace.beginSection("CameraPipe");     // Catch: Throwable -> L6
            CameraPipeComponent r12 = DaggerCameraPipeComponent.builder().cameraPipeConfigModule(new CameraPipeConfigModule(r2)).threadConfigModule(new ThreadConfigModule(r2.getThreadConfig())).build();     // Catch: Throwable -> L6
            Trace.endSection();
            return new CameraPipeImpl(r12);
        L6:
            th = move-exception;
            Trace.endSection();
            throw th;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010-\u001a\u0004\b.\u0010/R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u00100\u001a\u0004\b1\u00102R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u00103\u001a\u0004\b4\u00105¨\u00066"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$Config;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "appContext", "Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;", "threadConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", "cameraMetadataConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraBackendConfig;", "cameraBackendConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", "cameraInteropConfig", "Landroidx/camera/camera2/pipe/media/ImageSources;", "imageSources", "Landroidx/camera/camera2/pipe/CameraPipe$Flags;", "flags", "Landroidx/camera/camera2/pipe/PlatformApiCompat;", "platformApiCompat", "<init>", "(Landroid/content/Context;Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;Landroidx/camera/camera2/pipe/CameraPipe$CameraBackendConfig;Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;Landroidx/camera/camera2/pipe/media/ImageSources;Landroidx/camera/camera2/pipe/CameraPipe$Flags;Landroidx/camera/camera2/pipe/PlatformApiCompat;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;", "getThreadConfig", "()Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;", "Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", "getCameraMetadataConfig", "()Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", "Landroidx/camera/camera2/pipe/CameraPipe$CameraBackendConfig;", "getCameraBackendConfig", "()Landroidx/camera/camera2/pipe/CameraPipe$CameraBackendConfig;", "Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", "getCameraInteropConfig", "()Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", "Landroidx/camera/camera2/pipe/media/ImageSources;", "getImageSources", "()Landroidx/camera/camera2/pipe/media/ImageSources;", "Landroidx/camera/camera2/pipe/CameraPipe$Flags;", "getFlags", "()Landroidx/camera/camera2/pipe/CameraPipe$Flags;", "Landroidx/camera/camera2/pipe/PlatformApiCompat;", "getPlatformApiCompat", "()Landroidx/camera/camera2/pipe/PlatformApiCompat;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Config {
        private final Context appContext;
        private final CameraBackendConfig cameraBackendConfig;
        private final CameraInteropConfig cameraInteropConfig;
        private final CameraMetadataConfig cameraMetadataConfig;
        private final Flags flags;
        private final ImageSources imageSources;
        private final ThreadConfig threadConfig;

        public Config(Context r1, ThreadConfig r2, CameraMetadataConfig r3, CameraBackendConfig r4, CameraInteropConfig r5, ImageSources r6, Flags r7, PlatformApiCompat r8) {
            this.appContext = r1;
            this.threadConfig = r2;
            this.cameraMetadataConfig = r3;
            this.cameraBackendConfig = r4;
            this.cameraInteropConfig = r5;
            this.imageSources = r6;
            this.flags = r7;
        }

        public /* synthetic */ Config(Context r13, ThreadConfig r14, CameraMetadataConfig r15, CameraBackendConfig r16, CameraInteropConfig r17, ImageSources r18, Flags r19, PlatformApiCompat r20, int r21, DefaultConstructorMarker r22) {
            if ((r21 & 2) == 0) goto L6;
            r14 = new ThreadConfig(null, null, null, null, null, null, null, 127, null);
        L6:
            if ((r21 & 4) == 0) goto L9;
            r15 = new CameraMetadataConfig(null, null, 3, null);
        L9:
            if ((r21 & 8) == 0) goto L11;
            CameraBackendConfig r3 = new CameraBackendConfig(null, null, null, 7, null);
        L13:
            if ((r21 & 16) == 0) goto L15;
            CameraInteropConfig r4 = new CameraInteropConfig(null, null, null, 7, null);
        L17:
            if ((r21 & 32) == 0) goto L19;
            ImageSources r1 = null;
        L21:
            if ((r21 & 64) == 0) goto L23;
            Flags r5 = new Flags(false, 1, null);
        L25:
            if ((r21 & 128) == 0) goto L28;
            PlatformApiCompat r222 = null;
        L29:
            this(r13, r14, r15, r3, r4, r1, r5, r222);
            return;
        L28:
            r222 = r20;
            goto L29
        L23:
            r5 = r19;
            goto L25
        L19:
            r1 = r18;
            goto L21
        L15:
            r4 = r17;
            goto L17
        L11:
            r3 = r16;
            goto L13
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Config) == true) goto L8;
            return false;
        L8:
            Config r52 = (Config) r5;
            if (Intrinsics.areEqual(this.appContext, r52.appContext) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.threadConfig, r52.threadConfig) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.cameraMetadataConfig, r52.cameraMetadataConfig) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.cameraBackendConfig, r52.cameraBackendConfig) == true) goto L21;
            return false;
        L21:
            if (Intrinsics.areEqual(this.cameraInteropConfig, r52.cameraInteropConfig) == true) goto L24;
            return false;
        L24:
            if (Intrinsics.areEqual(this.imageSources, r52.imageSources) == true) goto L27;
            return false;
        L27:
            if (Intrinsics.areEqual(this.flags, r52.flags) == true) goto L30;
            return false;
        L30:
            if (Intrinsics.areEqual(null, null) == true) goto L32;
            return false;
        L32:
            return true;
        }

        public final Context getAppContext() {
            return this.appContext;
        }

        public final CameraBackendConfig getCameraBackendConfig() {
            return this.cameraBackendConfig;
        }

        public final CameraInteropConfig getCameraInteropConfig() {
            return this.cameraInteropConfig;
        }

        public final CameraMetadataConfig getCameraMetadataConfig() {
            return this.cameraMetadataConfig;
        }

        public final Flags getFlags() {
            return this.flags;
        }

        public final ImageSources getImageSources() {
            return this.imageSources;
        }

        public final PlatformApiCompat getPlatformApiCompat() {
            return null;
        }

        public final ThreadConfig getThreadConfig() {
            return this.threadConfig;
        }

        public int hashCode() {
            int r0 = ((((((((this.appContext.hashCode() * 31) + this.threadConfig.hashCode()) * 31) + this.cameraMetadataConfig.hashCode()) * 31) + this.cameraBackendConfig.hashCode()) * 31) + this.cameraInteropConfig.hashCode()) * 31;
            ImageSources r1 = this.imageSources;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return (((r0 + r12) * 31) + this.flags.hashCode()) * 31;
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public String toString() {
            return "Config(appContext=" + this.appContext + ", threadConfig=" + this.threadConfig + ", cameraMetadataConfig=" + this.cameraMetadataConfig + ", cameraBackendConfig=" + this.cameraBackendConfig + ", cameraInteropConfig=" + this.cameraInteropConfig + ", imageSources=" + this.imageSources + ", flags=" + this.flags + ", platformApiCompat=null)";
        }
    }

    @kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\r\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$Flags;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "strictModeEnabled", "<init>", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getStrictModeEnabled", "()Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Flags {
        private final boolean strictModeEnabled;

        public Flags(boolean r1) {
            this.strictModeEnabled = r1;
        }

        public /* synthetic */ Flags(boolean r1, int r2, DefaultConstructorMarker r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = false;
        L5:
            this(r1);
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof Flags) == true) goto L9;
            return false;
        L9:
            if (this.strictModeEnabled == ((Flags) r4).strictModeEnabled) goto L11;
            return false;
        L11:
            return true;
        }

        public final boolean getStrictModeEnabled() {
            return this.strictModeEnabled;
        }

        public int hashCode() {
            return Boolean.hashCode(this.strictModeEnabled);
        }

        public String toString() {
            return "Flags(strictModeEnabled=" + this.strictModeEnabled + ')';
        }
    }

    @kotlin.Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001c\u0010\u001bR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001e\u0010\u001bR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!R\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010%\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Landroidx/camera/camera2/pipe/CameraPipe$ThreadConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/Executor;", "defaultLightweightExecutor", "defaultBackgroundExecutor", "defaultBlockingExecutor", "defaultCameraExecutor", "Landroid/os/Handler;", "defaultCameraHandler", "Lkotlin/Function0;", "defaultCameraHandlerFn", "Lkotlinx/coroutines/CoroutineScope;", "testOnlyScope", "<init>", "(Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroid/os/Handler;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineScope;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/concurrent/Executor;", "getDefaultLightweightExecutor", "()Ljava/util/concurrent/Executor;", "getDefaultBackgroundExecutor", "getDefaultBlockingExecutor", "getDefaultCameraExecutor", "Landroid/os/Handler;", "getDefaultCameraHandler", "()Landroid/os/Handler;", "Lkotlin/jvm/functions/Function0;", "getDefaultCameraHandlerFn", "()Lkotlin/jvm/functions/Function0;", "Lkotlinx/coroutines/CoroutineScope;", "getTestOnlyScope", "()Lkotlinx/coroutines/CoroutineScope;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ThreadConfig {
        private final Executor defaultBackgroundExecutor;
        private final Executor defaultBlockingExecutor;
        private final Executor defaultCameraExecutor;
        private final Handler defaultCameraHandler;
        private final Function0<Handler> defaultCameraHandlerFn;
        private final Executor defaultLightweightExecutor;
        private final CoroutineScope testOnlyScope;

        /* JADX WARN: Multi-variable type inference failed */
        public ThreadConfig(Executor r1, Executor r2, Executor r3, Executor r4, Handler r5, Function0<? extends Handler> r6, CoroutineScope r7) {
            this.defaultLightweightExecutor = r1;
            this.defaultBackgroundExecutor = r2;
            this.defaultBlockingExecutor = r3;
            this.defaultCameraExecutor = r4;
            this.defaultCameraHandler = r5;
            this.defaultCameraHandlerFn = r6;
            this.testOnlyScope = r7;
        }

        public /* synthetic */ ThreadConfig(Executor r2, Executor r3, Executor r4, Executor r5, Handler r6, Function0 r7, CoroutineScope r8, int r9, DefaultConstructorMarker r10) {
            if ((r9 & 1) == 0) goto L6;
            r2 = null;
        L6:
            if ((r9 & 2) == 0) goto L9;
            r3 = null;
        L9:
            if ((r9 & 4) == 0) goto L12;
            r4 = null;
        L12:
            if ((r9 & 8) == 0) goto L15;
            r5 = null;
        L15:
            if ((r9 & 16) == 0) goto L18;
            r6 = null;
        L18:
            if ((r9 & 32) == 0) goto L21;
            r7 = null;
        L21:
            if ((r9 & 64) == 0) goto L23;
            r8 = null;
        L23:
            this(r2, r3, r4, r5, r6, r7, r8);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ThreadConfig) == true) goto L8;
            return false;
        L8:
            ThreadConfig r52 = (ThreadConfig) r5;
            if (Intrinsics.areEqual(this.defaultLightweightExecutor, r52.defaultLightweightExecutor) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.defaultBackgroundExecutor, r52.defaultBackgroundExecutor) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.defaultBlockingExecutor, r52.defaultBlockingExecutor) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.defaultCameraExecutor, r52.defaultCameraExecutor) == true) goto L21;
            return false;
        L21:
            if (Intrinsics.areEqual(this.defaultCameraHandler, r52.defaultCameraHandler) == true) goto L24;
            return false;
        L24:
            if (Intrinsics.areEqual(this.defaultCameraHandlerFn, r52.defaultCameraHandlerFn) == true) goto L27;
            return false;
        L27:
            if (Intrinsics.areEqual(this.testOnlyScope, r52.testOnlyScope) == true) goto L29;
            return false;
        L29:
            return true;
        }

        public final Executor getDefaultBackgroundExecutor() {
            return this.defaultBackgroundExecutor;
        }

        public final Executor getDefaultBlockingExecutor() {
            return this.defaultBlockingExecutor;
        }

        public final Executor getDefaultCameraExecutor() {
            return this.defaultCameraExecutor;
        }

        public final Handler getDefaultCameraHandler() {
            return this.defaultCameraHandler;
        }

        public final Function0<Handler> getDefaultCameraHandlerFn() {
            return this.defaultCameraHandlerFn;
        }

        public final Executor getDefaultLightweightExecutor() {
            return this.defaultLightweightExecutor;
        }

        public final CoroutineScope getTestOnlyScope() {
            return this.testOnlyScope;
        }

        public int hashCode() {
            Executor r0 = this.defaultLightweightExecutor;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r03 = r02 * 31;
            Executor r2 = this.defaultBackgroundExecutor;
            if (r2 != null) goto L9;
            int r22 = 0;
        L10:
            int r04 = (r03 + r22) * 31;
            Executor r23 = this.defaultBlockingExecutor;
            if (r23 != null) goto L13;
            int r24 = 0;
        L14:
            int r05 = (r04 + r24) * 31;
            Executor r25 = this.defaultCameraExecutor;
            if (r25 != null) goto L17;
            int r26 = 0;
        L18:
            int r06 = (r05 + r26) * 31;
            Handler r27 = this.defaultCameraHandler;
            if (r27 != null) goto L21;
            int r28 = 0;
        L22:
            int r07 = (r06 + r28) * 31;
            Function0<Handler> r29 = this.defaultCameraHandlerFn;
            if (r29 != null) goto L25;
            int r210 = 0;
        L26:
            int r08 = (r07 + r210) * 31;
            CoroutineScope r3 = this.testOnlyScope;
            if (r3 == null) goto L31;
            r1 = r3.hashCode();
        L31:
            return r08 + r1;
        L25:
            r210 = r29.hashCode();
            goto L26
        L21:
            r28 = r27.hashCode();
            goto L22
        L17:
            r26 = r25.hashCode();
            goto L18
        L13:
            r24 = r23.hashCode();
            goto L14
        L9:
            r22 = r2.hashCode();
            goto L10
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public String toString() {
            return "ThreadConfig(defaultLightweightExecutor=" + this.defaultLightweightExecutor + ", defaultBackgroundExecutor=" + this.defaultBackgroundExecutor + ", defaultBlockingExecutor=" + this.defaultBlockingExecutor + ", defaultCameraExecutor=" + this.defaultCameraExecutor + ", defaultCameraHandler=" + this.defaultCameraHandler + ", defaultCameraHandlerFn=" + this.defaultCameraHandlerFn + ", testOnlyScope=" + this.testOnlyScope + ')';
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    CameraSurfaceManager cameraSurfaceManager();

    CameraDevices cameras();

    CameraGraph createCameraGraph(CameraGraph.Config r1);

    List<CameraGraph> createCameraGraphs(CameraGraph.ConcurrentConfig r1);

    /* renamed from: isConfigSupported-NpXggIU, reason: not valid java name */
    Object mo238isConfigSupportedNpXggIU(CameraGraph.Config r1, Continuation<? super ConfigQueryResult> r2);

    void shutdown();
}
