package androidx.car.app.model;

import android.util.Log;
import androidx.car.app.model.constraints.ActionsConstraints;
import androidx.car.app.model.constraints.CarTextConstraints;
import androidx.car.app.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class MessageTemplate implements Template {
    private final List<Action> mActionList;
    private final ActionStrip mActionStrip;
    private final CarText mDebugMessage;
    private final Action mHeaderAction;
    private final CarIcon mIcon;
    private final boolean mIsLoading;
    private final CarText mMessage;
    private final CarText mTitle;

    public static final class Builder {
        List<Action> mActionList;
        ActionStrip mActionStrip;
        Throwable mDebugCause;
        CarText mDebugMessage;
        String mDebugString;
        Action mHeaderAction;
        CarIcon mIcon;
        boolean mIsLoading;
        CarText mMessage;
        CarText mTitle;

        public Builder(CharSequence r2) {
            this.mActionList = new ArrayList();
            Objects.requireNonNull(r2);
            this.mMessage = CarText.create(r2);
        }

        public MessageTemplate build() {
            if (this.mIsLoading == false) goto L10;
            if (this.mIcon == null) goto L10;
            Segment$$ExternalSyntheticBUOutline1.m("Template in a loading state can not have an icon");
            return null;
        L10:
            if (this.mMessage.isEmpty() == true) goto L24;
            String r0 = this.mDebugString;
            if (r0 != null) goto L15;
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L15:
            if (r0.isEmpty() == false) goto L17;
        L19:
            String r02 = r0 + Log.getStackTraceString(this.mDebugCause);
            if (r02.isEmpty() == true) goto L23;
            this.mDebugMessage = CarText.create(r02);
        L23:
            return new MessageTemplate(this);
        L17:
            if (this.mDebugCause == null) goto L19;
            r0 = r0.concat("\n");
            goto L19
        L24:
            Segment$$ExternalSyntheticBUOutline1.m("Message cannot be empty");
            return null;
        }

        public Builder setHeaderAction(Action r3) {
            ActionsConstraints r0 = ActionsConstraints.ACTIONS_CONSTRAINTS_HEADER;
            Objects.requireNonNull(r3);
            r0.validateOrThrow(Collections.singletonList(r3));
            this.mHeaderAction = r3;
            return this;
        }

        public Builder setTitle(CharSequence r2) {
            Objects.requireNonNull(r2);
            CarText r22 = CarText.create(r2);
            this.mTitle = r22;
            CarTextConstraints.TEXT_ONLY.validateOrThrow(r22);
            return this;
        }
    }

    private MessageTemplate() {
        this.mIsLoading = false;
        this.mTitle = null;
        this.mMessage = null;
        this.mDebugMessage = null;
        this.mIcon = null;
        this.mHeaderAction = null;
        this.mActionStrip = null;
        this.mActionList = Collections.EMPTY_LIST;
    }

    public MessageTemplate(Builder r2) {
        this.mIsLoading = r2.mIsLoading;
        this.mTitle = r2.mTitle;
        this.mMessage = r2.mMessage;
        this.mDebugMessage = r2.mDebugMessage;
        this.mIcon = r2.mIcon;
        this.mHeaderAction = r2.mHeaderAction;
        this.mActionStrip = r2.mActionStrip;
        this.mActionList = CollectionUtils.unmodifiableCopy(r2.mActionList);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MessageTemplate) == true) goto L8;
        return false;
    L8:
        MessageTemplate r52 = (MessageTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L25:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L25;
        if (Objects.equals(this.mMessage, r52.mMessage) == false) goto L25;
        if (Objects.equals(this.mDebugMessage, r52.mDebugMessage) == false) goto L25;
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L25;
        if (Objects.equals(this.mActionList, r52.mActionList) == false) goto L25;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L25;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L25;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public CarText getDebugMessage() {
        return this.mDebugMessage;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public CarIcon getIcon() {
        return this.mIcon;
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
        return Objects.hash(new Object[]{Boolean.valueOf(this.mIsLoading), this.mTitle, this.mMessage, this.mDebugMessage, this.mHeaderAction, this.mActionList, this.mIcon, this.mActionStrip});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "MessageTemplate";
    }
}
