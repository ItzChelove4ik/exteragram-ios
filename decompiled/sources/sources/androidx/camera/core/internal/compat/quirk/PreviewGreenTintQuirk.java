package androidx.camera.core.internal.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\u0005H\u0007J\u001e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J\u001e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u000f"}, d2 = {"Landroidx/camera/core/internal/compat/quirk/PreviewGreenTintQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "isMotoE20", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "load", "shouldForceEnableStreamSharing", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "appUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "shouldForceEnableStreamSharingForMotoE20", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
@SourceDebugExtension({"SMAP\nPreviewGreenTintQuirk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreviewGreenTintQuirk.kt\nandroidx/camera/core/internal/compat/quirk/PreviewGreenTintQuirk\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,70:1\n1761#2,3:71\n1761#2,3:74\n*S KotlinDebug\n*F\n+ 1 PreviewGreenTintQuirk.kt\nandroidx/camera/core/internal/compat/quirk/PreviewGreenTintQuirk\n*L\n61#1:71,3\n63#1:74,3\n*E\n"})
/* loaded from: classes3.dex */
public final class PreviewGreenTintQuirk implements Quirk {
    public static final PreviewGreenTintQuirk INSTANCE = null;

    static {
        INSTANCE = new PreviewGreenTintQuirk();
    }

    private PreviewGreenTintQuirk() {
    }

    private final boolean isMotoE20() {
        if (StringsKt.equals("motorola", Build.BRAND, true) == true) goto L5;
        return false;
    L5:
        if (StringsKt.equals("moto e20", Build.MODEL, true) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @JvmStatic
    public static final boolean load() {
        return INSTANCE.isMotoE20();
    }

    @JvmStatic
    public static final boolean shouldForceEnableStreamSharing(String r2, Collection<? extends UseCase> r3) {
        PreviewGreenTintQuirk r0 = INSTANCE;
        if (r0.isMotoE20() == true) goto L5;
        return false;
    L5:
        return r0.shouldForceEnableStreamSharingForMotoE20(r2, r3);
    }

    private final boolean shouldForceEnableStreamSharingForMotoE20(String r5, Collection<? extends UseCase> r6) {
        if (Intrinsics.areEqual(r5, MVEL.VERSION_SUB) == true) goto L5;
    L33:
        return false;
    L5:
        if (r6.size() != 2) goto L33;
        Collection<? extends UseCase> r62 = r6;
        boolean r4 = r62 instanceof Collection;
        if (r4 == true) goto L10;
    L12:
        Iterator<T> r1 = r62.iterator();
    L14:
        if (r1.hasNext() == false) goto L11;
        if ((((UseCase) r1.next()) instanceof Preview) == false) goto L14;
        boolean r12 = true;
    L18:
        if (r4 == true) goto L20;
    L22:
        Iterator<T> r42 = r62.iterator();
    L24:
        if (r42.hasNext() == false) goto L21;
        UseCase r63 = (UseCase) r42.next();
        if (r63.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) == false) goto L24;
        if (r63.getCurrentConfig().getCaptureType() != UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE) goto L24;
        boolean r43 = true;
    L30:
        if (r12 == false) goto L33;
        if (r43 == false) goto L33;
        return true;
    L21:
        r43 = false;
        goto L30
    L20:
        if (r62.isEmpty() == false) goto L22;
    L11:
        r12 = false;
        goto L18
    L10:
        if (r62.isEmpty() == false) goto L12;
        goto L11
    }
}
