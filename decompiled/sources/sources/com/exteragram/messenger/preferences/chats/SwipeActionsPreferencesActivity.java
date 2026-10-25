package com.exteragram.messenger.preferences.chats;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.chats.components.SwipeActionsCell;
import com.exteragram.messenger.utils.chats.SwipeAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u001e\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J4\u0010\u0015\u001a\u00020\u00142\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\t2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u001e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J0\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0014J\b\u0010%\u001a\u00020\u000fH\u0002J\u0012\u0010&\u001a\u0004\u0018\u00010\u00192\u0006\u0010'\u001a\u00020\u001cH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/exteragram/messenger/preferences/chats/SwipeActionsPreferencesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "previewCell", "Lcom/exteragram/messenger/preferences/chats/components/SwipeActionsCell;", "reorderIcon", "Landroid/graphics/drawable/Drawable;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "addSection", PluginsConstants.Settings.TYPE_HEADER, "actions", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/utils/chats/SwipeAction;", "onReorder", "sectionId", _UrlKt.FRAGMENT_ENCODE_SET, "uItems", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "refresh", "fromId", "id", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSwipeActionsPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SwipeActionsPreferencesActivity.kt\ncom/exteragram/messenger/preferences/chats/SwipeActionsPreferencesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,122:1\n1586#2:123\n1661#2,3:124\n1642#2,10:127\n1915#2:137\n1916#2:139\n1652#2:140\n1#3:138\n*S KotlinDebug\n*F\n+ 1 SwipeActionsPreferencesActivity.kt\ncom/exteragram/messenger/preferences/chats/SwipeActionsPreferencesActivity\n*L\n78#1:123\n78#1:124,3\n90#1:127,10\n90#1:137\n90#1:139\n90#1:140\n90#1:138\n*E\n"})
/* loaded from: classes4.dex */
public final class SwipeActionsPreferencesActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private SwipeActionsCell previewCell;
    private Drawable reorderIcon;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/preferences/chats/SwipeActionsPreferencesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PREVIEW_ID", _UrlKt.FRAGMENT_ENCODE_SET, "LOOP_ID", "REVERSED_ID", "ACTION_BASE_ID", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class EntriesMappings {
        public static final /* synthetic */ EnumEntries<SwipeAction> entries$0 = null;

        static {
            entries$0 = EnumEntriesKt.enumEntries(SwipeAction.values());
        }
    }

    public static void $r8$lambda$TRXQy28dSixzgsde64CyTK6Ltrk(boolean r0) {
        ExteraConfig.setSwipeActionsLoop(r0);
    }

    public static void $r8$lambda$_wLfPwBhgVdjeUETKd6OqpR1Pso(boolean r0) {
        ExteraConfig.setSwipeActionsReversed(r0);
    }

    public static /* synthetic */ void $r8$lambda$i1XCpoaVAoZvoutRdt6FWjH17gg(SwipeActionsPreferencesActivity r0, int r1, ArrayList r2) {
        r0.onReorder(r1, r2);
    }

    static {
        Companion = new Companion(null);
    }

    public SwipeActionsPreferencesActivity() {
    }

    private final UniversalAdapter addSection(ArrayList<UItem> r20, UniversalAdapter r21, String r22, List<? extends SwipeAction> r23) {
        r21.whiteSectionStart();
        r20.add(UItem.asHeader(r22));
        r21.reorderSectionStart();
        List<? extends SwipeAction> r1 = r23;
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        Iterator<T> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        SwipeAction r3 = (SwipeAction) r12.next();
        UItem r32 = BasePreferencesActivity.uButton$TMessagesProj$default(this, r3.ordinal() + 100, LocaleController.getString(r3.titleRes), r3.iconRes, null, null, null, false, null, false, false, null, 1976, null);
        r32.object2 = this.reorderIcon;
        r2.add(r32);
        goto L4
    L6:
        r20.addAll(r2);
        r21.reorderSectionEnd();
        r21.whiteSectionEnd();
        return r21;
    }

    private final SwipeAction fromId(int r2) {
        int r22 = r2 - 100;
        EnumEntries<SwipeAction> r1 = EntriesMappings.entries$0;
        if (r22 >= 0) goto L5;
        return null;
    L5:
        if (r22 < r1.size()) goto L7;
        return null;
    L7:
        return r1.get(r22);
    }

    private final void onReorder(int r5, ArrayList<UItem> r6) {
        if (r5 == 0) goto L4;
        return;
    L4:
        ArrayList r52 = new ArrayList();
        ArrayList r0 = new ArrayList();
        int r1 = r6.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L9;
        UItem r3 = r6.get(r2);
        r2 = r2 + 1;
        SwipeAction r32 = fromId(r3.id);
        if (r32 == null) goto L5;
        r0.add(r32);
        goto L5
    L9:
        r52.addAll(r0);
        SwipeAction.setEnabled(r52);
        refresh();
    }

    private final void refresh() {
        SwipeActionsCell r0 = this.previewCell;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.updateActions();
        UniversalAdapter r1 = getListView$TMessagesProj().adapter;
        if (r1 == null) goto L9;
        r1.update(true);
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r3) {
        this.previewCell = new SwipeActionsCell(r3);
        View r32 = super.createView(r3);
        getListView$TMessagesProj().allowReorder(true);
        getListView$TMessagesProj().listenReorder(new SwipeActionsPreferencesActivity$$ExternalSyntheticLambda0(this));
        return r32;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r16, UniversalAdapter r17) {
        Drawable r1 = this.reorderIcon;
        if (r1 != null) goto L5;
        r1 = ContextCompat.getDrawable(getContext(), R.drawable.list_reorder);
    L5:
        this.reorderIcon = r1;
        SwipeActionsCell r12 = this.previewCell;
        if (r12 != null) goto L8;
        r12 = null;
    L8:
        r16.add(UItem.asCustom(1, r12));
        r16.add(UItem.asShadow(LocaleController.getString(R.string.SwipeActionsInfo)));
        r17.whiteSectionStart();
        r16.add(UItem.asHeader(LocaleController.getString(R.string.SwipeActionsBehavior)));
        r16.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, 2, LocaleController.getString(R.string.SwipeActionsLoop), ExteraConfig.getSwipeActionsLoop(), null, 0, false, "swipeActionsLoop", false, 0, 440, null));
        r16.add(BasePreferencesActivity.uCheck$TMessagesProj$default(this, 3, LocaleController.getString(R.string.SwipeActionsReversed), ExteraConfig.getSwipeActionsReversed(), null, 0, false, "swipeActionsReversed", false, 0, 440, null));
        r17.whiteSectionEnd();
        r16.add(UItem.asShadow(LocaleController.getString(R.string.SwipeActionsBehaviorInfo)));
        addSection(r16, r17, LocaleController.getString(R.string.SwipeActionsEnabled), SwipeAction.enabled());
        r16.add(UItem.asShadow(LocaleController.getString(R.string.SwipeActionsOrderInfo)));
        List<SwipeAction> r13 = SwipeAction.disabled();
        if (r13.isEmpty() == true) goto L12;
        addSection(r16, r17, LocaleController.getString(R.string.SwipeActionsDisabled), r13);
        r16.add(UItem.asShadow(null));
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.SwipeActions);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r22 = r1.id;
        if (r22 != 2) goto L5;
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new SwipeActionsPreferencesActivity$$ExternalSyntheticLambda1());
        SwipeActionsCell r0 = this.previewCell;
        if (r0 != null) goto L16;
        r0 = null;
    L16:
        r0.updateActions();
        return;
    L5:
        if (r22 == 3) goto L11;
        SwipeAction r12 = fromId(r22);
        if (r12 != null) goto L9;
        return;
    L9:
        r12.setEnabled(!r12.isEnabled());
        refresh();
        return;
    L11:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new SwipeActionsPreferencesActivity$$ExternalSyntheticLambda2());
    }
}
