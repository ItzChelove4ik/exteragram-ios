package com.exteragram.messenger.icons.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.icons.BaseIconPacks;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.icons.ui.components.IconPackCell;
import com.exteragram.messenger.icons.ui.components.NewIconPackBottomSheet;
import com.exteragram.messenger.icons.ui.picker.IconPickerController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J5\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0016\u0010\u0012\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00140\u0013\"\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J(\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J\u001e\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00102\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002J\b\u0010$\u001a\u00020%H\u0016J\u001e\u0010&\u001a\u00020\r2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010(\u001a\u00020)H\u0014J<\u0010*\u001a\u00020\r2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010(\u001a\u00020)2\u0006\u0010+\u001a\u00020%2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020%0-2\u0006\u0010.\u001a\u00020\bH\u0002J\u0016\u0010/\u001a\u00020\u00102\f\u0010,\u001a\b\u0012\u0004\u0012\u00020%0-H\u0002J\u0012\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u000f\u001a\u00020%H\u0002J0\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J0\u00109\u001a\u00020\b2\u0006\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0014J\u0010\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u000201H\u0002J\u0010\u0010<\u001a\u00020\r2\u0006\u0010;\u001a\u000201H\u0002J\u0010\u0010=\u001a\u00020\r2\u0006\u0010;\u001a\u000201H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/exteragram/messenger/icons/ui/IconPacksActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "floatingButton", "Lorg/telegram/ui/Components/FragmentFloatingButton;", "scrollUpdated", _UrlKt.FRAGMENT_ENCODE_SET, "reorderRunnable", "Ljava/lang/Runnable;", "onFragmentCreate", "onFragmentDestroy", _UrlKt.FRAGMENT_ENCODE_SET, "didReceivedNotification", "id", _UrlKt.FRAGMENT_ENCODE_SET, "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "onInsets", "left", "top", "right", "bottom", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "updateConfigFromReorder", "sectionId", "uItems", "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", PluginsConstants.Settings.ITEMS, "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "addCustomPacksSection", PluginsConstants.Settings.TYPE_HEADER, "ids", _UrlKt.FRAGMENT_ENCODE_SET, "reorderable", "countCustomPacks", "getPackById", "Lcom/exteragram/messenger/icons/IconPack;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "openEditor", "pack", "sharePack", "confirmDeletePack", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconPacksActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,234:1\n1642#2,10:235\n1915#2:245\n1916#2:247\n1652#2:248\n296#2,2:249\n296#2,2:252\n1834#2,4:254\n1#3:246\n1#3:251\n*S KotlinDebug\n*F\n+ 1 IconPacksActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksActivity\n*L\n89#1:235,10\n89#1:245\n89#1:247\n89#1:248\n93#1:249,2\n119#1:252,2\n164#1:254,4\n89#1:246\n*E\n"})
/* loaded from: classes4.dex */
public final class IconPacksActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    private FragmentFloatingButton floatingButton;
    private Runnable reorderRunnable;
    private boolean scrollUpdated;

    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/exteragram/messenger/icons/ui/IconPacksActivity$createView$3", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrolled", _UrlKt.FRAGMENT_ENCODE_SET, "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", _UrlKt.FRAGMENT_ENCODE_SET, "dy", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.icons.ui.IconPacksActivity$createView$3, reason: invalid class name */
    public static final class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ IconPacksActivity this$0;

        public AnonymousClass3(IconPacksActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (r3 != 0) goto L5;
        L13:
            IconPacksActivity.access$setScrollUpdated$p(this.this$0, true);
            return;
        L5:
            if (IconPacksActivity.access$getScrollUpdated$p(this.this$0) == false) goto L13;
            FragmentFloatingButton r22 = IconPacksActivity.access$getFloatingButton$p(this.this$0);
            if (r22 != null) goto L9;
            r22 = null;
        L9:
            if (r3 >= 0) goto L11;
            boolean r32 = true;
        L12:
            r22.setButtonVisible(r32, true);
            goto L13
        L11:
            r32 = false;
            goto L12
        }
    }

    public static /* synthetic */ void $r8$lambda$5ACnyBOPEUZ6o3wgWrjnrOh2IGg(IconPacksActivity r0, int r1, ArrayList r2) {
        r0.updateConfigFromReorder(r1, r2);
    }

    public static void $r8$lambda$A703UknTxEoYhXsLsLMEQOxGKTs(IconPacksActivity r0, Context r1, View r2) {
        new NewIconPackBottomSheet(r0, r1).show();
    }

    public static void $r8$lambda$Nlmz4x_L8eYWlQRuLcV6MQ_eW7w(IconPacksActivity r0, IconPack r1) {
        r0.sharePack(r1);
    }

    public static void $r8$lambda$O09Q0YvY6IqFbcNwPawtwKXo5og(IconPacksActivity r3, File r4) {
        Activity r32 = r3.getParentActivity();
        if (r32 != null) goto L5;
        return;
    L5:
        Intent r0 = new Intent("android.intent.action.SEND");
        r0.setType("application/zip");
        r0.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(r32, ApplicationLoader.getApplicationId() + ".provider", r4));
        r0.addFlags(1);
        r32.startActivity(Intent.createChooser(r0, LocaleController.getString(R.string.ShareFile)));
    }

    public static void $r8$lambda$_KE7t3AKcOcQlqRy3JLg14r82rk(IconPack r1, IconPacksActivity r2) {
        File r12 = IconManager.INSTANCE.bundlePackBlocking(r1.getId());
        if (r12 != null) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(new IconPacksActivity$$ExternalSyntheticLambda9(r2, r12));
    }

    /* renamed from: $r8$lambda$cpdY_71GLVKD68AGwMX2-k7ryE0, reason: not valid java name */
    public static void m1442$r8$lambda$cpdY_71GLVKD68AGwMX2k7ryE0() {
        IconManager.INSTANCE.initialize(true);
    }

    public static void $r8$lambda$j8xr0tsHauQK4SkJPnBUddf7xjA(IconPack r0, AlertDialog r1, int r2) {
        IconManager.INSTANCE.deletePack(r0.getId());
    }

    public static void $r8$lambda$qvBNI3GJXGyDRJJ2Z9oWKE21GP4(IconPacksActivity r0, IconPack r1) {
        r0.confirmDeletePack(r1);
    }

    public static boolean $r8$lambda$vIV9A2HliqWTFOh2bzB6NKZr1DY(String r4) {
        return StringsKt.startsWith$default(r4, "base.", false, 2, null);
    }

    public static void $r8$lambda$zPvZBHCf4glQiJchkElgGhbG6CU(IconPacksActivity r0, IconPack r1) {
        r0.openEditor(r1);
    }

    public IconPacksActivity() {
    }

    public static final /* synthetic */ FragmentFloatingButton access$getFloatingButton$p(IconPacksActivity r0) {
        return r0.floatingButton;
    }

    public static final /* synthetic */ boolean access$getScrollUpdated$p(IconPacksActivity r0) {
        return r0.scrollUpdated;
    }

    public static final /* synthetic */ void access$setScrollUpdated$p(IconPacksActivity r0, boolean r1) {
        r0.scrollUpdated = r1;
    }

    private final void addCustomPacksSection(ArrayList<UItem> r5, UniversalAdapter r6, String r7, List<String> r8, boolean r9) {
        r6.whiteSectionStart();
        r5.add(UItem.asHeader(r7));
        if (r9 == false) goto L5;
        r6.reorderSectionStart();
    L5:
        Iterator<String> r72 = r8.iterator();
    L7:
        if (r72.hasNext() == false) goto L14;
        String r82 = r72.next();
        if (StringsKt.startsWith$default(r82, "base.", false, 2, null) == true) goto L7;
        IconPack r83 = getPackById(r82);
        if (r83 == null) goto L7;
        r5.add(IconPackCell.Factory.asIconPackCell(r83).setReordering(r9));
        goto L7
    L14:
        if (r9 == false) goto L16;
        r6.reorderSectionEnd();
    L16:
        r6.whiteSectionEnd();
    }

    private final void confirmDeletePack(IconPack r3) {
        TextView r0 = null;
        AlertDialog r2 = new AlertDialog.Builder(getParentActivity(), this.resourceProvider).setTitle(LocaleController.getString(R.string.DeletePack)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.DeletePackInfo, new Object[]{r3.getName()}))).setPositiveButton(LocaleController.getString(R.string.Delete), new IconPacksActivity$$ExternalSyntheticLambda5(r3)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r2.show();
        View r22 = r2.getButton(-1);
        if ((r22 instanceof TextView) == false) goto L5;
        r0 = (TextView) r22;
    L5:
        if (r0 == null) goto L8;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    private final int countCustomPacks(List<String> r7) {
        List<String> r72 = r7;
        if ((r72 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r73 = r72.iterator();
        int r0 = 0;
    L9:
        if (r73.hasNext() == false) goto L17;
        String r2 = (String) r73.next();
        if (StringsKt.startsWith$default(r2, "base.", false, 2, null) == true) goto L9;
        if (getPackById(r2) == null) goto L9;
        r0 = r0 + 1;
        if (r0 >= 0) goto L9;
        CollectionsKt.throwCountOverflow();
        goto L9
    L17:
        return r0;
    L5:
        if (r72.isEmpty() == false) goto L7;
        return 0;
    }

    private final IconPack getPackById(String r4) {
        if (StringsKt.startsWith$default(r4, "base.", false, 2, null) == false) goto L7;
        return BaseIconPacks.INSTANCE.getBasePack(r4);
    L7:
        return IconManager.INSTANCE.findPackById(r4);
    }

    private final void openEditor(IconPack r3) {
        ExteraConfig.setEditingIconPackId(r3.getId());
        IconPickerController.setActive((LaunchActivity) getParentActivity(), true);
        presentFragment(new IconPacksEditorActivity(r3));
    }

    private final void sharePack(IconPack r3) {
        Utilities.globalQueue.postRunnable(new IconPacksActivity$$ExternalSyntheticLambda7(r3, this));
    }

    private final void updateConfigFromReorder(int r11, ArrayList<UItem> r12) {
        ArrayList r0 = new ArrayList();
        int r1 = r12.size();
        int r3 = 0;
    L3:
        String r4 = null;
        if (r3 >= r1) goto L14;
        UItem r5 = r12.get(r3);
        r3 = r3 + 1;
        Object r52 = r5.object;
        if ((r52 instanceof IconPack) == false) goto L8;
        IconPack r53 = (IconPack) r52;
    L9:
        if (r53 == null) goto L11;
        r4 = r53.getId();
    L11:
        if (r4 == null) goto L3;
        r0.add(r4);
        goto L3
    L8:
        r53 = null;
        goto L9
    L14:
        if (countCustomPacks(ExteraConfig.getIconPacksLayout()) <= 1) goto L17;
        if (r11 != 0) goto L17;
        boolean r112 = true;
    L18:
        if (r112 == false) goto L27;
        ArrayList<String> r122 = ExteraConfig.getIconPacksLayout();
        int r32 = r122.size();
        int r54 = 0;
    L20:
        if (r54 >= r32) goto L24;
        String r6 = r122.get(r54);
        r54 = r54 + 1;
        if (StringsKt.startsWith$default(r6, "base.", false, 2, null) == false) goto L20;
        r4 = r6;
    L24:
        String r42 = r4;
        ExteraConfig.getIconPacksLayout().clear();
        ExteraConfig.getIconPacksLayout().addAll(r0);
        if (r42 == null) goto L28;
        ExteraConfig.getIconPacksLayout().add(r42);
    L28:
        ExteraConfig.saveIconPacksLayout();
        if (r112 == false) goto L35;
        Runnable r113 = this.reorderRunnable;
        if (r113 == null) goto L33;
        AndroidUtilities.cancelRunOnUIThread(r113);
    L33:
        IconPacksActivity$$ExternalSyntheticLambda6 r114 = new IconPacksActivity$$ExternalSyntheticLambda6();
        this.reorderRunnable = r114;
        AndroidUtilities.runOnUIThread(r114, 500);
    L35:
        if (this.fragmentView == null) goto L48;
        UniversalAdapter r10 = getListView$TMessagesProj().adapter;
        if (r10 == null) goto L49;
        r10.update(true);
        return;
    L49:
        return;
    L48:
        return;
    L27:
        ExteraConfig.getIconPacksHidden().clear();
        ExteraConfig.getIconPacksHidden().addAll(r0);
    L17:
        r112 = false;
        goto L18
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        View r0 = super.createView(r5);
        FragmentFloatingButton r1 = new FragmentFloatingButton(r5, this.resourceProvider);
        this.floatingButton = r1;
        r1.imageView.setImageResource(R.drawable.msg_add);
        FragmentFloatingButton r12 = this.floatingButton;
        FragmentFloatingButton r2 = null;
        if (r12 != null) goto L5;
        r12 = null;
    L5:
        r12.setContentDescription(LocaleController.getString(R.string.Add));
        FragmentFloatingButton r13 = this.floatingButton;
        if (r13 != null) goto L8;
        r13 = null;
    L8:
        r13.setOnClickListener(new IconPacksActivity$$ExternalSyntheticLambda3(this, r5));
        if ((r0 instanceof FrameLayout) == false) goto L11;
        FrameLayout r52 = (FrameLayout) r0;
    L12:
        if (r52 == null) goto L18;
        FragmentFloatingButton r14 = this.floatingButton;
        if (r14 == null) goto L17;
        r2 = r14;
    L17:
        r52.addView(r2, FragmentFloatingButton.createDefaultLayoutParams());
    L18:
        getListView$TMessagesProj().allowReorder(true);
        getListView$TMessagesProj().setReorderHandleOnly(true);
        getListView$TMessagesProj().listenReorder(new IconPacksActivity$$ExternalSyntheticLambda4(this));
        getListView$TMessagesProj().setOnScrollListener(new AnonymousClass3(this));
        return r0;
    L11:
        r52 = null;
        goto L12
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.iconPackUpdated) goto L5;
        return;
    L5:
        if (this.fragmentView == null) goto L11;
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L12;
        r0.update(true);
        return;
    L12:
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
        ArrayList<String> r2 = ExteraConfig.getIconPacksLayout();
        int r3 = r2.size();
        int r4 = 0;
    L3:
        String r5 = null;
        if (r4 >= r3) goto L8;
        String r7 = r2.get(r4);
        r4 = r4 + 1;
        if (StringsKt.startsWith$default(r7, "base.", false, 2, null) == false) goto L3;
        r5 = r7;
    L8:
        String r52 = r5;
        if (r52 != null) goto L11;
        r52 = "base.default";
    L11:
        r13.whiteSectionStart();
        r12.add(UItem.asHeader(LocaleController.getString(R.string.BasePacks)));
        String[] r22 = {"base.default", "base.solar", "base.remix"};
        int r32 = 0;
    L13:
        if (r32 >= 3) goto L19;
        String r42 = r22[r32];
        IconPack r72 = getPackById(r42);
        if (r72 == null) goto L18;
        UItem r73 = IconPackCell.Factory.asIconPackCell(r72);
        r73.checked = Intrinsics.areEqual(r42, r52);
        r12.add(r73);
    L18:
        r32 = r32 + 1;
        goto L13
    L19:
        r13.whiteSectionEnd();
        r12.add(UItem.asShadow(LocaleController.getString(R.string.BaseIconPackInfo)));
        int r74 = countCustomPacks(ExteraConfig.getIconPacksLayout());
        if (r74 <= 0) goto L27;
        String r33 = LocaleController.getString(R.string.EnabledPacks);
        ArrayList<String> r43 = ExteraConfig.getIconPacksLayout();
        if (r74 <= 1) goto L25;
        boolean r53 = true;
    L26:
        addCustomPacksSection(r12, r13, r33, r43, r53);
        goto L27
    L25:
        r53 = false;
    L27:
        int r9 = countCustomPacks(ExteraConfig.getIconPacksHidden());
        if (r9 <= 0) goto L37;
        if (r74 <= 0) goto L31;
        r12.add(UItem.asShadow());
    L31:
        String r34 = LocaleController.getString(R.string.AllPacks);
        ArrayList<String> r44 = ExteraConfig.getIconPacksHidden();
        if (r9 <= 1) goto L35;
        boolean r54 = true;
    L36:
        addCustomPacksSection(r12, r13, r34, r44, r54);
        goto L37
    L35:
        r54 = false;
    L37:
        if (r74 > 0) goto L41;
        if (r9 > 0) goto L41;
        return;
    L41:
        r12.add(UItem.asShadow(LocaleController.getString(R.string.IconPacksHint)));
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.IconPacks);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        Object r12 = r1.object;
        if ((r12 instanceof IconPack) == false) goto L5;
        IconPack r13 = (IconPack) r12;
    L6:
        if (r13 == null) goto L25;
        String r14 = r13.getId();
        if (r14 != null) goto L11;
        return;
    L11:
        if (StringsKt.startsWith$default(r14, "base.", false, 2, null) == false) goto L14;
        CollectionsKt.removeAll(ExteraConfig.getIconPacksLayout(), new IconPacksActivity$$ExternalSyntheticLambda8());
        ExteraConfig.getIconPacksLayout().add(r14);
    L21:
        ExteraConfig.saveIconPacksLayout();
        IconManager.INSTANCE.initialize(true);
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L26;
        r0.update(true);
        return;
    L26:
        return;
    L14:
        if (ExteraConfig.getIconPacksLayout().contains(r14) == false) goto L19;
        ExteraConfig.getIconPacksLayout().remove(r14);
        if (ExteraConfig.getIconPacksHidden().contains(r14) == true) goto L21;
        ExteraConfig.getIconPacksHidden().add(0, r14);
        goto L21
    L19:
        if (ExteraConfig.getIconPacksHidden().contains(r14) == false) goto L21;
        ExteraConfig.getIconPacksHidden().remove(r14);
        ExteraConfig.getIconPacksLayout().add(0, r14);
        goto L21
    L25:
        return;
    L5:
        r13 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.iconPackUpdated);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.iconPackUpdated);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r3, int r4, int r5, int r6) {
        FragmentFloatingButton r0 = this.floatingButton;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.setTranslationY(-r6);
        super.onInsets(r3, r4, r5, r6);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r4, View r5, int r6, float r7, float r8) {
        Object r0 = r4.object;
        if ((r0 instanceof IconPack) == false) goto L5;
        IconPack r02 = (IconPack) r0;
    L6:
        if (r02 != null) goto L10;
        return super.onLongClick(r4, r5, r6, r7, r8);
    L10:
        if (StringsKt.startsWith$default(r02.getId(), "base.", false, 2, null) == false) goto L12;
        return false;
    L12:
        ItemOptions r42 = ItemOptions.makeOptions(this, r5);
        r42.addIf(ExteraConfig.getIconPacksLayout().contains(r02.getId()), R.drawable.msg_edit, LocaleController.getString(R.string.Edit), new IconPacksActivity$$ExternalSyntheticLambda0(this, r02));
        r42.add(R.drawable.msg_share, LocaleController.getString(R.string.ShareFile), new IconPacksActivity$$ExternalSyntheticLambda1(this, r02));
        r42.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new IconPacksActivity$$ExternalSyntheticLambda2(this, r02));
        r42.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r5));
        r42.show();
        return true;
    L5:
        r02 = null;
        goto L6
    }
}
