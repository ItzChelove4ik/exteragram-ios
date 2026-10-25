package org.telegram.ui.Components.Premium.boosts;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BoostsActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public abstract class BoostDialogs {

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostDialogs$1, reason: invalid class name */
    public class AnonymousClass1 extends NumberPicker {
        public AnonymousClass1(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostDialogs$2, reason: invalid class name */
    public class AnonymousClass2 extends NumberPicker {
        public AnonymousClass2(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostDialogs$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayout {
        boolean ignoreLayout;
        final TextPaint paint;
        final /* synthetic */ AlertsCreator.ScheduleDatePickerColors val$datePickerColors;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass3(Context r1, AlertsCreator.ScheduleDatePickerColors r2, NumberPicker r3, NumberPicker r4, NumberPicker r5) {
            this.val$datePickerColors = r2;
            this.val$dayPicker = r3;
            this.val$hourPicker = r4;
            this.val$minutePicker = r5;
            super(r1);
            this.ignoreLayout = false;
            TextPaint r32 = new TextPaint(1);
            this.paint = r32;
            setWillNotDraw(false);
            r32.setTextSize(AndroidUtilities.dp(20.0f));
            r32.setTypeface(AndroidUtilities.bold());
            r32.setColor(r2.textColor);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r4) {
            super.onDraw(r4);
            r4.drawText(":", this.val$hourPicker.getRight() - AndroidUtilities.dp(12.0f), (getHeight() / 2.0f) - AndroidUtilities.dp(11.0f), this.paint);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$dayPicker.setItemCount(r02);
            this.val$hourPicker.setItemCount(r02);
            this.val$minutePicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$dayPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r12 = this.val$hourPicker.getLayoutParams();
            r12.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r13 = this.val$minutePicker.getLayoutParams();
            r13.height = AndroidUtilities.dp(42.0f) * r02;
            this.ignoreLayout = false;
            super.onMeasure(r5, r6);
            return;
        L5:
            r02 = 5;
            goto L6
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.BoostDialogs$4, reason: invalid class name */
    public class AnonymousClass4 extends TextView {
        public AnonymousClass4(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    public static /* synthetic */ void $r8$lambda$0sIZWlBOtXE0iTn0WiE10O0Ug48(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$2lm90PUidWXgUQmtUkwt3ZnOFB0(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$2wdGK5T_WlSND73cS7_d6VR4A4o(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ boolean $r8$lambda$4xLmZwpdQSywMRE5P__abi8Bd2g(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$741Yww92LdJcczmNRaaQhvdXYic(BottomSheet r0, AlertDialog r1, int r2) {
        r0.dismiss();
        UserSelectorBottomSheet.open();
    }

    public static /* synthetic */ void $r8$lambda$7DNp19UP3N1YlcuhgNvNug5jSww(BulletinFactory r4, boolean r5, TLRPC.Chat r6, Theme.ResourcesProvider r7) {
        int r0 = R.raw.star_premium_2;
        if (r5 == false) goto L5;
        String r1 = LocaleController.getString("BoostingGiveawayCreated", R.string.BoostingGiveawayCreated);
    L6:
        if (r5 == false) goto L13;
        if (ChatObject.isChannelAndNotMegaGroup(r6) == false) goto L10;
        int r52 = R.string.BoostingCheckStatistic;
    L11:
        String r53 = LocaleController.getString(r52);
    L17:
        r4.createSimpleBulletin(r0, r1, AndroidUtilities.replaceSingleTag(r53, Theme.key_undo_cancelColor, 0, new BoostDialogs$$ExternalSyntheticLambda3(r6), r7)).setDuration(5000).show();
        return;
    L10:
        r52 = R.string.BoostingCheckStatisticGroup;
        goto L11
    L13:
        if (ChatObject.isChannelAndNotMegaGroup(r6) == false) goto L15;
        int r54 = R.string.BoostingCheckGiftsStatistic;
    L16:
        r53 = LocaleController.getString(r54);
        goto L17
    L15:
        r54 = R.string.BoostingCheckGiftsStatisticGroup;
        goto L16
    L5:
        r1 = LocaleController.getString("BoostingAwardsCreated", R.string.BoostingAwardsCreated);
        goto L6
    }

    /* renamed from: $r8$lambda$8lzv6OdLVvVG-_rUKzt1onaIfmw, reason: not valid java name */
    public static /* synthetic */ void m12069$r8$lambda$8lzv6OdLVvVG_rUKzt1onaIfmw(AtomicBoolean r1) {
        r1.set(true);
    }

    public static /* synthetic */ void $r8$lambda$EDb9agqos9CqHv0V8p9nBs0cols(LinearLayout r3, NumberPicker r4, NumberPicker r5, int r6, int r7, NumberPicker r8, NumberPicker r9, int r10, int r11) {
        r3.performHapticFeedback(3, 2);     // Catch: Exception -> L35
    L5:
        if (r9.getTag() == null) goto L22;
        if (r9.getTag().equals("DAY") == false) goto L22;
        if (r9.getValue() != r9.getMinValue()) goto L18;
        Calendar r32 = Calendar.getInstance();
        r32.setTimeInMillis(System.currentTimeMillis());
        int r62 = r32.get(11);
        int r33 = (r32.get(12) / 5) + 1;
        if (r33 <= 11) goto L16;
        if (r62 != 23) goto L14;
        r9.setMinValue(r9.getMinValue() + 1);
        r4.setMinValue(0);
    L15:
        r5.setMinValue(0);
        goto L22
    L14:
        r4.setMinValue(r62 + 1);
        goto L15
    L16:
        r4.setMinValue(r62);
        r5.setMinValue(r33);
        goto L22
    L18:
        if (r9.getValue() != r9.getMaxValue()) goto L20;
        r4.setMaxValue(r6);
        r5.setMaxValue(Math.min(r7 / 5, 11));
        goto L22
    L20:
        r4.setMinValue(0);
        r5.setMinValue(0);
        r4.setMaxValue(23);
        r5.setMaxValue(11);
    L22:
        if (r9.getTag() != null) goto L24;
        return;
    L24:
        if (r9.getTag().equals("HOUR") == true) goto L26;
        return;
    L26:
        if (r8.getValue() == r8.getMinValue()) goto L28;
        return;
    L28:
        if (r9.getValue() != r9.getMinValue()) goto L33;
        Calendar r34 = Calendar.getInstance();
        r34.setTimeInMillis(System.currentTimeMillis());
        int r35 = (r34.get(12) / 5) + 1;
        if (r35 <= 11) goto L32;
        r5.setMinValue(0);
        return;
    L32:
        r5.setMinValue(r35);
        return;
    L33:
        r5.setMinValue(0);
        r5.setMaxValue(11);
    }

    public static /* synthetic */ void $r8$lambda$EbtP9d6Znf2Ul5_6kmSvZfkI9wI(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$G8aK9MHLRLY1MBREFiiBtR2rFMY(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$GGFFoBNRqPVcAybOz38EqP5kdjQ(Utilities.Callback r0, TLRPC.TL_premiumGiftCodeOption r1, AlertDialog r2, int r3) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$NWq1N7kPvi2Rz29fp29hJH62YP0(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$NpchwS3EGotSPguWejniRKo1gMw(TLRPC.TL_error r0) {
    }

    public static /* synthetic */ void $r8$lambda$VOfOU8OEDQW5Wbf8DkelLBPU_2E(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$VPq37rbydrsIqgb9Iss9McjMFtk(AtomicBoolean r0, Browser.Progress r1, boolean r2, String r3, long r4, TLRPC.TL_messageMediaGiveaway r6, Context r7, Theme.ResourcesProvider r8, TLRPC.payments_GiveawayInfo r9) {
        if (r0.get() == true) goto L14;
        r1.end();
        if ((r9 instanceof TLRPC.TL_payments_giveawayInfo) == false) goto L10;
        showAbout(r2, r3, r4, (TLRPC.TL_payments_giveawayInfo) r9, r6, r7, r8);
        return;
    L10:
        if ((r9 instanceof TLRPC.TL_payments_giveawayInfoResults) == false) goto L13;
        showAboutEnd(r2, r3, r4, (TLRPC.TL_payments_giveawayInfoResults) r9, r6, r7, r8);
        return;
    L13:
        return;
    }

    /* renamed from: $r8$lambda$Wlhhib8TJhPesjG7DAW42-R6DoM, reason: not valid java name */
    public static /* synthetic */ void m12070$r8$lambda$Wlhhib8TJhPesjG7DAW42R6DoM(CharSequence r3) {
        BulletinFactory r0 = BulletinFactory.global();
        if (r0 == null) goto L6;
        r0.createSimpleBulletinWithIconSize(R.raw.forward, r3, 30).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$X4dQhplwJym1ddLdysLPmpadu6Q(AlertDialog r0, int r1) {
    }

    /* renamed from: $r8$lambda$XQXY-3GnmQJl_ETJARL_GGHa3zQ, reason: not valid java name */
    public static /* synthetic */ void m12071$r8$lambda$XQXY3GnmQJl_ETJARL_GGHa3zQ(AtomicBoolean r0, Runnable r1, AlertDialog r2, int r3) {
        r0.set(true);
        r1.run();
    }

    public static /* synthetic */ String $r8$lambda$YSGskBisETYvgyCen4zeXNwfwe4(long r4, Calendar r6, int r7, int r8) {
        if (r8 == 0) goto L4;
        long r42 = r4 + (r8 * DurationKt.MILLIS_IN_DAY);
        r6.setTimeInMillis(r42);
        if (r6.get(1) != r7) goto L10;
        return LocaleController.getInstance().getFormatterScheduleDay().format(r42);
    L10:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r42);
    L4:
        return LocaleController.getString("MessageScheduleToday", R.string.MessageScheduleToday);
    }

    public static /* synthetic */ void $r8$lambda$c72RtgTgpwcfzG0p6mcFzlj_Q04(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$cZ5xH0mvxDzwAIYwsdC6X8hbsjA(AlertDialog r0, int r1) {
    }

    /* renamed from: $r8$lambda$dG4D-ISFJU7dCZmA7e5xQH-lhlI, reason: not valid java name */
    public static /* synthetic */ void m12072$r8$lambda$dG4DISFJU7dCZmA7e5xQHlhlI(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$dtW27XoeU9880emapboAQgyrSUg(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$e8nUNEQOAbk1f3QlMZPiBpsxQzo(TLRPC.TL_payments_giveawayInfoResults r0, AlertDialog r1, int r2) {
        BaseFragment r12 = LaunchActivity.getLastFragment();
        if (r12 != null) goto L5;
        return;
    L5:
        GiftInfoBottomSheet.show(r12, r0.gift_code_slug);
    }

    /* renamed from: $r8$lambda$g-pY5WECZOxGjtBvgYQyOdQKExk, reason: not valid java name */
    public static /* synthetic */ void m12073$r8$lambda$gpY5WECZOxGjtBvgYQyOdQKExk(MessageObject r10, TLRPC.payments_GiveawayInfo r11) {
        TLRPC.MessageMedia r0 = r10.messageOwner.media;
        if ((r0 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L8;
        TLRPC.TL_messageMediaGiveawayResults r02 = (TLRPC.TL_messageMediaGiveawayResults) r0;
        TLRPC.TL_messageMediaGiveaway r1 = new TLRPC.TL_messageMediaGiveaway();
        r1.prize_description = r02.prize_description;
        r1.months = r02.months;
        r1.quantity = r02.winners_count + r02.unclaimed_count;
        r1.only_new_subscribers = r02.only_new_subscribers;
        r1.until_date = r02.until_date;
        if ((r02.flags & 32) == 0) goto L7;
        r1.flags |= 32;
        r1.stars = r02.stars;
    L7:
        TLRPC.TL_messageMediaGiveaway r8 = r1;
        long r6 = r10.messageOwner.date * 1000;
        BaseFragment r9 = LaunchActivity.getLastFragment();
        if (r9 != null) goto L12;
        return;
    L12:
        String r5 = getGiveawayCreatorName(r10);
        boolean r4 = isChannel(r10);
        Bulletin.LottieLayout r102 = new Bulletin.LottieLayout(r9.getParentActivity(), r9.getResourceProvider());
        if ((r11 instanceof TLRPC.TL_payments_giveawayInfoResults) == false) goto L16;
        r102.setAnimation(R.raw.chats_infotip, 30, 30, new String[0]);
        r102.textView.setText(LocaleController.getString(R.string.BoostingGiveawayShortStatusEnded));
    L21:
        r102.textView.setSingleLine(false);
        r102.textView.setMaxLines(2);
        r102.setButton(new Bulletin.UndoButton(r9.getParentActivity(), true, r9.getResourceProvider()).setText(LocaleController.getString(R.string.LearnMore)).setUndoAction(new BoostDialogs$$ExternalSyntheticLambda4(r11, r4, r5, r6, r8, r9)));
        Bulletin.make(r9, r102, 2750).show();
        return;
    L16:
        if ((r11 instanceof TLRPC.TL_payments_giveawayInfo) == false) goto L21;
        if (((TLRPC.TL_payments_giveawayInfo) r11).participating == false) goto L20;
        r102.setAnimation(R.raw.forward, 30, 30, new String[0]);
        r102.textView.setText(LocaleController.getString(R.string.BoostingGiveawayShortStatusParticipating));
        goto L21
    L20:
        r102.setAnimation(R.raw.chats_infotip, 30, 30, new String[0]);
        r102.textView.setText(LocaleController.getString(R.string.BoostingGiveawayShortStatusNotParticipating));
        goto L21
    L8:
        r1 = (TLRPC.TL_messageMediaGiveaway) r0;
        goto L7
    }

    /* renamed from: $r8$lambda$go7JRZCOKY9jSvHOkD6-dtEM5VU, reason: not valid java name */
    public static /* synthetic */ void m12074$r8$lambda$go7JRZCOKY9jSvHOkD6dtEM5VU(TLRPC.Chat r5) {
        if (r5 == null) goto L5;
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        LaunchActivity.getLastFragment().showAsSheet(new BoostsActivity(-r5.id), r0);
        return;
    }

    public static /* synthetic */ String $r8$lambda$hAXZYdyPEbGpmJlpvQtFkdtN9Gc(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1 * 5)});
    }

    public static /* synthetic */ void $r8$lambda$hQzqBx4sRzdZK3tycFZNmRhRFFM(AtomicBoolean r0, Runnable r1, DialogInterface r2) {
        if (r0.get() == true) goto L6;
        r1.run();
        return;
    }

    /* renamed from: $r8$lambda$hj-zw7Ppl6QDtNIr-0JWcIOmJOc, reason: not valid java name */
    public static /* synthetic */ String m12075$r8$lambda$hjzw7Ppl6QDtNIr0JWcIOmJOc(int r0) {
        return String.valueOf(r0);
    }

    public static /* synthetic */ void $r8$lambda$jJNioCmwRSeWzJggHgxTeD18Axg(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$jfoikiIAXhVcYEZJhjU0Mp9LFow(TLRPC.payments_GiveawayInfo r18, boolean r19, String r20, long r21, TLRPC.TL_messageMediaGiveaway r23, BaseFragment r24) {
        if ((r18 instanceof TLRPC.TL_payments_giveawayInfo) == false) goto L7;
        showAbout(r19, r20, r21, (TLRPC.TL_payments_giveawayInfo) r18, r23, r24.getParentActivity(), r24.getResourceProvider());
        return;
    L7:
        if ((r18 instanceof TLRPC.TL_payments_giveawayInfoResults) == false) goto L10;
        showAboutEnd(r19, r20, r21, (TLRPC.TL_payments_giveawayInfoResults) r18, r23, r24.getParentActivity(), r24.getResourceProvider());
        return;
    }

    public static /* synthetic */ void $r8$lambda$uiKy_Sm31gbPIi8Kcd7_Wd7f12Y(AtomicBoolean r0, Browser.Progress r1, TLRPC.TL_error r2) {
        if (r0.get() == false) goto L5;
        return;
    L5:
        r1.end();
    }

    public static /* synthetic */ void $r8$lambda$yD9c29e5n89zb_oHpompqfR8Vvc(Calendar r6, NumberPicker r7, NumberPicker r8, NumberPicker r9, AlertsCreator.ScheduleDatePickerDelegate r10, BottomSheet.Builder r11, View r12) {
        r6.setTimeInMillis(System.currentTimeMillis() + (r7.getValue() * DurationKt.MILLIS_IN_DAY));
        r6.set(11, r8.getValue());
        r6.set(12, r9.getValue() * 5);
        r10.didSelectDate(true, (int) (r6.getTimeInMillis() / 1000), 0);
        r11.getDismissRunnable().run();
    }

    public static void applyDialogStyle(AlertDialog r2, boolean r3) {
        r2.setTextSize(20, 14);
        r2.setMessageLineSpacing(2.5f);
        if (r3 == true) goto L6;
        ViewGroup.MarginLayoutParams r22 = (ViewGroup.MarginLayoutParams) r2.getButtonsLayout().getLayoutParams();
        r22.topMargin = AndroidUtilities.dp(-14.0f);
        return;
    }

    public static boolean checkReduceQuantity(List<Integer> r6, Context r7, Theme.ResourcesProvider r8, List<TLRPC.TL_premiumGiftCodeOption> r9, TLRPC.TL_premiumGiftCodeOption r10, Utilities.Callback<TLRPC.TL_premiumGiftCodeOption> r11) {
        if (r10.store_product != null) goto L23;
        ArrayList r0 = new ArrayList();
        Iterator<TLRPC.TL_premiumGiftCodeOption> r92 = r9.iterator();
    L6:
        if (r92.hasNext() == false) goto L14;
        TLRPC.TL_premiumGiftCodeOption r2 = r92.next();
        if (r2.months != r10.months) goto L6;
        if (r2.store_product == null) goto L6;
        if (r6.contains(Integer.valueOf(r2.users)) == false) goto L6;
        r0.add(r2);
        goto L6
    L14:
        TLRPC.TL_premiumGiftCodeOption r62 = (TLRPC.TL_premiumGiftCodeOption) r0.get(0);
        int r93 = r0.size();
        int r22 = 0;
    L15:
        if (r22 >= r93) goto L21;
        Object r3 = r0.get(r22);
        r22 = r22 + 1;
        TLRPC.TL_premiumGiftCodeOption r32 = (TLRPC.TL_premiumGiftCodeOption) r3;
        int r4 = r10.users;
        int r5 = r32.users;
        if (r4 <= r5) goto L15;
        if (r5 <= r62.users) goto L15;
        r62 = r32;
        goto L15
    L21:
        String r94 = LocaleController.formatPluralString("GiftMonths", r62.months, new Object[0]);
        int r102 = r10.users;
        int r02 = r62.users;
        AlertDialog.Builder r1 = new AlertDialog.Builder(r7, r8);
        r1.setTitle(LocaleController.getString("BoostingReduceQuantity", R.string.BoostingReduceQuantity));
        r1.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingReduceQuantityTextPlural", r102, new Object[]{r94, Integer.valueOf(r02)})));
        r1.setPositiveButton(LocaleController.getString("Reduce", R.string.Reduce), new BoostDialogs$$ExternalSyntheticLambda27(r11, r62));
        r1.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), new BoostDialogs$$ExternalSyntheticLambda28());
        r1.show();
        return true;
    L23:
        return false;
    }

    public static boolean checkReduceUsers(Context r4, Theme.ResourcesProvider r5, List<TLRPC.TL_premiumGiftCodeOption> r6, TLRPC.TL_premiumGiftCodeOption r7) {
        if (r7.store_product != null) goto L14;
        ArrayList r0 = new ArrayList();
        Iterator<TLRPC.TL_premiumGiftCodeOption> r62 = r6.iterator();
    L6:
        if (r62.hasNext() == false) goto L12;
        TLRPC.TL_premiumGiftCodeOption r1 = r62.next();
        if (r1.months != r7.months) goto L6;
        if (r1.store_product == null) goto L6;
        r0.add(Integer.valueOf(r1.users));
        goto L6
    L12:
        String r63 = TextUtils.join(", ", r0);
        int r72 = r7.users;
        AlertDialog.Builder r02 = new AlertDialog.Builder(r4, r5);
        r02.setTitle(LocaleController.getString("BoostingReduceQuantity", R.string.BoostingReduceQuantity));
        r02.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingReduceUsersTextPlural", r72, new Object[]{r63})));
        r02.setPositiveButton(LocaleController.getString("OK", R.string.OK), new BoostDialogs$$ExternalSyntheticLambda23());
        r02.show();
        return true;
    L14:
        return false;
    }

    private static String getGiveawayCreatorName(MessageObject r3) {
        if (r3 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r1 = r3.getForwardedName();
        if (r1 != null) goto L12;
        long r12 = MessageObject.getPeerId(r3.messageOwner.peer_id);
        TLRPC.Chat r32 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r12));
        if (r32 != null) goto L10;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        return r32.title;
    L12:
        return r1;
    }

    public static long getThreeDaysAfterToday() {
        return roundByFiveMinutes(new Date().getTime() + 259200000);
    }

    private static boolean isChannel(MessageObject r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        long r1 = r3.getFromChatId();
        TLRPC.Chat r32 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r1));
        if (r32 != null) goto L8;
    L11:
        return false;
    L8:
        if (ChatObject.isChannelAndNotMegaGroup(r32) == false) goto L11;
        return true;
    }

    public static void openGiveAwayStatusDialog(MessageObject r10, Browser.Progress r11, Context r12, Theme.ResourcesProvider r13) {
        AtomicBoolean r1 = new AtomicBoolean(false);
        r11.init();
        r11.onCancel(new BoostDialogs$$ExternalSyntheticLambda29(r1));
        TLRPC.MessageMedia r0 = r10.messageOwner.media;
        if ((r0 instanceof TLRPC.TL_messageMediaGiveawayResults) == false) goto L6;
        TLRPC.TL_messageMediaGiveawayResults r02 = (TLRPC.TL_messageMediaGiveawayResults) r0;
        TLRPC.TL_messageMediaGiveaway r2 = new TLRPC.TL_messageMediaGiveaway();
        r2.prize_description = r02.prize_description;
        r2.months = r02.months;
        r2.quantity = r02.winners_count + r02.unclaimed_count;
        r2.only_new_subscribers = r02.only_new_subscribers;
        r2.until_date = r02.until_date;
        r2.stars = r02.stars;
        r2.flags = r02.flags;
    L5:
        TLRPC.TL_messageMediaGiveaway r7 = r2;
        BoostRepository.getGiveawayInfo(r10, new BoostDialogs$$ExternalSyntheticLambda30(r1, r11, isChannel(r10), getGiveawayCreatorName(r10), r10.messageOwner.date * 1000, r7, r12, r13), new BoostDialogs$$ExternalSyntheticLambda31(r1, r11));
        return;
    L6:
        r2 = (TLRPC.TL_messageMediaGiveaway) r0;
        goto L5
    }

    public static void processApplyGiftCodeError(TLRPC.TL_error r5, FrameLayout r6, Theme.ResourcesProvider r7, Runnable r8) {
        if (r5 == null) goto L16;
        String r0 = r5.text;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.contains("PREMIUM_SUB_ACTIVE_UNTIL_") == false) goto L11;
        String r52 = LocaleController.getInstance().getFormatterBoostExpired().format(new Date(Long.parseLong(r5.text.replace("PREMIUM_SUB_ACTIVE_UNTIL_", _UrlKt.FRAGMENT_ENCODE_SET)) * 1000));
        SpannableStringBuilder r82 = AndroidUtilities.replaceSingleTag(LocaleController.getString("GiftPremiumActivateErrorText", R.string.GiftPremiumActivateErrorText), Theme.key_undo_cancelColor, 0, r8);
        BulletinFactory.of(r6, r7).createSimpleBulletin(R.raw.chats_infotip, LocaleController.getString(R.string.GiftPremiumActivateErrorTitle), AndroidUtilities.replaceCharSequence("%1$s", r82, AndroidUtilities.replaceTags("**" + r52 + "**"))).show();
        r6.performHapticFeedback(3, 2);     // Catch: Exception -> L13
        return;
    L19:
        return;
    L11:
        showToastError(r6.getContext(), r5);
        return;
    }

    private static long roundByFiveMinutes(long r2) {
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(r2);
        r0.set(14, 0);
        r0.set(13, 0);
        int r3 = r0.get(12);
    L4:
        if ((r3 % 5) == 0) goto L6;
        r3 = r3 + 1;
        goto L4
    L6:
        r0.set(12, r3);
        return r0.getTimeInMillis();
    }

    public static void showAbout(boolean r16, String r17, long r18, TLRPC.TL_payments_giveawayInfo r20, TLRPC.TL_messageMediaGiveaway r21, Context r22, Theme.ResourcesProvider r23) {
        int r3 = r21.quantity;
        String r4 = LocaleController.formatPluralString("BoldMonths", r21.months, new Object[0]);
        String r6 = LocaleController.getInstance().getFormatterGiveawayMonthDay().format(new Date(r21.until_date * 1000));
        String r7 = LocaleController.getInstance().getFormatterDay().format(new Date(r20.start_date * 1000));
        String r8 = LocaleController.getInstance().getFormatterGiveawayMonthDayYear().format(new Date(r20.start_date * 1000));
        if (r21.channels.size() <= 1) goto L5;
        boolean r9 = true;
    L7:
        if ((r21.flags & 32) == 0) goto L9;
        boolean r13 = true;
    L10:
        AlertDialog.Builder r14 = new AlertDialog.Builder(r22, r23);
        r14.setTitle(LocaleController.getString("BoostingGiveAwayAbout", R.string.BoostingGiveAwayAbout));
        SpannableStringBuilder r10 = new SpannableStringBuilder();
        if (r13 == false) goto L17;
        if (r16 == false) goto L15;
        String r42 = "BoostingStarsGiveawayHowItWorksText";
    L14:
        int r11 = 1;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r42, (int) r21.stars, new Object[]{r17})));
    L22:
        r10.append("\n\n");
        String r12 = r21.prize_description;
        if (r12 == null) goto L28;
        if (r12.isEmpty() == true) goto L28;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksIncludeText", r3, new Object[]{r17, r21.prize_description})));
        r10.append("\n\n");
    L28:
        if (r21.only_new_subscribers == false) goto L32;
        if (r9 == false) goto L31;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextDateSeveral1", r3, new Object[]{r6, Integer.valueOf(r3), r17, LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextDateSeveral2", r21.channels.size() - r11, new Object[]{r7, r8})})));
    L35:
        r10.append("\n\n");
        if (r20.participating == false) goto L40;
        if (r9 == false) goto L39;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayParticipantMultiPlural", r21.channels.size() - r11, new Object[]{r17})));
    L61:
        r14.setMessage(r10);
        r14.setPositiveButton(LocaleController.getString("OK", R.string.OK), new BoostDialogs$$ExternalSyntheticLambda8());
        applyDialogStyle(r14.show(), false);
        return;
    L39:
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingGiveawayParticipant", R.string.BoostingGiveawayParticipant, new Object[]{r17})));
        goto L61
    L40:
        String r32 = r20.disallowed_country;
        if (r32 == null) goto L46;
        if (r32.isEmpty() == true) goto L46;
        r10.append(AndroidUtilities.replaceTags(LocaleController.getString("BoostingGiveawayNotEligibleCountry", R.string.BoostingGiveawayNotEligibleCountry)));
    L46:
        if (r20.admin_disallowed_chat_id == 0) goto L56;
        TLRPC.Chat r0 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r20.admin_disallowed_chat_id));
        if (r0 == null) goto L50;
        String r02 = r0.title;
    L51:
        if (r16 == false) goto L53;
        int r1 = R.string.BoostingGiveawayNotEligibleAdmin;
    L54:
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatString(r1, new Object[]{r02})));
        goto L61
    L53:
        r1 = R.string.BoostingGiveawayNotEligibleAdminGroup;
        goto L54
    L50:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L51
    L56:
        if (r20.joined_too_early_date == 0) goto L58;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingGiveawayNotEligible", R.string.BoostingGiveawayNotEligible, new Object[]{LocaleController.getInstance().getFormatterGiveawayMonthDayYear().format(new Date(r20.joined_too_early_date * 1000))})));
        goto L61
    L58:
        if (r9 == false) goto L60;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayTakePartMultiPlural", r21.channels.size() - r11, new Object[]{r17, r6})));
        goto L61
    L60:
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatString("BoostingGiveawayTakePart", R.string.BoostingGiveawayTakePart, new Object[]{r17, r6})));
        goto L61
    L31:
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextDate", r3, new Object[]{r6, Integer.valueOf(r3), r17, r7, r8})));
        goto L35
    L32:
        if (r9 == false) goto L34;
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextSeveral1", r3, new Object[]{r6, Integer.valueOf(r3), r17, LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextSeveral2", r21.channels.size() - r11, new Object[0])})));
        goto L35
    L34:
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubText", r3, new Object[]{r6, Integer.valueOf(r3), r17})));
        goto L35
    L15:
        r42 = "BoostingStarsGiveawayHowItWorksTextGroup";
        goto L14
    L17:
        r11 = 1;
        if (r16 == false) goto L20;
        String r122 = "BoostingGiveawayHowItWorksText";
    L21:
        r10.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString(r122, r3, new Object[]{r17, Integer.valueOf(r3), r4})));
        goto L22
    L20:
        r122 = "BoostingGiveawayHowItWorksTextGroup";
        goto L21
    L9:
        r13 = false;
        goto L10
    L5:
        r9 = false;
        goto L7
    }

    public static void showAboutEnd(boolean r16, String r17, long r18, TLRPC.TL_payments_giveawayInfoResults r20, TLRPC.TL_messageMediaGiveaway r21, Context r22, Theme.ResourcesProvider r23) {
        if (r21.until_date != 0) goto L5;
        r21.until_date = r20.finish_date;
    L5:
        int r5 = r21.quantity;
        String r6 = LocaleController.formatPluralString("BoldMonths", r21.months, new Object[0]);
        String r8 = LocaleController.getInstance().getFormatterGiveawayMonthDay().format(new Date(r21.until_date * 1000));
        String r9 = LocaleController.getInstance().getFormatterDay().format(new Date(r20.start_date * 1000));
        String r10 = LocaleController.getInstance().getFormatterGiveawayMonthDayYear().format(new Date(r20.start_date * 1000));
        if (r21.channels.size() <= 1) goto L8;
        boolean r11 = true;
    L10:
        if ((r21.flags & 32) == 0) goto L12;
        boolean r13 = true;
    L13:
        AlertDialog.Builder r14 = new AlertDialog.Builder(r22, r23);
        r14.setTitle(LocaleController.getString("BoostingGiveawayEnd", R.string.BoostingGiveawayEnd));
        SpannableStringBuilder r12 = new SpannableStringBuilder();
        if (r13 == false) goto L20;
        if (r16 == false) goto L18;
        String r62 = "BoostingStarsGiveawayHowItWorksTextEnd";
    L17:
        String r132 = r8;
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r62, (int) r21.stars, new Object[]{r17})));
    L25:
        r12.append("\n\n");
        String r7 = r21.prize_description;
        if (r7 == null) goto L31;
        if (r7.isEmpty() == true) goto L31;
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksIncludeText", r5, new Object[]{r17, r21.prize_description})));
        r12.append("\n\n");
    L31:
        if (r21.only_new_subscribers == false) goto L35;
        if (r11 == false) goto L34;
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextDateSeveralEnd1", r5, new Object[]{r132, Integer.valueOf(r5), r17, LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextDateSeveral2", r21.channels.size() - 1, new Object[]{r9, r10})})));
    L38:
        r12.append(" ");
        int r0 = r20.activated_count;
        if (r0 <= 0) goto L42;
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayUsedLinksPlural", r0, new Object[0])));
    L42:
        if (r20.refunded == false) goto L45;
        String r02 = LocaleController.getString("BoostingGiveawayCanceledByPayment", R.string.BoostingGiveawayCanceledByPayment);
        TextView r1 = new TextView(r22);
        r1.setTextSize(1, 14.0f);
        r1.setTypeface(AndroidUtilities.bold());
        r1.setGravity(17);
        r1.setText(r02);
        int r03 = Theme.key_text_RedRegular;
        r1.setTextColor(Theme.getColor(r03, r23));
        r1.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Theme.multAlpha(Theme.getColor(r03, r23), 0.1f)));
        r1.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        r14.addBottomView(r1);
        r14.setMessage(r12);
        r14.setPositiveButton(LocaleController.getString("Close", R.string.Close), new BoostDialogs$$ExternalSyntheticLambda11());
        applyDialogStyle(r14.show(), true);
        return;
    L45:
        r14.setMessage(r12);
        if (r20.winner == false) goto L52;
        String r04 = LocaleController.getString(R.string.BoostingGiveawayYouWon);
        if ((r20.flags & 16) != 0) goto L51;
        r14.setPositiveButton(LocaleController.getString("BoostingGiveawayViewPrize", R.string.BoostingGiveawayViewPrize), new BoostDialogs$$ExternalSyntheticLambda12(r20));
    L51:
        r14.setNegativeButton(LocaleController.getString("Close", R.string.Close), new BoostDialogs$$ExternalSyntheticLambda13());
    L53:
        EffectsTextView r15 = new EffectsTextView(r22);
        NotificationCenter.listenEmojiLoading(r15);
        r15.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r23));
        r15.setTextSize(1, 14.0f);
        r15.setGravity(17);
        r15.setText(r04);
        r15.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_profile_actionPressedBackground, r23)));
        r15.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(9.0f));
        r14.aboveMessageView(r15);
        applyDialogStyle(r14.show(), false);
        return;
    L52:
        r04 = LocaleController.getString("BoostingGiveawayYouNotWon", R.string.BoostingGiveawayYouNotWon);
        r14.setPositiveButton(LocaleController.getString("Close", R.string.Close), new BoostDialogs$$ExternalSyntheticLambda14());
        goto L53
    L34:
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextDateEnd", r5, new Object[]{r132, Integer.valueOf(r5), r17, r9, r10})));
        goto L38
    L35:
        if (r11 == false) goto L37;
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextSeveralEnd1", r5, new Object[]{r132, Integer.valueOf(r5), r17, LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextSeveral2", r21.channels.size() - 1, new Object[0])})));
        goto L38
    L37:
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGiveawayHowItWorksSubTextEnd", r5, new Object[]{r132, Integer.valueOf(r5), r17})));
        goto L38
    L18:
        r62 = "BoostingStarsGiveawayHowItWorksTextEndGroup";
        goto L17
    L20:
        r132 = r8;
        if (r16 == false) goto L23;
        String r72 = "BoostingGiveawayHowItWorksTextEnd";
    L24:
        r12.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString(r72, r5, new Object[]{r17, Integer.valueOf(r5), r6})));
        goto L25
    L23:
        r72 = "BoostingGiveawayHowItWorksTextEndGroup";
        goto L24
    L12:
        r13 = false;
        goto L13
    L8:
        r11 = false;
        goto L10
    }

    public static void showBulletin(BaseFragment r1, TLRPC.Chat r2, boolean r3) {
        if (r1 != null) goto L4;
        return;
    L4:
        showBulletin(BulletinFactory.of(r1), r1.getResourceProvider(), r2, r3);
    }

    private static void showBulletin(BulletinFactory r1, Theme.ResourcesProvider r2, TLRPC.Chat r3, boolean r4) {
        AndroidUtilities.runOnUIThread(new BoostDialogs$$ExternalSyntheticLambda0(r1, r4, r3, r2), 300);
    }

    public static void showBulletinAbout(MessageObject r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.messageOwner == null) goto L9;
        BoostRepository.getGiveawayInfo(r2, new BoostDialogs$$ExternalSyntheticLambda1(r2), new BoostDialogs$$ExternalSyntheticLambda2());
        return;
    }

    public static void showDatePicker(Context r29, long r30, AlertsCreator.ScheduleDatePickerDelegate r32, Theme.ResourcesProvider r33) {
        AlertsCreator.ScheduleDatePickerColors r2 = new AlertsCreator.ScheduleDatePickerColors(r33);
        BottomSheet.Builder r9 = new BottomSheet.Builder(r29, false, r33);
        r9.setApplyBottomPadding(false);
        NumberPicker r3 = new NumberPicker(r29, r33);
        r3.setTextColor(r2.textColor);
        r3.setTextOffset(AndroidUtilities.dp(10.0f));
        r3.setItemCount(5);
        AnonymousClass1 r13 = new AnonymousClass1(r29, r33);
        r13.setWrapSelectorWheel(true);
        r13.setAllItemsCount(24);
        r13.setItemCount(5);
        r13.setTextColor(r2.textColor);
        r13.setTextOffset(-AndroidUtilities.dp(10.0f));
        r13.setTag("HOUR");
        AnonymousClass2 r14 = new AnonymousClass2(r29, r33);
        r14.setWrapSelectorWheel(true);
        r14.setAllItemsCount(60);
        r14.setItemCount(5);
        r14.setTextColor(r2.textColor);
        r14.setTextOffset(-AndroidUtilities.dp(34.0f));
        AnonymousClass3 r12 = new AnonymousClass3(r29, r2, r3, r13, r14);
        r12.setOrientation(1);
        FrameLayout r0 = new FrameLayout(r29);
        r12.addView(r0, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r4 = new TextView(r29);
        r4.setText(LocaleController.getString("BoostingSelectDateTime", R.string.BoostingSelectDateTime));
        r4.setTextColor(r2.textColor);
        r4.setTextSize(1, 20.0f);
        r4.setTypeface(AndroidUtilities.bold());
        r0.addView(r4, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r4.setOnTouchListener(new BoostDialogs$$ExternalSyntheticLambda15());
        LinearLayout r02 = new LinearLayout(r29);
        r02.setOrientation(0);
        r02.setWeightSum(1.0f);
        r12.addView(r02, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        long r42 = System.currentTimeMillis();
        Calendar r15 = Calendar.getInstance();
        r15.setTimeInMillis(r42);
        int r8 = r15.get(1);
        AnonymousClass4 r10 = new AnonymousClass4(r29);
        long r11 = BoostRepository.giveawayPeriodMax() * 1000;
        Calendar r1 = Calendar.getInstance();
        r1.setTimeInMillis(r11);
        int r92 = r1.get(6);
        r1.setTimeInMillis(System.currentTimeMillis());
        r1.add(14, (int) r11);
        int r16 = r1.get(11);
        int r162 = r15.get(12);
        r02.addView(r3, LayoutHelper.createLinear(0, 270, 0.5f));
        r3.setMinValue(0);
        r3.setMaxValue(r92 - 1);
        r3.setWrapSelectorWheel(false);
        r3.setTag("DAY");
        r3.setFormatter(new BoostDialogs$$ExternalSyntheticLambda16(r42, r15, r8));
        BoostDialogs$$ExternalSyntheticLambda17 r112 = new BoostDialogs$$ExternalSyntheticLambda17(r12, r13, r14, r16, r162, r3);
        r3.setOnValueChangedListener(r112);
        r13.setMinValue(0);
        r13.setMaxValue(23);
        r02.addView(r13, LayoutHelper.createLinear(0, 270, 0.2f));
        r13.setFormatter(new BoostDialogs$$ExternalSyntheticLambda18());
        r13.setOnValueChangedListener(r112);
        r14.setMinValue(0);
        r14.setMaxValue(11);
        r14.setValue(0);
        r14.setFormatter(new BoostDialogs$$ExternalSyntheticLambda19());
        r02.addView(r14, LayoutHelper.createLinear(0, 270, 0.3f));
        r14.setOnValueChangedListener(r112);
        if (r30 <= 0) goto L5;
        r15.setTimeInMillis(System.currentTimeMillis());
        r15.set(12, 0);
        r15.set(13, 0);
        r15.set(14, 0);
        r15.set(11, 0);
        int r03 = (int) ((r30 - r15.getTimeInMillis()) / DurationKt.MILLIS_IN_DAY);
        r15.setTimeInMillis(r30);
        r14.setValue(r15.get(12) / 5);
        r13.setValue(r15.get(11));
        r3.setValue(r03);
        r112.onValueChange(r3, r3.getValue(), r3.getValue());
        r112.onValueChange(r13, r13.getValue(), r13.getValue());
    L5:
        r10.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r10.setGravity(17);
        r10.setTextColor(r2.buttonTextColor);
        r10.setTextSize(1, 14.0f);
        r10.setTypeface(AndroidUtilities.bold());
        r10.setBackground(Theme.AdaptiveRipple.filledRect(r2.buttonBackgroundColor, new float[]{8.0f}));
        r10.setText(LocaleController.getString("BoostingConfirm", R.string.BoostingConfirm));
        r12.addView(r10, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r10.setOnClickListener(new BoostDialogs$$ExternalSyntheticLambda20(r15, r3, r13, r14, r32, r9));
        r9.setCustomView(r12);
        BottomSheet r04 = r9.show();
        r04.setBackgroundColor(r2.backgroundColor);
        r04.fixNavigationBar(r2.backgroundColor);
        if (ColorUtils.calculateLuminance(r2.backgroundColor) <= 0.699999988079071d) goto L8;
        boolean r102 = true;
    L9:
        AndroidUtilities.setLightStatusBar(r04, r102);
        return;
    L8:
        r102 = false;
        goto L9
    }

    public static void showFloodWait(int r8) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L6;
        return;
    L6:
        if (r8 >= 60) goto L9;
        String r82 = LocaleController.formatPluralString("Seconds", r8, new Object[0]);
    L15:
        AlertDialog.Builder r1 = new AlertDialog.Builder(r0.getContext(), r0.getResourceProvider());
        r1.setTitle(LocaleController.getString(R.string.CantBoostTooOften));
        r1.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("CantBoostTooOftenDescription", R.string.CantBoostTooOftenDescription, new Object[]{r82})));
        r1.setPositiveButton(LocaleController.getString(R.string.OK), new BoostDialogs$$ExternalSyntheticLambda5());
        r1.show();
        return;
    L9:
        if (r8 >= 3600) goto L11;
        r82 = LocaleController.formatPluralString("Minutes", r8 / 60, new Object[0]);
        goto L15
    L11:
        int r3 = (r8 / 60) / 60;
        if (r3 <= 2) goto L14;
        r82 = LocaleController.formatPluralString("Hours", r3, new Object[0]);
        goto L15
    L14:
        r82 = LocaleController.formatPluralString("Hours", r3, new Object[0]) + " " + LocaleController.formatPluralString("Minutes", r8 % 60, new Object[0]);
        goto L15
    }

    public static void showGiftLinkForwardedBulletin(long r2) {
        if (r2 != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L6;
        SpannableStringBuilder r22 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.BoostingGiftLinkForwardedToSavedMsg));
    L9:
        AndroidUtilities.runOnUIThread(new BoostDialogs$$ExternalSyntheticLambda32(r22), 450);
        return;
    L6:
        if (DialogObject.isChatDialog(r2) == false) goto L8;
        TLRPC.Chat r23 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r2));
        r22 = AndroidUtilities.replaceTags(LocaleController.formatString("BoostingGiftLinkForwardedTo", R.string.BoostingGiftLinkForwardedTo, new Object[]{r23.title}));
        goto L9
    L8:
        TLRPC.User r24 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r2));
        r22 = AndroidUtilities.replaceTags(LocaleController.formatString("BoostingGiftLinkForwardedTo", R.string.BoostingGiftLinkForwardedTo, new Object[]{UserObject.getFirstName(r24)}));
        goto L9
    }

    public static void showMoreBoostsNeeded(long r2, BottomSheet r4) {
        TLRPC.Chat r22 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r2));
        BaseFragment r3 = LaunchActivity.getLastFragment();
        if (r3 != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3.getContext(), r3.getResourceProvider());
        r0.setTitle(LocaleController.getString(R.string.BoostingMoreBoostsNeeded));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGetMoreBoostByGiftingCount", BoostRepository.boostsPerSentGift(), new Object[]{r22.title})));
        r0.setNegativeButton(LocaleController.getString("GiftPremium", R.string.GiftPremium), new BoostDialogs$$ExternalSyntheticLambda9(r4));
        r0.setPositiveButton(LocaleController.getString("Close", R.string.Close), new BoostDialogs$$ExternalSyntheticLambda10());
        r0.show();
    }

    public static void showPrivateChannelAlert(TLRPC.Chat r2, Context r3, Theme.ResourcesProvider r4, Runnable r5, Runnable r6) {
        AtomicBoolean r0 = new AtomicBoolean(false);
        AlertDialog.Builder r1 = new AlertDialog.Builder(r3, r4);
        boolean r22 = ChatObject.isChannelAndNotMegaGroup(r2);
        if (r22 == false) goto L5;
        int r32 = R.string.BoostingGiveawayPrivateChannel;
    L6:
        r1.setTitle(LocaleController.getString(r32));
        if (r22 == false) goto L9;
        int r23 = R.string.BoostingGiveawayPrivateChannelWarning;
    L10:
        r1.setMessage(LocaleController.getString(r23));
        r1.setPositiveButton(LocaleController.getString("Add", R.string.Add), new BoostDialogs$$ExternalSyntheticLambda24(r0, r6));
        r1.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), new BoostDialogs$$ExternalSyntheticLambda25());
        r1.setOnDismissListener(new BoostDialogs$$ExternalSyntheticLambda26(r0, r5));
        r1.show();
        return;
    L9:
        r23 = R.string.BoostingGiveawayPrivateGroupWarning;
        goto L10
    L5:
        r32 = R.string.BoostingGiveawayPrivateGroup;
        goto L6
    }

    public static void showStartGiveawayDialog(Runnable r3) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r1 = new AlertDialog.Builder(r0.getContext(), r0.getResourceProvider());
        r1.setTitle(LocaleController.getString(R.string.BoostingStartGiveawayConfirmTitle));
        r1.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.BoostingStartGiveawayConfirmText)));
        r1.setPositiveButton(LocaleController.getString(R.string.Start), new BoostDialogs$$ExternalSyntheticLambda21(r3));
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), new BoostDialogs$$ExternalSyntheticLambda22());
        r1.show();
    }

    public static void showToastError(Context r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L9;
        String r0 = r2.text;
        if (r0 != null) goto L6;
        return;
    L6:
        if (TextUtils.isEmpty(r0) == true) goto L11;
        Toast.makeText(r1, r2.text, 1).show();
        return;
    L11:
        return;
    }

    public static void showUnsavedChanges(int r1, Context r2, Theme.ResourcesProvider r3, Runnable r4, Runnable r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r2, r3);
        r0.setTitle(LocaleController.getString("UnsavedChanges", R.string.UnsavedChanges));
        if (r1 != 1) goto L5;
        String r12 = LocaleController.getString("BoostingApplyChangesUsers", R.string.BoostingApplyChangesUsers);
    L12:
        r0.setMessage(r12);
        r0.setPositiveButton(LocaleController.getString("ApplyTheme", R.string.ApplyTheme), new BoostDialogs$$ExternalSyntheticLambda6(r4));
        r0.setNegativeButton(LocaleController.getString("Discard", R.string.Discard), new BoostDialogs$$ExternalSyntheticLambda7(r5));
        r0.show();
        return;
    L5:
        if (r1 != 2) goto L7;
        r12 = LocaleController.getString("BoostingApplyChangesChannels", R.string.BoostingApplyChangesChannels);
        goto L12
    L7:
        if (r1 == 3) goto L9;
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    L9:
        r12 = LocaleController.getString("BoostingApplyChangesCountries", R.string.BoostingApplyChangesCountries);
        goto L12
    }
}
