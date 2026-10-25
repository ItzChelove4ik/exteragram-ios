package org.telegram.ui.Components.Premium.boosts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorBtnCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class ReassignBoostBottomSheet extends BottomSheetWithRecyclerListView {
    private final ButtonWithCounterView actionButton;
    private final List<TL_stories.TL_myBoost> allUsedBoosts;
    private final SelectorBtnCell buttonContainer;
    private final TLRPC.Chat currentChat;
    private final TL_stories.TL_premium_myBoosts myBoosts;
    private final List<TL_stories.TL_myBoost> selectedBoosts;
    private CountDownTimer timer;
    private TopCell topCell;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.ReassignBoostBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends CountDownTimer {
        final /* synthetic */ ReassignBoostBottomSheet this$0;

        public AnonymousClass1(ReassignBoostBottomSheet r1, long r2, long r4) {
            this.this$0 = r1;
            super(r2, r4);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
        }

        @Override // android.os.CountDownTimer
        public void onTick(long r7) {
            ArrayList r72 = new ArrayList(ReassignBoostBottomSheet.m12114$$Nest$fgetallUsedBoosts(this.this$0).size());
            Iterator r8 = ReassignBoostBottomSheet.m12114$$Nest$fgetallUsedBoosts(this.this$0).iterator();
        L3:
            int r1 = 0;
            if (r8.hasNext() == false) goto L12;
            TL_stories.TL_myBoost r0 = (TL_stories.TL_myBoost) r8.next();
            if (r0.cooldown_until_date <= 0) goto L9;
            r72.add(r0);
        L9:
            if ((r0.cooldown_until_date * 1000) >= System.currentTimeMillis()) goto L3;
            r0.cooldown_until_date = 0;
            goto L3
        L12:
            if (r72.isEmpty() == false) goto L15;
            return;
        L15:
            if (r1 >= ReassignBoostBottomSheet.access$000(this.this$0).getChildCount()) goto L32;
            View r82 = ReassignBoostBottomSheet.access$100(this.this$0).getChildAt(r1);
            if ((r82 instanceof SelectorUserCell) == false) goto L21;
            SelectorUserCell r83 = (SelectorUserCell) r82;
            if (r72.contains(r83.getBoost()) == false) goto L21;
            r83.updateTimer();
        L21:
            r1 = r1 + 1;
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.ReassignBoostBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 implements Bulletin.Delegate {
        final /* synthetic */ ReassignBoostBottomSheet this$0;

        public AnonymousClass2(ReassignBoostBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getTopOffset(int r1) {
            return AndroidUtilities.statusBarHeight;
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.boosts.ReassignBoostBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ ReassignBoostBottomSheet this$0;

        public AnonymousClass3(ReassignBoostBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ReassignBoostBottomSheet.m12114$$Nest$fgetallUsedBoosts(this.this$0).size() + 3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L4;
            return 0;
        L4:
            if (r1 != 1) goto L6;
            return 1;
        L6:
            if (r1 == 2) goto L12;
            return 3;
        L12:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 3) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            if (r4.getItemViewType() != 3) goto L7;
            TL_stories.TL_myBoost r52 = (TL_stories.TL_myBoost) ReassignBoostBottomSheet.m12114$$Nest$fgetallUsedBoosts(this.this$0).get(r5 - 3);
            SelectorUserCell r42 = (SelectorUserCell) r4.itemView;
            r42.setBoost(r52);
            r42.setChecked(ReassignBoostBottomSheet.m12116$$Nest$fgetselectedBoosts(this.this$0).contains(r52), false);
            return;
        L7:
            if (r4.getItemViewType() != 2) goto L11;
            HeaderCell r3 = (HeaderCell) r4.itemView;
            r3.setTextSize(15.0f);
            r3.setPadding(0, 0, 0, AndroidUtilities.dp(2.0f));
            r3.setText(LocaleController.getString(R.string.BoostingRemoveBoostFrom));
            return;
        L11:
            if (r4.getItemViewType() != 0) goto L14;
            ReassignBoostBottomSheet.m12118$$Nest$fputtopCell(this.this$0, (TopCell) r4.itemView);
            ReassignBoostBottomSheet.m12117$$Nest$fgettopCell(this.this$0).setData(ReassignBoostBottomSheet.m12115$$Nest$fgetcurrentChat(this.this$0), this.this$0);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            Context r32 = r3.getContext();
            if (r4 != 0) goto L5;
            TopCell r42 = new TopCell(r32);
            r42.showBoosts(ReassignBoostBottomSheet.m12116$$Nest$fgetselectedBoosts(this.this$0), ReassignBoostBottomSheet.m12115$$Nest$fgetcurrentChat(this.this$0));
            View r43 = r42;
        L12:
            View r2 = r43;
        L16:
            r2.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r2);
        L5:
            if (r4 != 1) goto L7;
            r2 = new ShadowSectionCell(r32, 12, Theme.getColor(Theme.key_windowBackgroundGray));
            goto L16
        L7:
            if (r4 != 2) goto L9;
            r2 = new HeaderCell(r32, 22);
            goto L16
        L9:
            if (r4 == 3) goto L11;
            r2 = new View(r32);
            goto L16
        L11:
            r43 = new SelectorUserCell(r32, true, ReassignBoostBottomSheet.access$200(this.this$0), true);
            goto L12
        }
    }

    public static class ArrowView extends FrameLayout {
        public ArrowView(Context r2) {
            super(r2);
            ImageView r22 = new ImageView(getContext());
            r22.setImageResource(R.drawable.msg_arrow_avatar);
            r22.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7));
            addView(r22);
        }
    }

    public static class AvatarHolderView extends FrameLayout {
        private final Paint bgPaint;
        private final BoostIconView boostIconView;
        public TLRPC.Chat chat;
        AvatarDrawable fromAvatarDrawable;
        private final BackupImageView imageView;

        /* renamed from: -$$Nest$fgetboostIconView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BoostIconView m12119$$Nest$fgetboostIconView(AvatarHolderView r0) {
            return r0.boostIconView;
        }

        public AvatarHolderView(Context r11) {
            super(r11);
            Paint r0 = new Paint(1);
            this.bgPaint = r0;
            this.fromAvatarDrawable = new AvatarDrawable();
            BackupImageView r1 = new BackupImageView(getContext());
            this.imageView = r1;
            r1.setRoundRadius(AndroidUtilities.dp(30.0f));
            BoostIconView r2 = new BoostIconView(r11);
            this.boostIconView = r2;
            r2.setAlpha(0.0f);
            addView(r1, LayoutHelper.createFrame(-1, -1.0f, 0, 5.0f, 5.0f, 5.0f, 5.0f));
            addView(r2, LayoutHelper.createFrame(28, 28.0f, 85, 0.0f, 0.0f, 0.0f, 3.0f));
            r0.setColor(Theme.getColor(Theme.key_dialogBackground));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            r5.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, (getMeasuredHeight() / 2.0f) - AndroidUtilities.dp(2.0f), this.bgPaint);
            super.dispatchDraw(r5);
        }

        public void setChat(TLRPC.Chat r2) {
            this.chat = r2;
            this.fromAvatarDrawable.setInfo(r2);
            this.imageView.setForUserOrChat(r2, this.fromAvatarDrawable);
        }
    }

    public static class BoostIconView extends View {
        Drawable boostDrawable;
        Paint paint;

        public BoostIconView(Context r2) {
            super(r2);
            this.paint = new Paint(1);
            this.boostDrawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_boost_remove);
            this.paint.setColor(Theme.getColor(Theme.key_dialogBackground));
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            float r0 = getMeasuredWidth() / 2.0f;
            float r2 = getMeasuredHeight() / 2.0f;
            r13.drawCircle(r0, r2, getMeasuredWidth() / 2.0f, this.paint);
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), -AndroidUtilities.dp(10.0f), 0.0f);
            r13.drawCircle(r0, r2, (getMeasuredWidth() / 2.0f) - AndroidUtilities.dp(2.0f), PremiumGradient.getInstance().getMainGradientPaint());
            float r3 = AndroidUtilities.dp(18.0f) / 2.0f;
            this.boostDrawable.setBounds((int) (r0 - r3), (int) (r2 - r3), (int) (r0 + r3), (int) (r2 + r3));
            this.boostDrawable.draw(r13);
        }
    }

    public static class TopCell extends LinearLayout {
        private final List<TLRPC.Chat> addedChats;
        private final ArrowView arrowView;
        private final FrameLayout avatarsContainer;
        private final FrameLayout avatarsWrapper;
        private final TextView description;
        private final AvatarHolderView toAvatar;

        /* renamed from: org.telegram.ui.Components.Premium.boosts.ReassignBoostBottomSheet$TopCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ TopCell this$0;
            final /* synthetic */ AvatarHolderView val$finalRemovedAvatar;

            public AnonymousClass1(TopCell r1, AvatarHolderView r2) {
                this.this$0 = r1;
                this.val$finalRemovedAvatar = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                this.val$finalRemovedAvatar.setLayerType(0, null);
                TopCell.m12120$$Nest$fgetavatarsWrapper(this.this$0).removeView(this.val$finalRemovedAvatar);
            }
        }

        public static /* synthetic */ void $r8$lambda$MrxgNgsrrDTDKmlwHRIipGiqZNs(TopCell r0, int r1) {
            r0.lambda$setData$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$gWjA69fY9828v8mIfgoMFjI1LEs(BottomSheet r2) {
            r2.dismiss();
            NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.didStartedMultiGiftsSelector, new Object[0]);
            AndroidUtilities.runOnUIThread(new ReassignBoostBottomSheet$TopCell$$ExternalSyntheticLambda2(), 220);
        }

        /* renamed from: -$$Nest$fgetavatarsWrapper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m12120$$Nest$fgetavatarsWrapper(TopCell r0) {
            return r0.avatarsWrapper;
        }

        public TopCell(Context r13) {
            super(r13);
            this.addedChats = new ArrayList();
            setOrientation(1);
            setClipChildren(false);
            FrameLayout r2 = new FrameLayout(getContext());
            this.avatarsContainer = r2;
            r2.setClipChildren(false);
            FrameLayout r3 = new FrameLayout(getContext());
            this.avatarsWrapper = r3;
            r3.setClipChildren(false);
            r2.addView(r3, LayoutHelper.createFrame(-1, 70.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
            ArrowView r1 = new ArrowView(r13);
            this.arrowView = r1;
            r2.addView(r1, LayoutHelper.createFrame(24, 24, 17));
            AvatarHolderView r12 = new AvatarHolderView(r13);
            this.toAvatar = r12;
            r12.setLayerType(2, null);
            r2.addView(r12, LayoutHelper.createFrame(70, 70, 17));
            addView(r2, LayoutHelper.createLinear(-1, 70, 0.0f, 15.0f, 0.0f, 0.0f));
            TextView r14 = new TextView(r13);
            r14.setTypeface(AndroidUtilities.bold());
            r14.setText(LocaleController.getString(R.string.BoostingReassignBoost));
            r14.setTextSize(1, 20.0f);
            r14.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            addView(r14, LayoutHelper.createLinear(-2, -2, 1, 0, 15, 0, 7));
            LinkSpanDrawable.LinksTextView r132 = new LinkSpanDrawable.LinksTextView(getContext());
            this.description = r132;
            r132.setTextSize(1, 14.0f);
            r132.setGravity(1);
            r132.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            r132.setLineSpacing(r132.getLineSpacingExtra(), r132.getLineSpacingMultiplier() * 1.1f);
            addView(r132, LayoutHelper.createLinear(-2, -2, 1, 28, 0, 28, 18));
        }

        private /* synthetic */ void lambda$setData$1(int r2) {
        L6:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (this.description.getLayout().getLineForOffset(r2) != 0) goto L11;
            this.description.getEditableText().insert(r2, "\n");     // Catch: Exception -> L6
            return;
        }

        public void setData(TLRPC.Chat r5, BottomSheet r6) {
            int r2 = BoostRepository.boostsPerSentGift();     // Catch: Exception -> L8
            if (r5 != null) goto L5;
            String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            SpannableStringBuilder r53 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingReassignBoostTextPluralWithLink", r2, new Object[]{r52, "%3$s"}));     // Catch: Exception -> L8
            SpannableStringBuilder r62 = AndroidUtilities.replaceSingleTag(LocaleController.getString("BoostingReassignBoostTextLink", R.string.BoostingReassignBoostTextLink), Theme.key_chat_messageLinkIn, 2, new ReassignBoostBottomSheet$TopCell$$ExternalSyntheticLambda0(r6));     // Catch: Exception -> L8
            int r0 = TextUtils.indexOf(r53, "%3$s");     // Catch: Exception -> L8
            r53.replace(r0, r0 + 4, r62);     // Catch: Exception -> L8
            this.description.setText(r53, TextView.BufferType.EDITABLE);     // Catch: Exception -> L8
            this.description.post(new ReassignBoostBottomSheet$TopCell$$ExternalSyntheticLambda1(this, r0));     // Catch: Exception -> L8
            return;
        L5:
            r52 = r5.title;     // Catch: Exception -> L8
        L8:
            e = move-exception;
            FileLog.e(e);
        }

        public void showBoosts(List<TL_stories.TL_myBoost> r6, TLRPC.Chat r7) {
            ArrayList r0 = new ArrayList(r6.size());
            Iterator<TL_stories.TL_myBoost> r62 = r6.iterator();
        L4:
            if (r62.hasNext() == false) goto L6;
            TL_stories.TL_myBoost r1 = r62.next();
            r0.add(MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-DialogObject.getPeerDialogId(r1.peer))));
            goto L4
        L6:
            showChats(r0, r7);
        }

        public void showChats(List<TLRPC.Chat> r20, TLRPC.Chat r21) {
            ArrayList r1 = new ArrayList();
            ArrayList r2 = new ArrayList();
            CubicBezierInterpolator r3 = CubicBezierInterpolator.DEFAULT;
            Iterator<TLRPC.Chat> r4 = r20.iterator();
        L4:
            if (r4.hasNext() == false) goto L8;
            TLRPC.Chat r5 = r4.next();
            if (this.addedChats.contains(r5) == true) goto L4;
            r2.add(r5);
            goto L4
        L8:
            Iterator<TLRPC.Chat> r42 = this.addedChats.iterator();
        L10:
            if (r42.hasNext() == false) goto L14;
            TLRPC.Chat r52 = r42.next();
            if (r20.contains(r52) == true) goto L10;
            r1.add(r52);
            goto L10
        L14:
            ArrayList r43 = new ArrayList();
            int r53 = 0;
            int r6 = 0;
        L16:
            if (r6 >= this.avatarsWrapper.getChildCount()) goto L21;
            AvatarHolderView r7 = (AvatarHolderView) this.avatarsWrapper.getChildAt(r6);
            if (r7.getTag() != null) goto L20;
            r43.add(r7);
        L20:
            r6 = r6 + 1;
            goto L16
        L21:
            int r62 = r2.size();
            int r72 = 0;
        L22:
            char r10 = 2;
            float r11 = 0.0f;
            float r14 = 0.1f;
            char r15 = CharCompanionObject.MIN_VALUE;
            if (r72 >= r62) goto L27;
            Object r16 = r2.get(r72);
            r72 = r72 + 1;
            AvatarHolderView r12 = new AvatarHolderView(getContext());
            r12.setLayerType(2, null);
            r12.setChat((TLRPC.Chat) r16);
            int r8 = r43.size();
            this.avatarsWrapper.addView(r12, 0, LayoutHelper.createFrame(70, 70, 17));
            r12.setTranslationX((-r8) * AndroidUtilities.dp(23.0f));
            r12.setAlpha(0.0f);
            r12.setScaleX(0.1f);
            r12.setScaleY(0.1f);
            r12.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setInterpolator(r3).setDuration(200).start();
            if (r8 != 0) goto L22;
            AvatarHolderView.m12119$$Nest$fgetboostIconView(r12).setScaleY(1.0f);
            AvatarHolderView.m12119$$Nest$fgetboostIconView(r12).setScaleX(1.0f);
            AvatarHolderView.m12119$$Nest$fgetboostIconView(r12).setAlpha(1.0f);
            goto L22
        L27:
            int r63 = r1.size();
            int r73 = 0;
        L29:
            if (r73 >= r63) goto L52;
            Object r122 = r1.get(r73);
            r73 = r73 + 1;
            TLRPC.Chat r123 = (TLRPC.Chat) r122;
            int r13 = r43.size();
        L31:
            if (r53 >= r13) goto L36;
            Object r17 = r43.get(r53);
            r53 = r53 + 1;
            AvatarHolderView r82 = (AvatarHolderView) r17;
            char r172 = r10;
            if (r82.chat == r123) goto L37;
            r10 = r172;
        L37:
            if (r82 == null) goto L50;
            r82.setTag("REMOVED");
            r82.animate().alpha(r11).translationXBy(AndroidUtilities.dp(23.0f)).scaleX(r14).scaleY(r14).setInterpolator(r3).setDuration(200).setListener(new AnonymousClass1(this, r82)).start();
            int r54 = r43.size();
            int r102 = 0;
            int r124 = 0;
        L39:
            if (r124 >= r54) goto L45;
            Object r132 = r43.get(r124);
            r124 = r124 + 1;
            AvatarHolderView r133 = (AvatarHolderView) r132;
            int r18 = r43.size() - 1;
            if (r133 == r82) goto L39;
            r102 = r102 + 1;
            r133.animate().translationX((-(r18 - r102)) * AndroidUtilities.dp(23.0f)).setInterpolator(r3).setDuration(200).start();
            goto L39
        L45:
            if (r43.get(r43.size() - 1) == r82) goto L47;
        L49:
            float r83 = 0.1f;
            char r134 = CharCompanionObject.MIN_VALUE;
        L51:
            r14 = r83;
            r15 = r134;
            r10 = r172;
            r53 = 0;
            r11 = 0.0f;
            goto L29
        L47:
            if (r43.size() <= 1) goto L49;
            r83 = 0.1f;
            AvatarHolderView.m12119$$Nest$fgetboostIconView((AvatarHolderView) r43.get(r43.size() - 2)).setScaleY(0.1f);
            AvatarHolderView.m12119$$Nest$fgetboostIconView((AvatarHolderView) r43.get(r43.size() - 2)).setScaleX(0.1f);
            ViewPropertyAnimator r55 = AvatarHolderView.m12119$$Nest$fgetboostIconView((AvatarHolderView) r43.get(r43.size() - 2)).animate();
            r134 = CharCompanionObject.MIN_VALUE;
            r55.alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(200).setInterpolator(r3).start();
            goto L51
        L50:
            r83 = r14;
            r134 = r15;
            goto L51
        L36:
            r172 = r10;
            r82 = null;
            goto L37
        L52:
            AvatarHolderView r44 = this.toAvatar;
            if (r44.chat != null) goto L55;
            r44.setChat(r21);
        L55:
            this.addedChats.removeAll(r1);
            this.addedChats.addAll(r2);
            this.avatarsContainer.animate().cancel();
            if (this.addedChats.isEmpty() == true) goto L61;
            if (this.addedChats.size() == 1) goto L61;
            int r19 = this.addedChats.size() - 1;
            this.avatarsContainer.animate().setInterpolator(r3).translationX(AndroidUtilities.dp(11.5f) * r19).setDuration(200).start();
        L62:
            this.toAvatar.animate().cancel();
            this.avatarsWrapper.animate().cancel();
            boolean r110 = this.addedChats.isEmpty();
            FrameLayout r22 = this.avatarsWrapper;
            if (r110 == false) goto L66;
            r22.animate().setInterpolator(r3).translationX(0.0f).setDuration(200).start();
            this.toAvatar.animate().setInterpolator(r3).translationX(0.0f).setDuration(200).start();
            return;
        L66:
            r22.animate().setInterpolator(r3).translationX(-AndroidUtilities.dp(48.0f)).setDuration(200).start();
            this.toAvatar.animate().setInterpolator(r3).translationX(AndroidUtilities.dp(48.0f)).setDuration(200).start();
            return;
        L61:
            this.avatarsContainer.animate().setInterpolator(r3).translationX(0.0f).setDuration(200).start();
            goto L62
        }
    }

    /* renamed from: $r8$lambda$-752T2RVgUsB0m5Djt37kiBybVc, reason: not valid java name */
    public static /* synthetic */ void m12113$r8$lambda$752T2RVgUsB0m5Djt37kiBybVc(ReassignBoostBottomSheet r0, TLRPC.Chat r1, View r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NlCzwSNF7sukRUtOJ76Wqaixg5c(ReassignBoostBottomSheet r0, TLRPC.TL_error r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$Qr1BJi1w80YER5I7tKPJga8D9Yk(ReassignBoostBottomSheet r0, TL_stories.TL_premium_myBoosts r1, List r2, HashSet r3, TL_stories.TL_premium_boostsStatus r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RZERkx7zwUOsKMtpEp2Xc3A8DoE(ReassignBoostBottomSheet r0, TLRPC.Chat r1, List r2, HashSet r3, TL_stories.TL_premium_myBoosts r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$oprTr1MlV0LfBeVFdyRg2NWzEQY(ReassignBoostBottomSheet r0, TLRPC.Chat r1, View r2, int r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetallUsedBoosts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12114$$Nest$fgetallUsedBoosts(ReassignBoostBottomSheet r0) {
        return r0.allUsedBoosts;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m12115$$Nest$fgetcurrentChat(ReassignBoostBottomSheet r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetselectedBoosts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12116$$Nest$fgetselectedBoosts(ReassignBoostBottomSheet r0) {
        return r0.selectedBoosts;
    }

    /* renamed from: -$$Nest$fgettopCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TopCell m12117$$Nest$fgettopCell(ReassignBoostBottomSheet r0) {
        return r0.topCell;
    }

    /* renamed from: -$$Nest$fputtopCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12118$$Nest$fputtopCell(ReassignBoostBottomSheet r0, TopCell r1) {
        r0.topCell = r1;
    }

    public ReassignBoostBottomSheet(BaseFragment r9, TL_stories.TL_premium_myBoosts r10, TLRPC.Chat r11) {
        super(r9, false, false);
        this.selectedBoosts = new ArrayList();
        this.allUsedBoosts = new ArrayList();
        this.topPadding = 0.3f;
        this.myBoosts = r10;
        this.currentChat = r11;
        ArrayList<TL_stories.TL_myBoost> r92 = r10.my_boosts;
        int r102 = r92.size();
        int r1 = 0;
    L3:
        if (r1 >= r102) goto L9;
        TL_stories.TL_myBoost r2 = r92.get(r1);
        r1 = r1 + 1;
        TL_stories.TL_myBoost r22 = r2;
        TLRPC.Peer r3 = r22.peer;
        if (r3 == null) goto L3;
        if (DialogObject.getPeerDialogId(r3) == (-r11.id)) goto L3;
        this.allUsedBoosts.add(r22);
        goto L3
    L9:
        SelectorBtnCell r93 = new SelectorBtnCell(getContext(), this.resourcesProvider, this.recyclerListView);
        this.buttonContainer = r93;
        r93.setClickable(true);
        r93.setOrientation(1);
        r93.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r93.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        GradientButtonWithCounterView r12 = new GradientButtonWithCounterView(getContext(), true, this.resourcesProvider);
        this.actionButton = r12;
        r12.withCounterIcon();
        r12.setCounterColor(-6785796);
        r12.setOnClickListener(new ReassignBoostBottomSheet$$ExternalSyntheticLambda0(this, r11));
        r93.addView(r12, LayoutHelper.createLinear(-1, 48, 87));
        ViewGroup r103 = this.containerView;
        int r4 = this.backgroundPaddingLeft;
        r103.addView(r93, LayoutHelper.createFrameMarginPx(-1, -2.0f, 87, r4, 0, r4, 0));
        RecyclerListView r94 = this.recyclerListView;
        int r104 = this.backgroundPaddingLeft;
        r94.setPadding(r104, 0, r104, AndroidUtilities.dp(64.0f));
        this.recyclerListView.setOnItemClickListener(new ReassignBoostBottomSheet$$ExternalSyntheticLambda1(this, r11));
        fixNavigationBar();
        updateTitle();
        updateActionButton(false);
    }

    public static /* synthetic */ RecyclerListView access$000(ReassignBoostBottomSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ RecyclerListView access$100(ReassignBoostBottomSheet r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(ReassignBoostBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(TL_stories.TL_premium_myBoosts r2, List r3, HashSet r4, TL_stories.TL_premium_boostsStatus r5) {
        dismiss();
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.boostedChannelByUser, new Object[]{r2, Integer.valueOf(r3.size()), Integer.valueOf(r4.size()), r5});
    }

    private /* synthetic */ void lambda$new$1(TLRPC.Chat r4, List r5, HashSet r6, TL_stories.TL_premium_myBoosts r7) {
        MessagesController.getInstance(this.currentAccount).getBoostsController().getBoostsStats(-r4.id, new ReassignBoostBottomSheet$$ExternalSyntheticLambda4(this, r7, r5, r6));
    }

    private /* synthetic */ void lambda$new$2(TLRPC.TL_error r3) {
        this.actionButton.setLoading(false);
        BoostDialogs.showToastError(getContext(), r3);
    }

    private /* synthetic */ void lambda$new$3(TLRPC.Chat r5, View r6) {
        if (this.selectedBoosts.isEmpty() == false) goto L6;
        return;
    L6:
        if (this.actionButton.isLoading() == false) goto L8;
        return;
    L8:
        this.actionButton.setLoading(true);
        ArrayList r62 = new ArrayList();
        HashSet r0 = new HashSet();
        Iterator<TL_stories.TL_myBoost> r1 = this.selectedBoosts.iterator();
    L10:
        if (r1.hasNext() == false) goto L12;
        TL_stories.TL_myBoost r2 = r1.next();
        r62.add(Integer.valueOf(r2.slot));
        r0.add(Long.valueOf(DialogObject.getPeerDialogId(r2.peer)));
        goto L10
    L12:
        BoostRepository.applyBoost(r5.id, r62, new ReassignBoostBottomSheet$$ExternalSyntheticLambda2(this, r5, r62, r0), new ReassignBoostBottomSheet$$ExternalSyntheticLambda3(this));
    }

    private /* synthetic */ void lambda$new$4(TLRPC.Chat r3, View r4, int r5) {
        if ((r4 instanceof SelectorUserCell) == false) goto L14;
        SelectorUserCell r42 = (SelectorUserCell) r4;
        if (r42.getBoost().cooldown_until_date <= 0) goto L8;
        SpannableStringBuilder r32 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingWaitWarningPlural", BoostRepository.boostsPerSentGift(), new Object[0]));
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.chats_infotip, r32, 5).show(true);
        return;
    L8:
        boolean r52 = this.selectedBoosts.contains(r42.getBoost());
        List<TL_stories.TL_myBoost> r1 = this.selectedBoosts;
        if (r52 == false) goto L11;
        r1.remove(r42.getBoost());
    L12:
        r42.setChecked(this.selectedBoosts.contains(r42.getBoost()), true);
        updateActionButton(true);
        this.topCell.showBoosts(this.selectedBoosts, r3);
        return;
    L11:
        r1.add(r42.getBoost());
        goto L12
    }

    public static ReassignBoostBottomSheet show(BaseFragment r1, TL_stories.TL_premium_myBoosts r2, TLRPC.Chat r3) {
        ReassignBoostBottomSheet r0 = new ReassignBoostBottomSheet(r1, r2, r3);
        r0.show();
        return r0;
    }

    private void updateActionButton(boolean r5) {
        boolean r1 = false;
        this.actionButton.setShowZero(false);
        int r0 = this.selectedBoosts.size();
        ButtonWithCounterView r2 = this.actionButton;
        if (r0 <= 1) goto L5;
        r2.setText(LocaleController.getString(R.string.BoostingReassignBoosts), r5);
    L6:
        this.actionButton.setCount(this.selectedBoosts.size(), r5);
        ButtonWithCounterView r52 = this.actionButton;
        if (this.selectedBoosts.size() <= 0) goto L9;
        r1 = true;
    L9:
        r52.setEnabled(r1);
        return;
    L5:
        r2.setText(LocaleController.getString(R.string.BoostingReassignBoost), r5);
        goto L6
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
        return new AnonymousClass3(this);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.BoostingReassignBoost);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.timer = new AnonymousClass1(this, LongCompanionObject.MAX_VALUE, 1000);
        Bulletin.addDelegate(this.container, new AnonymousClass2(this));
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.timer.cancel();
        Bulletin.removeDelegate(this.container);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        this.timer.start();
    }
}
