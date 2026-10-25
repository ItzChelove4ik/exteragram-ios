package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.utils.chats.WidePosts;
import java.util.function.BooleanSupplier;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public final /* synthetic */ class WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$0;
    public final /* synthetic */ BooleanSupplier f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ WidePosts$CommentsPostAuthorLoader$$ExternalSyntheticLambda2(WidePosts.CommentsPostAuthorLoader r1, BooleanSupplier r2, TLObject r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WidePosts.CommentsPostAuthorLoader.$r8$lambda$gjjzueT_z3itgCxvhwJtxlXATpg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
