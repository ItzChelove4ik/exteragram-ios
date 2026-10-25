package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.navigation.model.NavigationTemplate;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class MessageInfo implements NavigationTemplate.NavigationInfo {
    private final CarIcon mImage;
    private final CarText mText;
    private final CarText mTitle;

    public static final class Builder {
    }

    private MessageInfo() {
        this.mTitle = null;
        this.mText = null;
        this.mImage = null;
    }

    public MessageInfo(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MessageInfo) == true) goto L8;
        return false;
    L8:
        MessageInfo r52 = (MessageInfo) r5;
        if (Objects.equals(this.mTitle, r52.mTitle) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.mText, r52.mText) == false) goto L15;
        if (Objects.equals(this.mImage, r52.mImage) == false) goto L15;
        return true;
    }

    public CarIcon getImage() {
        return this.mImage;
    }

    public CarText getText() {
        return this.mText;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mTitle, this.mText, this.mImage});
    }

    public String toString() {
        return "MessageInfo";
    }
}
