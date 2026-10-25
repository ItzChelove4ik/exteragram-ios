package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda71 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_messages_forwardMessages f$10;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ ArrayList f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ MessageObject f$8;
    public final /* synthetic */ TLRPC.Peer f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda71(SendMessagesHelper r1, long r2, int r4, boolean r5, boolean r6, LongSparseArray r7, ArrayList r8, ArrayList r9, MessageObject r10, TLRPC.Peer r11, TLRPC.TL_messages_forwardMessages r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r15, TLRPC.TL_error r16) {
        SendMessagesHelper.m4910$r8$lambda$94ENbJI5yVotafzEbvevl5Wb4I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r15, r16);
    }
}
