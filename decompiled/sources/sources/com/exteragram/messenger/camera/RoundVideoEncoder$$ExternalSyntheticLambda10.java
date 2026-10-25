package com.exteragram.messenger.camera;

import org.telegram.messenger.DispatchQueue;

/* loaded from: classes4.dex */
public final /* synthetic */ class RoundVideoEncoder$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ DispatchQueue f$0;

    public /* synthetic */ RoundVideoEncoder$$ExternalSyntheticLambda10(DispatchQueue r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.recycle();
    }
}
