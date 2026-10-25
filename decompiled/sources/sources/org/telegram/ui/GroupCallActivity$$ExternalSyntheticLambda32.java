package org.telegram.ui;

import android.app.Activity;
import android.view.View;
import org.telegram.messenger.ChatObject;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda32 implements RecyclerListView.OnItemClickListenerExtended {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ Activity f$1;
    public final /* synthetic */ ChatObject.Call f$2;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda32(GroupCallActivity r1, Activity r2, ChatObject.Call r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListenerExtended
    public final void onItemClick(View r8, int r9, float r10, float r11) {
        GroupCallActivity.$r8$lambda$RSysMEdo37O6WxwsTVxkCM90IQg(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
