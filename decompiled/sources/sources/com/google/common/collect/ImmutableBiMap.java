package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements Map {

    public static final class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        public Builder() {
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> build() {
            return buildOrThrow();
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap build() {
            return build();
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @Deprecated
        public ImmutableBiMap<K, V> buildKeepingLast() {
            throw new UnsupportedOperationException("Not supported for bimaps");
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @Deprecated
        public /* bridge */ /* synthetic */ ImmutableMap buildKeepingLast() {
            return buildKeepingLast();
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableBiMap<K, V> buildOrThrow() {
            int r0 = this.size;
            if (r0 != 0) goto L7;
            return ImmutableBiMap.of();
        L7:
            if (this.valueComparator != null) goto L9;
        L12:
            this.entriesUsed = true;
            return new RegularImmutableBiMap(this.alternatingKeysAndValues, this.size);
        L9:
            if (this.entriesUsed == false) goto L11;
            this.alternatingKeysAndValues = Arrays.copyOf(this.alternatingKeysAndValues, r0 * 2);
        L11:
            ImmutableMap.Builder.sortEntries(this.alternatingKeysAndValues, this.size, this.valueComparator);
            goto L12
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap buildOrThrow() {
            return buildOrThrow();
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(K r1, V r2) {
            super.put(r1, r2);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> r1) {
            super.put(r1);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap.Builder put(Object r1, Object r2) {
            return put(r1, r2);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap.Builder put(Map.Entry r1) {
            return put(r1);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> r1) {
            super.putAll(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Map<? extends K, ? extends V> r1) {
            super.putAll(r1);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap.Builder putAll(Iterable r1) {
            return putAll(r1);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public /* bridge */ /* synthetic */ ImmutableMap.Builder putAll(Map r1) {
            return putAll(r1);
        }
    }

    public ImmutableBiMap() {
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder();
    }

    public static <K, V> ImmutableBiMap<K, V> of() {
        return RegularImmutableBiMap.EMPTY;
    }

    public abstract ImmutableBiMap<V, K> inverse();

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public /* bridge */ /* synthetic */ ImmutableCollection values() {
        return values();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public final ImmutableSet<V> values() {
        return inverse().keySet();
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Collection values() {
        return values();
    }
}
