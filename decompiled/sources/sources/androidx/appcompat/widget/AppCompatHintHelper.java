package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* loaded from: classes3.dex */
abstract class AppCompatHintHelper {
    public static InputConnection onCreateInputConnection(InputConnection r0, EditorInfo r1, View r2) {
        if (r0 != null) goto L4;
    L9:
        return r0;
    L4:
        if (r1.hintText != null) goto L9;
        ViewParent r12 = r2.getParent();
    L7:
        if ((r12 instanceof View) == false) goto L9;
        r12 = r12.getParent();
        goto L7
    }
}
