package androidx.core.os;

import android.os.Build;
import com.android.dx.AppDataDirGuesser;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class BuildCompat$$ExternalSyntheticBackportWithForwarding1 {
    public static /* synthetic */ int m() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 36) goto L7;
        return r0 * AppDataDirGuesser.PER_USER_RANGE;
    L7:
        return BuildCompat$$ExternalSyntheticApiModelOutline0.m();
    }
}
