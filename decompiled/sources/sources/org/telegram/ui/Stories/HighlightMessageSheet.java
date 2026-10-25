package org.telegram.ui.Stories;

import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesController$$ExternalSyntheticLambda456;
import org.telegram.messenger.MessagesController$$ExternalSyntheticLambda500;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.Stories.LiveCommentsView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes3.dex */
public abstract class HighlightMessageSheet {
    public static int TIER_COLOR1 = 3;
    public static int TIER_COLOR2 = 4;
    public static int TIER_COLOR_BACKGROUND = 5;
    public static int TIER_EMOJIS = 2;
    public static int TIER_LENGTH = 1;
    public static int TIER_PERIOD;

    /* renamed from: org.telegram.ui.Stories.HighlightMessageSheet$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends StarsReactionsSheet.StarsSlider {
        final /* synthetic */ Utilities.Callback[] val$setStars;

        public AnonymousClass1(Context r1, Theme.ResourcesProvider r2, Utilities.Callback[] r3) {
            this.val$setStars = r3;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Stars.StarsReactionsSheet.StarsSlider
        public void onValueChanged(int r2) {
            this.val$setStars[0].run(Integer.valueOf(r2));
        }
    }

    /* loaded from: classes7.dex */
    public static class TierValueView extends FrameLayout {
        private final TextView subtitleTextView;
        private final AnimatedTextView titleTextView;

        public TierValueView(Context r13, CharSequence r14, Theme.ResourcesProvider r15) {
            super(r13);
            int r0 = AndroidUtilities.dp(12.0f);
            int r1 = Theme.key_dialogTextBlack;
            setBackground(Theme.createRoundRectDrawable(r0, Theme.multAlpha(Theme.getColor(r1, r15), 0.06f)));
            LinearLayout r02 = new LinearLayout(r13);
            r02.setOrientation(1);
            addView(r02, LayoutHelper.createFrame(-1, -2.0f, 17, 6.0f, 0.0f, 6.0f, 0.0f));
            AnimatedTextView r4 = new AnimatedTextView(r13, false, true, true);
            this.titleTextView = r4;
            r4.setAnimationProperties(0.6f, 0, 450, CubicBezierInterpolator.EASE_OUT_QUINT);
            r4.setTextSize(AndroidUtilities.dp(17.0f));
            r4.setTextColor(Theme.getColor(r1, r15));
            r4.setScaleProperty(0.7f);
            r4.setGravity(17);
            r4.setTypeface(AndroidUtilities.bold());
            r4.setAllowCancel(true);
            r02.addView(r4, LayoutHelper.createLinear(-1, 20, 0.0f, 0.0f, 0.0f, 1.66f));
            TextView r42 = new TextView(r13);
            this.subtitleTextView = r42;
            r42.setTextSize(1, 11.0f);
            r42.setTextColor(Theme.getColor(r1, r15));
            r42.setGravity(17);
            r02.addView(r42, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
            r42.setText(r14);
        }

        public void set(CharSequence r2) {
            this.titleTextView.setText(r2, true);
        }
    }

    public static /* synthetic */ void $r8$lambda$AMz9hzLpiy1t41hnyTvTz8oks24(long[] r5, ButtonWithCounterView r6, ColoredImageSpan[] r7, LiveCommentsView.Message r8, LiveCommentsView.LiveCommentView r9, int r10, TierValueView r11, TierValueView r12, TierValueView r13, StarsReactionsSheet.StarsSlider r14, boolean[] r15, Integer r16) {
        long r0 = r16.intValue();
        r5[0] = r0;
        r6.setText(StarsIntroActivity.replaceStars(LocaleController.formatString(R.string.StarsAddHighlightedMessage, new Object[]{LocaleController.formatNumber(r0, ',')}), r7), true);
        r8.stars = r5[0];
        r9.set(r8);
        int r52 = getTierOption(r10, r16.intValue(), TIER_PERIOD);
        int r62 = getTierOption(r10, r16.intValue(), TIER_LENGTH);
        int r72 = getTierOption(r10, r16.intValue(), TIER_EMOJIS);
        if (r52 < 60) goto L5;
        String r53 = LocaleController.formatString(R.string.SlowmodeMinutes, new Object[]{Integer.valueOf(r52 / 60)});
    L6:
        r11.set(r53);
        r12.set(LocaleController.formatNumber(r62, ','));
        r13.set(LocaleController.formatNumber(r72, ','));
        r14.setColor(getTierOption(r10, r16.intValue(), TIER_COLOR1), getTierOption(r10, r16.intValue(), TIER_COLOR2), !r15[0]);
        r15[0] = false;
        return;
    L5:
        r53 = LocaleController.formatString(R.string.SlowmodeSeconds, new Object[]{Integer.valueOf(r52)});
        goto L6
    }

    /* renamed from: $r8$lambda$TYVdgVFeZEkw19-HNzSwaGb-MGQ, reason: not valid java name */
    public static /* synthetic */ void m19336$r8$lambda$TYVdgVFeZEkw19HNzSwaGbMGQ(Utilities.Callback r2, long[] r3, BottomSheet r4, View r5) {
        r2.run(Long.valueOf(r3[0]));
        r4.dismiss();
    }

    static {
    }

    public static int[] getDefaultTiers() {
        return new int[]{10000, 3600, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 20, -10787210, -8681059, -14341066, CastStatusCodes.AUTHENTICATION_FAILED, 1800, 280, 10, -2013375, -1482439, -7666429, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 900, 200, 7, -1214690, -1214690, -6606592, 250, MediaError.DetailedErrorCode.TEXT_UNKNOWN, 150, 4, -1926647, -1926647, -6668800, 100, 300, 110, 3, -12539616, -12539616, -15244800, 50, 120, 80, 2, -12147733, -12147733, -16756594, 10, 60, 60, 1, -6988581, -6988581, -11991141, 0, 30, 30, 0, -6988581, -6988581, -11991141};
    }

    public static int getMaxLength(int r3) {
        int[] r32 = MessagesController.getInstance(r3).starsGroupcallMessageLimits;
        if (r32 == null) goto L9;
        int r0 = r32.length;
        int r1 = TIER_LENGTH;
        if (r0 > (r1 + 1)) goto L8;
        return MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
    L8:
        return r32[r1 + 1];
    L9:
        return MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
    }

    public static int getTierOption(int r4, int r5, int r6) {
        int[] r42 = MessagesController.getInstance(r4).starsGroupcallMessageLimits;
        int r1 = 0;
    L4:
        if (r1 >= (r42.length / 7)) goto L10;
        int r2 = r1 * 7;
        if (r5 >= r42[r2]) goto L8;
        r1 = r1 + 1;
        goto L4
    L8:
        return r42[(r2 + 1) + r6];
    L10:
        return 0;
    }

    public static void open(Context r39, int r40, long r41, String r43, TLRPC.TL_textWithEntities r44, long r45, long r47, Utilities.Callback<Long> r49, Theme.ResourcesProvider r50) {
        int r6 = 0;
        BottomSheet.Builder r5 = new BottomSheet.Builder(r39, false, r50);
        r5.setApplyBottomPadding(false);
        LinearLayout r7 = new LinearLayout(r39);
        r7.setOrientation(1);
        r5.setCustomView(r7);
        int[] r9 = MessagesController.getInstance(r40).starsGroupcallMessageLimits;
        CharSequence r10 = MessageObject.formatTextWithEntities(r44, false, new TextPaint());
        if ((r10 instanceof Spannable) == false) goto L5;
        Spannable r11 = (Spannable) r10;
        int r12 = ((AnimatedEmojiSpan[]) r11.getSpans(0, r10.length(), AnimatedEmojiSpan.class)).length + ((Emoji.EmojiSpan[]) r11.getSpans(0, r10.length(), Emoji.EmojiSpan.class)).length;
    L7:
        if (r47 > 0) goto L9;
        long r13 = 100;
    L10:
        int r112 = (int) Math.max(r45, r13);
        int r132 = (r9.length / 7) - 1;
    L11:
        if (r132 < 0) goto L18;
        int r14 = r132 * 7;
        int r15 = r9[r14];
        int r142 = r14 + 1;
        int r17 = r6;
        int r62 = r9[r142 + TIER_LENGTH];
        if (r12 > r9[r142 + TIER_EMOJIS]) goto L17;
        if (r10.length() > r62) goto L17;
        r112 = Math.max(r112, r15);
    L19:
        long[] r63 = new long[1];
        r63[r17] = r112;
        ButtonWithCounterView r102 = new ButtonWithCounterView(r39, null);
        LiveCommentsView.Message r113 = new LiveCommentsView.Message();
        r113.dialogId = r41;
        r113.text = r44;
        r113.stars = r63[r17];
        LiveCommentsView.LiveCommentView r1 = new LiveCommentsView.LiveCommentView(r39, r40, true);
        LinearLayout r133 = new LinearLayout(r39);
        r133.setOrientation(r17);
        TierValueView r143 = new TierValueView(r39, LocaleController.getString(R.string.LiveStoryHighlightFeaturePin), r50);
        r133.addView(r143, LayoutHelper.createLinear(-1, -1, 1.0f, 112, 0, 0, 5, 0));
        TierValueView r152 = new TierValueView(r39, LocaleController.getString(R.string.LiveStoryHighlightFeatureLength), r50);
        r133.addView(r152, LayoutHelper.createLinear(-1, -1, 1.0f, 112, 5, 0, 5, 0));
        TierValueView r8 = new TierValueView(r39, LocaleController.getString(R.string.LiveStoryHighlightFeatureEmoji), r50);
        r133.addView(r8, LayoutHelper.createLinear(-1, -1, 1.0f, 112, 5, 0, 0, 0));
        AnonymousClass1 r64 = new AnonymousClass1(r39, r50, r5);
        Utilities.Callback[] r52 = {new HighlightMessageSheet$$ExternalSyntheticLambda0(r63, r102, new ColoredImageSpan[1], r113, r1, r40, r143, r152, r8, r64, new boolean[]{true})};
        r1.set(r113);
        int r65 = 9;
        int[] r103 = {1, 50, 100, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, CastStatusCodes.AUTHENTICATION_FAILED, 5000, 7500, 10000};
        int r114 = MessagesController.getInstance(r40).starsGroupcallMessageAmountMax;
        ArrayList r122 = new ArrayList();
        int r144 = 0;
    L20:
        if (r144 >= r65) goto L37;
        LinearLayout r18 = r7;
        if (r103[r144] < r45) goto L36;
        if (r144 > 0) goto L26;
    L30:
        int r66 = r103[r144];
        if (r66 > r114) goto L32;
        r122.add(Integer.valueOf(r66));
        if (r103[r144] != r114) goto L36;
    L39:
        if (r122.isEmpty() == false) goto L41;
    L42:
        r122.add(Integer.valueOf(r114));
    L43:
        int[] r2 = new int[r122.size()];
        int r3 = 0;
    L45:
        if (r3 >= r122.size()) goto L47;
        r2[r3] = ((Integer) r122.get(r3)).intValue();
        r3 = r3 + 1;
        goto L45
    L47:
        r64.setSteps(100, r2);
        r64.setValue((int) r63[0]);
        ViewGroup r32 = r18;
        r32.addView(r64, LayoutHelper.createLinear(-1, -2, 0.0f, -52.0f, 0.0f, -42.0f));
        r52[0].run(Integer.valueOf((int) r63[0]));
        r32.addView(r133, LayoutHelper.createLinear(-1, 56, 16.0f, 0.0f, 16.0f, 0.0f));
        int r22 = Theme.key_dialogTextBlack;
        TextView r67 = TextHelper.makeTextView(r39, 20.0f, r22, true, r50);
        r67.setGravity(17);
        r67.setText(LocaleController.getString(R.string.LiveStoryHighlightTitle));
        r32.addView(r67, LayoutHelper.createLinear(-1, -2, 42.0f, 18.0f, 42.0f, 9.0f));
        TextView r0 = TextHelper.makeTextView(r39, 14.0f, r22, false, r50);
        r0.setGravity(17);
        r0.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.LiveStoryHighlightText, new Object[]{r43})));
        r32.addView(r0, LayoutHelper.createLinear(-1, -2, 42.0f, 0.0f, 42.0f, 0.0f));
        r32.addView(r1, LayoutHelper.createLinear(-2, -2, 17, 42, 22, 42, 20));
        r32.addView(r102, LayoutHelper.createLinear(-1, 48, 16.0f, 0.0f, 16.0f, 12.0f));
        r102.setOnClickListener(new HighlightMessageSheet$$ExternalSyntheticLambda1(r49, r63, r5.show()));
        return;
    L41:
        if (((Integer) r122.get(r122.size() - 1)).intValue() >= r114) goto L43;
    L32:
        r122.add(Integer.valueOf(r114));
        goto L39
    L26:
        if (r122.isEmpty() == false) goto L30;
        if (r103[r144] <= r45) goto L30;
        r122.add(Integer.valueOf((int) r45));
    L36:
        r144 = r144 + 1;
        r7 = r18;
        r65 = 9;
        goto L20
    L37:
        r18 = r7;
    L17:
        r132 = r132 - 1;
        r6 = r17;
        goto L11
    L18:
        r17 = r6;
        goto L19
    L9:
        r13 = r47;
        goto L10
    L5:
        r12 = 0;
        goto L7
    }

    public static int[] parseTiers(TLRPC.TL_jsonArray r13) {
        int[] r0 = new int[r13.value.size() * 7];
        int r2 = 0;
    L4:
        if (r2 >= r13.value.size()) goto L65;
        TLRPC.JSONValue r3 = r13.value.get(r2);
        if ((r3 instanceof TLRPC.TL_jsonObject) == false) goto L64;
        ArrayList<TLRPC.TL_jsonObjectValue> r32 = ((TLRPC.TL_jsonObject) r3).value;
        int r4 = r32.size();
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L64;
        TLRPC.TL_jsonObjectValue r6 = r32.get(r5);
        r5 = r5 + 1;
        TLRPC.TL_jsonObjectValue r62 = r6;
        TLRPC.JSONValue r7 = r62.value;
        int r9 = 2;
        int r10 = -1;
        if ((r7 instanceof TLRPC.TL_jsonNumber) == true) goto L12;
        if ((r7 instanceof TLRPC.TL_jsonString) == false) goto L9;
        String r72 = ((TLRPC.TL_jsonString) r7).value;
        String r63 = r62.key;
        r63.getClass();
        switch(r63.hashCode()) {
            case -1354842834: goto L51;
            case -1354842833: goto L47;
            case -628825439: goto L44;
            default: goto L42;
        };
    L42:
        r9 = -1;
    L54:
        switch(r9) {
            case 0: goto L58;
            case 1: goto L57;
            case 2: goto L56;
            default: goto L59;
        };
    L56:
        r10 = 6;
        goto L59
    L57:
        r10 = 5;
        goto L59
    L58:
        r10 = 4;
    L59:
        if (r10 < 0) goto L9;
        r0[(r2 * 7) + r10] = (int) Long.parseLong("FF" + r72, 16);     // Catch: Exception -> L62
    L62:
        e = move-exception;
        FileLog.e(e);
        goto L9
    L44:
        if (r63.equals("color_bg") == true) goto L54;
    L47:
        if (r63.equals("color2") == false) goto L42;
        r9 = 1;
        goto L54
    L51:
        if (r63.equals("color1") == false) goto L42;
        r9 = 0;
        goto L54
    L12:
        int r73 = (int) ((TLRPC.TL_jsonNumber) r7).value;
        String r64 = r62.key;
        r64.getClass();
        switch(r64.hashCode()) {
            case -1544802595: goto L28;
            case -1186480213: goto L24;
            case 109757537: goto L20;
            case 1686749675: goto L16;
            default: goto L14;
        };
    L14:
        boolean r65 = -1;
    L31:
        switch(r65) {
            case 0: goto L36;
            case 1: goto L35;
            case 2: goto L34;
            case 3: goto L33;
            default: goto L32;
        };
    L32:
        r9 = -1;
        goto L36
    L33:
        r9 = 3;
        goto L36
    L34:
        r9 = 0;
        goto L36
    L35:
        r9 = 1;
    L36:
        if (r9 < 0) goto L9;
        r0[(r2 * 7) + r9] = r73;
        goto L9
    L16:
        if (r64.equals("emoji_max") == false) goto L14;
        r65 = 3;
        goto L31
    L20:
        if (r64.equals("stars") == false) goto L14;
        r65 = 2;
        goto L31
    L24:
        if (r64.equals("pin_period") == false) goto L14;
        r65 = true;
        goto L31
    L28:
        if (r64.equals("text_length_max") == false) goto L14;
        r65 = false;
    L64:
        r2 = r2 + 1;
        goto L4
    L65:
        return r0;
    }

    public static int[] parseTiersString(String r1) {
        if (r1 == null) goto L12;
        if (r1.length() == 0) goto L12;
        return Arrays.stream(r1.split(",")).mapToInt(new MessagesController$$ExternalSyntheticLambda500()).toArray();
    L8:
        e = move-exception;
        FileLog.e(e);
        return getDefaultTiers();
    L12:
        return getDefaultTiers();
    }

    public static boolean tiersEqual(int[] r5, int[] r6) {
        if (r5 != null) goto L7;
        if (r6 != null) goto L7;
        return true;
    L7:
        if (r5 == null) goto L21;
        if (r6 == null) goto L21;
        if (r5.length == r6.length) goto L13;
        return false;
    L13:
        int r2 = 0;
    L15:
        if (r2 >= r5.length) goto L20;
        if (r5[r2] != r6[r2]) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        return false;
    L20:
        return true;
    L21:
        return false;
    }

    public static String tiersToString(int[] r1) {
        return (String) Arrays.stream(r1).mapToObj(new MessagesController$$ExternalSyntheticLambda456()).collect(Collectors.joining(","));
    }
}
