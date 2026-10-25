package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class TemplateInfo {
    private final Class<? extends Template> mTemplateClass;
    private final String mTemplateId;

    private TemplateInfo() {
        this.mTemplateClass = null;
        this.mTemplateId = null;
    }

    public TemplateInfo(Class<? extends Template> r1, String r2) {
        this.mTemplateClass = r1;
        this.mTemplateId = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof TemplateInfo) == true) goto L8;
        return false;
    L8:
        TemplateInfo r52 = (TemplateInfo) r5;
        if (Objects.equals(this.mTemplateClass, r52.mTemplateClass) == true) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.mTemplateId, r52.mTemplateId) == false) goto L13;
        return true;
    }

    public Class<? extends Template> getTemplateClass() {
        Class<? extends Template> r0 = this.mTemplateClass;
        Objects.requireNonNull(r0);
        return r0;
    }

    public String getTemplateId() {
        String r0 = this.mTemplateId;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTemplateClass, this.mTemplateId});
    }
}
