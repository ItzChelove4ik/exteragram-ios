package androidx.camera.camera2.pipe;

import android.os.Build;
import android.util.Size;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaStatus;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@kotlin.Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001:\u00070123456J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\b\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\f\u001a\u00020\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000bR\u0014\u0010\u0017\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001c8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010#\u001a\u0004\u0018\u00010 8&X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0016\u0010'\u001a\u0004\u0018\u00010$8&X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010+\u001a\u0004\u0018\u00010(8&X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0016\u0010/\u001a\u0004\u0018\u00010,8&X¦\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00067À\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isValidForHighSpeedOperatingMode", "()Z", "Landroidx/camera/camera2/pipe/CameraStream;", "getStream", "()Landroidx/camera/camera2/pipe/CameraStream;", "stream", "Landroidx/camera/camera2/pipe/OutputId;", "getId-4LaLFng", "()I", "id", "Landroid/util/Size;", "getSize", "()Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "getFormat-8FPWQzE", "format", "Landroidx/camera/camera2/pipe/CameraId;", "getCamera-Dz_R5H8", "()Ljava/lang/String;", "camera", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "getMirrorMode-dO1_9xk", "()Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "getTimestampBase-pcPfPbY", "()Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "getDynamicRangeProfile-OoVcG5w", "()Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "getStreamUseCase-8x2ez34", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "getOutputType", "()Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "outputType", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "getStreamUseHint-HIPxoCc", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", "Config", "OutputType", "MirrorMode", "TimestampBase", "DynamicRangeProfile", "StreamUseHint", "StreamUseCase", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface OutputStream {

    @kotlin.Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 32\u00020\u0001:\u0003345Bc\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b \u0010\u0018R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010'\u001a\u0004\b(\u0010)R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010*\u001a\u0004\b+\u0010,R\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010-\u001a\u0004\b.\u0010/R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00100\u001a\u0004\b1\u00102\u0082\u0001\u000267¨\u00068"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$Config;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "format", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", _UrlKt.FRAGMENT_ENCODE_SET, "sensorPixelModes", "<init>", "(Landroid/util/Size;ILjava/lang/String;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroid/util/Size;", "getSize", "()Landroid/util/Size;", "I", "getFormat-8FPWQzE", "()I", "Ljava/lang/String;", "getCamera-1LO98Z0", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "getMirrorMode-dO1_9xk", "()Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "getDynamicRangeProfile-OoVcG5w", "()Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "getStreamUseCase-8x2ez34", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "getStreamUseHint-HIPxoCc", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "Ljava/util/List;", "getSensorPixelModes", "()Ljava/util/List;", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "getTimestampBase-pcPfPbY", "()Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "Companion", "SimpleOutputConfig", "LazyOutputConfig", "Landroidx/camera/camera2/pipe/OutputStream$Config$LazyOutputConfig;", "Landroidx/camera/camera2/pipe/OutputStream$Config$SimpleOutputConfig;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Config {
        public static final Companion Companion = null;
        private final String camera;
        private final DynamicRangeProfile dynamicRangeProfile;
        private final int format;
        private final MirrorMode mirrorMode;
        private final List<Object> sensorPixelModes;
        private final Size size;
        private final StreamUseCase streamUseCase;
        private final StreamUseHint streamUseHint;

        @kotlin.Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0005*\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u007f\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$Config$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", _UrlKt.FRAGMENT_ENCODE_SET, "isLazilyConfigurable", "(Landroidx/camera/camera2/pipe/OutputStream$OutputType;)Z", "Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "format", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "outputType", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", _UrlKt.FRAGMENT_ENCODE_SET, "sensorPixelModes", "Landroidx/camera/camera2/pipe/OutputStream$Config;", "create-vBYXiEU", "(Landroid/util/Size;ILjava/lang/String;Landroidx/camera/camera2/pipe/OutputStream$OutputType;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;Ljava/util/List;)Landroidx/camera/camera2/pipe/OutputStream$Config;", "create", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: create-vBYXiEU$default, reason: not valid java name */
            public static /* synthetic */ Config m323createvBYXiEU$default(Companion r1, Size r2, int r3, String r4, OutputType r5, MirrorMode r6, TimestampBase r7, DynamicRangeProfile r8, StreamUseCase r9, StreamUseHint r10, List r11, int r12, Object r13) {
                if ((r12 & 4) == 0) goto L6;
                r4 = null;
            L6:
                if ((r12 & 8) == 0) goto L9;
                r5 = OutputType.Companion.getSURFACE();
            L9:
                if ((r12 & 16) == 0) goto L12;
                r6 = null;
            L12:
                if ((r12 & 32) == 0) goto L15;
                r7 = null;
            L15:
                if ((r12 & 64) == 0) goto L18;
                r8 = null;
            L18:
                if ((r12 & 128) == 0) goto L21;
                r9 = null;
            L21:
                if ((r12 & 256) == 0) goto L24;
                r10 = null;
            L24:
                if ((r12 & 512) == 0) goto L27;
                r11 = CollectionsKt.emptyList();
            L27:
                return r1.m324createvBYXiEU(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
            }

            private final boolean isLazilyConfigurable(OutputType r4) {
                OutputType.Companion r3 = OutputType.Companion;
                if (Intrinsics.areEqual(r4, r3.getSURFACE_TEXTURE()) == false) goto L5;
            L17:
                return true;
            L5:
                if (Intrinsics.areEqual(r4, r3.getSURFACE_VIEW()) == true) goto L17;
                if (Intrinsics.areEqual(r4, r3.getMEDIA_CODEC()) == true) goto L14;
                if (Intrinsics.areEqual(r4, r3.getMEDIA_RECORDER()) == true) goto L14;
                return false;
            L14:
                if (Build.VERSION.SDK_INT < 35) goto L16;
                return true;
            L16:
                return false;
            }

            /* renamed from: create-vBYXiEU, reason: not valid java name */
            public final Config m324createvBYXiEU(Size r13, int r14, String r15, OutputType r16, MirrorMode r17, TimestampBase r18, DynamicRangeProfile r19, StreamUseCase r20, StreamUseHint r21, List<Object> r22) {
                if (isLazilyConfigurable(r16) == false) goto L7;
                return new LazyOutputConfig(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, null);
            L7:
                if (Intrinsics.areEqual(r16, OutputType.Companion.getSURFACE()) == true) goto L9;
                Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
                return null;
            L9:
                return new SimpleOutputConfig(r13, r14, r15, r17, r18, r19, r20, r21, r22, null);
            }
        }

        @kotlin.Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$Config$LazyOutputConfig;", "Landroidx/camera/camera2/pipe/OutputStream$Config;", "Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "format", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "outputType", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "sensorPixelModes", "<init>", "(Landroid/util/Size;ILjava/lang/String;Landroidx/camera/camera2/pipe/OutputStream$OutputType;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "getOutputType$camera_camera2_pipe", "()Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class LazyOutputConfig extends Config {
            private final OutputType outputType;

            private LazyOutputConfig(Size r12, int r13, String r14, OutputType r15, MirrorMode r16, TimestampBase r17, DynamicRangeProfile r18, StreamUseCase r19, StreamUseHint r20, List<Object> r21) {
                super(r12, r13, r14, r16, r17, r18, r19, r20, r21, null);
                this.outputType = r15;
            }

            public /* synthetic */ LazyOutputConfig(Size r1, int r2, String r3, OutputType r4, MirrorMode r5, TimestampBase r6, DynamicRangeProfile r7, StreamUseCase r8, StreamUseHint r9, List r10, DefaultConstructorMarker r11) {
                this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
            }

            public final OutputType getOutputType$camera_camera2_pipe() {
                return this.outputType;
            }
        }

        @kotlin.Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$Config$SimpleOutputConfig;", "Landroidx/camera/camera2/pipe/OutputStream$Config;", "Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "format", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "sensorPixelModes", "<init>", "(Landroid/util/Size;ILjava/lang/String;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class SimpleOutputConfig extends Config {
            private SimpleOutputConfig(Size r12, int r13, String r14, MirrorMode r15, TimestampBase r16, DynamicRangeProfile r17, StreamUseCase r18, StreamUseHint r19, List<Object> r20) {
                super(r12, r13, r14, r15, r16, r17, r18, r19, r20, null);
            }

            public /* synthetic */ SimpleOutputConfig(Size r1, int r2, String r3, MirrorMode r4, TimestampBase r5, DynamicRangeProfile r6, StreamUseCase r7, StreamUseHint r8, List r9, DefaultConstructorMarker r10) {
                this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
            }
        }

        static {
            Companion = new Companion(null);
        }

        private Config(Size r1, int r2, String r3, MirrorMode r4, TimestampBase r5, DynamicRangeProfile r6, StreamUseCase r7, StreamUseHint r8, List<Object> r9) {
            this.size = r1;
            this.format = r2;
            this.camera = r3;
            this.mirrorMode = r4;
            this.dynamicRangeProfile = r6;
            this.streamUseCase = r7;
            this.streamUseHint = r8;
            this.sensorPixelModes = r9;
        }

        public /* synthetic */ Config(Size r1, int r2, String r3, MirrorMode r4, TimestampBase r5, DynamicRangeProfile r6, StreamUseCase r7, StreamUseHint r8, List r9, DefaultConstructorMarker r10) {
            this(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        }

        /* renamed from: getCamera-1LO98Z0, reason: not valid java name */
        public final String m316getCamera1LO98Z0() {
            return this.camera;
        }

        /* renamed from: getDynamicRangeProfile-OoVcG5w, reason: not valid java name */
        public final DynamicRangeProfile m317getDynamicRangeProfileOoVcG5w() {
            return this.dynamicRangeProfile;
        }

        /* renamed from: getFormat-8FPWQzE, reason: not valid java name */
        public final int m318getFormat8FPWQzE() {
            return this.format;
        }

        /* renamed from: getMirrorMode-dO1_9xk, reason: not valid java name */
        public final MirrorMode m319getMirrorModedO1_9xk() {
            return this.mirrorMode;
        }

        public final List<Object> getSensorPixelModes() {
            return this.sensorPixelModes;
        }

        public final Size getSize() {
            return this.size;
        }

        /* renamed from: getStreamUseCase-8x2ez34, reason: not valid java name */
        public final StreamUseCase m320getStreamUseCase8x2ez34() {
            return this.streamUseCase;
        }

        /* renamed from: getStreamUseHint-HIPxoCc, reason: not valid java name */
        public final StreamUseHint m321getStreamUseHintHIPxoCc() {
            return this.streamUseHint;
        }

        /* renamed from: getTimestampBase-pcPfPbY, reason: not valid java name */
        public final TimestampBase m322getTimestampBasepcPfPbY() {
            return null;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("Config(size=");
            r0.append(this.size);
            r0.append(", format=");
            r0.append(StreamFormat.m395toStringimpl(this.format));
            r0.append(", camera=");
            String r1 = this.camera;
            if (r1 != null) goto L5;
            String r12 = "null";
        L6:
            r0.append(r12);
            r0.append(", mirrorMode=");
            r0.append(this.mirrorMode);
            r0.append(", timestampBase=null, dynamicRangeProfile=");
            r0.append(this.dynamicRangeProfile);
            r0.append(", streamUseCase=");
            r0.append(this.streamUseCase);
            r0.append(", streamUseHint=");
            r0.append(this.streamUseHint);
            r0.append(", sensorPixelModes=");
            r0.append(this.sensorPixelModes);
            r0.append(')');
            return r0.toString();
        L5:
            r12 = CameraId.m232toStringimpl(r1);
            goto L6
        }
    }

    @kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(J)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "getValue", "()J", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class DynamicRangeProfile {
        public static final Companion Companion = null;
        private static final long DOLBY_VISION_10B_HDR_OEM = 0;
        private static final long DOLBY_VISION_10B_HDR_OEM_PO = 0;
        private static final long DOLBY_VISION_10B_HDR_REF = 0;
        private static final long DOLBY_VISION_10B_HDR_REF_PO = 0;
        private static final long DOLBY_VISION_8B_HDR_OEM = 0;
        private static final long DOLBY_VISION_8B_HDR_OEM_PO = 0;
        private static final long DOLBY_VISION_8B_HDR_REF = 0;
        private static final long DOLBY_VISION_8B_HDR_REF_PO = 0;
        private static final long HDR10 = 0;
        private static final long HDR10_PLUS = 0;
        private static final long HLG10 = 0;
        private static final long PUBLIC_MAX = 0;
        private static final long STANDARD = 0;
        private final long value;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "STANDARD", "J", "getSTANDARD-fFAQAUE", "()J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getSTANDARD-fFAQAUE, reason: not valid java name */
            public final long m332getSTANDARDfFAQAUE() {
                return DynamicRangeProfile.access$getSTANDARD$cp();
            }
        }

        static {
            Companion = new Companion(null);
            STANDARD = m326constructorimpl(1);
            HLG10 = m326constructorimpl(2);
            HDR10 = m326constructorimpl(4);
            HDR10_PLUS = m326constructorimpl(8);
            DOLBY_VISION_10B_HDR_REF = m326constructorimpl(16);
            DOLBY_VISION_10B_HDR_REF_PO = m326constructorimpl(32);
            DOLBY_VISION_10B_HDR_OEM = m326constructorimpl(64);
            DOLBY_VISION_10B_HDR_OEM_PO = m326constructorimpl(128);
            DOLBY_VISION_8B_HDR_REF = m326constructorimpl(256);
            DOLBY_VISION_8B_HDR_REF_PO = m326constructorimpl(512);
            DOLBY_VISION_8B_HDR_OEM = m326constructorimpl(1024);
            DOLBY_VISION_8B_HDR_OEM_PO = m326constructorimpl(MediaStatus.COMMAND_QUEUE_REPEAT_ONE);
            PUBLIC_MAX = m326constructorimpl(MediaStatus.COMMAND_EDIT_TRACKS);
        }

        private /* synthetic */ DynamicRangeProfile(long r1) {
            this.value = r1;
        }

        public static final /* synthetic */ long access$getSTANDARD$cp() {
            return STANDARD;
        }

        /* renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ DynamicRangeProfile m325boximpl(long r1) {
            return new DynamicRangeProfile(r1);
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static long m326constructorimpl(long r0) {
            return r0;
        }

        /* renamed from: equals-impl, reason: not valid java name */
        public static boolean m327equalsimpl(long r4, Object r6) {
            if ((r6 instanceof DynamicRangeProfile) == true) goto L6;
            return false;
        L6:
            if (r4 == ((DynamicRangeProfile) r6).m331unboximpl()) goto L8;
            return false;
        L8:
            return true;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m328equalsimpl0(long r0, long r2) {
            if (r0 != r2) goto L6;
            return true;
        L6:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m329hashCodeimpl(long r0) {
            return Long.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m330toStringimpl(long r2) {
            return "DynamicRangeProfile(value=" + r2 + ')';
        }

        public boolean equals(Object r3) {
            return m327equalsimpl(this.value, r3);
        }

        public int hashCode() {
            return m329hashCodeimpl(this.value);
        }

        public String toString() {
            return m330toStringimpl(this.value);
        }

        /* renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ long m331unboximpl() {
            return this.value;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class MirrorMode {
        public static final Companion Companion = null;
        private static final int MIRROR_MODE_AUTO = 0;
        private static final int MIRROR_MODE_H = 0;
        private static final int MIRROR_MODE_NONE = 0;
        private static final int MIRROR_MODE_V = 0;
        private final int value;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$MirrorMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "MIRROR_MODE_AUTO", "I", "getMIRROR_MODE_AUTO-DrUKqn0", "()I", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getMIRROR_MODE_AUTO-DrUKqn0, reason: not valid java name */
            public final int m340getMIRROR_MODE_AUTODrUKqn0() {
                return MirrorMode.access$getMIRROR_MODE_AUTO$cp();
            }
        }

        static {
            Companion = new Companion(null);
            MIRROR_MODE_AUTO = m334constructorimpl(0);
            MIRROR_MODE_NONE = m334constructorimpl(1);
            MIRROR_MODE_H = m334constructorimpl(2);
            MIRROR_MODE_V = m334constructorimpl(3);
        }

        private /* synthetic */ MirrorMode(int r1) {
            this.value = r1;
        }

        public static final /* synthetic */ int access$getMIRROR_MODE_AUTO$cp() {
            return MIRROR_MODE_AUTO;
        }

        /* renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ MirrorMode m333boximpl(int r1) {
            return new MirrorMode(r1);
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static int m334constructorimpl(int r0) {
            return r0;
        }

        /* renamed from: equals-impl, reason: not valid java name */
        public static boolean m335equalsimpl(int r2, Object r3) {
            if ((r3 instanceof MirrorMode) == true) goto L6;
            return false;
        L6:
            if (r2 == ((MirrorMode) r3).m339unboximpl()) goto L8;
            return false;
        L8:
            return true;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m336equalsimpl0(int r0, int r1) {
            if (r0 != r1) goto L5;
            return true;
        L5:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m337hashCodeimpl(int r0) {
            return Integer.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m338toStringimpl(int r2) {
            return "MirrorMode(value=" + r2 + ')';
        }

        public boolean equals(Object r1) {
            return m335equalsimpl(this.value, r1);
        }

        public int hashCode() {
            return m337hashCodeimpl(this.value);
        }

        public String toString() {
            return m338toStringimpl(this.value);
        }

        /* renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m339unboximpl() {
            return this.value;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$OutputType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class OutputType {
        public static final Companion Companion = null;
        private static final OutputType MEDIA_CODEC = null;
        private static final OutputType MEDIA_RECORDER = null;
        private static final OutputType SURFACE = null;
        private static final OutputType SURFACE_DEFERRED_FOR_QUERY_ONLY = null;
        private static final OutputType SURFACE_TEXTURE = null;
        private static final OutputType SURFACE_VIEW = null;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$OutputType$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SURFACE", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "getSURFACE", "()Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "SURFACE_VIEW", "getSURFACE_VIEW", "SURFACE_TEXTURE", "getSURFACE_TEXTURE", "SURFACE_DEFERRED_FOR_QUERY_ONLY", "getSURFACE_DEFERRED_FOR_QUERY_ONLY$camera_camera2_pipe", "MEDIA_CODEC", "getMEDIA_CODEC", "MEDIA_RECORDER", "getMEDIA_RECORDER", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final OutputType getMEDIA_CODEC() {
                return OutputType.access$getMEDIA_CODEC$cp();
            }

            public final OutputType getMEDIA_RECORDER() {
                return OutputType.access$getMEDIA_RECORDER$cp();
            }

            public final OutputType getSURFACE() {
                return OutputType.access$getSURFACE$cp();
            }

            public final OutputType getSURFACE_DEFERRED_FOR_QUERY_ONLY$camera_camera2_pipe() {
                return OutputType.access$getSURFACE_DEFERRED_FOR_QUERY_ONLY$cp();
            }

            public final OutputType getSURFACE_TEXTURE() {
                return OutputType.access$getSURFACE_TEXTURE$cp();
            }

            public final OutputType getSURFACE_VIEW() {
                return OutputType.access$getSURFACE_VIEW$cp();
            }
        }

        static {
            Companion = new Companion(null);
            SURFACE = new OutputType();
            SURFACE_VIEW = new OutputType();
            SURFACE_TEXTURE = new OutputType();
            SURFACE_DEFERRED_FOR_QUERY_ONLY = new OutputType();
            MEDIA_CODEC = new OutputType();
            MEDIA_RECORDER = new OutputType();
        }

        private OutputType() {
        }

        public static final /* synthetic */ OutputType access$getMEDIA_CODEC$cp() {
            return MEDIA_CODEC;
        }

        public static final /* synthetic */ OutputType access$getMEDIA_RECORDER$cp() {
            return MEDIA_RECORDER;
        }

        public static final /* synthetic */ OutputType access$getSURFACE$cp() {
            return SURFACE;
        }

        public static final /* synthetic */ OutputType access$getSURFACE_DEFERRED_FOR_QUERY_ONLY$cp() {
            return SURFACE_DEFERRED_FOR_QUERY_ONLY;
        }

        public static final /* synthetic */ OutputType access$getSURFACE_TEXTURE$cp() {
            return SURFACE_TEXTURE;
        }

        public static final /* synthetic */ OutputType access$getSURFACE_VIEW$cp() {
            return SURFACE_VIEW;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(J)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "getValue", "()J", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class StreamUseCase {
        private static final long CROPPED_RAW = 0;
        public static final Companion Companion = null;
        private static final long DEFAULT = 0;
        private static final long PREVIEW = 0;
        private static final long PREVIEW_VIDEO_STILL = 0;
        private static final long STILL_CAPTURE = 0;
        private static final long VIDEO_CALL = 0;
        private static final long VIDEO_RECORD = 0;
        private final long value;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", PluginsConstants.Strategy.DEFAULT, "J", "getDEFAULT-vrKr8v8", "()J", "PREVIEW", "getPREVIEW-vrKr8v8", "VIDEO_RECORD", "getVIDEO_RECORD-vrKr8v8", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getDEFAULT-vrKr8v8, reason: not valid java name */
            public final long m348getDEFAULTvrKr8v8() {
                return StreamUseCase.access$getDEFAULT$cp();
            }

            /* renamed from: getPREVIEW-vrKr8v8, reason: not valid java name */
            public final long m349getPREVIEWvrKr8v8() {
                return StreamUseCase.access$getPREVIEW$cp();
            }

            /* renamed from: getVIDEO_RECORD-vrKr8v8, reason: not valid java name */
            public final long m350getVIDEO_RECORDvrKr8v8() {
                return StreamUseCase.access$getVIDEO_RECORD$cp();
            }
        }

        static {
            Companion = new Companion(null);
            DEFAULT = m342constructorimpl(0);
            PREVIEW = m342constructorimpl(1);
            STILL_CAPTURE = m342constructorimpl(2);
            VIDEO_RECORD = m342constructorimpl(3);
            PREVIEW_VIDEO_STILL = m342constructorimpl(4);
            VIDEO_CALL = m342constructorimpl(5);
            CROPPED_RAW = m342constructorimpl(6);
        }

        private /* synthetic */ StreamUseCase(long r1) {
            this.value = r1;
        }

        public static final /* synthetic */ long access$getDEFAULT$cp() {
            return DEFAULT;
        }

        public static final /* synthetic */ long access$getPREVIEW$cp() {
            return PREVIEW;
        }

        public static final /* synthetic */ long access$getVIDEO_RECORD$cp() {
            return VIDEO_RECORD;
        }

        /* renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ StreamUseCase m341boximpl(long r1) {
            return new StreamUseCase(r1);
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static long m342constructorimpl(long r0) {
            return r0;
        }

        /* renamed from: equals-impl, reason: not valid java name */
        public static boolean m343equalsimpl(long r4, Object r6) {
            if ((r6 instanceof StreamUseCase) == true) goto L6;
            return false;
        L6:
            if (r4 == ((StreamUseCase) r6).m347unboximpl()) goto L8;
            return false;
        L8:
            return true;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m344equalsimpl0(long r0, long r2) {
            if (r0 != r2) goto L6;
            return true;
        L6:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m345hashCodeimpl(long r0) {
            return Long.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m346toStringimpl(long r2) {
            return "StreamUseCase(value=" + r2 + ')';
        }

        public boolean equals(Object r3) {
            return m343equalsimpl(this.value, r3);
        }

        public int hashCode() {
            return m345hashCodeimpl(this.value);
        }

        public String toString() {
            return m346toStringimpl(this.value);
        }

        /* renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ long m347unboximpl() {
            return this.value;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(J)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "getValue", "()J", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class StreamUseHint {
        public static final Companion Companion = null;
        private static final long DEFAULT = 0;
        private static final long VIDEO_RECORD = 0;
        private final long value;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", PluginsConstants.Strategy.DEFAULT, "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "getDEFAULT-4VYZOf8", "()J", "J", "VIDEO_RECORD", "getVIDEO_RECORD-4VYZOf8", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getDEFAULT-4VYZOf8, reason: not valid java name */
            public final long m358getDEFAULT4VYZOf8() {
                return StreamUseHint.access$getDEFAULT$cp();
            }

            /* renamed from: getVIDEO_RECORD-4VYZOf8, reason: not valid java name */
            public final long m359getVIDEO_RECORD4VYZOf8() {
                return StreamUseHint.access$getVIDEO_RECORD$cp();
            }
        }

        static {
            Companion = new Companion(null);
            DEFAULT = m352constructorimpl(0);
            VIDEO_RECORD = m352constructorimpl(1);
        }

        private /* synthetic */ StreamUseHint(long r1) {
            this.value = r1;
        }

        public static final /* synthetic */ long access$getDEFAULT$cp() {
            return DEFAULT;
        }

        public static final /* synthetic */ long access$getVIDEO_RECORD$cp() {
            return VIDEO_RECORD;
        }

        /* renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ StreamUseHint m351boximpl(long r1) {
            return new StreamUseHint(r1);
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static long m352constructorimpl(long r0) {
            return r0;
        }

        /* renamed from: equals-impl, reason: not valid java name */
        public static boolean m353equalsimpl(long r4, Object r6) {
            if ((r6 instanceof StreamUseHint) == true) goto L6;
            return false;
        L6:
            if (r4 == ((StreamUseHint) r6).m357unboximpl()) goto L8;
            return false;
        L8:
            return true;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m354equalsimpl0(long r0, long r2) {
            if (r0 != r2) goto L6;
            return true;
        L6:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m355hashCodeimpl(long r0) {
            return Long.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m356toStringimpl(long r2) {
            return "StreamUseHint(value=" + r2 + ')';
        }

        public boolean equals(Object r3) {
            return m353equalsimpl(this.value, r3);
        }

        public int hashCode() {
            return m355hashCodeimpl(this.value);
        }

        public String toString() {
            return m356toStringimpl(this.value);
        }

        /* renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ long m357unboximpl() {
            return this.value;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0087@\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class TimestampBase {
        public static final Companion Companion = null;
        private static final int TIMESTAMP_BASE_CHOREOGRAPHER_SYNCED = 0;
        private static final int TIMESTAMP_BASE_DEFAULT = 0;
        private static final int TIMESTAMP_BASE_MONOTONIC = 0;
        private static final int TIMESTAMP_BASE_REALTIME = 0;
        private static final int TIMESTAMP_BASE_SENSOR = 0;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/OutputStream$TimestampBase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "TIMESTAMP_BASE_REALTIME", "I", "getTIMESTAMP_BASE_REALTIME-6HVI0MA", "()I", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getTIMESTAMP_BASE_REALTIME-6HVI0MA, reason: not valid java name */
            public final int m361getTIMESTAMP_BASE_REALTIME6HVI0MA() {
                return TimestampBase.access$getTIMESTAMP_BASE_REALTIME$cp();
            }
        }

        static {
            Companion = new Companion(null);
            TIMESTAMP_BASE_DEFAULT = m360constructorimpl(0);
            TIMESTAMP_BASE_SENSOR = m360constructorimpl(1);
            TIMESTAMP_BASE_MONOTONIC = m360constructorimpl(2);
            TIMESTAMP_BASE_REALTIME = m360constructorimpl(3);
            TIMESTAMP_BASE_CHOREOGRAPHER_SYNCED = m360constructorimpl(4);
        }

        public static final /* synthetic */ int access$getTIMESTAMP_BASE_REALTIME$cp() {
            return TIMESTAMP_BASE_REALTIME;
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static int m360constructorimpl(int r0) {
            return r0;
        }
    }

    /* renamed from: getCamera-Dz_R5H8, reason: not valid java name */
    String mo308getCameraDz_R5H8();

    /* renamed from: getDynamicRangeProfile-OoVcG5w, reason: not valid java name */
    DynamicRangeProfile mo309getDynamicRangeProfileOoVcG5w();

    /* renamed from: getFormat-8FPWQzE, reason: not valid java name */
    int mo310getFormat8FPWQzE();

    /* renamed from: getId-4LaLFng, reason: not valid java name */
    int mo311getId4LaLFng();

    /* renamed from: getMirrorMode-dO1_9xk, reason: not valid java name */
    MirrorMode mo312getMirrorModedO1_9xk();

    OutputType getOutputType();

    Size getSize();

    CameraStream getStream();

    /* renamed from: getStreamUseCase-8x2ez34, reason: not valid java name */
    StreamUseCase mo313getStreamUseCase8x2ez34();

    /* renamed from: getStreamUseHint-HIPxoCc, reason: not valid java name */
    StreamUseHint mo314getStreamUseHintHIPxoCc();

    /* renamed from: getTimestampBase-pcPfPbY, reason: not valid java name */
    TimestampBase mo315getTimestampBasepcPfPbY();

    default boolean isValidForHighSpeedOperatingMode() {
        if (mo313getStreamUseCase8x2ez34() == null) goto L33;
        StreamUseCase r0 = mo313getStreamUseCase8x2ez34();
        StreamUseCase.Companion r1 = StreamUseCase.Companion;
        long r2 = r1.m348getDEFAULTvrKr8v8();
        if (r0 != null) goto L7;
        boolean r02 = false;
    L8:
        if (r02 == true) goto L35;
        StreamUseCase r03 = mo313getStreamUseCase8x2ez34();
        long r22 = r1.m349getPREVIEWvrKr8v8();
        if (r03 != null) goto L12;
        boolean r04 = false;
    L13:
        if (r04 == true) goto L36;
        StreamUseCase r05 = mo313getStreamUseCase8x2ez34();
        long r12 = r1.m350getVIDEO_RECORDvrKr8v8();
        if (r05 != null) goto L17;
        boolean r06 = false;
    L18:
        if (r06 == false) goto L20;
        return true;
    L20:
        if (mo314getStreamUseHintHIPxoCc() == null) goto L38;
        StreamUseHint r07 = mo314getStreamUseHintHIPxoCc();
        StreamUseHint.Companion r13 = StreamUseHint.Companion;
        long r23 = r13.m358getDEFAULT4VYZOf8();
        if (r07 != null) goto L24;
        boolean r08 = false;
    L25:
        if (r08 == true) goto L39;
        StreamUseHint r7 = mo314getStreamUseHintHIPxoCc();
        long r09 = r13.m359getVIDEO_RECORD4VYZOf8();
        if (r7 != null) goto L29;
        boolean r72 = false;
    L30:
        if (r72 == true) goto L40;
        return false;
    L40:
        return true;
    L29:
        r72 = StreamUseHint.m354equalsimpl0(r7.m357unboximpl(), r09);
        goto L30
    L39:
        return true;
    L24:
        r08 = StreamUseHint.m354equalsimpl0(r07.m357unboximpl(), r23);
        goto L25
    L38:
        return true;
    L17:
        r06 = StreamUseCase.m344equalsimpl0(r05.m347unboximpl(), r12);
        goto L18
    L36:
        return true;
    L12:
        r04 = StreamUseCase.m344equalsimpl0(r03.m347unboximpl(), r22);
        goto L13
    L35:
        return true;
    L7:
        r02 = StreamUseCase.m344equalsimpl0(r0.m347unboximpl(), r2);
        goto L8
    L33:
        return true;
    }
}
