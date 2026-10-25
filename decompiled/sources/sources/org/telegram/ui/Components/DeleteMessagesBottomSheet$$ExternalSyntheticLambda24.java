package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.DeleteMessagesBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class DeleteMessagesBottomSheet$$ExternalSyntheticLambda24 implements Utilities.IndexedConsumer {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ DeleteMessagesBottomSheet.Action f$1;

    public /* synthetic */ DeleteMessagesBottomSheet$$ExternalSyntheticLambda24(ArrayList r1, DeleteMessagesBottomSheet.Action r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.IndexedConsumer
    public final void accept(Object r2, int r3) {
        DeleteMessagesBottomSheet.$r8$lambda$3aD_GFuyKFLa8QoUV4uVSjGfOx8(this.f$0, this.f$1, (TLObject) r2, r3);
    }
}
