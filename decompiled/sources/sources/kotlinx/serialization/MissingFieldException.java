package kotlinx.serialization;

import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B5\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nB\u001f\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\r\u0010\u000eR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lkotlinx/serialization/MissingFieldException;", "Lkotlinx/serialization/SerializationException;", _UrlKt.FRAGMENT_ENCODE_SET, "message", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "missingFields", "serialName", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;Ljava/util/List;Ljava/lang/String;)V", "(Ljava/util/List;Ljava/lang/String;)V", "newMessage", "withNewMessageInternal$kotlinx_serialization_core", "(Ljava/lang/String;)Lkotlinx/serialization/MissingFieldException;", "withNewMessageInternal", "Ljava/util/List;", "getMissingFields", "()Ljava/util/List;", "Ljava/lang/String;", "getSerialName", "()Ljava/lang/String;", "kotlinx-serialization-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MissingFieldException extends SerializationException {
    private final List<String> missingFields;
    private final String serialName;

    private MissingFieldException(String r1, Throwable r2, List<String> r3, String r4) {
        super(r1, r2);
        this.missingFields = r3;
        this.serialName = r4;
    }

    public MissingFieldException(List<String> r3, String r4) {
        if (r3.size() != 1) goto L5;
        String r0 = "Field '" + r3.get(0) + "' is required for type with serial name '" + r4 + "', but it was missing";
    L6:
        this(r0, null, r3, r4);
        return;
    L5:
        r0 = "Fields " + r3 + " are required for type with serial name '" + r4 + "', but they were missing";
        goto L6
    }

    public final MissingFieldException withNewMessageInternal$kotlinx_serialization_core(String r4) {
        return new MissingFieldException(r4, this, this.missingFields, this.serialName);
    }
}
