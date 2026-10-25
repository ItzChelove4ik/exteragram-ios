package org.telegram.ui;

import android.net.Uri;
import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda45 implements MessagesStorage.LongCallback {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ DialogsActivity f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ Uri f$5;
    public final /* synthetic */ AlertDialog f$6;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda45(LaunchActivity r1, int r2, DialogsActivity r3, boolean r4, ArrayList r5, Uri r6, AlertDialog r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r10) {
        LaunchActivity.$r8$lambda$jGdZXYDSkfOgLzekuP2aXrcvSU0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10);
    }
}
