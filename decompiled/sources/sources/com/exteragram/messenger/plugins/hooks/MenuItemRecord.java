package com.exteragram.messenger.plugins.hooks;

import android.content.Context;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.utils.PyObjectUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u0001J\u0013\u0010%\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010'\u001a\u00020\u0018H\u0016J\u001c\u0010.\u001a\u00020\u000b2\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010/R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\tR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\tR\u0011\u0010\u001f\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u001d\u0010(\u001a\u0004\u0018\u00010)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b*\u0010+¨\u00060"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;", _UrlKt.FRAGMENT_ENCODE_SET, "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "pyData", "Lcom/chaquo/python/PyObject;", "<init>", "(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V", "getPluginId", "()Ljava/lang/String;", "removed", _UrlKt.FRAGMENT_ENCODE_SET, "itemId", "getItemId", "menuType", "getMenuType", "text", "getText", "onClickCallback", "getOnClickCallback", "()Lcom/chaquo/python/PyObject;", "iconName", "getIconName", "iconResId", _UrlKt.FRAGMENT_ENCODE_SET, "getIconResId", "()I", "subtext", "getSubtext", "conditionString", "getConditionString", PluginsConstants.MenuItemProperties.PRIORITY, "getPriority", "markRemoved", _UrlKt.FRAGMENT_ENCODE_SET, "executeClick", "contextData", "equals", "other", "hashCode", "compiledCondition", "Ljava/io/Serializable;", "getCompiledCondition", "()Ljava/io/Serializable;", "compiledCondition$delegate", "Lkotlin/Lazy;", "checkCondition", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMenuItemRecord.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MenuItemRecord.kt\ncom/exteragram/messenger/plugins/hooks/MenuItemRecord\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,143:1\n1#2:144\n*E\n"})
/* loaded from: classes.dex */
public final class MenuItemRecord {
    private final Lazy compiledCondition$delegate;
    private final String conditionString;
    private final String iconName;
    private final int iconResId;
    private final String itemId;
    private final String menuType;
    private final PyObject onClickCallback;
    private final String pluginId;
    private final int priority;
    private volatile boolean removed;
    private final String subtext;
    private final String text;

    public static Serializable $r8$lambda$WYcNbFqpjFZmRSNFMesyFPF1vow(MenuItemRecord r2) {
        String r22 = r2.conditionString;
        if (r22 != null) goto L5;
        return null;
    L5:
        if (r22.length() == 0) goto L15;
        return MVEL.compileExpression(r22);
    L9:
        e = move-exception;
        FileLog.e(e);
        return null;
    L15:
        return null;
    }

    public MenuItemRecord(String r5, PyObject r6) {
        this.pluginId = r5;
        this.menuType = PyObjectUtils.getString(r6, PluginsConstants.MenuItemProperties.MENU_TYPE, null, true);
        this.text = PyObjectUtils.getString(r6, "text", null, true);
        this.onClickCallback = r6.callAttr("get", new Object[]{"on_click"});
        String r52 = PyObjectUtils.getString(r6, PluginsConstants.MenuItemProperties.ITEM_ID, null, true);
        if (r52 != null) goto L5;
    L6:
        r52 = null;
    L7:
        if (r52 != null) goto L9;
        r52 = UUID.randomUUID().toString();
    L9:
        this.itemId = r52;
        String r53 = PyObjectUtils.getString(r6, "icon", null, true);
        this.iconName = r53;
        this.subtext = PyObjectUtils.getString(r6, "subtext", null, true);
        this.conditionString = PyObjectUtils.getString(r6, "condition", null, true);
        int r3 = 0;
        this.priority = PyObjectUtils.getInt(r6, PluginsConstants.MenuItemProperties.PRIORITY, 0, true);
        if (TextUtils.isEmpty(r53) == false) goto L23;
    L12:
        this.iconResId = r3;
        if (TextUtils.isEmpty(this.menuType) == false) goto L15;
    L20:
        f$$ExternalSyntheticBUOutline1.m("MenuItemRecord missing essential fields: menuType, text, or onClickCallback.");
        throw null;
    L15:
        if (TextUtils.isEmpty(this.text) == true) goto L20;
        if (this.onClickCallback == null) goto L20;
        this.compiledCondition$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new MenuItemRecord$$ExternalSyntheticLambda0(this));
        return;
    L23:
        Context r62 = ApplicationLoader.applicationContext;     // Catch: Exception -> L22
        r3 = r62.getResources().getIdentifier(r53, "drawable", r62.getPackageName());     // Catch: Exception -> L22
        goto L12
    L5:
        if (r52.length() != 0) goto L7;
        goto L6
    }

    private final Serializable getCompiledCondition() {
        return (Serializable) this.compiledCondition$delegate.getValue();
    }

    public final boolean checkCondition(Map<String, ? extends Object> r3) {
        String r0 = this.conditionString;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.length() == 0) goto L22;
        if (r3 == null) goto L23;
        Serializable r2 = getCompiledCondition();
        if (r2 != null) goto L20;
        return false;
    L20:
        Boolean r22 = (Boolean) MVEL.executeExpression(r2, r3, Boolean.TYPE);     // Catch: Exception -> L19
        if (r22 == null) goto L16;
        return r22.booleanValue();
    L16:
        return false;
    L23:
        return true;
    L22:
        return true;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (Intrinsics.areEqual(MenuItemRecord.class, r5.getClass()) == false) goto L15;
        MenuItemRecord r52 = (MenuItemRecord) r5;
        if (Intrinsics.areEqual(this.itemId, r52.itemId) == false) goto L15;
        if (Intrinsics.areEqual(this.pluginId, r52.pluginId) == false) goto L15;
        return true;
    }

    public final void executeClick(Object r3) {
        if (this.removed == false) goto L5;
        return;
    L5:
        if (PluginsController.Companion.getInstance().isPluginActive$TMessagesProj(this.pluginId) == false) goto L18;
        PyObject r2 = this.onClickCallback;     // Catch: Exception -> L11
        if (r2 == null) goto L17;
        r2.call(new Object[]{r3});     // Catch: Exception -> L11
        return;
    L17:
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public final String getConditionString() {
        return this.conditionString;
    }

    public final String getIconName() {
        return this.iconName;
    }

    public final int getIconResId() {
        return this.iconResId;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final String getMenuType() {
        return this.menuType;
    }

    public final PyObject getOnClickCallback() {
        return this.onClickCallback;
    }

    public final String getPluginId() {
        return this.pluginId;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final String getSubtext() {
        return this.subtext;
    }

    public final String getText() {
        return this.text;
    }

    public int hashCode() {
        return (this.itemId.hashCode() * 31) + this.pluginId.hashCode();
    }

    public final void markRemoved() {
        this.removed = true;
    }
}
