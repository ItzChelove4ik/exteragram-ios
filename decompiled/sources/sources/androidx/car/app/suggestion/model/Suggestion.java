package androidx.car.app.suggestion.model;

import android.app.PendingIntent;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class Suggestion {
    private final PendingIntent mAction;
    private final CarIcon mIcon;
    private final String mIdentifier;
    private final CarText mSubtitle;
    private final CarText mTitle;

    public static final class Builder {
    }

    private Suggestion() {
        this.mIdentifier = _UrlKt.FRAGMENT_ENCODE_SET;
        this.mTitle = CarText.create(_UrlKt.FRAGMENT_ENCODE_SET);
        this.mSubtitle = null;
        this.mIcon = null;
        this.mAction = null;
    }

    public Suggestion(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Suggestion) == true) goto L8;
        return false;
    L8:
        Suggestion r52 = (Suggestion) r5;
        if (Objects.equals(this.mIdentifier, r52.mIdentifier) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L19;
        if (Objects.equals(this.mSubtitle, r52.mSubtitle) == false) goto L19;
        if (Objects.equals(this.mAction, r52.mAction) == false) goto L19;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L19;
        return true;
    }

    public PendingIntent getAction() {
        return this.mAction;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public CarText getSubtitle() {
        return this.mSubtitle;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mIdentifier, this.mTitle, this.mSubtitle, this.mIcon, this.mAction});
    }

    public String toString() {
        return "[id: " + this.mIdentifier + ", title: " + CarText.toShortString(this.mTitle) + ", subtitle: " + CarText.toShortString(this.mSubtitle) + ", pendingIntent: " + this.mAction + ", icon: " + this.mIcon + "]";
    }
}
