package com.exteragram.messenger.plugins.models;

import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B]\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\"\u001a\u00020#H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001f¨\u0006$"}, d2 = {"Lcom/exteragram/messenger/plugins/models/TextSetting;", "Lcom/exteragram/messenger/plugins/models/SettingItem;", "text", _UrlKt.FRAGMENT_ENCODE_SET, "subtext", "icon", PluginsConstants.Settings.ACCENT, _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.RED, "onClickCallback", "Lcom/chaquo/python/PyObject;", "createSubFragmentCallback", "onLongClickCallback", "linkAlias", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "getSubtext", "setSubtext", "getAccent", "()Z", "setAccent", "(Z)V", "getRed", "setRed", "getOnClickCallback", "()Lcom/chaquo/python/PyObject;", "setOnClickCallback", "(Lcom/chaquo/python/PyObject;)V", "getCreateSubFragmentCallback", "setCreateSubFragmentCallback", "cleanup", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextSetting extends SettingItem {
    private boolean accent;
    private PyObject createSubFragmentCallback;
    private PyObject onClickCallback;
    private boolean red;
    private String subtext;
    private String text;

    public TextSetting(String r2, String r3, String r4, boolean r5, boolean r6, PyObject r7, PyObject r8, PyObject r9, String r10) {
        super("text", r4, r9, r10);
        this.text = r2;
        this.subtext = r3;
        this.accent = r5;
        this.red = r6;
        this.onClickCallback = r7;
        this.createSubFragmentCallback = r8;
    }

    @Override // com.exteragram.messenger.plugins.models.SettingItem
    public void cleanup() {
        super.cleanup();
        closeCallback(this.onClickCallback);
        closeCallback(this.createSubFragmentCallback);
        this.onClickCallback = null;
        this.createSubFragmentCallback = null;
    }

    public final boolean getAccent() {
        return this.accent;
    }

    public final PyObject getCreateSubFragmentCallback() {
        return this.createSubFragmentCallback;
    }

    public final PyObject getOnClickCallback() {
        return this.onClickCallback;
    }

    public final boolean getRed() {
        return this.red;
    }

    public final String getSubtext() {
        return this.subtext;
    }

    public final String getText() {
        return this.text;
    }

    public final void setAccent(boolean r1) {
        this.accent = r1;
    }

    public final void setCreateSubFragmentCallback(PyObject r1) {
        this.createSubFragmentCallback = r1;
    }

    public final void setOnClickCallback(PyObject r1) {
        this.onClickCallback = r1;
    }

    public final void setRed(boolean r1) {
        this.red = r1;
    }

    public final void setSubtext(String r1) {
        this.subtext = r1;
    }

    public final void setText(String r1) {
        this.text = r1;
    }
}
