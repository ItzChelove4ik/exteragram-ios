package org.telegram.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.BotWebViewSheet;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public abstract class OAuthSheet {
    private static BottomSheet showing;

    /* renamed from: org.telegram.ui.OAuthSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends Drawable {
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ Text val$text;

        public AnonymousClass1(Text r1, Theme.ResourcesProvider r2) {
            this.val$text = r1;
            this.val$resourcesProvider = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            this.val$text.draw(r7, getBounds().centerX() - (this.val$text.getCurrentWidth() / 2.0f), getBounds().centerY(), Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.val$resourcesProvider), 1.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$3lihHEW1tILjKbxIgKnX7YRsiak(Context r1) {
        Activity r12 = AndroidUtilities.findActivity(r1);
        if (r12 != null) goto L5;
        r12 = LaunchActivity.instance;
    L5:
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.isFinishing() == true) goto L11;
        r12.moveTaskToBack(true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$4DvKHYHrJDvFx1GneRe3QEE2swg(AlertDialog r0, BottomSheet r1, String r2, Theme.ResourcesProvider r3, TLRPC.Bool r4, TLRPC.TL_error r5) {
        r0.dismiss();
        if ((r4 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        showing = r1;
        r1.show();
        return;
    L6:
        BottomSheet r02 = showing;
        if (r02 == null) goto L9;
        r02.dismiss();
        showing = null;
    L9:
        BulletinFactory r03 = getBulletinFactory();
        int r12 = R.raw.error;
        String r42 = LocaleController.getString(R.string.BotAuthLoggedInFailTitle);
        if (TextUtils.isEmpty(r2) == false) goto L12;
        CharSequence r22 = LocaleController.getString(R.string.BotAuthLoggedInFailNoDomain);
    L13:
        r03.createSimpleBulletin(r12, r42, r22).show();
        return;
    L12:
        r22 = AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.BotAuthLoggedInFail, new Object[]{r2}), Theme.getColor(Theme.key_undo_cancelColor, r3));
        goto L13
    }

    public static /* synthetic */ void $r8$lambda$6xPbgwiQuEyzUNYJAAB_K6HFjFM(TLRPC.TL_urlAuthResultRequest r9, String[] r10, Context r11, int r12, Runnable r13, BaseFragment r14) {
        if (r9.match_codes.isEmpty() == false) goto L5;
    L8:
        r13.run();
        return;
    L5:
        if (TextUtils.isEmpty(r10[0]) == false) goto L8;
        showMatchCodeSheet(r11, r12, r9.match_codes, r9.domain, new OAuthSheet$$ExternalSyntheticLambda16(r10, r13), true, new OAuthSheet$$ExternalSyntheticLambda17(), r14.getResourceProvider());
    }

    public static /* synthetic */ void $r8$lambda$AAswbIL2pMdG7We6ICRnWfm2ZPA(ButtonWithCounterView r0, ButtonWithCounterView r1, TLRPC.TL_urlAuthResultRequest r2, int[] r3, Context r4, BaseFragment r5, boolean r6, boolean r7, String r8, boolean[] r9, Runnable r10, View r11) {
        if (r0.isLoading() == false) goto L5;
        return;
    L5:
        if (r1.isLoading() == false) goto L8;
        return;
    L8:
        if (r2.request_phone_number == false) goto L15;
        TLRPC.User r02 = UserConfig.getInstance(r3[0]).getCurrentUser();
        AlertDialog.Builder r12 = new AlertDialog.Builder(r4, r5.getResourceProvider()).setTitle(LocaleController.getString(R.string.BotAuthPhoneNumber));
        int r32 = R.string.BotAuthPhoneNumberText;
        if (r6 == false) goto L13;
        if (r7 == true) goto L13;
        r8 = UserObject.getUserName(r2.bot);
    L13:
        r12.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(r32, new Object[]{r8, PhoneFormat.getInstance().format("+" + r02.phone).replaceAll(" ", " ")}))).setNegativeButton(LocaleController.getString(R.string.BotAuthPhoneNumberDeny), new OAuthSheet$$ExternalSyntheticLambda22(r9, r10)).setPositiveButton(LocaleController.getString(R.string.BotAuthPhoneNumberAccept), new OAuthSheet$$ExternalSyntheticLambda23(r9, r10)).makeRed(-2).show();
        return;
    L15:
        r10.run();
    }

    public static /* synthetic */ void $r8$lambda$Foy1WD7Lw4Sl81Axg8hb75DEfbQ(boolean[] r3, BotWebViewContainer r4, TLRPC.TL_messages_requestUrlAuth r5, int r6) {
        showing = null;
        if (r3[0] == true) goto L12;
        r3[0] = true;
        if (r4 == null) goto L7;
        r4.notifyEvent("oauth_result_failed", BotWebViewContainer.obj());
    L7:
        if (r5 != null) goto L9;
        return;
    L9:
        if (TextUtils.isEmpty(r5.url) == true) goto L14;
        TLRPC.TL_messages_declineUrlAuth r32 = new TLRPC.TL_messages_declineUrlAuth();
        r32.url = r5.url;
        ConnectionsManager.getInstance(r6).sendRequest(r32, null);
        return;
    L14:
        return;
    }

    public static /* synthetic */ void $r8$lambda$ItlOwHQ0s5YEaaQexFOiw72zpBQ(BottomSheet[] r2, DialogInterface r3) {
        showing = null;
        BottomSheet r1 = r2[0];
        if (r1 == null) goto L6;
        r1.dismiss();
        r2[0] = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$Ksf0UZGXVXhBJxF7xJZJZbnNlec(ButtonWithCounterView r12, ButtonWithCounterView r13, TLRPC.TL_messages_requestUrlAuth r14, String[] r15, TextCheckCell r16, boolean[] r17, int[] r18, boolean[] r19, BottomSheet r20, String r21, Theme.ResourcesProvider r22, boolean r23, String r24, TLRPC.TL_urlAuthResultRequest r25, BotWebViewContainer r26) {
        if (r12.isLoading() == false) goto L6;
        return;
    L6:
        if (r13.isLoading() == false) goto L8;
        return;
    L8:
        boolean r132 = true;
        r12.setLoading(true);
        TLRPC.TL_messages_acceptUrlAuth r10 = new TLRPC.TL_messages_acceptUrlAuth();
        if (TLObject.hasFlag(r14.flags, 2) == false) goto L12;
        r10.flags |= 2;
        r10.peer = r14.peer;
        r10.msg_id = r14.msg_id;
        r10.button_id = r14.button_id;
    L12:
        if (TLObject.hasFlag(r14.flags, 4) == false) goto L14;
        r10.flags |= 4;
        r10.url = r14.url;
    L14:
        String r0 = r15[0];
        if (r0 == null) goto L17;
        r10.match_code = r0;
    L17:
        if (r16 != null) goto L19;
    L21:
        r132 = false;
    L22:
        r10.write_allowed = r132;
        r10.share_phone_number = r17[0];
        ConnectionsManager.getInstance(r18[0]).sendRequestTyped(r10, new AiTonesController$$ExternalSyntheticLambda0(), new OAuthSheet$$ExternalSyntheticLambda15(r19, r20, r21, r22, r23, r18, r14, r24, r25, r10, r26));
        return;
    L19:
        if (r16.isChecked() == false) goto L21;
        goto L21
    }

    public static /* synthetic */ void $r8$lambda$KxXt3asaUsxnKMGProHSahhnkC4(BottomSheet r4, FrameLayout r5, ArrayList r6, int[] r7, Utilities.Callback r8, View r9) {
        ItemOptions r42 = ItemOptions.makeOptions(r4.container, r4.getResourcesProvider(), r5);
        int r52 = r6.size();
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L12;
        Object r1 = r6.get(r0);
        r0 = r0 + 1;
        int r12 = ((Integer) r1).intValue();
        if (UserConfig.getInstance(r12).getCurrentUser() == null) goto L3;
        if (r7[0] != r12) goto L10;
        boolean r2 = true;
    L11:
        r42.addAccount(r12, r2, new OAuthSheet$$ExternalSyntheticLambda18(r8, r12));
        goto L3
    L10:
        r2 = false;
        goto L11
    L12:
        r42.setDrawScrim(false).setOnTopOfScrim().setDimAlpha(0).setGravity(3).translate(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f)).show();
    }

    public static /* synthetic */ void $r8$lambda$LEF4U4aGV6a65A5Hsz9Z0_E37nc(boolean[] r0, BottomSheet r1, TLRPC.Bool r2, TLRPC.TL_error r3) {
        r0[0] = true;
        r1.dismiss();
    }

    /* renamed from: $r8$lambda$QH7aFdNPh8Gei4-JjHn1iAiSUKU, reason: not valid java name */
    public static /* synthetic */ void m16352$r8$lambda$QH7aFdNPh8Gei4JjHn1iAiSUKU(AlertDialog r0, BottomSheet r1, boolean r2, Integer r3, TLRPC.TL_messages_requestUrlAuth r4, String r5, TLRPC.UrlAuthResult r6, String[] r7, boolean r8, BotWebViewContainer r9, String r10, Theme.ResourcesProvider r11, TLRPC.UrlAuthResult r12, TLRPC.TL_error r13) {
        r0.dismiss();
        if (r12 == null) goto L6;
        r1.dismiss();
        handle(r2, r3.intValue(), r4, r12, r5, r6, r7[0], r8, r9);
        return;
    L6:
        if (r13 != null) goto L8;
        return;
    L8:
        if ("URL_EXPIRED".equalsIgnoreCase(r13.text) == false) goto L15;
        r1.dismiss();
        BulletinFactory r02 = getBulletinFactory();
        int r14 = R.raw.error;
        String r22 = LocaleController.getString(R.string.BotAuthLoggedInFailTitle);
        if (TextUtils.isEmpty(r10) == false) goto L12;
        CharSequence r32 = LocaleController.getString(R.string.BotAuthLoggedInFailNoDomain);
    L13:
        r02.createSimpleBulletin(r14, r22, r32).show();
        return;
    L12:
        r32 = AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.BotAuthLoggedInFail, new Object[]{r10}), Theme.getColor(Theme.key_undo_cancelColor, r11));
        goto L13
    L15:
        BulletinFactory.of(r1.topBulletinContainer, r1.getResourcesProvider()).showForError(r13);
    }

    public static /* synthetic */ void $r8$lambda$QMRsKZdGBNWh_PnTkts_J0NLAyU(TextCheckCell r0, View r1) {
        r0.setChecked(!r0.isChecked());
    }

    public static /* synthetic */ void $r8$lambda$U1LH9SuT0RMFwlUwp1no2gswtgQ(ButtonWithCounterView r0, BottomSheet[] r1, Runnable r2, View r3) {
        if (r0.isLoading() == true) goto L10;
        BottomSheet r32 = r1[0];
        if (r32 == null) goto L9;
        r32.dismiss();
        r1[0] = null;
        r2.run();
        return;
    L9:
        return;
    }

    public static /* synthetic */ void $r8$lambda$Vwf3ayBT571WRJWaRL5oT5_Gm5M(Utilities.Callback r0, int r1) {
        r0.run(Integer.valueOf(r1));
    }

    public static /* synthetic */ void $r8$lambda$_XhonlkijnqPLxQI1Pl3uWgZHTg(int[] r14, TLRPC.TL_messages_requestUrlAuth r15, BottomSheet r16, boolean r17, String r18, TLRPC.UrlAuthResult r19, String[] r20, boolean r21, BotWebViewContainer r22, String r23, Theme.ResourcesProvider r24, Integer r25) {
        if (r14[0] != r25.intValue()) goto L5;
        return;
    L5:
        AlertDialog r2 = new AlertDialog(ApplicationLoader.applicationContext, 3);
        r2.showDelayed(200);
        ConnectionsManager.getInstance(r25.intValue()).sendRequestTyped(r15, new AiTonesController$$ExternalSyntheticLambda0(), new OAuthSheet$$ExternalSyntheticLambda20(r2, r16, r17, r25, r15, r18, r19, r20, r21, r22, r23, r24));
    }

    public static /* synthetic */ void $r8$lambda$_v5KRZYa6dp0PtdBna1SElZe1B0(boolean[] r0, Runnable r1, AlertDialog r2, int r3) {
        r0[0] = false;
        r1.run();
    }

    public static /* synthetic */ int $r8$lambda$b4FpqIvCNWYnjZflWn0J4aEpMao(Integer r2, Integer r3) {
        long r0 = UserConfig.getInstance(r2.intValue()).loginTime;
        long r22 = UserConfig.getInstance(r3.intValue()).loginTime;
        if (r0 <= r22) goto L6;
        return 1;
    L6:
        if (r0 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$jC2YU9RuChWHWeVgWqh7nBtoVt0() {
    }

    public static /* synthetic */ void $r8$lambda$pfTlQM9HwhspZJEfT70vEXzhlY8(ArrayList r13, BackupImageView[] r14, TLRPC.TL_messages_stickerSet r15) {
        if (r15 != null) goto L4;
        return;
    L4:
        if (r15.set == null) goto L37;
        int r1 = 0;
    L8:
        if (r1 >= r13.size()) goto L38;
        String r2 = (String) r13.get(r1);
        int r3 = 0;
    L10:
        TLRPC.Document r5 = null;
        if (r3 >= r15.packs.size()) goto L24;
        if (r15.packs.get(r3).documents.isEmpty() == true) goto L23;
        if (TextUtils.equals(r15.packs.get(r3).emoticon, r2) == false) goto L23;
        long r32 = r15.packs.get(r3).documents.get(0).longValue();
        int r6 = 0;
    L18:
        if (r6 >= r15.documents.size()) goto L24;
        if (r15.documents.get(r6).id == r32) goto L21;
        r6 = r6 + 1;
        goto L18
    L21:
        r5 = r15.documents.get(r6);
    L23:
        r3 = r3 + 1;
    L24:
        if (r5 == null) goto L26;
        r14[r1].setImage(ImageLocation.getForDocument(r5), "40_40", ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r5.thumbs, 40), r5), "40_40", Emoji.getEmojiBigDrawable(r2), null);
    L26:
        r1 = r1 + 1;
        goto L8
    L38:
        return;
    }

    /* renamed from: $r8$lambda$vmd5HyhxjlEvAUp-kN27745VRlk, reason: not valid java name */
    public static /* synthetic */ void m16353$r8$lambda$vmd5HyhxjlEvAUpkN27745VRlk(String[] r1, Runnable r2, String r3) {
        r1[0] = r3;
        r2.run();
    }

    public static /* synthetic */ void $r8$lambda$wMhAEAmdBygSATigTTqIkxlshXY(BottomSheet[] r1, Utilities.Callback r2, String r3, View r4) {
        BottomSheet r0 = r1[0];
        if (r0 == null) goto L6;
        r0.dismiss();
        r1[0] = null;
        r2.run(r3);
        return;
    }

    public static /* synthetic */ void $r8$lambda$x55_jhWYHbgJgOmuImGb3tCtbd8(boolean[] r2, BottomSheet r3, String r4, Theme.ResourcesProvider r5, boolean r6, int[] r7, TLRPC.TL_messages_requestUrlAuth r8, String r9, TLRPC.TL_urlAuthResultRequest r10, TLRPC.TL_messages_acceptUrlAuth r11, BotWebViewContainer r12, TLRPC.UrlAuthResult r13, TLRPC.TL_error r14) {
        r2[0] = true;
        r3.dismiss();
        if (r14 != null) goto L5;
        handle(r6, r7[0], r8, r13, r9, r10, null, r11.share_phone_number, r12);
        return;
    L5:
        if ("URL_EXPIRED".equalsIgnoreCase(r14.text) == false) goto L12;
        BulletinFactory r22 = getBulletinFactory();
        int r32 = R.raw.error;
        String r62 = LocaleController.getString(R.string.BotAuthLoggedInFailTitle);
        if (TextUtils.isEmpty(r4) == false) goto L9;
        CharSequence r42 = LocaleController.getString(R.string.BotAuthLoggedInFailNoDomain);
    L10:
        r22.createSimpleBulletin(r32, r62, r42).show();
        return;
    L9:
        r42 = AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.BotAuthLoggedInFail, new Object[]{r4}), Theme.getColor(Theme.key_undo_cancelColor, r5));
        goto L10
    L12:
        getBulletinFactory().showForError(r14);
    }

    /* renamed from: $r8$lambda$xnxdbcMFvYfYefG-2jCmQJ3S1m4, reason: not valid java name */
    public static /* synthetic */ void m16354$r8$lambda$xnxdbcMFvYfYefG2jCmQJ3S1m4(Context r3, String[] r4, TLRPC.TL_messages_requestUrlAuth r5, int r6, BottomSheet r7, String r8, Theme.ResourcesProvider r9, String r10) {
        AlertDialog r0 = new AlertDialog(r3, 3);
        r0.showDelayed(200);
        TLRPC.TL_messages_checkUrlAuthMatchCode r32 = new TLRPC.TL_messages_checkUrlAuthMatchCode();
        r4[0] = r10;
        r32.match_code = r10;
        r32.url = r5.url;
        ConnectionsManager.getInstance(r6).sendRequestTyped(r32, new AiTonesController$$ExternalSyntheticLambda0(), new OAuthSheet$$ExternalSyntheticLambda21(r0, r7, r8, r9));
    }

    public static /* synthetic */ void $r8$lambda$zFEFn3uSz5fZExu1EDUAFOgXuac(boolean[] r0, Runnable r1, AlertDialog r2, int r3) {
        r0[0] = true;
        r1.run();
    }

    public static /* synthetic */ void $r8$lambda$zN4O2JcVZJ2SPRwDmEJNBYP5WTw(TLRPC.TL_messages_requestUrlAuth r1, boolean[] r2, BottomSheet r3, ButtonWithCounterView r4, BotWebViewContainer r5, int r6, View r7) {
        if (r1 != null) goto L5;
    L15:
        r2[0] = true;
        r3.dismiss();
        return;
    L5:
        if (TextUtils.isEmpty(r1.url) == true) goto L15;
        if (r4.isLoading() == false) goto L10;
        return;
    L10:
        r4.setLoading(true);
        if (r5 == null) goto L13;
        r5.notifyEvent("oauth_result_failed", BotWebViewContainer.obj());
    L13:
        TLRPC.TL_messages_declineUrlAuth r42 = new TLRPC.TL_messages_declineUrlAuth();
        r42.url = r1.url;
        ConnectionsManager.getInstance(r6).sendRequestTyped(r42, new AiTonesController$$ExternalSyntheticLambda0(), new OAuthSheet$$ExternalSyntheticLambda19(r2, r3));
    }

    public static BulletinFactory getBulletinFactory() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (BotWebViewSheet.activeSheets.isEmpty() == true) goto L22;
        Iterator<BotWebViewSheet> r1 = BotWebViewSheet.activeSheets.iterator();
        BotWebViewSheet r3 = null;
    L6:
        if (r1.hasNext() == false) goto L10;
        BotWebViewSheet r4 = r1.next();
        if (r4.attached == false) goto L6;
        r3 = r4;
        goto L6
    L10:
        if (r3 == null) goto L22;
        if (r0 != null) goto L13;
    L15:
        Context r02 = LaunchActivity.instance;
        if (r02 != null) goto L20;
        r02 = ApplicationLoader.applicationContext;
    L20:
        return BulletinFactory.of(Bulletin.BulletinWindow.make(r02), null);
    L13:
        if (r0.getParentActivity() == null) goto L15;
        r02 = r0.getParentActivity();
    L22:
        if (ArticleViewer.activeSheets.isEmpty() == true) goto L40;
        Iterator<ArticleViewer> r12 = ArticleViewer.activeSheets.iterator();
        ArticleViewer r32 = null;
    L25:
        if (r12.hasNext() == false) goto L29;
        ArticleViewer r42 = r12.next();
        if (r42.isVisible() == false) goto L25;
        r32 = r42;
        goto L25
    L29:
        if (r32 == null) goto L40;
        if (r0 != null) goto L32;
    L34:
        Context r03 = LaunchActivity.instance;
        if (r03 != null) goto L39;
        r03 = ApplicationLoader.applicationContext;
    L39:
        return BulletinFactory.of(Bulletin.BulletinWindow.make(r03), null);
    L32:
        if (r0.getParentActivity() == null) goto L34;
        r03 = r0.getParentActivity();
    L40:
        if (r0 == null) goto L48;
        if (r0.getLastSheet() == null) goto L48;
        if (r0.getLastSheet().getBulletinFactory() == null) goto L48;
        return r0.getLastSheet().getBulletinFactory();
    L48:
        return BulletinFactory.of(r0);
    }

    public static void handle(boolean r9, int r10, TLRPC.TL_messages_requestUrlAuth r11, TLRPC.UrlAuthResult r12) {
        handle(r9, r10, r11, r12, null, null, null, false, null);
    }

    public static void handle(boolean r43, int r44, TLRPC.TL_messages_requestUrlAuth r45, TLRPC.UrlAuthResult r46, String r47, TLRPC.UrlAuthResult r48, String r49, boolean r50, BotWebViewContainer r51) {
        if ((r46 instanceof TLRPC.TL_urlAuthResultAccepted) == false) goto L58;
        TLRPC.TL_urlAuthResultAccepted r0 = (TLRPC.TL_urlAuthResultAccepted) r46;
        if (r51 == null) goto L13;
        if (TextUtils.isEmpty(r45.in_app_origin) == false) goto L10;
        return;
    L10:
        if (TextUtils.equals(r51.getOriginHost(), r45.in_app_origin) == true) goto L13;
        return;
    L13:
        if (TextUtils.isEmpty(r0.url) == false) goto L49;
        boolean r02 = r48 instanceof TLRPC.TL_urlAuthResultRequest;
        if (r02 == false) goto L23;
        TLRPC.TL_urlAuthResultRequest r1 = (TLRPC.TL_urlAuthResultRequest) r48;
        if (r1.is_app == true) goto L19;
        String r12 = r1.domain;
    L25:
        if (TextUtils.isEmpty(r12) == true) goto L37;
        if (r02 == true) goto L28;
    L31:
        boolean r13 = false;
    L32:
        BulletinFactory r03 = getBulletinFactory();
        int r4 = R.raw.contact_check;
        String r5 = LocaleController.getString(R.string.BotAuthLoggedInSuccessTitle);
        if (r13 == false) goto L35;
        int r6 = R.string.BotAuthLoggedInSuccessWithoutPhoneNumber;
    L36:
        r03.createSimpleBulletin(r4, r5, AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(r6, new Object[]{r12}), Theme.getColor(Theme.key_undo_cancelColor))).show();
        goto L37
    L35:
        r6 = R.string.BotAuthLoggedInSuccess;
        goto L36
    L28:
        if (((TLRPC.TL_urlAuthResultRequest) r48).request_phone_number == false) goto L31;
        if (r50 == true) goto L31;
        r13 = true;
    L37:
        if (r51 == null) goto L40;
        r51.notifyEvent("oauth_result_confirmed", BotWebViewContainer.obj("result_url", null));
        return;
    L40:
        if (r43 == false) goto L91;
        BaseFragment r04 = LaunchActivity.getSafeLastFragment();
        if (r04 == null) goto L200;
        Context r05 = r04.getContext();
        if (r05 == null) goto L201;
        AndroidUtilities.runOnUIThread(new OAuthSheet$$ExternalSyntheticLambda0(r05), 800);
        return;
    L201:
        return;
    L200:
        return;
    L91:
        return;
    L19:
        if (TextUtils.isEmpty(r1.verified_app_name) == true) goto L21;
        r12 = r1.verified_app_name;
        goto L25
    L21:
        r12 = LocaleController.getString(R.string.UnverifiedApp);
        goto L25
    L23:
        r12 = null;
        goto L25
    L49:
        if (r51 == null) goto L52;
        r51.notifyEvent("oauth_result_confirmed", BotWebViewContainer.obj("result_url", r0.url));
        return;
    L52:
        BaseFragment r14 = LaunchActivity.getSafeLastFragment();
        if (r14 == null) goto L202;
        Browser.openUrlInSystemBrowser(r14.getContext(), r0.url);
        return;
    L202:
        return;
    L58:
        if ((r46 instanceof TLRPC.TL_urlAuthResultDefault) == false) goto L84;
        if (r51 == null) goto L62;
        return;
    L62:
        if (TextUtils.isEmpty(r45.url) == true) goto L73;
        BaseFragment r06 = LaunchActivity.getSafeLastFragment();
        if (r06 == null) goto L203;
        String r15 = r45.url;
        if (r48 != null) goto L69;
        boolean r132 = true;
    L70:
        AlertsCreator.showOpenUrlAlert(r06, r15, false, r132);
        return;
    L69:
        r132 = false;
        goto L70
    L203:
        return;
    L73:
        if (TextUtils.isEmpty(r47) == true) goto L196;
        BaseFragment r07 = LaunchActivity.getSafeLastFragment();
        if (r07 == null) goto L204;
        if (r48 != null) goto L80;
        boolean r133 = true;
    L81:
        AlertsCreator.showOpenUrlAlert(r07, r47, false, r133);
        return;
    L80:
        r133 = false;
        goto L81
    L204:
        return;
    L196:
        return;
    L84:
        if ((r46 instanceof TLRPC.TL_urlAuthResultRequest) == false) goto L205;
        TLRPC.TL_urlAuthResultRequest r152 = (TLRPC.TL_urlAuthResultRequest) r46;
        BaseFragment r08 = LaunchActivity.getSafeLastFragment();
        if (r08 == null) goto L206;
        Context r16 = r08.getContext();
        if (r16 == null) goto L197;
        Theme.ResourcesProvider r11 = r08.getResourceProvider();
        BottomSheet.Builder r2 = new BottomSheet.Builder(r16, false, r08.getResourceProvider());
        FrameLayout r42 = new FrameLayout(r16);
        r2.setCustomView(r42);
        ArrayList r7 = new ArrayList();
        boolean r8 = ConnectionsManager.getInstance(r44).isTestBackend();
        r7.clear();
        int r10 = 0;
    L94:
        if (r10 >= 16) goto L101;
        if (UserConfig.getInstance(r10).isClientActivated() == false) goto L100;
        if (ConnectionsManager.getInstance(r10).isTestBackend() != r8) goto L100;
        r7.add(Integer.valueOf(r10));
    L100:
        r10 = r10 + 1;
        goto L94
    L101:
        Collections.sort(r7, new OAuthSheet$$ExternalSyntheticLambda3());
        if (r45.peer == null) goto L104;
        boolean r22 = true;
    L105:
        boolean r82 = r152.is_app;
        FrameLayout r102 = new FrameLayout(r16);
        FrameLayout r122 = new FrameLayout(r16);
        r122.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(14.0f), r08.getThemedColor(Theme.key_dialogBackgroundGray)));
        BackupImageView r134 = new BackupImageView(r16);
        r134.setRoundRadius(AndroidUtilities.dp(14.0f));
        r134.getImageReceiver().setCrossfadeWithOldImage(true);
        AvatarDrawable r23 = new AvatarDrawable();
        int[] r142 = {r44};
        TLRPC.User r52 = UserConfig.getInstance(r142[0]).getCurrentUser();
        r23.setInfo(r52);
        r134.setForUserOrChat(r52, r23);
        r122.addView(r134, LayoutHelper.createFrame(28, 28, 115));
        ImageView r24 = new ImageView(r16);
        r24.setScaleType(ImageView.ScaleType.CENTER);
        int r53 = r08.getThemedColor(Theme.key_dialogTextGray3);
        PorterDuff.Mode r62 = PorterDuff.Mode.SRC_IN;
        r24.setColorFilter(new PorterDuffColorFilter(r53, r62));
        r24.setImageResource(R.drawable.arrows_select);
        r122.addView(r24, LayoutHelper.createFrame(18, 18.0f, 21, 0.0f, 0.0f, 4.0f, 0.0f));
        r102.addView(r122, LayoutHelper.createFrame(52, 28, 17));
        r102.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), 0);
        r42.addView(r102, LayoutHelper.createLinear(-2, -2, 0.0f, 51, 6, 4, 6, 0));
        ScaleStateListAnimator.apply(r102);
        if (r7.size() > 1) goto L108;
    L109:
        r102.setVisibility(8);
    L110:
        LinearLayout r25 = new LinearLayout(r16);
        r25.setOrientation(1);
        r42.addView(r25, LayoutHelper.createFrame(-1, -1, 119));
        BackupImageView r410 = new BackupImageView(r16);
        r410.setRoundRadius(AndroidUtilities.dp(40.0f));
        AvatarDrawable r135 = new AvatarDrawable();
        r135.setInfo(r152.bot);
        r410.setForUserOrChat(r152.bot, r135);
        r25.addView(r410, LayoutHelper.createLinear(80, 80, 49, 0, 21, 0, 16));
        if (r152.is_app == true) goto L113;
        String r411 = r152.domain;
    L117:
        int r72 = Theme.key_dialogTextBlack;
        TextView r136 = TextHelper.makeTextView(r16, 20.0f, r72, true);
        r136.setGravity(17);
        r136.setText(AndroidUtilities.replaceSingleLink(LocaleController.formatString(R.string.BotAuthTitle, new Object[]{r411}), r08.getThemedColor(Theme.key_featuredStickers_addButton)));
        r25.addView(r136, LayoutHelper.createLinear(-1, -2, 49, 32.0f, 0.0f, 32.0f, 9.66f));
        TextView r137 = TextHelper.makeTextView(r16, 14.0f, r72, false);
        r137.setGravity(17);
        if (r82 == false) goto L120;
        int r3 = R.string.BotAuthAppSubtitle;
    L123:
        r137.setText(AndroidUtilities.replaceTags(LocaleController.getString(r3)));
        r25.addView(r137, LayoutHelper.createLinear(-1, -2, 49, 32, 0, 32, 24));
        if (TextUtils.isEmpty(r152.platform) == true) goto L126;
    L133:
        LinearLayout r32 = new LinearLayout(r16);
        r32.setClipToPadding(false);
        r32.setClipChildren(false);
        r32.setOrientation(1);
        r32.setBackground(Theme.createRoundRectDrawableShadowed(AndroidUtilities.dp(16.0f), r08.getThemedColor(Theme.key_windowBackgroundWhite)));
        r25.addView(r32, LayoutHelper.createLinear(-1, -2, 55, 9, -3, 9, -3));
        String r28 = "—";
        if (TextUtils.isEmpty(r152.platform) == true) goto L136;
    L139:
        LinearLayout r54 = new LinearLayout(r16);
        r54.setOrientation(0);
        ImageView r138 = new ImageView(r16);
        String r30 = r411;
        r138.setImageResource(R.drawable.msg2_devices);
        boolean r31 = r82;
        r138.setColorFilter(new PorterDuffColorFilter(r08.getThemedColor(r72), r62));
        r54.addView(r138, LayoutHelper.createLinear(24, 24, 19, 17, 0, 20, 0));
        LinearLayout r412 = new LinearLayout(r16);
        r412.setOrientation(1);
        r54.addView(r412, LayoutHelper.createLinear(-1, -2, 55, 0.0f, 10.66f, 20.0f, 11.0f));
        TextView r9 = TextHelper.makeTextView(r16, 16.0f, r72, false);
        if (TextUtils.isEmpty(r152.platform) == false) goto L142;
        String r83 = "—";
    L143:
        r9.setText(r83);
        r412.addView(r9, LayoutHelper.createLinear(-1, -2, 55, 0.0f, 0.0f, 0.0f, 4.33f));
        TextView r84 = TextHelper.makeTextView(r16, 13.0f, Theme.key_windowBackgroundWhiteGrayText, false);
        if (TextUtils.isEmpty(r152.browser) == false) goto L146;
        String r92 = "—";
    L147:
        r84.setText(r92);
        FrameLayout r322 = r102;
        Theme.ResourcesProvider r33 = r11;
        r412.addView(r84, LayoutHelper.createLinear(-1, -2, 55));
        r32.addView(r54, LayoutHelper.createLinear(-1, -2));
    L149:
        if (TextUtils.isEmpty(r152.region) == true) goto L151;
    L152:
        LinearLayout r413 = new LinearLayout(r16);
        r413.setOrientation(0);
        ImageView r55 = new ImageView(r16);
        r55.setImageResource(R.drawable.msg2_language);
        r55.setColorFilter(new PorterDuffColorFilter(r08.getThemedColor(r72), r62));
        r413.addView(r55, LayoutHelper.createLinear(24, 24, 19, 17, 0, 20, 0));
        LinearLayout r56 = new LinearLayout(r16);
        r56.setOrientation(1);
        r413.addView(r56, LayoutHelper.createLinear(-1, -2, 55, 0.0f, 10.66f, 20.0f, 11.0f));
        TextView r63 = TextHelper.makeTextView(r16, 16.0f, r72, false);
        if (TextUtils.isEmpty(r152.region) == false) goto L155;
        String r73 = "—";
    L156:
        r63.setText(r73);
        r56.addView(r63, LayoutHelper.createLinear(-1, -2, 55, 0.0f, 0.0f, 0.0f, 4.33f));
        TextView r64 = TextHelper.makeTextView(r16, 13.0f, Theme.key_windowBackgroundWhiteGrayText, false);
        if (TextUtils.isEmpty(r152.ip) == true) goto L160;
        r28 = LocaleController.formatString(R.string.BotAuthBasedOnIP, new Object[]{r152.ip});
    L160:
        r64.setText(r28);
        r56.addView(r64, LayoutHelper.createLinear(-1, -2, 55));
        r32.addView(r413, LayoutHelper.createLinear(-1, -2));
    L161:
        TextView r34 = TextHelper.makeTextView(r16, 14.0f, Theme.key_windowBackgroundWhiteGrayText, false);
        r34.setText(LocaleController.getString(R.string.BotAuthInfo));
        r25.addView(r34, LayoutHelper.createLinear(-1, -2, 55, 22, 5, 22, 20));
    L163:
        if (r152.request_write_access == false) goto L165;
        FrameLayout r35 = new FrameLayout(r16);
        int r414 = AndroidUtilities.dp(16.0f);
        int r57 = Theme.key_windowBackgroundWhite;
        r35.setBackground(Theme.createRoundRectDrawableShadowed(r414, r08.getThemedColor(r57)));
        TextCheckCell r415 = new TextCheckCell(r16, r08.getResourceProvider());
        r415.setTextAndCheck(LocaleController.getString(R.string.BotAuthAllowMessages), true, false);
        r415.setBackground(Theme.createRadSelectorDrawable(r08.getThemedColor(r57), r08.getThemedColor(Theme.key_listSelector), 16, 16));
        r415.setOnClickListener(new OAuthSheet$$ExternalSyntheticLambda4(r415));
        r35.addView(r415, LayoutHelper.createFrame(-1, -1, 119));
        r25.addView(r35, LayoutHelper.createLinear(-1, -2, 7, 9, -3, 9, -3));
        TextView r36 = TextHelper.makeTextView(r16, 14.0f, Theme.key_windowBackgroundWhiteGrayText, false);
        r36.setText(LocaleController.formatString(R.string.BotAuthAllowMessagesInfo, new Object[]{UserObject.getUserName(r152.bot)}));
        r25.addView(r36, LayoutHelper.createLinear(-1, -2, 55, 22, 6, 22, 20));
        TextCheckCell r139 = r415;
    L166:
        LinearLayout r37 = new LinearLayout(r16);
        r37.setOrientation(0);
        ButtonWithCounterView r58 = new ButtonWithCounterView(r16, r08.getResourceProvider()).setRound();
        r58.setColor(r08.getThemedColor(Theme.key_text_RedRegular));
        r58.setText(LocaleController.getString(R.string.Decline));
        r37.addView(r58, LayoutHelper.createLinear(-1, 48, 1.0f, 119, 0, 0, 5, 0));
        ButtonWithCounterView r65 = new ButtonWithCounterView(r16, r08.getResourceProvider()).setRound();
        r65.setText(LocaleController.getString(R.string.BotAuthLogin));
        r37.addView(r65, LayoutHelper.createLinear(-1, 48, 1.0f, 119, 5, 0, 0, 0));
        r25.addView(r37, LayoutHelper.createLinear(-1, -2, 7, 12, 12, 12, 8));
        BottomSheet r38 = r2.create();
        r38.setBackgroundColor(r08.getThemedColor(Theme.key_windowBackgroundGray));
        String[] r74 = {r49};
        boolean r232 = r31;
        FrameLayout r123 = r322;
        Theme.ResourcesProvider r112 = r33;
        OAuthSheet$$ExternalSyntheticLambda5 r103 = new OAuthSheet$$ExternalSyntheticLambda5(r142, r45, r38, r43, r47, r48, r74, r50, r51, r30, r112);
        if (r152.user_id_hint != 0) goto L169;
    L175:
        r123.setOnClickListener(new OAuthSheet$$ExternalSyntheticLambda6(r38, r122, r7, r142, r103));
        boolean[] r26 = new boolean[1];
        r58.setOnClickListener(new OAuthSheet$$ExternalSyntheticLambda7(r45, r26, r38, r58, r51, r44));
        boolean[] r66 = new boolean[1];
        String r104 = r30;
        r65.setOnClickListener(new OAuthSheet$$ExternalSyntheticLambda10(r65, r58, r152, r142, r16, r08, r22, r232, r104, r66, new OAuthSheet$$ExternalSyntheticLambda9(r152, r74, r16, r44, new OAuthSheet$$ExternalSyntheticLambda8(r65, r58, r45, r74, r139, r66, r142, r26, r38, r104, r112, r43, r47, r152, r51), r08)));
        r38.setOnDismissListener(new OAuthSheet$$ExternalSyntheticLambda11(new BottomSheet[1]));
        BottomSheet r17 = showing;
        if (r17 == null) goto L179;
        r17.dismiss();
        showing = null;
    L179:
        if (r152.match_codes_first == false) goto L187;
        if (r152.match_codes.isEmpty() == true) goto L187;
        if (TextUtils.isEmpty(r74[0]) == false) goto L187;
        showing = showMatchCodeSheet(r16, r44, r152.match_codes, r104, new OAuthSheet$$ExternalSyntheticLambda1(r16, r74, r45, r44, r38, r104, r112), false, new OAuthSheet$$ExternalSyntheticLambda2(r26, r51, r45, r44), r08.getResourceProvider());
        return;
    L187:
        showing = r38;
        r38.show();
        return;
    L169:
        if (UserConfig.getInstance(r44).getClientUserId() == r152.user_id_hint) goto L175;
        int r09 = r7.size();
        int r18 = 0;
    L171:
        if (r18 >= r09) goto L175;
        Object r27 = r7.get(r18);
        r18 = r18 + 1;
        Integer r29 = (Integer) r27;
        if (UserConfig.getInstance(r29.intValue()).getClientUserId() != r152.user_id_hint) goto L171;
        r103.run(r29);
        goto L175
    L165:
        r139 = null;
        goto L166
    L155:
        r73 = r152.region;
        goto L156
    L151:
        if (TextUtils.isEmpty(r152.ip) == true) goto L161;
    L146:
        r92 = r152.browser;
        goto L147
    L142:
        r83 = r152.platform;
        goto L143
    L136:
        if (TextUtils.isEmpty(r152.browser) == false) goto L139;
        r30 = r411;
        r31 = r82;
        r322 = r102;
        r33 = r11;
        goto L149
    L126:
        if (TextUtils.isEmpty(r152.browser) == false) goto L133;
        if (TextUtils.isEmpty(r152.region) == false) goto L133;
        if (TextUtils.isEmpty(r152.ip) == false) goto L133;
        r30 = r411;
        r31 = r82;
        r322 = r102;
        r33 = r11;
        goto L163
    L120:
        if (r22 == false) goto L122;
        r3 = R.string.BotAuthBotSubtitle;
        goto L123
    L122:
        r3 = R.string.BotAuthSiteSubtitle;
        goto L123
    L113:
        if (TextUtils.isEmpty(r152.verified_app_name) == true) goto L115;
        r411 = r152.verified_app_name;
        goto L117
    L115:
        r411 = LocaleController.getString(R.string.UnverifiedApp);
        goto L117
    L108:
        if (r45.peer == null) goto L110;
    L104:
        r22 = false;
        goto L105
    L197:
        return;
    L206:
        return;
    }

    public static BottomSheet showMatchCodeSheet(Context r27, int r28, ArrayList<String> r29, String r30, Utilities.Callback<String> r31, boolean r32, Runnable r33, Theme.ResourcesProvider r34) {
        BottomSheet[] r4 = new BottomSheet[1];
        BottomSheet.Builder r5 = new BottomSheet.Builder(r27);
        LinearLayout r6 = new LinearLayout(r27);
        r6.setOrientation(1);
        r5.setCustomView(r6);
        TextView r7 = new TextView(r27);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setGravity(17);
        r7.setTextSize(1, 18.0f);
        r7.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r34));
        r6.addView(r7, LayoutHelper.createLinear(-1, -2, 1, 0, 25, 0, 19));
        LinearLayout r9 = new LinearLayout(r27);
        r9.setOrientation(0);
        r9.setGravity(17);
        r9.setPadding(0, AndroidUtilities.dp(19.0f), 0, AndroidUtilities.dp(19.0f));
        r6.addView(r9, LayoutHelper.createLinear(-1, -2, 1, 0, 0, 0, 0));
        if (ConnectionsManager.getInstance(r28).isTestBackend() == false) goto L13;
        int r11 = 0;
    L6:
        if (r11 >= 16) goto L13;
        if (UserConfig.getInstance(r11).isClientActivated() == false) goto L12;
        if (ConnectionsManager.getInstance(r11).isTestBackend() == true) goto L12;
    L14:
        BackupImageView[] r12 = new BackupImageView[r29.size()];
        boolean r14 = true;
        int r13 = 0;
        BottomSheet.Builder r52 = r5;
    L16:
        if (r13 >= r29.size()) goto L26;
        String r15 = r29.get(r13);
        FrameLayout r3 = new FrameLayout(r27);
        BottomSheet.Builder r19 = r52;
        r3.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(70.0f), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r34), 0.05f)));
        Drawable r53 = Emoji.getEmojiBigDrawable(r15);
        if (r53 != null) goto L20;
        Drawable r25 = new AnonymousClass1(new Text(r15, 30.0f, AndroidUtilities.bold()), r34);
        r14 = false;
    L21:
        BackupImageView r54 = new BackupImageView(r27);
        r12[r13] = r54;
        r54.getImageReceiver().setCurrentAccount(r11);
        r54.setImage(null, null, null, null, r25, null);
        NotificationCenter.listenEmojiLoading(r54);
        r3.addView(r54, LayoutHelper.createFrame(40, 40, 17));
        if (r13 != 0) goto L24;
        int r23 = 0;
    L25:
        r9.addView(r3, LayoutHelper.createLinear(70, 70, 16, r23, 0, 0, 0));
        ScaleStateListAnimator.apply(r3);
        r3.setOnClickListener(new OAuthSheet$$ExternalSyntheticLambda12(r4, r31, r15));
        r13 = r13 + 1;
        r52 = r19;
        goto L16
    L24:
        r23 = 24;
        goto L25
    L20:
        r25 = r53;
        goto L21
    L26:
        BottomSheet.Builder r192 = r52;
        TLRPC.TL_inputStickerSetShortName r35 = new TLRPC.TL_inputStickerSetShortName();
        r35.short_name = "RestrictedEmoji";
        MediaDataController.getInstance(r11).getStickerSet(r35, null, false, new OAuthSheet$$ExternalSyntheticLambda13(r29, r12));
        if (r14 == false) goto L29;
        int r1 = R.string.BotAuthSelectEmoji;
    L30:
        r7.setText(LocaleController.getString(r1));
        TextView r16 = new TextView(r27);
        r16.setGravity(17);
        r16.setTextSize(1, 12.0f);
        r16.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r34));
        r16.setText(AndroidUtilities.replaceSingleLink(LocaleController.formatString(R.string.BotAuthLoginRequestFrom, new Object[]{r30}), Theme.getColor(Theme.key_featuredStickers_addButton)));
        r6.addView(r16, LayoutHelper.createLinear(-1, -2, 1, 0, 23, 0, 11));
        ButtonWithCounterView r0 = new ButtonWithCounterView(r27, r34).setRound();
        if (r32 == false) goto L33;
        r0.setNeutral();
        r0.setText(LocaleController.getString(R.string.Cancel));
    L34:
        r6.addView(r0, LayoutHelper.createLinear(-1, 48, 7, 12, 12, 12, 12));
        r0.setOnClickListener(new OAuthSheet$$ExternalSyntheticLambda14(r0, r4, r33));
        BottomSheet r02 = r192.show();
        r4[0] = r02;
        return r02;
    L33:
        r0.setColor(Theme.getColor(Theme.key_text_RedRegular, r34));
        r0.setText(LocaleController.getString(R.string.Decline));
        goto L34
    L29:
        r1 = R.string.BotAuthSelectCode;
    L12:
        r11 = r11 + 1;
    L13:
        r11 = r28;
        goto L14
    }
}
