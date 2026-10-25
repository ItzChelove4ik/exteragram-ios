package org.telegram.messenger;

import org.telegram.tgnet.ResultCallback;
import org.telegram.ui.ActionBar.EmojiThemes;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda15 implements EmojiThemes.ColorsLoadedCallback {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ EmojiThemes f$1;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda15(ResultCallback r1, EmojiThemes r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.EmojiThemes.ColorsLoadedCallback
    public final void onColorsLoaded() {
        ChatThemeController.$r8$lambda$9ljL1XJFcrdtWan4GgdCkHeA1hI(this.f$0, this.f$1);
    }
}
