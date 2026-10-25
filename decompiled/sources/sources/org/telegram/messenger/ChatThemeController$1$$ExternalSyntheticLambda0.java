package org.telegram.messenger;

import org.telegram.messenger.ChatThemeController;
import org.telegram.tgnet.ResultCallback;
import org.telegram.ui.ActionBar.EmojiThemes;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$1$$ExternalSyntheticLambda0 implements EmojiThemes.ColorsLoadedCallback {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ EmojiThemes f$1;

    public /* synthetic */ ChatThemeController$1$$ExternalSyntheticLambda0(ResultCallback r1, EmojiThemes r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.EmojiThemes.ColorsLoadedCallback
    public final void onColorsLoaded() {
        ChatThemeController.AnonymousClass1.m3930$r8$lambda$WUOXDGj6ur6Ksiy5DXjFwChPbw(this.f$0, this.f$1);
    }
}
