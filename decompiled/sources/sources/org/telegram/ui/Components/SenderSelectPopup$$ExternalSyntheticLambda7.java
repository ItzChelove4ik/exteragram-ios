package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import java.util.List;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SenderSelectPopup;

/* loaded from: classes7.dex */
public final /* synthetic */ class SenderSelectPopup$$ExternalSyntheticLambda7 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ SenderSelectPopup f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ ChatActivity f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ SenderSelectPopup.OnSelectCallback f$5;

    public /* synthetic */ SenderSelectPopup$$ExternalSyntheticLambda7(SenderSelectPopup r1, List r2, Context r3, ChatActivity r4, boolean r5, SenderSelectPopup.OnSelectCallback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r9, int r10) {
        SenderSelectPopup.$r8$lambda$HIGKI1wAB9kKh9QcQ9WmAYEk_Co(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
