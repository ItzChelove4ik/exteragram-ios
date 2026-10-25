package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.PollCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class TodoItemMenu$$ExternalSyntheticLambda18 implements PollCreateActivity.PollCreateActivityDelegate {
    public final /* synthetic */ TodoItemMenu f$0;
    public final /* synthetic */ ChatActivity f$1;

    public /* synthetic */ TodoItemMenu$$ExternalSyntheticLambda18(TodoItemMenu r1, ChatActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.PollCreateActivity.PollCreateActivityDelegate
    public final void sendPoll(TLRPC.MessageMedia r7, ArrayList r8, boolean r9, int r10) {
        TodoItemMenu.$r8$lambda$gphvZuYzaby3l64C9SptheH9oNo(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
