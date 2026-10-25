package com.exteragram.messenger.plugins.models;

import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001BY\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010#\u001a\u00020$H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/exteragram/messenger/plugins/models/SelectorSetting;", "Lcom/exteragram/messenger/plugins/models/SettingItem;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "text", "defaultValue", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, _UrlKt.FRAGMENT_ENCODE_SET, "icon", "onChangeCallback", "Lcom/chaquo/python/PyObject;", "onLongClickCallback", "linkAlias", "<init>", "(Ljava/lang/String;Ljava/lang/String;I[Ljava/lang/String;Ljava/lang/String;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getText", "setText", "getDefaultValue", "()I", "setDefaultValue", "(I)V", "getItems", "()[Ljava/lang/String;", "setItems", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "getOnChangeCallback", "()Lcom/chaquo/python/PyObject;", "setOnChangeCallback", "(Lcom/chaquo/python/PyObject;)V", "cleanup", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SelectorSetting extends SettingItem {
    private int defaultValue;
    private String[] items;
    private String key;
    private PyObject onChangeCallback;
    private String text;

    public SelectorSetting(String r2, String r3, int r4, String[] r5, String r6, PyObject r7, PyObject r8, String r9) {
        super(PluginsConstants.Settings.TYPE_SELECTOR, r6, r8, r9);
        this.key = r2;
        this.text = r3;
        this.defaultValue = r4;
        this.items = r5;
        this.onChangeCallback = r7;
    }

    @Override // com.exteragram.messenger.plugins.models.SettingItem
    public void cleanup() {
        super.cleanup();
        closeCallback(this.onChangeCallback);
        this.onChangeCallback = null;
    }

    public final int getDefaultValue() {
        return this.defaultValue;
    }

    public final String[] getItems() {
        return this.items;
    }

    public final String getKey() {
        return this.key;
    }

    public final PyObject getOnChangeCallback() {
        return this.onChangeCallback;
    }

    public final String getText() {
        return this.text;
    }

    public final void setDefaultValue(int r1) {
        this.defaultValue = r1;
    }

    public final void setItems(String[] r1) {
        this.items = r1;
    }

    public final void setKey(String r1) {
        this.key = r1;
    }

    public final void setOnChangeCallback(PyObject r1) {
        this.onChangeCallback = r1;
    }

    public final void setText(String r1) {
        this.text = r1;
    }
}
