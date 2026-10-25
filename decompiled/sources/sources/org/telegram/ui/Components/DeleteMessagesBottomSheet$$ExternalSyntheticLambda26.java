package org.telegram.ui.Components;

import java.util.function.Predicate;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class DeleteMessagesBottomSheet$$ExternalSyntheticLambda26 implements Predicate {
    public final /* synthetic */ TLRPC.InputPeer f$0;

    public /* synthetic */ DeleteMessagesBottomSheet$$ExternalSyntheticLambda26(TLRPC.InputPeer r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return DeleteMessagesBottomSheet.$r8$lambda$Z6edFMb0U5mfK8D1txzIg4DltdQ(this.f$0, (MessageObject) r1);
    }
}
