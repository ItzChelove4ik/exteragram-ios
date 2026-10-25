package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.Set;

/* loaded from: classes.dex */
final class TransportFactoryImpl implements TransportFactory {
    private final Set<Encoding> supportedPayloadEncodings;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    public TransportFactoryImpl(Set<Encoding> r1, TransportContext r2, TransportInternal r3) {
        this.supportedPayloadEncodings = r1;
        this.transportContext = r2;
        this.transportInternal = r3;
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String r7, Class<T> r8, Encoding r9, Transformer<T, byte[]> r10) {
        if (this.supportedPayloadEncodings.contains(r9) == true) goto L5;
        FieldSet$$ExternalSyntheticBUOutline0.m("%s is not supported byt this factory. Supported encodings are: %s.", new Object[]{r9, this.supportedPayloadEncodings});
        return null;
    L5:
        return new TransportImpl(this.transportContext, r7, r9, r10, this.transportInternal);
    }
}
