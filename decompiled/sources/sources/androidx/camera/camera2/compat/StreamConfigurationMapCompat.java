package androidx.camera.camera2.compat;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import androidx.camera.camera2.compat.workaround.OutputSizesCorrector;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.core.Logger;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001+B\u001b\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u000fJ\u001b\u0010\u0012\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0011\u0018\u00010\b¢\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0015\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0011\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\r¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001a\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\r¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082D¢\u0006\u0006\n\u0004\b \u0010!R&\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\b0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R(\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010$R*\u0010'\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\b0\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010$R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*¨\u0006,"}, d2 = {"Landroidx/camera/camera2/compat/StreamConfigurationMapCompat;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/StreamConfigurationMap;", "map", "Landroidx/camera/camera2/compat/workaround/OutputSizesCorrector;", "outputSizesCorrector", "<init>", "(Landroid/hardware/camera2/params/StreamConfigurationMap;Landroidx/camera/camera2/compat/workaround/OutputSizesCorrector;)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getOutputFormats", "()[Ljava/lang/Integer;", "format", "Landroid/util/Size;", "getOutputSizes", "(I)[Landroid/util/Size;", "getHighResolutionOutputSizes", "Landroid/util/Range;", "getHighSpeedVideoFpsRanges", "()[Landroid/util/Range;", "size", "getHighSpeedVideoFpsRangesFor", "(Landroid/util/Size;)[Landroid/util/Range;", "getHighSpeedVideoSizes", "()[Landroid/util/Size;", _UrlKt.FRAGMENT_ENCODE_SET, "getOutputMinFrameDuration", "(ILandroid/util/Size;)J", "toStreamConfigurationMap", "()Landroid/hardware/camera2/params/StreamConfigurationMap;", "Landroidx/camera/camera2/compat/workaround/OutputSizesCorrector;", _UrlKt.FRAGMENT_ENCODE_SET, "tag", "Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "cachedFormatOutputSizes", "Ljava/util/Map;", "cachedFormatHighResolutionOutputSizes", "Ljava/lang/Class;", "cachedClassOutputSizes", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat$StreamConfigurationMapCompatImpl;", "impl", "Landroidx/camera/camera2/compat/StreamConfigurationMapCompat$StreamConfigurationMapCompatImpl;", "StreamConfigurationMapCompatImpl", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamConfigurationMapCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamConfigurationMapCompat.kt\nandroidx/camera/camera2/compat/StreamConfigurationMapCompat\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,231:1\n129#2,4:232\n*S KotlinDebug\n*F\n+ 1 StreamConfigurationMapCompat.kt\nandroidx/camera/camera2/compat/StreamConfigurationMapCompat\n*L\n194#1:232,4\n*E\n"})
/* loaded from: classes3.dex */
public final class StreamConfigurationMapCompat {
    private final Map<Class<?>, Size[]> cachedClassOutputSizes;
    private final Map<Integer, Size[]> cachedFormatHighResolutionOutputSizes;
    private final Map<Integer, Size[]> cachedFormatOutputSizes;
    private StreamConfigurationMapCompatImpl impl;
    private final OutputSizesCorrector outputSizesCorrector;
    private final String tag;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H&¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0003H&¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0003H&¢\u0006\u0004\b\n\u0010\tJ\u001d\u0010\f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0018\u00010\u0002H&¢\u0006\u0004\b\f\u0010\rJ%\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\u0007H&¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0002H&¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H&¢\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0016H&¢\u0006\u0004\b\u0017\u0010\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/compat/StreamConfigurationMapCompat$StreamConfigurationMapCompatImpl;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getOutputFormats", "()[Ljava/lang/Integer;", "format", "Landroid/util/Size;", "getOutputSizes", "(I)[Landroid/util/Size;", "getHighResolutionOutputSizes", "Landroid/util/Range;", "getHighSpeedVideoFpsRanges", "()[Landroid/util/Range;", "size", "getHighSpeedVideoFpsRangesFor", "(Landroid/util/Size;)[Landroid/util/Range;", "getHighSpeedVideoSizes", "()[Landroid/util/Size;", _UrlKt.FRAGMENT_ENCODE_SET, "getOutputMinFrameDuration", "(ILandroid/util/Size;)J", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "unwrap", "()Landroid/hardware/camera2/params/StreamConfigurationMap;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface StreamConfigurationMapCompatImpl {
        Size[] getHighResolutionOutputSizes(int r1);

        Range<Integer>[] getHighSpeedVideoFpsRanges();

        Range<Integer>[] getHighSpeedVideoFpsRangesFor(Size r1);

        Size[] getHighSpeedVideoSizes();

        Integer[] getOutputFormats();

        long getOutputMinFrameDuration(int r1, Size r2);

        Size[] getOutputSizes(int r1);

        StreamConfigurationMap unwrap();
    }

    public StreamConfigurationMapCompat(StreamConfigurationMap r2, OutputSizesCorrector r3) {
        this.outputSizesCorrector = r3;
        this.tag = "StreamConfigurationMapCompat";
        this.cachedFormatOutputSizes = new LinkedHashMap();
        this.cachedFormatHighResolutionOutputSizes = new LinkedHashMap();
        this.cachedClassOutputSizes = new LinkedHashMap();
        if (Build.VERSION.SDK_INT < 34) goto L5;
        StreamConfigurationMapCompatImpl r32 = new StreamConfigurationMapCompatApi34Impl(r2);
    L6:
        this.impl = r32;
        return;
    L5:
        r32 = new StreamConfigurationMapCompatBaseImpl(r2);
        goto L6
    }

    public final Size[] getHighResolutionOutputSizes(int r4) {
        if (this.cachedFormatHighResolutionOutputSizes.containsKey(Integer.valueOf(r4)) == false) goto L9;
        Size[] r3 = this.cachedFormatHighResolutionOutputSizes.get(Integer.valueOf(r4));
        if (r3 != null) goto L7;
        return null;
    L7:
        return (Size[]) r3.clone();
    L9:
        Size[] r0 = this.impl.getHighResolutionOutputSizes(r4);
        if (r0 != null) goto L12;
    L15:
        this.cachedFormatHighResolutionOutputSizes.put(Integer.valueOf(r4), r0);
        if (r0 != null) goto L18;
        return null;
    L18:
        return (Size[]) r0.clone();
    L12:
        if (r0.length == 0) goto L15;
        r0 = this.outputSizesCorrector.applyQuirks(r0, r4);
        goto L15
    }

    public final Range<Integer>[] getHighSpeedVideoFpsRanges() {
        return this.impl.getHighSpeedVideoFpsRanges();
    }

    public final Range<Integer>[] getHighSpeedVideoFpsRangesFor(Size r1) {
        return this.impl.getHighSpeedVideoFpsRangesFor(r1);
    }

    public final Size[] getHighSpeedVideoSizes() {
        return this.impl.getHighSpeedVideoSizes();
    }

    public final Integer[] getOutputFormats() {
        return this.impl.getOutputFormats();
    }

    public final long getOutputMinFrameDuration(int r4, Size r5) {
        return this.impl.getOutputMinFrameDuration(r4, r5);
    L4:
        e = move-exception;
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L12;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unable to get min frame duration for format = " + r4 + " and size = " + r5, e);
        return 0;
    L12:
        return 0;
    }

    public final Size[] getOutputSizes(int r6) {
        Size[] r1 = null;
        if (this.cachedFormatOutputSizes.containsKey(Integer.valueOf(r6)) == false) goto L21;
        Size[] r5 = this.cachedFormatOutputSizes.get(Integer.valueOf(r6));
        if (r5 != null) goto L7;
        return null;
    L7:
        return (Size[]) r5.clone();
    L21:
        r1 = this.impl.getOutputSizes(r6);     // Catch: Throwable -> L11
    L13:
        if (r1 != null) goto L15;
    L19:
        Logger.w(this.tag, "Retrieved output sizes array is null or empty for format " + r6);
        return r1;
    L15:
        if (r1.length == 0) goto L19;
        Size[] r0 = this.outputSizesCorrector.applyQuirks(r1, r6);
        this.cachedFormatOutputSizes.put(Integer.valueOf(r6), r0);
        return (Size[]) r0.clone();
    L11:
        th = move-exception;
        Logger.w(this.tag, "Failed to get output sizes for " + r6, th);
        goto L13
    }

    public final StreamConfigurationMap toStreamConfigurationMap() {
        return this.impl.unwrap();
    }
}
