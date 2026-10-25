package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda518 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ HashMap f$10;
    public final /* synthetic */ HashSet f$11;
    public final /* synthetic */ Runnable f$12;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ TLRPC.messages_Dialogs f$3;
    public final /* synthetic */ TLRPC.messages_Dialogs f$4;
    public final /* synthetic */ ArrayList f$5;
    public final /* synthetic */ TLObject f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ SparseArray f$8;
    public final /* synthetic */ ArrayList f$9;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda518(MessagesController r1, ArrayList r2, ArrayList r3, TLRPC.messages_Dialogs r4, TLRPC.messages_Dialogs r5, ArrayList r6, TLObject r7, ArrayList r8, SparseArray r9, ArrayList r10, HashMap r11, HashSet r12, Runnable r13) {
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
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r16, TLRPC.TL_error r17) {
        MessagesController.m4495$r8$lambda$3srxCu384XQSlVrjg3rEuwgYI4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, r16, r17);
    }
}
