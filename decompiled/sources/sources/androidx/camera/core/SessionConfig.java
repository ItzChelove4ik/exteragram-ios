package androidx.camera.core;

import android.util.Range;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.UseCaseType;
import androidx.camera.core.featuregroup.impl.feature.FeatureTypeInternal;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.UseCaseUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.core.util.Consumer;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.HttpUrl$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.Response$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001:\u0001GBc\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0016\u0010\u0014J\u0013\u0010\u0017\u001a\u00020\u0012*\u00020\u0003H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u001a\u001a\u00020\u0019*\u00020\u0003H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b\"\u0010#R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010$\u001a\u0004\b%\u0010&R\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u001e\u001a\u0004\b'\u0010 R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b(\u0010 R\u001a\u0010*\u001a\u00020)8\u0017X\u0096D¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b*\u0010,R\u001a\u0010-\u001a\u00020\n8\u0017X\u0096D¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u001a\u00101\u001a\u00020)8\u0017X\u0096D¢\u0006\f\n\u0004\b1\u0010+\u001a\u0004\b2\u0010,R\u001c\u00104\u001a\u0004\u0018\u0001038\u0017X\u0096\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020)8\u0017X\u0096D¢\u0006\f\n\u0004\b8\u0010+\u001a\u0004\b8\u0010,R<\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f092\u0012\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f098\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R$\u0010@\u001a\u00020?2\u0006\u0010:\u001a\u00020?8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010D\u001a\u0004\bE\u0010F¨\u0006H"}, d2 = {"Landroidx/camera/core/SessionConfig;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "useCases", "Landroidx/camera/core/ViewPort;", "viewPort", "Landroidx/camera/core/CameraEffect;", "effects", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "frameRateRange", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/featuregroup/GroupableFeature;", "requiredFeatureGroup", "preferredFeatureGroup", "<init>", "(Ljava/util/List;Landroidx/camera/core/ViewPort;Ljava/util/List;Landroid/util/Range;Ljava/util/Set;Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "validateFrameRate", "()V", "validateFeatureGroups", "validateRequiredFeatures", "validateDefaultGroupableFeatureValues", "(Landroidx/camera/core/UseCase;)V", _UrlKt.FRAGMENT_ENCODE_SET, "getUseCaseName", "(Landroidx/camera/core/UseCase;)Ljava/lang/String;", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getEffects", "()Ljava/util/List;", "Landroid/util/Range;", "getFrameRateRange", "()Landroid/util/Range;", "Ljava/util/Set;", "getRequiredFeatureGroup", "()Ljava/util/Set;", "getPreferredFeatureGroup", "getUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "isLegacy", "Z", "()Z", "sessionType", "I", "getSessionType", "()I", "requireNonEmptyUseCases", "getRequireNonEmptyUseCases", "Landroidx/camera/core/CameraFilter;", "cameraFilter", "Landroidx/camera/core/CameraFilter;", "getCameraFilter", "()Landroidx/camera/core/CameraFilter;", "isAutoRotationEnabled", "Landroidx/core/util/Consumer;", "value", "featureSelectionListener", "Landroidx/core/util/Consumer;", "getFeatureSelectionListener", "()Landroidx/core/util/Consumer;", "Ljava/util/concurrent/Executor;", "featureSelectionListenerExecutor", "Ljava/util/concurrent/Executor;", "getFeatureSelectionListenerExecutor", "()Ljava/util/concurrent/Executor;", "Landroidx/camera/core/ViewPort;", "getViewPort", "()Landroidx/camera/core/ViewPort;", "Builder", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionConfig.kt\nandroidx/camera/core/SessionConfig\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,464:1\n1#2:465\n1869#3,2:466\n1563#3:468\n1634#3,3:469\n1869#3:472\n774#3:473\n865#3,2:474\n1870#3:476\n*S KotlinDebug\n*F\n+ 1 SessionConfig.kt\nandroidx/camera/core/SessionConfig\n*L\n179#1:466,2\n195#1:468\n195#1:469,3\n196#1:472\n198#1:473\n198#1:474,2\n196#1:476\n*E\n"})
/* loaded from: classes3.dex */
public abstract class SessionConfig {
    private final CameraFilter cameraFilter;
    private final List<CameraEffect> effects;
    private Consumer<Set<GroupableFeature>> featureSelectionListener;
    private Executor featureSelectionListenerExecutor;
    private final Range<Integer> frameRateRange;
    private final boolean isAutoRotationEnabled;
    private final boolean isLegacy;
    private final List<GroupableFeature> preferredFeatureGroup;
    private final boolean requireNonEmptyUseCases;
    private final Set<GroupableFeature> requiredFeatureGroup;
    private final int sessionType;
    private final List<UseCase> useCases;

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0007\"\u00020\u0003¢\u0006\u0004\b\u0005\u0010\bJ\u001b\u0010\f\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0011R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0011R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\u0015R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0011R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\u001b¨\u0006\""}, d2 = {"Landroidx/camera/core/SessionConfig$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "useCases", "<init>", "(Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "([Landroidx/camera/core/UseCase;)V", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "frameRateRange", "setFrameRateRange", "(Landroid/util/Range;)Landroidx/camera/core/SessionConfig$Builder;", "Landroidx/camera/core/SessionConfig;", "build", "()Landroidx/camera/core/SessionConfig;", "Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraEffect;", "effects", "Landroid/util/Range;", "Landroidx/camera/core/featuregroup/GroupableFeature;", "requiredFeatureGroup", "preferredFeatureGroup", _UrlKt.FRAGMENT_ENCODE_SET, "isAutoRotationEnabled", "Z", "Landroidx/camera/core/CameraFilter;", "cameraFilter", "Landroidx/camera/core/CameraFilter;", "sessionType", "I", "requireNonEmptyUseCases", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder {
        private CameraFilter cameraFilter;
        private List<CameraEffect> effects;
        private Range<Integer> frameRateRange;
        private boolean isAutoRotationEnabled;
        private final List<GroupableFeature> preferredFeatureGroup;
        private boolean requireNonEmptyUseCases;
        private final List<GroupableFeature> requiredFeatureGroup;
        private int sessionType;
        private final List<UseCase> useCases;

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(List<? extends UseCase> r1) {
            this.useCases = r1;
            this.effects = new ArrayList();
            this.frameRateRange = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
            this.requiredFeatureGroup = new ArrayList();
            this.preferredFeatureGroup = new ArrayList();
            this.requireNonEmptyUseCases = true;
        }

        public Builder(UseCase... r1) {
            this(ArraysKt.toList(r1));
        }

        public static final /* synthetic */ CameraFilter access$getCameraFilter$p(Builder r0) {
            return r0.cameraFilter;
        }

        public static final /* synthetic */ boolean access$getRequireNonEmptyUseCases$p(Builder r0) {
            return r0.requireNonEmptyUseCases;
        }

        public static final /* synthetic */ int access$getSessionType$p(Builder r0) {
            return r0.sessionType;
        }

        public static final /* synthetic */ boolean access$isAutoRotationEnabled$p(Builder r0) {
            return r0.isAutoRotationEnabled;
        }

        public final SessionConfig build() {
            return new SessionConfig$Builder$build$1(this, this.useCases, null, CollectionsKt.toList(this.effects), this.frameRateRange, CollectionsKt.toSet(this.requiredFeatureGroup), CollectionsKt.toList(this.preferredFeatureGroup));
        }

        public final Builder setFrameRateRange(Range<Integer> r1) {
            this.frameRateRange = r1;
            return this;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[FeatureTypeInternal.values().length];
            r0[FeatureTypeInternal.DYNAMIC_RANGE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L15:
            r0[FeatureTypeInternal.FPS_RANGE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L23:
            r0[FeatureTypeInternal.VIDEO_STABILIZATION.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[FeatureTypeInternal.IMAGE_FORMAT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L19:
            r0[FeatureTypeInternal.RECORDING_QUALITY.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L8:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static void $r8$lambda$nHoExTCOzN2kfNQ5jRFz93oyuMM(Set r0) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public SessionConfig(List<? extends UseCase> r1, ViewPort r2, List<? extends CameraEffect> r3, Range<Integer> r4, Set<? extends GroupableFeature> r5, List<? extends GroupableFeature> r6) {
        this.effects = r3;
        this.frameRateRange = r4;
        this.requiredFeatureGroup = r5;
        this.preferredFeatureGroup = r6;
        this.useCases = CollectionsKt.distinct(r1);
        this.requireNonEmptyUseCases = true;
        this.featureSelectionListener = new SessionConfig$$ExternalSyntheticLambda0();
        this.featureSelectionListenerExecutor = CameraXExecutors.mainThreadExecutor();
        if (getRequireNonEmptyUseCases() == true) goto L5;
    L9:
        validateFrameRate();
        validateFeatureGroups();
        return;
    L5:
        if (r1.isEmpty() == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m("SessionConfig must contain at least one UseCase.");
        throw null;
    }

    public /* synthetic */ SessionConfig(List r8, ViewPort r9, List r10, Range r11, Set r12, List r13, int r14, DefaultConstructorMarker r15) {
        if ((r14 & 2) == 0) goto L5;
        r9 = null;
    L5:
        ViewPort r2 = r9;
        if ((r14 & 4) == 0) goto L8;
        r10 = CollectionsKt.emptyList();
    L8:
        List r3 = r10;
        if ((r14 & 8) == 0) goto L11;
        r11 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
    L11:
        Range r4 = r11;
        if ((r14 & 16) == 0) goto L14;
        r12 = SetsKt.emptySet();
    L14:
        Set r5 = r12;
        if ((r14 & 32) == 0) goto L17;
        r13 = CollectionsKt.emptyList();
    L17:
        this(r8, r2, r3, r4, r5, r13);
    }

    private final String getUseCaseName(UseCase r1) {
        if ((r1 instanceof Preview) == false) goto L7;
        return "Preview";
    L7:
        if ((r1 instanceof ImageCapture) == false) goto L11;
        return "ImageCapture";
    L11:
        if (UseCaseUtil.isVideoCapture(r1) == false) goto L14;
        return "VideoCapture";
    L14:
        return "UseCase";
    }

    private final void validateDefaultGroupableFeatureValues(UseCase r10) {
        String r9 = getUseCaseName(r10);
        FeatureTypeInternal r0 = UseCaseType.Companion.getAppConfiguredGroupableFeatureType$camera_core(r10);
        if (r0 != null) goto L5;
        return;
    L5:
        StringBuilder r1 = new StringBuilder("A ");
        r1.append(r0.name());
        r1.append(" value is set to ");
        r1.append(r9);
        r1.append(" despite using feature groups. Do not use APIs like ");
        int[] r2 = WhenMappings.$EnumSwitchMapping$0;
        int r3 = r2[r0.ordinal()];
        if (r3 == 1) goto L20;
        if (r3 == 2) goto L19;
        if (r3 == 3) goto L16;
        if (r3 == 4) goto L14;
        if (r3 != 5) goto L12;
        String r92 = "Recorder.Builder.setQualitySelector";
    L21:
        r1.append(r92);
        r1.append(" while using feature groups. If, for example, ");
        int r93 = r2[r0.ordinal()];
        if (r93 == 1) goto L33;
        if (r93 == 2) goto L32;
        if (r93 == 3) goto L31;
        if (r93 == 4) goto L30;
        if (r93 != 5) goto L28;
        String r94 = "UHD recording quality";
    L34:
        r1.append(r94);
        r1.append(" is required, instead set ");
        int r95 = r2[r0.ordinal()];
        if (r95 == 1) goto L46;
        if (r95 == 2) goto L45;
        if (r95 == 3) goto L44;
        if (r95 == 4) goto L43;
        if (r95 == 5) goto L42;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L42:
        String r96 = "GroupableFeatures.UHD_RECORDING";
    L47:
        r1.append(r96);
        r1.append(" as either a required or preferred feature.");
        throw new IllegalArgumentException(r1.toString().toString());
    L43:
        r96 = "GroupableFeature.IMAGE_ULTRA_HDR";
        goto L47
    L44:
        r96 = "GroupableFeature.PREVIEW_STABILIZATION";
        goto L47
    L45:
        r96 = "GroupableFeature.FPS_60";
        goto L47
    L46:
        r96 = "GroupableFeature.HDR_HLG10";
        goto L47
    L28:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L30:
        r94 = "JPEG_R output format";
        goto L34
    L31:
        r94 = "stabilization";
        goto L34
    L32:
        r94 = "60 FPS";
        goto L34
    L33:
        r94 = "HDR";
        goto L34
    L12:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        r92 = r9 + ".Builder.setOutputFormat";
        goto L21
    L16:
        if (UseCaseUtil.isVideoCapture(r10) == false) goto L18;
        r92 = r9 + ".Builder.setVideoStabilizationEnabled";
        goto L21
    L18:
        r92 = r9 + ".Builder.setPreviewStabilizationEnabled";
        goto L21
    L19:
        r92 = r9 + ".Builder.setTargetFrameRateRange";
        goto L21
    L20:
        r92 = r9 + ".Builder.setDynamicRange";
        goto L21
    }

    private final void validateFeatureGroups() {
        if (this.requiredFeatureGroup.isEmpty() == true) goto L5;
    L7:
        validateRequiredFeatures();
        if (CollectionsKt.distinct(this.preferredFeatureGroup).size() != this.preferredFeatureGroup.size()) goto L21;
        Set r0 = CollectionsKt.intersect(this.requiredFeatureGroup, this.preferredFeatureGroup);
        if (r0.isEmpty() == false) goto L19;
        Iterator<T> r02 = this.useCases.iterator();
    L13:
        if (r02.hasNext() == false) goto L26;
        UseCase r1 = (UseCase) r02.next();
        if (UseCaseType.Companion.getFeatureGroupUseCaseType(r1) == UseCaseType.UNDEFINED) goto L17;
        validateDefaultGroupableFeatureValues(r1);
        goto L13
    L17:
        Response$Builder$$ExternalSyntheticBUOutline0.m(r1, " is not supported with feature group");
        return;
    L26:
        return;
    L19:
        Options$Companion$$ExternalSyntheticBUOutline0.m("requiredFeatures and preferredFeatures have duplicate values: ", r0);
        return;
    L21:
        HttpUrl$Builder$$ExternalSyntheticBUOutline0.m("Duplicate values in preferredFeatures(", this.preferredFeatureGroup, 41);
        return;
    L5:
        if (this.preferredFeatureGroup.isEmpty() == false) goto L7;
    }

    private final void validateFrameRate() {
        if (Intrinsics.areEqual(this.frameRateRange, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == true) goto L16;
        Iterator<UseCase> r2 = this.useCases.iterator();
    L7:
        if (r2.hasNext() == false) goto L17;
        if (r2.next().getAppConfig().hasTargetFrameRate() == false) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Can't set target frame rate on a UseCase (by Preview.Builder.setTargetFrameRate() or VideoCapture.Builder.setTargetFrameRate()) if the frame rate range has already been set in the SessionConfig.");
        return;
    L17:
        return;
    }

    private final void validateRequiredFeatures() {
        Set<GroupableFeature> r0 = this.requiredFeatureGroup;
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator<T> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        r1.add(((GroupableFeature) r02.next()).getFeatureTypeInternal());
        goto L4
    L6:
        Iterator r03 = CollectionsKt.distinct(r1).iterator();
    L8:
        if (r03.hasNext() == false) goto L29;
        FeatureTypeInternal r12 = (FeatureTypeInternal) r03.next();
        Set<GroupableFeature> r2 = this.requiredFeatureGroup;
        ArrayList r3 = new ArrayList();
        Iterator<T> r22 = r2.iterator();
    L11:
        if (r22.hasNext() == false) goto L16;
        Object r4 = r22.next();
        if (((GroupableFeature) r4).getFeatureTypeInternal() != r12) goto L11;
        r3.add(r4);
        goto L11
    L16:
        if (r3.size() <= 1) goto L8;
        Options$Companion$$ExternalSyntheticBUOutline0.m("requiredFeatures has conflicting feature values: ", r3);
        return;
    }

    public CameraFilter getCameraFilter() {
        return this.cameraFilter;
    }

    public final List<CameraEffect> getEffects() {
        return this.effects;
    }

    public final Consumer<Set<GroupableFeature>> getFeatureSelectionListener() {
        return this.featureSelectionListener;
    }

    public final Executor getFeatureSelectionListenerExecutor() {
        return this.featureSelectionListenerExecutor;
    }

    public final Range<Integer> getFrameRateRange() {
        return this.frameRateRange;
    }

    public final List<GroupableFeature> getPreferredFeatureGroup() {
        return this.preferredFeatureGroup;
    }

    public abstract boolean getRequireNonEmptyUseCases();

    public final Set<GroupableFeature> getRequiredFeatureGroup() {
        return this.requiredFeatureGroup;
    }

    public int getSessionType() {
        return this.sessionType;
    }

    public final List<UseCase> getUseCases() {
        return this.useCases;
    }

    public final ViewPort getViewPort() {
        return null;
    }

    public boolean isAutoRotationEnabled() {
        return this.isAutoRotationEnabled;
    }

    public boolean isLegacy() {
        return this.isLegacy;
    }

    public String toString() {
        return "SessionConfig@" + Integer.toHexString(System.identityHashCode(this)) + " {useCases=" + this.useCases + ", frameRateRange=" + this.frameRateRange + ", requiredFeatureGroup=" + this.requiredFeatureGroup + ", preferredFeatureGroup=" + this.preferredFeatureGroup + ", effects=" + this.effects + ", viewPort=null}";
    }
}
