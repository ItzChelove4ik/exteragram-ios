package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final /* synthetic */ class zzac implements Continuation {
    static final /* synthetic */ zzac zza = null;

    static {
        zza = new zzac();
    }

    private /* synthetic */ zzac() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task r1) {
        Intent r0 = (Intent) ((Bundle) r1.getResult()).getParcelable("notification_data");
        if (r0 != null) goto L5;
        return null;
    L5:
        return new CloudMessage(r0);
    }
}
