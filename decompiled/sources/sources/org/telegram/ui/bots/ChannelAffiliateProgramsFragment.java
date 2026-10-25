package org.telegram.ui.bots;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.rop.code.RegisterSpec;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.GradientHeaderActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.BotStarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes7.dex */
public class ChannelAffiliateProgramsFragment extends GradientHeaderActivity implements NotificationCenter.NotificationCenterDelegate {
    private FrameLayout aboveTitleView;
    private UniversalAdapter adapter;
    public final long dialogId;
    private View emptyLayout;
    private GLIconTextureView iconTextureView;

    /* renamed from: org.telegram.ui.bots.ChannelAffiliateProgramsFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends View {
        final /* synthetic */ ChannelAffiliateProgramsFragment this$0;

        public AnonymousClass1(ChannelAffiliateProgramsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.bots.ChannelAffiliateProgramsFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChannelAffiliateProgramsFragment this$0;

        public AnonymousClass2(ChannelAffiliateProgramsFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (ChannelAffiliateProgramsFragment.m21453$$Nest$misLoadingVisible(this.this$0) == false) goto L5;
        L8:
            BotStarsController.getInstance(ChannelAffiliateProgramsFragment.access$000(this.this$0)).getChannelConnectedBots(this.this$0.dialogId).load();
            BotStarsController.getInstance(ChannelAffiliateProgramsFragment.access$100(this.this$0)).getChannelSuggestedBots(this.this$0.dialogId).load();
            return;
        L5:
            if (r1.canScrollVertically(1) == false) goto L8;
        }
    }

    /* renamed from: org.telegram.ui.bots.ChannelAffiliateProgramsFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends UniversalAdapter {
        final /* synthetic */ ChannelAffiliateProgramsFragment this$0;

        public AnonymousClass3(ChannelAffiliateProgramsFragment r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 42) goto L7;
            HeaderCell r1 = new HeaderCell(this.this$0.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 21, 0, false, ChannelAffiliateProgramsFragment.access$200(this.this$0));
            r1.setHeight(25);
            return new RecyclerListView.Holder(r1);
        L7:
            return super.onCreateViewHolder(r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.bots.ChannelAffiliateProgramsFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends ClickableSpan {
        final /* synthetic */ ChannelAffiliateProgramsFragment this$0;
        final /* synthetic */ BotStarsController.ChannelSuggestedBots.Sort val$sort;
        final /* synthetic */ BotStarsController.ChannelSuggestedBots val$suggestedBots;

        /* renamed from: $r8$lambda$QMZLnVo-PdXO0mtWhJV_v-2NsjA, reason: not valid java name */
        public static /* synthetic */ void m21454$r8$lambda$QMZLnVoPdXO0mtWhJV_v2NsjA(BotStarsController.ChannelSuggestedBots r1) {
            r1.setSort(BotStarsController.ChannelSuggestedBots.Sort.BY_DATE);
        }

        public static /* synthetic */ void $r8$lambda$XbB1DXKigg8elnRlkmYyn33hXdA(BotStarsController.ChannelSuggestedBots r1) {
            r1.setSort(BotStarsController.ChannelSuggestedBots.Sort.BY_REVENUE);
        }

        /* renamed from: $r8$lambda$c4-aMaoP7a1hx4I7sg7mijCQquY, reason: not valid java name */
        public static /* synthetic */ void m21455$r8$lambda$c4aMaoP7a1hx4I7sg7mijCQquY(BotStarsController.ChannelSuggestedBots r1) {
            r1.setSort(BotStarsController.ChannelSuggestedBots.Sort.BY_PROFITABILITY);
        }

        public AnonymousClass4(ChannelAffiliateProgramsFragment r1, BotStarsController.ChannelSuggestedBots.Sort r2, BotStarsController.ChannelSuggestedBots r3) {
            this.this$0 = r1;
            this.val$sort = r2;
            this.val$suggestedBots = r3;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r7) {
            ItemOptions r72 = ItemOptions.makeOptions(this.this$0, r7);
            boolean r2 = true;
            if (this.val$sort != BotStarsController.ChannelSuggestedBots.Sort.BY_DATE) goto L5;
            boolean r0 = true;
        L6:
            ItemOptions r73 = r72.addChecked(r0, LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSortDate), new ChannelAffiliateProgramsFragment$4$$ExternalSyntheticLambda0(this.val$suggestedBots));
            if (this.val$sort != BotStarsController.ChannelSuggestedBots.Sort.BY_REVENUE) goto L9;
            boolean r02 = true;
        L10:
            ItemOptions r74 = r73.addChecked(r02, LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSortRevenue), new ChannelAffiliateProgramsFragment$4$$ExternalSyntheticLambda1(this.val$suggestedBots));
            if (this.val$sort == BotStarsController.ChannelSuggestedBots.Sort.BY_PROFITABILITY) goto L14;
            r2 = false;
        L14:
            r74.addChecked(r2, LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSortProfitability), new ChannelAffiliateProgramsFragment$4$$ExternalSyntheticLambda2(this.val$suggestedBots)).setGravity(5).setDrawScrim(false).setDimAlpha(0).translate(AndroidUtilities.dp(24.0f), -AndroidUtilities.dp(24.0f)).show();
            return;
        L9:
            r02 = false;
            goto L10
        L5:
            r0 = false;
            goto L6
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
            r1.setColor(r1.linkColor);
        }
    }

    /* renamed from: org.telegram.ui.bots.ChannelAffiliateProgramsFragment$5, reason: invalid class name */
    public class AnonymousClass5 extends StarParticlesView {
        public AnonymousClass5(Context r1) {
            super(r1);
            setClipWithGradient();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public void configure() {
            super.configure();
            StarParticlesView.Drawable r2 = this.drawable;
            r2.useGradient = true;
            r2.useBlur = false;
            r2.forceMaxAlpha = true;
            r2.checkBounds = true;
            r2.init();
        }

        @Override // org.telegram.ui.Components.Premium.StarParticlesView
        public int getStarsRectWidth() {
            return getMeasuredWidth();
        }
    }

    /* renamed from: org.telegram.ui.bots.ChannelAffiliateProgramsFragment$6, reason: invalid class name */
    public class AnonymousClass6 extends ChatActivity {
        final /* synthetic */ BottomSheet val$sheet;

        public AnonymousClass6(Bundle r1, BottomSheet r2) {
            this.val$sheet = r2;
            super(r1);
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onFragmentDestroy() {
            super.onFragmentDestroy();
            this.val$sheet.makeAttached(null);
            this.val$sheet.show();
        }
    }

    public static class BotCell extends FrameLayout {
        private final ImageView arrowView;
        private final int currentAccount;
        private final BackupImageView imageView;
        private final View linkBgView;
        private final View linkFg2View;
        private final ImageView linkFgView;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private final LinearLayout textLayout;
        private final TextView textView;
        private final TextView titleView;

        public static class Factory extends UItem.UItemFactory<BotCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(Object r1) {
                return as(r1, true);
            }

            public static UItem as(Object r1, boolean r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.red = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                Object r0 = r2.object;
                if ((r0 instanceof TL_payments.connectedBotStarRef) == false) goto L7;
                ((BotCell) r1).set((TL_payments.connectedBotStarRef) r0, r2.red, r3);
                return;
            L7:
                if ((r0 instanceof TL_payments.starRefProgram) == false) goto L10;
                ((BotCell) r1).set((TL_payments.starRefProgram) r0, r2.red, r3);
                return;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public BotCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new BotCell(r1, r3, r5);
            }
        }

        public BotCell(Context r12, int r13, Theme.ResourcesProvider r14) {
            super(r12);
            this.currentAccount = r13;
            this.resourcesProvider = r14;
            BackupImageView r132 = new BackupImageView(r12);
            this.imageView = r132;
            r132.setRoundRadius(AndroidUtilities.dp(46.0f));
            addView(r132, LayoutHelper.createFrame(46, 46.0f, 19, 13.0f, 0.0f, 13.0f, 0.0f));
            View r133 = new View(r12);
            this.linkBgView = r133;
            r133.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(11.0f), Theme.getColor(Theme.key_windowBackgroundWhite, r14)));
            addView(r133, LayoutHelper.createFrame(22, 22.0f, 19, 40.0f, 15.0f, 0.0f, 0.0f));
            View r134 = new View(r12);
            this.linkFg2View = r134;
            r134.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(9.665f), Theme.getColor(Theme.key_color_green, r14)));
            addView(r134, LayoutHelper.createFrame(19.33f, 19.33f, 19, 41.33f, 15.0f, 0.0f, 0.0f));
            ImageView r135 = new ImageView(r12);
            this.linkFgView = r135;
            r135.setScaleX(0.6f);
            r135.setScaleY(0.6f);
            addView(r135, LayoutHelper.createFrame(19.33f, 19.33f, 19, 41.33f, 15.0f, 0.0f, 0.0f));
            LinearLayout r136 = new LinearLayout(r12);
            this.textLayout = r136;
            r136.setOrientation(1);
            addView(r136, LayoutHelper.createFrame(-1, -2.0f, 55, 66.0f, 8.66f, 10.0f, 0.0f));
            TextView r1 = new TextView(r12);
            this.titleView = r1;
            r1.setMaxLines(1);
            r1.setSingleLine(true);
            TextUtils.TruncateAt r2 = TextUtils.TruncateAt.END;
            r1.setEllipsize(r2);
            r1.setTypeface(AndroidUtilities.bold());
            r1.setTextSize(1, 16.0f);
            r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r14));
            NotificationCenter.listenEmojiLoading(r1);
            r136.addView(r1, LayoutHelper.createLinear(-1, -2, 55, 6, 0, 24, 0));
            TextView r15 = new TextView(r12);
            this.textView = r15;
            r15.setMaxLines(1);
            r15.setSingleLine(true);
            r15.setEllipsize(r2);
            r15.setTextSize(1, 14.0f);
            r15.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r14));
            r136.addView(r15, LayoutHelper.createLinear(-1, -2, 55, 6, 1, 24, 0));
            ImageView r137 = new ImageView(r12);
            this.arrowView = r137;
            r137.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_switchTrack, r14), PorterDuff.Mode.SRC_IN));
            r137.setImageResource(R.drawable.msg_arrowright);
            r137.setScaleType(ImageView.ScaleType.CENTER);
            addView(r137, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 10.0f, 0.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(AndroidUtilities.dp(72.0f), getHeight() - 1, getWidth(), getHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
        }

        public void set(TL_payments.connectedBotStarRef r7, boolean r8, boolean r9) {
            TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r7.bot_id));
            AvatarDrawable r1 = new AvatarDrawable();
            r1.setInfo(r0);
            this.imageView.setForUserOrChat(r0, r1);
            this.titleView.setText(Emoji.replaceEmoji(UserObject.getUserName(r0), this.titleView.getPaint().getFontMetricsInt(), false));
            SpannableStringBuilder r02 = new SpannableStringBuilder();
            if (r7.commission_permille <= 0) goto L5;
            r02.append(" d");
            FilterCreateActivity.NewSpan r12 = new FilterCreateActivity.NewSpan(10.0f);
            r12.setColor(Theme.getColor(Theme.key_color_green));
            r12.setText(AffiliateProgramFragment.percents(r7.commission_permille));
            r02.setSpan(r12, 1, 2, 33);
        L5:
            int r13 = r7.duration_months;
            if (r13 != 0) goto L9;
            r02.append(LocaleController.getString(R.string.Lifetime));
        L15:
            this.textView.setText(r02);
            ImageView r03 = this.arrowView;
            if (r8 == false) goto L18;
            int r82 = 0;
        L19:
            r03.setVisibility(r82);
            this.linkBgView.setVisibility(0);
            this.linkFgView.setVisibility(0);
            this.linkFg2View.setVisibility(0);
            View r83 = this.linkFg2View;
            int r04 = AndroidUtilities.dp(9.665f);
            if (r7.revoked == false) goto L22;
            int r14 = Theme.key_color_red;
        L23:
            r83.setBackground(Theme.createCircleDrawable(r04, Theme.getColor(r14, this.resourcesProvider)));
            ImageView r84 = this.linkFgView;
            if (r7.revoked == false) goto L26;
            int r05 = R.drawable.msg_link_2;
        L27:
            r84.setImageResource(r05);
            ImageView r85 = this.linkFgView;
            float r15 = 0.6f;
            if (r7.revoked == false) goto L30;
            float r06 = 0.8f;
        L31:
            r85.setScaleX(r06);
            ImageView r86 = this.linkFgView;
            if (r7.revoked == false) goto L34;
            r15 = 0.8f;
        L34:
            r86.setScaleY(r15);
            this.needDivider = r9;
            setWillNotDraw(!r9);
            return;
        L30:
            r06 = 0.6f;
            goto L31
        L26:
            r05 = R.drawable.msg_limit_links;
            goto L27
        L22:
            r14 = Theme.key_color_green;
            goto L23
        L18:
            r82 = 8;
            goto L19
        L9:
            if (r13 >= 12) goto L11;
        L14:
            r02.append(LocaleController.formatPluralString("Months", r13, new Object[0]));
            goto L15
        L11:
            if ((r13 % 12) != 0) goto L14;
            r02.append(LocaleController.formatPluralString("Years", r13 / 12, new Object[0]));
            goto L15
        }

        public void set(TL_payments.starRefProgram r6, boolean r7, boolean r8) {
            TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r6.bot_id));
            AvatarDrawable r1 = new AvatarDrawable();
            r1.setInfo(r0);
            this.imageView.setForUserOrChat(r0, r1);
            this.titleView.setText(UserObject.getUserName(r0));
            SpannableStringBuilder r02 = new SpannableStringBuilder();
            if (r6.commission_permille <= 0) goto L5;
            r02.append(" d");
            FilterCreateActivity.NewSpan r12 = new FilterCreateActivity.NewSpan(10.0f);
            r12.setColor(Theme.getColor(Theme.key_color_green));
            r12.setText(AffiliateProgramFragment.percents(r6.commission_permille));
            r02.setSpan(r12, 1, 2, 33);
        L5:
            int r62 = r6.duration_months;
            int r13 = 0;
            if (r62 != 0) goto L9;
            r02.append(LocaleController.getString(R.string.Lifetime));
        L15:
            this.textView.setText(r02);
            ImageView r63 = this.arrowView;
            if (r7 == true) goto L19;
            r13 = 8;
        L19:
            r63.setVisibility(r13);
            this.linkBgView.setVisibility(8);
            this.linkFgView.setVisibility(8);
            this.linkFg2View.setVisibility(8);
            this.needDivider = r8;
            setWillNotDraw(!r8);
            return;
        L9:
            if (r62 >= 12) goto L11;
        L14:
            r02.append(LocaleController.formatPluralString("Months", r62, new Object[0]));
            goto L15
        L11:
            if ((r62 % 12) != 0) goto L14;
            r02.append(LocaleController.formatPluralString("Years", r62 / 12, new Object[0]));
            goto L15
        }
    }

    public static class HeaderSortCell extends HeaderCell {
        private final LinkSpanDrawable.LinksTextView subtextView;

        public static class Factory extends UItem.UItemFactory<HeaderSortCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(CharSequence r1, CharSequence r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.text = r1;
                r0.subtext = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((HeaderSortCell) r1).set(r2.text, r2.subtext);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public HeaderSortCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new HeaderSortCell(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }
        }

        public HeaderSortCell(Context r9, Theme.ResourcesProvider r10) {
            super(r9, r10);
            LinkSpanDrawable.LinksTextView r0 = new LinkSpanDrawable.LinksTextView(r9, r10);
            this.subtextView = r0;
            r0.setTextSize(1, 14.0f);
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r10));
            r0.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r10));
            r0.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
            if (LocaleController.isRTL == false) goto L5;
            int r92 = 3;
        L6:
            addView(r0, LayoutHelper.createFrame(-2, -2.0f, r92 | 48, 10.0f, 14.0f, 10.0f, 0.0f));
            return;
        L5:
            r92 = 5;
            goto L6
        }

        public void set(CharSequence r1, CharSequence r2) {
            setText(r1);
            this.subtextView.setText(r2);
        }
    }

    /* renamed from: $r8$lambda$165OaZsZ-IekrmNA08S5AIc_qQc, reason: not valid java name */
    public static /* synthetic */ void m21441$r8$lambda$165OaZsZIekrmNA08S5AIc_qQc(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$2SBeY3sdWApIf4v0GmGw5wGD8xw(BottomSheet r8, Context r9, int r10, long r11, Theme.ResourcesProvider r13, TLRPC.UserFull r14) {
        AndroidUtilities.runOnUIThread(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda31(r14, r8, r9, r10, r11, r13));
    }

    public static /* synthetic */ void $r8$lambda$5P6EVfhhJfxQGq65h7xcZmgD49g(Context r1) {
        Browser.openUrl(r1, LocaleController.getString(R.string.ChannelAffiliateProgramJoinButtonInfoLink));
    }

    public static /* synthetic */ void $r8$lambda$8ozG0bzrbDF0uUxgLUm3whr64Ic(ChannelAffiliateProgramsFragment r0, TL_payments.connectedBotStarRef r1, AlertDialog r2, int r3) {
        r0.lambda$createView$6(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$CNUSHoXx-s3X85hx4PJhdmecPzw, reason: not valid java name */
    public static /* synthetic */ void m21442$r8$lambda$CNUSHoXxs3X85hx4PJhdmecPzw(ButtonWithCounterView r15, int r16, long r17, BottomSheet r19, TL_payments.starRefProgram r20, long r21, boolean r23, Context r24, Theme.ResourcesProvider r25, TLRPC.User r26, TLObject r27, TLRPC.TL_error r28) {
        AndroidUtilities.runOnUIThread(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda28(r15, r27, r16, r17, r19, r20, r21, r23, r24, r25, r26, r28));
    }

    /* renamed from: $r8$lambda$DA_jYfNNWEocIbgyGZeSJnRhK-8, reason: not valid java name */
    public static /* synthetic */ void m21443$r8$lambda$DA_jYfNNWEocIbgyGZeSJnRhK8(BottomSheet r0, TL_payments.connectedBotStarRef r1, View r2) {
        r0.dismiss();
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r02 == null) goto L6;
        r02.presentFragment(ProfileActivity.of(r1.bot_id));
        return;
    }

    /* renamed from: $r8$lambda$GfWtO3GP-RLf2Utb4HcFxHVUeXo, reason: not valid java name */
    public static /* synthetic */ void m21444$r8$lambda$GfWtO3GPRLf2Utb4HcFxHVUeXo(TLRPC.UserFull r2, BottomSheet r3, Context r4, int r5, long r6, Theme.ResourcesProvider r8) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.starref_program == null) goto L8;
        r3.dismiss();
        showConnectAffiliateAlert(r4, r5, r2.starref_program, r6, r8, true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$IcNRNxpxS4fjkN6ISX9CYpyMFyg(DialogInterface r0) {
    }

    public static /* synthetic */ void $r8$lambda$JQNNCmZMqJW62sashngQ3zmj_6I(ChannelAffiliateProgramsFragment r0, TL_payments.connectedBotStarRef r1, TLRPC.User r2) {
        r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: $r8$lambda$JlU1eFbtlWLhVfeI_f9c-rkV5bc, reason: not valid java name */
    public static /* synthetic */ void m21445$r8$lambda$JlU1eFbtlWLhVfeI_f9crkV5bc(ChannelAffiliateProgramsFragment r0, Context r1, View r2, int r3) {
        r0.lambda$createView$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JyLqYpBbt_sueTIyA3qWtGeOAoU(ChannelAffiliateProgramsFragment r0, Context r1, TLRPC.User r2, TL_payments.connectedBotStarRef r3) {
        r0.lambda$createView$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$K-8ZQB56DMnKndeDZ5XKYwGV050, reason: not valid java name */
    public static /* synthetic */ void m21446$r8$lambda$K8ZQB56DMnKndeDZ5XKYwGV050(BottomSheet r8, Context r9, int r10, long r11, Theme.ResourcesProvider r13, TLRPC.UserFull r14) {
        AndroidUtilities.runOnUIThread(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda26(r14, r8, r9, r10, r11, r13));
    }

    public static /* synthetic */ void $r8$lambda$LRr52sRYPPx8YPyfkS8PVzQPZoY(ButtonWithCounterView r6, TLObject r7, int r8, long r9, BottomSheet r11, TL_payments.starRefProgram r12, long r13, boolean r15, Context r16, Theme.ResourcesProvider r17, TLRPC.User r18, TLRPC.TL_error r19) {
        int r1 = 0;
        r6.setLoading(false);
        if ((r7 instanceof TL_payments.connectedStarRefBots) == false) goto L26;
        TL_payments.connectedStarRefBots r72 = (TL_payments.connectedStarRefBots) r7;
        BotStarsController.getInstance(r8).getChannelConnectedBots(r9).apply(r72);
        r11.dismiss();
    L6:
        if (r1 >= r72.connected_bots.size()) goto L11;
        TL_payments.connectedBotStarRef r62 = r72.connected_bots.get(r1);
        if (r62.bot_id == r12.bot_id) goto L9;
        r1 = r1 + 1;
    L9:
        TL_payments.connectedBotStarRef r2 = r62;
        if (r13 != r9) goto L15;
        if (r15 == true) goto L15;
    L22:
        if (r2 == null) goto L31;
        BotStarsController.getInstance(r8).getChannelSuggestedBots(r9).remove(r2.bot_id);
        BulletinFactory.of(showShareAffiliateAlert(r16, r8, r2, r9, r17).topBulletinContainer, r17).createUsersBulletin(r18, LocaleController.getString(R.string.AffiliateProgramJoinedTitle), LocaleController.getString(R.string.AffiliateProgramJoinedText)).show();
        return;
    L31:
        return;
    L15:
        BaseFragment r63 = LaunchActivity.getSafeLastFragment();
        if (r63 == null) goto L22;
        if ((r63 instanceof ChannelAffiliateProgramsFragment) == true) goto L20;
    L21:
        r63.presentFragment(new ChannelAffiliateProgramsFragment(r9));
        goto L22
    L20:
        if (((ChannelAffiliateProgramsFragment) r63).dialogId == r9) goto L22;
    L11:
        r62 = null;
        goto L9
    L26:
        if (r19 == null) goto L32;
        BulletinFactory.of(r11.topBulletinContainer, r17).showForError(r19);
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$LaV6bFG2Bq5HWb47gLnB0lpyIjw(ChannelAffiliateProgramsFragment r0, Context r1, View r2, int r3) {
        return r0.lambda$createView$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$RSMiA0pDgQwFzQxvPORVXXWPlFw(BottomSheet r4, TL_payments.starRefProgram r5, View r6) {
        BaseFragment r62 = LaunchActivity.getSafeLastFragment();
        if (r62 == null) goto L6;
        r4.dismiss();
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r5.bot_id);
        r62.presentFragment(new AnonymousClass6(r0, r4));
        return;
    }

    public static /* synthetic */ void $r8$lambda$SMHHNYwLqpSUSV1onZnaUGrpXFo(TL_payments.connectedBotStarRef r2, BottomSheet r3, Theme.ResourcesProvider r4, TLRPC.User r5) {
        AndroidUtilities.addToClipboard(r2.url);
        BulletinFactory.of(r3.topBulletinContainer, r4).createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.AffiliateProgramLinkCopiedTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AffiliateProgramLinkCopiedText, new Object[]{AffiliateProgramFragment.percents(r2.commission_permille), UserObject.getUserName(r5)}))).show();
    }

    public static /* synthetic */ void $r8$lambda$U5M0dEYFsmK8eGou6IsN1ErQ8PI(int r7, TL_payments.connectedBotStarRef r8, BottomSheet r9, Context r10, long r11, Theme.ResourcesProvider r13, TL_payments.connectedBotStarRef r14) {
        if (r14 != null) goto L7;
        TLRPC.User r82 = MessagesController.getInstance(r7).getUser(Long.valueOf(r8.bot_id));
        if (r82 == null) goto L9;
        MessagesController.getInstance(r7).loadFullUser(r82, 0, true, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda29(r9, r10, r7, r11, r13));
        return;
    L9:
        return;
    L7:
        r9.dismiss();
        showShareAffiliateAlert(r10, r7, r14, r11, r13);
    }

    /* renamed from: $r8$lambda$XgA49I2JQGHCeLKN--4jezb1a-Q, reason: not valid java name */
    public static /* synthetic */ void m21447$r8$lambda$XgA49I2JQGHCeLKN4jezb1aQ(int r5, BottomSheet r6, Theme.ResourcesProvider r7, View r8, long[] r9, Runnable r10, View r11) {
        ArrayList<TLObject> r112 = BotStarsController.getInstance(r5).getAdmined();
        r112.add(0, UserConfig.getInstance(r5).getCurrentUser());
        ItemOptions r52 = ItemOptions.makeOptions(r6.getContainerView(), r7, r8);
        int r62 = r112.size();
        int r72 = 0;
    L3:
        if (r72 >= r62) goto L18;
        TLObject r82 = r112.get(r72);
        r72 = r72 + 1;
        TLObject r83 = r82;
        if ((r83 instanceof TLRPC.User) == true) goto L6;
        if ((r83 instanceof TLRPC.Chat) == false) goto L3;
        TLRPC.Chat r1 = (TLRPC.Chat) r83;
        if (ChatObject.isChannelAndNotMegaGroup(r1) == false) goto L3;
        long r12 = -r1.id;
    L14:
        if (r12 != r9[0]) goto L16;
        boolean r3 = true;
    L17:
        r52.addChat(r83, r3, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda23(r9, r12, r10));
        goto L3
    L16:
        r3 = false;
        goto L17
    L6:
        r12 = ((TLRPC.User) r83).id;
        goto L14
    L18:
        r52.setDrawScrim(false).setDimAlpha(0).setGravity(5).translate(AndroidUtilities.dp(24.0f), 0.0f).show();
    }

    /* renamed from: $r8$lambda$XpRUTWcD8Q-f60LYRYKywfdbQTg, reason: not valid java name */
    public static /* synthetic */ void m21448$r8$lambda$XpRUTWcD8Qf60LYRYKywfdbQTg(ButtonWithCounterView r15, long[] r16, int r17, TL_payments.starRefProgram r18, BottomSheet r19, long r20, boolean r22, Context r23, Theme.ResourcesProvider r24, TLRPC.User r25, View r26) {
        if (r15.isLoading() == false) goto L5;
        return;
    L5:
        r15.setLoading(true);
        long r4 = r16[0];
        TL_payments.connectStarRefBot r0 = new TL_payments.connectStarRefBot();
        r0.bot = MessagesController.getInstance(r17).getInputUser(r18.bot_id);
        r0.peer = MessagesController.getInstance(r17).getInputPeer(r4);
        ConnectionsManager.getInstance(r17).sendRequest(r0, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda20(r15, r17, r4, r19, r18, r20, r22, r23, r24, r25));
    }

    public static /* synthetic */ void $r8$lambda$_FG2plHb7hHSRb9HEpFX5_1VyqY(ChannelAffiliateProgramsFragment r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hnS2-N_Xb8UttwgCYfQn0SREgRU, reason: not valid java name */
    public static /* synthetic */ void m21449$r8$lambda$hnS2N_Xb8UttwgCYfQn0SREgRU(ChannelAffiliateProgramsFragment r0, TL_payments.connectedBotStarRef r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$mUxD7g8A1fYLDJWVK6U92WVYez0(int r8, Context r9, long r10, TL_payments.connectedBotStarRef r12, BottomSheet r13, Theme.ResourcesProvider r14) {
        BotStarsController.getInstance(r8).getConnectedBot(r9, r10, r12.bot_id, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda27(r8, r12, r13, r9, r10, r14));
    }

    /* renamed from: $r8$lambda$mwyYcih-qrMhDuFrVLJJfCk1_A0, reason: not valid java name */
    public static /* synthetic */ void m21450$r8$lambda$mwyYcihqrMhDuFrVLJJfCk1_A0(ChannelAffiliateProgramsFragment r0, TLObject r1, AlertDialog r2) {
        r0.lambda$createView$4(r1, r2);
    }

    /* renamed from: $r8$lambda$myZWo4kuTQPqu_UZ-iRZ7NiFhCg, reason: not valid java name */
    public static /* synthetic */ void m21451$r8$lambda$myZWo4kuTQPqu_UZiRZ7NiFhCg(long[] r1, long r2, Runnable r4) {
        r1[0] = r2;
        r4.run();
    }

    public static /* synthetic */ void $r8$lambda$o7GLpztc6L3hEaCRVJDrJ8rB_Mw(ChannelAffiliateProgramsFragment r0, TLRPC.User r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$rHlQjS6rQzJZVWBe1x1qEJrZ2KM(long[] r7, int r8, BackupImageView r9, BackupImageView r10, TextView r11) {
        if (r7[0] < 0) goto L5;
        TLRPC.User r1 = MessagesController.getInstance(r8).getUser(Long.valueOf(r7[0]));
        AvatarDrawable r2 = new AvatarDrawable();
        r2.setInfo(r1);
        r9.setForUserOrChat(r1, r2);
    L7:
        if (r7[0] < 0) goto L14;
        TLRPC.User r72 = MessagesController.getInstance(r8).getUser(Long.valueOf(r7[0]));
        if (r10 == null) goto L11;
        AvatarDrawable r82 = new AvatarDrawable();
        r82.setInfo(r72);
        r10.setForUserOrChat(r72, r82);
    L11:
        if (r11 == null) goto L23;
        r11.setText(UserObject.getUserName(r72));
        return;
    L23:
        return;
    L14:
        TLRPC.Chat r73 = MessagesController.getInstance(r8).getChat(Long.valueOf(-r7[0]));
        if (r10 == null) goto L17;
        AvatarDrawable r83 = new AvatarDrawable();
        r83.setInfo(r73);
        r10.setForUserOrChat(r73, r83);
    L17:
        if (r11 == null) goto L24;
        if (r73 != null) goto L20;
        String r74 = _UrlKt.FRAGMENT_ENCODE_SET;
    L21:
        r11.setText(r74);
        return;
    L20:
        r74 = r73.title;
        goto L21
    L24:
        return;
    L5:
        TLRPC.Chat r12 = MessagesController.getInstance(r8).getChat(Long.valueOf(-r7[0]));
        AvatarDrawable r22 = new AvatarDrawable();
        r22.setInfo(r12);
        r9.setForUserOrChat(r12, r22);
        goto L7
    }

    /* renamed from: $r8$lambda$rkFAKY-igEpM2vOin2Pe0z1jB8Q, reason: not valid java name */
    public static /* synthetic */ void m21452$r8$lambda$rkFAKYigEpM2vOin2Pe0z1jB8Q(int r15, BottomSheet r16, Theme.ResourcesProvider r17, View r18, long r19, Context r21, TL_payments.connectedBotStarRef r22, View r23) {
        ArrayList<TLObject> r0 = BotStarsController.getInstance(r15).getAdmined();
        r0.add(0, UserConfig.getInstance(r15).getCurrentUser());
        Theme.ResourcesProvider r10 = r17;
        ItemOptions r1 = ItemOptions.makeOptions(r16.getContainerView(), r10, r18);
        int r11 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r11) goto L20;
        int r12 = r3 + 1;
        TLObject r13 = r0.get(r3);
        if ((r13 instanceof TLRPC.User) == false) goto L9;
        long r32 = ((TLRPC.User) r13).id;
    L7:
        long r6 = r32;
        if (r6 != r19) goto L17;
        boolean r14 = true;
    L18:
        r1.addChat(r13, r14, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda21(r15, r21, r6, r22, r16, r10));
    L19:
        r10 = r17;
    L12:
        r3 = r12;
        goto L3
    L17:
        r14 = false;
        goto L18
    L9:
        if ((r13 instanceof TLRPC.Chat) == false) goto L19;
        TLRPC.Chat r33 = (TLRPC.Chat) r13;
        if (ChatObject.isChannelAndNotMegaGroup(r33) == false) goto L12;
        r32 = -r33.id;
        goto L7
    L20:
        r1.setDrawScrim(false).setDimAlpha(0).setGravity(5).translate(AndroidUtilities.dp(24.0f), 0.0f).show();
    }

    public static /* synthetic */ void $r8$lambda$s5dd51qpgOmxoxkAzPDs_oY_Tx4(DialogInterface r0) {
    }

    public static /* synthetic */ void $r8$lambda$tpdveAv7H9ApMIxMHVHQz7ezBY4(TL_payments.connectedBotStarRef r7, int r8, BottomSheet r9, Context r10, long r11, Theme.ResourcesProvider r13, Runnable r14, View r15) {
        if (r7.revoked == false) goto L8;
        TLRPC.User r72 = MessagesController.getInstance(r8).getUser(Long.valueOf(r7.bot_id));
        if (r72 == null) goto L10;
        MessagesController.getInstance(r8).loadFullUser(r72, 0, true, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda24(r9, r10, r8, r11, r13));
        return;
    L10:
        return;
    L8:
        r14.run();
    }

    public static /* synthetic */ void $r8$lambda$u08yvmq6eYP6ESO54NvUTqSJEm0(BottomSheet r0, TL_payments.starRefProgram r1, View r2) {
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 == null) goto L6;
        r0.dismiss();
        r22.presentFragment(ProfileActivity.of(r1.bot_id));
        return;
    }

    public static /* synthetic */ void $r8$lambda$xtQBE4xrwBaD_CFWzyNr4lb7X2Y(TLRPC.UserFull r2, BottomSheet r3, Context r4, int r5, long r6, Theme.ResourcesProvider r8) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.starref_program == null) goto L8;
        r3.dismiss();
        showConnectAffiliateAlert(r4, r5, r2.starref_program, r6, r8, true);
        return;
    }

    /* renamed from: -$$Nest$misLoadingVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21453$$Nest$misLoadingVisible(ChannelAffiliateProgramsFragment r0) {
        return r0.isLoadingVisible();
    }

    public ChannelAffiliateProgramsFragment(long r1) {
        this.dialogId = r1;
        setWhiteBackground(true);
        setMinusHeaderHeight(AndroidUtilities.dp(60.0f));
    }

    public static /* synthetic */ int access$000(ChannelAffiliateProgramsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(ChannelAffiliateProgramsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(ChannelAffiliateProgramsFragment r0) {
        return r0.resourceProvider;
    }

    private boolean isLoadingVisible() {
        int r1 = 0;
    L4:
        if (r1 >= this.listView.getChildCount()) goto L10;
        if ((this.listView.getChildAt(r1) instanceof FlickerLoadingView) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$createView$0(Context r17, View r18, int r19) {
        UniversalAdapter r1 = this.adapter;
        if (r1 == null) goto L14;
        Object r12 = r1.getItem(r19).object;
        if ((r12 instanceof TL_payments.starRefProgram) == false) goto L10;
        showConnectAffiliateAlert(r17, this.currentAccount, (TL_payments.starRefProgram) r12, this.dialogId, this.resourceProvider, false);
        return;
    L10:
        if ((r12 instanceof TL_payments.connectedBotStarRef) == false) goto L13;
        showShareAffiliateAlert(r17, this.currentAccount, (TL_payments.connectedBotStarRef) r12, this.dialogId, this.resourceProvider);
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$createView$1(TLRPC.User r2) {
        getMessagesController().openApp(r2, getClassGuid());
    }

    private /* synthetic */ void lambda$createView$2(TL_payments.connectedBotStarRef r3) {
        presentFragment(ChatActivity.of(r3.bot_id));
    }

    private /* synthetic */ void lambda$createView$3(TL_payments.connectedBotStarRef r4, TLRPC.User r5) {
        AndroidUtilities.addToClipboard(r4.url);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.copy, LocaleController.getString(R.string.AffiliateProgramLinkCopiedTitle), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AffiliateProgramLinkCopiedText, new Object[]{AffiliateProgramFragment.percents(r4.commission_permille), UserObject.getUserName(r5)}))).show();
    }

    private /* synthetic */ void lambda$createView$4(TLObject r4, AlertDialog r5) {
        if ((r4 instanceof TL_payments.connectedStarRefBots) == false) goto L5;
        BotStarsController.ChannelConnectedBots r0 = BotStarsController.getInstance(this.currentAccount).getChannelConnectedBots(this.dialogId);
        r0.applyEdit((TL_payments.connectedStarRefBots) r4);
        BotStarsController.getInstance(this.currentAccount).getChannelSuggestedBots(this.dialogId).reload();
        this.adapter.update(true);
    L5:
        r5.dismiss();
    }

    private /* synthetic */ void lambda$createView$5(AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda30(this, r2, r1));
    }

    private /* synthetic */ void lambda$createView$6(TL_payments.connectedBotStarRef r3, AlertDialog r4, int r5) {
        AlertDialog r42 = new AlertDialog(getParentActivity(), 3);
        r42.showDelayed(200);
        TL_payments.editConnectedStarRefBot r52 = new TL_payments.editConnectedStarRefBot();
        r52.link = r3.url;
        r52.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r52.revoked = true;
        getConnectionsManager().sendRequest(r52, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda25(this, r42));
    }

    private /* synthetic */ void lambda$createView$7(Context r3, TLRPC.User r4, TL_payments.connectedBotStarRef r5) {
        new AlertDialog.Builder(r3, this.resourceProvider).setTitle(LocaleController.getString(R.string.LeaveAffiliateLink)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.LeaveAffiliateLinkAlert, new Object[]{UserObject.getUserName(r4)}))).setPositiveButton(LocaleController.getString(R.string.LeaveAffiliateLinkButton), new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda22(this, r5)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).makeRed(-1).show();
    }

    private /* synthetic */ boolean lambda$createView$8(Context r12, View r13, int r14) {
        UniversalAdapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return false;
    L5:
        Object r142 = r0.getItem(r14).object;
        if ((r142 instanceof TL_payments.connectedBotStarRef) == false) goto L9;
        TL_payments.connectedBotStarRef r143 = (TL_payments.connectedBotStarRef) r142;
        TLRPC.User r02 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r143.bot_id));
        ItemOptions.makeOptions(this, r13).addIf(r02.bot_has_main_app, R.drawable.msg_bot, LocaleController.getString(R.string.ProfileBotOpenApp), new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda9(this, r02)).addIf(!r02.bot_has_main_app, R.drawable.msg_bot, LocaleController.getString(R.string.BotWebViewOpenBot), new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda10(this, r143)).add(R.drawable.msg_link2, LocaleController.getString(R.string.CopyLink), new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda11(this, r143, r02)).addIf(!r143.revoked, R.drawable.msg_leave, LocaleController.getString(R.string.LeaveAffiliateLinkButton), true, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda12(this, r12, r02, r143)).setGravity(5).show();
        return true;
    L9:
        return false;
    }

    public static StarParticlesView makeParticlesView(Context r0, int r1, int r2) {
        return new AnonymousClass5(r0);
    }

    public static void showConnectAffiliateAlert(Context r33, int r34, TL_payments.starRefProgram r35, long r36, Theme.ResourcesProvider r38, boolean r39) {
        if (r35 == null) goto L31;
        if (r33 == null) goto L32;
        BottomSheet.Builder r0 = new BottomSheet.Builder(r33, false, r38);
        long[] r5 = {r36};
        TLRPC.User r11 = MessagesController.getInstance(r34).getUser(Long.valueOf(r35.bot_id));
        LinearLayout r3 = new LinearLayout(r33);
        r3.setOrientation(1);
        r3.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r3.setClipChildren(false);
        r3.setClipToPadding(false);
        FrameLayout r6 = new FrameLayout(r33);
        r6.setClipToPadding(false);
        r6.setClipChildren(false);
        FrameLayout r7 = new FrameLayout(r33);
        r7.setClipToPadding(false);
        r7.setClipChildren(false);
        r6.addView(r7, LayoutHelper.createFrame(60, 60.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
        BackupImageView r12 = new BackupImageView(r33);
        r12.setRoundRadius(AndroidUtilities.dp(30.0f));
        AvatarDrawable r14 = new AvatarDrawable();
        r14.setInfo(r11);
        r12.setForUserOrChat(r11, r14);
        ScaleStateListAnimator.apply(r12);
        r7.addView(r12, LayoutHelper.createFrame(60, 60, 119));
        if (r35.daily_revenue_per_user.positive() == false) goto L9;
        FrameLayout r13 = new FrameLayout(r33);
        r13.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), Theme.getColor(Theme.key_dialogBackground, r38)));
        r13.setPadding(AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f));
        TextView r1 = new TextView(r33);
        r1.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), Theme.getColor(Theme.key_color_green, r38)));
        r1.setTypeface(AndroidUtilities.bold());
        r1.setTextSize(1, 10.0f);
        float r24 = 10.0f;
        r1.setPadding(AndroidUtilities.dp(5.33f), 0, AndroidUtilities.dp(5.33f), 0);
        r1.setTextColor(-1);
        r1.setGravity(17);
        StringBuilder r2 = new StringBuilder("⭐️ ");
        long[] r25 = r5;
        r2.append(StarsIntroActivity.formatStarsAmountShort(r35.daily_revenue_per_user, 1.0f, ','));
        r1.setText(StarsIntroActivity.replaceStars(r2.toString(), 0.75f, new ColoredImageSpan[1]));
        r13.addView(r1, LayoutHelper.createFrame(-2, 15.66f));
        r7.addView(r13, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, -4.0f));
    L10:
        ImageView r15 = new ImageView(r33);
        r15.setImageResource(R.drawable.msg_arrow_avatar);
        ImageView.ScaleType r22 = ImageView.ScaleType.CENTER;
        r15.setScaleType(r22);
        r15.setTranslationX(-AndroidUtilities.dp(2.0825f));
        int r72 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7, r38);
        PorterDuff.Mode r8 = PorterDuff.Mode.SRC_IN;
        r15.setColorFilter(new PorterDuffColorFilter(r72, r8));
        r6.addView(r15, LayoutHelper.createFrame(36, 60.0f, 17, 60.0f, 0.0f, 60.0f, 0.0f));
        FrameLayout r16 = new FrameLayout(r33);
        r16.setClipToPadding(false);
        r16.setClipChildren(false);
        r6.addView(r16, LayoutHelper.createFrame(60, 60.0f, 21, 0.0f, 0.0f, 5.66f, 0.0f));
        BackupImageView r132 = new BackupImageView(r33);
        r132.setRoundRadius(AndroidUtilities.dp(30.0f));
        r16.addView(r132, LayoutHelper.createFrame(60, 60, 119));
        FrameLayout r52 = new FrameLayout(r33);
        int r73 = AndroidUtilities.dp(r24);
        int r142 = Theme.key_dialogBackground;
        r52.setBackground(Theme.createRoundRectDrawable(r73, Theme.getColor(r142, r38)));
        r52.setPadding(AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f));
        TextView r74 = new TextView(r33);
        r74.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(r24), Theme.getColor(Theme.key_featuredStickers_addButton, r38)));
        r74.setTypeface(AndroidUtilities.bold());
        r74.setTextSize(1, r24);
        r74.setPadding(AndroidUtilities.dp(5.33f), 0, AndroidUtilities.dp(5.33f), 0);
        r74.setTextColor(-1);
        r74.setGravity(17);
        SpannableString r112 = new SpannableString("s " + AffiliateProgramFragment.percents(r35.commission_permille));
        ColoredImageSpan r133 = new ColoredImageSpan(R.drawable.msg_link_1);
        r133.setScale(0.65f, 0.65f);
        r133.spaceScaleX = 0.7f;
        r133.translate(AndroidUtilities.dp(-2.0f), AndroidUtilities.dp(0.0f));
        r112.setSpan(r133, 0, 1, 33);
        r74.setText(r112);
        r52.addView(r74, LayoutHelper.createFrame(-2, 15.66f));
        r16.addView(r52, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, -4.0f));
        r3.addView(r6, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 0));
        TextView r17 = new TextView(r33);
        int r53 = Theme.key_windowBackgroundWhiteBlackText;
        r17.setTextColor(Theme.getColor(r53, r38));
        r17.setTextSize(1, 20.0f);
        r17.setGravity(17);
        r17.setText(LocaleController.getString(R.string.ChannelAffiliateProgramJoinTitle));
        r17.setTypeface(AndroidUtilities.bold());
        r3.addView(r17, LayoutHelper.createLinear(-1, -2, 0.0f, 21.0f, 0.0f, 9.0f));
        LinearLayout r122 = new LinearLayout(r33);
        r122.setOrientation(0);
        int r62 = AndroidUtilities.dp(28.0f);
        int r75 = Theme.key_windowBackgroundGray;
        r122.setBackground(Theme.createRoundRectDrawable(r62, Theme.getColor(r75, r38)));
        TextView r63 = new TextView(r33);
        r63.setTextSize(1, 13.0f);
        r63.setTextColor(Theme.getColor(r53, r38));
        r63.setText(LocaleController.formatString(R.string.ChannelAffiliateProgramJoinViewBot, new Object[]{DialogObject.getName(r34, r35.bot_id)}));
        r122.addView(r63, LayoutHelper.createLinear(-2, -2, 16, 11, 0, 0, 0));
        ImageView r64 = new ImageView(r33);
        r64.setScaleType(r22);
        int r143 = Theme.key_dialogTextGray3;
        r64.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r143, r38), r8));
        r64.setImageResource(R.drawable.settings_arrow);
        r64.setScaleX(1.2f);
        r64.setScaleY(1.2f);
        r122.addView(r64, LayoutHelper.createLinear(-2, -2, 16, 5, 0, 8, 0));
        r3.addView(r122, LayoutHelper.createLinear(-2, 28, 1, 4, 0, 4, 0));
        ScaleStateListAnimator.apply(r122);
        TextView r65 = new TextView(r33);
        r65.setTextColor(Theme.getColor(r53, r38));
        r65.setTextSize(1, 14.0f);
        r65.setGravity(17);
        NotificationCenter.listenEmojiLoading(r65);
        SpannableString r152 = new SpannableString(StarsIntroActivity.formatStarsAmountShort(r35.daily_revenue_per_user, 0.95f, ','));
        r152.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r152.length(), 33);
        r65.setText(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatSpannable(R.string.ChannelAffiliateProgramJoinRevenue, new Object[]{r152}), 0.725f));
        r3.addView(r65, LayoutHelper.createLinear(-1, -2, 0.0f, 10.0f, 0.0f, 20.0f));
        TextView r18 = new TextView(r33);
        r18.setTextColor(Theme.getColor(r53, r38));
        r18.setTextSize(1, 14.0f);
        r18.setGravity(17);
        NotificationCenter.listenEmojiLoading(r18);
        int r66 = R.string.ChannelAffiliateProgramJoinText;
        String r113 = UserObject.getUserName(r11);
        CharSequence r123 = AffiliateProgramFragment.percents(r35.commission_permille);
        int r134 = r35.duration_months;
        if (r134 > 0) goto L15;
        String r135 = LocaleController.getString(R.string.ChannelAffiliateProgramJoinText_Lifetime);
    L13:
        BottomSheet.Builder r222 = r0;
        boolean r153 = false;
    L21:
        r18.setText(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.formatString(r66, new Object[]{r113, r123, r135})), r18.getPaint().getFontMetricsInt(), r153));
        r3.addView(r18, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 22.0f));
        if (r36 < 0) goto L24;
        TextView r02 = new TextView(r33);
        r02.setTextColor(Theme.getColor(r53, r38));
        r02.setTextSize(1, 14.0f);
        r02.setGravity(17);
        r02.setText(LocaleController.getString(R.string.ChannelAffiliateProgramLinkSendTo));
        r3.addView(r02, LayoutHelper.createLinear(-1, -2, 20.0f, 0.0f, 20.0f, 0.0f));
        LinearLayout r03 = new LinearLayout(r33);
        r03.setOrientation(0);
        r03.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), Theme.getColor(r75, r38)));
        r03.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(28.0f), Theme.getColor(r75, r38), Theme.blendOver(Theme.getColor(r75, r38), Theme.getColor(Theme.key_listSelector, r38))));
        BackupImageView r19 = new BackupImageView(r33);
        r19.setRoundRadius(AndroidUtilities.dp(14.0f));
        r03.addView(r19, LayoutHelper.createLinear(28, 28));
        TextView r4 = new TextView(r33);
        r4.setTextSize(1, 13.0f);
        r4.setTextColor(Theme.getColor(r53, r38));
        r03.addView(r4, LayoutHelper.createLinear(-2, -2, 16, 6, 0, 0, 0));
        ImageView r54 = new ImageView(r33);
        r54.setScaleType(r22);
        r54.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r143, r38), r8));
        r54.setImageResource(R.drawable.arrows_select);
        r03.addView(r54, LayoutHelper.createLinear(-2, -2, 16, 2, 0, 5, 0));
        r3.addView(r03, LayoutHelper.createLinear(-2, 28, 1, 0, 11, 0, 20));
        LinearLayout r124 = r03;
        BackupImageView r136 = r19;
        TextView r144 = r4;
    L25:
        ButtonWithCounterView r110 = new ButtonWithCounterView(r33, r38);
        r110.setText(LocaleController.getString(R.string.ChannelAffiliateProgramJoinButton), false);
        r3.addView(r110, LayoutHelper.createLinear(-1, 48));
        LinkSpanDrawable.LinksTextView r04 = new LinkSpanDrawable.LinksTextView(r33, r38);
        r04.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ChannelAffiliateProgramJoinButtonInfo), new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda13(r33)));
        r04.setGravity(17);
        r04.setTextSize(1, 12.0f);
        r04.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r38));
        r04.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r38));
        r3.addView(r04, LayoutHelper.createLinear(-1, -2, 49, 14, 14, 14, 6));
        BottomSheet.Builder r05 = r222;
        r05.setCustomView(r3);
        BottomSheet r23 = r05.create();
        r12.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda14(r23, r35));
        r110.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda15(r110, r25, r34, r35, r23, r36, r39, r33, r38, r11));
        r23.setOnDismissListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda16());
        ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda17 r32 = new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda17(r25, r34, r132, r136, r144);
        r32.run();
        if (r124 == null) goto L28;
        BotStarsController.getInstance(r34).loadAdminedBots();
        BotStarsController.getInstance(r34).loadAdminedChannels();
        LinearLayout r42 = r124;
        Theme.ResourcesProvider r10 = r38;
        r42.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda18(r34, r23, r38, r42, r25, r32));
    L29:
        r122.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda19(r23, r35));
        r23.fixNavigationBar(Theme.getColor(r142, r10));
        r23.show();
        return;
    L28:
        r10 = r38;
        goto L29
    L24:
        r124 = null;
        r136 = null;
        r144 = null;
        goto L25
    L15:
        if (r134 >= 12) goto L17;
    L20:
        r222 = r0;
        r153 = false;
        r135 = LocaleController.formatPluralString("ChannelAffiliateProgramJoinText_Months", r134, new Object[0]);
        goto L21
    L17:
        if ((r134 % 12) != 0) goto L20;
        r135 = LocaleController.formatPluralString("ChannelAffiliateProgramJoinText_Years", r134 / 12, new Object[0]);
        goto L13
    L9:
        r25 = r5;
        r24 = 10.0f;
        goto L10
    L32:
        return;
    }

    public static BottomSheet showShareAffiliateAlert(Context r44, int r45, TL_payments.connectedBotStarRef r46, long r47, Theme.ResourcesProvider r49) {
        if (r46 == null) goto L5;
        if (r44 == null) goto L99;
        BottomSheet.Builder r2 = new BottomSheet.Builder(r44, false, r49);
        TLRPC.User r8 = MessagesController.getInstance(r45).getUser(Long.valueOf(r46.bot_id));
        LinearLayout r9 = new LinearLayout(r44);
        r9.setOrientation(1);
        LinearLayout r17 = null;
        r9.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r9.setClipChildren(false);
        r9.setClipToPadding(false);
        FrameLayout r0 = new FrameLayout(r44);
        View r12 = new View(r44);
        int r14 = AndroidUtilities.dp(40.0f);
        if (r46.revoked == false) goto L9;
        int r15 = Theme.key_color_red;
    L10:
        r12.setBackground(Theme.createCircleDrawable(r14, Theme.getColor(r15, r49)));
        r0.addView(r12, LayoutHelper.createFrame(80, 80.0f, 49, 0.0f, 0.0f, 0.0f, 0.0f));
        ImageView r122 = new ImageView(r44);
        ImageView.ScaleType r142 = ImageView.ScaleType.CENTER;
        r122.setScaleType(r142);
        if (r46.revoked == false) goto L13;
        int r152 = R.drawable.msg_link_2;
    L14:
        r122.setImageResource(r152);
        if (r46.revoked == false) goto L17;
        float r153 = 2.0f;
    L18:
        r122.setScaleX(r153);
        if (r46.revoked == false) goto L21;
        float r154 = 2.0f;
    L22:
        r122.setScaleY(r154);
        r0.addView(r122, LayoutHelper.createFrame(80, 80.0f, 49, 0.0f, 0.0f, 0.0f, 0.0f));
        if (r46.participants <= 0) goto L29;
        FrameLayout r11 = new FrameLayout(r44);
        r11.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(50.0f), Theme.getColor(Theme.key_dialogBackground, r49)));
        r0.addView(r11, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 66.0f, 0.0f, 0.0f));
        TextView r7 = new TextView(r44);
        r7.setTypeface(AndroidUtilities.bold());
        r7.setTextSize(1, 12.0f);
        int r123 = AndroidUtilities.dp(9.5f);
        if (r46.revoked == false) goto L27;
        int r13 = Theme.key_color_red;
    L28:
        r7.setBackground(Theme.createRoundRectDrawable(r123, Theme.getColor(r13, r49)));
        r7.setTextColor(-1);
        r7.setPadding(AndroidUtilities.dp(6.66f), 0, AndroidUtilities.dp(6.66f), 0);
        SpannableStringBuilder r124 = new SpannableStringBuilder();
        r124.append("s ");
        ColoredImageSpan r132 = new ColoredImageSpan(R.drawable.mini_reply_user);
        r132.setScale(0.937f, 0.937f);
        r132.translate(-AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.0f));
        r132.spaceScaleX = 0.8f;
        BottomSheet.Builder r27 = r2;
        r124.setSpan(r132, 0, 1, 33);
        r124.append(String.valueOf(r46.participants));
        r7.setText(r124);
        r7.setGravity(17);
        r11.addView(r7, LayoutHelper.createFrame(-1, 19.0f, 119, 1.33f, 1.33f, 1.33f, 1.33f));
    L30:
        r9.addView(r0, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 0));
        TextView r02 = new TextView(r44);
        int r22 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setTextColor(Theme.getColor(r22, r49));
        r02.setTextSize(1, 20.0f);
        r02.setGravity(17);
        r02.setText(LocaleController.getString(R.string.ChannelAffiliateProgramLinkTitle));
        r02.setTypeface(AndroidUtilities.bold());
        r9.addView(r02, LayoutHelper.createLinear(-1, -2, 20.0f, 16.0f, 20.0f, 9.33f));
        LinearLayout r10 = new LinearLayout(r44);
        r10.setOrientation(0);
        int r5 = AndroidUtilities.dp(28.0f);
        int r6 = Theme.key_windowBackgroundGray;
        r10.setBackground(Theme.createRoundRectDrawable(r5, Theme.getColor(r6, r49)));
        BackupImageView r52 = new BackupImageView(r44);
        r52.setRoundRadius(AndroidUtilities.dp(14.0f));
        AvatarDrawable r112 = new AvatarDrawable();
        r10.addView(r52, LayoutHelper.createLinear(28, 28));
        TextView r133 = new TextView(r44);
        r133.setTextSize(1, 13.0f);
        r133.setTextColor(Theme.getColor(r22, r49));
        r133.setText(DialogObject.getName(r45, r46.bot_id));
        r112.setInfo(r8);
        r52.setForUserOrChat(r8, r112);
        r10.addView(r133, LayoutHelper.createLinear(-2, -2, 16, 6, 0, 0, 0));
        ImageView r53 = new ImageView(r44);
        r53.setScaleType(r142);
        int r82 = Theme.key_dialogTextGray3;
        int r113 = Theme.getColor(r82, r49);
        PorterDuff.Mode r134 = PorterDuff.Mode.SRC_IN;
        r53.setColorFilter(new PorterDuffColorFilter(r113, r134));
        r53.setImageResource(R.drawable.settings_arrow);
        r53.setScaleX(1.2f);
        r53.setScaleY(1.2f);
        r10.addView(r53, LayoutHelper.createLinear(-2, -2, 16, 5, 0, 8, 0));
        r9.addView(r10, LayoutHelper.createLinear(-2, 28, 1, 4, 0, 4, 0));
        ScaleStateListAnimator.apply(r10);
        TextView r54 = new TextView(r44);
        r54.setTextColor(Theme.getColor(r22, r49));
        r54.setTextSize(1, 14.0f);
        r54.setGravity(17);
        if (r46.revoked == false) goto L33;
        r54.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ChannelAffiliateProgramLinkTextRevoked)));
        TLRPC.User r35 = r8;
        LinearLayout r34 = r10;
        PorterDuff.Mode r36 = r134;
    L57:
        r9.addView(r54, LayoutHelper.createLinear(-1, -2, 20.0f, 19.0f, 20.0f, 18.0f));
        if (r46.revoked == true) goto L68;
        TextView r03 = new TextView(r44);
        r03.setTextColor(Theme.getColor(r22, r49));
        r03.setTextSize(1, 14.0f);
        r03.setGravity(17);
        r03.setText(LocaleController.getString(R.string.ChannelAffiliateProgramLinkSendTo));
        r9.addView(r03, LayoutHelper.createLinear(-1, -2, 20.0f, 0.0f, 20.0f, 0.0f));
        LinearLayout r04 = new LinearLayout(r44);
        r04.setOrientation(0);
        r04.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), Theme.getColor(r6, r49)));
        BackupImageView r55 = new BackupImageView(r44);
        r55.setRoundRadius(AndroidUtilities.dp(14.0f));
        AvatarDrawable r72 = new AvatarDrawable();
        r04.addView(r55, LayoutHelper.createLinear(28, 28));
        TextView r102 = new TextView(r44);
        r102.setTextSize(1, 13.0f);
        r102.setTextColor(Theme.getColor(r22, r49));
        if (r47 < 0) goto L62;
        TLRPC.User r114 = MessagesController.getInstance(r45).getUser(Long.valueOf(r47));
        r72.setInfo(r114);
        r55.setForUserOrChat(r114, r72);
        r102.setText(UserObject.getUserName(r114));
        long r125 = r47;
        int r155 = r22;
    L67:
        r04.addView(r102, LayoutHelper.createLinear(-2, -2, 16, 6, 0, 0, 0));
        ImageView r1 = new ImageView(r44);
        r1.setScaleType(r142);
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r82, r49), r36));
        r1.setImageResource(R.drawable.arrows_select);
        r04.addView(r1, LayoutHelper.createLinear(-2, -2, 16, 2, 0, 5, 0));
        r9.addView(r04, LayoutHelper.createLinear(-2, 28, 1, 0, 9, 0, 22));
        r17 = r04;
    L69:
        TextView r05 = new TextView(r44);
        r05.setTextSize(1, 16.0f);
        r05.setGravity(17);
        r05.setTextColor(Theme.getColor(r155, r49));
        r05.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(r6, r49), Theme.blendOver(Theme.getColor(r6, r49), Theme.getColor(Theme.key_listSelector, r49))));
        r05.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(14.66f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(14.66f));
        String r23 = r46.url;
        if (r23 != null) goto L72;
    L74:
        String r24 = r46.url;
    L75:
        r05.setText(r24);
        r9.addView(r05, LayoutHelper.createFrame(-1, -2.0f, 7, 0.0f, 0.0f, 0.0f, 12.0f));
        ButtonWithCounterView r103 = new ButtonWithCounterView(r44, r49);
        if (r46.revoked == true) goto L78;
        SpannableStringBuilder r25 = new SpannableStringBuilder();
        r25.append("c ");
        r25.setSpan(new ColoredImageSpan(R.drawable.msg_copy_filled), 0, 1, 33);
        r25.append(LocaleController.getString(R.string.ChannelAffiliateProgramLinkCopy));
        r103.setText(r25, false);
    L79:
        r9.addView(r103, LayoutHelper.createLinear(-1, 48));
        LinkSpanDrawable.LinksTextView r26 = new LinkSpanDrawable.LinksTextView(r44, r49);
        long r56 = r46.participants;
        if (r56 > 0) goto L82;
        String r57 = LocaleController.formatString(R.string.ChannelAffiliateProgramLinkOpenedNone, new Object[]{UserObject.getUserName(r35)});
    L83:
        r26.setText(r57);
        r26.setGravity(17);
        r26.setTextSize(1, 12.0f);
        r26.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r49));
        r26.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r49));
        r9.addView(r26, LayoutHelper.createLinear(-1, -2, 49, 14, 12, 14, 2));
        BottomSheet.Builder r28 = r27;
        r28.setCustomView(r9);
        BottomSheet r29 = r28.create();
        ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda3 r83 = new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda3(r46, r29, r49, r35);
        if (r46.revoked == true) goto L86;
        r05.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda4(r83));
    L86:
        r103.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda5(r46, r45, r29, r44, r125, r49, r83));
        r29.setOnDismissListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda6());
        if (r17 == null) goto L89;
        BotStarsController.getInstance(r45).loadAdminedBots();
        BotStarsController.getInstance(r45).loadAdminedChannels();
        Theme.ResourcesProvider r3 = r49;
        LinearLayout r4 = r17;
        TL_payments.connectedBotStarRef r16 = r46;
        r4.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda7(r45, r29, r3, r4, r47, r44, r46));
    L90:
        r34.setOnClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda8(r29, r16));
        r29.fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r3));
        BaseFragment r06 = LaunchActivity.getSafeLastFragment();
        if (AndroidUtilities.isTablet() == true) goto L96;
        if (r06 == null) goto L96;
        if (AndroidUtilities.hasDialogOnTop(r06) == true) goto L96;
        r29.makeAttached(r06);
    L96:
        r29.show();
        return r29;
    L89:
        r16 = r46;
        r3 = r49;
        goto L90
    L82:
        r57 = LocaleController.formatPluralString("ChannelAffiliateProgramLinkOpened", (int) r56, new Object[]{UserObject.getUserName(r35)});
        goto L83
    L78:
        r103.setText(LocaleController.getString(R.string.ChannelAffiliateProgramLinkRejoin), false);
        goto L79
    L72:
        if (r23.startsWith("https://") == false) goto L74;
        r24 = r46.url.substring(8);
        goto L75
    L62:
        r125 = r47;
        r155 = r22;
        TLRPC.Chat r18 = MessagesController.getInstance(r45).getChat(Long.valueOf(-r125));
        r72.setInfo(r18);
        r55.setForUserOrChat(r18, r72);
        if (r18 != null) goto L65;
        String r19 = _UrlKt.FRAGMENT_ENCODE_SET;
    L66:
        r102.setText(r19);
        goto L67
    L65:
        r19 = r18.title;
        goto L66
    L68:
        r125 = r47;
        r155 = r22;
        goto L69
    L33:
        int r115 = r46.commission_permille;
        if (r47 >= 0) goto L46;
        int r116 = R.string.ChannelAffiliateProgramLinkTextChannel;
        CharSequence r156 = AffiliateProgramFragment.percents(r115);
        r34 = r10;
        String r104 = UserObject.getUserName(r8);
        r35 = r8;
        int r07 = r46.duration_months;
        if (r07 > 0) goto L38;
        String r08 = LocaleController.getString(R.string.ChannelAffiliateProgramJoinText_Lifetime);
        r36 = r134;
    L45:
        r54.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r116, new Object[]{r156, r104, r08})));
        goto L57
    L38:
        r36 = r134;
        if (r07 < 12) goto L44;
        if ((r07 % 12) != 0) goto L44;
        r08 = LocaleController.formatPluralString("ChannelAffiliateProgramJoinText_Years", r07 / 12, new Object[0]);
    L44:
        r08 = LocaleController.formatPluralString("ChannelAffiliateProgramJoinText_Months", r07, new Object[0]);
        goto L45
    L46:
        r35 = r8;
        r34 = r10;
        r36 = r134;
        int r09 = R.string.ChannelAffiliateProgramLinkTextUser;
        CharSequence r105 = AffiliateProgramFragment.percents(r115);
        String r117 = UserObject.getUserName(r35);
        int r135 = r46.duration_months;
        if (r135 > 0) goto L50;
        String r73 = LocaleController.getString(R.string.ChannelAffiliateProgramJoinText_Lifetime);
    L56:
        r54.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r09, new Object[]{r105, r117, r73})));
        goto L57
    L50:
        if (r135 < 12) goto L55;
        if ((r135 % 12) != 0) goto L55;
        r73 = LocaleController.formatPluralString("ChannelAffiliateProgramJoinText_Years", r135 / 12, new Object[0]);
    L55:
        r73 = LocaleController.formatPluralString("ChannelAffiliateProgramJoinText_Months", r135, new Object[0]);
        goto L56
    L27:
        r13 = Theme.key_color_green;
        goto L28
    L29:
        r27 = r2;
        goto L30
    L21:
        r154 = 1.8f;
        goto L22
    L17:
        r153 = 1.8f;
        goto L18
    L13:
        r152 = R.drawable.msg_limit_links;
        goto L14
    L9:
        r15 = Theme.key_featuredStickers_addButton;
        goto L10
    L99:
        return null;
    L5:
        return null;
    }

    private CharSequence sortText(BotStarsController.ChannelSuggestedBots.Sort r7) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append(LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSort)).append(" ");
        if (r7 != BotStarsController.ChannelSuggestedBots.Sort.BY_PROFITABILITY) goto L6;
        SpannableString r1 = new SpannableString(LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSortProfitability) + RegisterSpec.PREFIX);
    L11:
        ColoredImageSpan r2 = new ColoredImageSpan(R.drawable.arrow_more);
        r2.useLinkPaintColor = true;
        r2.setScale(0.6f, 0.6f);
        r1.setSpan(r2, r1.length() - 1, r1.length(), 33);
        r1.setSpan(new AnonymousClass4(this, r7, BotStarsController.getInstance(this.currentAccount).getChannelSuggestedBots(this.dialogId)), 0, r1.length(), 33);
        r0.append(r1);
    L12:
        return r0;
    L6:
        if (r7 != BotStarsController.ChannelSuggestedBots.Sort.BY_REVENUE) goto L9;
        r1 = new SpannableString(LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSortRevenue) + RegisterSpec.PREFIX);
        goto L11
    L9:
        if (r7 != BotStarsController.ChannelSuggestedBots.Sort.BY_DATE) goto L12;
        r1 = new SpannableString(LocaleController.getString(R.string.ChannelAffiliateProgramProgramsSortDate) + RegisterSpec.PREFIX);
        goto L11
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public RecyclerView.Adapter<?> createAdapter() {
        AnonymousClass3 r0 = new AnonymousClass3(this, this.listView, getContext(), this.currentAccount, this.classGuid, true, new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda2(this), getResourceProvider());
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.ui.GradientHeaderActivity
    public StarParticlesView createParticlesView() {
        return makeParticlesView(getContext(), 75, 1);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r11) {
        this.useFillLastLayoutManager = false;
        this.particlesViewHeight = AndroidUtilities.dp(238.0f);
        AnonymousClass1 r1 = new AnonymousClass1(this, r11);
        this.emptyLayout = r1;
        r1.setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray));
        super.createView(r11);
        this.aboveTitleView = new FrameLayout(r11);
        GLIconTextureView r12 = new GLIconTextureView(r11, 1, 3);
        this.iconTextureView = r12;
        r12.setImportantForAccessibility(4);
        GLIconRenderer r13 = this.iconTextureView.mRenderer;
        r13.colorKey1 = Theme.key_starsGradient1;
        r13.colorKey2 = Theme.key_starsGradient2;
        r13.updateColors();
        this.iconTextureView.setStarParticlesView(this.particlesView);
        this.aboveTitleView.addView(this.iconTextureView, LayoutHelper.createFrame(190, 190.0f, 17, 0.0f, 32.0f, 0.0f, 12.0f));
        configureHeader(LocaleController.getString(R.string.ChannelAffiliateProgramTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.ChannelAffiliateProgramText)), this.aboveTitleView, null);
        this.listView.setOnItemClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda0(this, r11));
        this.listView.setOnItemLongClickListener(new ChannelAffiliateProgramsFragment$$ExternalSyntheticLambda1(this, r11));
        DefaultItemAnimator r112 = new DefaultItemAnimator();
        r112.setSupportsChangeAnimations(false);
        r112.setDelayAnimations(false);
        r112.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r112.setDurations(350);
        this.listView.setItemAnimator(r112);
        this.listView.setOnScrollListener(new AnonymousClass2(this));
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.channelConnectedBotsUpdate) goto L12;
        if (((Long) r6[0]).longValue() != this.dialogId) goto L19;
        UniversalAdapter r42 = this.adapter;
        if (r42 == null) goto L9;
        r42.update(true);
    L9:
        BotStarsController.getInstance(this.currentAccount).getChannelConnectedBots(this.dialogId).load();
        return;
    L19:
        return;
    L12:
        if (r4 == NotificationCenter.channelSuggestedBotsUpdate) goto L14;
        return;
    L14:
        if (((Long) r6[0]).longValue() != this.dialogId) goto L21;
        UniversalAdapter r3 = this.adapter;
        if (r3 == null) goto L22;
        r3.update(true);
        return;
    L22:
        return;
    }

    public void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        if (getContext() != null) goto L5;
        return;
    L5:
        r7.add(UItem.asFullyCustom(getHeader(getContext())));
        r7.add(AffiliateProgramFragment.FeatureCell.Factory.as(R.drawable.menu_feature_reliable, LocaleController.getString(R.string.ChannelAffiliateProgramFeature1Title), LocaleController.getString(R.string.ChannelAffiliateProgramFeature1)));
        r7.add(AffiliateProgramFragment.FeatureCell.Factory.as(R.drawable.menu_feature_transparent, LocaleController.getString(R.string.ChannelAffiliateProgramFeature2Title), LocaleController.getString(R.string.ChannelAffiliateProgramFeature2)));
        r7.add(AffiliateProgramFragment.FeatureCell.Factory.as(R.drawable.menu_feature_simple, LocaleController.getString(R.string.ChannelAffiliateProgramFeature3Title), LocaleController.getString(R.string.ChannelAffiliateProgramFeature3)));
        r7.add(UItem.asShadow(1, null));
        BotStarsController.ChannelConnectedBots r82 = BotStarsController.getInstance(this.currentAccount).getChannelConnectedBots(this.dialogId);
        int r2 = 0;
        if (r82.bots.isEmpty() == true) goto L8;
    L9:
        r7.add(UItem.asHeader(LocaleController.getString(R.string.ChannelAffiliateProgramMyPrograms)));
        int r1 = 0;
    L11:
        if (r1 >= r82.bots.size()) goto L14;
        r7.add(BotCell.Factory.as(r82.bots.get(r1)));
        r1 = r1 + 1;
        goto L11
    L14:
        if (r82.endReached == true) goto L16;
    L17:
        r7.add(UItem.asFlicker(29));
        r7.add(UItem.asFlicker(29));
        r7.add(UItem.asFlicker(29));
    L18:
        r7.add(UItem.asShadow(2, null));
    L19:
        BotStarsController.ChannelSuggestedBots r83 = BotStarsController.getInstance(this.currentAccount).getChannelSuggestedBots(this.dialogId);
        if (r83.bots.isEmpty() == true) goto L22;
    L23:
        r7.add(HeaderSortCell.Factory.as(LocaleController.getString(R.string.ChannelAffiliateProgramPrograms), sortText(r83.getSort())));
    L25:
        if (r2 >= r83.bots.size()) goto L28;
        r7.add(BotCell.Factory.as(r83.bots.get(r2)));
        r2 = r2 + 1;
        goto L25
    L28:
        if (r83.endReached == true) goto L30;
    L31:
        r7.add(UItem.asFlicker(29));
        r7.add(UItem.asFlicker(29));
        r7.add(UItem.asFlicker(29));
    L32:
        r7.add(UItem.asShadow(3, null));
    L33:
        r7.add(UItem.asCustom(this.emptyLayout));
        return;
    L30:
        if (r83.isLoading() == false) goto L32;
    L22:
        if (r83.count <= 0) goto L33;
    L16:
        if (r82.isLoading() == false) goto L18;
    L8:
        if (r82.count <= 0) goto L19;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.channelConnectedBotsUpdate);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.channelSuggestedBotsUpdate);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.channelConnectedBotsUpdate);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.channelSuggestedBotsUpdate);
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(true);
        this.iconTextureView.setDialogVisible(true);
        return;
    }

    @Override // org.telegram.ui.GradientHeaderActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        GLIconTextureView r0 = this.iconTextureView;
        if (r0 == null) goto L6;
        r0.setPaused(false);
        this.iconTextureView.setDialogVisible(false);
        return;
    }
}
