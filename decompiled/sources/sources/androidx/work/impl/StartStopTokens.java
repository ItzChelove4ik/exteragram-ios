package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\u0006J\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\u0007\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0005\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Landroidx/work/impl/StartStopTokens;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/WorkGenerationalId;", "id", "Landroidx/work/impl/StartStopToken;", "tokenFor", "(Landroidx/work/impl/model/WorkGenerationalId;)Landroidx/work/impl/StartStopToken;", "remove", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "contains", "(Landroidx/work/impl/model/WorkGenerationalId;)Z", "Landroidx/work/impl/model/WorkSpec;", "spec", "(Landroidx/work/impl/model/WorkSpec;)Landroidx/work/impl/StartStopToken;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public interface StartStopTokens {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/StartStopTokens$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/work/impl/StartStopTokens;", "synchronized", _UrlKt.FRAGMENT_ENCODE_SET, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }

        public static /* synthetic */ StartStopTokens create$default(Companion r0, boolean r1, int r2, Object r3) {
            if ((r2 & 1) == 0) goto L6;
            r1 = true;
        L6:
            return r0.create(r1);
        }

        @JvmStatic
        @JvmOverloads
        public final StartStopTokens create() {
            return create$default(this, false, 1, null);
        }

        @JvmStatic
        @JvmOverloads
        public final StartStopTokens create(boolean r1) {
            StartStopTokensImpl r0 = new StartStopTokensImpl();
            if (r1 == true) goto L5;
            return r0;
        L5:
            return new SynchronizedStartStopTokensImpl(r0);
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    @JvmStatic
    @JvmOverloads
    static StartStopTokens create() {
        return Companion.create();
    }

    @JvmStatic
    @JvmOverloads
    static StartStopTokens create(boolean r1) {
        return Companion.create(r1);
    }

    boolean contains(WorkGenerationalId r1);

    StartStopToken remove(WorkGenerationalId r1);

    List<StartStopToken> remove(String r1);

    StartStopToken tokenFor(WorkGenerationalId r1);

    default StartStopToken tokenFor(WorkSpec r1) {
        return tokenFor(WorkSpecKt.generationalId(r1));
    }
}
