package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public interface SignInClient {
    @Deprecated
    Task<BeginSignInResult> beginSignIn(BeginSignInRequest r1);

    String getPhoneNumberFromIntent(Intent r1);

    Task<PendingIntent> getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest r1);

    @Deprecated
    SignInCredential getSignInCredentialFromIntent(Intent r1);

    @Deprecated
    Task<PendingIntent> getSignInIntent(GetSignInIntentRequest r1);

    @Deprecated
    Task<Void> signOut();
}
