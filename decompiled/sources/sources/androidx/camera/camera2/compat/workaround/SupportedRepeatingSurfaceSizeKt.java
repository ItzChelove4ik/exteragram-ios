package androidx.camera.camera2.compat.workaround;

import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.RepeatingStreamConstraintForVideoRecordingQuirk;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\u0010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0003j\b\u0012\u0004\u0012\u00020\u0001`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"MINI_PREVIEW_SIZE_HUAWEI_MATE_9", "Landroid/util/Size;", "SIZE_COMPARATOR", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getSupportedRepeatingSurfaceSizes", _UrlKt.FRAGMENT_ENCODE_SET, "([Landroid/util/Size;)[Landroid/util/Size;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSupportedRepeatingSurfaceSize.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SupportedRepeatingSurfaceSize.kt\nandroidx/camera/camera2/compat/workaround/SupportedRepeatingSurfaceSizeKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,38:1\n37#2:39\n36#2,3:40\n*S KotlinDebug\n*F\n+ 1 SupportedRepeatingSurfaceSize.kt\nandroidx/camera/camera2/compat/workaround/SupportedRepeatingSurfaceSizeKt\n*L\n36#1:39\n36#1:40,3\n*E\n"})
/* loaded from: classes3.dex */
public abstract class SupportedRepeatingSurfaceSizeKt {
    private static final Size MINI_PREVIEW_SIZE_HUAWEI_MATE_9 = null;
    private static final Comparator<Size> SIZE_COMPARATOR = null;

    static {
        MINI_PREVIEW_SIZE_HUAWEI_MATE_9 = new Size(320, 240);
        SIZE_COMPARATOR = new CompareSizesByArea();
    }

    public static final Size[] getSupportedRepeatingSurfaceSizes(Size[] r7) {
        if (((RepeatingStreamConstraintForVideoRecordingQuirk) DeviceQuirks.INSTANCE.get(RepeatingStreamConstraintForVideoRecordingQuirk.class)) != null) goto L5;
        return r7;
    L5:
        ArrayList r0 = new ArrayList();
        int r1 = r7.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        Size r4 = r7[r3];
        if (SIZE_COMPARATOR.compare(r4, MINI_PREVIEW_SIZE_HUAWEI_MATE_9) < 0) goto L10;
        r0.add(r4);
    L10:
        r3 = r3 + 1;
        goto L6
    L12:
        return (Size[]) r0.toArray(new Size[0]);
    }
}
