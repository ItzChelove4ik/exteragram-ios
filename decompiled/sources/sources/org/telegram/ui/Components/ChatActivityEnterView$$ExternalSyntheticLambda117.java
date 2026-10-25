package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda117 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeSwitchInline f$2;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda117(ChatActivityEnterView r1, MessageObject r2, TL_keyboard.TL_inlineButtonTypeSwitchInline r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r12, ArrayList r13, CharSequence r14, boolean r15, boolean r16, int r17, int r18, TopicsFragment r19) {
        return ChatActivityEnterView.$r8$lambda$e0SkfzilB8RTBSCPzWxnt24OnHQ(this.f$0, this.f$1, this.f$2, r12, r13, r14, r15, r16, r17, r18, r19);
    }
}
