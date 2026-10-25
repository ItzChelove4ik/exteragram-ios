package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.Store;

/* loaded from: classes5.dex */
public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda9 implements SuccessContinuation {
    public final /* synthetic */ FirebaseMessaging f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Store.Token f$3;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda9(FirebaseMessaging r1, String r2, boolean r3, Store.Token r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object r4) {
        return FirebaseMessaging.$r8$lambda$uomApBO9Rimks2gDHrUhO7dV5io(this.f$0, this.f$1, this.f$2, this.f$3, (String) r4);
    }
}
