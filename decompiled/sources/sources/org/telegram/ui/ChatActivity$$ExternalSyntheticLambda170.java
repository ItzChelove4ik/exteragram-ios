package org.telegram.ui;

import android.text.style.CharacterStyle;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda170 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ ChatMessageCell f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_contact f$3;
    public final /* synthetic */ CharacterStyle f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda170(ChatActivity r1, ChatMessageCell r2, String r3, TLRPC.TL_contact r4, CharacterStyle r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        ChatActivity.$r8$lambda$7Oosq47scuckL8nMPPh6OMv9JCE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.User) r7);
    }
}
