package androidx.car.app.model.signin;

import androidx.car.app.model.Action;
import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Template;
import androidx.car.app.utils.CollectionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class SignInTemplate implements Template {
    private final List<Action> mActionList;
    private final ActionStrip mActionStrip;
    private final CarText mAdditionalText;
    private final Action mHeaderAction;
    private final CarText mInstructions;
    private final boolean mIsLoading;
    private final SignInMethod mSignInMethod;
    private final CarText mTitle;

    public static final class Builder {
    }

    public interface SignInMethod {
    }

    private SignInTemplate() {
        this.mIsLoading = false;
        this.mHeaderAction = null;
        this.mTitle = null;
        this.mInstructions = null;
        this.mAdditionalText = null;
        this.mActionStrip = null;
        this.mActionList = Collections.EMPTY_LIST;
        this.mSignInMethod = null;
    }

    public SignInTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SignInTemplate) == true) goto L8;
        return false;
    L8:
        SignInTemplate r52 = (SignInTemplate) r5;
        if (this.mIsLoading == r52.mIsLoading) goto L11;
    L25:
        return false;
    L11:
        if (Objects.equals(this.mHeaderAction, r52.mHeaderAction) == false) goto L25;
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L25;
        if (Objects.equals(this.mInstructions, r52.mInstructions) == false) goto L25;
        if (Objects.equals(this.mAdditionalText, r52.mAdditionalText) == false) goto L25;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L25;
        if (Objects.equals(this.mActionList, r52.mActionList) == false) goto L25;
        if (Objects.equals(this.mSignInMethod, r52.mSignInMethod) == false) goto L25;
        return true;
    }

    public ActionStrip getActionStrip() {
        return this.mActionStrip;
    }

    public List<Action> getActions() {
        return CollectionUtils.emptyIfNull(this.mActionList);
    }

    public CarText getAdditionalText() {
        return this.mAdditionalText;
    }

    public Action getHeaderAction() {
        return this.mHeaderAction;
    }

    public CarText getInstructions() {
        return this.mInstructions;
    }

    public SignInMethod getSignInMethod() {
        SignInMethod r0 = this.mSignInMethod;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.mIsLoading), this.mHeaderAction, this.mTitle, this.mInstructions, this.mAdditionalText, this.mActionStrip, this.mActionList, this.mSignInMethod});
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public String toString() {
        return "SignInTemplate";
    }
}
