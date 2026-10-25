package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
abstract class zzby<FieldDescriptorType> {
    public abstract /* synthetic */ Object clone();

    public abstract Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator();

    public abstract boolean equals(Object r1);

    public abstract int hashCode();

    public abstract boolean isEmpty();

    public abstract boolean isImmutable();

    public abstract boolean isInitialized();

    public abstract Iterator<Map.Entry<FieldDescriptorType, Object>> iterator();

    public abstract void zza(zzby<FieldDescriptorType> r1);

    public abstract int zzas();

    public abstract int zzat();

    public abstract void zzv();
}
