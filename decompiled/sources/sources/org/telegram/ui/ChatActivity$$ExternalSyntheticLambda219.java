package org.telegram.ui;

import android.content.DialogInterface;
import android.text.style.CharacterStyle;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda219 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ CharacterStyle f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ ChatMessageCell f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda219(ChatActivity r1, String r2, CharacterStyle r3, MessageObject r4, ChatMessageCell r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r9, int r10) {
        ChatActivity.m7414$r8$lambda$9gaVQfobs16ATgjHpmdJKQVcw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
