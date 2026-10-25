package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Size;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.compat.workaround.OutputSizesCorrector;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.internal.utils.SizeUtil;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 *2\u00020\u0001:\u0001*B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J8\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u0002H\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00160\u001b\"\u0004\b\u0000\u0010\u001c2\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u0002H\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00160\u001bJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\fJ&\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00160\u00162\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00160\u0016J'\u0010#\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0%\u0018\u00010$2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\f0\u0016¢\u0006\u0002\u0010'J$\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u0016\"\u0004\b\u0000\u0010\u001c*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u00160\u0016H\u0002J\u001c\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0%0\u00162\u0006\u0010 \u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0006\u0010\bR\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u0017\u0010\u0018¨\u0006+"}, d2 = {"Landroidx/camera/camera2/internal/HighSpeedResolver;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;)V", "isHighSpeedSupported", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "isHighSpeedSupported$delegate", "Lkotlin/Lazy;", "maxSize", "Landroid/util/Size;", "getMaxSize", "()Landroid/util/Size;", "maxSize$delegate", "streamConfigurationMapCompat", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", "getStreamConfigurationMapCompat", "()Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", "streamConfigurationMapCompat$delegate", "supportedSizes", _UrlKt.FRAGMENT_ENCODE_SET, "getSupportedSizes", "()Ljava/util/List;", "supportedSizes$delegate", "filterCommonSupportedSizes", _UrlKt.FRAGMENT_ENCODE_SET, "T", "sizesMap", "getMaxFrameRate", _UrlKt.FRAGMENT_ENCODE_SET, "size", "getSizeArrangements", "sizesList", "getFrameRateRangesFor", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Range;", "surfaceSizes", "(Ljava/util/List;)[Landroid/util/Range;", "findCommonElements", "getHighSpeedVideoFpsRangesFor", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHighSpeedResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HighSpeedResolver.kt\nandroidx/camera/camera2/internal/HighSpeedResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,237:1\n774#2:238\n865#2,2:239\n1252#2,2:243\n774#2:245\n865#2,2:246\n1255#2:248\n1563#2:250\n1634#2,3:251\n774#2:254\n865#2,2:255\n1869#2,2:261\n1969#2,14:265\n465#3:241\n415#3:242\n1#4:249\n37#5:257\n36#5,3:258\n12667#6,2:263\n*S KotlinDebug\n*F\n+ 1 HighSpeedResolver.kt\nandroidx/camera/camera2/internal/HighSpeedResolver\n*L\n80#1:238\n80#1:239,2\n81#1:243,2\n81#1:245\n81#1:246,2\n81#1:248\n130#1:250\n130#1:251,3\n160#1:254\n160#1:255,2\n182#1:261,2\n48#1:265,14\n81#1:241\n81#1:242\n164#1:257\n164#1:258,3\n41#1:263,2\n*E\n"})
/* loaded from: classes3.dex */
public final class HighSpeedResolver {
    public static final Companion Companion = null;
    private static final Range<Integer> DEFAULT_FPS = null;
    private final CameraMetadata cameraMetadata;
    private final Lazy isHighSpeedSupported$delegate;
    private final Lazy maxSize$delegate;
    private final Lazy streamConfigurationMapCompat$delegate;
    private final Lazy supportedSizes$delegate;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u000eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/internal/HighSpeedResolver$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "DEFAULT_FPS", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "getDEFAULT_FPS", "()Landroid/util/Range;", "isHighSpeedOn", _UrlKt.FRAGMENT_ENCODE_SET, "attachedSurfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "newUseCaseConfigs", "Landroidx/camera/core/impl/UseCaseConfig;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHighSpeedResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HighSpeedResolver.kt\nandroidx/camera/camera2/internal/HighSpeedResolver$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,237:1\n1563#2:238\n1634#2,3:239\n1563#2:242\n1634#2,3:243\n1761#2,3:246\n1740#2,3:249\n*S KotlinDebug\n*F\n+ 1 HighSpeedResolver.kt\nandroidx/camera/camera2/internal/HighSpeedResolver$Companion\n*L\n222#1:238\n222#1:239,3\n223#1:242\n223#1:243,3\n225#1:246,3\n229#1:249,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Range<Integer> getDEFAULT_FPS() {
            return HighSpeedResolver.access$getDEFAULT_FPS$cp();
        }

        @JvmStatic
        public final boolean isHighSpeedOn(Collection<? extends AttachedSurfaceInfo> r4, Collection<? extends UseCaseConfig<?>> r5) {
            Collection<? extends AttachedSurfaceInfo> r42 = r4;
            ArrayList r3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r42, 10));
            Iterator<T> r43 = r42.iterator();
        L4:
            if (r43.hasNext() == false) goto L6;
            r3.add(Integer.valueOf(((AttachedSurfaceInfo) r43.next()).getSessionType()));
            goto L4
        L6:
            Collection<? extends UseCaseConfig<?>> r52 = r5;
            ArrayList r44 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r52, 10));
            Iterator<T> r53 = r52.iterator();
        L8:
            if (r53.hasNext() == false) goto L10;
            r44.add(Integer.valueOf(((UseCaseConfig) r53.next()).getSessionType(0)));
            goto L8
        L10:
            List r32 = CollectionsKt.plus(r3, r44);
            boolean r45 = r32 instanceof Collection;
            if (r45 == true) goto L13;
        L15:
            Iterator r0 = r32.iterator();
        L17:
            if (r0.hasNext() == false) goto L14;
            if (((Number) r0.next()).intValue() != 1) goto L17;
            boolean r02 = true;
        L21:
            if (r02 == false) goto L34;
            if (r45 == true) goto L24;
        L26:
            Iterator r33 = r32.iterator();
        L28:
            if (r33.hasNext() == false) goto L34;
            if (((Number) r33.next()).intValue() == 1) goto L28;
            f$$ExternalSyntheticBUOutline1.m("All sessionTypes should be high-speed when any of them is high-speed");
            return false;
        L24:
            if (r32.isEmpty() == false) goto L26;
            return r02;
        L34:
            return r02;
        L14:
            r02 = false;
            goto L21
        L13:
            if (r32.isEmpty() == false) goto L15;
            goto L14
        }
    }

    public static StreamConfigurationMapCompat $r8$lambda$F31AeowgvxM3uU8JKUsYWGum9Ro(HighSpeedResolver r3) {
        StreamConfigurationMap r0 = (StreamConfigurationMap) r3.cameraMetadata.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r0 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Cannot retrieve SCALER_STREAM_CONFIGURATION_MAP");
        return null;
    L5:
        return new StreamConfigurationMapCompat(r0, new OutputSizesCorrector(r3.cameraMetadata, r0));
    }

    public static boolean $r8$lambda$O5QZeAnVoeGmQjN2Jc9dlH23mag(HighSpeedResolver r5) {
        int[] r52 = (int[]) r5.cameraMetadata.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (r52 == null) goto L11;
        int r1 = r52.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L11;
        if (r52[r2] == 9) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return true;
    L11:
        return false;
    }

    public static Size $r8$lambda$ZNvvifbt712SApORMdG7iRlzVn4(HighSpeedResolver r4) {
        List<Size> r42 = r4.getSupportedSizes();
        if (r42.isEmpty() == false) goto L6;
        r42 = null;
    L6:
        if (r42 == null) goto L21;
        Iterator<T> r43 = r42.iterator();
        if (r43.hasNext() == false) goto L20;
        Object r0 = r43.next();
        if (r43.hasNext() == false) goto L19;
        int r1 = SizeUtil.getArea((Size) r0);
    L13:
        Object r2 = r43.next();
        int r3 = SizeUtil.getArea((Size) r2);
        if (r1 >= r3) goto L17;
        r0 = r2;
        r1 = r3;
    L17:
        if (r43.hasNext() == true) goto L13;
    L19:
        return (Size) r0;
    L20:
        Utils$$ExternalSyntheticBUOutline0.m();
    L21:
        return null;
    }

    public static List $r8$lambda$y1qpmO2j3UYk6EnWiZS3G3Hx_Sw(HighSpeedResolver r0) {
        Size[] r02 = r0.getStreamConfigurationMapCompat().getHighSpeedVideoSizes();
        if (r02 == null) goto L9;
        List r03 = ArraysKt.toList(r02);
        if (r03 == null) goto L9;
        return r03;
    L9:
        return CollectionsKt.emptyList();
    }

    static {
        Companion = new Companion(null);
        DEFAULT_FPS = new Range(120, 120);
    }

    public HighSpeedResolver(CameraMetadata r1) {
        this.cameraMetadata = r1;
        this.isHighSpeedSupported$delegate = LazyKt.lazy(new HighSpeedResolver$$ExternalSyntheticLambda0(this));
        this.maxSize$delegate = LazyKt.lazy(new HighSpeedResolver$$ExternalSyntheticLambda1(this));
        this.streamConfigurationMapCompat$delegate = LazyKt.lazy(new HighSpeedResolver$$ExternalSyntheticLambda2(this));
        this.supportedSizes$delegate = LazyKt.lazy(new HighSpeedResolver$$ExternalSyntheticLambda3(this));
    }

    public static final /* synthetic */ Range access$getDEFAULT_FPS$cp() {
        return DEFAULT_FPS;
    }

    private final <T> List<T> findCommonElements(List<? extends List<? extends T>> r2) {
        if (r2.isEmpty() == true) goto L5;
        List<T> r1 = CollectionsKt.toMutableList((Collection) CollectionsKt.first(r2));
        Iterator<T> r22 = CollectionsKt.drop(r2, 1).iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        r1.retainAll((List) r22.next());
        goto L8
    L10:
        return r1;
    L5:
        return CollectionsKt.emptyList();
    }

    private final List<Range<Integer>> getHighSpeedVideoFpsRangesFor(Size r2) {
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L4
        Object r1 = Result.m2018constructorimpl(getStreamConfigurationMapCompat().getHighSpeedVideoFpsRangesFor(r2));     // Catch: Throwable -> L4
    L7:
        if (Result.m2024isFailureimpl(r1) == false) goto L9;
        r1 = null;
    L9:
        Range[] r12 = (Range[]) r1;
        if (r12 == null) goto L17;
        List r13 = ArraysKt.filterNotNull(r12);
        if (r13 == null) goto L17;
        List<Range<Integer>> r14 = CollectionsKt.toList(r13);
        if (r14 == null) goto L17;
        return r14;
    L17:
        return CollectionsKt.emptyList();
    L4:
        th = move-exception;
        Result.Companion r22 = Result.Companion;
        r1 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L7
    }

    private final StreamConfigurationMapCompat getStreamConfigurationMapCompat() {
        return (StreamConfigurationMapCompat) this.streamConfigurationMapCompat$delegate.getValue();
    }

    private final List<Size> getSupportedSizes() {
        return (List) this.supportedSizes$delegate.getValue();
    }

    public final <T> Map<T, List<Size>> filterCommonSupportedSizes(Map<T, ? extends List<Size>> r7) {
        List<T> r0 = findCommonElements(CollectionsKt.toList(r7.values()));
        ArrayList r1 = new ArrayList();
        Iterator<T> r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L8;
        T r2 = r02.next();
        List<Size> r4 = getSupportedSizes();
        if (r4.contains((Size) r2) == false) goto L4;
        r1.add(r2);
        goto L4
    L8:
        LinkedHashMap r6 = new LinkedHashMap(MapsKt.mapCapacity(r7.size()));
        Iterator<T> r72 = r7.entrySet().iterator();
    L10:
        if (r72.hasNext() == false) goto L18;
        Map.Entry r03 = (Map.Entry) r72.next();
        Object r22 = r03.getKey();
        List r04 = (List) r03.getValue();
        ArrayList r3 = new ArrayList();
        Iterator<T> r05 = r04.iterator();
    L13:
        if (r05.hasNext() == false) goto L17;
        Object r42 = r05.next();
        if (r1.contains((Size) r42) == false) goto L13;
        r3.add(r42);
        goto L13
    L17:
        r6.put(r22, r3);
        goto L10
    L18:
        return r6;
    }

    public final Range<Integer>[] getFrameRateRangesFor(List<Size> r5) {
        int r0 = r5.size();
        if (1 <= r0) goto L5;
    L26:
        return null;
    L5:
        if (r0 >= 3) goto L26;
        if (CollectionsKt.distinct(r5).size() != 1) goto L26;
        List<Range<Integer>> r4 = getHighSpeedVideoFpsRangesFor(r5.get(0));
        if (r4.isEmpty() == false) goto L13;
        r4 = null;
    L13:
        if (r4 != null) goto L16;
        return null;
    L16:
        if (r5.size() != 2) goto L25;
        ArrayList r52 = new ArrayList();
        Iterator<T> r42 = r4.iterator();
    L19:
        if (r42.hasNext() == false) goto L23;
        Object r1 = r42.next();
        Range r2 = (Range) r1;
        if (Intrinsics.areEqual(r2.getLower(), r2.getUpper()) == false) goto L19;
        r52.add(r1);
        goto L19
    L23:
        r4 = r52;
    L25:
        return (Range[]) r4.toArray(new Range[0]);
    }

    public final int getMaxFrameRate(Size r3) {
        List<Range<Integer>> r2 = getHighSpeedVideoFpsRangesFor(r3);
        if (r2.isEmpty() == false) goto L7;
        r2 = null;
    L7:
        if (r2 != null) goto L10;
        Logger.w("HighSpeedResolver", "No supported high speed  fps for " + r3);
        return 0;
    L10:
        Iterator<T> r22 = r2.iterator();
        if (r22.hasNext() == false) goto L20;
        Integer r32 = (Integer) ((Range) r22.next()).getUpper();
    L14:
        if (r22.hasNext() == false) goto L19;
        Integer r0 = (Integer) ((Range) r22.next()).getUpper();
        if (r32.compareTo(r0) >= 0) goto L14;
        r32 = r0;
        goto L14
    L19:
        return r32.intValue();
    L20:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public final Size getMaxSize() {
        return (Size) this.maxSize$delegate.getValue();
    }

    public final List<List<Size>> getSizeArrangements(List<? extends List<Size>> r6) {
        if (r6.isEmpty() == true) goto L5;
        List r5 = findCommonElements(r6);
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r5, 10));
        Iterator r52 = r5.iterator();
    L8:
        if (r52.hasNext() == false) goto L13;
        Size r1 = (Size) r52.next();
        int r2 = r6.size();
        ArrayList r3 = new ArrayList(r2);
        int r4 = 0;
    L10:
        if (r4 >= r2) goto L12;
        r3.add(r1);
        r4 = r4 + 1;
        goto L10
    L12:
        r0.add(r3);
        goto L8
    L13:
        return r0;
    L5:
        return CollectionsKt.emptyList();
    }

    public final boolean isHighSpeedSupported() {
        return ((Boolean) this.isHighSpeedSupported$delegate.getValue()).booleanValue();
    }
}
