package androidx.camera.core.impl;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0016\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ \u0010\f\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0002HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0010J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R'\u0010\u0005\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\rR#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001b\u0010\rR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b\u001d\u0010\u0010¨\u0006\u001e"}, d2 = {"Landroidx/camera/core/impl/SurfaceStreamSpecQueryResult;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/UseCaseConfig;", "Landroidx/camera/core/impl/StreamSpec;", "useCaseStreamSpecs", "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "attachedSurfaceStreamSpecs", _UrlKt.FRAGMENT_ENCODE_SET, "maxSupportedFrameRate", "<init>", "(Ljava/util/Map;Ljava/util/Map;I)V", "component1", "()Ljava/util/Map;", "component2", "component3", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Map;", "getUseCaseStreamSpecs", "getAttachedSurfaceStreamSpecs", "I", "getMaxSupportedFrameRate", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SurfaceStreamSpecQueryResult {
    private final Map<AttachedSurfaceInfo, StreamSpec> attachedSurfaceStreamSpecs;
    private final int maxSupportedFrameRate;
    private final Map<UseCaseConfig<?>, StreamSpec> useCaseStreamSpecs;

    /* JADX WARN: Multi-variable type inference failed */
    public SurfaceStreamSpecQueryResult(Map<UseCaseConfig<?>, ? extends StreamSpec> r1, Map<AttachedSurfaceInfo, ? extends StreamSpec> r2, int r3) {
        this.useCaseStreamSpecs = r1;
        this.attachedSurfaceStreamSpecs = r2;
        this.maxSupportedFrameRate = r3;
    }

    public final Map<UseCaseConfig<?>, StreamSpec> component1() {
        return this.useCaseStreamSpecs;
    }

    public final Map<AttachedSurfaceInfo, StreamSpec> component2() {
        return this.attachedSurfaceStreamSpecs;
    }

    public final int component3() {
        return this.maxSupportedFrameRate;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SurfaceStreamSpecQueryResult) == true) goto L8;
        return false;
    L8:
        SurfaceStreamSpecQueryResult r52 = (SurfaceStreamSpecQueryResult) r5;
        if (Intrinsics.areEqual(this.useCaseStreamSpecs, r52.useCaseStreamSpecs) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.attachedSurfaceStreamSpecs, r52.attachedSurfaceStreamSpecs) == true) goto L15;
        return false;
    L15:
        if (this.maxSupportedFrameRate == r52.maxSupportedFrameRate) goto L17;
        return false;
    L17:
        return true;
    }

    public int hashCode() {
        return (((this.useCaseStreamSpecs.hashCode() * 31) + this.attachedSurfaceStreamSpecs.hashCode()) * 31) + Integer.hashCode(this.maxSupportedFrameRate);
    }

    public String toString() {
        return "SurfaceStreamSpecQueryResult(useCaseStreamSpecs=" + this.useCaseStreamSpecs + ", attachedSurfaceStreamSpecs=" + this.attachedSurfaceStreamSpecs + ", maxSupportedFrameRate=" + this.maxSupportedFrameRate + ')';
    }
}
