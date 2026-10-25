package org.telegram.ui.Components;

import android.view.View;
import java.util.List;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class SenderSelectPopup$$ExternalSyntheticLambda8 implements RecyclerListView.OnItemLongClickListener {
    public final /* synthetic */ SenderSelectPopup f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ ChatActivity f$3;

    public /* synthetic */ SenderSelectPopup$$ExternalSyntheticLambda8(SenderSelectPopup r1, List r2, MessagesController r3, ChatActivity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener
    public final boolean onItemClick(View r7, int r8) {
        return SenderSelectPopup.$r8$lambda$u0XZtqVZ_E2FDclVx06u_2sg8iA(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
