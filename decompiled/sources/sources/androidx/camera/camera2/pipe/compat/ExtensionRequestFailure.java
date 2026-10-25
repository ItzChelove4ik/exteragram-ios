package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0080\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ)\u0010\u0010\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\r*\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0018\u001a\u00020\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\"R\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010\u0016¨\u0006%"}, d2 = {"Landroidx/camera/camera2/pipe/compat/ExtensionRequestFailure;", "Landroidx/camera/camera2/pipe/RequestFailure;", "Landroidx/camera/camera2/pipe/RequestMetadata;", "requestMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "wasImageCaptured", "Landroidx/camera/camera2/pipe/FrameNumber;", "frameNumber", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "<init>", "(Landroidx/camera/camera2/pipe/RequestMetadata;ZJILkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/RequestMetadata;", "getRequestMetadata", "()Landroidx/camera/camera2/pipe/RequestMetadata;", "Z", "getWasImageCaptured", "()Z", "J", "getFrameNumber-Ugla2oM", "()J", "I", "getReason", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExtensionRequestFailure implements RequestFailure {
    private final long frameNumber;
    private final int reason;
    private final RequestMetadata requestMetadata;
    private final boolean wasImageCaptured;

    private ExtensionRequestFailure(RequestMetadata r1, boolean r2, long r3, int r5) {
        this.requestMetadata = r1;
        this.wasImageCaptured = r2;
        this.frameNumber = r3;
        this.reason = r5;
    }

    public /* synthetic */ ExtensionRequestFailure(RequestMetadata r1, boolean r2, long r3, int r5, DefaultConstructorMarker r6) {
        this(r1, r2, r3, r5);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof ExtensionRequestFailure) == true) goto L8;
        return false;
    L8:
        ExtensionRequestFailure r82 = (ExtensionRequestFailure) r8;
        if (Intrinsics.areEqual(this.requestMetadata, r82.requestMetadata) == true) goto L12;
        return false;
    L12:
        if (this.wasImageCaptured == r82.wasImageCaptured) goto L15;
        return false;
    L15:
        if (FrameNumber.m270equalsimpl0(this.frameNumber, r82.frameNumber) == true) goto L18;
        return false;
    L18:
        if (this.reason == r82.reason) goto L20;
        return false;
    L20:
        return true;
    }

    @Override // androidx.camera.camera2.pipe.RequestFailure
    public int getReason() {
        return this.reason;
    }

    @Override // androidx.camera.camera2.pipe.RequestFailure
    public boolean getWasImageCaptured() {
        return this.wasImageCaptured;
    }

    public int hashCode() {
        return (((((this.requestMetadata.hashCode() * 31) + Boolean.hashCode(this.wasImageCaptured)) * 31) + FrameNumber.m271hashCodeimpl(this.frameNumber)) * 31) + Integer.hashCode(this.reason);
    }

    public String toString() {
        return "ExtensionRequestFailure(requestMetadata=" + this.requestMetadata + ", wasImageCaptured=" + this.wasImageCaptured + ", frameNumber=" + FrameNumber.m272toStringimpl(this.frameNumber) + ", reason=" + this.reason + ')';
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r1) {
        return null;
    }
}
