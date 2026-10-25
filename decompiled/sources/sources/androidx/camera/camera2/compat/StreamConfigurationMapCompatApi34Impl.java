package androidx.camera.camera2.compat;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.PixelJpegRSupportedQuirk;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Landroidx/camera/camera2/compat/StreamConfigurationMapCompatApi34Impl;", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompatBaseImpl;", "map", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "<init>", "(Landroid/hardware/camera2/params/StreamConfigurationMap;)V", "hasJpegRQuirk", _UrlKt.FRAGMENT_ENCODE_SET, "getHasJpegRQuirk", "()Z", "getOutputFormats", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "()[Ljava/lang/Integer;", "getOutputSizes", "Landroid/util/Size;", "format", "(I)[Landroid/util/Size;", "getHighResolutionOutputSizes", "getOutputMinFrameDuration", _UrlKt.FRAGMENT_ENCODE_SET, "size", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamConfigurationMapCompatApi34Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamConfigurationMapCompatApi34Impl.kt\nandroidx/camera/camera2/compat/StreamConfigurationMapCompatApi34Impl\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,69:1\n3829#2:70\n4344#2,2:71\n37#3:73\n36#3,3:74\n*S KotlinDebug\n*F\n+ 1 StreamConfigurationMapCompatApi34Impl.kt\nandroidx/camera/camera2/compat/StreamConfigurationMapCompatApi34Impl\n*L\n38#1:70\n38#1:71,2\n38#1:73\n38#1:74,3\n*E\n"})
/* loaded from: classes3.dex */
public final class StreamConfigurationMapCompatApi34Impl extends StreamConfigurationMapCompatBaseImpl {
    public StreamConfigurationMapCompatApi34Impl(StreamConfigurationMap r1) {
        super(r1);
    }

    private final boolean getHasJpegRQuirk() {
        if (DeviceQuirks.INSTANCE.get(PixelJpegRSupportedQuirk.class) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompatBaseImpl, androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Size[] getHighResolutionOutputSizes(int r2) {
        if (r2 != 4101) goto L9;
        if (getHasJpegRQuirk() == false) goto L9;
        return null;
    L9:
        return super.getHighResolutionOutputSizes(r2);
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompatBaseImpl, androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Integer[] getOutputFormats() {
        Integer[] r0 = super.getOutputFormats();
        if (getHasJpegRQuirk() == false) goto L15;
        if (r0 == null) goto L13;
        ArrayList r7 = new ArrayList();
        int r1 = r0.length;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L12;
        Integer r4 = r0[r3];
        if (r4.intValue() == 4101) goto L10;
        r7.add(r4);
    L10:
        r3 = r3 + 1;
        goto L6
    L12:
        return (Integer[]) r7.toArray(new Integer[0]);
    L13:
        return null;
    L15:
        return r0;
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompatBaseImpl, androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public long getOutputMinFrameDuration(int r2, Size r3) {
        if (r2 != 4101) goto L9;
        if (getHasJpegRQuirk() == false) goto L9;
        return 0;
    L9:
        return super.getOutputMinFrameDuration(r2, r3);
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompatBaseImpl, androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Size[] getOutputSizes(int r2) {
        if (r2 != 4101) goto L9;
        if (getHasJpegRQuirk() == false) goto L9;
        return null;
    L9:
        return super.getOutputSizes(r2);
    }
}
