package org.telegram.ui.bots;

import androidx.biometric.BiometricPrompt;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotBiometry$$ExternalSyntheticLambda2 implements Utilities.Callback3 {
    public final /* synthetic */ BotBiometry f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ BotBiometry$$ExternalSyntheticLambda2(BotBiometry r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback3
    public final void run(Object r2, Object r3, Object r4) {
        BotBiometry.$r8$lambda$pEiG_HCeyRVHgYVe8JMkCi7lud8(this.f$0, this.f$1, (Boolean) r2, (BiometricPrompt.AuthenticationResult) r3, (BiometricPrompt.CryptoObject) r4);
    }
}
