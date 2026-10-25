package org.telegram.ui;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda9 implements OnCompleteListener {
    public final /* synthetic */ LoginActivity.LoginActivitySetupEmail f$0;
    public final /* synthetic */ GoogleSignInClient f$1;

    public /* synthetic */ LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda9(LoginActivity.LoginActivitySetupEmail r1, GoogleSignInClient r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r2) {
        LoginActivity.LoginActivitySetupEmail.$r8$lambda$kUDFuMKR293PG3Kl1e8rdHfeaVU(this.f$0, this.f$1, r2);
    }
}
