package com.exteragram.messenger.pillstack.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 22\u00020\u0001:\u000223B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u001e\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J4\u0010\u001c\u001a\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00112\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001fH\u0002J\u0018\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0007H\u0002J\u001e\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u00062\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J0\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+H\u0014J\b\u0010-\u001a\u00020\u000fH\u0002J\u0012\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020\u000fH\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "itemDetails", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;", "activeSectionId", "hiddenSectionId", "reorderIcon", "Landroid/graphics/drawable/Drawable;", "resetItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "initializeOptionStrings", _UrlKt.FRAGMENT_ENCODE_SET, "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "addMenuSection", PluginsConstants.Settings.TYPE_HEADER, "ids", _UrlKt.FRAGMENT_ENCODE_SET, "createMenuItem", "id", "info", "updateConfigFromReorder", "sectionId", "uItems", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "saveAndNotify", "updateResetButtonVisibility", "animated", _UrlKt.FRAGMENT_ENCODE_SET, "resetToDefault", "Companion", "ItemInfo", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPillStackPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PillStackPreferencesActivity.kt\ncom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,189:1\n1915#2,2:190\n1915#2,2:192\n1586#2:194\n1661#2,3:195\n1915#2,2:198\n*S KotlinDebug\n*F\n+ 1 PillStackPreferencesActivity.kt\ncom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity\n*L\n57#1:190,2\n105#1:192,2\n128#1:194\n128#1:195,3\n182#1:198,2\n*E\n"})
/* loaded from: classes4.dex */
public final class PillStackPreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private int activeSectionId;
    private int hiddenSectionId;
    private final HashMap<Integer, ItemInfo> itemDetails;
    private Drawable reorderIcon;
    private ActionBarMenuItem resetItem;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ITEM_INFINITE_SCROLLING", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity$ItemInfo;", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", _UrlKt.FRAGMENT_ENCODE_SET, "iconColorTop", "iconColorBottom", "<init>", "(Ljava/lang/CharSequence;III)V", "getName", "()Ljava/lang/CharSequence;", "getIconRes", "()I", "getIconColorTop", "getIconColorBottom", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ItemInfo {
        private final int iconColorBottom;
        private final int iconColorTop;
        private final int iconRes;
        private final CharSequence name;

        public ItemInfo(CharSequence r1, int r2, int r3, int r4) {
            this.name = r1;
            this.iconRes = r2;
            this.iconColorTop = r3;
            this.iconColorBottom = r4;
        }

        public final int getIconColorBottom() {
            return this.iconColorBottom;
        }

        public final int getIconColorTop() {
            return this.iconColorTop;
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final CharSequence getName() {
            return this.name;
        }
    }

    /* renamed from: $r8$lambda$-3FGXaoGeJS5FZh7gxso_upJJ4Q, reason: not valid java name */
    public static void m1512$r8$lambda$3FGXaoGeJS5FZh7gxso_upJJ4Q(boolean r0) {
        PillStackConfig.setInfiniteScrolling(r0);
    }

    /* renamed from: $r8$lambda$HVjwJYSrla1QmWYd-wZS2ggvmn4, reason: not valid java name */
    public static void m1513$r8$lambda$HVjwJYSrla1QmWYdwZS2ggvmn4(ItemInfo r2, View r3) {
        if ((r3 instanceof TextCell) == true) goto L5;
        return;
    L5:
        TextCell r32 = (TextCell) r3;
        r32.setColorfulIcon(r2.getIconColorTop(), r2.getIconColorBottom(), r2.getIconRes());
        r32.setImageLeft(21);
        r32.setOffsetFromImage(65);
    }

    /* renamed from: $r8$lambda$dI1FQDoFO_2vqGuejmNBeW_Ae-M, reason: not valid java name */
    public static /* synthetic */ void m1514$r8$lambda$dI1FQDoFO_2vqGuejmNBeW_AeM(PillStackPreferencesActivity r0, int r1, ArrayList r2) {
        r0.updateConfigFromReorder(r1, r2);
    }

    public static void $r8$lambda$nb07ABsFXOeUSiMbpCuLmpuXiB0(PillStackPreferencesActivity r0, View r1) {
        r0.resetToDefault();
    }

    static {
        Companion = new Companion(null);
    }

    public PillStackPreferencesActivity() {
        this.itemDetails = new HashMap();
        this.activeSectionId = -1;
        this.hiddenSectionId = -1;
    }

    private final int addMenuSection(ArrayList<UItem> r4, UniversalAdapter r5, String r6, List<Integer> r7) {
        r5.whiteSectionStart();
        r4.add(UItem.asHeader(r6));
        int r62 = r5.reorderSectionStart();
        Iterator<T> r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L8;
        int r0 = ((Number) r72.next()).intValue();
        ItemInfo r1 = this.itemDetails.get(Integer.valueOf(r0));
        if (r1 == null) goto L4;
        r4.add(createMenuItem(r0, r1));
        goto L4
    L8:
        r5.reorderSectionEnd();
        r5.whiteSectionEnd();
        return r62;
    }

    private final UItem createMenuItem(int r15, ItemInfo r16) {
        UItem r152 = BasePreferencesActivity.uButton$TMessagesProj$default(this, r15, r16.getName(), r16.getIconRes(), null, null, null, false, null, false, false, null, 1976, null);
        r152.object2 = this.reorderIcon;
        r152.bind = new PillStackPreferencesActivity$$ExternalSyntheticLambda3(r16);
        return r152;
    }

    private final void resetToDefault() {
        PillStackConfig.getActivePills().clear();
        PillStackConfig.getActivePills().addAll(PillStackConfig.getDefaultActivePills());
        PillStackConfig.getHiddenPills().clear();
        Iterator<T> r0 = PillRegistry.getRegisteredPills().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        PillRegistry.PillInfo r1 = (PillRegistry.PillInfo) r0.next();
        if (PillStackConfig.getActivePills().contains(Integer.valueOf(r1.id())) == true) goto L4;
        PillStackConfig.getHiddenPills().add(Integer.valueOf(r1.id()));
        goto L4
    L8:
        saveAndNotify();
    }

    private final void saveAndNotify() {
        PillStackConfig.savePillsLayout();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackLayoutChanged, new Object[0]);
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L5;
        r0.update(true);
    L5:
        updateResetButtonVisibility$default(this, false, 1, null);
    }

    private final void updateConfigFromReorder(int r5, ArrayList<UItem> r6) {
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r6, 10));
        int r1 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        UItem r3 = r6.get(r2);
        r2 = r2 + 1;
        r0.add(Integer.valueOf(r3.id));
        goto L3
    L6:
        if (r5 != this.activeSectionId) goto L9;
        PillStackConfig.getActivePills().clear();
        PillStackConfig.getActivePills().addAll(r0);
    L11:
        saveAndNotify();
        return;
    L9:
        if (r5 != this.hiddenSectionId) goto L11;
        PillStackConfig.getHiddenPills().clear();
        PillStackConfig.getHiddenPills().addAll(r0);
        goto L11
    }

    private final void updateResetButtonVisibility(boolean r3) {
        ActionBarMenuItem r2 = this.resetItem;
        if (r2 == null) goto L6;
        AndroidUtilities.updateViewVisibilityAnimated(r2, !Intrinsics.areEqual(PillStackConfig.getActivePills(), PillStackConfig.getDefaultActivePills()), 0.5f, r3);
        return;
    }

    public static /* synthetic */ void updateResetButtonVisibility$default(PillStackPreferencesActivity r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = true;
    L5:
        r0.updateResetButtonVisibility(r1);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        View r42 = super.createView(r4);
        ActionBarMenuItem r0 = this.actionBar.createMenu().addItem(0, R.drawable.msg_reset);
        r0.setContentDescription(LocaleController.getString(R.string.Reset));
        r0.setOnClickListener(new PillStackPreferencesActivity$$ExternalSyntheticLambda0(this));
        this.resetItem = r0;
        updateResetButtonVisibility(false);
        UniversalRecyclerView r02 = getListView$TMessagesProj();
        r02.allowReorder(true);
        r02.listenReorder(new PillStackPreferencesActivity$$ExternalSyntheticLambda1(this));
        return r42;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r14, UniversalAdapter r15) {
        Drawable r0 = this.reorderIcon;
        if (r0 != null) goto L5;
        r0 = ContextCompat.getDrawable(getContext(), R.drawable.list_reorder);
    L5:
        this.reorderIcon = r0;
        r14.add(UItem.asHeader(LocaleController.getString(R.string.Settings)));
        r14.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, 1000, LocaleController.getString(R.string.PillStackInfiniteScrolling), PillStackConfig.getInfiniteScrolling(), null, 0, false, "pillStackInfiniteScrolling", false, 0, 440, null));
        r14.add(UItem.asShadow(null));
        this.activeSectionId = -1;
        this.hiddenSectionId = -1;
        if (PillStackConfig.getActivePills().isEmpty() == true) goto L9;
        this.activeSectionId = addMenuSection(r14, r15, LocaleController.getString(R.string.PillStackActivePills), PillStackConfig.getActivePills());
        r14.add(UItem.asShadow(LocaleController.getString(R.string.PillStackPillsSettingsInfo)));
    L9:
        if (PillStackConfig.getHiddenPills().isEmpty() == true) goto L14;
        this.hiddenSectionId = addMenuSection(r14, r15, LocaleController.getString(R.string.PillStackHiddenPills), PillStackConfig.getHiddenPills());
        if (PillStackConfig.getActivePills().isEmpty() == false) goto L15;
        r14.add(UItem.asShadow(LocaleController.getString(R.string.PillStackPillsSettingsInfo)));
        return;
    L15:
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.PillStackPills);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void initializeOptionStrings() {
        Iterator<T> r0 = PillRegistry.getRegisteredPills().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        PillRegistry.PillInfo r1 = (PillRegistry.PillInfo) r0.next();
        this.itemDetails.put(Integer.valueOf(r1.id()), new ItemInfo(r1.name(), r1.iconRes(), r1.iconColorTop(), r1.iconColorBottom()));
        goto L4
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = r1.id;
        if (r22 > 0) goto L6;
        return;
    L6:
        if (r22 != 1000) goto L10;
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new PillStackPreferencesActivity$$ExternalSyntheticLambda2());
        return;
    L10:
        if (PillStackConfig.getActivePills().contains(Integer.valueOf(r22)) == false) goto L15;
        PillStackConfig.getActivePills().remove(Integer.valueOf(r22));
        if (PillStackConfig.getHiddenPills().contains(Integer.valueOf(r22)) == true) goto L17;
        PillStackConfig.getHiddenPills().add(0, Integer.valueOf(r22));
    L17:
        saveAndNotify();
        return;
    L15:
        if (PillStackConfig.getHiddenPills().contains(Integer.valueOf(r22)) == false) goto L17;
        PillStackConfig.getHiddenPills().remove(Integer.valueOf(r22));
        PillStackConfig.getActivePills().add(Integer.valueOf(r22));
        goto L17
    }
}
