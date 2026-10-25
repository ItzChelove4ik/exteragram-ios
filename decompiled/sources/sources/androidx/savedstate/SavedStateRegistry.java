package androidx.savedstate;

import android.os.Bundle;
import androidx.savedstate.Recreator;
import androidx.savedstate.internal.SavedStateRegistryImpl;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\u00020\u000e2\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013H\u0007¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Landroidx/savedstate/SavedStateRegistry;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/savedstate/internal/SavedStateRegistryImpl;", "impl", "<init>", "(Landroidx/savedstate/internal/SavedStateRegistryImpl;)V", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "consumeRestoredStateForKey", "(Ljava/lang/String;)Landroid/os/Bundle;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "provider", _UrlKt.FRAGMENT_ENCODE_SET, "registerSavedStateProvider", "(Ljava/lang/String;Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;)V", "getSavedStateProvider", "(Ljava/lang/String;)Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "Ljava/lang/Class;", "Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "clazz", "runOnNextRecreation", "(Ljava/lang/Class;)V", "Landroidx/savedstate/internal/SavedStateRegistryImpl;", "Landroidx/savedstate/Recreator$SavedStateProvider;", "recreatorProvider", "Landroidx/savedstate/Recreator$SavedStateProvider;", "SavedStateProvider", "AutoRecreated", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SavedStateRegistry {
    private final SavedStateRegistryImpl impl;
    private Recreator.SavedStateProvider recreatorProvider;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", _UrlKt.FRAGMENT_ENCODE_SET, "onRecreated", _UrlKt.FRAGMENT_ENCODE_SET, "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner r1);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\f\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "saveState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface SavedStateProvider {
        Bundle saveState();
    }

    public SavedStateRegistry(SavedStateRegistryImpl r1) {
        this.impl = r1;
    }

    public final Bundle consumeRestoredStateForKey(String r1) {
        return this.impl.consumeRestoredStateForKey(r1);
    }

    public final SavedStateProvider getSavedStateProvider(String r1) {
        return this.impl.getSavedStateProvider(r1);
    }

    public final void registerSavedStateProvider(String r1, SavedStateProvider r2) {
        this.impl.registerSavedStateProvider(r1, r2);
    }

    public final void runOnNextRecreation(Class<? extends AutoRecreated> r4) {
        if (this.impl.isAllowingSavingState$savedstate() == false) goto L16;
        Recreator.SavedStateProvider r0 = this.recreatorProvider;
        if (r0 != null) goto L7;
        r0 = new Recreator.SavedStateProvider(this);
    L7:
        this.recreatorProvider = r0;
        r4.getDeclaredConstructor(null);     // Catch: NoSuchMethodException -> L13
        Recreator.SavedStateProvider r3 = this.recreatorProvider;
        if (r3 == null) goto L20;
        r3.add(r4.getName());
        return;
    L20:
        return;
    L13:
        e = move-exception;
        throw new IllegalArgumentException("Class " + r4.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("Can not perform this action after onSaveInstanceState");
    }
}
