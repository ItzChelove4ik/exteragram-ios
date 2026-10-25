package com.exteragram.messenger.ai.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.ui.components.RoleCell;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AIEditorAlert;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 -2\u00020\u00012\u00020\u0002:\u0001-B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J5\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000f0\u000e\"\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0006H\u0014J\u001e\u0010\u0018\u001a\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J0\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0014J0\u0010%\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0014J\u0010\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\b2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010*\u001a\u00020\b2\u0006\u0010'\u001a\u00020(H\u0002J\u0012\u0010+\u001a\u00020\b2\b\u0010'\u001a\u0004\u0018\u00010(H\u0002J\u0010\u0010,\u001a\u00020\b2\u0006\u0010'\u001a\u00020(H\u0002¨\u0006."}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentDestroy", _UrlKt.FRAGMENT_ENCODE_SET, "didReceivedNotification", "id", _UrlKt.FRAGMENT_ENCODE_SET, "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "needHideTitle", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "confirmDeleteRole", "role", "Lcom/exteragram/messenger/ai/data/Role;", "deleteRole", "selectRole", "showRoleAlert", "showRolePreview", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRolesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RolesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/RolesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,190:1\n1915#2,2:191\n1915#2,2:193\n*S KotlinDebug\n*F\n+ 1 RolesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/RolesActivity\n*L\n84#1:191,2\n92#1:193,2\n*E\n"})
/* loaded from: classes4.dex */
public final class RolesActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NAME_LIMIT", _UrlKt.FRAGMENT_ENCODE_SET, "PROMPT_LIMIT", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/ai/ui/activities/RolesActivity$createView$1", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.RolesActivity$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ RolesActivity this$0;

        public AnonymousClass1(RolesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 == (-1)) goto L8;
            if (r2 == 0) goto L6;
            return;
        L6:
            RolesActivity.access$showRoleAlert(this.this$0, null);
            return;
        L8:
            this.this$0.finishFragment();
        }
    }

    public static void $r8$lambda$0cBzozEYAxC9_T7XloEOX8p4yJI(RolesActivity r0, Role r1) {
        r0.confirmDeleteRole(r1);
    }

    public static void $r8$lambda$CeDw4zHLMWfIne7QSJRetR1ZxwU(RolesActivity r0, Role r1, AlertDialog r2, int r3) {
        r0.deleteRole(r1);
    }

    public static void $r8$lambda$M65RxkksXxqX5qSjSDo1RBn0BTI(RolesActivity r0, Role r1) {
        r0.showRoleAlert(r1);
    }

    public static void $r8$lambda$T3SxBP3lIsmBLz88Ugcp0k58Xoo(RolesActivity r0, Role r1, View r2) {
        r0.selectRole(r1);
    }

    public static void $r8$lambda$bmawYMqP3qLjVbtTI8yheOmR9xA(RolesActivity r0, Role r1, View r2) {
        r0.selectRole(r1);
    }

    /* renamed from: $r8$lambda$srPp-9OP6XUqRCfRYkVYSV_y27Y, reason: not valid java name */
    public static Boolean m1088$r8$lambda$srPp9OP6XUqRCfRYkVYSV_y27Y(Role r1, RolesActivity r2, String r3, String r4, Long r5) {
        Role r0 = new Role(r3, r4);
        if (r5 == null) goto L5;
        long r32 = r5.longValue();
    L6:
        Role r33 = r0.setEmojiId(r32);
        if (r1 != null) goto L9;
    L11:
        boolean r52 = false;
    L12:
        if (r1 != null) goto L14;
    L16:
        boolean r12 = AiController.getInstance().addRole(r33);
    L17:
        if (r12 == false) goto L22;
        if (r52 == false) goto L20;
        AiConfig.setSelectedAiRole(r33);
    L20:
        r2.getNotificationCenter().postNotificationName(NotificationCenter.rolesUpdated, new Object[0]);
    L22:
        return Boolean.valueOf(r12);
    L14:
        if (r1.isSuggestion() == true) goto L16;
        r12 = AiController.getInstance().updateRole(r1, r33);
        goto L17
    L9:
        if (r1.isSelected() == false) goto L11;
        r52 = true;
        goto L12
    L5:
        r32 = 0;
        goto L6
    }

    public static void $r8$lambda$y0aGRp8REUtt26t0n_9jHeDKEwQ(Role r2, RolesActivity r3) {
        if (AndroidUtilities.addToClipboard(r2.getName() + "\n" + r2.getPrompt()) == false) goto L6;
        BulletinFactory.of(r3).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    static {
        Companion = new Companion(null);
    }

    public RolesActivity() {
    }

    public static final /* synthetic */ void access$showRoleAlert(RolesActivity r0, Role r1) {
        r0.showRoleAlert(r1);
    }

    private final void confirmDeleteRole(Role r4) {
        Activity r0 = getParentActivity();
        if (r0 == null) goto L12;
        TextView r1 = null;
        AlertDialog r42 = new AlertDialog.Builder(r0).setTitle(LocaleController.getString(R.string.Delete)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.DeleteRoleInfo, new Object[]{r4.getName()}))).setPositiveButton(LocaleController.getString(R.string.Delete), new RolesActivity$$ExternalSyntheticLambda6(this, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        showDialog(r42);
        View r3 = r42.getButton(-1);
        if ((r3 instanceof TextView) == false) goto L8;
        r1 = (TextView) r3;
    L8:
        if (r1 == null) goto L11;
        r1.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L11:
        return;
    }

    private final void deleteRole(Role r3) {
        boolean r0 = r3.isSelected();
        if (AiController.getInstance().removeRole(r3) == false) goto L12;
        if (r0 == false) goto L7;
        AiConfig.setSelectedAiRole(AiController.getInstance().getSuggestedRoles().get(0));
    L7:
        UniversalAdapter r2 = getListView$TMessagesProj().adapter;
        if (r2 == null) goto L11;
        r2.update(true);
        return;
    L11:
        return;
    }

    private final void selectRole(Role r2) {
        if (r2.isSelected() == true) goto L10;
        AiConfig.setSelectedAiRole(r2);
        UniversalAdapter r1 = getListView$TMessagesProj().adapter;
        if (r1 == null) goto L9;
        r1.update(true);
        return;
    L9:
        return;
    }

    private final void showRoleAlert(Role r11) {
        Activity r0 = getParentActivity();
        if (r0 != null) goto L5;
        return;
    L5:
        AIEditorAlert.CreateAiStyleAlert r1 = new AIEditorAlert.CreateAiStyleAlert(r0, this.resourceProvider);
        if (r11 == null) goto L9;
        String r2 = r11.getName();
        if (r2 == null) goto L9;
    L10:
        if (r11 == null) goto L13;
        String r3 = r11.getPrompt();
        if (r3 == null) goto L13;
    L14:
        if (r11 == null) goto L16;
        long r4 = r11.getEmojiId();
    L17:
        if (r11 != null) goto L19;
    L22:
        boolean r02 = false;
    L21:
        boolean r6 = r02;
        r1.setLocalStyle(r2, r3, r4, r6, 64, 1024, new RolesActivity$$ExternalSyntheticLambda3(r11, this)).show();
        return;
    L19:
        if (r11.isSuggestion() == true) goto L22;
        r02 = true;
        goto L21
    L16:
        r4 = 0;
    L13:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    private final void showRolePreview(Role r9) {
        Activity r0 = getParentActivity();
        if (r0 != null) goto L5;
        return;
    L5:
        new AIEditorAlert.CreateAiStyleAlert(r0, this.resourceProvider).setLocalStylePreview(r9.getName(), r9.getPrompt(), r9.getEmojiId(), 64, 1024).show();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        View r42 = super.createView(r4);
        this.actionBar.createMenu().addItem(0, R.drawable.msg_add);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        return r42;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.rolesUpdated) goto L8;
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L9;
        r0.update(true);
        return;
    L9:
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asTopView(getTitle(), LocaleController.getString(R.string.RolesInfo), "exteraGramPlaceholders", "🎭"));
        r4.add(UItem.asHeader(LocaleController.getString(R.string.Suggestions)));
        Iterator<T> r52 = AiController.getInstance().getSuggestedRoles().iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        Role r0 = (Role) r52.next();
        r4.add(RoleCell.Factory.asRoleCell(r0, new RolesActivity$$ExternalSyntheticLambda4(this, r0)));
        goto L4
    L6:
        r4.add(UItem.asShadow(null));
        List<Role> r53 = AiController.getInstance().getRoles();
        if (r53.isEmpty() == true) goto L12;
        r4.add(UItem.asHeader(LocaleController.getString(R.string.Roles)));
        Iterator<T> r54 = r53.iterator();
    L10:
        if (r54.hasNext() == false) goto L15;
        Role r02 = (Role) r54.next();
        r4.add(RoleCell.Factory.asRoleCell(r02, new RolesActivity$$ExternalSyntheticLambda5(this, r02)));
        goto L10
    L15:
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.Roles);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean needHideTitle() {
        return true;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        Object r12 = r1.object;
        if ((r12 instanceof Role) == false) goto L5;
        Role r13 = (Role) r12;
    L6:
        if (r13 != null) goto L9;
        return;
    L9:
        if (r13.isSuggestion() == false) goto L12;
        showRolePreview(r13);
        return;
    L12:
        showRoleAlert(r13);
        return;
    L5:
        r13 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.rolesUpdated);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.rolesUpdated);
        super.onFragmentDestroy();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r2, View r3, int r4, float r5, float r6) {
        Object r22 = r2.object;
        if ((r22 instanceof Role) == false) goto L5;
        Role r23 = (Role) r22;
    L6:
        if (r23 != null) goto L8;
        return false;
    L8:
        if (r23.isSuggestion() == true) goto L18;
        ItemOptions r42 = ItemOptions.makeOptions(this, r3);
        r42.add(R.drawable.msg_edit, LocaleController.getString(R.string.Edit), new RolesActivity$$ExternalSyntheticLambda0(this, r23));
        r42.add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new RolesActivity$$ExternalSyntheticLambda1(r23, this));
        r42.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new RolesActivity$$ExternalSyntheticLambda2(this, r23));
        r42.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r3));
        if (LocaleController.isRTL == false) goto L13;
        int r1 = 3;
    L14:
        r42.setGravity(r1);
        r42.show();
        return true;
    L13:
        r1 = 5;
        goto L14
    L18:
        return false;
    L5:
        r23 = null;
        goto L6
    }
}
