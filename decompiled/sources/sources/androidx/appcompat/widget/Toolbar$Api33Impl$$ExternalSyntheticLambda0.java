package androidx.appcompat.widget;

import android.window.OnBackInvokedCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class Toolbar$Api33Impl$$ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ Toolbar$Api33Impl$$ExternalSyntheticLambda0(Runnable r1) {
        this.f$0 = r1;
    }

    public final void onBackInvoked() {
        this.f$0.run();
    }
}
