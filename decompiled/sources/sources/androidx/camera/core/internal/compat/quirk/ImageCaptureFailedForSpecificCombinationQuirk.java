package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes3.dex */
public final class ImageCaptureFailedForSpecificCombinationQuirk implements Quirk {
    private static final Set<String> PIXEL_MODELS = null;

    static {
        PIXEL_MODELS = new HashSet(Arrays.asList(new String[]{"pixel 4a", "pixel 4a (5g)", "pixel 5", "pixel 5a"}));
    }

    public ImageCaptureFailedForSpecificCombinationQuirk() {
    }

    private static boolean isOnePlus12() {
        if ("oneplus".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if ("cph2583".equalsIgnoreCase(Build.MODEL) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isPixelProblematicDevice() {
        if ("google".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if (PIXEL_MODELS.contains(Build.MODEL.toLowerCase()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isVideoCapturePreviewImageCaptureCombination(Collection<UseCase> r8) {
        if (r8.size() == 3) goto L5;
        return false;
    L5:
        Iterator<UseCase> r7 = r8.iterator();
        boolean r82 = false;
        boolean r0 = false;
        boolean r2 = false;
    L7:
        if (r7.hasNext() == false) goto L20;
        UseCase r3 = r7.next();
        if ((r3 instanceof Preview) == true) goto L10;
        if ((r3 instanceof ImageCapture) == true) goto L13;
        if (r3.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) == false) goto L7;
        if (r3.getCurrentConfig().getCaptureType() == UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE) goto L18;
        r0 = false;
        goto L7
    L18:
        r0 = true;
        goto L7
    L13:
        r2 = true;
        goto L7
    L10:
        r82 = true;
        goto L7
    L20:
        if (r82 == false) goto L24;
        if (r0 == false) goto L24;
        if (r2 == false) goto L24;
        return true;
    L24:
        return false;
    }

    public static boolean load() {
        if (isOnePlus12() == false) goto L5;
        return true;
    L5:
        if (isPixelProblematicDevice() == true) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean shouldForceEnableStreamSharingForOnePlus12(String r2, Collection<UseCase> r3) {
        if (r2.equals("1") == true) goto L5;
        return false;
    L5:
        if (isVideoCapturePreviewImageCaptureCombination(r3) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean shouldForceEnableStreamSharingForPixelDevice(String r2, Collection<UseCase> r3) {
        if (r2.equals("1") == true) goto L5;
        return false;
    L5:
        if (isVideoCapturePreviewImageCaptureCombination(r3) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean shouldForceEnableStreamSharing(String r2, Collection<UseCase> r3) {
        if (isOnePlus12() == false) goto L7;
        return shouldForceEnableStreamSharingForOnePlus12(r2, r3);
    L7:
        if (isPixelProblematicDevice() == true) goto L9;
        return false;
    L9:
        return shouldForceEnableStreamSharingForPixelDevice(r2, r3);
    }
}
