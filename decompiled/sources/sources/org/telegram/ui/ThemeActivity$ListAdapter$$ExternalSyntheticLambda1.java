package org.telegram.ui;

import android.view.View;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ThemeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeActivity$ListAdapter$$ExternalSyntheticLambda1 implements RecyclerListView.OnItemLongClickListener {
    public final /* synthetic */ ThemeActivity.ListAdapter f$0;
    public final /* synthetic */ ThemeActivity.ThemeAccentsListAdapter f$1;

    public /* synthetic */ ThemeActivity$ListAdapter$$ExternalSyntheticLambda1(ThemeActivity.ListAdapter r1, ThemeActivity.ThemeAccentsListAdapter r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener
    public final boolean onItemClick(View r2, int r3) {
        return ThemeActivity.ListAdapter.m20721$r8$lambda$5mrqp0vZCnQvra7nElGyyji0w(this.f$0, this.f$1, r2, r3);
    }
}
