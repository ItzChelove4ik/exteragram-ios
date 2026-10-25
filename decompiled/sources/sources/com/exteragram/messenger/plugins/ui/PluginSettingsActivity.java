package com.exteragram.messenger.plugins.ui;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import com.exteragram.messenger.plugins.models.CustomSetting;
import com.exteragram.messenger.plugins.models.DividerSetting;
import com.exteragram.messenger.plugins.models.EditTextSetting;
import com.exteragram.messenger.plugins.models.HeaderSetting;
import com.exteragram.messenger.plugins.models.InputSetting;
import com.exteragram.messenger.plugins.models.SelectorSetting;
import com.exteragram.messenger.plugins.models.SettingItem;
import com.exteragram.messenger.plugins.models.SwitchSetting;
import com.exteragram.messenger.plugins.models.TextSetting;
import com.exteragram.messenger.plugins.ui.PluginSettingsTranslation;
import com.exteragram.messenger.plugins.ui.components.PluginEditTextCell;
import com.exteragram.messenger.plugins.ui.components.PluginTranslateButton;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.sun.jna.Callback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0005B\u001d\b\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nBA\b\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\u0011J\u0010\u0010&\u001a\u00020\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\tJ\b\u0010'\u001a\u00020\tH\u0016J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020+H\u0016J5\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00172\u0016\u0010/\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010100\"\u0004\u0018\u000101H\u0016¢\u0006\u0002\u00102J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0014J\u0010\u00107\u001a\u00020+2\u0006\u00108\u001a\u00020)H\u0016J\b\u00109\u001a\u00020+H\u0002J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020)H\u0002J\u0014\u0010<\u001a\u0004\u0018\u00010\t2\b\u0010=\u001a\u0004\u0018\u00010\tH\u0002J\u0006\u0010>\u001a\u00020+J(\u0010?\u001a\u00020+2\u0016\u0010@\u001a\u0012\u0012\u0004\u0012\u00020B0Aj\b\u0012\u0004\u0012\u00020B`C2\u0006\u0010D\u001a\u00020EH\u0014J0\u0010F\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0014J4\u0010M\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010N\u001a\u00020\t2\u0006\u0010O\u001a\u00020)2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020+0QH\u0002J0\u0010R\u001a\u00020)2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0014J \u0010S\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010N\u001a\u00020\tH\u0002J \u0010T\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010H\u001a\u0002042\u0006\u0010N\u001a\u00020\tH\u0002J\u0010\u0010U\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u000eH\u0002J\u0018\u0010V\u001a\u00020+2\u0006\u0010G\u001a\u00020B2\u0006\u0010W\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\t0!j\b\u0012\u0004\u0012\u00020\t`\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;", "<init>", "()V", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "targetSettingName", _UrlKt.FRAGMENT_ENCODE_SET, "(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V", "title", "settingsList", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/models/SettingItem;", "createSubFragmentCallback", "Lcom/chaquo/python/PyObject;", "(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V", "customTitle", "settingItems", "resetItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "targetSettingItemId", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Integer;", "settingsLinkPrefix", "translation", "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;", "getTranslation", "()Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;", "translation$delegate", "Lkotlin/Lazy;", "visibleTexts", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "translateItem", "translateButton", "Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;", "setSettingsLinkPrefix", "getTitle", "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentDestroy", _UrlKt.FRAGMENT_ENCODE_SET, "didReceivedNotification", "id", "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "onTranslationChanged", "failed", "onTranslateClick", "updateTranslateItem", "animated", "tr", "text", "checkTargetSetting", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "toggleSwitch", PluginsConstants.Settings.KEY, "newValue", "setViewChecked", "Lkotlin/Function1;", "onLongClick", "showStringInputDialog", "showSelectorDialog", "getStableId", "openSubFragmentNative", Callback.METHOD_NAME, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginSettingsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginSettingsActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,723:1\n1#2:724\n11693#3:725\n12040#3,3:726\n11693#3:731\n12040#3,3:732\n37#4,2:729\n*S KotlinDebug\n*F\n+ 1 PluginSettingsActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsActivity\n*L\n368#1:725\n368#1:726,3\n634#1:731\n634#1:732,3\n368#1:729,2\n*E\n"})
/* loaded from: classes.dex */
public final class PluginSettingsActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate, PluginSettingsTranslation.Listener {
    private PyObject createSubFragmentCallback;
    private String customTitle;
    private Plugin plugin;
    private ActionBarMenuItem resetItem;
    private List<? extends SettingItem> settingItems;
    private String settingsLinkPrefix;
    private Integer targetSettingItemId;
    private String targetSettingName;
    private PluginTranslateButton translateButton;
    private ActionBarMenuItem translateItem;
    private final Lazy translation$delegate;
    private final LinkedHashSet<String> visibleTexts;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$-pUp2ILhhcyv5WxvS1CSPTqdrFI, reason: not valid java name */
    public static Unit m1578$r8$lambda$pUp2ILhhcyv5WxvS1CSPTqdrFI(EditTextBoldCursor r0, Ref.ObjectRef r1, View r2, PluginSettingsActivity r3, String r4, InputSetting r5) {
        Editable r02 = r0.getText();
        if (r02 == null) goto L5;
        String r03 = r02.toString();
    L6:
        if (r03 != null) goto L8;
        r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        AlertDialog r12 = (AlertDialog) r1.element;
        if (r12 == null) goto L11;
        r12.dismiss();
    L11:
        ((TextCell) r2).setValue(r03, true);
        PluginsController.Companion.runOnPluginsQueue(new PluginSettingsActivity$$ExternalSyntheticLambda3(r3, r4, r03, r5));
        return Unit.INSTANCE;
    L5:
        r03 = null;
        goto L6
    }

    /* renamed from: $r8$lambda$0EPq7QsU6t-rt8nXerx8Tdh5Ql4, reason: not valid java name */
    public static void m1579$r8$lambda$0EPq7QsU6trt8nXerx8Tdh5Ql4(PluginSettingsActivity r1, String r2, String r3, InputSetting r4) {
        PluginsController r0 = PluginsController.Companion.getInstance();
        Plugin r12 = r1.plugin;
        if (r12 != null) goto L5;
        r12 = null;
    L5:
        r0.setPluginSettingAndTriggerOnChange(r12.getId(), r2, r3, r4.getOnChangeCallback());
    }

    /* renamed from: $r8$lambda$1-Ja_dxkLD7loik7O2cLHRMxUJg, reason: not valid java name */
    public static void m1580$r8$lambda$1Ja_dxkLD7loik7O2cLHRMxUJg(PluginSettingsActivity r3) {
        BulletinFactory r0 = BulletinFactory.of(r3);
        int r1 = R.raw.info;
        int r2 = R.string.ResetPluginSettings;
        Plugin r32 = r3.plugin;
        if (r32 != null) goto L5;
        r32 = null;
    L5:
        r0.createSimpleBulletin(r1, LocaleController.formatString(r2, new Object[]{r32.getName()})).show();
    }

    /* renamed from: $r8$lambda$1DSLgS5vJm0OSFsCSJZFD5-2nto, reason: not valid java name */
    public static void m1581$r8$lambda$1DSLgS5vJm0OSFsCSJZFD52nto(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static void $r8$lambda$6Z6uxA2UOxlGL4PYCk_0i6kuoQo(PluginSettingsActivity r4, PyObject r5, UItem r6) {
        PluginsController.Companion r0 = PluginsController.Companion;
        PluginsController r1 = r0.getInstance();
        Plugin r2 = r4.plugin;
        PythonPluginsEngine r3 = null;
        if (r2 != null) goto L6;
        r2 = null;
    L6:
        if (r1.isPluginActive$TMessagesProj(r2) == true) goto L23;
        return;
    L23:
        PyObject r12 = r5.call(new Object[0]);     // Catch: Exception -> L20
        if (r12 == null) goto L16;
        PluginsController.PluginsEngine r02 = r0.getEngines().get(PluginsConstants.PYTHON);     // Catch: Exception -> L20
        if ((r02 instanceof PythonPluginsEngine) == false) goto L15;
        r3 = (PythonPluginsEngine) r02;     // Catch: Exception -> L20
    L15:
        if (r3 == null) goto L25;
        AndroidUtilities.runOnUIThread(new PluginSettingsActivity$$ExternalSyntheticLambda18(r3.parsePySettingDefinitions(r12.asList()), r6, r4, r5));
        return;
    L25:
        return;
    L16:
        return;
    L20:
        e = move-exception;
        FileLog.e("Error opening subfragment", e);
    }

    /* renamed from: $r8$lambda$9pY5TzlHVvCQHIyjDvEtTS9-eys, reason: not valid java name */
    public static void m1582$r8$lambda$9pY5TzlHVvCQHIyjDvEtTS9eys(PluginSettingsActivity r4, PyObject r5) {
        PluginsController.Companion r0 = PluginsController.Companion;
        PluginsController r1 = r0.getInstance();
        Plugin r2 = r4.plugin;
        PythonPluginsEngine r3 = null;
        if (r2 != null) goto L6;
        r2 = null;
    L6:
        if (r1.isPluginActive$TMessagesProj(r2) == true) goto L21;
        return;
    L21:
        PyObject r52 = r5.call(new Object[0]);     // Catch: Exception -> L20
        if (r52 == null) goto L23;
        PluginsController.PluginsEngine r02 = r0.getEngines().get(PluginsConstants.PYTHON);     // Catch: Exception -> L20
        if ((r02 instanceof PythonPluginsEngine) == false) goto L15;
        r3 = (PythonPluginsEngine) r02;     // Catch: Exception -> L20
    L15:
        if (r3 == null) goto L24;
        AndroidUtilities.runOnUIThread(new PluginSettingsActivity$$ExternalSyntheticLambda14(r4, r3.parsePySettingDefinitions(r52.asList())));
        return;
    L24:
        return;
    L23:
        return;
    }

    /* renamed from: $r8$lambda$Ce6DjGUz1AF3cZ-vPIAbGAMv1I8, reason: not valid java name */
    public static void m1583$r8$lambda$Ce6DjGUz1AF3cZvPIAbGAMv1I8(PluginSettingsActivity r0, ValueAnimator r1) {
        ActionBar r02 = r0.actionBar;
        if (r02 == null) goto L6;
        r02.requestLayout();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$FtON3Qfl_JLDF4AkVSaK3Vu--aU, reason: not valid java name */
    public static void m1584$r8$lambda$FtON3Qfl_JLDF4AkVSaK3VuaU(Ref.ObjectRef r0, View r1, List r2, int r3, PluginSettingsActivity r4, String r5, SelectorSetting r6, View r7) {
        Dialog r02 = (Dialog) r0.element;
        if (r02 == null) goto L5;
        r02.dismiss();
    L5:
        ((TextCell) r1).setValue((CharSequence) r2.get(r3), true);
        PluginsController.Companion.runOnPluginsQueue(new PluginSettingsActivity$$ExternalSyntheticLambda23(r4, r5, r3, r6));
    }

    public static void $r8$lambda$HBVQq8PwM6cRiVfKvot_s_07HcA(PluginSettingsActivity r1, String r2, int r3, SelectorSetting r4) {
        PluginsController r0 = PluginsController.Companion.getInstance();
        Plugin r12 = r1.plugin;
        if (r12 != null) goto L5;
        r12 = null;
    L5:
        r0.setPluginSettingAndTriggerOnChange(r12.getId(), r2, Integer.valueOf(r3), r4.getOnChangeCallback());
    }

    public static void $r8$lambda$JT5omKF85jr8iGVjIOakR0qPPKE(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        r0.setSelection(r0.length());
        AndroidUtilities.showKeyboard(r0);
    }

    public static Unit $r8$lambda$Mp2TFAjnIDnsFeYKm2SBjgKL0yY(View r0, boolean r1) {
        ((TextCheckCell) r0).setChecked(r1);
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$OFWPI4yzwIJOrUvW8Cepcz2YkHA(List r11, UItem r12, PluginSettingsActivity r13, PyObject r14) {
        if (r11.isEmpty() == true) goto L55;
        SettingItem r0 = r12.settingItem;
        if ((r0 instanceof TextSetting) == false) goto L7;
        TextSetting r02 = (TextSetting) r0;
    L8:
        if (r02 == null) goto L12;
        Object r03 = r02.getText();
        if (r03 == null) goto L12;
    L13:
        if (r03 == null) goto L23;
        String r04 = r03.toString();
        if (r04 == null) goto L23;
        if (r04.length() > 0) goto L20;
        r04 = null;
    L20:
        if (r04 == null) goto L23;
    L22:
        String r5 = r04;
        String r05 = r13.settingsLinkPrefix;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r05 != null) goto L32;
    L42:
        SettingItem r122 = r12.settingItem;
        if (r122 == null) goto L45;
        String r123 = r122.getLinkAlias();
    L46:
        if (r123 == null) goto L49;
        r1 = r123;
    L49:
        Plugin r124 = r13.plugin;
        if (r124 != null) goto L52;
        Plugin r4 = null;
    L53:
        r13.presentFragment(new PluginSettingsActivity(r4, r5, r11, r14, null, 16, null).setSettingsLinkPrefix(r1));
        return;
    L52:
        r4 = r124;
        goto L53
    L45:
        r123 = null;
        goto L46
    L32:
        if (r05.length() == 0) goto L42;
        String r06 = r13.settingsLinkPrefix;
        SettingItem r125 = r12.settingItem;
        if (r125 == null) goto L37;
        String r126 = r125.getLinkAlias();
    L38:
        if (r126 == null) goto L41;
        r1 = r126;
    L41:
        r1 = r06 + ":" + r1;
        goto L49
    L37:
        r126 = null;
    L23:
        r04 = r13.customTitle;
        if (r04 != null) goto L22;
        Plugin r07 = r13.plugin;
        if (r07 != null) goto L28;
        r07 = null;
    L28:
        r04 = r07.getName();
    L12:
        r03 = r12.text;
        goto L13
    L7:
        r02 = null;
        goto L8
    }

    public static void $r8$lambda$PhH2ExfyC8JNHIRVvp74w06bowU(PluginSettingsActivity r6) {
        PluginsController.Companion r0 = PluginsController.Companion;
        PluginsController r1 = r0.getInstance();
        Plugin r2 = r6.plugin;
        Plugin r3 = null;
        if (r2 != null) goto L5;
        r2 = null;
    L5:
        PluginsController.clearPluginSettingsPreferences$default(r1, r2.getId(), false, 2, null);
        PluginsController r02 = r0.getInstance();
        Plugin r12 = r6.plugin;
        if (r12 == null) goto L9;
        r3 = r12;
    L9:
        r02.loadPluginSettings(r3.getId());
        AndroidUtilities.runOnUIThread(new PluginSettingsActivity$$ExternalSyntheticLambda6(r6));
    }

    public static void $r8$lambda$QtNmdLcrwqKVepYU6Vz2gsC7dA8(Function0 r0, AlertDialog r1, int r2) {
        r0.invoke();
    }

    public static Unit $r8$lambda$RxWgJDfcHix48N9hb55E5aPYwzo(View r0, boolean r1) {
        ((NotificationsCheckCell) r0).setChecked(r1);
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$T_NnNSIT5Jyk2zKST0nB2HCN7WE(PluginSettingsActivity r0, List r1) {
        r0.settingItems = r1;
        UniversalAdapter r02 = r0.getListView$TMessagesProj().adapter;
        if (r02 == null) goto L6;
        r02.update(true);
        return;
    }

    /* renamed from: $r8$lambda$UzH2mCmxw9lvEAd71iI0aCVth-U, reason: not valid java name */
    public static void m1585$r8$lambda$UzH2mCmxw9lvEAd71iI0aCVthU(ActionBarMenuItem r3, PluginSettingsActivity r4, View r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3.getContext(), r4.resourceProvider);
        r0.setTitle(LocaleController.getString(R.string.ResetSettings));
        int r32 = R.string.ResetPluginSettingsInfo;
        Plugin r1 = r4.plugin;
        TextView r2 = null;
        if (r1 != null) goto L5;
        r1 = null;
    L5:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(r32, new Object[]{r1.getName()})));
        r0.setPositiveButton(LocaleController.getString(R.string.Reset), new PluginSettingsActivity$$ExternalSyntheticLambda1(r4, r5));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r33 = r0.create();
        r4.showDialog(r33);
        View r34 = r33.getButton(-1);
        if ((r34 instanceof TextView) == false) goto L8;
        r2 = (TextView) r34;
    L8:
        if (r2 == null) goto L11;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    public static void $r8$lambda$VrbycZPHCdbIObj6EGdZ3pXZPkI(PluginSettingsActivity r0, ValueAnimator r1) {
        ActionBar r02 = r0.actionBar;
        if (r02 == null) goto L6;
        r02.requestLayout();
        return;
    }

    public static void $r8$lambda$gAEBM3Bjop7AmlJJRHsVjP6iksk(PluginSettingsActivity r1, String r2, boolean r3, PyObject r4) {
        PluginsController r0 = PluginsController.Companion.getInstance();
        Plugin r12 = r1.plugin;
        if (r12 != null) goto L5;
        r12 = null;
    L5:
        r0.setPluginSettingAndTriggerOnChange(r12.getId(), r2, Boolean.valueOf(r3), r4);
    }

    public static void $r8$lambda$h03KbGNTNb1OtVtW90IDgFBXz1A(PluginSettingsActivity r7, View r8, AlertDialog r9, int r10) {
        View r92 = r7.fragmentView;
        if (r92 == null) goto L7;
        View r93 = r92.findFocus();
        if (r93 == null) goto L7;
        r93.clearFocus();
    L7:
        AndroidUtilities.updateViewVisibilityAnimated(r8, false, 0.5f, true, 1.0f, true, new PluginSettingsActivity$$ExternalSyntheticLambda16(r7));
        PluginsController.Companion.runOnPluginsQueue(new PluginSettingsActivity$$ExternalSyntheticLambda17(r7));
    }

    public static void $r8$lambda$kwCmZ5TLqkavBSYxn6tyCG3apKs(PluginSettingsActivity r0, View r1) {
        r0.onTranslateClick();
    }

    public static void $r8$lambda$wxlGKayieU74hzvWkJtTGgqkxNw(PluginSettingsActivity r0, ValueAnimator r1) {
        ActionBar r02 = r0.actionBar;
        if (r02 == null) goto L6;
        r02.requestLayout();
        return;
    }

    /* renamed from: $r8$lambda$xCyMslpbmlOrWwsP0i6G-lhGMtE, reason: not valid java name */
    public static PluginSettingsTranslation m1586$r8$lambda$xCyMslpbmlOrWwsP0i6GlhGMtE(PluginSettingsActivity r1) {
        PluginSettingsTranslation.Companion r0 = PluginSettingsTranslation.Companion;
        Plugin r12 = r1.plugin;
        if (r12 != null) goto L6;
        r12 = null;
    L6:
        return r0.of(r12.getId());
    }

    public static void $r8$lambda$yxSicdVfS2sImvkcDsBjw3ufT3w(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public PluginSettingsActivity() {
        this.translation$delegate = LazyKt.lazy(new PluginSettingsActivity$$ExternalSyntheticLambda15(this));
        this.visibleTexts = new LinkedHashSet();
    }

    @JvmOverloads
    public PluginSettingsActivity(Plugin r3) {
        this(r3, null, 2, null);
    }

    @JvmOverloads
    public PluginSettingsActivity(Plugin r1, String r2) {
        this();
        this.plugin = r1;
        this.targetSettingName = r2;
    }

    public /* synthetic */ PluginSettingsActivity(Plugin r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    @JvmOverloads
    public PluginSettingsActivity(Plugin r9, String r10, List<? extends SettingItem> r11, PyObject r12) {
        this(r9, r10, r11, r12, null, 16, null);
    }

    @JvmOverloads
    public PluginSettingsActivity(Plugin r1, String r2, List<? extends SettingItem> r3, PyObject r4, String r5) {
        this();
        this.plugin = r1;
        this.customTitle = r2;
        this.settingItems = r3;
        this.createSubFragmentCallback = r4;
        this.targetSettingName = r5;
    }

    public /* synthetic */ PluginSettingsActivity(Plugin r7, String r8, List r9, PyObject r10, String r11, int r12, DefaultConstructorMarker r13) {
        if ((r12 & 16) == 0) goto L5;
        r11 = null;
    L5:
        this(r7, r8, r9, r10, r11);
    }

    private final int getStableId(SettingItem r2) {
        if ((r2 instanceof SwitchSetting) == false) goto L7;
        return Objects.hash(new Object[]{PluginsConstants.Settings.TYPE_SWITCH, ((SwitchSetting) r2).getKey()});
    L7:
        if ((r2 instanceof InputSetting) == false) goto L11;
        return Objects.hash(new Object[]{PluginsConstants.Settings.TYPE_INPUT, ((InputSetting) r2).getKey()});
    L11:
        if ((r2 instanceof EditTextSetting) == false) goto L15;
        return Objects.hash(new Object[]{"edit", ((EditTextSetting) r2).getKey()});
    L15:
        if ((r2 instanceof SelectorSetting) == false) goto L19;
        return Objects.hash(new Object[]{PluginsConstants.Settings.TYPE_SELECTOR, ((SelectorSetting) r2).getKey()});
    L19:
        if ((r2 instanceof HeaderSetting) == false) goto L23;
        return Objects.hash(new Object[]{PluginsConstants.Settings.TYPE_HEADER, ((HeaderSetting) r2).getText()});
    L23:
        if ((r2 instanceof DividerSetting) == false) goto L27;
        return Objects.hash(new Object[]{PluginsConstants.Settings.TYPE_DIVIDER, ((DividerSetting) r2).getText()});
    L27:
        if ((r2 instanceof TextSetting) == false) goto L31;
        return Objects.hash(new Object[]{"text", ((TextSetting) r2).getText()});
    L31:
        if ((r2 instanceof CustomSetting) == false) goto L46;
        CustomSetting r22 = (CustomSetting) r2;
        UItem r1 = r22.getItem();
        Integer r0 = null;
        if (r1 == null) goto L36;
        int r12 = r1.id;
    L35:
        Integer r13 = Integer.valueOf(r12);
    L40:
        PyObject r23 = r22.getFactoryArgs();
        if (r23 == null) goto L44;
        r0 = Integer.valueOf(r23.hashCode());
    L44:
        return Objects.hash(new Object[]{PluginsConstants.Settings.TYPE_CUSTOM, r13, r0});
    L36:
        CustomSetting.Factory<?> r14 = r22.getFactory();
        if (r14 == null) goto L39;
        r12 = r14.hashCode();
        goto L35
    L39:
        r13 = null;
        goto L40
    L46:
        return r2.hashCode();
    }

    private final PluginSettingsTranslation getTranslation() {
        return (PluginSettingsTranslation) this.translation$delegate.getValue();
    }

    private final void onTranslateClick() {
        if (getTranslation().isLoading() == true) goto L6;
        getTranslation().setEnabled(!getTranslation().getEnabled());
        return;
    }

    private final void openSubFragmentNative(UItem r3, PyObject r4) {
        PluginsController.Companion.runOnPluginsQueue(new PluginSettingsActivity$$ExternalSyntheticLambda19(this, r4, r3));
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, org.telegram.ui.ActionBar.AlertDialog] */
    private final void showSelectorDialog(UItem r17, View r18, String r19) {
        SettingItem r0 = r17.settingItem;
        if ((r0 instanceof SelectorSetting) == false) goto L5;
        SelectorSetting r7 = (SelectorSetting) r0;
    L6:
        if (r7 != null) goto L9;
        return;
    L9:
        if (getParentActivity() != null) goto L11;
        return;
    L11:
        Ref.ObjectRef r1 = new Ref.ObjectRef();
        LinearLayout r10 = new LinearLayout(getContext());
        r10.setOrientation(1);
        String[] r02 = r7.getItems();
        ArrayList r3 = new ArrayList(r02.length);
        int r2 = r02.length;
        int r4 = 0;
    L12:
        if (r4 >= r2) goto L17;
        String r6 = getTranslation().text(r02[r4]);
        if (r6 != null) goto L16;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L16:
        r3.add(r6);
        r4 = r4 + 1;
        goto L12
    L17:
        PluginsController r03 = PluginsController.Companion.getInstance();
        Plugin r22 = this.plugin;
        if (r22 != null) goto L20;
        r22 = null;
    L20:
        String r62 = r19;
        int r13 = r03.getPluginSettingInt(r22.getId(), r62, r7.getDefaultValue());
        int r14 = r3.size();
        int r42 = 0;
    L21:
        if (r42 >= r14) goto L27;
        RadioColorCell r15 = new RadioColorCell(getParentActivity());
        r15.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r15.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        CharSequence r04 = (CharSequence) r3.get(r42);
        if (r13 != r42) goto L25;
        boolean r23 = true;
    L26:
        r15.setTextAndValue(r04, r23);
        r15.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r10.addView(r15);
        r15.setOnClickListener(new PluginSettingsActivity$$ExternalSyntheticLambda20(r1, r18, r3, r42, this, r62, r7));
        r42 = r42 + 1;
        r62 = r19;
        goto L21
    L25:
        r23 = false;
        goto L26
    L27:
        AlertDialog.Builder r05 = new AlertDialog.Builder(getParentActivity());
        r05.setTitle(r17.text);
        r05.setView(r10);
        r05.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        ?? r06 = r05.create();
        r1.element = r06;
        showDialog((Dialog) r06);
        return;
    L5:
        r7 = null;
        goto L6
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [T, android.app.Dialog, org.telegram.ui.ActionBar.AlertDialog] */
    private final void showStringInputDialog(UItem r17, View r18, String r19) {
        SettingItem r1 = r17.settingItem;
        if ((r1 instanceof InputSetting) == false) goto L5;
        InputSetting r6 = (InputSetting) r1;
    L6:
        if (r6 != null) goto L9;
        return;
    L9:
        if (getParentActivity() != null) goto L11;
        return;
    L11:
        Ref.ObjectRef r2 = new Ref.ObjectRef();
        AlertDialog.Builder r7 = new AlertDialog.Builder(getContext(), this.resourceProvider);
        r7.setTitle(r17.text);
        LinearLayout r8 = new LinearLayout(getContext());
        r8.setOrientation(1);
        String r12 = r6.getSubtext();
        if (r12 == null) goto L14;
        TextView r5 = new TextView(getContext());
        r5.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourceProvider));
        r5.setTextSize(1, 16.0f);
        r5.setText(getTranslation().text(r12));
        r8.addView(r5, LayoutHelper.createLinear(-1, -2, 24.0f, 5.0f, 24.0f, 12.0f));
    L14:
        EditTextBoldCursor r13 = new EditTextBoldCursor(getContext());
        r13.lineYFix = true;
        r13.setTextSize(1, 18.0f);
        PluginsController r52 = PluginsController.Companion.getInstance();
        Plugin r9 = this.plugin;
        if (r9 != null) goto L17;
        r9 = null;
    L17:
        r13.setText(r52.getPluginSettingString(r9.getId(), r19, r6.getDefaultValue()));
        r13.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourceProvider));
        r13.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, this.resourceProvider));
        r13.setHintText(LocaleController.getString(R.string.EnterValue));
        r13.setFocusable(true);
        r13.setInputType(147457);
        int r0 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r13.setCursorColor(Theme.getColor(r0, this.resourceProvider));
        r13.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, this.resourceProvider), Theme.getColor(r0, this.resourceProvider), Theme.getColor(Theme.key_text_RedRegular, this.resourceProvider));
        r13.setBackground(null);
        r13.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        PluginSettingsActivity$$ExternalSyntheticLambda7 r02 = new PluginSettingsActivity$$ExternalSyntheticLambda7(r13, r2, r18, this, r19, r6);
        r8.addView(r13, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r7.makeCustomMaxHeight();
        r7.setView(r8);
        r7.setWidth(AndroidUtilities.dp(292.0f));
        r7.setPositiveButton(LocaleController.getString(R.string.Done), new PluginSettingsActivity$$ExternalSyntheticLambda8(r02));
        r7.setNegativeButton(LocaleController.getString(R.string.Cancel), new PluginSettingsActivity$$ExternalSyntheticLambda9());
        ?? r03 = r7.create();
        r03.setOnDismissListener(new PluginSettingsActivity$$ExternalSyntheticLambda10(r13));
        r03.setOnShowListener(new PluginSettingsActivity$$ExternalSyntheticLambda11(r13));
        r03.setDismissDialogByButtons(false);
        r2.element = r03;
        showDialog((Dialog) r03);
        return;
    L5:
        r6 = null;
        goto L6
    }

    private final void toggleSwitch(UItem r2, String r3, boolean r4, Function1<? super Boolean, Unit> r5) {
        r5.invoke(Boolean.valueOf(r4));
        r2.setChecked(r4);
        SettingItem r22 = r2.settingItem;
        PyObject r0 = null;
        if ((r22 instanceof SwitchSetting) == false) goto L5;
        SwitchSetting r23 = (SwitchSetting) r22;
    L6:
        if (r23 == null) goto L8;
        r0 = r23.getOnChangeCallback();
    L8:
        PluginsController.Companion.runOnPluginsQueue(new PluginSettingsActivity$$ExternalSyntheticLambda0(this, r3, r4, r0));
        return;
    L5:
        r23 = null;
        goto L6
    }

    private final String tr(String r2) {
        if (r2 == null) goto L5;
        this.visibleTexts.add(r2);
    L5:
        return getTranslation().text(r2);
    }

    private final void updateTranslateItem(boolean r10) {
        ActionBarMenuItem r0 = this.translateItem;
        int r7 = 1;
        if (getTranslation().isTranslatable() == false) goto L5;
    L8:
        boolean r1 = true;
    L9:
        AndroidUtilities.updateViewVisibilityAnimated(r0, r1, 0.5f, true, 1.0f, r10, new PluginSettingsActivity$$ExternalSyntheticLambda2(this));
        PluginTranslateButton r102 = this.translateButton;
        if (r102 != null) goto L12;
        return;
    L12:
        if (getTranslation().getEnabled() == true) goto L15;
        r7 = 0;
    L18:
        r102.setState(r7);
        ActionBarMenuItem r9 = this.translateItem;
        if (r9 == null) goto L23;
        r9.setContentDescription(r102.getContentDescription());
        return;
    L23:
        return;
    L15:
        if (getTranslation().isLoading() == true) goto L18;
        r7 = 2;
        goto L18
    L5:
        if (getTranslation().getEnabled() == true) goto L8;
        r1 = false;
        goto L9
    }

    public final void checkTargetSetting() {
        Integer r0 = this.targetSettingItemId;
        if (r0 == null) goto L6;
        scrollToItem(r0.intValue());
        this.targetSettingItemId = null;
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        View r0 = super.createView(r8);
        ActionBarMenu r1 = this.actionBar.createMenu();
        ActionBarMenuItem r2 = r1.addItem(1, 0);
        PluginTranslateButton r4 = new PluginTranslateButton(r8, Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourceProvider));
        r2.addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        this.translateButton = r4;
        r2.setContentDescription(r4.getContentDescription());
        AndroidUtilities.updateViewVisibilityAnimated(r2, false, 0.5f, false);
        r2.setOnClickListener(new PluginSettingsActivity$$ExternalSyntheticLambda12(this));
        this.translateItem = r2;
        if (this.createSubFragmentCallback != null) goto L8;
        ActionBarMenuItem r12 = r1.addItem(0, R.drawable.msg_reset);
        r12.setContentDescription(LocaleController.getString(R.string.Reset));
        PluginsController r22 = PluginsController.Companion.getInstance();
        Plugin r42 = this.plugin;
        if (r42 != null) goto L7;
        r42 = null;
    L7:
        AndroidUtilities.updateViewVisibilityAnimated(r12, r22.hasPluginSettingsPreferences(r42.getId()), 0.5f, false);
        r12.setTag(null);
        r12.setOnClickListener(new PluginSettingsActivity$$ExternalSyntheticLambda13(r12, this));
        this.resetItem = r12;
    L8:
        checkTargetSetting();
        updateTranslateItem(false);
        this.fragmentView = r0;
        return r0;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r9, int r10, Object... r11) {
        Plugin r0 = null;
        if (r9 != NotificationCenter.pluginSettingsRegistered) goto L31;
        Object r92 = ArraysKt.firstOrNull(r11);
        if ((r92 instanceof String) == false) goto L7;
        String r93 = (String) r92;
    L8:
        if (r93 == null) goto L15;
        Plugin r102 = this.plugin;
        if (r102 != null) goto L13;
        r102 = null;
    L13:
        if (Intrinsics.areEqual(r102.getId(), r93) == true) goto L15;
        return;
    L15:
        PyObject r94 = this.createSubFragmentCallback;
        if (r94 == null) goto L19;
        PluginsController.Companion.runOnPluginsQueue(new PluginSettingsActivity$$ExternalSyntheticLambda4(this, r94));
        return;
    L19:
        UniversalAdapter r95 = getListView$TMessagesProj().adapter;
        if (r95 == null) goto L22;
        r95.update(true);
    L22:
        ActionBarMenuItem r1 = this.resetItem;
        if (r1 == null) goto L51;
        PluginsController r96 = PluginsController.Companion.getInstance();
        Plugin r103 = this.plugin;
        if (r103 == null) goto L28;
        r0 = r103;
    L28:
        AndroidUtilities.updateViewVisibilityAnimated(r1, r96.hasPluginSettingsPreferences(r0.getId()), 0.5f, true, 1.0f, true, new PluginSettingsActivity$$ExternalSyntheticLambda5(this));
        return;
    L51:
        return;
    L7:
        r93 = null;
        goto L8
    L31:
        if (r9 != NotificationCenter.pluginSettingsUnregistered) goto L52;
        Object r97 = ArraysKt.firstOrNull(r11);
        if ((r97 instanceof String) == false) goto L35;
        String r98 = (String) r97;
    L36:
        if (r98 == null) goto L56;
        Plugin r104 = this.plugin;
        if (r104 != null) goto L42;
        r104 = null;
    L42:
        if (Intrinsics.areEqual(r104.getId(), r98) == false) goto L53;
        PluginsController r99 = PluginsController.Companion.getInstance();
        Plugin r105 = this.plugin;
        if (r105 == null) goto L48;
        r0 = r105;
    L48:
        if (r99.hasPluginSettings(r0.getId()) == true) goto L54;
        finishFragment();
        return;
    L54:
        return;
    L53:
        return;
    L56:
        return;
    L35:
        r98 = null;
        goto L36
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.CharSequence] */
    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r17, UniversalAdapter r18) {
        List r0 = this.settingItems;
        if (r0 != null) goto L8;
        PluginsController r02 = PluginsController.Companion.getInstance();
        Plugin r3 = this.plugin;
        if (r3 != null) goto L7;
        r3 = null;
    L7:
        r0 = r02.getPluginSettingsList(r3.getId());
    L8:
        List r32 = r0;
        if (r32 != null) goto L11;
        return;
    L11:
        if (r32.isEmpty() == true) goto L181;
        this.visibleTexts.clear();
        String r33 = this.customTitle;
        if (r33 != null) goto L19;
        Plugin r34 = this.plugin;
        if (r34 != null) goto L18;
        r34 = null;
    L18:
        r33 = r34.getName();
    L19:
        tr(r33);
        Iterator r35 = r0.iterator();
        boolean r5 = false;
    L21:
        if (r35.hasNext() == false) goto L160;
        SettingItem r7 = (SettingItem) r35.next();
        String r03 = r7.getIcon();
        if (r03 != null) goto L25;
    L30:
        int r04 = 0;
    L169:
        if ((r7 instanceof HeaderSetting) == false) goto L39;
        String r05 = ((HeaderSetting) r7).getText();     // Catch: Exception -> L36
        if (r05 == null) goto L144;
        UItem r06 = UItem.asHeader(tr(r05));     // Catch: Exception -> L36
        r06.settingItem = r7;     // Catch: Exception -> L36
    L145:
        if (r06 == null) goto L21;
        r06.id = getStableId(r7);
        SettingItem r6 = r06.settingItem;
        if (r6 == null) goto L149;
        String r62 = r6.getLinkAlias();
    L150:
        String r72 = this.targetSettingName;
        if (r72 != null) goto L153;
    L158:
        r17.add(r06);
        goto L21
    L153:
        if (r72.length() == 0) goto L158;
        if (Intrinsics.areEqual(r62, this.targetSettingName) == false) goto L158;
        this.targetSettingItemId = Integer.valueOf(r06.id);
        this.targetSettingName = null;
        r5 = true;
        goto L158
    L149:
        r62 = null;
    L144:
        r06 = null;
        goto L145
    L39:
        if ((r7 instanceof SwitchSetting) == false) goto L54;
        String r8 = ((SwitchSetting) r7).getKey();     // Catch: Exception -> L36
        String r9 = ((SwitchSetting) r7).getText();     // Catch: Exception -> L36
        if (r8 == null) goto L144;
        if (r9 == null) goto L144;
        PluginsController r10 = PluginsController.Companion.getInstance();     // Catch: Exception -> L36
        Plugin r11 = this.plugin;     // Catch: Exception -> L36
        if (r11 != null) goto L46;
        r11 = null;
    L46:
        boolean r102 = r10.getPluginSettingBoolean(r11.getId(), r8, ((SwitchSetting) r7).getDefaultValue());     // Catch: Exception -> L36
        UItem r92 = UItem.asCheck(0, tr(r9));     // Catch: Exception -> L36
        r92.setChecked(r102);     // Catch: Exception -> L36
        r92.drawLine = false;     // Catch: Exception -> L36
        String r103 = ((SwitchSetting) r7).getSubtext();     // Catch: Exception -> L36
        if (r103 == null) goto L49;
        r92.textValue = tr(r103);     // Catch: Exception -> L36
        r92.multiline = true;     // Catch: Exception -> L36
    L49:
        if (r04 == 0) goto L51;
        r92.iconResId = r04;     // Catch: Exception -> L36
    L51:
        r92.object2 = r8;     // Catch: Exception -> L36
        r92.settingItem = r7;     // Catch: Exception -> L36
    L52:
        r06 = r92;
        goto L145
    L54:
        if ((r7 instanceof InputSetting) == false) goto L66;
        String r82 = ((InputSetting) r7).getKey();     // Catch: Exception -> L36
        String r93 = ((InputSetting) r7).getText();     // Catch: Exception -> L36
        if (r82 == null) goto L144;
        if (r93 == null) goto L144;
        PluginsController r104 = PluginsController.Companion.getInstance();     // Catch: Exception -> L36
        Plugin r112 = this.plugin;     // Catch: Exception -> L36
        if (r112 != null) goto L61;
        r112 = null;
    L61:
        r92 = UItem.asButton(0, tr(r93), r104.getPluginSettingString(r112.getId(), r82, ((InputSetting) r7).getDefaultValue()));     // Catch: Exception -> L36
        tr(((InputSetting) r7).getSubtext());     // Catch: Exception -> L36
        if (r04 == 0) goto L64;
        r92.iconResId = r04;     // Catch: Exception -> L36
    L64:
        r92.object2 = r82;     // Catch: Exception -> L36
        r92.settingItem = r7;     // Catch: Exception -> L36
        goto L52
    L66:
        if ((r7 instanceof EditTextSetting) == true) goto L68;
        boolean r83 = r7 instanceof SelectorSetting;     // Catch: Exception -> L36
        String r94 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r83 == false) goto L109;
        String r84 = ((SelectorSetting) r7).getKey();     // Catch: Exception -> L36
        String r105 = ((SelectorSetting) r7).getText();     // Catch: Exception -> L36
        if (r84 == null) goto L144;
        if (r105 == null) goto L144;
        if (((SelectorSetting) r7).getItems().length != 0) goto L84;
        boolean r113 = true;
    L85:
        if (r113 == true) goto L144;
        PluginsController.Companion r114 = PluginsController.Companion;     // Catch: Exception -> L36
        PluginsController r12 = r114.getInstance();     // Catch: Exception -> L36
        Plugin r13 = this.plugin;     // Catch: Exception -> L36
        if (r13 != null) goto L89;
        r13 = null;
    L89:
        int r122 = r12.getPluginSettingInt(r13.getId(), r84, ((SelectorSetting) r7).getDefaultValue());     // Catch: Exception -> L36
        if (r122 >= 0) goto L92;
    L94:
        r122 = RangesKt.coerceIn(((SelectorSetting) r7).getDefaultValue(), ArraysKt.getIndices(((SelectorSetting) r7).getItems()));     // Catch: Exception -> L36
        PluginsController r115 = r114.getInstance();     // Catch: Exception -> L36
        Plugin r132 = this.plugin;     // Catch: Exception -> L36
        if (r132 != null) goto L97;
        r132 = null;
    L97:
        r115.setPluginSetting(r132.getId(), r84, Integer.valueOf(r122));     // Catch: Exception -> L36
    L98:
        String[] r116 = ((SelectorSetting) r7).getItems();     // Catch: Exception -> L36
        ArrayList r133 = new ArrayList(r116.length);     // Catch: Exception -> L36
        int r14 = r116.length;     // Catch: Exception -> L36
        int r15 = 0;
    L99:
        if (r15 >= r14) goto L104;
        String r63 = tr(r116[r15]);     // Catch: Exception -> L36
        if (r63 != null) goto L103;
        r63 = _UrlKt.FRAGMENT_ENCODE_SET;
    L103:
        r133.add(r63);     // Catch: Exception -> L36
        r15 = r15 + 1;     // Catch: Exception -> L36
        goto L99
    L104:
        String[] r64 = (String[]) r133.toArray(new String[0]);     // Catch: Exception -> L36
        r92 = UItem.asButton(0, tr(r105), r64[r122]);     // Catch: Exception -> L36
        r92.texts = r64;     // Catch: Exception -> L36
        r92.intValue = r122;     // Catch: Exception -> L36
        if (r04 == 0) goto L107;
        r92.iconResId = r04;     // Catch: Exception -> L36
    L107:
        r92.object2 = r84;     // Catch: Exception -> L36
        r92.settingItem = r7;     // Catch: Exception -> L36
        goto L52
    L92:
        if (r122 >= ((SelectorSetting) r7).getItems().length) goto L94;
    L84:
        r113 = false;
        goto L85
    L109:
        if ((r7 instanceof TextSetting) == false) goto L121;
        UItem r65 = UItem.asButton(0, tr(((TextSetting) r7).getText()));     // Catch: Exception -> L36
        r65.settingItem = r7;     // Catch: Exception -> L36
        if (r04 == 0) goto L113;
        r65.iconResId = r04;     // Catch: Exception -> L36
    L113:
        r65.accent = ((TextSetting) r7).getAccent();     // Catch: Exception -> L36
        r65.red = ((TextSetting) r7).getRed();     // Catch: Exception -> L36
        String r07 = ((TextSetting) r7).getSubtext();     // Catch: Exception -> L36
        if (r07 != null) goto L116;
    L119:
        r06 = r65;
        goto L145
    L116:
        if (r07.length() == 0) goto L119;
        r65.subtext = tr(((TextSetting) r7).getSubtext());     // Catch: Exception -> L36
        r65.intValue = 60;     // Catch: Exception -> L36
        goto L119
    L121:
        if ((r7 instanceof DividerSetting) == false) goto L130;
        String r08 = tr(((DividerSetting) r7).getText());     // Catch: Exception -> L36
        if (r08 == null) goto L128;
        ?? r09 = LocaleUtils.fullyFormatText(r08, this, null);     // Catch: Exception -> L36
        if (r09 == 0) goto L128;
        r94 = r09;
    L128:
        r06 = UItem.asShadow(r94);     // Catch: Exception -> L36
        goto L145
    L130:
        if ((r7 instanceof CustomSetting) == false) goto L144;
        r06 = ((CustomSetting) r7).getItem();     // Catch: Exception -> L36
        if (r06 != null) goto L140;
        CustomSetting.Factory<?> r010 = ((CustomSetting) r7).getFactory();     // Catch: Exception -> L36
        if (r010 == null) goto L139;
        Plugin r66 = this.plugin;     // Catch: Exception -> L36
        if (r66 != null) goto L138;
        r66 = null;
    L138:
        r06 = r010.create(r66, (CustomSetting) r7, ((CustomSetting) r7).getFactoryArgs());     // Catch: Exception -> L36
        goto L140
    L139:
        r06 = null;
    L140:
        if (r06 == null) goto L144;
        r06.settingItem = r7;     // Catch: Exception -> L36
        goto L145
    L68:
        if (((EditTextSetting) r7).getKey() == null) goto L144;
        if (((EditTextSetting) r7).getHint() == null) goto L144;
        PluginEditTextCell.Factory.Companion r011 = PluginEditTextCell.Factory.Companion;     // Catch: Exception -> L36
        Plugin r85 = this.plugin;     // Catch: Exception -> L36
        if (r85 != null) goto L74;
        r85 = null;
    L74:
        r06 = r011.as(r85, (EditTextSetting) r7);     // Catch: Exception -> L36
        r06.text = tr(((EditTextSetting) r7).getHint());     // Catch: Exception -> L36
    L36:
        e = move-exception;
        Log.e("PluginSettings", "Error creating item", e);
        goto L144
    L25:
        if (r03.length() > 0) goto L28;
        r03 = null;
    L28:
        if (r03 == null) goto L30;
        Context r86 = ApplicationLoader.applicationContext;
        r04 = r86.getResources().getIdentifier(r03, "drawable", r86.getPackageName());
        goto L169
    L160:
        if (r5 == true) goto L167;
        String r012 = this.targetSettingName;
        if (r012 == null) goto L167;
        if (r012.length() == 0) goto L167;
        SettingsRegistry.getInstance().onSettingNotFound(this);
        this.targetSettingName = null;
    L167:
        getTranslation().check(this.visibleTexts);
        updateTranslateItem(true);
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        PluginSettingsTranslation r0 = getTranslation();
        String r1 = this.customTitle;
        if (r1 != null) goto L8;
        Plugin r2 = this.plugin;
        if (r2 != null) goto L7;
        r2 = null;
    L7:
        r1 = r2.getName();
    L8:
        String r22 = r0.text(r1);
        if (r22 != null) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return r22;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r2, View r3, int r4, float r5, float r6) {
        PluginsController r42 = PluginsController.Companion.getInstance();
        Plugin r52 = this.plugin;
        String r62 = null;
        Plugin r63 = null;
        if (r52 != null) goto L6;
        r52 = null;
    L6:
        if (r42.isPluginActive$TMessagesProj(r52) == false) goto L72;
        SettingItem r43 = r2.settingItem;
        if (r43 != null) goto L12;
        return;
    L12:
        if ((r43 instanceof TextSetting) == false) goto L23;
        TextSetting r44 = (TextSetting) r43;
        PyObject r53 = r44.getCreateSubFragmentCallback();
        if (r53 == null) goto L17;
        openSubFragmentNative(r2, r53);
        return;
    L17:
        PyObject r1 = r44.getOnClickCallback();
        if (r1 == null) goto L67;
        r1.call(new Object[]{r3});     // Catch: Exception -> L21
        return;
    L21:
        Unit r12 = Unit.INSTANCE;
        return;
    L67:
        return;
    L23:
        if ((r43 instanceof CustomSetting) == false) goto L40;
        PyObject r54 = ((CustomSetting) r43).getCreateSubFragmentCallback();
        if (r54 == null) goto L64;
        openSubFragmentNative(r2, r54);
        return;
    L64:
        CustomSetting.Factory<?> r55 = ((CustomSetting) r43).getFactory();     // Catch: Exception -> L63
        if (r55 == null) goto L36;
        Plugin r13 = this.plugin;     // Catch: Exception -> L63
        if (r13 == null) goto L34;
        r63 = r13;
    L34:
        r55.onClick(r63, r2, r3);     // Catch: Exception -> L63
        return;
    L36:
        PyObject r14 = ((CustomSetting) r43).getOnClickCallback();     // Catch: Exception -> L63
        if (r14 == null) goto L69;
        r14.call(new Object[]{r3});     // Catch: Exception -> L63
        return;
    L69:
        return;
    L76:
        return;
    L40:
        Object r56 = r2.object2;
        if ((r56 instanceof String) == false) goto L43;
        r62 = (String) r56;
    L43:
        if (r62 != null) goto L46;
        return;
    L46:
        if ((r3 instanceof TextCheckCell) == false) goto L50;
        toggleSwitch(r2, r62, !((TextCheckCell) r3).isChecked(), new PluginSettingsActivity$$ExternalSyntheticLambda21(r3));
        return;
    L50:
        if ((r3 instanceof NotificationsCheckCell) == false) goto L54;
        toggleSwitch(r2, r62, !((NotificationsCheckCell) r3).isChecked(), new PluginSettingsActivity$$ExternalSyntheticLambda22(r3));
        return;
    L54:
        if ((r3 instanceof TextCell) == true) goto L56;
        return;
    L56:
        if ((r43 instanceof SelectorSetting) == false) goto L60;
        showSelectorDialog(r2, r3, r62);
        return;
    L60:
        if ((r43 instanceof InputSetting) == false) goto L71;
        showStringInputDialog(r2, r3, r62);
        return;
    L71:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginSettingsRegistered);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginSettingsUnregistered);
        getTranslation().addListener(this);
        getTranslation().resetFailed();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginSettingsRegistered);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginSettingsUnregistered);
        getTranslation().removeListener(this);
        super.onFragmentDestroy();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r4, View r5, int r6, float r7, float r8) {
        PluginsController r62 = PluginsController.Companion.getInstance();
        Plugin r72 = this.plugin;
        Plugin r82 = null;
        if (r72 != null) goto L6;
        r72 = null;
    L6:
        if (r62.isPluginActive$TMessagesProj(r72) == true) goto L8;
        return false;
    L8:
        SettingItem r63 = r4.settingItem;
        if (r63 != null) goto L11;
        return false;
    L11:
        PyObject r0 = r63.getOnLongClickCallback();
        if (r0 != null) goto L42;
        String r02 = r63.getLinkAlias();
        if (r02 == null) goto L28;
        if (r02.length() == 0) goto L28;
        Plugin r03 = this.plugin;
        if (r03 != null) goto L23;
        r03 = null;
    L23:
        String r04 = r63.getLink(r03.getId(), this.settingsLinkPrefix);
        if (r04 == null) goto L28;
        showCopyLinkOptions(r5, r04);
        return true;
    L28:
        if ((r63 instanceof CustomSetting) == false) goto L37;
        CustomSetting.Factory<?> r64 = ((CustomSetting) r63).getFactory();
        if (r64 == null) goto L37;
        Plugin r3 = this.plugin;     // Catch: Exception -> L38
        if (r3 == null) goto L35;
        r82 = r3;
    L35:
        r64.onLongClick(r82, r4, r5);     // Catch: Exception -> L38
    L36:
        return true;
    L37:
        return false;
    L42:
        r0.call(new Object[]{r5});     // Catch: Exception -> L39
    L14:
        return true;
    }

    @Override // com.exteragram.messenger.plugins.ui.PluginSettingsTranslation.Listener
    public void onTranslationChanged(boolean r4) {
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L5;
        r0.update(true);
    L5:
        ActionBar r02 = this.actionBar;
        if (r02 == null) goto L8;
        r02.setTitle(getTitle());
    L8:
        updateTranslateItem(true);
        if (r4 == true) goto L11;
        return;
    L11:
        if (isLastFragment() == false) goto L15;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
        return;
    }

    public final PluginSettingsActivity setSettingsLinkPrefix(String r1) {
        this.settingsLinkPrefix = r1;
        return this;
    }
}
