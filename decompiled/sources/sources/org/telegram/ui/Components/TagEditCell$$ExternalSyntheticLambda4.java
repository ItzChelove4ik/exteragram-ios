package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class TagEditCell$$ExternalSyntheticLambda4 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TLRPC.TL_messages_editChatParticipantRank f$3;
    public final /* synthetic */ BottomSheet f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;
    public final /* synthetic */ ButtonWithCounterView f$7;

    public /* synthetic */ TagEditCell$$ExternalSyntheticLambda4(MessagesController r1, long r2, TLRPC.User r4, TLRPC.TL_messages_editChatParticipantRank r5, BottomSheet r6, boolean r7, Theme.ResourcesProvider r8, ButtonWithCounterView r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r12, Object r13) {
        TagEditCell.$r8$lambda$EYaTEK9ekuFJlW_iXKESiNrDaJI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, (TLRPC.Updates) r12, (TLRPC.TL_error) r13);
    }
}
