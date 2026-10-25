package androidx.datastore.preferences.protobuf;

/* loaded from: classes.dex */
interface Schema<T> {
    boolean equals(T r1, T r2);

    int getSerializedSize(T r1);

    int hashCode(T r1);

    boolean isInitialized(T r1);

    void makeImmutable(T r1);

    void mergeFrom(T r1, Reader r2, ExtensionRegistryLite r3);

    void mergeFrom(T r1, T r2);

    T newInstance();

    void writeTo(T r1, Writer r2);
}
