package com.exteragram.messenger.plugins.models;

import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001BI\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010%\u001a\u00020&H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/exteragram/messenger/plugins/models/EditTextSetting;", "Lcom/exteragram/messenger/plugins/models/SettingItem;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.HINT, "defaultValue", PluginsConstants.Settings.MULTILINE, _UrlKt.FRAGMENT_ENCODE_SET, "maxLength", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.MASK, "onChangeCallback", "Lcom/chaquo/python/PyObject;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;Lcom/chaquo/python/PyObject;)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getHint", "setHint", "getDefaultValue", "setDefaultValue", "getMultiline", "()Z", "setMultiline", "(Z)V", "getMaxLength", "()I", "setMaxLength", "(I)V", "getMask", "setMask", "getOnChangeCallback", "()Lcom/chaquo/python/PyObject;", "setOnChangeCallback", "(Lcom/chaquo/python/PyObject;)V", "cleanup", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EditTextSetting extends SettingItem {
    private String defaultValue;
    private String hint;
    private String key;
    private String mask;
    private int maxLength;
    private boolean multiline;
    private PyObject onChangeCallback;

    public EditTextSetting(String r8, String r9, String r10, boolean r11, int r12, String r13, PyObject r14) {
        super(PluginsConstants.Settings.TYPE_EDIT_TEXT, null, null, null, 14, null);
        this.key = r8;
        this.hint = r9;
        this.defaultValue = r10;
        this.multiline = r11;
        this.maxLength = r12;
        this.mask = r13;
        this.onChangeCallback = r14;
    }

    @Override // com.exteragram.messenger.plugins.models.SettingItem
    public void cleanup() {
        super.cleanup();
        closeCallback(this.onChangeCallback);
        this.onChangeCallback = null;
    }

    public final String getDefaultValue() {
        return this.defaultValue;
    }

    public final String getHint() {
        return this.hint;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getMask() {
        return this.mask;
    }

    public final int getMaxLength() {
        return this.maxLength;
    }

    public final boolean getMultiline() {
        return this.multiline;
    }

    public final PyObject getOnChangeCallback() {
        return this.onChangeCallback;
    }

    public final void setDefaultValue(String r1) {
        this.defaultValue = r1;
    }

    public final void setHint(String r1) {
        this.hint = r1;
    }

    public final void setKey(String r1) {
        this.key = r1;
    }

    public final void setMask(String r1) {
        this.mask = r1;
    }

    public final void setMaxLength(int r1) {
        this.maxLength = r1;
    }

    public final void setMultiline(boolean r1) {
        this.multiline = r1;
    }

    public final void setOnChangeCallback(PyObject r1) {
        this.onChangeCallback = r1;
    }
}
