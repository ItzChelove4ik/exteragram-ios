package io.noties.markwon.html.jsoup.nodes;

import io.noties.markwon.html.jsoup.helper.Validate;
import java.util.Arrays;
import java.util.Iterator;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class Attributes implements Iterable<Attribute>, Cloneable {
    private static final String[] Empty = null;
    String[] keys;
    private int size;
    String[] vals;

    /* renamed from: io.noties.markwon.html.jsoup.nodes.Attributes$1, reason: invalid class name */
    public class AnonymousClass1 implements Iterator<Attribute> {
        int i;
        final /* synthetic */ Attributes this$0;

        public AnonymousClass1(Attributes r1) {
            this.this$0 = r1;
            this.i = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.i >= Attributes.access$000(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Attribute next() {
            Attributes r0 = this.this$0;
            String[] r1 = r0.vals;
            int r2 = this.i;
            String r12 = r1[r2];
            String r22 = r0.keys[r2];
            if (r12 != null) goto L5;
            r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            Attribute r3 = new Attribute(r22, r12, r0);
            this.i++;
            return r3;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Attribute next() {
            return next();
        }

        @Override // java.util.Iterator
        public void remove() {
            Attributes r0 = this.this$0;
            int r1 = this.i - 1;
            this.i = r1;
            Attributes.access$100(r0, r1);
        }
    }

    static {
        Empty = new String[0];
    }

    public Attributes() {
        this.size = 0;
        String[] r0 = Empty;
        this.keys = r0;
        this.vals = r0;
    }

    public static /* synthetic */ int access$000(Attributes r0) {
        return r0.size;
    }

    public static /* synthetic */ void access$100(Attributes r0, int r1) {
        r0.remove(r1);
    }

    private void add(String r3, String r4) {
        checkCapacity(this.size + 1);
        String[] r0 = this.keys;
        int r1 = this.size;
        r0[r1] = r3;
        this.vals[r1] = r4;
        this.size = r1 + 1;
    }

    private void checkCapacity(int r4) {
        if (r4 < this.size) goto L5;
        boolean r0 = true;
    L6:
        Validate.isTrue(r0);
        String[] r02 = this.keys;
        int r1 = r02.length;
        if (r1 < r4) goto L9;
        return;
    L9:
        int r2 = 4;
        if (r1 < 4) goto L12;
        r2 = this.size * 2;
    L12:
        if (r4 > r2) goto L15;
        r4 = r2;
    L15:
        this.keys = copyOf(r02, r4);
        this.vals = copyOf(this.vals, r4);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static String checkNotNull(String r0) {
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0;
    }

    private static String[] copyOf(String[] r2, int r3) {
        String[] r0 = new String[r3];
        System.arraycopy(r2, 0, r0, 0, Math.min(r2.length, r3));
        return r0;
    }

    private void remove(int r5) {
        if (r5 < this.size) goto L5;
        boolean r0 = true;
    L6:
        Validate.isFalse(r0);
        int r02 = (this.size - r5) - 1;
        if (r02 <= 0) goto L9;
        String[] r2 = this.keys;
        int r3 = r5 + 1;
        System.arraycopy(r2, r3, r2, r5, r02);
        String[] r22 = this.vals;
        System.arraycopy(r22, r3, r22, r5, r02);
    L9:
        int r52 = this.size - 1;
        this.size = r52;
        this.keys[r52] = null;
        this.vals[r52] = null;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public Attributes clone() {
        Attributes r0 = (Attributes) super.clone();     // Catch: CloneNotSupportedException -> L5
        r0.size = this.size;
        this.keys = copyOf(this.keys, this.size);
        this.vals = copyOf(this.vals, this.size);
        return r0;
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m2013clone() {
        return clone();
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if (r4 != null) goto L8;
    L18:
        return false;
    L8:
        if (getClass() != r4.getClass()) goto L18;
        Attributes r42 = (Attributes) r4;
        if (this.size == r42.size) goto L14;
        return false;
    L14:
        if (Arrays.equals(this.keys, r42.keys) == true) goto L17;
        return false;
    L17:
        return Arrays.equals(this.vals, r42.vals);
    }

    public String get(String r2) {
        int r22 = indexOfKey(r2);
        if (r22 != (-1)) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return checkNotNull(this.vals[r22]);
    }

    public int hashCode() {
        return (((this.size * 31) + Arrays.hashCode(this.keys)) * 31) + Arrays.hashCode(this.vals);
    }

    public int indexOfKey(String r3) {
        Validate.notNull(r3);
        int r0 = 0;
    L4:
        if (r0 >= this.size) goto L9;
        if (r3.equals(this.keys[r0]) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return new AnonymousClass1(this);
    }

    public Attributes put(String r3, String r4) {
        int r0 = indexOfKey(r3);
        if (r0 == (-1)) goto L6;
        this.vals[r0] = r4;
        return this;
    L6:
        add(r3, r4);
        return this;
    }

    public int size() {
        return this.size;
    }
}
