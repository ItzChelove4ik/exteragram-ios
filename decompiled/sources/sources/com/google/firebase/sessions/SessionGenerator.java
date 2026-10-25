package com.google.firebase.sessions;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\tJ\b\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/firebase/sessions/SessionGenerator;", _UrlKt.FRAGMENT_ENCODE_SET, "timeProvider", "Lcom/google/firebase/sessions/TimeProvider;", "uuidGenerator", "Lcom/google/firebase/sessions/UuidGenerator;", "<init>", "(Lcom/google/firebase/sessions/TimeProvider;Lcom/google/firebase/sessions/UuidGenerator;)V", "generateNewSession", "Lcom/google/firebase/sessions/SessionDetails;", "currentSession", "generateSessionId", _UrlKt.FRAGMENT_ENCODE_SET, "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SessionGenerator {
    private final TimeProvider timeProvider;
    private final UuidGenerator uuidGenerator;

    public SessionGenerator(TimeProvider r1, UuidGenerator r2) {
        this.timeProvider = r1;
        this.uuidGenerator = r2;
    }

    private final String generateSessionId() {
        return StringsKt.replace$default(this.uuidGenerator.next().toString(), "-", _UrlKt.FRAGMENT_ENCODE_SET, false, 4, null).toLowerCase(Locale.ROOT);
    }

    public final SessionDetails generateNewSession(SessionDetails r7) {
        String r1 = generateSessionId();
        if (r7 == null) goto L6;
        String r2 = r7.getFirstSessionId();
        if (r2 == null) goto L6;
    L7:
        if (r7 == null) goto L10;
        int r72 = r7.getSessionIndex() + 1;
    L9:
        int r3 = r72;
        return new SessionDetails(r1, r2, r3, this.timeProvider.currentTime().getUs());
    L10:
        r72 = 0;
    L6:
        r2 = r1;
        goto L7
    }
}
