package androidx.camera.camera2.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\t¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExtraSupportedSurfaceCombinationsQuirk;", "Landroidx/camera/core/impl/Quirk;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/SurfaceCombination;", "getSamsungS7ExtraCombinations", "(Ljava/lang/String;)Ljava/util/List;", "getExtraSupportedSurfaceCombinations", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ExtraSupportedSurfaceCombinationsQuirk implements Quirk {
    public static final Companion Companion = null;
    private static final SurfaceCombination FULL_LEVEL_YUV_PRIV_YUV_CONFIGURATION = null;
    private static final SurfaceCombination FULL_LEVEL_YUV_YUV_YUV_CONFIGURATION = null;
    private static final SurfaceCombination LEVEL_3_LEVEL_PRIV_PRIV_YUV_SUBSET_CONFIGURATION = null;
    private static final Set<String> SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_GOOGLE_MODELS = null;
    private static final Set<String> SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_SAMSUNG_MODELS = null;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\r\u0010\u0012\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Landroidx/camera/camera2/compat/quirk/ExtraSupportedSurfaceCombinationsQuirk$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "FULL_LEVEL_YUV_PRIV_YUV_CONFIGURATION", "Landroidx/camera/core/impl/SurfaceCombination;", "FULL_LEVEL_YUV_YUV_YUV_CONFIGURATION", "LEVEL_3_LEVEL_PRIV_PRIV_YUV_SUBSET_CONFIGURATION", "SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_GOOGLE_MODELS", _UrlKt.FRAGMENT_ENCODE_SET, "SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_SAMSUNG_MODELS", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "isSamsungS7", "isSamsungS7$camera_camera2", "()Z", "supportExtraLevel3ConfigurationsGoogleDevice", "supportExtraLevel3ConfigurationsGoogleDevice$camera_camera2", "supportExtraLevel3ConfigurationsSamsungDevice", "supportExtraLevel3ConfigurationsSamsungDevice$camera_camera2", "createFullYuvPrivYuvConfiguration", "createFullYuvPrivYuvConfiguration$camera_camera2", "createFullYuvYuvYuvConfiguration", "createFullYuvYuvYuvConfiguration$camera_camera2", "createLevel3PrivPrivYuvSubsetConfiguration", "createLevel3PrivPrivYuvSubsetConfiguration$camera_camera2", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final SurfaceCombination createFullYuvPrivYuvConfiguration$camera_camera2() {
            SurfaceCombination r7 = new SurfaceCombination();
            SurfaceConfig.Companion r0 = SurfaceConfig.Companion;
            SurfaceConfig.ConfigType r1 = SurfaceConfig.ConfigType.YUV;
            r7.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.VGA, null, 4, null));
            r7.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, SurfaceConfig.ConfigType.PRIV, SurfaceConfig.ConfigSize.PREVIEW, null, 4, null));
            r7.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.MAXIMUM, null, 4, null));
            return r7;
        }

        public final SurfaceCombination createFullYuvYuvYuvConfiguration$camera_camera2() {
            SurfaceCombination r6 = new SurfaceCombination();
            SurfaceConfig.Companion r0 = SurfaceConfig.Companion;
            SurfaceConfig.ConfigType r1 = SurfaceConfig.ConfigType.YUV;
            r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.VGA, null, 4, null));
            r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.PREVIEW, null, 4, null));
            r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.MAXIMUM, null, 4, null));
            return r6;
        }

        public final SurfaceCombination createLevel3PrivPrivYuvSubsetConfiguration$camera_camera2() {
            SurfaceCombination r6 = new SurfaceCombination();
            SurfaceConfig.Companion r0 = SurfaceConfig.Companion;
            SurfaceConfig.ConfigType r1 = SurfaceConfig.ConfigType.PRIV;
            r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.PREVIEW, null, 4, null));
            r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, r1, SurfaceConfig.ConfigSize.VGA, null, 4, null));
            r6.addSurfaceConfig(SurfaceConfig.Companion.create$default(r0, SurfaceConfig.ConfigType.YUV, SurfaceConfig.ConfigSize.MAXIMUM, null, 4, null));
            return r6;
        }

        public final boolean isEnabled() {
            if (isSamsungS7$camera_camera2() == false) goto L5;
            return true;
        L5:
            if (supportExtraLevel3ConfigurationsGoogleDevice$camera_camera2() == false) goto L7;
            return true;
        L7:
            if (supportExtraLevel3ConfigurationsSamsungDevice$camera_camera2() == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final boolean isSamsungS7$camera_camera2() {
            String r2 = Build.DEVICE;
            if (StringsKt.equals("heroqltevzw", r2, true) == false) goto L5;
        L9:
            return true;
        L5:
            if (StringsKt.equals("heroqltetmo", r2, true) == true) goto L9;
            return false;
        }

        public final boolean supportExtraLevel3ConfigurationsGoogleDevice$camera_camera2() {
            if (Device.INSTANCE.isGoogleDevice() == true) goto L6;
            return false;
        L6:
            String r1 = Build.MODEL.toUpperCase(Locale.ROOT);
            return ExtraSupportedSurfaceCombinationsQuirk.access$getSUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_GOOGLE_MODELS$cp().contains(r1);
        }

        public final boolean supportExtraLevel3ConfigurationsSamsungDevice$camera_camera2() {
            if (Device.INSTANCE.isSamsungDevice() == true) goto L5;
            return false;
        L5:
            String r5 = Build.MODEL.toUpperCase(Locale.ROOT);
            Iterator r1 = ExtraSupportedSurfaceCombinationsQuirk.access$getSUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_SAMSUNG_MODELS$cp().iterator();
        L7:
            if (r1.hasNext() == false) goto L12;
            if (StringsKt.startsWith$default(r5, (String) r1.next(), false, 2, null) == false) goto L7;
            return true;
        L12:
            return false;
        }
    }

    static {
        Companion r0 = new Companion(null);
        Companion = r0;
        FULL_LEVEL_YUV_PRIV_YUV_CONFIGURATION = r0.createFullYuvPrivYuvConfiguration$camera_camera2();
        FULL_LEVEL_YUV_YUV_YUV_CONFIGURATION = r0.createFullYuvYuvYuvConfiguration$camera_camera2();
        LEVEL_3_LEVEL_PRIV_PRIV_YUV_SUBSET_CONFIGURATION = r0.createLevel3PrivPrivYuvSubsetConfiguration$camera_camera2();
        SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_GOOGLE_MODELS = SetsKt.setOf(new String[]{"PIXEL 6", "PIXEL 6 PRO", "PIXEL 7", "PIXEL 7 PRO", "PIXEL 8", "PIXEL 8 PRO", "PIXEL 9", "PIXEL 9 PRO", "PIXEL 9 PRO XL", "PIXEL 9 PRO FOLD"});
        SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_SAMSUNG_MODELS = SetsKt.setOf(new String[]{"SM-S921", "SC-51E", "SCG25", "SM-S926", "SM-S928", "SC-52E", "SCG26", "SM-S931", "SM-S936", "SM-S937", "SM-S938", "SCG31", "SCG32", "SC-51F", "SC-52F"});
    }

    public ExtraSupportedSurfaceCombinationsQuirk() {
    }

    public static final /* synthetic */ Set access$getSUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_GOOGLE_MODELS$cp() {
        return SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_GOOGLE_MODELS;
    }

    public static final /* synthetic */ Set access$getSUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_SAMSUNG_MODELS$cp() {
        return SUPPORT_EXTRA_LEVEL_3_CONFIGURATIONS_SAMSUNG_MODELS;
    }

    private final List<SurfaceCombination> getSamsungS7ExtraCombinations(String r2) {
        ArrayList r1 = new ArrayList();
        if (Intrinsics.areEqual(r2, "1") == false) goto L5;
        r1.add(FULL_LEVEL_YUV_PRIV_YUV_CONFIGURATION);
    L5:
        return r1;
    }

    public final List<SurfaceCombination> getExtraSupportedSurfaceCombinations(String r3) {
        Companion r0 = Companion;
        if (r0.isSamsungS7$camera_camera2() == false) goto L7;
        return getSamsungS7ExtraCombinations(r3);
    L7:
        if (r0.supportExtraLevel3ConfigurationsGoogleDevice$camera_camera2() == true) goto L14;
        if (r0.supportExtraLevel3ConfigurationsSamsungDevice$camera_camera2() == true) goto L14;
        return CollectionsKt.emptyList();
    L14:
        return CollectionsKt.listOf(LEVEL_3_LEVEL_PRIV_PRIV_YUV_SUBSET_CONFIGURATION);
    }
}
