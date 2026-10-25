package com.google.android.datatransport;

/* loaded from: classes.dex */
public interface TransportFactory {
    <T> Transport<T> getTransport(String r1, Class<T> r2, Encoding r3, Transformer<T, byte[]> r4);
}
