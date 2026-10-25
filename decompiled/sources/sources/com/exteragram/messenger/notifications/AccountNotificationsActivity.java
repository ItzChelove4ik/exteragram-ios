package com.exteragram.messenger.notifications;

import android.view.View;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0014J0\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/notifications/AccountNotificationsActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "<init>", "()V", "shiftDp", _UrlKt.FRAGMENT_ENCODE_SET, "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "Landroid/view/View;", "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", "y", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAccountNotificationsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountNotificationsActivity.kt\ncom/exteragram/messenger/notifications/AccountNotificationsActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,51:1\n1915#2,2:52\n*S KotlinDebug\n*F\n+ 1 AccountNotificationsActivity.kt\ncom/exteragram/messenger/notifications/AccountNotificationsActivity\n*L\n27#1:52,2\n*E\n"})
/* loaded from: classes4.dex */
public final class AccountNotificationsActivity extends BasePreferencesActivity {
    public static final Companion Companion = null;
    private float shiftDp;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/exteragram/messenger/notifications/AccountNotificationsActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ACCOUNT_ITEM_ID", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$0zg-YvVaHRH7kK1NsE-XFc5V7Yw, reason: not valid java name */
    public static void m1505$r8$lambda$0zgYvVaHRH7kK1NsEXFc5V7Yw(int r1, boolean r2) {
        AccountNotifications.INSTANCE.setEnabled(r1, r2);
    }

    static {
        Companion = new Companion(null);
    }

    public AccountNotificationsActivity() {
        this.shiftDp = -3.0f;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
        r3.add(UItem.asHeader(LocaleController.getString(R.string.ShowNotificationsFor)));
        Iterator<T> r2 = AccountNotifications.getAccounts().iterator();
    L4:
        if (r2.hasNext() == false) goto L13;
        int r42 = ((Number) r2.next()).intValue();
        UItem r0 = UItem.asUserCheckbox(r42 + 1000, UserConfig.getInstance(r42).getCurrentUser());
        if (r42 != UserConfig.selectedAccount) goto L8;
    L11:
        boolean r43 = true;
    L12:
        r3.add(r0.setChecked(r43));
        goto L4
    L8:
        if (AccountNotifications.INSTANCE.isEnabled(r42) == true) goto L11;
        r43 = false;
        goto L12
    L13:
        r3.add(UItem.asShadow(LocaleController.getString(R.string.AccountNotificationsInfo)));
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.AccountNotifications);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.viewType == 37) goto L5;
        return;
    L5:
        int r32 = r1.id - 1000;
        if (r32 != UserConfig.selectedAccount) goto L9;
        float r12 = -this.shiftDp;
        this.shiftDp = r12;
        AndroidUtilities.shakeViewSpring(r2, r12);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        BulletinFactory.of(this).createSimpleBulletin(R.raw.info, LocaleController.getString(R.string.AccountNotificationsCurrent)).show();
        return;
    L9:
        toggleBooleanSettingAndRefresh$TMessagesProj(r1, new AccountNotificationsActivity$$ExternalSyntheticLambda0(r32));
    }
}
