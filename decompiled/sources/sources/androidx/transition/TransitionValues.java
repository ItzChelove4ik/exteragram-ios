package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class TransitionValues {
    final ArrayList<Transition> mTargetedTransitions;
    public final Map<String, Object> values;

    @SuppressLint({"UnknownNullness"})
    public View view;

    public TransitionValues(View r2) {
        this.values = new HashMap();
        this.mTargetedTransitions = new ArrayList();
        this.view = r2;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof TransitionValues) == false) goto L10;
        TransitionValues r32 = (TransitionValues) r3;
        if (this.view == r32.view) goto L7;
        return false;
    L7:
        if (this.values.equals(r32.values) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public int hashCode() {
        return (this.view.hashCode() * 31) + this.values.hashCode();
    }

    public String toString() {
        String r1 = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.view + "\n").concat("    values:");
        Iterator<String> r2 = this.values.keySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        String r3 = r2.next();
        r1 = r1 + "    " + r3 + ": " + this.values.get(r3) + "\n";
        goto L4
    L6:
        return r1;
    }
}
