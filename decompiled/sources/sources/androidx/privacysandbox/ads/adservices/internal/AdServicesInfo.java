package androidx.privacysandbox.ads.adservices.internal;

import android.os.Build;
import android.os.ext.SdkExtensions;
import kotlin.Metadata;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Landroidx/privacysandbox/ads/adservices/internal/AdServicesInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "adServicesVersion", _UrlKt.FRAGMENT_ENCODE_SET, "extServicesVersionS", "Extensions30ExtImpl", "Extensions30Impl", "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AdServicesInfo {
    public static final AdServicesInfo INSTANCE = null;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Landroidx/privacysandbox/ads/adservices/internal/AdServicesInfo$Extensions30ExtImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "getAdExtServicesVersionS", _UrlKt.FRAGMENT_ENCODE_SET, "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Extensions30ExtImpl {
        public static final Extensions30ExtImpl INSTANCE = null;

        static {
            INSTANCE = new Extensions30ExtImpl();
        }

        private Extensions30ExtImpl() {
        }

        public final int getAdExtServicesVersionS() {
            return SdkExtensions.getExtensionVersion(31);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Landroidx/privacysandbox/ads/adservices/internal/AdServicesInfo$Extensions30Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "getAdServicesVersion", _UrlKt.FRAGMENT_ENCODE_SET, "ads-adservices_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Extensions30Impl {
        public static final Extensions30Impl INSTANCE = null;

        static {
            INSTANCE = new Extensions30Impl();
        }

        private Extensions30Impl() {
        }

        public final int getAdServicesVersion() {
            return SdkExtensions.getExtensionVersion(DurationKt.NANOS_IN_MILLIS);
        }
    }

    static {
        INSTANCE = new AdServicesInfo();
    }

    private AdServicesInfo() {
    }

    public final int adServicesVersion() {
        if (Build.VERSION.SDK_INT >= 33) goto L5;
        return 0;
    L5:
        return Extensions30Impl.INSTANCE.getAdServicesVersion();
    }

    public final int extServicesVersionS() {
        int r1 = Build.VERSION.SDK_INT;
        if (r1 == 31) goto L10;
        if (r1 == 32) goto L10;
        return 0;
    L10:
        return Extensions30ExtImpl.INSTANCE.getAdExtServicesVersionS();
    }
}
