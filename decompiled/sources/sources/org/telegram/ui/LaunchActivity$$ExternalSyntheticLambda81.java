package org.telegram.ui;

import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda81 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda81(AtomicBoolean r1, int r2, int[] r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r4) {
        LaunchActivity.$r8$lambda$JNvtOkdfXaE0xNk9AI0pNahyj3M(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
