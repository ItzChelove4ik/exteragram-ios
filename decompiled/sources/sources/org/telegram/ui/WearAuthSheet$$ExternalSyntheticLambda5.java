package org.telegram.ui;

import com.google.android.gms.tasks.OnSuccessListener;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.WearAuthSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class WearAuthSheet$$ExternalSyntheticLambda5 implements OnSuccessListener {
    public final /* synthetic */ WearAuthSheet.AuthSession f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ int[] f$2;

    public /* synthetic */ WearAuthSheet$$ExternalSyntheticLambda5(WearAuthSheet.AuthSession r1, ButtonWithCounterView r2, int[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object r3) {
        WearAuthSheet.$r8$lambda$sT1lps5_P_spk0l9a6EloZJgcVg(this.f$0, this.f$1, this.f$2, (Integer) r3);
    }
}
