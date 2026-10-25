package androidx.camera.core.internal;

import androidx.camera.core.UseCase;
import androidx.camera.core.impl.StreamSpec;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u000e¨\u0006\u0018"}, d2 = {"Landroidx/camera/core/internal/StreamSpecQueryResult;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "Landroidx/camera/core/impl/StreamSpec;", "streamSpecs", _UrlKt.FRAGMENT_ENCODE_SET, "maxSupportedFrameRate", "<init>", "(Ljava/util/Map;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Map;", "getStreamSpecs", "()Ljava/util/Map;", "I", "getMaxSupportedFrameRate", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StreamSpecQueryResult {
    private final int maxSupportedFrameRate;
    private final Map<UseCase, StreamSpec> streamSpecs;

    /* JADX WARN: Multi-variable type inference failed */
    public StreamSpecQueryResult(Map<UseCase, ? extends StreamSpec> r1, int r2) {
        this.streamSpecs = r1;
        this.maxSupportedFrameRate = r2;
    }

    public /* synthetic */ StreamSpecQueryResult(Map r1, int r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = MapsKt.emptyMap();
    L6:
        if ((r3 & 2) == 0) goto L8;
        r2 = Integer.MAX_VALUE;
    L8:
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof StreamSpecQueryResult) == true) goto L8;
        return false;
    L8:
        StreamSpecQueryResult r52 = (StreamSpecQueryResult) r5;
        if (Intrinsics.areEqual(this.streamSpecs, r52.streamSpecs) == true) goto L12;
        return false;
    L12:
        if (this.maxSupportedFrameRate == r52.maxSupportedFrameRate) goto L14;
        return false;
    L14:
        return true;
    }

    public final int getMaxSupportedFrameRate() {
        return this.maxSupportedFrameRate;
    }

    public final Map<UseCase, StreamSpec> getStreamSpecs() {
        return this.streamSpecs;
    }

    public int hashCode() {
        return (this.streamSpecs.hashCode() * 31) + Integer.hashCode(this.maxSupportedFrameRate);
    }

    public String toString() {
        return "StreamSpecQueryResult(streamSpecs=" + this.streamSpecs + ", maxSupportedFrameRate=" + this.maxSupportedFrameRate + ')';
    }
}
