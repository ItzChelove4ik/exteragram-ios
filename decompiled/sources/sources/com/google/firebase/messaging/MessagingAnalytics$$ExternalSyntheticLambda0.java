package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagingAnalytics$$ExternalSyntheticLambda0 implements Transformer {
    public /* synthetic */ MessagingAnalytics$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object r1) {
        return ((MessagingClientEventExtension) r1).toByteArray();
    }
}
