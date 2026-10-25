package androidx.car.app.model;

import androidx.car.app.model.constraints.TabContentsConstraints;
import java.util.Objects;

/* loaded from: classes3.dex */
public class TabContents {
    public static final String CONTENT_ID = "TAB_CONTENTS_CONTENT_ID";
    private final Template mTemplate;

    public static final class Builder {
        Template mTemplate;

        public Builder(Template r3) {
            TabContentsConstraints r0 = TabContentsConstraints.API_7;
            Objects.requireNonNull(r3);
            r0.validateOrThrow(r3);
            this.mTemplate = r3;
        }

        public TabContents build() {
            return new TabContents(this);
        }
    }

    private TabContents() {
        this.mTemplate = null;
    }

    public TabContents(Builder r1) {
        this.mTemplate = r1.mTemplate;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof TabContents) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mTemplate, ((TabContents) r2).mTemplate);
    }

    public String getContentId() {
        return CONTENT_ID;
    }

    public Template getTemplate() {
        Template r0 = this.mTemplate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTemplate});
    }

    public String toString() {
        return "[template: " + this.mTemplate + "]";
    }
}
