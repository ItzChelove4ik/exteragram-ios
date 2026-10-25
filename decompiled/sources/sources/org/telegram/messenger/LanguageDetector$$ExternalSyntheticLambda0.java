package org.telegram.messenger;

import com.google.android.gms.tasks.OnSuccessListener;
import org.telegram.messenger.LanguageDetector;

/* loaded from: classes5.dex */
public final /* synthetic */ class LanguageDetector$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ LanguageDetector.StringCallback f$0;

    public /* synthetic */ LanguageDetector$$ExternalSyntheticLambda0(LanguageDetector.StringCallback r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r1) {
        LanguageDetector.$r8$lambda$A9hSaUSYsiHXwXl3Wg4NimNwdkM(this.f$0, (String) r1);
    }
}
