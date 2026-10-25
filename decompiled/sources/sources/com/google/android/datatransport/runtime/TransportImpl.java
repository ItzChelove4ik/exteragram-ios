package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

/* loaded from: classes.dex */
final class TransportImpl<T> implements Transport<T> {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer<T, byte[]> transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    public static /* synthetic */ void $r8$lambda$bZYIR2AWMsK3OiMdU9QsskqwQec(Exception r0) {
    }

    public TransportImpl(TransportContext r1, String r2, Encoding r3, Transformer<T, byte[]> r4, TransportInternal r5) {
        this.transportContext = r1;
        this.name = r2;
        this.payloadEncoding = r3;
        this.transformer = r4;
        this.transportInternal = r5;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override // com.google.android.datatransport.Transport
    public void schedule(Event<T> r4, TransportScheduleCallback r5) {
        this.transportInternal.send(SendRequest.builder().setTransportContext(this.transportContext).setEvent(r4).setTransportName(this.name).setTransformer(this.transformer).setEncoding(this.payloadEncoding).build(), r5);
    }

    @Override // com.google.android.datatransport.Transport
    public void send(Event<T> r2) {
        schedule(r2, new TransportImpl$$ExternalSyntheticLambda0());
    }
}
