package androidx.constraintlayout.motion.widget;

import android.annotation.SuppressLint;
import android.view.View;

@SuppressLint({"LogConditional"})
/* loaded from: classes3.dex */
public abstract class Debug {
    public static String getName(View r1) {
        return r1.getContext().getResources().getResourceEntryName(r1.getId());
    L4:
        return "UNKNOWN";
    }
}
