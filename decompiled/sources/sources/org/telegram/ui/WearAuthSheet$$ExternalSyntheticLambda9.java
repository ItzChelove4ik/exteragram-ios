package org.telegram.ui;

import com.google.android.gms.tasks.OnSuccessListener;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.WearAuthSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class WearAuthSheet$$ExternalSyntheticLambda9 implements OnSuccessListener {
    public final /* synthetic */ WearAuthSheet.AuthSession f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;

    public /* synthetic */ WearAuthSheet$$ExternalSyntheticLambda9(WearAuthSheet.AuthSession r1, ButtonWithCounterView r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r2) {
        WearAuthSheet.$r8$lambda$OcJG_vbyI8TW993hMKLFWF8qEFU(this.f$0, this.f$1, (Integer) r2);
    }
}
