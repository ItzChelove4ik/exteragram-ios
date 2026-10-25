package com.exteragram.messenger.ai.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import com.exteragram.messenger.ai.ui.OnDeviceModelDialogs;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000 ;2\u00020\u00012\u00020\u0002:\u0001;B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J5\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0016\u0010\u0016\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00180\u0017\"\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\rH\u0014J\u001e\u0010\u001d\u001a\u00020\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020\u000bH\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002J\b\u0010'\u001a\u00020\u000fH\u0002J\u0010\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020-H\u0002J\u0016\u0010.\u001a\u00020\u000f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000f00H\u0002J\b\u00101\u001a\u00020\u000fH\u0002J0\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020 2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0014J0\u00108\u001a\u00020\r2\u0006\u00103\u001a\u00020 2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0014J\u0010\u00109\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010:\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "<init>", "()V", "statusPoll", "Ljava/lang/Runnable;", "newServiceCircle", "Landroid/graphics/drawable/Drawable;", "newServicePlus", "newServiceDrawable", "Lorg/telegram/ui/Components/CombinedDrawable;", "onFragmentCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onResume", _UrlKt.FRAGMENT_ENCODE_SET, "onPause", "onFragmentDestroy", "didReceivedNotification", "id", _UrlKt.FRAGMENT_ENCODE_SET, "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "getTitle", _UrlKt.FRAGMENT_ENCODE_SET, "needHideTitle", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "getNewServiceDrawable", "applyOnDeviceBadge", _UrlKt.FRAGMENT_ENCODE_SET, "title", "reloadServices", "onDeviceUnavailable", "service", "Lcom/exteragram/messenger/ai/data/Service;", "showOnDeviceOptions", PluginsConstants.Settings.VIEW, "Landroid/view/View;", "changeModelConfig", "change", "Lkotlin/Function0;", "openAiCoreSettings", "onClick", PluginsConstants.Settings.ITEM, "position", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "onLongClick", "confirmDeleteService", "deleteService", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nServicesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServicesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/ServicesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,290:1\n1924#2,3:291\n*S KotlinDebug\n*F\n+ 1 ServicesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/ServicesActivity\n*L\n111#1:291,3\n*E\n"})
/* loaded from: classes4.dex */
public final class ServicesActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    public static final Companion Companion = null;
    private Drawable newServiceCircle;
    private CombinedDrawable newServiceDrawable;
    private Drawable newServicePlus;
    private final Runnable statusPoll;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NEW_SERVICE", _UrlKt.FRAGMENT_ENCODE_SET, "SERVICE_ID_BASE", "STATUS_POLL_INTERVAL", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/ai/ui/activities/ServicesActivity$getNewServiceDrawable$1", "Lorg/telegram/ui/Components/CombinedDrawable;", "setColorFilter", _UrlKt.FRAGMENT_ENCODE_SET, "colorFilter", "Landroid/graphics/ColorFilter;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.ai.ui.activities.ServicesActivity$getNewServiceDrawable$1, reason: invalid class name */
    public static final class AnonymousClass1 extends CombinedDrawable {
        public AnonymousClass1(Drawable r1, Drawable r2) {
            super(r1, r2);
            this.translateX = AndroidUtilities.dp(2.0f);
        }

        @Override // org.telegram.ui.Components.CombinedDrawable, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static void $r8$lambda$3oj12F1tQPr8C2eulDW8tSsMxYs(Service r3, ServicesActivity r4) {
        if (AndroidUtilities.addToClipboard(r3.getUrl() + "\n" + r3.getModel() + "\n" + r3.getKey()) == false) goto L6;
        BulletinFactory.of(r4).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
        return;
    }

    /* renamed from: $r8$lambda$FI2TIpAwsCT0r57J4GG-ovSwgvg, reason: not valid java name */
    public static void m1089$r8$lambda$FI2TIpAwsCT0r57J4GGovSwgvg(ServicesActivity r0, Service r1) {
        r0.confirmDeleteService(r1);
    }

    public static void $r8$lambda$J5FwvCCb6gvNmrvFa6BnEgpawXU(ServicesActivity r1) {
        AiConfig.setOnDeviceReasoning(!AiConfig.getOnDeviceReasoning());
        r1.reloadServices();
    }

    /* renamed from: $r8$lambda$K-j6WNT5KP23GrFAaw4DS1f_4DE, reason: not valid java name */
    public static void m1090$r8$lambda$Kj6WNT5KP23GrFAaw4DS1f_4DE(ServicesActivity r0) {
        r0.openAiCoreSettings();
    }

    /* renamed from: $r8$lambda$QVuHMXK4Xz_lIjCUAFt3M-kEt1w, reason: not valid java name */
    public static void m1091$r8$lambda$QVuHMXK4Xz_lIjCUAFt3MkEt1w(ServicesActivity r0) {
        r0.openAiCoreSettings();
    }

    public static void $r8$lambda$TuZnTxTCXXoI5onXdbftkEf9SkM(ServicesActivity r1) {
        OnDeviceAvailability.refresh(new ServicesActivity$$ExternalSyntheticLambda0(r1));
    }

    public static /* synthetic */ void $r8$lambda$V4YpQSHLGGUCXy_MEMp44PF3JmM(ServicesActivity r0) {
        r0.reloadServices();
    }

    /* renamed from: $r8$lambda$Vm-0Gcg3EpLJLd-sI33sFpym5jo, reason: not valid java name */
    public static void m1092$r8$lambda$Vm0Gcg3EpLJLdsI33sFpym5jo(ServicesActivity r1) {
        r1.changeModelConfig(new ServicesActivity$$ExternalSyntheticLambda12());
    }

    public static Unit $r8$lambda$iJt0lAxd7qdQnUfnJPryceLwHYA() {
        AiConfig.setOnDevicePreviewModel(!AiConfig.getOnDevicePreviewModel());
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$tzTFq1IWkrg8JKLICcBXJc5XXjQ(ServicesActivity r1) {
        r1.changeModelConfig(new ServicesActivity$$ExternalSyntheticLambda13());
    }

    /* renamed from: $r8$lambda$vEJSsXMOGR-duS4ls0mYqdidfJQ, reason: not valid java name */
    public static Unit m1093$r8$lambda$vEJSsXMOGRduS4ls0mYqdidfJQ() {
        AiConfig.setOnDeviceFastModel(!AiConfig.getOnDeviceFastModel());
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$w7MLWq2dmqoCm_oD7NBh7uP8LM4(ServicesActivity r1, Service r2) {
        r1.presentFragment(new EditServiceActivity(r2));
    }

    public static void $r8$lambda$wMznpH1eB0FbSVLkAHpKikm25ho(ServicesActivity r0, Service r1) {
        r0.onDeviceUnavailable(r1);
    }

    public static void $r8$lambda$wZTLgFZV023pJJhY6kHr6Mb9e7o(ServicesActivity r0, Service r1, AlertDialog r2, int r3) {
        r0.deleteService(r1);
    }

    static {
        Companion = new Companion(null);
    }

    public ServicesActivity() {
        this.statusPoll = new ServicesActivity$statusPoll$1(this);
    }

    public static final /* synthetic */ void access$reloadServices(ServicesActivity r0) {
        r0.reloadServices();
    }

    private final CharSequence applyOnDeviceBadge(CharSequence r3) {
        if (OnDeviceAvailability.isDownloadInProgress() == false) goto L11;
        int r2 = OnDeviceAvailability.getDownloadPercent();
        if (r2 < 0) goto L7;
        String r22 = r2 + "%";
    L9:
        return LocaleUtils.applyBadge(r3, r22, Theme.key_chats_unreadCounterMuted, Theme.key_chats_unreadCounterText);
    L7:
        r22 = LocaleController.getString(R.string.AIOnDeviceBadgeDownloading);
        goto L9
    L11:
        if (OnDeviceAvailability.needsDownload() == false) goto L15;
        return LocaleUtils.applyBadge(r3, LocaleController.getString(R.string.AIOnDeviceBadgeDownload), Theme.key_chats_unreadCounterMuted, Theme.key_chats_unreadCounterText);
    L15:
        if (OnDeviceAvailability.isReady() == false) goto L19;
        return LocaleUtils.applyBadge(r3, LocaleController.getString(R.string.AIOnDeviceBadgeReady), Theme.key_featuredStickers_addButton, Theme.key_featuredStickers_buttonText);
    L19:
        return LocaleUtils.applyBadge(r3, LocaleController.getString(R.string.AIOnDeviceBadgeUnavailable), Theme.key_chats_unreadCounterMuted, Theme.key_chats_unreadCounterText);
    }

    private final void changeModelConfig(Function0<Unit> r3) {
        r3.invoke();
        OnDeviceAvailability.onModelConfigChanged(new ServicesActivity$$ExternalSyntheticLambda0(this));
        AndroidUtilities.cancelRunOnUIThread(this.statusPoll);
        AndroidUtilities.runOnUIThread(this.statusPoll, 5000);
    }

    private final void confirmDeleteService(Service r4) {
        Activity r0 = getParentActivity();
        if (r0 == null) goto L12;
        TextView r1 = null;
        AlertDialog r42 = new AlertDialog.Builder(r0).setTitle(LocaleController.getString(R.string.Delete)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.DeleteServiceInfo, new Object[]{r4.getShortModel()}))).setPositiveButton(LocaleController.getString(R.string.Delete), new ServicesActivity$$ExternalSyntheticLambda6(this, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
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

    private final void deleteService(Service r3) {
        boolean r0 = r3.isSelected();
        if (AiController.getInstance().removeService(r3) == true) goto L6;
        return;
    L6:
        if (r0 == false) goto L10;
        AiConfig.clearSelectedService();
        if (AiController.getInstance().isServicesEmpty() == true) goto L10;
        AiConfig.setSelectedServices(AiController.getInstance().getAll().get(0));
    L10:
        getNotificationCenter().postNotificationName(NotificationCenter.servicesUpdated, new Object[0]);
        if (this.fragmentView == null) goto L16;
        UniversalAdapter r2 = getListView$TMessagesProj().adapter;
        if (r2 == null) goto L17;
        r2.update(true);
        return;
    L17:
        return;
    }

    private final CombinedDrawable getNewServiceDrawable() {
        if (this.newServiceDrawable != null) goto L10;
        this.newServiceCircle = getContext().getResources().getDrawable(R.drawable.poll_add_circle).mutate();
        Drawable r0 = getContext().getResources().getDrawable(R.drawable.poll_add_plus).mutate();
        this.newServicePlus = r0;
        Drawable r2 = this.newServiceCircle;
        if (r2 != null) goto L7;
        r2 = null;
    L7:
        if (r0 != null) goto L9;
        r0 = null;
    L9:
        this.newServiceDrawable = new AnonymousClass1(r2, r0);
    L10:
        Drawable r02 = this.newServiceCircle;
        if (r02 != null) goto L13;
        r02 = null;
    L13:
        int r3 = getThemedColor(Theme.key_switchTrackChecked);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r02.setColorFilter(new PorterDuffColorFilter(r3, r4));
        Drawable r03 = this.newServicePlus;
        if (r03 != null) goto L16;
        r03 = null;
    L16:
        r03.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_checkboxCheck), r4));
        CombinedDrawable r5 = this.newServiceDrawable;
        if (r5 != null) goto L19;
        return null;
    L19:
        return r5;
    }

    private final void onDeviceUnavailable(Service r2) {
        if (getContext() == null) goto L18;
        reloadServices();
        if (OnDeviceAvailability.isReady() == false) goto L12;
        if (r2.isSelected() == true) goto L17;
        AiConfig.setSelectedServices(r2);
        reloadServices();
        return;
    L17:
        return;
    L12:
        if (OnDeviceAvailability.needsDownload() == false) goto L15;
        OnDeviceModelDialogs.showDownloadDialog(this);
        return;
    L15:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.AIErrorOnDeviceInfo)).show();
        return;
    }

    private final void openAiCoreSettings() {
        Intent r0 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L4
        r0.setData(Uri.parse("package:com.google.android.aicore"));     // Catch: Exception -> L4
        r0.addFlags(268435456);     // Catch: Exception -> L4
        getContext().startActivity(r0);     // Catch: Exception -> L4
        return;
    L4:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.AIOnDeviceManageUnavailable)).show();
    }

    private final void reloadServices() {
        AiController.getInstance().loadServices();
        if (getContext() != null) goto L5;
        return;
    L5:
        if (this.fragmentView == null) goto L13;
        UniversalAdapter r1 = getListView$TMessagesProj().adapter;
        if (r1 == null) goto L12;
        r1.update(true);
        return;
    L12:
        return;
    }

    private final void showOnDeviceOptions(View r6) {
        ItemOptions r0 = ItemOptions.makeOptions(this, r6);
        if (OnDeviceAvailability.isThinkingSupported() == false) goto L5;
        r0.addChecked(AiConfig.getOnDeviceReasoning(), R.drawable.msg_discuss, LocaleController.getString(R.string.AIReasoning), new ServicesActivity$$ExternalSyntheticLambda7(this));
    L5:
        r0.addChecked(AiConfig.getOnDevicePreviewModel(), R.drawable.msg_fave, LocaleController.getString(R.string.AIOnDevicePreviewModel), new ServicesActivity$$ExternalSyntheticLambda8(this));
        r0.addChecked(AiConfig.getOnDeviceFastModel(), R.drawable.msg_speed, LocaleController.getString(R.string.AIOnDeviceFastModel), new ServicesActivity$$ExternalSyntheticLambda9(this));
        r0.addGap();
        r0.add(R.drawable.msg_reset, LocaleController.getString(R.string.AIOnDeviceRefresh), new ServicesActivity$$ExternalSyntheticLambda10(this));
        r0.add(R.drawable.msg_settings, LocaleController.getString(R.string.AIOnDeviceManage), new ServicesActivity$$ExternalSyntheticLambda11(this));
        if (LocaleController.isRTL == false) goto L8;
        int r1 = 3;
    L9:
        r0.setGravity(r1);
        r0.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r6));
        r0.show();
        return;
    L8:
        r1 = 5;
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.servicesUpdated) goto L6;
        reloadServices();
        return;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> r6, UniversalAdapter r7) {
        r6.add(UItem.asTopView(getTitle(), LocaleController.getString(R.string.ServicesInfo), "exteraGramPlaceholders", "🔑"));
        r6.add(UItem.asHeader(LocaleController.getString(R.string.Services)));
        Iterator<T> r72 = AiController.getInstance().getAll().iterator();
        int r0 = 0;
    L4:
        if (r72.hasNext() == false) goto L17;
        Object r1 = r72.next();
        int r2 = r0 + 1;
        if (r0 >= 0) goto L8;
        CollectionsKt.throwIndexOverflow();
    L8:
        Service r12 = (Service) r1;
        if (r12.isOnDevice() == false) goto L11;
        CharSequence r3 = applyOnDeviceBadge(r12.getModel());
    L13:
        if (r12.isOnDevice() == false) goto L15;
        String r4 = LocaleController.getString(R.string.AIOnDevice);
    L16:
        UItem r02 = UItem.asRadio2(r0 + 100, r3, r4).setChecked(r12.isSelected());
        r02.object = r12;
        r6.add(r02);
        r0 = r2;
        goto L4
    L15:
        r4 = r12.getUrl();
        goto L16
    L11:
        r3 = r12.getModel();
        goto L13
    L17:
        UItem r5 = UItem.asButton(1, getNewServiceDrawable(), LocaleController.getString(R.string.NewService)).accent();
        r5.pad = 61;
        r6.add(r5);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        return LocaleController.getString(R.string.Services);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean needHideTitle() {
        return true;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        Object r22 = r1.object;
        if ((r22 instanceof Service) == false) goto L5;
        Service r23 = (Service) r22;
    L6:
        int r12 = r1.id;
        if (r12 < 100) goto L29;
        if (r23 == null) goto L29;
        if (r23.isOnDevice() == false) goto L24;
        if (OnDeviceAvailability.isReady() == true) goto L24;
        if (OnDeviceAvailability.isDownloadInProgress() == false) goto L18;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.ic_download, LocaleController.getString(R.string.AIErrorOnDeviceDownloading), LocaleController.getString(R.string.AIOnDeviceManage), new ServicesActivity$$ExternalSyntheticLambda4(this)).show();
        return;
    L18:
        if (OnDeviceAvailability.needsDownload() == false) goto L21;
        OnDeviceModelDialogs.showDownloadDialog(this);
        return;
    L21:
        OnDeviceAvailability.refresh(new ServicesActivity$$ExternalSyntheticLambda5(this, r23));
        return;
    L24:
        if (r23.isSelected() == true) goto L32;
        AiConfig.setSelectedServices(r23);
        UniversalAdapter r0 = getListView$TMessagesProj().adapter;
        if (r0 == null) goto L33;
        r0.update(true);
        return;
    L33:
        return;
    L32:
        return;
    L29:
        if (r12 != 1) goto L34;
        presentFragment(new EditServiceActivity(null, 1, null));
        return;
    L34:
        return;
    L5:
        r23 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.servicesUpdated);
        OnDeviceAvailability.refresh(new ServicesActivity$$ExternalSyntheticLambda0(this));
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        AndroidUtilities.cancelRunOnUIThread(this.statusPoll);
        getNotificationCenter().removeObserver(this, NotificationCenter.servicesUpdated);
        super.onFragmentDestroy();
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem r3, View r4, int r5, float r6, float r7) {
        Object r32 = r3.object;
        if ((r32 instanceof Service) == false) goto L5;
        Service r33 = (Service) r32;
    L6:
        if (r33 != null) goto L10;
        return false;
    L10:
        if (r33.isOnDevice() == false) goto L13;
        showOnDeviceOptions(r4);
        return true;
    L13:
        ItemOptions r52 = ItemOptions.makeOptions(this, r4);
        r52.add(R.drawable.msg_edit, LocaleController.getString(R.string.Edit), new ServicesActivity$$ExternalSyntheticLambda1(this, r33));
        r52.add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new ServicesActivity$$ExternalSyntheticLambda2(r33, this));
        r52.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new ServicesActivity$$ExternalSyntheticLambda3(this, r33));
        if (LocaleController.isRTL == false) goto L16;
        int r34 = 3;
    L17:
        r52.setGravity(r34);
        r52.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(r4));
        r52.show();
        return true;
    L16:
        r34 = 5;
        goto L17
    L5:
        r33 = null;
        goto L6
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        AndroidUtilities.cancelRunOnUIThread(this.statusPoll);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        OnDeviceAvailability.refresh(new ServicesActivity$$ExternalSyntheticLambda0(this));
        AndroidUtilities.cancelRunOnUIThread(this.statusPoll);
        AndroidUtilities.runOnUIThread(this.statusPoll, 5000);
    }
}
