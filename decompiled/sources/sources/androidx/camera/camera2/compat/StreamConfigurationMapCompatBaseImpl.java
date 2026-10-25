package androidx.camera.camera2.compat;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Size;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\b\u0010\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\rJ\u001d\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0013\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001b¨\u0006\u001e"}, d2 = {"Landroidx/camera/camera2/compat/StreamConfigurationMapCompatBaseImpl;", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat$StreamConfigurationMapCompatImpl;", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "streamConfigurationMap", "<init>", "(Landroid/hardware/camera2/params/StreamConfigurationMap;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getOutputFormats", "()[Ljava/lang/Integer;", "format", "Landroid/util/Size;", "getOutputSizes", "(I)[Landroid/util/Size;", "getHighResolutionOutputSizes", "Landroid/util/Range;", "getHighSpeedVideoFpsRanges", "()[Landroid/util/Range;", "size", "getHighSpeedVideoFpsRangesFor", "(Landroid/util/Size;)[Landroid/util/Range;", "getHighSpeedVideoSizes", "()[Landroid/util/Size;", _UrlKt.FRAGMENT_ENCODE_SET, "getOutputMinFrameDuration", "(ILandroid/util/Size;)J", "unwrap", "()Landroid/hardware/camera2/params/StreamConfigurationMap;", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "getStreamConfigurationMap", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class StreamConfigurationMapCompatBaseImpl implements StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl {
    private final StreamConfigurationMap streamConfigurationMap;

    public StreamConfigurationMapCompatBaseImpl(StreamConfigurationMap r1) {
        this.streamConfigurationMap = r1;
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Size[] getHighResolutionOutputSizes(int r1) {
        StreamConfigurationMap r0 = this.streamConfigurationMap;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getHighResolutionOutputSizes(r1);
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Range<Integer>[] getHighSpeedVideoFpsRanges() {
        StreamConfigurationMap r0 = this.streamConfigurationMap;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getHighSpeedVideoFpsRanges();
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Range<Integer>[] getHighSpeedVideoFpsRangesFor(Size r1) {
        StreamConfigurationMap r0 = this.streamConfigurationMap;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getHighSpeedVideoFpsRangesFor(r1);
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Size[] getHighSpeedVideoSizes() {
        StreamConfigurationMap r0 = this.streamConfigurationMap;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getHighSpeedVideoSizes();
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Integer[] getOutputFormats() {
        StreamConfigurationMap r3 = this.streamConfigurationMap;     // Catch: IllegalArgumentException -> L7 NullPointerException -> L9
        if (r3 == null) goto L12;
        int[] r32 = r3.getOutputFormats();     // Catch: IllegalArgumentException -> L7 NullPointerException -> L9
    L14:
        if (r32 != null) goto L16;
        return null;
    L16:
        return ArraysKt.toTypedArray(r32);
    L12:
        r32 = null;
    L7:
        e = move-exception;
        Logger.w("StreamConfigurationMapCompatBaseImpl", "Failed to get output formats from StreamConfigurationMap", e);
    L9:
        e = move-exception;
        Logger.w("StreamConfigurationMapCompatBaseImpl", "Failed to get output formats from StreamConfigurationMap", e);
        goto L12
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public long getOutputMinFrameDuration(int r1, Size r2) {
        StreamConfigurationMap r0 = this.streamConfigurationMap;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getOutputMinFrameDuration(r1, r2);
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public Size[] getOutputSizes(int r1) {
        StreamConfigurationMap r0 = this.streamConfigurationMap;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getOutputSizes(r1);
    }

    @Override // androidx.camera.camera2.compat.StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl
    public StreamConfigurationMap unwrap() {
        return this.streamConfigurationMap;
    }
}
