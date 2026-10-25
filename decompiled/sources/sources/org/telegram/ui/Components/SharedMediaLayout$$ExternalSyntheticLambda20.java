package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda20 implements RecyclerListView.OnItemClickListenerExtended {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ SharedMediaLayout.MediaPage f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda20(SharedMediaLayout r1, SharedMediaLayout.MediaPage r2, Context r3, long r4, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListenerExtended
    public final void onItemClick(View r11, int r12, float r13, float r14) {
        SharedMediaLayout.m12762$r8$lambda$7nLI5dvvByyV42nwwgDzzMJUSA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r11, r12, r13, r14);
    }
}
