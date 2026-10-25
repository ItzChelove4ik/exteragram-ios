package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public abstract class MultimapBuilder<K0, V0> {

    /* renamed from: com.google.common.collect.MultimapBuilder$1, reason: invalid class name */
    public class AnonymousClass1 extends MultimapBuilderWithKeys<Object> {
        final /* synthetic */ int val$expectedKeys;

        public AnonymousClass1(int r1) {
            this.val$expectedKeys = r1;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K, V> Map<K, Collection<V>> createMap() {
            return Platform.newHashMapWithExpectedSize(this.val$expectedKeys);
        }
    }

    /* renamed from: com.google.common.collect.MultimapBuilder$3, reason: invalid class name */
    public class AnonymousClass3 extends MultimapBuilderWithKeys<K0> {
        final /* synthetic */ Comparator val$comparator;

        public AnonymousClass3(Comparator r1) {
            this.val$comparator = r1;
        }

        @Override // com.google.common.collect.MultimapBuilder.MultimapBuilderWithKeys
        public <K extends K0, V> Map<K, Collection<V>> createMap() {
            return new TreeMap(this.val$comparator);
        }
    }

    public static final class ArrayListSupplier<V> implements Supplier<List<V>>, Serializable {
        private final int expectedValuesPerKey;

        public ArrayListSupplier(int r2) {
            this.expectedValuesPerKey = CollectPreconditions.checkNonnegative(r2, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.Supplier
        public /* bridge */ /* synthetic */ Object get() {
            return get();
        }

        @Override // com.google.common.base.Supplier
        public List<V> get() {
            return new ArrayList(this.expectedValuesPerKey);
        }
    }

    public static abstract class ListMultimapBuilder<K0, V0> extends MultimapBuilder<K0, V0> {
        public ListMultimapBuilder() {
            super(null);
        }

        public abstract <K extends K0, V extends V0> ListMultimap<K, V> build();
    }

    public static abstract class MultimapBuilderWithKeys<K0> {

        /* renamed from: com.google.common.collect.MultimapBuilder$MultimapBuilderWithKeys$1, reason: invalid class name */
        public class AnonymousClass1 extends ListMultimapBuilder<K0, Object> {
            final /* synthetic */ MultimapBuilderWithKeys this$0;
            final /* synthetic */ int val$expectedValuesPerKey;

            public AnonymousClass1(MultimapBuilderWithKeys r1, int r2) {
                this.val$expectedValuesPerKey = r2;
                this.this$0 = r1;
            }

            @Override // com.google.common.collect.MultimapBuilder.ListMultimapBuilder
            public <K extends K0, V> ListMultimap<K, V> build() {
                return Multimaps.newListMultimap(this.this$0.createMap(), new ArrayListSupplier(this.val$expectedValuesPerKey));
            }
        }

        public MultimapBuilderWithKeys() {
        }

        public ListMultimapBuilder<K0, Object> arrayListValues() {
            return arrayListValues(2);
        }

        public ListMultimapBuilder<K0, Object> arrayListValues(int r2) {
            CollectPreconditions.checkNonnegative(r2, "expectedValuesPerKey");
            return new AnonymousClass1(this, r2);
        }

        public abstract <K extends K0, V> Map<K, Collection<V>> createMap();
    }

    private MultimapBuilder() {
    }

    public /* synthetic */ MultimapBuilder(AnonymousClass1 r1) {
        this();
    }

    public static MultimapBuilderWithKeys<Object> hashKeys() {
        return hashKeys(8);
    }

    public static MultimapBuilderWithKeys<Object> hashKeys(int r1) {
        CollectPreconditions.checkNonnegative(r1, "expectedKeys");
        return new AnonymousClass1(r1);
    }

    public static MultimapBuilderWithKeys<Comparable> treeKeys() {
        return treeKeys(Ordering.natural());
    }

    public static <K0> MultimapBuilderWithKeys<K0> treeKeys(Comparator<K0> r1) {
        Preconditions.checkNotNull(r1);
        return new AnonymousClass3(r1);
    }
}
