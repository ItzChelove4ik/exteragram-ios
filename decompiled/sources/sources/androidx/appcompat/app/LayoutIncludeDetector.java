package androidx.appcompat.app;

import android.util.AttributeSet;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
class LayoutIncludeDetector {
    private final Deque<WeakReference<XmlPullParser>> mXmlParserStack;

    public LayoutIncludeDetector() {
        this.mXmlParserStack = new ArrayDeque();
    }

    private static boolean isParserOutdated(XmlPullParser r3) {
        if (r3 != null) goto L13;
    L11:
        return true;
    L13:
        if (r3.getEventType() == 3) goto L11;
        if (r3.getEventType() == 1) goto L11;
        return false;
    }

    private static XmlPullParser popOutdatedAttrHolders(Deque<WeakReference<XmlPullParser>> r2) {
    L3:
        if (r2.isEmpty() == true) goto L8;
        XmlPullParser r0 = r2.peek().get();
        if (isParserOutdated(r0) == false) goto L7;
        r2.pop();
        goto L3
    L7:
        return r0;
    L8:
        return null;
    }

    private static boolean shouldInheritContext(XmlPullParser r1, XmlPullParser r2) {
        if (r2 == null) goto L8;
        if (r1 == r2) goto L13;
        if (r2.getEventType() != 2) goto L14;
        return "include".equals(r2.getName());
    L14:
        return false;
    L15:
        return false;
    L13:
        return false;
    L8:
        return false;
    }

    public boolean detect(AttributeSet r4) {
        if ((r4 instanceof XmlPullParser) == false) goto L9;
        XmlPullParser r42 = (XmlPullParser) r4;
        if (r42.getDepth() != 1) goto L11;
        XmlPullParser r0 = popOutdatedAttrHolders(this.mXmlParserStack);
        this.mXmlParserStack.push(new WeakReference(r42));
        if (shouldInheritContext(r42, r0) == false) goto L12;
        return true;
    L12:
        return false;
    L11:
        return false;
    L9:
        return false;
    }
}
