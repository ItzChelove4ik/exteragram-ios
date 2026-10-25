package io.noties.markwon.html.jsoup.nodes;

import io.noties.markwon.html.jsoup.helper.Validate;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class Attribute implements Map.Entry<String, String>, Cloneable {
    private String key;
    Attributes parent;
    private String val;

    public Attribute(String r2, String r3, Attributes r4) {
        Validate.notNull(r2);
        this.key = r2.trim();
        Validate.notEmpty(r2);
        this.val = r3;
        this.parent = r4;
    }

    public Attribute clone() {
        return (Attribute) super.clone();
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m2012clone() {
        return clone();
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L23:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L23;
        Attribute r52 = (Attribute) r5;
        String r2 = this.key;
        String r3 = r52.key;
        if (r2 != null) goto L13;
        if (r3 == null) goto L17;
    L16:
        return false;
    L17:
        String r4 = this.val;
        String r53 = r52.val;
        if (r4 != null) goto L20;
        if (r53 != null) goto L23;
        return true;
    L20:
        return r4.equals(r53);
    L13:
        if (r2.equals(r3) == true) goto L17;
        goto L17
    }

    @Override // java.util.Map.Entry
    public /* bridge */ /* synthetic */ String getKey() {
        return getKey2();
    }

    @Override // java.util.Map.Entry
    /* renamed from: getKey, reason: avoid collision after fix types in other method */
    public String getKey2() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public /* bridge */ /* synthetic */ String getValue() {
        return getValue2();
    }

    @Override // java.util.Map.Entry
    /* renamed from: getValue, reason: avoid collision after fix types in other method */
    public String getValue2() {
        return this.val;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        String r0 = this.key;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r03 = r02 * 31;
        String r2 = this.val;
        if (r2 == null) goto L10;
        r1 = r2.hashCode();
    L10:
        return r03 + r1;
    L5:
        r02 = 0;
        goto L6
    }

    @Override // java.util.Map.Entry
    public /* bridge */ /* synthetic */ String setValue(String r1) {
        return setValue2(r1);
    }

    /* renamed from: setValue, reason: avoid collision after fix types in other method */
    public String setValue2(String r4) {
        String r0 = this.parent.get(this.key);
        Attributes r1 = this.parent;
        if (r1 == null) goto L7;
        int r12 = r1.indexOfKey(this.key);
        if (r12 == (-1)) goto L7;
        this.parent.vals[r12] = r4;
    L7:
        this.val = r4;
        return r0;
    }
}
