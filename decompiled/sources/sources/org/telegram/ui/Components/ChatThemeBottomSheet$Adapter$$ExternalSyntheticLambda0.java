package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatThemeBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatThemeBottomSheet$Adapter$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ChatThemeBottomSheet.Adapter f$0;
    public final /* synthetic */ Theme.ThemeInfo f$1;

    public /* synthetic */ ChatThemeBottomSheet$Adapter$$ExternalSyntheticLambda0(ChatThemeBottomSheet.Adapter r1, Theme.ThemeInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatThemeBottomSheet.Adapter.m10000$r8$lambda$MUPCxfMvqH_LmOnCUidlJeSbDo(this.f$0, this.f$1, r2, r3);
    }
}
