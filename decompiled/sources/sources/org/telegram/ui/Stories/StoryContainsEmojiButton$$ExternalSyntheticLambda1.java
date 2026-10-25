package org.telegram.ui.Stories;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryContainsEmojiButton$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ StoryContainsEmojiButton f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ StoryContainsEmojiButton$$ExternalSyntheticLambda1(StoryContainsEmojiButton r1, Object r2, ArrayList r3, boolean[] r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StoryContainsEmojiButton.m19759$r8$lambda$kUbC5uPWii6qjpC7ZGhSO3KI3I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
