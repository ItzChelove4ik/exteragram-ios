package org.telegram.ui.Components;

import java.util.List;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.MultiContactsSelectorBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda119 implements MultiContactsSelectorBottomSheet.SelectorListener {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TL_keyboard.TL_buttonTypeRequestPeer f$2;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda119(ChatActivityEnterView r1, MessageObject r2, TL_keyboard.TL_buttonTypeRequestPeer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.MultiContactsSelectorBottomSheet.SelectorListener
    public final void onUserSelected(List r3) {
        ChatActivityEnterView.$r8$lambda$K8vfyA8_y1oF_PPZBWYxqNhbGdw(this.f$0, this.f$1, this.f$2, r3);
    }
}
