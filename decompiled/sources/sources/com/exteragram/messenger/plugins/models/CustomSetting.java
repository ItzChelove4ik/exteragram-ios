package com.exteragram.messenger.plugins.models;

import android.view.View;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.Components.UItem;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001'B1\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\fB=\b\u0016\u0012\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\u000fBG\b\u0016\u0012\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\u0011B9\b\u0016\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\u0014J\b\u0010%\u001a\u00020&H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018¨\u0006("}, d2 = {"Lcom/exteragram/messenger/plugins/models/CustomSetting;", "Lcom/exteragram/messenger/plugins/models/SettingItem;", "onClickCallback", "Lcom/chaquo/python/PyObject;", "createSubFragmentCallback", "onLongClickCallback", "linkAlias", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", PluginsConstants.Settings.ITEM, "Lorg/telegram/ui/Components/UItem;", "(Lorg/telegram/ui/Components/UItem;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", PluginsConstants.Settings.FACTORY, "Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;", "(Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", "factoryArgs", "(Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", PluginsConstants.Settings.VIEW, "Landroid/view/View;", "(Landroid/view/View;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", "getOnClickCallback", "()Lcom/chaquo/python/PyObject;", "setOnClickCallback", "(Lcom/chaquo/python/PyObject;)V", "getCreateSubFragmentCallback", "setCreateSubFragmentCallback", "getItem", "()Lorg/telegram/ui/Components/UItem;", "setItem", "(Lorg/telegram/ui/Components/UItem;)V", "getFactory", "()Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;", "setFactory", "(Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;)V", "getFactoryArgs", "setFactoryArgs", "cleanup", _UrlKt.FRAGMENT_ENCODE_SET, "Factory", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CustomSetting extends SettingItem {
    private PyObject createSubFragmentCallback;
    private Factory<?> factory;
    private PyObject factoryArgs;
    private UItem item;
    private PyObject onClickCallback;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J \u0010\u0019\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0002H\u0016J\b\u0010\u001a\u001a\u00020\u0007H\u0016J\b\u0010\u001b\u001a\u00020\u0007H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;", "V", "Landroid/view/View;", "Lorg/telegram/ui/Components/UItem$UItemFactory;", "<init>", "()V", "isShadowValue", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "setShadowValue", "(Z)V", "isClickableValue", "setClickableValue", "create", "Lorg/telegram/ui/Components/UItem;", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "setting", "Lcom/exteragram/messenger/plugins/models/CustomSetting;", "args", "Lcom/chaquo/python/PyObject;", "onClick", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "onLongClick", "isShadow", "isClickable", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Factory<V extends View> extends UItem.UItemFactory<V> {
        private boolean isClickableValue;
        private boolean isShadowValue;

        public Factory() {
            this.isClickableValue = true;
        }

        public UItem create(Plugin r1, CustomSetting r2, PyObject r3) {
            return null;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return this.isClickableValue;
        }

        public final boolean isClickableValue() {
            return this.isClickableValue;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isShadow() {
            return this.isShadowValue;
        }

        public final boolean isShadowValue() {
            return this.isShadowValue;
        }

        public void onClick(Plugin r1, UItem r2, View r3) {
        }

        public void onLongClick(Plugin r1, UItem r2, View r3) {
        }

        public final void setClickableValue(boolean r1) {
            this.isClickableValue = r1;
        }

        public final void setShadowValue(boolean r1) {
            this.isShadowValue = r1;
        }
    }

    public CustomSetting(View r1, PyObject r2, PyObject r3, PyObject r4, String r5) {
        this(UItem.asCustom(r1), r2, r3, r4, r5);
    }

    private CustomSetting(PyObject r3, PyObject r4, PyObject r5, String r6) {
        super(PluginsConstants.Settings.TYPE_CUSTOM, null, r5, r6);
        this.onClickCallback = r3;
        this.createSubFragmentCallback = r4;
    }

    public CustomSetting(Factory<?> r7, PyObject r8, PyObject r9, PyObject r10, PyObject r11, String r12) {
        this(r7, r9, r10, r11, r12);
        this.factoryArgs = r8;
    }

    public CustomSetting(Factory<?> r1, PyObject r2, PyObject r3, PyObject r4, String r5) {
        this(r2, r3, r4, r5);
        this.factory = r1;
    }

    public CustomSetting(UItem r1, PyObject r2, PyObject r3, PyObject r4, String r5) {
        this(r2, r3, r4, r5);
        this.item = r1;
        r1.settingItem = this;
    }

    @Override // com.exteragram.messenger.plugins.models.SettingItem
    public void cleanup() {
        super.cleanup();
        closeCallback(this.onClickCallback);
        closeCallback(this.createSubFragmentCallback);
        closeCallback(this.factoryArgs);
        this.onClickCallback = null;
        this.createSubFragmentCallback = null;
        this.factoryArgs = null;
    }

    public final PyObject getCreateSubFragmentCallback() {
        return this.createSubFragmentCallback;
    }

    public final Factory<?> getFactory() {
        return this.factory;
    }

    public final PyObject getFactoryArgs() {
        return this.factoryArgs;
    }

    public final UItem getItem() {
        return this.item;
    }

    public final PyObject getOnClickCallback() {
        return this.onClickCallback;
    }

    public final void setCreateSubFragmentCallback(PyObject r1) {
        this.createSubFragmentCallback = r1;
    }

    public final void setFactory(Factory<?> r1) {
        this.factory = r1;
    }

    public final void setFactoryArgs(PyObject r1) {
        this.factoryArgs = r1;
    }

    public final void setItem(UItem r1) {
        this.item = r1;
    }

    public final void setOnClickCallback(PyObject r1) {
        this.onClickCallback = r1;
    }
}
