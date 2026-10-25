package com.google.common.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class Splitter {
    private final int limit;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final CharMatcher trimmer;

    /* renamed from: com.google.common.base.Splitter$1, reason: invalid class name */
    public class AnonymousClass1 extends SplittingIterator {
        final /* synthetic */ CharMatcher val$separatorMatcher;

        public AnonymousClass1(Splitter r1, CharSequence r2, CharMatcher r3) {
            this.val$separatorMatcher = r3;
            super(r1, r2);
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorEnd(int r1) {
            return r1 + 1;
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorStart(int r2) {
            return this.val$separatorMatcher.indexIn(this.toSplit, r2);
        }
    }

    /* renamed from: com.google.common.base.Splitter$2, reason: invalid class name */
    public class AnonymousClass2 extends SplittingIterator {
        final /* synthetic */ String val$separator;

        public AnonymousClass2(Splitter r1, CharSequence r2, String r3) {
            this.val$separator = r3;
            super(r1, r2);
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorEnd(int r1) {
            return r1 + this.val$separator.length();
        }

        @Override // com.google.common.base.Splitter.SplittingIterator
        public int separatorStart(int r6) {
            int r0 = this.val$separator.length();
            int r1 = this.toSplit.length() - r0;
        L3:
            if (r6 > r1) goto L11;
            int r2 = 0;
        L5:
            if (r2 >= r0) goto L10;
            if (this.toSplit.charAt(r2 + r6) != this.val$separator.charAt(r2)) goto L8;
            r2 = r2 + 1;
            goto L5
        L8:
            r6 = r6 + 1;
            goto L3
        L10:
            return r6;
        L11:
            return -1;
        }
    }

    /* renamed from: com.google.common.base.Splitter$5, reason: invalid class name */
    public class AnonymousClass5 implements Iterable<String> {
        final /* synthetic */ Splitter this$0;
        final /* synthetic */ CharSequence val$sequence;

        public AnonymousClass5(Splitter r1, CharSequence r2) {
            this.val$sequence = r2;
            this.this$0 = r1;
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return Splitter.access$000(this.this$0, this.val$sequence);
        }

        public String toString() {
            Joiner r0 = Joiner.on(", ");
            StringBuilder r1 = new StringBuilder();
            r1.append('[');
            StringBuilder r3 = r0.appendTo(r1, this);
            r3.append(']');
            return r3.toString();
        }
    }

    public static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        public SplittingIterator(Splitter r2, CharSequence r3) {
            this.offset = 0;
            this.trimmer = Splitter.access$200(r2);
            this.omitEmptyStrings = Splitter.access$300(r2);
            this.limit = Splitter.access$400(r2);
            this.toSplit = r3;
        }

        @Override // com.google.common.base.AbstractIterator
        public /* bridge */ /* synthetic */ String computeNext() {
            return computeNext2();
        }

        @Override // com.google.common.base.AbstractIterator
        /* renamed from: computeNext, reason: avoid collision after fix types in other method */
        public String computeNext2() {
            int r0 = this.offset;
        L3:
            int r1 = this.offset;
            if (r1 == (-1)) goto L37;
            int r12 = separatorStart(r1);
            if (r12 != (-1)) goto L8;
            r12 = this.toSplit.length();
            this.offset = -1;
        L9:
            int r3 = this.offset;
            if (r3 == r0) goto L11;
        L14:
            if (r0 >= r12) goto L18;
            if (this.trimmer.matches(this.toSplit.charAt(r0)) == false) goto L18;
            r0 = r0 + 1;
        L18:
            if (r12 <= r0) goto L23;
            if (this.trimmer.matches(this.toSplit.charAt(r12 - 1)) == false) goto L23;
            r12 = r12 - 1;
        L23:
            if (this.omitEmptyStrings == false) goto L26;
            if (r0 != r12) goto L26;
            r0 = this.offset;
        L26:
            int r32 = this.limit;
            if (r32 != 1) goto L33;
            r12 = this.toSplit.length();
            this.offset = -1;
        L29:
            if (r12 <= r0) goto L35;
            if (this.trimmer.matches(this.toSplit.charAt(r12 - 1)) == false) goto L35;
            r12 = r12 - 1;
        L35:
            return this.toSplit.subSequence(r0, r12).toString();
        L33:
            this.limit = r32 - 1;
            goto L35
        L11:
            int r33 = r3 + 1;
            this.offset = r33;
            if (r33 <= this.toSplit.length()) goto L3;
            this.offset = -1;
            goto L3
        L8:
            this.offset = separatorEnd(r12);
            goto L9
        L37:
            return endOfData();
        }

        public abstract int separatorEnd(int r1);

        public abstract int separatorStart(int r1);
    }

    public interface Strategy {
        Iterator<String> iterator(Splitter r1, CharSequence r2);
    }

    /* renamed from: $r8$lambda$CK-_F_WMWUv6g4fX5TCs4Q17sbA, reason: not valid java name */
    public static /* synthetic */ Iterator m1914$r8$lambda$CK_F_WMWUv6g4fX5TCs4Q17sbA(CharMatcher r1, Splitter r2, CharSequence r3) {
        return new AnonymousClass1(r2, r3, r1);
    }

    public static /* synthetic */ Iterator $r8$lambda$e00L1vhkadM2rBAapSaIfxSYJ7k(String r1, Splitter r2, CharSequence r3) {
        return new AnonymousClass2(r2, r3, r1);
    }

    private Splitter(Strategy r4) {
        this(r4, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    private Splitter(Strategy r1, boolean r2, CharMatcher r3, int r4) {
        this.strategy = r1;
        this.omitEmptyStrings = r2;
        this.trimmer = r3;
        this.limit = r4;
    }

    public static /* synthetic */ Iterator access$000(Splitter r0, CharSequence r1) {
        return r0.splittingIterator(r1);
    }

    public static /* synthetic */ CharMatcher access$200(Splitter r0) {
        return r0.trimmer;
    }

    public static /* synthetic */ boolean access$300(Splitter r0) {
        return r0.omitEmptyStrings;
    }

    public static /* synthetic */ int access$400(Splitter r0) {
        return r0.limit;
    }

    public static Splitter on(char r0) {
        return on(CharMatcher.is(r0));
    }

    public static Splitter on(CharMatcher r2) {
        Preconditions.checkNotNull(r2);
        return new Splitter(new Splitter$$ExternalSyntheticLambda1(r2));
    }

    public static Splitter on(String r3) {
        Preconditions.checkArgument(!r3.isEmpty(), "The separator may not be the empty string.");
        if (r3.length() != 1) goto L7;
        return on(r3.charAt(0));
    L7:
        return new Splitter(new Splitter$$ExternalSyntheticLambda0(r3));
    }

    private Iterator<String> splittingIterator(CharSequence r2) {
        return this.strategy.iterator(this, r2);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(CharSequence r2) {
        Preconditions.checkNotNull(r2);
        return new AnonymousClass5(this, r2);
    }

    public List<String> splitToList(CharSequence r2) {
        Preconditions.checkNotNull(r2);
        Iterator<String> r1 = splittingIterator(r2);
        ArrayList r22 = new ArrayList();
    L4:
        if (r1.hasNext() == false) goto L7;
        r22.add(r1.next());
        goto L4
    L7:
        return Collections.unmodifiableList(r22);
    }
}
