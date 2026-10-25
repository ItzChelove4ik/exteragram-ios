package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Trace;
import android.util.ArrayMap;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.core.Debug;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ)\u0010\u000e\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u000b*\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR \u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010\"\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidFrameInfo;", "Landroidx/camera/camera2/pipe/FrameInfo;", "Landroid/hardware/camera2/TotalCaptureResult;", "totalCaptureResult", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", "<init>", "(Landroid/hardware/camera2/TotalCaptureResult;Ljava/lang/String;Landroidx/camera/camera2/pipe/RequestMetadata;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroid/hardware/camera2/TotalCaptureResult;", "Ljava/lang/String;", "getCamera-Dz_R5H8", "Landroidx/camera/camera2/pipe/RequestMetadata;", "getRequestMetadata", "()Landroidx/camera/camera2/pipe/RequestMetadata;", "Landroidx/camera/camera2/pipe/compat/AndroidFrameMetadata;", "result", "Landroidx/camera/camera2/pipe/compat/AndroidFrameMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/FrameMetadata;", "physicalResults", "Ljava/util/Map;", "getMetadata", "()Landroidx/camera/camera2/pipe/FrameMetadata;", "metadata", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFrameMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameMetadata.kt\nandroidx/camera/camera2/pipe/compat/AndroidFrameInfo\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,145:1\n48#2,2:146\n71#2,4:148\n50#2,3:152\n78#2,4:155\n*S KotlinDebug\n*F\n+ 1 FrameMetadata.kt\nandroidx/camera/camera2/pipe/compat/AndroidFrameInfo\n*L\n99#1:146,2\n99#1:148,4\n99#1:152,3\n99#1:155,4\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidFrameInfo implements FrameInfo {
    private final String camera;
    private final Map<CameraId, FrameMetadata> physicalResults;
    private final RequestMetadata requestMetadata;
    private final AndroidFrameMetadata result;
    private final TotalCaptureResult totalCaptureResult;

    private AndroidFrameInfo(TotalCaptureResult r5, String r6, RequestMetadata r7) {
        this.totalCaptureResult = r5;
        this.camera = r6;
        this.requestMetadata = r7;
        this.result = new AndroidFrameMetadata(r5, m420getCameraDz_R5H8(), null);
        Debug r52 = Debug.INSTANCE;
        Trace.beginSection("physicalCaptureResults");     // Catch: Throwable -> L21
        int r53 = Build.VERSION.SDK_INT;     // Catch: Throwable -> L21
        if (r53 < 31) goto L7;
        Map<String, CaptureResult> r54 = Api31Compat.getPhysicalCameraTotalResults(access$getTotalCaptureResult$p(this));     // Catch: Throwable -> L21
    L10:
        if (r54 != null) goto L12;
    L18:
        Map<CameraId, FrameMetadata> r62 = MapsKt.emptyMap();     // Catch: Throwable -> L21
    L19:
        Trace.endSection();
        this.physicalResults = r62;
        return;
    L12:
        if (r54.isEmpty() == true) goto L18;
        r62 = new ArrayMap(r54.size());     // Catch: Throwable -> L21
        Iterator<Map.Entry<String, CaptureResult>> r55 = r54.entrySet().iterator();     // Catch: Throwable -> L21
    L16:
        if (r55.hasNext() == false) goto L19;
        Map.Entry<String, CaptureResult> r72 = r55.next();     // Catch: Throwable -> L21
        String r1 = CameraId.m228constructorimpl(r72.getKey());     // Catch: Throwable -> L21
        r62.put(CameraId.m227boximpl(r1), new AndroidFrameMetadata(r72.getValue(), r1, null));     // Catch: Throwable -> L21
        goto L16
    L7:
        if (r53 < 28) goto L9;
        r54 = Api28Compat.getPhysicalCaptureResults(access$getTotalCaptureResult$p(this));     // Catch: Throwable -> L21
        goto L10
    L9:
        r54 = MapsKt.emptyMap();     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public /* synthetic */ AndroidFrameInfo(TotalCaptureResult r1, String r2, RequestMetadata r3, DefaultConstructorMarker r4) {
        this(r1, r2, r3);
    }

    public static final /* synthetic */ TotalCaptureResult access$getTotalCaptureResult$p(AndroidFrameInfo r0) {
        return r0.totalCaptureResult;
    }

    /* renamed from: getCamera-Dz_R5H8, reason: not valid java name */
    public String m420getCameraDz_R5H8() {
        return this.camera;
    }

    @Override // androidx.camera.camera2.pipe.FrameInfo
    public FrameMetadata getMetadata() {
        return this.result;
    }

    public String toString() {
        return "FrameInfo(camera: " + CameraId.m232toStringimpl(this.result.mo265getCameraDz_R5H8()) + ", frameNumber: " + this.result.mo266getFrameNumberUgla2oM() + ')';
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CaptureResult.class)) == false) goto L7;
        return (T) this.totalCaptureResult;
    L7:
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(TotalCaptureResult.class)) == false) goto L12;
        T r1 = (T) this.totalCaptureResult;
        if (r1 != null) goto L11;
        return null;
    L11:
        return r1;
    L12:
        return null;
    }
}
