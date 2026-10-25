package androidx.camera.camera2.adapter;

import android.media.CamcorderProfile;
import android.media.EncoderProfiles;
import android.os.Build;
import androidx.camera.camera2.compat.quirk.CamcorderProfileResolutionQuirk;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.InvalidVideoProfilesQuirk;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.compat.EncoderProfilesProxyCompat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0002J\n\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u000bH\u0003J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u000bH\u0003J\u0010\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/adapter/EncoderProfilesProviderAdapter;", "Landroidx/camera/core/impl/EncoderProfilesProvider;", "cameraIdString", _UrlKt.FRAGMENT_ENCODE_SET, "cameraQuirks", "Landroidx/camera/core/impl/Quirks;", "<init>", "(Ljava/lang/String;Landroidx/camera/core/impl/Quirks;)V", "hasValidCameraId", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "mEncoderProfilesCache", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/EncoderProfilesProxy;", "hasProfile", "quality", "getAll", "findHighestQualityProfiles", "findLowestQualityProfiles", "getProfilesInternal", "createProfilesFromCamcorderProfile", "isEncoderProfilesResolutionValidInQuirk", "profiles", "Api31Impl", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EncoderProfilesProviderAdapter implements EncoderProfilesProvider {
    public static final Companion Companion = null;
    private final int cameraId;
    private final String cameraIdString;
    private final Quirks cameraQuirks;
    private final boolean hasValidCameraId;
    private final Map<Integer, EncoderProfilesProxy> mEncoderProfilesCache;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/adapter/EncoderProfilesProviderAdapter$Api31Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getAll", "Landroid/media/EncoderProfiles;", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "quality", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Api31Impl {
        public static final Api31Impl INSTANCE = null;

        static {
            INSTANCE = new Api31Impl();
        }

        private Api31Impl() {
        }

        public final EncoderProfiles getAll(String r1, int r2) {
            return CamcorderProfile.getAll(r1, r2);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/adapter/EncoderProfilesProviderAdapter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public EncoderProfilesProviderAdapter(String r1, Quirks r2) {
        this.cameraIdString = r1;
        this.cameraQuirks = r2;
        this.mEncoderProfilesCache = new LinkedHashMap();
        int r12 = Integer.parseInt(r1);     // Catch: NumberFormatException -> L5
        boolean r22 = true;
    L6:
        this.hasValidCameraId = r22;
        this.cameraId = r12;
        return;
    L5:
        Logger.w("EncoderProfilesProviderAdapter", "Camera id is not an integer:  " + this.cameraIdString + ", unable to create EncoderProfilesProviderAdapter.");
        r22 = false;
        r12 = -1;
        goto L6
    }

    private final EncoderProfilesProxy createProfilesFromCamcorderProfile(int r4) {
        CamcorderProfile r3 = CamcorderProfile.get(this.cameraId, r4);     // Catch: RuntimeException -> L5
    L7:
        if (r3 != null) goto L9;
        return null;
    L9:
        return EncoderProfilesProxyCompat.from(r3);
    L5:
        e = move-exception;
        Logger.w("EncoderProfilesProviderAdapter", "Unable to get CamcorderProfile by quality: " + r4, e);
        r3 = null;
        goto L7
    }

    private final EncoderProfilesProxy findHighestQualityProfiles() {
        Iterator<Integer> r0 = EncoderProfilesProvider.QUALITY_HIGH_TO_LOW.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        EncoderProfilesProxy r1 = getAll(r0.next().intValue());
        if (r1 == null) goto L4;
        return r1;
    L8:
        return null;
    }

    private final EncoderProfilesProxy findLowestQualityProfiles() {
        int r0 = CollectionsKt.getLastIndex(EncoderProfilesProvider.QUALITY_HIGH_TO_LOW);
    L4:
        if ((-1) >= r0) goto L9;
        EncoderProfilesProxy r1 = getAll(EncoderProfilesProvider.QUALITY_HIGH_TO_LOW.get(r0).intValue());
        if (r1 != null) goto L7;
        r0 = r0 - 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    private final EncoderProfilesProxy getProfilesInternal(int r4) {
        if (Build.VERSION.SDK_INT < 31) goto L16;
        EncoderProfiles r0 = Api31Impl.INSTANCE.getAll(this.cameraIdString, r4);
        if (r0 != null) goto L9;
        return null;
    L9:
        if (DeviceQuirks.INSTANCE.get(InvalidVideoProfilesQuirk.class) == null) goto L17;
        Logger.d("EncoderProfilesProviderAdapter", "EncoderProfiles contains invalid video profiles, use CamcorderProfile to create EncoderProfilesProxy.");
        goto L16
    L17:
        return EncoderProfilesProxyCompat.from(r0);
    L13:
        e = move-exception;
        Logger.w("EncoderProfilesProviderAdapter", "Failed to create EncoderProfilesProxy, EncoderProfiles might contain invalid video profiles. Use CamcorderProfile instead.", e);
    L16:
        return this.createProfilesFromCamcorderProfile(r4);
    }

    private final boolean isEncoderProfilesResolutionValidInQuirk(EncoderProfilesProxy r3) {
        CamcorderProfileResolutionQuirk r2 = (CamcorderProfileResolutionQuirk) this.cameraQuirks.get(CamcorderProfileResolutionQuirk.class);
        if (r2 != null) goto L5;
        return true;
    L5:
        List<EncoderProfilesProxy.VideoProfileProxy> r32 = r3.getVideoProfiles();
        if (r32.isEmpty() == false) goto L8;
        return true;
    L8:
        EncoderProfilesProxy.VideoProfileProxy r33 = r32.get(0);
        return r2.getSupportedResolutions().contains(r33.getResolution());
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProvider
    public EncoderProfilesProxy getAll(int r4) {
        EncoderProfilesProxy r1 = null;
        if (this.hasValidCameraId == true) goto L6;
        return null;
    L6:
        if (CamcorderProfile.hasProfile(this.cameraId, r4) == true) goto L9;
        return null;
    L9:
        if (this.mEncoderProfilesCache.containsKey(Integer.valueOf(r4)) == true) goto L11;
        EncoderProfilesProxy r0 = getProfilesInternal(r4);
        if (r0 != null) goto L15;
    L23:
        this.mEncoderProfilesCache.put(Integer.valueOf(r4), r0);
        return r0;
    L15:
        if (isEncoderProfilesResolutionValidInQuirk(r0) == true) goto L23;
        if (r4 != 0) goto L18;
        r1 = findLowestQualityProfiles();
    L22:
        r0 = r1;
        goto L23
    L18:
        if (r4 != 1) goto L22;
        r1 = findHighestQualityProfiles();
        goto L22
    L11:
        return this.mEncoderProfilesCache.get(Integer.valueOf(r4));
    }

    @Override // androidx.camera.core.impl.EncoderProfilesProvider
    public boolean hasProfile(int r3) {
        if (this.hasValidCameraId == true) goto L6;
        return false;
    L6:
        if (getAll(r3) == null) goto L9;
        return true;
    L9:
        return false;
    }
}
