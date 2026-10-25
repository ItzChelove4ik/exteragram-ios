package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlertContactsLayout;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda46 implements ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ CharSequence f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ boolean f$7;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda46(LaunchActivity r1, ChatActivity r2, ArrayList r3, int r4, int r5, CharSequence r6, int r7, boolean r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertContactsLayout.PhonebookShareAlertDelegate
    public final void didSelectContact(TLRPC.User r17, boolean r18, int r19, long r20, boolean r22, long r23) {
        LaunchActivity.$r8$lambda$bZgcR4i3cQW0GAWfPjPLrGo1UbA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r17, r18, r19, r20, r22, r23);
    }
}
