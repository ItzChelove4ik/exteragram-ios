package androidx.camera.camera2.config;

import android.util.Log;
import android.util.Pair;
import androidx.camera.camera2.adapter.CameraStateAdapter;
import androidx.camera.camera2.adapter.GraphStateToCameraStateAdapter;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraGraphConfigProvider;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0001-BC\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 JV\u0010!\u001a\u00020\u00002\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010$\u001a\u00020#HÖ\u0001¢\u0006\u0004\b$\u0010%R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010&R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010'R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010(R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010)R\u0011\u0010,\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Landroidx/camera/camera2/config/UseCaseCameraConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraphFactory", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "graphStateToCameraStateAdapter", "Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "sessionConfigAdapter", "Landroidx/camera/core/impl/SessionProcessor;", "sessionProcessor", "Lkotlin/Lazy;", "Landroidx/camera/camera2/impl/CameraGraphConfigProvider$CameraGraphCreationResult;", "lazyCreationResult", "<init>", "(Lkotlin/jvm/functions/Function1;Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;Landroidx/camera/camera2/adapter/SessionConfigAdapter;Landroidx/camera/core/impl/SessionProcessor;Lkotlin/Lazy;)V", "provideSessionConfigAdapter", "()Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "provideSessionProcessor", "()Landroidx/camera/core/impl/SessionProcessor;", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "cameraStateAdapter", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "provideUseCaseGraphContext", "(Landroidx/camera/camera2/adapter/CameraStateAdapter;)Landroidx/camera/camera2/config/UseCaseGraphContext;", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "copy", "(Lkotlin/jvm/functions/Function1;Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;Landroidx/camera/camera2/adapter/SessionConfigAdapter;Landroidx/camera/core/impl/SessionProcessor;Lkotlin/Lazy;)Landroidx/camera/camera2/config/UseCaseCameraConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lkotlin/jvm/functions/Function1;", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "Lkotlin/Lazy;", "getCameraGraphConfig", "()Landroidx/camera/camera2/pipe/CameraGraph$Config;", "cameraGraphConfig", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCameraConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseCameraConfig\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,261:1\n85#2,4:262\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseCameraConfig\n*L\n103#1:262,4\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraConfig {
    public static final Companion Companion = null;
    private final Function1<CameraGraph.Config, CameraGraph> cameraGraphFactory;
    private final GraphStateToCameraStateAdapter graphStateToCameraStateAdapter;
    private final Lazy<CameraGraphConfigProvider.CameraGraphCreationResult> lazyCreationResult;
    private final SessionConfigAdapter sessionConfigAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/config/UseCaseCameraConfig$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/camera/camera2/config/UseCaseCameraConfig;", "sessionConfigAdapter", "Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "cameraGraphConfigProvider", "Landroidx/camera/camera2/impl/CameraGraphConfigProvider;", "cameraGraphFactory", "Lkotlin/Function1;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Landroidx/camera/camera2/pipe/CameraGraph;", "graphStateToCameraStateAdapter", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "sessionProcessor", "Landroidx/camera/core/impl/SessionProcessor;", "isExtensions", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        /* renamed from: $r8$lambda$o-X4lbhDoh8cSXf19LhLAjDjp3A, reason: not valid java name */
        public static CameraGraphConfigProvider.CameraGraphCreationResult m39$r8$lambda$oX4lbhDoh8cSXf19LhLAjDjp3A(SessionConfigAdapter r8, boolean r9, SessionProcessor r10, CameraGraphConfigProvider r11, GraphStateToCameraStateAdapter r12) {
            SessionConfig r2 = r8.getValidSessionConfigOrNull();
            if (r9 == false) goto L6;
            int r0 = CameraGraph.OperatingMode.Companion.m220getEXTENSION2uNL3no();
        L5:
            int r1 = r0;
            Integer r02 = null;
            if (r9 == false) goto L22;
            if (r10 == null) goto L22;
            Pair<Integer, Integer> r92 = r10.getImplementationType();
            if (r92 == null) goto L22;
            r02 = (Integer) r92.second;
        L22:
            return r11.m57create79VDu0o(r1, r2, false, r12, r02, r8.getSurfaceToStreamUseCaseMap(), r8.getSurfaceToStreamUseHintMap());
        L6:
            if (r2 != null) goto L9;
            r0 = CameraGraph.OperatingMode.Companion.m222getNORMAL2uNL3no();
            goto L5
        L9:
            if (r2.getSessionType() != 1) goto L12;
            r0 = CameraGraph.OperatingMode.Companion.m221getHIGH_SPEED2uNL3no();
            goto L5
        L12:
            if (r2.getSessionType() != 0) goto L14;
            r0 = CameraGraph.OperatingMode.Companion.m222getNORMAL2uNL3no();
            goto L5
        L14:
            r0 = CameraGraph.OperatingMode.Companion.m219customEP6OhB0(r2.getSessionType());
            goto L5
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final UseCaseCameraConfig create(SessionConfigAdapter r7, CameraGraphConfigProvider r8, Function1<? super CameraGraph.Config, ? extends CameraGraph> r9, GraphStateToCameraStateAdapter r10, SessionProcessor r11, boolean r12) {
            return new UseCaseCameraConfig(r9, r10, r7, r11, LazyKt.lazy(new UseCaseCameraConfig$Companion$$ExternalSyntheticLambda0(r7, r12, r11, r8, r10)));
        }
    }

    public static CameraGraph $r8$lambda$cN9bs2FiqZ_7BAECL0YWz7rNXsA(UseCaseCameraConfig r1) {
        return r1.cameraGraphFactory.invoke(r1.getCameraGraphConfig());
    }

    /* renamed from: $r8$lambda$wBKLl4Khp_wXx4IT-qUufaPdjA4, reason: not valid java name */
    public static Map m38$r8$lambda$wBKLl4Khp_wXx4ITqUufaPdjA4(UseCaseCameraConfig r0) {
        return r0.lazyCreationResult.getValue().getStreamConfigMap();
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UseCaseCameraConfig(Function1<? super CameraGraph.Config, ? extends CameraGraph> r1, GraphStateToCameraStateAdapter r2, SessionConfigAdapter r3, SessionProcessor r4, Lazy<CameraGraphConfigProvider.CameraGraphCreationResult> r5) {
        this.cameraGraphFactory = r1;
        this.graphStateToCameraStateAdapter = r2;
        this.sessionConfigAdapter = r3;
        this.lazyCreationResult = r5;
    }

    public static /* synthetic */ UseCaseCameraConfig copy$default(UseCaseCameraConfig r0, Function1 r1, GraphStateToCameraStateAdapter r2, SessionConfigAdapter r3, SessionProcessor r4, Lazy r5, int r6, Object r7) {
        if ((r6 & 1) == 0) goto L6;
        r1 = r0.cameraGraphFactory;
    L6:
        if ((r6 & 2) == 0) goto L9;
        r2 = r0.graphStateToCameraStateAdapter;
    L9:
        if ((r6 & 4) == 0) goto L12;
        r3 = r0.sessionConfigAdapter;
    L12:
        if ((r6 & 8) == 0) goto L15;
        r0.getClass();
        r4 = null;
    L15:
        if ((r6 & 16) == 0) goto L17;
        r5 = r0.lazyCreationResult;
    L17:
        SessionProcessor r62 = r4;
        Lazy r72 = r5;
        SessionConfigAdapter r52 = r3;
        Function1 r32 = r1;
        return r0.copy(r32, r2, r52, r62, r72);
    }

    public final UseCaseCameraConfig copy(Function1<? super CameraGraph.Config, ? extends CameraGraph> r1, GraphStateToCameraStateAdapter r2, SessionConfigAdapter r3, SessionProcessor r4, Lazy<CameraGraphConfigProvider.CameraGraphCreationResult> r5) {
        return new UseCaseCameraConfig(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if (r6 == null) goto L8;
        Class<?> r2 = r6.getClass();
    L10:
        if (Intrinsics.areEqual(UseCaseCameraConfig.class, r2) == true) goto L12;
        return false;
    L12:
        UseCaseCameraConfig r62 = (UseCaseCameraConfig) r6;
        if (Intrinsics.areEqual(this.sessionConfigAdapter, r62.sessionConfigAdapter) == true) goto L16;
        return false;
    L16:
        if (Intrinsics.areEqual(this.graphStateToCameraStateAdapter, r62.graphStateToCameraStateAdapter) == true) goto L18;
        return false;
    L18:
        r62.getClass();
        if (Intrinsics.areEqual(null, null) == true) goto L21;
        return false;
    L21:
        return true;
    L8:
        r2 = null;
        goto L10
    }

    public final CameraGraph.Config getCameraGraphConfig() {
        return this.lazyCreationResult.getValue().getConfig();
    }

    public int hashCode() {
        return ((this.sessionConfigAdapter.hashCode() * 31) + this.graphStateToCameraStateAdapter.hashCode()) * 31;
    }

    public final SessionConfigAdapter provideSessionConfigAdapter() {
        return this.sessionConfigAdapter;
    }

    public final SessionProcessor provideSessionProcessor() {
        return null;
    }

    public final UseCaseGraphContext provideUseCaseGraphContext(CameraStateAdapter r11) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Prepared UseCaseGraphContext (Deferred)");
    L5:
        UseCaseCameraConfig$$ExternalSyntheticLambda0 r3 = new UseCaseCameraConfig$$ExternalSyntheticLambda0(this);
        UseCaseCameraConfig$$ExternalSyntheticLambda1 r6 = new UseCaseCameraConfig$$ExternalSyntheticLambda1(this);
        return new UseCaseGraphContext(r3, r11, this.graphStateToCameraStateAdapter, r6, null, 16, null);
    }

    public String toString() {
        return "UseCaseCameraConfig(cameraGraphFactory=" + this.cameraGraphFactory + ", graphStateToCameraStateAdapter=" + this.graphStateToCameraStateAdapter + ", sessionConfigAdapter=" + this.sessionConfigAdapter + ", sessionProcessor=null, lazyCreationResult=" + this.lazyCreationResult + ')';
    }
}
