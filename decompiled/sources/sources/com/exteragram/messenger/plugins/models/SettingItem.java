package com.exteragram.messenger.plugins.models;

import com.chaquo.python.PyObject;
import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B5\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0004J\b\u0010\u0019\u001a\u00020\u0017H\u0016J\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/exteragram/messenger/plugins/models/SettingItem;", _UrlKt.FRAGMENT_ENCODE_SET, "type", _UrlKt.FRAGMENT_ENCODE_SET, "icon", "onLongClickCallback", "Lcom/chaquo/python/PyObject;", "linkAlias", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "getIcon", "setIcon", "getOnLongClickCallback", "()Lcom/chaquo/python/PyObject;", "setOnLongClickCallback", "(Lcom/chaquo/python/PyObject;)V", "getLinkAlias", "setLinkAlias", "closeCallback", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "cleanup", "getLink", "pluginId", "prefix", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class SettingItem {
    private String icon;
    private String linkAlias;
    private PyObject onLongClickCallback;
    private String type;

    public SettingItem(String r1, String r2, PyObject r3, String r4) {
        this.type = r1;
        this.icon = r2;
        this.onLongClickCallback = r3;
        this.linkAlias = r4;
    }

    public /* synthetic */ SettingItem(String r2, String r3, PyObject r4, String r5, int r6, DefaultConstructorMarker r7) {
        if ((r6 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r6 & 4) == 0) goto L9;
        r4 = null;
    L9:
        if ((r6 & 8) == 0) goto L11;
        r5 = null;
    L11:
        this(r2, r3, r4, r5);
    }

    public void cleanup() {
        closeCallback(this.onLongClickCallback);
        this.onLongClickCallback = null;
    }

    public final void closeCallback(PyObject r1) {
        if (r1 == null) goto L8;
        r1.close();     // Catch: Exception -> L5
        return;
    L9:
        return;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getLink(String r2, String r3) {
        String r0 = this.linkAlias;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.length() == 0) goto L20;
        if (r2 != null) goto L9;
        return null;
    L9:
        if (r2.length() == 0) goto L21;
        String r1 = this.linkAlias;
        if (r3 == null) goto L16;
        r1 = r3 + ":" + r1;
    L16:
        return "https://t.me/exteraSettings?p=" + r2 + "&s=" + r1;
    L21:
        return null;
    L20:
        return null;
    }

    public final String getLinkAlias() {
        return this.linkAlias;
    }

    public final PyObject getOnLongClickCallback() {
        return this.onLongClickCallback;
    }

    public final String getType() {
        return this.type;
    }

    public final void setIcon(String r1) {
        this.icon = r1;
    }

    public final void setLinkAlias(String r1) {
        this.linkAlias = r1;
    }

    public final void setOnLongClickCallback(PyObject r1) {
        this.onLongClickCallback = r1;
    }

    public final void setType(String r1) {
        this.type = r1;
    }
}
