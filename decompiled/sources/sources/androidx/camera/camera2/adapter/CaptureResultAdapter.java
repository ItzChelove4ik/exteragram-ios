package androidx.camera.camera2.adapter;

import androidx.camera.camera2.impl.TagsKt;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.UnsafeWrapper;
import androidx.camera.core.impl.CameraCaptureMetaData$AeMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AeState;
import androidx.camera.core.impl.CameraCaptureMetaData$AfMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AfState;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbMode;
import androidx.camera.core.impl.CameraCaptureMetaData$AwbState;
import androidx.camera.core.impl.CameraCaptureMetaData$FlashState;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.ExifData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020&H\u0016¢\u0006\u0004\b)\u0010*J)\u0010/\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010,*\u00020+2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0016¢\u0006\u0004\b/\u00100R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u00101R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00102R\u001a\u0010\b\u001a\u00020\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u00103\u001a\u0004\b4\u00105¨\u00066"}, d2 = {"Landroidx/camera/camera2/adapter/CaptureResultAdapter;", "Landroidx/camera/core/impl/CameraCaptureResult;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameInfo;", "result", "<init>", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/camera/core/impl/CameraCaptureMetaData$AfMode;", "getAfMode", "()Landroidx/camera/core/impl/CameraCaptureMetaData$AfMode;", "Landroidx/camera/core/impl/CameraCaptureMetaData$AfState;", "getAfState", "()Landroidx/camera/core/impl/CameraCaptureMetaData$AfState;", "Landroidx/camera/core/impl/CameraCaptureMetaData$AeMode;", "getAeMode", "()Landroidx/camera/core/impl/CameraCaptureMetaData$AeMode;", "Landroidx/camera/core/impl/CameraCaptureMetaData$AeState;", "getAeState", "()Landroidx/camera/core/impl/CameraCaptureMetaData$AeState;", "Landroidx/camera/core/impl/CameraCaptureMetaData$AwbMode;", "getAwbMode", "()Landroidx/camera/core/impl/CameraCaptureMetaData$AwbMode;", "Landroidx/camera/core/impl/CameraCaptureMetaData$AwbState;", "getAwbState", "()Landroidx/camera/core/impl/CameraCaptureMetaData$AwbState;", "Landroidx/camera/core/impl/CameraCaptureMetaData$FlashState;", "getFlashState", "()Landroidx/camera/core/impl/CameraCaptureMetaData$FlashState;", _UrlKt.FRAGMENT_ENCODE_SET, "getTimestamp", "()J", "Landroidx/camera/core/impl/TagBundle;", "getTagBundle", "()Landroidx/camera/core/impl/TagBundle;", "Landroidx/camera/core/impl/utils/ExifData$Builder;", "exifBuilder", _UrlKt.FRAGMENT_ENCODE_SET, "populateExifData", "(Landroidx/camera/core/impl/utils/ExifData$Builder;)V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/RequestMetadata;", "J", "Landroidx/camera/camera2/pipe/FrameInfo;", "getResult$camera_camera2", "()Landroidx/camera/camera2/pipe/FrameInfo;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CaptureResultAdapter implements CameraCaptureResult, UnsafeWrapper {
    private final long frameNumber;
    private final RequestMetadata requestMetadata;
    private final FrameInfo result;

    private CaptureResultAdapter(RequestMetadata r1, long r2, FrameInfo r4) {
        this.requestMetadata = r1;
        this.frameNumber = r2;
        this.result = r4;
    }

    public /* synthetic */ CaptureResultAdapter(RequestMetadata r1, long r2, FrameInfo r4, DefaultConstructorMarker r5) {
        this(r1, r2, r4);
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AeMode getAeMode() {
        return CaptureResultAdapterKt.access$getAeMode(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AeState getAeState() {
        return CaptureResultAdapterKt.access$getAeState(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AfMode getAfMode() {
        return CaptureResultAdapterKt.access$getAfMode(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AfState getAfState() {
        return CaptureResultAdapterKt.access$getAfState(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AwbMode getAwbMode() {
        return CaptureResultAdapterKt.access$getAwbMode(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$AwbState getAwbState() {
        return CaptureResultAdapterKt.access$getAwbState(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public CameraCaptureMetaData$FlashState getFlashState() {
        return CaptureResultAdapterKt.access$getFlashState(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public TagBundle getTagBundle() {
        return (TagBundle) this.requestMetadata.getOrDefault(TagsKt.getCAMERAX_TAG_BUNDLE(), TagBundle.emptyBundle());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public long getTimestamp() {
        return CaptureResultAdapterKt.access$getTimestamp(this.result.getMetadata());
    }

    @Override // androidx.camera.core.impl.CameraCaptureResult
    public void populateExifData(ExifData.Builder r1) {
        super.populateExifData(r1);
        CaptureResultAdapterKt.access$populateExifData(this.result.getMetadata(), r1);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, androidx.camera.camera2.pipe.FrameInfo, androidx.camera.camera2.pipe.UnsafeWrapper] */
    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        boolean r0 = Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(FrameInfo.class));
        ?? r1 = (T) this.result;
        if (r0 == false) goto L6;
        return r1;
    L6:
        return (T) r1.unwrapAs(r2);
    }
}
