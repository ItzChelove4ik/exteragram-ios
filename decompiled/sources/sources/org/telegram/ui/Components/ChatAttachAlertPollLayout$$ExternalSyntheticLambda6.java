package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda6 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ ChatAttachAlertPollLayout f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ ChatAttachAlert f$2;
    public final /* synthetic */ Context f$3;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda6(ChatAttachAlertPollLayout r1, Theme.ResourcesProvider r2, ChatAttachAlert r3, Context r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r7, int r8) {
        ChatAttachAlertPollLayout.m9837$r8$lambda$fqvD6Lp7juAPjPPjnmplnmHcs(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
