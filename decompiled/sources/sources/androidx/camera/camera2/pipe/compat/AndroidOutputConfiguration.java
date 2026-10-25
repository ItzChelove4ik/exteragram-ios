package androidx.camera.camera2.pipe.compat;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.params.OutputConfiguration;
import android.media.MediaCodec;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.camera.camera2.compat.DynamicRangeProfilesCompat$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.core.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticBUOutline0;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0001\u0018\u0000 &2\u00020\u0001:\u0001&B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J)\u0010\u0015\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0012*\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001c\u0010\t\u001a\u0004\u0018\u00010\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010\u0019R\u001c\u0010\r\u001a\u0004\u0018\u00010\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010#\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidOutputConfiguration;", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "Landroid/hardware/camera2/params/OutputConfiguration;", "output", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceSharing", _UrlKt.FRAGMENT_ENCODE_SET, "maxSharedSurfaceCount", "Landroidx/camera/camera2/pipe/CameraId;", "physicalCameraId", "<init>", "(Landroid/hardware/camera2/params/OutputConfiguration;ZILjava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroid/view/Surface;", "surface", _UrlKt.FRAGMENT_ENCODE_SET, "addSurface", "(Landroid/view/Surface;)V", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroid/hardware/camera2/params/OutputConfiguration;", "Z", "getSurfaceSharing", "()Z", "I", "getMaxSharedSurfaceCount", "()I", "Ljava/lang/String;", "getPhysicalCameraId-1LO98Z0", "Landroid/view/Surface;", "getSurface", "()Landroid/view/Surface;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Configuration.kt\nandroidx/camera/camera2/pipe/compat/AndroidOutputConfiguration\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/DebugKt\n*L\n1#1,373:1\n272#2:374\n253#2,4:375\n276#2:379\n253#2,4:380\n*S KotlinDebug\n*F\n+ 1 Configuration.kt\nandroidx/camera/camera2/pipe/compat/AndroidOutputConfiguration\n*L\n348#1:374\n348#1:375,4\n355#1:379\n355#1:380,4\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidOutputConfiguration implements OutputConfigurationWrapper {
    public static final Companion Companion = null;
    private final int maxSharedSurfaceCount;
    private final OutputConfiguration output;
    private final String physicalCameraId;
    private final Surface surface;
    private final boolean surfaceSharing;

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0005*\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\u00020\t*\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0010\u001a\u00020\t*\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u009b\u0001\u0010(\u001a\u0004\u0018\u00010%2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\u001e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u00132\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b&\u0010'¨\u0006)"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidOutputConfiguration$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "Ljava/lang/Class;", "toKlass", "(Landroidx/camera/camera2/pipe/OutputStream$OutputType;)Ljava/lang/Class;", "Landroid/hardware/camera2/params/OutputConfiguration;", _UrlKt.FRAGMENT_ENCODE_SET, "enableSurfaceSharingCompat", "(Landroid/hardware/camera2/params/OutputConfiguration;)V", "Landroidx/camera/camera2/pipe/CameraId;", "physicalCameraId", "setPhysicalCameraIdCompat-8Ri2elo", "(Landroid/hardware/camera2/params/OutputConfiguration;Ljava/lang/String;)V", "setPhysicalCameraIdCompat", "Landroid/view/Surface;", "surface", _UrlKt.FRAGMENT_ENCODE_SET, "format", "outputType", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", _UrlKt.FRAGMENT_ENCODE_SET, "sensorPixelModes", "Landroid/util/Size;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "surfaceSharing", "surfaceGroupId", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "create-gWWoySg", "(Landroid/view/Surface;Ljava/lang/Integer;Landroidx/camera/camera2/pipe/OutputStream$OutputType;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Ljava/util/List;Landroid/util/Size;ZILjava/lang/String;)Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "create", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Configuration.kt\nandroidx/camera/camera2/pipe/compat/AndroidOutputConfiguration$Companion\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/DebugKt\n*L\n1#1,373:1\n75#2,2:374\n71#2,2:376\n59#2,2:378\n71#2,2:380\n71#2,2:382\n268#3:384\n253#3,4:385\n276#3:389\n253#3,4:390\n*S KotlinDebug\n*F\n+ 1 Configuration.kt\nandroidx/camera/camera2/pipe/compat/AndroidOutputConfiguration$Companion\n*L\n196#1:374,2\n229#1:376,2\n243#1:378,2\n258#1:380,2\n279#1:382,2\n320#1:384\n320#1:385,4\n327#1:389\n327#1:390,4\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: create-gWWoySg$default, reason: not valid java name */
        public static /* synthetic */ OutputConfigurationWrapper m421creategWWoySg$default(Companion r1, Surface r2, Integer r3, OutputStream.OutputType r4, OutputStream.MirrorMode r5, OutputStream.TimestampBase r6, OutputStream.DynamicRangeProfile r7, OutputStream.StreamUseCase r8, List r9, Size r10, boolean r11, int r12, String r13, int r14, Object r15) {
            if ((r14 & 2) == 0) goto L6;
            r3 = null;
        L6:
            if ((r14 & 4) == 0) goto L9;
            r4 = OutputStream.OutputType.Companion.getSURFACE();
        L9:
            if ((r14 & 8) == 0) goto L12;
            r5 = null;
        L12:
            if ((r14 & 16) == 0) goto L15;
            r6 = null;
        L15:
            if ((r14 & 32) == 0) goto L18;
            r7 = null;
        L18:
            if ((r14 & 64) == 0) goto L21;
            r8 = null;
        L21:
            if ((r14 & 128) == 0) goto L24;
            r9 = CollectionsKt.emptyList();
        L24:
            if ((r14 & 256) == 0) goto L27;
            r10 = null;
        L27:
            if ((r14 & 512) == 0) goto L30;
            r11 = false;
        L30:
            if ((r14 & 1024) == 0) goto L33;
            r12 = -1;
        L33:
            if ((r14 & 2048) == 0) goto L36;
            r13 = null;
        L36:
            return r1.m423creategWWoySg(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        }

        private final void enableSurfaceSharingCompat(OutputConfiguration r2) {
            if (Build.VERSION.SDK_INT < 26) goto L6;
            Api26Compat.enableSurfaceSharing(r2);
            return;
        }

        /* renamed from: setPhysicalCameraIdCompat-8Ri2elo, reason: not valid java name */
        private final void m422setPhysicalCameraIdCompat8Ri2elo(OutputConfiguration r2, String r3) {
            int r1 = Build.VERSION.SDK_INT;
            if (r1 < 28) goto L7;
            if (r1 < 28) goto L9;
            Api28Compat.setPhysicalCameraId(r2, r3);
            return;
        L9:
            return;
        L7:
            DynamicRangeProfilesCompat$$ExternalSyntheticBUOutline0.m("physicalCameraId is not supported on API ", r1, " (requires API 28)");
        }

        private final Class<? extends Object> toKlass(OutputStream.OutputType r3) {
            OutputStream.OutputType.Companion r2 = OutputStream.OutputType.Companion;
            if (Intrinsics.areEqual(r3, r2.getSURFACE_TEXTURE()) == false) goto L7;
            return SurfaceTexture.class;
        L7:
            if (Intrinsics.areEqual(r3, r2.getSURFACE_VIEW()) == false) goto L11;
            return SurfaceHolder.class;
        L11:
            if (Intrinsics.areEqual(r3, r2.getMEDIA_CODEC()) == false) goto L20;
            if (Build.VERSION.SDK_INT < 35) goto L16;
            return MediaCodec.class;
        L16:
            Segment$$ExternalSyntheticBUOutline1.m("OutputType.MEDIA_CODEC requires API 35 or higher.");
            return null;
        L20:
            if (Intrinsics.areEqual(r3, r2.getMEDIA_RECORDER()) == true) goto L22;
            DexMaker$$ExternalSyntheticBUOutline0.m("Unsupported OutputType: ", r3);
            return null;
        L22:
            if (Build.VERSION.SDK_INT < 35) goto L25;
            return MediaRecorder.class;
        L25:
            Segment$$ExternalSyntheticBUOutline1.m("OutputType.MEDIA_RECORDER requires API 35 or higher.");
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: create-gWWoySg, reason: not valid java name */
        public final OutputConfigurationWrapper m423creategWWoySg(Surface r12, Integer r13, OutputStream.OutputType r14, OutputStream.MirrorMode r15, OutputStream.TimestampBase r16, OutputStream.DynamicRangeProfile r17, OutputStream.StreamUseCase r18, List<Object> r19, Size r20, boolean r21, int r22, String r23) {
            OutputStream.OutputType.Companion r5 = OutputStream.OutputType.Companion;
            if (Intrinsics.areEqual(r14, r5.getSURFACE_DEFERRED_FOR_QUERY_ONLY$camera_camera2_pipe()) == false) goto L15;
            if (Build.VERSION.SDK_INT < 35) goto L15;
            if (r13 == null) goto L12;
            if (r20 == null) goto L10;
            OutputConfiguration r122 = Api35Compat.newImageReaderOutputConfiguration(r13.intValue(), r20);
        L35:
            if (r21 == false) goto L37;
            enableSurfaceSharingCompat(r122);
        L37:
            if (r23 == null) goto L40;
            m422setPhysicalCameraIdCompat8Ri2elo(r122, r23);
        L40:
            if (r15 == null) goto L49;
            int r132 = Build.VERSION.SDK_INT;
            if (r132 < 33) goto L45;
            Api33Compat.setMirrorMode(r122, r15.m339unboximpl());
            goto L49
        L45:
            if (OutputStream.MirrorMode.m336equalsimpl0(r15.m339unboximpl(), OutputStream.MirrorMode.Companion.m340getMIRROR_MODE_AUTODrUKqn0()) == true) goto L49;
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L49;
            android.util.Log.w("CXCP", "Cannot set mirrorMode to a non-default value on API " + r132 + ". This may result in unexpected behavior. Requested " + OutputStream.MirrorMode.m338toStringimpl(r15.m339unboximpl()));
        L49:
            if (r17 == null) goto L58;
            int r133 = Build.VERSION.SDK_INT;
            if (r133 < 33) goto L54;
            Api33Compat.setDynamicRangeProfile(r122, r17.m331unboximpl());
            goto L58
        L54:
            if (OutputStream.DynamicRangeProfile.m328equalsimpl0(r17.m331unboximpl(), OutputStream.DynamicRangeProfile.Companion.m332getSTANDARDfFAQAUE()) == true) goto L58;
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L58;
            android.util.Log.w("CXCP", "Cannot set dynamicRangeProfile to a non-default value on API " + r133 + ". This may result in unexpected behavior. Requested " + OutputStream.DynamicRangeProfile.m330toStringimpl(r17.m331unboximpl()));
        L58:
            if (r18 == null) goto L63;
            if (Build.VERSION.SDK_INT < 33) goto L63;
            Api33Compat.setStreamUseCase(r122, r18.m347unboximpl());
        L63:
            if (r19.isEmpty() == true) goto L75;
            int r134 = Build.VERSION.SDK_INT;
            if (r134 < 31) goto L72;
            Iterator<Object> r11 = r19.iterator();
            if (r11.hasNext() == false) goto L75;
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r11.next());
            throw null;
        L72:
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L75;
            android.util.Log.w("CXCP", "Cannot add sensorPixelModeUsed value on API " + r134 + ". This may result in unexpected behavior. Requested " + r19);
        L75:
            if (Build.VERSION.SDK_INT < 28) goto L77;
            int r135 = Api28Compat.getMaxSharedSurfaceCount(r122);
        L79:
            return new AndroidOutputConfiguration(r122, r21, r135, r23, null);
        L77:
            r135 = 1;
            goto L79
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
            return null;
        L15:
            if (Intrinsics.areEqual(r14, r5.getSURFACE()) == false) goto L31;
            if (r12 != 0) goto L84;
            Objects.toString(r5.getSURFACE());
            Segment$$ExternalSyntheticBUOutline1.m("non-null surface!");
            return null;
        L84:
            if (r22 == (-1)) goto L23;
            OutputConfiguration r136 = new OutputConfiguration(r22, r12);     // Catch: Throwable -> L21
        L20:
            r12 = r136;
            r122 = r12;
            goto L35
        L23:
            r136 = new OutputConfiguration(r12);     // Catch: Throwable -> L21
        L21:
            th = move-exception;
            if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L28;
            android.util.Log.w("CXCP", "Failed to create an OutputConfiguration for " + r12 + '!', th);
        L28:
            return null;
        L31:
            int r123 = Build.VERSION.SDK_INT;
            if (r123 < 26) goto L82;
            if (r20 == null) goto L80;
            r122 = Api26Compat.newOutputConfiguration(r20, toKlass(r14));
            goto L35
        L80:
            Segment$$ExternalSyntheticBUOutline1.m("Size must defined when creating a deferred OutputConfiguration.");
            return null;
        L82:
            ExifInterface$$ExternalSyntheticBUOutline0.m("Deferred OutputConfigurations are not supported on API ", r123, " (requires API 26)");
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    private AndroidOutputConfiguration(OutputConfiguration r1, boolean r2, int r3, String r4) {
        this.output = r1;
        this.surfaceSharing = r2;
        this.maxSharedSurfaceCount = r3;
        this.physicalCameraId = r4;
        this.surface = r1.getSurface();
    }

    public /* synthetic */ AndroidOutputConfiguration(OutputConfiguration r1, boolean r2, int r3, String r4, DefaultConstructorMarker r5) {
        this(r1, r2, r3, r4);
    }

    @Override // androidx.camera.camera2.pipe.compat.OutputConfigurationWrapper
    public void addSurface(Surface r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 26) goto L7;
        if (r0 < 26) goto L9;
        Api26Compat.addSurfaces(this.output, r3);
        return;
    L9:
        return;
    L7:
        DynamicRangeProfilesCompat$$ExternalSyntheticBUOutline0.m("addSurface is not supported on API ", r0, " (requires API 26)");
    }

    public String toString() {
        return this.output.toString();
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(OutputConfiguration.class)) == true) goto L5;
        return null;
    L5:
        return (T) this.output;
    }
}
