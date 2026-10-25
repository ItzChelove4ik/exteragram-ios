package org.telegram.ui.Components;

import android.util.SparseArray;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda173 implements Runnable {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ TLRPC.EncryptedChat f$10;
    public final /* synthetic */ TLRPC.ChatFull f$11;
    public final /* synthetic */ long f$12;
    public final /* synthetic */ MessageObject f$13;
    public final /* synthetic */ SparseArray[] f$14;
    public final /* synthetic */ MessageObject.GroupedMessages f$15;
    public final /* synthetic */ int f$16;
    public final /* synthetic */ int f$17;
    public final /* synthetic */ Runnable f$18;
    public final /* synthetic */ Runnable f$19;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$20;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.ChannelParticipant[] f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ AlertDialog[] f$6;
    public final /* synthetic */ BaseFragment f$7;
    public final /* synthetic */ TLRPC.User f$8;
    public final /* synthetic */ TLRPC.Chat f$9;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda173(int[] r1, int[] r2, int r3, TLObject r4, TLRPC.ChannelParticipant[] r5, int r6, AlertDialog[] r7, BaseFragment r8, TLRPC.User r9, TLRPC.Chat r10, TLRPC.EncryptedChat r11, TLRPC.ChatFull r12, long r13, MessageObject r15, SparseArray[] r16, MessageObject.GroupedMessages r17, int r18, int r19, Runnable r20, Runnable r21, Theme.ResourcesProvider r22) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
        this.f$12 = r13;
        this.f$13 = r15;
        this.f$14 = r16;
        this.f$15 = r17;
        this.f$16 = r18;
        this.f$17 = r19;
        this.f$18 = r20;
        this.f$19 = r21;
        this.f$20 = r22;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertsCreator.$r8$lambda$VDlqP2SkH_1STo7Y03mck4jyQhY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18, this.f$19, this.f$20);
    }
}
