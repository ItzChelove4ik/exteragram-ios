package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import android.util.Pair;
import androidx.camera.core.impl.Quirk;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes3.dex */
public class CaptureFailedRetryQuirk implements Quirk {
    private static final Set<Pair<String, String>> FAILED_RETRY_ALLOW_LIST = null;

    static {
        FAILED_RETRY_ALLOW_LIST = new HashSet(Collections.singletonList(Pair.create("SAMSUNG", "SM-G981U1")));
    }

    public CaptureFailedRetryQuirk() {
    }

    public static boolean load() {
        String r0 = Build.BRAND;
        Locale r1 = Locale.US;
        String r02 = r0.toUpperCase(r1);
        String r12 = Build.MODEL.toUpperCase(r1);
        return FAILED_RETRY_ALLOW_LIST.contains(Pair.create(r02, r12));
    }
}
