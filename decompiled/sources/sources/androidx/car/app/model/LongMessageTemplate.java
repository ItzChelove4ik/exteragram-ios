package androidx.car.app.model;

import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class LongMessageTemplate implements Template {
    private final List<Action> mActionList;
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final CarText mMessage;
    private final CarText mTitle;

    public static final class Builder {
    }

    private LongMessageTemplate() {
        this.mTitle = null;
        this.mMessage = null;
        this.mActionStrip = null;
        this.mHeaderAction = null;
        this.mActionList = Collections.EMPTY_LIST;
    }

    public LongMessageTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof LongMessageTemplate) == true) goto L8;
        return false;
    L8:
        LongMessageTemplate r52 = (LongMessageTemplate) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.mMessage, r52.mMessage) == false) goto L19;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L19;
        if (Objects.equals(this.mActionList, r52.mActionList) == false) goto L19;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L19;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public CarText getMessage() {
        CarText r0 = this.mMessage;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTitle, this.mMessage, this.mHeaderAction, this.mActionList, this.mActionStrip});
    }

    public String toString() {
        return "LongMessageTemplate";
    }
}
