package org.telegram.ui;

import com.exteragram.messenger.utils.chats.WidePosts;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda128 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ Integer f$10;
    public final /* synthetic */ byte[] f$11;
    public final /* synthetic */ int f$12;
    public final /* synthetic */ int f$13;
    public final /* synthetic */ TLRPC.TL_messages_getDiscussionMessage f$14;
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$15;
    public final /* synthetic */ Runnable f$16;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ Long f$5;
    public final /* synthetic */ Integer f$6;
    public final /* synthetic */ Integer f$7;
    public final /* synthetic */ Runnable f$8;
    public final /* synthetic */ String f$9;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda128(LaunchActivity r1, AtomicBoolean r2, int r3, boolean r4, TLRPC.Chat r5, Long r6, Integer r7, Integer r8, Runnable r9, String r10, Integer r11, byte[] r12, int r13, int r14, TLRPC.TL_messages_getDiscussionMessage r15, WidePosts.CommentsPostAuthorLoader r16, Runnable r17) {
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
        this.f$13 = r14;
        this.f$14 = r15;
        this.f$15 = r16;
        this.f$16 = r17;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r20, TLRPC.TL_error r21) {
        LaunchActivity.$r8$lambda$MXEmYwDOB8MOIonRt_a5ynGcvE8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, r20, r21);
    }
}
