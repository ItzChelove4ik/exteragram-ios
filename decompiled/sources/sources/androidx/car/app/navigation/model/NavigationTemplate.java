package androidx.car.app.navigation.model;

import androidx.car.app.model.ActionStrip;
import androidx.car.app.model.CarColor;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class NavigationTemplate implements Template {
    private final ActionStrip mActionStrip;
    private final CarColor mBackgroundColor;
    private final TravelEstimate mDestinationTravelEstimate;
    private final ActionStrip mMapActionStrip;
    private final NavigationInfo mNavigationInfo;
    private final PanModeDelegate mPanModeDelegate;
    private final Toggle mPanModeToggle;

    public static final class Builder {
    }

    public interface NavigationInfo {
    }

    private NavigationTemplate() {
        this.mNavigationInfo = null;
        this.mBackgroundColor = null;
        this.mDestinationTravelEstimate = null;
        this.mActionStrip = null;
        this.mMapActionStrip = null;
        this.mPanModeToggle = null;
        this.mPanModeDelegate = null;
    }

    public NavigationTemplate(Builder r1) {
        throw null;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof NavigationTemplate) == true) goto L8;
        return false;
    L8:
        NavigationTemplate r52 = (NavigationTemplate) r5;
        if (Objects.equals(this.mNavigationInfo, r52.mNavigationInfo) == true) goto L11;
    L31:
        return false;
    L11:
        if (Objects.equals(this.mBackgroundColor, r52.mBackgroundColor) == false) goto L31;
        if (Objects.equals(this.mDestinationTravelEstimate, r52.mDestinationTravelEstimate) == false) goto L31;
        if (Objects.equals(this.mActionStrip, r52.mActionStrip) == false) goto L31;
        if (Objects.equals(this.mMapActionStrip, r52.mMapActionStrip) == false) goto L31;
        if (Objects.equals(this.mPanModeToggle, r52.mPanModeToggle) == false) goto L31;
        if (this.mPanModeDelegate != null) goto L23;
        boolean r4 = true;
    L24:
        Boolean r42 = Boolean.valueOf(r4);
        if (r52.mPanModeDelegate != null) goto L27;
        boolean r53 = true;
    L29:
        if (r42.equals(Boolean.valueOf(r53)) == false) goto L31;
        return true;
    L27:
        r53 = false;
        goto L29
    L23:
        r4 = false;
        goto L24
    }

    public ActionStrip getActionStrip() {
        ActionStrip r0 = this.mActionStrip;
        Objects.requireNonNull(r0);
        return r0;
    }

    public CarColor getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public TravelEstimate getDestinationTravelEstimate() {
        return this.mDestinationTravelEstimate;
    }

    public ActionStrip getMapActionStrip() {
        return this.mMapActionStrip;
    }

    public NavigationInfo getNavigationInfo() {
        return this.mNavigationInfo;
    }

    public PanModeDelegate getPanModeDelegate() {
        return this.mPanModeDelegate;
    }

    @Deprecated
    public Toggle getPanModeToggle() {
        return this.mPanModeToggle;
    }

    public int hashCode() {
        NavigationInfo r0 = this.mNavigationInfo;
        CarColor r1 = this.mBackgroundColor;
        TravelEstimate r2 = this.mDestinationTravelEstimate;
        ActionStrip r3 = this.mActionStrip;
        ActionStrip r4 = this.mMapActionStrip;
        Toggle r5 = this.mPanModeToggle;
        if (this.mPanModeDelegate != null) goto L5;
        boolean r7 = true;
    L7:
        return Objects.hash(new Object[]{r0, r1, r2, r3, r4, r5, Boolean.valueOf(r7)});
    L5:
        r7 = false;
        goto L7
    }

    public String toString() {
        return "NavigationTemplate";
    }
}
