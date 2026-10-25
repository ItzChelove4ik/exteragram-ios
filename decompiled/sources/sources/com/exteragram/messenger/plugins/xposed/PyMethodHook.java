package com.exteragram.messenger.plugins.xposed;

import c.f$$ExternalSyntheticBUOutline1;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.hooks.HookFilter;
import de.robv.android.xposed.XC_MethodHook;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fB+\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\b\u0010\u0010B3\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\b\u0010\u0011J\u001e\u0010\u001b\u001a\u00020\u001c2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J\u001e\u0010\u001d\u001a\u00020\u001c2\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\u001cH\u0016J0\u0010%\u001a\u00020\u000e2\u0016\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00172\u0006\u0010!\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u000eH\u0002J\"\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u000eH\u0002J\u0018\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;", "Lde/robv/android/xposed/XC_MethodHook;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "pythonCallback", "Lcom/chaquo/python/PyObject;", "<init>", "(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V", PluginsConstants.MenuItemProperties.PRIORITY, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;Lcom/chaquo/python/PyObject;I)V", "hasBeforeHook", _UrlKt.FRAGMENT_ENCODE_SET, "hasAfterHook", "(Ljava/lang/String;Lcom/chaquo/python/PyObject;ZZ)V", "(Ljava/lang/String;Lcom/chaquo/python/PyObject;IZZ)V", "beforeHook", "afterHook", "beforeHookedFilters", "Ljava/util/ArrayList;", "Lcom/exteragram/messenger/plugins/hooks/HookFilter;", "Lkotlin/collections/ArrayList;", "afterHookedFilters", "disabled", "closed", "setBeforeHookedFilters", _UrlKt.FRAGMENT_ENCODE_SET, "setAfterHookedFilters", "getBeforeHookedFilters", "getAfterHookedFilters", "beforeHookedMethod", "param", "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;", "afterHookedMethod", "close", "executeFilters", "filters", "isBefore", "getCallbackIfPresent", "callbackObject", "name", "enabled", "handleHookError", "hookMethodName", "t", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPyMethodHook.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PyMethodHook.kt\ncom/exteragram/messenger/plugins/xposed/PyMethodHook\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,185:1\n1#2:186\n*E\n"})
/* loaded from: classes.dex */
public final class PyMethodHook extends XC_MethodHook implements AutoCloseable {
    private final PyObject afterHook;
    private ArrayList<HookFilter> afterHookedFilters;
    private final PyObject beforeHook;
    private ArrayList<HookFilter> beforeHookedFilters;
    private volatile boolean closed;
    private volatile boolean disabled;
    private final String pluginId;
    private final PyObject pythonCallback;

    public PyMethodHook(String r2, PyObject r3) {
        this(r2, r3, true, true);
    }

    public PyMethodHook(String r7, PyObject r8, int r9) {
        this(r7, r8, r9, true, true);
    }

    public PyMethodHook(String r1, PyObject r2, int r3, boolean r4, boolean r5) {
        super(r3);
        this.beforeHookedFilters = new ArrayList();
        this.afterHookedFilters = new ArrayList();
        if (r2 == null) goto L6;
        this.pluginId = r1;
        this.pythonCallback = r2;
        this.beforeHook = getCallbackIfPresent(r2, PluginsConstants.Xposed.BEFORE_HOOKED_METHOD, r4);
        this.afterHook = getCallbackIfPresent(r2, PluginsConstants.Xposed.AFTER_HOOKED_METHOD, r5);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Python callback object cannot be null");
        throw null;
    }

    public PyMethodHook(String r2, PyObject r3, boolean r4, boolean r5) {
        this.beforeHookedFilters = new ArrayList();
        this.afterHookedFilters = new ArrayList();
        if (r3 == null) goto L6;
        this.pluginId = r2;
        this.pythonCallback = r3;
        this.beforeHook = getCallbackIfPresent(r3, PluginsConstants.Xposed.BEFORE_HOOKED_METHOD, r4);
        this.afterHook = getCallbackIfPresent(r3, PluginsConstants.Xposed.AFTER_HOOKED_METHOD, r5);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Python callback object cannot be null");
        throw null;
    }

    private final boolean executeFilters(ArrayList<HookFilter> r1, XC_MethodHook.MethodHookParam r2, boolean r3) {
        Iterator<HookFilter> r0 = r1.iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        if (r0.next().execute(r2, r3) == true) goto L4;
        return false;
    L9:
        return true;
    }

    private final PyObject getCallbackIfPresent(PyObject r1, String r2, boolean r3) {
        if (r3 == true) goto L4;
        return null;
    L4:
        if (r1.containsKey(r2) == true) goto L6;
        return null;
    L6:
        return (PyObject) r1.get(r2);
    }

    private final void handleHookError(String r6, Throwable r7) {
        if ((r7 instanceof PyException) == true) goto L5;
    L10:
        FileLog.e("Plugin '" + this.pluginId + "' crashed in " + r6 + ": " + r7.getMessage(), r7);
        return;
    L5:
        if (r7.getMessage() == null) goto L10;
        if (StringsKt.contains$default(r7.getMessage(), "closed", false, 2, null) == false) goto L10;
        this.disabled = true;
        FileLog.e("Attempted to call a closed PyObject callback in " + this.pluginId);
    }

    @Override // de.robv.android.xposed.XC_MethodHook
    public void afterHookedMethod(XC_MethodHook.MethodHookParam r4) {
        if (this.disabled == true) goto L21;
        PyObject r0 = this.afterHook;
        if (r0 != null) goto L9;
        return;
    L9:
        if (PluginsController.Companion.getInstance().isPluginActive$TMessagesProj(this.pluginId) == false) goto L23;
    L16:
        th = move-exception;
        handleHookError("afterHookedMethod", th);
        return;
    L12:
        if (executeFilters(this.afterHookedFilters, r4, false) == true) goto L14;
        return;
    L14:
        r0.call(new Object[]{r4});     // Catch: Throwable -> L16
        return;
    L23:
        return;
    }

    @Override // de.robv.android.xposed.XC_MethodHook
    public void beforeHookedMethod(XC_MethodHook.MethodHookParam r4) {
        if (this.disabled == true) goto L21;
        PyObject r0 = this.beforeHook;
        if (r0 != null) goto L9;
        return;
    L9:
        if (PluginsController.Companion.getInstance().isPluginActive$TMessagesProj(this.pluginId) == false) goto L23;
    L16:
        th = move-exception;
        handleHookError("beforeHookedMethod", th);
        return;
    L12:
        if (executeFilters(this.beforeHookedFilters, r4, true) == true) goto L14;
        return;
    L14:
        r0.call(new Object[]{r4});     // Catch: Throwable -> L16
        return;
    L23:
        return;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.closed == true) goto L13;
        this.closed = true;
        this.disabled = true;
        PyObject r0 = this.beforeHook;
        if (r0 == null) goto L8;
        r0.close();
    L8:
        PyObject r1 = this.afterHook;
        if (r1 == null) goto L12;
        r1.close();
        return;
    L12:
        return;
    }

    public final ArrayList<HookFilter> getAfterHookedFilters() {
        return this.afterHookedFilters;
    }

    public final ArrayList<HookFilter> getBeforeHookedFilters() {
        return this.beforeHookedFilters;
    }

    public final void setAfterHookedFilters(ArrayList<HookFilter> r1) {
        this.afterHookedFilters = r1;
    }

    public final void setBeforeHookedFilters(ArrayList<HookFilter> r1) {
        this.beforeHookedFilters = r1;
    }
}
