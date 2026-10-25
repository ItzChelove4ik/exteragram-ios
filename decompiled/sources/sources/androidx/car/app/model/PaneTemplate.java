package androidx.car.app.model;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class PaneTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final Action mHeaderAction;
    private final Pane mPane;
    private final CarText mTitle;

    public static final class Builder {
    }

    private PaneTemplate() {
        this.mTitle = null;
        this.mPane = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
    }

    public PaneTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof PaneTemplate) == true) goto L8;
        return false;
    L8:
        PaneTemplate r52 = (PaneTemplate) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equals(this.mPane, r52.mPane) == false) goto L17;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L17;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L17;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public Pane getPane() {
        Pane r0 = this.mPane;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTitle, this.mPane, this.mHeaderAction, this.mActionStrip});
    }

    public String toString() {
        return "PaneTemplate";
    }
}
