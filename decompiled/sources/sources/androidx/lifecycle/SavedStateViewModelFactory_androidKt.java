package androidx.lifecycle;

import android.app.Application;
import androidx.savedstate.Recreator$$ExternalSyntheticBUOutline0;
import c.a$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000*\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u001aI\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0000¢\u0006\u0002\u0010\n\u001a6\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\fH\u0000\"\u0018\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"newInstance", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "constructor", "Ljava/lang/reflect/Constructor;", PluginsConstants.PARAMS, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Landroidx/lifecycle/ViewModel;", "ANDROID_VIEWMODEL_SIGNATURE", _UrlKt.FRAGMENT_ENCODE_SET, "VIEWMODEL_SIGNATURE", "findMatchingConstructor", "signature", "lifecycle-viewmodel-savedstate"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class SavedStateViewModelFactory_androidKt {
    private static final List<Class<?>> ANDROID_VIEWMODEL_SIGNATURE = null;
    private static final List<Class<?>> VIEWMODEL_SIGNATURE = null;

    static {
        ANDROID_VIEWMODEL_SIGNATURE = CollectionsKt.listOf(new Class[]{Application.class, SavedStateHandle.class});
        VIEWMODEL_SIGNATURE = CollectionsKt.listOf(SavedStateHandle.class);
    }

    public static final /* synthetic */ List access$getANDROID_VIEWMODEL_SIGNATURE$p() {
        return ANDROID_VIEWMODEL_SIGNATURE;
    }

    public static final /* synthetic */ List access$getVIEWMODEL_SIGNATURE$p() {
        return VIEWMODEL_SIGNATURE;
    }

    public static final <T> Constructor<T> findMatchingConstructor(Class<T> r6, List<? extends Class<?>> r7) {
        Object[] r0 = r6.getConstructors();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L15;
        Constructor<T> r3 = (Constructor<T>) r0[r2];
        List r4 = ArraysKt.toList(r3.getParameterTypes());
        if (Intrinsics.areEqual(r7, r4) == true) goto L6;
        if (r7.size() != r4.size()) goto L14;
        if (r4.containsAll(r7) == false) goto L14;
        throw new UnsupportedOperationException("Class " + r6.getSimpleName() + " must have parameters in the proper order: " + r7);
    L14:
        r2 = r2 + 1;
        goto L3
    L6:
        return r3;
    L15:
        return null;
    }

    public static final <T extends ViewModel> T newInstance(Class<T> r2, Constructor<T> r3, Object... r4) {
        return r3.newInstance(Arrays.copyOf(r4, r4.length));
    L9:
        e = move-exception;
        a$$ExternalSyntheticBUOutline0.m("Failed to access ", r2, e);
        return null;
    L7:
        e = move-exception;
        Recreator$$ExternalSyntheticBUOutline0.m("A ", r2, " cannot be instantiated.", e);
        return null;
    L5:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("An exception happened in constructor of " + r2, e.getCause());
        return null;
    }
}
