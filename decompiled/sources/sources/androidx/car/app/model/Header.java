package androidx.car.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Header {
    private final List<Action> mEndHeaderActions;
    private final Action mStartHeaderAction;
    private final CarText mTitle;

    public static final class Builder {
    }

    private Header() {
        this.mTitle = null;
        this.mStartHeaderAction = null;
        this.mEndHeaderActions = new ArrayList();
    }

    public Header(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Header) == true) goto L8;
        return false;
    L8:
        Header r52 = (Header) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mEndHeaderActions, r52.mEndHeaderActions) == false) goto L15;
        if (Objects.equals(this.mStartHeaderAction, r52.mStartHeaderAction) == false) goto L15;
        return true;
    }

    public List<Action> getEndHeaderActions() {
        return this.mEndHeaderActions;
    }

    public Action getStartHeaderAction() {
        return this.mStartHeaderAction;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTitle, this.mEndHeaderActions, this.mStartHeaderAction});
    }

    public String toString() {
        return "Header: " + this.mTitle;
    }
}
