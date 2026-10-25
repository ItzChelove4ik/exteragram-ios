package com.google.android.material.motion;

import android.window.OnBackInvokedCallback;

/* loaded from: classes5.dex */
public final /* synthetic */ class MaterialBackOrchestrator$Api33BackCallbackDelegate$$ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final /* synthetic */ MaterialBackHandler f$0;

    public /* synthetic */ MaterialBackOrchestrator$Api33BackCallbackDelegate$$ExternalSyntheticLambda0(MaterialBackHandler r1) {
        this.f$0 = r1;
    }

    public final void onBackInvoked() {
        this.f$0.handleBackInvoked();
    }
}
