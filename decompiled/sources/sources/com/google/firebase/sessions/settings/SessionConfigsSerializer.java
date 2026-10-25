package com.google.firebase.sessions.settings;

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

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/sessions/settings/SessionConfigsSerializer;", "Landroidx/datastore/core/Serializer;", "Lcom/google/firebase/sessions/settings/SessionConfigs;", "<init>", "()V", "defaultValue", "getDefaultValue", "()Lcom/google/firebase/sessions/settings/SessionConfigs;", "readFrom", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/InputStream;", "(Ljava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeTo", _UrlKt.FRAGMENT_ENCODE_SET, "t", "output", "Ljava/io/OutputStream;", "(Lcom/google/firebase/sessions/settings/SessionConfigs;Ljava/io/OutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionConfigs.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionConfigs.kt\ncom/google/firebase/sessions/settings/SessionConfigsSerializer\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,59:1\n147#2:60\n*S KotlinDebug\n*F\n+ 1 SessionConfigs.kt\ncom/google/firebase/sessions/settings/SessionConfigsSerializer\n*L\n49#1:60\n*E\n"})
/* loaded from: classes.dex */
public final class SessionConfigsSerializer implements Serializer<SessionConfigs> {
    public static final SessionConfigsSerializer INSTANCE = null;
    private static final SessionConfigs defaultValue = null;

    static {
        INSTANCE = new SessionConfigsSerializer();
        defaultValue = new SessionConfigs(null, null, null, null, null);
    }

    private SessionConfigsSerializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.datastore.core.Serializer
    public SessionConfigs getDefaultValue() {
        return defaultValue;
    }

    @Override // androidx.datastore.core.Serializer
    public /* bridge */ /* synthetic */ SessionConfigs getDefaultValue() {
        return getDefaultValue();
    }

    @Override // androidx.datastore.core.Serializer
    public Object readFrom(InputStream r1, Continuation<? super SessionConfigs> r2) {
        Json.Default r0 = Json.Default;     // Catch: Exception -> L4
        String r12 = StringsKt.decodeToString(ByteStreamsKt.readBytes(r1));     // Catch: Exception -> L4
        r0.getSerializersModule();     // Catch: Exception -> L4
        return (SessionConfigs) r0.decodeFromString(SessionConfigs.Companion.serializer(), r12);
    L4:
        e = move-exception;
        throw new CorruptionException("Cannot parse session configs", e);
    }

    /* renamed from: writeTo, reason: avoid collision after fix types in other method */
    public Object writeTo2(SessionConfigs r1, OutputStream r2, Continuation<? super Unit> r3) {
        r2.write(StringsKt.encodeToByteArray(Json.Default.encodeToString(SessionConfigs.Companion.serializer(), r1)));
        return Unit.INSTANCE;
    }

    @Override // androidx.datastore.core.Serializer
    public /* bridge */ /* synthetic */ Object writeTo(SessionConfigs r1, OutputStream r2, Continuation r3) {
        return writeTo2(r1, r2, r3);
    }
}
