package org.telegram.ui.bots;

import androidx.biometric.BiometricPrompt;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotBiometry$$ExternalSyntheticLambda4 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback3 f$0;
    public final /* synthetic */ BiometricPrompt.CryptoObject f$1;

    public /* synthetic */ BotBiometry$$ExternalSyntheticLambda4(Utilities.Callback3 r1, BiometricPrompt.CryptoObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        BotBiometry.$r8$lambda$AgFIv5GtY57vd6xtNQlyOnEYRpI(this.f$0, this.f$1, (Boolean) r2, (BiometricPrompt.AuthenticationResult) r3);
    }
}
