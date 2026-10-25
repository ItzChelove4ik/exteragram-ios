package com.exteragram.messenger.plugins.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.models.EditTextSetting;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@Metadata(d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0010\b\u0007\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0014J\b\u0010\u0018\u001a\u00020\u0013H\u0002J$\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0016H\u0002J\u0014\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u000bH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006$"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell;", "Lorg/telegram/ui/Cells/EditTextCell;", "context", "Landroid/content/Context;", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "pendingSaveRunnable", "Ljava/lang/Runnable;", "valueToSave", _UrlKt.FRAGMENT_ENCODE_SET, "currentSetting", "Lcom/exteragram/messenger/plugins/models/EditTextSetting;", "pluginId", "saveTextWatcher", "com/exteragram/messenger/plugins/ui/components/PluginEditTextCell$saveTextWatcher$1", "Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$saveTextWatcher$1;", "scheduleSave", _UrlKt.FRAGMENT_ENCODE_SET, "onFocusChanged", "focused", _UrlKt.FRAGMENT_ENCODE_SET, "onDetachedFromWindow", "flushPendingSave", "bind", "setting", PluginsConstants.Settings.HINT, _UrlKt.FRAGMENT_ENCODE_SET, "setWatchersEnabled", "enabled", "createInputFilter", "Landroid/text/InputFilter;", "maskRegex", "Factory", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"ViewConstructor"})
@SourceDebugExtension({"SMAP\nPluginEditTextCell.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginEditTextCell.kt\ncom/exteragram/messenger/plugins/ui/components/PluginEditTextCell\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,256:1\n1#2:257\n37#3,2:258\n*S KotlinDebug\n*F\n+ 1 PluginEditTextCell.kt\ncom/exteragram/messenger/plugins/ui/components/PluginEditTextCell\n*L\n157#1:258,2\n*E\n"})
/* loaded from: classes4.dex */
public final class PluginEditTextCell extends EditTextCell {
    public static final Companion Companion = null;
    private static final int SAVE_DEBOUNCE_MS = 750;
    private EditTextSetting currentSetting;
    private Runnable pendingSaveRunnable;
    private String pluginId;
    private final PluginEditTextCell$saveTextWatcher$1 saveTextWatcher;
    private String valueToSave;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SAVE_DEBOUNCE_MS", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J2\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0016H\u0016¨\u0006\u001c"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$Factory;", "Lorg/telegram/ui/Components/UItem$UItemFactory;", "Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell;", "<init>", "()V", "createView", "context", "Landroid/content/Context;", "listView", "Lorg/telegram/ui/Components/RecyclerListView;", "currentAccount", _UrlKt.FRAGMENT_ENCODE_SET, "classGuid", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "bindView", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.VIEW, "Landroid/view/View;", PluginsConstants.Settings.ITEM, "Lorg/telegram/ui/Components/UItem;", PluginsConstants.Settings.TYPE_DIVIDER, _UrlKt.FRAGMENT_ENCODE_SET, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "isClickable", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory extends UItem.UItemFactory<PluginEditTextCell> {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginEditTextCell$Factory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "as", "Lorg/telegram/ui/Components/UItem;", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "setting", "Lcom/exteragram/messenger/plugins/models/EditTextSetting;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final UItem as(Plugin r1, EditTextSetting r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.settingItem = r2;
                return r0;
            }
        }

        static {
            Companion = new Companion(null);
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @JvmStatic
        public static final UItem as(Plugin r1, EditTextSetting r2) {
            return Companion.as(r1, r2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            if ((r1 instanceof PluginEditTextCell) == true) goto L5;
            return;
        L5:
            if ((r2.settingItem instanceof EditTextSetting) == false) goto L11;
            Object r0 = r2.object;
            if ((r0 instanceof Plugin) == false) goto L12;
            PluginEditTextCell r12 = (PluginEditTextCell) r1;
            r12.bind(((Plugin) r0).getId(), (EditTextSetting) r2.settingItem, r2.text);
            r12.setDivider(r3);
            return;
        L12:
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public PluginEditTextCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new PluginEditTextCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static void $r8$lambda$IbrC_cSxtCiu_K4wjWo2euGo6o8(PluginEditTextCell r1, EditTextSetting r2, String r3, String r4, String r5) {
        r1.pendingSaveRunnable = null;
        PluginsController.Companion.runOnPluginsQueue(new PluginEditTextCell$$ExternalSyntheticLambda2(r2, r3, r4, r5));
    }

    public static void $r8$lambda$jcl0iUP5pG5MF2sgNmuVkCk7LY4(EditTextSetting r2, String r3, String r4, String r5) {
        if (r2.getMaxLength() > 0) goto L5;
    L7:
        PluginsController.Companion.getInstance().setPluginSettingAndTriggerOnChange(r4, r5, r3, r2.getOnChangeCallback());
        return;
    L5:
        if (r3.length() <= r2.getMaxLength()) goto L7;
        r3 = r3.substring(0, r2.getMaxLength());
        goto L7
    }

    /* renamed from: $r8$lambda$yi_0eYqsHGvmzLBjga-zYnBufaA, reason: not valid java name */
    public static CharSequence m1609$r8$lambda$yi_0eYqsHGvmzLBjgazYnBufaA(Pattern r2, CharSequence r3, int r4, int r5, Spanned r6, int r7, int r8) {
        StringBuilder r62 = new StringBuilder(r5 - r4);
        boolean r72 = true;
    L3:
        if (r4 >= r5) goto L14;
        char r82 = r3.charAt(r4);
        if (r2 == null) goto L9;
        Matcher r1 = r2.matcher(String.valueOf(r82));
        if (r1 == null) goto L9;
        boolean r12 = r1.matches();
    L10:
        if (r12 == false) goto L12;
        r62.append(r82);
    L13:
        r4 = r4 + 1;
        goto L3
    L12:
        r72 = false;
    L9:
        r12 = false;
        goto L10
    L14:
        if (r72 == false) goto L18;
        return null;
    L18:
        return r62.toString();
    }

    static {
        Companion = new Companion(null);
    }

    public PluginEditTextCell(Context r8, Theme.ResourcesProvider r9) {
        super(r8, null, false, false, -1, r9);
        PluginEditTextCell$saveTextWatcher$1 r7 = new PluginEditTextCell$saveTextWatcher$1(this);
        this.saveTextWatcher = r7;
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, r9));
        this.editText.addTextChangedListener(r7);
    }

    public static final /* synthetic */ void access$scheduleSave(PluginEditTextCell r0) {
        r0.scheduleSave();
    }

    public static final /* synthetic */ void access$setValueToSave$p(PluginEditTextCell r0, String r1) {
        r0.valueToSave = r1;
    }

    public static /* synthetic */ void bind$default(PluginEditTextCell r0, String r1, EditTextSetting r2, CharSequence r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = r2.getHint();
    L5:
        r0.bind(r1, r2, r3);
    }

    private final InputFilter createInputFilter(String r3) {
        Pattern r0 = null;
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return null;
    L5:
        if (r3 == null) goto L12;
        r0 = Pattern.compile(r3);     // Catch: PatternSyntaxException -> L8
    L8:
        e = move-exception;
        FileLog.e("Invalid mask for EditText: ".concat(r3), e);
        return null;
    L12:
        return new PluginEditTextCell$$ExternalSyntheticLambda1(r0);
    }

    private final void flushPendingSave() {
        Runnable r0 = this.pendingSaveRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        r0.run();
        this.pendingSaveRunnable = null;
        return;
    }

    private final void scheduleSave() {
        Runnable r0 = this.pendingSaveRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        EditTextSetting r02 = this.currentSetting;
        if (r02 == null) goto L9;
        String r03 = r02.getKey();
    L8:
        String r6 = r03;
        String r4 = this.valueToSave;
        String r5 = this.pluginId;
        EditTextSetting r3 = this.currentSetting;
        if (r6 == null) goto L18;
        if (r4 == null) goto L19;
        if (r5 == null) goto L20;
        if (r3 == null) goto L21;
        PluginEditTextCell$$ExternalSyntheticLambda0 r1 = new PluginEditTextCell$$ExternalSyntheticLambda0(this, r3, r4, r5, r6);
        this.pendingSaveRunnable = r1;
        AndroidUtilities.runOnUIThread(r1, 750);
        return;
    L21:
        return;
    L20:
        return;
    L19:
        return;
    L18:
        return;
    L9:
        r03 = null;
        goto L8
    }

    private final void setWatchersEnabled(boolean r3) {
        this.editText.removeTextChangedListener(this.saveTextWatcher);
        if (r3 == false) goto L6;
        this.editText.addTextChangedListener(this.saveTextWatcher);
        return;
    }

    @JvmOverloads
    public final void bind(String r7, EditTextSetting r8) {
        bind$default(this, r7, r8, null, 4, null);
    }

    @JvmOverloads
    public final void bind(String r7, EditTextSetting r8, CharSequence r9) {
        EditTextSetting r0 = this.currentSetting;
        if (r0 == null) goto L10;
        if (r0 == null) goto L6;
        String r02 = r0.getKey();
    L8:
        if (TextUtils.equals(r02, r8.getKey()) == false) goto L10;
        boolean r03 = true;
    L11:
        if (r03 == true) goto L13;
        flushPendingSave();
    L13:
        this.pluginId = r7;
        this.currentSetting = r8;
        setWatchersEnabled(false);
        setMultiline(r8.getMultiline());
        if (r8.getMultiline() == false) goto L16;
        int r3 = 131072;
    L17:
        int r32 = r3 | 573441;
        if (this.editText.getInputType() == r32) goto L21;
        this.editText.setInputType(r32);
    L21:
        if (r8.getMaxLength() <= 0) goto L23;
        setShowLimitWhenNear(r8.getMaxLength() / Math.min(r8.getMaxLength(), 4));
    L24:
        ArrayList r33 = new ArrayList();
        InputFilter r4 = createInputFilter(r8.getMask());
        if (r4 == null) goto L28;
        r33.add(r4);
    L28:
        if (r8.getMaxLength() <= 0) goto L30;
        r33.add(new InputFilter.LengthFilter(r8.getMaxLength()));
    L30:
        EditTextCaption r42 = this.editText;
        if (r33.isEmpty() == true) goto L33;
        InputFilter[] r2 = (InputFilter[]) r33.toArray(new InputFilter[0]);
    L34:
        r42.setFilters(r2);
        String r72 = PluginsController.Companion.getInstance().getPluginSettingString(r7, r8.getKey(), r8.getDefaultValue());
        if (TextUtils.equals(r72, String.valueOf(this.editText.getText())) == false) goto L37;
    L42:
        this.valueToSave = r72;
        if (r9 != null) goto L45;
        r9 = r8.getHint();
    L45:
        if (r9 == null) goto L47;
        this.editText.setHint(r9);
    L47:
        setWatchersEnabled(true);
        return;
    L37:
        if (this.editText.hasFocus() == false) goto L41;
        if (r03 == false) goto L41;
        setWatchersEnabled(true);
        return;
    L41:
        setText(r72);
        goto L42
    L33:
        r2 = new InputFilter[0];
        goto L34
    L23:
        setShowLimitWhenNear(-1);
        goto L24
    L16:
        r3 = 0;
        goto L17
    L6:
        r02 = null;
    L10:
        r03 = false;
        goto L11
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        flushPendingSave();
    }

    @Override // org.telegram.ui.Cells.EditTextCell
    public void onFocusChanged(boolean r1) {
        super.onFocusChanged(r1);
        if (r1 == true) goto L6;
        flushPendingSave();
        return;
    }
}
