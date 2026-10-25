package androidx.lifecycle.viewmodel.internal;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u0003J!\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0010\u001a\u0004\u0018\u00018\u0000\"\f\b\u0000\u0010\u000f*\u00060\u0004j\u0002`\u00052\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u000b\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0019\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/lifecycle/viewmodel/internal/ViewModelImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "closeable", _UrlKt.FRAGMENT_ENCODE_SET, "closeWithRuntimeException", "(Ljava/lang/AutoCloseable;)V", "clear", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "addCloseable", "(Ljava/lang/String;Ljava/lang/AutoCloseable;)V", "T", "getCloseable", "(Ljava/lang/String;)Ljava/lang/AutoCloseable;", "Landroidx/lifecycle/viewmodel/internal/SynchronizedObject;", "lock", "Landroidx/lifecycle/viewmodel/internal/SynchronizedObject;", _UrlKt.FRAGMENT_ENCODE_SET, "keyToCloseables", "Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "closeables", "Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, "isCleared", "Z", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nViewModelImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ViewModelImpl.kt\nandroidx/lifecycle/viewmodel/internal/ViewModelImpl\n+ 2 SynchronizedObject.kt\nandroidx/lifecycle/viewmodel/internal/SynchronizedObjectKt\n+ 3 SynchronizedObject.jvmAndAndroid.kt\nandroidx/lifecycle/viewmodel/internal/SynchronizedObject_jvmKt\n*L\n1#1,132:1\n32#2:133\n32#2:135\n32#2:137\n32#2:139\n26#3:134\n26#3:136\n26#3:138\n26#3:140\n*S KotlinDebug\n*F\n+ 1 ViewModelImpl.kt\nandroidx/lifecycle/viewmodel/internal/ViewModelImpl\n*L\n80#1:133\n103#1:135\n117#1:137\n122#1:139\n80#1:134\n103#1:136\n117#1:138\n122#1:140\n*E\n"})
/* loaded from: classes.dex */
public final class ViewModelImpl {
    private final Set<AutoCloseable> closeables;
    private volatile boolean isCleared;
    private final Map<String, AutoCloseable> keyToCloseables;
    private final SynchronizedObject lock;

    public ViewModelImpl() {
        this.lock = new SynchronizedObject();
        this.keyToCloseables = new LinkedHashMap();
        this.closeables = new LinkedHashSet();
    }

    public static final /* synthetic */ void access$closeWithRuntimeException(ViewModelImpl r0, AutoCloseable r1) {
        r0.closeWithRuntimeException(r1);
    }

    public static final /* synthetic */ Set access$getCloseables$p(ViewModelImpl r0) {
        return r0.closeables;
    }

    public static final /* synthetic */ Map access$getKeyToCloseables$p(ViewModelImpl r0) {
        return r0.keyToCloseables;
    }

    private final void closeWithRuntimeException(AutoCloseable r1) {
        if (r1 == null) goto L10;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r1);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return;
    }

    public final void addCloseable(String r3, AutoCloseable r4) {
        if (this.isCleared == false) goto L6;
        closeWithRuntimeException(r4);
        return;
    L6:
        SynchronizedObject r0 = this.lock;
        monitor-enter(r0);
        AutoCloseable r32 = (AutoCloseable) access$getKeyToCloseables$p(this).put(r3, r4);     // Catch: Throwable -> L12
        monitor-exit(r0);
        closeWithRuntimeException(r32);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    public final void clear() {
        if (this.isCleared == false) goto L5;
        return;
    L5:
        this.isCleared = true;
        SynchronizedObject r0 = this.lock;
        monitor-enter(r0);
        Iterator r1 = access$getKeyToCloseables$p(this).values().iterator();     // Catch: Throwable -> L11
    L9:
        if (r1.hasNext() == false) goto L13;
        access$closeWithRuntimeException(this, (AutoCloseable) r1.next());     // Catch: Throwable -> L11
        goto L9
    L13:
        Iterator r12 = access$getCloseables$p(this).iterator();     // Catch: Throwable -> L11
    L15:
        if (r12.hasNext() == false) goto L17;
        access$closeWithRuntimeException(this, (AutoCloseable) r12.next());     // Catch: Throwable -> L11
        goto L15
    L17:
        access$getCloseables$p(this).clear();     // Catch: Throwable -> L11
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r0);
        return;
    L11:
        th = move-exception;
        throw th;
    }

    public final <T extends AutoCloseable> T getCloseable(String r2) {
        SynchronizedObject r0 = this.lock;
        monitor-enter(r0);
        T r1 = (T) access$getKeyToCloseables$p(this).get(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
