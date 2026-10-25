package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class SuggestBirthdayActionLayout {
    private TL_account.TL_birthday birthday;
    private final ButtonBounce bounce;
    private Text button;
    private final Paint buttonPaint;
    private final RectF buttonRect;
    private final int currentAccount;
    private boolean hasButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private final RLottieDrawable sticker;
    private Text text;
    private Text[] titles;
    private Text[] values;
    private final View view;

    public static /* synthetic */ void $r8$lambda$Avf38b9pChx6IA2bCG57Is3SvVg(SuggestBirthdayActionLayout r0, TLRPC.UserFull r1, TL_account.TL_birthday r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$open$1(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$UAOYWrbAnMwAMnHDt-wz1KAm0W4, reason: not valid java name */
    public static /* synthetic */ void m13145$r8$lambda$UAOYWrbAnMwAMnHDtwz1KAm0W4(SuggestBirthdayActionLayout r0, TLObject r1, TLRPC.UserFull r2, TL_account.TL_birthday r3, TLRPC.TL_error r4) {
        r0.lambda$open$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Ua0ChaOZDPn0LahZ_d2XEVSvGQk(SuggestBirthdayActionLayout r0, TL_account.TL_birthday r1) {
        r0.lambda$open$2(r1);
    }

    public SuggestBirthdayActionLayout(int r9, ChatActionCell r10, Theme.ResourcesProvider r11) {
        this.buttonRect = new RectF();
        this.buttonPaint = new Paint(1);
        this.currentAccount = r9;
        this.view = r10;
        this.resourcesProvider = r11;
        RLottieDrawable r2 = new RLottieDrawable(R.raw.cake, AndroidUtilities.dp(66.0f), AndroidUtilities.dp(66.0f), true, null);
        this.sticker = r2;
        r2.restart();
        this.bounce = new ButtonBounce(r10);
    }

    private final String getMonthName(int r13) {
        int[] r12 = {R.string.January, R.string.February, R.string.March, R.string.April, R.string.May, R.string.June, R.string.July, R.string.August, R.string.September, R.string.October, R.string.November, R.string.December};
        if (r13 < 0) goto L10;
        if (r13 >= 12) goto L10;
        return LocaleController.getString(r12[r13]);
    L10:
        return _UrlKt.FRAGMENT_ENCODE_SET + r13;
    }

    private /* synthetic */ void lambda$open$0(TLObject r2, TLRPC.UserFull r3, TL_account.TL_birthday r4, TLRPC.TL_error r5) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L6;
        return;
    L6:
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L9;
        BulletinFactory.of(r0).createSimpleBulletin(R.raw.gift, LocaleController.getString(R.string.PrivacyBirthdaySetDone), LocaleController.getString(R.string.PrivacyBirthdaySetDoneInfo)).setDuration(5000).show();
        return;
    L9:
        if (r3 == null) goto L15;
        int r22 = r3.flags2;
        if (r4 != null) goto L13;
        r3.flags2 = r22 & (-33);
    L14:
        r3.birthday = r4;
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r3, false);
        goto L15
    L13:
        r3.flags2 = r22 | 32;
    L15:
        if (r5 == null) goto L22;
        String r23 = r5.text;
        if (r23 == null) goto L22;
        if (r23.startsWith("FLOOD_WAIT_") == false) goto L22;
        new AlertDialog.Builder(this.view.getContext()).setTitle(LocaleController.getString(R.string.PrivacyBirthdayTooOftenTitle)).setMessage(LocaleController.getString(R.string.PrivacyBirthdayTooOftenMessage)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        return;
    L22:
        BulletinFactory.of(r0).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
    }

    private /* synthetic */ void lambda$open$1(TLRPC.UserFull r7, TL_account.TL_birthday r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new SuggestBirthdayActionLayout$$ExternalSyntheticLambda2(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$open$2(TL_account.TL_birthday r6) {
        TL_account.updateBirthday r0 = new TL_account.updateBirthday();
        r0.flags |= 1;
        r0.birthday = r6;
        TLRPC.UserFull r1 = MessagesController.getInstance(this.currentAccount).getUserFull(UserConfig.getInstance(this.currentAccount).getClientUserId());
        if (r1 == null) goto L5;
        TL_account.TL_birthday r2 = r1.birthday;
    L7:
        if (r1 == null) goto L9;
        r1.flags2 |= 32;
        r1.birthday = r6;
        MessagesStorage.getInstance(this.currentAccount).updateUserInfo(r1, false);
    L9:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SuggestBirthdayActionLayout$$ExternalSyntheticLambda1(this, r1, r2), 1024);
        MessagesController.getInstance(this.currentAccount).invalidateContentSettings();
        MessagesController.getInstance(this.currentAccount).removeSuggestion(0, "BIRTHDAY_SETUP");
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.newSuggestionsAvailable, new Object[0]);
        return;
    L5:
        r2 = null;
        goto L7
    }

    public void attach() {
        this.sticker.setMasterParent(this.view);
    }

    public void detach() {
        this.sticker.setMasterParent(null);
    }

    public void draw(Canvas r14) {
        int r6 = AndroidUtilities.dp(66.0f);
        int r0 = (this.view.getWidth() - r6) / 2;
        this.sticker.setBounds(r0, AndroidUtilities.dp(13.0f), r0 + r6, AndroidUtilities.dp(13.0f) + r6);
        this.sticker.draw(r14);
        this.text.draw(r14, (this.view.getWidth() - this.text.getWidth()) / 2.0f, AndroidUtilities.dp(19.0f) + r6, -1, 1.0f);
        int r62 = (int) (((AndroidUtilities.dp(19.0f) + r6) + this.text.getHeight()) + AndroidUtilities.dp(17.0f));
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.titles.length) goto L6;
        r2 = (int) (r2 + ((AndroidUtilities.dp(9.0f) + Math.max(this.titles[r1].getWidth(), this.values[r1].getWidth())) + AndroidUtilities.dp(9.0f)));
        r1 = r1 + 1;
        goto L4
    L6:
        int r12 = (this.view.getWidth() - r2) / 2;
        int r10 = 0;
    L8:
        if (r10 >= this.titles.length) goto L11;
        float r02 = (AndroidUtilities.dp(9.0f) + Math.max(this.titles[r10].getWidth(), this.values[r10].getWidth())) + AndroidUtilities.dp(9.0f);
        float r13 = r12;
        float r11 = r13 + (r02 / 2.0f);
        int r122 = (int) (r13 + r02);
        Text r03 = this.titles[r10];
        r03.draw(r14, r11 - (r03.getWidth() / 2.0f), r62, -1, 0.75f);
        Text r04 = this.values[r10];
        r04.draw(r14, r11 - (r04.getWidth() / 2.0f), AndroidUtilities.dp(16.0f) + r62, -1, 1.0f);
        r10 = r10 + 1;
        r12 = r122;
        goto L8
    L11:
        if (this.hasButton == false) goto L16;
        int r63 = r62 + AndroidUtilities.dp(38.0f);
        r14.save();
        float r05 = this.button.getWidth() + AndroidUtilities.dp(26.0f);
        float r22 = AndroidUtilities.dp(30.0f);
        float r5 = r63;
        this.buttonRect.set((this.view.getWidth() - r05) / 2.0f, r5, (this.view.getWidth() + r05) / 2.0f, r5 + r22);
        float r06 = this.bounce.getScale(0.1f);
        r14.scale(r06, r06, this.buttonRect.centerX(), this.buttonRect.centerY());
        float r23 = r22 / 2.0f;
        r14.drawRoundRect(this.buttonRect, r23, r23, this.buttonPaint);
        this.button.draw(r14, this.buttonRect.left + AndroidUtilities.dp(13.0f), this.buttonRect.centerY(), -1, 1.0f);
        r14.restore();
        return;
    }

    public int height() {
        int r0 = AndroidUtilities.dp(140.0f) + ((int) this.text.getHeight());
        if (this.hasButton == false) goto L5;
        int r2 = AndroidUtilities.dp(40.0f);
    L7:
        return r0 + r2;
    L5:
        r2 = 0;
        goto L7
    }

    public boolean onTouchEvent(MotionEvent r4) {
        boolean r0 = this.buttonRect.contains(r4.getX(), r4.getY());
        if (r4.getAction() != 0) goto L6;
        this.bounce.setPressed(r0);
    L18:
        return this.bounce.isPressed();
    L6:
        if (r4.getAction() == 2) goto L18;
        if (r4.getAction() != 1) goto L15;
        if (this.bounce.isPressed() == false) goto L13;
        open();
    L13:
        this.bounce.setPressed(false);
        goto L18
    L15:
        if (r4.getAction() != 3) goto L18;
        this.bounce.setPressed(false);
        goto L18
    }

    public void open() {
        AlertsCreator.createBirthdayPickerDialog(this.view.getContext(), LocaleController.getString(R.string.DateOfBirth), LocaleController.getString(R.string.DateOfBirthAddToProfile), this.birthday, new SuggestBirthdayActionLayout$$ExternalSyntheticLambda0(this), null, true, false, this.resourcesProvider).show();
    }

    public void set(MessageObject r11) {
        TLRPC.TL_messageActionSuggestBirthday r0 = (TLRPC.TL_messageActionSuggestBirthday) r11.messageOwner.action;
        this.birthday = r0.birthday;
        this.text = new Text(TextUtils.concat(new CharSequence[]{r11.messageText, ":"}), 13.0f).multiline(6).align(Layout.Alignment.ALIGN_CENTER).setMaxWidth(width() - AndroidUtilities.dp(32.0f));
        if ((r0.birthday.flags & 1) == 0) goto L5;
        int r1 = 3;
    L6:
        Text[] r4 = new Text[r1];
        this.titles = r4;
        this.values = new Text[r1];
        r4[0] = new Text(LocaleController.getString(R.string.DateDay), 11.0f);
        this.values[0] = new Text(_UrlKt.FRAGMENT_ENCODE_SET + r0.birthday.day, 11.0f, AndroidUtilities.bold());
        this.titles[1] = new Text(LocaleController.getString(R.string.DateMonth), 11.0f);
        this.values[1] = new Text(_UrlKt.FRAGMENT_ENCODE_SET + getMonthName(r0.birthday.month - 1), 11.0f, AndroidUtilities.bold());
        if ((r0.birthday.flags & 1) == 0) goto L9;
        this.titles[2] = new Text(LocaleController.getString(R.string.DateYear), 11.0f);
        this.values[2] = new Text(_UrlKt.FRAGMENT_ENCODE_SET + r0.birthday.year, 11.0f, AndroidUtilities.bold());
    L9:
        this.hasButton = !r11.isOutOwner();
        Theme.ResourcesProvider r112 = this.resourcesProvider;
        if (r112 == null) goto L12;
        boolean r113 = r112.isDark();
    L13:
        Paint r02 = this.buttonPaint;
        if (r113 == false) goto L17;
        int r114 = -1;
    L18:
        r02.setColor(Theme.multAlpha(r114, 0.12f));
        this.button = new Text(LocaleController.getString(R.string.SuggestedDateOfBirthView), 14.0f, AndroidUtilities.bold());
        return;
    L17:
        r114 = -16777216;
        goto L18
    L12:
        r113 = Theme.isCurrentThemeDark();
        goto L13
    L5:
        r1 = 2;
        goto L6
    }

    public int width() {
        return AndroidUtilities.dp(174.0f);
    }
}
