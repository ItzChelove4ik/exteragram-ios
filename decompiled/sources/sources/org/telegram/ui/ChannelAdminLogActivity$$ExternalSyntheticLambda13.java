package org.telegram.ui;

import java.util.function.Function;
import org.telegram.messenger.MessageObject;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelAdminLogActivity$$ExternalSyntheticLambda13 implements Function {
    public /* synthetic */ ChannelAdminLogActivity$$ExternalSyntheticLambda13() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object r1) {
        return Long.valueOf(((MessageObject) r1).getFromChatId());
    }
}
