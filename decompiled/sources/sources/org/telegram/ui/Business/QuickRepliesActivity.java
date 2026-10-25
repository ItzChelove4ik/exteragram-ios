package org.telegram.ui.Business;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes6.dex */
public class QuickRepliesActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static AlertDialog currentDialog;
    private NumberTextView countText;
    private ActionBarMenuItem deleteItem;
    private ActionBarMenuItem editItem;
    private UniversalRecyclerView listView;
    private int repliesOrderId;
    public final ArrayList<Integer> selected;
    private boolean shownEditItem;

    /* renamed from: org.telegram.ui.Business.QuickRepliesActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ QuickRepliesActivity this$0;

        /* renamed from: $r8$lambda$Drj0QK6Vg7Q32YLC5x4nHAk-fys, reason: not valid java name */
        public static /* synthetic */ void m6394$r8$lambda$Drj0QK6Vg7Q32YLC5x4nHAkfys(AnonymousClass1 r0, int r1, String r2) {
            r0.lambda$onItemClick$0(r1, r2);
        }

        /* renamed from: $r8$lambda$PL1XKwFLEbCdes8XkCB-0AoZmEo, reason: not valid java name */
        public static /* synthetic */ void m6395$r8$lambda$PL1XKwFLEbCdes8XkCB0AoZmEo(AnonymousClass1 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$1(r1, r2);
        }

        public AnonymousClass1(QuickRepliesActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(int r2, String r3) {
            QuickRepliesActivity.m6391$$Nest$mclearSelection(this.this$0);
            QuickRepliesController.getInstance(QuickRepliesActivity.access$400(this.this$0)).renameReply(r2, r3);
        }

        private /* synthetic */ void lambda$onItemClick$1(AlertDialog r1, int r2) {
            QuickRepliesController.getInstance(QuickRepliesActivity.access$300(this.this$0)).deleteReplies(this.this$0.selected);
            QuickRepliesActivity.m6391$$Nest$mclearSelection(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r11) {
            if (r11 != (-1)) goto L11;
            boolean r112 = this.this$0.selected.isEmpty();
            QuickRepliesActivity r10 = this.this$0;
            if (r112 == false) goto L8;
            r10.finishFragment();
            return;
        L8:
            QuickRepliesActivity.m6391$$Nest$mclearSelection(r10);
            return;
        L11:
            if (r11 != 1) goto L21;
            if (this.this$0.selected.size() != 1) goto L25;
            int r113 = this.this$0.selected.get(0).intValue();
            QuickRepliesController.QuickReply r6 = QuickRepliesController.getInstance(QuickRepliesActivity.access$000(this.this$0)).findReply(r113);
            if (r6 == null) goto L26;
            QuickRepliesActivity.openRenameReplyAlert(this.this$0.getContext(), QuickRepliesActivity.access$100(this.this$0), null, r6, QuickRepliesActivity.access$200(this.this$0), false, new QuickRepliesActivity$1$$ExternalSyntheticLambda0(this, r113));
            return;
        L26:
            return;
        L25:
            return;
        L21:
            if (r11 != 2) goto L24;
            QuickRepliesActivity r114 = this.this$0;
            r114.showDialog(new AlertDialog.Builder(r114.getContext(), this.this$0.getResourceProvider()).setTitle(LocaleController.formatPluralString("BusinessRepliesDeleteTitle", this.this$0.selected.size(), new Object[0])).setMessage(LocaleController.formatPluralString("BusinessRepliesDeleteMessage", this.this$0.selected.size(), new Object[0])).setPositiveButton(LocaleController.getString(R.string.Remove), new QuickRepliesActivity$1$$ExternalSyntheticLambda1(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Business.QuickRepliesActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ QuickRepliesActivity this$0;

        public AnonymousClass2(QuickRepliesActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Business.QuickRepliesActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextBoldCursor {
        AnimatedTextView.AnimatedTextDrawable limit;
        AnimatedColor limitColor;
        private int limitCount;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass3(Context r8, Theme.ResourcesProvider r9) {
            this.val$resourcesProvider = r9;
            super(r8);
            this.limitColor = new AnimatedColor(this);
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.limit = r0;
            r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.limit.setTextSize(AndroidUtilities.dp(15.33f));
            this.limit.setCallback(this);
            this.limit.setGravity(5);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
            AnimatedColor r1 = this.limitColor;
            if (this.limitCount >= 0) goto L5;
            int r2 = Theme.key_text_RedRegular;
        L6:
            r0.setTextColor(r1.set(Theme.getColor(r2, this.val$resourcesProvider)));
            this.limit.setBounds(getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            this.limit.draw(r6);
            return;
        L5:
            r2 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            if (this.limit == null) goto L10;
            this.limitCount = 32 - r1.length();
            this.limit.cancelAnimation();
            AnimatedTextView.AnimatedTextDrawable r12 = this.limit;
            int r22 = this.limitCount;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r22 > 4) goto L8;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        L8:
            r12.setText(r42);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Business.QuickRepliesActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements InputFilter {
        public AnonymousClass4() {
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
            return String.valueOf(r1).replaceAll("[^\\d_\\p{L}\\x{200c}\\x{00b7}\\x{0d80}-\\x{0dff}]", _UrlKt.FRAGMENT_ENCODE_SET);
        }
    }

    /* renamed from: org.telegram.ui.Business.QuickRepliesActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ TextView val$errorTextView;
        final /* synthetic */ Runnable[] val$hideError;

        public AnonymousClass5(TextView r1, Runnable[] r2) {
            this.val$errorTextView = r1;
            this.val$hideError = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (this.val$errorTextView.getAlpha() <= 0.0f) goto L6;
            AndroidUtilities.cancelRunOnUIThread(this.val$hideError[0]);
            AndroidUtilities.runOnUIThread(this.val$hideError[0]);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Business.QuickRepliesActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements TextView.OnEditorActionListener {
        final /* synthetic */ int val$currentAccount;
        final /* synthetic */ View val$currentFocus;
        final /* synthetic */ QuickRepliesController.QuickReply val$currentReply;
        final /* synthetic */ AlertDialog[] val$dialog;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ TextView val$errorTextView;
        final /* synthetic */ Utilities.Callback val$updateError;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public AnonymousClass6(EditTextBoldCursor r1, int r2, QuickRepliesController.QuickReply r3, TextView r4, Utilities.Callback r5, Utilities.Callback r6, AlertDialog[] r7, View r8) {
            this.val$editText = r1;
            this.val$currentAccount = r2;
            this.val$currentReply = r3;
            this.val$errorTextView = r4;
            this.val$updateError = r5;
            this.val$whenDone = r6;
            this.val$dialog = r7;
            this.val$currentFocus = r8;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView r3, int r4, KeyEvent r5) {
            if (r4 != 6) goto L32;
            String r32 = this.val$editText.getText().toString();
            if (r32.length() > 0) goto L7;
        L30:
            AndroidUtilities.shakeView(this.val$editText);
            return true;
        L7:
            if (r32.length() > 32) goto L30;
            QuickRepliesController r42 = QuickRepliesController.getInstance(this.val$currentAccount);
            QuickRepliesController.QuickReply r1 = this.val$currentReply;
            if (r1 != null) goto L12;
            int r12 = -1;
        L14:
            if (r42.isNameBusy(r32, r12) == false) goto L17;
            AndroidUtilities.shakeView(this.val$editText);
            this.val$errorTextView.setText(LocaleController.getString(R.string.BusinessRepliesNameBusy));
            this.val$updateError.run(Boolean.TRUE);
            return true;
        L17:
            Utilities.Callback r43 = this.val$whenDone;
            if (r43 == null) goto L20;
            r43.run(r32);
        L20:
            AlertDialog r33 = this.val$dialog[0];
            if (r33 == null) goto L24;
            r33.dismiss();
        L24:
            if (this.val$dialog[0] != QuickRepliesActivity.m6392$$Nest$sfgetcurrentDialog()) goto L26;
            QuickRepliesActivity.m6393$$Nest$sfputcurrentDialog(null);
        L26:
            View r2 = this.val$currentFocus;
            if (r2 == null) goto L29;
            r2.requestFocus();
        L29:
            return true;
        L12:
            r12 = r1.id;
            goto L14
        L32:
            return false;
        }
    }

    public static class LargeQuickReplyView extends FrameLayout {
        private final Paint arrowPaint;
        private final Path arrowPath;
        private final AvatarDrawable avatarDrawable;
        private final CheckBox2 checkBox;
        private final ImageReceiver imageReceiver;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private int[] spanWidth;
        private final TextView textView;
        private final TextView titleView;

        public LargeQuickReplyView(Context r18, Theme.ResourcesProvider r19) {
            super(r18);
            this.avatarDrawable = new AvatarDrawable();
            this.imageReceiver = new ImageReceiver(this);
            this.arrowPath = new Path();
            this.arrowPaint = new Paint(1);
            this.spanWidth = new int[1];
            this.resourcesProvider = r19;
            setWillNotDraw(false);
            TextView r5 = new TextView(r18);
            this.titleView = r5;
            r5.setSingleLine();
            TextUtils.TruncateAt r6 = TextUtils.TruncateAt.END;
            r5.setEllipsize(r6);
            r5.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r19));
            r5.setTypeface(AndroidUtilities.bold());
            r5.setTextSize(1, 16.0f);
            boolean r7 = LocaleController.isRTL;
            if (r7 == false) goto L5;
            float r13 = 40.0f;
        L6:
            if (r7 == false) goto L8;
            float r15 = 78.0f;
        L9:
            addView(r5, LayoutHelper.createFrame(-1, -2.0f, 7, r13, 10.33f, r15, 0.0f));
            TextView r52 = new TextView(r18);
            this.textView = r52;
            r52.setLines(2);
            r52.setEllipsize(r6);
            r52.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r19));
            r52.setTextSize(1, 15.0f);
            boolean r1 = LocaleController.isRTL;
            if (r1 == false) goto L12;
            float r132 = 40.0f;
        L13:
            if (r1 == false) goto L15;
            float r152 = 78.0f;
        L16:
            addView(r52, LayoutHelper.createFrame(-1, -2.0f, 7, r132, 32.0f, r152, 0.0f));
            CheckBox2 r12 = new CheckBox2(getContext(), 21, r19);
            this.checkBox = r12;
            r12.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            r12.setDrawUnchecked(false);
            r12.setDrawBackgroundAsArc(3);
            addView(r12, LayoutHelper.createFrameRelatively(24.0f, 24.0f, 8388659, 33.0f, 25.0f, 0.0f, 0.0f));
            return;
        L15:
            r152 = 40.0f;
            goto L16
        L12:
            r132 = 78.0f;
            goto L13
        L8:
            r15 = 40.0f;
            goto L9
        L5:
            r13 = 78.0f;
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            ImageReceiver r0 = this.imageReceiver;
            if (LocaleController.isRTL == false) goto L6;
            int r1 = getMeasuredWidth() - AndroidUtilities.dp(65.0f);
        L7:
            r0.setImageCoords(r1, AndroidUtilities.dp(11.33f), AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
            this.imageReceiver.draw(r8);
            super.onDraw(r8);
            r8.drawPath(this.arrowPath, this.arrowPaint);
            if (this.needDivider == false) goto L22;
            Paint r02 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r02 != null) goto L12;
            r02 = Theme.dividerPaint;
        L12:
            Paint r6 = r02;
            float r12 = 78.0f;
            if (LocaleController.isRTL == false) goto L15;
            float r03 = 0.0f;
        L16:
            float r04 = AndroidUtilities.dp(r03);
            float r3 = getMeasuredHeight() - 1;
            int r4 = getWidth();
            if (LocaleController.isRTL == true) goto L20;
            r12 = 0.0f;
        L20:
            r8.drawRect(r04, r3, r4 - AndroidUtilities.dp(r12), getMeasuredHeight(), r6);
            return;
        L15:
            r03 = 78.0f;
            goto L16
        L22:
            return;
        L6:
            r1 = AndroidUtilities.dp(9.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(78.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
            this.arrowPaint.setStyle(Paint.Style.STROKE);
            this.arrowPaint.setStrokeCap(Paint.Cap.ROUND);
            this.arrowPaint.setStrokeJoin(Paint.Join.ROUND);
            this.arrowPaint.setStrokeWidth(AndroidUtilities.dpf2(1.66f));
            this.arrowPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider), 0.85f));
            this.arrowPath.rewind();
            float r52 = getMeasuredHeight() / 2.0f;
            if (LocaleController.isRTL == false) goto L5;
            float r62 = AndroidUtilities.dpf2(29.66f);
        L6:
            this.arrowPath.moveTo(r62, r52 - AndroidUtilities.dpf2(5.66f));
            Path r0 = this.arrowPath;
            if (LocaleController.isRTL == false) goto L9;
            int r2 = -1;
        L10:
            r0.lineTo((r2 * AndroidUtilities.dpf2(5.33f)) + r62, r52);
            this.arrowPath.lineTo(r62, r52 + AndroidUtilities.dpf2(5.66f));
            return;
        L9:
            r2 = 1;
            goto L10
        L5:
            r62 = getMeasuredWidth() - AndroidUtilities.dpf2(24.33f);
            goto L6
        }

        public void set(QuickRepliesController.QuickReply r17, boolean r18) {
            int r2 = UserConfig.selectedAccount;
            this.titleView.setText(MessagesController.getInstance(r2).getPeerName(UserConfig.getInstance(r2).getClientUserId()));
            SpannableStringBuilder r3 = new SpannableStringBuilder();
            MessageObject r4 = r17.topMessage;
            if (r4 == null) goto L6;
            r3.append(Emoji.replaceEmoji(r4.messageText, this.textView.getPaint().getFontMetricsInt(), false));
        L6:
            if (r17.getMessagesCount() <= 1) goto L13;
            r3.append("  ");
            int r7 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(80.0f);
            CharSequence r8 = MoreSpan.of(r17.getMessagesCount() - 1, this.spanWidth);
            SpannableStringBuilder r9 = new SpannableStringBuilder(TextUtils.ellipsize(r3, this.textView.getPaint(), (r7 * 1.5f) - this.spanWidth[0], TextUtils.TruncateAt.END));
            if (r9.length() > 0) goto L10;
        L12:
            r9.append(r8);
            r3 = r9;
            goto L13
        L10:
            if (r9.charAt(r9.length() - 1) != 8230) goto L12;
            r9.append("  ");
        L13:
            this.textView.setText(r3);
            TLRPC.MessageMedia r32 = MessageObject.getMedia(r17.topMessage);
            if (r32 == null) goto L24;
            TLRPC.Photo r82 = r32.photo;
            if (r82 == null) goto L24;
            TLRPC.PhotoSize r22 = FileLoader.getClosestPhotoSizeWithSize(r82.sizes, AndroidUtilities.dp(36.0f), true, null, true);
            ImageReceiver r5 = this.imageReceiver;
            ImageLocation r6 = ImageLocation.getForObject(r22, r32.photo);
            MessageObject r12 = r17.topMessage;
            BitmapDrawable r83 = r12.strippedThumb;
            if (r22 != null) goto L21;
            long r1 = 0;
        L22:
            r5.setImage(r6, "36_36", r83, r1, null, r12, 0);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(6.0f));
        L34:
            this.needDivider = r18;
            invalidate();
            return;
        L21:
            r1 = r22.size;
        L24:
            if (r32 == null) goto L33;
            TLRPC.Document r84 = r32.document;
            if (r84 == null) goto L33;
            TLRPC.PhotoSize r23 = FileLoader.getClosestPhotoSizeWithSize(r84.thumbs, AndroidUtilities.dp(36.0f), true, null, true);
            TLRPC.Document r52 = r32.document;
            if (r23 != null) goto L31;
            ImageLocation r24 = ImageLocation.getForDocument(r52);
            long r53 = r32.document.size;
            ImageLocation r85 = r24;
            String r92 = ImageLoader.AUTOPLAY_FILTER;
        L30:
            long r11 = r53;
            ImageReceiver r72 = this.imageReceiver;
            MessageObject r14 = r17.topMessage;
            r72.setImage(r85, r92, r14.strippedThumb, r11, null, r14, 0);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(6.0f));
            goto L34
        L31:
            ImageLocation r33 = ImageLocation.getForObject(r23, r52);
            r53 = r23.size;
            r92 = "36_36";
            r85 = r33;
        L33:
            this.avatarDrawable.setInfo(UserConfig.getInstance(r2).getCurrentUser());
            this.imageReceiver.setForUserOrChat(UserConfig.getInstance(r2).getCurrentUser(), this.avatarDrawable);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(56.0f));
            goto L34
        }

        public void setChecked(boolean r1, boolean r2) {
            this.checkBox.setChecked(r1, r2);
        }
    }

    public static class MoreSpan extends ReplacementSpan {
        private final Paint backgroundPaint;
        private final Text text;

        public MoreSpan(int r4) {
            this.backgroundPaint = new Paint(1);
            this.text = new Text(LocaleController.formatPluralString("BusinessRepliesMore", r4, new Object[0]), 9.33f, AndroidUtilities.bold());
        }

        public static CharSequence of(int r3, int[] r4) {
            SpannableString r0 = new SpannableString("+");
            MoreSpan r1 = new MoreSpan(r3);
            r4[0] = r1.getSize();
            r0.setSpan(r1, 0, r0.length(), 33);
            return r0;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r7, CharSequence r8, int r9, int r10, float r11, int r12, int r13, int r14, Paint r15) {
            float r82 = AndroidUtilities.dpf2(14.66f);
            float r3 = (r12 + r14) / 2.0f;
            RectF r92 = AndroidUtilities.rectTmp;
            float r83 = r82 / 2.0f;
            r92.set(r11, r3 - r83, getSize() + r11, r83 + r3);
            Paint r84 = this.backgroundPaint;
            int r102 = Theme.key_windowBackgroundWhiteGrayText2;
            r84.setColor(Theme.multAlpha(Theme.getColor(r102), 0.15f));
            r7.drawRoundRect(r92, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.backgroundPaint);
            this.text.draw(r7, r11 + AndroidUtilities.dp(5.0f), r3, Theme.getColor(r102), Utilities.clamp((r15.getAlpha() * 2) / 255.0f, 1.0f, 0.0f));
        }

        public int getSize() {
            return (int) (this.text.getCurrentWidth() + AndroidUtilities.dp(10.0f));
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return getSize();
        }
    }

    public static class QuickReplyView extends FrameLayout {
        private final AvatarDrawable avatarDrawable;
        private final CheckBox2 checkBox;
        private final ImageReceiver imageReceiver;
        private boolean local;
        private boolean needDivider;
        private final ImageView orderView;
        private final Theme.ResourcesProvider resourcesProvider;
        private int[] spanWidth;
        private final TextView textView;

        public QuickReplyView(Context r17, boolean r18, Theme.ResourcesProvider r19) {
            super(r17);
            this.avatarDrawable = new AvatarDrawable();
            this.imageReceiver = new ImageReceiver(this);
            this.spanWidth = new int[1];
            this.resourcesProvider = r19;
            setWillNotDraw(false);
            if (r18 == false) goto L5;
            int r5 = 42;
        L6:
            SpoilersTextView r6 = new SpoilersTextView(r17);
            this.textView = r6;
            r6.setLines(2);
            r6.setEllipsize(TextUtils.TruncateAt.END);
            r6.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r19));
            r6.setTextSize(1, 14.0f);
            boolean r3 = LocaleController.isRTL;
            float r7 = 64.0f;
            if (r3 == false) goto L9;
            float r12 = r5;
        L10:
            if (r3 == true) goto L13;
            r7 = r5;
        L13:
            addView(r6, LayoutHelper.createFrame(-1, -2.0f, 7, r12, 7.0f, r7, 0.0f));
            if (r18 == false) goto L20;
            ImageView r52 = new ImageView(r17);
            this.orderView = r52;
            r52.setScaleType(ImageView.ScaleType.CENTER);
            r52.setImageResource(R.drawable.list_reorder);
            r52.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_stickers_menu), PorterDuff.Mode.MULTIPLY));
            r52.setAlpha(0.0f);
            if (LocaleController.isRTL == false) goto L18;
            int r1 = 3;
        L19:
            addView(r52, LayoutHelper.createFrame(50, 50, r1 | 112));
        L21:
            CheckBox2 r13 = new CheckBox2(getContext(), 21, r19);
            this.checkBox = r13;
            r13.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            r13.setDrawUnchecked(false);
            r13.setDrawBackgroundAsArc(3);
            addView(r13, LayoutHelper.createFrameRelatively(24.0f, 24.0f, 8388659, 33.0f, 25.0f, 0.0f, 0.0f));
            return;
        L18:
            r1 = 5;
            goto L19
        L20:
            this.orderView = null;
            goto L21
        L9:
            r12 = 64.0f;
            goto L10
        L5:
            r5 = 16;
            goto L6
        }

        public void invalidateEmojis() {
            this.textView.invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            ImageReceiver r0 = this.imageReceiver;
            if (LocaleController.isRTL == false) goto L6;
            int r1 = getMeasuredWidth() - AndroidUtilities.dp(51.0f);
        L7:
            r0.setImageCoords(r1, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f));
            this.imageReceiver.draw(r8);
            super.onDraw(r8);
            if (this.needDivider == false) goto L22;
            Paint r02 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r02 != null) goto L12;
            r02 = Theme.dividerPaint;
        L12:
            Paint r6 = r02;
            float r12 = 64.0f;
            if (LocaleController.isRTL == false) goto L15;
            float r03 = 0.0f;
        L16:
            float r04 = AndroidUtilities.dp(r03);
            float r3 = getMeasuredHeight() - 1;
            int r4 = getWidth();
            if (LocaleController.isRTL == true) goto L20;
            r12 = 0.0f;
        L20:
            r8.drawRect(r04, r3, r4 - AndroidUtilities.dp(r12), getMeasuredHeight(), r6);
            return;
        L15:
            r03 = 64.0f;
            goto L16
        L22:
            return;
        L6:
            r1 = AndroidUtilities.dp(15.0f);
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        }

        public void set(QuickRepliesController.QuickReply r18, String r19, boolean r20) {
            String r2 = r19;
            if (r18 == null) goto L5;
            boolean r4 = r18.local;
        L6:
            this.local = r4;
            SpannableStringBuilder r42 = new SpannableStringBuilder();
            if (r2 != null) goto L9;
        L13:
            r42.append("/").append(r18.name);
            r42.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r42.length(), 33);
            r42.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider)), 0, r42.length(), 33);
            if (r2 == null) goto L21;
            ForegroundColorSpan r6 = new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2, this.resourcesProvider));
            if (r2.length() > 0) goto L18;
            int r22 = 1;
        L19:
            r42.setSpan(r6, 0, Math.min(r22, r42.length()), 33);
            goto L21
        L18:
            r22 = r2.length();
        L21:
            if (r18.topMessage == null) goto L30;
            r42.append(" ");
            CharSequence r23 = r18.topMessage.caption;
            if (TextUtils.isEmpty(r23) == false) goto L25;
            r23 = r18.topMessage.messageText;
        L25:
            CharSequence r24 = Emoji.replaceEmoji(new SpannableStringBuilder(r23), this.textView.getPaint().getFontMetricsInt(), false);
            TLRPC.Message r62 = r18.topMessage.messageOwner;
            if (r62 == null) goto L28;
            MessageObject.replaceAnimatedEmoji(r24, r62.entities, this.textView.getPaint().getFontMetricsInt());
        L28:
            r42.append(r24);
        L30:
            if (r18.getMessagesCount() <= 1) goto L37;
            r42.append("  ");
            int r63 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(80.0f);
            CharSequence r7 = MoreSpan.of(r18.getMessagesCount() - 1, this.spanWidth);
            SpannableStringBuilder r8 = new SpannableStringBuilder(TextUtils.ellipsize(r42, this.textView.getPaint(), (r63 * 1.5f) - this.spanWidth[0], TextUtils.TruncateAt.END));
            if (r8.length() > 0) goto L34;
        L36:
            r8.append(r7);
            r42 = r8;
            goto L37
        L34:
            if (r8.charAt(r8.length() - 1) != 8230) goto L36;
            r8.append("  ");
        L37:
            this.textView.setText(r42);
            int r25 = UserConfig.selectedAccount;
            TLRPC.MessageMedia r3 = MessageObject.getMedia(r18.topMessage);
            long r64 = 0;
            if (r3 == null) goto L47;
            TLRPC.Photo r10 = r3.photo;
            if (r10 == null) goto L47;
            TLRPC.PhotoSize r26 = FileLoader.getClosestPhotoSizeWithSize(r10.sizes, AndroidUtilities.dp(36.0f), true, null, true);
            ImageReceiver r82 = this.imageReceiver;
            ImageLocation r9 = ImageLocation.getForObject(r26, r3.photo);
            MessageObject r15 = r18.topMessage;
            BitmapDrawable r11 = r15.strippedThumb;
            if (r26 == null) goto L45;
            r64 = r26.size;
        L45:
            r82.setImage(r9, "36_36", r11, r64, null, r15, 0);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(4.0f));
        L71:
            this.needDivider = r20;
            invalidate();
            return;
        L47:
            if (r3 != null) goto L49;
        L60:
            if (r3 == null) goto L70;
            TLRPC.WebPage r102 = r3.webpage;
            if (r102 == null) goto L70;
            TLRPC.Photo r103 = r102.photo;
            if (r103 == null) goto L70;
            TLRPC.PhotoSize r27 = FileLoader.getClosestPhotoSizeWithSize(r103.sizes, AndroidUtilities.dp(36.0f), true, null, true);
            ImageReceiver r83 = this.imageReceiver;
            ImageLocation r92 = ImageLocation.getForObject(r27, r3.webpage.photo);
            BitmapDrawable r112 = r18.topMessage.strippedThumb;
            if (r27 == null) goto L69;
            r64 = r27.size;
        L69:
            r83.setImage(r92, "36_36", r112, r64, null, r3.webpage, 0);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(4.0f));
        L70:
            this.avatarDrawable.setInfo(UserConfig.getInstance(r25).getCurrentUser());
            this.imageReceiver.setForUserOrChat(UserConfig.getInstance(r25).getCurrentUser(), this.avatarDrawable);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(36.0f));
            goto L71
        L49:
            if (r3.document == null) goto L60;
            if (r18.topMessage.isVideo() == false) goto L53;
        L54:
            TLRPC.PhotoSize r28 = FileLoader.getClosestPhotoSizeWithSize(r3.document.thumbs, AndroidUtilities.dp(36.0f), true, null, true);
            TLRPC.Document r5 = r3.document;
            if (r28 != null) goto L58;
            ImageLocation r29 = ImageLocation.getForDocument(r5);
            long r52 = r3.document.size;
            ImageLocation r84 = r29;
            String r93 = ImageLoader.AUTOPLAY_FILTER;
        L57:
            long r113 = r52;
            ImageReceiver r72 = this.imageReceiver;
            MessageObject r14 = r18.topMessage;
            r72.setImage(r84, r93, r14.strippedThumb, r113, null, r14, 0);
            this.imageReceiver.setRoundRadius(AndroidUtilities.dp(4.0f));
            goto L71
        L58:
            ImageLocation r32 = ImageLocation.getForObject(r28, r5);
            r52 = r28.size;
            r93 = "36_36";
            r84 = r32;
            goto L57
        L53:
            if (r18.topMessage.isSticker() == false) goto L60;
        L9:
            if (r2.length() <= 0) goto L13;
            if (r2.startsWith("/") == true) goto L13;
            r2 = "/".concat(r2);
            goto L13
        L5:
            r4 = false;
            goto L6
        }

        public void setChecked(boolean r1, boolean r2) {
            this.checkBox.setChecked(r1, r2);
        }

        public void setReorder(boolean r2) {
            ViewPropertyAnimator r0 = this.orderView.animate();
            if (r2 == true) goto L5;
        L7:
            float r1 = 0.0f;
        L8:
            r0.alpha(r1).start();
            return;
        L5:
            if (this.local == true) goto L7;
            r1 = 1.0f;
            goto L8
        }
    }

    public static /* synthetic */ void $r8$lambda$0pRxDBuy8ztEkr1uoTVgdmpUhUM(EditTextBoldCursor r2, Utilities.Callback r3, int r4, QuickRepliesController.QuickReply r5, TextView r6, Utilities.Callback r7, AlertDialog r8, int r9) {
        String r92 = r2.getText().toString();
        if (r92.length() > 0) goto L5;
    L19:
        AndroidUtilities.shakeView(r2);
        r3.run(Boolean.FALSE);
        return;
    L5:
        if (r92.length() > 32) goto L19;
        QuickRepliesController r42 = QuickRepliesController.getInstance(r4);
        if (r5 != null) goto L10;
        int r52 = -1;
    L12:
        if (r42.isNameBusy(r92, r52) == false) goto L15;
        AndroidUtilities.shakeView(r2);
        r6.setText(LocaleController.getString(R.string.BusinessRepliesNameBusy));
        r3.run(Boolean.TRUE);
        return;
    L15:
        if (r7 == null) goto L17;
        r7.run(r92);
    L17:
        r8.dismiss();
        return;
    L10:
        r52 = r5.id;
        goto L12
    }

    /* renamed from: $r8$lambda$P5PUcuUv393yrf-idvYa-bp3Hts, reason: not valid java name */
    public static /* synthetic */ boolean m6384$r8$lambda$P5PUcuUv393yrfidvYabp3Hts(QuickRepliesActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$QvBAgX4oJR40Y-L-K8c3o2Ep4iU, reason: not valid java name */
    public static /* synthetic */ void m6385$r8$lambda$QvBAgX4oJR40YLK8c3o2Ep4iU(EditTextBoldCursor r2, Runnable r3) {
        AndroidUtilities.hideKeyboard(r2);
        AndroidUtilities.runOnUIThread(r3, 80);
    }

    public static /* synthetic */ void $r8$lambda$STX8o8VIagTLmLgWzlyCbaxoNGU(QuickRepliesActivity r0, int r1, ArrayList r2) {
        r0.whenReordered(r1, r2);
    }

    /* renamed from: $r8$lambda$VHsMSvYMPiYKY5kqrnTa-h4CNd4, reason: not valid java name */
    public static /* synthetic */ void m6386$r8$lambda$VHsMSvYMPiYKY5kqrnTah4CNd4(Runnable[] r4, ValueAnimator[] r5, TextView r6, TextView r7, Boolean r8) {
        AndroidUtilities.cancelRunOnUIThread(r4[0]);
        ValueAnimator r1 = r5[0];
        if (r1 == null) goto L5;
        r1.cancel();
    L5:
        float r12 = r6.getAlpha();
        if (r8.booleanValue() == false) goto L8;
        float r2 = 1.0f;
    L9:
        ValueAnimator r13 = ValueAnimator.ofFloat(new float[]{r12, r2});
        r5[0] = r13;
        r13.addUpdateListener(new QuickRepliesActivity$$ExternalSyntheticLambda15(r6, r7));
        r5[0].setDuration(320);
        r5[0].setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r5[0].start();
        if (r8.booleanValue() == false) goto L13;
        AndroidUtilities.runOnUIThread(r4[0], 5320);
        return;
    L13:
        return;
    L8:
        r2 = 0.0f;
        goto L9
    }

    /* renamed from: $r8$lambda$XM2A6ayOWb_zvhyd-xXiBs-bbZo, reason: not valid java name */
    public static /* synthetic */ void m6387$r8$lambda$XM2A6ayOWb_zvhydxXiBsbbZo(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$YvpIIRQr5jc8H4moXkzXFBpXttM(TextView r1, TextView r2, ValueAnimator r3) {
        r1.setAlpha(((Float) r3.getAnimatedValue()).floatValue());
        r2.setAlpha(1.0f - ((Float) r3.getAnimatedValue()).floatValue());
    }

    public static /* synthetic */ void $r8$lambda$ZSAcGoE2QDXJpND0A7RqwPPsXOI(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$gbxsx_vSGprSr8TydVleT5TFUuU(QuickRepliesActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$hAI-r_4M_oJ6LY9AuJFwIY5j5v4, reason: not valid java name */
    public static /* synthetic */ void m6388$r8$lambda$hAIr_4M_oJ6LY9AuJFwIY5j5v4(QuickRepliesActivity r0, String r1) {
        r0.lambda$onClick$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$hvouD5g7XQ2lzy887zG6fTkgEh4(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: $r8$lambda$ifxmYZ-dj0M51BTjeMLg-3okpfY, reason: not valid java name */
    public static /* synthetic */ boolean m6389$r8$lambda$ifxmYZdj0M51BTjeMLg3okpfY(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$mvxXaGaQpNH83o7El7LuGcpnyNA(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$ooGZaQKg8Jnz5ztOJEWiMXrBTWA(View r2) {
        if ((r2 instanceof QuickReplyView) == false) goto L6;
        ((QuickReplyView) r2).setChecked(false, true);
        return;
    }

    /* renamed from: $r8$lambda$pApO_rKYvW0eMiEeyJSKJxdI-A8, reason: not valid java name */
    public static /* synthetic */ void m6390$r8$lambda$pApO_rKYvW0eMiEeyJSKJxdIA8(Utilities.Callback r1) {
        r1.run(Boolean.FALSE);
    }

    public static /* synthetic */ void $r8$lambda$po2nOazX27SpPC0wsVV1Ce4mLog(QuickRepliesActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sF9FHqFkkH3wZHUsi6MiJxSdiw8(View r0, DialogInterface r1) {
        currentDialog = null;
        if (r0 == null) goto L6;
        r0.requestFocus();
        return;
    }

    /* renamed from: -$$Nest$mclearSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6391$$Nest$mclearSelection(QuickRepliesActivity r0) {
        r0.clearSelection();
    }

    /* renamed from: -$$Nest$sfgetcurrentDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AlertDialog m6392$$Nest$sfgetcurrentDialog() {
        return currentDialog;
    }

    /* renamed from: -$$Nest$sfputcurrentDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6393$$Nest$sfputcurrentDialog(AlertDialog r0) {
        currentDialog = r0;
    }

    public QuickRepliesActivity() {
        this.selected = new ArrayList();
        this.shownEditItem = true;
    }

    public static /* synthetic */ int access$000(QuickRepliesActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(QuickRepliesActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(QuickRepliesActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$300(QuickRepliesActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(QuickRepliesActivity r0) {
        return r0.currentAccount;
    }

    private void clearSelection() {
        this.selected.clear();
        AndroidUtilities.forEachViews(this.listView, new QuickRepliesActivity$$ExternalSyntheticLambda16());
        this.actionBar.hideActionMode();
        this.listView.allowReorder(false);
    }

    private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        r7.add(UItem.asTopView(LocaleController.getString(R.string.BusinessReplies), LocaleController.getString(R.string.BusinessRepliesInfo), "RestrictedEmoji", "📝"));
        r8.whiteSectionStart();
        if (QuickRepliesController.getInstance(this.currentAccount).canAddNew() == false) goto L5;
        r7.add(UItem.asButton(1, R.drawable.msg_viewintopic, LocaleController.getString(R.string.BusinessRepliesAdd)).accent());
    L5:
        this.repliesOrderId = r8.reorderSectionStart();
        ArrayList<QuickRepliesController.QuickReply> r0 = QuickRepliesController.getInstance(this.currentAccount).replies;
        int r1 = r0.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L8;
        QuickRepliesController.QuickReply r3 = r0.get(r2);
        r2 = r2 + 1;
        QuickRepliesController.QuickReply r32 = r3;
        r7.add(UItem.asQuickReply(r32).setChecked(this.selected.contains(Integer.valueOf(r32.id))));
        goto L6
    L8:
        r8.reorderSectionEnd();
        r8.whiteSectionEnd();
        r7.add(UItem.asShadow(LocaleController.getString(R.string.BusinessRepliesAddInfo)));
    }

    private /* synthetic */ void lambda$onClick$1(String r5) {
        Bundle r0 = new Bundle();
        r0.putInt("chatMode", 5);
        r0.putLong("user_id", getUserConfig().getClientUserId());
        r0.putString("quick_reply", r5);
        ChatActivity r52 = new ChatActivity(r0);
        r52.forceEmptyHistory();
        presentFragment(r52);
    }

    private void onClick(UItem r8, View r9, int r10, float r11, float r12) {
        if (r8.id != 1) goto L7;
        openRenameReplyAlert(getContext(), this.currentAccount, null, null, getResourceProvider(), false, new QuickRepliesActivity$$ExternalSyntheticLambda14(this));
        return;
    L7:
        if (r8.viewType == 16) goto L9;
        return;
    L9:
        if ((r8.object instanceof QuickRepliesController.QuickReply) == true) goto L11;
        return;
    L11:
        if (this.selected.isEmpty() == true) goto L14;
        updateSelect(r8, r9);
        return;
    L14:
        QuickRepliesController.QuickReply r82 = (QuickRepliesController.QuickReply) r8.object;
        if (r82.local == true) goto L21;
        Bundle r92 = new Bundle();
        r92.putInt("chatMode", 5);
        r92.putLong("user_id", getUserConfig().getClientUserId());
        r92.putString("quick_reply", r82.name);
        ChatActivity r102 = new ChatActivity(r92);
        r102.setQuickReplyId(r82.id);
        presentFragment(r102);
        return;
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.viewType != 16) goto L11;
        Object r32 = r1.object;
        if ((r32 instanceof QuickRepliesController.QuickReply) == true) goto L7;
    L9:
        updateSelect(r1, r2);
        return true;
    L7:
        if (((QuickRepliesController.QuickReply) r32).local == false) goto L9;
        return false;
    L11:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [org.telegram.ui.ActionBar.AlertDialog$Builder] */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    public static void openRenameReplyAlert(Context r23, int r24, String r25, QuickRepliesController.QuickReply r26, Theme.ResourcesProvider r27, boolean r28, Utilities.Callback<String> r29) {
        BaseFragment r2 = LaunchActivity.getLastFragment();
        Activity r4 = AndroidUtilities.findActivity(r23);
        if (r4 == null) goto L5;
        View r8 = r4.getCurrentFocus();
    L7:
        if (r2 != null) goto L9;
    L14:
        boolean r10 = false;
    L15:
        AlertDialog[] r7 = new AlertDialog[1];
        if (r10 == false) goto L19;
        Object r22 = new AlertDialogDecor.Builder(r23, r27);
    L18:
        ?? r11 = r22;
        if (r26 != null) goto L23;
        if (r25 != null) goto L23;
        int r210 = R.string.BusinessRepliesNewTitle;
    L24:
        r11.setTitle(LocaleController.getString(r210));
        AnonymousClass3 r211 = new AnonymousClass3(r23, r27);
        MediaDataController.getInstance(r24).fetchNewEmojiKeywords(AndroidUtilities.getCurrentKeyboardLanguage(), true);
        r211.setTextSize(1, 18.0f);
        if (r26 != null) goto L29;
        if (r25 != null) goto L28;
        String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L30:
        r211.setText(r6);
        int r62 = Theme.key_dialogTextBlack;
        r211.setTextColor(Theme.getColor(r62, r27));
        r211.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r27));
        r211.setHintText(LocaleController.getString(R.string.BusinessRepliesNamePlaceholder));
        r211.setSingleLine(true);
        r211.setFocusable(true);
        r211.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, r27), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r27), Theme.getColor(Theme.key_text_RedRegular, r27));
        r211.setImeOptions(6);
        r211.setBackgroundDrawable(null);
        r211.setPadding(0, 0, AndroidUtilities.dp(42.0f), 0);
        r211.setFilters(new InputFilter[]{new AnonymousClass4()});
        ?? r5 = new LinearLayout(r23);
        r5.setOrientation(1);
        FrameLayout r12 = new FrameLayout(r23);
        TextView r13 = new TextView(r23);
        r13.setTextColor(Theme.getColor(r62, r27));
        r13.setTextSize(1, 16.0f);
        if (r26 != null) goto L34;
        if (r25 != null) goto L34;
        int r14 = R.string.BusinessRepliesNewMessage;
    L35:
        r13.setText(LocaleController.getString(r14));
        r12.addView(r13, LayoutHelper.createFrame(-1, -2, 83));
        TextView r42 = new TextView(r23);
        r42.setTextColor(Theme.getColor(Theme.key_text_RedBold, r27));
        r42.setTextSize(1, 16.0f);
        r42.setText(LocaleController.getString(R.string.BusinessRepliesNameBusy));
        r42.setAlpha(0.0f);
        r12.addView(r42, LayoutHelper.createFrame(-1, -2, 83));
        Runnable[] r0 = {new QuickRepliesActivity$$ExternalSyntheticLambda1(r6)};
        QuickRepliesActivity$$ExternalSyntheticLambda0 r63 = new QuickRepliesActivity$$ExternalSyntheticLambda0(r0, new ValueAnimator[1], r42, r13);
        r211.addTextChangedListener(new AnonymousClass5(r42, r0));
        r5.addView(r12, LayoutHelper.createLinear(-1, -2, 24.0f, 5.0f, 24.0f, 12.0f));
        r5.addView(r211, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r11.setView(r5);
        r11.setWidth(AndroidUtilities.dp(292.0f));
        r211.setOnEditorActionListener(new AnonymousClass6(r211, r24, r26, r42, r63, r29, r7, r8));
        r11.setPositiveButton(LocaleController.getString(R.string.Done), new QuickRepliesActivity$$ExternalSyntheticLambda2(r211, r63, r24, r26, r42, r29));
        r11.setNegativeButton(LocaleController.getString(R.string.Cancel), new QuickRepliesActivity$$ExternalSyntheticLambda3());
        if (r10 == false) goto L38;
        AlertDialog r02 = r11.create();
        currentDialog = r02;
        r7[0] = r02;
        r02.setOnDismissListener(new QuickRepliesActivity$$ExternalSyntheticLambda4(r8));
        currentDialog.setOnShowListener(new QuickRepliesActivity$$ExternalSyntheticLambda5(r211));
        currentDialog.showDelayed(250);
    L39:
        r7[0].setDismissDialogByButtons(false);
        r211.setSelection(r211.getText().length());
        return;
    L38:
        r11.overrideDismissListener(new QuickRepliesActivity$$ExternalSyntheticLambda6(r211));
        AlertDialog r03 = r11.create();
        r7[0] = r03;
        r03.setOnDismissListener(new QuickRepliesActivity$$ExternalSyntheticLambda7(r211));
        r7[0].setOnShowListener(new QuickRepliesActivity$$ExternalSyntheticLambda8(r211));
        r7[0].show();
    L34:
        r14 = R.string.BusinessRepliesEditMessage;
        goto L35
    L28:
        r6 = r25;
        goto L30
    L29:
        r6 = r26.name;
    L23:
        r210 = R.string.BusinessRepliesEditTitle;
        goto L24
    L19:
        r22 = new AlertDialog.Builder(r23, r27);
        goto L18
    L9:
        if ((r2.getFragmentView() instanceof SizeNotifierFrameLayout) == false) goto L14;
        if (((SizeNotifierFrameLayout) r2.getFragmentView()).measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L14;
        if (r28 == true) goto L14;
        r10 = true;
        goto L15
    L5:
        r8 = null;
        goto L7
    }

    private void updateEditItem() {
        boolean r1 = false;
        if (this.selected.size() != 1) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L14;
        QuickRepliesController.QuickReply r02 = QuickRepliesController.getInstance(this.currentAccount).findReply(this.selected.get(0).intValue());
        if (r02 != null) goto L10;
    L12:
        r0 = r1;
        goto L14
    L10:
        if (r02.isSpecial() == true) goto L12;
        r1 = true;
    L14:
        if (this.shownEditItem == r0) goto L29;
        this.shownEditItem = r0;
        ViewPropertyAnimator r03 = this.editItem.animate();
        float r2 = 1.0f;
        if (this.shownEditItem == false) goto L18;
        float r12 = 1.0f;
    L19:
        ViewPropertyAnimator r04 = r03.alpha(r12);
        if (this.shownEditItem == false) goto L22;
        float r13 = 1.0f;
    L23:
        ViewPropertyAnimator r05 = r04.scaleX(r13);
        if (this.shownEditItem == true) goto L27;
        r2 = 0.7f;
    L27:
        r05.scaleY(r2).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(340).start();
        return;
    L22:
        r13 = 0.7f;
        goto L23
    L18:
        r12 = 0.0f;
        goto L19
    L29:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void updateSelect(UItem r5, View r6) {
        QuickRepliesController.QuickReply r0 = (QuickRepliesController.QuickReply) r5.object;
        QuickReplyView r62 = (QuickReplyView) r6;
        boolean r1 = this.selected.contains(Integer.valueOf(r0.id));
        ArrayList<Integer> r2 = this.selected;
        if (r1 == false) goto L5;
        r2.remove(Integer.valueOf(r0.id));
    L6:
        this.listView.allowReorder(!this.selected.isEmpty());
        boolean r02 = this.selected.contains(Integer.valueOf(r0.id));
        r5.checked = r02;
        r62.setChecked(r02, true);
        if (this.actionBar.isActionModeShowed() != this.selected.isEmpty()) goto L12;
        boolean r52 = this.selected.isEmpty();
        ActionBar r63 = this.actionBar;
        if (r52 == false) goto L11;
        r63.hideActionMode();
        goto L12
    L11:
        r63.showActionMode();
    L12:
        this.countText.setNumber(Math.max(1, this.selected.size()), true);
        updateEditItem();
        return;
    L5:
        r2.add(Integer.valueOf(r0.id));
        goto L6
    }

    private void whenReordered(int r2, ArrayList<UItem> r3) {
        if (r2 != this.repliesOrderId) goto L16;
        int r22 = 0;
    L6:
        if (r22 >= r3.size()) goto L11;
        if ((r3.get(r22).object instanceof QuickRepliesController.QuickReply) == false) goto L10;
        ((QuickRepliesController.QuickReply) r3.get(r22).object).order = r22;
    L10:
        r22 = r22 + 1;
        goto L6
    L11:
        QuickRepliesController.getInstance(this.currentAccount).reorder();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.BusinessReplies));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r0 = this.actionBar.createActionMode();
        NumberTextView r3 = new NumberTextView(getContext());
        this.countText = r3;
        r3.setTextSize(18);
        this.countText.setTypeface(AndroidUtilities.bold());
        this.countText.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        r0.addView(this.countText, LayoutHelper.createLinear(0, -1, 1.0f, 72, 0, 0, 0));
        this.countText.setOnTouchListener(new QuickRepliesActivity$$ExternalSyntheticLambda9());
        ActionBarMenuItem r32 = r0.addItem(1, R.drawable.msg_edit);
        this.editItem = r32;
        r32.setContentDescription(LocaleController.getString(R.string.Edit));
        ActionBarMenuItem r02 = r0.addItem(2, R.drawable.msg_delete);
        this.deleteItem = r02;
        r02.setContentDescription(LocaleController.getString(R.string.Delete));
        AnonymousClass2 r03 = new AnonymousClass2(this, r12);
        r03.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        UniversalRecyclerView r122 = new UniversalRecyclerView(this, new QuickRepliesActivity$$ExternalSyntheticLambda10(this), new QuickRepliesActivity$$ExternalSyntheticLambda11(this), new QuickRepliesActivity$$ExternalSyntheticLambda12(this));
        this.listView = r122;
        r122.setSections();
        this.listView.adapter.setApplyBackground(false);
        this.listView.listenReorder(new QuickRepliesActivity$$ExternalSyntheticLambda13(this));
        r03.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView, true);
        this.fragmentView = r03;
        return r03;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.quickRepliesUpdated) goto L10;
        UniversalRecyclerView r0 = this.listView;
        if (r0 == null) goto L11;
        UniversalAdapter r02 = r0.adapter;
        if (r02 == null) goto L12;
        r02.update(true);
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.quickRepliesUpdated);
        QuickRepliesController.getInstance(this.currentAccount).load();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.quickRepliesUpdated);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
    }
}
