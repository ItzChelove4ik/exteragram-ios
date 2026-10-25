package androidx.car.app.model.constraints;

import androidx.car.app.model.Tab;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class TabsConstraints {
    public static final TabsConstraints DEFAULT = null;
    private final int mMaxTabs;
    private final int mMinTabs;

    public static final class Builder {
        int mMaxTabs;
        int mMinTabs;

        public Builder() {
            this.mMaxTabs = Integer.MAX_VALUE;
            this.mMinTabs = 0;
        }

        public TabsConstraints build() {
            return new TabsConstraints(this);
        }

        public Builder setMaxTabs(int r1) {
            this.mMaxTabs = r1;
            return this;
        }

        public Builder setMinTabs(int r1) {
            this.mMinTabs = r1;
            return this;
        }
    }

    static {
        DEFAULT = new Builder().setMaxTabs(4).setMinTabs(2).build();
    }

    public TabsConstraints(Builder r2) {
        this.mMaxTabs = r2.mMaxTabs;
        this.mMinTabs = r2.mMinTabs;
    }

    public void validateOrThrow(List<Tab> r4, String r5) {
        if (r4.size() >= this.mMinTabs) goto L5;
        Dex$$ExternalSyntheticBUOutline1.m("There must be at least ", this.mMinTabs, " tab(s) added, but only found ", r4.size());
        return;
    L5:
        if (r4.size() > this.mMaxTabs) goto L21;
        HashSet r3 = new HashSet();
        Iterator<Tab> r42 = r4.iterator();
        boolean r0 = false;
    L8:
        if (r42.hasNext() == false) goto L17;
        Tab r1 = r42.next();
        if (r5.equals(r1.getContentId()) == false) goto L13;
        r0 = true;
    L13:
        if (r3.add(r1.getContentId()) == true) goto L8;
        Native$$ExternalSyntheticBUOutline2.m("Found duplicate tab ID: ", r1.getContentId(), ". Each tab must have a unique ID.");
        return;
    L17:
        if (r0 == false) goto L19;
        return;
    L19:
        f$$ExternalSyntheticBUOutline1.m("There is no tab with content ID matching the active tab content ID set on the template");
        return;
    L21:
        Dex$$ExternalSyntheticBUOutline1.m("There cannot be more than ", this.mMaxTabs, " tabs added, found ", r4.size());
    }
}
