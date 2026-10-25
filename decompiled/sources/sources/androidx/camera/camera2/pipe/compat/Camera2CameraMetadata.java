package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.os.Build;
import android.os.Trace;
import android.util.ArrayMap;
import androidx.camera.camera2.pipe.CameraExtensionMetadata;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0018\u0010\r\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n\u0012\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0015\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0013*\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J&\u0010\u0017\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J+\u0010\u001a\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001e\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0013*\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010#\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0002H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010'\u001a\u00020&2\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b'\u0010(R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010+R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010,\u001a\u0004\b\u0005\u0010-R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010.R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010/R&\u0010\r\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u00100R\u001e\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u00101R&\u00103\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0006\u0012\u0004\u0018\u00010\f028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b3\u00104R \u00105\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020&028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b5\u00104R \u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R$\u00109\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00108R$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030:0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u00108R$\u0010=\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030<0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u00108R \u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u00108R$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030:0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u00108R$\u0010@\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u00108R$\u0010A\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030:0\u000e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u00108R\u001e\u0010D\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030:0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010CR\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020$0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010C¨\u0006I"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraMetadata;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroidx/camera/camera2/pipe/CameraId;", "camera", _UrlKt.FRAGMENT_ENCODE_SET, "isRedacted", "Landroid/hardware/camera2/CameraCharacteristics;", "characteristics", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", "metadataProvider", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Metadata$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "metadata", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CameraCharacteristics$Key;", "cacheBlocklist", "<init>", "(Ljava/lang/String;ZLandroid/hardware/camera2/CameraCharacteristics;Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;Ljava/util/Map;Ljava/util/Set;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "T", PluginsConstants.Settings.KEY, "getOrThrow", "(Landroid/hardware/camera2/CameraCharacteristics;Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;", "get", "(Landroid/hardware/camera2/CameraCharacteristics$Key;)Ljava/lang/Object;", PluginsConstants.Settings.DEFAULT, "getOrDefault", "(Landroid/hardware/camera2/CameraCharacteristics$Key;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "cameraId", "awaitPhysicalMetadata-EfqyGwQ", "(Ljava/lang/String;)Landroidx/camera/camera2/pipe/CameraMetadata;", "awaitPhysicalMetadata", _UrlKt.FRAGMENT_ENCODE_SET, "extension", "Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "awaitExtensionMetadata", "(I)Landroidx/camera/camera2/pipe/CameraExtensionMetadata;", "Ljava/lang/String;", "getCamera-Dz_R5H8", "()Ljava/lang/String;", "Z", "()Z", "Landroid/hardware/camera2/CameraCharacteristics;", "Landroidx/camera/camera2/pipe/compat/Camera2MetadataProvider;", "Ljava/util/Map;", "Ljava/util/Set;", "Landroid/util/ArrayMap;", "values", "Landroid/util/ArrayMap;", "extensionCache", "Lkotlin/Lazy;", "_supportedExtensions", "Lkotlin/Lazy;", "_keys", "Landroid/hardware/camera2/CaptureRequest$Key;", "_requestKeys", "Landroid/hardware/camera2/CaptureResult$Key;", "_resultKeys", "_physicalCameraIds", "_physicalRequestKeys", "_sessionCharacteristicsKeys", "_sessionKeys", "getSessionKeys", "()Ljava/util/Set;", "sessionKeys", "getPhysicalCameraIds", "physicalCameraIds", "getSupportedExtensions", "supportedExtensions", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CameraMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraMetadata.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraMetadata\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,297:1\n1#2:298\n48#3,2:299\n71#3,4:301\n50#3,3:305\n78#3,4:308\n48#3,2:314\n71#3,4:316\n50#3,3:320\n78#3,4:323\n48#3,2:329\n71#3,4:331\n50#3,3:335\n78#3,4:338\n48#3,2:344\n71#3,4:346\n50#3,3:350\n78#3,4:353\n48#3,2:359\n71#3,4:361\n50#3:365\n52#3:372\n78#3,4:373\n48#3,2:381\n71#3,4:383\n50#3,3:387\n78#3,4:390\n48#3,2:396\n71#3,4:398\n50#3,3:402\n78#3,4:405\n48#3,2:411\n71#3,4:413\n50#3,3:417\n78#3,4:420\n75#4,2:312\n75#4,2:327\n75#4,2:342\n75#4,2:357\n59#4,2:366\n75#4,2:377\n75#4,2:379\n75#4,2:394\n75#4,2:409\n75#4,2:424\n1563#5:368\n1634#5,3:369\n*S KotlinDebug\n*F\n+ 1 Camera2CameraMetadata.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraMetadata\n*L\n160#1:299,2\n160#1:301,4\n160#1:305,3\n160#1:308,4\n172#1:314,2\n172#1:316,4\n172#1:320,3\n172#1:323,4\n185#1:329,2\n185#1:331,4\n185#1:335,3\n185#1:338,4\n198#1:344,2\n198#1:346,4\n198#1:350,3\n198#1:353,4\n214#1:359,2\n214#1:361,4\n214#1:365\n214#1:372\n214#1:373,4\n236#1:381,2\n236#1:383,4\n236#1:387,3\n236#1:390,4\n257#1:396,2\n257#1:398,4\n257#1:402,3\n257#1:405,4\n277#1:411,2\n277#1:413,4\n277#1:417,3\n277#1:420,4\n164#1:312,2\n177#1:327,2\n190#1:342,2\n203#1:357,2\n216#1:366,2\n221#1:377,2\n224#1:379,2\n242#1:394,2\n263#1:409,2\n281#1:424,2\n218#1:368\n218#1:369,3\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraMetadata implements CameraMetadata {
    private final Lazy<Set<CameraCharacteristics.Key<?>>> _keys;
    private final Lazy<Set<CameraId>> _physicalCameraIds;
    private final Lazy<Set<CaptureRequest.Key<?>>> _physicalRequestKeys;
    private final Lazy<Set<CaptureRequest.Key<?>>> _requestKeys;
    private final Lazy<Set<CaptureResult.Key<?>>> _resultKeys;
    private final Lazy<Set<CameraCharacteristics.Key<?>>> _sessionCharacteristicsKeys;
    private final Lazy<Set<CaptureRequest.Key<?>>> _sessionKeys;
    private final Lazy<Set<Integer>> _supportedExtensions;
    private final Set<CameraCharacteristics.Key<?>> cacheBlocklist;
    private final String camera;
    private final CameraCharacteristics characteristics;
    private final ArrayMap<Integer, CameraExtensionMetadata> extensionCache;
    private final boolean isRedacted;
    private final Map<Metadata.Key<?>, Object> metadata;
    private final Camera2MetadataProvider metadataProvider;
    private final ArrayMap<CameraCharacteristics.Key<?>, Object> values;

    public static Set $r8$lambda$6jK87doF68L06RJW28qItaCN2Cc(Camera2CameraMetadata r3) {
        Debug r1 = Debug.INSTANCE;     // Catch: AssertionError -> L7
        Trace.beginSection("Camera-" + CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()) + "#supportedExtensions");     // Catch: Throwable -> L9
        Set<Integer> r0 = access$getMetadataProvider$p(r3).mo458getSupportedCameraExtensionsEfqyGwQ(r3.mo237getCameraDz_R5H8());     // Catch: Throwable -> L9
        Trace.endSection();     // Catch: AssertionError -> L7
        return r0;
    L9:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L7
        throw th;     // Catch: AssertionError -> L7
    L7:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L16;
        android.util.Log.w("CXCP", "Failed to getSupportedExtensions from Camera-" + CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()), e);
    L16:
        return SetsKt.emptySet();
    }

    public static Set $r8$lambda$7QGiosAsnjzt4Vh5sZvU8ramzyE(Camera2CameraMetadata r3) {
        if (Build.VERSION.SDK_INT < 28) goto L5;
        Debug r1 = Debug.INSTANCE;     // Catch: AssertionError -> L14
        Trace.beginSection("Camera-" + r3.mo237getCameraDz_R5H8() + "#availablePhysicalCameraRequestKeys");     // Catch: Throwable -> L10
        List<CaptureRequest.Key<?>> r0 = Api28Compat.getAvailablePhysicalCameraRequestKeys(access$getCharacteristics$p(r3));     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = CollectionsKt.emptyList();     // Catch: Throwable -> L10
    L12:
        Set r02 = CollectionsKt.toSet(r0);     // Catch: Throwable -> L10
        Trace.endSection();     // Catch: AssertionError -> L14
        return r02;
    L10:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L14
        throw th;     // Catch: AssertionError -> L14
    L14:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L22;
        android.util.Log.w("CXCP", "Failed to getAvailablePhysicalCameraRequestKeys from Camera-" + r3.mo237getCameraDz_R5H8(), e);
    L22:
        return SetsKt.emptySet();
    L5:
        return SetsKt.emptySet();
    }

    public static Set $r8$lambda$BODuUBBZ02eZsHvr6gg9PgV4V2A(Camera2CameraMetadata r3) {
        Debug r0 = Debug.INSTANCE;     // Catch: AssertionError -> L11
        Trace.beginSection(CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()) + "#availableCaptureResultKeys");     // Catch: Throwable -> L6
        List<CaptureResult.Key<?>> r02 = access$getCharacteristics$p(r3).getAvailableCaptureResultKeys();     // Catch: Throwable -> L6
        if (r02 != null) goto L8;
        r02 = CollectionsKt.emptyList();     // Catch: Throwable -> L6
    L8:
        Set r03 = CollectionsKt.toSet(r02);     // Catch: Throwable -> L6
        Trace.endSection();     // Catch: AssertionError -> L11
        return r03;
    L6:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L11
        throw th;     // Catch: AssertionError -> L11
    L11:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L19;
        android.util.Log.w("CXCP", "Failed to getAvailableCaptureResultKeys from " + CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()), e);
    L19:
        return SetsKt.emptySet();
    }

    public static Set $r8$lambda$bVgw3guBRT4qUjSeoBqmXlH3OyE(Camera2CameraMetadata r3) {
        Debug r0 = Debug.INSTANCE;     // Catch: AssertionError -> L11
        Trace.beginSection(CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()) + "#availableCaptureRequestKeys");     // Catch: Throwable -> L6
        List<CaptureRequest.Key<?>> r02 = access$getCharacteristics$p(r3).getAvailableCaptureRequestKeys();     // Catch: Throwable -> L6
        if (r02 != null) goto L8;
        r02 = CollectionsKt.emptyList();     // Catch: Throwable -> L6
    L8:
        Set r03 = CollectionsKt.toSet(r02);     // Catch: Throwable -> L6
        Trace.endSection();     // Catch: AssertionError -> L11
        return r03;
    L6:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L11
        throw th;     // Catch: AssertionError -> L11
    L11:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L19;
        android.util.Log.w("CXCP", "Failed to getAvailableCaptureRequestKeys from " + CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()), e);
    L19:
        return SetsKt.emptySet();
    }

    /* renamed from: $r8$lambda$fbQ7MpcKFG8fJ8N6r-2fzmopaIg, reason: not valid java name */
    public static Set m432$r8$lambda$fbQ7MpcKFG8fJ8N6r2fzmopaIg(Camera2CameraMetadata r5) {
        if (Build.VERSION.SDK_INT < 28) goto L5;
        Debug r3 = Debug.INSTANCE;     // Catch: NullPointerException -> L20 AssertionError -> L22
        Trace.beginSection(CameraId.m232toStringimpl(r5.mo237getCameraDz_R5H8()) + "#physicalCameraIds");     // Catch: Throwable -> L10
        Set<String> r32 = Api28Compat.getPhysicalCameraIds(access$getCharacteristics$p(r5));     // Catch: Throwable -> L10
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L12;
        android.util.Log.i("CXCP", "Loaded physicalCameraIds from " + CameraId.m232toStringimpl(r5.mo237getCameraDz_R5H8()) + ": " + r32);     // Catch: Throwable -> L10
    L12:
        if (r32 != null) goto L14;
        r32 = SetsKt.emptySet();     // Catch: Throwable -> L10
    L14:
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r32, 10));     // Catch: Throwable -> L10
        Iterator<T> r33 = r32.iterator();     // Catch: Throwable -> L10
    L16:
        if (r33.hasNext() == false) goto L18;
        r2.add(CameraId.m227boximpl(CameraId.m228constructorimpl((String) r33.next())));     // Catch: Throwable -> L10
        goto L16
    L18:
        Set r22 = CollectionsKt.toSet(r2);     // Catch: Throwable -> L10
        Trace.endSection();     // Catch: NullPointerException -> L20 AssertionError -> L22
        return r22;
    L10:
        th = move-exception;
        Trace.endSection();     // Catch: NullPointerException -> L20 AssertionError -> L22
        throw th;     // Catch: NullPointerException -> L20 AssertionError -> L22
    L22:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L34;
        android.util.Log.w("CXCP", "Failed to getPhysicalCameraIds from " + CameraId.m232toStringimpl(r5.mo237getCameraDz_R5H8()), e);
    L34:
        return SetsKt.emptySet();
    L20:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L40;
        android.util.Log.w("CXCP", "Failed to getPhysicalCameraIds from " + CameraId.m232toStringimpl(r5.mo237getCameraDz_R5H8()), e);
    L40:
        return SetsKt.emptySet();
    L5:
        return SetsKt.emptySet();
    }

    /* renamed from: $r8$lambda$kMchj1M_-zhNcriAQkASoDiOYyk, reason: not valid java name */
    public static Set m433$r8$lambda$kMchj1M_zhNcriAQkASoDiOYyk(Camera2CameraMetadata r3) {
        if (Build.VERSION.SDK_INT < 35) goto L5;
        Debug r1 = Debug.INSTANCE;     // Catch: AssertionError -> L14
        Trace.beginSection("Camera-" + r3.mo237getCameraDz_R5H8() + "#getAvailableSessionCharacteristicsKeys");     // Catch: Throwable -> L10
        List<CameraCharacteristics.Key<?>> r0 = Api35Compat.getAvailableSessionCharacteristicsKeys(access$getCharacteristics$p(r3));     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = CollectionsKt.emptyList();     // Catch: Throwable -> L10
    L12:
        Set r02 = CollectionsKt.toSet(r0);     // Catch: Throwable -> L10
        Trace.endSection();     // Catch: AssertionError -> L14
        return r02;
    L10:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L14
        throw th;     // Catch: AssertionError -> L14
    L14:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L22;
        android.util.Log.w("CXCP", "Failed to getAvailableSessionCharacteristicsKeys from Camera-" + r3.mo237getCameraDz_R5H8(), e);
    L22:
        return SetsKt.emptySet();
    L5:
        return SetsKt.emptySet();
    }

    /* renamed from: $r8$lambda$oNGg3oBR-HSEZOlQaEMZFDTnMGA, reason: not valid java name */
    public static Set m434$r8$lambda$oNGg3oBRHSEZOlQaEMZFDTnMGA(Camera2CameraMetadata r3) {
        Debug r0 = Debug.INSTANCE;     // Catch: AssertionError -> L11
        Trace.beginSection(CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()) + "#keys");     // Catch: Throwable -> L6
        List<CameraCharacteristics.Key<?>> r02 = access$getCharacteristics$p(r3).getKeys();     // Catch: Throwable -> L6
        if (r02 != null) goto L8;
        r02 = CollectionsKt.emptyList();     // Catch: Throwable -> L6
    L8:
        Set r03 = CollectionsKt.toSet(r02);     // Catch: Throwable -> L6
        Trace.endSection();     // Catch: AssertionError -> L11
        return r03;
    L6:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L11
        throw th;     // Catch: AssertionError -> L11
    L11:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L19;
        android.util.Log.w("CXCP", "Failed to getKeys from " + CameraId.m232toStringimpl(r3.mo237getCameraDz_R5H8()) + '}', e);
    L19:
        return SetsKt.emptySet();
    }

    /* renamed from: $r8$lambda$zS47a6lsd54WlPjLo8-dMKY4McE, reason: not valid java name */
    public static Set m435$r8$lambda$zS47a6lsd54WlPjLo8dMKY4McE(Camera2CameraMetadata r3) {
        if (Build.VERSION.SDK_INT < 28) goto L5;
        Debug r1 = Debug.INSTANCE;     // Catch: AssertionError -> L14
        Trace.beginSection("Camera-" + r3.mo237getCameraDz_R5H8() + "#availableSessionKeys");     // Catch: Throwable -> L10
        List<CaptureRequest.Key<?>> r0 = Api28Compat.getAvailableSessionKeys(access$getCharacteristics$p(r3));     // Catch: Throwable -> L10
        if (r0 != null) goto L12;
        r0 = CollectionsKt.emptyList();     // Catch: Throwable -> L10
    L12:
        Set r02 = CollectionsKt.toSet(r0);     // Catch: Throwable -> L10
        Trace.endSection();     // Catch: AssertionError -> L14
        return r02;
    L10:
        th = move-exception;
        Trace.endSection();     // Catch: AssertionError -> L14
        throw th;     // Catch: AssertionError -> L14
    L14:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L22;
        android.util.Log.w("CXCP", "Failed to getAvailableSessionKeys from Camera-" + r3.mo237getCameraDz_R5H8(), e);
    L22:
        return SetsKt.emptySet();
    L5:
        return SetsKt.emptySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Camera2CameraMetadata(String r1, boolean r2, CameraCharacteristics r3, Camera2MetadataProvider r4, Map<Metadata.Key<?>, ? extends Object> r5, Set<? extends CameraCharacteristics.Key<?>> r6) {
        this.camera = r1;
        this.isRedacted = r2;
        this.characteristics = r3;
        this.metadataProvider = r4;
        this.metadata = r5;
        this.cacheBlocklist = r6;
        this.values = new ArrayMap();
        this.extensionCache = new ArrayMap();
        LazyThreadSafetyMode r12 = LazyThreadSafetyMode.PUBLICATION;
        this._supportedExtensions = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda0(this));
        this._keys = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda1(this));
        this._requestKeys = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda2(this));
        this._resultKeys = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda3(this));
        this._physicalCameraIds = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda4(this));
        this._physicalRequestKeys = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda5(this));
        this._sessionCharacteristicsKeys = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda6(this));
        this._sessionKeys = LazyKt.lazy(r12, new Camera2CameraMetadata$$ExternalSyntheticLambda7(this));
    }

    public /* synthetic */ Camera2CameraMetadata(String r1, boolean r2, CameraCharacteristics r3, Camera2MetadataProvider r4, Map r5, Set r6, DefaultConstructorMarker r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public static final /* synthetic */ CameraCharacteristics access$getCharacteristics$p(Camera2CameraMetadata r0) {
        return r0.characteristics;
    }

    public static final /* synthetic */ Camera2MetadataProvider access$getMetadataProvider$p(Camera2CameraMetadata r0) {
        return r0.metadataProvider;
    }

    private final <T> T getOrThrow(CameraCharacteristics r1, CameraCharacteristics.Key<T> r2) {
        return (T) r1.get(r2);
    L4:
        LiveData$$ExternalSyntheticBUOutline0.m("Failed to get characteristic for ", r2, ": Framework throw an AssertionError");
        return null;
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    public CameraExtensionMetadata awaitExtensionMetadata(int r4) {
        ArrayMap<Integer, CameraExtensionMetadata> r0 = this.extensionCache;
        monitor-enter(r0);
        CameraExtensionMetadata r1 = this.extensionCache.get(Integer.valueOf(r4));     // Catch: Throwable -> L16
        monitor-exit(r0);
        if (r1 == null) goto L8;
        return r1;
    L8:
        CameraExtensionMetadata r02 = this.metadataProvider.mo455awaitCameraExtensionMetadata0r8Bogc(mo237getCameraDz_R5H8(), r4);
        ArrayMap<Integer, CameraExtensionMetadata> r12 = this.extensionCache;
        monitor-enter(r12);
        this.extensionCache.put(Integer.valueOf(r4), r02);     // Catch: Throwable -> L13
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r12);
        return r02;
    L13:
        th = move-exception;
        throw th;
    L16:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    /* renamed from: awaitPhysicalMetadata-EfqyGwQ */
    public CameraMetadata mo236awaitPhysicalMetadataEfqyGwQ(String r3) {
        if (getPhysicalCameraIds().contains(CameraId.m227boximpl(r3)) == false) goto L7;
        return this.metadataProvider.mo456awaitCameraMetadataEfqyGwQ(r3);
    L7:
        throw new IllegalStateException((CameraId.m232toStringimpl(r3) + " is not a valid physical camera on " + this).toString());
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    public <T> T get(CameraCharacteristics.Key<T> r3) {
        if (this.cacheBlocklist.contains(r3) == true) goto L5;
        ArrayMap<CameraCharacteristics.Key<?>, Object> r0 = this.values;
        monitor-enter(r0);
        T r1 = (T) this.values.get(r3);
        monitor-exit(r0);
        if (r1 != null) goto L22;
        T r02 = (T) getOrThrow(this.characteristics, r3);
        if (r02 == null) goto L21;
        ArrayMap<CameraCharacteristics.Key<?>, Object> r12 = this.values;
        monitor-enter(r12);
        this.values.put(r3, r02);     // Catch: Throwable -> L18
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L18
        monitor-exit(r12);
        return r02;
    L18:
        th = move-exception;
        throw th;
    L21:
        return r02;
    L22:
        return r1;
    L23:
        th = move-exception;
        throw th;
    L5:
        return (T) getOrThrow(this.characteristics, r3);
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    /* renamed from: getCamera-Dz_R5H8 */
    public String mo237getCameraDz_R5H8() {
        return this.camera;
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    public <T> T getOrDefault(CameraCharacteristics.Key<T> r1, T r2) {
        T r0 = (T) get(r1);
        if (r0 != null) goto L5;
        return r2;
    L5:
        return r0;
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    public Set<CameraId> getPhysicalCameraIds() {
        return this._physicalCameraIds.getValue();
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    public Set<CaptureRequest.Key<?>> getSessionKeys() {
        return this._sessionKeys.getValue();
    }

    @Override // androidx.camera.camera2.pipe.CameraMetadata
    public Set<Integer> getSupportedExtensions() {
        return this._supportedExtensions.getValue();
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CameraCharacteristics.class)) == true) goto L5;
        return null;
    L5:
        return (T) this.characteristics;
    }
}
