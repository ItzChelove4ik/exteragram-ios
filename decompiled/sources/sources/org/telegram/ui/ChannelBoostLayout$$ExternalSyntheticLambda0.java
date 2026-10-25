package org.telegram.ui;

import android.content.Context;
import android.view.View;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelBoostLayout$$ExternalSyntheticLambda0 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ ChannelBoostLayout f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;
    public final /* synthetic */ BaseFragment f$4;

    public /* synthetic */ ChannelBoostLayout$$ExternalSyntheticLambda0(ChannelBoostLayout r1, Context r2, long r3, Theme.ResourcesProvider r5, BaseFragment r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r9, int r10) {
        ChannelBoostLayout.m7258$r8$lambda$GhurS3FtoHJm20tUPuMDQ0AnCc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
