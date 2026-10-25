package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.core.R$id;

/* loaded from: classes3.dex */
public abstract class ViewGroupCompat {
    private static final WindowInsets CONSUMED = null;
    static boolean sCompatInsetsDispatchInstalled;

    /* renamed from: $r8$lambda$Lnvt8czxDhWTdEYEcOIJD_YU-AE, reason: not valid java name */
    public static /* synthetic */ WindowInsets m671$r8$lambda$Lnvt8czxDhWTdEYEcOIJD_YUAE(WindowInsets[] r0, View.OnApplyWindowInsetsListener r1, View r2, WindowInsets r3) {
        if (r1 == null) goto L4;
        WindowInsets r12 = r1.onApplyWindowInsets(r2, r3);
    L5:
        r0[0] = r12;
        return CONSUMED;
    L4:
        r12 = r2.onApplyWindowInsets(r3);
        goto L5
    }

    static {
        CONSUMED = WindowInsetsCompat.CONSUMED.toWindowInsets();
        sCompatInsetsDispatchInstalled = false;
    }

    public static WindowInsets dispatchApplyWindowInsets(View r5, WindowInsets r6) {
        Object r0 = r5.getTag(R$id.tag_on_apply_window_listener);
        Object r1 = r5.getTag(R$id.tag_window_insets_animation_callback);
        if ((r0 instanceof View.OnApplyWindowInsetsListener) == false) goto L6;
        View.OnApplyWindowInsetsListener r02 = (View.OnApplyWindowInsetsListener) r0;
    L9:
        WindowInsets[] r12 = {CONSUMED};
        r5.setOnApplyWindowInsetsListener(new ViewGroupCompat$$ExternalSyntheticLambda0(r12, r02));
        r5.dispatchApplyWindowInsets(r6);
        Object r62 = r5.getTag(R$id.tag_compat_insets_dispatch);
        if ((r62 instanceof View.OnApplyWindowInsetsListener) == false) goto L12;
        r02 = (View.OnApplyWindowInsetsListener) r62;
    L12:
        r5.setOnApplyWindowInsetsListener(r02);
        WindowInsets r03 = r12[0];
        if (r03 != null) goto L15;
    L21:
        WindowInsets r52 = r12[0];
        if (r52 == null) goto L25;
        return r52;
    L25:
        return CONSUMED;
    L15:
        if (r03.isConsumed() == true) goto L21;
        if ((r5 instanceof ViewGroup) == false) goto L21;
        ViewGroup r53 = (ViewGroup) r5;
        int r04 = r53.getChildCount();
        int r2 = 0;
    L19:
        if (r2 >= r04) goto L21;
        dispatchApplyWindowInsets(r53.getChildAt(r2), r12[0]);
        r2 = r2 + 1;
        goto L19
    L6:
        if ((r1 instanceof View.OnApplyWindowInsetsListener) == false) goto L8;
        r02 = (View.OnApplyWindowInsetsListener) r1;
        goto L9
    L8:
        r02 = null;
        goto L9
    }

    public static boolean isTransitionGroup(ViewGroup r0) {
        return r0.isTransitionGroup();
    }
}
