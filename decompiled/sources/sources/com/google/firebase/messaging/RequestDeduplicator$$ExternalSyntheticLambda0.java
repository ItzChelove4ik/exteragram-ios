package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class RequestDeduplicator$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ RequestDeduplicator f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ RequestDeduplicator$$ExternalSyntheticLambda0(RequestDeduplicator r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r2) {
        return RequestDeduplicator.$r8$lambda$7vbEhMqRR2Cih64GBiItUUcAhYE(this.f$0, this.f$1, r2);
    }
}
