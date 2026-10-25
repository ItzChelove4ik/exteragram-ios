package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class TagEditCell$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ ButtonWithCounterView f$0;
    public final /* synthetic */ TagEditCell f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ String[] f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ BottomSheet f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ Theme.ResourcesProvider f$9;

    public /* synthetic */ TagEditCell$$ExternalSyntheticLambda1(ButtonWithCounterView r1, TagEditCell r2, MessagesController r3, long r4, TLRPC.User r6, String[] r7, int r8, BottomSheet r9, boolean r10, Theme.ResourcesProvider r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r13) {
        TagEditCell.$r8$lambda$fr1MKt7PjmshMn150UCDwPHMoK8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r13);
    }
}
