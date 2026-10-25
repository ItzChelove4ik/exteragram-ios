package org.telegram.ui;

import android.app.PendingIntent;
import com.google.android.gms.tasks.OnSuccessListener;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$PhoneView$$ExternalSyntheticLambda16 implements OnSuccessListener {
    public final /* synthetic */ LoginActivity.PhoneView f$0;

    public /* synthetic */ LoginActivity$PhoneView$$ExternalSyntheticLambda16(LoginActivity.PhoneView r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r1) {
        LoginActivity.PhoneView.$r8$lambda$dSpuUAevP4IEEJIedS5ygq_C0gI(this.f$0, (PendingIntent) r1);
    }
}
