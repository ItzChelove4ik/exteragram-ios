package org.telegram.ui.Components;

import java.util.function.Predicate;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public final /* synthetic */ class DeleteMessagesBottomSheet$$ExternalSyntheticLambda28 implements Predicate {
    public final /* synthetic */ TLObject f$0;

    public /* synthetic */ DeleteMessagesBottomSheet$$ExternalSyntheticLambda28(TLObject r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return DeleteMessagesBottomSheet.$r8$lambda$gw8LTTCkjGeKs402AxemaLkVg0A(this.f$0, (MessageObject) r1);
    }
}
