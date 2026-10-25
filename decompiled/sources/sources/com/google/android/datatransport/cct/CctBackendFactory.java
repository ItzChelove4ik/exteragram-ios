package com.google.android.datatransport.cct;

import androidx.annotation.Keep;
import com.google.android.datatransport.runtime.backends.BackendFactory;
import com.google.android.datatransport.runtime.backends.CreationContext;
import com.google.android.datatransport.runtime.backends.TransportBackend;

@Keep
/* loaded from: classes4.dex */
public class CctBackendFactory implements BackendFactory {
    public CctBackendFactory() {
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendFactory
    public TransportBackend create(CreationContext r3) {
        return new CctTransportBackend(r3.getApplicationContext(), r3.getWallClock(), r3.getMonotonicClock());
    }
}
