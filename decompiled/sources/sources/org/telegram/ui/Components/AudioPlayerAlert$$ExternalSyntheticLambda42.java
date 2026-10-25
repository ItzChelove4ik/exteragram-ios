package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda42 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_document f$3;
    public final /* synthetic */ MessageObject f$4;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda42(AudioPlayerAlert r1, ArrayList r2, boolean r3, TLRPC.TL_document r4, MessageObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r14, ArrayList r15, CharSequence r16, boolean r17, boolean r18, int r19, int r20, TopicsFragment r21) {
        return AudioPlayerAlert.$r8$lambda$4QQ6sjtvyktt2FMYJtm9DnKFxM8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r14, r15, r16, r17, r18, r19, r20, r21);
    }
}
