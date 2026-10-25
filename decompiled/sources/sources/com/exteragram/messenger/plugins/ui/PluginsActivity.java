package com.exteragram.messenger.plugins.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.ui.components.EmptyPluginsView;
import com.exteragram.messenger.plugins.ui.components.PluginCell;
import com.exteragram.messenger.plugins.ui.components.PluginCellDelegate;
import com.exteragram.messenger.plugins.utils.PluginsWatchdog;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\bH\u0016J(\u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J0\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0014J\u0018\u0010'\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0018H\u0002J\b\u0010(\u001a\u00020\u0006H\u0016J\b\u0010)\u001a\u00020\u0015H\u0016J5\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020#2\u0016\u0010-\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010/0.\"\u0004\u0018\u00010/H\u0016¢\u0006\u0002\u00100J\u0010\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginsActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "searching", _UrlKt.FRAGMENT_ENCODE_SET, "query", _UrlKt.FRAGMENT_ENCODE_SET, "searchItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "infoItem", "emptyView", "Lcom/exteragram/messenger/plugins/ui/components/EmptyPluginsView;", "isSwitchingEngineState", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getTitle", "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "createPluginItem", "plugin", "Lcom/exteragram/messenger/plugins/Plugin;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "togglePluginsEngine", "onFragmentCreate", "onFragmentDestroy", "didReceivedNotification", "id", "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "onBackPressed", "invoked", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginsActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginsActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,406:1\n1#2:407\n*E\n"})
/* loaded from: classes.dex */
public final class PluginsActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    private EmptyPluginsView emptyView;
    private ActionBarMenuItem infoItem;
    private boolean isSwitchingEngineState;
    private String query;
    private ActionBarMenuItem searchItem;
    private boolean searching;

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/exteragram/messenger/plugins/ui/PluginsActivity$createPluginItem$1", "Lcom/exteragram/messenger/plugins/ui/components/PluginCellDelegate;", "sharePlugin", _UrlKt.FRAGMENT_ENCODE_SET, "openInExternalApp", "deletePlugin", "togglePlugin", PluginsConstants.Settings.VIEW, "Landroid/view/View;", "openPluginSettings", "pinPlugin", "canOpenInExternalApp", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.PluginsActivity$createPluginItem$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 implements PluginCellDelegate {
        final /* synthetic */ Plugin $plugin;
        final /* synthetic */ PluginsActivity this$0;

        public static void $r8$lambda$B78fwne4P6pAvuzj85JvNnMgcJ4(PluginsActivity r6, boolean r7, Plugin r8, PluginCell r9, String r10) {
            AndroidUtilities.runOnUIThread(new PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda1(r6, r10, r7, r8, r9));
        }

        /* renamed from: $r8$lambda$OKd-U1ejuz10Tc6xzLWoqFUhobw, reason: not valid java name */
        public static void m1591$r8$lambda$OKdU1ejuz10Tc6xzLWoqFUhobw(PluginsActivity r2, String r3, boolean r4, Plugin r5, PluginCell r6) {
            if (r2.fragmentView != null) goto L5;
            return;
        L5:
            if (r2.getParentActivity() == null) goto L17;
            if (r3 == null) goto L14;
            BulletinFactory r62 = BulletinFactory.of(r2);
            int r0 = R.raw.error;
            if (r4 == false) goto L11;
            int r42 = R.string.PluginEnableError;
        L12:
            r62.createSimpleBulletin(r0, LocaleController.formatString(r42, new Object[]{r5.getName()}), LocaleUtils.createCopySpan(r2), new PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda5(r3, r2)).show();
            return;
        L11:
            r42 = R.string.PluginDisableError;
            goto L12
        L14:
            r6.setChecked(r4, true);
            return;
        }

        /* renamed from: $r8$lambda$dCkz7DYSlhbIDttnEZkf-gb6Bks, reason: not valid java name */
        public static void m1592$r8$lambda$dCkz7DYSlhbIDttnEZkfgb6Bks(String r0, PluginsActivity r1) {
            if (AndroidUtilities.addToClipboard(r0) == false) goto L6;
            BulletinFactory.of(r1).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
            return;
        }

        /* renamed from: $r8$lambda$eJ4WLK9pRQm-_9sSE0sYfFNZu9w, reason: not valid java name */
        public static void m1593$r8$lambda$eJ4WLK9pRQm_9sSE0sYfFNZu9w(PluginsActivity r2, String r3) {
            if (r2.fragmentView != null) goto L5;
            return;
        L5:
            if (r2.getParentActivity() == null) goto L13;
            r2.getListView$TMessagesProj().adapter.update(true);
            if (r3 == null) goto L12;
            BulletinFactory.of(r2).createSimpleBulletin(R.raw.error, r3).show();
            return;
        L12:
            return;
        }

        public static void $r8$lambda$qDFeu64a6Hu0aGaLV5GG__Rbfe8(PluginsActivity r1, String r2) {
            AndroidUtilities.runOnUIThread(new PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda2(r1, r2));
        }

        public static void $r8$lambda$u3FIXrSeGwHHZjopYSc8LgAgO6g(Plugin r0, PluginsActivity r1, DialogInterface r2, int r3) {
            PluginsController.Companion.getInstance().deletePlugin(r0.getId(), new PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda0(r1));
        }

        public AnonymousClass1(Plugin r1, PluginsActivity r2) {
            this.$plugin = r1;
            this.this$0 = r2;
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public boolean canOpenInExternalApp() {
            PluginsController.PluginsEngine r1 = PluginsController.Companion.getInstance().getPluginEngine(this.$plugin.getId());
            if (r1 != null) goto L5;
            return false;
        L5:
            if (r1.canOpenInExternalApp() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public void deletePlugin() {
            if (this.$plugin.isNotResponding() == false) goto L6;
            PluginsWatchdog.Companion.showNotRespondingAlert(this.$plugin);
            return;
        L6:
            TextView r1 = null;
            AlertDialog r4 = new AlertDialog.Builder(this.this$0.getParentActivity(), PluginsActivity.m1590access$getResourceProvider$p$s1111628113(this.this$0)).setTitle(LocaleController.getString(R.string.PluginDelete)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PluginDeleteInfo, new Object[]{this.$plugin.getName()}))).setPositiveButton(LocaleController.getString(R.string.Delete), new PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda3(this.$plugin, this.this$0)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
            r4.show();
            View r42 = r4.getButton(-1);
            if ((r42 instanceof TextView) == false) goto L9;
            r1 = (TextView) r42;
        L9:
            if (r1 == null) goto L12;
            r1.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public void openInExternalApp() {
            PluginsController.PluginsEngine r0 = PluginsController.Companion.getInstance().getPluginEngine(this.$plugin.getId());
            if (r0 == null) goto L6;
            r0.openInExternalApp(this.$plugin.getId());
            return;
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public void openPluginSettings() {
            PluginsController.Companion.openPluginSettings(this.$plugin.getId());
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public void pinPlugin(View r5) {
            PluginsController.Companion r0 = PluginsController.Companion;
            boolean r1 = r0.isPluginPinned(this.$plugin.getId());
            r0.setPluginPinned(this.$plugin.getId(), !r1);
            ((PluginCell) r5).setPinned(!r1);
            this.this$0.getListView$TMessagesProj().adapter.update(true);
            this.this$0.getListView$TMessagesProj().smoothScrollToPosition(0);
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public void sharePlugin() {
            PluginsController.PluginsEngine r0 = PluginsController.Companion.getInstance().getPluginEngine(this.$plugin.getId());
            if (r0 == null) goto L6;
            r0.sharePlugin(this.$plugin.getId());
            return;
        }

        @Override // com.exteragram.messenger.plugins.ui.components.PluginCellDelegate
        public void togglePlugin(View r6) {
            if (this.$plugin.isNotResponding() == false) goto L6;
            PluginsWatchdog.Companion.showNotRespondingAlert(this.$plugin);
            return;
        L6:
            boolean r0 = !this.$plugin.isEnabled();
            PluginsController.Companion.getInstance().setPluginEnabled(this.$plugin.getId(), r0, new PluginsActivity$createPluginItem$1$$ExternalSyntheticLambda4(this.this$0, r0, this.$plugin, (PluginCell) r6));
        }
    }

    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/exteragram/messenger/plugins/ui/PluginsActivity$createView$1", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;", "onSearchExpand", _UrlKt.FRAGMENT_ENCODE_SET, "onSearchCollapse", "onTextChanged", "editText", "Landroid/widget/EditText;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.PluginsActivity$createView$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01571 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ PluginsActivity this$0;

        public C01571(PluginsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            PluginsActivity.access$setSearching$p(this.this$0, false);
            PluginsActivity.access$setQuery$p(this.this$0, null);
            this.this$0.getListView$TMessagesProj().adapter.update(true);
            this.this$0.getListView$TMessagesProj().scrollToPosition(0);
            ActionBarMenuItem r3 = PluginsActivity.access$getInfoItem$p(this.this$0);
            if (r3 == null) goto L6;
            r3.setVisibility(0);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            PluginsActivity.access$setSearching$p(this.this$0, true);
            this.this$0.getListView$TMessagesProj().adapter.update(true);
            this.this$0.getListView$TMessagesProj().scrollToPosition(0);
            ActionBarMenuItem r2 = PluginsActivity.access$getInfoItem$p(this.this$0);
            if (r2 == null) goto L6;
            r2.setVisibility(8);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            PluginsActivity.access$setQuery$p(this.this$0, r2.getText().toString());
            this.this$0.getListView$TMessagesProj().adapter.update(true);
            this.this$0.getListView$TMessagesProj().scrollToPosition(0);
        }
    }

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/exteragram/messenger/plugins/ui/PluginsActivity$createView$3", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", _UrlKt.FRAGMENT_ENCODE_SET, "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.PluginsActivity$createView$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PluginsActivity this$0;

        public AnonymousClass3(PluginsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.plugins.ui.PluginsActivity$fillItems$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Plugin, String> {
        public static final AnonymousClass2 INSTANCE = null;

        static {
            INSTANCE = new AnonymousClass2();
        }

        public AnonymousClass2() {
            super(1, Plugin.class, "getName", "getName()Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ String invoke(Plugin r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final String invoke2(Plugin r1) {
            return r1.getName();
        }
    }

    public static void $r8$lambda$87aeonAH2q9g0DvYVc2XzY_e4QU(PluginsActivity r1) {
        AndroidUtilities.runOnUIThread(new PluginsActivity$$ExternalSyntheticLambda0(r1));
    }

    public static String $r8$lambda$R2n2rGoh95VONEXx2uOtUlcXz0E(Function1 r0, Object r1) {
        return (String) r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$TCQsQa4X6XcL-GZNkkr3zfTdMj0, reason: not valid java name */
    public static void m1589$r8$lambda$TCQsQa4X6XcLGZNkkr3zfTdMj0(PluginsActivity r0, View r1) {
        r0.presentFragment(new PluginsInfoActivity());
    }

    public static boolean $r8$lambda$TEMUUXesJLeJaCYdM8utDdAnU1s(PluginsActivity r3, Plugin r4) {
        String r42 = r4.getName();
        Locale r0 = Locale.ROOT;
        return !StringsKt.contains$default(r42.toLowerCase(r0), r3.query.toLowerCase(r0), false, 2, null);
    }

    public static boolean $r8$lambda$dG5hHh6ZSdGRIr2CfssTWqEWAHA(Function1 r0, Object r1) {
        return ((Boolean) r0.invoke(r1)).booleanValue();
    }

    public static void $r8$lambda$eZWok32IQObkKLTATWSLUxE8JkE(PluginsActivity r5) {
        if (r5.fragmentView != null) goto L5;
        return;
    L5:
        if (r5.getParentActivity() != null) goto L8;
        return;
    L8:
        if (r5.searching == false) goto L10;
        r5.actionBar.closeSearchField();
    L10:
        ActionBarMenuItem r0 = r5.searchItem;
        if (ExteraConfig.getPluginsEngine() == true) goto L13;
    L15:
        boolean r1 = false;
    L16:
        AndroidUtilities.updateViewVisibilityAnimated(r0, r1, 0.5f, true);
        r5.getListView$TMessagesProj().adapter.update(true);
        r5.isSwitchingEngineState = false;
        return;
    L13:
        if (PluginsController.Companion.getInstance().getPlugins().isEmpty() == true) goto L15;
        r1 = true;
        goto L16
    }

    public PluginsActivity() {
    }

    public static final /* synthetic */ ActionBarMenuItem access$getInfoItem$p(PluginsActivity r0) {
        return r0.infoItem;
    }

    /* renamed from: access$getResourceProvider$p$s-1111628113, reason: not valid java name */
    public static final /* synthetic */ Theme.ResourcesProvider m1590access$getResourceProvider$p$s1111628113(PluginsActivity r0) {
        return r0.resourceProvider;
    }

    public static final /* synthetic */ void access$setQuery$p(PluginsActivity r0, String r1) {
        r0.query = r1;
    }

    public static final /* synthetic */ void access$setSearching$p(PluginsActivity r0, boolean r1) {
        r0.searching = r1;
    }

    private final UItem createPluginItem(Plugin r3) {
        return PluginCell.Factory.Companion.asPlugin(r3, new AnonymousClass1(r3, this));
    }

    private final void togglePluginsEngine(View r4, UItem r5) {
        if (this.isSwitchingEngineState == false) goto L5;
        return;
    L5:
        this.isSwitchingEngineState = true;
        ExteraConfig.setPluginsEngine(true ^ ExteraConfig.getPluginsEngine());
        ExteraConfig.getEditor().putBoolean("pluginsEngine", ExteraConfig.getPluginsEngine()).apply();
        TextCheckCell r42 = (TextCheckCell) r4;
        boolean r0 = ExteraConfig.getPluginsEngine();
        r5.checked = r0;
        r42.setChecked(r0);
        boolean r52 = ExteraConfig.getPluginsEngine();
        if (ExteraConfig.getPluginsEngine() == false) goto L8;
        int r02 = Theme.key_windowBackgroundChecked;
    L9:
        r42.setBackgroundColorAnimated(r52, Theme.getColor(r02));
        PluginsActivity$$ExternalSyntheticLambda2 r43 = new PluginsActivity$$ExternalSyntheticLambda2(this);
        if (ExteraConfig.getPluginsEngine() == false) goto L13;
        PluginsController.Companion.getInstance().init(r43);
        return;
    L13:
        PluginsController.Companion.getInstance().shutdown(r43);
        return;
    L8:
        r02 = Theme.key_windowBackgroundUnchecked;
        goto L9
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        View r62 = super.createView(r6);
        ActionBarMenuItem r0 = this.actionBar.menu.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new C01571(this));
        this.searchItem = r0;
        if (r0 == null) goto L5;
        r0.setSearchPaddingStart(7);
    L5:
        ActionBarMenuItem r02 = this.searchItem;
        if (r02 == null) goto L8;
        r02.setSearchFieldHint(LocaleController.getString(R.string.Search));
    L8:
        ActionBarMenuItem r03 = this.searchItem;
        if (ExteraConfig.getPluginsEngine() == true) goto L11;
    L13:
        boolean r3 = false;
    L14:
        AndroidUtilities.updateViewVisibilityAnimated(r03, r3, 0.5f, false);
        ActionBarMenuItem r04 = this.actionBar.menu.addItem(1, R.drawable.msg_info);
        this.infoItem = r04;
        if (r04 == null) goto L17;
        r04.setOnClickListener(new PluginsActivity$$ExternalSyntheticLambda1(this));
    L17:
        getListView$TMessagesProj().setOnScrollListener(new AnonymousClass3(this));
        this.fragmentView = r62;
        return r62;
    L11:
        if (PluginsController.Companion.getInstance().getPlugins().isEmpty() == true) goto L13;
        r3 = true;
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.pluginsUpdated) goto L13;
        ActionBarMenuItem r22 = this.searchItem;
        if (ExteraConfig.getPluginsEngine() == true) goto L7;
    L9:
        boolean r32 = false;
    L10:
        AndroidUtilities.updateViewVisibilityAnimated(r22, r32, 0.5f, true);
        getListView$TMessagesProj().adapter.update(true);
        return;
    L7:
        if (PluginsController.Companion.getInstance().getPlugins().isEmpty() == true) goto L9;
        r32 = true;
        goto L10
    L13:
        if (r2 != NotificationCenter.reloadInterface) goto L17;
        getListView$TMessagesProj().invalidateViews();
        return;
    L17:
        if (r2 != NotificationCenter.pluginIsNotResponding) goto L20;
        getListView$TMessagesProj().adapter.update(true);
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r8, UniversalAdapter r9) {
        if (this.searching == true) goto L6;
        r8.add(UItem.asRippleCheck(0, LocaleController.getString(R.string.EnablePluginsEngine)).setChecked(ExteraConfig.getPluginsEngine()));
    L6:
        if (ExteraConfig.getPluginsEngine() == false) goto L70;
        HashMap r92 = new HashMap(PluginsController.Companion.getInstance().getPlugins());
        UItem r1 = UItem.asSpace(AndroidUtilities.dp(8.0f));
        r1.transparent = true;
        r8.add(r1);
        if (this.searching == false) goto L14;
        if (TextUtils.isEmpty(this.query) == true) goto L14;
        r92.values().removeIf(new PluginsActivity$$ExternalSyntheticLambda4(new PluginsActivity$$ExternalSyntheticLambda3(this)));
    L14:
        if (r92.isEmpty() == false) goto L39;
        if (this.emptyView != null) goto L18;
        this.emptyView = new EmptyPluginsView(getContext(), this.resourceProvider);
    L18:
        EmptyPluginsView r93 = this.emptyView;
        if (r93 != null) goto L21;
        return;
    L21:
        int r4 = 2;
        if (this.searching == false) goto L29;
        if (r93.getTag() != null) goto L26;
    L27:
        MediaDataController.getInstance(UserConfig.selectedAccount).setPlaceholderImage(r93.getBackupImageView(), "AnimatedEmojies", "🔎", "100_100");
        r93.setText(LocaleController.getString(R.string.PluginsNotFound));
        r93.setTag(1);
    L34:
        if (this.searching == true) goto L37;
        r4 = 1;
    L37:
        r8.add(UItem.asFullscreenCustom(r93, AndroidUtilities.dp(r4 * 74), true).setTransparent(true));
    L56:
        UItem r7 = UItem.asSpace(AndroidUtilities.dp(4.0f));
        r7.transparent = true;
        r8.add(r7);
        return;
    L26:
        if (((Integer) r93.getTag()).intValue() == 1) goto L34;
    L29:
        if (r93.getTag() != null) goto L31;
    L32:
        MediaDataController.getInstance(UserConfig.selectedAccount).setPlaceholderImage(r93.getBackupImageView(), "AnimatedEmojies", "📂", "100_100");
        r93.setText(LocaleUtils.formatWithUsernames(LocaleController.getString(R.string.PluginsInfo)));
        r93.setTag(2);
        goto L34
    L31:
        if (((Integer) r93.getTag()).intValue() == 2) goto L34;
    L39:
        if (ExteraConfig.getPinnedPlugins().isEmpty() == true) goto L49;
        Iterator<String> r12 = ExteraConfig.getPinnedPlugins().iterator();
    L42:
        if (r12.hasNext() == false) goto L49;
        String r3 = r12.next();
        if (r92.containsKey(r3) == false) goto L42;
        Plugin r32 = (Plugin) r92.get(r3);
        if (r32 == null) goto L42;
        r8.add(createPluginItem(r32));
        r8.add(UItem.asSpace(AndroidUtilities.dp(8.0f)));
    L49:
        ArrayList r13 = new ArrayList(r92.values());
        CollectionsKt.sortWith(r13, Comparator.comparing(new PluginsActivity$$ExternalSyntheticLambda5(AnonymousClass2.INSTANCE)));
        Iterator r94 = r13.iterator();
    L51:
        if (r94.hasNext() == false) goto L56;
        Plugin r14 = (Plugin) r94.next();
        if (PluginsController.Companion.isPluginPinned(r14.getId()) == true) goto L51;
        r8.add(createPluginItem(r14));
        r8.add(UItem.asSpace(AndroidUtilities.dp(8.0f)));
        goto L51
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.Plugins);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.searching == false) goto L9;
        if (r2 == false) goto L10;
        this.actionBar.closeSearchField();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.viewType != 9) goto L6;
        togglePluginsEngine(r2, r1);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginsUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.reloadInterface);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginIsNotResponding);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginsUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.reloadInterface);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginIsNotResponding);
        super.onFragmentDestroy();
    }
}
