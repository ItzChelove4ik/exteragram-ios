package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda14 implements View.OnClickListener {
    public final /* synthetic */ CreateRtmpStreamBottomSheet f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ ButtonWithCounterView f$3;
    public final /* synthetic */ TL_phone.getGroupCallStreamRtmpUrl f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda14(CreateRtmpStreamBottomSheet r1, Context r2, Theme.ResourcesProvider r3, ButtonWithCounterView r4, TL_phone.getGroupCallStreamRtmpUrl r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r8) {
        CreateRtmpStreamBottomSheet.$r8$lambda$LM6PhvwV2Iicyw7wCG0Pz8FUDcw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
