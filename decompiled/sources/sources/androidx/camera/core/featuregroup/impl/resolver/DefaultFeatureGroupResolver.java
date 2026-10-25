package androidx.camera.core.featuregroup.impl.resolver;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.ResolvedFeatureGroup;
import androidx.camera.core.featuregroup.impl.UseCaseType;
import androidx.camera.core.featuregroup.impl.feature.FeatureTypeInternal;
import androidx.camera.core.featuregroup.impl.feature.VideoStabilizationFeature;
import androidx.camera.core.featuregroup.impl.resolver.FeatureGroupResolutionResult;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import androidx.camera.core.impl.utils.UseCaseUtil;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\"\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J8\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0002J\u0012\u0010\u0015\u001a\u00020\u0016*\b\u0012\u0004\u0012\u00020\f0\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/DefaultFeatureGroupResolver;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolver;", "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "<init>", "(Landroidx/camera/core/impl/CameraInfoInternal;)V", "resolveFeatureGroup", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", "sessionConfig", "Landroidx/camera/core/SessionConfig;", "getMissingUseCase", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$UseCaseMissing;", "Landroidx/camera/core/featuregroup/GroupableFeature;", "useCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "getFeatureListResolvedByPriority", "orderedPreferredFeatures", "index", _UrlKt.FRAGMENT_ENCODE_SET, "currentOptionalFeatures", "isConflictFree", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDefaultFeatureGroupResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultFeatureGroupResolver.kt\nandroidx/camera/core/featuregroup/impl/resolver/DefaultFeatureGroupResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,229:1\n1869#2,2:230\n1869#2,2:232\n774#2:234\n865#2,2:235\n1761#2,3:237\n1761#2,3:240\n1761#2,3:243\n1761#2,3:246\n1563#2:250\n1634#2,3:251\n1869#2:254\n774#2:255\n865#2,2:256\n1870#2:258\n1#3:249\n*S KotlinDebug\n*F\n+ 1 DefaultFeatureGroupResolver.kt\nandroidx/camera/core/featuregroup/impl/resolver/DefaultFeatureGroupResolver\n*L\n81#1:230,2\n89#1:232,2\n96#1:234\n96#1:235,2\n112#1:237,3\n113#1:240,3\n115#1:243,3\n116#1:246,3\n213#1:250\n213#1:251,3\n214#1:254\n215#1:255\n215#1:256,2\n214#1:258\n*E\n"})
/* loaded from: classes3.dex */
public final class DefaultFeatureGroupResolver implements FeatureGroupResolver {
    private static final Companion Companion = null;
    private final CameraInfoInternal cameraInfoInternal;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/DefaultFeatureGroupResolver$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

        static {
            int[] r0 = new int[VideoStabilization.values().length];
            r0[VideoStabilization.PREVIEW.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[VideoStabilization.ON.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L6:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[FeatureTypeInternal.values().length];
            r02[FeatureTypeInternal.IMAGE_FORMAT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L16
        L33:
            r02[FeatureTypeInternal.DYNAMIC_RANGE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L17
        L27:
            r02[FeatureTypeInternal.FPS_RANGE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L18
        L29:
            r02[FeatureTypeInternal.VIDEO_STABILIZATION.ordinal()] = 4;     // Catch: NoSuchFieldError -> L19
        L21:
            r02[FeatureTypeInternal.RECORDING_QUALITY.ordinal()] = 5;     // Catch: NoSuchFieldError -> L20
        L12:
            $EnumSwitchMapping$1 = r02;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public DefaultFeatureGroupResolver(CameraInfoInternal r1) {
        this.cameraInfoInternal = r1;
    }

    private final FeatureGroupResolutionResult getFeatureListResolvedByPriority(SessionConfig r3, List<? extends GroupableFeature> r4, int r5, List<? extends GroupableFeature> r6) {
        if (r5 < r4.size()) goto L12;
        Set<? extends GroupableFeature> r42 = SetsKt.plus(r3.getRequiredFeatureGroup(), r6);
        Logger.d("DefaultFeatureGroupResolver", "getFeatureListResolvedByPriority: features = " + r42 + ", useCases = " + r3.getUseCases());
        if (isConflictFree(r42) == false) goto L11;
        if (this.cameraInfoInternal.isResolvedFeatureGroupSupported(new ResolvedFeatureGroup(r42), r3) == false) goto L11;
        return new FeatureGroupResolutionResult.Supported(new ResolvedFeatureGroup(r42));
    L11:
        return FeatureGroupResolutionResult.Unsupported.INSTANCE;
    L12:
        int r0 = r5 + 1;
        FeatureGroupResolutionResult r52 = getFeatureListResolvedByPriority(r3, r4, r0, CollectionsKt.plus(r6, r4.get(r5)));
        if ((r52 instanceof FeatureGroupResolutionResult.Supported) == false) goto L16;
        return r52;
    L16:
        return getFeatureListResolvedByPriority(r3, r4, r0, r6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FeatureGroupResolutionResult getFeatureListResolvedByPriority$default(DefaultFeatureGroupResolver r0, SessionConfig r1, List r2, int r3, List r4, int r5, Object r6) {
        if ((r5 & 4) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 8) == 0) goto L9;
        r4 = CollectionsKt.emptyList();
    L9:
        return r0.getFeatureListResolvedByPriority(r1, r2, r3, r4);
    }

    private final FeatureGroupResolutionResult.UseCaseMissing getMissingUseCase(GroupableFeature r8, List<? extends UseCase> r9) {
        List<? extends UseCase> r92 = r9;
        boolean r7 = r92 instanceof Collection;
        boolean r1 = false;
        if (r7 == true) goto L5;
    L7:
        Iterator<T> r2 = r92.iterator();
    L9:
        if (r2.hasNext() == false) goto L6;
        if ((((UseCase) r2.next()) instanceof ImageCapture) == false) goto L9;
        boolean r22 = true;
    L13:
        if (r7 == true) goto L15;
    L17:
        Iterator<T> r3 = r92.iterator();
    L19:
        if (r3.hasNext() == false) goto L16;
        UseCase r4 = (UseCase) r3.next();
        if ((r4 instanceof Preview) == true) goto L24;
        if (UseCaseUtil.isVideoCapture(r4) == false) goto L19;
    L24:
        boolean r32 = true;
    L25:
        if (r7 == true) goto L27;
    L29:
        Iterator<T> r42 = r92.iterator();
    L31:
        if (r42.hasNext() == false) goto L28;
        UseCase r5 = (UseCase) r42.next();
        if ((r5 instanceof Preview) == true) goto L36;
        if (UseCaseUtil.isVideoCapture(r5) == false) goto L31;
    L36:
        boolean r43 = true;
    L37:
        if (r7 == true) goto L39;
    L41:
        Iterator<T> r72 = r92.iterator();
    L43:
        if (r72.hasNext() == false) goto L47;
        if (UseCaseUtil.isVideoCapture((UseCase) r72.next()) == false) goto L43;
        r1 = true;
    L47:
        int r73 = WhenMappings.$EnumSwitchMapping$1[r8.getFeatureTypeInternal().ordinal()];
        if (r73 != 1) goto L50;
        String r74 = UseCaseType.IMAGE_CAPTURE.toString();
        if (r22 == false) goto L81;
    L60:
        r74 = null;
    L81:
        if (r74 != null) goto L83;
        return null;
    L83:
        return new FeatureGroupResolutionResult.UseCaseMissing(r74, r8);
    L50:
        if (r73 != 2) goto L52;
        r74 = UseCaseType.PREVIEW + " or " + UseCaseType.VIDEO_CAPTURE;
        if (r32 == true) goto L60;
    L52:
        if (r73 != 3) goto L54;
        r74 = UseCaseType.PREVIEW + " or " + UseCaseType.VIDEO_CAPTURE + " or " + UseCaseType.IMAGE_ANALYSIS;
        if (r43 == true) goto L60;
    L54:
        if (r73 != 4) goto L56;
        int r75 = WhenMappings.$EnumSwitchMapping$0[((VideoStabilizationFeature) r8).getVideoStabilization().ordinal()];
        if (r75 == 1) goto L70;
        if (r75 != 2) goto L60;
        r74 = UseCaseType.VIDEO_CAPTURE.toString();
        if (r1 == true) goto L60;
    L70:
        r74 = UseCaseType.PREVIEW + " or " + UseCaseType.VIDEO_CAPTURE + " or " + UseCaseType.IMAGE_ANALYSIS;
        if (r43 == true) goto L60;
    L56:
        if (r73 != 5) goto L61;
        r74 = UseCaseType.VIDEO_CAPTURE.toString();
        if (r1 == true) goto L60;
    L61:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L39:
        if (r92.isEmpty() == false) goto L41;
    L28:
        r43 = false;
        goto L37
    L27:
        if (r92.isEmpty() == false) goto L29;
    L16:
        r32 = false;
        goto L25
    L15:
        if (r92.isEmpty() == false) goto L17;
    L6:
        r22 = false;
        goto L13
    L5:
        if (r92.isEmpty() == false) goto L7;
        goto L6
    }

    private final boolean isConflictFree(Set<? extends GroupableFeature> r7) {
        ArrayList r6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r7, 10));
        Iterator<T> r0 = r7.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r6.add(((GroupableFeature) r0.next()).getFeatureTypeInternal());
        goto L4
    L6:
        Iterator r62 = CollectionsKt.distinct(r6).iterator();
    L8:
        if (r62.hasNext() == false) goto L19;
        FeatureTypeInternal r02 = (FeatureTypeInternal) r62.next();
        ArrayList r2 = new ArrayList();
        Iterator<T> r3 = r7.iterator();
    L11:
        if (r3.hasNext() == false) goto L16;
        Object r4 = r3.next();
        if (((GroupableFeature) r4).getFeatureTypeInternal() != r02) goto L11;
        r2.add(r4);
        goto L11
    L16:
        if (r2.size() <= 1) goto L8;
        return false;
    L19:
        return true;
    }

    @Override // androidx.camera.core.featuregroup.impl.resolver.FeatureGroupResolver
    public FeatureGroupResolutionResult resolveFeatureGroup(SessionConfig r13) {
        List<UseCase> r0 = r13.getUseCases();
        Set<GroupableFeature> r1 = r13.getRequiredFeatureGroup();
        List<GroupableFeature> r2 = r13.getPreferredFeatureGroup();
        if (r1.isEmpty() == true) goto L5;
    L9:
        Iterator<T> r3 = r0.iterator();
    L11:
        if (r3.hasNext() == false) goto L16;
        UseCase r5 = (UseCase) r3.next();
        if (UseCaseType.Companion.getFeatureGroupUseCaseType(r5) != UseCaseType.UNDEFINED) goto L11;
        return new FeatureGroupResolutionResult.UnsupportedUseCase(r5);
    L16:
        Iterator<T> r12 = r1.iterator();
    L18:
        if (r12.hasNext() == false) goto L22;
        FeatureGroupResolutionResult.UseCaseMissing r32 = getMissingUseCase((GroupableFeature) r12.next(), r0);
        if (r32 == null) goto L18;
        return r32;
    L22:
        ArrayList r7 = new ArrayList();
        Iterator<T> r14 = r2.iterator();
    L24:
        if (r14.hasNext() == false) goto L31;
        Object r22 = r14.next();
        FeatureGroupResolutionResult.UseCaseMissing r52 = getMissingUseCase((GroupableFeature) r22, r0);
        if (r52 == null) goto L28;
        Logger.d("DefaultFeatureGroupResolver", "resolveFeatureGroup: filtered out preferred feature due to " + r52);
    L29:
        if (r52 != null) goto L24;
        r7.add(r22);
        goto L24
    L28:
        r52 = null;
        goto L29
    L31:
        Logger.d("DefaultFeatureGroupResolver", "resolveFeatureGroup: filteredPreferredFeatures = " + r7);
        return getFeatureListResolvedByPriority$default(this, r13, r7, 0, null, 12, null);
    L5:
        if (r2.isEmpty() == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Must have at least one required or preferred feature");
        return null;
    }
}
