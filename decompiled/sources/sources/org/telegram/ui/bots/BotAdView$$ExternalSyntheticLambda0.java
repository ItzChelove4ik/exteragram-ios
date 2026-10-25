package org.telegram.ui.bots;

import android.text.style.ClickableSpan;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotAdView$$ExternalSyntheticLambda0 implements LinkSpanDrawable.LinksTextView.OnLinkPress {
    public final /* synthetic */ BotAdView f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ MessageObject f$2;

    public /* synthetic */ BotAdView$$ExternalSyntheticLambda0(BotAdView r1, ChatActivity r2, MessageObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView.OnLinkPress
    public final void run(ClickableSpan r3) {
        BotAdView.$r8$lambda$OIb3kV9Ft2Li2M_cmO02FE0zoNY(this.f$0, this.f$1, this.f$2, r3);
    }
}
