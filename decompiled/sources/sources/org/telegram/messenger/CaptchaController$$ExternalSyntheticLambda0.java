package org.telegram.messenger;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.recaptcha.RecaptchaTasksClient;
import org.telegram.messenger.CaptchaController;

/* loaded from: classes5.dex */
public final /* synthetic */ class CaptchaController$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ CaptchaController.Request f$2;

    public /* synthetic */ CaptchaController$$ExternalSyntheticLambda0(String r1, String r2, CaptchaController.Request r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r3) {
        CaptchaController.$r8$lambda$sd2jL_u9Guq5e7dqFK5pWLGn8dc(this.f$0, this.f$1, this.f$2, (RecaptchaTasksClient) r3);
    }
}
