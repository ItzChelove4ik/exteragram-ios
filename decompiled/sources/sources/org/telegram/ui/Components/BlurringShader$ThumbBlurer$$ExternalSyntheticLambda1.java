package org.telegram.ui.Components;

import android.graphics.Bitmap;
import org.telegram.ui.Components.BlurringShader;

/* loaded from: classes7.dex */
public final /* synthetic */ class BlurringShader$ThumbBlurer$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ BlurringShader.ThumbBlurer f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Bitmap f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ Bitmap f$4;

    public /* synthetic */ BlurringShader$ThumbBlurer$$ExternalSyntheticLambda1(BlurringShader.ThumbBlurer r1, String r2, Bitmap r3, boolean r4, Bitmap r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BlurringShader.ThumbBlurer.$r8$lambda$OrHVDsAZ8dT1x1nPFZGhWxsDW9Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
