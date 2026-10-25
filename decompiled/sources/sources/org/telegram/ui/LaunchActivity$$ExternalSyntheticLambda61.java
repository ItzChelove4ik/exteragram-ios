package org.telegram.ui;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda61 implements Utilities.Callback {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ ChannelBoostsController f$3;
    public final /* synthetic */ Long f$4;
    public final /* synthetic */ ChatMessageCell f$5;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda61(LaunchActivity r1, Browser.Progress r2, Runnable r3, ChannelBoostsController r4, Long r5, ChatMessageCell r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        LaunchActivity.m15595$r8$lambda$GiJiD7UitvqHhwQAhBf2XfR74(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TL_stories.TL_premium_boostsStatus) r8);
    }
}
