package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import android.util.Log;
import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.WithinAppServiceConnection;

/* loaded from: classes5.dex */
class WithinAppServiceBinder extends Binder {
    private final IntentHandler intentHandler;

    public interface IntentHandler {
        Task<Void> handle(Intent r1);
    }

    public static /* synthetic */ void $r8$lambda$t7ts_4_AauwgtOGOXEuv2hjyze8(WithinAppServiceConnection.BindRequest r0, Task r1) {
        r0.finish();
    }

    public WithinAppServiceBinder(IntentHandler r1) {
        this.intentHandler = r1;
    }

    public void send(WithinAppServiceConnection.BindRequest r3) {
        if (Binder.getCallingUid() != Process.myUid()) goto L10;
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L7;
        Log.d("FirebaseMessaging", "service received new intent via bind strategy");
    L7:
        this.intentHandler.handle(r3.intent).addOnCompleteListener(new CredentialManager$$ExternalSyntheticLambda0(), new WithinAppServiceBinder$$ExternalSyntheticLambda0(r3));
        return;
    L10:
        throw new SecurityException("Binding only allowed within app");
    }
}
