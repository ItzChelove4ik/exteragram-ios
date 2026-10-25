package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
final class AutoValue_Config_Option<T> extends Config.Option<T> {
    private final String id;
    private final Object token;
    private final Class<T> valueClass;

    public AutoValue_Config_Option(String r2, Class<T> r3, Object r4) {
        if (r2 == null) goto L10;
        this.id = r2;
        if (r3 == null) goto L8;
        this.valueClass = r3;
        this.token = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null valueClass");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null id");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof Config.Option) == false) goto L19;
        Config.Option r52 = (Config.Option) r5;
        if (this.id.equals(r52.getId()) == false) goto L19;
        if (this.valueClass.equals(r52.getValueClass()) == false) goto L19;
        Object r4 = this.token;
        if (r4 != null) goto L17;
        if (r52.getToken() != null) goto L19;
    L18:
        return true;
    L17:
        if (r4.equals(r52.getToken()) == true) goto L18;
    L19:
        return false;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public String getId() {
        return this.id;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public Object getToken() {
        return this.token;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public Class<T> getValueClass() {
        return this.valueClass;
    }

    public int hashCode() {
        int r0 = (((this.id.hashCode() ^ 1000003) * 1000003) ^ this.valueClass.hashCode()) * 1000003;
        Object r3 = this.token;
        if (r3 != null) goto L5;
        int r32 = 0;
    L7:
        return r32 ^ r0;
    L5:
        r32 = r3.hashCode();
        goto L7
    }

    public String toString() {
        return "Option{id=" + this.id + ", valueClass=" + this.valueClass + ", token=" + this.token + "}";
    }
}
