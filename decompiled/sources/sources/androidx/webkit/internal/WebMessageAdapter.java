package androidx.webkit.internal;

import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes4.dex */
public abstract class WebMessageAdapter implements WebMessageBoundaryInterface {
    private static final String[] sFeatures = null;

    static {
        sFeatures = new String[]{"WEB_MESSAGE_ARRAY_BUFFER"};
    }

    private static WebMessagePortCompat[] toWebMessagePortCompats(InvocationHandler[] r4) {
        WebMessagePortCompat[] r0 = new WebMessagePortCompat[r4.length];
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L6;
        r0[r1] = new WebMessagePortImpl(r4[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public static WebMessageCompat webMessageCompatFromBoundaryInterface(WebMessageBoundaryInterface r3) {
        WebMessagePortCompat[] r0 = toWebMessagePortCompats(r3.getPorts());
        if (WebViewFeatureInternal.WEB_MESSAGE_ARRAY_BUFFER.isSupportedByWebView() == false) goto L15;
        WebMessagePayloadBoundaryInterface r32 = (WebMessagePayloadBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebMessagePayloadBoundaryInterface.class, r3.getMessagePayload());
        int r1 = r32.getType();
        if (r1 == 0) goto L13;
        if (r1 == 1) goto L11;
        return null;
    L11:
        return new WebMessageCompat(r32.getAsArrayBuffer(), r0);
    L13:
        return new WebMessageCompat(r32.getAsString(), r0);
    L15:
        return new WebMessageCompat(r3.getData(), r0);
    }
}
