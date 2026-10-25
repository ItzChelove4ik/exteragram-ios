package androidx.camera.camera2.compat.quirk;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FlashAvailabilityBufferUnderflowQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", "Companion", "DeviceInfo", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CameraXQuirksClassDetector"})
/* loaded from: classes3.dex */
public final class FlashAvailabilityBufferUnderflowQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final Set<DeviceInfo> KNOWN_AFFECTED_MODELS = null;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FlashAvailabilityBufferUnderflowQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "KNOWN_AFFECTED_MODELS", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/compat/quirk/FlashAvailabilityBufferUnderflowQuirk$DeviceInfo;", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean isEnabled() {
            return FlashAvailabilityBufferUnderflowQuirk.access$getKNOWN_AFFECTED_MODELS$cp().contains(DeviceInfo.Companion.invoke(Build.MANUFACTURER, Build.MODEL));
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0012\u0010\b¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FlashAvailabilityBufferUnderflowQuirk$DeviceInfo;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "manufacturer", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getManufacturer", "getModel", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DeviceInfo {
        public static final Companion Companion = null;
        private final String manufacturer;
        private final String model;

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0086\u0002¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/compat/quirk/FlashAvailabilityBufferUnderflowQuirk$DeviceInfo$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "invoke", "Landroidx/camera/camera2/compat/quirk/FlashAvailabilityBufferUnderflowQuirk$DeviceInfo;", "manufacturer", _UrlKt.FRAGMENT_ENCODE_SET, "model", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final DeviceInfo invoke(String r2, String r3) {
                Locale r0 = Locale.US;
                return new DeviceInfo(r2.toLowerCase(r0), r3.toLowerCase(r0), null);
            }
        }

        static {
            Companion = new Companion(null);
        }

        private DeviceInfo(String r1, String r2) {
            this.manufacturer = r1;
            this.model = r2;
        }

        public /* synthetic */ DeviceInfo(String r1, String r2, DefaultConstructorMarker r3) {
            this(r1, r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof DeviceInfo) == true) goto L8;
            return false;
        L8:
            DeviceInfo r52 = (DeviceInfo) r5;
            if (Intrinsics.areEqual(this.manufacturer, r52.manufacturer) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.model, r52.model) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public int hashCode() {
            return (this.manufacturer.hashCode() * 31) + this.model.hashCode();
        }

        public String toString() {
            return "DeviceInfo(manufacturer=" + this.manufacturer + ", model=" + this.model + ')';
        }
    }

    static {
        Companion = new Companion(null);
        DeviceInfo.Companion r0 = DeviceInfo.Companion;
        KNOWN_AFFECTED_MODELS = SetsKt.setOf(new DeviceInfo[]{r0.invoke("sprd", "lemp"), r0.invoke("sprd", "DM20C")});
    }

    public FlashAvailabilityBufferUnderflowQuirk() {
    }

    public static final /* synthetic */ Set access$getKNOWN_AFFECTED_MODELS$cp() {
        return KNOWN_AFFECTED_MODELS;
    }
}
