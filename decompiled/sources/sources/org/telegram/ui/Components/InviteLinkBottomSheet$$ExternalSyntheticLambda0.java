package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import java.util.HashMap;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class InviteLinkBottomSheet$$ExternalSyntheticLambda0 implements RecyclerListView.OnItemClickListener {
    public final /* synthetic */ InviteLinkBottomSheet f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;
    public final /* synthetic */ HashMap f$2;
    public final /* synthetic */ TLRPC.ChatFull f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ BaseFragment f$6;

    public /* synthetic */ InviteLinkBottomSheet$$ExternalSyntheticLambda0(InviteLinkBottomSheet r1, TLRPC.TL_chatInviteExported r2, HashMap r3, TLRPC.ChatFull r4, Context r5, long r6, BaseFragment r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
    public final void onItemClick(View r11, int r12) {
        InviteLinkBottomSheet.$r8$lambda$xFBtOwhsbkqPdS2873DRoqizSGM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
