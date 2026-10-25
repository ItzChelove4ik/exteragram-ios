package androidx.camera.core.internal;

import androidx.camera.core.UseCase;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.core.streamsharing.StreamSharing;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b \u0010!R\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b\"\u0010!R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b$\u0010%R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b&\u0010%R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b'\u0010%R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010+\u001a\u0004\b,\u0010-R#\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u00101\u001a\u0004\b2\u00103R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0012\u00101\u001a\u0004\b4\u00103¨\u00065"}, d2 = {"Landroidx/camera/core/internal/CalculatedUseCaseInfo;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "appUseCases", "cameraUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "cameraUseCasesToAttach", "cameraUseCasesToKeep", "cameraUseCasesToDetach", "Landroidx/camera/core/streamsharing/StreamSharing;", "streamSharing", "placeholderForExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/internal/CameraUseCaseAdapter$ConfigPair;", "useCaseConfigs", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "primaryStreamSpecResult", "secondaryStreamSpecResult", "<init>", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/core/streamsharing/StreamSharing;Landroidx/camera/core/UseCase;Ljava/util/Map;Landroidx/camera/core/internal/StreamSpecQueryResult;Landroidx/camera/core/internal/StreamSpecQueryResult;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Collection;", "getAppUseCases", "()Ljava/util/Collection;", "getCameraUseCases", "Ljava/util/List;", "getCameraUseCasesToAttach", "()Ljava/util/List;", "getCameraUseCasesToKeep", "getCameraUseCasesToDetach", "Landroidx/camera/core/streamsharing/StreamSharing;", "getStreamSharing", "()Landroidx/camera/core/streamsharing/StreamSharing;", "Landroidx/camera/core/UseCase;", "getPlaceholderForExtensions", "()Landroidx/camera/core/UseCase;", "Ljava/util/Map;", "getUseCaseConfigs", "()Ljava/util/Map;", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "getPrimaryStreamSpecResult", "()Landroidx/camera/core/internal/StreamSpecQueryResult;", "getSecondaryStreamSpecResult", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CalculatedUseCaseInfo {
    private final Collection<UseCase> appUseCases;
    private final Collection<UseCase> cameraUseCases;
    private final List<UseCase> cameraUseCasesToAttach;
    private final List<UseCase> cameraUseCasesToDetach;
    private final List<UseCase> cameraUseCasesToKeep;
    private final UseCase placeholderForExtensions;
    private final StreamSpecQueryResult primaryStreamSpecResult;
    private final StreamSpecQueryResult secondaryStreamSpecResult;
    private final StreamSharing streamSharing;
    private final Map<UseCase, CameraUseCaseAdapter.ConfigPair> useCaseConfigs;

    /* JADX WARN: Multi-variable type inference failed */
    public CalculatedUseCaseInfo(Collection<? extends UseCase> r1, Collection<? extends UseCase> r2, List<? extends UseCase> r3, List<? extends UseCase> r4, List<? extends UseCase> r5, StreamSharing r6, UseCase r7, Map<UseCase, ? extends CameraUseCaseAdapter.ConfigPair> r8, StreamSpecQueryResult r9, StreamSpecQueryResult r10) {
        this.appUseCases = r1;
        this.cameraUseCases = r2;
        this.cameraUseCasesToAttach = r3;
        this.cameraUseCasesToKeep = r4;
        this.cameraUseCasesToDetach = r5;
        this.streamSharing = r6;
        this.placeholderForExtensions = r7;
        this.useCaseConfigs = r8;
        this.primaryStreamSpecResult = r9;
        this.secondaryStreamSpecResult = r10;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CalculatedUseCaseInfo) == true) goto L8;
        return false;
    L8:
        CalculatedUseCaseInfo r52 = (CalculatedUseCaseInfo) r5;
        if (Intrinsics.areEqual(this.appUseCases, r52.appUseCases) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.cameraUseCases, r52.cameraUseCases) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.cameraUseCasesToAttach, r52.cameraUseCasesToAttach) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.cameraUseCasesToKeep, r52.cameraUseCasesToKeep) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.cameraUseCasesToDetach, r52.cameraUseCasesToDetach) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.streamSharing, r52.streamSharing) == true) goto L27;
        return false;
    L27:
        if (Intrinsics.areEqual(this.placeholderForExtensions, r52.placeholderForExtensions) == true) goto L30;
        return false;
    L30:
        if (Intrinsics.areEqual(this.useCaseConfigs, r52.useCaseConfigs) == true) goto L33;
        return false;
    L33:
        if (Intrinsics.areEqual(this.primaryStreamSpecResult, r52.primaryStreamSpecResult) == true) goto L36;
        return false;
    L36:
        if (Intrinsics.areEqual(this.secondaryStreamSpecResult, r52.secondaryStreamSpecResult) == true) goto L38;
        return false;
    L38:
        return true;
    }

    public final Collection<UseCase> getAppUseCases() {
        return this.appUseCases;
    }

    public final Collection<UseCase> getCameraUseCases() {
        return this.cameraUseCases;
    }

    public final List<UseCase> getCameraUseCasesToAttach() {
        return this.cameraUseCasesToAttach;
    }

    public final List<UseCase> getCameraUseCasesToDetach() {
        return this.cameraUseCasesToDetach;
    }

    public final List<UseCase> getCameraUseCasesToKeep() {
        return this.cameraUseCasesToKeep;
    }

    public final UseCase getPlaceholderForExtensions() {
        return this.placeholderForExtensions;
    }

    public final StreamSpecQueryResult getPrimaryStreamSpecResult() {
        return this.primaryStreamSpecResult;
    }

    public final StreamSpecQueryResult getSecondaryStreamSpecResult() {
        return this.secondaryStreamSpecResult;
    }

    public final StreamSharing getStreamSharing() {
        return this.streamSharing;
    }

    public final Map<UseCase, CameraUseCaseAdapter.ConfigPair> getUseCaseConfigs() {
        return this.useCaseConfigs;
    }

    public int hashCode() {
        int r0 = ((((((((this.appUseCases.hashCode() * 31) + this.cameraUseCases.hashCode()) * 31) + this.cameraUseCasesToAttach.hashCode()) * 31) + this.cameraUseCasesToKeep.hashCode()) * 31) + this.cameraUseCasesToDetach.hashCode()) * 31;
        StreamSharing r1 = this.streamSharing;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        UseCase r13 = this.placeholderForExtensions;
        if (r13 != null) goto L9;
        int r14 = 0;
    L10:
        int r03 = (((((r02 + r14) * 31) + this.useCaseConfigs.hashCode()) * 31) + this.primaryStreamSpecResult.hashCode()) * 31;
        StreamSpecQueryResult r3 = this.secondaryStreamSpecResult;
        if (r3 == null) goto L15;
        r2 = r3.hashCode();
    L15:
        return r03 + r2;
    L9:
        r14 = r13.hashCode();
        goto L10
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public String toString() {
        return "CalculatedUseCaseInfo(appUseCases=" + this.appUseCases + ", cameraUseCases=" + this.cameraUseCases + ", cameraUseCasesToAttach=" + this.cameraUseCasesToAttach + ", cameraUseCasesToKeep=" + this.cameraUseCasesToKeep + ", cameraUseCasesToDetach=" + this.cameraUseCasesToDetach + ", streamSharing=" + this.streamSharing + ", placeholderForExtensions=" + this.placeholderForExtensions + ", useCaseConfigs=" + this.useCaseConfigs + ", primaryStreamSpecResult=" + this.primaryStreamSpecResult + ", secondaryStreamSpecResult=" + this.secondaryStreamSpecResult + ')';
    }
}
