package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.components.IconSelectorAlert;
import com.exteragram.messenger.utils.ui.FolderIcons;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_chatlists;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EditEmojiTextCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.EditTextSuggestionsFix;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.FolderBottomSheet;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.QRCodeBottomSheet;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.PeerColorActivity;

/* loaded from: classes6.dex */
public class FilterCreateActivity extends BaseFragment {
    private ListAdapter adapter;
    private CreateLinkCell createLinkCell;
    private boolean creatingNew;
    private boolean doNotCloseWhenSave;
    private ActionBarMenuItem doneItem;
    private boolean excludeExpanded;
    private MessagesController.DialogFilter filter;
    private HeaderCellColorPreview folderTagsHeader;
    private boolean hasUserChanged;
    private boolean includeExpanded;
    private ArrayList<TL_chatlists.TL_exportedChatlistInvite> invites;
    private ArrayList<ItemInner> items;
    private RecyclerListView listView;
    private boolean loadingInvites;
    private boolean nameChangedManually;
    private EditEmojiTextCell nameEditTextCell;
    private HeaderCellWithRight nameHeaderCell;
    private int nameRow;
    private ArrayList<Long> newAlwaysShow;
    private boolean newFilterAnimations;
    private int newFilterColor;
    private String newFilterEmoticon;
    private int newFilterFlags;
    private CharSequence newFilterName;
    private ArrayList<Long> newNeverShow;
    private LongSparseIntArray newPinned;
    private ArrayList<ItemInner> oldItems;
    private int requestingInvitesReqId;
    private HintView saveHintView;
    float shiftDp;
    private Runnable showBulletinOnResume;
    private boolean showedUpdateBulletin;
    private UndoView undoView;

    /* renamed from: org.telegram.ui.FilterCreateActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ FilterCreateActivity this$0;

        public AnonymousClass1(FilterCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            FilterCreateActivity.m14793$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (FilterCreateActivity.m14790$$Nest$mcheckDiscard(this.this$0, true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.FilterCreateActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ FilterCreateActivity this$0;

        public AnonymousClass2(FilterCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public void addView(View r4) {
            if ((r4 instanceof EmojiView) == false) goto L11;
            ViewGroup.LayoutParams r0 = ((EmojiView) r4).getLayoutParams();
            if (r0 != null) goto L8;
            r0 = new FrameLayout.LayoutParams(-1, -2);
        L8:
            if ((r0 instanceof FrameLayout.LayoutParams) == false) goto L10;
            ((FrameLayout.LayoutParams) r0).gravity = 87;
        L10:
            r4.setLayoutParams(r0);
        L11:
            super.addView(r4);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r1 = getPaddingLeft();
            int r142 = (r14 - r12) - getPaddingRight();
            int r122 = getPaddingTop();
            int r152 = r15 - r13;
            int r132 = r152 - getPaddingBottom();
            int r2 = 0;
        L3:
            if (r2 >= r112) goto L37;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L36;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L9;
            r7 = 51;
        L9:
            int r8 = Gravity.getAbsoluteGravity(r7, getLayoutDirection());
            int r72 = r7 & 112;
            int r82 = r8 & 7;
            if (r82 != 1) goto L12;
            int r83 = ((((r142 - r1) - r5) / 2) + r1) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L15:
            int r84 = r83 - r9;
        L18:
            if (r72 != 16) goto L20;
            int r73 = ((((r132 - r122) - r6) / 2) + r122) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L26:
            int r43 = r73 - r42;
        L30:
            if ((r3 instanceof EmojiView) == true) goto L32;
        L35:
            r3.layout(r84, r43, r5 + r84, r6 + r43);
            goto L36
        L32:
            if (AndroidUtilities.isTablet() == false) goto L34;
            r43 = r152 - r6;
            goto L35
        L34:
            r43 = (r152 + r0) - r6;
            goto L35
        L20:
            if (r72 != 48) goto L22;
            int r44 = r4.topMargin;
        L24:
            r43 = r44 + r122;
            goto L30
        L22:
            if (r72 == 80) goto L25;
            r44 = r4.topMargin;
            goto L24
        L25:
            r73 = r132 - r6;
            r42 = r4.bottomMargin;
            goto L26
        L12:
            if (r82 == 5) goto L14;
            r84 = r4.leftMargin + r1;
            goto L18
        L14:
            r83 = r142 - r5;
            r9 = r4.rightMargin;
        L36:
            r2 = r2 + 1;
            goto L3
        L37:
            super.notifyHeightChanged();
        }
    }

    /* renamed from: org.telegram.ui.FilterCreateActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ FilterCreateActivity this$0;

        public AnonymousClass3(FilterCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r2) {
            if (r2 >= 0) goto L4;
        L7:
            ItemInner r22 = null;
        L8:
            if (r22 == null) goto L14;
            if (ItemInner.m14810$$Nest$fgetisRed(r22) == false) goto L14;
            return Integer.valueOf(Theme.multAlpha(getThemedColor(Theme.key_text_RedRegular), 0.12f));
        L14:
            return Integer.valueOf(getThemedColor(Theme.key_listSelector));
        L4:
            if (r2 >= FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).size()) goto L7;
            r22 = (ItemInner) FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).get(r2);
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean requestFocus(int r1, Rect r2) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.FilterCreateActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ FilterCreateActivity this$0;

        public AnonymousClass4(FilterCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (FilterCreateActivity.m14775$$Nest$fgetlistView(this.this$0).scrollingByUser == true) goto L5;
            return;
        L5:
            if (FilterCreateActivity.m14776$$Nest$fgetnameEditTextCell(this.this$0) != null) goto L7;
            return;
        L7:
            if (FilterCreateActivity.m14776$$Nest$fgetnameEditTextCell(this.this$0).editTextEmoji == null) goto L16;
            boolean r12 = FilterCreateActivity.m14776$$Nest$fgetnameEditTextCell(this.this$0).editTextEmoji.isPopupShowing();
            FilterCreateActivity r0 = this.this$0;
            if (r12 == false) goto L12;
            FilterCreateActivity.m14776$$Nest$fgetnameEditTextCell(r0).editTextEmoji.hidePopup(true);
            return;
        L12:
            FilterCreateActivity.m14776$$Nest$fgetnameEditTextCell(r0).editTextEmoji.closeKeyboard();
            return;
        }
    }

    /* renamed from: org.telegram.ui.FilterCreateActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends HintView {
        final /* synthetic */ FilterCreateActivity this$0;

        public AnonymousClass5(FilterCreateActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            if (r1 == 0) goto L9;
            ((ViewGroup) getParent()).removeView(this);     // Catch: Exception -> L6
            return;
        L10:
            return;
        }
    }

    public static class ButtonCell extends FrameLayout {
        private boolean divider;
        private ImageView imageView;
        private int lastIconResId;
        private TextView textView;
        private Boolean translateText;

        public ButtonCell(Context r13) {
            super(r13);
            this.divider = true;
            this.translateText = null;
            ImageView r1 = new ImageView(r13);
            this.imageView = r1;
            r1.setScaleType(ImageView.ScaleType.CENTER);
            ImageView r12 = this.imageView;
            int r3 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r2 = 5;
        L6:
            addView(r12, LayoutHelper.createFrame(24, 24.0f, r2 | 16, 24.0f, 0.0f, 24.0f, 0.0f));
            TextView r14 = new TextView(r13);
            this.textView = r14;
            r14.setTextSize(1, 16.0f);
            this.textView.setLines(1);
            this.textView.setSingleLine();
            TextView r132 = this.textView;
            boolean r0 = LocaleController.isRTL;
            int r15 = 24;
            if (r0 == false) goto L9;
            int r5 = 24;
        L10:
            if (r0 == false) goto L12;
            r15 = 0;
        L12:
            r132.setPadding(r5, 0, r15, 0);
            TextView r133 = this.textView;
            if (LocaleController.isRTL == false) goto L15;
            r3 = 5;
        L15:
            r133.setGravity(r3);
            TextView r134 = this.textView;
            boolean r02 = LocaleController.isRTL;
            if (r02 == false) goto L18;
            float r6 = 0.0f;
        L19:
            if (r02 == false) goto L21;
            float r8 = 72.0f;
        L22:
            addView(r134, LayoutHelper.createFrame(-1, -2.0f, 23, r6, 0.0f, r8, 0.0f));
            return;
        L21:
            r8 = 0.0f;
            goto L22
        L18:
            r6 = 72.0f;
            goto L19
        L9:
            r5 = 0;
            goto L10
        L5:
            r2 = 3;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.divider == false) goto L6;
            r8.drawRect(this.textView.getLeft(), getMeasuredHeight() - 1, this.textView.getRight(), getMeasuredHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void set(int r8, CharSequence r9, boolean r10) {
            if (LocaleController.isRTL == false) goto L5;
            int r0 = -1;
        L6:
            ImageView r2 = this.imageView;
            boolean r3 = false;
            if (r8 != 0) goto L9;
            r2.setVisibility(8);
        L10:
            boolean r22 = LocaleController.isRTL;
            TextView r4 = this.textView;
            float r5 = 72.0f;
            if (r22 == false) goto L16;
            ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) r4.getLayoutParams();
            if (r8 != 0) goto L15;
            r5 = 24.0f;
        L15:
            r23.rightMargin = AndroidUtilities.dp(r5);
        L20:
            this.textView.setText(r9);
            if (r10 == true) goto L24;
            if (r8 == 0) goto L24;
            r3 = true;
        L24:
            Boolean r92 = this.translateText;
            if (r92 != null) goto L27;
        L28:
            this.translateText = Boolean.valueOf(r3);
            int r93 = this.lastIconResId;
            TextView r24 = this.textView;
            float r42 = 0.0f;
            if (r93 != r8) goto L34;
            r24.clearAnimation();
            ViewPropertyAnimator r94 = this.textView.animate();
            if (r3 == false) goto L33;
            r42 = AndroidUtilities.dp(r0 * (-7));
        L33:
            r94.translationX(r42).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L37:
            this.divider = r10;
            setWillNotDraw(!r10);
            this.lastIconResId = r8;
            return;
        L34:
            if (r3 == false) goto L36;
            r42 = AndroidUtilities.dp(r0 * (-7));
        L36:
            r24.setTranslationX(r42);
            goto L37
        L27:
            if (r92.booleanValue() == r3) goto L37;
        L16:
            ViewGroup.MarginLayoutParams r25 = (ViewGroup.MarginLayoutParams) r4.getLayoutParams();
            if (r8 != 0) goto L19;
            r5 = 24.0f;
        L19:
            r25.leftMargin = AndroidUtilities.dp(r5);
            goto L20
        L9:
            r2.setVisibility(0);
            this.imageView.setImageResource(r8);
            goto L10
        L5:
            r0 = 1;
            goto L6
        }

        public void setRed(boolean r5) {
            ImageView r0 = this.imageView;
            if (r5 == false) goto L5;
            int r2 = Theme.key_text_RedBold;
        L6:
            r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2), PorterDuff.Mode.MULTIPLY));
            TextView r4 = this.textView;
            if (r5 == false) goto L9;
            int r52 = Theme.key_text_RedRegular;
        L10:
            r4.setTextColor(Theme.getColor(r52));
            return;
        L9:
            r52 = Theme.key_windowBackgroundWhiteBlueText4;
            goto L10
        L5:
            r2 = Theme.key_windowBackgroundWhiteBlueText2;
            goto L6
        }
    }

    public static class ColorImageSpan extends ImageSpan {
        int lastColor;

        public ColorImageSpan(Drawable r1, int r2) {
            super(r1, r2);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r5, CharSequence r6, int r7, int r8, float r9, int r10, int r11, int r12, Paint r13) {
            if (r13.getColor() != this.lastColor) goto L5;
        L7:
            super.draw(r5, r6, r7, r8, r9, r10, r11, r12, r13);
            return;
        L5:
            if (getDrawable() == null) goto L7;
            Drawable r0 = getDrawable();
            int r2 = r13.getColor();
            this.lastColor = r2;
            r0.setColorFilter(new PorterDuffColorFilter(r2, PorterDuff.Mode.MULTIPLY));
            goto L7
        }
    }

    public static class CreateLinkCell extends FrameLayout {
        ImageView imageView;
        boolean needDivider;
        TextView textView;

        public CreateLinkCell(Context r18) {
            super(r18);
            TextView r2 = new TextView(r18);
            this.textView = r2;
            r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setText(LocaleController.getString(R.string.CreateNewLink));
            TextView r22 = this.textView;
            int r5 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r3 = 5;
        L6:
            r22.setGravity(r3);
            TextView r23 = this.textView;
            boolean r32 = LocaleController.isRTL;
            if (r32 == false) goto L9;
            int r9 = 16;
        L10:
            if (r32 == false) goto L12;
            int r33 = 0;
        L13:
            r23.setPadding(r9, 0, r33, 0);
            TextView r24 = this.textView;
            boolean r34 = LocaleController.isRTL;
            if (r34 == false) goto L16;
            float r13 = 0.0f;
        L17:
            if (r34 == false) goto L19;
            float r15 = 64.0f;
        L20:
            addView(r24, LayoutHelper.createFrame(-1, -2.0f, 23, r13, 0.0f, r15, 0.0f));
            this.imageView = new ImageView(r18);
            Drawable r25 = r18.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r1 = r18.getResources().getDrawable(R.drawable.poll_add_plus);
            int r8 = Theme.getColor(Theme.key_featuredStickers_addButton);
            PorterDuff.Mode r10 = PorterDuff.Mode.MULTIPLY;
            r25.setColorFilter(new PorterDuffColorFilter(r8, r10));
            r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck), r10));
            this.imageView.setImageDrawable(new CombinedDrawable(r25, r1));
            this.imageView.setScaleType(ImageView.ScaleType.CENTER);
            ImageView r12 = this.imageView;
            boolean r26 = LocaleController.isRTL;
            if (r26 == false) goto L23;
            r5 = 5;
        L23:
            int r122 = r5 | 16;
            if (r26 == false) goto L26;
            float r132 = 0.0f;
        L27:
            if (r26 == false) goto L29;
            float r152 = 16.0f;
        L30:
            addView(r12, LayoutHelper.createFrame(32, 32.0f, r122, r132, 0.0f, r152, 0.0f));
            return;
        L29:
            r152 = 0.0f;
            goto L30
        L26:
            r132 = 16.0f;
            goto L27
        L19:
            r15 = 0.0f;
            goto L20
        L16:
            r13 = 64.0f;
            goto L17
        L12:
            r33 = 16;
            goto L13
        L9:
            r9 = 0;
            goto L10
        L5:
            r3 = 3;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L6;
            r8.drawRect(this.textView.getLeft(), getMeasuredHeight() - 1, this.textView.getRight(), getMeasuredHeight(), Theme.dividerPaint);
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(45.0f), TLObject.FLAG_30));
        }

        public void setDivider(boolean r2) {
            if (this.needDivider == r2) goto L6;
            this.needDivider = r2;
            setWillNotDraw(!r2);
            return;
        }

        @Override // android.view.View
        public void setEnabled(boolean r5) {
            super.setEnabled(r5);
            TextView r0 = this.textView;
            float r1 = 0.5f;
            if (r5 == false) goto L5;
            float r3 = 1.0f;
        L6:
            r0.setAlpha(r3);
            ImageView r4 = this.imageView;
            if (r5 == false) goto L9;
            r1 = 1.0f;
        L9:
            r4.setAlpha(r1);
            return;
        L5:
            r3 = 0.5f;
            goto L6
        }

        public void setText(String r1) {
            this.textView.setText(r1);
        }
    }

    public static class FilterInvitesBottomSheet extends BottomSheetWithRecyclerListView {
        private AdapterWithDiffUtils adapter;
        private FrameLayout bulletinContainer;
        private TextView button;
        private MessagesController.DialogFilter filter;
        private ArrayList<TL_chatlists.TL_exportedChatlistInvite> invites;
        private final ArrayList<ItemInner> items;
        private final ArrayList<ItemInner> oldItems;

        /* renamed from: org.telegram.ui.FilterCreateActivity$FilterInvitesBottomSheet$1, reason: invalid class name */
        public class AnonymousClass1 extends AdapterWithDiffUtils {
            final /* synthetic */ FilterInvitesBottomSheet this$0;

            /* renamed from: org.telegram.ui.FilterCreateActivity$FilterInvitesBottomSheet$1$1, reason: invalid class name and collision with other inner class name */
            public class C00661 extends LinkCell {
                final /* synthetic */ AnonymousClass1 this$1;

                public C00661(AnonymousClass1 r1, Context r2, BaseFragment r3, int r4, int r5) {
                    this.this$1 = r1;
                    super(r2, r3, r4, r5);
                }

                public void copy() {
                    String r0 = this.lastUrl;
                    if (r0 != null) goto L6;
                    return;
                L6:
                    if (AndroidUtilities.addToClipboard(r0) == false) goto L9;
                    BulletinFactory.of(FilterInvitesBottomSheet.m14796$$Nest$fgetbulletinContainer(this.this$1.this$0), null).createCopyLinkBulletin().show();
                    return;
                }

                @Override // org.telegram.ui.FilterCreateActivity.LinkCell
                public void onDelete(TL_chatlists.TL_exportedChatlistInvite r2) {
                    FilterInvitesBottomSheet.m14798$$Nest$fgetinvites(this.this$1.this$0).remove(r2);
                    FilterInvitesBottomSheet.m14800$$Nest$mupdateCreateInviteButton(this.this$1.this$0);
                    FilterInvitesBottomSheet.m14801$$Nest$mupdateRows(this.this$1.this$0, true);
                }

                @Override // org.telegram.ui.FilterCreateActivity.LinkCell
                public void options() {
                    ItemOptions r0 = ItemOptions.makeOptions(this.this$1.this$0.container, this);
                    r0.add(R.drawable.msg_copy, LocaleController.getString(R.string.CopyLink), new FilterCreateActivity$FilterInvitesBottomSheet$1$1$$ExternalSyntheticLambda0(this));
                    r0.add(R.drawable.msg_qrcode, LocaleController.getString(R.string.GetQRCode), new FilterCreateActivity$FilterInvitesBottomSheet$1$1$$ExternalSyntheticLambda1(this));
                    r0.add(R.drawable.msg_delete, LocaleController.getString(R.string.DeleteLink), true, new FilterCreateActivity$FilterInvitesBottomSheet$1$1$$ExternalSyntheticLambda2(this));
                    if (LocaleController.isRTL == false) goto L5;
                    r0.setGravity(3);
                L5:
                    r0.show();
                }
            }

            public AnonymousClass1(FilterInvitesBottomSheet r1) {
                this.this$0 = r1;
            }

            private RecyclerView.Adapter realAdapter() {
                return FilterInvitesBottomSheet.access$800(this.this$0).getAdapter();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return FilterInvitesBottomSheet.m14799$$Nest$fgetitems(this.this$0).size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                return ((ItemInner) FilterInvitesBottomSheet.m14799$$Nest$fgetitems(this.this$0).get(r1)).viewType;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                int r0 = r1.getItemViewType();
                if (r0 != 8) goto L5;
                return true;
            L5:
                if (r0 == 7) goto L11;
                return false;
            L11:
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyDataSetChanged() {
                realAdapter().notifyDataSetChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemChanged(int r1) {
                realAdapter().notifyItemChanged(r1 + 1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemChanged(int r1, Object r2) {
                realAdapter().notifyItemChanged(r1 + 1, r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemInserted(int r1) {
                realAdapter().notifyItemInserted(r1 + 1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemMoved(int r1, int r2) {
                realAdapter().notifyItemMoved(r1 + 1, r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemRangeChanged(int r1, int r2) {
                realAdapter().notifyItemRangeChanged(r1 + 1, r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemRangeChanged(int r1, int r2, Object r3) {
                realAdapter().notifyItemRangeChanged(r1 + 1, r2, r3);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemRangeInserted(int r1, int r2) {
                realAdapter().notifyItemRangeInserted(r1 + 1, r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemRangeRemoved(int r1, int r2) {
                realAdapter().notifyItemRangeRemoved(r1 + 1, r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyItemRemoved(int r1) {
                realAdapter().notifyItemRemoved(r1 + 1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
                int r0 = r6.getItemViewType();
                ItemInner r1 = (ItemInner) FilterInvitesBottomSheet.m14799$$Nest$fgetitems(this.this$0).get(r7);
                boolean r2 = true;
                int r72 = r7 + 1;
                if (r72 < FilterInvitesBottomSheet.m14799$$Nest$fgetitems(this.this$0).size()) goto L5;
            L7:
                r2 = false;
            L9:
                if (r0 != 7) goto L13;
                ((LinkCell) r6.itemView).setInvite(ItemInner.m14811$$Nest$fgetlink(r1), r2);
                return;
            L13:
                if (r0 != 6) goto L15;
            L23:
                TextInfoPrivacyCell r62 = (TextInfoPrivacyCell) r6.itemView;
                if (r0 != 6) goto L27;
                r62.setFixedSize(0);
                r62.setText(ItemInner.m14815$$Nest$fgettext(r1));
                return;
            L27:
                r62.setFixedSize(12);
                r62.setText(_UrlKt.FRAGMENT_ENCODE_SET);
                return;
            L15:
                if (r0 == 3) goto L23;
                if (r0 != 0) goto L20;
                return;
            L20:
                if (r0 != 8) goto L29;
                CreateLinkCell r5 = (CreateLinkCell) r6.itemView;
                r5.setText(LocaleController.getString(R.string.CreateNewInviteLink));
                r5.setDivider(r2);
                return;
            L29:
                return;
            L5:
                if (((ItemInner) FilterInvitesBottomSheet.m14799$$Nest$fgetitems(this.this$0).get(r72)).isShadow() == true) goto L7;
                goto L7
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
                if (r8 != 8) goto L6;
                View r72 = new CreateLinkCell(this.this$0.getContext());
                r72.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
            L16:
                return new RecyclerListView.Holder(r72);
            L6:
                if (r8 != 7) goto L9;
                C00661 r0 = new C00661(this, this.this$0.getContext(), null, FilterInvitesBottomSheet.access$900(this.this$0), FilterInvitesBottomSheet.m14797$$Nest$fgetfilter(this.this$0).id);
                r0.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
                r72 = r0;
                goto L16
            L9:
                if (r8 != 6) goto L11;
            L14:
                r72 = new TextInfoPrivacyCell(this.this$0.getContext());
                r72.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
                goto L16
            L11:
                if (r8 == 3) goto L14;
                FilterInvitesBottomSheet r6 = this.this$0;
                r72 = new HeaderView(r6, r6.getContext());
                goto L16
            }
        }

        public class HeaderView extends FrameLayout {
            private final ImageView closeImageView;
            private final ImageView imageView;
            private final TextView subtitleView;
            final /* synthetic */ FilterInvitesBottomSheet this$0;
            private final SpoilersTextView titleView;

            public static /* synthetic */ void $r8$lambda$lQrSdV3uIc2ygIgVNVQwZe8eMB4(HeaderView r0, View r1) {
                r0.lambda$new$0(r1);
            }

            public HeaderView(FilterInvitesBottomSheet r13, Context r14) {
                this.this$0 = r13;
                super(r14);
                ImageView r0 = new ImageView(r14);
                this.imageView = r0;
                ImageView.ScaleType r1 = ImageView.ScaleType.CENTER;
                r0.setScaleType(r1);
                r0.setImageResource(R.drawable.msg_limit_links);
                r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                r0.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), Theme.getColor(Theme.key_featuredStickers_addButton)));
                addView(r0, LayoutHelper.createFrame(54, 44.0f, 49, 0.0f, 22.0f, 0.0f, 0.0f));
                SpoilersTextView r02 = new SpoilersTextView(r14);
                this.titleView = r02;
                r02.setTypeface(AndroidUtilities.bold());
                r02.setTextSize(1, 20.0f);
                int r2 = Theme.key_dialogTextBlack;
                r02.setTextColor(Theme.getColor(r2));
                r02.setGravity(1);
                r02.setText(r13.getTitle(r02));
                if (FilterInvitesBottomSheet.m14797$$Nest$fgetfilter(r13) != null) goto L5;
            L7:
                int r4 = 0;
            L8:
                r02.cacheType = r4;
                addView(r02, LayoutHelper.createFrame(-2, -2.0f, 49, 20.0f, 84.0f, 20.0f, 0.0f));
                TextView r03 = new TextView(r14);
                this.subtitleView = r03;
                if (FilterInvitesBottomSheet.m14798$$Nest$fgetinvites(r13).isEmpty() == false) goto L11;
                String r132 = LocaleController.getString(R.string.FolderLinkShareSubtitleEmpty);
            L12:
                r03.setText(r132);
                r03.setLines(2);
                r03.setGravity(1);
                r03.setTextSize(1, 14.0f);
                r03.setTextColor(Theme.getColor(r2));
                addView(r03, LayoutHelper.createFrame(-2, -2.0f, 49, 30.0f, 117.0f, 30.0f, 0.0f));
                ImageView r133 = new ImageView(r14);
                this.closeImageView = r133;
                r133.setScaleType(r1);
                r133.setImageResource(R.drawable.msg_close);
                r133.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText5), PorterDuff.Mode.MULTIPLY));
                r133.setOnClickListener(new FilterCreateActivity$FilterInvitesBottomSheet$HeaderView$$ExternalSyntheticLambda0(this));
                addView(r133, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, -4.0f, 2.0f, 0.0f));
                return;
            L11:
                r132 = LocaleController.getString(R.string.FolderLinkShareSubtitle);
                goto L12
            L5:
                if (FilterInvitesBottomSheet.m14797$$Nest$fgetfilter(r13).title_noanimate == false) goto L7;
                r4 = 26;
                goto L8
            }

            private /* synthetic */ void lambda$new$0(View r1) {
                this.this$0.dismiss();
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(171.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: $r8$lambda$FOilqV-sWxRGlG5JlgpOd9Y8cVU, reason: not valid java name */
        public static /* synthetic */ void m14794$r8$lambda$FOilqVsWxRGlG5JlgpOd9Y8cVU(FilterInvitesBottomSheet r0, View r1, int r2) {
            r0.lambda$onViewCreated$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$O7PVyJOLzdWToGz8Kvo97Tb9ASk(FilterInvitesBottomSheet r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$PtxB57vVtG7SpSeyo74XZjhcgzQ(BaseFragment r2, TLObject r3, MessagesController.DialogFilter r4, TLRPC.TL_error r5, Runnable r6, long r7) {
            if (r2 != null) goto L4;
            return;
        L4:
            if (r2.getContext() != null) goto L7;
            return;
        L7:
            if ((r3 instanceof TL_chatlists.TL_chatlists_exportedInvites) == false) goto L9;
            TL_chatlists.TL_chatlists_exportedInvites r32 = (TL_chatlists.TL_chatlists_exportedInvites) r3;
            r2.getMessagesController().putChats(r32.chats, false);
            r2.getMessagesController().putUsers(r32.users, false);
            new FilterInvitesBottomSheet(r2, r4, r32.invites).show();
        L16:
            if (r6 == null) goto L20;
            AndroidUtilities.runOnUIThread(r6, Math.max(0, 200 - (System.currentTimeMillis() - r7)));
            return;
        L20:
            return;
        L9:
            if (r5 != null) goto L11;
        L15:
            BulletinFactory.of(r2).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            goto L16
        L11:
            if ("FILTER_ID_INVALID".equals(r5.text) == false) goto L15;
            if (r4.isDefault() == true) goto L15;
            new FilterInvitesBottomSheet(r2, r4, null).show();
            goto L16
        }

        public static /* synthetic */ void $r8$lambda$iUBLOzQJb9_5WX6WsFGsm3MDFvg(FilterInvitesBottomSheet r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$createLink$4(r1, r2);
        }

        /* renamed from: $r8$lambda$jggyXHF-HSNL21ig8jYS6Bxm8Xw, reason: not valid java name */
        public static /* synthetic */ void m14795$r8$lambda$jggyXHFHSNL21ig8jYS6Bxm8Xw(FilterInvitesBottomSheet r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$createLink$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$xTpNyZuWJQLrAhKG15CixJcejUg(BaseFragment r8, MessagesController.DialogFilter r9, Runnable r10, long r11, TLObject r13, TLRPC.TL_error r14) {
            AndroidUtilities.runOnUIThread(new FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda1(r8, r13, r9, r14, r10, r11));
        }

        /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m14796$$Nest$fgetbulletinContainer(FilterInvitesBottomSheet r0) {
            return r0.bulletinContainer;
        }

        /* renamed from: -$$Nest$fgetfilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MessagesController.DialogFilter m14797$$Nest$fgetfilter(FilterInvitesBottomSheet r0) {
            return r0.filter;
        }

        /* renamed from: -$$Nest$fgetinvites, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m14798$$Nest$fgetinvites(FilterInvitesBottomSheet r0) {
            return r0.invites;
        }

        /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m14799$$Nest$fgetitems(FilterInvitesBottomSheet r0) {
            return r0.items;
        }

        /* renamed from: -$$Nest$mupdateCreateInviteButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14800$$Nest$mupdateCreateInviteButton(FilterInvitesBottomSheet r0) {
            r0.updateCreateInviteButton();
        }

        /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14801$$Nest$mupdateRows(FilterInvitesBottomSheet r0, boolean r1) {
            r0.updateRows(r1);
        }

        public FilterInvitesBottomSheet(BaseFragment r8, MessagesController.DialogFilter r9, ArrayList<TL_chatlists.TL_exportedChatlistInvite> r10) {
            super(r8, false, false);
            this.invites = new ArrayList();
            this.oldItems = new ArrayList();
            this.items = new ArrayList();
            this.filter = r9;
            if (r10 == null) goto L5;
            this.invites.addAll(r10);
        L5:
            updateRows(false);
            this.actionBar.setTitle(getTitle());
            fixNavigationBar(Theme.getColor(Theme.key_dialogBackground));
            TextView r82 = new TextView(getContext());
            this.button = r82;
            r82.setTextSize(1, 14.0f);
            this.button.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.button.setTypeface(AndroidUtilities.bold());
            this.button.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
            this.button.setText(LocaleController.getString(R.string.FolderLinkShareButton));
            this.button.setGravity(17);
            this.button.setOnClickListener(new FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda3(this));
            FrameLayout.LayoutParams r83 = LayoutHelper.createFrame(-1, 48.0f, 87, 16.0f, 10.0f, 16.0f, 10.0f);
            int r92 = r83.leftMargin;
            int r102 = this.backgroundPaddingLeft;
            r83.leftMargin = r92 + r102;
            r83.rightMargin += r102;
            this.containerView.addView(this.button, r83);
            FrameLayout r84 = new FrameLayout(getContext());
            this.bulletinContainer = r84;
            this.containerView.addView(r84, LayoutHelper.createFrame(-1, 100.0f, 80, 6.0f, 0.0f, 6.0f, 0.0f));
            updateCreateInviteButton();
        }

        public static /* synthetic */ RecyclerListView access$800(FilterInvitesBottomSheet r0) {
            return r0.recyclerListView;
        }

        public static /* synthetic */ int access$900(FilterInvitesBottomSheet r0) {
            return r0.currentAccount;
        }

        private void createLink() {
            ArrayList<TLRPC.InputPeer> r0 = new ArrayList();
            int r1 = 0;
        L4:
            if (r1 >= this.filter.alwaysShow.size()) goto L12;
            long r2 = this.filter.alwaysShow.get(r1).longValue();
            if (r2 >= 0) goto L10;
            if (FilterCreateActivity.canAddToFolder(getBaseFragment().getMessagesController().getChat(Long.valueOf(-r2))) == false) goto L10;
            r0.add(getBaseFragment().getMessagesController().getInputPeer(r2));
        L10:
            r1 = r1 + 1;
            goto L4
        L12:
            if (r0.isEmpty() == false) goto L15;
            dismiss();
            getBaseFragment().presentFragment(new FilterChatlistActivity(this.filter, null));
            return;
        L15:
            TL_chatlists.TL_chatlists_exportChatlistInvite r12 = new TL_chatlists.TL_chatlists_exportChatlistInvite();
            TL_chatlists.TL_inputChatlistDialogFilter r22 = new TL_chatlists.TL_inputChatlistDialogFilter();
            r12.chatlist = r22;
            r22.filter_id = this.filter.id;
            r12.peers = r0;
            r12.title = _UrlKt.FRAGMENT_ENCODE_SET;
            getBaseFragment().getConnectionsManager().sendRequest(r12, new FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda4(this));
        }

        private /* synthetic */ void lambda$createLink$3(TLRPC.TL_error r4, TLObject r5) {
            if (FilterCreateActivity.processErrors(r4, getBaseFragment(), BulletinFactory.of(this.bulletinContainer, null)) == true) goto L5;
            return;
        L5:
            if ((r5 instanceof TL_chatlists.TL_chatlists_exportedChatlistInvite) == false) goto L9;
            FilterCreateActivity.hideNew(0);
            dismiss();
            getBaseFragment().getMessagesController().loadRemoteFilters(true);
            getBaseFragment().presentFragment(new FilterChatlistActivity(this.filter, ((TL_chatlists.TL_chatlists_exportedChatlistInvite) r5).invite));
            return;
        }

        private /* synthetic */ void lambda$createLink$4(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda5(this, r3, r2));
        }

        private /* synthetic */ void lambda$new$2(View r1) {
            createLink();
        }

        private /* synthetic */ void lambda$onViewCreated$5(View r2, int r3) {
            int r32 = r3 - 1;
            if (r32 >= 0) goto L5;
            return;
        L5:
            if (r32 >= this.items.size()) goto L17;
            ItemInner r22 = this.items.get(r32);
            int r33 = r22.viewType;
            if (r33 != 7) goto L12;
            dismiss();
            getBaseFragment().presentFragment(new FilterChatlistActivity(this.filter, ItemInner.m14811$$Nest$fgetlink(r22)));
            return;
        L12:
            if (r33 != 8) goto L16;
            createLink();
            return;
        L16:
            return;
        }

        public static void show(BaseFragment r8, MessagesController.DialogFilter r9, Runnable r10) {
            long r4 = System.currentTimeMillis();
            TL_chatlists.TL_chatlists_getExportedInvites r6 = new TL_chatlists.TL_chatlists_getExportedInvites();
            TL_chatlists.TL_inputChatlistDialogFilter r0 = new TL_chatlists.TL_inputChatlistDialogFilter();
            r6.chatlist = r0;
            r0.filter_id = r9.id;
            r8.getConnectionsManager().sendRequest(r6, new FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda0(r8, r9, r10, r4));
        }

        private void updateCreateInviteButton() {
            TextView r0 = this.button;
            if (this.invites.isEmpty() == false) goto L5;
            int r1 = 0;
        L6:
            r0.setVisibility(r1);
            RecyclerListView r02 = this.recyclerListView;
            int r3 = AndroidUtilities.dp(6.0f);
            int r12 = AndroidUtilities.dp(6.0f);
            if (this.invites.isEmpty() == false) goto L9;
            int r4 = AndroidUtilities.dp(68.0f);
        L10:
            r02.setPadding(r3, 0, r12, r4);
            return;
        L9:
            r4 = 0;
            goto L10
        L5:
            r1 = 8;
            goto L6
        }

        private void updateRows(boolean r4) {
            this.oldItems.clear();
            this.oldItems.addAll(this.items);
            this.items.clear();
            this.items.add(ItemInner.asHeader(null));
            if (this.invites.isEmpty() == true) goto L8;
            this.items.add(ItemInner.asShadow(null));
            this.items.add(ItemInner.asCreateLink());
            int r0 = 0;
        L6:
            if (r0 >= this.invites.size()) goto L8;
            this.items.add(ItemInner.asLink(this.invites.get(r0)));
            r0 = r0 + 1;
        L8:
            AdapterWithDiffUtils r02 = this.adapter;
            if (r02 == null) goto L16;
            if (r4 == false) goto L13;
            r02.setItems(this.oldItems, this.items);
            return;
        L13:
            notifyDataSetChanged();
            return;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
            AnonymousClass1 r12 = new AnonymousClass1(this);
            this.adapter = r12;
            return r12;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            return getTitle(null);
        }

        public CharSequence getTitle(TextView r3) {
            if (this.filter == null) goto L8;
            if (r3 != null) goto L6;
            Paint.FontMetricsInt r32 = null;
        L7:
            Object r2 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(new SpannableStringBuilder(this.filter.name), r32, false), this.filter.entities, r32);
        L10:
            return LocaleController.formatSpannable(R.string.FolderLinkShareTitle2, new Object[]{r2});
        L6:
            r32 = r3.getPaint().getFontMetricsInt();
            goto L7
        L8:
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L10
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public void onViewCreated(FrameLayout r3) {
            super.onViewCreated(r3);
            this.recyclerListView.setOverScrollMode(2);
            this.recyclerListView.setOnItemClickListener(new FilterCreateActivity$FilterInvitesBottomSheet$$ExternalSyntheticLambda2(this));
            DefaultItemAnimator r32 = new DefaultItemAnimator();
            r32.setSupportsChangeAnimations(false);
            r32.setDelayAnimations(false);
            r32.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r32.setDurations(350);
            this.recyclerListView.setItemAnimator(r32);
        }
    }

    public class HeaderCellColorPreview extends HeaderCell {
        private final AnimatedColor animatedColor;
        private int currentColor;
        public final TextView noTag;
        private boolean noTagShown;
        public final AnimatedTextView previewView;
        final /* synthetic */ FilterCreateActivity this$0;

        /* renamed from: org.telegram.ui.FilterCreateActivity$HeaderCellColorPreview$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView {
            private final Paint backgroundPaint;
            final /* synthetic */ HeaderCellColorPreview this$1;
            final /* synthetic */ FilterCreateActivity val$this$0;

            public AnonymousClass1(HeaderCellColorPreview r1, Context r2, boolean r3, boolean r4, boolean r5, FilterCreateActivity r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
                this.backgroundPaint = new Paint(1);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r8) {
                int r0 = HeaderCellColorPreview.m14802$$Nest$fgetanimatedColor(this.this$1).set(HeaderCellColorPreview.m14803$$Nest$fgetcurrentColor(this.this$1));
                setTextColor(r0);
                Paint r1 = this.backgroundPaint;
                if (Theme.isCurrentThemeDark() == false) goto L5;
                float r2 = 0.2f;
            L6:
                r1.setColor(Theme.multAlpha(r0, r2));
                RectF r02 = AndroidUtilities.rectTmp;
                r02.set((getWidth() - getDrawable().getCurrentWidth()) - AndroidUtilities.dpf2(9.32f), (getHeight() - AndroidUtilities.dpf2(14.66f)) / 2.0f, getWidth(), (getHeight() + AndroidUtilities.dpf2(14.66f)) / 2.0f);
                r8.drawRoundRect(r02, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.backgroundPaint);
                super.dispatchDraw(r8);
                return;
            L5:
                r2 = 0.1f;
                goto L6
            }
        }

        /* renamed from: -$$Nest$fgetanimatedColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedColor m14802$$Nest$fgetanimatedColor(HeaderCellColorPreview r0) {
            return r0.animatedColor;
        }

        /* renamed from: -$$Nest$fgetcurrentColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14803$$Nest$fgetcurrentColor(HeaderCellColorPreview r0) {
            return r0.currentColor;
        }

        public HeaderCellColorPreview(FilterCreateActivity r18, Context r19) {
            this.this$0 = r18;
            super(r19, Theme.key_windowBackgroundWhiteBlueHeader, 22, 15, false, FilterCreateActivity.access$1000(r18));
            TextView r1 = new TextView(getContext());
            this.noTag = r1;
            r1.setTextSize(1, 14.0f);
            r1.setTextColor(r18.getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
            if (r18.getUserConfig().isPremium() == false) goto L5;
            int r2 = R.string.FolderTagNoColor;
        L6:
            r1.setText(LocaleController.getString(r2));
            int r8 = 5;
            r1.setGravity(5);
            if (LocaleController.isRTL == false) goto L9;
            int r22 = 3;
        L10:
            int r12 = r22 | 48;
            int r23 = this.padding;
            addView(r1, LayoutHelper.createFrame(-1, -1.0f, r12, r23, 16.66f, r23, this.bottomMargin));
            r1.setAlpha(0.0f);
            AnonymousClass1 r11 = new AnonymousClass1(this, getContext(), false, true, true, r18);
            this.previewView = r11;
            this.animatedColor = new AnimatedColor(r11, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            r11.setTextSize(AndroidUtilities.dp(10.0f));
            r11.setTypeface(AndroidUtilities.bold());
            r11.setGravity(5);
            r11.setPadding(AndroidUtilities.dp(4.66f), 0, AndroidUtilities.dp(4.66f), 0);
            if (LocaleController.isRTL == false) goto L13;
            r8 = 3;
        L13:
            int r13 = this.padding;
            addView(r11, LayoutHelper.createFrame(-1, -1.0f, r8 | 48, r13, 16.66f, r13, this.bottomMargin));
            return;
        L9:
            r22 = 5;
            goto L10
        L5:
            r2 = R.string.FolderTagNoColorPremium;
            goto L6
        }

        public TextPaint getPreviewTextPaint() {
            return this.previewView.getPaint();
        }

        public void setPreviewColor(int r6, boolean r7) {
            TextView r0 = this.noTag;
            if (this.this$0.getUserConfig().isPremium() == false) goto L5;
            int r1 = R.string.FolderTagNoColor;
        L6:
            r0.setText(LocaleController.getString(r1));
            int r02 = 0;
            if (r6 >= 0) goto L9;
            boolean r2 = true;
        L10:
            if (r2 == true) goto L13;
            FilterCreateActivity r03 = this.this$0;
            int[] r3 = Theme.keys_avatar_nameInMessage;
            r02 = r03.getThemedColor(r3[r6 % r3.length]);
        L13:
            this.currentColor = r02;
            if (r2 == true) goto L16;
            this.previewView.setEmojiColor(r02);
        L16:
            if (r7 == true) goto L19;
            this.animatedColor.set(this.currentColor, true);
        L19:
            if (r2 == this.noTagShown) goto L30;
            this.noTagShown = r2;
            ViewPropertyAnimator r62 = this.noTag.animate();
            float r72 = 0.0f;
            if (r2 == false) goto L23;
            float r12 = 1.0f;
        L24:
            ViewPropertyAnimator r63 = r62.alpha(r12).setDuration(320);
            CubicBezierInterpolator r13 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r63.setInterpolator(r13).start();
            ViewPropertyAnimator r5 = this.previewView.animate();
            if (r2 == true) goto L28;
            r72 = 1.0f;
        L28:
            r5.alpha(r72).setDuration(320).setInterpolator(r13).start();
            return;
        L23:
            r12 = 0.0f;
            goto L24
        L30:
            return;
        L9:
            r2 = false;
            goto L10
        L5:
            r1 = R.string.FolderTagNoColorPremium;
            goto L6
        }

        public void setPreviewText(CharSequence r4, boolean r5) {
            if (r4 != null) goto L4;
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        L4:
            boolean r2 = false;
            if (r4.length() <= 12) goto L7;
            r4 = r4.subSequence(0, 12);
        L7:
            AnimatedTextView r3 = this.previewView;
            CharSequence r42 = Emoji.replaceEmoji(r4, r3.getPaint().getFontMetricsInt(), false);
            if (r5 == true) goto L10;
        L12:
            r3.setText(r42, r2);
            return;
        L10:
            if (LocaleController.isRTL == true) goto L12;
            r2 = true;
            goto L12
        }
    }

    public class HeaderCellWithRight extends HeaderCell {
        private final AnimatedTextView rightTextView;
        final /* synthetic */ FilterCreateActivity this$0;

        /* renamed from: org.telegram.ui.FilterCreateActivity$HeaderCellWithRight$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView {
            final /* synthetic */ HeaderCellWithRight this$1;
            final /* synthetic */ FilterCreateActivity val$this$0;

            public AnonymousClass1(HeaderCellWithRight r1, Context r2, boolean r3, boolean r4, boolean r5, FilterCreateActivity r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
            public void onMeasure(int r1, int r2) {
                super.onMeasure(r1, r2);
                setPivotX(getMeasuredWidth());
            }
        }

        /* renamed from: -$$Nest$fgetrightTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedTextView m14804$$Nest$fgetrightTextView(HeaderCellWithRight r0) {
            return r0.rightTextView;
        }

        public HeaderCellWithRight(FilterCreateActivity r10, Context r11, Theme.ResourcesProvider r12) {
            this.this$0 = r10;
            super(r11);
            AnonymousClass1 r0 = new AnonymousClass1(this, r11, true, true, true, r10);
            this.rightTextView = r0;
            int r102 = 5;
            if (LocaleController.isRTL == false) goto L5;
            int r9 = 3;
        L6:
            r0.setGravity(r9);
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r12));
            r0.setTextSize(AndroidUtilities.dpf2(15.0f));
            if (LocaleController.isRTL == false) goto L9;
            r102 = 3;
        L9:
            addView(r0, LayoutHelper.createFrame(-1, 18.0f, r102 | 48, 22.0f, 17.0f, 22.0f, 0.0f));
            ScaleStateListAnimator.apply(r0, 0.04f, 1.2f);
            return;
        L5:
            r9 = 5;
            goto L6
        }
    }

    public static class HintInnerCell extends FrameLayout {
        private RLottieImageView imageView;

        public static /* synthetic */ void $r8$lambda$cLgEa38qcIqwuA1amEZIn2m7VRw(HintInnerCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public HintInnerCell(Context r8) {
            super(r8);
            RLottieImageView r0 = new RLottieImageView(r8);
            this.imageView = r0;
            r0.setAnimation(R.raw.filter_new, 100, 100);
            this.imageView.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.playAnimation();
            addView(this.imageView, LayoutHelper.createFrame(100, 100.0f, 17, 0.0f, 0.0f, 0.0f, 0.0f));
            this.imageView.setOnClickListener(new FilterCreateActivity$HintInnerCell$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            if (this.imageView.isPlaying() == true) goto L6;
            this.imageView.setProgress(0.0f);
            this.imageView.playAnimation();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(156.0f), TLObject.FLAG_30));
        }
    }

    public static class ItemInner extends AdapterWithDiffUtils.Item {
        private String chatType;
        private long did;
        private int flags;
        private int iconResId;
        private boolean include;
        private boolean isRed;
        private TL_chatlists.TL_exportedChatlistInvite link;
        private boolean newSpan;
        private View.OnClickListener onClickListener;
        private CharSequence subtext;
        private CharSequence text;

        /* renamed from: -$$Nest$fgetchatType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m14805$$Nest$fgetchatType(ItemInner r0) {
            return r0.chatType;
        }

        /* renamed from: -$$Nest$fgetdid, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m14806$$Nest$fgetdid(ItemInner r2) {
            return r2.did;
        }

        /* renamed from: -$$Nest$fgetflags, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14807$$Nest$fgetflags(ItemInner r0) {
            return r0.flags;
        }

        /* renamed from: -$$Nest$fgeticonResId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14808$$Nest$fgeticonResId(ItemInner r0) {
            return r0.iconResId;
        }

        /* renamed from: -$$Nest$fgetinclude, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14809$$Nest$fgetinclude(ItemInner r0) {
            return r0.include;
        }

        /* renamed from: -$$Nest$fgetisRed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14810$$Nest$fgetisRed(ItemInner r0) {
            return r0.isRed;
        }

        /* renamed from: -$$Nest$fgetlink, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_chatlists.TL_exportedChatlistInvite m14811$$Nest$fgetlink(ItemInner r0) {
            return r0.link;
        }

        /* renamed from: -$$Nest$fgetnewSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m14812$$Nest$fgetnewSpan(ItemInner r0) {
            return r0.newSpan;
        }

        /* renamed from: -$$Nest$fgetonClickListener, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View.OnClickListener m14813$$Nest$fgetonClickListener(ItemInner r0) {
            return r0.onClickListener;
        }

        /* renamed from: -$$Nest$fgetsubtext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CharSequence m14814$$Nest$fgetsubtext(ItemInner r0) {
            return r0.subtext;
        }

        /* renamed from: -$$Nest$fgettext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CharSequence m14815$$Nest$fgettext(ItemInner r0) {
            return r0.text;
        }

        public ItemInner(int r1, boolean r2) {
            super(r1, r2);
        }

        public static ItemInner asAnimatedHeader(CharSequence r3, CharSequence r4, View.OnClickListener r5) {
            ItemInner r0 = new ItemInner(11, false);
            r0.text = r3;
            r0.subtext = r4;
            r0.onClickListener = r5;
            return r0;
        }

        public static ItemInner asButton(int r3, CharSequence r4, boolean r5) {
            ItemInner r0 = new ItemInner(4, false);
            r0.iconResId = r3;
            r0.text = r4;
            r0.isRed = r5;
            return r0;
        }

        public static ItemInner asChat(boolean r3, long r4) {
            ItemInner r0 = new ItemInner(1, false);
            r0.include = r3;
            r0.did = r4;
            return r0;
        }

        public static ItemInner asChat(boolean r3, CharSequence r4, String r5, int r6) {
            ItemInner r0 = new ItemInner(1, false);
            r0.include = r3;
            r0.text = r4;
            r0.chatType = r5;
            r0.flags = r6;
            return r0;
        }

        public static ItemInner asCreateLink() {
            return new ItemInner(8, false);
        }

        public static ItemInner asEdit() {
            return new ItemInner(2, false);
        }

        public static ItemInner asHeader(CharSequence r2) {
            ItemInner r0 = new ItemInner(0, false);
            r0.text = r2;
            return r0;
        }

        public static ItemInner asHeader(CharSequence r2, boolean r3) {
            ItemInner r0 = new ItemInner(0, false);
            r0.text = r2;
            r0.newSpan = r3;
            return r0;
        }

        public static ItemInner asLink(TL_chatlists.TL_exportedChatlistInvite r3) {
            ItemInner r0 = new ItemInner(7, false);
            r0.link = r3;
            return r0;
        }

        public static ItemInner asShadow(CharSequence r3) {
            if (TextUtils.isEmpty(r3) == false) goto L5;
            int r1 = 3;
        L6:
            ItemInner r0 = new ItemInner(r1, false);
            r0.text = r3;
            return r0;
        L5:
            r1 = 6;
            goto L6
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L60:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L60;
            ItemInner r72 = (ItemInner) r7;
            int r2 = this.viewType;
            if (r2 == r72.viewType) goto L14;
            return false;
        L14:
            if (r2 == 11) goto L16;
            if (r2 == 0) goto L28;
            if (r2 == 1) goto L28;
            if (r2 == 3) goto L28;
            if (r2 == 4) goto L28;
        L30:
            int r22 = this.viewType;
            if (r22 == 0) goto L33;
            if (r22 != 1) goto L46;
            if (this.did == r72.did) goto L40;
        L44:
            return false;
        L40:
            if (TextUtils.equals(this.chatType, r72.chatType) == false) goto L44;
            if (this.flags != r72.flags) goto L44;
            return true;
        L46:
            if (r22 != 7) goto L59;
            TL_chatlists.TL_exportedChatlistInvite r23 = this.link;
            TL_chatlists.TL_exportedChatlistInvite r3 = r72.link;
            if (r23 == r3) goto L59;
            if (TextUtils.equals(r23.url, r3.url) == false) goto L58;
            TL_chatlists.TL_exportedChatlistInvite r24 = this.link;
            boolean r32 = r24.revoked;
            TL_chatlists.TL_exportedChatlistInvite r4 = r72.link;
            if (r32 != r4.revoked) goto L58;
            if (TextUtils.equals(r24.title, r4.title) == false) goto L58;
            if (this.link.peers.size() == r72.link.peers.size()) goto L59;
        L58:
            return false;
        L59:
            return true;
        L33:
            if (this.newSpan != r72.newSpan) goto L35;
            return true;
        L35:
            return false;
        L28:
            if (TextUtils.equals(this.text, r72.text) == true) goto L30;
            return false;
        L16:
            if (TextUtils.equals(this.text, r72.text) == true) goto L18;
        L20:
            return false;
        L18:
            if (TextUtils.equals(this.subtext, r72.subtext) == false) goto L20;
            return true;
        }

        public boolean isShadow() {
            int r1 = this.viewType;
            if (r1 != 3) goto L5;
            return true;
        L5:
            if (r1 == 6) goto L11;
            return false;
        L11:
            return true;
        }

        public ItemInner whenClicked(View.OnClickListener r1) {
            this.onClickListener = r1;
            return this;
        }
    }

    public static class LinkCell extends FrameLayout {
        private int currentAccount;
        private int filterId;
        private BaseFragment fragment;
        private TL_chatlists.TL_exportedChatlistInvite lastInvite;
        private boolean lastRevoked;
        protected String lastUrl;
        Drawable linkIcon;
        boolean needDivider;
        ImageView optionsIcon;
        Paint paint;
        float revokeT;
        Drawable revokedLinkIcon;
        Paint revokedPaint;
        AnimatedTextView subtitleTextView;
        AnimatedTextView titleTextView;
        private ValueAnimator valueAnimator;

        /* renamed from: org.telegram.ui.FilterCreateActivity$LinkCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ LinkCell this$0;
            final /* synthetic */ boolean val$value;

            public AnonymousClass1(LinkCell r1, boolean r2) {
                this.this$0 = r1;
                this.val$value = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                LinkCell r12 = this.this$0;
                if (this.val$value == false) goto L5;
                float r0 = 1.0f;
            L6:
                r12.revokeT = r0;
                r12.invalidate();
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public static /* synthetic */ void $r8$lambda$0xrEOk9ZI70zMybF01porQSA7AA(LinkCell r0) {
            r0.lambda$deleteLink$4();
        }

        public static /* synthetic */ void $r8$lambda$CWpk8voDAjZsLq7r4_U71MR24Mg(LinkCell r0, ValueAnimator r1) {
            r0.lambda$setRevoked$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$KrGdEq_RxbevANF8oVn8Dl46YdU(LinkCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$QjsyL2O0SICtVWSTKcd0Tj-2hs4, reason: not valid java name */
        public static /* synthetic */ void m14816$r8$lambda$QjsyL2O0SICtVWSTKcd0Tj2hs4(LinkCell r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$deleteLink$6(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$o7QJ-afvF1jnbG30D0z_OldOYso, reason: not valid java name */
        public static /* synthetic */ void m14817$r8$lambda$o7QJafvF1jnbG30D0z_OldOYso(LinkCell r0, TLRPC.TL_error r1, Runnable r2) {
            r0.lambda$deleteLink$5(r1, r2);
        }

        public LinkCell(Context r12, BaseFragment r13, int r14, int r15) {
            super(r12);
            this.fragment = r13;
            this.currentAccount = r14;
            this.filterId = r15;
            setImportantForAccessibility(1);
            AnimatedTextView r142 = new AnimatedTextView(r12, true, true, false);
            this.titleTextView = r142;
            r142.setTextSize(AndroidUtilities.dp(15.66f));
            this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            AnimatedTextView r143 = this.titleTextView;
            int r1 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r0 = 5;
        L6:
            r143.setGravity(r0);
            this.titleTextView.setEllipsizeByGradient(true);
            AnimatedTextView r132 = this.titleTextView;
            boolean r144 = LocaleController.isRTL;
            if (r144 == false) goto L9;
            float r7 = 56.0f;
        L10:
            if (r144 == false) goto L12;
            float r9 = 64.0f;
        L13:
            addView(r132, LayoutHelper.createFrame(-1, 20.0f, 55, r7, 10.33f, r9, 0.0f));
            AnimatedTextView r133 = new AnimatedTextView(r12, false, false, false);
            this.subtitleTextView = r133;
            r133.setTextSize(AndroidUtilities.dp(13.0f));
            this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            AnimatedTextView r134 = this.subtitleTextView;
            if (LocaleController.isRTL == false) goto L16;
            int r145 = 5;
        L17:
            r134.setGravity(r145);
            AnimatedTextView r135 = this.subtitleTextView;
            boolean r146 = LocaleController.isRTL;
            if (r146 == false) goto L20;
            float r72 = 56.0f;
        L21:
            if (r146 == false) goto L23;
            float r92 = 64.0f;
        L24:
            addView(r135, LayoutHelper.createFrame(-1, 16.0f, 55, r72, 33.33f, r92, 0.0f));
            ImageView r136 = new ImageView(r12);
            this.optionsIcon = r136;
            r136.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_ab_other));
            this.optionsIcon.setScaleType(ImageView.ScaleType.CENTER);
            this.optionsIcon.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector)));
            ImageView r122 = this.optionsIcon;
            int r147 = Theme.getColor(Theme.key_stickers_menu);
            PorterDuff.Mode r02 = PorterDuff.Mode.SRC_IN;
            r122.setColorFilter(new PorterDuffColorFilter(r147, r02));
            this.optionsIcon.setOnClickListener(new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda0(this));
            this.optionsIcon.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
            ImageView r123 = this.optionsIcon;
            boolean r137 = LocaleController.isRTL;
            if (r137 == true) goto L28;
            r1 = 5;
        L28:
            int r4 = r1 | 16;
            if (r137 == false) goto L31;
            float r5 = 8.0f;
        L32:
            if (r137 == false) goto L34;
            float r73 = 4.0f;
        L35:
            addView(r123, LayoutHelper.createFrame(40, 40.0f, r4, r5, 4.0f, r73, 4.0f));
            Paint r124 = new Paint();
            this.paint = r124;
            r124.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
            Paint r125 = new Paint();
            this.revokedPaint = r125;
            r125.setColor(Theme.getColor(Theme.key_color_red));
            Drawable r126 = getContext().getResources().getDrawable(R.drawable.msg_link_1).mutate();
            this.linkIcon = r126;
            r126.setColorFilter(new PorterDuffColorFilter(-1, r02));
            Drawable r127 = getContext().getResources().getDrawable(R.drawable.msg_link_2).mutate();
            this.revokedLinkIcon = r127;
            r127.setColorFilter(new PorterDuffColorFilter(-1, r02));
            setWillNotDraw(false);
            return;
        L34:
            r73 = 8.0f;
            goto L35
        L31:
            r5 = 4.0f;
            goto L32
        L23:
            r92 = 56.0f;
            goto L24
        L20:
            r72 = 64.0f;
            goto L21
        L16:
            r145 = 3;
            goto L17
        L12:
            r9 = 56.0f;
            goto L13
        L9:
            r7 = 64.0f;
            goto L10
        L5:
            r0 = 3;
            goto L6
        }

        private String getSlug() {
            String r1 = this.lastUrl;
            if (r1 != null) goto L7;
            return null;
        L7:
            return r1.substring(r1.lastIndexOf(47) + 1);
        }

        private /* synthetic */ void lambda$deleteLink$4() {
            onDelete(this.lastInvite);
        }

        private /* synthetic */ void lambda$deleteLink$5(TLRPC.TL_error r1, Runnable r2) {
            if (r1 == null) goto L5;
            BulletinFactory.of(this.fragment).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            AndroidUtilities.cancelRunOnUIThread(r2);
            return;
        }

        private /* synthetic */ void lambda$deleteLink$6(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda6(this, r3, r1));
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            options();
        }

        private /* synthetic */ void lambda$setRevoked$1(ValueAnimator r1) {
            this.revokeT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        public void deleteLink() {
            String r0 = getSlug();
            if (r0 != null) goto L5;
            return;
        L5:
            TL_chatlists.TL_chatlists_deleteExportedInvite r1 = new TL_chatlists.TL_chatlists_deleteExportedInvite();
            TL_chatlists.TL_inputChatlistDialogFilter r2 = new TL_chatlists.TL_inputChatlistDialogFilter();
            r1.chatlist = r2;
            r2.filter_id = this.filterId;
            r1.slug = r0;
            FilterCreateActivity$LinkCell$$ExternalSyntheticLambda4 r02 = new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda4(this);
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda5(this, r02));
            AndroidUtilities.runOnUIThread(r02, 150);
        }

        public abstract void onDelete(TL_chatlists.TL_exportedChatlistInvite r1);

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            super.onDraw(r13);
            if (LocaleController.isRTL == false) goto L5;
            int r0 = getMeasuredWidth() - AndroidUtilities.dp(32.0f);
        L6:
            float r1 = r0;
            r13.drawCircle(r1, getMeasuredHeight() / 2.0f, AndroidUtilities.dp(16.0f), this.paint);
            float r5 = 0.0f;
            if (this.revokeT <= 0.0f) goto L9;
            r13.drawCircle(r1, getMeasuredHeight() / 2.0f, AndroidUtilities.dp(16.0f) * this.revokeT, this.revokedPaint);
        L9:
            float r12 = this.revokeT;
            if (r12 >= 1.0f) goto L12;
            this.linkIcon.setAlpha((int) ((1.0f - r12) * 255.0f));
            this.linkIcon.setBounds(r0 - AndroidUtilities.dp(14.0f), (getMeasuredHeight() / 2) - AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f) + r0, (getMeasuredHeight() / 2) + AndroidUtilities.dp(14.0f));
            this.linkIcon.draw(r13);
        L12:
            float r14 = this.revokeT;
            if (r14 <= 0.0f) goto L16;
            this.revokedLinkIcon.setAlpha((int) (r14 * 255.0f));
            this.revokedLinkIcon.setBounds(r0 - AndroidUtilities.dp(14.0f), (getMeasuredHeight() / 2) - AndroidUtilities.dp(14.0f), r0 + AndroidUtilities.dp(14.0f), (getMeasuredHeight() / 2) + AndroidUtilities.dp(14.0f));
            this.revokedLinkIcon.draw(r13);
        L16:
            if (this.needDivider == true) goto L18;
            return;
        L18:
            if (LocaleController.isRTL == false) goto L20;
        L19:
            float r7 = r5;
            float r8 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L24;
            int r15 = AndroidUtilities.dp(64.0f);
        L25:
            r13.drawRect(r7, r8, r02 - r15, getMeasuredHeight(), Theme.dividerPaint);
            return;
        L24:
            r15 = 0;
            goto L25
        L20:
            r5 = AndroidUtilities.dp(64.0f);
            goto L19
        L5:
            r0 = AndroidUtilities.dp(32.0f);
            goto L6
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
            super.onInitializeAccessibilityNodeInfo(r5);
            StringBuilder r0 = new StringBuilder();
            TL_chatlists.TL_exportedChatlistInvite r1 = this.lastInvite;
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r1 != null) goto L5;
        L7:
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            r0.append(r12);
            r0.append(LocaleController.getString(R.string.InviteLink));
            r0.append(", ");
            r0.append(this.subtitleTextView.getText());
            TL_chatlists.TL_exportedChatlistInvite r13 = this.lastInvite;
            if (r13 != null) goto L11;
        L13:
            r0.append(r2);
            r5.setContentDescription(r0.toString());
            return;
        L11:
            if (TextUtils.isEmpty(r13.title) == false) goto L13;
            r2 = "\n\n" + this.lastInvite.url;
            goto L13
        L5:
            if (TextUtils.isEmpty(r1.title) == true) goto L7;
            r12 = this.lastInvite.title + "\n ";
            goto L8
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
        }

        public void options() {
            BaseFragment r0 = this.fragment;
            if ((r0 instanceof FilterCreateActivity) == true) goto L5;
            return;
        L5:
            RecyclerListView r02 = FilterCreateActivity.m14775$$Nest$fgetlistView((FilterCreateActivity) r0);
            ItemOptions r1 = ItemOptions.makeOptions(this.fragment, this);
            r1.setScrimViewBackground(r02.getClipBackground(this));
            r1.add(R.drawable.msg_qrcode, LocaleController.getString(R.string.GetQRCode), new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda2(this));
            r1.add(R.drawable.msg_delete, LocaleController.getString(R.string.DeleteLink), true, new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda3(this));
            if (LocaleController.isRTL == false) goto L8;
            r1.setGravity(3);
        L8:
            r1.show();
        }

        public void qrcode() {
            if (this.lastUrl != null) goto L5;
            return;
        L5:
            QRCodeBottomSheet r1 = new QRCodeBottomSheet(getContext(), LocaleController.getString(R.string.InviteByQRCode), this.lastUrl, LocaleController.getString(R.string.QRCodeLinkHelpFolder), false);
            r1.setCenterAnimation(R.raw.qr_code_logo);
            r1.show();
        }

        public void setInvite(TL_chatlists.TL_exportedChatlistInvite r6, boolean r7) {
            if (this.lastInvite != r6) goto L5;
            boolean r0 = true;
        L6:
            this.lastInvite = r6;
            String r2 = r6.url;
            this.lastUrl = r2;
            if (r2.startsWith("http://") == false) goto L10;
            r2 = r2.substring(7);
        L10:
            if (r2.startsWith("https://") == false) goto L12;
            r2 = r2.substring(8);
        L12:
            boolean r3 = TextUtils.isEmpty(r6.title);
            AnimatedTextView r4 = this.titleTextView;
            if (r3 == false) goto L15;
            r4.setText(r2, r0);
        L16:
            this.subtitleTextView.setText(LocaleController.formatPluralString("FilterInviteChats", r6.peers.size(), new Object[0]), r0);
            if (this.needDivider == r7) goto L19;
            this.needDivider = r7;
            invalidate();
        L19:
            setRevoked(r6.revoked, r0);
            return;
        L15:
            r4.setText(r6.title, r0);
            goto L16
        L5:
            r0 = false;
            goto L6
        }

        public void setRevoked(boolean r4, boolean r5) {
            this.lastRevoked = r4;
            if ((r4 ? 1.0f : 0.0f) == this.revokeT) goto L18;
            ValueAnimator r0 = this.valueAnimator;
            if (r0 == null) goto L7;
            r0.cancel();
            this.valueAnimator = null;
        L7:
            float r02 = 0.0f;
            if (r5 == false) goto L14;
            float r52 = this.revokeT;
            if (r4 == false) goto L12;
            r02 = 1.0f;
        L12:
            ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r02});
            this.valueAnimator = r53;
            r53.addUpdateListener(new FilterCreateActivity$LinkCell$$ExternalSyntheticLambda1(this));
            this.valueAnimator.addListener(new AnonymousClass1(this, r4));
            this.valueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.valueAnimator.setDuration(350);
            this.valueAnimator.start();
            return;
        L14:
            if (r4 == false) goto L16;
            r02 = 1.0f;
        L16:
            this.revokeT = r02;
            invalidate();
            return;
        }
    }

    public class ListAdapter extends AdapterWithDiffUtils {
        private Context mContext;
        final /* synthetic */ FilterCreateActivity this$0;

        /* renamed from: org.telegram.ui.FilterCreateActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends EditEmojiTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2, SizeNotifierFrameLayout r3, String r4, boolean r5, int r6, int r7, Theme.ResourcesProvider r8) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Cells.EditEmojiTextCell
            public int emojiCacheType() {
                return 25;
            }
        }

        /* renamed from: org.telegram.ui.FilterCreateActivity$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass2(ListAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r6) {
                if (TextUtils.equals(r6, FilterCreateActivity.m14781$$Nest$fgetnewFilterName(this.this$1.this$0)) == true) goto L19;
                FilterCreateActivity.m14784$$Nest$fputnameChangedManually(this.this$1.this$0, !TextUtils.isEmpty(r6));
                FilterCreateActivity.m14789$$Nest$fputnewFilterName(this.this$1.this$0, AnimatedEmojiSpan.onlyEmojiSpans(r6));
                if (FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$1.this$0) == null) goto L8;
                FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$1.this$0).setPreviewText(AnimatedEmojiSpan.cloneSpans(FilterCreateActivity.m14781$$Nest$fgetnewFilterName(this.this$1.this$0), -1, FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$1.this$0).getPreviewTextPaint().getFontMetricsInt(), 0.5f), true);
            L8:
                if (FilterCreateActivity.m14777$$Nest$fgetnameHeaderCell(this.this$1.this$0) == null) goto L18;
                AnimatedTextView r62 = HeaderCellWithRight.m14804$$Nest$fgetrightTextView(FilterCreateActivity.m14777$$Nest$fgetnameHeaderCell(this.this$1.this$0));
                FilterCreateActivity r2 = this.this$1.this$0;
                if (r2.hasAnimatedEmojis(FilterCreateActivity.m14781$$Nest$fgetnewFilterName(r2)) == true) goto L12;
                String r22 = null;
            L17:
                r62.setText(r22);
                goto L18
            L12:
                if (FilterCreateActivity.m14778$$Nest$fgetnewFilterAnimations(this.this$1.this$0) == false) goto L14;
                int r23 = R.string.FilterNameAnimationsDisable;
            L15:
                r22 = LocaleController.getString(r23);
                goto L17
            L14:
                r23 = R.string.FilterNameAnimationsEnable;
            L18:
                FilterCreateActivity.access$400(this.this$1.this$0).setTitle(AnimatedEmojiSpan.cloneSpans(FilterCreateActivity.m14781$$Nest$fgetnewFilterName(this.this$1.this$0), -1, FilterCreateActivity.access$300(this.this$1.this$0).getTitleFontMetricsInt()));
            L19:
                FilterCreateActivity.m14791$$Nest$mcheckDoneButton(this.this$1.this$0, true);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.FilterCreateActivity$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends LinkCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1, Context r2, BaseFragment r3, int r4, int r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.FilterCreateActivity.LinkCell
            public void onDelete(TL_chatlists.TL_exportedChatlistInvite r1) {
                FilterCreateActivity.m14792$$Nest$monDelete(this.this$1.this$0, r1);
            }
        }

        /* renamed from: $r8$lambda$7PZDDR-oXAedSldqfBL0BandrCA, reason: not valid java name */
        public static /* synthetic */ void m14818$r8$lambda$7PZDDRoXAedSldqfBL0BandrCA(ListAdapter r0, PeerColorActivity.PeerColorGrid r1, Integer r2) {
            r0.lambda$onBindViewHolder$2(r1, r2);
        }

        /* renamed from: $r8$lambda$PXYLqQV4XP3QscJT6l-0J0napi8, reason: not valid java name */
        public static /* synthetic */ void m14819$r8$lambda$PXYLqQV4XP3QscJT6l0J0napi8(ListAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$vQcC8TiZN3zYfzHlUpkf20rSuZ0(ListAdapter r0, String r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public ListAdapter(FilterCreateActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$2(PeerColorActivity.PeerColorGrid r4, Integer r5) {
            boolean r0 = this.this$0.getUserConfig().isPremium();
            FilterCreateActivity r1 = this.this$0;
            if (r0 == true) goto L6;
            r1.showDialog(new PremiumFeatureBottomSheet(this.this$0, 35, true));
            return;
        L6:
            int r52 = r5.intValue();
            FilterCreateActivity.m14787$$Nest$fputnewFilterColor(r1, r52);
            r4.setSelected(r52, true);
            if (FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0) == null) goto L13;
            HeaderCellColorPreview r42 = FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0);
            if (this.this$0.getUserConfig().isPremium() == true) goto L11;
            int r53 = -1;
        L12:
            r42.setPreviewColor(r53, true);
            goto L13
        L11:
            r53 = FilterCreateActivity.m14779$$Nest$fgetnewFilterColor(this.this$0);
        L13:
            FilterCreateActivity.m14791$$Nest$mcheckDoneButton(this.this$0, true);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(String r3) {
            FilterCreateActivity.m14788$$Nest$fputnewFilterEmoticon(this.this$0, r3);
            FilterCreateActivity.m14776$$Nest$fgetnameEditTextCell(this.this$0).setIcon(FolderIcons.getTabIcon(FilterCreateActivity.m14780$$Nest$fgetnewFilterEmoticon(this.this$0)), true);
            FilterCreateActivity.m14791$$Nest$mcheckDoneButton(this.this$0, true);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(View r4) {
            FilterCreateActivity r0 = this.this$0;
            if (FilterCreateActivity.m14780$$Nest$fgetnewFilterEmoticon(r0) != null) goto L5;
            String r1 = "📁";
        L6:
            IconSelectorAlert.show(r0, r4, r1, new FilterCreateActivity$ListAdapter$$ExternalSyntheticLambda2(this));
            return;
        L5:
            r1 = FilterCreateActivity.m14780$$Nest$fgetnewFilterEmoticon(this.this$0);
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            ItemInner r0 = (ItemInner) FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).get(r1);
            if (r0 != null) goto L7;
            return 3;
        L7:
            return r0.viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 == 3) goto L15;
            if (r0 != 0) goto L6;
            return false;
        L6:
            if (r0 != 2) goto L8;
            return false;
        L8:
            if (r0 != 5) goto L10;
            return false;
        L10:
            if (r0 != 9) goto L12;
            return false;
        L12:
            if (r0 == 11) goto L21;
            return true;
        L21:
            return false;
        L15:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            ItemInner r0 = (ItemInner) FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).get(r12);
            if (r0 == null) goto L95;
            int r122 = r12 + 1;
            if (r122 < FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).size()) goto L8;
        L10:
            boolean r9 = false;
        L11:
            int r123 = r11.getItemViewType();
            if (r123 == 0) goto L87;
            if (r123 != 1) goto L15;
            UserCell r4 = (UserCell) r11.itemView;
            if (ItemInner.m14805$$Nest$fgetchatType(r0) == null) goto L45;
            r4.setData(ItemInner.m14805$$Nest$fgetchatType(r0), ItemInner.m14815$$Nest$fgettext(r0), null, 0, r9);
            return;
        L45:
            long r112 = ItemInner.m14806$$Nest$fgetdid(r0);
            FilterCreateActivity r10 = this.this$0;
            if (r112 <= 0) goto L59;
            TLRPC.User r5 = r10.getMessagesController().getUser(Long.valueOf(r112));
            if (r5 != null) goto L50;
            return;
        L50:
            if (r5.bot == false) goto L54;
            String r102 = LocaleController.getString(R.string.Bot);
        L57:
            r4.setData(r5, null, r102, 0, r9);
            return;
        L54:
            if (r5.contact == false) goto L56;
            r102 = LocaleController.getString(R.string.FilterContact);
            goto L57
        L56:
            r102 = LocaleController.getString(R.string.FilterNonContact);
            goto L57
        L59:
            TLRPC.Chat r52 = r10.getMessagesController().getChat(Long.valueOf(-r112));
            if (r52 != null) goto L62;
            return;
        L62:
            if (ChatObject.isCommunity(r52) == false) goto L66;
            String r103 = LocaleController.getString(R.string.Community);
        L85:
            r4.setData(r52, null, r103, 0, r9);
            return;
        L66:
            if (r52.participants_count == 0) goto L72;
            boolean r104 = ChatObject.isChannelAndNotMegaGroup(r52);
            int r113 = r52.participants_count;
            if (r104 == false) goto L70;
            r103 = LocaleController.formatPluralStringComma("Subscribers", r113);
            goto L85
        L70:
            r103 = LocaleController.formatPluralStringComma("Members", r113);
            goto L85
        L72:
            if (ChatObject.isPublic(r52) == true) goto L80;
            if (ChatObject.isChannel(r52) == true) goto L76;
        L78:
            r103 = LocaleController.getString(R.string.MegaPrivate);
            goto L85
        L76:
            if (r52.megagroup == true) goto L78;
            r103 = LocaleController.getString(R.string.ChannelPrivate);
            goto L85
        L80:
            if (ChatObject.isChannel(r52) == true) goto L82;
        L84:
            r103 = LocaleController.getString(R.string.MegaPublic);
            goto L85
        L82:
            if (r52.megagroup == true) goto L84;
            r103 = LocaleController.getString(R.string.ChannelPublic);
            goto L85
        L15:
            if (r123 == 4) goto L39;
            int r2 = -1;
            switch(r123) {
                case 6: goto L37;
                case 7: goto L35;
                case 8: goto L33;
                case 9: goto L27;
                case 10: goto L21;
                case 11: goto L19;
                default: goto L96;
            };
        L19:
            HeaderCellWithRight r114 = (HeaderCellWithRight) r11.itemView;
            FilterCreateActivity.m14786$$Nest$fputnameHeaderCell(this.this$0, r114);
            r114.setText(ItemInner.m14815$$Nest$fgettext(r0));
            HeaderCellWithRight.m14804$$Nest$fgetrightTextView(r114).setText(ItemInner.m14814$$Nest$fgetsubtext(r0));
            HeaderCellWithRight.m14804$$Nest$fgetrightTextView(r114).setOnClickListener(ItemInner.m14813$$Nest$fgetonClickListener(r0));
            return;
        L21:
            PeerColorActivity.PeerColorGrid r115 = (PeerColorActivity.PeerColorGrid) r11.itemView;
            r115.setCloseAsLock(!this.this$0.getUserConfig().isPremium());
            if (this.this$0.getUserConfig().isPremium() == false) goto L25;
            r2 = FilterCreateActivity.m14779$$Nest$fgetnewFilterColor(this.this$0);
        L25:
            r115.setSelected(r2, false);
            r115.setOnColorClick(new FilterCreateActivity$ListAdapter$$ExternalSyntheticLambda1(this, r115));
            return;
        L27:
            FilterCreateActivity.m14783$$Nest$fputfolderTagsHeader(this.this$0, (HeaderCellColorPreview) r11.itemView);
            FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0).setPreviewText(AnimatedEmojiSpan.cloneSpans(FilterCreateActivity.m14781$$Nest$fgetnewFilterName(this.this$0), -1, FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0).getPreviewTextPaint().getFontMetricsInt(), 0.5f), false);
            HeaderCellColorPreview r116 = FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0);
            if (this.this$0.getUserConfig().isPremium() == false) goto L31;
            r2 = FilterCreateActivity.m14779$$Nest$fgetnewFilterColor(this.this$0);
        L31:
            r116.setPreviewColor(r2, false);
            FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0).setText(LocaleController.getString(R.string.FolderTagColor));
            return;
        L33:
            FilterCreateActivity.m14782$$Nest$fputcreateLinkCell(this.this$0, (CreateLinkCell) r11.itemView);
            FilterCreateActivity.m14771$$Nest$fgetcreateLinkCell(this.this$0).setDivider(r9);
            return;
        L35:
            ((LinkCell) r11.itemView).setInvite(ItemInner.m14811$$Nest$fgetlink(r0), r9);
            return;
        L37:
            ((TextInfoPrivacyCell) r11.itemView).setText(ItemInner.m14815$$Nest$fgettext(r0));
            return;
        L96:
            return;
        L39:
            ButtonCell r105 = (ButtonCell) r11.itemView;
            r105.setRed(ItemInner.m14810$$Nest$fgetisRed(r0));
            r105.set(ItemInner.m14808$$Nest$fgeticonResId(r0), ItemInner.m14815$$Nest$fgettext(r0), r9);
            return;
        L87:
            HeaderCell r106 = (HeaderCell) r11.itemView;
            if (ItemInner.m14812$$Nest$fgetnewSpan(r0) == false) goto L91;
            r106.setText(FilterCreateActivity.withNew(0, ItemInner.m14815$$Nest$fgettext(r0), false));
            return;
        L91:
            r106.setText(ItemInner.m14815$$Nest$fgettext(r0));
            return;
        L8:
            if (((ItemInner) FilterCreateActivity.m14774$$Nest$fgetitems(this.this$0).get(r122)).isShadow() == true) goto L10;
            r9 = true;
            goto L11
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
            switch(r12) {
                case 0: goto L15;
                case 1: goto L14;
                case 2: goto L13;
                case 3: goto L12;
                case 4: goto L11;
                case 5: goto L10;
                case 6: goto L4;
                case 7: goto L9;
                case 8: goto L8;
                case 9: goto L7;
                case 10: goto L6;
                case 11: goto L5;
                default: goto L4;
            };
        L4:
            View r112 = new TextInfoPrivacyCell(this.mContext);
        L17:
            return new RecyclerListView.Holder(r112);
        L5:
            FilterCreateActivity r122 = this.this$0;
            r112 = new HeaderCellWithRight(r122, this.mContext, FilterCreateActivity.access$000(r122));
            goto L17
        L6:
            r112 = new PeerColorActivity.PeerColorGrid(this.this$0.getContext(), 2, FilterCreateActivity.access$600(this.this$0), FilterCreateActivity.access$700(this.this$0));
            goto L17
        L7:
            r112 = new HeaderCellColorPreview(this.this$0, this.mContext);
            goto L17
        L8:
            r112 = new CreateLinkCell(this.mContext);
            goto L17
        L9:
            Context r2 = this.mContext;
            FilterCreateActivity r3 = this.this$0;
            r112 = new AnonymousClass3(this, r2, r3, FilterCreateActivity.access$500(r3), FilterCreateActivity.m14772$$Nest$fgetfilter(this.this$0).id);
            goto L17
        L10:
            r112 = new HintInnerCell(this.mContext);
            goto L17
        L11:
            r112 = new ButtonCell(this.mContext);
            goto L17
        L12:
            r112 = new ShadowSectionCell(this.mContext);
            goto L17
        L13:
            FilterCreateActivity r10 = this.this$0;
            AnonymousClass1 r1 = new AnonymousClass1(this, this.mContext, (SizeNotifierFrameLayout) this.this$0.fragmentView, LocaleController.getString(R.string.FilterNameHint), false, 12, 4, FilterCreateActivity.access$100(this.this$0));
            FilterCreateActivity.m14785$$Nest$fputnameEditTextCell(r10, r1);
            r1.setOnChangeIconListener(new FilterCreateActivity$ListAdapter$$ExternalSyntheticLambda0(this));
            r1.setIcon(FolderIcons.getTabIcon(FilterCreateActivity.m14780$$Nest$fgetnewFilterEmoticon(this.this$0)), false);
            r1.setAllowEntities(false);
            r1.editTextEmoji.getEditText().setEmojiColor(Integer.valueOf(this.this$0.getThemedColor(Theme.key_featuredStickers_addButton)));
            r1.editTextEmoji.setEmojiViewCacheType(25);
            r1.editTextEmoji.setText(FilterCreateActivity.m14781$$Nest$fgetnewFilterName(this.this$0));
            AnimatedEmojiDrawable.toggleAnimations(FilterCreateActivity.access$200(this.this$0), FilterCreateActivity.m14778$$Nest$fgetnewFilterAnimations(this.this$0));
            EditTextCaption r102 = r1.editTextEmoji.getEditText();
            r102.addTextChangedListener(new EditTextSuggestionsFix());
            r102.addTextChangedListener(new AnonymousClass2(this));
            r102.setPadding(AndroidUtilities.dp(7.0f), r102.getPaddingTop(), r102.getPaddingRight(), r102.getPaddingBottom());
            r1.editTextEmoji.getEditText().setImeOptions(268435462);
            r112 = r1;
            goto L17
        L14:
            UserCell r103 = new UserCell(this.mContext, 6, 0, false);
            r103.setSelfAsSavedMessages(true);
            r112 = r103;
            goto L17
        L15:
            r112 = new HeaderCell(this.mContext, 22);
            goto L17
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            int r0 = r4.getItemViewType();
            if (r0 != 2) goto L6;
            return;
        L6:
            if (r0 != 9) goto L9;
            ((HeaderCellColorPreview) r4.itemView).setPreviewText(AnimatedEmojiSpan.cloneSpans(FilterCreateActivity.m14781$$Nest$fgetnewFilterName(this.this$0), -1, FilterCreateActivity.m14773$$Nest$fgetfolderTagsHeader(this.this$0).getPreviewTextPaint().getFontMetricsInt(), 0.5f), true);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 2) goto L6;
            EditEmojiTextCell r1 = (EditEmojiTextCell) r2.itemView;
            r1.editTextEmoji.hidePopup(true);
            r1.editTextEmoji.closeKeyboard();
            return;
        }
    }

    public static class NewSpan extends ReplacementSpan {
        Paint bgPaint;
        private int color;
        private int fontSize;
        float height;
        StaticLayout layout;
        private boolean outline;
        private CharSequence text;
        private int textColor;
        TextPaint textPaint;
        public boolean usePaintAlpha;
        float width;

        public NewSpan(float r3) {
            this.textPaint = new TextPaint(1);
            this.bgPaint = new Paint(1);
            this.text = "NEW";
            this.outline = false;
            this.textPaint.setTypeface(AndroidUtilities.bold());
            this.bgPaint.setStyle(Paint.Style.FILL);
            this.textPaint.setTextSize(AndroidUtilities.dp(r3));
        }

        public NewSpan(boolean r3, int r4) {
            this.textPaint = new TextPaint(1);
            this.bgPaint = new Paint(1);
            this.text = "NEW";
            this.outline = r3;
            this.fontSize = r4;
            this.textPaint.setTypeface(AndroidUtilities.bold());
            Paint r0 = this.bgPaint;
            if (r3 == false) goto L10;
            r0.setStyle(Paint.Style.STROKE);
            this.bgPaint.setStrokeWidth(AndroidUtilities.dpf2(1.33f));
            TextPaint r32 = this.textPaint;
            if (r4 >= 0) goto L7;
            float r42 = 10.0f;
        L8:
            r32.setTextSize(AndroidUtilities.dp(r42));
            this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.textPaint.setStrokeWidth(AndroidUtilities.dpf2(0.2f));
            this.textPaint.setLetterSpacing(0.03f);
            return;
        L7:
            r42 = r4;
            goto L8
        L10:
            r0.setStyle(Paint.Style.FILL);
            TextPaint r2 = this.textPaint;
            if (r4 >= 0) goto L13;
            float r33 = 12.0f;
        L14:
            r2.setTextSize(AndroidUtilities.dp(r33));
            return;
        L13:
            r33 = r4;
            goto L14
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            makeLayout();
            if (this.usePaintAlpha == false) goto L5;
            float r22 = r9.getAlpha() / 255.0f;
        L6:
            int r42 = this.color;
            int r62 = this.textColor;
            if (r42 != 0) goto L9;
            r42 = r9.getColor();
        L9:
            if (r62 == 0) goto L11;
        L14:
            this.bgPaint.setColor(r42);
            boolean r82 = this.outline;
            TextPaint r92 = this.textPaint;
            if (r82 == false) goto L17;
            r92.setColor(r42);
        L18:
            this.bgPaint.setAlpha((int) (r4.getAlpha() * r22));
            this.textPaint.setAlpha((int) (r4.getAlpha() * r22));
            float r52 = r5 + AndroidUtilities.dp(2.0f);
            float r23 = (r7 - this.height) + AndroidUtilities.dp(1.0f);
            RectF r32 = AndroidUtilities.rectTmp;
            r32.set(r52, r23, this.width + r52, this.height + r23);
            if (this.outline == false) goto L21;
            float r63 = AndroidUtilities.dp(3.66f);
            r32.left -= AndroidUtilities.dp(4.0f);
            r32.top -= AndroidUtilities.dp(2.33f);
            r32.right += AndroidUtilities.dp(3.66f);
            r32.bottom += AndroidUtilities.dp(1.33f);
        L26:
            r1.drawRoundRect(r32, r63, r63, this.bgPaint);
            r1.save();
            r1.translate(r52, r23);
            this.layout.draw(r1);
            r1.restore();
            return;
        L21:
            r63 = AndroidUtilities.dp(4.4f);
            float r43 = AndroidUtilities.dp(-4.0f);
            if (this.fontSize != 8) goto L24;
            float r72 = -3.66f;
        L25:
            r32.inset(r43, AndroidUtilities.dp(r72));
            goto L26
        L24:
            r72 = -2.33f;
            goto L25
        L17:
            r92.setColor(r62);
            goto L18
        L11:
            if (AndroidUtilities.computePerceivedBrightness(r42) <= 0.721f) goto L13;
            r62 = -16777216;
            goto L14
        L13:
            r62 = -1;
            goto L14
        L5:
            r22 = 1.0f;
            goto L6
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            makeLayout();
            return (int) (AndroidUtilities.dp(10.0f) + this.width);
        }

        public StaticLayout makeLayout() {
            if (this.layout != null) goto L6;
            StaticLayout r1 = new StaticLayout(this.text, this.textPaint, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.layout = r1;
            this.width = r1.getLineWidth(0);
            this.height = this.layout.getHeight();
        L6:
            return this.layout;
        }

        public void setColor(int r1) {
            this.color = r1;
        }

        public void setText(CharSequence r1) {
            this.text = r1;
            if (this.layout == null) goto L6;
            this.layout = null;
            makeLayout();
            return;
        }

        public void setTextColor(int r1) {
            this.textColor = r1;
        }

        public void setTypeface(Typeface r1) {
            this.textPaint.setTypeface(r1);
        }
    }

    public static class TextSpan extends ReplacementSpan {
        Paint bgPaint;
        private int colorKey;
        private final Theme.ResourcesProvider resourcesProvider;
        private Text text;

        public TextSpan(String r3, float r4, int r5, Theme.ResourcesProvider r6) {
            this.bgPaint = new Paint(1);
            this.resourcesProvider = r6;
            this.colorKey = r5;
            this.text = new Text(r3, r4, AndroidUtilities.bold());
            this.bgPaint.setStyle(Paint.Style.FILL);
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            int r22 = Theme.getColor(this.colorKey, this.resourcesProvider);
            this.bgPaint.setColor(Theme.multAlpha(r22, 0.15f));
            float r72 = (r8 + r6) / 2.0f;
            float r32 = AndroidUtilities.dp(14.66f);
            RectF r62 = AndroidUtilities.rectTmp;
            float r33 = r32 / 2.0f;
            r62.set(r5, r72 - r33, (this.text.getWidth() + r5) + AndroidUtilities.dp(9.33f), r33 + r72);
            r1.drawRoundRect(r62, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.bgPaint);
            this.text.draw(r1, r5 + AndroidUtilities.dp(4.66f), r72, r22, 1.0f);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) (AndroidUtilities.dp(9.33f) + this.text.getWidth());
        }
    }

    /* renamed from: $r8$lambda$3F_5NIR4uCxJ-JRFE1Jq5d6Oyw0, reason: not valid java name */
    public static /* synthetic */ void m14758$r8$lambda$3F_5NIR4uCxJJRFE1Jq5d6Oyw0(FilterCreateActivity r0, Runnable r1) {
        r0.lambda$save$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$7gxch7B70LgMcmG2JvgkzPbdkPo(FilterCreateActivity r0, ItemInner r1, boolean r2, AlertDialog r3, int r4) {
        r0.lambda$showRemoveAlert$22(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$90PmKFL---IjnY_1ZWQSVC8DyPE, reason: not valid java name */
    public static /* synthetic */ void m14759$r8$lambda$90PmKFLIjnY_1ZWQSVC8DyPE(FilterCreateActivity r0, TL_chatlists.TL_chatlists_exportedChatlistInvite r1) {
        r0.lambda$onClickCreateLink$10(r1);
    }

    /* renamed from: $r8$lambda$CmEqbeppH-KIJxpbxgwFXDyP7bk, reason: not valid java name */
    public static /* synthetic */ void m14760$r8$lambda$CmEqbeppHKIJxpbxgwFXDyP7bk(FilterCreateActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onClickCreateLink$12(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$EAiVXe9Yl9sL1hdHBEq1o50JJaY(LongSparseIntArray r2, Long r3, Long r4) {
        int r32 = r2.get(r3.longValue());
        int r22 = r2.get(r4.longValue());
        if (r32 <= r22) goto L6;
        return 1;
    L6:
        if (r32 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    /* renamed from: $r8$lambda$HPNmIHWedjY0xezK328wK-cy7EM, reason: not valid java name */
    public static /* synthetic */ boolean m14761$r8$lambda$HPNmIHWedjY0xezK328wKcy7EM(FilterCreateActivity r0, View r1, int r2) {
        return r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LX90Uksk4VxpfF0Ku4gFWQF6Hbs(FilterCreateActivity r0, TL_chatlists.TL_exportedChatlistInvite r1) {
        r0.onEdit(r1);
    }

    /* renamed from: $r8$lambda$OP1n6e-YQvRWQWy6VBLKDjfgzXU, reason: not valid java name */
    public static /* synthetic */ void m14762$r8$lambda$OP1n6eYQvRWQWy6VBLKDjfgzXU(FilterCreateActivity r0, AlertDialog r1) {
        r0.lambda$deleteFolder$15(r1);
    }

    /* renamed from: $r8$lambda$OUMeDN9Y2sU_1s1dEg0INBS-B6c, reason: not valid java name */
    public static /* synthetic */ void m14763$r8$lambda$OUMeDN9Y2sU_1s1dEg0INBSB6c(FilterCreateActivity r0, ItemInner r1) {
        r0.lambda$createView$7(r1);
    }

    /* renamed from: $r8$lambda$OYmr-0fejiowj97i-j4v7f5BJgY, reason: not valid java name */
    public static /* synthetic */ void m14764$r8$lambda$OYmr0fejiowj97ij4v7f5BJgY(FilterCreateActivity r0, TLObject r1) {
        r0.lambda$loadInvites$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ocv_ltOJzEU0YFddgb0dAKhFyT4(FilterCreateActivity r0, View r1) {
        r0.lambda$updateRows$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$R7p4qhwSm_B9fw1WQ9YgBVmolT0(FilterCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$deleteFolder$17(r1, r2);
    }

    /* renamed from: $r8$lambda$Sn-fwkw2cJEKYpbZLGRytZdZmHU, reason: not valid java name */
    public static /* synthetic */ void m14765$r8$lambda$Snfwkw2cJEKYpbZLGRytZdZmHU(boolean r1, AlertDialog r2, MessagesController.DialogFilter r3, int r4, String r5, String r6, ArrayList r7, boolean r8, int r9, ArrayList r10, ArrayList r11, boolean r12, boolean r13, boolean r14, boolean r15, BaseFragment r16, Runnable r17) {
        if (r1 == false) goto L10;
        if (r2 != null) goto L13;
    L8:
        processAddFilter(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17);
        return;
    L13:
        r2.dismiss();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    L10:
        if (r17 == null) goto L15;
        r17.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$TzBNVMNF90o6wKXy45Ypq2Qn0cU(FilterCreateActivity r0) {
        r0.lambda$processDone$23();
    }

    public static /* synthetic */ void $r8$lambda$UdDTiJPly741kX9C5S_EKF2tbNM(FilterCreateActivity r0, View r1) {
        r0.lambda$updateRows$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$UqW6ss0PMxRe0xAUTAKvHj1u2d8(boolean r18, AlertDialog r19, MessagesController.DialogFilter r20, int r21, String r22, String r23, ArrayList r24, boolean r25, int r26, ArrayList r27, ArrayList r28, boolean r29, boolean r30, boolean r31, boolean r32, BaseFragment r33, Runnable r34, TLObject r35, TLRPC.TL_error r36) {
        AndroidUtilities.runOnUIThread(new FilterCreateActivity$$ExternalSyntheticLambda14(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34));
    }

    public static /* synthetic */ void $r8$lambda$WRh8VCnuQcgXSbkqrAQuDpOTVXc(FilterCreateActivity r0, View r1) {
        r0.lambda$updateRows$5(r1);
    }

    /* renamed from: $r8$lambda$aF0Mx0-E8hqNKOCq-amjyQUfV10, reason: not valid java name */
    public static /* synthetic */ void m14766$r8$lambda$aF0Mx0E8hqNKOCqamjyQUfV10(FilterCreateActivity r0, Boolean r1) {
        r0.lambda$deleteFolder$14(r1);
    }

    /* renamed from: $r8$lambda$fBbs6Rk3hXpveRcsKGNphz-05Hc, reason: not valid java name */
    public static /* synthetic */ void m14767$r8$lambda$fBbs6Rk3hXpveRcsKGNphz05Hc(FilterCreateActivity r0) {
        r0.lambda$getThemeDescriptions$28();
    }

    public static /* synthetic */ void $r8$lambda$fhTwM7q5tDWItxB3y0Hkq5DaRxY(FilterCreateActivity r0, View r1, int r2) {
        r0.lambda$createView$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hjXH2sP125rcyjBANpW0Lc_RzJw(FilterCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jJCUsCjryv9EtPw3V8fwbDmCYSg(FilterCreateActivity r0, boolean r1, ArrayList r2, int r3) {
        r0.lambda$selectChatsFor$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kdwro4Yly5JpQcRhoRsQPOJye4w(FilterCreateActivity r0, boolean r1, int r2) {
        r0.lambda$onUpdate$29(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mF2QVY62zxXqwI5B6URws30ICmc(FilterCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$21(r1, r2);
    }

    /* renamed from: $r8$lambda$mqrfe-1pb9VJgTqutu__pYVMsUk, reason: not valid java name */
    public static /* synthetic */ void m14768$r8$lambda$mqrfe1pb9VJgTqutu__pYVMsUk(FilterCreateActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadInvites$1(r1, r2);
    }

    /* renamed from: $r8$lambda$mx0imAyN-_esXdIXs-gJ_1RbioI, reason: not valid java name */
    public static /* synthetic */ void m14769$r8$lambda$mx0imAyN_esXdIXsgJ_1RbioI(FilterCreateActivity r0, View r1) {
        r0.lambda$updateRows$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$tjzkXLOnlUB8vsoHXPBJqFrrDIE(FilterCreateActivity r0, View r1) {
        r0.deleteFolder(r1);
    }

    /* renamed from: $r8$lambda$uo_tDt_r0UO-0A8BMicfMmk4qSY, reason: not valid java name */
    public static /* synthetic */ void m14770$r8$lambda$uo_tDt_r0UO0A8BMicfMmk4qSY(FilterCreateActivity r0, TL_chatlists.TL_exportedChatlistInvite r1) {
        r0.onDelete(r1);
    }

    public static /* synthetic */ void $r8$lambda$vF1NJlTp2BfFxyZLfyMdP6rwMZg(FilterCreateActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$onClickCreateLink$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vqqQtgI7BM_OYnh8qVzK3Fqv0do(FilterCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$y91rk8agS58UOnaUcLaV6FxTJ6E(FilterCreateActivity r0, View r1) {
        r0.lambda$updateRows$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$zCOEfKlRlGv2d_bPzgfM6JDmx_M(FilterCreateActivity r0) {
        r0.lambda$onClickCreateLink$13();
    }

    public static /* synthetic */ void $r8$lambda$zO9KJMwUWgX6AEytmS0d6BEl7NY(FilterCreateActivity r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$deleteFolder$16(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetcreateLinkCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CreateLinkCell m14771$$Nest$fgetcreateLinkCell(FilterCreateActivity r0) {
        return r0.createLinkCell;
    }

    /* renamed from: -$$Nest$fgetfilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessagesController.DialogFilter m14772$$Nest$fgetfilter(FilterCreateActivity r0) {
        return r0.filter;
    }

    /* renamed from: -$$Nest$fgetfolderTagsHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderCellColorPreview m14773$$Nest$fgetfolderTagsHeader(FilterCreateActivity r0) {
        return r0.folderTagsHeader;
    }

    /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14774$$Nest$fgetitems(FilterCreateActivity r0) {
        return r0.items;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m14775$$Nest$fgetlistView(FilterCreateActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetnameEditTextCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditEmojiTextCell m14776$$Nest$fgetnameEditTextCell(FilterCreateActivity r0) {
        return r0.nameEditTextCell;
    }

    /* renamed from: -$$Nest$fgetnameHeaderCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderCellWithRight m14777$$Nest$fgetnameHeaderCell(FilterCreateActivity r0) {
        return r0.nameHeaderCell;
    }

    /* renamed from: -$$Nest$fgetnewFilterAnimations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14778$$Nest$fgetnewFilterAnimations(FilterCreateActivity r0) {
        return r0.newFilterAnimations;
    }

    /* renamed from: -$$Nest$fgetnewFilterColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14779$$Nest$fgetnewFilterColor(FilterCreateActivity r0) {
        return r0.newFilterColor;
    }

    /* renamed from: -$$Nest$fgetnewFilterEmoticon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m14780$$Nest$fgetnewFilterEmoticon(FilterCreateActivity r0) {
        return r0.newFilterEmoticon;
    }

    /* renamed from: -$$Nest$fgetnewFilterName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m14781$$Nest$fgetnewFilterName(FilterCreateActivity r0) {
        return r0.newFilterName;
    }

    /* renamed from: -$$Nest$fputcreateLinkCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14782$$Nest$fputcreateLinkCell(FilterCreateActivity r0, CreateLinkCell r1) {
        r0.createLinkCell = r1;
    }

    /* renamed from: -$$Nest$fputfolderTagsHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14783$$Nest$fputfolderTagsHeader(FilterCreateActivity r0, HeaderCellColorPreview r1) {
        r0.folderTagsHeader = r1;
    }

    /* renamed from: -$$Nest$fputnameChangedManually, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14784$$Nest$fputnameChangedManually(FilterCreateActivity r0, boolean r1) {
        r0.nameChangedManually = r1;
    }

    /* renamed from: -$$Nest$fputnameEditTextCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14785$$Nest$fputnameEditTextCell(FilterCreateActivity r0, EditEmojiTextCell r1) {
        r0.nameEditTextCell = r1;
    }

    /* renamed from: -$$Nest$fputnameHeaderCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14786$$Nest$fputnameHeaderCell(FilterCreateActivity r0, HeaderCellWithRight r1) {
        r0.nameHeaderCell = r1;
    }

    /* renamed from: -$$Nest$fputnewFilterColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14787$$Nest$fputnewFilterColor(FilterCreateActivity r0, int r1) {
        r0.newFilterColor = r1;
    }

    /* renamed from: -$$Nest$fputnewFilterEmoticon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14788$$Nest$fputnewFilterEmoticon(FilterCreateActivity r0, String r1) {
        r0.newFilterEmoticon = r1;
    }

    /* renamed from: -$$Nest$fputnewFilterName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14789$$Nest$fputnewFilterName(FilterCreateActivity r0, CharSequence r1) {
        r0.newFilterName = r1;
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14790$$Nest$mcheckDiscard(FilterCreateActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mcheckDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14791$$Nest$mcheckDoneButton(FilterCreateActivity r0, boolean r1) {
        r0.checkDoneButton(r1);
    }

    /* renamed from: -$$Nest$monDelete, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14792$$Nest$monDelete(FilterCreateActivity r0, TL_chatlists.TL_exportedChatlistInvite r1) {
        r0.onDelete(r1);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14793$$Nest$mprocessDone(FilterCreateActivity r0) {
        r0.processDone();
    }

    public FilterCreateActivity() {
        this(null, null);
    }

    public FilterCreateActivity(MessagesController.DialogFilter r2) {
        this(r2, null);
    }

    public FilterCreateActivity(MessagesController.DialogFilter r7, ArrayList<Long> r8) {
        this.nameRow = -1;
        this.newFilterAnimations = true;
        this.invites = new ArrayList();
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
        this.shiftDp = -5.0f;
        this.filter = r7;
        if (r7 != null) goto L9;
        MessagesController.DialogFilter r72 = new MessagesController.DialogFilter();
        this.filter = r72;
        r72.id = 2;
    L5:
        MessagesController.DialogFilter r73 = getMessagesController().dialogFiltersById.get(this.filter.id);
        MessagesController.DialogFilter r1 = this.filter;
        if (r73 == null) goto L8;
        r1.id++;
        goto L5
    L8:
        r1.name = _UrlKt.FRAGMENT_ENCODE_SET;
        r1.color = (int) (Math.random() * 8.0d);
        this.creatingNew = true;
    L9:
        TextPaint r74 = new TextPaint(1);
        r74.setTextSize(AndroidUtilities.dp(17.0f));
        SpannableStringBuilder r12 = new SpannableStringBuilder(this.filter.name);
        this.newFilterName = r12;
        CharSequence r13 = Emoji.replaceEmoji(r12, r74.getFontMetricsInt(), false);
        this.newFilterName = r13;
        this.newFilterName = MessageObject.replaceAnimatedEmoji(r13, this.filter.entities, r74.getFontMetricsInt());
        boolean r75 = !this.filter.title_noanimate;
        this.newFilterAnimations = r75;
        AnimatedEmojiDrawable.toggleAnimations(this.currentAccount, r75);
        MessagesController.DialogFilter r76 = this.filter;
        this.newFilterEmoticon = r76.emoticon;
        this.newFilterFlags = r76.flags;
        this.newFilterColor = r76.color;
        ArrayList<Long> r77 = new ArrayList(this.filter.alwaysShow);
        this.newAlwaysShow = r77;
        if (r8 == null) goto L12;
        r77.addAll(r8);
    L12:
        this.newNeverShow = new ArrayList(this.filter.neverShow);
        this.newPinned = this.filter.pinnedDialogs.m5171clone();
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(FilterCreateActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(FilterCreateActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(FilterCreateActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$200(FilterCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$300(FilterCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(FilterCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$500(FilterCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(FilterCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(FilterCreateActivity r0) {
        return r0.resourceProvider;
    }

    public static boolean canAddToFolder(TLRPC.Chat r1) {
        if (ChatObject.canUserDoAdminAction(r1, 3) == false) goto L5;
        return true;
    L5:
        if (ChatObject.isPublic(r1) == true) goto L7;
        return false;
    L7:
        if (r1.join_request == false) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean canCreateLink() {
        if (TextUtils.isEmpty(this.newFilterName) == false) goto L7;
        if (TextUtils.isEmpty(this.filter.name) == false) goto L7;
        return false;
    L7:
        if ((this.newFilterFlags & (~(MessagesController.DIALOG_FILTER_FLAG_CHATLIST | MessagesController.DIALOG_FILTER_FLAG_CHATLIST_ADMIN))) == 0) goto L9;
        return false;
    L9:
        if (this.newNeverShow.isEmpty() == true) goto L11;
        return false;
    L11:
        if (this.newAlwaysShow.isEmpty() == true) goto L18;
        return true;
    L18:
        return false;
    }

    private boolean checkDiscard(boolean r3) {
        if (this.doneItem.getAlpha() != 1.0f) goto L12;
        if (r3 == false) goto L14;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        if (this.creatingNew == false) goto L8;
        r32.setTitle(LocaleController.getString(R.string.FilterDiscardNewTitle));
        r32.setMessage(LocaleController.getString(R.string.FilterDiscardNewAlert));
        r32.setPositiveButton(LocaleController.getString(R.string.FilterDiscardNewSave), new FilterCreateActivity$$ExternalSyntheticLambda0(this));
    L9:
        r32.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new FilterCreateActivity$$ExternalSyntheticLambda2(this));
        showDialog(r32.create());
        return false;
    L8:
        r32.setTitle(LocaleController.getString(R.string.FilterDiscardTitle));
        r32.setMessage(LocaleController.getString(R.string.FilterDiscardAlert));
        r32.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new FilterCreateActivity$$ExternalSyntheticLambda1(this));
        goto L9
    L14:
        return false;
    L12:
        return true;
    }

    private void checkDoneButton(boolean r5) {
        boolean r1 = false;
        if (TextUtils.isEmpty(this.newFilterName) == false) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == false) goto L20;
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS) == 0) goto L12;
    L13:
        r1 = true;
    L14:
        if (r1 == true) goto L16;
    L18:
        r0 = r1;
        goto L20
    L16:
        if (this.creatingNew == true) goto L18;
        r0 = hasChanges();
        goto L20
    L12:
        if (this.newAlwaysShow.isEmpty() == true) goto L14;
    L20:
        if (this.doneItem.isEnabled() != r0) goto L22;
        return;
    L22:
        this.doneItem.setEnabled(r0);
        ActionBarMenuItem r12 = this.doneItem;
        float r2 = 0.0f;
        if (r5 == false) goto L37;
        ViewPropertyAnimator r4 = r12.animate();
        if (r0 == false) goto L27;
        float r52 = 1.0f;
    L28:
        ViewPropertyAnimator r42 = r4.alpha(r52);
        if (r0 == false) goto L31;
        float r53 = 1.0f;
    L32:
        ViewPropertyAnimator r43 = r42.scaleX(r53);
        if (r0 == false) goto L35;
        r2 = 1.0f;
    L35:
        r43.scaleY(r2).setDuration(180).start();
        return;
    L31:
        r53 = 0.0f;
        goto L32
    L27:
        r52 = 0.0f;
        goto L28
    L37:
        if (r0 == false) goto L39;
        float r54 = 1.0f;
    L40:
        r12.setAlpha(r54);
        ActionBarMenuItem r55 = this.doneItem;
        if (r0 == false) goto L43;
        float r13 = 1.0f;
    L44:
        r55.setScaleX(r13);
        ActionBarMenuItem r44 = this.doneItem;
        if (r0 == false) goto L47;
        r2 = 1.0f;
    L47:
        r44.setScaleY(r2);
        return;
    L43:
        r13 = 0.0f;
        goto L44
    L39:
        r54 = 0.0f;
        goto L40
    L5:
        if (this.newFilterName.length() > 12) goto L7;
        r0 = true;
        goto L8
    }

    private void deleteFolder(View r3) {
        MessagesController.DialogFilter r32 = this.filter;
        if (r32 != null) goto L5;
    L8:
        AlertDialog.Builder r33 = new AlertDialog.Builder(getParentActivity());
        r33.setTitle(LocaleController.getString(R.string.FilterDelete));
        r33.setMessage(LocaleController.getString(R.string.FilterDeleteAlert));
        r33.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r33.setPositiveButton(LocaleController.getString(R.string.Delete), new FilterCreateActivity$$ExternalSyntheticLambda17(this));
        AlertDialog r34 = r33.create();
        showDialog(r34);
        TextView r2 = (TextView) r34.getButton(-1);
        if (r2 == null) goto L12;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L12:
        return;
    L5:
        if (r32.isChatlist() == false) goto L8;
        FolderBottomSheet.showForDeletion(this, this.filter.id, new FilterCreateActivity$$ExternalSyntheticLambda16(this));
    }

    private void fillFilterName() {
        if (this.creatingNew == true) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(this.newFilterName) == false) goto L7;
    L9:
        Pair<String, String> r0 = FolderIcons.getEmoticonFromFlags(this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_ALL_CHATS);
        String r1 = r0.first;
        String r02 = r0.second;
        if (r1 != null) goto L12;
    L14:
        this.newFilterName = r1;
        this.newFilterEmoticon = r02;
        HeaderCellColorPreview r03 = this.folderTagsHeader;
        if (r03 == null) goto L17;
        r03.setPreviewText(AnimatedEmojiSpan.cloneSpans(r1, -1, r03.getPreviewTextPaint().getFontMetricsInt(), 0.5f), false);
    L17:
        RecyclerView.ViewHolder r04 = this.listView.findViewHolderForAdapterPosition(this.nameRow);
        if (r04 == null) goto L22;
        this.adapter.onViewAttachedToWindow(r04);
        return;
    L22:
        return;
    L12:
        if (r1.length() <= 12) goto L14;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L7:
        if (this.nameChangedManually == false) goto L9;
    }

    private boolean hasChanges() {
        this.hasUserChanged = false;
        if (this.filter.alwaysShow.size() == this.newAlwaysShow.size()) goto L6;
        this.hasUserChanged = true;
    L6:
        if (this.filter.neverShow.size() == this.newNeverShow.size()) goto L8;
        this.hasUserChanged = true;
    L8:
        MessagesController.DialogFilter r0 = this.filter;
        if (r0.color == this.newFilterColor) goto L12;
        this.hasUserChanged = true;
    L12:
        if (this.hasUserChanged == true) goto L19;
        Collections.sort(r0.alwaysShow);
        Collections.sort(this.newAlwaysShow);
        if (this.filter.alwaysShow.equals(this.newAlwaysShow) == true) goto L16;
        this.hasUserChanged = true;
    L16:
        Collections.sort(this.filter.neverShow);
        Collections.sort(this.newNeverShow);
        if (this.filter.neverShow.equals(this.newNeverShow) == true) goto L19;
        this.hasUserChanged = true;
    L19:
        MessagesController.DialogFilter r02 = this.filter;
        if (r02.title_noanimate == (!this.newFilterAnimations)) goto L23;
        return true;
    L23:
        if (TextUtils.equals(r02.name, this.newFilterName) == true) goto L26;
        return true;
    L26:
        if (TextUtils.equals(this.filter.emoticon, this.newFilterEmoticon) == true) goto L29;
        return true;
    L29:
        if (this.filter.flags == this.newFilterFlags) goto L32;
        return true;
    L32:
        return this.hasUserChanged;
    }

    public static void hideNew(int r3) {
        MessagesController.getGlobalMainSettings().edit().putBoolean("n_" + r3, true).apply();
    }

    private /* synthetic */ void lambda$checkDiscard$19(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$checkDiscard$20(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$checkDiscard$21(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$7(ItemInner r3) {
        FilterChatlistActivity r0 = new FilterChatlistActivity(this.filter, ItemInner.m14811$$Nest$fgetlink(r3));
        r0.setOnEdit(new FilterCreateActivity$$ExternalSyntheticLambda23(this));
        r0.setOnDelete(new FilterCreateActivity$$ExternalSyntheticLambda24(this));
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createView$8(View r3, int r4) {
        if (getParentActivity() == null) goto L35;
        ItemInner r42 = this.items.get(r4);
        if (r42 != null) goto L9;
        return;
    L9:
        if (ItemInner.m14813$$Nest$fgetonClickListener(r42) == null) goto L12;
        ItemInner.m14813$$Nest$fgetonClickListener(r42).onClick(r3);
        return;
    L12:
        int r0 = r42.viewType;
        if (r0 != 1) goto L17;
        UserCell r32 = (UserCell) r3;
        showRemoveAlert(r42, r32.getName(), r32.getCurrentObject(), ItemInner.m14809$$Nest$fgetinclude(r42));
        return;
    L17:
        if (r0 != 7) goto L25;
        FilterCreateActivity$$ExternalSyntheticLambda20 r33 = new FilterCreateActivity$$ExternalSyntheticLambda20(this, r42);
        if (this.doneItem.isEnabled() == false) goto L22;
        save(false, r33);
        return;
    L22:
        r33.run();
        return;
    L25:
        if (r0 != 8) goto L27;
    L32:
        onClickCreateLink(r3);
        return;
    L27:
        if (r0 == 4) goto L29;
        return;
    L29:
        if (ItemInner.m14808$$Nest$fgeticonResId(r42) == R.drawable.msg2_link2) goto L32;
        return;
    }

    private /* synthetic */ boolean lambda$createView$9(View r3, int r4) {
        ItemInner r42 = this.items.get(r4);
        if (r42 != null) goto L6;
        return false;
    L6:
        if ((r3 instanceof UserCell) == false) goto L9;
        UserCell r32 = (UserCell) r3;
        showRemoveAlert(r42, r32.getName(), r32.getCurrentObject(), ItemInner.m14809$$Nest$fgetinclude(r42));
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$deleteFolder$14(Boolean r1) {
        finishFragment();
    }

    private /* synthetic */ void lambda$deleteFolder$15(AlertDialog r2) {
        if (r2 != null) goto L9;
    L7:
        getMessagesController().removeFilter(this.filter);
        getMessagesStorage().deleteDialogFilter(this.filter);
        finishFragment();
        return;
    L9:
        r2.dismiss();     // Catch: Exception -> L5
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$deleteFolder$16(AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new FilterCreateActivity$$ExternalSyntheticLambda29(this, r1));
    }

    private /* synthetic */ void lambda$deleteFolder$17(AlertDialog r3, int r4) {
        if (getParentActivity() == null) goto L5;
        AlertDialog r32 = new AlertDialog(getParentActivity(), 3);
        r32.setCanCancel(false);
        r32.show();
    L6:
        TLRPC.TL_messages_updateDialogFilter r42 = new TLRPC.TL_messages_updateDialogFilter();
        r42.id = this.filter.id;
        getConnectionsManager().sendRequest(r42, new FilterCreateActivity$$ExternalSyntheticLambda25(this, r32));
        return;
    L5:
        r32 = null;
        goto L6
    }

    private /* synthetic */ void lambda$getThemeDescriptions$28() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof UserCell) == false) goto L9;
        ((UserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private /* synthetic */ void lambda$loadInvites$0(TLObject r4) {
        this.loadingInvites = false;
        if ((r4 instanceof TL_chatlists.TL_chatlists_exportedInvites) == false) goto L5;
        TL_chatlists.TL_chatlists_exportedInvites r42 = (TL_chatlists.TL_chatlists_exportedInvites) r4;
        getMessagesController().putChats(r42.chats, false);
        getMessagesController().putUsers(r42.users, false);
        this.invites.clear();
        this.invites.addAll(r42.invites);
        updateRows();
    L5:
        this.requestingInvitesReqId = 0;
    }

    private /* synthetic */ void lambda$loadInvites$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new FilterCreateActivity$$ExternalSyntheticLambda28(this, r1));
    }

    private /* synthetic */ void lambda$onClickCreateLink$10(TL_chatlists.TL_chatlists_exportedChatlistInvite r1) {
        onEdit(r1.invite);
    }

    private /* synthetic */ void lambda$onClickCreateLink$11(TLRPC.TL_error r3, TLObject r4) {
        if (processErrors(r3, this, BulletinFactory.of(this)) == true) goto L5;
        return;
    L5:
        if ((r4 instanceof TL_chatlists.TL_chatlists_exportedChatlistInvite) == false) goto L9;
        hideNew(0);
        getMessagesController().loadRemoteFilters(true);
        TL_chatlists.TL_chatlists_exportedChatlistInvite r42 = (TL_chatlists.TL_chatlists_exportedChatlistInvite) r4;
        FilterChatlistActivity r32 = new FilterChatlistActivity(this.filter, r42.invite);
        r32.setOnEdit(new FilterCreateActivity$$ExternalSyntheticLambda23(this));
        r32.setOnDelete(new FilterCreateActivity$$ExternalSyntheticLambda24(this));
        presentFragment(r32);
        AndroidUtilities.runOnUIThread(new FilterCreateActivity$$ExternalSyntheticLambda32(this, r42), 200);
        return;
    }

    private /* synthetic */ void lambda$onClickCreateLink$12(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new FilterCreateActivity$$ExternalSyntheticLambda31(this, r3, r2));
    }

    private /* synthetic */ void lambda$onClickCreateLink$13() {
        getMessagesController().updateFilterDialogs(this.filter);
        ArrayList<TLRPC.InputPeer> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.filter.alwaysShow.size()) goto L12;
        long r2 = this.filter.alwaysShow.get(r1).longValue();
        if (r2 >= 0) goto L10;
        if (canAddToFolder(getMessagesController().getChat(Long.valueOf(-r2))) == false) goto L10;
        r0.add(getMessagesController().getInputPeer(r2));
    L10:
        r1 = r1 + 1;
        goto L4
    L12:
        if (getUserConfig().isPremium() == false) goto L14;
        int r12 = getMessagesController().dialogFiltersChatsLimitPremium;
    L16:
        if (r0.size() <= r12) goto L20;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 4, this.currentAccount, null));
        return;
    L20:
        if (r0.isEmpty() == true) goto L23;
        TL_chatlists.TL_chatlists_exportChatlistInvite r9 = new TL_chatlists.TL_chatlists_exportChatlistInvite();
        TL_chatlists.TL_inputChatlistDialogFilter r13 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r9.chatlist = r13;
        r13.filter_id = this.filter.id;
        r9.peers = r0;
        r9.title = _UrlKt.FRAGMENT_ENCODE_SET;
        getConnectionsManager().sendRequest(r9, new FilterCreateActivity$$ExternalSyntheticLambda30(this));
        return;
    L23:
        FilterChatlistActivity r92 = new FilterChatlistActivity(this.filter, null);
        r92.setOnEdit(new FilterCreateActivity$$ExternalSyntheticLambda23(this));
        r92.setOnDelete(new FilterCreateActivity$$ExternalSyntheticLambda24(this));
        presentFragment(r92);
        return;
    L14:
        r12 = getMessagesController().dialogFiltersChatsLimitDefault;
        goto L16
    }

    private /* synthetic */ void lambda$onUpdate$29(boolean r3, int r4) {
        BulletinFactory r2 = BulletinFactory.of(this);
        if (r3 == false) goto L5;
        int r0 = R.raw.folder_in;
    L7:
        if (r3 == false) goto L9;
        String r32 = LocaleController.formatPluralString("FolderLinkAddedChats", r4, new Object[0]);
    L10:
        r2.createSimpleBulletin(r0, r32, LocaleController.getString(R.string.FolderLinkChatlistUpdate)).setDuration(5000).show();
        return;
    L9:
        r32 = LocaleController.formatPluralString("FolderLinkRemovedChats", r4, new Object[0]);
        goto L10
    L5:
        r0 = R.raw.folder_out;
        goto L7
    }

    private /* synthetic */ void lambda$processDone$23() {
        if (this.doNotCloseWhenSave == false) goto L6;
        this.doNotCloseWhenSave = false;
        Paint.FontMetricsInt r1 = this.actionBar.getTitleFontMetricsInt();
        Spannable r0 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(this.filter.name, r1, false), this.filter.entities, r1);
        this.actionBar.setTitleAnimated(r0, true, 220);
        return;
    L6:
        finishFragment();
    }

    private /* synthetic */ void lambda$save$24(Runnable r4) {
        this.hasUserChanged = false;
        this.creatingNew = false;
        MessagesController.DialogFilter r1 = this.filter;
        r1.flags = this.newFilterFlags;
        checkDoneButton(true);
        getNotificationCenter().postNotificationName(NotificationCenter.dialogFiltersUpdated, new Object[0]);
        if (r4 == null) goto L6;
        r4.run();
        return;
    }

    private /* synthetic */ void lambda$selectChatsFor$18(boolean r5, ArrayList r6, int r7) {
        this.newFilterFlags = r7;
        if (r5 == false) goto L21;
        onUpdate(true, this.newAlwaysShow, r6);
        this.newAlwaysShow = r6;
        int r52 = 0;
    L6:
        if (r52 >= this.newAlwaysShow.size()) goto L8;
        this.newNeverShow.remove(this.newAlwaysShow.get(r52));
        r52 = r52 + 1;
        goto L6
    L8:
        ArrayList r53 = new ArrayList();
        int r62 = this.newPinned.size();
        int r0 = 0;
    L9:
        if (r0 >= r62) goto L18;
        long r1 = this.newPinned.keyAt(r0);
        Long r3 = Long.valueOf(r1);
        if (DialogObject.isEncryptedDialog(r1) == true) goto L17;
        if (this.newAlwaysShow.contains(r3) == true) goto L17;
        r53.add(r3);
    L17:
        r0 = r0 + 1;
        goto L9
    L18:
        int r63 = r53.size();
        int r02 = 0;
    L19:
        if (r02 >= r63) goto L25;
        this.newPinned.delete(((Long) r53.get(r02)).longValue());
        r02 = r02 + 1;
    L25:
        fillFilterName();
        checkDoneButton(false);
        updateRows();
        return;
    L21:
        onUpdate(false, this.newNeverShow, r6);
        this.newNeverShow = r6;
        int r54 = 0;
    L23:
        if (r54 >= this.newNeverShow.size()) goto L25;
        Long r64 = this.newNeverShow.get(r54);
        this.newAlwaysShow.remove(r64);
        this.newPinned.delete(r64.longValue());
        r54 = r54 + 1;
        goto L23
    }

    private /* synthetic */ void lambda$showRemoveAlert$22(ItemInner r3, boolean r4, AlertDialog r5, int r6) {
        if (ItemInner.m14807$$Nest$fgetflags(r3) <= 0) goto L5;
        int r52 = this.newFilterFlags;
        this.newFilterFlags = (~ItemInner.m14807$$Nest$fgetflags(r3)) & r52;
    L9:
        fillFilterName();
        updateRows();
        checkDoneButton(true);
        if (r4 == false) goto L13;
        onUpdate(false, 1);
        return;
    L13:
        return;
    L5:
        if (r4 == false) goto L7;
        ArrayList<Long> r53 = this.newAlwaysShow;
    L8:
        r53.remove(Long.valueOf(ItemInner.m14806$$Nest$fgetdid(r3)));
        goto L9
    L7:
        r53 = this.newNeverShow;
        goto L8
    }

    private /* synthetic */ void lambda$updateRows$2(View r4) {
        this.newFilterAnimations = !this.newFilterAnimations;
        HeaderCellWithRight r42 = this.nameHeaderCell;
        if (r42 == null) goto L13;
        AnimatedTextView r43 = HeaderCellWithRight.m14804$$Nest$fgetrightTextView(r42);
        if (hasAnimatedEmojis(this.newFilterName) == true) goto L7;
        String r1 = null;
    L12:
        r43.setText(r1);
        goto L13
    L7:
        if (this.newFilterAnimations == false) goto L9;
        int r12 = R.string.FilterNameAnimationsDisable;
    L10:
        r1 = LocaleController.getString(r12);
        goto L12
    L9:
        r12 = R.string.FilterNameAnimationsEnable;
    L13:
        AnimatedEmojiDrawable.toggleAnimations(this.currentAccount, this.newFilterAnimations);
        checkDoneButton(true);
        ActionBar r44 = this.actionBar;
        if (r44 == null) goto L29;
        int r0 = 26;
        if (r44.getTitleTextView() == null) goto L23;
        SimpleTextView r45 = this.actionBar.getTitleTextView();
        if (this.newFilterAnimations == false) goto L20;
        int r2 = 0;
    L21:
        r45.setEmojiCacheType(r2);
        goto L23
    L20:
        r2 = 26;
    L23:
        if (this.actionBar.getTitleTextView2() == null) goto L30;
        SimpleTextView r46 = this.actionBar.getTitleTextView2();
        if (this.newFilterAnimations == false) goto L27;
        r0 = 0;
    L27:
        r46.setEmojiCacheType(r0);
        return;
    L30:
        return;
    }

    private /* synthetic */ void lambda$updateRows$3(View r1) {
        selectChatsFor(true);
    }

    private /* synthetic */ void lambda$updateRows$4(View r1) {
        this.includeExpanded = true;
        updateRows();
    }

    private /* synthetic */ void lambda$updateRows$5(View r1) {
        selectChatsFor(false);
    }

    private /* synthetic */ void lambda$updateRows$6(View r1) {
        this.excludeExpanded = true;
        updateRows();
    }

    private void onClickCreateLink(View r3) {
        if (this.creatingNew == false) goto L9;
        if (this.doneItem.getAlpha() <= 0.0f) goto L9;
        float r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r3, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        this.doNotCloseWhenSave = true;
        showSaveHint();
        return;
    L9:
        if (canCreateLink() == true) goto L30;
        float r02 = -this.shiftDp;
        this.shiftDp = r02;
        AndroidUtilities.shakeViewSpring(r3, r02);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (TextUtils.isEmpty(this.newFilterName) == false) goto L17;
        if (TextUtils.isEmpty(this.filter.name) == false) goto L17;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.FilterInviteErrorEmptyName)).show();
        return;
    L17:
        if ((this.newFilterFlags & (~(MessagesController.DIALOG_FILTER_FLAG_CHATLIST | MessagesController.DIALOG_FILTER_FLAG_CHATLIST_ADMIN))) == 0) goto L25;
        if (this.newNeverShow.isEmpty() == true) goto L22;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.FilterInviteErrorTypesExcluded)).show();
        return;
    L22:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.FilterInviteErrorTypes)).show();
        return;
    L25:
        if (this.newAlwaysShow.isEmpty() == false) goto L28;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.FilterInviteErrorEmpty)).show();
        return;
    L28:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.FilterInviteErrorExcluded)).show();
        return;
    L30:
        save(false, new FilterCreateActivity$$ExternalSyntheticLambda26(this));
    }

    private void onDelete(TL_chatlists.TL_exportedChatlistInvite r4) {
        if (r4 == null) goto L21;
        int r0 = 0;
    L6:
        if (r0 >= this.invites.size()) goto L11;
        if (TextUtils.equals(this.invites.get(r0).url, r4.url) == true) goto L12;
        r0 = r0 + 1;
    L12:
        if (r0 < 0) goto L20;
        this.invites.remove(r0);
        if (this.invites.isEmpty() == false) goto L16;
        this.filter.flags &= ~MessagesController.DIALOG_FILTER_FLAG_CHATLIST;
    L16:
        updateRows();
        return;
    L20:
        return;
    L11:
        r0 = -1;
        goto L12
    }

    private void onEdit(TL_chatlists.TL_exportedChatlistInvite r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r0 = 0;
    L6:
        if (r0 >= this.invites.size()) goto L11;
        if (TextUtils.equals(this.invites.get(r0).url, r4.url) == true) goto L12;
        r0 = r0 + 1;
    L12:
        ArrayList<TL_chatlists.TL_exportedChatlistInvite> r1 = this.invites;
        if (r0 >= 0) goto L15;
        r1.add(r4);
    L16:
        updateRows();
        return;
    L15:
        r1.set(r0, r4);
        goto L16
    L11:
        r0 = -1;
        goto L12
    }

    private void onUpdate(boolean r2, int r3) {
        if (this.showedUpdateBulletin == true) goto L19;
        MessagesController.DialogFilter r0 = this.filter;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isChatlist() == true) goto L10;
        return;
    L10:
        if (this.filter.isMyChatlist() == false) goto L17;
        this.showedUpdateBulletin = true;
        this.showBulletinOnResume = new FilterCreateActivity$$ExternalSyntheticLambda22(this, r2, r3);
        if (getLayoutContainer() == null) goto L18;
        this.showBulletinOnResume.run();
        this.showBulletinOnResume = null;
        return;
    L18:
        return;
    L17:
        return;
    }

    private void onUpdate(boolean r6, ArrayList<Long> r7, ArrayList<Long> r8) {
        if (r7 == null) goto L19;
        if (r8 == null) goto L19;
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r7.size()) goto L12;
        if (r8.contains(r7.get(r1)) == true) goto L11;
        r2 = r2 + 1;
    L11:
        r1 = r1 + 1;
        goto L7
    L12:
        int r12 = 0;
        int r3 = 0;
    L14:
        if (r12 >= r8.size()) goto L24;
        if (r7.contains(r8.get(r12)) == true) goto L18;
        r3 = r3 + 1;
    L18:
        r12 = r12 + 1;
    L24:
        if (r6 == false) goto L32;
        if (r3 <= 0) goto L29;
        if (r3 <= r2) goto L29;
        onUpdate(true, r3);
        return;
    L29:
        if (r2 <= 0) goto L41;
        onUpdate(false, r2);
        return;
    L41:
        return;
    L32:
        if (r3 <= 0) goto L42;
        onUpdate(false, r3);
        return;
    L42:
        return;
    L19:
        if (r7 == null) goto L21;
        r2 = r7.size();
        r3 = 0;
        goto L24
    L21:
        if (r8 == null) goto L23;
        r3 = r8.size();
        r2 = 0;
        goto L24
    L23:
        r2 = 0;
        r3 = 0;
        goto L24
    }

    private static void processAddFilter(MessagesController.DialogFilter r1, int r2, String r3, String r4, ArrayList<TLRPC.MessageEntity> r5, boolean r6, int r7, ArrayList<Long> r8, ArrayList<Long> r9, boolean r10, boolean r11, boolean r12, boolean r13, BaseFragment r14, Runnable r15) {
        if (r1.flags != r2) goto L5;
        if (r12 == true) goto L5;
    L8:
        r1.flags = r2;
        r1.name = r4;
        r1.emoticon = r3;
        r1.entities = r5;
        r1.color = r7;
        r1.neverShow = r9;
        r1.alwaysShow = r8;
        r1.title_noanimate = r6;
        if (r10 == false) goto L11;
        r14.getMessagesController().addFilter(r1, r11);
    L12:
        r14.getMessagesStorage().saveDialogFilter(r1, r11, true);
        if (r11 == false) goto L18;
        TLRPC.TL_messages_updateDialogFiltersOrder r16 = new TLRPC.TL_messages_updateDialogFiltersOrder();
        ArrayList<MessagesController.DialogFilter> r22 = r14.getMessagesController().getDialogFilters();
        int r32 = r22.size();
        int r42 = 0;
    L15:
        if (r42 >= r32) goto L17;
        r16.order.add(Integer.valueOf(r22.get(r42).id));
        r42 = r42 + 1;
        goto L15
    L17:
        r14.getConnectionsManager().sendRequest(r16, null);
    L18:
        if (r15 == null) goto L22;
        r15.run();
        return;
    L22:
        return;
    L11:
        r14.getMessagesController().onFilterUpdate(r1);
    L5:
        r1.pendingUnreadCount = -1;
        if (r13 == false) goto L8;
        r1.unreadCount = -1;
        goto L8
    }

    private void processDone() {
        HintView r0 = this.saveHintView;
        if (r0 == null) goto L5;
        r0.hide(true);
        this.saveHintView = null;
    L5:
        save(true, new FilterCreateActivity$$ExternalSyntheticLambda18(this));
    }

    public static boolean processErrors(TLRPC.TL_error r9, BaseFragment r10, BulletinFactory r11) {
        if (r9 != null) goto L5;
    L35:
        return true;
    L5:
        if (TextUtils.isEmpty(r9.text) == true) goto L35;
        if ("INVITE_PEERS_TOO_MUCH".equals(r9.text) == false) goto L11;
        new LimitReachedBottomSheet(r10, r10.getContext(), 4, r10.getCurrentAccount(), null).show();
        goto L35
    L11:
        if ("PEERS_LIST_EMPTY".equals(r9.text) == false) goto L14;
        r11.createErrorBulletin(LocaleController.getString(R.string.FolderLinkNoChatsError)).show();
        goto L35
    L14:
        if ("USER_CHANNELS_TOO_MUCH".equals(r9.text) == false) goto L17;
        r11.createErrorBulletin(LocaleController.getString(R.string.FolderLinkOtherAdminLimitError)).show();
        goto L35
    L17:
        if ("CHANNELS_TOO_MUCH".equals(r9.text) == false) goto L20;
        new LimitReachedBottomSheet(r10, r10.getContext(), 5, r10.getCurrentAccount(), null).show();
        goto L35
    L20:
        if ("INVITES_TOO_MUCH".equals(r9.text) == false) goto L23;
        new LimitReachedBottomSheet(r10, r10.getContext(), 12, r10.getCurrentAccount(), null).show();
        goto L35
    L23:
        if ("CHATLISTS_TOO_MUCH".equals(r9.text) == false) goto L26;
        new LimitReachedBottomSheet(r10, r10.getContext(), 13, r10.getCurrentAccount(), null).show();
        goto L35
    L26:
        if ("INVITE_SLUG_EXPIRED".equals(r9.text) == false) goto L29;
        r11.createErrorBulletin(LocaleController.getString(R.string.NoFolderFound)).show();
        goto L35
    L29:
        if ("FILTER_INCLUDE_TOO_MUCH".equals(r9.text) == false) goto L32;
        new LimitReachedBottomSheet(r10, r10.getContext(), 4, r10.getCurrentAccount(), null).show();
        goto L35
    L32:
        if ("DIALOG_FILTERS_TOO_MUCH".equals(r9.text) == false) goto L34;
        new LimitReachedBottomSheet(r10, r10.getContext(), 3, r10.getCurrentAccount(), null).show();
        goto L35
    L34:
        r11.createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
        goto L35
    }

    private void save(boolean r18, Runnable r19) {
        CharSequence[] r2 = {this.newFilterName};
        saveFilterToServer(this.filter, this.newFilterFlags, r2[0].toString(), getMediaDataController().getEntities(r2, false), !this.newFilterAnimations, this.newFilterColor, this.newFilterEmoticon, this.newAlwaysShow, this.newNeverShow, this.newPinned, this.creatingNew, false, this.hasUserChanged, true, r18, this, new FilterCreateActivity$$ExternalSyntheticLambda27(this, r19));
    }

    public static void saveFilterToServer(MessagesController.DialogFilter r24, int r25, String r26, ArrayList<TLRPC.MessageEntity> r27, boolean r28, int r29, String r30, ArrayList<Long> r31, ArrayList<Long> r32, LongSparseIntArray r33, boolean r34, boolean r35, boolean r36, boolean r37, boolean r38, BaseFragment r39, Runnable r40) {
        LongSparseIntArray r1 = r33;
        if (r39 != null) goto L5;
        return;
    L5:
        if (r39.getParentActivity() == null) goto L107;
        int r3 = 3;
        if (r38 == false) goto L10;
        AlertDialog r5 = new AlertDialog(r39.getParentActivity(), 3);
        r5.setCanCancel(false);
        r5.show();
    L11:
        TLRPC.TL_messages_updateDialogFilter r7 = new TLRPC.TL_messages_updateDialogFilter();
        r7.id = r24.id;
        int r9 = 1;
        r7.flags |= 1;
        TLRPC.TL_dialogFilter r8 = new TLRPC.TL_dialogFilter();
        r7.filter = r8;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_CONTACTS) == 0) goto L14;
        boolean r10 = true;
    L15:
        r8.contacts = r10;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS) == 0) goto L18;
        boolean r102 = true;
    L19:
        r8.non_contacts = r102;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_GROUPS) == 0) goto L22;
        boolean r103 = true;
    L23:
        r8.groups = r103;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_CHANNELS) == 0) goto L26;
        boolean r104 = true;
    L27:
        r8.broadcasts = r104;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_BOTS) == 0) goto L30;
        boolean r105 = true;
    L31:
        r8.bots = r105;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED) == 0) goto L34;
        boolean r106 = true;
    L35:
        r8.exclude_muted = r106;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ) == 0) goto L38;
        boolean r107 = true;
    L39:
        r8.exclude_read = r107;
        if ((r25 & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED) == 0) goto L42;
        boolean r108 = true;
    L43:
        r8.exclude_archived = r108;
        r8.id = r24.id;
        r8.title = new TLRPC.TL_textWithEntities();
        TLRPC.TL_dialogFilter r82 = r7.filter;
        TLRPC.TL_textWithEntities r109 = r82.title;
        r109.text = r26;
        r109.entities = r27;
        r82.title_noanimate = r28;
        if (r29 >= 0) goto L46;
        r82.flags &= -134217729;
        r82.color = 0;
    L47:
        if (r30 == null) goto L49;
        r82.emoticon = r30;
        r82.flags |= 33554432;
    L49:
        MessagesController r83 = r39.getMessagesController();
        ArrayList<Long> r13 = new ArrayList();
        if (r1.size() == 0) goto L59;
        int r14 = r1.size();
        int r15 = 0;
    L52:
        if (r15 >= r14) goto L58;
        long r16 = r1.keyAt(r15);
        if (DialogObject.isEncryptedDialog(r16) == true) goto L57;
        r13.add(Long.valueOf(r16));
    L57:
        r15 = r15 + 1;
        goto L52
    L58:
        Collections.sort(r13, new FilterCreateActivity$$ExternalSyntheticLambda11(r1));
    L59:
        int r4 = 0;
    L60:
        if (r4 >= r3) goto L89;
        if (r4 != 0) goto L63;
        ArrayList<TLRPC.InputPeer> r142 = r7.filter.include_peers;
        ArrayList<Long> r152 = r31;
    L67:
        int r310 = r152.size();
        int r92 = 0;
    L68:
        if (r92 >= r310) goto L88;
        Long r0 = r152.get(r92);
        int r19 = r310;
        long r2 = r0.longValue();
        if (r4 != 0) goto L75;
        if (r1.indexOfKey(r2) < 0) goto L75;
    L87:
        r92 = r92 + 1;
        r1 = r33;
        r310 = r19;
    L75:
        if (DialogObject.isEncryptedDialog(r2) == true) goto L87;
        if (r2 <= 0) goto L81;
        TLRPC.User r02 = r83.getUser(r0);
        if (r02 == null) goto L87;
        TLRPC.TL_inputPeerUser r12 = new TLRPC.TL_inputPeerUser();
        r12.user_id = r2;
        r12.access_hash = r02.access_hash;
        r142.add(r12);
        goto L87
    L81:
        long r03 = -r2;
        TLRPC.Chat r22 = r83.getChat(Long.valueOf(r03));
        if (r22 == null) goto L87;
        if (ChatObject.isChannel(r22) == false) goto L86;
        TLRPC.TL_inputPeerChannel r311 = new TLRPC.TL_inputPeerChannel();
        r311.channel_id = r03;
        r311.access_hash = r22.access_hash;
        r142.add(r311);
        goto L87
    L86:
        TLRPC.TL_inputPeerChat r23 = new TLRPC.TL_inputPeerChat();
        r23.chat_id = r03;
        r142.add(r23);
        goto L87
    L88:
        r4 = r4 + 1;
        r1 = r33;
        r3 = 3;
        r9 = 1;
        goto L60
    L63:
        TLRPC.TL_dialogFilter r143 = r7.filter;
        if (r4 != r9) goto L66;
        r142 = r143.exclude_peers;
        r152 = r32;
        goto L67
    L66:
        r142 = r143.pinned_peers;
        r152 = r13;
        goto L67
    L89:
        r39.getConnectionsManager().sendRequest(r7, new FilterCreateActivity$$ExternalSyntheticLambda12(r38, r5, r24, r25, r30, r26, r27, r28, r29, r31, r32, r34, r35, r36, r37, r39, r40));
        if (r38 == true) goto L106;
        processAddFilter(r24, r25, r30, r26, r27, r28, r29, r31, r32, r34, r35, r36, r37, r39, null);
        return;
    L106:
        return;
    L46:
        r82.flags |= 134217728;
        r82.color = r29;
        goto L47
    L42:
        r108 = false;
        goto L43
    L38:
        r107 = false;
        goto L39
    L34:
        r106 = false;
        goto L35
    L30:
        r105 = false;
        goto L31
    L26:
        r104 = false;
        goto L27
    L22:
        r103 = false;
        goto L23
    L18:
        r102 = false;
        goto L19
    L14:
        r10 = false;
        goto L15
    L10:
        r5 = null;
        goto L11
    }

    private void selectChatsFor(boolean r4) {
        if (r4 == false) goto L4;
        ArrayList<Long> r0 = this.newAlwaysShow;
    L5:
        UsersSelectActivity r1 = new UsersSelectActivity(r4, r0, this.newFilterFlags);
        r1.noChatTypes = this.filter.isChatlist();
        r1.setDelegate(new FilterCreateActivity$$ExternalSyntheticLambda15(this, r4));
        presentFragment(r1);
        return;
    L4:
        r0 = this.newNeverShow;
        goto L5
    }

    private void showRemoveAlert(ItemInner r3, CharSequence r4, Object r5, boolean r6) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        if (r6 == false) goto L11;
        r0.setTitle(LocaleController.getString(R.string.FilterRemoveInclusionTitle));
        if ((r5 instanceof String) == false) goto L8;
        r0.setMessage(LocaleController.formatString(R.string.FilterRemoveInclusionText, new Object[]{r4}));
    L18:
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.StickersRemove), new FilterCreateActivity$$ExternalSyntheticLambda21(this, r3, r6));
        AlertDialog r32 = r0.create();
        showDialog(r32);
        TextView r2 = (TextView) r32.getButton(-1);
        if (r2 == null) goto L22;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L22:
        return;
    L8:
        if ((r5 instanceof TLRPC.User) == false) goto L10;
        r0.setMessage(LocaleController.formatString(R.string.FilterRemoveInclusionUserText, new Object[]{r4}));
        goto L18
    L10:
        r0.setMessage(LocaleController.formatString(R.string.FilterRemoveInclusionChatText, new Object[]{r4}));
        goto L18
    L11:
        r0.setTitle(LocaleController.getString(R.string.FilterRemoveExclusionTitle));
        if ((r5 instanceof String) == false) goto L15;
        r0.setMessage(LocaleController.formatString(R.string.FilterRemoveExclusionText, new Object[]{r4}));
        goto L18
    L15:
        if ((r5 instanceof TLRPC.User) == false) goto L17;
        r0.setMessage(LocaleController.formatString(R.string.FilterRemoveExclusionUserText, new Object[]{r4}));
        goto L18
    L17:
        r0.setMessage(LocaleController.formatString(R.string.FilterRemoveExclusionChatText, new Object[]{r4}));
        goto L18
    }

    private void showSaveHint() {
        HintView r0 = this.saveHintView;
        if (r0 != null) goto L5;
    L7:
        AnonymousClass5 r02 = new AnonymousClass5(this, getContext(), 6, true);
        this.saveHintView = r02;
        r02.textView.setMaxWidth(AndroidUtilities.displaySize.x);
        this.saveHintView.setExtraTranslationY(AndroidUtilities.dp(-16.0f));
        this.saveHintView.setText(LocaleController.getString(R.string.FilterFinishCreating));
        ViewGroup.MarginLayoutParams r03 = new ViewGroup.MarginLayoutParams(-2, -2);
        r03.rightMargin = AndroidUtilities.dp(3.0f);
        getParentLayout().getOverlayContainerView().addView(this.saveHintView, r03);
        this.saveHintView.showForView(this.doneItem, true);
        return;
    L5:
        if (r0.getVisibility() != 0) goto L7;
    }

    private void updateRows() {
        updateRows(true);
    }

    private void updateRows(boolean r12) {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        ArrayList<ItemInner> r0 = this.items;
        String r1 = LocaleController.getString(R.string.FilterNameHeader);
        if (hasAnimatedEmojis(this.newFilterName) == true) goto L5;
        String r2 = null;
    L10:
        r0.add(ItemInner.asAnimatedHeader(r1, r2, new FilterCreateActivity$$ExternalSyntheticLambda3(this)));
        this.nameRow = this.items.size();
        this.items.add(ItemInner.asEdit());
        this.items.add(ItemInner.asShadow(null));
        this.items.add(ItemInner.asHeader(LocaleController.getString(R.string.FilterInclude)));
        this.items.add(ItemInner.asButton(R.drawable.msg2_chats_add, LocaleController.getString(R.string.FilterAddChats), false).whenClicked(new FilterCreateActivity$$ExternalSyntheticLambda4(this)));
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_CONTACTS) == 0) goto L14;
        this.items.add(ItemInner.asChat(true, LocaleController.getString(R.string.FilterContacts), "contacts", MessagesController.DIALOG_FILTER_FLAG_CONTACTS));
    L14:
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS) == 0) goto L17;
        this.items.add(ItemInner.asChat(true, LocaleController.getString(R.string.FilterNonContacts), "non_contacts", MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS));
    L17:
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_GROUPS) == 0) goto L20;
        this.items.add(ItemInner.asChat(true, LocaleController.getString(R.string.FilterGroups), "groups", MessagesController.DIALOG_FILTER_FLAG_GROUPS));
    L20:
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_CHANNELS) == 0) goto L23;
        this.items.add(ItemInner.asChat(true, LocaleController.getString(R.string.FilterChannels), "channels", MessagesController.DIALOG_FILTER_FLAG_CHANNELS));
    L23:
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_BOTS) == 0) goto L26;
        this.items.add(ItemInner.asChat(true, LocaleController.getString(R.string.FilterBots), "bots", MessagesController.DIALOG_FILTER_FLAG_BOTS));
    L26:
        if (this.newAlwaysShow.isEmpty() == false) goto L28;
    L40:
        this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.FilterIncludeInfo)));
        if (this.filter.isChatlist() == true) goto L68;
        this.items.add(ItemInner.asHeader(LocaleController.getString(R.string.FilterExclude)));
        this.items.add(ItemInner.asButton(R.drawable.msg2_chats_add, LocaleController.getString(R.string.FilterRemoveChats), false).whenClicked(new FilterCreateActivity$$ExternalSyntheticLambda6(this)));
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED) == 0) goto L46;
        this.items.add(ItemInner.asChat(false, LocaleController.getString(R.string.FilterMuted), "muted", MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED));
    L46:
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ) == 0) goto L49;
        this.items.add(ItemInner.asChat(false, LocaleController.getString(R.string.FilterRead), "read", MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ));
    L49:
        if ((this.newFilterFlags & MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED) == 0) goto L52;
        this.items.add(ItemInner.asChat(false, LocaleController.getString(R.string.FilterArchived), "archived", MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED));
    L52:
        if (this.newNeverShow.isEmpty() == false) goto L54;
    L66:
        this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.FilterExcludeInfo)));
        goto L68
    L54:
        if (this.excludeExpanded == false) goto L56;
    L59:
        int r02 = this.newNeverShow.size();
    L60:
        int r5 = 0;
    L61:
        if (r5 >= r02) goto L64;
        this.items.add(ItemInner.asChat(false, this.newNeverShow.get(r5).longValue()));
        r5 = r5 + 1;
        goto L61
    L64:
        if (r02 == this.newNeverShow.size()) goto L66;
        this.items.add(ItemInner.asButton(R.drawable.arrow_more, LocaleController.formatPluralString("FilterShowMoreChats", this.newNeverShow.size() - 5, new Object[0]), false).whenClicked(new FilterCreateActivity$$ExternalSyntheticLambda7(this)));
        goto L66
    L56:
        if (this.newNeverShow.size() < 8) goto L59;
        r02 = Math.min(5, this.newNeverShow.size());
    L68:
        if (getMessagesController().folderTags == false) goto L70;
    L71:
        this.items.add(new ItemInner(9, false));
        this.items.add(new ItemInner(10, false));
        this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.FolderTagColorInfo)));
    L72:
        boolean r03 = this.invites.isEmpty();
        ArrayList<ItemInner> r22 = this.items;
        if (r03 == false) goto L75;
        r22.add(ItemInner.asHeader(LocaleController.getString(R.string.FilterShareFolder), true));
        this.items.add(ItemInner.asButton(R.drawable.msg2_link2, LocaleController.getString(R.string.FilterShareFolderButton), false));
        this.items.add(ItemInner.asShadow(LocaleController.getString(R.string.FilterInviteLinksHintNew)));
    L87:
        if (this.creatingNew == true) goto L89;
        this.items.add(ItemInner.asButton(0, LocaleController.getString(R.string.FilterDelete), true).whenClicked(new FilterCreateActivity$$ExternalSyntheticLambda8(this)));
        this.items.add(ItemInner.asShadow(null));
    L89:
        ListAdapter r04 = this.adapter;
        if (r04 == null) goto L99;
        if (r12 == false) goto L94;
        r04.setItems(this.oldItems, this.items);
        return;
    L94:
        r04.notifyDataSetChanged();
        return;
    L99:
        return;
    L75:
        r22.add(ItemInner.asHeader(LocaleController.getString(R.string.FilterInviteLinks), true));
        this.items.add(ItemInner.asCreateLink());
        int r05 = 0;
    L76:
        int r23 = this.invites.size();
        ArrayList<ItemInner> r52 = this.items;
        if (r05 >= r23) goto L79;
        r52.add(ItemInner.asLink(this.invites.get(r05)));
        r05 = r05 + 1;
        goto L76
    L79:
        MessagesController.DialogFilter r06 = this.filter;
        if (r06 != null) goto L82;
    L84:
        String r07 = LocaleController.getString(R.string.FilterInviteLinksHint);
    L85:
        r52.add(ItemInner.asShadow(r07));
        goto L87
    L82:
        if (r06.isChatlist() == false) goto L84;
        r07 = LocaleController.getString(R.string.FilterInviteLinksHintNew);
        goto L85
    L70:
        if (getUserConfig().isPremium() == true) goto L72;
    L28:
        if (this.includeExpanded == false) goto L30;
    L33:
        int r08 = this.newAlwaysShow.size();
    L34:
        int r7 = 0;
    L35:
        if (r7 >= r08) goto L38;
        this.items.add(ItemInner.asChat(true, this.newAlwaysShow.get(r7).longValue()));
        r7 = r7 + 1;
        goto L35
    L38:
        if (r08 == this.newAlwaysShow.size()) goto L40;
        this.items.add(ItemInner.asButton(R.drawable.arrow_more, LocaleController.formatPluralString("FilterShowMoreChats", this.newAlwaysShow.size() - 5, new Object[0]), false).whenClicked(new FilterCreateActivity$$ExternalSyntheticLambda5(this)));
        goto L40
    L30:
        if (this.newAlwaysShow.size() < 8) goto L33;
        r08 = Math.min(5, this.newAlwaysShow.size());
        goto L34
    L5:
        if (this.newFilterAnimations == false) goto L7;
        int r24 = R.string.FilterNameAnimationsDisable;
    L8:
        r2 = LocaleController.getString(r24);
        goto L10
    L7:
        r24 = R.string.FilterNameAnimationsEnable;
        goto L8
    }

    public static CharSequence withNew(int r6, CharSequence r7, boolean r8) {
        if (r6 >= 0) goto L4;
    L15:
        return r7;
    L4:
        if (MessagesController.getGlobalMainSettings().getBoolean("n_" + r6, false) == true) goto L15;
        Context r62 = ApplicationLoader.applicationContext;
        if (r62 == null) goto L15;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r7);
        r0.append("  ");
        SpannableString r72 = new SpannableString("NEW");
        if (r8 == false) goto L12;
        Drawable r63 = r62.getResources().getDrawable(R.drawable.msg_other_new_outline).mutate();
        r63.setBounds(0, -AndroidUtilities.dp(8.0f), r63.getIntrinsicWidth(), r63.getIntrinsicHeight() - AndroidUtilities.dp(8.0f));
        r72.setSpan(new ColorImageSpan(r63, 0), 0, r72.length(), 33);
    L13:
        r0.append(r72);
        return r0;
    L12:
        Drawable r82 = r62.getResources().getDrawable(R.drawable.msg_other_new_filled).mutate();
        Drawable r64 = r62.getResources().getDrawable(R.drawable.msg_other_new_filled_text).mutate();
        int r4 = Theme.getColor(Theme.key_featuredStickers_unread);
        PorterDuff.Mode r5 = PorterDuff.Mode.MULTIPLY;
        r82.setColorFilter(new PorterDuffColorFilter(r4, r5));
        r64.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_buttonText), r5));
        CombinedDrawable r3 = new CombinedDrawable(r82, r64);
        r3.setBounds(0, 0, r3.getIntrinsicWidth(), r3.getIntrinsicHeight());
        r72.setSpan(new ImageSpan(r3, 0), 0, r72.length(), 33);
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        return checkDiscard(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        ActionBarMenu r0 = this.actionBar.createMenu();
        boolean r2 = this.creatingNew;
        ActionBar r3 = this.actionBar;
        if (r2 == false) goto L5;
        r3.setTitle(LocaleController.getString(R.string.FilterNew));
    L20:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.doneItem = r0.addItem(1, LocaleController.getString(R.string.Save).toUpperCase());
        AnonymousClass2 r02 = new AnonymousClass2(this, r7);
        this.fragmentView = r02;
        AnonymousClass2 r03 = r02;
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass3 r22 = new AnonymousClass3(this, r7);
        this.listView = r22;
        r22.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setLayoutManager(new LinearLayoutManager(r7, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r04 = this.listView;
        ListAdapter r1 = new ListAdapter(this, r7);
        this.adapter = r1;
        r04.setAdapter(r1);
        this.listView.setOnItemClickListener(new FilterCreateActivity$$ExternalSyntheticLambda9(this));
        this.listView.setOnItemLongClickListener(new FilterCreateActivity$$ExternalSyntheticLambda10(this));
        DefaultItemAnimator r72 = new DefaultItemAnimator();
        r72.setSupportsChangeAnimations(false);
        r72.setDelayAnimations(false);
        r72.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r72.setDurations(350);
        this.listView.setItemAnimator(r72);
        this.listView.setOnScrollListener(new AnonymousClass4(this));
        checkDoneButton(false);
        loadInvites();
        return this.fragmentView;
    L5:
        Paint.FontMetricsInt r23 = r3.getTitleFontMetricsInt();
        this.actionBar.setTitle(MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(this.filter.name, r23, false), this.filter.entities, r23));
        ActionBar r24 = this.actionBar;
        if (r24 == null) goto L20;
        int r32 = 26;
        if (r24.getTitleTextView() == null) goto L15;
        SimpleTextView r25 = this.actionBar.getTitleTextView();
        if (this.newFilterAnimations == false) goto L12;
        int r5 = 0;
    L13:
        r25.setEmojiCacheType(r5);
        goto L15
    L12:
        r5 = 26;
    L15:
        if (this.actionBar.getTitleTextView2() == null) goto L20;
        SimpleTextView r26 = this.actionBar.getTitleTextView2();
        if (this.newFilterAnimations == false) goto L19;
        r32 = 0;
    L19:
        r26.setEmojiCacheType(r32);
        goto L20
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        FilterCreateActivity$$ExternalSyntheticLambda13 r8 = new FilterCreateActivity$$ExternalSyntheticLambda13(this);
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, TextCell.class, UserCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r18 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_text_RedRegular));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"ImageView"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{UserCell.class}, new String[]{"adminTextView"}, null, null, null, Theme.key_profile_creatorIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        return r1;
    }

    public boolean hasAnimatedEmojis(CharSequence r3) {
        if ((r3 instanceof Spanned) == true) goto L5;
        return false;
    L5:
        Spanned r32 = (Spanned) r3;
        AnimatedEmojiSpan[] r2 = (AnimatedEmojiSpan[]) r32.getSpans(0, r32.length(), AnimatedEmojiSpan.class);
        if (r2 != null) goto L8;
    L11:
        return false;
    L8:
        if (r2.length <= 0) goto L11;
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    public void loadInvites() {
        if (this.loadingInvites == true) goto L13;
        MessagesController.DialogFilter r0 = this.filter;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isChatlist() == false) goto L14;
        this.loadingInvites = true;
        TL_chatlists.TL_chatlists_getExportedInvites r02 = new TL_chatlists.TL_chatlists_getExportedInvites();
        TL_chatlists.TL_inputChatlistDialogFilter r1 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r02.chatlist = r1;
        r1.filter_id = this.filter.id;
        this.requestingInvitesReqId = getConnectionsManager().sendRequest(r02, new FilterCreateActivity$$ExternalSyntheticLambda19(this));
        return;
    L14:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        EditEmojiTextCell r0 = this.nameEditTextCell;
        if (r0 == null) goto L13;
        EditTextEmoji r02 = r0.editTextEmoji;
        if (r02 == null) goto L13;
        if (r02.isPopupShowing() == false) goto L13;
        if (r2 == false) goto L14;
        this.nameEditTextCell.editTextEmoji.hidePopup(true);
        return false;
    L14:
        return false;
    L13:
        return checkDiscard(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        updateRows();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.requestingInvitesReqId == 0) goto L6;
        getConnectionsManager().cancelRequest(this.requestingInvitesReqId, true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
        UndoView r0 = this.undoView;
        if (r0 == null) goto L6;
        r0.setTranslationY(-r4);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        updateRows();
        Runnable r0 = this.showBulletinOnResume;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }
}
