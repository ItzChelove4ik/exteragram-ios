package com.exteragram.messenger.plugins.hooks;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003J\b\u0010\f\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0016\u001a\u00020\bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;", "Lcom/exteragram/messenger/plugins/hooks/HookRecord;", "pluginId", _UrlKt.FRAGMENT_ENCODE_SET, "hookName", "matchSubstring", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.MenuItemProperties.PRIORITY, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/lang/String;ZI)V", "getPluginId", "getHookName", "getPriority", "isMatchSubstring", "cleanup", _UrlKt.FRAGMENT_ENCODE_SET, "matches", "criteria", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", "hashCode", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EventHookRecord implements HookRecord {
    private final String hookName;
    private final boolean matchSubstring;
    private final String pluginId;
    private final int priority;

    public EventHookRecord(String r1, String r2, boolean r3, int r4) {
        this.pluginId = r1;
        this.hookName = r2;
        this.matchSubstring = r3;
        this.priority = r4;
    }

    @Override // com.exteragram.messenger.plugins.hooks.HookRecord
    public void cleanup() {
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (Intrinsics.areEqual(EventHookRecord.class, r5.getClass()) == false) goto L17;
        EventHookRecord r52 = (EventHookRecord) r5;
        if (this.matchSubstring != r52.matchSubstring) goto L17;
        if (Intrinsics.areEqual(this.pluginId, r52.pluginId) == false) goto L17;
        if (Intrinsics.areEqual(this.hookName, r52.hookName) == false) goto L17;
        return true;
    }

    public final String getHookName() {
        return this.hookName;
    }

    public final String getPluginId() {
        return this.pluginId;
    }

    public final int getPriority() {
        return this.priority;
    }

    public int hashCode() {
        String r0 = this.pluginId;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r03 = r02 * 31;
        String r2 = this.hookName;
        if (r2 == null) goto L10;
        r1 = r2.hashCode();
    L10:
        return ((r03 + r1) * 31) + Boolean.hashCode(this.matchSubstring);
    L5:
        r02 = 0;
        goto L6
    }

    public final boolean isMatchSubstring() {
        return this.matchSubstring;
    }

    @Override // com.exteragram.messenger.plugins.hooks.HookRecord
    public boolean matches(Object r4) {
        if ((r4 instanceof String) == false) goto L5;
        String r42 = (String) r4;
    L7:
        if (r42 != null) goto L9;
        return false;
    L9:
        String r2 = this.hookName;
        if (r2 != null) goto L13;
        return false;
    L13:
        if (this.matchSubstring == false) goto L22;
        if (r2.length() > 0) goto L17;
    L20:
        return false;
    L17:
        if (StringsKt.contains$default(r42, r2, false, 2, null) == false) goto L20;
        return true;
    L22:
        return Intrinsics.areEqual(r2, r42);
    L5:
        r42 = null;
        goto L7
    }
}
