package org.telegram.ui.Components;

import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda1 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ CreateRtmpStreamBottomSheet f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ TL_phone.getGroupCallStreamRtmpUrl f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ CreateRtmpStreamBottomSheet$$ExternalSyntheticLambda1(CreateRtmpStreamBottomSheet r1, ButtonWithCounterView r2, TL_phone.getGroupCallStreamRtmpUrl r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        CreateRtmpStreamBottomSheet.$r8$lambda$QwlepziNYSoq2zy6Y3LWL5xAfn8(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
