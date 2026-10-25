package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AccessibilityNodeProviderCompat {
    private final Object mProvider;

    public static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProvider {
        final AccessibilityNodeProviderCompat mCompat;

        public AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat r1) {
            this.mCompat = r1;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int r1) {
            AccessibilityNodeInfoCompat r0 = this.mCompat.createAccessibilityNodeInfo(r1);
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.unwrap();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String r3, int r4) {
            List<AccessibilityNodeInfoCompat> r2 = this.mCompat.findAccessibilityNodeInfosByText(r3, r4);
            if (r2 != null) goto L6;
            return null;
        L6:
            ArrayList r32 = new ArrayList();
            int r42 = r2.size();
            int r0 = 0;
        L7:
            if (r0 >= r42) goto L9;
            r32.add(r2.get(r0).unwrap());
            r0 = r0 + 1;
            goto L7
        L9:
            return r32;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int r1) {
            AccessibilityNodeInfoCompat r0 = this.mCompat.findFocus(r1);
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.unwrap();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int r1, int r2, Bundle r3) {
            return this.mCompat.performAction(r1, r2, r3);
        }
    }

    public static class AccessibilityNodeProviderApi26 extends AccessibilityNodeProviderApi19 {
        public AccessibilityNodeProviderApi26(AccessibilityNodeProviderCompat r1) {
            super(r1);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int r1, AccessibilityNodeInfo r2, String r3, Bundle r4) {
            this.mCompat.addExtraDataToAccessibilityNodeInfo(r1, AccessibilityNodeInfoCompat.wrap(r2), r3, r4);
        }
    }

    public AccessibilityNodeProviderCompat() {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        this.mProvider = new AccessibilityNodeProviderApi26(this);
        return;
    L6:
        this.mProvider = new AccessibilityNodeProviderApi19(this);
    }

    public AccessibilityNodeProviderCompat(Object r1) {
        this.mProvider = r1;
    }

    public void addExtraDataToAccessibilityNodeInfo(int r1, AccessibilityNodeInfoCompat r2, String r3, Bundle r4) {
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int r1) {
        return null;
    }

    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String r1, int r2) {
        return null;
    }

    public AccessibilityNodeInfoCompat findFocus(int r1) {
        return null;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    public boolean performAction(int r1, int r2, Bundle r3) {
        return false;
    }
}
