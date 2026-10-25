package com.exteragram.messenger.plugins.xposed;

import c.f$$ExternalSyntheticBUOutline1;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.utils.PyObjectUtils;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;", "Lde/robv/android/xposed/XC_MethodReplacement;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "pythonCallback", "Lcom/chaquo/python/PyObject;", "<init>", "(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V", PluginsConstants.MenuItemProperties.PRIORITY, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;Lcom/chaquo/python/PyObject;I)V", "replaceHook", "disabled", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "replaceHookedMethod", _UrlKt.FRAGMENT_ENCODE_SET, "param", "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;", "close", _UrlKt.FRAGMENT_ENCODE_SET, "handleHookError", "t", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPyMethodReplacement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PyMethodReplacement.kt\ncom/exteragram/messenger/plugins/xposed/PyMethodReplacement\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,95:1\n1#2:96\n*E\n"})
/* loaded from: classes.dex */
public final class PyMethodReplacement extends XC_MethodReplacement implements AutoCloseable {
    private volatile boolean closed;
    private volatile boolean disabled;
    private final String pluginId;
    private final PyObject pythonCallback;
    private final PyObject replaceHook;

    public PyMethodReplacement(String r4, PyObject r5) {
        if (r5 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Python callback object cannot be null");
        throw null;
    L5:
        if (r5.containsKey(PluginsConstants.Xposed.REPLACE_HOOKED_METHOD) == false) goto L12;
        this.pluginId = r4;
        this.pythonCallback = r5;
        Object r42 = r5.get(PluginsConstants.Xposed.REPLACE_HOOKED_METHOD);
        if (r42 == null) goto L10;
        this.replaceHook = (PyObject) r42;
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline1.m("Python callback object must contain a method named 'replaceHookedMethod'");
        throw null;
    }

    public PyMethodReplacement(String r3, PyObject r4, int r5) {
        super(r5);
        if (r4 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Python callback object cannot be null");
        throw null;
    L5:
        if (r4.containsKey(PluginsConstants.Xposed.REPLACE_HOOKED_METHOD) == false) goto L12;
        this.pluginId = r3;
        this.pythonCallback = r4;
        Object r32 = r4.get(PluginsConstants.Xposed.REPLACE_HOOKED_METHOD);
        if (r32 == null) goto L10;
        this.replaceHook = (PyObject) r32;
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline1.m("Python callback object must contain a method named 'replaceHookedMethod'");
        throw null;
    }

    private final void handleHookError(Throwable r6) {
        if ((r6 instanceof PyException) == true) goto L5;
    L10:
        FileLog.e("Plugin '" + this.pluginId + "' crashed in replaceHookedMethod: " + r6.getMessage(), r6);
        return;
    L5:
        if (r6.getMessage() == null) goto L10;
        if (StringsKt.contains$default(r6.getMessage(), "closed", false, 2, null) == false) goto L10;
        this.disabled = true;
        FileLog.e("Attempted to call a closed PyObject callback in " + this.pluginId);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.closed == false) goto L5;
        return;
    L5:
        this.closed = true;
        this.disabled = true;
        this.replaceHook.close();
    }

    @Override // de.robv.android.xposed.XC_MethodReplacement
    public Object replaceHookedMethod(XC_MethodHook.MethodHookParam r3) {
        if (this.disabled == false) goto L5;
    L44:
        return XposedBridge.invokeOriginalMethod(r3.method, r3.thisObject, r3.args);
    L32:
        e = move-exception;
        Throwable r32 = e.getTargetException();
        if (r32 != null) goto L37;
        throw e;
    L37:
        throw r32;
    L5:
        if (PluginsController.Companion.getInstance().isPluginActive$TMessagesProj(this.pluginId) == false) goto L44;
        Object r0 = null;
        PyObject r33 = this.replaceHook.call(new Object[]{r3});     // Catch: Throwable -> L18
        if (r33 == null) goto L15;
        r0 = r33.toJava(Object.class);     // Catch: Throwable -> L12
    L12:
        th = th;
    L38:
        handleHookError(th);     // Catch: Throwable -> L25
        if (PyObjectUtils.isSharedValue(null) == true) goto L24;
        PyObjectUtils.closeSafely(r33);
    L24:
        return null;
    L25:
        th = move-exception;
        if (PyObjectUtils.isSharedValue(null) == true) goto L29;
        PyObjectUtils.closeSafely(r33);
    L29:
        throw th;
    L15:
        if (PyObjectUtils.isSharedValue(r0) == true) goto L17;
        PyObjectUtils.closeSafely(r33);
    L17:
        return r0;
    L18:
        th = th;
        r33 = null;
        goto L38
    }
}
