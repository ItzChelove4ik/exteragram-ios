package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.view.Surface;
import androidx.camera.camera2.impl.CameraCallbackMap$$ExternalSyntheticApiModelOutline0;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.StreamId;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0000\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0012\u0010\n\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J&\u0010\u001b\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ+\u0010\u001e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010\"\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0018*\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000 H\u0016¢\u0006\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R \u0010\b\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010&R \u0010\t\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010&R \u0010\n\u001a\u000e\u0012\u0002\b\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010&R&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b'\u0010(R\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010)\u001a\u0004\b*\u0010+R\u001a\u0010\u0011\u001a\u00020\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010,\u001a\u0004\b-\u0010.R\u001a\u0010\u0013\u001a\u00020\u00128\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0013\u0010/\u001a\u0004\b0\u00101R\u001a\u0010\u0015\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u00102\u001a\u0004\b3\u00104¨\u00065"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2RequestMetadata;", "Landroidx/camera/camera2/pipe/RequestMetadata;", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "cameraCaptureSessionWrapper", "Landroid/hardware/camera2/CaptureRequest;", "captureRequest", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "defaultParameters", "graphParameters", "requiredParameters", "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "streams", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", _UrlKt.FRAGMENT_ENCODE_SET, "repeating", "Landroidx/camera/camera2/pipe/Request;", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/RequestNumber;", "requestNumber", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;Landroid/hardware/camera2/CaptureRequest;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;IZLandroidx/camera/camera2/pipe/Request;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "T", "Landroidx/camera/camera2/pipe/Metadata$Key;", PluginsConstants.Settings.KEY, "get", "(Landroidx/camera/camera2/pipe/Metadata$Key;)Ljava/lang/Object;", PluginsConstants.Settings.DEFAULT, "getOrDefault", "(Landroidx/camera/camera2/pipe/Metadata$Key;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "Landroid/hardware/camera2/CaptureRequest;", "Ljava/util/Map;", "getStreams", "()Ljava/util/Map;", "I", "getTemplate-fGx8uWA", "()I", "Z", "getRepeating", "()Z", "Landroidx/camera/camera2/pipe/Request;", "getRequest", "()Landroidx/camera/camera2/pipe/Request;", "J", "getRequestNumber-my6kx4g", "()J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2RequestMetadata implements RequestMetadata {
    private final CameraCaptureSessionWrapper cameraCaptureSessionWrapper;
    private final CaptureRequest captureRequest;
    private final Map<?, Object> defaultParameters;
    private final Map<?, Object> graphParameters;
    private final boolean repeating;
    private final Request request;
    private final long requestNumber;
    private final Map<?, Object> requiredParameters;
    private final Map<StreamId, Surface> streams;
    private final int template;

    /* JADX WARN: Multi-variable type inference failed */
    private Camera2RequestMetadata(CameraCaptureSessionWrapper r1, CaptureRequest r2, Map<?, ? extends Object> r3, Map<?, ? extends Object> r4, Map<?, ? extends Object> r5, Map<StreamId, ? extends Surface> r6, int r7, boolean r8, Request r9, long r10) {
        this.cameraCaptureSessionWrapper = r1;
        this.captureRequest = r2;
        this.defaultParameters = r3;
        this.graphParameters = r4;
        this.requiredParameters = r5;
        this.streams = r6;
        this.template = r7;
        this.repeating = r8;
        this.request = r9;
        this.requestNumber = r10;
    }

    public /* synthetic */ Camera2RequestMetadata(CameraCaptureSessionWrapper r1, CaptureRequest r2, Map r3, Map r4, Map r5, Map r6, int r7, boolean r8, Request r9, long r10, DefaultConstructorMarker r12) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    @Override // androidx.camera.camera2.pipe.Metadata
    public <T> T get(Metadata.Key<T> r2) {
        if (this.requiredParameters.containsKey(r2) == false) goto L7;
        return (T) this.requiredParameters.get(r2);
    L7:
        if (getRequest().getExtras().containsKey(r2) == false) goto L11;
        return (T) getRequest().getExtras().get(r2);
    L11:
        if (this.graphParameters.containsKey(r2) == false) goto L15;
        return (T) this.graphParameters.get(r2);
    L15:
        return (T) this.defaultParameters.get(r2);
    }

    @Override // androidx.camera.camera2.pipe.Metadata
    public <T> T getOrDefault(Metadata.Key<T> r1, T r2) {
        T r0 = (T) get(r1);
        if (r0 != null) goto L5;
        return r2;
    L5:
        return r0;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    public boolean getRepeating() {
        return this.repeating;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    public Request getRequest() {
        return this.request;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    /* renamed from: getRequestNumber-my6kx4g */
    public long mo67getRequestNumbermy6kx4g() {
        return this.requestNumber;
    }

    @Override // androidx.camera.camera2.pipe.RequestMetadata
    public Map<StreamId, Surface> getStreams() {
        return this.streams;
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r4) {
        if (Intrinsics.areEqual(r4, Reflection.getOrCreateKotlinClass(CaptureRequest.class)) == false) goto L7;
        return (T) this.captureRequest;
    L7:
        if (Intrinsics.areEqual(r4, Reflection.getOrCreateKotlinClass(CameraCaptureSession.class)) == false) goto L13;
        T r3 = (T) this.cameraCaptureSessionWrapper.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCaptureSession.class));
        if (r3 != null) goto L11;
        return null;
    L11:
        return r3;
    L13:
        if (Intrinsics.areEqual(r4, Reflection.getOrCreateKotlinClass(CameraCallbackMap$$ExternalSyntheticApiModelOutline0.m())) == true) goto L15;
    L21:
        return null;
    L15:
        if (Build.VERSION.SDK_INT < 31) goto L20;
        T r32 = (T) this.cameraCaptureSessionWrapper.unwrapAs(Reflection.getOrCreateKotlinClass(CameraCallbackMap$$ExternalSyntheticApiModelOutline0.m()));
        if (r32 != null) goto L19;
        return null;
    L19:
        return r32;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        goto L21
    }
}
