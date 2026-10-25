package com.exteragram.messenger.nowplaying.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.api.dto.NowPlayingInfoDTO;
import com.exteragram.messenger.api.model.NowPlayingServiceType;
import com.exteragram.messenger.nowplaying.NowPlayingController;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogRadioCell;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001)B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J(\u0010\u0013\u001a\u00020\u00142\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J0\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010(\u001a\u00020&H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity;", "Lorg/telegram/ui/ActionBar/BaseFragment;", "<init>", "()V", "nowPlayingEdit", "Lorg/telegram/ui/Cells/EditTextCell;", "listView", "Lorg/telegram/ui/Components/UniversalRecyclerView;", "initialState", "Lcom/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity$NowPlayingState;", "currentState", "doneButtonDrawable", "Lorg/telegram/ui/Components/CrossfadeDrawable;", "doneButton", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "fillItems", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", _UrlKt.FRAGMENT_ENCODE_SET, "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "shiftDp", "processDone", "error", _UrlKt.FRAGMENT_ENCODE_SET, "checkDone", "animated", "NowPlayingState", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSetupNowPlayingActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SetupNowPlayingActivity.kt\ncom/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,239:1\n1#2:240\n37#3,2:241\n*S KotlinDebug\n*F\n+ 1 SetupNowPlayingActivity.kt\ncom/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity\n*L\n181#1:241,2\n*E\n"})
/* loaded from: classes4.dex */
public final class SetupNowPlayingActivity extends BaseFragment {
    private NowPlayingState currentState;
    private ActionBarMenuItem doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private NowPlayingState initialState;
    private UniversalRecyclerView listView;
    private EditTextCell nowPlayingEdit;
    private int shiftDp;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity$NowPlayingState;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/model/NowPlayingServiceType;", "serviceType", _UrlKt.FRAGMENT_ENCODE_SET, "username", "<init>", "(Lcom/exteragram/messenger/api/model/NowPlayingServiceType;Ljava/lang/String;)V", "copy", "(Lcom/exteragram/messenger/api/model/NowPlayingServiceType;Ljava/lang/String;)Lcom/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity$NowPlayingState;", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lcom/exteragram/messenger/api/model/NowPlayingServiceType;", "getServiceType", "()Lcom/exteragram/messenger/api/model/NowPlayingServiceType;", "Ljava/lang/String;", "getUsername", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class NowPlayingState {
        private final NowPlayingServiceType serviceType;
        private final String username;

        public NowPlayingState(NowPlayingServiceType r1, String r2) {
            this.serviceType = r1;
            this.username = r2;
        }

        public static /* synthetic */ NowPlayingState copy$default(NowPlayingState r0, NowPlayingServiceType r1, String r2, int r3, Object r4) {
            if ((r3 & 1) == 0) goto L6;
            r1 = r0.serviceType;
        L6:
            if ((r3 & 2) == 0) goto L9;
            r2 = r0.username;
        L9:
            return r0.copy(r1, r2);
        }

        public final NowPlayingState copy(NowPlayingServiceType r1, String r2) {
            return new NowPlayingState(r1, r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof NowPlayingState) == true) goto L8;
            return false;
        L8:
            NowPlayingState r52 = (NowPlayingState) r5;
            if (this.serviceType == r52.serviceType) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.username, r52.username) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final NowPlayingServiceType getServiceType() {
            return this.serviceType;
        }

        public final String getUsername() {
            return this.username;
        }

        public int hashCode() {
            int r0 = this.serviceType.hashCode() * 31;
            String r1 = this.username;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return r0 + r12;
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public String toString() {
            return "NowPlayingState(serviceType=" + this.serviceType + ", username=" + this.username + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity$createView$1", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.nowplaying.ui.SetupNowPlayingActivity$createView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ SetupNowPlayingActivity this$0;

        public AnonymousClass1(SetupNowPlayingActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L9;
            if (r3 != 0) goto L13;
            SetupNowPlayingActivity.access$processDone(this.this$0, true);
            return;
        L13:
            return;
        L9:
            if (this.this$0.onBackPressed(true) == false) goto L12;
            this.this$0.finishFragment();
            return;
        }
    }

    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/nowplaying/ui/SetupNowPlayingActivity$createView$4", "Lorg/telegram/ui/Cells/EditTextCell;", "onTextChanged", _UrlKt.FRAGMENT_ENCODE_SET, "newText", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.nowplaying.ui.SetupNowPlayingActivity$createView$4, reason: invalid class name */
    public static final class AnonymousClass4 extends EditTextCell {
        final /* synthetic */ SetupNowPlayingActivity this$0;

        public AnonymousClass4(Context r8, SetupNowPlayingActivity r9, String r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r8, r10, false, false, 50, r11);
        }

        @Override // org.telegram.ui.Cells.EditTextCell
        public void onTextChanged(CharSequence r5) {
            super.onTextChanged(r5);
            SetupNowPlayingActivity r0 = this.this$0;
            SetupNowPlayingActivity.access$setCurrentState$p(r0, NowPlayingState.copy$default(SetupNowPlayingActivity.access$getCurrentState$p(r0), null, r5.toString(), 1, null));
            SetupNowPlayingActivity.access$checkDone(this.this$0, true);
        }
    }

    public static void $r8$lambda$65Eo9dAoQu20mGd5AiKcotAyIc8(SetupNowPlayingActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static void $r8$lambda$GvDsubjmbhvBbRYmXXSIlK254A4(SetupNowPlayingActivity r1, NowPlayingInfoDTO r2, Boolean r3) {
        AndroidUtilities.runOnUIThread(new SetupNowPlayingActivity$$ExternalSyntheticLambda6(r3, r1, r2));
    }

    public static void $r8$lambda$MSonrrRrgZu58S0CRLr_ONVDk_k(Boolean r1, SetupNowPlayingActivity r2, NowPlayingInfoDTO r3) {
        if (r1.booleanValue() == true) goto L6;
        r2.doneButtonDrawable.animateToProgress(0.0f);
        BulletinFactory.of(r2).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        return;
    L6:
        r2.getNotificationCenter().postNotificationName(NotificationCenter.nowPlayingUpdated, new Object[]{r3.getServiceType()});
        r2.finishFragment();
    }

    /* renamed from: $r8$lambda$aYleJsY0ObT6PFj-JTH8W4zOu6w, reason: not valid java name */
    public static void m1506$r8$lambda$aYleJsY0ObT6PFjJTH8W4zOu6w(SetupNowPlayingActivity r1, NowPlayingInfoDTO r2) {
        AndroidUtilities.runOnUIThread(new SetupNowPlayingActivity$$ExternalSyntheticLambda3(r2, r1));
    }

    public static void $r8$lambda$kY74elv6_RazXh9BxDMIwSdGQz8(NowPlayingInfoDTO r3, SetupNowPlayingActivity r4) {
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 == null) goto L9;
        NowPlayingServiceType r2 = r3.getServiceType();
        String r32 = r3.getUsername();
        if (r32 == null) goto L8;
        r0 = r32;
    L8:
        NowPlayingState r1 = new NowPlayingState(r2, r0);
    L10:
        r4.currentState = r1;
        r4.initialState = r1;
        EditTextCell r33 = r4.nowPlayingEdit;
        UniversalRecyclerView r02 = null;
        if (r33 != null) goto L13;
        r33 = null;
    L13:
        r33.setText(r1.getUsername());
        UniversalRecyclerView r34 = r4.listView;
        if (r34 == null) goto L17;
        r02 = r34;
    L17:
        r02.adapter.update(true);
        r4.checkDone(false);
        return;
    L9:
        r1 = new NowPlayingState(NowPlayingServiceType.NONE, _UrlKt.FRAGMENT_ENCODE_SET);
        goto L10
    }

    /* renamed from: $r8$lambda$wgnVTF9u-5xJNetChrEj7S2xD90, reason: not valid java name */
    public static void m1507$r8$lambda$wgnVTF9u5xJNetChrEj7S2xD90(SetupNowPlayingActivity r2) {
        Activity r0 = r2.getParentActivity();
        if (r2.currentState.getServiceType() != NowPlayingServiceType.LAST_FM) goto L5;
        String r22 = "https://www.last.fm/";
    L6:
        Browser.openUrl(r0, r22);
        return;
    L5:
        r22 = "https://stats.fm/";
        goto L6
    }

    public static void $r8$lambda$yD3XffdomCr8Fj72OprKQC9RicE(SetupNowPlayingActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public SetupNowPlayingActivity() {
        this.currentState = new NowPlayingState(NowPlayingServiceType.NONE, null);
        this.shiftDp = -4;
    }

    public static final /* synthetic */ void access$checkDone(SetupNowPlayingActivity r0, boolean r1) {
        r0.checkDone(r1);
    }

    public static final /* synthetic */ NowPlayingState access$getCurrentState$p(SetupNowPlayingActivity r0) {
        return r0.currentState;
    }

    public static final /* synthetic */ void access$processDone(SetupNowPlayingActivity r0, boolean r1) {
        r0.processDone(r1);
    }

    public static final /* synthetic */ void access$setCurrentState$p(SetupNowPlayingActivity r0, NowPlayingState r1) {
        r0.currentState = r1;
    }

    private final void checkDone(boolean r4) {
        if (this.doneButton == null) goto L34;
        boolean r0 = Intrinsics.areEqual(this.initialState, this.currentState);
        this.doneButton.setEnabled(!r0);
        ActionBarMenuItem r3 = this.doneButton;
        float r1 = 0.0f;
        if (r4 == false) goto L20;
        ViewPropertyAnimator r32 = r3.animate();
        if (r0 == true) goto L10;
        float r42 = 1.0f;
    L11:
        ViewPropertyAnimator r33 = r32.alpha(r42);
        if (r0 == true) goto L14;
        float r43 = 1.0f;
    L15:
        ViewPropertyAnimator r34 = r33.scaleX(r43);
        if (r0 == true) goto L18;
        r1 = 1.0f;
    L18:
        r34.scaleY(r1).setDuration(180).start();
        return;
    L14:
        r43 = 0.0f;
        goto L15
    L10:
        r42 = 0.0f;
        goto L11
    L20:
        if (r3 == null) goto L33;
        if (r0 == true) goto L23;
        float r44 = 1.0f;
    L24:
        r3.setAlpha(r44);
        if (r0 == true) goto L27;
        float r45 = 1.0f;
    L28:
        r3.setScaleX(r45);
        if (r0 == true) goto L31;
        r1 = 1.0f;
    L31:
        r3.setScaleY(r1);
        return;
    L27:
        r45 = 0.0f;
        goto L28
    L23:
        r44 = 0.0f;
        goto L24
    L33:
        return;
    }

    private final void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asHeader(LocaleController.getString(R.string.SelectService)));
        Iterator<NowPlayingServiceType> r52 = NowPlayingServiceType.getEntries().iterator();
    L4:
        if (r52.hasNext() == false) goto L12;
        NowPlayingServiceType r0 = r52.next();
        UItem r1 = UItem.asRadio(r0.ordinal(), r0.getDisplayName());
        if (r0 == this.currentState.getServiceType()) goto L8;
    L10:
        boolean r02 = false;
    L11:
        r4.add(r1.setChecked(r02));
        goto L4
    L8:
        if (this.initialState == null) goto L10;
        r02 = true;
        goto L11
    L12:
        r4.add(UItem.asShadow(LocaleController.getString(R.string.SelectServiceInfo)));
        if (this.currentState.getServiceType() == NowPlayingServiceType.NONE) goto L22;
        r4.add(UItem.asHeader(LocaleController.getString(R.string.EnterUsername)));
        EditTextCell r53 = this.nowPlayingEdit;
        if (r53 != null) goto L17;
        r53 = null;
    L17:
        r4.add(UItem.asCustom(r53));
        r4.add(UItem.asShadow(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.EnterUsernameInfo), new SetupNowPlayingActivity$$ExternalSyntheticLambda4(this))));
        return;
    }

    private final void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if ((r2 instanceof DialogRadioCell) == false) goto L10;
        NowPlayingServiceType r12 = ((NowPlayingServiceType[]) NowPlayingServiceType.getEntries().toArray(new NowPlayingServiceType[0]))[r1.id];
        UniversalRecyclerView r42 = null;
        this.currentState = NowPlayingState.copy$default(this.currentState, r12, null, 2, null);
        UniversalRecyclerView r13 = this.listView;
        if (r13 == null) goto L8;
        r42 = r13;
    L8:
        r42.adapter.update(true);
        checkDone(true);
        return;
    }

    private final void processDone(boolean r5) {
        if (this.doneButtonDrawable.getProgress() <= 0.0f) goto L5;
        return;
    L5:
        EditTextCell r0 = null;
        if (r5 == true) goto L8;
    L22:
        this.doneButtonDrawable.animateToProgress(1.0f);
        NowPlayingServiceType r1 = this.currentState.getServiceType();
        if (this.currentState.getServiceType() != NowPlayingServiceType.NONE) goto L25;
        String r2 = null;
    L26:
        NowPlayingInfoDTO r52 = new NowPlayingInfoDTO(r1, r2);
        NowPlayingController.updateNowPlayingInfo$default(r52, false, new SetupNowPlayingActivity$$ExternalSyntheticLambda5(this, r52), 2, null);
        return;
    L25:
        r2 = this.currentState.getUsername();
        goto L26
    L8:
        if (this.currentState.getServiceType() == NowPlayingServiceType.NONE) goto L22;
        EditTextCell r53 = this.nowPlayingEdit;
        if (r53 != null) goto L12;
        r53 = null;
    L12:
        CharSequence r54 = r53.getText();
        if (r54 != null) goto L15;
    L16:
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        EditTextCell r55 = this.nowPlayingEdit;
        if (r55 == null) goto L20;
        r0 = r55;
    L20:
        int r56 = -this.shiftDp;
        this.shiftDp = r56;
        AndroidUtilities.shakeViewSpring(r0, r56);
        return;
    L15:
        if (r54.length() != 0) goto L22;
        goto L16
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.NowPlaying));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r7);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        Drawable r4 = null;
        UniversalRecyclerView r1 = new UniversalRecyclerView(this, new SetupNowPlayingActivity$$ExternalSyntheticLambda0(this), new SetupNowPlayingActivity$$ExternalSyntheticLambda1(this), null);
        this.listView = r1;
        r1.setSections();
        ActionBar r12 = this.actionBar;
        UniversalRecyclerView r2 = this.listView;
        if (r2 != null) goto L5;
        r2 = null;
    L5:
        r12.setAdaptiveBackground(r2);
        UniversalRecyclerView r13 = this.listView;
        if (r13 != null) goto L8;
        r13 = null;
    L8:
        r13.adapter.setApplyBackground(false);
        UniversalRecyclerView r14 = this.listView;
        if (r14 != null) goto L11;
        r14 = null;
    L11:
        r0.addView(r14, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass4 r5 = new AnonymousClass4(r7, this, LocaleController.getString(R.string.Username), this.resourceProvider);
        this.nowPlayingEdit = r5;
        r5.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        EditTextCell r15 = this.nowPlayingEdit;
        if (r15 != null) goto L14;
        r15 = null;
    L14:
        r15.hideKeyboardOnEnter();
        NowPlayingController.getNowPlayingInfo(new SetupNowPlayingActivity$$ExternalSyntheticLambda2(this));
        Drawable r72 = ContextCompat.getDrawable(r7, R.drawable.ic_ab_done);
        if (r72 == null) goto L17;
        r4 = r72.mutate();
    L17:
        if (r4 == null) goto L19;
        r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultIcon), PorterDuff.Mode.MULTIPLY));
    L19:
        this.doneButtonDrawable = new CrossfadeDrawable(r4, new CircularProgressDrawable(Theme.getColor(Theme.key_actionBarDefaultIcon)));
        this.doneButton = this.actionBar.createMenu().addItemWithWidth(0, this.doneButtonDrawable, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDone(false);
        this.fragmentView = r0;
        return r0;
    }
}
