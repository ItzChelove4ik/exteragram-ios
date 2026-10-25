package androidx.camera.core;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Landroidx/camera/core/LegacySessionConfig;", "Landroidx/camera/core/SessionConfig;", "useCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "viewPort", "Landroidx/camera/core/ViewPort;", "effects", "Landroidx/camera/core/CameraEffect;", "<init>", "(Ljava/util/List;Landroidx/camera/core/ViewPort;Ljava/util/List;)V", "useCaseGroup", "Landroidx/camera/core/UseCaseGroup;", "(Landroidx/camera/core/UseCaseGroup;)V", "isLegacy", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "requireNonEmptyUseCases", "getRequireNonEmptyUseCases", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LegacySessionConfig extends SessionConfig {
    private final boolean isLegacy;
    private final boolean requireNonEmptyUseCases;

    public LegacySessionConfig(UseCaseGroup r3) {
        List<UseCase> r0 = r3.getUseCases();
        r3.getViewPort();
        this(r0, null, r3.getEffects());
    }

    public LegacySessionConfig(List<? extends UseCase> r10, ViewPort r11, List<? extends CameraEffect> r12) {
        super(r10, r11, r12, null, null, null, 56, null);
        this.isLegacy = true;
    }

    public /* synthetic */ LegacySessionConfig(List r1, ViewPort r2, List r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = null;
    L6:
        if ((r4 & 4) == 0) goto L8;
        r3 = CollectionsKt.emptyList();
    L8:
        this(r1, r2, r3);
    }

    @Override // androidx.camera.core.SessionConfig
    public boolean getRequireNonEmptyUseCases() {
        return this.requireNonEmptyUseCases;
    }

    @Override // androidx.camera.core.SessionConfig
    public boolean isLegacy() {
        return this.isLegacy;
    }
}
