package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a'\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "seen", "goldenMask", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", _UrlKt.FRAGMENT_ENCODE_SET, "throwMissingFieldException", "(IILkotlinx/serialization/descriptors/SerialDescriptor;)V", "kotlinx-serialization-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class PluginExceptionsKt {
    public static final void throwMissingFieldException(int r2, int r3, SerialDescriptor r4) {
        ArrayList r0 = new ArrayList();
        int r22 = (~r2) & r3;
        int r32 = 0;
    L4:
        if (r32 >= 32) goto L10;
        if ((r22 & 1) == 0) goto L8;
        r0.add(r4.getElementName(r32));
    L8:
        r22 = r22 >>> 1;
        r32 = r32 + 1;
        goto L4
    L10:
        throw new MissingFieldException(r0, r4.getSerialName());
    }
}
