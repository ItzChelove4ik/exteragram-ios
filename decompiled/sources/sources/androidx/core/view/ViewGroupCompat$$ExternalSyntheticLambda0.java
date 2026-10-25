package androidx.core.view;

import android.view.View;
import android.view.WindowInsets;

/* loaded from: classes3.dex */
public final /* synthetic */ class ViewGroupCompat$$ExternalSyntheticLambda0 implements View.OnApplyWindowInsetsListener {
    public final /* synthetic */ WindowInsets[] f$0;
    public final /* synthetic */ View.OnApplyWindowInsetsListener f$1;

    public /* synthetic */ ViewGroupCompat$$ExternalSyntheticLambda0(WindowInsets[] r1, View.OnApplyWindowInsetsListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View r2, WindowInsets r3) {
        return ViewGroupCompat.m671$r8$lambda$Lnvt8czxDhWTdEYEcOIJD_YUAE(this.f$0, this.f$1, r2, r3);
    }
}
