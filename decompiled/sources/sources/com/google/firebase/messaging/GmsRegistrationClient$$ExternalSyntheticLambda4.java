package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class GmsRegistrationClient$$ExternalSyntheticLambda4 implements Continuation {
    public final /* synthetic */ GmsRegistrationClient f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ GmsRegistrationClient$$ExternalSyntheticLambda4(GmsRegistrationClient r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r2) {
        return GmsRegistrationClient.$r8$lambda$dcA7IYHfufmIHy3QuCUwV4XCcgI(this.f$0, this.f$1, r2);
    }
}
