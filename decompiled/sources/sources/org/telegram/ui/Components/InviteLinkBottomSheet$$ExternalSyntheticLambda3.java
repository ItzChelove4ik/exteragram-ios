package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class InviteLinkBottomSheet$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ InviteLinkBottomSheet f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$4;
    public final /* synthetic */ TLRPC.TL_chatInviteImporter f$5;

    public /* synthetic */ InviteLinkBottomSheet$$ExternalSyntheticLambda3(InviteLinkBottomSheet r1, AlertDialog r2, Context r3, long r4, TLRPC.TL_chatInviteExported r6, TLRPC.TL_chatInviteImporter r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r9) {
        InviteLinkBottomSheet.m11067$r8$lambda$SATBJY4oVEYQD07Y7IPMdyCW8w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (TLRPC.ChannelParticipant) r9);
    }
}
