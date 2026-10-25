package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.util.Size;
import androidx.camera.camera2.pipe.CameraExtensionMetadata;
import androidx.camera.camera2.pipe.Metadata;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0018\u0010\r\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0013\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0010*\u00020\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0005\u0010\u0019R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001dR&\u0010\r\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u001eR,\u0010#\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0 0\u001f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b#\u0010\u001eR0\u0010%\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030$\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0 0\u001f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b%\u0010\u001eR,\u0010&\u001a\u001a\u0012\u0004\u0012\u00020\"\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0 0\u001f8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b&\u0010\u001eR$\u0010(\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0!0 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0!0 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010)R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010)R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010)R\u0014\u0010.\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0019¨\u0006/"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata;", "Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "Landroidx/camera/camera2/pipe/CameraId;", "camera", _UrlKt.FRAGMENT_ENCODE_SET, "isRedacted", _UrlKt.FRAGMENT_ENCODE_SET, "cameraExtension", "Landroid/hardware/camera2/CameraExtensionCharacteristics;", "extensionCharacteristics", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Metadata$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "metadata", "<init>", "(Ljava/lang/String;ZILandroid/hardware/camera2/CameraExtensionCharacteristics;Ljava/util/Map;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Ljava/lang/String;", "getCamera-Dz_R5H8", "()Ljava/lang/String;", "Z", "()Z", "I", "getCameraExtension", "()I", "Landroid/hardware/camera2/CameraExtensionCharacteristics;", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Lazy;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "supportedExtensionSizesByFormat", "Ljava/lang/Class;", "supportedExtensionSizesByClass", "supportedPostviewSizes", "Landroid/hardware/camera2/CaptureRequest$Key;", "_requestKeys", "Lkotlin/Lazy;", "Landroid/hardware/camera2/CaptureResult$Key;", "_resultKeys", "_isPostviewSupported", "_isCaptureProgressSupported", "isPostviewSupported", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CameraExtensionMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraExtensionMetadata.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata\n+ 2 Lazy.kt\nandroidx/camera/camera2/pipe/core/LazyKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,192:1\n50#2,9:193\n50#2,9:202\n27#2,9:211\n27#2,9:220\n50#2,9:232\n63#2:248\n50#2,9:249\n63#2:265\n50#2,9:266\n384#3,3:229\n387#3,4:241\n384#3,3:245\n387#3,4:258\n384#3,3:262\n387#3,4:275\n*S KotlinDebug\n*F\n+ 1 Camera2CameraExtensionMetadata.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata\n*L\n149#1:193,9\n162#1:202,9\n172#1:211,9\n181#1:220,9\n96#1:232,9\n113#1:248\n113#1:249,9\n134#1:265\n134#1:266,9\n95#1:229,3\n95#1:241,4\n112#1:245,3\n112#1:258,4\n133#1:262,3\n133#1:275,4\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraExtensionMetadata implements CameraExtensionMetadata {
    private final Lazy<Boolean> _isCaptureProgressSupported;
    private final Lazy<Boolean> _isPostviewSupported;
    private final Lazy<Set<CaptureRequest.Key<?>>> _requestKeys;
    private final Lazy<Set<CaptureResult.Key<?>>> _resultKeys;
    private final String camera;
    private final int cameraExtension;
    private final CameraExtensionCharacteristics extensionCharacteristics;
    private final boolean isRedacted;
    private final Map<Metadata.Key<?>, Object> metadata;
    private final Map<Class<?>, Lazy<Set<Size>>> supportedExtensionSizesByClass;
    private final Map<Integer, Lazy<Set<Size>>> supportedExtensionSizesByFormat;
    private final Map<Size, Lazy<Set<Size>>> supportedPostviewSizes;

    private Camera2CameraExtensionMetadata(String r1, boolean r2, int r3, CameraExtensionCharacteristics r4, Map<Metadata.Key<?>, ? extends Object> r5) {
        this.camera = r1;
        this.isRedacted = r2;
        this.cameraExtension = r3;
        this.extensionCharacteristics = r4;
        this.metadata = r5;
        this.supportedExtensionSizesByFormat = new LinkedHashMap();
        this.supportedExtensionSizesByClass = new LinkedHashMap();
        this.supportedPostviewSizes = new LinkedHashMap();
        LazyThreadSafetyMode r12 = LazyThreadSafetyMode.PUBLICATION;
        this._requestKeys = LazyKt.lazy(r12, new Camera2CameraExtensionMetadata$special$$inlined$lazyOrEmptySet$1(this, this));
        this._resultKeys = LazyKt.lazy(r12, new Camera2CameraExtensionMetadata$special$$inlined$lazyOrEmptySet$2(this, this));
        this._isPostviewSupported = LazyKt.lazy(r12, new Camera2CameraExtensionMetadata$special$$inlined$lazyOrFalse$1(this, this));
        this._isCaptureProgressSupported = LazyKt.lazy(r12, new Camera2CameraExtensionMetadata$special$$inlined$lazyOrFalse$2(this, this));
    }

    public /* synthetic */ Camera2CameraExtensionMetadata(String r1, boolean r2, int r3, CameraExtensionCharacteristics r4, Map r5, DefaultConstructorMarker r6) {
        this(r1, r2, r3, r4, r5);
    }

    public static final /* synthetic */ CameraExtensionCharacteristics access$getExtensionCharacteristics$p(Camera2CameraExtensionMetadata r0) {
        return r0.extensionCharacteristics;
    }

    /* renamed from: getCamera-Dz_R5H8, reason: not valid java name */
    public String m431getCameraDz_R5H8() {
        return this.camera;
    }

    public int getCameraExtension() {
        return this.cameraExtension;
    }

    @Override // androidx.camera.camera2.pipe.CameraExtensionMetadata
    public boolean isPostviewSupported() {
        return this._isPostviewSupported.getValue().booleanValue();
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Camera2CameraExtensionMetadata$$ExternalSyntheticApiModelOutline0.m())) == true) goto L5;
        return null;
    L5:
        return (T) this.extensionCharacteristics;
    }
}
