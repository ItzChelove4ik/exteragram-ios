package androidx.core.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a?\u0010\u0006\u001a\u00020\u00052.\u0010\u0004\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\u0000\"\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "pairs", "Landroid/os/Bundle;", "bundleOf", "([Lkotlin/Pair;)Landroid/os/Bundle;", "core-ktx"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class BundleKt {
    @Deprecated(message = "This method does not provide type safety at compile time. Use the platform `Bundle` class directly instead.")
    public static final Bundle bundleOf(Pair<String, ? extends Object>... r10) {
        Bundle r0 = new Bundle(r10.length);
        int r1 = r10.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L95;
        Pair<String, ? extends Object> r3 = r10[r2];
        String r4 = r3.component1();
        Object r32 = r3.component2();
        if (r32 != null) goto L8;
        r0.putString(r4, null);
    L92:
        r2 = r2 + 1;
        goto L3
    L8:
        if ((r32 instanceof Boolean) == false) goto L11;
        r0.putBoolean(r4, ((Boolean) r32).booleanValue());
        goto L92
    L11:
        if ((r32 instanceof Byte) == false) goto L14;
        r0.putByte(r4, ((Number) r32).byteValue());
        goto L92
    L14:
        if ((r32 instanceof Character) == false) goto L17;
        r0.putChar(r4, ((Character) r32).charValue());
        goto L92
    L17:
        if ((r32 instanceof Double) == false) goto L20;
        r0.putDouble(r4, ((Number) r32).doubleValue());
        goto L92
    L20:
        if ((r32 instanceof Float) == false) goto L23;
        r0.putFloat(r4, ((Number) r32).floatValue());
        goto L92
    L23:
        if ((r32 instanceof Integer) == false) goto L26;
        r0.putInt(r4, ((Number) r32).intValue());
        goto L92
    L26:
        if ((r32 instanceof Long) == false) goto L29;
        r0.putLong(r4, ((Number) r32).longValue());
        goto L92
    L29:
        if ((r32 instanceof Short) == false) goto L32;
        r0.putShort(r4, ((Number) r32).shortValue());
        goto L92
    L32:
        if ((r32 instanceof Bundle) == false) goto L35;
        r0.putBundle(r4, (Bundle) r32);
        goto L92
    L35:
        if ((r32 instanceof CharSequence) == false) goto L38;
        r0.putCharSequence(r4, (CharSequence) r32);
        goto L92
    L38:
        if ((r32 instanceof Parcelable) == false) goto L41;
        r0.putParcelable(r4, (Parcelable) r32);
        goto L92
    L41:
        if ((r32 instanceof boolean[]) == false) goto L44;
        r0.putBooleanArray(r4, (boolean[]) r32);
        goto L92
    L44:
        if ((r32 instanceof byte[]) == false) goto L47;
        r0.putByteArray(r4, (byte[]) r32);
        goto L92
    L47:
        if ((r32 instanceof char[]) == false) goto L50;
        r0.putCharArray(r4, (char[]) r32);
        goto L92
    L50:
        if ((r32 instanceof double[]) == false) goto L53;
        r0.putDoubleArray(r4, (double[]) r32);
        goto L92
    L53:
        if ((r32 instanceof float[]) == false) goto L56;
        r0.putFloatArray(r4, (float[]) r32);
        goto L92
    L56:
        if ((r32 instanceof int[]) == false) goto L59;
        r0.putIntArray(r4, (int[]) r32);
        goto L92
    L59:
        if ((r32 instanceof long[]) == false) goto L62;
        r0.putLongArray(r4, (long[]) r32);
        goto L92
    L62:
        if ((r32 instanceof short[]) == false) goto L65;
        r0.putShortArray(r4, (short[]) r32);
        goto L92
    L65:
        if ((r32 instanceof Object[]) == false) goto L81;
        Class<?> r6 = r32.getClass().getComponentType();
        if (Parcelable.class.isAssignableFrom(r6) == false) goto L70;
        r0.putParcelableArray(r4, (Parcelable[]) r32);
        goto L92
    L70:
        if (String.class.isAssignableFrom(r6) == false) goto L73;
        r0.putStringArray(r4, (String[]) r32);
        goto L92
    L73:
        if (CharSequence.class.isAssignableFrom(r6) == false) goto L76;
        r0.putCharSequenceArray(r4, (CharSequence[]) r32);
        goto L92
    L76:
        if (Serializable.class.isAssignableFrom(r6) == false) goto L78;
        r0.putSerializable(r4, (Serializable) r32);
        goto L92
    L78:
        BundleKt$$ExternalSyntheticBUOutline0.m("Illegal value array type ", r6.getCanonicalName(), " for key \"", r4, 34);
        return null;
    L81:
        if ((r32 instanceof Serializable) == false) goto L84;
        r0.putSerializable(r4, (Serializable) r32);
        goto L92
    L84:
        if ((r32 instanceof IBinder) == false) goto L87;
        r0.putBinder(r4, (IBinder) r32);
        goto L92
    L87:
        if ((r32 instanceof Size) == false) goto L90;
        r0.putSize(r4, (Size) r32);
        goto L92
    L90:
        if ((r32 instanceof SizeF) == false) goto L93;
        r0.putSizeF(r4, (SizeF) r32);
        goto L92
    L93:
        BundleKt$$ExternalSyntheticBUOutline0.m("Illegal value type ", r32.getClass().getCanonicalName(), " for key \"", r4, 34);
        return null;
    L95:
        return r0;
    }
}
