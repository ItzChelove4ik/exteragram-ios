package androidx.savedstate;

import android.os.Bundle;
import androidx.savedstate.internal.SavedStateRegistryImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\u000b2\u000e\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fH\u0007J\u0014\u0010\u0010\u001a\u00020\u000b2\n\u0010\u0011\u001a\u00060\u000ej\u0002`\u000fH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/savedstate/SavedStateRegistryController;", _UrlKt.FRAGMENT_ENCODE_SET, "impl", "Landroidx/savedstate/internal/SavedStateRegistryImpl;", "<init>", "(Landroidx/savedstate/internal/SavedStateRegistryImpl;)V", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "performAttach", _UrlKt.FRAGMENT_ENCODE_SET, "performRestore", "savedState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "performSave", "outBundle", "Companion", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    public static final Companion Companion = null;
    private final SavedStateRegistryImpl impl;
    private final SavedStateRegistry savedStateRegistry;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/savedstate/SavedStateRegistryController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/savedstate/SavedStateRegistryController;", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public static Unit $r8$lambda$mYKAg7nh_JcP4XSEzfHJKM82YOw(SavedStateRegistryOwner r2) {
            r2.getLifecycle().addObserver(new Recreator(r2));
            return Unit.INSTANCE;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final SavedStateRegistryController create(SavedStateRegistryOwner r2) {
            return new SavedStateRegistryController(new SavedStateRegistryImpl(r2, new SavedStateRegistryController$Companion$$ExternalSyntheticLambda0(r2)), null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private SavedStateRegistryController(SavedStateRegistryImpl r2) {
        this.impl = r2;
        this.savedStateRegistry = new SavedStateRegistry(r2);
    }

    public /* synthetic */ SavedStateRegistryController(SavedStateRegistryImpl r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    @JvmStatic
    public static final SavedStateRegistryController create(SavedStateRegistryOwner r1) {
        return Companion.create(r1);
    }

    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistry;
    }

    public final void performAttach() {
        this.impl.performAttach();
    }

    public final void performRestore(Bundle r1) {
        this.impl.performRestore$savedstate(r1);
    }

    public final void performSave(Bundle r1) {
        this.impl.performSave$savedstate(r1);
    }
}
