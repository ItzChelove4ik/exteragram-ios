package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
public final /* synthetic */ class EnhancedIntentService$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ EnhancedIntentService f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ EnhancedIntentService$$ExternalSyntheticLambda0(EnhancedIntentService r1, Intent r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r2) {
        EnhancedIntentService.$r8$lambda$WY92l0hxChBULatAfpI9V2iAiEU(this.f$0, this.f$1, r2);
    }
}
