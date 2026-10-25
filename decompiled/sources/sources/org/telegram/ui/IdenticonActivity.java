package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmojiData;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.IdenticonDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.URLSpanReplacement;

/* loaded from: classes6.dex */
public class IdenticonActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private AnimatorSet animatorSet;
    private int chat_id;
    private TextView codeTextView;
    private FrameLayout container;
    private boolean emojiSelected;
    private String emojiText;
    private TextView emojiTextView;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout1;
    private TextView textView;

    /* renamed from: org.telegram.ui.IdenticonActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ IdenticonActivity this$0;

        public AnonymousClass1(IdenticonActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.IdenticonActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ IdenticonActivity this$0;

        public AnonymousClass2(IdenticonActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (IdenticonActivity.m15480$$Nest$fgetcodeTextView(this.this$0) == null) goto L6;
            int r12 = (IdenticonActivity.m15480$$Nest$fgetcodeTextView(this.this$0).getLeft() + (IdenticonActivity.m15480$$Nest$fgetcodeTextView(this.this$0).getMeasuredWidth() / 2)) - (IdenticonActivity.m15481$$Nest$fgetemojiTextView(this.this$0).getMeasuredWidth() / 2);
            int r22 = (((IdenticonActivity.m15480$$Nest$fgetcodeTextView(this.this$0).getMeasuredHeight() - IdenticonActivity.m15481$$Nest$fgetemojiTextView(this.this$0).getMeasuredHeight()) / 2) + IdenticonActivity.m15483$$Nest$fgetlinearLayout1(this.this$0).getTop()) - AndroidUtilities.dp(16.0f);
            IdenticonActivity.m15481$$Nest$fgetemojiTextView(this.this$0).layout(r12, r22, IdenticonActivity.m15481$$Nest$fgetemojiTextView(this.this$0).getMeasuredWidth() + r12, IdenticonActivity.m15481$$Nest$fgetemojiTextView(this.this$0).getMeasuredHeight() + r22);
            return;
        }
    }

    /* renamed from: org.telegram.ui.IdenticonActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ IdenticonActivity this$0;

        public AnonymousClass3(IdenticonActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(IdenticonActivity.m15479$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            IdenticonActivity.m15484$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.IdenticonActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ IdenticonActivity this$0;

        public AnonymousClass4(IdenticonActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            View r0 = this.this$0.fragmentView;
            if (r0 != null) goto L5;
            return true;
        L5:
            r0.getViewTreeObserver().removeOnPreDrawListener(this);
            int r02 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
            if (r02 == 3) goto L10;
            if (r02 == 1) goto L10;
            IdenticonActivity.m15482$$Nest$fgetlinearLayout(this.this$0).setOrientation(1);
        L11:
            View r03 = this.this$0.fragmentView;
            r03.setPadding(r03.getPaddingLeft(), 0, this.this$0.fragmentView.getPaddingRight(), this.this$0.fragmentView.getPaddingBottom());
            return true;
        L10:
            IdenticonActivity.m15482$$Nest$fgetlinearLayout(this.this$0).setOrientation(0);
            goto L11
        }
    }

    public static class LinkMovementMethodMy extends LinkMovementMethod {
        private LinkMovementMethodMy() {
        }

        public /* synthetic */ LinkMovementMethodMy(IdenticonActivityIA r1) {
            this();
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView r1, Spannable r2, MotionEvent r3) {
            return super.onTouchEvent(r1, r2, r3);
        L4:
            e = move-exception;
            FileLog.e(e);
            return false;
        }
    }

    /* renamed from: $r8$lambda$_9TeDfLKkBhGucrGhUv-CKvDx8M, reason: not valid java name */
    public static /* synthetic */ boolean m15478$r8$lambda$_9TeDfLKkBhGucrGhUvCKvDx8M(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m15479$$Nest$fgetanimatorSet(IdenticonActivity r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetcodeTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15480$$Nest$fgetcodeTextView(IdenticonActivity r0) {
        return r0.codeTextView;
    }

    /* renamed from: -$$Nest$fgetemojiTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15481$$Nest$fgetemojiTextView(IdenticonActivity r0) {
        return r0.emojiTextView;
    }

    /* renamed from: -$$Nest$fgetlinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m15482$$Nest$fgetlinearLayout(IdenticonActivity r0) {
        return r0.linearLayout;
    }

    /* renamed from: -$$Nest$fgetlinearLayout1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m15483$$Nest$fgetlinearLayout1(IdenticonActivity r0) {
        return r0.linearLayout1;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15484$$Nest$fputanimatorSet(IdenticonActivity r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    public IdenticonActivity(Bundle r1) {
        super(r1);
    }

    private void fixLayout() {
        this.fragmentView.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass4(this));
    }

    private void updateEmojiButton(boolean r14) {
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L5;
        r0.cancel();
        this.animatorSet = null;
    L5:
        float r02 = 0.0f;
        if (r14 == false) goto L32;
        AnimatorSet r142 = new AnimatorSet();
        this.animatorSet = r142;
        TextView r2 = this.emojiTextView;
        if (this.emojiSelected == false) goto L10;
        float r3 = 1.0f;
    L11:
        ObjectAnimator r22 = ObjectAnimator.ofFloat(r2, "alpha", new float[]{r3});
        TextView r5 = this.codeTextView;
        if (this.emojiSelected == false) goto L14;
        float r7 = 0.0f;
    L15:
        ObjectAnimator r32 = ObjectAnimator.ofFloat(r5, "alpha", new float[]{r7});
        TextView r52 = this.emojiTextView;
        if (this.emojiSelected == false) goto L18;
        float r72 = 1.0f;
    L19:
        ObjectAnimator r53 = ObjectAnimator.ofFloat(r52, "scaleX", new float[]{r72});
        TextView r8 = this.emojiTextView;
        if (this.emojiSelected == false) goto L22;
        float r9 = 1.0f;
    L23:
        ObjectAnimator r82 = ObjectAnimator.ofFloat(r8, "scaleY", new float[]{r9});
        TextView r10 = this.codeTextView;
        if (this.emojiSelected == false) goto L26;
        float r11 = 0.0f;
    L27:
        ObjectAnimator r73 = ObjectAnimator.ofFloat(r10, "scaleX", new float[]{r11});
        TextView r102 = this.codeTextView;
        if (this.emojiSelected == true) goto L31;
        r02 = 1.0f;
    L31:
        r142.playTogether(new Animator[]{r22, r32, r53, r82, r73, ObjectAnimator.ofFloat(r102, "scaleY", new float[]{r02})});
        this.animatorSet.addListener(new AnonymousClass3(this));
        this.animatorSet.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.setDuration(150);
        this.animatorSet.start();
    L57:
        TextView r143 = this.emojiTextView;
        if (this.emojiSelected == true) goto L60;
        int r13 = Theme.key_chat_emojiPanelIcon;
    L61:
        r143.setTag(Integer.valueOf(r13));
        return;
    L60:
        r13 = Theme.key_chat_emojiPanelIconSelected;
        goto L61
    L26:
        r11 = 1.0f;
        goto L27
    L22:
        r9 = 0.0f;
        goto L23
    L18:
        r72 = 0.0f;
        goto L19
    L14:
        r7 = 1.0f;
        goto L15
    L10:
        r3 = 0.0f;
        goto L11
    L32:
        TextView r144 = this.emojiTextView;
        if (this.emojiSelected == false) goto L35;
        float r23 = 1.0f;
    L36:
        r144.setAlpha(r23);
        TextView r145 = this.codeTextView;
        if (this.emojiSelected == false) goto L39;
        float r24 = 0.0f;
    L40:
        r145.setAlpha(r24);
        TextView r146 = this.emojiTextView;
        if (this.emojiSelected == false) goto L43;
        float r25 = 1.0f;
    L44:
        r146.setScaleX(r25);
        TextView r147 = this.emojiTextView;
        if (this.emojiSelected == false) goto L47;
        float r26 = 1.0f;
    L48:
        r147.setScaleY(r26);
        TextView r148 = this.codeTextView;
        if (this.emojiSelected == false) goto L51;
        float r27 = 0.0f;
    L52:
        r148.setScaleX(r27);
        TextView r149 = this.codeTextView;
        if (this.emojiSelected == true) goto L56;
        r02 = 1.0f;
    L56:
        r149.setScaleY(r02);
        goto L57
    L51:
        r27 = 1.0f;
        goto L52
    L47:
        r26 = 0.0f;
        goto L48
    L43:
        r25 = 0.0f;
        goto L44
    L39:
        r24 = 1.0f;
        goto L40
    L35:
        r23 = 0.0f;
        goto L36
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.EncryptionKey));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r12);
        this.fragmentView = r0;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.fragmentView.setOnTouchListener(new IdenticonActivity$$ExternalSyntheticLambda0());
        LinearLayout r2 = new LinearLayout(r12);
        this.linearLayout = r2;
        r2.setOrientation(1);
        this.linearLayout.setWeightSum(100.0f);
        r0.addView(this.linearLayout, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r02 = new FrameLayout(r12);
        r02.setPadding(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f));
        this.linearLayout.addView(r02, LayoutHelper.createLinear(-1, -1, 50.0f));
        ImageView r22 = new ImageView(r12);
        r22.setScaleType(ImageView.ScaleType.FIT_XY);
        r02.addView(r22, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass2 r03 = new AnonymousClass2(this, r12);
        this.container = r03;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.linearLayout.addView(this.container, LayoutHelper.createLinear(-1, -1, 50.0f));
        LinearLayout r04 = new LinearLayout(r12);
        this.linearLayout1 = r04;
        r04.setOrientation(1);
        this.linearLayout1.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        this.container.addView(this.linearLayout1, LayoutHelper.createFrame(-2, -2, 17));
        TextView r05 = new TextView(r12);
        this.codeTextView = r05;
        int r4 = Theme.key_windowBackgroundWhiteGrayText4;
        r05.setTextColor(Theme.getColor(r4));
        this.codeTextView.setGravity(17);
        this.codeTextView.setTypeface(Typeface.MONOSPACE);
        this.codeTextView.setTextSize(1, 16.0f);
        this.linearLayout1.addView(this.codeTextView, LayoutHelper.createLinear(-2, -2, 1));
        TextView r06 = new TextView(r12);
        this.textView = r06;
        r06.setTextColor(Theme.getColor(r4));
        this.textView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLinksClickable(true);
        this.textView.setClickable(true);
        this.textView.setGravity(17);
        this.textView.setMovementMethod(new LinkMovementMethodMy(null));
        this.linearLayout1.addView(this.textView, LayoutHelper.createFrame(-2, -2, 1));
        TextView r07 = new TextView(r12);
        this.emojiTextView = r07;
        r07.setTextColor(Theme.getColor(r4));
        this.emojiTextView.setGravity(17);
        this.emojiTextView.setTextSize(1, 32.0f);
        this.container.addView(this.emojiTextView, LayoutHelper.createFrame(-2, -2.0f));
        TLRPC.EncryptedChat r122 = MessagesController.getInstance(this.currentAccount).getEncryptedChat(Integer.valueOf(this.chat_id));
        if (r122 == null) goto L29;
        IdenticonDrawable r08 = new IdenticonDrawable();
        r22.setImageDrawable(r08);
        r08.setEncryptedChat(r122);
        TLRPC.User r09 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r122.user_id));
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        StringBuilder r23 = new StringBuilder();
        byte[] r42 = r122.key_hash;
        if (r42.length <= 16) goto L25;
        String r43 = Utilities.bytesToHex(r42);
        int r5 = 0;
    L8:
        if (r5 >= 32) goto L17;
        if (r5 == 0) goto L16;
        if ((r5 % 8) != 0) goto L14;
        r1.append('\n');
        goto L16
    L14:
        if ((r5 % 4) != 0) goto L16;
        r1.append(' ');
    L16:
        int r9 = r5 * 2;
        r1.append(r43.substring(r9, r9 + 2));
        r1.append(' ');
        r5 = r5 + 1;
        goto L8
    L17:
        r1.append("\n");
        int r44 = 0;
    L19:
        if (r44 >= 5) goto L24;
        byte[] r52 = r122.key_hash;
        int r8 = r44 * 4;
        int r53 = (r52[r8 + 19] & UByte.MAX_VALUE) | ((((r52[r8 + 16] & ByteCompanionObject.MAX_VALUE) << 24) | ((r52[r8 + 17] & UByte.MAX_VALUE) << 16)) | ((r52[r8 + 18] & UByte.MAX_VALUE) << 8));
        if (r44 == 0) goto L23;
        r23.append(" ");
    L23:
        String[] r82 = EmojiData.emojiSecret;
        r23.append(r82[r53 % r82.length]);
        r44 = r44 + 1;
        goto L19
    L24:
        this.emojiText = r23.toString();
    L25:
        this.codeTextView.setText(r1.toString());
        r1.clear();
        int r123 = R.string.EncryptionKeyDescription;
        String r010 = r09.first_name;
        r1.append(AndroidUtilities.replaceTags(LocaleController.formatString("EncryptionKeyDescription", r123, new Object[]{r010, r010})));
        int r124 = r1.toString().indexOf("telegram.org");
        if (r124 == (-1)) goto L28;
        r1.setSpan(new URLSpanReplacement(LocaleController.getString(R.string.EncryptionKeyLink)), r124, r124 + 12, 33);
    L28:
        this.textView.setText(r1);
    L29:
        updateEmojiButton(false);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L8;
        TextView r0 = this.emojiTextView;
        if (r0 == null) goto L9;
        r0.invalidate();
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.container, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        TextView r9 = this.textView;
        int r10 = ThemeDescription.FLAG_TEXTCOLOR;
        int r15 = Theme.key_windowBackgroundWhiteGrayText4;
        r1.add(new ThemeDescription(r9, r10, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.codeTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.textView, ThemeDescription.FLAG_LINKCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        fixLayout();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.chat_id = getArguments().getInt("chat_id");
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        fixLayout();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r2, boolean r3) {
        if (r2 == false) goto L8;
        if (r3 == true) goto L9;
        String r22 = this.emojiText;
        if (r22 == null) goto L10;
        TextView r1 = this.emojiTextView;
        r1.setText(Emoji.replaceEmoji(r22, r1.getPaint().getFontMetricsInt(), false));
        return;
    L10:
        return;
    L9:
        return;
    }
}
