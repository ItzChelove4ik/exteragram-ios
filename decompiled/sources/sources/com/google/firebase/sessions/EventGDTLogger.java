package com.google.firebase.sessions;

import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0001\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/google/firebase/sessions/EventGDTLogger;", "Lcom/google/firebase/sessions/EventGDTLoggerInterface;", "transportFactoryProvider", "Lcom/google/firebase/inject/Provider;", "Lcom/google/android/datatransport/TransportFactory;", "<init>", "(Lcom/google/firebase/inject/Provider;)V", "log", _UrlKt.FRAGMENT_ENCODE_SET, "sessionEvent", "Lcom/google/firebase/sessions/SessionEvent;", "encode", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EventGDTLogger implements EventGDTLoggerInterface {
    public static final Companion Companion = null;
    private final Provider<TransportFactory> transportFactoryProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/sessions/EventGDTLogger$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AQS_LOG_SOURCE", _UrlKt.FRAGMENT_ENCODE_SET, "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static /* synthetic */ byte[] $r8$lambda$W13Olas0VV9EajY9qS9yOYVUJPs(EventGDTLogger r0, SessionEvent r1) {
        return r0.encode(r1);
    }

    static {
        Companion = new Companion(null);
    }

    public EventGDTLogger(Provider<TransportFactory> r1) {
        this.transportFactoryProvider = r1;
    }

    private final byte[] encode(SessionEvent r3) {
        String r2 = SessionEvents.INSTANCE.getSESSION_EVENT_ENCODER$com_google_firebase_firebase_sessions().encode(r3);
        Log.d("FirebaseSessions", "Session Event Type: " + r3.getEventType().name());
        return r2.getBytes(Charsets.UTF_8);
    }

    @Override // com.google.firebase.sessions.EventGDTLoggerInterface
    public void log(SessionEvent r5) {
        this.transportFactoryProvider.get().getTransport("FIREBASE_APPQUALITY_SESSION", SessionEvent.class, Encoding.of("json"), new EventGDTLogger$$ExternalSyntheticLambda0(this)).send(Event.ofData(r5));
    }
}
