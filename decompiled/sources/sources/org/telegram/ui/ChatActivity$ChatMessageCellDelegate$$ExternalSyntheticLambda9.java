package org.telegram.ui;

import android.view.View;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda9 implements View.OnClickListener {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ AtomicReference f$2;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda9(ChatActivity.ChatMessageCellDelegate r1, ArrayList r2, AtomicReference r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$yDbVOnIowdhyaCmtR83y9ElHJVM(this.f$0, this.f$1, this.f$2, r3);
    }
}
