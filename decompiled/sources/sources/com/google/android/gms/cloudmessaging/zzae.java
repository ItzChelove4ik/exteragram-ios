package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

/* loaded from: classes.dex */
final /* synthetic */ class zzae implements Continuation {
    static final /* synthetic */ zzae zza = null;

    static {
        zza = new zzae();
    }

    private /* synthetic */ zzae() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task r3) {
        if (r3.isSuccessful() == false) goto L7;
        return (Bundle) r3.getResult();
    L7:
        if (Log.isLoggable("Rpc", 3) == false) goto L11;
        Log.d("Rpc", "Error making request: ".concat(String.valueOf(r3.getException())));
    L11:
        throw new IOException("SERVICE_NOT_AVAILABLE", r3.getException());
    }
}
