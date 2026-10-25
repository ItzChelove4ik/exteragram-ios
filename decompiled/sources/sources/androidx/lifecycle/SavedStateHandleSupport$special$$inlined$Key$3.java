package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"androidx/lifecycle/SavedStateHandleSupport$special$$inlined$Key$3", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCreationExtras.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CreationExtras.kt\nandroidx/lifecycle/viewmodel/CreationExtras$Companion$Key$1\n*L\n1#1,162:1\n*E\n"})
/* loaded from: classes.dex */
public final class SavedStateHandleSupport$special$$inlined$Key$3 implements CreationExtras.Key<Bundle> {
    public SavedStateHandleSupport$special$$inlined$Key$3() {
    }

    public String toString() {
        return "CreationExtras.Key@" + Integer.toString(hashCode(), CharsKt.checkRadix(16)) + "<" + Reflection.getOrCreateKotlinClass(Bundle.class).getSimpleName() + ">";
    }
}
