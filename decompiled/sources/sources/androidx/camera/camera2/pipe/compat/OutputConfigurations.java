package androidx.camera.camera2.pipe.compat;

import android.view.Surface;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.StreamId;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 R#\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u00058\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001b\u001a\u0004\b!\u0010\u001d¨\u0006\""}, d2 = {"Landroidx/camera/camera2/pipe/compat/OutputConfigurations;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "all", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "deferred", "postviewOutput", "Landroidx/camera/camera2/pipe/OutputId;", "Landroid/view/Surface;", "outputSurfaceMap", "<init>", "(Ljava/util/List;Ljava/util/Map;Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getAll", "()Ljava/util/List;", "Ljava/util/Map;", "getDeferred", "()Ljava/util/Map;", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "getPostviewOutput", "()Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "getOutputSurfaceMap", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OutputConfigurations {
    private final List<OutputConfigurationWrapper> all;
    private final Map<StreamId, OutputConfigurationWrapper> deferred;
    private final Map<OutputId, Surface> outputSurfaceMap;
    private final OutputConfigurationWrapper postviewOutput;

    /* JADX WARN: Multi-variable type inference failed */
    public OutputConfigurations(List<? extends OutputConfigurationWrapper> r1, Map<StreamId, ? extends OutputConfigurationWrapper> r2, OutputConfigurationWrapper r3, Map<OutputId, ? extends Surface> r4) {
        this.all = r1;
        this.deferred = r2;
        this.postviewOutput = r3;
        this.outputSurfaceMap = r4;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof OutputConfigurations) == true) goto L8;
        return false;
    L8:
        OutputConfigurations r52 = (OutputConfigurations) r5;
        if (Intrinsics.areEqual(this.all, r52.all) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.deferred, r52.deferred) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.postviewOutput, r52.postviewOutput) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.outputSurfaceMap, r52.outputSurfaceMap) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public final List<OutputConfigurationWrapper> getAll() {
        return this.all;
    }

    public final Map<StreamId, OutputConfigurationWrapper> getDeferred() {
        return this.deferred;
    }

    public final Map<OutputId, Surface> getOutputSurfaceMap() {
        return this.outputSurfaceMap;
    }

    public final OutputConfigurationWrapper getPostviewOutput() {
        return this.postviewOutput;
    }

    public int hashCode() {
        int r0 = ((this.all.hashCode() * 31) + this.deferred.hashCode()) * 31;
        OutputConfigurationWrapper r1 = this.postviewOutput;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return ((r0 + r12) * 31) + this.outputSurfaceMap.hashCode();
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public String toString() {
        return "OutputConfigurations(all=" + this.all + ", deferred=" + this.deferred + ", postviewOutput=" + this.postviewOutput + ", outputSurfaceMap=" + this.outputSurfaceMap + ')';
    }
}
