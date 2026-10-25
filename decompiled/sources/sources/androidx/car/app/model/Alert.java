package androidx.car.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class Alert {
    private static final int MAX_ACTION_COUNT = 2;
    private final List<Action> mActions;
    private final AlertCallbackDelegate mCallbackDelegate;
    private final long mDuration;
    private final CarIcon mIcon;
    private final int mId;
    private final CarText mSubtitle;
    private final CarText mTitle;

    public static final class Builder {
    }

    private Alert() {
        this.mId = 0;
        this.mTitle = CarText.create(_UrlKt.FRAGMENT_ENCODE_SET);
        this.mSubtitle = null;
        this.mIcon = null;
        this.mActions = new ArrayList();
        this.mDuration = 0;
        this.mCallbackDelegate = null;
    }

    public Alert(Builder r1) {
        throw null;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof Alert) == true) goto L9;
        return false;
    L9:
        if (this.mId != ((Alert) r4).mId) goto L11;
        return true;
    L11:
        return false;
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public AlertCallbackDelegate getCallbackDelegate() {
        return this.mCallbackDelegate;
    }

    public long getDurationMillis() {
        return this.mDuration;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public int getId() {
        return this.mId;
    }

    public CarText getSubtitle() {
        return this.mSubtitle;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mId)});
    }

    public String toString() {
        return "[id: " + this.mId + ", title: " + this.mTitle + ", icon: " + this.mIcon + "]";
    }
}
