package org.telegram.ui.bots;

import androidx.biometric.BiometricPrompt;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotBiometry$$ExternalSyntheticLambda3 implements Utilities.Callback3 {
    public final /* synthetic */ BotBiometry f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ BotBiometry$$ExternalSyntheticLambda3(BotBiometry r1, String r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback3
    public final void run(Object r7, Object r8, Object r9) {
        BotBiometry.m21308$r8$lambda$SwnpHR0cMtPMBDUwliQmdyIAAs(this.f$0, this.f$1, this.f$2, (Boolean) r7, (BiometricPrompt.AuthenticationResult) r8, (BiometricPrompt.CryptoObject) r9);
    }
}
