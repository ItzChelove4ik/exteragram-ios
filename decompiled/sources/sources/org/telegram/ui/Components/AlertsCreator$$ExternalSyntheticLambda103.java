package org.telegram.ui.Components;

import android.util.SparseArray;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda103 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Runnable f$10;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ MessageObject.GroupedMessages f$4;
    public final /* synthetic */ TLRPC.EncryptedChat f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ int f$8;
    public final /* synthetic */ SparseArray[] f$9;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda103(long r1, boolean r3, int r4, MessageObject r5, MessageObject.GroupedMessages r6, TLRPC.EncryptedChat r7, long r8, int r10, int r11, SparseArray[] r12, Runnable r13) {
        this.f$0 = r1;
        this.f$1 = r3;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r10;
        this.f$8 = r11;
        this.f$9 = r12;
        this.f$10 = r13;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r18, int r19) {
        AlertsCreator.$r8$lambda$45Z4lyQdMXhFG_phvhoy7rYTsPo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r18, r19);
    }
}
