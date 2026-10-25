package com.google.firebase.sessions;

import androidx.datastore.core.CorruptionException;
import androidx.datastore.core.Serializer;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0096@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/google/firebase/sessions/SessionDataSerializer;", "Landroidx/datastore/core/Serializer;", "Lcom/google/firebase/sessions/SessionData;", "sessionGenerator", "Lcom/google/firebase/sessions/SessionGenerator;", "<init>", "(Lcom/google/firebase/sessions/SessionGenerator;)V", "defaultValue", "getDefaultValue", "()Lcom/google/firebase/sessions/SessionData;", "readFrom", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/InputStream;", "(Ljava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeTo", _UrlKt.FRAGMENT_ENCODE_SET, "t", "output", "Ljava/io/OutputStream;", "(Lcom/google/firebase/sessions/SessionData;Ljava/io/OutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionData.kt\ncom/google/firebase/sessions/SessionDataSerializer\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,59:1\n147#2:60\n*S KotlinDebug\n*F\n+ 1 SessionData.kt\ncom/google/firebase/sessions/SessionDataSerializer\n*L\n49#1:60\n*E\n"})
/* loaded from: classes.dex */
public final class SessionDataSerializer implements Serializer<SessionData> {
    private final SessionGenerator sessionGenerator;

    public SessionDataSerializer(SessionGenerator r1) {
        this.sessionGenerator = r1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.datastore.core.Serializer
    public SessionData getDefaultValue() {
        return new SessionData(this.sessionGenerator.generateNewSession(null), null, null, 6, null);
    }

    @Override // androidx.datastore.core.Serializer
    public /* bridge */ /* synthetic */ SessionData getDefaultValue() {
        return getDefaultValue();
    }

    @Override // androidx.datastore.core.Serializer
    public Object readFrom(InputStream r1, Continuation<? super SessionData> r2) {
        Json.Default r0 = Json.Default;     // Catch: Exception -> L4
        String r12 = StringsKt.decodeToString(ByteStreamsKt.readBytes(r1));     // Catch: Exception -> L4
        r0.getSerializersModule();     // Catch: Exception -> L4
        return (SessionData) r0.decodeFromString(SessionData.Companion.serializer(), r12);
    L4:
        e = move-exception;
        throw new CorruptionException("Cannot parse session data", e);
    }

    /* renamed from: writeTo, reason: avoid collision after fix types in other method */
    public Object writeTo2(SessionData r1, OutputStream r2, Continuation<? super Unit> r3) {
        r2.write(StringsKt.encodeToByteArray(Json.Default.encodeToString(SessionData.Companion.serializer(), r1)));
        return Unit.INSTANCE;
    }

    @Override // androidx.datastore.core.Serializer
    public /* bridge */ /* synthetic */ Object writeTo(SessionData r1, OutputStream r2, Continuation r3) {
        return writeTo2(r1, r2, r3);
    }
}
