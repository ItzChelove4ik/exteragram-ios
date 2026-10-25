package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CloseCameraDeviceOnCameraGraphCloseQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "shouldCloseCameraDevice", _UrlKt.FRAGMENT_ENCODE_SET, "isExtensions", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
@SourceDebugExtension({"SMAP\nCloseCameraDeviceOnCameraGraphCloseQuirk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CloseCameraDeviceOnCameraGraphCloseQuirk.kt\nandroidx/camera/camera2/compat/quirk/CloseCameraDeviceOnCameraGraphCloseQuirk\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,113:1\n1761#2,3:114\n*S KotlinDebug\n*F\n+ 1 CloseCameraDeviceOnCameraGraphCloseQuirk.kt\nandroidx/camera/camera2/compat/quirk/CloseCameraDeviceOnCameraGraphCloseQuirk\n*L\n104#1:114,3\n*E\n"})
/* loaded from: classes3.dex */
public final class CloseCameraDeviceOnCameraGraphCloseQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final boolean isSamsungExynos7570Device = false;
    private static final boolean isSamsungExynos7870Device = false;
    private static final boolean isSamsungProblematicDevice = false;
    private static final boolean isSonyProblematicDevice = false;
    private static final boolean isXiaomiProblematicDevice = false;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/compat/quirk/CloseCameraDeviceOnCameraGraphCloseQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isSamsungExynos7570Device", "isSamsungExynos7870Device", "isXiaomiProblematicDevice", "isSonyProblematicDevice", "isSamsungProblematicDevice", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final boolean isEnabled() {
            if (CloseCameraDeviceOnCameraGraphCloseQuirk.access$isSamsungExynos7570Device$cp() == false) goto L5;
        L32:
            return true;
        L5:
            if (CloseCameraDeviceOnCameraGraphCloseQuirk.access$isSamsungExynos7870Device$cp() == true) goto L32;
            int r2 = Build.VERSION.SDK_INT;
            if (30 > r2) goto L19;
            if (r2 >= 34) goto L19;
            Device r22 = Device.INSTANCE;
            if (r22.isOppoDevice() == false) goto L14;
        L17:
            return true;
        L14:
            if (r22.isOnePlusDevice() == true) goto L17;
            if (r22.isRealmeDevice() == true) goto L17;
        L19:
            if (Device.INSTANCE.isVivoDevice() == false) goto L22;
            return true;
        L22:
            if (CloseCameraDeviceOnCameraGraphCloseQuirk.access$isXiaomiProblematicDevice$cp() == false) goto L25;
            return true;
        L25:
            if (CloseCameraDeviceOnCameraGraphCloseQuirk.access$isSamsungProblematicDevice$cp() == false) goto L28;
            return true;
        L28:
            if (CloseCameraDeviceOnCameraGraphCloseQuirk.access$isSonyProblematicDevice$cp() == false) goto L30;
            return true;
        L30:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
        String r0 = Build.HARDWARE;
        isSamsungExynos7570Device = Intrinsics.areEqual(r0, "samsungexynos7570");
        isSamsungExynos7870Device = Intrinsics.areEqual(r0, "samsungexynos7870");
        Device r02 = Device.INSTANCE;
        boolean r2 = false;
        if (r02.isXiaomiDevice() == true) goto L5;
    L7:
        boolean r1 = false;
    L8:
        isXiaomiProblematicDevice = r1;
        if (r02.isSonyDevice() == false) goto L21;
        List r03 = CollectionsKt.listOf(new String[]{"XQ-DQ", "SO", "A301SO"});
        if ((r03 instanceof Collection) == true) goto L13;
    L15:
        Iterator r04 = r03.iterator();
    L17:
        if (r04.hasNext() == false) goto L21;
        if (StringsKt.startsWith(Build.DEVICE, (String) r04.next(), true) == false) goto L17;
        boolean r05 = true;
    L22:
        isSonyProblematicDevice = r05;
        if (Device.INSTANCE.isSamsungDevice() == false) goto L29;
        int r06 = Build.VERSION.SDK_INT;
        if (r06 < 31) goto L29;
        if (r06 > 34) goto L29;
        r2 = true;
    L29:
        isSamsungProblematicDevice = r2;
        return;
    L13:
        if (r03.isEmpty() == false) goto L15;
    L21:
        r05 = false;
        goto L22
    L5:
        if (ArraysKt.contains(new String[]{"aurora", "houji"}, Build.DEVICE.toLowerCase(Locale.ROOT)) == false) goto L7;
        r1 = true;
        goto L8
    }

    public CloseCameraDeviceOnCameraGraphCloseQuirk() {
    }

    public static final /* synthetic */ boolean access$isSamsungExynos7570Device$cp() {
        return isSamsungExynos7570Device;
    }

    public static final /* synthetic */ boolean access$isSamsungExynos7870Device$cp() {
        return isSamsungExynos7870Device;
    }

    public static final /* synthetic */ boolean access$isSamsungProblematicDevice$cp() {
        return isSamsungProblematicDevice;
    }

    public static final /* synthetic */ boolean access$isSonyProblematicDevice$cp() {
        return isSonyProblematicDevice;
    }

    public static final /* synthetic */ boolean access$isXiaomiProblematicDevice$cp() {
        return isXiaomiProblematicDevice;
    }

    public final boolean shouldCloseCameraDevice(boolean r1) {
        if (isXiaomiProblematicDevice == false) goto L5;
    L13:
        return r1;
    L5:
        if (isSamsungProblematicDevice == true) goto L7;
        return true;
    L7:
        if (isSamsungExynos7570Device == false) goto L9;
        return true;
    L9:
        if (isSamsungExynos7870Device == false) goto L13;
        return true;
    }
}
