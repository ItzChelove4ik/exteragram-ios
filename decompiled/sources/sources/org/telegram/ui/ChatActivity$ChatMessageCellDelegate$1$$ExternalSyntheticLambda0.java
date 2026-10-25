package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Stories.recorder.StoryRecorder;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$1$$ExternalSyntheticLambda0 implements Utilities.Callback4 {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate.AnonymousClass1 f$0;
    public final /* synthetic */ StoryRecorder f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$1$$ExternalSyntheticLambda0(ChatActivity.ChatMessageCellDelegate.AnonymousClass1 r1, StoryRecorder r2, View r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback4
    public final void run(Object r8, Object r9, Object r10, Object r11) {
        ChatActivity.ChatMessageCellDelegate.AnonymousClass1.$r8$lambda$ii7ayWe7VJevw098VRa0E8J6HnU(this.f$0, this.f$1, this.f$2, (Long) r8, (Runnable) r9, (Boolean) r10, (Long) r11);
    }
}
