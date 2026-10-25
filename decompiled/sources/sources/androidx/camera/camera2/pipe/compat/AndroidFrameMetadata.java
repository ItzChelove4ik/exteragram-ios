package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.FrameNumber;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\u000b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ+\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0013\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\b*\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u0017R&\u0010\u001c\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010#\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidFrameMetadata;", "Landroidx/camera/camera2/pipe/FrameMetadata;", "Landroid/hardware/camera2/CaptureResult;", "captureResult", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "<init>", "(Landroid/hardware/camera2/CaptureResult;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "T", "Landroid/hardware/camera2/CaptureResult$Key;", PluginsConstants.Settings.KEY, "get", "(Landroid/hardware/camera2/CaptureResult$Key;)Ljava/lang/Object;", PluginsConstants.Settings.DEFAULT, "getOrDefault", "(Landroid/hardware/camera2/CaptureResult$Key;Ljava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroid/hardware/camera2/CaptureResult;", "Ljava/lang/String;", "getCamera-Dz_R5H8", _UrlKt.FRAGMENT_ENCODE_SET, "extraMetadata", "Ljava/util/Map;", "getExtraMetadata", "()Ljava/util/Map;", "Landroidx/camera/camera2/pipe/FrameNumber;", "getFrameNumber-Ugla2oM", "()J", "frameNumber", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidFrameMetadata implements FrameMetadata {
    private final String camera;
    private final CaptureResult captureResult;
    private final Map<?, Object> extraMetadata;

    private AndroidFrameMetadata(CaptureResult r1, String r2) {
        this.captureResult = r1;
        this.camera = r2;
        this.extraMetadata = MapsKt.emptyMap();
    }

    public /* synthetic */ AndroidFrameMetadata(CaptureResult r1, String r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    @Override // androidx.camera.camera2.pipe.FrameMetadata
    public <T> T get(CaptureResult.Key<T> r2) {
        T r0 = (T) getExtraMetadata().get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return (T) this.captureResult.get(r2);
    }

    @Override // androidx.camera.camera2.pipe.FrameMetadata
    /* renamed from: getCamera-Dz_R5H8 */
    public String mo265getCameraDz_R5H8() {
        return this.camera;
    }

    public Map<?, Object> getExtraMetadata() {
        return this.extraMetadata;
    }

    @Override // androidx.camera.camera2.pipe.FrameMetadata
    /* renamed from: getFrameNumber-Ugla2oM */
    public long mo266getFrameNumberUgla2oM() {
        return FrameNumber.m268constructorimpl(this.captureResult.getFrameNumber());
    }

    @Override // androidx.camera.camera2.pipe.FrameMetadata
    public <T> T getOrDefault(CaptureResult.Key<T> r1, T r2) {
        T r0 = (T) get(r1);
        if (r0 != null) goto L5;
        return r2;
    L5:
        return r0;
    }

    public String toString() {
        return "FrameMetadata(camera: " + CameraId.m232toStringimpl(mo265getCameraDz_R5H8()) + ", frameNumber: " + this.captureResult.getFrameNumber() + ')';
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CaptureResult.class)) == false) goto L7;
        return (T) this.captureResult;
    L7:
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(TotalCaptureResult.class)) == false) goto L12;
        T r1 = (T) this.captureResult;
        if (r1 != null) goto L11;
        return null;
    L11:
        return r1;
    L12:
        return null;
    }
}
