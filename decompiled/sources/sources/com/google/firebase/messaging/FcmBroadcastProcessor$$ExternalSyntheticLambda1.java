package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
public final /* synthetic */ class FcmBroadcastProcessor$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Intent f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ FcmBroadcastProcessor$$ExternalSyntheticLambda1(Context r1, Intent r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r3) {
        return FcmBroadcastProcessor.m1956$r8$lambda$7tuBEF0Y9HKYiHL1KguBHpRmY(this.f$0, this.f$1, this.f$2, r3);
    }
}
