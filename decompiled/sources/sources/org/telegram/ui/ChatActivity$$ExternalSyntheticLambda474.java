package org.telegram.ui;

import android.view.View;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.Components.ReactedUsersListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda474 implements ReactedUsersListView.OnHeightChangedListener {
    public final /* synthetic */ View f$0;
    public final /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout f$1;
    public final /* synthetic */ int[] f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda474(View r1, ActionBarPopupWindow.ActionBarPopupWindowLayout r2, int[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.ReactedUsersListView.OnHeightChangedListener
    public final void onHeightChanged(ReactedUsersListView r3, int r4) {
        ChatActivity.m7477$r8$lambda$RtDHhOFIX4jOnlTN1hupeDANEU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
