package androidx.car.app.model.signin;

import androidx.car.app.model.Action;
import androidx.car.app.model.OnClickDelegate;
import androidx.car.app.model.signin.SignInTemplate;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ProviderSignInMethod implements SignInTemplate.SignInMethod {
    private final Action mAction;

    private ProviderSignInMethod() {
        this.mAction = null;
    }

    public ProviderSignInMethod(Action r4) {
        Objects.requireNonNull(r4);
        if (r4.getType() != 1) goto L10;
        OnClickDelegate r0 = r4.getOnClickDelegate();
        Objects.requireNonNull(r0);
        if (r0.isParkedOnly() == false) goto L8;
        this.mAction = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("The action must use a ParkedOnlyOnClickListener");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline1.m("The action must not be a standard action");
        throw null;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ProviderSignInMethod) == true) goto L10;
        return false;
    L10:
        return Objects.equals(this.mAction, ((ProviderSignInMethod) r2).mAction);
    }

    public Action getAction() {
        Action r0 = this.mAction;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mAction});
    }

    public String toString() {
        return "[action:" + this.mAction + "]";
    }
}
