package androidx.datastore.core;

import androidx.datastore.core.Message;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CompletableDeferred;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "msg", "Landroidx/datastore/core/Message$Update;", "ex", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DataStoreImpl$writeActor$2<T> extends Lambda implements Function2<Message.Update<T>, Throwable, Unit> {
    public static final DataStoreImpl$writeActor$2 INSTANCE = null;

    static {
        INSTANCE = new DataStoreImpl$writeActor$2();
    }

    public DataStoreImpl$writeActor$2() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Object r1, Throwable r2) {
        invoke((Message.Update) r1, r2);
        return Unit.INSTANCE;
    }

    public final void invoke(Message.Update<T> r1, Throwable r2) {
        CompletableDeferred<T> r0 = r1.getAck();
        if (r2 != null) goto L5;
        r2 = new CancellationException("DataStore scope was cancelled before updateData could complete");
    L5:
        r0.completeExceptionally(r2);
    }
}
