package androidx.camera.core;

import android.util.Range;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.featuregroup.GroupableFeature;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0004R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0004¨\u0006\u000f"}, d2 = {"androidx/camera/core/SessionConfig$Builder$build$1", "Landroidx/camera/core/SessionConfig;", "isAutoRotationEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "cameraFilter", "Landroidx/camera/core/CameraFilter;", "getCameraFilter", "()Landroidx/camera/core/CameraFilter;", "sessionType", _UrlKt.FRAGMENT_ENCODE_SET, "getSessionType", "()I", "requireNonEmptyUseCases", "getRequireNonEmptyUseCases", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SessionConfig$Builder$build$1 extends SessionConfig {
    private final CameraFilter cameraFilter;
    private final boolean isAutoRotationEnabled;
    private final boolean requireNonEmptyUseCases;
    private final int sessionType;

    public SessionConfig$Builder$build$1(SessionConfig.Builder r8, List<? extends UseCase> r9, ViewPort r10, List<? extends CameraEffect> r11, Range<Integer> r12, Set<? extends GroupableFeature> r13, List<? extends GroupableFeature> r14) {
        super(r9, r10, r11, r12, r13, r14);
        this.isAutoRotationEnabled = SessionConfig.Builder.access$isAutoRotationEnabled$p(r8);
        this.cameraFilter = SessionConfig.Builder.access$getCameraFilter$p(r8);
        this.sessionType = SessionConfig.Builder.access$getSessionType$p(r8);
        this.requireNonEmptyUseCases = SessionConfig.Builder.access$getRequireNonEmptyUseCases$p(r8);
    }

    @Override // androidx.camera.core.SessionConfig
    public CameraFilter getCameraFilter() {
        return this.cameraFilter;
    }

    @Override // androidx.camera.core.SessionConfig
    public boolean getRequireNonEmptyUseCases() {
        return this.requireNonEmptyUseCases;
    }

    @Override // androidx.camera.core.SessionConfig
    public int getSessionType() {
        return this.sessionType;
    }

    @Override // androidx.camera.core.SessionConfig
    public boolean isAutoRotationEnabled() {
        return this.isAutoRotationEnabled;
    }
}
