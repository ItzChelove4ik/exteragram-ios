package org.telegram.ui;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda11 implements OnCompleteListener {
    public final /* synthetic */ LoginActivity.LoginActivityEmailCodeView f$0;
    public final /* synthetic */ GoogleSignInClient f$1;

    public /* synthetic */ LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda11(LoginActivity.LoginActivityEmailCodeView r1, GoogleSignInClient r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r2) {
        LoginActivity.LoginActivityEmailCodeView.m15841$r8$lambda$3vYOFCLUVn4GOrcJZfRxhTZb7Y(this.f$0, this.f$1, r2);
    }
}
