package org.telegram.ui;

import android.view.View;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.QrActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class QrActivity$ThemeListViewController$$ExternalSyntheticLambda1 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ QrActivity.ThemeListViewController f$0;

    public /* synthetic */ QrActivity$ThemeListViewController$$ExternalSyntheticLambda1(QrActivity.ThemeListViewController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r1, int r2) {
        this.f$0.onItemClicked(r1, r2);
    }
}
