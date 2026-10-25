package org.telegram.ui;

import android.view.View;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda8 implements View.OnClickListener {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ ChatMessageCell f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda8(ChatActivity.ChatMessageCellDelegate r1, AtomicReference r2, ChatMessageCell r3, float r4, float r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$qb6VMcbXHzEoC1OK2eElIOsYuuw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
