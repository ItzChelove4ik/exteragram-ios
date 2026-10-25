package org.telegram.ui.Adapters;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.MentionsAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$7$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MentionsAdapter.AnonymousClass7 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ TLObject f$5;
    public final /* synthetic */ MessagesController f$6;

    public /* synthetic */ MentionsAdapter$7$$ExternalSyntheticLambda1(MentionsAdapter.AnonymousClass7 r1, int r2, ArrayList r3, LongSparseArray r4, TLRPC.TL_error r5, TLObject r6, MessagesController r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MentionsAdapter.AnonymousClass7.$r8$lambda$zKNGlFYhWSel0AkBXRWguEoa5p8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
