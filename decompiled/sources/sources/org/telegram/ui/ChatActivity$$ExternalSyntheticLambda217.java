package org.telegram.ui;

import android.text.style.CharacterStyle;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda217 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ ChatMessageCell f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ CharacterStyle f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda217(ChatActivity r1, Browser.Progress r2, ChatMessageCell r3, String r4, CharacterStyle r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatActivity.m7417$r8$lambda$9xkZoyaCfsTPDKXfYYj9ETl_eU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
