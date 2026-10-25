package org.telegram.ui;

import org.telegram.messenger.LanguageDetector;
import org.telegram.ui.Cells.BotHelpCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda318 implements LanguageDetector.StringCallback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ BotHelpCell f$1;
    public final /* synthetic */ CharSequence f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda318(ChatActivity r1, BotHelpCell r2, CharSequence r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.LanguageDetector.StringCallback
    public final void run(String r3) {
        ChatActivity.$r8$lambda$Tu1nAgiFwe_7RfuvSSWSEiJtJbg(this.f$0, this.f$1, this.f$2, r3);
    }
}
