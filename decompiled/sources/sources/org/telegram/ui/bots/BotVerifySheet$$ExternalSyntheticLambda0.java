package org.telegram.ui.bots;

import java.util.ArrayList;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotVerifySheet$$ExternalSyntheticLambda0 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TL_bots.botVerifierSettings f$3;

    public /* synthetic */ BotVerifySheet$$ExternalSyntheticLambda0(DialogsActivity r1, int r2, long r3, TL_bots.botVerifierSettings r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r14, ArrayList r15, CharSequence r16, boolean r17, boolean r18, int r19, int r20, TopicsFragment r21) {
        return BotVerifySheet.$r8$lambda$x5Yrw2bULKJdRpOmKd7iEVSZhKY(this.f$0, this.f$1, this.f$2, this.f$3, r14, r15, r16, r17, r18, r19, r20, r21);
    }
}
