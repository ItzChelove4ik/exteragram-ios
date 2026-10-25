package org.telegram.ui.Adapters;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.MentionsAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$7$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MentionsAdapter.AnonymousClass7 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ MessagesController f$4;

    public /* synthetic */ MentionsAdapter$7$$ExternalSyntheticLambda0(MentionsAdapter.AnonymousClass7 r1, int r2, ArrayList r3, LongSparseArray r4, MessagesController r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MentionsAdapter.AnonymousClass7.$r8$lambda$sAygAurSP9mNI8mbqknn4iuJ2EA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
