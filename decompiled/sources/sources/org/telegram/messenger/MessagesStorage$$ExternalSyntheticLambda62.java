package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda62 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.messages_Dialogs f$1;
    public final /* synthetic */ Runnable f$10;
    public final /* synthetic */ TLRPC.messages_Dialogs f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ ArrayList f$5;
    public final /* synthetic */ SparseArray f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ HashMap f$8;
    public final /* synthetic */ HashSet f$9;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda62(MessagesStorage r1, TLRPC.messages_Dialogs r2, TLRPC.messages_Dialogs r3, ArrayList r4, ArrayList r5, ArrayList r6, SparseArray r7, ArrayList r8, HashMap r9, HashSet r10, Runnable r11) {
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
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$rWrydi_w9PSyxOsLnUryS0cq39o(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}
