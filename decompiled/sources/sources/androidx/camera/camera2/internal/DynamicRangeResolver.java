package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.util.Log;
import androidx.camera.camera2.compat.DynamicRangeProfilesCompat;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001AB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JU\u0010\u000f\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010JK\u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J-\u0010\u001b\u001a\u00020\u001a2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ5\u0010!\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u00072\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b!\u0010\"J\u0017\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\u0007H\u0002¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020$2\u0006\u0010#\u001a\u00020\u0007H\u0002¢\u0006\u0004\b'\u0010&J-\u0010*\u001a\u00020$2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u00072\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b*\u0010+J\u001f\u0010.\u001a\u00020$2\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u0007H\u0002¢\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020$¢\u0006\u0004\b0\u00101JK\u00109\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\u0007082\f\u00104\u001a\b\u0012\u0004\u0012\u000203022\u0010\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b022\f\u00107\u001a\b\u0012\u0004\u0012\u00020602¢\u0006\u0004\b9\u0010:R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010;\u001a\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010@¨\u0006B"}, d2 = {"Landroidx/camera/camera2/internal/DynamicRangeResolver;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/DynamicRange;", "supportedDynamicRanges", "orderedExistingDynamicRanges", "orderedNewDynamicRanges", "Landroidx/camera/core/impl/UseCaseConfig;", "config", _UrlKt.FRAGMENT_ENCODE_SET, "outCombinedConstraints", "resolveDynamicRangeAndUpdateConstraints", "(Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Landroidx/camera/core/impl/UseCaseConfig;Ljava/util/Set;)Landroidx/camera/core/DynamicRange;", "requestedDynamicRange", "combinedConstraints", _UrlKt.FRAGMENT_ENCODE_SET, "rangeOwnerLabel", "resolveDynamicRange", "(Landroidx/camera/core/DynamicRange;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/lang/String;)Landroidx/camera/core/DynamicRange;", "newDynamicRange", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", "dynamicRangesInfo", _UrlKt.FRAGMENT_ENCODE_SET, "updateConstraints", "(Ljava/util/Set;Landroidx/camera/core/DynamicRange;Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;)V", "rangeToMatch", _UrlKt.FRAGMENT_ENCODE_SET, "fullySpecifiedCandidateRanges", "constraints", "findSupportedHdrMatch", "(Landroidx/camera/core/DynamicRange;Ljava/util/Collection;Ljava/util/Set;)Landroidx/camera/core/DynamicRange;", "dynamicRange", _UrlKt.FRAGMENT_ENCODE_SET, "isFullyUnspecified", "(Landroidx/camera/core/DynamicRange;)Z", "isPartiallySpecified", "rangeToResolve", "candidateRange", "canResolveWithinConstraints", "(Landroidx/camera/core/DynamicRange;Landroidx/camera/core/DynamicRange;Ljava/util/Set;)Z", "testRange", "fullySpecifiedRange", "canResolveDynamicRange", "(Landroidx/camera/core/DynamicRange;Landroidx/camera/core/DynamicRange;)Z", "is10BitDynamicRangeSupported", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "existingSurfaces", "newUseCaseConfigs", _UrlKt.FRAGMENT_ENCODE_SET, "useCasePriorityOrder", _UrlKt.FRAGMENT_ENCODE_SET, "resolveAndValidateDynamicRanges", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)Ljava/util/Map;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "getCameraMetadata", "()Landroidx/camera/camera2/pipe/CameraMetadata;", "is10BitSupported", "Z", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat;", "Api33Impl", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDynamicRangeResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DynamicRangeResolver.kt\nandroidx/camera/camera2/internal/DynamicRangeResolver\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,481:1\n85#2,4:482\n85#2,4:486\n85#2,4:490\n85#2,4:494\n85#2,4:498\n85#2,4:502\n*S KotlinDebug\n*F\n+ 1 DynamicRangeResolver.kt\nandroidx/camera/camera2/internal/DynamicRangeResolver\n*L\n218#1:482,4\n236#1:486,4\n256#1:490,4\n287#1:494,4\n316#1:498,4\n426#1:502,4\n*E\n"})
/* loaded from: classes3.dex */
public final class DynamicRangeResolver {
    private final CameraMetadata cameraMetadata;
    private final DynamicRangeProfilesCompat dynamicRangesInfo;
    private final boolean is10BitSupported;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/internal/DynamicRangeResolver$Api33Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRecommended10BitDynamicRange", "Landroidx/camera/core/DynamicRange;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api33Impl {
        public static final Api33Impl INSTANCE = null;

        static {
            INSTANCE = new Api33Impl();
        }

        private Api33Impl() {
        }

        public final DynamicRange getRecommended10BitDynamicRange(CameraMetadata r3) {
            Long r2 = (Long) r3.get(CameraCharacteristics.REQUEST_RECOMMENDED_TEN_BIT_DYNAMIC_RANGE_PROFILE);
            if (r2 != null) goto L5;
            return null;
        L5:
            return DynamicRangeConversions.INSTANCE.profileToDynamicRange(r2.longValue());
        }
    }

    public DynamicRangeResolver(CameraMetadata r3) {
        this.cameraMetadata = r3;
        int[] r0 = (int[]) r3.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (r0 == null) goto L5;
        boolean r02 = ArraysKt.contains(r0, 18);
    L6:
        this.is10BitSupported = r02;
        this.dynamicRangesInfo = DynamicRangeProfilesCompat.Companion.fromCameraMetaData(r3);
        return;
    L5:
        r02 = false;
        goto L6
    }

    private final boolean canResolveDynamicRange(DynamicRange r4, DynamicRange r5) {
        if (r5.isFullySpecified() == true) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Fully specified range ", r5, " not actually fully specified.");
        return false;
    L5:
        if (r4.getEncoding() != 2) goto L10;
        if (r5.getEncoding() != 1) goto L10;
        return false;
    L10:
        if (r4.getEncoding() == 2) goto L17;
        if (r4.getEncoding() == 0) goto L17;
        if (r4.getEncoding() == r5.getEncoding()) goto L17;
        return false;
    L17:
        if (r4.getBitDepth() != 0) goto L19;
    L22:
        return true;
    L19:
        if (r4.getBitDepth() == r5.getBitDepth()) goto L22;
        return false;
    }

    private final boolean canResolveWithinConstraints(DynamicRange r2, DynamicRange r3, Set<DynamicRange> r4) {
        if (r4.contains(r3) == true) goto L10;
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "DynamicRangeResolver: Candidate Dynamic range is not within constraints.\nDynamic range to resolve:\n  " + r2 + "\nCandidate dynamic range:\n  " + r3);
        return false;
    L11:
        return false;
    L10:
        return canResolveDynamicRange(r2, r3);
    }

    private final DynamicRange findSupportedHdrMatch(DynamicRange r6, Collection<DynamicRange> r7, Set<DynamicRange> r8) {
        if (r6.getEncoding() != 1) goto L5;
        return null;
    L5:
        Iterator<DynamicRange> r72 = r7.iterator();
    L7:
        if (r72.hasNext() == false) goto L16;
        DynamicRange r0 = r72.next();
        int r3 = r0.getEncoding();
        if (r0.isFullySpecified() == false) goto L15;
        if (r3 == 1) goto L7;
        if (canResolveWithinConstraints(r6, r0, r8) == false) goto L7;
        return r0;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Fully specified DynamicRange must have fully defined encoding.");
    L16:
        return null;
    }

    private final boolean isFullyUnspecified(DynamicRange r1) {
        return Intrinsics.areEqual(r1, DynamicRange.UNSPECIFIED);
    }

    private final boolean isPartiallySpecified(DynamicRange r2) {
        if (r2.getEncoding() != 2) goto L5;
        return true;
    L5:
        if (r2.getEncoding() == 0) goto L9;
        if (r2.getBitDepth() != 0) goto L9;
        return true;
    L9:
        if (r2.getEncoding() == 0) goto L11;
        return false;
    L11:
        if (r2.getBitDepth() != 0) goto L19;
        return false;
    L19:
        return true;
    }

    private final DynamicRange resolveDynamicRange(DynamicRange r7, Set<DynamicRange> r8, Set<DynamicRange> r9, Set<DynamicRange> r10, String r11) {
        if (r7.isFullySpecified() == true) goto L5;
        int r0 = r7.getEncoding();
        int r2 = r7.getBitDepth();
        if (r0 != 1) goto L15;
        if (r2 != 0) goto L15;
        DynamicRange r6 = DynamicRange.SDR;
        if (r8.contains(r6) == false) goto L14;
        return r6;
    L14:
        return null;
    L15:
        DynamicRange r92 = findSupportedHdrMatch(r7, r9, r8);
        if (r92 == null) goto L21;
        Camera2Logger r62 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "DynamicRangeResolver: Resolved dynamic range for use case " + r11 + " from existing attached surface.\n" + r7 + "\n->\n" + r92);
    L20:
        return r92;
    L21:
        DynamicRange r93 = findSupportedHdrMatch(r7, r10, r8);
        if (r93 == null) goto L27;
        Camera2Logger r63 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L26;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "DynamicRangeResolver: Resolved dynamic range for use case " + r11 + " from concurrently bound use case.\n" + r7 + "\n->\n" + r93);
    L26:
        return r93;
    L27:
        DynamicRange r94 = DynamicRange.SDR;
        if (canResolveWithinConstraints(r7, r94, r8) == false) goto L34;
        Camera2Logger r64 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L32;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "DynamicRangeResolver: Resolved dynamic range for use case " + r11 + " to no compatible HDR dynamic ranges.\n" + r7 + "\n->\n" + r94);
    L32:
        return r94;
    L34:
        if (r0 == 2) goto L36;
    L54:
        Iterator<DynamicRange> r82 = r8.iterator();
    L56:
        if (r82.hasNext() == false) goto L69;
        DynamicRange r95 = r82.next();
        if (r95.isFullySpecified() == false) goto L68;
        if (Intrinsics.areEqual(r95, DynamicRange.SDR) == true) goto L56;
        if (canResolveDynamicRange(r7, r95) == false) goto L56;
        Camera2Logger r65 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L67;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "DynamicRangeResolver: Resolved dynamic range for use case " + r11 + " from validated dynamic range constraints or supported HDR dynamic ranges.\n" + r7 + "\n->\n" + r95);
    L67:
        return r95;
    L68:
        Segment$$ExternalSyntheticBUOutline1.m("Candidate dynamic range must be fully specified.");
    L69:
        return null;
    L36:
        if (r2 == 10) goto L38;
        if (r2 != 0) goto L54;
    L38:
        LinkedHashSet r96 = new LinkedHashSet();
        if (Build.VERSION.SDK_INT < 33) goto L43;
        DynamicRange r102 = Api33Impl.INSTANCE.getRecommended10BitDynamicRange(this.cameraMetadata);
        if (r102 == null) goto L44;
        r96.add(r102);
    L44:
        r96.add(DynamicRange.HLG_10_BIT);
        DynamicRange r97 = findSupportedHdrMatch(r7, r96, r8);
        if (r97 == null) goto L54;
        Camera2Logger r66 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L53;
        String r67 = Camera2Logger.access$getTRUNCATED_TAG$p();
        StringBuilder r83 = new StringBuilder("DynamicRangeResolver: Resolved dynamic range for use case ");
        r83.append(r11);
        r83.append("from ");
        if (Intrinsics.areEqual(r97, r102) == false) goto L51;
        String r103 = "recommended";
    L52:
        r83.append(r103);
        r83.append(" 10-bit supported dynamic range.\n");
        r83.append(r7);
        r83.append("\n->\n");
        r83.append(r97);
        Log.d(r67, r83.toString());
        goto L53
    L51:
        r103 = "required";
    L53:
        return r97;
    L43:
        r102 = null;
        goto L44
    L5:
        if (r8.contains(r7) == false) goto L7;
        return r7;
    L7:
        return null;
    }

    private final DynamicRange resolveDynamicRangeAndUpdateConstraints(Set<DynamicRange> r7, Set<DynamicRange> r8, Set<DynamicRange> r9, UseCaseConfig<?> r10, Set<DynamicRange> r11) {
        DynamicRange r1 = r10.getDynamicRange();
        DynamicRange r6 = resolveDynamicRange(r1, r11, r8, r9, r10.getTargetName());
        if (r6 == null) goto L7;
        updateConstraints(r11, r6, this.dynamicRangesInfo);
        return r6;
    L7:
        throw new IllegalArgumentException("Unable to resolve supported dynamic range. The dynamic range may not be supported on the device or may not be allowed concurrently with other attached use cases.\nUse case:\n  " + r10.getTargetName() + "\nRequested dynamic range:\n  " + r1 + "\nSupported dynamic ranges:\n  " + r7 + "\nConstrained set of concurrent dynamic ranges:\n  " + r11);
    }

    private final void updateConstraints(Set<DynamicRange> r2, DynamicRange r3, DynamicRangeProfilesCompat r4) {
        Preconditions.checkState(!r2.isEmpty(), "Cannot update already-empty constraints.");
        Set<DynamicRange> r1 = r4.getDynamicRangeCaptureRequestConstraints(r3);
        if (r1.isEmpty() == true) goto L9;
        Set r42 = CollectionsKt.toSet(r2);
        r2.retainAll(r1);
        if (r2.isEmpty() == true) goto L8;
        return;
    L8:
        throw new IllegalArgumentException(("Constraints of dynamic range cannot be combined with existing constraints.\nDynamic range:\n  " + r3 + "\nConstraints:\n  " + r1 + "\nExisting constraints:\n  " + r42).toString());
    }

    public final boolean is10BitDynamicRangeSupported() {
        return this.is10BitSupported;
    }

    public final Map<UseCaseConfig<?>, DynamicRange> resolveAndValidateDynamicRanges(List<? extends AttachedSurfaceInfo> r9, List<? extends UseCaseConfig<?>> r10, List<Integer> r11) {
        LinkedHashSet r2 = new LinkedHashSet();
        Iterator<? extends AttachedSurfaceInfo> r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L6;
        r2.add(r92.next().getDynamicRange());
        goto L4
    L6:
        Set<DynamicRange> r1 = this.dynamicRangesInfo.getSupportedDynamicRanges();
        Set<DynamicRange> r5 = CollectionsKt.toMutableSet(r1);
        Iterator<DynamicRange> r93 = r2.iterator();
    L8:
        if (r93.hasNext() == false) goto L10;
        updateConstraints(r5, r93.next(), this.dynamicRangesInfo);
        goto L8
    L10:
        ArrayList r94 = new ArrayList();
        ArrayList r0 = new ArrayList();
        ArrayList r3 = new ArrayList();
        Iterator<Integer> r112 = r11.iterator();
    L12:
        if (r112.hasNext() == false) goto L20;
        UseCaseConfig<?> r4 = r10.get(r112.next().intValue());
        DynamicRange r6 = r4.getDynamicRange();
        if (isFullyUnspecified(r6) == true) goto L15;
        if (isPartiallySpecified(r6) == true) goto L18;
        r94.add(r4);
        goto L12
    L18:
        r0.add(r4);
        goto L12
    L15:
        r3.add(r4);
        goto L12
    L20:
        LinkedHashMap r102 = new LinkedHashMap();
        LinkedHashSet r32 = new LinkedHashSet();
        ArrayList r62 = new ArrayList();
        r62.addAll(r94);
        r62.addAll(r0);
        r62.addAll(r3);
        int r95 = r62.size();
        int r113 = 0;
    L21:
        if (r113 >= r95) goto L26;
        Object r02 = r62.get(r113);
        r113 = r113 + 1;
        UseCaseConfig<?> r42 = (UseCaseConfig) r02;
        DynamicRangeResolver r03 = this;
        DynamicRange r8 = r03.resolveDynamicRangeAndUpdateConstraints(r1, r2, r32, r42, r5);
        r102.put(r42, r8);
        if (r2.contains(r8) == true) goto L25;
        r32.add(r8);
    L25:
        DynamicRangeResolver this = r03;
        goto L21
    L26:
        return r102;
    }
}
