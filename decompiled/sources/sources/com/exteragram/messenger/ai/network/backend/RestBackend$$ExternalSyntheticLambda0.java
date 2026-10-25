package com.exteragram.messenger.ai.network.backend;

import okhttp3.Call;

/* loaded from: classes4.dex */
public final /* synthetic */ class RestBackend$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Call f$0;

    public /* synthetic */ RestBackend$$ExternalSyntheticLambda0(Call r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.cancel();
    }
}
