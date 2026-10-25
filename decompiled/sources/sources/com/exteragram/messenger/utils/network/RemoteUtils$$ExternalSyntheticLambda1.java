package com.exteragram.messenger.utils.network;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda1(AtomicBoolean r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RemoteUtils.m1790$r8$lambda$0p9UTAdmiW_DBagD4ppdMeLYUk(this.f$0, this.f$1);
    }
}
