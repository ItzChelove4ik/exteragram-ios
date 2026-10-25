package org.telegram.ui;

import android.text.style.CharacterStyle;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda288 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ ChatMessageCell f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ CharacterStyle f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda288(ChatActivity r1, Browser.Progress r2, ChatMessageCell r3, String r4, CharacterStyle r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r8, Object r9) {
        ChatActivity.$r8$lambda$DPCxId2BX5ddmnvpJL8FXDFr8xk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLObject) r8, (Boolean) r9);
    }
}
