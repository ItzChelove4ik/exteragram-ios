package org.telegram.ui;

import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.MessagesStorage;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda84 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ HashSet f$3;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda84(DialogsActivity r1, ArrayList r2, int r3, HashSet r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r4) {
        DialogsActivity.$r8$lambda$i2jlLRIZdMRp03MEE3S2o4oaxiw(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
