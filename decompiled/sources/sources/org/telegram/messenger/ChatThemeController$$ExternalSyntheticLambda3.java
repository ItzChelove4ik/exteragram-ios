package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.EmojiThemes;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda3 implements EmojiThemes.ColorsLoadedCallback {
    public final /* synthetic */ ChatThemeController f$0;
    public final /* synthetic */ TLRPC.TL_chatThemeUniqueGift f$1;
    public final /* synthetic */ EmojiThemes f$2;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda3(ChatThemeController r1, TLRPC.TL_chatThemeUniqueGift r2, EmojiThemes r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.EmojiThemes.ColorsLoadedCallback
    public final void onColorsLoaded() {
        ChatThemeController.$r8$lambda$BbziMLBR9ndND54MZeoCa83AWNE(this.f$0, this.f$1, this.f$2);
    }
}
