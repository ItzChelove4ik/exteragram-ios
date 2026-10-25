package androidx.activity.contextaware;

import android.content.Context;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/activity/contextaware/ContextAwareHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "context", "Landroid/content/Context;", "listeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/activity/contextaware/OnContextAvailableListener;", "addOnContextAvailableListener", _UrlKt.FRAGMENT_ENCODE_SET, "listener", "clearAvailableContext", "dispatchOnContextAvailable", "peekAvailableContext", "removeOnContextAvailableListener", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextAwareHelper {
    private volatile Context context;
    private final Set<OnContextAvailableListener> listeners;

    public ContextAwareHelper() {
        this.listeners = new CopyOnWriteArraySet();
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener r2) {
        Context r0 = this.context;
        if (r0 == null) goto L5;
        r2.onContextAvailable(r0);
    L5:
        this.listeners.add(r2);
    }

    public final void clearAvailableContext() {
        this.context = null;
    }

    public final void dispatchOnContextAvailable(Context r2) {
        this.context = r2;
        Iterator<OnContextAvailableListener> r1 = this.listeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onContextAvailable(r2);
        goto L4
    }

    public final Context peekAvailableContext() {
        return this.context;
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener r1) {
        this.listeners.remove(r1);
    }
}
