package org.telegram.ui;

import android.util.SparseIntArray;
import androidx.viewpager.widget.ViewPager;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ReactedUsersListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$110$$ExternalSyntheticLambda2 implements ReactedUsersListView.OnHeightChangedListener {
    public final /* synthetic */ SparseIntArray f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ ViewPager f$3;
    public final /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout f$4;
    public final /* synthetic */ int[] f$5;

    public /* synthetic */ ChatActivity$110$$ExternalSyntheticLambda2(SparseIntArray r1, int r2, int r3, ViewPager r4, ActionBarPopupWindow.ActionBarPopupWindowLayout r5, int[] r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.Components.ReactedUsersListView.OnHeightChangedListener
    public final void onHeightChanged(ReactedUsersListView r9, int r10) {
        ChatActivity.AnonymousClass110.m8174$r8$lambda$Pokxp1DobjqRTPZqi6AfZnT8o(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
