package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.EventInternal;

/* loaded from: classes4.dex */
public interface TransportBackend {
    EventInternal decorate(EventInternal r1);

    BackendResponse send(BackendRequest r1);
}
