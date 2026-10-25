package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.utils.chats.WidePosts;
import java.util.function.BooleanSupplier;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$0;
    public final /* synthetic */ BooleanSupplier f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda0(WidePosts.CommentsPostAuthorLoader r1, BooleanSupplier r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        WidePosts.CommentsPostAuthorLoader.$r8$lambda$hvJG59DvSHxFWdF_H3X8Icv7E4c(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
