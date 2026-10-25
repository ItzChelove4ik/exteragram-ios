package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class InviteLinkBottomSheet$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ InviteLinkBottomSheet f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$4;
    public final /* synthetic */ TLRPC.TL_chatInviteImporter f$5;
    public final /* synthetic */ TLRPC.ChannelParticipant f$6;

    public /* synthetic */ InviteLinkBottomSheet$$ExternalSyntheticLambda9(InviteLinkBottomSheet r1, AlertDialog r2, Context r3, long r4, TLRPC.TL_chatInviteExported r6, TLRPC.TL_chatInviteImporter r7, TLRPC.ChannelParticipant r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InviteLinkBottomSheet.$r8$lambda$Fup4DBioGKA4t_gOvlYNyLSzrEU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
