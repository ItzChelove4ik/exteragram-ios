package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import androidx.collection.LongSparseArray;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class InviteMembersBottomSheet$$ExternalSyntheticLambda2 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ InviteMembersBottomSheet f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ BaseFragment f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ Context f$4;

    public /* synthetic */ InviteMembersBottomSheet$$ExternalSyntheticLambda2(InviteMembersBottomSheet r1, long r2, BaseFragment r4, LongSparseArray r5, Context r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r9, int r10) {
        InviteMembersBottomSheet.$r8$lambda$dsXwWPemcmdmI3oksW5D41WsLTM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
