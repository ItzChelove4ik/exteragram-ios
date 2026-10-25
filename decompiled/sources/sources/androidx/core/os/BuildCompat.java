package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\f\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u000f¨\u0006\u0014"}, d2 = {"Landroidx/core/os/BuildCompat;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "codename", "buildCodename", _UrlKt.FRAGMENT_ENCODE_SET, "isAtLeastPreReleaseCodename", "(Ljava/lang/String;Ljava/lang/String;)Z", "isAtLeastT", "()Z", "isAtLeastB_1", _UrlKt.FRAGMENT_ENCODE_SET, "R_EXTENSION_INT", "I", "S_EXTENSION_INT", "T_EXTENSION_INT", "AD_SERVICES_EXTENSION_INT", "Api30Impl", "core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BuildCompat {

    @JvmField
    public static final int AD_SERVICES_EXTENSION_INT = 0;
    public static final BuildCompat INSTANCE = null;

    @JvmField
    public static final int R_EXTENSION_INT = 0;

    @JvmField
    public static final int S_EXTENSION_INT = 0;

    @JvmField
    public static final int T_EXTENSION_INT = 0;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Landroidx/core/os/BuildCompat$Api30Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getExtensionVersion", _UrlKt.FRAGMENT_ENCODE_SET, "extension", "core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = null;

        static {
            INSTANCE = new Api30Impl();
        }

        private Api30Impl() {
        }

        public final int getExtensionVersion(int r1) {
            return SdkExtensions.getExtensionVersion(r1);
        }
    }

    static {
        INSTANCE = new BuildCompat();
        int r0 = Build.VERSION.SDK_INT;
        int r1 = 0;
        if (r0 < 30) goto L5;
        int r3 = Api30Impl.INSTANCE.getExtensionVersion(30);
    L6:
        R_EXTENSION_INT = r3;
        if (r0 < 30) goto L9;
        int r32 = Api30Impl.INSTANCE.getExtensionVersion(31);
    L10:
        S_EXTENSION_INT = r32;
        if (r0 < 30) goto L13;
        int r33 = Api30Impl.INSTANCE.getExtensionVersion(33);
    L14:
        T_EXTENSION_INT = r33;
        if (r0 < 30) goto L17;
        r1 = Api30Impl.INSTANCE.getExtensionVersion(DurationKt.NANOS_IN_MILLIS);
    L17:
        AD_SERVICES_EXTENSION_INT = r1;
        return;
    L13:
        r33 = 0;
        goto L14
    L9:
        r32 = 0;
        goto L10
    L5:
        r3 = 0;
        goto L6
    }

    private BuildCompat() {
    }

    @JvmStatic
    public static final boolean isAtLeastB_1() {
        if (Build.VERSION.SDK_INT >= 36) goto L5;
        return false;
    L5:
        if (BuildCompat$$ExternalSyntheticBackportWithForwarding1.m() < 3600001) goto L10;
        return true;
    L10:
        return false;
    }

    @JvmStatic
    public static final boolean isAtLeastPreReleaseCodename(String r4, String r5) {
        if (Intrinsics.areEqual("REL", r5) == false) goto L5;
        return false;
    L5:
        Integer r0 = isAtLeastPreReleaseCodename$codenameToInt(r5);
        Integer r2 = isAtLeastPreReleaseCodename$codenameToInt(r4);
        if (r0 == null) goto L12;
        if (r2 == null) goto L12;
        if (r0.intValue() < r2.intValue()) goto L11;
        return true;
    L11:
        return false;
    L12:
        if (r0 != null) goto L18;
        if (r2 != null) goto L18;
        Locale r02 = Locale.ROOT;
        if (r5.toUpperCase(r02).compareTo(r4.toUpperCase(r02)) < 0) goto L17;
        return true;
    L17:
        return false;
    L18:
        if (r0 == null) goto L20;
        return true;
    L20:
        return false;
    }

    private static final Integer isAtLeastPreReleaseCodename$codenameToInt(String r1) {
        if (Intrinsics.areEqual(r1.toUpperCase(Locale.ROOT), "BAKLAVA") == true) goto L5;
        return null;
    L5:
        return 0;
    }

    @Deprecated(message = "Android Tiramisu is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 33`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 33", imports = {}))
    @JvmStatic
    public static final boolean isAtLeastT() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 33) goto L5;
        return true;
    L5:
        if (r0 >= 32) goto L7;
        return false;
    L7:
        if (isAtLeastPreReleaseCodename("Tiramisu", Build.VERSION.CODENAME) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
