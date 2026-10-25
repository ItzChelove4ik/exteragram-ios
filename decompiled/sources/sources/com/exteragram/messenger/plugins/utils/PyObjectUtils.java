package com.exteragram.messenger.plugins.utils;

import android.text.TextUtils;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J-\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\f0\u000eH\u0007¢\u0006\u0002\u0010\u000fJ&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0007J.\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0005H\u0007J\"\u0010\u0015\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0005H\u0007J\"\u0010\u0016\u001a\u00020\u00172\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0017H\u0007J*\u0010\u0016\u001a\u00020\u00172\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0005H\u0007J7\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00192\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u00112\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019H\u0007¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isSharedValue", _UrlKt.FRAGMENT_ENCODE_SET, "value", "closeSafely", _UrlKt.FRAGMENT_ENCODE_SET, "pyObject", "Lcom/chaquo/python/PyObject;", "toJavaCompat", "T", "clazz", "Ljava/lang/Class;", "(Lcom/chaquo/python/PyObject;Ljava/lang/Class;)Ljava/lang/Object;", "getString", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "defaultValue", "fromMap", "getBoolean", "getInt", _UrlKt.FRAGMENT_ENCODE_SET, "getStringArray", _UrlKt.FRAGMENT_ENCODE_SET, "(Lcom/chaquo/python/PyObject;Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PyObjectUtils {
    public static final PyObjectUtils INSTANCE = null;

    static {
        INSTANCE = new PyObjectUtils();
    }

    private PyObjectUtils() {
    }

    @JvmStatic
    public static final void closeSafely(PyObject r0) {
        if (r0 != null) goto L7;
        return;
    L7:
        r0.close();     // Catch: Throwable -> L6
        return;
    }

    @JvmStatic
    public static final boolean getBoolean(PyObject r1, String r2, boolean r3) {
        if (r1 != null) goto L4;
    L10:
        return r3;
    L4:
        if (TextUtils.isEmpty(r2) == true) goto L10;
        PyObject r12 = (PyObject) r1.get(r2);     // Catch: Throwable -> L11
        if (r12 == null) goto L10;
        return r12.toBoolean();
    }

    @JvmStatic
    public static final int getInt(PyObject r1, String r2, int r3) {
        return getInt(r1, r2, r3, false);
    }

    @JvmStatic
    public static final int getInt(PyObject r1, String r2, int r3, boolean r4) {
        if (r1 != null) goto L4;
    L12:
        return r3;
    L4:
        if (TextUtils.isEmpty(r2) == true) goto L12;
        if (r4 == false) goto L8;
        PyObject r12 = r1.callAttr("get", new Object[]{r2});     // Catch: Throwable -> L13
    L9:
        if (r12 == null) goto L12;
        return r12.toInt();
    L8:
        r12 = (PyObject) r1.get(r2);     // Catch: Throwable -> L13
        goto L9
    }

    @JvmStatic
    public static final String getString(PyObject r1, String r2, String r3) {
        return getString(r1, r2, r3, false);
    }

    @JvmStatic
    public static final String getString(PyObject r1, String r2, String r3, boolean r4) {
        if (r1 != null) goto L4;
    L14:
        return r3;
    L4:
        if (TextUtils.isEmpty(r2) == true) goto L14;
        if (r4 == false) goto L8;
        PyObject r12 = r1.callAttr("get", new Object[]{r2});     // Catch: Throwable -> L15
    L9:
        if (r12 == null) goto L14;
        String r13 = r12.toString();     // Catch: Throwable -> L15
        if (r13 == null) goto L14;
        return r13;
    L8:
        r12 = (PyObject) r1.get(r2);     // Catch: Throwable -> L15
        goto L9
    }

    @JvmStatic
    public static final String[] getStringArray(PyObject r1, String r2, String[] r3) {
        if (r1 != null) goto L4;
    L15:
        return r3;
    L4:
        if (TextUtils.isEmpty(r2) == true) goto L15;
        PyObject r12 = (PyObject) r1.get(r2);     // Catch: Throwable -> L16
        if (r12 == null) goto L15;
        String[] r13 = (String[]) r12.toJava(String[].class);     // Catch: Throwable -> L16
        if (r13 == null) goto L15;
        if (r13.length != 0) goto L14;
        return r3;
    L14:
        return r13;
    }

    @JvmStatic
    public static final boolean isSharedValue(Object r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if ((r1 instanceof Boolean) == false) goto L6;
        return true;
    L6:
        if ((r1 instanceof Number) == false) goto L8;
        return true;
    L8:
        if ((r1 instanceof Character) == false) goto L10;
        return true;
    L10:
        if ((r1 instanceof String) == true) goto L19;
        return false;
    L19:
        return true;
    }

    @JvmStatic
    public static final <T> T toJavaCompat(PyObject r3, Class<T> r4) {
        if (r3 != null) goto L12;
        return null;
    L12:
        return (T) r3.toJava(r4);
    L7:
        PyObject r32 = r3.callAttr("__getattribute__", new Object[]{"java"});     // Catch: Throwable -> L11
        if (r32 == null) goto L15;
        return (T) r32.toJava(r4);
    L15:
        return null;
    L16:
        return null;
    }
}
