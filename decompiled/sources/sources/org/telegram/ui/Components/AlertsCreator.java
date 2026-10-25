package org.telegram.ui.Components;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.URLSpan;
import android.util.Base64;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.util.Consumer;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.export.ui.ExportMapper$$ExternalSyntheticLambda2;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.timepicker.TimeModel;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.YearMonth;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.NotificationsSettingsFacade;
import org.telegram.messenger.OneUIUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.pip.utils.PipUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.TimezonesController;
import org.telegram.ui.CacheControlActivity;
import org.telegram.ui.Cells.AccountSelectCell;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.RadioColorCell;
import org.telegram.ui.Cells.TextColorCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.LanguageSelectActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.LoginActivity;
import org.telegram.ui.NotificationsCustomSettingsActivity;
import org.telegram.ui.NotificationsSettingsActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumFeatureCell;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.PrivacyControlActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ProfileNotificationsActivity;
import org.telegram.ui.SelectChatUserSheet;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.ThemePreviewActivity;
import org.telegram.ui.TooManyCommunitiesActivity;
import org.telegram.ui.community.cells.CommunityBanGroupConfirmCell;

/* loaded from: classes7.dex */
public abstract class AlertsCreator {
    private static final Pattern URL_PATTERN = null;

    /* renamed from: org.telegram.ui.Components.AlertsCreator$1, reason: invalid class name */
    public class AnonymousClass1 extends URLSpanNoUnderline {
        final /* synthetic */ AlertDialog.Builder val$builder;

        public AnonymousClass1(String r1, AlertDialog.Builder r2) {
            this.val$builder = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r2) {
            this.val$builder.getDismissRunnable().run();
            super.onClick(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$10, reason: invalid class name */
    public class AnonymousClass10 extends TextView {
        public AnonymousClass10(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$11, reason: invalid class name */
    public class AnonymousClass11 extends FrameLayout {
        final /* synthetic */ CheckBoxCell[] val$cell;

        public AnonymousClass11(Context r1, CheckBoxCell[] r2) {
            this.val$cell = r2;
            super(r1);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (this.val$cell[0] == null) goto L6;
            setMeasuredDimension(getMeasuredWidth(), (getMeasuredHeight() + this.val$cell[0].getMeasuredHeight()) + AndroidUtilities.dp(7.0f));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$12, reason: invalid class name */
    public class AnonymousClass12 extends BackupImageView {
        final Path path;

        public AnonymousClass12(Context r1) {
            super(r1);
            this.path = new Path();
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r6) {
            r6.save();
            this.path.rewind();
            this.path.addCircle(AndroidUtilities.dp(54.0f), AndroidUtilities.dp(53.0f), AndroidUtilities.dp(14.0f), Path.Direction.CW);
            r6.clipPath(this.path, Region.Op.DIFFERENCE);
            super.onDraw(r6);
            r6.restore();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$13, reason: invalid class name */
    public class AnonymousClass13 extends FrameLayout {
        final /* synthetic */ CheckBoxCell[] val$cell;

        public AnonymousClass13(Context r1, CheckBoxCell[] r2) {
            this.val$cell = r2;
            super(r1);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (this.val$cell[0] == null) goto L6;
            setMeasuredDimension(getMeasuredWidth(), (getMeasuredHeight() + this.val$cell[0].getMeasuredHeight()) + AndroidUtilities.dp(7.0f));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$14, reason: invalid class name */
    public class AnonymousClass14 extends TextView {
        public AnonymousClass14(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$15, reason: invalid class name */
    public class AnonymousClass15 extends FrameLayout {
        final /* synthetic */ CheckBoxCell[] val$cell;

        public AnonymousClass15(Context r1, CheckBoxCell[] r2) {
            this.val$cell = r2;
            super(r1);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (this.val$cell[0] == null) goto L6;
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + this.val$cell[0].getMeasuredHeight());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$16, reason: invalid class name */
    public class AnonymousClass16 extends TextView {
        public AnonymousClass16(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$17, reason: invalid class name */
    public class AnonymousClass17 extends CodepointsLengthInputFilter {
        final /* synthetic */ NumberTextView val$checkTextView;

        public AnonymousClass17(int r1, NumberTextView r2) {
            this.val$checkTextView = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.CodepointsLengthInputFilter, android.text.InputFilter
        public CharSequence filter(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
            CharSequence r22 = super.filter(r1, r2, r3, r4, r5, r6);
            if (r22 == null) goto L8;
            if (r1 == null) goto L8;
            if (r22.length() == r1.length()) goto L8;
            VibratorUtils.vibrate();
            AndroidUtilities.shakeView(this.val$checkTextView);
        L8:
            return r22;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$18, reason: invalid class name */
    public class AnonymousClass18 implements TextWatcher {
        final /* synthetic */ NumberTextView val$checkTextView;
        final /* synthetic */ int val$maxSymbolsCount;

        public AnonymousClass18(int r1, NumberTextView r2) {
            this.val$maxSymbolsCount = r1;
            this.val$checkTextView = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            boolean r2 = false;
            int r0 = this.val$maxSymbolsCount - Character.codePointCount(r5, 0, r5.length());
            NumberTextView r52 = this.val$checkTextView;
            if (r0 < 30) goto L5;
            AndroidUtilities.updateViewVisibilityAnimated(r52, false);
            return;
        L5:
            if (r52.getVisibility() != 0) goto L7;
            r2 = true;
        L7:
            r52.setNumber(r0, r2);
            AndroidUtilities.updateViewVisibilityAnimated(this.val$checkTextView, true);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        boolean ignoreTextChange;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ int val$maxLength;

        public AnonymousClass2(int r1, EditTextBoldCursor r2) {
            this.val$maxLength = r1;
            this.val$editText = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            if (this.ignoreTextChange == true) goto L15;
            int r0 = r3.length();
            int r1 = this.val$maxLength;
            if (r0 <= r1) goto L14;
            this.ignoreTextChange = true;
            r3.delete(r1, r3.length());
            AndroidUtilities.shakeView(this.val$editText);
            this.val$editText.performHapticFeedback(3, 2);     // Catch: Exception -> L11
        L9:
            this.ignoreTextChange = false;
            return;
        L14:
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$21, reason: invalid class name */
    public class AnonymousClass21 extends NumberPicker {
        public AnonymousClass21(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$22, reason: invalid class name */
    public class AnonymousClass22 extends LinearLayout {
        private Text ampmText;
        private boolean isAM;
        private final Text separatorText;
        final /* synthetic */ NumberPicker val$hourPicker;

        public AnonymousClass22(Context r2, NumberPicker r3) {
            this.val$hourPicker = r3;
            super(r2);
            this.separatorText = new Text(":", 18.0f);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r15) {
            int r7 = Theme.key_windowBackgroundWhiteBlackText;
            this.separatorText.draw(r15, (getWidth() - this.separatorText.getCurrentWidth()) / 2.0f, getHeight() / 2.0f, Theme.getColor(r7), 1.0f);
            if (LocaleController.is24HourFormat == false) goto L5;
        L18:
            super.dispatchDraw(r15);
            return;
        L5:
            if ((this.val$hourPicker.getValue() % 24) >= 12) goto L7;
            boolean r152 = true;
        L9:
            if (this.isAM == r152) goto L11;
        L12:
            this.isAM = r152;
            if (r152 == false) goto L15;
            String r153 = "AM";
        L16:
            this.ampmText = new Text(r153, 18.0f);
        L17:
            this.ampmText.draw(r15, (getWidth() / 2.0f) + AndroidUtilities.dp(43.0f), (getHeight() / 2.0f) + AndroidUtilities.dp(1.0f), Theme.getColor(r7), 1.0f);
            goto L18
        L15:
            r153 = "PM";
            goto L16
        L11:
            if (this.ampmText != null) goto L17;
        L7:
            r152 = false;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$23, reason: invalid class name */
    public class AnonymousClass23 extends NumberPicker {
        public AnonymousClass23(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$24, reason: invalid class name */
    public class AnonymousClass24 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass24(Context r1, NumberPicker r2, NumberPicker r3) {
            this.val$hourPicker = r2;
            this.val$minutePicker = r3;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$hourPicker.setItemCount(r02);
            this.val$minutePicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$hourPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r12 = this.val$minutePicker.getLayoutParams();
            r12.height = AndroidUtilities.dp(42.0f) * r02;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$25, reason: invalid class name */
    public class AnonymousClass25 extends NumberPicker {
        public AnonymousClass25(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$26, reason: invalid class name */
    public class AnonymousClass26 extends NumberPicker {
        public AnonymousClass26(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$27, reason: invalid class name */
    public class AnonymousClass27 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass27(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$hourPicker = r3;
            this.val$minutePicker = r4;
            super(r1);
            this.ignoreLayout = false;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$28, reason: invalid class name */
    public class AnonymousClass28 extends TextView {
        public AnonymousClass28(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$29, reason: invalid class name */
    public class AnonymousClass29 extends NumberPicker {
        public AnonymousClass29(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextBoldCursor {
        public AnonymousClass3(Context r1) {
            super(r1);
        }

        @Override // com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo r3) {
            InputConnection r2 = super.onCreateInputConnection(r3);
            r3.imeOptions &= -1073741825;
            return r2;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$30, reason: invalid class name */
    public class AnonymousClass30 extends NumberPicker {
        public AnonymousClass30(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$31, reason: invalid class name */
    public class AnonymousClass31 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass31(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$hourPicker = r3;
            this.val$minutePicker = r4;
            super(r1);
            this.ignoreLayout = false;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$32, reason: invalid class name */
    public class AnonymousClass32 extends TextView {
        public AnonymousClass32(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$33, reason: invalid class name */
    public class AnonymousClass33 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$monthPicker;
        final /* synthetic */ NumberPicker val$yearPicker;

        public AnonymousClass33(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$monthPicker = r3;
            this.val$yearPicker = r4;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$dayPicker.setItemCount(r02);
            this.val$monthPicker.setItemCount(r02);
            this.val$yearPicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$dayPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r12 = this.val$monthPicker.getLayoutParams();
            r12.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r13 = this.val$yearPicker.getLayoutParams();
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$34, reason: invalid class name */
    public class AnonymousClass34 extends TextView {
        public AnonymousClass34(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$35, reason: invalid class name */
    public class AnonymousClass35 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;
        final /* synthetic */ NumberPicker val$monthPicker;

        public AnonymousClass35(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, NumberPicker r5) {
            this.val$monthPicker = r2;
            this.val$dayPicker = r3;
            this.val$hourPicker = r4;
            this.val$minutePicker = r5;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$monthPicker.setItemCount(r02);
            this.val$dayPicker.setItemCount(r02);
            this.val$hourPicker.setItemCount(r02);
            this.val$minutePicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$monthPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r12 = this.val$dayPicker.getLayoutParams();
            r12.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r13 = this.val$hourPicker.getLayoutParams();
            r13.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r14 = this.val$minutePicker.getLayoutParams();
            r14.height = AndroidUtilities.dp(42.0f) * r02;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$36, reason: invalid class name */
    public class AnonymousClass36 extends LinearLayout {
        final /* synthetic */ NumberPicker val$minutePicker;
        final /* synthetic */ Text val$sep2;

        public AnonymousClass36(Context r1, Text r2, NumberPicker r3) {
            this.val$sep2 = r2;
            this.val$minutePicker = r3;
            super(r1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            this.val$sep2.draw(r4, this.val$minutePicker.getX() - AndroidUtilities.dp(50.0f), getHeight() / 2.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$37, reason: invalid class name */
    public class AnonymousClass37 extends NumberPicker {
        public AnonymousClass37(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$38, reason: invalid class name */
    public class AnonymousClass38 extends NumberPicker {
        public AnonymousClass38(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$39, reason: invalid class name */
    public class AnonymousClass39 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass39(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$hourPicker = r3;
            this.val$minutePicker = r4;
            super(r1);
            this.ignoreLayout = false;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$4, reason: invalid class name */
    public class AnonymousClass4 extends URLSpanNoUnderline {
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass4(String r1, BaseFragment r2) {
            this.val$fragment = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.URLSpanNoUnderline, android.text.style.URLSpan, android.text.style.ClickableSpan
        public void onClick(View r2) {
            this.val$fragment.dismissCurrentDialog();
            super.onClick(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$40, reason: invalid class name */
    public class AnonymousClass40 extends TextView {
        public AnonymousClass40(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$41, reason: invalid class name */
    public class AnonymousClass41 extends NumberPicker {
        public AnonymousClass41(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$42, reason: invalid class name */
    public class AnonymousClass42 extends NumberPicker {
        public AnonymousClass42(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$43, reason: invalid class name */
    public class AnonymousClass43 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass43(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$hourPicker = r3;
            this.val$minutePicker = r4;
            super(r1);
            this.ignoreLayout = false;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$44, reason: invalid class name */
    public class AnonymousClass44 extends TextView {
        public AnonymousClass44(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$45, reason: invalid class name */
    public class AnonymousClass45 extends NumberPicker {
        final /* synthetic */ int[] val$values;

        public AnonymousClass45(Context r1, Theme.ResourcesProvider r2, int[] r3) {
            this.val$values = r3;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r3) {
            int r2 = this.val$values[r3];
            if (r2 != 0) goto L7;
            return LocaleController.getString(R.string.AutoDeleteNever);
        L7:
            if (r2 >= 10080) goto L11;
            return LocaleController.formatPluralString("Days", r2 / 1440, new Object[0]);
        L11:
            if (r2 >= 44640) goto L15;
            return LocaleController.formatPluralString("Weeks", r2 / 1440, new Object[0]);
        L15:
            if (r2 >= 525600) goto L19;
            return LocaleController.formatPluralString("Months", r2 / 10080, new Object[0]);
        L19:
            return LocaleController.formatPluralString("Years", ((r2 * 5) / 31) * 1440, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$46, reason: invalid class name */
    public class AnonymousClass46 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$numberPicker;

        public AnonymousClass46(Context r1, NumberPicker r2) {
            this.val$numberPicker = r2;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$numberPicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$numberPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            this.ignoreLayout = false;
            super.onMeasure(r4, r5);
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$47, reason: invalid class name */
    public class AnonymousClass47 extends AnimatedTextView {
        public AnonymousClass47(Context r1, boolean r2, boolean r3, boolean r4) {
            super(r1, r2, r3, r4);
        }

        @Override // android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$48, reason: invalid class name */
    public class AnonymousClass48 extends NumberPicker {
        public AnonymousClass48(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Times", r2 + 1, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$49, reason: invalid class name */
    public class AnonymousClass49 extends NumberPicker {
        public AnonymousClass49(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Times", r2 + 1, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$5, reason: invalid class name */
    public class AnonymousClass5 extends LinkSpanDrawable.LinksTextView {
        public AnonymousClass5(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$50, reason: invalid class name */
    public class AnonymousClass50 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$divider;
        final /* synthetic */ NumberPicker val$minutes;
        final /* synthetic */ NumberPicker val$times;

        public AnonymousClass50(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$times = r2;
            this.val$minutes = r3;
            this.val$divider = r4;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$times.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$times.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            this.val$minutes.setItemCount(r02);
            ViewGroup.LayoutParams r12 = this.val$minutes.getLayoutParams();
            r12.height = AndroidUtilities.dp(42.0f) * r02;
            this.val$divider.setItemCount(r02);
            ViewGroup.LayoutParams r13 = this.val$divider.getLayoutParams();
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$51, reason: invalid class name */
    public class AnonymousClass51 extends TextView {
        public AnonymousClass51(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$52, reason: invalid class name */
    public class AnonymousClass52 extends NumberPicker {
        final /* synthetic */ int[] val$values;

        public AnonymousClass52(Context r1, Theme.ResourcesProvider r2, int[] r3) {
            this.val$values = r3;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r3) {
            int r2 = this.val$values[r3];
            if (r2 != 0) goto L7;
            return LocaleController.getString(R.string.MuteNever);
        L7:
            if (r2 >= 60) goto L11;
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        L11:
            if (r2 >= 1440) goto L15;
            return LocaleController.formatPluralString("Hours", r2 / 60, new Object[0]);
        L15:
            if (r2 >= 10080) goto L19;
            return LocaleController.formatPluralString("Days", r2 / 1440, new Object[0]);
        L19:
            if (r2 >= 44640) goto L23;
            return LocaleController.formatPluralString("Weeks", r2 / 10080, new Object[0]);
        L23:
            if (r2 >= 525600) goto L27;
            return LocaleController.formatPluralString("Months", r2 / 44640, new Object[0]);
        L27:
            return LocaleController.formatPluralString("Years", r2 / 525600, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$53, reason: invalid class name */
    public class AnonymousClass53 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$numberPicker;

        public AnonymousClass53(Context r1, NumberPicker r2) {
            this.val$numberPicker = r2;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$numberPicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$numberPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            this.ignoreLayout = false;
            super.onMeasure(r4, r5);
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$54, reason: invalid class name */
    public class AnonymousClass54 extends TextView {
        public AnonymousClass54(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$55, reason: invalid class name */
    public class AnonymousClass55 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$monthPicker;
        final /* synthetic */ NumberPicker val$yearPicker;

        public AnonymousClass55(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$monthPicker = r3;
            this.val$yearPicker = r4;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            this.ignoreLayout = true;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            int r02 = 3;
        L6:
            this.val$dayPicker.setItemCount(r02);
            this.val$monthPicker.setItemCount(r02);
            this.val$yearPicker.setItemCount(r02);
            ViewGroup.LayoutParams r1 = this.val$dayPicker.getLayoutParams();
            r1.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r12 = this.val$monthPicker.getLayoutParams();
            r12.height = AndroidUtilities.dp(42.0f) * r02;
            ViewGroup.LayoutParams r13 = this.val$yearPicker.getLayoutParams();
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$56, reason: invalid class name */
    public class AnonymousClass56 extends TextView {
        public AnonymousClass56(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$57, reason: invalid class name */
    public class AnonymousClass57 extends ReportAlert {
        final /* synthetic */ Utilities.Callback2 val$report;

        public AnonymousClass57(Context r1, int r2, Theme.ResourcesProvider r3, Utilities.Callback2 r4) {
            this.val$report = r4;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.ReportAlert
        public void onSend(int r1, String r2) {
            this.val$report.run(Integer.valueOf(r1), r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$58, reason: invalid class name */
    public class AnonymousClass58 extends ViewOutlineProvider {
        public AnonymousClass58() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight() + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$59, reason: invalid class name */
    public class AnonymousClass59 extends ViewOutlineProvider {
        public AnonymousClass59() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight() + AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$6, reason: invalid class name */
    public class AnonymousClass6 extends LinkSpanDrawable.LinksTextView {
        public AnonymousClass6(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$60, reason: invalid class name */
    public class AnonymousClass60 extends ViewOutlineProvider {
        public AnonymousClass60() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight() + AndroidUtilities.dp(6.0f), AndroidUtilities.dpf2(6.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$61, reason: invalid class name */
    public class AnonymousClass61 extends FrameLayout {
        final /* synthetic */ GroupCallPipButton val$button;

        public AnonymousClass61(Context r1, GroupCallPipButton r2) {
            this.val$button = r2;
            super(r1);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.val$button.setTranslationY((getMeasuredHeight() * 0.28f) - (this.val$button.getMeasuredWidth() / 2.0f));
            this.val$button.setTranslationX((getMeasuredWidth() * 0.82f) - (this.val$button.getMeasuredWidth() / 2.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$62, reason: invalid class name */
    public class AnonymousClass62 extends ViewOutlineProvider {
        public AnonymousClass62() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight() + AndroidUtilities.dp(6.0f), AndroidUtilities.dpf2(6.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$63, reason: invalid class name */
    public class AnonymousClass63 extends CodepointsLengthInputFilter {
        final /* synthetic */ Context val$context;

        public AnonymousClass63(int r1, Context r2) {
            this.val$context = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.CodepointsLengthInputFilter, android.text.InputFilter
        public CharSequence filter(CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
            CharSequence r22 = super.filter(r1, r2, r3, r4, r5, r6);
            if (r22 == null) goto L10;
            if (r1 == null) goto L10;
            if (r22.length() == r1.length()) goto L10;
            Vibrator r0 = (Vibrator) this.val$context.getSystemService("vibrator");
            if (r0 == null) goto L10;
            r0.vibrate(200);
        L10:
            return r22;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$64, reason: invalid class name */
    public class AnonymousClass64 extends NumberPicker {
        public AnonymousClass64(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Hours", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$65, reason: invalid class name */
    public class AnonymousClass65 extends NumberPicker {
        public AnonymousClass65(Context r1, Theme.ResourcesProvider r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.NumberPicker
        public CharSequence getContentDescription(int r2) {
            return LocaleController.formatPluralString("Minutes", r2, new Object[0]);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$66, reason: invalid class name */
    public class AnonymousClass66 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$dayPicker;
        final /* synthetic */ NumberPicker val$hourPicker;
        final /* synthetic */ NumberPicker val$minutePicker;

        public AnonymousClass66(Context r1, NumberPicker r2, NumberPicker r3, NumberPicker r4) {
            this.val$dayPicker = r2;
            this.val$hourPicker = r3;
            this.val$minutePicker = r4;
            super(r1);
            this.ignoreLayout = false;
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

    /* renamed from: org.telegram.ui.Components.AlertsCreator$67, reason: invalid class name */
    public class AnonymousClass67 extends TextView {
        public AnonymousClass67(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$68, reason: invalid class name */
    public class AnonymousClass68 extends TextView {
        public AnonymousClass68(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView, android.view.View
        public CharSequence getAccessibilityClassName() {
            return Button.class.getName();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$69, reason: invalid class name */
    public class AnonymousClass69 extends LinearLayout {
        boolean ignoreLayout;
        final /* synthetic */ NumberPicker val$picker;

        public AnonymousClass69(Context r1, NumberPicker r2) {
            this.val$picker = r2;
            super(r1);
            this.ignoreLayout = false;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            this.ignoreLayout = true;
            ViewGroup.LayoutParams r0 = this.val$picker.getLayoutParams();
            r0.height = AndroidUtilities.dp(42.0f) * 8;
            this.ignoreLayout = false;
            super.onMeasure(r3, r4);
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$7, reason: invalid class name */
    public class AnonymousClass7 extends FrameLayout {
        final /* synthetic */ CheckBoxCell[] val$cell;

        public AnonymousClass7(Context r1, CheckBoxCell[] r2) {
            this.val$cell = r2;
            super(r1);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (this.val$cell[0] == null) goto L6;
            setMeasuredDimension(getMeasuredWidth(), (getMeasuredHeight() + this.val$cell[0].getMeasuredHeight()) + AndroidUtilities.dp(7.0f));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$8, reason: invalid class name */
    public class AnonymousClass8 extends TextView {
        public AnonymousClass8(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r3, TextView.BufferType r4) {
            super.setText(Emoji.replaceEmoji(r3, getPaint().getFontMetricsInt(), false), r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.AlertsCreator$9, reason: invalid class name */
    public class AnonymousClass9 extends FrameLayout {
        final /* synthetic */ CheckBoxCell[] val$cell;

        public AnonymousClass9(Context r1, CheckBoxCell[] r2) {
            this.val$cell = r2;
            super(r1);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(r3, r4);
            if (this.val$cell[0] == null) goto L6;
            setMeasuredDimension(getMeasuredWidth(), (getMeasuredHeight() + this.val$cell[0].getMeasuredHeight()) + AndroidUtilities.dp(7.0f));
            return;
        }
    }

    public interface AccountSelectDelegate {
        void didSelectAccount(int r1);
    }

    public interface BlockDialogCallback {
        void run(boolean r1, boolean r2);
    }

    public interface DatePickerDelegate {
        void didSelectDate(int r1, int r2, int r3);
    }

    public interface FormattedDatePickerDelegate {
        void didSelectDate(int r1, int r2);
    }

    public static class ScheduleDatePickerColors {
        public final int backgroundColor;
        public final int buttonBackgroundColor;
        public final int buttonBackgroundPressedColor;
        public final int buttonTextColor;
        public final int iconColor;
        public final int iconSelectorColor;
        public final int subMenuBackgroundColor;
        public final int subMenuSelectorColor;
        public final int subMenuTextColor;
        public final int textColor;

        private ScheduleDatePickerColors() {
            this(null);
        }

        public ScheduleDatePickerColors(int r13, int r14, int r15, int r16, int r17, int r18, int r19) {
            this(r13, r14, r15, r16, r17, r18, r19, Theme.getColor(Theme.key_featuredStickers_buttonText), Theme.getColor(Theme.key_featuredStickers_addButton), Theme.getColor(Theme.key_featuredStickers_addButtonPressed));
        }

        public ScheduleDatePickerColors(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
            this.textColor = r1;
            this.backgroundColor = r2;
            this.iconColor = r3;
            this.iconSelectorColor = r4;
            this.subMenuTextColor = r5;
            this.subMenuBackgroundColor = r6;
            this.subMenuSelectorColor = r7;
            this.buttonTextColor = r8;
            this.buttonBackgroundColor = r9;
            this.buttonBackgroundPressedColor = r10;
        }

        public ScheduleDatePickerColors(Theme.ResourcesProvider r13) {
            int r0 = Theme.key_dialogTextBlack;
            if (r13 == null) goto L6;
            int r02 = r13.getColorOrDefault(r0);
        L5:
            int r2 = r02;
            int r03 = Theme.key_dialogBackground;
            if (r13 == null) goto L11;
            int r04 = r13.getColorOrDefault(r03);
        L10:
            int r3 = r04;
            int r05 = Theme.key_sheet_other;
            if (r13 == null) goto L16;
            int r06 = r13.getColorOrDefault(r05);
        L15:
            int r4 = r06;
            int r07 = Theme.key_player_actionBarSelector;
            if (r13 == null) goto L21;
            int r08 = r13.getColorOrDefault(r07);
        L20:
            int r5 = r08;
            int r09 = Theme.key_actionBarDefaultSubmenuItem;
            if (r13 == null) goto L26;
            int r010 = r13.getColorOrDefault(r09);
        L25:
            int r6 = r010;
            int r011 = Theme.key_actionBarDefaultSubmenuBackground;
            if (r13 == null) goto L31;
            int r012 = r13.getColorOrDefault(r011);
        L30:
            int r7 = r012;
            int r013 = Theme.key_listSelector;
            if (r13 == null) goto L36;
            int r014 = r13.getColorOrDefault(r013);
        L35:
            int r8 = r014;
            int r015 = Theme.key_featuredStickers_buttonText;
            if (r13 == null) goto L41;
            int r016 = r13.getColorOrDefault(r015);
        L40:
            int r9 = r016;
            int r017 = Theme.key_featuredStickers_addButton;
            if (r13 == null) goto L46;
            int r018 = r13.getColorOrDefault(r017);
        L45:
            int r10 = r018;
            if (r13 == null) goto L50;
            int r132 = r13.getColorOrDefault(Theme.key_featuredStickers_addButtonPressed);
        L51:
            this(r2, r3, r4, r5, r6, r7, r8, r9, r10, r132);
            return;
        L50:
            r132 = Theme.getColor(Theme.key_featuredStickers_addButtonPressed);
            goto L51
        L46:
            r018 = Theme.getColor(r017);
            goto L45
        L41:
            r016 = Theme.getColor(r015);
            goto L40
        L36:
            r014 = Theme.getColor(r013);
            goto L35
        L31:
            r012 = Theme.getColor(r011);
            goto L30
        L26:
            r010 = Theme.getColor(r09);
            goto L25
        L21:
            r08 = Theme.getColor(r07);
            goto L20
        L16:
            r06 = Theme.getColor(r05);
            goto L15
        L11:
            r04 = Theme.getColor(r03);
            goto L10
        L6:
            r02 = Theme.getColor(r0);
            goto L5
        }

        public /* synthetic */ ScheduleDatePickerColors(AlertsCreatorIA r1) {
            this();
        }
    }

    public interface ScheduleDatePickerDelegate {
        void didSelectDate(boolean r1, int r2, int r3);
    }

    public interface SoundFrequencyDelegate {
        void didSelectValues(int r1, int r2);
    }

    public interface StatusUntilDatePickerDelegate {
        void didSelectDate(int r1);
    }

    /* renamed from: $r8$lambda$-FAsqu55A8BuxjTk5_pCI7VzAaw, reason: not valid java name */
    public static /* synthetic */ void m8696$r8$lambda$FAsqu55A8BuxjTk5_pCI7VzAaw(Runnable r0, Object[] r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$-ybewMoJc97SiAxDWHMXQlZ8QnQ, reason: not valid java name */
    public static /* synthetic */ void m8697$r8$lambda$ybewMoJc97SiAxDWHMXQlZ8QnQ(HintView2 r0) {
        AndroidUtilities.removeFromParent(r0);
    }

    public static /* synthetic */ void $r8$lambda$0RhnXu7skqioEiKmEllOuwIH5pk(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$0gG6wS-6rVuahA1HzAWarVn01Bo, reason: not valid java name */
    public static /* synthetic */ void m8698$r8$lambda$0gG6wS6rVuahA1HzAWarVn01Bo(EditTextBoldCursor r0, BaseFragment r1, Activity r2, DialogInterface r3) {
        AndroidUtilities.hideKeyboard(r0);
        if (r1 == null) goto L6;
        AndroidUtilities.requestAdjustResize(r2, r1.getClassGuid());
        return;
    }

    /* renamed from: $r8$lambda$17ENlob5U-Jduz-PvZgQfpIV-60, reason: not valid java name */
    public static /* synthetic */ void m8699$r8$lambda$17ENlob5UJduzPvZgQfpIV60(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    /* renamed from: $r8$lambda$1Y6rQ2__bsGRHMvQcgG-ywB0B2Y, reason: not valid java name */
    public static /* synthetic */ void m8700$r8$lambda$1Y6rQ2__bsGRHMvQcgGywB0B2Y(EditText r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$1aIuKIILGAVlIwZXhXuc9nfmWu8(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$2Jmm_yQQyHRoDj6MCS07uYneosc(EditTextBoldCursor r0, AlertDialog r1, BaseFragment r2) {
        processCreate(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$2ShZuR-Um6f3MBR0x0ROyKEMYM8, reason: not valid java name */
    public static /* synthetic */ void m8701$r8$lambda$2ShZuRUm6f3MBR0x0ROyKEMYM8(boolean[] r1, Runnable r2, View r3) {
        r1[0] = true;
        r2.run();
    }

    public static /* synthetic */ void $r8$lambda$2aRFXAKpWnrK4jEGOhxP4OI9Meg(boolean[] r10, int r11, NumberPicker r12, NumberPicker r13, NumberPicker r14, TextView r15, Calendar r16, ScheduleDatePickerDelegate r17, BottomSheet.Builder r18, View r19) {
        r10[0] = false;
        boolean r102 = checkScheduleDate(null, null, r11, 3, r12, r13, r14);
        checkPollCloseCustomDeadline(r15, r12, r13, r14);
        r16.setTimeInMillis(System.currentTimeMillis());
        r16.add(6, r12.getValue());
        r16.set(11, r13.getValue());
        r16.set(12, r14.getValue());
        if (r102 == false) goto L5;
        r16.set(13, 0);
        r16.set(14, 0);
    L5:
        r17.didSelectDate(true, (int) (r16.getTimeInMillis() / 1000), 0);
        r18.getDismissRunnable().run();
    }

    public static /* synthetic */ void $r8$lambda$3Z__0fZ_SzFQaPXq6q4NvKYCyqI(int[] r7, int[] r8, String[] r9, TextView r10) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append(LocaleController.getString(R.string.MessageScheduledRepeatOption));
        r0.append(" ");
        int r1 = r0.length();
        int r3 = 0;
    L4:
        if (r3 >= r7.length) goto L9;
        if (r8[0] == r7[r3]) goto L7;
        r3 = r3 + 1;
        goto L4
    L7:
        r0.append(r9[r3]);
        r0.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r1, r0.length(), 33);
    L9:
        r0.append(" v");
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L12;
        ColoredImageSpan r72 = new ColoredImageSpan(R.drawable.arrows_select);
        r72.spaceScaleX = 0.7f;
        r72.translate(AndroidUtilities.dp(-1.33f), AndroidUtilities.dp(0.0f));
        r72.setAlpha(0.75f);
        r0.setSpan(r72, r0.length() - 1, r0.length(), 33);
    L13:
        r10.setText(r0);
        return;
    L12:
        ColoredImageSpan r73 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        r73.spaceScaleX = 0.7f;
        r73.translate(AndroidUtilities.dp(-1.33f), AndroidUtilities.dp(0.0f));
        r73.setAlpha(0.75f);
        r0.setSpan(r73, r0.length() - 1, r0.length(), 33);
        goto L13
    }

    public static /* synthetic */ String $r8$lambda$3cJWMvwUi7xHuegRoJGu1pkgIPQ(int r5) {
        if (r5 == 0) goto L4;
        Calendar r0 = Calendar.getInstance();
        int r2 = r0.get(1);
        r0.add(6, r5);
        long r3 = r0.getTimeInMillis();
        if (r0.get(1) != r2) goto L10;
        return LocaleController.getInstance().getFormatterScheduleDay().format(r3);
    L10:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r3);
    L4:
        return LocaleController.getString(R.string.MessageScheduleToday);
    }

    /* renamed from: $r8$lambda$3ruG3ADp-ljK1V8lWjMp32M-tuE, reason: not valid java name */
    public static /* synthetic */ String m8702$r8$lambda$3ruG3ADpljK1V8lWjMp32MtuE(int r3) {
        Calendar r0 = Calendar.getInstance();
        r0.set(5, 1);
        r0.set(2, r3);
        return r0.getDisplayName(2, 1, Locale.getDefault());
    }

    /* renamed from: $r8$lambda$3trxnD-_UGCa40FbpnhoNVYDyGw, reason: not valid java name */
    public static /* synthetic */ void m8703$r8$lambda$3trxnD_UGCa40FbpnhoNVYDyGw(long r2, String r4, int[] r5, long r6, int r8, Runnable r9, AlertDialog r10, int r11) {
        SharedPreferences.Editor r102 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if (r2 == 0) goto L6;
        r102.putInt("color_" + r4, r5[0]);
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannel(r2, r6);
    L21:
        r102.apply();
        if (r9 == null) goto L25;
        r9.run();
        return;
    L25:
        return;
    L6:
        if (r8 != 1) goto L8;
        r102.putInt("MessagesLed", r5[0]);
    L20:
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannelGlobal(r8);
        goto L21
    L8:
        if (r8 != 0) goto L11;
        r102.putInt("GroupLed", r5[0]);
        goto L20
    L11:
        if (r8 != 3) goto L14;
        r102.putInt("StoriesLed", r5[0]);
        goto L20
    L14:
        if (r8 != 5) goto L16;
    L19:
        r102.putInt("ReactionLed", r5[0]);
        goto L20
    L16:
        if (r8 == 4) goto L19;
        r102.putInt("ChannelLed", r5[0]);
        goto L20
    }

    /* renamed from: $r8$lambda$423SrzgdSx4Htk3AI-ZLaUXHAbI, reason: not valid java name */
    public static /* synthetic */ void m8704$r8$lambda$423SrzgdSx4Htk3AIZLaUXHAbI(Context r2, AlertDialog r3, int r4) {
        r2.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + r2.getPackageName())));     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$45Z4lyQdMXhFG_phvhoy7rYTsPo(long r28, boolean r30, int r31, MessageObject r32, MessageObject.GroupedMessages r33, TLRPC.EncryptedChat r34, long r35, int r37, int r38, SparseArray[] r39, Runnable r40, AlertDialog r41, int r42) {
        if (r30 == false) goto L5;
        long r4 = UserConfig.getInstance(r31).getClientUserId();
    L6:
        ArrayList<Long> r6 = null;
        long r9 = 0;
        if (r32 == null) goto L40;
        ArrayList<Integer> r12 = new ArrayList();
        if (r33 == null) goto L24;
        int r8 = 0;
    L12:
        if (r8 >= r33.messages.size()) goto L23;
        MessageObject r11 = r33.messages.get(r8);
        r12.add(Integer.valueOf(r11.getId()));
        if (r34 == null) goto L22;
        if (r11.messageOwner.random_id == 0) goto L22;
        if (r11.type == 10) goto L22;
        if (r6 != null) goto L21;
        r6 = new ArrayList();
    L21:
        r6.add(Long.valueOf(r11.messageOwner.random_id));
    L22:
        r8 = r8 + 1;
    L23:
        ArrayList<Long> r13 = r6;
        if (r35 == 0) goto L38;
        TLRPC.Peer r0 = r32.messageOwner.peer_id;
        if (r0 == null) goto L38;
        if (r0.chat_id != (-r35)) goto L38;
        long r15 = r35;
    L39:
        MessagesController.getInstance(r31).deleteMessages(r12, r13, r34, r15, r37, true, r38);
    L66:
        if (r40 == null) goto L82;
        r40.run();
        return;
    L82:
        return;
    L38:
        r15 = r4;
        goto L39
    L24:
        r12.add(Integer.valueOf(r32.getId()));
        if (r34 == null) goto L23;
        if (r32.messageOwner.random_id == 0) goto L23;
        if (r32.type == 10) goto L23;
        r6 = new ArrayList();
        r6.add(Long.valueOf(r32.messageOwner.random_id));
        goto L23
    L40:
        int r1 = 1;
    L41:
        if (r1 < 0) goto L66;
        ArrayList<Integer> r112 = new ArrayList();
        int r122 = 0;
    L44:
        if (r122 >= r39[r1].size()) goto L46;
        r112.add(Integer.valueOf(r39[r1].keyAt(r122)));
        r122 = r122 + 1;
        goto L44
    L46:
        if (r34 == null) goto L58;
        ArrayList<Long> r123 = new ArrayList();
        int r132 = 0;
    L49:
        if (r132 >= r39[r1].size()) goto L56;
        MessageObject r14 = (MessageObject) r39[r1].valueAt(r132);
        long r29 = r9;
        long r82 = r14.messageOwner.random_id;
        if (r82 == r29) goto L55;
        if (r14.type == 10) goto L55;
        r123.add(Long.valueOf(r82));
    L55:
        r132 = r132 + 1;
        r9 = r29;
        goto L49
    L56:
        ArrayList<Long> r21 = r123;
    L57:
        long r292 = r9;
        MessagesController r19 = MessagesController.getInstance(r31);
        if (r1 == 1) goto L62;
    L64:
        long r23 = r4;
    L65:
        r19.deleteMessages(r112, r21, r34, r23, r37, true, r38);
        r39[r1].clear();
        r1 = r1 - 1;
        r9 = r292;
        goto L41
    L62:
        if (r35 == r292) goto L64;
        r23 = r35;
        goto L65
    L58:
        r21 = null;
        goto L57
    L5:
        r4 = r28;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$47BZmzpDx8mGT7Vhowhn1LRRYME(SharedPreferences r4, TLRPC.TL_help_support r5, AlertDialog r6, int r7, BaseFragment r8) {
        SharedPreferences.Editor r42 = r4.edit();
        r42.putLong("support_id2", r5.user.id);
        SerializedData r0 = new SerializedData();
        r5.user.serializeToStream(r0);
        r42.putString("support_user", Base64.encodeToString(r0.toByteArray(), 0));
        r42.apply();
        r0.cleanup();
        r6.dismiss();     // Catch: Exception -> L5
    L7:
        ArrayList r43 = new ArrayList();
        r43.add(r5.user);
        MessagesStorage.getInstance(r7).putUsersAndChats(r43, null, true, true);
        MessagesController.getInstance(r7).putUser(r5.user, false);
        Bundle r44 = new Bundle();
        r44.putLong("user_id", r5.user.id);
        r8.presentFragment(new ChatActivity(r44));
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    /* renamed from: $r8$lambda$4AGhtuCVg-t7B0KCSgEhFM1h-po, reason: not valid java name */
    public static /* synthetic */ void m8705$r8$lambda$4AGhtuCVgt7B0KCSgEhFM1hpo(BaseFragment r1, AlertDialog r2, int r3) {
        MessagesController.getInstance(r1.getCurrentAccount()).openByUserName("spambot", r1, 1);
    }

    public static /* synthetic */ void $r8$lambda$4f2RXDpSskM5bIoBqQs4mqEINPk(long r10, int r12, long r13) {
        Activity r1 = AndroidUtilities.getActivity();
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (PhotoViewer.getInstance().isVisible() == true) goto L12;
        if (r0 != null) goto L6;
    L8:
        if (r0 == null) goto L11;
        Theme.ResourcesProvider r02 = r0.getResourceProvider();
    L10:
        Theme.ResourcesProvider r2 = r02;
        new StarsIntroActivity.StarsNeededSheet(r1, r2, r10, 13, DialogObject.getShortName(r12, r13), new AlertsCreator$$ExternalSyntheticLambda18(), r13).show();
        return;
    L11:
        r02 = null;
        goto L10
    L6:
        if (r0.hasShownSheet() == false) goto L8;
    L12:
        r02 = new DarkThemeResourceProvider();
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$4h1QXnCtk1wDGstYiHUh_8pydWo(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    /* renamed from: $r8$lambda$4jQxRDs8XvljzRFU76zZA7Jr0-Q, reason: not valid java name */
    public static /* synthetic */ void m8706$r8$lambda$4jQxRDs8XvljzRFU76zZA7Jr0Q(TLRPC.User r3, BaseFragment r4, AlertDialog.Builder r5, View r6) {
        Bundle r62 = new Bundle();
        r62.putLong("user_id", r3.id);
        if (r4.getMessagesController().checkCanOpenChat(r62, r4) == false) goto L5;
        r4.presentFragment(new ProfileActivity(r62));
    L5:
        r5.getDismissRunnable().run();
    }

    public static /* synthetic */ String $r8$lambda$5ApjPnX9XVpDogAXi2cVmAG2aSE(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$5JuoPPWGOLkfTpWhiW6wMromfjg(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$5W5CEmYNNvBTNCjPrtcFRLDjjOU(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$5aUufNy1MDZKIkBKmtkSXzIDxdE(int r12, long r13, long r15, Utilities.Callback r17, long r18) {
        if (StarsController.getInstance(r12).getBalance().amount >= r13) goto L19;
        Activity r3 = AndroidUtilities.getActivity();
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (PhotoViewer.getInstance().isVisible() == true) goto L14;
        if (r1 != null) goto L8;
    L10:
        if (r1 == null) goto L13;
        Theme.ResourcesProvider r14 = r1.getResourceProvider();
    L12:
        Theme.ResourcesProvider r4 = r14;
        if (r3 != null) goto L17;
        return;
    L17:
        new StarsIntroActivity.StarsNeededSheet(r3, r4, r13, 13, DialogObject.getShortName(r12, r15), new AlertsCreator$$ExternalSyntheticLambda149(r17, r18), r15).show();
        return;
    L13:
        r14 = null;
        goto L12
    L8:
        if (r1.hasShownSheet() == false) goto L10;
    L14:
        r14 = new DarkThemeResourceProvider();
        goto L12
    L19:
        r17.run(Long.valueOf(r18));
    }

    public static /* synthetic */ void $r8$lambda$6ZqQMNFwAbwiSWlJWIeA75Lfd6A(TLRPC.TL_langPackLanguage r8, LaunchActivity r9, AlertDialog r10, int r11) {
        boolean r102 = r8.official;
        String r112 = r8.lang_code;
        if (r102 == false) goto L5;
        String r103 = "remote_" + r112;
    L6:
        LocaleController.LocaleInfo r104 = LocaleController.getInstance().getLanguageFromDict(r103);
        if (r104 != null) goto L12;
        r104 = new LocaleController.LocaleInfo();
        r104.name = r8.native_name;
        r104.nameEnglish = r8.name;
        r104.shortName = r8.lang_code;
        r104.baseLangCode = r8.base_lang_code;
        r104.pluralLangCode = r8.plural_code;
        r104.isRtl = r8.rtl;
        if (r8.official == false) goto L11;
        r104.pathToFile = "remote";
        goto L12
    L11:
        r104.pathToFile = "unofficial";
    L12:
        LocaleController.getInstance().applyLanguage(r104, true, false, false, true, UserConfig.selectedAccount, null);
        r9.rebuildAllFragments(true);
        return;
    L5:
        r103 = "unofficial_" + r112;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$6oZc5n3MdDzwF4a5SzW4RKje_ok(Runnable r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == false) goto L8;
        r0.run();
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$70kgG05GPY4bbiUAQmjvxShXoBI(long r1, AlertDialog r3, AlertDialog.OnButtonClickListener r4, TextView r5, int r6, KeyEvent r7) {
        if (r6 == 6) goto L9;
        if (r1 > 0) goto L7;
    L12:
        return false;
    L7:
        if (r7.getKeyCode() != 66) goto L12;
    L9:
        if (r3.isShowing() == false) goto L12;
        r4.onClick(r3, 0);
        return true;
    }

    public static /* synthetic */ void $r8$lambda$7QqWO7kKX_hCREqJN28fu7I9nAo(boolean[] r1, Runnable r2) {
        if (r1[0] == false) goto L7;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    /* renamed from: $r8$lambda$7Usn3xFHr7f-Ifi1lYQswWu23rM, reason: not valid java name */
    public static /* synthetic */ void m8707$r8$lambda$7Usn3xFHr7fIfi1lYQswWu23rM(Context r2, String r3, long r4, boolean r6, Browser.Progress r7) {
        Uri r32 = Uri.parse(r3);
        if (r4 != 0) goto L5;
        boolean r42 = true;
    L6:
        Browser.openUrl(r2, r32, r42, r6, r7);
        return;
    L5:
        r42 = false;
        goto L6
    }

    /* renamed from: $r8$lambda$7amHu8URFBNRD36GrkRf5M-Kr8I, reason: not valid java name */
    public static /* synthetic */ void m8708$r8$lambda$7amHu8URFBNRD36GrkRf5MKr8I(EditTextBoldCursor r0) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$7mRfxjOHDJL1Si1xUrnwuBHK5zQ(CheckBoxCell[] r1, View r2) {
        r1[((Integer) r2.getTag()).intValue()].setChecked(!r1[r2.intValue()].isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$7o7k7qpPmmMfrAymo4NyxHyiJ6c(Runnable r0, AlertDialog r1, int r2) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ String $r8$lambda$7w5QnENBrX9PLIJoNdbHch723kc(int r5) {
        boolean r0 = LocaleController.is24HourFormat;
        int r2 = 12;
        if (r0 == false) goto L5;
        int r3 = 24;
    L6:
        int r32 = r5 % r3;
        if ((r5 % 12) != 0) goto L10;
        if (r0 == true) goto L10;
    L11:
        String r02 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r2)});
        if (r5 >= 24) goto L14;
        return r02;
    L14:
        return LocaleController.formatString(R.string.BusinessHoursNextDayPicker, new Object[]{r02});
    L10:
        r2 = r32;
        goto L11
    L5:
        r3 = 12;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$8CCWsNUayFqU_OVFndpKF4JwN3A(ArrayList r3) {
        if (r3 == null) goto L9;
        BaseFragment r32 = LaunchActivity.getLastFragment();
        if (r32 != null) goto L7;
        return;
    L7:
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        r32.showAsSheet(new PrivacyControlActivity(11), r0);
        return;
    }

    public static /* synthetic */ void $r8$lambda$8RQ9EXD23RcWTImIAoCrrgBN7RE(Utilities.Callback r0, EditText r1, AlertDialog r2, int r3) {
        r2.dismiss();
        if (r0 == null) goto L6;
        r0.run(r1.getText().toString());
        return;
    }

    public static /* synthetic */ void $r8$lambda$8iOZHHjibVZj7obo7SH8qRfO5VI(int r12, ArrayList r13, long r14, Activity r16, Theme.ResourcesProvider r17, Utilities.Callback r18, HashMap r19, Boolean r20) {
        if (r20.booleanValue() == false) goto L13;
        SharedPreferences.Editor r0 = MessagesController.getInstance(r12).getMainSettings().edit();
        int r1 = r13.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L12;
        Object r3 = r13.get(r2);
        r2 = r2 + 1;
        Long r32 = (Long) r3;
        long r4 = r32.longValue();
        long r6 = MessagesController.getInstance(r12).getSendPaidMessagesStars(r4);
        if (r6 > 0) goto L11;
        if (r4 <= 0) goto L11;
        r6 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(r12).isUserContactBlocked(r4));
    L11:
        r0.putLong("ask_paid_message_" + r4 + "_price", r6);
        StarsController.getInstance(r12).justAgreedToNotAskDialogs.put(r32, Long.valueOf(System.currentTimeMillis()));
        goto L5
    L12:
        r0.apply();
    L13:
        AlertsCreator$$ExternalSyntheticLambda111 r33 = new AlertsCreator$$ExternalSyntheticLambda111(r12, r14, r16, r13, r17, r18, r19);
        if (StarsController.getInstance(r12).balanceAvailable() == true) goto L17;
        StarsController.getInstance(r12).invalidateBalance(r33);
        return;
    L17:
        r33.run();
    }

    /* renamed from: $r8$lambda$8l55-3duIzC18eYQPQBmGhbOFYc, reason: not valid java name */
    public static /* synthetic */ void m8709$r8$lambda$8l553duIzC18eYQPQBmGhbOFYc(Activity r0, AlertDialog r1, int r2) {
        if (r0.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) goto L6;
        r0.requestPermissions(new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"}, 30);
        return;
    }

    public static /* synthetic */ void $r8$lambda$9LrB06lCCWQliiGNRL837aCC1VE(BaseFragment r0, AlertDialog r1, int r2) {
        performAskAQuestion(r0);
    }

    /* renamed from: $r8$lambda$9x3d7pogGxkeYS1c6tiQ-dt4aJs, reason: not valid java name */
    public static /* synthetic */ void m8710$r8$lambda$9x3d7pogGxkeYS1c6tiQdt4aJs(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ String $r8$lambda$A3N78y69brDFDcfSNp5AtDnPszI(int r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET + r2;
    }

    public static /* synthetic */ String $r8$lambda$A90L6jSAy3tPxH5dLZOauazJ0f8(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$A9CsCTjLm111axuBTTYnjeSO95w(Runnable r0, AlertDialog r1, int r2) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$ADkHyuSF8XrVGKedIjp7IUXCv8c(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$AVvC_MenWh6xxqWt49ZWzfDP-2w, reason: not valid java name */
    public static /* synthetic */ void m8711$r8$lambda$AVvC_MenWh6xxqWt49ZWzfDP2w(long r0, NumberPicker r2, NumberPicker r3, NumberPicker r4, Calendar r5, MessagesStorage.IntCallback r6, BottomSheet.Builder r7, View r8) {
        checkCalendarDate(r0, r2, r3, r4);
        r5.set(1, r4.getValue());
        r5.set(2, r3.getValue());
        r5.set(5, r2.getValue());
        r5.set(12, 0);
        r5.set(11, 0);
        r5.set(13, 0);
        r6.run((int) (r5.getTimeInMillis() / 1000));
        r7.getDismissRunnable().run();
    }

    public static /* synthetic */ void $r8$lambda$AXfzc1V0Fk6E4ivE9LTDxYqr1bc(NumberPicker r6, NumberPicker r7, NumberPicker r8, Calendar r9, ScheduleDatePickerDelegate r10, BottomSheet.Builder r11, View r12) {
        boolean r62 = checkScheduleDate(null, null, 0, r6, r7, r8);
        r9.setTimeInMillis(System.currentTimeMillis());
        r9.add(6, r6.getValue());
        r9.set(11, r7.getValue());
        r9.set(12, r8.getValue());
        if (r62 == false) goto L5;
        r9.set(13, 0);
        r9.set(14, 0);
    L5:
        r10.didSelectDate(true, (int) (r9.getTimeInMillis() / 1000), 0);
        r11.getDismissRunnable().run();
    }

    public static /* synthetic */ boolean $r8$lambda$Am4aqzrBNrWuGx35tYfMf3dBUkk(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$AvuHCR1k6V5fBzOuStuj5qfZeNs(Context r1, AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        r1.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$AwguZseODyQKOO6OAJGEVmKorI0(Runnable r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == false) goto L8;
        r0.run();
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$AwoAOrUvysrgnd0Ax8bTi1NNwP0(EditTextBoldCursor r1, int r2, MessagesStorage.StringCallback r3, AlertDialog[] r4, View r5, TextView r6, int r7, KeyEvent r8) {
        if (r7 != 6) goto L14;
        String r62 = r1.getText().toString();
        if (r62.length() <= r2) goto L8;
        AndroidUtilities.shakeView(r1);
        return true;
    L8:
        r3.run(r62);
        AlertDialog r12 = r4[0];
        if (r12 == null) goto L11;
        r12.dismiss();
    L11:
        if (r5 == null) goto L13;
        r5.requestFocus();
    L13:
        return true;
    L14:
        return false;
    }

    /* renamed from: $r8$lambda$B32SY-B81IKGaTCy4ZjWiasBWcw, reason: not valid java name */
    public static /* synthetic */ void m8712$r8$lambda$B32SYB81IKGaTCy4ZjWiasBWcw(BaseFragment r0, EditTextBoldCursor r1, Theme.ThemeAccent r2, Theme.ThemeInfo r3, AlertDialog r4, View r5) {
        if (r0.getParentActivity() != null) goto L6;
        return;
    L6:
        if (r1.length() != 0) goto L10;
        r1.performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(r1);
        return;
    L10:
        if ((r0 instanceof ThemePreviewActivity) == false) goto L12;
        Theme.applyPreviousTheme();
        r0.finishFragment();
    L12:
        if (r2 == null) goto L15;
        r3.setCurrentAccentId(r2.id);
        Theme.refreshThemeColors();
        Utilities.searchQueue.postRunnable(new AlertsCreator$$ExternalSyntheticLambda172(r1, r4, r0));
        return;
    L15:
        processCreate(r1, r4, r0);
    }

    /* renamed from: $r8$lambda$B3CCxfTO-bHkOakE2Umujkhi7V4, reason: not valid java name */
    public static /* synthetic */ void m8713$r8$lambda$B3CCxfTObHkOakE2Umujkhi7V4(EditText r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static /* synthetic */ String $r8$lambda$B_BRpu4PIFqE41AwqsrNE_iFnXU(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$B_MntKRtRTIQ5Hy1AOEzBFyoS_8(boolean[] r0, int[] r1, int[] r2, NumberPicker r3, NumberPicker r4, NumberPicker r5, NumberPicker r6, FormattedDatePickerDelegate r7, BottomSheet.Builder r8, View r9) {
        r0[0] = false;
        int r02 = r1[r2[0]];
        r7.didSelectDate((int) (checkFormattedDateInput(null, r3, r4, r5, r6, r02) / 1000), r02);
        r8.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$BmbIODRDL9Jf-ZEGx0riA5SkRDU, reason: not valid java name */
    public static /* synthetic */ void m8714$r8$lambda$BmbIODRDL9JfZEGx0riA5SkRDU(MessagesStorage.IntCallback r0, AlertDialog r1, int r2) {
        r0.run(0);
    }

    public static /* synthetic */ void $r8$lambda$BuM5zw7btB3HnBnmq7dGcmazMTM(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$C2L7G0xsxJqpq97s9KKCDDJl33w(BlockDialogCallback r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.run(r1[0], r1[1]);
    }

    public static /* synthetic */ String $r8$lambda$CBGP4qfoLAdcacqIESswwtUHgFA(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$Cz3wuWTWR9Idipo9ackwcAIKHO4() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        BaseFragment.BottomSheetParams r1 = new BaseFragment.BottomSheetParams();
        r1.transitionFromLeft = true;
        r1.allowNestedScroll = false;
        r0.showAsSheet(new PremiumPreviewFragment("schedule_repeat"), r1);
    }

    public static /* synthetic */ void $r8$lambda$D9oh4ZDfJDJuAdh2qMMwYPcPcvQ(ArrayList r0, Runnable r1, AlertDialog.Builder r2, View r3) {
        SharedConfig.setSecretMapPreviewType(((Integer) r0.get(((Integer) r3.getTag()).intValue())).intValue());
        if (r1 == null) goto L5;
        r1.run();
    L5:
        r2.getDismissRunnable().run();
    }

    public static /* synthetic */ void $r8$lambda$DmCpeigz3pbtaYG4DvqKXDA9KMw(SharedPreferences r1, AlertDialog r2, int r3, BaseFragment r4, TLObject r5, TLRPC.TL_error r6) {
        if (r6 != null) goto L5;
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda165(r1, (TLRPC.TL_help_support) r5, r2, r3, r4));
        return;
    L5:
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda166(r2));
    }

    public static /* synthetic */ String $r8$lambda$Dn8pmCsIIynmaZM4RXRTLKjWjsc(int r0, int r1) {
        if (r1 != r0) goto L6;
        return "—";
    L6:
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$ELZyW1pRDW5quWeAPRVNxAsJ25k(int r8, NumberPicker r9, NumberPicker r10, NumberPicker r11, TextView r12, NumberPicker r13, int r14, int r15) {
        checkScheduleDate(null, null, r8, 3, r9, r10, r11);
        checkPollCloseCustomDeadline(r12, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$Ec4YWT6WDAVf4QJnnkmc26Ei3L8(Context r0, AlertDialog r1, int r2) {
        Browser.openUrl(r0, BuildVars.RELEASES_URL);
    }

    /* renamed from: $r8$lambda$EiHl3Zc5r3GSKGa2a_fe-npvjJ8, reason: not valid java name */
    public static /* synthetic */ boolean m8715$r8$lambda$EiHl3Zc5r3GSKGa2a_fenpvjJ8(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$ExmC4O4Lbp9a4_gnWEQi8bZkNf8(LaunchActivity r0, AlertDialog r1, int r2) {
        r0.presentFragment(new LanguageSelectActivity());
    }

    public static /* synthetic */ void $r8$lambda$F13ytT5slXERaTEg_lytSyQvct4(boolean r11, boolean r12, boolean r13, TLRPC.User r14, BaseFragment r15, boolean r16, TLRPC.Chat r17, boolean r18, boolean[] r19, boolean r20, MessagesStorage.BooleanCallback r21, Theme.ResourcesProvider r22, AlertDialog.Builder r23, int r24, Context r25, AlertDialog r26, int r27) {
        boolean r0 = false;
        if (r11 == true) goto L24;
        if (r12 == true) goto L24;
        if (r13 == true) goto L24;
        if (UserObject.isUserSelf(r14) == false) goto L10;
        createClearOrDeleteDialogAlert(r15, r16, true, r17, r14, false, r18, r19[0], r20, r21, r22);
        return;
    L10:
        if (r14 == null) goto L16;
        if (r19[0] == false) goto L16;
        MessagesStorage.getInstance(r15.getCurrentAccount()).getMessagesCount(r14.id, new AlertsCreator$$ExternalSyntheticLambda160(r15, r16, r17, r14, r18, r19, r20, r21, r22));
        return;
    L16:
        if (ChatObject.isChannel(r17) == false) goto L24;
        if (r17.creator == false) goto L24;
        if (r19[0] == true) goto L24;
        Browser.Progress r1 = r23.create().makeButtonLoading(-1);
        r1.init();
        TLRPC.TL_channels_getFutureCreatorAfterLeave r112 = new TLRPC.TL_channels_getFutureCreatorAfterLeave();
        r112.channel = MessagesController.getInputChannel(r17);
        ConnectionsManager.getInstance(r24).sendRequestTyped(r112, new AiTonesController$$ExternalSyntheticLambda0(), new AlertsCreator$$ExternalSyntheticLambda161(r1, r21, r12, r19, r15, r17, r25, r24, r22));
        return;
    L24:
        if (r21 == null) goto L31;
        if (r12 == false) goto L27;
    L28:
        r0 = true;
    L29:
        r21.run(r0);
        return;
    L27:
        if (r19[0] == false) goto L29;
    }

    public static /* synthetic */ void $r8$lambda$FObFRL5WEdlClqr70Mr7_ZYI7S8(ArrayList r7, int r8, BaseFragment r9, Theme.ResourcesProvider r10, DialogInterface r11, int r12) {
        if (r12 != 0) goto L5;
        int r6 = 0;
    L12:
        if (r7 == null) goto L18;
        int r122 = 0;
    L15:
        if (r122 >= r7.size()) goto L18;
        NotificationsController.getInstance(UserConfig.selectedAccount).setDialogNotificationsSettings(((Long) r7.get(r122)).longValue(), r8, r6);
        r122 = r122 + 1;
    L18:
        if (BulletinFactory.canShowBulletin(r9) == false) goto L22;
        BulletinFactory.createMuteBulletin(r9, r6, 0, r10).show();
        return;
    L22:
        return;
    L5:
        int r0 = 1;
        if (r12 != 1) goto L8;
    L7:
        r6 = r0;
        goto L12
    L8:
        r0 = 2;
        if (r12 == 2) goto L7;
        r0 = 3;
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$FmRRU9Xd6EHNstJ48KoTTJZsWMY(NumberPicker r0, NumberPicker r1, SoundFrequencyDelegate r2, BottomSheet.Builder r3, View r4) {
        r2.didSelectValues(r0.getValue() + 1, (r1.getValue() + 1) * 60);
        r3.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$Fr4Y93jgZTnhsofZoj65-3br0Fs, reason: not valid java name */
    public static /* synthetic */ void m8716$r8$lambda$Fr4Y93jgZTnhsofZoj653br0Fs(NumberPicker r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, int r4, int r5) {
        checkScheduleDate(null, null, 0, r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FzA0nC9sUQ0yyNhRCxcdBjafWKs(long r2, int r4, Runnable r5, AlertDialog r6, int r7) {
        SharedPreferences.Editor r62 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if (r2 == 0) goto L6;
        r62.putInt("color_" + r2, 0);
    L20:
        r62.apply();
        if (r5 == null) goto L24;
        r5.run();
        return;
    L24:
        return;
    L6:
        if (r4 != 1) goto L8;
        r62.putInt("MessagesLed", 0);
        goto L20
    L8:
        if (r4 != 0) goto L11;
        r62.putInt("GroupLed", 0);
        goto L20
    L11:
        if (r4 != 3) goto L14;
        r62.putInt("StoriesLed", 0);
        goto L20
    L14:
        if (r4 != 5) goto L16;
    L19:
        r62.putInt("ReactionsLed", 0);
        goto L20
    L16:
        if (r4 == 4) goto L19;
        r62.putInt("ChannelLed", 0);
        goto L20
    }

    public static /* synthetic */ void $r8$lambda$GrCDwBB6gVNxCOYpueg7yEDVqxU(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda199(r0));
    }

    public static /* synthetic */ void $r8$lambda$HEvbBaVkS3tygf2fstRGhrdMj8k(AtomicBoolean r0, Consumer r1, AlertDialog r2, int r3) {
        r0.set(true);
        r1.accept(Boolean.FALSE);
    }

    public static /* synthetic */ void $r8$lambda$HKeVx7WnmXlQkXrs_AlsLS8rHSU(MessagesStorage.BooleanCallback r0, boolean[] r1, AlertDialog r2, int r3) {
        if (r0 == null) goto L5;
        r0.run(r1[0]);
        return;
    }

    public static /* synthetic */ String $r8$lambda$HeJlE3m0NqZoCtJgbTlCYDDzOlQ(int[] r2, int r3) {
        int r22 = r2[r3];
        if (r22 != 0) goto L7;
        return LocaleController.getString(R.string.MuteNever);
    L7:
        if (r22 >= 60) goto L11;
        return LocaleController.formatPluralString("Minutes", r22, new Object[0]);
    L11:
        if (r22 >= 1440) goto L15;
        return LocaleController.formatPluralString("Hours", r22 / 60, new Object[0]);
    L15:
        if (r22 >= 10080) goto L19;
        return LocaleController.formatPluralString("Days", r22 / 1440, new Object[0]);
    L19:
        if (r22 >= 44640) goto L23;
        return LocaleController.formatPluralString("Weeks", r22 / 10080, new Object[0]);
    L23:
        if (r22 >= 525600) goto L27;
        return LocaleController.formatPluralString("Months", r22 / 44640, new Object[0]);
    L27:
        return LocaleController.formatPluralString("Years", r22 / 525600, new Object[0]);
    }

    /* renamed from: $r8$lambda$HmNIiD4N49JH4hKLdiE4jUULJ-0, reason: not valid java name */
    public static /* synthetic */ void m8717$r8$lambda$HmNIiD4N49JH4hKLdiE4jUULJ0(Context r2, AlertDialog r3, int r4) {
        if (r2 == null) goto L14;
        Intent r42 = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + r2.getPackageName()));     // Catch: Exception -> L10
        Activity r32 = AndroidUtilities.findActivity(r2);     // Catch: Exception -> L10
        if ((r32 instanceof LaunchActivity) == false) goto L8;
        r32.startActivityForResult(r42, 105);     // Catch: Exception -> L10
        return;
    L8:
        r2.startActivity(r42);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public static /* synthetic */ void $r8$lambda$Htpg1_4_5jM2uBTX0BHN7zjSByY(AlertDialog[] r0, MessagesStorage.LongCallback r1, long r2, View r4) {
        r0[0].dismiss();
        r1.run(-r2);
    }

    /* renamed from: $r8$lambda$I8Xr-EwIlzTFlywUAdOGStgIYxI, reason: not valid java name */
    public static /* synthetic */ void m8718$r8$lambda$I8XrEwIlzTFlywUAdOGStgIYxI(CheckBoxCell r1, View r2) {
        r1.setChecked(!r1.isChecked(), true);
    }

    public static /* synthetic */ boolean $r8$lambda$ILg2DIFBjViKu6uyQfDHywCW6Hg(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ CharSequence $r8$lambda$ILqM_Y2jJ0MQyClPw8PydHaO_ec(Integer r2) {
        return LocaleController.formatPluralString("Hours", r2.intValue(), new Object[0]);
    }

    public static /* synthetic */ boolean $r8$lambda$ISGHVganXhbap0xqfzUQvvZf8iE(EditTextBoldCursor r1, Utilities.Callback r2, AlertDialog[] r3, View r4, TextView r5, int r6, KeyEvent r7) {
        if (r6 != 6) goto L14;
        String r52 = r1.getText().toString().trim();
        if (isValidUrl(r52) == true) goto L8;
        AndroidUtilities.shakeView(r1);
        return true;
    L8:
        r2.run(r52);
        AlertDialog r12 = r3[0];
        if (r12 == null) goto L11;
        r12.dismiss();
    L11:
        if (r4 == null) goto L13;
        r4.requestFocus();
    L13:
        return true;
    L14:
        return false;
    }

    public static /* synthetic */ String $r8$lambda$IrhT15W1uiGQslDYnzAMwyz53TY(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    /* renamed from: $r8$lambda$JGVieBwip74p0UURCTN0-YHu3po, reason: not valid java name */
    public static /* synthetic */ boolean m8719$r8$lambda$JGVieBwip74p0UURCTN0YHu3po(long r4, TLObject r6) {
        if ((r6 instanceof TLRPC.User) == false) goto L8;
        if (((TLRPC.User) r6).id == r4) goto L11;
        return true;
    L11:
        return false;
    L8:
        if ((r6 instanceof TLRPC.Chat) == true) goto L10;
        return false;
    L10:
        return !ChatObject.hasAdminRights((TLRPC.Chat) r6);
    }

    public static /* synthetic */ void $r8$lambda$JiwJr3bR3ySTvE4ZdMp08xfUBy0() {
    }

    public static /* synthetic */ boolean $r8$lambda$Jjq3hMRIN7eWfFjFhIsF2NznXhU(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$KhcpysP5nkkpDoA4FTkC9k5dpuQ(ActionBarMenuItem r1, ScheduleDatePickerColors r2, View r3) {
        r1.toggleSubMenu();
        r1.setPopupItemsColor(r2.subMenuTextColor, false);
        r1.setupPopupRadialSelectors(r2.subMenuSelectorColor);
        r1.redrawPopup(r2.subMenuBackgroundColor);
    }

    /* renamed from: $r8$lambda$KxBOIUYT-GOYrp8SW9YsZt7HpYM, reason: not valid java name */
    public static /* synthetic */ void m8720$r8$lambda$KxBOIUYTGOYrp8SW9YsZt7HpYM(MessagesStorage.BooleanCallback r1) {
        if (r1 == null) goto L5;
        r1.run(false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$LRAdENUP_MozpZnBzKEsiPXAOfU(Runnable r0, AlertDialog r1, int r2) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$LdJu7Qmuemuf-6TZOVhs9v4mB0o, reason: not valid java name */
    public static /* synthetic */ void m8721$r8$lambda$LdJu7Qmuemuf6TZOVhs9v4mB0o(String[] r5, int[] r6, TextView r7, ButtonWithCounterView r8, NumberPicker r9, NumberPicker r10, NumberPicker r11, NumberPicker r12, int[] r13) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append(LocaleController.getString(R.string.DateFormatOption));
        r0.append(" ");
        int r1 = r0.length();
        r0.append(r5[r6[0]]);
        r0.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r1, r0.length(), 33);
        r0.append(" v");
        ColoredImageSpan r52 = new ColoredImageSpan(R.drawable.arrows_select);
        r52.spaceScaleX = 0.7f;
        r52.translate(AndroidUtilities.dp(-1.33f), AndroidUtilities.dp(0.0f));
        r52.setAlpha(0.75f);
        r0.setSpan(r52, r0.length() - 1, r0.length(), 33);
        r7.setText(r0);
        checkFormattedDateInput(r8, r9, r10, r11, r12, r13[r6[0]]);
    }

    public static /* synthetic */ String $r8$lambda$LiMtNDofYI_wQ00E_vlWgvoAMVc(int[] r2, int r3) {
        int r22 = r2[r3];
        if (r22 != 0) goto L7;
        return LocaleController.getString(R.string.AutoDeleteNever);
    L7:
        if (r22 >= 10080) goto L11;
        return LocaleController.formatPluralString("Days", r22 / 1440, new Object[0]);
    L11:
        if (r22 >= 44640) goto L15;
        return LocaleController.formatPluralString("Weeks", r22 / 10080, new Object[0]);
    L15:
        if (r22 >= 525600) goto L19;
        return LocaleController.formatPluralString("Months", r22 / 44640, new Object[0]);
    L19:
        return LocaleController.formatPluralString("Years", r22 / 525600, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$LrNPZuNpi1fP4aSKDHVBU1LHgeU(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$MSxnwsmqBQLnTqouFS7kXjhlKXY(Utilities.Callback2 r0, CheckBoxCell r1, AlertDialog[] r2, AlertDialog r3, int r4) {
        r0.run(Boolean.FALSE, Boolean.valueOf(r1.isChecked()));
        AlertDialog r02 = r2[0];
        if (r02 == null) goto L6;
        r02.dismiss();
        return;
    }

    public static /* synthetic */ void $r8$lambda$MXrVsrwggVFspmfqS18NtXBMRT0(Utilities.Callback r0, NumberPicker r1, DialogInterface r2) {
        r0.run(Integer.valueOf(r1.getValue()));
    }

    public static /* synthetic */ void $r8$lambda$MsXNQ8U6DHyZcXFzwN3CJ_zkd5s(int r5, int r6, NumberPicker r7, NumberPicker r8, int r9, LinearLayout r10, Boolean r11) {
        int r0 = r5 % 60;
        int r52 = (r5 - r0) / 60;
        int r1 = r6 % 60;
        int r62 = (r6 - r1) / 60;
        int r2 = 59;
        if (r1 != 0) goto L7;
        if (r62 <= 0) goto L7;
        r62 = r62 - 1;
        r1 = 59;
    L7:
        if (r11.booleanValue() == false) goto L9;
        int r92 = r7.getValue();
        int r3 = r8.getValue();
    L12:
        r7.setMinValue(r52);
        r7.setMaxValue(r62);
        if (r92 <= r62) goto L15;
        r7.setValue(r62);
        r92 = r62;
    L17:
        if (r92 > r52) goto L23;
        r8.setMinValue(r0);
        if (r52 != r62) goto L21;
        r2 = r1;
    L21:
        r8.setMaxValue(r2);
    L32:
        if (r3 <= r8.getMaxValue()) goto L35;
        r3 = r8.getMaxValue();
        r8.setValue(r3);
    L38:
        if (r11.booleanValue() == true) goto L40;
        r7.setValue(r92);
        r8.setValue(r3);
    L40:
        r10.invalidate();
        return;
    L35:
        if (r3 >= r8.getMinValue()) goto L38;
        r3 = r8.getMinValue();
        r8.setValue(r3);
        goto L38
    L23:
        if (r92 < r62) goto L28;
        if (r52 == r62) goto L27;
        r0 = 0;
    L27:
        r8.setMinValue(r0);
        r8.setMaxValue(r1);
        goto L32
    L28:
        if (r52 != r62) goto L30;
        r8.setMinValue(r0);
        r8.setMaxValue(r1);
        goto L32
    L30:
        r8.setMinValue(0);
        r8.setMaxValue(59);
        goto L32
    L15:
        if (r92 >= r52) goto L17;
        r7.setValue(r52);
        r92 = r52;
        goto L17
    L9:
        r3 = r9 % 60;
        r92 = (r9 - r3) / 60;
        if (r92 != 24) goto L12;
        r92 = r92 - 1;
        r3 = 59;
        goto L12
    }

    /* renamed from: $r8$lambda$N-pY1DpdIMd8X6TtwPRDhWMCbZM, reason: not valid java name */
    public static /* synthetic */ void m8722$r8$lambda$NpY1DpdIMd8X6TtwPRDhWMCbZM(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$NCQRxBjszVhUVo0Miw27kuKThQY(BottomSheet.Builder r0, Utilities.Callback r1, View r2) {
        r0.getDismissRunnable().run();
        r1.run(null);
    }

    public static /* synthetic */ void $r8$lambda$NNSJfy2sja2z9fBQu3TYVjqPSO4(boolean[] r0, String r1, long r2, long r4, NumberPicker r6, NumberPicker r7, NumberPicker r8, Calendar r9, ScheduleDatePickerDelegate r10, boolean[] r11, int[] r12, BottomSheet.Builder r13, View r14) {
        r0[0] = false;
        if (r1 == null) goto L7;
        int r02 = 3;
    L5:
        int r3 = r02;
    L10:
        boolean r03 = checkScheduleDate(null, null, r3, r6, r7, r8);
        r9.setTimeInMillis(System.currentTimeMillis());
        r9.add(6, r6.getValue());
        r9.set(11, r7.getValue());
        r9.set(12, r8.getValue());
        if (r03 == false) goto L13;
        r9.set(13, 0);
        r9.set(14, 0);
    L13:
        r10.didSelectDate(r11[0], (int) (r9.getTimeInMillis() / 1000), r12[0]);
        r13.getDismissRunnable().run();
        return;
    L7:
        if (r2 != r4) goto L9;
        r02 = 1;
        goto L5
    L9:
        r3 = 0;
        goto L10
    }

    public static /* synthetic */ String $r8$lambda$NOMVvCxYCTpiagkgFs21K69ZNsU(int r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET + r2;
    }

    /* renamed from: $r8$lambda$OJr-OFv3MzH5qWx3hfnlcA8Z5NM, reason: not valid java name */
    public static /* synthetic */ void m8723$r8$lambda$OJrOFv3MzH5qWx3hfnlcA8Z5NM(BottomSheet r0, Theme.ResourcesProvider r1, FrameLayout r2, String[] r3, int[] r4, Runnable r5, View r6) {
        ItemOptions r02 = ItemOptions.makeOptions(r0.container, r1, r2);
        int r12 = 0;
    L4:
        if (r12 >= r3.length) goto L6;
        r02.add(r3[r12], new AlertsCreator$$ExternalSyntheticLambda84(r4, r12, r5));
        r12 = r12 + 1;
        goto L4
    L6:
        r02.setGravity(1);
        r02.show();
    }

    public static /* synthetic */ boolean $r8$lambda$OanBZlfmFL8_qN6PlxbHZsAgpE4(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$P3UZJUdRWFnUu19WlGm7uCAc-CM, reason: not valid java name */
    public static /* synthetic */ void m8724$r8$lambda$P3UZJUdRWFnUu19WlGm7uCAcCM(NumberPicker r0, int r1, int r2) {
    }

    public static /* synthetic */ void $r8$lambda$P8NMOUvbwYK2xHrms1N6B4BMlg0(AlertDialog[] r3, int[] r4, int r5, Runnable r6, BaseFragment r7) {
        AlertDialog r1 = r3[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setOnCancelListener(new AlertsCreator$$ExternalSyntheticLambda168(r4, r5, r6));
        r7.showDialog(r3[0]);
    }

    public static /* synthetic */ void $r8$lambda$PED2WFIBJBnAmPRdgc03JXrXMkI(BottomSheet r0, View r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$PwnKAwMK2NHHji4iN_8VaNIVkIE(int[] r23, int[] r24, int r25, TLRPC.ChannelParticipant[] r26, int r27, AlertDialog[] r28, BaseFragment r29, TLRPC.User r30, TLRPC.Chat r31, TLRPC.EncryptedChat r32, TLRPC.ChatFull r33, long r34, MessageObject r36, SparseArray[] r37, MessageObject.GroupedMessages r38, int r39, int r40, Runnable r41, Runnable r42, Theme.ResourcesProvider r43, TLObject r44, TLRPC.TL_error r45) {
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda173(r23, r24, r25, r44, r26, r27, r28, r29, r30, r31, r32, r33, r34, r36, r37, r38, r39, r40, r41, r42, r43));
    }

    public static /* synthetic */ String $r8$lambda$Pyb8SQ7BNI5TCroB6zY8vjY20rU(int r5) {
        if (r5 == 0) goto L4;
        Calendar r0 = Calendar.getInstance();
        int r2 = r0.get(1);
        r0.add(6, r5);
        long r3 = r0.getTimeInMillis();
        int r02 = r0.get(1);
        if (r02 == r2) goto L8;
    L11:
        if (r02 != r2) goto L15;
        return LocaleController.getInstance().getFormatterScheduleDay().format(r3);
    L15:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r3);
    L8:
        if (r5 >= 7) goto L11;
        return LocaleController.getInstance().getFormatterWeek().format(r3) + ", " + LocaleController.getInstance().getFormatterScheduleDay().format(r3);
    L4:
        return LocaleController.getString(R.string.MessageScheduleToday);
    }

    /* renamed from: $r8$lambda$QBsRViu-FHoT2t-ZXxJrB59-PHg, reason: not valid java name */
    public static /* synthetic */ void m8725$r8$lambda$QBsRViuFHoT2tZXxJrB59PHg(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
        r1.dismiss();
    }

    public static /* synthetic */ String $r8$lambda$QH7KL5AaqGb8FPTLVjuwxE35JjM(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    /* renamed from: $r8$lambda$QO_sCcaXxHlbSGh500edC5GV-R0, reason: not valid java name */
    public static /* synthetic */ void m8726$r8$lambda$QO_sCcaXxHlbSGh500edC5GVR0(Browser.Progress r7, MessagesStorage.BooleanCallback r8, boolean r9, boolean[] r10, BaseFragment r11, TLRPC.Chat r12, Context r13, int r14, Theme.ResourcesProvider r15, TLRPC.User r16, TLRPC.TL_error r17) {
        r7.end();
        TLRPC.User r72 = r16;
        if ((r72 instanceof TLRPC.TL_userEmpty) == false) goto L5;
        r72 = null;
    L5:
        TLRPC.User r3 = r72;
        if (r3 != null) goto L14;
        if (r8 == null) goto L16;
        if (r9 == true) goto L11;
        boolean r73 = false;
        if (r10[0] == true) goto L11;
    L12:
        r8.run(r73);
        return;
    L11:
        r73 = true;
        goto L12
    L16:
        return;
    L14:
        showLeaveGroupWithFutureOwner(r11, r12, r3, new AlertsCreator$$ExternalSyntheticLambda178(r13, r12, r3, r14, r8, r15), new AlertsCreator$$ExternalSyntheticLambda179(r8));
    }

    /* renamed from: $r8$lambda$QbYgsw9-uEcknAJJYYuEhlAgBZ0, reason: not valid java name */
    public static /* synthetic */ void m8727$r8$lambda$QbYgsw9uEcknAJJYYuEhlAgBZ0(boolean[] r1, ScheduleDatePickerDelegate r2, BottomSheet.Builder r3, View r4) {
        r1[0] = false;
        r2.didSelectDate(true, -1, 0);
        r3.getDismissRunnable().run();
    }

    public static /* synthetic */ void $r8$lambda$QbyoH1oSRt3LziKEF7tW6ZMU3NA(int[] r5, long r6, String r8, long r9, AlertDialog.Builder r11, Runnable r12, View r13) {
        r5[0] = ((Integer) r13.getTag()).intValue();
        SharedPreferences.Editor r132 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if (r6 == 0) goto L14;
        int r52 = r5[0];
        if (r52 != 0) goto L7;
        r132.putInt(r8, 0);
    L13:
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannel(r6, r9);
    L35:
        r132.apply();
        r11.getDismissRunnable().run();
        if (r12 == null) goto L39;
        r12.run();
        return;
    L39:
        return;
    L7:
        if (r52 != 1) goto L9;
        r132.putInt(r8, 1);
        goto L13
    L9:
        if (r52 != 2) goto L11;
        r132.putInt(r8, 3);
        goto L13
    L11:
        if (r52 != 3) goto L13;
        r132.putInt(r8, 2);
        goto L13
    L14:
        int r53 = r5[0];
        if (r53 != 0) goto L17;
        r132.putInt(r8, 2);
    L26:
        if (r8.equals("vibrate_channel") == false) goto L29;
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannelGlobal(2);
        goto L35
    L29:
        if (r8.equals("vibrate_group") == false) goto L32;
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannelGlobal(0);
        goto L35
    L32:
        if (r8.equals("vibrate_react") == false) goto L34;
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannelGlobal(4);
        goto L35
    L34:
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannelGlobal(1);
        goto L35
    L17:
        if (r53 != 1) goto L19;
        r132.putInt(r8, 0);
        goto L26
    L19:
        if (r53 != 2) goto L21;
        r132.putInt(r8, 1);
        goto L26
    L21:
        if (r53 != 3) goto L23;
        r132.putInt(r8, 3);
        goto L26
    L23:
        if (r53 != 4) goto L26;
        r132.putInt(r8, 4);
        goto L26
    }

    /* renamed from: $r8$lambda$Rhlm-_9WDqGaEyoW-1DqFrMvULo, reason: not valid java name */
    public static /* synthetic */ void m8728$r8$lambda$Rhlm_9WDqGaEyoW1DqFrMvULo(AnimatedTextView r0, NumberPicker r1, int r2, int r3) {
        if (r3 != 0) goto L5;
        r0.setText(LocaleController.getString(R.string.DisableAutoDeleteTimer));     // Catch: Exception -> L7
        return;
    L5:
        r0.setText(LocaleController.getString(R.string.SetAutoDeleteTimer));     // Catch: Exception -> L7
        return;
    }

    public static /* synthetic */ void $r8$lambda$RiLWelADiOfH29U0DcSv7bM4Zys(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    /* renamed from: $r8$lambda$SKXQCmUoeDjb-FnpTrxbuZrA548, reason: not valid java name */
    public static /* synthetic */ void m8729$r8$lambda$SKXQCmUoeDjbFnpTrxbuZrA548(int r9, long r10, long r12, Utilities.Callback r14, long r15) {
        AlertsCreator$$ExternalSyntheticLambda56 r0 = new AlertsCreator$$ExternalSyntheticLambda56(r9, r10, r12, r14, r15);
        if (StarsController.getInstance(r9).balanceAvailable() == true) goto L6;
        StarsController.getInstance(r9).invalidateBalance(r0);
        return;
    L6:
        r0.run();
    }

    /* renamed from: $r8$lambda$SKiG87VB4cM3-SULTE57OxPwepU, reason: not valid java name */
    public static /* synthetic */ void m8730$r8$lambda$SKiG87VB4cM3SULTE57OxPwepU(NumberPicker r6, NumberPicker r7, NumberPicker r8, Calendar r9, StatusUntilDatePickerDelegate r10, BottomSheet.Builder r11, View r12) {
        boolean r62 = checkScheduleDate(null, null, 0, r6, r7, r8);
        r9.setTimeInMillis(System.currentTimeMillis());
        r9.add(6, r6.getValue());
        r9.set(11, r7.getValue());
        r9.set(12, r8.getValue());
        if (r62 == false) goto L5;
        r9.set(13, 0);
        r9.set(14, 0);
    L5:
        r10.didSelectDate((int) (r9.getTimeInMillis() / 1000));
        r11.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$SLSIM7GR4UWn-ibYXw6kOMgm2wA, reason: not valid java name */
    public static /* synthetic */ void m8731$r8$lambda$SLSIM7GR4UWnibYXw6kOMgm2wA(int[] r2, int r3, AlertDialog.Builder r4, Runnable r5, View r6) {
        r2[0] = ((Integer) r6.getTag()).intValue();
        SharedPreferences.Editor r62 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if (r3 != 1) goto L5;
        r62.putInt("popupAll", r2[0]);
    L8:
        r62.apply();
        r4.getDismissRunnable().run();
        if (r5 == null) goto L12;
        r5.run();
        return;
    L12:
        return;
    L5:
        if (r3 != 0) goto L7;
        r62.putInt("popupGroup", r2[0]);
        goto L8
    L7:
        r62.putInt("popupChannel", r2[0]);
        goto L8
    }

    public static /* synthetic */ void $r8$lambda$SPCP6mcmSwdBAjRoQmtw8RM4xYg(int[] r10, long r11, long r13, int r15, SharedPreferences r16, AlertDialog.Builder r17, Runnable r18, View r19) {
        int r3 = 0;
        r10[0] = ((Integer) r19.getTag()).intValue();
        SharedPreferences.Editor r2 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        if (r11 == 0) goto L15;
        int r102 = r10[0];
        if (r102 != 0) goto L7;
        r3 = 3;
    L14:
        r2.putInt("priority_" + r11, r3);
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannel(r11, r13);
    L35:
        r2.apply();
        r17.getDismissRunnable().run();
        if (r18 == null) goto L39;
        r18.run();
        return;
    L39:
        return;
    L7:
        if (r102 != 1) goto L9;
        r3 = 4;
        goto L14
    L9:
        if (r102 != 2) goto L11;
        r3 = 5;
        goto L14
    L11:
        if (r102 == 3) goto L14;
        r3 = 1;
        goto L14
    L15:
        int r112 = r10[0];
        if (r112 != 0) goto L18;
        int r113 = 4;
    L23:
        if (r15 != 1) goto L25;
        r2.putInt("priority_messages", r113);
        r10[0] = r16.getInt("priority_messages", 1);
    L34:
        NotificationsController.getInstance(UserConfig.selectedAccount).deleteNotificationChannelGlobal(r15);
        goto L35
    L25:
        if (r15 != 0) goto L27;
        r2.putInt("priority_group", r113);
        r10[0] = r16.getInt("priority_group", 1);
        goto L34
    L27:
        if (r15 != 2) goto L29;
        r2.putInt("priority_channel", r113);
        r10[0] = r16.getInt("priority_channel", 1);
        goto L34
    L29:
        if (r15 != 3) goto L31;
        r2.putInt("priority_stories", r113);
        r10[0] = r16.getInt("priority_stories", 1);
        goto L34
    L31:
        if (r15 == 4) goto L33;
        if (r15 != 5) goto L34;
    L33:
        r2.putInt("priority_react", r113);
        r10[0] = r16.getInt("priority_react", 1);
        goto L34
    L18:
        if (r112 != 1) goto L20;
        r113 = 5;
        goto L23
    L20:
        if (r112 != 2) goto L22;
        r113 = 0;
        goto L23
    L22:
        r113 = 1;
        goto L23
    }

    public static /* synthetic */ void $r8$lambda$SXCmYtWaLCYGwiTLSvC87LQTSjo(Runnable r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == false) goto L8;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$S_PmKWCL6pvR5CSC-L7cjOaUWj0, reason: not valid java name */
    public static /* synthetic */ void m8732$r8$lambda$S_PmKWCL6pvR5CSCL7cjOaUWj0(long r7, int r9, EditText r10, AlertDialog r11, int r12) {
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r7 <= 0) goto L16;
        TLRPC.UserFull r1 = MessagesController.getInstance(r9).getUserFull(UserConfig.getInstance(r9).getClientUserId());
        String r3 = r10.getText().toString().replace("\n", " ").replaceAll(" +", " ").trim();
        if (r1 == null) goto L15;
        String r4 = r1.about;
        if (r4 == null) goto L11;
        r2 = r4;
    L11:
        if (r2.equals(r3) == false) goto L14;
        AndroidUtilities.hideKeyboard(r10);
        r11.dismiss();
        return;
    L14:
        r1.about = r3;
        NotificationCenter.getInstance(r9).postNotificationName(NotificationCenter.userInfoDidLoad, new Object[]{Long.valueOf(r7), r1});
    L15:
        TL_account.updateProfile r102 = new TL_account.updateProfile();
        r102.about = r3;
        r102.flags |= 4;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{2, Long.valueOf(r7)});
        ConnectionsManager.getInstance(r9).sendRequest(r102, new AlertsCreator$$ExternalSyntheticLambda210(), 2);
    L28:
        r11.dismiss();
        return;
    L16:
        long r32 = -r7;
        TLRPC.ChatFull r122 = MessagesController.getInstance(r9).getChatFull(r32);
        String r13 = r10.getText().toString();
        if (r122 == null) goto L27;
        String r5 = r122.about;
        if (r5 == null) goto L23;
        r2 = r5;
    L23:
        if (r2.equals(r13) == false) goto L26;
        AndroidUtilities.hideKeyboard(r10);
        r11.dismiss();
        return;
    L26:
        r122.about = r13;
        NotificationCenter r103 = NotificationCenter.getInstance(r9);
        int r22 = NotificationCenter.chatInfoDidLoad;
        Boolean r6 = Boolean.FALSE;
        r103.postNotificationName(r22, new Object[]{r122, 0, r6, r6});
    L27:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{2, Long.valueOf(r7)});
        MessagesController.getInstance(r9).updateChatAbout(r32, r13, r122);
        goto L28
    }

    public static /* synthetic */ void $r8$lambda$SwguMLRhbl0UiSoYIwMNGhDHFfI(Utilities.Callback r0, HashMap r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$T6TiM1uGGJA-22H59Fb4YK9PtWA, reason: not valid java name */
    public static /* synthetic */ void m8733$r8$lambda$T6TiM1uGGJA22H59Fb4YK9PtWA(Runnable r0, DialogInterface r1) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$T9cu8CGTflzEUhwe-1H9tf0uMqs, reason: not valid java name */
    public static /* synthetic */ void m8734$r8$lambda$T9cu8CGTflzEUhwe1H9tf0uMqs(FrameLayout r0, Theme.ResourcesProvider r1, BottomSheet r2, FrameLayout r3, int[] r4, String[] r5, int[] r6, Runnable r7, View r8) {
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L6;
        BulletinFactory.of(r0, r1).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.premiumText(LocaleController.getString(R.string.MessageScheduledRepeatPremium), new AlertsCreator$$ExternalSyntheticLambda68())).show();
        return;
    L6:
        ItemOptions r02 = ItemOptions.makeOptions(r2.container, r1, r3);
        int r12 = 0;
    L8:
        if (r12 >= r4.length) goto L10;
        int r22 = r4[r12];
        r02.add(r5[r12], new AlertsCreator$$ExternalSyntheticLambda69(r6, r22, r7));
        r12 = r12 + 1;
        goto L8
    L10:
        r02.setGravity(1);
        r02.show();
    }

    public static /* synthetic */ void $r8$lambda$TQ0L29vgsmat3hW9OnHC7DqvGAQ(AlertDialog[] r0, String r1, BaseFragment r2, View r3) {
        AlertDialog r02 = r0[0];
        if (r02 == null) goto L6;
        r02.dismiss();
    L6:
        if (AndroidUtilities.addToClipboard(r1) == false) goto L9;
        BulletinFactory.of(r2).createCopyLinkBulletin().show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$TjoRtuwa3xGlRpiggpnVC9JthZM(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$U-gXUf8u_LULzSeujHDEHPPuI5M, reason: not valid java name */
    public static /* synthetic */ void m8735$r8$lambda$UgXUf8u_LULzSeujHDEHPPuI5M(NumberPicker r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, int r4, int r5) {
        checkScheduleDate(null, null, 0, r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$U6zUA9d4imoDB1nB0Qt56bQ6b7A(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    /* renamed from: $r8$lambda$UM2-TvE-SVa7MVUeDirWcrnF2g4, reason: not valid java name */
    public static /* synthetic */ void m8736$r8$lambda$UM2TvESVa7MVUeDirWcrnF2g4(DialogInterface r0) {
        SharedConfig.BackgroundActivityPrefs.increaseDismissedCount();
    }

    public static /* synthetic */ void $r8$lambda$UUjKrJ6ZjUf0_T77LY4OzU4PxIk(int[] r0, MessagesStorage.IntCallback r1, AlertDialog r2, int r3) {
        int r02 = r0[0];
        if (r02 != 0) goto L6;
        int r03 = 900;
    L12:
        r1.run(r03);
        return;
    L6:
        if (r02 != 1) goto L9;
        r03 = 3600;
        goto L12
    L9:
        if (r02 != 2) goto L11;
        r03 = 28800;
        goto L12
    L11:
        r03 = Integer.MAX_VALUE;
        goto L12
    }

    /* renamed from: $r8$lambda$UVueU0U36WCj1uI5n5WgQx-TqTU, reason: not valid java name */
    public static /* synthetic */ void m8737$r8$lambda$UVueU0U36WCj1uI5n5WgQxTqTU(NumberPicker r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, int r4, int r5) {
        updateDayPicker(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$UZvcDYS92eiNS-Mw8mxIgDsd4pI, reason: not valid java name */
    public static /* synthetic */ boolean m8738$r8$lambda$UZvcDYS92eiNSMw8mxIgDsd4pI(TextView r0, int r1, KeyEvent r2) {
        AndroidUtilities.hideKeyboard(r0);
        return false;
    }

    public static /* synthetic */ String $r8$lambda$Un4hjSam3yQ0I6lTJPG4yyeqxPE(String[] r0, int r1) {
        return r0[r1];
    }

    public static /* synthetic */ void $r8$lambda$UwYneRTtdQeuiVgviBwWevOX4DM(boolean r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, int r5) {
        if (r0 == false) goto L6;
        if (r5 != 0) goto L7;
        checkPickerDate(r1, r2, r3);
        return;
    L7:
        return;
    }

    /* renamed from: $r8$lambda$UzKw-pdmH_-YIHrzuajqlglY1go, reason: not valid java name */
    public static /* synthetic */ void m8739$r8$lambda$UzKwpdmH_YIHrzuajqlglY1go(TLRPC.User r3, BaseFragment r4, AlertDialog.Builder r5, View r6) {
        Bundle r62 = new Bundle();
        r62.putLong("user_id", r3.id);
        if (r4.getMessagesController().checkCanOpenChat(r62, r4) == false) goto L5;
        r4.presentFragment(new ProfileActivity(r62));
    L5:
        r5.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$V2mFkNc8sl91jWWCM4aH-t1POOc, reason: not valid java name */
    public static /* synthetic */ void m8740$r8$lambda$V2mFkNc8sl91jWWCM4aHt1POOc(MessageObject.GroupedMessages r8, int r9, BaseFragment r10, int r11, int r12, MessageObject r13, AlertDialog r14, int r15) {
        if (r8 != null) goto L4;
    L7:
        SendMessagesHelper.getInstance(r9).editMessage(r13, null, false, r10, null, r11 + r12, r12);
        return;
    L4:
        if (r8.messages.isEmpty() == true) goto L7;
        SendMessagesHelper.getInstance(r9).editMessage(r8.messages.get(0), null, false, r10, null, r11 + r12, r12);
    }

    public static /* synthetic */ void $r8$lambda$VDlqP2SkH_1STo7Y03mck4jyQhY(int[] r18, int[] r19, int r20, TLObject r21, TLRPC.ChannelParticipant[] r22, int r23, AlertDialog[] r24, BaseFragment r25, TLRPC.User r26, TLRPC.Chat r27, TLRPC.EncryptedChat r28, TLRPC.ChatFull r29, long r30, MessageObject r32, SparseArray[] r33, MessageObject.GroupedMessages r34, int r35, int r36, Runnable r37, Runnable r38, Theme.ResourcesProvider r39) {
        r18[0] = r18[0] + 1;
        r19[r20] = 0;
        if (r21 == null) goto L6;
        r22[r20] = ((TLRPC.TL_channels_channelParticipant) r21).participant;
    L6:
        if (r18[0] != r23) goto L13;
        r24[0].dismiss();     // Catch: Throwable -> L10
    L8:
        r24[0] = null;
        createDeleteMessagesAlert(r25, r26, r27, r28, r29, r30, r32, r33, r34, r35, r36, r22, r37, r38, r39);
        return;
    }

    public static /* synthetic */ boolean $r8$lambda$VIfLDj9XmFnd8Xat9Z_9HUnqk5M(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$VLjUSpmT6YBIljMXSsRLV2fl7Mw(int r10, long r11, Activity r13, ArrayList r14, Theme.ResourcesProvider r15, Utilities.Callback r16, HashMap r17) {
        if (StarsController.getInstance(r10).getBalance().amount >= r11) goto L8;
        if (r13 != null) goto L6;
        return;
    L6:
        long r8 = ((Long) r14.get(0)).longValue();
        new StarsIntroActivity.StarsNeededSheet(r13, r15, r11, 13, DialogObject.getShortName(r10, r8), new AlertsCreator$$ExternalSyntheticLambda180(r16, r17), r8).show();
        return;
    L8:
        r16.run(r17);
    }

    public static /* synthetic */ void $r8$lambda$VN4EyzPPJk4uwHr1LTUCyqxjV80(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ boolean $r8$lambda$VRQrE8qeVBub6KxkCgxwZs0jPpo(Runnable r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        r0.run();
        return true;
    L6:
        return false;
    }

    /* renamed from: $r8$lambda$VmE-LMvj8Ej3cEVlTGU7XLU9pdg, reason: not valid java name */
    public static /* synthetic */ String m8741$r8$lambda$VmELMvj8Ej3cEVlTGU7XLU9pdg(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$VsuoOj6LgHZM26KtLRCL7eTiDlo(EditTextBoldCursor r1, int r2, MessagesStorage.StringCallback r3, AlertDialog r4, int r5) {
        String r52 = r1.getText().toString().trim();
        if (r52.length() <= r2) goto L5;
    L9:
        AndroidUtilities.shakeView(r1);
        return;
    L5:
        if (r52.isEmpty() == true) goto L9;
        r3.run(r52);
        r4.dismiss();
    }

    /* renamed from: $r8$lambda$WhNNhT4QXCYdN-EJZ375p20liTk, reason: not valid java name */
    public static /* synthetic */ void m8742$r8$lambda$WhNNhT4QXCYdNEJZ375p20liTk(Utilities.Callback r0, NumberPicker r1, int r2, int r3) {
        r0.run(Boolean.TRUE);
    }

    /* renamed from: $r8$lambda$WkjL7WgvI-7DrvPQw-1SDGaaQiw, reason: not valid java name */
    public static /* synthetic */ void m8743$r8$lambda$WkjL7WgvI7DrvPQw1SDGaaQiw(boolean[] r2, int r3, View r4) {
        boolean r0 = !r2[r3];
        r2[r3] = r0;
        ((CheckBoxCell) r4).setChecked(r0, true);
    }

    public static /* synthetic */ void $r8$lambda$Wui4zyolXQCx9ysbyHxqZCWt420(AccountInstance r0, TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.Updates) == false) goto L6;
        r0.getMessagesController().processUpdates((TLRPC.Updates) r1, false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$WuqArnhWtKBQT8rKRmxrsbcEqXQ(boolean[] r12, long r13, long r15, int r17, NumberPicker r18, NumberPicker r19, NumberPicker r20, Calendar r21, ScheduleDatePickerDelegate r22, BottomSheet.Builder r23, View r24) {
        r12[0] = false;
        boolean r122 = checkScheduleDate(null, null, r13, r15, r17, r18, r19, r20);
        r21.setTimeInMillis(LocalDate.now().plusDays(r18.getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        r21.set(11, r19.getValue());
        r21.set(12, r20.getValue());
        if (r122 == false) goto L5;
        r21.set(13, 0);
    L5:
        r22.didSelectDate(true, (int) (r21.getTimeInMillis() / 1000), 0);
        r23.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$Wz_ES5UyWWV5sQsD--iw9Ct85x4, reason: not valid java name */
    public static /* synthetic */ void m8744$r8$lambda$Wz_ES5UyWWV5sQsDiw9Ct85x4(TLObject r8, int r9, ButtonWithCounterView r10, BottomSheet r11, long r12, TLRPC.TL_error r14, Theme.ResourcesProvider r15) {
        if ((r8 instanceof TLRPC.Updates) == false) goto L25;
        TLRPC.Updates r82 = (TLRPC.Updates) r8;
        MessagesController.getInstance(r9).putUsers(r82.users, false);
        MessagesController.getInstance(r9).putChats(r82.chats, false);
        ArrayList r142 = MessagesController.findUpdates(r82, TL_update.TL_updateGroupCall.class);
        int r152 = r142.size();
        TLRPC.GroupCall r0 = null;
        int r2 = 0;
    L5:
        if (r2 >= r152) goto L7;
        Object r02 = r142.get(r2);
        r2 = r2 + 1;
        r0 = ((TL_update.TL_updateGroupCall) r02).call;
        goto L5
    L7:
        Utilities.stageQueue.postRunnable(new AlertsCreator$$ExternalSyntheticLambda177(r9, r82));
        if (r0 != null) goto L10;
    L22:
        r10.setLoading(false);
        return;
    L10:
        if (LaunchActivity.instance == null) goto L22;
        r11.dismiss();
        SendMessagesHelper.getInstance(r9).sendMessage(SendMessagesHelper.SendMessageParams.of(r0.invite_link, r12));
        BaseFragment r83 = LaunchActivity.getSafeLastFragment();
        if (r83 != null) goto L15;
        return;
    L15:
        if ((r83 instanceof ChatActivity) == false) goto L20;
        ChatActivity r92 = (ChatActivity) r83;
        if (r92.getDialogId() != r12) goto L20;
        if (r92.getChatMode() != 0) goto L20;
        return;
    L20:
        r83.presentFragment(ChatActivity.of(r12));
        return;
    L25:
        if ((r8 instanceof TL_phone.groupCall) == false) goto L32;
        TL_phone.groupCall r84 = (TL_phone.groupCall) r8;
        MessagesController.getInstance(r9).putUsers(r84.users, false);
        MessagesController.getInstance(r9).putChats(r84.chats, false);
        if (LaunchActivity.instance != null) goto L30;
        r10.setLoading(false);
        return;
    L30:
        TLRPC.TL_inputGroupCall r4 = new TLRPC.TL_inputGroupCall();
        TLRPC.GroupCall r102 = r84.call;
        r4.id = r102.id;
        r4.access_hash = r102.access_hash;
        r11.dismiss();
        VoIPHelper.joinConference(LaunchActivity.instance, r9, r4, false, r84.call, null);
        SendMessagesHelper.getInstance(r9).sendMessage(SendMessagesHelper.SendMessageParams.of(r84.call.invite_link, r12));
        return;
    L32:
        if (r14 == null) goto L38;
        BulletinFactory.of(r11.topBulletinContainer, r15).showForError(r14);
        return;
    }

    public static /* synthetic */ void $r8$lambda$XFfVAIcsWP_jo770lPJNoU0UCXs(boolean r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, DatePickerDelegate r4, AlertDialog r5, int r6) {
        if (r0 == false) goto L4;
        checkPickerDate(r1, r2, r3);
    L4:
        r4.didSelectDate(r3.getValue(), r2.getValue(), r1.getValue());
    }

    public static /* synthetic */ void $r8$lambda$Z6A3x4C4ZBk_BmibMjgXd3XyQH4(NumberPicker r0, NumberPicker r1, NumberPicker r2, int r3, BottomSheet.Builder r4, Utilities.Callback r5, View r6) {
        TL_account.TL_birthday r62 = new TL_account.TL_birthday();
        r62.day = r0.getValue();
        r62.month = r1.getValue() + 1;
        if (r2.getValue() == r3) goto L5;
        r62.flags |= 1;
        r62.year = r2.getValue();
    L5:
        r4.getDismissRunnable().run();
        r5.run(r62);
    }

    /* renamed from: $r8$lambda$Z7nbZzMgWK-1IHUiadpi2X2myEY, reason: not valid java name */
    public static /* synthetic */ void m8745$r8$lambda$Z7nbZzMgWK1IHUiadpi2X2myEY(boolean[] r4, RLottieDrawable r5, HintView2[] r6, long r7, Context r9, long r10, ActionBarMenuItem r12, BottomSheet r13, View r14) {
        boolean r0 = r4[0];
        r4[0] = !r0;
        if (r0 == true) goto L9;
        if (r5.getCurrentFrame() < 40) goto L7;
        r5.setCurrentFrame(0);
    L7:
        r5.setCustomEndFrame(40);
        r5.start();
    L12:
        HintView2 r52 = r6[0];
        if (r52 == null) goto L15;
        r52.hide();
        r6[0] = null;
    L15:
        TLRPC.User r53 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r7));
        TLRPC.Chat r02 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r7));
        HintView2 r1 = new HintView2(r9, 3);
        r6[0] = r1;
        r1.setRoundingWithCornerEffect(false);
        r1.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r1.setRounding(20.0f);
        r1.setShadow(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(4.0f), Theme.multAlpha(-16777216, 0.25f));
        if (ChatObject.isChannelAndNotMegaGroup(r02) == true) goto L18;
        if (r02 != null) goto L38;
        if (r53 == null) goto L38;
        if (r7 != r10) goto L33;
        if (r4[0] == false) goto L30;
        int r42 = R.string.ScheduleNotifyOnSelf;
    L31:
        String r43 = LocaleController.getString(r42);
    L42:
        r1.setText(r43);
        r1.setDuration(5000);
        if (r12 == null) goto L45;
        int r44 = 42;
    L46:
        r1.setJoint(1.0f, -(r44 + 20));
        r1.setOnHiddenListener(new AlertsCreator$$ExternalSyntheticLambda76(r1));
        r13.getContainerView().setClipToPadding(false);
        r13.getContainerView().setClipChildren(false);
        r13.getContainerView().addView(r1, LayoutHelper.createFrame(-1, 200.0f, 48, 0.0f, -194.0f, 0.0f, 0.0f));
        r1.show();
        return;
    L45:
        r44 = -8;
        goto L46
    L30:
        r42 = R.string.ScheduleNotifyOffSelf;
        goto L31
    L33:
        if (r4[0] == false) goto L35;
        int r45 = R.string.ScheduleNotifyOnChat;
    L36:
        r43 = LocaleController.formatString(r45, new Object[]{UserObject.getForcedFirstName(r53)});
        goto L42
    L35:
        r45 = R.string.ScheduleNotifyOffChat;
    L38:
        if (r4[0] == false) goto L40;
        int r46 = R.string.ScheduleNotifyOnGroup;
    L41:
        r43 = LocaleController.getString(r46);
        goto L42
    L40:
        r46 = R.string.ScheduleNotifyOffGroup;
        goto L41
    L18:
        if (r4[0] == false) goto L20;
        int r47 = R.string.ScheduleNotifyOnChannel;
    L21:
        r43 = LocaleController.getString(r47);
        goto L42
    L20:
        r47 = R.string.ScheduleNotifyOffChannel;
        goto L21
    L9:
        if (r5.getCurrentFrame() >= 40) goto L11;
        r5.setCurrentFrame(40);
    L11:
        r5.setCustomEndFrame(80);
        r5.start();
        goto L12
    }

    /* renamed from: $r8$lambda$ZCAVDXBJQn-WMBgNpGhHzqJIvZM, reason: not valid java name */
    public static /* synthetic */ String m8746$r8$lambda$ZCAVDXBJQnWMBgNpGhHzqJIvZM(int r2) {
        return _UrlKt.FRAGMENT_ENCODE_SET + r2;
    }

    public static /* synthetic */ void $r8$lambda$ZItObE1VZ1DDG7W_v3Fox3KBpr0(EditTextBoldCursor r3, Utilities.Callback r4, AlertDialog[] r5, View r6) {
        String r0 = r3.getText().toString().trim();
        Uri r1 = Uri.parse(r0);
        if (r1 != null) goto L5;
    L6:
        r1 = Uri.parse("https://" + r0);
    L7:
        if (r1 != null) goto L9;
    L20:
        AndroidUtilities.shakeView(r3);
        return;
    L9:
        if (r1.getHost() == null) goto L20;
        String r32 = r1.getHost().toLowerCase();
        if (r32.startsWith("www.") == false) goto L14;
        r32 = r32.substring(4);
    L14:
        r4.run(r32);
        AlertDialog r33 = r5[0];
        if (r33 == null) goto L17;
        r33.dismiss();
    L17:
        if (r6 == null) goto L22;
        r6.requestFocus();
        return;
    L22:
        return;
    L5:
        if (r1.getHost() != null) goto L7;
        goto L6
    }

    public static /* synthetic */ String $r8$lambda$ZVI_mk2PHVpmxvL4DDjrEj_yblg(int r5) {
        if (r5 == 0) goto L4;
        Calendar r0 = Calendar.getInstance();
        int r2 = r0.get(1);
        r0.add(6, r5);
        long r3 = r0.getTimeInMillis();
        if (r0.get(1) != r2) goto L10;
        return LocaleController.getInstance().getFormatterWeek().format(r3) + ", " + LocaleController.getInstance().getFormatterScheduleDay().format(r3);
    L10:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r3);
    L4:
        return LocaleController.getString(R.string.MessageScheduleToday);
    }

    public static /* synthetic */ void $r8$lambda$ZgbXSj66_3gXC06di0KFHWI1904(AlertDialog r0, Context r1) {
        if (r0 == null) goto L4;
        r0.dismiss();
    L4:
        Browser.openUrl(r1, LocaleController.getString(R.string.BotWebViewStartPermissionLink));
    }

    public static /* synthetic */ void $r8$lambda$ZveGXZKWA5V_8jZeWdCB2LgrcSA(LaunchActivity r0, AlertDialog r1, int r2) {
        r0.presentFragment(new CacheControlActivity());
    }

    public static /* synthetic */ void $r8$lambda$ZwCXGgRBLB0dk5jqabrgdShl5J8(NumberPicker r5, int r6, NumberPicker r7, NumberPicker r8, int r9, int r10, int r11) {
        if (r5.getValue() != r6) goto L11;
        r7.setMinValue(1);
        r7.setMaxValue(YearMonth.of(2024, r8.getValue() + 1).lengthOfMonth());     // Catch: Exception -> L7
    L9:
        r8.setMinValue(0);
        r8.setMaxValue(11);
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
        r7.setMaxValue(31);
        goto L9
    L11:
        if (r5.getValue() != r9) goto L21;
        r8.setMinValue(0);
        r8.setMaxValue(r10);
        if (r8.getValue() != r10) goto L16;
        r7.setMinValue(1);
        r7.setMaxValue(r11);
        return;
    L16:
        r7.setMinValue(1);
        r7.setMaxValue(YearMonth.of(r5.getValue(), r8.getValue() + 1).lengthOfMonth());     // Catch: Exception -> L19
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
        r7.setMaxValue(31);
        return;
    L21:
        r7.setMinValue(1);
        r7.setMaxValue(YearMonth.of(r5.getValue(), r8.getValue() + 1).lengthOfMonth());     // Catch: Exception -> L24
    L26:
        r8.setMinValue(0);
        r8.setMaxValue(11);
        return;
    L24:
        e = move-exception;
        FileLog.e(e);
        r7.setMaxValue(31);
        goto L26
    }

    public static /* synthetic */ void $r8$lambda$_AniuwtpReVSx2W3DnlJni8WkEc(boolean r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, int r5) {
        if (r0 == false) goto L6;
        if (r5 != 0) goto L7;
        checkPickerDate(r1, r2, r3);
        return;
    L7:
        return;
    }

    public static /* synthetic */ void $r8$lambda$_B9Ye9x6KLpyJDQ6J4AJrw1n6fI(AlertDialog[] r1, Runnable r2, AccountSelectDelegate r3, View r4) {
        AlertDialog r12 = r1[0];
        if (r12 == null) goto L5;
        r12.setOnDismissListener(null);
    L5:
        r2.run();
        r3.didSelectAccount(((AccountSelectCell) r4).getAccountNumber());
    }

    public static /* synthetic */ void $r8$lambda$_VgfRrSENpdQKXDueZ7E_Nhlc04(int[] r0, NumberPicker r1, ScheduleDatePickerDelegate r2, BottomSheet.Builder r3, View r4) {
        r2.didSelectDate(true, r0[r1.getValue()] * 60, 0);
        r3.getDismissRunnable().run();
    }

    public static /* synthetic */ void $r8$lambda$_lyLW7empweDdhBitMKkn9KjJEc(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$_nzp2_KeSPjzmK3iynQfljl_Xag(int[] r0, NumberPicker r1, ScheduleDatePickerDelegate r2, BottomSheet.Builder r3, View r4) {
        r2.didSelectDate(true, r0[r1.getValue()], 0);
        r3.getDismissRunnable().run();
    }

    /* renamed from: $r8$lambda$b45ErqA_B0_3VGr6-cJn6ibchRw, reason: not valid java name */
    public static /* synthetic */ String m8747$r8$lambda$b45ErqA_B0_3VGr6cJn6ibchRw(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$bi_EHXbKrkbSi48nn1yOL9N_fF8(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$byCCrsBM7UcMsJG8ZA2yYWdYxkE(ScheduleDatePickerDelegate r1, boolean[] r2, BottomSheet.Builder r3, int r4) {
        if (r4 != 1) goto L6;
        r1.didSelectDate(r2[0], 2147483646, 0);
        r3.getDismissRunnable().run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$cIEbSFwNMk9x0u8zyC3c0fb61LQ(int r2, long r3, long r5, Runnable r7, Boolean r8) {
        if (r8.booleanValue() == false) goto L5;
        MessagesController.getInstance(r2).getMainSettings().edit().putLong("ask_paid_message_" + r3 + "_price", r5).apply();
        StarsController.getInstance(r2).justAgreedToNotAskDialogs.put(Long.valueOf(r3), Long.valueOf(System.currentTimeMillis()));
    L5:
        AndroidUtilities.runOnUIThread(r7);
    }

    /* renamed from: $r8$lambda$cm-votEQ3apSf-fGEf_CESxB3SA, reason: not valid java name */
    public static /* synthetic */ void m8748$r8$lambda$cmvotEQ3apSffGEf_CESxB3SA(String r1, Runnable r2, AlertDialog r3, int r4) {
        SharedPreferences.Editor r32 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount).edit();
        r32.remove("color_" + r1);
        r32.apply();
        if (r2 == null) goto L6;
        r2.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$dMO7qOaCKniG93cClqpFwXznWxo(int r8, ButtonWithCounterView r9, BottomSheet r10, long r11, Theme.ResourcesProvider r13, View r14) {
        TL_phone.createConferenceCall r142 = new TL_phone.createConferenceCall();
        r142.random_id = Utilities.random.nextInt();
        ConnectionsManager.getInstance(r8).sendRequest(r142, new AlertsCreator$$ExternalSyntheticLambda46(r8, r9, r10, r11, r13));
    }

    /* renamed from: $r8$lambda$dY-6Ry0X8Qe57dyVkl0YiAoiy1M, reason: not valid java name */
    public static /* synthetic */ boolean m8749$r8$lambda$dY6Ry0X8Qe57dyVkl0YiAoiy1M(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$dZYrZBKHFMAi_w_wUcwOfwQ_nJo(int r9, TLRPC.Chat r10, ArrayList r11, ArrayList r12, ArrayList r13) {
        if (LaunchActivity.isActive == false) goto L13;
        BaseFragment r2 = LaunchActivity.getLastFragment();
        if (r2 != null) goto L8;
        return;
    L8:
        if (r2.getParentActivity() == null) goto L12;
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(r2, r2.getParentActivity(), 11, r9, null);
        r1.setRestrictedUsers(r10, r11, r12, r13, null);
        r1.show();
        return;
    L12:
        return;
    }

    /* renamed from: $r8$lambda$d_lz7LU7PzaRqYinK-dwmUix4x0, reason: not valid java name */
    public static /* synthetic */ void m8750$r8$lambda$d_lz7LU7PzaRqYinKdwmUix4x0(int[] r5, LinearLayout r6, View r7) {
        r5[0] = ((Integer) r7.getTag()).intValue();
        int r52 = r6.getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L12;
        View r2 = r6.getChildAt(r0);
        if ((r2 instanceof RadioColorCell) == false) goto L11;
        RadioColorCell r3 = (RadioColorCell) r2;
        if (r2 != r7) goto L9;
        boolean r22 = true;
    L10:
        r3.setChecked(r22, true);
        goto L11
    L9:
        r22 = false;
    L11:
        r0 = r0 + 1;
        goto L3
    }

    /* renamed from: $r8$lambda$dkvST2miTxtbhBe80-tWxctSTRM, reason: not valid java name */
    public static /* synthetic */ void m8751$r8$lambda$dkvST2miTxtbhBe80tWxctSTRM(EditTextBoldCursor r1, AlertDialog r2, BaseFragment r3) {
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda211(r1, r2, r3));
    }

    public static /* synthetic */ String $r8$lambda$dw6kvGO8A76LcoIhvS4csIpZYYc(int r3, int r4) {
        if (r4 == 0) goto L4;
        LocalDate r42 = LocalDate.now().plusDays(r4);
        int r0 = r42.getYear();
        long r1 = r42.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        if (r0 != r3) goto L10;
        return LocaleController.getInstance().getFormatterWeek().format(r1) + ", " + LocaleController.getInstance().getFormatterScheduleDay().format(r1);
    L10:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r1);
    L4:
        return LocaleController.getString(R.string.MessageScheduleToday);
    }

    /* renamed from: $r8$lambda$eNawnWmD9EpK-1I1Hq16poCoI24, reason: not valid java name */
    public static /* synthetic */ String m8752$r8$lambda$eNawnWmD9EpK1I1Hq16poCoI24(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$e_UUPM06Fsq_qCQnM0W1euYrafk(AtomicBoolean r0, Consumer r1, DialogInterface r2) {
        if (r0.get() == true) goto L6;
        r1.accept(Boolean.FALSE);
        return;
    }

    public static /* synthetic */ void $r8$lambda$ehNRQ3J8pcY4QAlewwMylCwG58o(AlertDialog.Builder r0, DialogInterface.OnClickListener r1, View r2) {
        int r22 = ((Integer) r2.getTag()).intValue();
        r0.getDismissRunnable().run();
        r1.onClick(null, r22);
    }

    /* renamed from: $r8$lambda$ensk1rc511O5OmyEvELZl-8VpZ4, reason: not valid java name */
    public static /* synthetic */ void m8753$r8$lambda$ensk1rc511O5OmyEvELZl8VpZ4(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$fKbIdNPGh8xbX2LJhDZjzohPd4Q(MessagesStorage.BooleanCallback r1) {
        if (r1 == null) goto L5;
        r1.run(false);
        return;
    }

    /* renamed from: $r8$lambda$fzBMF-dOXKOQnDfeb5PrhwriVGo, reason: not valid java name */
    public static /* synthetic */ boolean m8754$r8$lambda$fzBMFdOXKOQnDfeb5PrhwriVGo(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$gG9xjEcjlYHYWcSNM7cus94BWsQ(EditTextBoldCursor r1, Utilities.Callback r2, AlertDialog r3, int r4) {
        String r42 = r1.getText().toString().trim();
        if (isValidUrl(r42) == true) goto L6;
        AndroidUtilities.shakeView(r1);
        return;
    L6:
        r2.run(r42);
        r3.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$gazL3HP9cROT0mC0PUQopH9pmjo(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$gbO0sAJ0d8Pu_n0zPaLGX7UYyZE(MessagesStorage.IntCallback r0, AlertDialog r1, int r2) {
        r0.run(1);
    }

    /* renamed from: $r8$lambda$gcJ6NSv7L5-8jI0sH0iFXd2WyTU, reason: not valid java name */
    public static /* synthetic */ void m8755$r8$lambda$gcJ6NSv7L58jI0sH0iFXd2WyTU(long r29, boolean r31, int r32, MessageObject r33, MessageObject.GroupedMessages r34, TLRPC.EncryptedChat r35, long r36, int r38, boolean[] r39, int r40, SparseArray[] r41, Runnable r42, AlertDialog r43, int r44) {
        if (r31 == false) goto L5;
        long r4 = UserConfig.getInstance(r32).getClientUserId();
    L6:
        ArrayList<Long> r6 = null;
        int r10 = 0;
        if (r33 == null) goto L53;
        ArrayList r11 = new ArrayList();
        ArrayList<Integer> r13 = new ArrayList();
        if (r34 == null) goto L29;
        int r12 = 0;
    L12:
        if (r12 >= r34.messages.size()) goto L27;
        MessageObject r14 = r34.messages.get(r12);
        if (r14.isEphemeral() == false) goto L17;
        r11.add(r14);
    L26:
        r12 = r12 + 1;
        goto L12
    L17:
        r13.add(Integer.valueOf(r14.getId()));
        if (r35 == null) goto L26;
        if (r14.messageOwner.random_id == 0) goto L26;
        if (r14.type == 10) goto L26;
        if (r6 != null) goto L25;
        r6 = new ArrayList();
    L25:
        r6.add(Long.valueOf(r14.messageOwner.random_id));
        goto L26
    L27:
        long r292 = 0;
    L28:
        ArrayList<Long> r142 = r6;
        if (r36 == r292) goto L46;
        TLRPC.Peer r0 = r33.messageOwner.peer_id;
        if (r0 == null) goto L46;
        if (r0.chat_id != (-r36)) goto L46;
        long r16 = r36;
    L48:
        if (r13.isEmpty() == true) goto L50;
        MessagesController.getInstance(r32).deleteMessages(r13, r142, r35, r16, r38, r39[0], r40);
    L50:
        long r2 = r16;
        int r02 = r11.size();
    L51:
        if (r10 >= r02) goto L78;
        Object r1 = r11.get(r10);
        r10 = r10 + 1;
        MessagesController.getInstance(r32).deleteEphemeralMessage(r2, r38, (MessageObject) r1);
    L78:
        if (r42 == null) goto L95;
        r42.run();
        return;
    L95:
        return;
    L46:
        r16 = r4;
        goto L48
    L29:
        r292 = 0;
        if (r33.isEphemeral() == false) goto L32;
        r11.add(r33);
        goto L28
    L32:
        r13.add(Integer.valueOf(r33.getId()));
        if (r35 == null) goto L28;
        if (r33.messageOwner.random_id == 0) goto L28;
        if (r33.type == 10) goto L28;
        r6 = new ArrayList();
        r6.add(Long.valueOf(r33.messageOwner.random_id));
        goto L28
    L53:
        int r8 = r38;
        int r15 = 1;
    L54:
        if (r15 < 0) goto L78;
        ArrayList<Integer> r9 = new ArrayList();
        int r112 = 0;
    L57:
        if (r112 >= r41[r15].size()) goto L59;
        r9.add(Integer.valueOf(r41[r15].keyAt(r112)));
        r112 = r112 + 1;
        goto L57
    L59:
        if (r35 == null) goto L70;
        ArrayList<Long> r113 = new ArrayList();
        int r122 = 0;
    L62:
        if (r122 >= r41[r15].size()) goto L69;
        MessageObject r132 = (MessageObject) r41[r15].valueAt(r122);
        long r143 = r132.messageOwner.random_id;
        if (r143 == 0) goto L68;
        if (r132.type == 10) goto L68;
        r113.add(Long.valueOf(r143));
    L68:
        r122 = r122 + 1;
        goto L62
    L69:
        ArrayList<Long> r22 = r113;
    L71:
        MessagesController r20 = MessagesController.getInstance(r32);
        if (r15 == 1) goto L74;
    L76:
        long r24 = r4;
    L77:
        r20.deleteMessages(r9, r22, r35, r24, r8, r39[0], r40);
        r41[r15].clear();
        r15 = r15 - 1;
        r8 = r38;
        goto L54
    L74:
        if (r36 == 0) goto L76;
        r24 = r36;
        goto L77
    L70:
        r22 = null;
        goto L71
    L5:
        r4 = r29;
        goto L6
    }

    /* renamed from: $r8$lambda$hMC-yO_T3Eby2CzK-yFOpV8zJgo, reason: not valid java name */
    public static /* synthetic */ void m8756$r8$lambda$hMCyO_T3Eby2CzKyFOpV8zJgo(TLRPC.EncryptedChat r0, NumberPicker r1, AlertDialog r2, int r3) {
        int r22 = r0.ttl;
        int r12 = r1.getValue();
        if (r12 < 0) goto L6;
        if (r12 >= 16) goto L6;
        r0.ttl = r12;
    L21:
        if (r22 == r0.ttl) goto L24;
        SecretChatHelper.getInstance(UserConfig.selectedAccount).sendTTLMessage(r0, null);
        MessagesStorage.getInstance(UserConfig.selectedAccount).updateEncryptedChatTTL(r0);
        return;
    L24:
        return;
    L6:
        if (r12 != 16) goto L9;
        r0.ttl = 30;
        goto L21
    L9:
        if (r12 != 17) goto L12;
        r0.ttl = 60;
        goto L21
    L12:
        if (r12 != 18) goto L15;
        r0.ttl = 3600;
        goto L21
    L15:
        if (r12 != 19) goto L18;
        r0.ttl = 86400;
        goto L21
    L18:
        if (r12 != 20) goto L21;
        r0.ttl = 604800;
        goto L21
    }

    /* renamed from: $r8$lambda$hQgCJYptc1EA__CvZA-j7Leapz0, reason: not valid java name */
    public static /* synthetic */ String m8757$r8$lambda$hQgCJYptc1EA__CvZAj7Leapz0(int r0) {
        return LocaleController.getString(R.string.NotificationsFrequencyDivider);
    }

    /* renamed from: $r8$lambda$hYIElN-LFjZQWhDdA1vgkdAElso, reason: not valid java name */
    public static /* synthetic */ void m8758$r8$lambda$hYIElNLFjZQWhDdA1vgkdAElso(boolean[] r3, Runnable r4, View r5) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r5).setChecked(r1, true);
        r4.run();
    }

    public static /* synthetic */ String $r8$lambda$hexhmP7vLomcHjgd_D4PjVzjm2U(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$iT0Q1OqWEhHXGl71eDAXEy3kh3U(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$ifAR8QapK5_4z3Au3xJHUzB0djk(AlertDialog[] r0, MessagesStorage.LongCallback r1, long r2, View r4) {
        r0[0].dismiss();
        r1.run(-r2);
    }

    /* renamed from: $r8$lambda$ixa66GvTix6a0S3VtbhlBL4-2HM, reason: not valid java name */
    public static /* synthetic */ boolean m8759$r8$lambda$ixa66GvTix6a0S3VtbhlBL42HM(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ String $r8$lambda$jB02K0V4N7u4FiczBAlule9KOfg(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$jDPkuSyHoahwMBH5K8DDPq86iDY(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$jemlvi6mKrQLB9Wj6RbQDKURoB4(NumberPicker r0, int r1, Runnable r2, View r3) {
        r0.setValue(r1);
        r2.run();
    }

    public static /* synthetic */ void $r8$lambda$jgxPbhvn0h_7g9kEGE5wLRvYVb0(String r2, BaseFragment r3, AlertDialog r4, int r5) {
        Intent r42 = new Intent("android.intent.action.VIEW", Uri.fromParts("sms", r2, null));     // Catch: Exception -> L4
        r42.putExtra("sms_body", ContactsController.getInstance(r3.getCurrentAccount()).getInviteText(1));     // Catch: Exception -> L4
        r3.getParentActivity().startActivityForResult(r42, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$jn-kOl_OsrQkRLE41YhnkYJ23zk, reason: not valid java name */
    public static /* synthetic */ void m8760$r8$lambda$jnkOl_OsrQkRLE41YhnkYJ23zk(int[] r4, int r5, Runnable r6, DialogInterface r7) {
        int r72 = r4.length;
        int r0 = 0;
    L3:
        if (r0 >= r72) goto L8;
        int r1 = r4[r0];
        if (r1 == 0) goto L7;
        ConnectionsManager.getInstance(r5).cancelRequest(r1, true);
    L7:
        r0 = r0 + 1;
        goto L3
    L8:
        if (r6 == null) goto L14;
        r6.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$jpWDy7rQ2rYg8DV8dAjPzQ_3zyo(TextView r0, String r1, long r2, long r4, NumberPicker r6, NumberPicker r7, NumberPicker r8, NumberPicker r9, int r10, int r11) {
        if (r1 == null) goto L6;
        int r12 = 3;
    L9:
        checkScheduleDate(r0, null, r12, r6, r7, r8);
        return;
    L6:
        if (r2 != r4) goto L8;
        r12 = 1;
        goto L9
    L8:
        r12 = 0;
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$kdMtrCw_l_B7tgDk0QNhT2E_68U(EditText r0, EditText r1, DialogInterface r2) {
        AndroidUtilities.hideKeyboard(r0);
        AndroidUtilities.hideKeyboard(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$keCcwZQUn5bSIOzKBgWQbehl9mU(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$kgKT1JXfzg6RppDHOqrapNO3uAk(Runnable r0, boolean[] r1, DialogInterface r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1[0] == false) goto L8;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$l-wLx9K0j3wxUmCYX24BxAyixZE, reason: not valid java name */
    public static /* synthetic */ void m8761$r8$lambda$lwLx9K0j3wxUmCYX24BxAyixZE(Utilities.Callback r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.run(Boolean.valueOf(r1[0]));
    }

    /* renamed from: $r8$lambda$lVM18fDfbPN8jTUk-YUZ879ND04, reason: not valid java name */
    public static /* synthetic */ void m8762$r8$lambda$lVM18fDfbPN8jTUkYUZ879ND04(long r20, int r22, boolean r23, int r24, MessagesStorage.IntCallback r25, int r26, BaseFragment r27, ArrayList r28, ArrayList r29, MessagesStorage.IntCallback r30, AlertDialog.Builder r31, View r32) {
        int r10 = ((Integer) r32.getTag()).intValue();
        if (r10 == 0) goto L5;
        if (r10 == 3) goto L21;
        int r5 = ConnectionsManager.getInstance(r22).getCurrentTime();
        if (r10 != 1) goto L28;
        r5 = r5 + 3600;
    L27:
        int r11 = 4;
    L33:
        NotificationsController.getInstance(r22).muteUntil(r20, r24, r5);
        if (r20 == 0) goto L40;
        if (r25 == null) goto L40;
        if (r10 != r11) goto L39;
        if (r23 == true) goto L39;
        r25.run(0);
    L39:
        r25.run(1);
    L40:
        if (r20 != 0) goto L42;
        NotificationsController.getInstance(r22).setGlobalNotificationsEnabled(r26, Integer.MAX_VALUE);
    L42:
        if (r30 == null) goto L44;
        r30.run(r10);
    L44:
        r31.getDismissRunnable().run();
        if (r10 != 0) goto L47;
        int r112 = 4;
    L55:
        if (r112 >= 0) goto L57;
        return;
    L57:
        if (BulletinFactory.canShowBulletin(r27) == false) goto L61;
        BulletinFactory.createMuteBulletin(r27, r112).show();
        return;
    L61:
        return;
    L47:
        if (r10 != 1) goto L49;
        r112 = 0;
        goto L55
    L49:
        if (r10 != 2) goto L52;
        r112 = 2;
        goto L55
    L52:
        if (r10 != 4) goto L54;
        r112 = 3;
        goto L55
    L54:
        r112 = -1;
        goto L55
    L28:
        if (r10 != 2) goto L30;
        r5 = r5 + 172800;
        goto L27
    L30:
        r11 = 4;
        if (r10 != 4) goto L33;
        r5 = Integer.MAX_VALUE;
        goto L33
    L21:
        if (r20 == 0) goto L23;
        Bundle r0 = new Bundle();
        r0.putLong("dialog_id", r20);
        r27.presentFragment(new ProfileNotificationsActivity(r0));
        goto L42
    L23:
        r27.presentFragment(new NotificationsCustomSettingsActivity(r26, r28, r29));
        goto L42
    L5:
        if (r20 == 0) goto L18;
        SharedPreferences.Editor r52 = MessagesController.getNotificationsSettings(r22).edit();
        if (r23 == false) goto L9;
        r52.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY + r20);
    L10:
        MessagesStorage.getInstance(r22).setDialogFlags(r20, 0);
        r52.apply();
        TLRPC.Dialog r3 = MessagesController.getInstance(r22).dialogs_dict.get(r20);
        if (r3 == null) goto L13;
        r3.notify_settings = new TLRPC.TL_peerNotifySettings();
    L13:
        NotificationsController.getInstance(r22).updateServerNotificationsSettings(r20, r24);
        if (r25 == null) goto L42;
        if (r23 == false) goto L17;
        r25.run(0);
        goto L42
    L17:
        r25.run(1);
        goto L42
    L9:
        r52.putInt(NotificationsSettingsFacade.PROPERTY_NOTIFY + r20, 0);
        goto L10
    L18:
        NotificationsController.getInstance(r22).setGlobalNotificationsEnabled(r26, 0);
        goto L42
    }

    public static /* synthetic */ void $r8$lambda$lyKkouJlycwYCLAVget7KC2ueyk(long r0, NumberPicker r2, NumberPicker r3, NumberPicker r4, NumberPicker r5, int r6, int r7) {
        checkCalendarDate(r0, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$m3nAnNtqC8ylzyCI632g3yivEM4(AlertDialog r0, Context r1) {
        if (r0 == null) goto L4;
        r0.dismiss();
    L4:
        Browser.openUrl(r1, LocaleController.getString(R.string.BotWebViewStartPermissionLink));
    }

    public static /* synthetic */ void $r8$lambda$mC3OtQ0uEbOu4bH2FqVHadGoRVg(Runnable r0, AlertDialog r1, int r2) {
        if (r0 == null) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$mGhy7kurAYvV_xmmFNKDR0fs-YU, reason: not valid java name */
    public static /* synthetic */ boolean m8763$r8$lambda$mGhy7kurAYvV_xmmFNKDR0fsYU(MessageObject r0) {
        return !r0.isEphemeral();
    }

    /* renamed from: $r8$lambda$mMhTasmbezyO-slsUc2_AFIrhiM, reason: not valid java name */
    public static /* synthetic */ void m8764$r8$lambda$mMhTasmbezyOslsUc2_AFIrhiM(ButtonWithCounterView r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, int[] r5, int[] r6, NumberPicker r7, int r8, int r9) {
        checkFormattedDateInput(r0, r1, r2, r3, r4, r5[r6[0]]);
    }

    /* renamed from: $r8$lambda$mWHoRVWwTvvXp4QC_4z4RV-Eu68, reason: not valid java name */
    public static /* synthetic */ void m8765$r8$lambda$mWHoRVWwTvvXp4QC_4z4RVEu68(int r1, TLRPC.Updates r2) {
        MessagesController.getInstance(r1).processUpdates(r2, false);
    }

    public static /* synthetic */ void $r8$lambda$m_KswKeRAssHkiLhfDbnwtSAr5M(Runnable r0, AlertDialog r1, int r2) {
        r1.dismiss();
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    public static /* synthetic */ String $r8$lambda$maS3skAhmXjorrDYfo0yCRXEukQ(int r2) {
        return LocaleController.formatPluralString("Times", r2 + 1, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$mijQ80opxDuz6vy_2xRFlG5lLzc(Utilities.Callback r0, NumberPicker r1, NumberPicker r2, DialogInterface r3) {
        r0.run(Integer.valueOf((r1.getValue() * 60) + r2.getValue()));
    }

    public static /* synthetic */ void $r8$lambda$nFrkTaoIXA41rNJtEXwubx1yF_I(int r9, ButtonWithCounterView r10, BottomSheet r11, long r12, Theme.ResourcesProvider r14, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda93(r15, r9, r10, r11, r12, r16, r14));
    }

    /* renamed from: $r8$lambda$nRFyR-Ze8xju6JoJoWq2UeH6nC8, reason: not valid java name */
    public static /* synthetic */ void m8766$r8$lambda$nRFyRZe8xju6JoJoWq2UeH6nC8(AtomicBoolean r2, View r3) {
        r2.set(!r2.get());
        ((CheckBoxCell) r3).setChecked(r2.get(), true);
    }

    /* renamed from: $r8$lambda$nZty1amtSGod2dJ4IIr-C60e7KI, reason: not valid java name */
    public static /* synthetic */ void m8767$r8$lambda$nZty1amtSGod2dJ4IIrC60e7KI(TLRPC.User r2, AccountInstance r3, ChatActivity r4, TLRPC.Chat r5, MessageObject r6, CheckBoxCell[] r7, Theme.ResourcesProvider r8, AlertDialog r9, int r10) {
        if (r2 == null) goto L4;
        r3.getMessagesStorage().deleteUserChatHistory(r4.getDialogId(), r2.id);
    L5:
        TLRPC.TL_contacts_blockFromReplies r22 = new TLRPC.TL_contacts_blockFromReplies();
        r22.msg_id = r6.getId();
        r22.delete_message = true;
        r22.delete_history = true;
        if (r7[0].isChecked() == false) goto L12;
        r22.report_spam = true;
        if (r4.getParentActivity() == null) goto L12;
        UndoView r42 = r4.getUndoView();
        if (r42 == null) goto L12;
        r42.showWithAction(0, 74, null);
    L12:
        r3.getConnectionsManager().sendRequest(r22, new AlertsCreator$$ExternalSyntheticLambda197(r3));
        return;
    L4:
        r3.getMessagesStorage().deleteUserChatHistory(r4.getDialogId(), -r5.id);
        goto L5
    }

    /* renamed from: $r8$lambda$npp6CQA9Wcn7-QJFc5eop3JC63c, reason: not valid java name */
    public static /* synthetic */ void m8768$r8$lambda$npp6CQA9Wcn7QJFc5eop3JC63c(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    public static /* synthetic */ void $r8$lambda$ntLntm1czrZFBXCAwcIN4eIoag4(Utilities.Callback r0, long r1) {
        r0.run(Long.valueOf(r1));
    }

    public static /* synthetic */ void $r8$lambda$nxReu0SzfGIRporicn4mosQOams(int[] r1, int r2, Runnable r3) {
        r1[0] = r2;
        r3.run();
    }

    public static /* synthetic */ void $r8$lambda$nyOEnuWOBNA9cdKho4EIGHjgFWY(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$o9r4saKGumsGmqEQhPa3bC3BcfA(NumberPicker r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, int r4, int r5) {
        updateDayPicker(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oNpumfDwro_v4LzLBwzm9h7BCbE(EditText r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$otAJCGEVgtFbZWfVWG7-cKeRdxU, reason: not valid java name */
    public static /* synthetic */ void m8769$r8$lambda$otAJCGEVgtFbZWfVWG7cKeRdxU(BaseFragment r9, TLRPC.User r10, boolean r11, AlertDialog r12, int r13) {
        TLRPC.UserFull r6 = r9.getMessagesController().getUserFull(r10.id);
        if (r6 != null) goto L5;
    L8:
        boolean r122 = false;
    L7:
        boolean r4 = r122;
        VoIPHelper.startCall(r10, r11, r4, r9.getParentActivity(), r6, r9.getAccountInstance(), true);
        return;
    L5:
        if (r6.video_calls_available == false) goto L8;
        r122 = true;
        goto L7
    }

    /* renamed from: $r8$lambda$oz_1YuJ21i1cZCXS0njAtleO-vw, reason: not valid java name */
    public static /* synthetic */ void m8770$r8$lambda$oz_1YuJ21i1cZCXS0njAtleOvw(NumberPicker r0, int r1, int r2) {
    }

    /* renamed from: $r8$lambda$p3mXhtmyDGsLzASOXiF-JSLjZi0, reason: not valid java name */
    public static /* synthetic */ boolean m8771$r8$lambda$p3mXhtmyDGsLzASOXiFJSLjZi0(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ TLObject $r8$lambda$pNxJkZUzDcm_ipHqmdNkMgS0GpY(int r2, long r3) {
        if (r3 <= 0) goto L7;
        return MessagesController.getInstance(r2).getUser(Long.valueOf(r3));
    L7:
        return MessagesController.getInstance(r2).getChat(Long.valueOf(-r3));
    }

    public static /* synthetic */ String $r8$lambda$pdttBajPPOl_qElf_B9hkTbdmqY(int r5) {
        if (r5 == 0) goto L4;
        Calendar r0 = Calendar.getInstance();
        int r2 = r0.get(1);
        r0.add(6, r5);
        long r3 = r0.getTimeInMillis();
        int r52 = r0.get(1);
        LocaleController.getInstance().getFormatterWeek().format(r3);
        if (r52 != r2) goto L10;
        return LocaleController.getInstance().getFormatterWeek().format(r3) + ", " + LocaleController.getInstance().getFormatterScheduleDay().format(r3);
    L10:
        return LocaleController.getInstance().getFormatterScheduleYear().format(r3);
    L4:
        return LocaleController.getString(R.string.MessageScheduleToday);
    }

    /* renamed from: $r8$lambda$pq8j9Pd5XkxSdd-FgAZmEpPqaSE, reason: not valid java name */
    public static /* synthetic */ void m8772$r8$lambda$pq8j9Pd5XkxSddFgAZmEpPqaSE(Runnable r0, NumberPicker r1, int r2) {
        if (r2 != 0) goto L5;
        r0.run();
        return;
    }

    /* renamed from: $r8$lambda$q-tgXQWVVuTfjmRipXSim1E8P-8, reason: not valid java name */
    public static /* synthetic */ void m8773$r8$lambda$qtgXQWVVuTfjmRipXSim1E8P8(int[] r0, Context r1, Theme.ResourcesProvider r2, Utilities.Callback2 r3, DialogInterface r4, int r5) {
        int r02 = r0[r5];
        if (r02 != 100) goto L6;
        new AnonymousClass57(r1, r02, r2, r3).show();
        return;
    L6:
        r3.run(Integer.valueOf(r02), _UrlKt.FRAGMENT_ENCODE_SET);
    }

    /* renamed from: $r8$lambda$q7i2EEeeHUTpA-m8JqW8ax1SIqk, reason: not valid java name */
    public static /* synthetic */ String m8774$r8$lambda$q7i2EEeeHUTpAm8JqW8ax1SIqk(Calendar r3, int r4, int r5) {
        r3.clear();
        r3.set(1, r4);
        r3.set(2, 0);
        r3.add(2, r5 - 120);
        if (r3.get(1) != r4) goto L7;
        return LocaleController.getInstance().getFormatterMonthOnly().format(r3.getTimeInMillis());
    L7:
        return LocaleController.getInstance().getFormatterMonthYear().format(r3.getTimeInMillis());
    }

    /* renamed from: $r8$lambda$qWpnOqCVwltGGH8I-FYjVyL_SC4, reason: not valid java name */
    public static /* synthetic */ String m8775$r8$lambda$qWpnOqCVwltGGH8IFYjVyL_SC4(int r0) {
        switch(r0) {
            case 0: goto L26;
            case 1: goto L24;
            case 2: goto L22;
            case 3: goto L20;
            case 4: goto L18;
            case 5: goto L16;
            case 6: goto L14;
            case 7: goto L12;
            case 8: goto L10;
            case 9: goto L8;
            case 10: goto L6;
            default: goto L4;
        };
    L4:
        return LocaleController.getString(R.string.December);
    L6:
        return LocaleController.getString(R.string.November);
    L8:
        return LocaleController.getString(R.string.October);
    L10:
        return LocaleController.getString(R.string.September);
    L12:
        return LocaleController.getString(R.string.August);
    L14:
        return LocaleController.getString(R.string.July);
    L16:
        return LocaleController.getString(R.string.June);
    L18:
        return LocaleController.getString(R.string.May);
    L20:
        return LocaleController.getString(R.string.April);
    L22:
        return LocaleController.getString(R.string.March);
    L24:
        return LocaleController.getString(R.string.February);
    L26:
        return LocaleController.getString(R.string.January);
    }

    /* renamed from: $r8$lambda$q_lygv1hZtxgkUkDm-DxbeVm2nU, reason: not valid java name */
    public static /* synthetic */ void m8776$r8$lambda$q_lygv1hZtxgkUkDmDxbeVm2nU(Activity r2, boolean r3, AlertDialog r4, int r5) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r3 == true) goto L6;
    L16:
        r2.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + r2.getPackageName())));     // Catch: Exception -> L13
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        if (PipUtils.checkPermissions(r2) != (-2)) goto L16;
        r2.startActivity(new Intent("android.settings.PICTURE_IN_PICTURE_SETTINGS", Uri.parse("package:" + r2.getPackageName())));     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    public static /* synthetic */ void $r8$lambda$qiNSShFEN0MkdKThkYEAMciB3gA(int r2, MessagesStorage.BooleanCallback r3) {
        NotificationCenter.getInstance(r2).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda239(r3), 250);
    }

    public static /* synthetic */ boolean $r8$lambda$quFLlkM89UpQR3a4R3pbmuoonZ0(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$quNn0HX6yvVOzV5WH_pInasU_3A(long r7, long r9, BaseFragment r11, Theme.ResourcesProvider r12, DialogInterface r13, int r14) {
        if (r14 != 0) goto L5;
        int r6 = 0;
    L12:
        NotificationsController.getInstance(UserConfig.selectedAccount).setDialogNotificationsSettings(r7, r9, r6);
        if (BulletinFactory.canShowBulletin(r11) == false) goto L16;
        BulletinFactory.createMuteBulletin(r11, r6, 0, r12).show();
        return;
    L16:
        return;
    L5:
        int r0 = 1;
        if (r14 != 1) goto L8;
    L7:
        r6 = r0;
        goto L12
    L8:
        r0 = 2;
        if (r14 == 2) goto L7;
        r0 = 3;
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$rsb0zrJDoOzvLrXzc5egKAMDhfQ(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ String $r8$lambda$rvhItVzjoUVEqZiwElYK9WM5V7o(int r2) {
        return LocaleController.formatPluralString("Minutes", r2 + 1, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$rwVXaDntC1d0r6iIe_4FrzjwPAI(boolean r0, TLRPC.Chat r1, AlertDialog.Builder r2, boolean[] r3) {
        if (r0 == true) goto L4;
        return;
    L4:
        if (ChatObject.isChannel(r1) == false) goto L28;
        View r02 = r2.create().getButton(-1);
        if ((r02 instanceof TextView) == false) goto L27;
        TextView r03 = (TextView) r02;
        if (r3[0] == false) goto L17;
        if (ChatObject.isChannelAndNotMegaGroup(r1) == false) goto L13;
        int r12 = R.string.ChannelDelete;
    L14:
        r03.setText(LocaleController.getString(r12));
        return;
    L13:
        r12 = R.string.DeleteMega;
        goto L14
    L17:
        if (r1.monoforum == false) goto L21;
        r03.setText(LocaleController.getString(R.string.LeaveConversationMenu));
        return;
    L21:
        if (r1.megagroup == false) goto L24;
        r03.setText(LocaleController.getString(R.string.LeaveMega));
        return;
    L24:
        r03.setText(LocaleController.getString(R.string.LeaveChannel));
        return;
    L27:
        return;
    }

    /* renamed from: $r8$lambda$ry1PicW-vO88M1rZD53DUmoPJ-M, reason: not valid java name */
    public static /* synthetic */ void m8777$r8$lambda$ry1PicWvO88M1rZD53DUmoPJM(int r2, long r3, TLRPC.Photo r5, Context r6, Theme.ResourcesProvider r7, Integer r8, String r9) {
        TL_account.reportProfilePhoto r92 = new TL_account.reportProfilePhoto();
        r92.peer = MessagesController.getInstance(r2).getInputPeer(r3);
        TLRPC.TL_inputPhoto r32 = new TLRPC.TL_inputPhoto();
        r32.id = r5.id;
        r32.file_reference = r5.file_reference;
        r32.access_hash = r5.access_hash;
        r92.photo_id = r32;
        r92.message = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r8.intValue() != 0) goto L6;
        r92.reason = new TLRPC.TL_inputReportReasonSpam();
    L20:
        ConnectionsManager.getInstance(r2).sendRequest(r92, null);
        BulletinFactory.of(Bulletin.BulletinWindow.make(r6), r7).createReportSent(r7).show();
        return;
    L6:
        if (r8.intValue() != 1) goto L9;
        r92.reason = new TLRPC.TL_inputReportReasonViolence();
        goto L20
    L9:
        if (r8.intValue() != 2) goto L12;
        r92.reason = new TLRPC.TL_inputReportReasonChildAbuse();
        goto L20
    L12:
        if (r8.intValue() != 5) goto L15;
        r92.reason = new TLRPC.TL_inputReportReasonPornography();
        goto L20
    L15:
        if (r8.intValue() != 3) goto L18;
        r92.reason = new TLRPC.TL_inputReportReasonIllegalDrugs();
        goto L20
    L18:
        if (r8.intValue() != 4) goto L20;
        r92.reason = new TLRPC.TL_inputReportReasonPersonalDetails();
        goto L20
    }

    public static /* synthetic */ void $r8$lambda$s8IFEOUSMBH81wsbyedoGFRSDi4(BaseFragment r11, boolean r12, TLRPC.Chat r13, TLRPC.User r14, boolean r15, boolean[] r16, boolean r17, MessagesStorage.BooleanCallback r18, Theme.ResourcesProvider r19, int r20) {
        if (r20 < 50) goto L7;
        createClearOrDeleteDialogAlert(r11, r12, true, r13, r14, false, r15, r16[0], r17, r18, r19);
        return;
    L7:
        if (r18 == null) goto L10;
        r18.run(r16[0]);
        return;
    }

    public static /* synthetic */ void $r8$lambda$sBl9EkEb9VTN0vdgyWvB7ufGqJc(CheckBoxCell[] r1, View r2) {
        r1[((Integer) r2.getTag()).intValue()].setChecked(!r1[r2.intValue()].isChecked(), true);
    }

    public static /* synthetic */ void $r8$lambda$sGT9GgdPY1J3zfkyfXfof3Kmyww(LinearLayout r6, int[] r7, View r8) {
        int r0 = r6.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        RadioColorCell r3 = (RadioColorCell) r6.getChildAt(r2);
        if (r3 != r8) goto L7;
        boolean r5 = true;
    L8:
        r3.setChecked(r5, true);
        r2 = r2 + 1;
        goto L3
    L7:
        r5 = false;
        goto L8
    L9:
        r7[0] = TextColorCell.colorsToSave[((Integer) r8.getTag()).intValue()];
    }

    public static /* synthetic */ void $r8$lambda$shbnAatIWjRTJVdhllbfQqdlhaE(Runnable r0, DialogInterface r1) {
        r0.run();
    }

    public static /* synthetic */ String $r8$lambda$sjVe6feKAX4CPYaqvACAGlaHRh4(int r0) {
        switch(r0) {
            case 0: goto L26;
            case 1: goto L24;
            case 2: goto L22;
            case 3: goto L20;
            case 4: goto L18;
            case 5: goto L16;
            case 6: goto L14;
            case 7: goto L12;
            case 8: goto L10;
            case 9: goto L8;
            case 10: goto L6;
            default: goto L4;
        };
    L4:
        return LocaleController.getString(R.string.December);
    L6:
        return LocaleController.getString(R.string.November);
    L8:
        return LocaleController.getString(R.string.October);
    L10:
        return LocaleController.getString(R.string.September);
    L12:
        return LocaleController.getString(R.string.August);
    L14:
        return LocaleController.getString(R.string.July);
    L16:
        return LocaleController.getString(R.string.June);
    L18:
        return LocaleController.getString(R.string.May);
    L20:
        return LocaleController.getString(R.string.April);
    L22:
        return LocaleController.getString(R.string.March);
    L24:
        return LocaleController.getString(R.string.February);
    L26:
        return LocaleController.getString(R.string.January);
    }

    /* renamed from: $r8$lambda$t9p0Co8XP6ZO-tkNEuoKq7F6EmU, reason: not valid java name */
    public static /* synthetic */ String m8778$r8$lambda$t9p0Co8XP6ZOtkNEuoKq7F6EmU(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    /* renamed from: $r8$lambda$tFDmHyRJSNiynm2aUcs6X0-nOIU, reason: not valid java name */
    public static /* synthetic */ void m8779$r8$lambda$tFDmHyRJSNiynm2aUcs6X0nOIU(boolean r0, NumberPicker r1, NumberPicker r2, NumberPicker r3, NumberPicker r4, int r5) {
        if (r0 == false) goto L6;
        if (r5 != 0) goto L7;
        checkPickerDate(r1, r2, r3);
        return;
    L7:
        return;
    }

    public static /* synthetic */ void $r8$lambda$tLKHOLvmGQIdm47MwScEem6WaXA(EditText r5, long r6, int r8, EditText r9, AlertDialog r10, int r11) {
        if (r5.getText() != null) goto L6;
        return;
    L6:
        if (r6 <= 0) goto L22;
        TLRPC.User r1 = MessagesController.getInstance(r8).getUser(Long.valueOf(r6));
        String r52 = r5.getText().toString();
        String r92 = r9.getText().toString();
        String r2 = r1.first_name;
        String r3 = r1.last_name;
        if (r2 != null) goto L10;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        if (r3 != null) goto L13;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if (r2.equals(r52) == true) goto L15;
    L18:
        TL_account.updateProfile r22 = new TL_account.updateProfile();
        r22.flags = 3;
        r22.first_name = r52;
        r1.first_name = r52;
        r22.last_name = r92;
        r1.last_name = r92;
        TLRPC.User r53 = MessagesController.getInstance(r8).getUser(Long.valueOf(UserConfig.getInstance(r8).getClientUserId()));
        if (r53 == null) goto L21;
        r53.first_name = r22.first_name;
        r53.last_name = r22.last_name;
    L21:
        UserConfig.getInstance(r8).saveConfig(true);
        NotificationCenter.getInstance(r8).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        NotificationCenter.getInstance(r8).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        ConnectionsManager.getInstance(r8).sendRequest(r22, new AlertsCreator$$ExternalSyntheticLambda219());
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{3, Long.valueOf(r6)});
    L29:
        r10.dismiss();
        return;
    L15:
        if (r3.equals(r92) == false) goto L18;
        r10.dismiss();
        return;
    L22:
        long r12 = -r6;
        TLRPC.Chat r93 = MessagesController.getInstance(r8).getChat(Long.valueOf(r12));
        String r54 = r5.getText().toString();
        String r112 = r93.title;
        if (r112 != null) goto L25;
    L28:
        r93.title = r54;
        NotificationCenter.getInstance(r8).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_CHAT_NAME)});
        MessagesController.getInstance(r8).changeChatTitle(r12, r54);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{3, Long.valueOf(r6)});
        goto L29
    L25:
        if (r112.equals(r54) == false) goto L28;
        r10.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$tO4ctTlKET7SjMQzZMq7dT7Nksk(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    /* renamed from: $r8$lambda$tUfQ2-PKxixHa2KrEl7AuAV6nLo, reason: not valid java name */
    public static /* synthetic */ void m8780$r8$lambda$tUfQ2PKxixHa2KrEl7AuAV6nLo(boolean r0, Context r1, AtomicBoolean r2, Consumer r3, AlertDialog r4, int r5) {
        if (r0 == true) goto L11;
        r2.set(true);
        r3.accept(Boolean.TRUE);
        return;
    L11:
        Intent r02 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L6
        r02.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L6
        r1.startActivity(r02);     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$tcakdY0PumWez3Ois6Fu-11T_fQ, reason: not valid java name */
    public static /* synthetic */ CharSequence m8781$r8$lambda$tcakdY0PumWez3Ois6Fu11T_fQ(Integer r2) {
        return LocaleController.formatPluralString("Minutes", r2.intValue(), new Object[0]);
    }

    /* renamed from: $r8$lambda$u-z7IjEYVK9bdF_AkdnLS7OhyiI, reason: not valid java name */
    public static /* synthetic */ void m8782$r8$lambda$uz7IjEYVK9bdF_AkdnLS7OhyiI(TextView r0, long r1, long r3, int r5, NumberPicker r6, NumberPicker r7, NumberPicker r8, NumberPicker r9, int r10, int r11) {
        checkScheduleDate(r0, null, r1, r3, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$uLatIWKpzF15oHxUpoOqNqIpg3g(Utilities.Callback r0, NumberPicker r1, int r2, int r3) {
        r0.run(Boolean.TRUE);
    }

    public static /* synthetic */ void $r8$lambda$uVCYcnN8kCCFstlQg99xvO4dOpQ(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$uW4QSppfZwBrBGFbXMYFIssnLgw(int[] r1, int r2, Runnable r3) {
        r1[0] = r2;
        r3.run();
    }

    /* renamed from: $r8$lambda$vKtI7mBYB6fypztloZfGvH-05hI, reason: not valid java name */
    public static /* synthetic */ void m8783$r8$lambda$vKtI7mBYB6fypztloZfGvH05hI(AlertDialog r0, int r1) {
    }

    public static /* synthetic */ void $r8$lambda$vnc7xQJSMx1gukA4o_qv4Liibm4(TLRPC.User r10, AccountInstance r11, CheckBoxCell[] r12, long r13, TLRPC.Chat r15, TLRPC.EncryptedChat r16, boolean r17, MessagesStorage.IntCallback r18, AlertDialog r19, int r20) {
        if (r10 == null) goto L6;
        r11.getMessagesController().blockPeer(r10.id);
    L6:
        if (r12 == null) goto L11;
        CheckBoxCell r0 = r12[0];
        if (r0 != null) goto L10;
    L18:
        if (r12 != null) goto L20;
    L24:
        if (r15 != null) goto L26;
        r11.getMessagesController().deleteDialog(r13, 0);
    L30:
        r18.run(1);
        return;
    L26:
        if (ChatObject.isNotInChat(r15) == false) goto L28;
        r11.getMessagesController().deleteDialog(r13, 0);
        goto L30
    L28:
        r11.getMessagesController().deleteParticipantFromChat(-r13, r11.getMessagesController().getUser(Long.valueOf(r11.getUserConfig().getClientUserId())));
        goto L30
    L20:
        if (r12[1].isChecked() == true) goto L24;
        r18.run(0);
        return;
    L10:
        if (r0.isChecked() == false) goto L18;
    L11:
        MessagesController r02 = r11.getMessagesController();
        if (r15 == null) goto L16;
        if (r17 == false) goto L16;
        boolean r6 = true;
    L17:
        r02.reportSpam(r13, r10, r15, r16, r6);
    L16:
        r6 = false;
        goto L17
    }

    /* renamed from: $r8$lambda$vrSLp3UFCmVSRXwpxLktM-t1BEY, reason: not valid java name */
    public static /* synthetic */ void m8784$r8$lambda$vrSLp3UFCmVSRXwpxLktMt1BEY(AlertDialog r0) {
        r0.dismiss();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    /* renamed from: $r8$lambda$wEKpmet6Y-CIKSgDxPEtJPn_w1I, reason: not valid java name */
    public static /* synthetic */ void m8785$r8$lambda$wEKpmet6YCIKSgDxPEtJPn_w1I(Context r2, TLRPC.Chat r3, TLRPC.User r4, int r5, MessagesStorage.BooleanCallback r6, Theme.ResourcesProvider r7) {
        new SelectChatUserSheet(r2, r3, r4, new AlertsCreator$$ExternalSyntheticLambda218(r5, r6), r7).show();
    }

    public static /* synthetic */ boolean $r8$lambda$wH5vMMAp3IINl6iHFFPefOnAVXs(AlertDialog r1, AlertDialog.OnButtonClickListener r2, TextView r3, int r4, KeyEvent r5) {
        if (r4 == 6) goto L7;
        if (r5.getKeyCode() == 66) goto L7;
    L10:
        return false;
    L7:
        if (r1.isShowing() == false) goto L10;
        r2.onClick(r1, 0);
        return true;
    }

    public static /* synthetic */ boolean $r8$lambda$xKDEoQOzSYNNzqzXH2P1J7k6Inc(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ String $r8$lambda$xZJrrEX07ZhPwQAjjR70z5CqeU8(int r2) {
        if (r2 != 0) goto L6;
        return LocaleController.getString(R.string.ShortMessageLifetimeForever);
    L6:
        if (r2 < 1) goto L10;
        if (r2 >= 16) goto L10;
        return LocaleController.formatTTLString(r2);
    L10:
        if (r2 != 16) goto L14;
        return LocaleController.formatTTLString(30);
    L14:
        if (r2 != 17) goto L18;
        return LocaleController.formatTTLString(60);
    L18:
        if (r2 != 18) goto L22;
        return LocaleController.formatTTLString(3600);
    L22:
        if (r2 != 19) goto L26;
        return LocaleController.formatTTLString(86400);
    L26:
        if (r2 == 20) goto L28;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L28:
        return LocaleController.formatTTLString(604800);
    }

    public static /* synthetic */ String $r8$lambda$xfAs6VCGKbFRXttWbozEpfa_ntc(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    public static /* synthetic */ void $r8$lambda$yRG220QXrkXUFWJHQK1HM28YZSQ(MessagesStorage.BooleanCallback r0, boolean[] r1, AlertDialog r2, int r3) {
        r0.run(r1[0]);
    }

    public static /* synthetic */ void $r8$lambda$ybbFo4N65zXy_x4GVHtgSmHtb9U(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$ybyRgPIDaxmaU2dTTD2itje9Cvg(int r3, LinkSpanDrawable.LinksTextView r4) {
        ArrayList<TLRPC.PrivacyRule> r32 = ContactsController.getInstance(r3).getPrivacyRules(11);
        String r0 = LocaleController.getString(R.string.EditProfileBirthdayInfoContacts);
        if (r32 != null) goto L5;
    L18:
        r4.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(r0, new AlertsCreator$$ExternalSyntheticLambda152(r32)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.66f)));
        return;
    L5:
        if (r32.isEmpty() == true) goto L18;
        int r1 = 0;
    L8:
        if (r1 >= r32.size()) goto L18;
        if ((r32.get(r1) instanceof TLRPC.TL_privacyValueAllowContacts) == true) goto L11;
        if ((r32.get(r1) instanceof TLRPC.TL_privacyValueAllowAll) == false) goto L15;
    L16:
        r0 = LocaleController.getString(R.string.EditProfileBirthdayInfo);
    L17:
        r1 = r1 + 1;
        goto L8
    L15:
        if ((r32.get(r1) instanceof TLRPC.TL_privacyValueDisallowAll) == false) goto L17;
    L11:
        r0 = LocaleController.getString(R.string.EditProfileBirthdayInfoContacts);
        goto L18
    }

    public static /* synthetic */ String $r8$lambda$yogBRsKYFDB_8u1DCMlPQHSU5Gs(int r1) {
        return String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r1)});
    }

    /* renamed from: $r8$lambda$z-LQoKpXrSKHremMNYhEDNOPaQA, reason: not valid java name */
    public static /* synthetic */ boolean m8786$r8$lambda$zLQoKpXrSKHremMNYhEDNOPaQA(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$z9OV7Sej2fK9BMVVPq5X-Sk-9LM, reason: not valid java name */
    public static /* synthetic */ void m8787$r8$lambda$z9OV7Sej2fK9BMVVPq5XSk9LM(Context r1, AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        r1.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$zQAevWB6uuL7DP9vk4cQmoCklys(Utilities.Callback2 r0, CheckBoxCell r1, AlertDialog[] r2, AlertDialog r3, int r4) {
        r0.run(Boolean.TRUE, Boolean.valueOf(r1.isChecked()));
        AlertDialog r02 = r2[0];
        if (r02 == null) goto L6;
        r02.dismiss();
        return;
    }

    /* renamed from: $r8$lambda$zda2CJF2LrhK-zHj2eGwSHbPoX4, reason: not valid java name */
    public static /* synthetic */ void m8788$r8$lambda$zda2CJF2LrhKzHj2eGwSHbPoX4(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    static {
        URL_PATTERN = Pattern.compile("^([a-zA-Z][a-zA-Z0-9+\\-.]*://)?([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,}(:\\d+)?(/[^\\s]*)?$");
    }

    private static void checkCalendarDate(long r9, NumberPicker r11, NumberPicker r12, NumberPicker r13) {
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(r9);
        int r92 = 1;
        int r10 = r0.get(1);
        int r2 = r0.get(2);
        int r4 = r0.get(5);
        r0.setTimeInMillis(System.currentTimeMillis());
        int r5 = r0.get(1);
        int r6 = r0.get(2);
        int r7 = r0.get(5);
        r13.setMaxValue(r5);
        r13.setMinValue(r10);
        int r132 = r13.getValue();
        if (r132 != r5) goto L5;
        int r8 = r6;
    L6:
        r12.setMaxValue(r8);
        if (r132 != r10) goto L9;
        int r82 = r2;
    L10:
        r12.setMinValue(r82);
        int r122 = r12.getValue();
        r0.set(1, r132);
        r0.set(2, r122);
        int r02 = r0.getActualMaximum(5);
        if (r132 != r5) goto L14;
        if (r122 != r6) goto L14;
        r02 = Math.min(r7, r02);
    L14:
        r11.setMaxValue(r02);
        if (r132 != r10) goto L18;
        if (r122 != r2) goto L18;
        r92 = r4;
    L18:
        r11.setMinValue(r92);
        return;
    L9:
        r82 = 0;
        goto L10
    L5:
        r8 = 11;
        goto L6
    }

    public static long checkFormattedDateInput(ButtonWithCounterView r7, NumberPicker r8, NumberPicker r9, NumberPicker r10, NumberPicker r11, int r12) {
        long r0 = System.currentTimeMillis();
        Calendar r2 = Calendar.getInstance();
        r2.setTimeInMillis(r0);
        int r1 = r2.get(1);
        int r3 = ((r9.getValue() - 120) / 12) + r1;
        r2.clear();
        r2.set(1, r3);
        int r5 = 2;
        r2.set(2, (r9.getValue() - 120) % 12);
        r8.setMinValue(1);
        r8.setMaxValue(r2.getActualMaximum(5));
        int r82 = r8.getValue();
        int r102 = r10.getValue();
        int r112 = r11.getValue();
        r2.set(5, r82);
        r2.set(11, r102);
        r2.set(12, r112);
        long r92 = r2.getTimeInMillis();
        r2.setTimeInMillis(r92);
        if (r7 == null) goto L36;
        boolean r113 = false;
        if (r12 == 0) goto L31;
        TLRPC.TL_messageEntityFormattedDate r83 = new TLRPC.TL_messageEntityFormattedDate();
        r83.date = (int) (r92 / 1000);
        if ((r12 & 1) == 0) goto L9;
        boolean r13 = true;
    L10:
        r83.relative = r13;
        if ((r12 & 2) == 0) goto L13;
        boolean r14 = true;
    L14:
        r83.short_time = r14;
        if ((r12 & 4) == 0) goto L17;
        boolean r15 = true;
    L18:
        r83.long_time = r15;
        if ((r12 & 8) == 0) goto L21;
        boolean r16 = true;
    L22:
        r83.short_date = r16;
        if ((r12 & 16) == 0) goto L25;
        boolean r17 = true;
    L26:
        r83.long_date = r17;
        if ((r12 & 32) == 0) goto L29;
        r113 = true;
    L29:
        r83.day_of_week = r113;
        r83.flags = r12;
        r7.setText(LocaleController.formatEntityFormattedDate(r83), true);
        return r92;
    L25:
        r17 = false;
        goto L26
    L21:
        r16 = false;
        goto L22
    L17:
        r15 = false;
        goto L18
    L13:
        r14 = false;
        goto L14
    L9:
        r13 = false;
        goto L10
    L31:
        if (r82 != 0) goto L33;
        r5 = 0;
    L35:
        r7.setText(LocaleController.getInstance().getFormatterScheduleSend(r5 + 9).format(r92), true);
        goto L36
    L33:
        if (r1 != r3) goto L35;
        r5 = 1;
    L36:
        return r92;
    }

    private static boolean checkInternalBotApp(String r1) {
        return Uri.parse(r1).getPath().matches("^/\\w*/[^\\d]*(?:\\?startapp=.*?|)$");
    }

    private static void checkPickerDate(NumberPicker r5, NumberPicker r6, NumberPicker r7) {
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(System.currentTimeMillis());
        int r1 = 1;
        int r2 = r0.get(1);
        int r3 = r0.get(2);
        int r02 = r0.get(5);
        r7.setMinValue(r2);
        int r72 = r7.getValue();
        if (r72 != r2) goto L5;
        int r4 = r3;
    L6:
        r6.setMinValue(r4);
        int r62 = r6.getValue();
        if (r72 != r2) goto L10;
        if (r62 != r3) goto L10;
        r1 = r02;
    L10:
        r5.setMinValue(r1);
        return;
    L5:
        r4 = 0;
        goto L6
    }

    public static void checkPollCloseCustomDeadline(TextView r4, NumberPicker r5, NumberPicker r6, NumberPicker r7) {
        int r52 = r5.getValue();
        int r62 = r6.getValue();
        int r72 = r7.getValue();
        Calendar r0 = Calendar.getInstance();
        long r1 = System.currentTimeMillis();
        r0.setTimeInMillis(r1);
        r0.add(6, r52);
        r0.set(11, r62);
        r0.set(12, r72);
        r0.set(13, 0);
        r0.set(14, 0);
        long r53 = r0.getTimeInMillis();
        if (r4 == null) goto L6;
        r4.setText(formatPollCloseCustomDeadline((int) ((r53 - r1) / 1000)));
        return;
    }

    public static void checkRestrictedInviteUsers(int r15, TLRPC.Chat r16, TLRPC.TL_messages_invitedUsers r17) {
        if (r17 != null) goto L5;
        return;
    L5:
        if (r17.missing_invitees.isEmpty() == true) goto L45;
        if (r16 == null) goto L46;
        ArrayList r5 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        ArrayList<TLRPC.TL_missingInvitee> r1 = r17.missing_invitees;
        int r2 = r1.size();
        int r4 = 0;
    L9:
        if (r4 >= r2) goto L31;
        TLRPC.TL_missingInvitee r8 = r1.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_missingInvitee r82 = r8;
        if (r17.updates == null) goto L19;
        int r9 = 0;
    L14:
        if (r9 >= r17.updates.users.size()) goto L19;
        TLRPC.User r10 = r17.updates.users.get(r9);
        if (r10.id == r82.user_id) goto L20;
        r9 = r9 + 1;
    L20:
        if (r10 != null) goto L22;
        r10 = MessagesController.getInstance(r15).getUser(Long.valueOf(r82.user_id));
    L22:
        if (r10 == null) goto L9;
        r5.add(r10);
        if (r82.premium_required_for_pm == false) goto L28;
        r6.add(Long.valueOf(r10.id));
    L28:
        if (r82.premium_would_allow_invite == false) goto L9;
        r7.add(Long.valueOf(r10.id));
    L19:
        r10 = null;
        goto L20
    L31:
        if (r5.isEmpty() == true) goto L47;
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda4(r15, r16, r5, r6, r7), 200);
        return;
    L47:
        return;
    L46:
        return;
    }

    public static boolean checkScheduleDate(TextView r10, TextView r11, int r12, NumberPicker r13, NumberPicker r14, NumberPicker r15) {
        return checkScheduleDate(r10, r11, 0, 0, r12, r13, r14, r15);
    }

    public static boolean checkScheduleDate(TextView r10, TextView r11, long r12, int r14, NumberPicker r15, NumberPicker r16, NumberPicker r17) {
        return checkScheduleDate(r10, r11, 0, r12, r14, r15, r16, r17);
    }

    public static boolean checkScheduleDate(TextView r29, TextView r30, long r31, long r33, int r35, NumberPicker r36, NumberPicker r37, NumberPicker r38) {
        int r7 = r36.getValue();
        int r8 = r37.getValue();
        int r9 = r38.getValue();
        Calendar r10 = Calendar.getInstance();
        long r11 = System.currentTimeMillis();
        r10.setTimeInMillis(r11);
        int r14 = r10.get(1);
        r10.get(6);
        if (r33 <= 0) goto L5;
        int r18 = r14;
        r10.setTimeInMillis(r11 + (r33 * 1000));
        r10.set(11, 23);
        r10.set(12, 59);
        r10.set(13, 59);
        r10.set(14, 0);
        long r25 = r11;
        int r112 = (int) ChronoUnit.DAYS.between(Instant.ofEpochMilli(r11).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(r10.getTimeInMillis()).atZone(ZoneId.systemDefault()).toLocalDate());
        long r12 = r10.getTimeInMillis();
        int r142 = 23;
        int r15 = 59;
    L6:
        int r332 = r15;
        if (r31 <= 0) goto L10;
        long r2 = TimeUnit.SECONDS.toMillis(r31);
    L9:
        long r312 = r2;
        long r22 = r25 + r312;
        r10.setTimeInMillis(r22);
        int r1 = r10.get(11);
        int r23 = r10.get(12);
        long r27 = r12;
        r10.setTimeInMillis(System.currentTimeMillis());
        r10.add(6, r7);
        r10.set(11, r8);
        r10.set(12, r9);
        r10.set(13, 0);
        r10.set(14, 0);
        long r82 = r10.getTimeInMillis();
        r36.setMinValue(0);
        if (r27 <= 0) goto L14;
        r36.setMaxValue(r112);
    L14:
        int r4 = r36.getValue();
        if (r4 != 0) goto L17;
        int r122 = r1;
    L18:
        r37.setMinValue(r122);
        if (r27 <= 0) goto L24;
        if (r4 != r112) goto L22;
        int r13 = r142;
    L23:
        r37.setMaxValue(r13);
        goto L24
    L22:
        r13 = 23;
    L24:
        int r5 = r37.getValue();
        if (r4 != 0) goto L28;
        if (r5 != r1) goto L28;
        int r16 = r23;
    L29:
        r38.setMinValue(r16);
        if (r27 <= 0) goto L36;
        if (r4 != r112) goto L34;
        if (r5 != r142) goto L34;
        int r152 = r332;
    L35:
        r38.setMaxValue(r152);
    L34:
        r152 = 59;
    L36:
        int r17 = r38.getValue();
        if (r82 > r22) goto L40;
        r10.setTimeInMillis(r22);
    L44:
        int r3 = r10.get(1);
        r10.setTimeInMillis(System.currentTimeMillis());
        r10.add(6, r4);
        r10.set(11, r5);
        r10.set(12, r17);
        r10.set(13, 0);
        r10.set(14, 0);
        long r19 = r10.getTimeInMillis();
        if (r29 == null) goto L53;
        if (r4 != 0) goto L49;
        int r32 = 0;
    L52:
        r29.setText(LocaleController.getInstance().getFormatterScheduleSend(r32 + (r35 * 3)).format(r19));
        goto L53
    L49:
        if (r18 != r3) goto L51;
        r32 = 1;
        goto L52
    L51:
        r32 = 2;
    L53:
        if (r30 == null) goto L69;
        int r0 = (int) ((r19 - r25) / 1000);
        if (r0 <= 86400) goto L58;
        String r02 = LocaleController.formatPluralString("DaysSchedule", Math.round(r0 / 86400.0f), new Object[0]);
    L65:
        if (r30.getTag() == null) goto L67;
        r30.setText(LocaleController.formatString("VoipChannelScheduleInfo", R.string.VoipChannelScheduleInfo, new Object[]{r02}));
        goto L69
    L67:
        r30.setText(LocaleController.formatString("VoipGroupScheduleInfo", R.string.VoipGroupScheduleInfo, new Object[]{r02}));
        goto L69
    L58:
        if (r0 < 3600) goto L61;
        r02 = LocaleController.formatPluralString("HoursSchedule", Math.round(r0 / 3600.0f), new Object[0]);
        goto L65
    L61:
        if (r0 < 60) goto L63;
        r02 = LocaleController.formatPluralString("MinutesSchedule", Math.round(r0 / 60.0f), new Object[0]);
        goto L65
    L63:
        r02 = LocaleController.formatPluralString("SecondsSchedule", r0, new Object[0]);
    L69:
        if ((r82 - r25) <= r312) goto L72;
        return true;
    L72:
        return false;
    L40:
        if (r27 <= 0) goto L44;
        if (r82 <= r27) goto L44;
        r10.setTimeInMillis(r27);
    L28:
        r16 = 0;
        goto L29
    L17:
        r122 = 0;
        goto L18
    L10:
        r2 = 60000;
        goto L9
    L5:
        r25 = r11;
        r18 = r14;
        r12 = r33;
        r112 = 0;
        r142 = 0;
        r15 = 0;
        goto L6
    }

    public static boolean checkSlowMode(Context r8, int r9, long r10, boolean r12) {
        if (DialogObject.isChatDialog(r10) == false) goto L22;
        TLRPC.Chat r102 = MessagesController.getInstance(r9).getChat(Long.valueOf(-r10));
        if (r102 != null) goto L7;
        return false;
    L7:
        if (r102.slowmode_enabled == true) goto L9;
        return false;
    L9:
        if (ChatObject.hasAdminRights(r102) == false) goto L11;
        return false;
    L11:
        if (r12 == true) goto L19;
        TLRPC.ChatFull r0 = MessagesController.getInstance(r9).getChatFull(r102.id);
        if (r0 != null) goto L15;
        r0 = MessagesStorage.getInstance(r9).loadChatInfo(r102.id, ChatObject.isChannel(r102), new CountDownLatch(1), false, false);
    L15:
        if (r0 == null) goto L19;
        if (r0.slowmode_next_send_date < ConnectionsManager.getInstance(r9).getCurrentTime()) goto L19;
        r12 = true;
    L19:
        if (r12 == false) goto L27;
        createSimpleAlert(r8, r102.title, LocaleController.getString(R.string.SlowmodeSendError)).show();
        return true;
    L27:
        return false;
    L22:
        return false;
    }

    public static AlertDialog createAccountSelectDialog(Activity r10, AccountSelectDelegate r11) {
        if (UserConfig.getActivatedAccountsCount() >= 2) goto L5;
        return null;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r10);
        Runnable r1 = r0.getDismissRunnable();
        AlertDialog[] r4 = new AlertDialog[1];
        LinearLayout r5 = new LinearLayout(r10);
        r5.setOrientation(1);
        int r6 = 0;
    L7:
        if (r6 >= 16) goto L12;
        if (UserConfig.getInstance(r6).getCurrentUser() == null) goto L11;
        AccountSelectCell r7 = new AccountSelectCell(r10, false);
        r7.setAccount(r6, false);
        r7.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r7.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r5.addView(r7, LayoutHelper.createLinear(-1, 50));
        r7.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda31(r4, r1, r11));
    L11:
        r6 = r6 + 1;
        goto L7
    L12:
        r0.setTitle(LocaleController.getString(R.string.SelectAccount));
        r0.setView(r5);
        r0.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r102 = r0.create();
        r4[0] = r102;
        return r102;
    }

    public static Dialog createApkRestrictedDialog(Context r4, Theme.ResourcesProvider r5) {
        return new AlertDialog.Builder(r4, r5).setMessage(LocaleController.getString(R.string.ApkRestricted)).setTopAnimation(R.raw.permission_request_apk, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new AlertsCreator$$ExternalSyntheticLambda0(r4)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create();
    }

    public static BottomSheet.Builder createAutoDeleteDatePickerDialog(Context r19, int r20, Theme.ResourcesProvider r21, ScheduleDatePickerDelegate r22) {
        if (r19 != null) goto L6;
        return null;
    L6:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(r21);
        BottomSheet.Builder r3 = new BottomSheet.Builder(r19, false, r21);
        r3.setApplyBottomPadding(false);
        int[] r6 = {0, 1440, 2880, 4320, 5760, 7200, 8640, 10080, 20160, 30240, 44640, 89280, 133920, 178560, 223200, 267840, 525600};
        AnonymousClass45 r7 = new AnonymousClass45(r19, r21, r6);
        r7.setMinValue(0);
        r7.setMaxValue(16);
        r7.setTextColor(r2.textColor);
        r7.setValue(0);
        r7.setFormatter(new AlertsCreator$$ExternalSyntheticLambda154(r6));
        AnonymousClass46 r1 = new AnonymousClass46(r19, r7);
        r1.setOrientation(1);
        FrameLayout r9 = new FrameLayout(r19);
        r1.addView(r9, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r10 = new TextView(r19);
        r10.setText(LocaleController.getString(R.string.AutoDeleteAfteTitle));
        r10.setTextColor(r2.textColor);
        r10.setTextSize(1, 20.0f);
        r10.setTypeface(AndroidUtilities.bold());
        r9.addView(r10, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r10.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda155());
        LinearLayout r92 = new LinearLayout(r19);
        r92.setOrientation(0);
        r92.setWeightSum(1.0f);
        r1.addView(r92, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        AnonymousClass47 r11 = new AnonymousClass47(r19, true, true, false);
        r92.addView(r7, LayoutHelper.createLinear(0, 270, 1.0f));
        r11.setPadding(0, 0, 0, 0);
        r11.setGravity(17);
        r11.setTextColor(r2.buttonTextColor);
        r11.setTextSize(AndroidUtilities.dp(14.0f));
        r11.setTypeface(AndroidUtilities.bold());
        r11.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), r2.buttonBackgroundColor, r2.buttonBackgroundPressedColor));
        r1.addView(r11, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r11.setText(LocaleController.getString(R.string.DisableAutoDeleteTimer));
        r7.setOnValueChangedListener(new AlertsCreator$$ExternalSyntheticLambda156(r11));
        r11.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda157(r6, r7, r22, r3));
        r3.setCustomView(r1);
        BottomSheet r0 = r3.show();
        r0.setBackgroundColor(r2.backgroundColor);
        r0.fixNavigationBar(r2.backgroundColor);
        return r3;
    }

    public static Dialog createBackgroundActivityDialog(Context r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r5).setTitle(LocaleController.getString(R.string.AllowBackgroundActivity));
        if (OneUIUtilities.isOneUI() == true) goto L5;
        int r1 = R.string.AllowBackgroundActivityInfo;
    L10:
        return r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(r1))).setTopAnimation(R.raw.permission_request_apk, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new AlertsCreator$$ExternalSyntheticLambda7(r5)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda8()).create();
    L5:
        if (Build.VERSION.SDK_INT < 31) goto L7;
        r1 = R.string.AllowBackgroundActivityInfoOneUIAboveS;
        goto L10
    L7:
        r1 = R.string.AllowBackgroundActivityInfoOneUIBelowS;
        goto L10
    }

    public static AlertDialog.Builder createBackgroundLocationPermissionDialog(Activity r11, TLRPC.User r12, Runnable r13, Theme.ResourcesProvider r14) {
        if (r11 != null) goto L4;
        return null;
    L4:
        if (Build.VERSION.SDK_INT < 29) goto L18;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r11, r14);
        if (Theme.getCurrentTheme().isDark() == false) goto L9;
        int r142 = R.raw.permission_map_dark;
    L10:
        String r143 = AndroidUtilities.readRes(r142);
        if (Theme.getCurrentTheme().isDark() == false) goto L13;
        int r1 = R.raw.permission_pin_dark;
    L14:
        String r15 = AndroidUtilities.readRes(r1);
        FrameLayout r2 = new FrameLayout(r11);
        r2.setClipToOutline(true);
        r2.setOutlineProvider(new AnonymousClass58());
        View r3 = new View(r11);
        r3.setBackground(SvgHelper.getDrawable(r143));
        r2.addView(r3, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        View r144 = new View(r11);
        r144.setBackground(SvgHelper.getDrawable(r15));
        r2.addView(r144, LayoutHelper.createFrame(60, 82.0f, 17, 0.0f, 0.0f, 0.0f, 0.0f));
        BackupImageView r145 = new BackupImageView(r11);
        r145.setRoundRadius(ExteraConfig.getAvatarCorners(52.0f));
        r145.setForUserOrChat(r12, new AvatarDrawable(r12));
        r2.addView(r145, LayoutHelper.createFrame(52, 52.0f, 17, 0.0f, 0.0f, 0.0f, 11.0f));
        r0.setTopView(r2);
        r0.setTopViewAspectRatio(0.37820512f);
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionBackgroundLocation)));
        r0.setPositiveButton(LocaleController.getString(R.string.Continue), new AlertsCreator$$ExternalSyntheticLambda141(r11));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new AlertsCreator$$ExternalSyntheticLambda142(r13));
        return r0;
    L13:
        r1 = R.raw.permission_pin;
        goto L14
    L9:
        r142 = R.raw.permission_map;
        goto L10
    L18:
        return null;
    }

    public static BottomSheet.Builder createBirthdayPickerDialog(Context r31, String r32, String r33, TL_account.TL_birthday r34, Utilities.Callback<TL_account.TL_birthday> r35, Runnable r36, boolean r37, boolean r38, Theme.ResourcesProvider r39) {
        if (r31 != null) goto L6;
        return null;
    L6:
        BottomSheet.Builder r8 = new BottomSheet.Builder(r31, false, r39);
        r8.setApplyBottomPadding(false);
        NumberPicker r14 = new NumberPicker(r31, r39);
        r14.setTextOffset(AndroidUtilities.dp(10.0f));
        r14.setItemCount(5);
        NumberPicker r15 = new NumberPicker(r31, r39);
        r15.setItemCount(5);
        r15.setTextOffset(-AndroidUtilities.dp(10.0f));
        NumberPicker r12 = new NumberPicker(r31, r39);
        r12.setItemCount(5);
        r12.setTextOffset(-AndroidUtilities.dp(24.0f));
        AnonymousClass33 r5 = new AnonymousClass33(r31, r14, r15, r12);
        r5.setOrientation(1);
        FrameLayout r7 = new FrameLayout(r31);
        r5.addView(r7, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r9 = new TextView(r31);
        r9.setText(r32);
        r9.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r39));
        r9.setTextSize(1, 20.0f);
        r9.setTypeface(AndroidUtilities.bold());
        r7.addView(r9, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r9.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda57());
        LinearLayout r72 = new LinearLayout(r31);
        r72.setGravity(17);
        r72.setOrientation(0);
        r72.setWeightSum(1.0f);
        r5.addView(r72, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        Calendar r11 = Calendar.getInstance();
        int r13 = r11.get(1) - 149;
        r11.setTimeInMillis(System.currentTimeMillis());
        int r18 = r11.get(5);
        int r17 = r11.get(2);
        int r16 = r11.get(1);
        int r132 = r16 + 1;
        AlertsCreator$$ExternalSyntheticLambda59 r112 = new AlertsCreator$$ExternalSyntheticLambda59(r12, r132, r14, r15, r16, r17, r18);
        System.currentTimeMillis();
        AnonymousClass34 r92 = new AnonymousClass34(r31);
        r72.addView(r14, LayoutHelper.createLinear(0, 270, 0.25f));
        r14.setMinValue(1);
        r14.setMaxValue(31);
        r14.setWrapSelectorWheel(false);
        r14.setFormatter(new AlertsCreator$$ExternalSyntheticLambda60());
        AlertsCreator$$ExternalSyntheticLambda61 r93 = new AlertsCreator$$ExternalSyntheticLambda61(r112);
        r14.setOnScrollListener(r93);
        r15.setMinValue(0);
        r15.setMaxValue(11);
        r15.setWrapSelectorWheel(false);
        r72.addView(r15, LayoutHelper.createLinear(0, 270, 0.5f));
        r15.setFormatter(new AlertsCreator$$ExternalSyntheticLambda62());
        r15.setOnScrollListener(r93);
        r12.setMinValue(r13);
        r12.setMaxValue(r132);
        r12.setWrapSelectorWheel(false);
        r12.setFormatter(new AlertsCreator$$ExternalSyntheticLambda63(r132));
        r72.addView(r12, LayoutHelper.createLinear(0, 270, 0.25f));
        r12.setOnScrollListener(r93);
        if (r34 == null) goto L12;
        r14.setValue(r34.day);
        r15.setValue(r34.month - 1);
        if ((r34.flags & 1) == 0) goto L11;
        r12.setValue(r34.year);
    L13:
        r112.run();
        if (r36 == null) goto L16;
        FrameLayout r1 = new FrameLayout(r31);
        LinkSpanDrawable.LinksTextView r3 = new LinkSpanDrawable.LinksTextView(r31);
        r3.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        r3.setTextSize(1, 13.0f);
        r3.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, r39));
        r3.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r39));
        r3.setGravity(17);
        r1.addView(r3, LayoutHelper.createFrame(-2, -2, 17));
        r5.addView(r1, LayoutHelper.createLinear(-1, -2));
        int r4 = UserConfig.selectedAccount;
        AlertsCreator$$ExternalSyntheticLambda64 r6 = new AlertsCreator$$ExternalSyntheticLambda64(r4, r3);
        r6.run();
        NotificationCenter.getInstance(r4).listen(r1, NotificationCenter.privacyRulesUpdated, new AlertsCreator$$ExternalSyntheticLambda65(r6));
        ContactsController.getInstance(r4).loadPrivacySettings();
    L16:
        if (r37 == false) goto L18;
        ButtonWithCounterView r19 = new ButtonWithCounterView(r31, false, r39);
        r19.setText(LocaleController.getString(R.string.DateOfBirthHideYear), false);
        r19.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda66(r12, r132, r112));
        r5.addView(r19, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 4));
    L18:
        r92.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r92.setGravity(17);
        r92.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r39));
        r92.setTextSize(1, 14.0f);
        r92.setTypeface(AndroidUtilities.bold());
        r92.setText(r33);
        r92.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(24.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r39), Theme.getColor(Theme.key_featuredStickers_addButtonPressed, r39)));
        ScaleStateListAnimator.apply(r92, 0.02f, 1.2f);
        if (r37 == false) goto L21;
        int r26 = 0;
    L22:
        if (r38 == false) goto L24;
        int r28 = 0;
    L25:
        r5.addView(r92, LayoutHelper.createLinear(-1, 48, 83, 16, r26, 16, r28));
        r92.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda67(r14, r15, r12, r132, r8, r35));
        if (r38 == false) goto L28;
        ButtonWithCounterView r310 = new ButtonWithCounterView(r31, false, r39);
        r310.setText(LocaleController.getString(R.string.BirthdayRemove), false);
        r310.setRound();
        r310.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda58(r8, r35));
        r5.addView(r310, LayoutHelper.createLinear(-1, 48, 83, 16, 4, 16, 16));
    L28:
        r8.setCustomView(r5);
        return r8;
    L24:
        r28 = 16;
        goto L25
    L21:
        r26 = 15;
        goto L22
    L11:
        r12.setValue(r132);
        goto L13
    L12:
        r14.setValue(r11.get(5));
        r15.setValue(r11.get(2));
        r12.setValue(r132);
        goto L13
    }

    public static void createBlockDialogAlert(BaseFragment r18, int r19, boolean r20, TLRPC.User r21, BlockDialogCallback r22) {
        if (r18 != null) goto L5;
        return;
    L5:
        if (r18.getParentActivity() != null) goto L7;
        return;
    L7:
        if (r19 != 1) goto L10;
        if (r21 != null) goto L10;
        return;
    L10:
        Activity r4 = r18.getParentActivity();
        AlertDialog.Builder r5 = new AlertDialog.Builder(r4);
        CheckBoxCell[] r7 = new CheckBoxCell[2];
        LinearLayout r8 = new LinearLayout(r4);
        r8.setOrientation(1);
        r5.setView(r8);
        if (r19 != 1) goto L13;
        String r2 = ContactsController.formatName(r21.first_name, r21.last_name);
        r5.setTitle(LocaleController.formatString("BlockUserTitle", R.string.BlockUserTitle, new Object[]{r2}));
        String r9 = LocaleController.getString(R.string.BlockUser);
        r5.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("BlockUserMessage", R.string.BlockUserMessage, new Object[]{r2})));
    L14:
        boolean[] r23 = {true, true};
        int r11 = 0;
    L16:
        if (r11 >= 2) goto L39;
        if (r11 != 0) goto L20;
        if (r20 == true) goto L20;
    L38:
        r11 = r11 + 1;
    L20:
        CheckBoxCell r13 = new CheckBoxCell(r4, 1);
        r7[r11] = r13;
        r13.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (r11 != 0) goto L23;
        r7[r11].setText(LocaleController.getString(R.string.ReportSpamTitle), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
    L29:
        CheckBoxCell r132 = r7[r11];
        if (LocaleController.isRTL == false) goto L32;
        int r14 = AndroidUtilities.dp(16.0f);
    L34:
        if (LocaleController.isRTL == false) goto L36;
        int r15 = AndroidUtilities.dp(8.0f);
    L37:
        r132.setPadding(r14, 0, r15, 0);
        r8.addView(r7[r11], LayoutHelper.createLinear(-1, 48));
        r7[r11].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda150(r23, r11));
        goto L38
    L36:
        r15 = AndroidUtilities.dp(16.0f);
        goto L37
    L32:
        r14 = AndroidUtilities.dp(8.0f);
        goto L34
    L23:
        CheckBoxCell r142 = r7[r11];
        if (r19 != 1) goto L27;
        int r152 = R.string.DeleteThisChatBothSides;
    L28:
        r142.setText(LocaleController.getString(r152), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
        goto L29
    L27:
        r152 = R.string.DeleteTheseChatsBothSides;
        goto L28
    L39:
        r5.setPositiveButton(r9, new AlertsCreator$$ExternalSyntheticLambda151(r22, r23));
        r5.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r1 = r5.create();
        r18.showDialog(r1);
        TextView r0 = (TextView) r1.getButton(-1);
        if (r0 == null) goto L48;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L48:
        return;
    L13:
        r5.setTitle(LocaleController.formatString("BlockUserTitle", R.string.BlockUserTitle, new Object[]{LocaleController.formatPluralString("UsersCountTitle", r19, new Object[0])}));
        r9 = LocaleController.getString(R.string.BlockUsers);
        r5.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("BlockUsersMessage", R.string.BlockUsersMessage, new Object[]{LocaleController.formatPluralString("UsersCount", r19, new Object[0])})));
        goto L14
    }

    public static void createBotLaunchAlert(BaseFragment r28, AtomicBoolean r29, TLRPC.User r30, Runnable r31) {
        if (r28 != null) goto L5;
        return;
    L5:
        Context r3 = r28.getContext();
        CheckBoxCell[] r5 = new CheckBoxCell[1];
        AlertDialog.Builder r6 = new AlertDialog.Builder(r3);
        AnonymousClass6 r7 = new AnonymousClass6(r3);
        NotificationCenter.listenEmojiLoading(r7);
        r7.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r7.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        r7.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L8;
        int r9 = 5;
    L9:
        r7.setGravity(r9 | 48);
        AnonymousClass7 r92 = new AnonymousClass7(r3, r5);
        r6.setCustomViewOffset(6);
        r6.setView(r92);
        AvatarDrawable r12 = new AvatarDrawable();
        r12.setTextSize(AndroidUtilities.dp(18.0f));
        BackupImageView r13 = new BackupImageView(r3);
        r13.setRoundRadius(AndroidUtilities.dp(20.0f));
        if (LocaleController.isRTL == false) goto L12;
        int r14 = 5;
    L13:
        r92.addView(r13, LayoutHelper.createFrame(40, 40.0f, r14 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        SimpleTextView r142 = new SimpleTextView(r3);
        r142.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r142.setTextSize(20);
        r142.setTypeface(AndroidUtilities.bold());
        if (LocaleController.isRTL == false) goto L16;
        int r15 = 5;
    L17:
        r142.setGravity(r15 | 16);
        r142.setEllipsizeByGradient(true);
        r142.setText(r30.first_name);
        if (r30.scam == false) goto L22;
        r142.setRightDrawable(Theme.dialogs_scamDrawable);
    L20:
        float r16 = 16.0f;
    L27:
        TextView r4 = new TextView(r3);
        r4.setTextColor(Theme.getColor(Theme.key_dialogTextBlue));
        r4.setTextSize(1, 14.0f);
        r4.setLines(1);
        r4.setMaxLines(1);
        r4.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L30;
        int r8 = 5;
    L31:
        r4.setGravity(r8 | 16);
        r4.setEllipsize(TextUtils.TruncateAt.END);
        r4.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda206(r30, r28, r6));
        SpannableString r82 = SpannableString.valueOf(LocaleController.getString(R.string.MoreAboutThisBot) + "  ");
        ColoredImageSpan r10 = new ColoredImageSpan(R.drawable.attach_arrow_right);
        r10.setTopOffset(1);
        r10.setSize(AndroidUtilities.dp(10.0f));
        r82.setSpan(r10, r82.length() - 1, r82.length(), 33);
        r4.setText(r82);
        boolean r52 = LocaleController.isRTL;
        if (r52 == false) goto L34;
        int r83 = 5;
    L35:
        int r23 = r83 | 48;
        int r84 = 76;
        if (r52 == false) goto L38;
        int r11 = 21;
    L39:
        float r112 = r11;
        if (r52 == false) goto L42;
        int r53 = 76;
    L43:
        r92.addView(r142, LayoutHelper.createFrame(-1, -2.0f, r23, r112, 0.0f, r53, 0.0f));
        boolean r54 = LocaleController.isRTL;
        if (r54 == false) goto L46;
        int r113 = 5;
    L47:
        int r232 = r113 | 48;
        if (r54 == false) goto L50;
        int r114 = 21;
    L51:
        float r115 = r114;
        if (r54 == true) goto L55;
        r84 = 21;
    L55:
        r92.addView(r4, LayoutHelper.createFrame(-1, -2.0f, r232, r115, 24.0f, r84, 0.0f));
        if (LocaleController.isRTL == false) goto L58;
        int r102 = 5;
    L59:
        r92.addView(r7, LayoutHelper.createFrame(-2, -2.0f, r102 | 48, 24.0f, 57.0f, 24.0f, 1.0f));
        if (r29 == null) goto L71;
        r29.set(true);
        CheckBoxCell r42 = new CheckBoxCell(r3, 1, r28.getResourceProvider());
        r5[0] = r42;
        r42.allowMultiline();
        r5[0].setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r5[0].setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.OpenUrlOption2, new Object[]{UserObject.getUserName(r30)})), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
        CheckBoxCell r43 = r5[0];
        if (LocaleController.isRTL == false) goto L64;
        int r85 = AndroidUtilities.dp(r16);
    L66:
        if (LocaleController.isRTL == false) goto L68;
        int r103 = AndroidUtilities.dp(8.0f);
    L69:
        r43.setPadding(r85, 0, r103, 0);
        r5[0].setChecked(true, false);
        r92.addView(r5[0], LayoutHelper.createFrame(-1, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        r5[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda207(r29));
        goto L71
    L68:
        r103 = AndroidUtilities.dp(r16);
        goto L69
    L64:
        r85 = AndroidUtilities.dp(8.0f);
    L71:
        if (UserObject.isReplyUser(r30) == false) goto L73;
        r12.setScaleSize(0.8f);
        r12.setAvatarType(12);
        r13.setImage(null, null, r12, r30);
    L74:
        r6.setPositiveButton(LocaleController.getString(R.string.Start), new AlertsCreator$$ExternalSyntheticLambda208(r31));
        r6.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r1 = r6.create();
        r28.showDialog(r1);
        r7.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.BotWebViewStartPermission2), new AlertsCreator$$ExternalSyntheticLambda209(r1, r3)));
        return;
    L73:
        r12.setScaleSize(1.0f);
        r12.setInfo(r28.getCurrentAccount(), r30);
        r13.setForUserOrChat(r30, r12);
        goto L74
    L58:
        r102 = 3;
        goto L59
    L50:
        r114 = 76;
        goto L51
    L46:
        r113 = 3;
        goto L47
    L42:
        r53 = 21;
        goto L43
    L38:
        r11 = 76;
        goto L39
    L34:
        r83 = 3;
        goto L35
    L30:
        r8 = 3;
        goto L31
    L22:
        if (r30.fake == false) goto L25;
        r142.setRightDrawable(Theme.dialogs_fakeDrawable);
        goto L20
    L25:
        if (r30.verified == false) goto L20;
        r16 = 16.0f;
        Drawable r86 = r3.getResources().getDrawable(R.drawable.verified_area).mutate();
        int r104 = Theme.getColor(Theme.key_chats_verifiedBackground);
        PorterDuff.Mode r116 = PorterDuff.Mode.MULTIPLY;
        r86.setColorFilter(new PorterDuffColorFilter(r104, r116));
        Drawable r105 = r3.getResources().getDrawable(R.drawable.verified_check).mutate();
        r105.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_verifiedCheck), r116));
        r142.setRightDrawable(new CombinedDrawable(r86, r105));
        goto L27
    L16:
        r15 = 3;
        goto L17
    L12:
        r14 = 3;
        goto L13
    L8:
        r9 = 3;
        goto L9
    }

    public static void createBotLaunchAlert(BaseFragment r25, TLRPC.User r26, Runnable r27, Runnable r28) {
        Context r2 = r25.getContext();
        AlertDialog.Builder r3 = new AlertDialog.Builder(r2);
        AnonymousClass5 r4 = new AnonymousClass5(r2);
        NotificationCenter.listenEmojiLoading(r4);
        r4.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r4.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        r4.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L5;
        int r5 = 5;
    L6:
        r4.setGravity(r5 | 48);
        FrameLayout r52 = new FrameLayout(r2);
        r3.setCustomViewOffset(6);
        r3.setView(r52);
        AvatarDrawable r9 = new AvatarDrawable();
        r9.setTextSize(AndroidUtilities.dp(18.0f));
        BackupImageView r10 = new BackupImageView(r2);
        r10.setRoundRadius(AndroidUtilities.dp(20.0f));
        if (LocaleController.isRTL == false) goto L9;
        int r11 = 5;
    L10:
        r52.addView(r10, LayoutHelper.createFrame(40, 40.0f, r11 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        SimpleTextView r112 = new SimpleTextView(r2);
        r112.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r112.setTextSize(20);
        r112.setTypeface(AndroidUtilities.bold());
        if (LocaleController.isRTL == false) goto L13;
        int r12 = 5;
    L14:
        r112.setGravity(r12 | 16);
        r112.setEllipsizeByGradient(true);
        r112.setText(r26.first_name);
        if (r26.scam == false) goto L18;
        r112.setRightDrawable(Theme.dialogs_scamDrawable);
    L23:
        TextView r7 = new TextView(r2);
        r7.setTextColor(Theme.getColor(Theme.key_dialogTextBlue));
        r7.setTextSize(1, 14.0f);
        r7.setLines(1);
        r7.setMaxLines(1);
        r7.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L26;
        int r122 = 5;
    L27:
        r7.setGravity(r122 | 16);
        r7.setEllipsize(TextUtils.TruncateAt.END);
        r7.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda12(r26, r25, r3));
        SpannableString r123 = SpannableString.valueOf(LocaleController.getString(R.string.MoreAboutThisBot) + "  ");
        ColoredImageSpan r13 = new ColoredImageSpan(R.drawable.attach_arrow_right);
        r13.setTopOffset(1);
        r13.setSize(AndroidUtilities.dp(10.0f));
        r123.setSpan(r13, r123.length() - 1, r123.length(), 33);
        r7.setText(r123);
        boolean r6 = LocaleController.isRTL;
        if (r6 == false) goto L30;
        int r124 = 5;
    L31:
        int r20 = r124 | 48;
        int r125 = 76;
        if (r6 == false) goto L34;
        int r14 = 21;
    L35:
        float r142 = r14;
        if (r6 == false) goto L38;
        int r62 = 76;
    L39:
        r52.addView(r112, LayoutHelper.createFrame(-1, -2.0f, r20, r142, 0.0f, r62, 0.0f));
        boolean r63 = LocaleController.isRTL;
        if (r63 == false) goto L42;
        int r113 = 5;
    L43:
        int r202 = r113 | 48;
        if (r63 == false) goto L46;
        int r114 = 21;
    L47:
        float r115 = r114;
        if (r63 == true) goto L51;
        r125 = 21;
    L51:
        r52.addView(r7, LayoutHelper.createFrame(-1, -2.0f, r202, r115, 24.0f, r125, 0.0f));
        if (LocaleController.isRTL == false) goto L54;
        int r72 = 5;
    L55:
        r52.addView(r4, LayoutHelper.createFrame(-2, -2.0f, r72 | 48, 24.0f, 57.0f, 24.0f, 1.0f));
        if (UserObject.isReplyUser(r26) == false) goto L58;
        r9.setScaleSize(0.8f);
        r9.setAvatarType(12);
        r10.setImage(null, null, r9, r26);
    L59:
        r3.setPositiveButton(LocaleController.getString(R.string.Start), new AlertsCreator$$ExternalSyntheticLambda13(r27));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r1 = r3.create();
        r25.showDialog(r1, false, new AlertsCreator$$ExternalSyntheticLambda14(r28));
        r4.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.BotWebViewStartPermission2), new AlertsCreator$$ExternalSyntheticLambda15(r1, r2)));
        return;
    L58:
        r9.setScaleSize(1.0f);
        r9.setInfo(r25.getCurrentAccount(), r26);
        r10.setForUserOrChat(r26, r9);
        goto L59
    L54:
        r72 = 3;
        goto L55
    L46:
        r114 = 76;
        goto L47
    L42:
        r113 = 3;
        goto L43
    L38:
        r62 = 21;
        goto L39
    L34:
        r14 = 76;
        goto L35
    L30:
        r124 = 3;
        goto L31
    L26:
        r122 = 3;
        goto L27
    L18:
        if (r26.fake == false) goto L21;
        r112.setRightDrawable(Theme.dialogs_fakeDrawable);
        goto L23
    L21:
        if (r26.verified == false) goto L23;
        Drawable r126 = r2.getResources().getDrawable(R.drawable.verified_area).mutate();
        int r143 = Theme.getColor(Theme.key_chats_verifiedBackground);
        PorterDuff.Mode r15 = PorterDuff.Mode.MULTIPLY;
        r126.setColorFilter(new PorterDuffColorFilter(r143, r15));
        Drawable r132 = r2.getResources().getDrawable(R.drawable.verified_check).mutate();
        r132.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_verifiedCheck), r15));
        r112.setRightDrawable(new CombinedDrawable(r126, r132));
        goto L23
    L13:
        r12 = 3;
        goto L14
    L9:
        r11 = 3;
        goto L10
    L5:
        r5 = 3;
        goto L6
    }

    public static BottomSheet.Builder createCalendarPickerDialog(Context r25, long r26, MessagesStorage.IntCallback r28, Theme.ResourcesProvider r29) {
        if (r25 != null) goto L6;
        return null;
    L6:
        BottomSheet.Builder r8 = new BottomSheet.Builder(r25, false, r29);
        r8.setApplyBottomPadding(false);
        NumberPicker r3 = new NumberPicker(r25, r29);
        r3.setTextOffset(AndroidUtilities.dp(10.0f));
        r3.setItemCount(5);
        NumberPicker r4 = new NumberPicker(r25, r29);
        r4.setItemCount(5);
        r4.setTextOffset(-AndroidUtilities.dp(10.0f));
        NumberPicker r5 = new NumberPicker(r25, r29);
        r5.setItemCount(5);
        r5.setTextOffset(-AndroidUtilities.dp(24.0f));
        AnonymousClass55 r9 = new AnonymousClass55(r25, r3, r4, r5);
        r9.setOrientation(1);
        FrameLayout r1 = new FrameLayout(r25);
        r9.addView(r1, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r2 = new TextView(r25);
        r2.setText(LocaleController.getString(R.string.ChooseDate));
        r2.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r29));
        r2.setTextSize(1, 20.0f);
        r2.setTypeface(AndroidUtilities.bold());
        r1.addView(r2, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r2.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda86());
        LinearLayout r11 = new LinearLayout(r25);
        r11.setOrientation(0);
        r11.setWeightSum(1.0f);
        r9.addView(r11, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        System.currentTimeMillis();
        AnonymousClass56 r12 = new AnonymousClass56(r25);
        r11.addView(r3, LayoutHelper.createLinear(0, 270, 0.25f));
        r3.setMinValue(1);
        r3.setMaxValue(31);
        r3.setWrapSelectorWheel(false);
        r3.setFormatter(new AlertsCreator$$ExternalSyntheticLambda87());
        AlertsCreator$$ExternalSyntheticLambda88 r0 = new AlertsCreator$$ExternalSyntheticLambda88(r26, r3, r4, r5);
        r3.setOnValueChangedListener(r0);
        r4.setMinValue(0);
        r4.setMaxValue(11);
        r4.setWrapSelectorWheel(false);
        r11.addView(r4, LayoutHelper.createLinear(0, 270, 0.5f));
        r4.setFormatter(new AlertsCreator$$ExternalSyntheticLambda89());
        r4.setOnValueChangedListener(r0);
        Calendar r15 = Calendar.getInstance();
        r15.setTimeInMillis(r26);
        int r13 = r15.get(1);
        r15.setTimeInMillis(System.currentTimeMillis());
        int r7 = r15.get(1);
        r5.setMinValue(r13);
        r5.setMaxValue(r7);
        r5.setWrapSelectorWheel(false);
        r5.setFormatter(new AlertsCreator$$ExternalSyntheticLambda90());
        r11.addView(r5, LayoutHelper.createLinear(0, 270, 0.25f));
        r5.setOnValueChangedListener(r0);
        r3.setValue(31);
        r4.setValue(12);
        r5.setValue(r7);
        checkCalendarDate(r26, r3, r4, r5);
        r12.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r12.setGravity(17);
        r12.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, r29));
        r12.setTextSize(1, 14.0f);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setText(LocaleController.getString(R.string.JumpToDate));
        r12.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r29), Theme.getColor(Theme.key_featuredStickers_addButtonPressed, r29)));
        r9.addView(r12, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r12.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda91(r26, r3, r4, r5, r15, r28, r8));
        r8.setCustomView(r9);
        return r8;
    }

    public static void createCallDialogAlert(BaseFragment r19, TLRPC.User r20, boolean r21) {
        if (r19 != null) goto L5;
        return;
    L5:
        if (r19.getParentActivity() == null) goto L46;
        if (r20 != null) goto L8;
        return;
    L8:
        if (UserObject.isDeleted(r20) == false) goto L10;
        return;
    L10:
        if (UserConfig.getInstance(r19.getCurrentAccount()).getClientUserId() == r20.id) goto L49;
        r19.getCurrentAccount();
        Activity r3 = r19.getParentActivity();
        FrameLayout r4 = new FrameLayout(r3);
        if (r21 == false) goto L15;
        String r5 = LocaleController.getString(R.string.VideoCallAlertTitle);
        String r6 = LocaleController.formatString("VideoCallAlert", R.string.VideoCallAlert, new Object[]{UserObject.getUserName(r20)});
    L16:
        AnonymousClass16 r7 = new AnonymousClass16(r3);
        NotificationCenter.listenEmojiLoading(r7);
        r7.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r7.setTextSize(1, 16.0f);
        int r10 = 3;
        if (LocaleController.isRTL == false) goto L19;
        int r8 = 5;
    L20:
        r7.setGravity(r8 | 48);
        r7.setText(AndroidUtilities.replaceTags(r6));
        AvatarDrawable r62 = new AvatarDrawable();
        r62.setTextSize(AndroidUtilities.dp(12.0f));
        r62.setScaleSize(1.0f);
        r62.setInfo(r19.getCurrentAccount(), r20);
        BackupImageView r82 = new BackupImageView(r3);
        r82.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
        r82.setForUserOrChat(r20, r62);
        if (LocaleController.isRTL == false) goto L23;
        int r63 = 5;
    L24:
        r4.addView(r82, LayoutHelper.createFrame(40, 40.0f, r63 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        TextView r64 = new TextView(r3);
        r64.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r64.setTextSize(1, 20.0f);
        r64.setTypeface(AndroidUtilities.bold());
        r64.setLines(1);
        r64.setMaxLines(1);
        r64.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L27;
        int r83 = 5;
    L28:
        r64.setGravity(r83 | 16);
        r64.setEllipsize(TextUtils.TruncateAt.END);
        r64.setText(r5);
        boolean r52 = LocaleController.isRTL;
        if (r52 == false) goto L31;
        int r84 = 5;
    L32:
        int r14 = r84 | 48;
        int r85 = 76;
        if (r52 == false) goto L35;
        int r12 = 21;
    L36:
        float r15 = r12;
        if (r52 == true) goto L40;
        r85 = 21;
    L40:
        r4.addView(r64, LayoutHelper.createFrame(-1, -2.0f, r14, r15, 11.0f, r85, 0.0f));
        if (LocaleController.isRTL == false) goto L43;
        r10 = 5;
    L43:
        r4.addView(r7, LayoutHelper.createFrame(-2, -2.0f, r10 | 48, 24.0f, 57.0f, 24.0f, 9.0f));
        r19.showDialog(new AlertDialog.Builder(r3).setView(r4).setPositiveButton(LocaleController.getString(R.string.Call), new AlertsCreator$$ExternalSyntheticLambda16(r19, r20, r21)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create());
        return;
    L35:
        r12 = 76;
        goto L36
    L31:
        r84 = 3;
        goto L32
    L27:
        r83 = 3;
        goto L28
    L23:
        r63 = 3;
        goto L24
    L19:
        r8 = 3;
        goto L20
    L15:
        r5 = LocaleController.getString(R.string.CallAlertTitle);
        r6 = LocaleController.formatString("CallAlert", R.string.CallAlert, new Object[]{UserObject.getUserName(r20)});
        goto L16
    L49:
        return;
    }

    public static void createChangeBioAlert(String r18, long r19, Context r21, int r22) {
        AlertDialog.Builder r3 = new AlertDialog.Builder(r21);
        if (r19 <= 0) goto L6;
        int r5 = R.string.UserBio;
    L7:
        r3.setTitle(LocaleController.getString(r5));
        if (r19 <= 0) goto L11;
        int r52 = R.string.VoipGroupBioEditAlertText;
    L12:
        r3.setMessage(LocaleController.getString(r52));
        FrameLayout r53 = new FrameLayout(r21);
        r53.setClipChildren(false);
        if (r19 >= 0) goto L17;
        long r9 = -r19;
        if (MessagesController.getInstance(r22).getChatFull(r9) != null) goto L17;
        MessagesController.getInstance(r22).loadFullChat(r9, ConnectionsManager.generateClassGuid(), true);
    L17:
        NumberTextView r8 = new NumberTextView(r21);
        EditText r92 = new EditText(r21);
        int r10 = Theme.key_voipgroup_actionBarItems;
        r92.setTextColor(Theme.getColor(r10));
        if (r19 <= 0) goto L21;
        int r11 = R.string.UserBio;
    L22:
        r92.setHint(LocaleController.getString(r11));
        r92.setTextSize(1, 16.0f);
        r92.setBackground(Theme.createEditTextDrawable(r21, true));
        r92.setMaxLines(4);
        r92.setRawInputType(147457);
        r92.setImeOptions(6);
        if (r19 <= 0) goto L25;
        int r2 = 70;
    L26:
        r92.setFilters(new InputFilter[]{new AnonymousClass17(r2, r8)});
        r8.setCenterAlign(true);
        r8.setTextSize(15);
        r8.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        r8.setImportantForAccessibility(2);
        if (LocaleController.isRTL == false) goto L30;
        int r4 = 3;
    L31:
        r53.addView(r8, LayoutHelper.createFrame(20, 20.0f, r4, 0.0f, 14.0f, 21.0f, 0.0f));
        if (LocaleController.isRTL == false) goto L34;
        int r42 = AndroidUtilities.dp(24.0f);
    L35:
        int r12 = AndroidUtilities.dp(8.0f);
        if (LocaleController.isRTL == false) goto L38;
        int r7 = 0;
    L39:
        r92.setPadding(r42, r12, r7, AndroidUtilities.dp(8.0f));
        r92.addTextChangedListener(new AnonymousClass18(r2, r8));
        AndroidUtilities.updateViewVisibilityAnimated(r8, false, 0.0f, false);
        r92.setText(r18);
        r92.setSelection(r92.getText().toString().length());
        r3.setView(r53);
        AlertDialog.OnButtonClickListener r23 = new AlertsCreator$$ExternalSyntheticLambda174(r19, r22, r92);
        r3.setPositiveButton(LocaleController.getString(R.string.Save), r23);
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r3.setOnPreDismissListener(new AlertsCreator$$ExternalSyntheticLambda175(r92));
        r53.addView(r92, LayoutHelper.createFrame(-1, -2.0f, 0, 23.0f, 12.0f, 23.0f, 21.0f));
        r92.requestFocus();
        AndroidUtilities.showKeyboard(r92);
        AlertDialog r32 = r3.create();
        r92.setOnEditorActionListener(new AlertsCreator$$ExternalSyntheticLambda176(r19, r32, r23));
        r32.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
        r32.show();
        r32.setTextColor(Theme.getColor(r10));
        return;
    L38:
        r7 = AndroidUtilities.dp(24.0f);
        goto L39
    L34:
        r42 = 0;
        goto L35
    L30:
        r4 = 5;
        goto L31
    L25:
        r2 = 255;
        goto L26
    L21:
        r11 = R.string.DescriptionPlaceholder;
        goto L22
    L11:
        r52 = R.string.DescriptionInfo;
        goto L12
    L6:
        r5 = R.string.DescriptionPlaceholder;
        goto L7
    }

    public static void createChangeNameAlert(long r19, Context r21, int r22) {
        if (DialogObject.isUserDialog(r19) == false) goto L5;
        TLRPC.User r1 = MessagesController.getInstance(r22).getUser(Long.valueOf(r19));
        String r4 = r1.first_name;
        String r12 = r1.last_name;
    L6:
        AlertDialog.Builder r7 = new AlertDialog.Builder(r21);
        if (r19 <= 0) goto L10;
        int r8 = R.string.VoipEditName;
    L11:
        r7.setTitle(LocaleController.getString(r8));
        LinearLayout r82 = new LinearLayout(r21);
        r82.setOrientation(1);
        EditText r10 = new EditText(r21);
        int r11 = Theme.key_voipgroup_actionBarItems;
        r10.setTextColor(Theme.getColor(r11));
        r10.setTextSize(1, 16.0f);
        r10.setMaxLines(1);
        r10.setLines(1);
        r10.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L14;
        int r13 = 5;
    L15:
        r10.setGravity(r13);
        r10.setInputType(49152);
        if (r19 <= 0) goto L18;
        int r15 = 5;
    L19:
        r10.setImeOptions(r15);
        if (r19 <= 0) goto L23;
        int r152 = R.string.FirstName;
    L24:
        r10.setHint(LocaleController.getString(r152));
        r10.setBackground(Theme.createEditTextDrawable(r21, true));
        r10.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        r10.requestFocus();
        if (r19 <= 0) goto L31;
        EditText r5 = new EditText(r21);
        r5.setTextColor(Theme.getColor(r11));
        r5.setTextSize(1, 16.0f);
        r5.setMaxLines(1);
        r5.setLines(1);
        r5.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L29;
        int r6 = 5;
    L30:
        r5.setGravity(r6);
        r5.setInputType(49152);
        r5.setImeOptions(6);
        r5.setHint(LocaleController.getString(R.string.LastName));
        r5.setBackground(Theme.createEditTextDrawable(r21, true));
        r5.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
    L32:
        AndroidUtilities.showKeyboard(r10);
        r82.addView(r10, LayoutHelper.createLinear(-1, -2, 0, 23, 12, 23, 21));
        if (r5 == null) goto L35;
        r82.addView(r5, LayoutHelper.createLinear(-1, -2, 0, 23, 12, 23, 21));
    L35:
        r10.setText(r4);
        r10.setSelection(r10.getText().toString().length());
        if (r5 == null) goto L38;
        r5.setText(r12);
        r5.setSelection(r5.getText().toString().length());
    L38:
        r7.setView(r82);
        AlertDialog.OnButtonClickListener r0 = new AlertsCreator$$ExternalSyntheticLambda169(r10, r19, r22, r5);
        r7.setPositiveButton(LocaleController.getString(R.string.Save), r0);
        r7.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r7.setOnPreDismissListener(new AlertsCreator$$ExternalSyntheticLambda170(r10, r5));
        AlertDialog r2 = r7.create();
        r2.setBackgroundColor(Theme.getColor(Theme.key_voipgroup_dialogBackground));
        r2.show();
        r2.setTextColor(Theme.getColor(r11));
        AlertsCreator$$ExternalSyntheticLambda171 r3 = new AlertsCreator$$ExternalSyntheticLambda171(r2, r0);
        if (r5 == null) goto L42;
        r5.setOnEditorActionListener(r3);
        return;
    L42:
        r10.setOnEditorActionListener(r3);
        return;
    L29:
        r6 = 3;
        goto L30
    L31:
        r5 = null;
        goto L32
    L23:
        r152 = R.string.VoipEditTitleHint;
        goto L24
    L18:
        r15 = 6;
        goto L19
    L14:
        r13 = 3;
        goto L15
    L10:
        r8 = R.string.VoipEditTitle;
        goto L11
    L5:
        r4 = MessagesController.getInstance(r22).getChat(Long.valueOf(-r19)).title;
        r12 = null;
        goto L6
    }

    public static void createClearDaysDialogAlert(BaseFragment r26, int r27, TLRPC.User r28, TLRPC.Chat r29, boolean r30, MessagesStorage.BooleanCallback r31, Theme.ResourcesProvider r32) {
        if (r26 != null) goto L5;
        return;
    L5:
        if (r26.getParentActivity() == null) goto L88;
        if (r28 != null) goto L9;
        if (r29 != null) goto L9;
        return;
    L9:
        int r5 = r26.getCurrentAccount();
        Activity r6 = r26.getParentActivity();
        AlertDialog.Builder r7 = new AlertDialog.Builder(r6, r32);
        long r8 = UserConfig.getInstance(r5).getClientUserId();
        CheckBoxCell[] r10 = new CheckBoxCell[1];
        AnonymousClass14 r11 = new AnonymousClass14(r6);
        NotificationCenter.listenEmojiLoading(r11);
        r11.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r11.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L12;
        int r13 = 5;
    L13:
        r11.setGravity(r13 | 48);
        AnonymousClass15 r132 = new AnonymousClass15(r6, r10);
        r7.setView(r132);
        TextView r12 = new TextView(r6);
        r12.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r12.setTextSize(1, 20.0f);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setLines(1);
        r12.setMaxLines(1);
        r12.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L16;
        int r14 = 5;
    L17:
        r12.setGravity(r14 | 16);
        r12.setEllipsize(TextUtils.TruncateAt.END);
        if (LocaleController.isRTL == false) goto L20;
        int r142 = 5;
    L21:
        r132.addView(r12, LayoutHelper.createFrame(-1, -2.0f, r142 | 48, 24.0f, 11.0f, 24.0f, 0.0f));
        if (LocaleController.isRTL == false) goto L24;
        int r143 = 5;
    L25:
        r132.addView(r11, LayoutHelper.createFrame(-2, -2.0f, r143 | 48, 24.0f, 48.0f, 24.0f, 18.0f));
        if (r27 != (-1)) goto L40;
        r12.setText(LocaleController.formatString("ClearHistory", R.string.ClearHistory, new Object[0]));
        if (r28 == null) goto L31;
        r11.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureClearHistoryWithUser", R.string.AreYouSureClearHistoryWithUser, new Object[]{UserObject.getUserName(r28)})));
    L41:
        boolean[] r52 = {false};
        if (r29 == null) goto L47;
        if (r30 == false) goto L47;
        if (ChatObject.isPublic(r29) == false) goto L47;
        r52[0] = true;
    L47:
        if (r28 == null) goto L54;
        long r112 = r28.id;
        if (r112 == r8) goto L54;
        if (r112 == UserObject.VERIFY) goto L54;
        if (ChatObject.isMonoForum(r29) == true) goto L54;
    L62:
        CheckBoxCell r1 = new CheckBoxCell(r6, 1, r32);
        r10[0] = r1;
        r1.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (r29 == null) goto L65;
        r10[0].setText(LocaleController.getString(R.string.DeleteMessagesOptionAlsoChat), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L66:
        CheckBoxCell r15 = r10[0];
        if (LocaleController.isRTL == false) goto L69;
        int r2 = AndroidUtilities.dp(16.0f);
    L71:
        if (LocaleController.isRTL == false) goto L73;
        int r4 = AndroidUtilities.dp(8.0f);
    L74:
        r15.setPadding(r2, 0, r4, 0);
        r132.addView(r10[0], LayoutHelper.createFrame(-1, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        r10[0].setChecked(false, false);
        r10[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda54(r52));
    L75:
        CharSequence r16 = LocaleController.getString(R.string.Delete);
        if (r29 == null) goto L83;
        if (r30 == false) goto L83;
        if (ChatObject.isPublic(r29) == false) goto L83;
        if (ChatObject.isChannelAndNotMegaGroup(r29) == true) goto L83;
        r16 = LocaleController.getString(R.string.ClearForAll);
    L83:
        r7.setPositiveButton(r16, new AlertsCreator$$ExternalSyntheticLambda55(r31, r52));
        r7.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r17 = r7.create();
        r26.showDialog(r17);
        TextView r0 = (TextView) r17.getButton(-1);
        if (r0 == null) goto L89;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L89:
        return;
    L73:
        r4 = AndroidUtilities.dp(16.0f);
        goto L74
    L69:
        r2 = AndroidUtilities.dp(8.0f);
        goto L71
    L65:
        r10[0].setText(LocaleController.formatString("DeleteMessagesOptionAlso", R.string.DeleteMessagesOptionAlso, new Object[]{UserObject.getFirstName(r28)}), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L54:
        if (r29 == null) goto L75;
        if (r30 == false) goto L75;
        if (ChatObject.isPublic(r29) == true) goto L75;
        if (ChatObject.isChannelAndNotMegaGroup(r29) == true) goto L75;
        if (ChatObject.isMonoForum(r29) == true) goto L75;
    L31:
        if (r30 == false) goto L37;
        if (ChatObject.isChannelAndNotMegaGroup(r29) == false) goto L35;
        r11.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureClearHistoryWithChannel", R.string.AreYouSureClearHistoryWithChannel, new Object[]{r29.title})));
        goto L41
    L35:
        r11.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureClearHistoryWithChat", R.string.AreYouSureClearHistoryWithChat, new Object[]{r29.title})));
        goto L41
    L37:
        if (r29.megagroup == false) goto L39;
        r11.setText(LocaleController.getString(R.string.AreYouSureClearHistoryGroup));
        goto L41
    L39:
        r11.setText(LocaleController.getString(R.string.AreYouSureClearHistoryChannel));
        goto L41
    L40:
        r12.setText(LocaleController.formatPluralString("DeleteDays", r27, new Object[0]));
        r11.setText(LocaleController.getString(R.string.DeleteHistoryByDaysMessage));
        goto L41
    L24:
        r143 = 3;
        goto L25
    L20:
        r142 = 3;
        goto L21
    L16:
        r14 = 3;
        goto L17
    L12:
        r13 = 3;
        goto L13
    }

    public static void createClearOrDeleteDialogAlert(BaseFragment r12, boolean r13, TLRPC.Chat r14, TLRPC.User r15, boolean r16, boolean r17, boolean r18, boolean r19, MessagesStorage.BooleanCallback r20) {
        if (r12 == null) goto L5;
        Theme.ResourcesProvider r0 = r12.getResourceProvider();
    L6:
        createClearOrDeleteDialogAlert(r12, r13, false, r14, r15, r16, r17, r18, r19, r20, r0);
        return;
    L5:
        r0 = null;
        goto L6
    }

    public static void createClearOrDeleteDialogAlert(BaseFragment r38, boolean r39, boolean r40, TLRPC.Chat r41, TLRPC.User r42, boolean r43, boolean r44, boolean r45, boolean r46, MessagesStorage.BooleanCallback r47, Theme.ResourcesProvider r48) {
        if (r38 != null) goto L5;
        return;
    L5:
        if (r38.getParentActivity() == null) goto L266;
        if (r41 != null) goto L9;
        if (r42 != null) goto L9;
        return;
    L9:
        int r14 = r38.getCurrentAccount();
        Activity r15 = r38.getParentActivity();
        AlertDialog.Builder r13 = new AlertDialog.Builder(r15, r48);
        long r0 = UserConfig.getInstance(r14).getClientUserId();
        CheckBoxCell[] r3 = new CheckBoxCell[1];
        AnonymousClass10 r5 = new AnonymousClass10(r15);
        NotificationCenter.listenEmojiLoading(r5);
        r5.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r5.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L12;
        int r8 = 5;
    L13:
        r5.setGravity(r8 | 48);
        if (r46 == false) goto L16;
    L20:
        long r16 = r0;
        boolean r1 = false;
    L21:
        AnonymousClass11 r02 = new AnonymousClass11(r15, r3);
        r13.setCustomViewOffset(6);
        r13.setView(r02);
        AvatarDrawable r11 = new AvatarDrawable();
        r11.setTextSize(AndroidUtilities.dp(18.0f));
        BackupImageView r6 = new BackupImageView(r15);
        r6.setRoundRadius(ExteraConfig.getAvatarCorners(30.0f));
        if (LocaleController.isRTL == false) goto L24;
        int r9 = 5;
    L25:
        r02.addView(r6, LayoutHelper.createFrame(30, 30.0f, r9 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        AnimatedTextView r92 = new AnimatedTextView(r15);
        r92.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r92.setTextSize(AndroidUtilities.dp(20.0f));
        r92.setTypeface(AndroidUtilities.bold());
        if (LocaleController.isRTL == false) goto L28;
        int r10 = 5;
    L29:
        r92.setGravity(r10 | 16);
        r92.setEllipsizeByGradient(true);
        if (r39 == false) goto L34;
        if (r1 == false) goto L33;
        r92.setText(LocaleController.getString(R.string.ClearHistoryCache));
    L49:
        boolean r102 = LocaleController.isRTL;
        if (r102 == false) goto L52;
        int r18 = 5;
    L53:
        int r24 = r18 | 48;
        if (r102 == false) goto L56;
        int r82 = 22;
    L57:
        float r83 = r82;
        if (r102 == false) goto L60;
        int r103 = 65;
    L61:
        r02.addView(r92, LayoutHelper.createFrame(-1, 24.0f, r24, r83, 7.66f, r103, 0.0f));
        if (LocaleController.isRTL == false) goto L64;
        int r93 = 5;
    L65:
        r02.addView(r5, LayoutHelper.createFrame(-2, -2.0f, r93 | 48, 24.0f, 49.0f, 24.0f, 1.0f));
        if (ChatObject.isMonoForum(r41) == false) goto L68;
        TLRPC.Chat r84 = r38.getMessagesController().getMonoForumLinkedChat(r41.id);
    L69:
        if (r42 != null) goto L71;
    L77:
        boolean r94 = false;
    L78:
        if (r42 == null) goto L80;
        int r104 = MessagesController.getInstance(r14).revokeTimePmLimit;
    L81:
        if (r43 == true) goto L87;
        if (r42 == null) goto L87;
        if (r94 == false) goto L87;
        if (r104 != Integer.MAX_VALUE) goto L87;
        boolean r95 = true;
    L88:
        boolean[] r105 = new boolean[1];
        if (r42 == null) goto L91;
        TLRPC.Chat r21 = r84;
        boolean r22 = r95;
        ArrayList<MessageObject> r2 = MessagesController.getInstance(r14).dialogMessage.get(r42.id);
    L92:
        if (r2 != null) goto L94;
    L104:
        boolean r23 = false;
    L106:
        if (r42 == null) goto L123;
        float r242 = 8.0f;
        if (r42.bot == false) goto L122;
        boolean r96 = r1;
        boolean r25 = r23;
        if (r42.id == UserObject.VERIFY) goto L121;
        CheckBoxCell r12 = new CheckBoxCell(r15, 1, r48);
        r3[0] = r12;
        r12.setBackground(Theme.getSelectorDrawable(false));
        r3[0].setText(LocaleController.getString(R.string.BlockBot), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        CheckBoxCell r17 = r3[0];
        if (LocaleController.isRTL == false) goto L114;
        int r26 = AndroidUtilities.dp(16.0f);
    L115:
        int r32 = AndroidUtilities.dp(10.0f);
        if (LocaleController.isRTL == false) goto L119;
        int r85 = AndroidUtilities.dp(8.0f);
    L118:
        boolean r27 = r96;
        r17.setPadding(r26, r32, r85, AndroidUtilities.dp(10.0f));
        CheckBoxCell r19 = r3[0];
        r105[0] = true;
        r19.setChecked(true, false);
        r3[0].setMultiline(true);
        r02.addView(r3[0], LayoutHelper.createFrame(-1, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        r3[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda50(r105));
        boolean r86 = false;
    L164:
        if (r42 != null) goto L166;
        Object r03 = null;
        int r110 = r38.getCurrentAccount();
        if (r21 == null) goto L175;
        TLRPC.Chat r28 = r21;
    L176:
        r11.setInfo(r110, r28);
        if (r21 == null) goto L179;
        TLRPC.Chat r111 = r21;
    L180:
        r6.setForUserOrChat(r111, r11);
    L181:
        if (r40 == true) goto L183;
        if (r39 == false) goto L213;
        if (r42 == null) goto L198;
        if (r43 == false) goto L195;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureClearHistoryWithSecretUser, new Object[]{UserObject.getUserName(r42)})));
    L236:
        if (r40 == false) goto L239;
        CharSequence r112 = LocaleController.getString(R.string.DeleteAll);
    L261:
        r13.setPositiveButton(r112, new AlertsCreator$$ExternalSyntheticLambda53(r27, r40, r43, r42, r38, r39, r41, r44, r105, r46, r47, r48, r13, r14, r15));
        r13.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r04 = r13.create();
        r38.showDialog(r04);
        TextView r05 = (TextView) r04.getButton(-1);
        if (r05 == null) goto L267;
        r05.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L267:
        return;
    L239:
        if (r39 == false) goto L243;
        if (r27 == false) goto L242;
        r112 = LocaleController.getString(R.string.ClearHistoryCache);
        goto L261
    L242:
        r112 = LocaleController.getString(R.string.ClearForMe);
        goto L261
    L243:
        if (r86 == false) goto L252;
        if (r105[0] == false) goto L252;
        if (ChatObject.isChannelAndNotMegaGroup(r41) == false) goto L249;
        int r113 = R.string.ChannelDelete;
    L250:
        r112 = LocaleController.getString(r113);
        goto L261
    L249:
        r113 = R.string.DeleteMega;
    L252:
        if (ChatObject.isChannel(r41) == true) goto L254;
        r112 = LocaleController.getString(R.string.DeleteChatUser);
        goto L261
    L254:
        if (r41.monoforum == false) goto L257;
        r112 = LocaleController.getString(R.string.LeaveConversationMenu);
        goto L261
    L257:
        if (r41.megagroup == false) goto L259;
        r112 = LocaleController.getString(R.string.LeaveMegaMenu);
        goto L261
    L259:
        r112 = LocaleController.getString(R.string.LeaveChannelMenu);
        goto L261
    L195:
        if (r42.id != r16) goto L197;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AreYouSureClearHistorySavedMessages)));
        goto L236
    L197:
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureClearHistoryWithUser, new Object[]{UserObject.getUserName(r42)})));
        goto L236
    L198:
        if (r21 == null) goto L200;
        String r114 = LocaleController.formatString(R.string.MonoforumTitle, new Object[]{r21.title});
    L202:
        if (ChatObject.isChannel(r41) == true) goto L204;
    L212:
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureClearHistoryWithChat, new Object[]{r114})));
        goto L236
    L204:
        if (r41.megagroup == false) goto L209;
        if (ChatObject.isPublic(r41) == false) goto L212;
    L209:
        if (r41.megagroup == false) goto L211;
        r5.setText(LocaleController.getString(R.string.AreYouSureClearHistoryGroup));
        goto L236
    L211:
        r5.setText(LocaleController.getString(R.string.AreYouSureClearHistoryChannel));
        goto L236
    L200:
        r114 = r41.title;
        goto L202
    L213:
        TLRPC.Chat r29 = r21;
        if (r42 == null) goto L227;
        if (r43 == false) goto L218;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureDeleteThisChatWithSecretUser, new Object[]{UserObject.getUserName(r42)})));
        goto L236
    L218:
        if (r42.id != r16) goto L221;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.AreYouSureDeleteThisChatSavedMessages)));
        goto L236
    L221:
        if (r42.bot == true) goto L223;
    L225:
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureDeleteThisChatWithUser, new Object[]{UserObject.getUserName(r42)})));
        goto L236
    L223:
        if (r42.support == true) goto L225;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureDeleteThisChatWithBotWithCheckmark, new Object[]{UserObject.getUserName(r42)})));
        goto L236
    L227:
        if (ChatObject.isChannel(r41) == false) goto L235;
        if (r29 == null) goto L230;
        String r115 = LocaleController.formatString(R.string.MonoforumTitle, new Object[]{r29.title});
    L232:
        if (r41.megagroup == false) goto L234;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.MegaLeaveAlertWithName, new Object[]{r115})));
        goto L236
    L234:
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.ChannelLeaveAlertWithName, new Object[]{r115})));
        goto L236
    L230:
        r115 = r41.title;
        goto L232
    L235:
        r5.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AreYouSureDeleteAndExitName, new Object[]{r41.title})));
        goto L236
    L183:
        if (UserObject.isUserSelf(r42) == false) goto L185;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.DeleteAllMessagesSavedAlert)));
        goto L236
    L185:
        if (r41 != null) goto L187;
    L189:
        r5.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.DeleteAllMessagesAlert)));
        goto L236
    L187:
        if (ChatObject.isChannelAndNotMegaGroup(r41) == false) goto L189;
        r5.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.DeleteAllMessagesChannelAlert)));
        goto L236
    L179:
        r111 = r41;
        goto L180
    L175:
        r28 = r41;
        goto L176
    L166:
        if (UserObject.isReplyUser(r42) == false) goto L168;
        r11.setScaleSize(0.8f);
        r11.setAvatarType(12);
        r03 = null;
        r6.setImage(null, null, r11, r42);
        goto L181
    L168:
        r03 = null;
        if (r42.id != r16) goto L171;
        r11.setScaleSize(0.8f);
        r11.setAvatarType(1);
        r6.setImage(null, null, r11, r42);
        goto L181
    L171:
        r11.setScaleSize(1.0f);
        r11.setInfo(r38.getCurrentAccount(), r42);
        r6.setForUserOrChat(r42, r11);
        goto L181
    L119:
        r85 = AndroidUtilities.dp(16.0f);
        goto L118
    L114:
        r26 = AndroidUtilities.dp(8.0f);
        goto L115
    L121:
        CheckBoxCell[] r262 = r3;
        r27 = r96;
    L124:
        if (r40 == true) goto L133;
        if (r43 == false) goto L127;
        if (r39 == true) goto L127;
    L129:
        if (UserObject.isDeleted(r42) == true) goto L133;
        if (r25 == true) goto L133;
        boolean r97 = false;
    L141:
        CheckBoxCell r116 = new CheckBoxCell(r15, 1, r48);
        r262[0] = r116;
        r116.setBackground(Theme.getSelectorDrawable(false));
        if (r97 == true) goto L144;
        if (r39 == false) goto L151;
        r262[0].setText(LocaleController.formatString(R.string.ClearHistoryOptionAlso, new Object[]{UserObject.getFirstName(r42)}), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L152:
        r262[0].setMultiline(true);
        CheckBoxCell r117 = r262[0];
        if (LocaleController.isRTL == false) goto L155;
        int r210 = AndroidUtilities.dp(16.0f);
    L156:
        int r33 = AndroidUtilities.dp(10.0f);
        if (LocaleController.isRTL == false) goto L159;
        int r87 = AndroidUtilities.dp(r242);
    L160:
        r117.setPadding(r210, r33, r87, AndroidUtilities.dp(10.0f));
        r02.addView(r262[0], LayoutHelper.createFrame(-1, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        AlertsCreator$$ExternalSyntheticLambda51 r06 = new AlertsCreator$$ExternalSyntheticLambda51(r97, r41, r13, r105);
        r262[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda52(r105, r06));
        if (r45 == false) goto L163;
        CheckBoxCell r118 = r262[0];
        r105[0] = true;
        r118.setChecked(true, false);
        r06.run();
    L163:
        r86 = r97;
        goto L164
    L159:
        r87 = AndroidUtilities.dp(16.0f);
        goto L160
    L155:
        r210 = AndroidUtilities.dp(r242);
        goto L156
    L151:
        r262[0].setText(LocaleController.formatString(R.string.DeleteMessagesOptionAlso, new Object[]{UserObject.getFirstName(r42)}), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        goto L152
    L144:
        if (ChatObject.isChannel(r41) == true) goto L146;
    L148:
        r262[0].setText(LocaleController.getString(R.string.DeleteGroupForAll), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        goto L152
    L146:
        if (r41.megagroup == true) goto L148;
        r262[0].setText(LocaleController.getString(R.string.DeleteChannelForAll), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L127:
        if (r22 == true) goto L129;
    L133:
        if (r44 == false) goto L139;
        if (r39 == true) goto L139;
        if (r41 == null) goto L139;
        if (r41.creator == false) goto L139;
        r97 = true;
    L140:
        if (r97 == false) goto L163;
    L139:
        r97 = false;
        goto L140
    L122:
        r27 = r1;
        r25 = r23;
        r262 = r3;
        goto L124
    L123:
        r27 = r1;
        r25 = r23;
        r262 = r3;
        r242 = 8.0f;
        goto L124
    L94:
        if (r2.size() != 1) goto L104;
        if (r2.get(0) == null) goto L104;
        if (r2.get(0).messageOwner == null) goto L104;
        if ((r2.get(0).messageOwner.action instanceof TLRPC.TL_messageActionUserJoined) == false) goto L102;
    L103:
        r23 = true;
        goto L106
    L102:
        if ((r2.get(0).messageOwner.action instanceof TLRPC.TL_messageActionContactSignUp) == false) goto L104;
    L91:
        r21 = r84;
        r22 = r95;
        r2 = null;
    L87:
        r95 = false;
        goto L88
    L80:
        r104 = MessagesController.getInstance(r14).revokeTimeLimit;
        goto L81
    L71:
        if (r42.bot == true) goto L77;
        if (r42.id == r16) goto L77;
        if (MessagesController.getInstance(r14).canRevokePmInbox == false) goto L77;
        r94 = true;
        goto L78
    L68:
        r84 = null;
        goto L69
    L64:
        r93 = 3;
        goto L65
    L60:
        r103 = 22;
        goto L61
    L56:
        r82 = 65;
        goto L57
    L52:
        r18 = 3;
        goto L53
    L33:
        r92.setText(LocaleController.getString(R.string.ClearHistory));
        goto L49
    L34:
        if (r41 != null) goto L36;
        r92.setText(LocaleController.getString(R.string.DeleteChatUser));
        goto L49
    L36:
        if (ChatObject.isCommunity(r41) == false) goto L39;
        r92.setText(LocaleController.getString(R.string.CommunityDelete));
        goto L49
    L39:
        if (ChatObject.isChannel(r41) == true) goto L41;
        r92.setText(LocaleController.getString(R.string.LeaveMega));
        goto L49
    L41:
        if (r41.monoforum == false) goto L44;
        r92.setText(LocaleController.getString(R.string.LeaveConversationMenu));
        goto L49
    L44:
        if (r41.megagroup == false) goto L46;
        r92.setText(LocaleController.getString(R.string.LeaveMega));
        goto L49
    L46:
        r92.setText(LocaleController.getString(R.string.LeaveChannel));
        goto L49
    L28:
        r10 = 3;
        goto L29
    L24:
        r9 = 3;
        goto L25
    L16:
        if (ChatObject.isChannel(r41) == false) goto L20;
        if (ChatObject.isPublic(r41) == false) goto L20;
        r16 = r0;
        r1 = true;
        goto L21
    L12:
        r8 = 3;
        goto L13
    }

    public static void createClearOrDeleteDialogsAlert(BaseFragment r21, boolean r22, boolean r23, int r24, int r25, boolean r26, MessagesStorage.BooleanCallback r27, Theme.ResourcesProvider r28) {
        int r2 = r21.getCurrentAccount();
        Activity r3 = r21.getParentActivity();
        AlertDialog.Builder r4 = new AlertDialog.Builder(r3, r28);
        UserConfig.getInstance(r2).getClientUserId();
        CheckBoxCell[] r5 = new CheckBoxCell[1];
        boolean[] r6 = new boolean[1];
        TextView r7 = new TextView(r3);
        r7.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r7.setTextSize(1, 16.0f);
        int r10 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r9 = 5;
    L6:
        r7.setGravity(r9 | 48);
        AnonymousClass13 r92 = new AnonymousClass13(r3, r5);
        r4.setCustomViewOffset(6);
        r4.setView(r92);
        TextView r12 = new TextView(r3);
        r12.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r12.setTextSize(1, 20.0f);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setLines(1);
        r12.setMaxLines(1);
        r12.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L9;
        int r13 = 5;
    L10:
        r12.setGravity(r13 | 16);
        r12.setEllipsize(TextUtils.TruncateAt.END);
        if (r23 == false) goto L23;
        if (r26 == false) goto L22;
        CheckBoxCell r14 = new CheckBoxCell(r3, 1, r28);
        r5[0] = r14;
        r14.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r5[0].setText(LocaleController.getString(R.string.DeleteMessagesForBothSidesWherePossible), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        CheckBoxCell r1 = r5[0];
        if (LocaleController.isRTL == false) goto L16;
        int r29 = AndroidUtilities.dp(16.0f);
    L18:
        if (LocaleController.isRTL == false) goto L20;
        int r32 = AndroidUtilities.dp(8.0f);
    L21:
        r1.setPadding(r29, 0, r32, 0);
        r92.addView(r5[0], LayoutHelper.createFrame(-1, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        r5[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda132(r6));
        goto L22
    L20:
        r32 = AndroidUtilities.dp(16.0f);
        goto L21
    L16:
        r29 = AndroidUtilities.dp(8.0f);
    L22:
        r12.setText(LocaleController.formatString("DeleteFewChatsTitle", R.string.DeleteFewChatsTitle, new Object[]{LocaleController.formatPluralString("ChatsSelected", r25, new Object[0])}));
        r7.setText(LocaleController.getString("AreYouSureDeleteFewChats", R.string.AreYouSureDeleteFewChats));
    L27:
        if (LocaleController.isRTL == false) goto L29;
        int r0 = 5;
    L30:
        r92.addView(r12, LayoutHelper.createFrame(-1, -2.0f, r0 | 48, 24.0f, 11.0f, 24.0f, 0.0f));
        if (LocaleController.isRTL == false) goto L33;
        r10 = 5;
    L33:
        r92.addView(r7, LayoutHelper.createFrame(-2, -2.0f, r10 | 48, 24.0f, 57.0f, 24.0f, 1.0f));
        if (r23 == false) goto L36;
        CharSequence r02 = LocaleController.getString("Delete", R.string.Delete);
    L39:
        r4.setPositiveButton(r02, new AlertsCreator$$ExternalSyntheticLambda133(r27, r6));
        r4.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        AlertDialog r03 = r4.create();
        r21.showDialog(r03);
        TextView r04 = (TextView) r03.getButton(-1);
        if (r04 == null) goto L43;
        r04.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L43:
        return;
    L36:
        if (r24 == 0) goto L38;
        r02 = LocaleController.getString("ClearHistoryCache", R.string.ClearHistoryCache);
        goto L39
    L38:
        r02 = LocaleController.getString("ClearHistory", R.string.ClearHistory);
        goto L39
    L29:
        r0 = 3;
        goto L30
    L23:
        if (r24 == 0) goto L25;
        r12.setText(LocaleController.formatString("ClearCacheFewChatsTitle", R.string.ClearCacheFewChatsTitle, new Object[]{LocaleController.formatPluralString("ChatsSelectedClearCache", r25, new Object[0])}));
        r7.setText(LocaleController.getString("AreYouSureClearHistoryCacheFewChats", R.string.AreYouSureClearHistoryCacheFewChats));
        goto L27
    L25:
        r12.setText(LocaleController.formatString("ClearFewChatsTitle", R.string.ClearFewChatsTitle, new Object[]{LocaleController.formatPluralString("ChatsSelectedClear", r25, new Object[0])}));
        r7.setText(LocaleController.getString("AreYouSureClearHistoryFewChats", R.string.AreYouSureClearHistoryFewChats));
        goto L27
    L9:
        r13 = 3;
        goto L10
    L5:
        r9 = 3;
        goto L6
    }

    public static Dialog createColorSelectDialog(Activity r8, long r9, int r11, int r12, Runnable r13) {
        return createColorSelectDialog(r8, r9, r11, r12, r13, null);
    }

    public static Dialog createColorSelectDialog(Activity r19, long r20, long r22, int r24, Runnable r25, Theme.ResourcesProvider r26) {
        SharedPreferences r2 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
        String r3 = NotificationsController.getSharedPrefKey(r20, r22);
        if (r20 != 0) goto L5;
        if (r24 != 1) goto L13;
        int r23 = r2.getInt("MessagesLed", -16776961);
    L25:
        LinearLayout r4 = new LinearLayout(r19);
        r4.setOrientation(1);
        String[] r5 = {LocaleController.getString(R.string.ColorRed), LocaleController.getString(R.string.ColorOrange), LocaleController.getString(R.string.ColorYellow), LocaleController.getString(R.string.ColorGreen), LocaleController.getString(R.string.ColorCyan), LocaleController.getString(R.string.ColorBlue), LocaleController.getString(R.string.ColorViolet), LocaleController.getString(R.string.ColorPink), LocaleController.getString(R.string.ColorWhite)};
        int[] r8 = {r23};
        int r11 = 0;
    L27:
        if (r11 >= 9) goto L33;
        RadioColorCell r12 = new RadioColorCell(r19, r26);
        r12.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r12.setTag(Integer.valueOf(r11));
        int r13 = TextColorCell.colors[r11];
        r12.setCheckColor(r13, r13);
        String r132 = r5[r11];
        if (r23 != TextColorCell.colorsToSave[r11]) goto L31;
        boolean r14 = true;
    L32:
        r12.setTextAndValue(r132, r14);
        r4.addView(r12);
        r12.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda145(r4, r8));
        r11 = r11 + 1;
        goto L27
    L31:
        r14 = false;
        goto L32
    L33:
        AlertDialog.Builder r10 = new AlertDialog.Builder(r19, r26);
        r10.setTitle(LocaleController.getString(R.string.LedColor));
        r10.setView(r4);
        r10.setPositiveButton(LocaleController.getString(R.string.Set), new AlertsCreator$$ExternalSyntheticLambda146(r20, r3, r8, r22, r24, r25));
        r10.setNeutralButton(LocaleController.getString(R.string.LedDisabled), new AlertsCreator$$ExternalSyntheticLambda147(r20, r24, r25));
        if (r20 == 0) goto L37;
        r10.setNegativeButton(LocaleController.getString(R.string.Default), new AlertsCreator$$ExternalSyntheticLambda148(r3, r25));
    L37:
        return r10.create();
    L13:
        if (r24 != 0) goto L16;
        r23 = r2.getInt("GroupLed", -16776961);
        goto L25
    L16:
        if (r24 != 3) goto L19;
        r23 = r2.getInt("StoriesLed", -16776961);
        goto L25
    L19:
        if (r24 != 5) goto L21;
    L24:
        r23 = r2.getInt("ReactionsLed", -16776961);
        goto L25
    L21:
        if (r24 == 4) goto L24;
        r23 = r2.getInt("ChannelLed", -16776961);
        goto L25
    L5:
        if (r2.contains("color_" + r3) == false) goto L8;
        r23 = r2.getInt("color_" + r3, -16776961);
        goto L25
    L8:
        if (DialogObject.isChatDialog(r20) == false) goto L10;
        r23 = r2.getInt("GroupLed", -16776961);
        goto L25
    L10:
        r23 = r2.getInt("MessagesLed", -16776961);
        goto L25
    }

    public static void createContactInviteDialog(BaseFragment r2, String r3, String r4, String r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r2.getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.ContactNotRegisteredTitle));
        r0.setMessage(LocaleController.formatString("ContactNotRegistered", R.string.ContactNotRegistered, new Object[]{ContactsController.formatName(r3, r4)}));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.Invite), new AlertsCreator$$ExternalSyntheticLambda159(r5, r2));
        r2.showDialog(r0.create());
    }

    public static AlertDialog.Builder createContactsPermissionDialog(Activity r4, MessagesStorage.IntCallback r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r4);
        r0.setTopAnimation(R.raw.permission_request_contacts, 72, false, Theme.getColor(Theme.key_dialogTopBackground));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ContactsPermissionAlert)));
        r0.setPositiveButton(LocaleController.getString(R.string.ContactsPermissionAlertContinue), new AlertsCreator$$ExternalSyntheticLambda10(r5));
        r0.setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), new AlertsCreator$$ExternalSyntheticLambda11(r5));
        return r0;
    }

    public static BottomSheet createCustomPicker(Context r19, String r20, int r21, String[] r22, Utilities.Callback<Integer> r23) {
        if (TimezonesController.getInstance(UserConfig.selectedAccount).getTimezones().isEmpty() == false) goto L5;
        return null;
    L5:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(null);
        BottomSheet.Builder r4 = new BottomSheet.Builder(r19, false, null);
        r4.setApplyBottomPadding(false);
        LinearLayout r6 = new LinearLayout(r19);
        r6.setOrientation(0);
        r6.setWeightSum(1.0f);
        NumberPicker r8 = new NumberPicker(r19);
        r8.setAllItemsCount(r22.length);
        r8.setItemCount(Math.min(r22.length, 8));
        r8.setTextColor(r2.textColor);
        r8.setGravity(17);
        r8.setMinValue(0);
        r8.setMaxValue(r22.length - 1);
        r8.setValue(r21);
        r6.addView(r8, LayoutHelper.createLinear(0, 432, 1.0f));
        r8.setFormatter(new AlertsCreator$$ExternalSyntheticLambda262(r22));
        AnonymousClass69 r1 = new AnonymousClass69(r19, r8);
        r1.setOrientation(1);
        FrameLayout r7 = new FrameLayout(r19);
        TextView r9 = new TextView(r19);
        r9.setText(r20);
        r9.setTextColor(r2.textColor);
        r9.setTextSize(1, 20.0f);
        r9.setTypeface(AndroidUtilities.bold());
        r7.addView(r9, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r9.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda263());
        r1.addView(r7, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        r1.addView(r6, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        ButtonWithCounterView r72 = new ButtonWithCounterView(r19, null);
        r72.setText(LocaleController.getString(R.string.Select), false);
        r72.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda264(r6));
        r1.addView(r72, LayoutHelper.createLinear(-1, 48, 0, 16, 12, 16, 12));
        r4.setCustomView(r1);
        BottomSheet r0 = r4.show();
        r0.setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda265(r23, r8));
        r0.setBackgroundColor(r2.backgroundColor);
        r0.fixNavigationBar(r2.backgroundColor);
        BottomSheet r02 = r4.create();
        BottomSheet[] r62 = {r02};
        return r02;
    }

    public static AlertDialog.Builder createDatePickerDialog(Context r12, int r13, int r14, int r15, int r16, int r17, int r18, String r19, boolean r20, DatePickerDelegate r21) {
        if (r12 != null) goto L5;
        return null;
    L5:
        LinearLayout r3 = new LinearLayout(r12);
        r3.setOrientation(0);
        r3.setWeightSum(1.0f);
        NumberPicker r5 = new NumberPicker(r12);
        NumberPicker r6 = new NumberPicker(r12);
        NumberPicker r7 = new NumberPicker(r12);
        r3.addView(r6, LayoutHelper.createLinear(0, -2, 0.3f));
        r6.setOnScrollListener(new AlertsCreator$$ExternalSyntheticLambda181(r20, r6, r5, r7));
        r5.setMinValue(0);
        r5.setMaxValue(11);
        r3.addView(r5, LayoutHelper.createLinear(0, -2, 0.3f));
        r5.setFormatter(new AlertsCreator$$ExternalSyntheticLambda182());
        r5.setOnValueChangedListener(new AlertsCreator$$ExternalSyntheticLambda183(r6, r5, r7));
        r5.setOnScrollListener(new AlertsCreator$$ExternalSyntheticLambda184(r20, r6, r5, r7));
        Calendar r9 = Calendar.getInstance();
        r9.setTimeInMillis(System.currentTimeMillis());
        int r92 = r9.get(1);
        r7.setMinValue(r13 + r92);
        r7.setMaxValue(r14 + r92);
        r7.setValue(r92 + r15);
        r3.addView(r7, LayoutHelper.createLinear(0, -2, 0.4f));
        r7.setOnValueChangedListener(new AlertsCreator$$ExternalSyntheticLambda185(r6, r5, r7));
        r7.setOnScrollListener(new AlertsCreator$$ExternalSyntheticLambda186(r20, r6, r5, r7));
        updateDayPicker(r6, r5, r7);
        if (r20 == false) goto L9;
        checkPickerDate(r6, r5, r7);
    L9:
        if (r16 == (-1)) goto L11;
        r6.setValue(r16);
        r5.setValue(r17);
        r7.setValue(r18);
    L11:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r12);
        r0.setTitle(r19);
        r0.setView(r3);
        r0.setPositiveButton(LocaleController.getString(R.string.Set), new AlertsCreator$$ExternalSyntheticLambda187(r20, r6, r5, r7, r21));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        return r0;
    }

    public static BottomSheet.Builder createDatePickerDialog(Context r25, String r26, String r27, long r28, ScheduleDatePickerDelegate r30) {
        if (r25 != null) goto L5;
        return null;
    L5:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(null);
        BottomSheet.Builder r9 = new BottomSheet.Builder(r25, false);
        r9.setApplyBottomPadding(false);
        NumberPicker r4 = new NumberPicker(r25);
        r4.setTextColor(r2.textColor);
        r4.setTextOffset(AndroidUtilities.dp(10.0f));
        r4.setItemCount(5);
        AnonymousClass29 r7 = new AnonymousClass29(r25);
        r7.setItemCount(5);
        r7.setTextColor(r2.textColor);
        r7.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass30 r6 = new AnonymousClass30(r25);
        r6.setItemCount(5);
        r6.setTextColor(r2.textColor);
        r6.setTextOffset(-AndroidUtilities.dp(34.0f));
        AnonymousClass31 r11 = new AnonymousClass31(r25, r4, r7, r6);
        r11.setOrientation(1);
        FrameLayout r3 = new FrameLayout(r25);
        r11.addView(r3, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r5 = new TextView(r25);
        r5.setText(r26);
        r5.setTextColor(r2.textColor);
        r5.setTextSize(1, 20.0f);
        r5.setTypeface(AndroidUtilities.bold());
        r3.addView(r5, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r5.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda188());
        LinearLayout r32 = new LinearLayout(r25);
        r32.setOrientation(0);
        r32.setWeightSum(1.0f);
        r11.addView(r32, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        Calendar r13 = Calendar.getInstance();
        AnonymousClass32 r14 = new AnonymousClass32(r25);
        r32.addView(r4, LayoutHelper.createLinear(0, 270, 0.5f));
        r4.setMinValue(0);
        r4.setMaxValue(365);
        r4.setWrapSelectorWheel(false);
        r4.setFormatter(new AlertsCreator$$ExternalSyntheticLambda189());
        AlertsCreator$$ExternalSyntheticLambda190 r0 = new AlertsCreator$$ExternalSyntheticLambda190(r4, r7, r6);
        r4.setOnValueChangedListener(r0);
        r7.setMinValue(0);
        r7.setMaxValue(23);
        r32.addView(r7, LayoutHelper.createLinear(0, 270, 0.2f));
        r7.setFormatter(new AlertsCreator$$ExternalSyntheticLambda191());
        r7.setOnValueChangedListener(r0);
        r6.setMinValue(0);
        r6.setMaxValue(59);
        r6.setValue(0);
        r6.setFormatter(new AlertsCreator$$ExternalSyntheticLambda192());
        r32.addView(r6, LayoutHelper.createLinear(0, 270, 0.3f));
        r6.setOnValueChangedListener(r0);
        if (r28 > 0) goto L8;
    L13:
        ScheduleDatePickerColors r16 = r2;
        float r252 = 34.0f;
        AnonymousClass31 r17 = r11;
    L14:
        checkScheduleDate(null, null, 0, r4, r7, r6);
        r14.setPadding(AndroidUtilities.dp(r252), 0, AndroidUtilities.dp(r252), 0);
        r14.setGravity(17);
        ScheduleDatePickerColors r02 = r16;
        r14.setTextColor(r02.buttonTextColor);
        r14.setTextSize(1, 14.0f);
        r14.setTypeface(AndroidUtilities.bold());
        r14.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), r02.buttonBackgroundColor, r02.buttonBackgroundPressedColor));
        r14.setText(r27);
        AnonymousClass31 r22 = r17;
        r22.addView(r14, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r14.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda193(r4, r7, r6, r13, r30, r9));
        r9.setCustomView(r22);
        BottomSheet r1 = r9.show();
        r1.setBackgroundColor(r02.backgroundColor);
        r1.fixNavigationBar(r02.backgroundColor);
        return r9;
    L8:
        if (r28 == 2147483646) goto L13;
        r17 = r11;
        long r10 = r28 * 1000;
        r252 = 34.0f;
        r13.setTimeInMillis(System.currentTimeMillis());
        r13.set(12, 0);
        r13.set(13, 0);
        r13.set(14, 0);
        r13.set(11, 0);
        r16 = r2;
        int r12 = (int) ((r10 - r13.getTimeInMillis()) / DurationKt.MILLIS_IN_DAY);
        r13.setTimeInMillis(r10);
        if (r12 < 0) goto L14;
        r6.setValue(r13.get(12));
        r7.setValue(r13.get(11));
        r4.setValue(r12);
        goto L14
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void createDeleteMessagesAlert(BaseFragment r43, TLRPC.User r44, TLRPC.Chat r45, TLRPC.EncryptedChat r46, TLRPC.ChatFull r47, long r48, MessageObject r50, SparseArray<MessageObject>[] r51, MessageObject.GroupedMessages r52, int r53, int r54, TLRPC.ChannelParticipant[] r55, Runnable r56, Runnable r57, Theme.ResourcesProvider r58) {
        boolean r15 = false;
        if (r54 != 1) goto L5;
        boolean r16 = true;
    L7:
        if (r54 != 3) goto L9;
        boolean r10 = true;
    L10:
        if (r43 == null) goto L439;
        if (r44 != null) goto L15;
        if (r45 != null) goto L15;
        if (r46 != null) goto L15;
        return;
    L15:
        Activity r4 = r43.getParentActivity();
        if (r4 == null) goto L442;
        int r11 = r43.getCurrentAccount();
        AlertDialog.Builder r12 = new AlertDialog.Builder(r4, r58);
        if (r57 == null) goto L21;
        float r13 = 0.5f;
    L22:
        r12.setDimAlpha(r13);
        if (r52 == null) goto L25;
        int r132 = r52.messages.size();
    L28:
        if (r46 == null) goto L31;
        long r1 = DialogObject.makeEncryptedDialogId(r46.id);
    L30:
        long r7 = r1;
        TLRPC.Chat r2 = r45;
    L34:
        if (r16 == false) goto L69;
        if (r50 == null) goto L41;
        TLRPC.Message r3 = r50.messageOwner;
        if (r3 == null) goto L41;
        int r14 = r3.schedule_repeat_period;
        if (r14 <= 0) goto L41;
        int r32 = r3.date;
    L52:
        if (r32 <= 0) goto L69;
        if (r14 <= 0) goto L69;
        String r22 = LocaleController.formatString(R.string.MessageScheduledRepeatDeletePostponeSeconds, new Object[]{Integer.valueOf(r14)});
        if (r14 != 31536000) goto L59;
        r22 = LocaleController.getString(R.string.MessageScheduledRepeatDeletePostponeYear);
    L57:
        String r122 = r22;
        new AlertDialog.Builder(r4, r58).setTitle(LocaleController.getString(R.string.MessageScheduledRepeatDeleteTitle)).setMessage(LocaleController.getString(R.string.MessageScheduledRepeatDeleteText)).setNegativeButton(r122, new AlertsCreator$$ExternalSyntheticLambda96(r52, r11, r43, r32, r14, r50)).setNeutralButton(LocaleController.getString(R.string.MessageScheduledRepeatDeleteAll), new AlertsCreator$$ExternalSyntheticLambda103(r7, r10, r11, r50, r52, r46, r48, r53, r54, r51, r56)).setPositiveButton(LocaleController.getString(R.string.Cancel), null).makeRed(-2).makeRed(-3).setOnPreDismissListener(new AlertsCreator$$ExternalSyntheticLambda104(r57)).show();
        return;
    L59:
        if (r14 < 2592000) goto L62;
        r22 = LocaleController.formatPluralString("MessageScheduledRepeatDeletePostponeMonths", r14 / 2592000, new Object[0]);
        goto L57
    L62:
        if (r14 < 604800) goto L65;
        r22 = LocaleController.formatPluralString("MessageScheduledRepeatDeletePostponeWeeks", r14 / 604800, new Object[0]);
        goto L57
    L65:
        if (r14 < 86400) goto L57;
        r22 = LocaleController.formatPluralString("MessageScheduledRepeatDeletePostponeDays", r14 / 86400, new Object[0]);
    L41:
        if (r52 != null) goto L43;
    L51:
        r14 = 0;
        r32 = 0;
        goto L52
    L43:
        if (r52.messages.isEmpty() == true) goto L51;
        if (r52.messages.get(0) == null) goto L51;
        if (r52.messages.get(0).messageOwner == null) goto L51;
        if (r52.messages.get(0).messageOwner.schedule_repeat_period <= 0) goto L51;
        r32 = r52.messages.get(0).messageOwner.date;
        r14 = r52.messages.get(0).messageOwner.schedule_repeat_period;
    L69:
        Runnable r142 = r57;
        long r8 = r7;
        boolean r33 = r10;
        int r72 = r11;
        int r102 = ConnectionsManager.getInstance(r72).getCurrentTime();
        MessagesController.getInstance(r72).config.starsSuggestedPostAgeMin.get(TimeUnit.SECONDS);
        if (r50 != null) goto L72;
        boolean r222 = 0;
        boolean r17 = false;
        int r112 = 0;
        r15 = false;
        boolean r23 = false;
    L84:
        if (r112 >= 2) goto L100;
        int r0 = 0;
    L86:
        boolean r25 = r17;
        if (r0 >= r51[r112].size()) goto L99;
        MessageObject r18 = r51[r112].valueAt(r0);
        if (r18.isDice() == false) goto L93;
        int r24 = r0;
        boolean r26 = r33;
        if (Math.abs(r102 - r18.messageOwner.date) <= 86400) goto L96;
    L94:
        r25 = true;
    L96:
        if (r18.isPaidSuggestedPostProtected() == false) goto L98;
        TLRPC.Message r02 = r18.messageOwner;
        r15 = r15 | r02.paid_suggested_post_stars;
        r23 = r23 | r02.paid_suggested_post_ton;
    L98:
        r0 = r24 + 1;
        r17 = r25;
        r33 = r26;
        goto L86
    L93:
        r24 = r0;
        r26 = r33;
        goto L94
    L99:
        r112 = r112 + 1;
        r17 = r25;
        r33 = r33;
        goto L84
    L100:
        boolean r262 = r33;
    L101:
        if (r52 == null) goto L109;
        int r03 = r222;
    L104:
        if (r03 >= r52.messages.size()) goto L109;
        MessageObject r34 = r52.messages.get(r03);
        if (r34.isPaidSuggestedPostProtected() == false) goto L108;
        TLRPC.Message r35 = r34.messageOwner;
        boolean r113 = r35.paid_suggested_post_stars | r15;
        r23 = r23 | r35.paid_suggested_post_ton;
        r15 = r113;
    L108:
        r03 = r03 + 1;
    L109:
        boolean[] r114 = new boolean[1];
        if (r44 != null) goto L112;
    L114:
        boolean r04 = r222;
    L115:
        if (r44 == null) goto L117;
        int r36 = MessagesController.getInstance(r72).revokeTimePmLimit;
    L118:
        if (r46 != null) goto L124;
        if (r44 == null) goto L124;
        if (r04 == false) goto L124;
        boolean r21 = r04;
        if (r36 != Integer.MAX_VALUE) goto L125;
        boolean r242 = true;
    L126:
        boolean r252 = r17;
        if (r2 != null) goto L129;
    L208:
        long r31 = r8;
        int r9 = r72;
        if (r16 == true) goto L306;
        if (r262 == true) goto L306;
        if (ChatObject.isChannel(r45) == true) goto L306;
        if (r46 != null) goto L306;
        if (r44 != null) goto L216;
    L221:
        if (r45 == null) goto L279;
    L222:
        if (r50 != null) goto L224;
        int r27 = 1;
        boolean r73 = false;
        int r82 = 0;
    L248:
        if (r27 < 0) goto L278;
        int r19 = 0;
    L250:
        int r472 = r27;
        if (r19 >= r51[r27].size()) goto L277;
        MessageObject r28 = r51[r472].valueAt(r19);
        int r172 = r19;
        TLRPC.MessageAction r110 = r28.messageOwner.action;
        if (r110 == null) goto L264;
        if ((r110 instanceof TLRPC.TL_messageActionEmpty) == true) goto L264;
        if ((r110 instanceof TLRPC.TL_messageActionPhoneCall) == true) goto L264;
        if ((r110 instanceof TLRPC.TL_messageActionPinMessage) == true) goto L264;
        if ((r110 instanceof TLRPC.TL_messageActionGeoProximityReached) == true) goto L264;
    L276:
        r19 = r172 + 1;
        r27 = r472;
    L264:
        if (r28.isOut() == true) goto L270;
        if (r21 == true) goto L270;
        if (r45 == null) goto L276;
        if (ChatObject.canBlockUsers(r45) == false) goto L276;
    L270:
        if ((r102 - r28.messageOwner.date) > r36) goto L276;
        r82 = r82 + 1;
        if (r73 == true) goto L276;
        if (r28.isOut() == true) goto L276;
        r73 = true;
        goto L276
    L277:
        r27 = r472 - 1;
        goto L248
    L278:
        boolean r37 = r73;
        int r29 = r82;
    L280:
        if (r29 <= 0) goto L305;
        if (r252 == false) goto L305;
        if (r44 != null) goto L284;
    L287:
        FrameLayout r111 = new FrameLayout(r4);
        CheckBoxCell r5 = new CheckBoxCell(r4, 1, r58);
        r5.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (r242 == false) goto L290;
        r5.setText(LocaleController.formatString("DeleteMessagesOptionAlso", R.string.DeleteMessagesOptionAlso, new Object[]{UserObject.getFirstName(r44)}), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L296:
        if (LocaleController.isRTL == false) goto L298;
        int r05 = AndroidUtilities.dp(16.0f);
    L300:
        if (LocaleController.isRTL == false) goto L303;
        int r42 = AndroidUtilities.dp(8.0f);
    L304:
        r5.setPadding(r05, 0, r42, 0);
        r111.addView(r5, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        r114[0] = true;
        r5.setChecked(true, true);
        r5.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda100(r114));
        r12.setView(r111);
        r12.setCustomViewOffset(9);
        int r74 = 1;
        boolean r173 = true;
        boolean r182 = r37;
    L307:
        int r422 = r29;
        int r49 = r9;
        int r41 = r132;
        boolean r212 = r15;
        boolean r38 = r262;
        int r152 = r74;
        AlertDialog.OnButtonClickListener r06 = new AlertsCreator$$ExternalSyntheticLambda101(r31, r38, r49, r50, r52, r46, r48, r53, r114, r54, r51, r56);
        if (r38 == false) goto L313;
        int r133 = r41;
        if (r133 != r152) goto L312;
        AlertDialog.Builder r6 = r12;
        r6.setTitle(LocaleController.getString(R.string.UnsaveSingleMessagesTitle));
    L317:
        if (r38 == false) goto L323;
        if (r133 != r152) goto L321;
        r6.setMessage(LocaleController.getString(R.string.AreYouSureUnsaveSingleMessage));
    L320:
        int r83 = 0;
    L355:
        if (r50 != null) goto L357;
        if (r133 != r152) goto L391;
        int r59 = r83;
        int r115 = r152;
        String r210 = null;
    L372:
        if (r115 < 0) goto L390;
        int r75 = r83;
    L375:
        if (r75 >= r51[r115].size()) goto L389;
        MessageObject r510 = r51[r115].valueAt(r75);
        if (r510.isGiveaway() == true) goto L379;
    L381:
        int r92 = r83;
    L382:
        if (r92 == 0) goto L387;
        long r93 = ((TLRPC.TL_messageMediaGiveaway) r510.messageOwner.media).until_date * 1000;
        r210 = LocaleController.getInstance().getFormatterGiveawayMonthDayYear().format(new Date(r93));
        if (System.currentTimeMillis() >= r93) goto L386;
        r59 = r152;
    L388:
        r75 = r75 + 1;
        goto L375
    L386:
        r59 = r83;
        goto L388
    L387:
        r59 = r92;
        goto L388
    L379:
        if (r510.isForwarded() == true) goto L381;
        r92 = r152;
        goto L382
    L389:
        r115 = r115 - 1;
        goto L372
    L390:
        r152 = r59;
    L392:
        TimeUnit r116 = TimeUnit.HOURS;
        if (r212 == false) goto L395;
        int r117 = (int) MessagesController.getInstance(r49).config.starsSuggestedPostAgeMin.get(r116);
        r6.setTitle(LocaleController.getString(R.string.SuggestionStarsWillBeLost));
        r6.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.SuggestionStarsWillBeLostInfo, new Object[]{Integer.valueOf(r117)})));
        r6.setPositiveButton(LocaleController.getString(R.string.SuggestionStarsWillBeLostDelete), r06);
    L404:
        r6.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r6.setOnPreDismissListener(new AlertsCreator$$ExternalSyntheticLambda102(r57));
        AlertDialog r07 = r6.create();
        r43.showDialog(r07);
        TextView r118 = (TextView) r07.getButton(-1);
        if (r118 == null) goto L407;
        r118.setTextColor(Theme.getColor(Theme.key_text_RedBold));
    L407:
        TextView r119 = (TextView) r07.getButton(-3);
        if (r119 == null) goto L440;
        r07.getButtonsLayout().setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(0.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f));
        ((ViewGroup.MarginLayoutParams) r07.getButtonsLayout().getLayoutParams()).topMargin = AndroidUtilities.dp(-8.0f);
        r119.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L440:
        return;
    L395:
        if (r23 == false) goto L397;
        int r120 = (int) MessagesController.getInstance(r49).config.starsSuggestedPostAgeMin.get(r116);
        r6.setTitle(LocaleController.getString(R.string.SuggestionTONWillBeLost));
        r6.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.SuggestionTONWillBeLostInfo, new Object[]{Integer.valueOf(r120)})));
        r6.setPositiveButton(LocaleController.getString(R.string.SuggestionStarsWillBeLostDelete), r06);
        goto L404
    L397:
        if (r152 == 0) goto L400;
        if (r38 == true) goto L400;
        r6.setTitle(LocaleController.getString(R.string.BoostingGiveawayDeleteMsgTitle));
        r6.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BoostingGiveawayDeleteMsgText, new Object[]{r210})));
        r6.setNeutralButton(LocaleController.getString(R.string.Delete), r06);
    L400:
        if (r38 == false) goto L402;
        int r121 = R.string.Remove;
    L403:
        r6.setPositiveButton(LocaleController.getString(r121), r06);
        goto L404
    L402:
        r121 = R.string.Delete;
        goto L403
    L391:
        r152 = r83;
    L369:
        r210 = null;
        goto L392
    L357:
        if (r50.isGiveaway() == true) goto L359;
    L361:
        int r123 = r83;
    L362:
        if (r123 == 0) goto L368;
        long r124 = ((TLRPC.TL_messageMediaGiveaway) r50.messageOwner.media).until_date * 1000;
        String r511 = LocaleController.getInstance().getFormatterGiveawayMonthDayYear().format(new Date(r124));
        if (System.currentTimeMillis() < r124) goto L367;
        r152 = r83;
    L367:
        r210 = r511;
        goto L392
    L368:
        r152 = r123;
        goto L369
    L359:
        if (r50.isForwarded() == true) goto L361;
        r123 = r152;
        goto L362
    L321:
        r6.setMessage(LocaleController.getString(R.string.AreYouSureUnsaveFewMessages));
        goto L320
    L323:
        if (r45 == null) goto L333;
        if (r182 == false) goto L333;
        if (r173 == true) goto L327;
    L329:
        if (r133 != r152) goto L331;
        r6.setMessage(LocaleController.getString(R.string.AreYouSureDeleteSingleMessage));
        goto L320
    L331:
        r6.setMessage(LocaleController.getString(R.string.AreYouSureDeleteFewMessages));
        goto L320
    L327:
        if (r422 == r133) goto L329;
        r6.setMessage(LocaleController.formatString(R.string.DeleteMessagesTextGroupPart, new Object[]{LocaleController.formatPluralString("messages", r422, new Object[0])}));
    L333:
        if (r173 == false) goto L339;
        if (r242 == true) goto L339;
        if (r422 == r133) goto L339;
        if (r45 == null) goto L338;
        r83 = 0;
        r6.setMessage(LocaleController.formatString("DeleteMessagesTextGroup", R.string.DeleteMessagesTextGroup, new Object[]{LocaleController.formatPluralString("messages", r422, new Object[0])}));
        goto L355
    L338:
        r83 = 0;
        r6.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("DeleteMessagesText", R.string.DeleteMessagesText, new Object[]{LocaleController.formatPluralString("messages", r422, new Object[0]), UserObject.getFirstName(r44)})));
    L339:
        r83 = 0;
        if (r45 != null) goto L342;
    L352:
        if (r133 != r152) goto L354;
        r6.setMessage(LocaleController.getString(R.string.AreYouSureDeleteSingleMessage));
        goto L355
    L354:
        r6.setMessage(LocaleController.getString(R.string.AreYouSureDeleteFewMessages));
        goto L355
    L342:
        if (r45.megagroup == false) goto L352;
        if (r16 == true) goto L352;
        if (r133 != r152) goto L351;
        if (r50 != null) goto L347;
    L349:
        int r125 = R.string.AreYouSureDeleteSingleMessageMega;
    L350:
        r6.setMessage(LocaleController.getString(r125));
        goto L355
    L347:
        if (r50.isEphemeral() == false) goto L349;
        r125 = R.string.AreYouSureDeleteSingleMessage;
        goto L350
    L351:
        r6.setMessage(LocaleController.getString(R.string.AreYouSureDeleteFewMessagesMega));
        goto L355
    L312:
        r6 = r12;
        r6.setTitle(LocaleController.formatString(R.string.UnsaveMessagesTitle, new Object[]{LocaleController.formatPluralString("messages", r133, new Object[0])}));
        goto L317
    L313:
        r6 = r12;
        r133 = r41;
        if (r133 != r152) goto L316;
        r6.setTitle(LocaleController.getString(R.string.DeleteSingleMessagesTitle));
        goto L317
    L316:
        r6.setTitle(LocaleController.formatString(R.string.DeleteMessagesTitle, new Object[]{LocaleController.formatPluralString("messages", r133, new Object[0])}));
        goto L317
    L303:
        r42 = AndroidUtilities.dp(16.0f);
        goto L304
    L298:
        r05 = AndroidUtilities.dp(8.0f);
        goto L300
    L290:
        if (r45 == null) goto L294;
        if (r37 == true) goto L293;
        if (r29 != r132) goto L294;
    L293:
        r5.setText(LocaleController.getString(R.string.DeleteForAll), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
    L294:
        r5.setText(LocaleController.getString(R.string.DeleteMessagesOption), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        goto L296
    L284:
        if (UserObject.isDeleted(r44) == false) goto L287;
    L305:
        r74 = 1;
        r182 = r37;
        r173 = false;
        goto L307
    L224:
        if (r50.isSendError() == true) goto L245;
        TLRPC.MessageAction r211 = r50.messageOwner.action;
        if (r211 == null) goto L238;
        if ((r211 instanceof TLRPC.TL_messageActionEmpty) == true) goto L238;
        if ((r211 instanceof TLRPC.TL_messageActionPhoneCall) == true) goto L238;
        if ((r211 instanceof TLRPC.TL_messageActionPinMessage) == true) goto L238;
        if ((r211 instanceof TLRPC.TL_messageActionGeoProximityReached) == true) goto L238;
        if ((r211 instanceof TLRPC.TL_messageActionSetChatTheme) == false) goto L245;
    L238:
        if (r50.isOut() == true) goto L243;
        if (r21 == true) goto L243;
        if (ChatObject.hasAdminRights(r45) == false) goto L245;
    L243:
        if ((r102 - r50.messageOwner.date) > r36) goto L245;
        r29 = 1;
    L246:
        r37 = !r50.isOut();
    L245:
        r29 = 0;
        goto L246
    L279:
        r29 = 0;
        r37 = false;
        goto L280
    L216:
        if (r44.id == UserConfig.getInstance(r9).getClientUserId()) goto L221;
        if (r44.bot == false) goto L222;
        if (r44.support == true) goto L222;
    L306:
        r74 = 1;
        r29 = 0;
        r173 = false;
    L207:
        r182 = false;
        goto L307
    L129:
        if (r2.megagroup == false) goto L208;
        if (r16 == true) goto L208;
        if (r262 == true) goto L208;
        ArrayList r126 = new ArrayList();
        if (r50 == null) goto L161;
        TLRPC.MessageAction r213 = r50.messageOwner.action;
        r31 = r8;
        if (r213 != null) goto L137;
    L144:
        if (r52 == null) goto L146;
        r126.addAll(r52.messages);
    L148:
        if (r50.isSendError() == false) goto L150;
    L160:
        r29 = r222;
    L179:
        ArrayList r39 = (ArrayList) r126.stream().filter(new AlertsCreator$$ExternalSyntheticLambda105()).mapToLong(new AlertsCreator$$ExternalSyntheticLambda106()).distinct().mapToObj(new AlertsCreator$$ExternalSyntheticLambda107(r72)).filter(new AlertsCreator$$ExternalSyntheticLambda108()).filter(new AlertsCreator$$ExternalSyntheticLambda109(UserConfig.getInstance(r72).getClientUserId())).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
        if (r39.isEmpty() == true) goto L192;
        if (r55 != null) goto L187;
        AlertDialog[] r62 = {new AlertDialog(r4, 3)};
        int r512 = r39.size();
        TLRPC.ChannelParticipant[] r410 = new TLRPC.ChannelParticipant[r512];
        int[] r214 = new int[r512];
        int[] r127 = new int[1];
        int r153 = r222;
    L183:
        if (r153 >= r512) goto L185;
        TLRPC.TL_channels_getParticipant r08 = new TLRPC.TL_channels_getParticipant();
        r08.channel = MessagesController.getInputChannel(r45);
        r08.participant = MessagesController.getInputPeer((TLObject) r39.get(r153));
        ConnectionsManager r84 = ConnectionsManager.getInstance(r72);
        ArrayList r232 = r39;
        int r310 = r153;
        AlertsCreator$$ExternalSyntheticLambda110 r09 = new AlertsCreator$$ExternalSyntheticLambda110(r127, r214, r310, r410, r512, r62, r43, r44, r45, r46, r47, r48, r50, r51, r52, r53, r54, r56, r142, r58);
        int[] r311 = r214;
        r311[r310] = r84.sendRequest(r08, r09);
        r153 = r310 + 1;
        r142 = r57;
        r62 = r62;
        r512 = r512;
        r214 = r311;
        r127 = r127;
        r72 = r72;
        r39 = r232;
        goto L183
    L185:
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda97(r62, r214, r72, r57, r43), 1000);
        return;
    L187:
        DeleteMessagesBottomSheet r010 = new DeleteMessagesBottomSheet(r43, r45, r126, r39, r55, r48, r53, r54, false, r56);
        if (r142 == null) goto L190;
        r010.setOnHideListener(new AlertsCreator$$ExternalSyntheticLambda98(r142));
    L190:
        r010.show();
        return;
    L192:
        r9 = r72;
        if (r29 <= 0) goto L205;
        if (r252 == false) goto L205;
        FrameLayout r312 = new FrameLayout(r4);
        CheckBoxCell r103 = new CheckBoxCell(r4, 1, r58);
        r103.setBackgroundDrawable(Theme.getSelectorDrawable(r222));
        boolean r63 = r222;
        r103.setText(LocaleController.getString(R.string.DeleteMessagesOption), _UrlKt.FRAGMENT_ENCODE_SET, r63, r63);
        if (LocaleController.isRTL == false) goto L198;
        int r011 = AndroidUtilities.dp(16.0f);
    L200:
        if (LocaleController.isRTL == false) goto L203;
        int r411 = AndroidUtilities.dp(8.0f);
    L204:
        r103.setPadding(r011, 0, r411, 0);
        r312.addView(r103, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        r114[0] = true;
        r103.setChecked(true, true);
        r103.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda99(r114));
        r12.setView(r312);
        r12.setCustomViewOffset(9);
        boolean r012 = true;
    L206:
        r173 = r012;
        r74 = 1;
        goto L207
    L203:
        r411 = AndroidUtilities.dp(16.0f);
        goto L204
    L198:
        r011 = AndroidUtilities.dp(8.0f);
    L205:
        r012 = false;
        goto L206
    L150:
        if (r50.getDialogId() != r48) goto L160;
        TLRPC.MessageAction r215 = r50.messageOwner.action;
        if (r215 == null) goto L156;
        if ((r215 instanceof TLRPC.TL_messageActionEmpty) == false) goto L160;
    L156:
        if (r50.isOut() == false) goto L160;
        if ((r102 - r50.messageOwner.date) > r36) goto L160;
        r29 = 1;
        goto L179
    L146:
        r126.add(r50);
        goto L148
    L137:
        if ((r213 instanceof TLRPC.TL_messageActionEmpty) == true) goto L144;
        if ((r213 instanceof TLRPC.TL_messageActionChatDeleteUser) == true) goto L144;
        if ((r213 instanceof TLRPC.TL_messageActionChatJoinedByLink) == true) goto L144;
        if ((r213 instanceof TLRPC.TL_messageActionChatAddUser) == false) goto L148;
    L161:
        r31 = r8;
        int r85 = r222;
        int r216 = 1;
    L162:
        if (r216 < 0) goto L178;
        int r94 = r222;
    L165:
        if (r94 >= r51[r216].size()) goto L177;
        MessageObject r513 = r51[r216].valueAt(r94);
        if (r216 == 1) goto L169;
    L175:
        int r217 = r216;
    L176:
        r126.add(r513);
        r94 = r94 + 1;
        r216 = r217;
        goto L165
    L169:
        if (r513.isOut() == false) goto L175;
        TLRPC.Message r64 = r513.messageOwner;
        r217 = r216;
        if (r64.action != null) goto L176;
        if ((r102 - r64.date) > r36) goto L176;
        r85 = r85 + 1;
        goto L176
    L177:
        r216 = r216 - 1;
        goto L162
    L178:
        r29 = r85;
    L125:
        r242 = r222;
    L124:
        r21 = r04;
        goto L125
    L117:
        r36 = MessagesController.getInstance(r72).revokeTimeLimit;
        goto L118
    L112:
        if (MessagesController.getInstance(r72).canRevokePmInbox == false) goto L114;
        r04 = true;
        goto L115
    L72:
        if (r50.isDice() == true) goto L74;
    L77:
        r17 = true;
    L79:
        if (r50.isPaidSuggestedPostProtected() == false) goto L81;
        TLRPC.Message r1110 = r50.messageOwner;
        r222 = 0;
        r15 = r1110.paid_suggested_post_stars;
        r262 = r33;
        r23 = r1110.paid_suggested_post_ton;
        goto L101
    L81:
        r222 = 0;
        r262 = r33;
        r23 = false;
        goto L101
    L74:
        if (Math.abs(r102 - r50.messageOwner.date) > 86400) goto L77;
        r17 = false;
        goto L79
    L31:
        if (r44 == null) goto L33;
        r1 = r44.id;
        goto L30
    L33:
        r2 = r45;
        r7 = -r2.id;
        goto L34
    L25:
        if (r50 == null) goto L27;
        r132 = 1;
        goto L28
    L27:
        r132 = r51[0].size() + r51[1].size();
        goto L28
    L21:
        r13 = 0.6f;
        goto L22
    L442:
        return;
    L439:
        return;
    L9:
        r10 = false;
        goto L10
    L5:
        r16 = false;
        goto L7
    }

    public static AlertDialog.Builder createDrawOverlayGroupCallPermissionDialog(Context r15) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r15);
        String r1 = AndroidUtilities.readRes(R.raw.pip_voice_request);
        GroupCallPipButton r2 = new GroupCallPipButton(r15, 0, true);
        r2.setImportantForAccessibility(2);
        AnonymousClass61 r5 = new AnonymousClass61(r15, r2);
        r5.setBackground(new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{-15128003, -15118002}));
        r5.setClipToOutline(true);
        r5.setOutlineProvider(new AnonymousClass62());
        View r6 = new View(r15);
        r6.setBackground(new BitmapDrawable(SvgHelper.getBitmap(r1, AndroidUtilities.dp(320.0f), AndroidUtilities.dp(184.61539f), false)));
        r5.addView(r6, LayoutHelper.createFrame(-1, -1.0f, 0, -1.0f, -1.0f, -1.0f, -1.0f));
        r5.addView(r2, LayoutHelper.createFrame(117, 117.0f));
        r0.setTopView(r5);
        r0.setTitle(LocaleController.getString(R.string.PermissionDrawAboveOtherAppsGroupCallTitle));
        r0.setMessage(LocaleController.getString(R.string.PermissionDrawAboveOtherAppsGroupCall));
        r0.setPositiveButton(LocaleController.getString(R.string.Enable), new AlertsCreator$$ExternalSyntheticLambda49(r15));
        r0.notDrawBackgroundOnTopView(true);
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setTopViewAspectRatio(0.5769231f);
        return r0;
    }

    public static AlertDialog.Builder createDrawOverlayPermissionDialog(Activity r1, AlertDialog.OnButtonClickListener r2) {
        return createDrawOverlayPermissionDialog(r1, r2, false);
    }

    public static AlertDialog.Builder createDrawOverlayPermissionDialog(Activity r13, AlertDialog.OnButtonClickListener r14, boolean r15) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r13);
        String r1 = AndroidUtilities.readRes(R.raw.pip_video_request);
        FrameLayout r2 = new FrameLayout(r13);
        r2.setBackground(new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{-14535089, -14527894}));
        r2.setClipToOutline(true);
        r2.setOutlineProvider(new AnonymousClass60());
        View r4 = new View(r13);
        r4.setBackground(new BitmapDrawable(SvgHelper.getBitmap(r1, AndroidUtilities.dp(320.0f), AndroidUtilities.dp(161.36752f), false)));
        r2.addView(r4, LayoutHelper.createFrame(-1, -1.0f, 0, -1.0f, -1.0f, -1.0f, -1.0f));
        r0.setTopView(r2);
        r0.setTitle(LocaleController.getString(R.string.PermissionDrawAboveOtherAppsTitle));
        r0.setMessage(LocaleController.getString(R.string.PermissionDrawAboveOtherApps));
        r0.setPositiveButton(LocaleController.getString(R.string.Enable), new AlertsCreator$$ExternalSyntheticLambda83(r13, r15));
        r0.notDrawBackgroundOnTopView(true);
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), r14);
        r0.setTopViewAspectRatio(0.50427353f);
        return r0;
    }

    public static Dialog createForgotPasscodeDialog(Context r2) {
        return new AlertDialog.Builder(r2).setTitle(LocaleController.getString(R.string.ForgotPasscode)).setMessage(LocaleController.getString(R.string.ForgotPasscodeInfo)).setPositiveButton(LocaleController.getString(R.string.Close), null).create();
    }

    public static BottomSheet.Builder createFormattedDatePickerDialog(Context r31, FormattedDatePickerDelegate r32, Runnable r33, Theme.ResourcesProvider r34) {
        if (r31 != null) goto L6;
        return null;
    L6:
        ScheduleDatePickerColors r7 = new ScheduleDatePickerColors(r34);
        BottomSheet.Builder r8 = new BottomSheet.Builder(r31, false, r34);
        r8.setApplyBottomPadding(false);
        long r2 = System.currentTimeMillis();
        Calendar r0 = Calendar.getInstance();
        r0.setTimeInMillis(r2);
        int r4 = r0.get(1);
        NumberPicker r12 = new NumberPicker(r31, r34);
        r12.setTextColor(r7.textColor);
        r12.setTextOffset(AndroidUtilities.dp(10.0f));
        r12.setItemCount(5);
        r12.setMinValue(1);
        r12.setMaxValue(31);
        r12.setWrapSelectorWheel(false);
        r12.setFormatter(new AlertsCreator$$ExternalSyntheticLambda33());
        NumberPicker r13 = new NumberPicker(r31, r34);
        r13.setTextColor(r7.textColor);
        r13.setTextOffset(-AndroidUtilities.dp(10.0f));
        r13.setItemCount(5);
        r13.setMinValue(0);
        r13.setMaxValue(239);
        r13.setValue(120);
        r13.setWrapSelectorWheel(false);
        r13.setFormatter(new AlertsCreator$$ExternalSyntheticLambda36(r0, r4));
        NumberPicker r15 = new NumberPicker(r31, r34);
        r15.setContentDescriptionCallback(new AlertsCreator$$ExternalSyntheticLambda37());
        r15.setWrapSelectorWheel(true);
        r15.setAllItemsCount(24);
        r15.setItemCount(5);
        r15.setTextColor(r7.textColor);
        r15.setTextOffset(AndroidUtilities.dp(10.0f));
        r15.setMinValue(0);
        r15.setMaxValue(23);
        r15.setFormatter(new AlertsCreator$$ExternalSyntheticLambda38());
        NumberPicker r5 = new NumberPicker(r31, r34);
        r5.setContentDescriptionCallback(new AlertsCreator$$ExternalSyntheticLambda39());
        r5.setWrapSelectorWheel(true);
        r5.setAllItemsCount(60);
        r5.setItemCount(5);
        r5.setTextColor(r7.textColor);
        r5.setTextOffset(-AndroidUtilities.dp(10.0f));
        r5.setMinValue(0);
        r5.setMaxValue(59);
        r5.setValue(0);
        r5.setFormatter(new AlertsCreator$$ExternalSyntheticLambda40());
        r0.setTimeInMillis(r2);
        r5.setValue(r0.get(12));
        r15.setValue(r0.get(11));
        r12.setValue(r0.get(5));
        r13.setValue(r0.get(2) + 120);
        Text r02 = new Text(LocaleController.formatString(R.string.formatDateAtTime, new Object[]{_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET}).trim(), 16.0f).setMaxWidth(AndroidUtilities.dp(100.0f));
        Layout.Alignment r3 = Layout.Alignment.ALIGN_CENTER;
        r02.align(r3).multiline(1).setColor(r7.textColor);
        Text r14 = new Text(":", 18.0f).setMaxWidth(AndroidUtilities.dp(100.0f)).align(r3).multiline(1).setColor(r7.textColor);
        FrameLayout r03 = new FrameLayout(r31);
        AnonymousClass35 r04 = new AnonymousClass35(r31, r13, r12, r15, r5);
        r04.setOrientation(1);
        r03.addView(r04, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r22 = new FrameLayout(r31);
        r04.addView(r22, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r42 = new TextView(r31);
        r42.setText(LocaleController.getString(R.string.RelativeDateAddDate));
        r42.setTextColor(r7.textColor);
        r42.setTextSize(1, 20.0f);
        r42.setTypeface(AndroidUtilities.bold());
        r22.addView(r42, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r42.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda41());
        AnonymousClass36 r23 = new AnonymousClass36(r31, r14, r5);
        r23.setOrientation(0);
        r23.setWeightSum(1.0f);
        r04.addView(r23, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        ButtonWithCounterView r152 = new ButtonWithCounterView(r31, r34);
        String[] r43 = {LocaleController.getString(R.string.DateFormatOptionNone), LocaleController.getString(R.string.DateFormatOptionRelative), LocaleController.getString(R.string.DateFormatOptionShortTime), LocaleController.getString(R.string.DateFormatOptionLongTime), LocaleController.getString(R.string.DateFormatOptionShortDate), LocaleController.getString(R.string.DateFormatOptionLongDate), LocaleController.getString(R.string.DateFormatOptionDayOfWeek)};
        int[] r11 = {0, 1, 2, 4, 8, 16, 32};
        int[] r18 = {5};
        int r35 = Theme.blendOver(r7.backgroundColor, Theme.multAlpha(r7.textColor, 0.075f));
        int r9 = Theme.multAlpha(r7.textColor, 0.1f);
        FrameLayout r10 = new FrameLayout(r31);
        TextView r142 = new TextView(r31);
        r142.setTextSize(1, 13.0f);
        r142.setTextColor(r7.textColor);
        r142.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r142.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(14.0f), r35, Theme.blendOver(r35, r9)));
        r142.setGravity(17);
        AlertsCreator$$ExternalSyntheticLambda42 r112 = new AlertsCreator$$ExternalSyntheticLambda42(r43, r18, r142, r152, r12, r13, r15, r5, r11);
        r10.addView(r142, LayoutHelper.createFrame(-2, 28.0f, 1, 32.0f, 4.0f, 32.0f, 5.0f));
        r04.addView(r10, LayoutHelper.createLinear(-1, -2));
        AlertsCreator$$ExternalSyntheticLambda43 r113 = new AlertsCreator$$ExternalSyntheticLambda43(r152, r12, r13, r15, r5, r11, r18);
        r23.addView(r12, LayoutHelper.createLinear(0, 270, 0.2f));
        r23.addView(r13, LayoutHelper.createLinear(0, 270, 0.4f));
        r23.addView(r15, LayoutHelper.createLinear(0, 270, 0.2f));
        r23.addView(r5, LayoutHelper.createLinear(0, 270, 0.2f));
        r12.setOnValueChangedListener(r113);
        r13.setOnValueChangedListener(r113);
        r15.setOnValueChangedListener(r113);
        r5.setOnValueChangedListener(r113);
        boolean[] r92 = {true};
        r152.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r152.setRound();
        r04.addView(r152, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r152.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda44(r92, r11, r18, r12, r13, r15, r5, r32, r8));
        r8.setCustomView(r03);
        BottomSheet r1 = r8.show();
        r1.setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda34(r33, r92));
        r1.setBackgroundColor(r7.backgroundColor);
        r1.fixNavigationBar(r7.backgroundColor);
        r142.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda35(r1, r34, r10, r43, r18, r112));
        r112.run();
        return r8;
    }

    public static Dialog createFreeSpaceDialog(LaunchActivity r3) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3);
        r0.setTitle(LocaleController.getString(R.string.LowDiskSpaceTitle));
        r0.setMessage(LocaleController.getString(R.string.LowDiskSpaceMessage2));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.LowDiskSpaceButton), new AlertsCreator$$ExternalSyntheticLambda144(r3));
        return r0.create();
    }

    public static AlertDialog.Builder createGigagroupConvertAlert(Activity r11, AlertDialog.OnButtonClickListener r12, AlertDialog.OnButtonClickListener r13) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r11);
        String r1 = AndroidUtilities.readRes(R.raw.gigagroup);
        FrameLayout r2 = new FrameLayout(r11);
        r2.setClipToOutline(true);
        r2.setOutlineProvider(new AnonymousClass59());
        View r3 = new View(r11);
        r3.setBackground(new BitmapDrawable(SvgHelper.getBitmap(r1, AndroidUtilities.dp(320.0f), AndroidUtilities.dp(127.17949f), false)));
        r2.addView(r3, LayoutHelper.createFrame(-1, -1.0f, 0, -1.0f, -1.0f, -1.0f, -1.0f));
        r0.setTopView(r2);
        r0.setTopViewAspectRatio(0.3974359f);
        r0.setTitle(LocaleController.getString(R.string.GigagroupAlertTitle));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.GigagroupAlertText)));
        r0.setPositiveButton(LocaleController.getString(R.string.GigagroupAlertLearnMore), r12);
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), r13);
        return r0;
    }

    public static void createImportDialogAlert(BaseFragment r24, String r25, String r26, TLRPC.User r27, TLRPC.Chat r28, Runnable r29) {
        if (r24 != null) goto L5;
        return;
    L5:
        if (r24.getParentActivity() == null) goto L50;
        if (r28 != null) goto L9;
        if (r27 != null) goto L9;
        return;
    L9:
        int r3 = r24.getCurrentAccount();
        Activity r4 = r24.getParentActivity();
        AlertDialog.Builder r5 = new AlertDialog.Builder(r4);
        long r6 = UserConfig.getInstance(r3).getClientUserId();
        TextView r8 = new TextView(r4);
        r8.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r8.setTextSize(1, 16.0f);
        if (LocaleController.isRTL == false) goto L12;
        int r9 = 5;
    L13:
        r8.setGravity(r9 | 48);
        FrameLayout r92 = new FrameLayout(r4);
        r5.setView(r92);
        AvatarDrawable r13 = new AvatarDrawable();
        r13.setTextSize(AndroidUtilities.dp(12.0f));
        BackupImageView r14 = new BackupImageView(r4);
        r14.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
        if (LocaleController.isRTL == false) goto L16;
        int r15 = 5;
    L17:
        r92.addView(r14, LayoutHelper.createFrame(40, 40.0f, r15 | 48, 22.0f, 5.0f, 22.0f, 0.0f));
        TextView r152 = new TextView(r4);
        r152.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r152.setTextSize(1, 20.0f);
        r152.setTypeface(AndroidUtilities.bold());
        r152.setLines(1);
        r152.setMaxLines(1);
        r152.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L20;
        int r42 = 5;
    L21:
        r152.setGravity(r42 | 16);
        r152.setEllipsize(TextUtils.TruncateAt.END);
        r152.setText(LocaleController.getString(R.string.ImportMessages));
        boolean r43 = LocaleController.isRTL;
        if (r43 == false) goto L24;
        int r16 = 5;
    L25:
        int r19 = r16 | 48;
        if (r43 == false) goto L28;
        int r11 = 21;
    L29:
        float r112 = r11;
        if (r43 == false) goto L32;
        int r44 = 76;
    L33:
        r92.addView(r152, LayoutHelper.createFrame(-1, -2.0f, r19, r112, 11.0f, r44, 0.0f));
        if (LocaleController.isRTL == false) goto L36;
        int r113 = 5;
    L37:
        r92.addView(r8, LayoutHelper.createFrame(-2, -2.0f, r113 | 48, 24.0f, 57.0f, 24.0f, 9.0f));
        if (r27 != null) goto L40;
        r13.setInfo(r3, r28);
        r14.setForUserOrChat(r28, r13);
    L47:
        r8.setText(AndroidUtilities.replaceTags(r26));
        r5.setPositiveButton(LocaleController.getString(R.string.Import), new AlertsCreator$$ExternalSyntheticLambda198(r29));
        r5.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r24.showDialog(r5.create());
        return;
    L40:
        if (UserObject.isReplyUser(r27) == false) goto L43;
        r13.setScaleSize(0.8f);
        r13.setAvatarType(12);
        r14.setImage(null, null, r13, r27);
        goto L47
    L43:
        if (r27.id != r6) goto L45;
        r13.setScaleSize(0.8f);
        r13.setAvatarType(1);
        r14.setImage(null, null, r13, r27);
        goto L47
    L45:
        r13.setScaleSize(1.0f);
        r13.setInfo(r3, r27);
        r14.setForUserOrChat(r27, r13);
        goto L47
    L36:
        r113 = 3;
        goto L37
    L32:
        r44 = 21;
        goto L33
    L28:
        r11 = 76;
        goto L29
    L24:
        r16 = 3;
        goto L25
    L20:
        r42 = 3;
        goto L21
    L16:
        r15 = 3;
        goto L17
    L12:
        r9 = 3;
        goto L13
    }

    public static AlertDialog.Builder createLanguageAlert(LaunchActivity r7, TLRPC.TL_langPackLanguage r8) {
        if (r8 != null) goto L5;
        return null;
    L5:
        r8.lang_code = r8.lang_code.replace(SignatureVisitor.SUPER, '_').toLowerCase();
        r8.plural_code = r8.plural_code.replace(SignatureVisitor.SUPER, '_').toLowerCase();
        String r1 = r8.base_lang_code;
        if (r1 == null) goto L8;
        r8.base_lang_code = r1.replace(SignatureVisitor.SUPER, '_').toLowerCase();
    L8:
        AlertDialog.Builder r12 = new AlertDialog.Builder(r7);
        if (LocaleController.getInstance().getCurrentLocaleInfo().shortName.equals(r8.lang_code) == false) goto L12;
        r12.setTitle(LocaleController.getString(R.string.Language));
        String r2 = LocaleController.formatString("LanguageSame", R.string.LanguageSame, new Object[]{r8.name});
        r12.setNegativeButton(LocaleController.getString(R.string.OK), null);
        r12.setNeutralButton(LocaleController.getString(R.string.SETTINGS), new AlertsCreator$$ExternalSyntheticLambda135(r7));
    L19:
        SpannableStringBuilder r0 = new SpannableStringBuilder(AndroidUtilities.replaceTags(r2));
        int r22 = TextUtils.indexOf(r0, '[');
        if (r22 == (-1)) goto L24;
        int r4 = r22 + 1;
        int r5 = TextUtils.indexOf(r0, ']', r4);
        if (r5 == (-1)) goto L26;
        r0.delete(r5, r5 + 1);
        r0.delete(r22, r4);
    L26:
        if (r22 == (-1)) goto L29;
        if (r5 == (-1)) goto L29;
        r0.setSpan(new AnonymousClass1(r8.translations_url, r12), r22, r5 - 1, 33);
    L29:
        TextView r82 = new TextView(r7);
        r82.setText(r0);
        r82.setTextSize(1, 16.0f);
        r82.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink));
        r82.setHighlightColor(Theme.getColor(Theme.key_dialogLinkSelection));
        r82.setPadding(AndroidUtilities.dp(23.0f), 0, AndroidUtilities.dp(23.0f), 0);
        r82.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r82.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r12.setView(r82);
        return r12;
    L24:
        r5 = -1;
        goto L26
    L12:
        if (r8.strings_count != 0) goto L14;
        r12.setTitle(LocaleController.getString(R.string.LanguageUnknownTitle));
        r2 = LocaleController.formatString("LanguageUnknownCustomAlert", R.string.LanguageUnknownCustomAlert, new Object[]{r8.name});
        r12.setNegativeButton(LocaleController.getString(R.string.OK), null);
        goto L19
    L14:
        r12.setTitle(LocaleController.getString(R.string.LanguageTitle));
        boolean r23 = r8.official;
        String r3 = r8.name;
        if (r23 == false) goto L17;
        r2 = LocaleController.formatString("LanguageAlert", R.string.LanguageAlert, new Object[]{r3, Integer.valueOf((int) Math.ceil((r8.translated_count / r8.strings_count) * 100.0f))});
    L18:
        r12.setPositiveButton(LocaleController.getString(R.string.Change), new AlertsCreator$$ExternalSyntheticLambda136(r8, r7));
        r12.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        goto L19
    L17:
        r2 = LocaleController.formatString("LanguageCustomAlert", R.string.LanguageCustomAlert, new Object[]{r3, Integer.valueOf((int) Math.ceil((r8.translated_count / r8.strings_count) * 100.0f))});
        goto L18
    }

    public static Dialog createLocationRequiredDialog(Context r4, boolean r5) {
        return new AlertDialog.Builder(r4).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionNoLocationFriends))).setTopAnimation(R.raw.permission_request_location, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new AlertsCreator$$ExternalSyntheticLambda167(r4)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create();
    }

    public static Dialog createLocationUpdateDialog(Activity r18, boolean r19, TLRPC.User r20, MessagesStorage.IntCallback r21, Theme.ResourcesProvider r22) {
        int[] r3 = new int[1];
        String[] r4 = {LocaleController.getString(R.string.SendLiveLocationFor15m), LocaleController.getString(R.string.SendLiveLocationFor1h), LocaleController.getString(R.string.SendLiveLocationFor8h), LocaleController.getString(R.string.SendLiveLocationForever)};
        LinearLayout r5 = new LinearLayout(r18);
        r5.setOrientation(1);
        r5.setPadding(0, 0, 0, AndroidUtilities.dp(4.0f));
        TextView r7 = new TextView(r18);
        if (r19 == false) goto L5;
        r7.setText(LocaleController.getString(R.string.LiveLocationAlertExpandMessage));
    L8:
        int r9 = Theme.key_dialogTextBlack;
        if (r22 == null) goto L11;
        int r92 = r22.getColorOrDefault(r9);
    L12:
        r7.setTextColor(r92);
        r7.setTextSize(1, 16.0f);
        int r10 = 3;
        if (LocaleController.isRTL == false) goto L15;
        int r93 = 5;
    L16:
        r7.setGravity(r93 | 48);
        if (LocaleController.isRTL == false) goto L19;
        r10 = 5;
    L19:
        int r13 = r10 | 48;
        if (r19 == false) goto L22;
        int r15 = 4;
    L23:
        r5.addView(r7, LayoutHelper.createLinear(-2, -2, r13, 24, r15, 24, 8));
        int r72 = 0;
    L24:
        if (r72 >= 4) goto L38;
        RadioColorCell r102 = new RadioColorCell(r18, r22);
        r102.heightDp = 42;
        r102.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r102.setTag(Integer.valueOf(r72));
        int r11 = Theme.key_radioBackground;
        if (r22 == null) goto L28;
        int r112 = r22.getColorOrDefault(r11);
    L29:
        int r12 = Theme.key_dialogRadioBackgroundChecked;
        if (r22 == null) goto L32;
        int r122 = r22.getColorOrDefault(r12);
    L33:
        r102.setCheckColor(r112, r122);
        String r113 = r4[r72];
        if (r3[0] != r72) goto L36;
        boolean r123 = true;
    L37:
        r102.setTextAndValue(r113, r123);
        r5.addView(r102);
        r102.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda94(r3, r5));
        r72 = r72 + 1;
        goto L24
    L36:
        r123 = false;
        goto L37
    L32:
        r122 = Theme.getColor(r12);
        goto L33
    L28:
        r112 = Theme.getColor(r11);
        goto L29
    L38:
        AlertDialog.Builder r2 = new AlertDialog.Builder(r18, r22);
        if (r19 == false) goto L41;
        r2.setTitle(LocaleController.getString(R.string.LiveLocationAlertExpandTitle));
    L45:
        r2.setView(r5);
        r2.setPositiveButton(LocaleController.getString(R.string.ShareFile), new AlertsCreator$$ExternalSyntheticLambda95(r3, r21));
        r2.setNeutralButton(LocaleController.getString(R.string.Cancel), null);
        return r2.create();
    L41:
        if (r22 == null) goto L43;
        int r1 = r22.getColorOrDefault(Theme.key_dialogTopBackground);
    L44:
        r2.setTopImage(new ShareLocationDrawable(r18, 0), r1);
        goto L45
    L43:
        r1 = Theme.getColor(Theme.key_dialogTopBackground);
        goto L44
    L22:
        r15 = 0;
        goto L23
    L15:
        r93 = 3;
        goto L16
    L11:
        r92 = Theme.getColor(r9);
        goto L12
    L5:
        if (r20 == null) goto L7;
        r7.setText(LocaleController.formatString(R.string.LiveLocationAlertPrivate, new Object[]{UserObject.getFirstName(r20)}));
        goto L8
    L7:
        r7.setText(LocaleController.getString(R.string.LiveLocationAlertGroup));
        goto L8
    }

    public static BottomSheet createMuteAlert(BaseFragment r12, long r13, long r15, Theme.ResourcesProvider r17) {
        if (r12 != null) goto L4;
        return null;
    L4:
        if (r12.getParentActivity() == null) goto L10;
        BottomSheet.Builder r0 = new BottomSheet.Builder(r12.getParentActivity(), false, r17);
        r0.setTitle(LocaleController.getString(R.string.Notifications), true);
        r0.setItems(new CharSequence[]{LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Hours", 1, new Object[0])}), LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Hours", 8, new Object[0])}), LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Days", 2, new Object[0])}), LocaleController.getString(R.string.MuteDisable)}, new AlertsCreator$$ExternalSyntheticLambda85(r13, r15, r12, r17));
        return r0.create();
    L10:
        return null;
    }

    public static BottomSheet createMuteAlert(BaseFragment r10, ArrayList<Long> r11, int r12, Theme.ResourcesProvider r13) {
        if (r10 != null) goto L4;
        return null;
    L4:
        if (r10.getParentActivity() == null) goto L10;
        BottomSheet.Builder r0 = new BottomSheet.Builder(r10.getParentActivity(), false, r13);
        r0.setTitle(LocaleController.getString(R.string.Notifications), true);
        r0.setItems(new CharSequence[]{LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Hours", 1, new Object[0])}), LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Hours", 8, new Object[0])}), LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Days", 2, new Object[0])}), LocaleController.getString(R.string.MuteDisable)}, new AlertsCreator$$ExternalSyntheticLambda131(r11, r12, r10, r13));
        return r0.create();
    L10:
        return null;
    }

    public static BottomSheet.Builder createMuteForPickerDialog(Context r18, Theme.ResourcesProvider r19, ScheduleDatePickerDelegate r20) {
        if (r18 != null) goto L6;
        return null;
    L6:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(r19);
        BottomSheet.Builder r3 = new BottomSheet.Builder(r18, false, r19);
        r3.setApplyBottomPadding(false);
        int[] r5 = {30, 60, 120, 180, 480, 1440, 2880, 4320, 5760, 7200, 8640, 10080, 20160, 30240, 44640, 89280, 133920, 178560, 223200, 267840, 525600};
        AnonymousClass52 r6 = new AnonymousClass52(r18, r19, r5);
        r6.setMinValue(0);
        r6.setMaxValue(20);
        r6.setTextColor(r2.textColor);
        r6.setValue(0);
        r6.setFormatter(new AlertsCreator$$ExternalSyntheticLambda127(r5));
        AnonymousClass53 r1 = new AnonymousClass53(r18, r6);
        r1.setOrientation(1);
        FrameLayout r8 = new FrameLayout(r18);
        r1.addView(r8, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r9 = new TextView(r18);
        r9.setText(LocaleController.getString(R.string.MuteForAlert));
        r9.setTextColor(r2.textColor);
        r9.setTextSize(1, 20.0f);
        r9.setTypeface(AndroidUtilities.bold());
        r8.addView(r9, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r9.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda128());
        LinearLayout r82 = new LinearLayout(r18);
        r82.setOrientation(0);
        r82.setWeightSum(1.0f);
        r1.addView(r82, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        AnonymousClass54 r10 = new AnonymousClass54(r18);
        r82.addView(r6, LayoutHelper.createLinear(0, 270, 1.0f));
        r6.setOnValueChangedListener(new AlertsCreator$$ExternalSyntheticLambda129());
        r10.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r10.setGravity(17);
        r10.setTextColor(r2.buttonTextColor);
        r10.setTextSize(1, 14.0f);
        r10.setTypeface(AndroidUtilities.bold());
        r10.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), r2.buttonBackgroundColor, r2.buttonBackgroundPressedColor));
        r10.setText(LocaleController.getString(R.string.AutoDeleteConfirm));
        r1.addView(r10, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r10.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda130(r5, r6, r20, r3));
        r3.setCustomView(r1);
        BottomSheet r0 = r3.show();
        r0.setBackgroundColor(r2.backgroundColor);
        r0.fixNavigationBar(r2.backgroundColor);
        return r3;
    }

    public static AlertDialog.Builder createNoAccessAlert(Context r6, String r7, String r8, Theme.ResourcesProvider r9) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r6);
        r0.setTitle(r7);
        HashMap r5 = new HashMap();
        int r62 = Theme.key_dialogTopBackground;
        r5.put("info1", Integer.valueOf(Theme.getColor(r62, r9)));
        r5.put("info2", Integer.valueOf(Theme.getColor(r62, r9)));
        r0.setTopAnimation(R.raw.not_available, 52, false, Theme.getColor(r62, r9), r5);
        r0.setTopAnimationIsNew(true);
        r0.setPositiveButton(LocaleController.getString(R.string.Close), null);
        r0.setMessage(r8);
        return r0;
    }

    public static BottomSheet.Builder createPollCloseDatePickerDialog(Context r26, long r27, ScheduleDatePickerDelegate r29, Runnable r30, ScheduleDatePickerColors r31, Theme.ResourcesProvider r32) {
        if (r26 != null) goto L6;
        return null;
    L6:
        int r6 = (int) MessagesController.getInstance(UserConfig.selectedAccount).config.pollClosePeriodMax.get(TimeUnit.SECONDS);
        BottomSheet.Builder r14 = new BottomSheet.Builder(r26, false, r32);
        r14.setApplyBottomPadding(false);
        NumberPicker r7 = new NumberPicker(r26, r32);
        r7.setTextColor(r31.textColor);
        r7.setTextOffset(AndroidUtilities.dp(10.0f));
        r7.setItemCount(5);
        AnonymousClass37 r9 = new AnonymousClass37(r26, r32);
        r9.setWrapSelectorWheel(true);
        r9.setAllItemsCount(24);
        r9.setItemCount(5);
        r9.setTextColor(r31.textColor);
        r9.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass38 r10 = new AnonymousClass38(r26, r32);
        r10.setWrapSelectorWheel(true);
        r10.setAllItemsCount(60);
        r10.setItemCount(5);
        r10.setTextColor(r31.textColor);
        r10.setTextOffset(-AndroidUtilities.dp(34.0f));
        FrameLayout r12 = new FrameLayout(r26);
        AnonymousClass39 r13 = new AnonymousClass39(r26, r7, r9, r10);
        r13.setOrientation(1);
        r12.addView(r13, LayoutHelper.createFrame(-1, -1.0f));
        r12.addView(new FrameLayout(r26), LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, 120.0f));
        FrameLayout r5 = new FrameLayout(r26);
        r13.addView(r5, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r8 = new TextView(r26);
        r8.setText(LocaleController.getString(R.string.StopPollDeadlineHeader));
        r8.setTextColor(r31.textColor);
        r8.setTextSize(1, 20.0f);
        r8.setTypeface(AndroidUtilities.bold());
        r5.addView(r8, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r8.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda240());
        LinearLayout r15 = new LinearLayout(r26);
        r15.setOrientation(0);
        r15.setWeightSum(1.0f);
        r13.addView(r15, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 23, 0, 23));
        TextView r102 = new TextView(r26);
        r102.setTextSize(1, 12.0f);
        r102.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, r32));
        r102.setGravity(17);
        Calendar r122 = Calendar.getInstance();
        AnonymousClass40 r52 = new AnonymousClass40(r26);
        r52.setText(LocaleController.getString(R.string.StopPollDeadlineButton));
        ScaleStateListAnimator.apply(r52, 0.02f, 1.2f);
        r15.addView(r7, LayoutHelper.createLinear(0, 270, 0.5f));
        r7.setMinValue(0);
        r7.setMaxValue(365);
        r7.setWrapSelectorWheel(false);
        r7.setFormatter(new AlertsCreator$$ExternalSyntheticLambda241());
        AlertsCreator$$ExternalSyntheticLambda242 r53 = new AlertsCreator$$ExternalSyntheticLambda242(r6, r7, r9, r10, r102);
        r7.setOnValueChangedListener(r53);
        r9.setMinValue(0);
        r9.setMaxValue(23);
        r15.addView(r9, LayoutHelper.createLinear(0, 270, 0.2f));
        r9.setFormatter(new AlertsCreator$$ExternalSyntheticLambda243());
        r9.setOnValueChangedListener(r53);
        r10.setMinValue(0);
        r10.setMaxValue(59);
        r10.setValue(0);
        r10.setFormatter(new AlertsCreator$$ExternalSyntheticLambda244());
        r15.addView(r10, LayoutHelper.createLinear(0, 270, 0.3f));
        r10.setOnValueChangedListener(r53);
        if (r27 > 0) goto L9;
    L13:
        TextView r82 = r102;
    L14:
        boolean[] r2 = {true};
        checkScheduleDate(null, null, r6, 3, r7, r9, r10);
        TextView r54 = r82;
        checkPollCloseCustomDeadline(r54, r7, r9, r10);
        r52.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r52.setGravity(17);
        r52.setTextColor(r31.buttonTextColor);
        r52.setTextSize(1, 14.0f);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setBackground(Theme.AdaptiveRipple.filledRect(r31.buttonBackgroundColor, new float[]{24.0f}));
        r13.addView(r52, LayoutHelper.createLinear(-1, 48, 83, 14, 15, 14, 16));
        r52.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda245(r2, r6, r7, r9, r10, r54, r122, r29, r14));
        r13.addView(r54, LayoutHelper.createLinear(-1, -2, 83, 14, 0, 14, 16));
        r14.setCustomView(r12);
        BottomSheet r0 = r14.show();
        r0.setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda246(r30, r2));
        r0.setBackgroundColor(r31.backgroundColor);
        r0.fixNavigationBar(r31.backgroundColor);
        return r14;
    L9:
        if (r27 == 2147483646) goto L13;
        long r3 = r27 * 1000;
        r122.setTimeInMillis(System.currentTimeMillis());
        r122.set(12, 0);
        r122.set(13, 0);
        r122.set(14, 0);
        r122.set(11, 0);
        r82 = r102;
        int r4 = (int) ((r3 - r122.getTimeInMillis()) / DurationKt.MILLIS_IN_DAY);
        r122.setTimeInMillis(r3);
        if (r4 < 0) goto L14;
        r10.setValue(r122.get(12));
        r9.setValue(r122.get(11));
        r7.setValue(r4);
        goto L14
    }

    public static Dialog createPopupSelectDialog(Activity r10, int r11, Runnable r12) {
        SharedPreferences r0 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
        int[] r2 = new int[1];
        if (r11 != 1) goto L5;
        r2[0] = r0.getInt("popupAll", 0);
    L8:
        String[] r02 = {LocaleController.getString(R.string.NoPopup), LocaleController.getString(R.string.OnlyWhenScreenOn), LocaleController.getString(R.string.OnlyWhenScreenOff), LocaleController.getString(R.string.AlwaysShowPopup)};
        LinearLayout r4 = new LinearLayout(r10);
        r4.setOrientation(1);
        AlertDialog.Builder r5 = new AlertDialog.Builder(r10);
        int r6 = 0;
    L10:
        if (r6 >= 4) goto L16;
        RadioColorCell r7 = new RadioColorCell(r10);
        r7.setTag(Integer.valueOf(r6));
        r7.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r7.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        String r8 = r02[r6];
        if (r2[0] != r6) goto L14;
        boolean r9 = true;
    L15:
        r7.setTextAndValue(r8, r9);
        r4.addView(r7);
        r7.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda134(r2, r11, r5, r12));
        r6 = r6 + 1;
        goto L10
    L14:
        r9 = false;
        goto L15
    L16:
        r5.setTitle(LocaleController.getString(R.string.PopupNotification));
        r5.setView(r4);
        r5.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        return r5.create();
    L5:
        if (r11 != 0) goto L7;
        r2[0] = r0.getInt("popupGroup", 0);
        goto L8
    L7:
        r2[0] = r0.getInt("popupChannel", 0);
        goto L8
    }

    public static Dialog createPrioritySelectDialog(Activity r8, long r9, int r11, int r12, Runnable r13) {
        return createPrioritySelectDialog(r8, r9, r11, r12, r13, null);
    }

    public static Dialog createPrioritySelectDialog(Activity r17, long r18, long r20, int r22, Runnable r23, Theme.ResourcesProvider r24) {
        long r3 = r18;
        int r7 = r22;
        SharedPreferences r8 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
        boolean r12 = true;
        int[] r2 = new int[1];
        if (r3 == 0) goto L16;
        int r1 = r8.getInt("priority_" + r3, 3);
        r2[0] = r1;
        if (r1 != 3) goto L7;
        r2[0] = 0;
    L14:
        String[] r13 = {LocaleController.getString(R.string.NotificationsPrioritySettings), LocaleController.getString(R.string.NotificationsPriorityLow), LocaleController.getString(R.string.NotificationsPriorityMedium), LocaleController.getString(R.string.NotificationsPriorityHigh), LocaleController.getString(R.string.NotificationsPriorityUrgent)};
    L15:
        String[] r14 = r13;
        LinearLayout r15 = new LinearLayout(r17);
        r15.setOrientation(1);
        AlertDialog.Builder r9 = new AlertDialog.Builder(r17, r24);
        int r16 = 0;
    L38:
        if (r16 >= r14.length) goto L44;
        RadioColorCell r5 = new RadioColorCell(r17, r24);
        r5.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r5.setTag(Integer.valueOf(r16));
        r5.setCheckColor(Theme.getColor(Theme.key_radioBackground, r24), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r24));
        String r6 = r14[r16];
        if (r2[0] != r16) goto L42;
        boolean r10 = r12;
    L43:
        r5.setTextAndValue(r6, r10);
        r15.addView(r5);
        r5.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda158(r2, r3, r20, r7, r8, r9, r23));
        r16 = r16 + 1;
        r3 = r18;
        r7 = r22;
        r12 = true;
        goto L38
    L42:
        r10 = false;
        goto L43
    L44:
        r9.setTitle(LocaleController.getString(R.string.NotificationsImportance));
        r9.setView(r15);
        r9.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        return r9.create();
    L7:
        if (r1 != 4) goto L9;
        r2[0] = 1;
        goto L14
    L9:
        if (r1 != 5) goto L11;
        r2[0] = 2;
        goto L14
    L11:
        if (r1 != 0) goto L13;
        r2[0] = 3;
        goto L14
    L13:
        r2[0] = 4;
        goto L14
    L16:
        if (r7 != 1) goto L18;
        r2[0] = r8.getInt("priority_messages", 1);
    L27:
        int r19 = r2[0];
        if (r19 != 4) goto L30;
        r2[0] = 0;
    L35:
        r13 = new String[]{LocaleController.getString(R.string.NotificationsPriorityLow), LocaleController.getString(R.string.NotificationsPriorityMedium), LocaleController.getString(R.string.NotificationsPriorityHigh), LocaleController.getString(R.string.NotificationsPriorityUrgent)};
        goto L15
    L30:
        if (r19 != 5) goto L32;
        r2[0] = 1;
        goto L35
    L32:
        if (r19 != 0) goto L34;
        r2[0] = 2;
        goto L35
    L34:
        r2[0] = 3;
        goto L35
    L18:
        if (r7 != 0) goto L20;
        r2[0] = r8.getInt("priority_group", 1);
        goto L27
    L20:
        if (r7 != 2) goto L22;
        r2[0] = r8.getInt("priority_channel", 1);
        goto L27
    L22:
        if (r7 != 3) goto L24;
        r2[0] = r8.getInt("priority_stories", 1);
        goto L27
    L24:
        if (r7 == 4) goto L26;
        if (r7 != 5) goto L27;
    L26:
        r2[0] = r8.getInt("priority_react", 1);
        goto L27
    }

    public static void createReportPhotoAlert(int r23, Context r24, long r25, TLRPC.Photo r27, Theme.ResourcesProvider r28) {
        if (r24 == null) goto L7;
        if (r27 == null) goto L8;
        AlertsCreator$$ExternalSyntheticLambda72 r0 = new AlertsCreator$$ExternalSyntheticLambda72(r23, r25, r27, r24, r28);
        BottomSheet.Builder r1 = new BottomSheet.Builder(r24, true, r28);
        r1.setTitle(LocaleController.getString(R.string.ReportProfilePhoto), true);
        r1.setItems(new CharSequence[]{LocaleController.getString(R.string.ReportChatSpam), LocaleController.getString(R.string.ReportChatFakeAccount), LocaleController.getString(R.string.ReportChatViolence), LocaleController.getString(R.string.ReportChatChild), LocaleController.getString(R.string.ReportChatIllegalDrugs), LocaleController.getString(R.string.ReportChatPersonalDetails), LocaleController.getString(R.string.ReportChatPornography), LocaleController.getString(R.string.ReportChatOther)}, new int[]{R.drawable.msg_clearcache, R.drawable.msg_report_fake, R.drawable.msg_report_violence, R.drawable.msg_block2, R.drawable.msg_report_drugs, R.drawable.msg_report_personal, R.drawable.msg_report_xxx, R.drawable.msg_report_other}, new AlertsCreator$$ExternalSyntheticLambda73(new int[]{0, 6, 1, 2, 3, 4, 5, 100}, r24, r28, r0));
        r1.show();
        return;
    L8:
        return;
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r10, long r11, long r13, int r15, ScheduleDatePickerDelegate r16, Runnable r17, Theme.ResourcesProvider r18) {
        return createScheduleDatePickerDialog(r10, r11, r13, r15, r16, r17, new ScheduleDatePickerColors(r18), r18);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r11, long r12, long r14, int r16, ScheduleDatePickerDelegate r17, Runnable r18, ScheduleDatePickerColors r19, Theme.ResourcesProvider r20) {
        return createScheduleDatePickerDialog(r11, r12, r14, r16, false, r17, r18, r19, r20);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r12, long r13, long r15, int r17, boolean r18, ScheduleDatePickerDelegate r19, Runnable r20, ScheduleDatePickerColors r21, Theme.ResourcesProvider r22) {
        return createScheduleDatePickerDialog(r12, null, r13, r15, r17, r18, r19, r20, r21, r22);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r10, long r11, long r13, ScheduleDatePickerDelegate r15, Runnable r16) {
        return createScheduleDatePickerDialog(r10, r11, r13, 0, r15, r16, new ScheduleDatePickerColors(null), null);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r7, long r8, ScheduleDatePickerDelegate r10) {
        return createScheduleDatePickerDialog(r7, r8, -1, r10, null);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r9, long r10, ScheduleDatePickerDelegate r12, Runnable r13, Theme.ResourcesProvider r14) {
        return createScheduleDatePickerDialog(r9, r10, -1, 0, r12, r13, r14);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r9, long r10, ScheduleDatePickerDelegate r12, Theme.ResourcesProvider r13) {
        return createScheduleDatePickerDialog(r9, r10, -1, 0, r12, null, r13);
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r10, long r11, ScheduleDatePickerDelegate r13, ScheduleDatePickerColors r14) {
        return createScheduleDatePickerDialog(r10, r11, -1, 0, r13, null, r14, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r21v11 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r27v3 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.view.ViewGroup, android.widget.FrameLayout] */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout, org.telegram.ui.Components.AlertsCreator$27] */
    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r50, String r51, long r52, long r54, int r56, boolean r57, ScheduleDatePickerDelegate r58, Runnable r59, ScheduleDatePickerColors r60, Theme.ResourcesProvider r61) {
        if (r50 != null) goto L5;
        return null;
    L5:
        int[] r13 = {r56};
        long r8 = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        BottomSheet.Builder r14 = new BottomSheet.Builder(r50, false, r61);
        r14.setApplyBottomPadding(false);
        NumberPicker r12 = new NumberPicker(r50, r61);
        r12.setTextColor(r60.textColor);
        r12.setTextOffset(AndroidUtilities.dp(10.0f));
        r12.setItemCount(5);
        AnonymousClass25 r82 = new AnonymousClass25(r50, r61);
        r82.setWrapSelectorWheel(true);
        r82.setAllItemsCount(24);
        r82.setItemCount(5);
        r82.setTextColor(r60.textColor);
        r82.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass26 r0 = new AnonymousClass26(r50, r61);
        r0.setWrapSelectorWheel(true);
        r0.setAllItemsCount(60);
        r0.setItemCount(5);
        r0.setTextColor(r60.textColor);
        r0.setTextOffset(-AndroidUtilities.dp(34.0f));
        ?? r4 = new FrameLayout(r50);
        ?? r5 = new AnonymousClass27(r50, r12, r82, r0);
        r5.setClipToPadding(false);
        r5.setClipChildren(false);
        r5.setOrientation(1);
        r4.addView(r5, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r1 = new FrameLayout(r50);
        r4.addView(r1, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, 120.0f));
        ViewGroup r3 = new FrameLayout(r50);
        r5.addView(r3, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r10 = new TextView(r50);
        if (TextUtils.isEmpty(r51) == true) goto L9;
        r10.setText(r51);
        ?? r21 = 0;
    L12:
        r10.setTextColor(r60.textColor);
        r10.setTextSize(1, 20.0f);
        r10.setTypeface(AndroidUtilities.bold());
        r3.addView(r10, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r10.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda19());
        boolean[] r102 = new boolean[1];
        r102[r21] = true;
        if (DialogObject.isUserDialog(r52) == true) goto L15;
    L30:
        AnonymousClass26 r25 = r0;
        FrameLayout r22 = r1;
        ViewGroup r7 = r3;
        View r36 = r4;
        Context r15 = r50;
        AnonymousClass25 r24 = r82;
        int[] r562 = r13;
        char r83 = 2;
        char r26 = 5;
        int r27 = 60;
        ViewGroup r132 = r5;
        ActionBarMenuItem r11 = null;
    L31:
        if (r11 == null) goto L34;
        r11.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda22(r11, r60));
        r11.setDelegate(new AlertsCreator$$ExternalSyntheticLambda23(r58, r102, r14));
    L34:
        RLottieImageView r02 = new RLottieImageView(r15);
        RLottieDrawable r29 = new RLottieDrawable(R.raw.notify_toggle, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), true, null);
        r29.setAllowDecodeSingleFrame(true);
        r29.setPlayInDirectionOfCustomEndFrame(true);
        r29.start();
        r29.setCurrentFrame(40);
        r29.setCustomEndFrame(40);
        r02.setScaleType(ImageView.ScaleType.CENTER);
        r02.setAnimation(r29);
        r02.setColorFilter(new PorterDuffColorFilter(r60.textColor, PorterDuff.Mode.SRC_IN));
        r02.setBackground(Theme.createSelectorDrawable(r60.iconSelectorColor, 1));
        if (r11 == null) goto L37;
        int r42 = 42;
    L38:
        r7.addView(r02, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 8.0f, r42 + 8, 0.0f));
        LinearLayout r43 = new LinearLayout(r15);
        r43.setOrientation(0);
        r43.setWeightSum(1.0f);
        r132.addView(r43, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        Calendar r103 = Calendar.getInstance();
        AnonymousClass28 r72 = new AnonymousClass28(r15);
        ActionBarMenuItem r292 = r11;
        r43.addView(r12, LayoutHelper.createLinear(0, 270, 0.5f));
        r12.setMinValue(0);
        r12.setMaxValue(365);
        r12.setWrapSelectorWheel(false);
        r12.setFormatter(new AlertsCreator$$ExternalSyntheticLambda24());
        char r23 = r83;
        AnonymousClass25 r84 = r24;
        AnonymousClass26 r9 = r25;
        AlertsCreator$$ExternalSyntheticLambda25 r03 = new AlertsCreator$$ExternalSyntheticLambda25(r72, r51, r8, r52, r12, r84, r9);
        r12.setOnValueChangedListener(r03);
        r84.setMinValue(0);
        r84.setMaxValue(23);
        r43.addView(r84, LayoutHelper.createLinear(0, 270, 0.2f));
        r84.setFormatter(new AlertsCreator$$ExternalSyntheticLambda26());
        r84.setOnValueChangedListener(r03);
        r9.setMinValue(0);
        r9.setMaxValue(59);
        r9.setValue(0);
        r9.setFormatter(new AlertsCreator$$ExternalSyntheticLambda27());
        r43.addView(r9, LayoutHelper.createLinear(0, 270, 0.3f));
        r9.setOnValueChangedListener(r03);
        if (r54 > 0) goto L41;
    L45:
        boolean[] r6 = {true};
        if (r51 == null) goto L49;
        int r2 = 3;
    L52:
        checkScheduleDate(r72, null, r2, r12, r84, r9);
        boolean r16 = ConnectionsManager.getInstance(UserConfig.selectedAccount).isTestBackend();
        if (r16 == false) goto L55;
        char r55 = '\t';
        int r28 = 10;
        int[] r53 = new int[10];
        r53[0] = 0;
        r53[1] = r27;
        r53[r23] = 300;
        r53[3] = 86400;
        r53[4] = 604800;
        r53[r26] = 1209600;
        r53[6] = 2592000;
        r53[7] = 7862400;
        r53[8] = 15724800;
        r53[9] = 31536000;
        ?? r272 = 3;
        int[] r112 = r53;
        int r510 = 8;
    L56:
        if (r16 == false) goto L58;
        String[] r17 = new String[r28];
        r17[0] = LocaleController.getString(R.string.MessageScheduledRepeatOptionNever);
        r17[1] = "Every minute";
        r17[r23] = "Every 5 minutes";
        r17[r272] = LocaleController.getString(R.string.MessageScheduledRepeatOptionDaily);
        r17[4] = LocaleController.getString(R.string.MessageScheduledRepeatOptionWeekly);
        r17[r26] = LocaleController.getString(R.string.MessageScheduledRepeatOptionBiweekly);
        r17[6] = LocaleController.getString(R.string.MessageScheduledRepeatOptionMonthly);
        r17[7] = LocaleController.getString(R.string.MessageScheduledRepeatOption3Monthly);
        r17[r510] = LocaleController.getString(R.string.MessageScheduledRepeatOption6Monthly);
        r17[r55] = LocaleController.getString(R.string.MessageScheduledRepeatOptionYearly);
    L59:
        if (r57 == true) goto L61;
        FrameLayout r122 = new FrameLayout(r50);
        float r542 = 14.0f;
        int r511 = r60.textColor;
        NumberPicker r232 = r12;
        int r210 = Theme.blendOver(r60.backgroundColor, Theme.multAlpha(r511, 0.075f));
        AnonymousClass25 r242 = r84;
        int r32 = Theme.multAlpha(r60.textColor, 0.1f);
        TextView r44 = new TextView(r50);
        boolean[] r252 = r6;
        long r262 = r8;
        r44.setTextSize(1, 13.0f);
        r44.setTextColor(r511);
        r44.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r44.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(14.0f), r210, Theme.blendOver(r210, r32)));
        r44.setGravity(17);
        int[] r73 = r562;
        AlertsCreator$$ExternalSyntheticLambda28 r211 = new AlertsCreator$$ExternalSyntheticLambda28(r112, r73, r17, r44);
        r211.run();
        r122.addView(r44, LayoutHelper.createFrame(-2, 28.0f, 1, 32.0f, 4.0f, 32.0f, 5.0f));
        r132.addView(r122, LayoutHelper.createLinear(-1, -2));
        AlertsCreator$$ExternalSyntheticLambda28 r282 = r211;
        FrameLayout r19 = r122;
    L62:
        r72.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r72.setGravity(17);
        r72.setTextColor(r60.buttonTextColor);
        r72.setTextSize(1, r542);
        r72.setTypeface(AndroidUtilities.bold());
        r72.setBackground(Theme.AdaptiveRipple.filledRect(r60.buttonBackgroundColor, new float[]{24.0f}));
        r132.addView(r72, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        String[] r18 = r17;
        TextView r47 = r44;
        int[] r133 = r73;
        int[] r172 = r112;
        boolean[] r110 = r252;
        r72.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda29(r110, r51, r262, r52, r232, r242, r9, r103, r58, r102, r133, r14));
        r14.setCustomView(r36);
        BottomSheet r104 = r14.show();
        r104.setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda30(r59, r110));
        r104.setBackgroundColor(r60.backgroundColor);
        r104.fixNavigationBar(r60.backgroundColor);
        if (r47 == null) goto L65;
        r47.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda20(r22, r61, r104, r19, r172, r18, r133, r282));
    L65:
        r02.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda21(r102, r29, new HintView2[1], r52, r50, r262, r292, r104));
        return r14;
    L61:
        r232 = r12;
        r242 = r84;
        r252 = r6;
        r262 = r8;
        r542 = 14.0f;
        r73 = r562;
        r44 = null;
        r19 = null;
        r282 = null;
        goto L62
    L58:
        r17 = new String[r510];
        r17[0] = LocaleController.getString(R.string.MessageScheduledRepeatOptionNever);
        r17[1] = LocaleController.getString(R.string.MessageScheduledRepeatOptionDaily);
        r17[r23] = LocaleController.getString(R.string.MessageScheduledRepeatOptionWeekly);
        r17[r272] = LocaleController.getString(R.string.MessageScheduledRepeatOptionBiweekly);
        r17[4] = LocaleController.getString(R.string.MessageScheduledRepeatOptionMonthly);
        r17[r26] = LocaleController.getString(R.string.MessageScheduledRepeatOption3Monthly);
        r17[6] = LocaleController.getString(R.string.MessageScheduledRepeatOption6Monthly);
        r17[7] = LocaleController.getString(R.string.MessageScheduledRepeatOptionYearly);
        goto L59
    L55:
        r272 = 3;
        r55 = '\t';
        r28 = 10;
        r510 = 8;
        r112 = new int[8];
        r112[0] = 0;
        r112[1] = 86400;
        r112[r23] = 604800;
        r112[3] = 1209600;
        r112[4] = 2592000;
        r112[r26] = 7862400;
        r112[6] = 15724800;
        r112[7] = 31536000;
        goto L56
    L49:
        if (r8 != r52) goto L51;
        r2 = 1;
        goto L52
    L51:
        r2 = 0;
        goto L52
    L41:
        if (r54 == 2147483646) goto L45;
        long r111 = 1000 * r54;
        r103.setTimeInMillis(System.currentTimeMillis());
        r103.set(12, 0);
        r103.set(13, 0);
        r103.set(14, 0);
        r103.set(11, 0);
        int r113 = (int) ((r111 - r103.getTimeInMillis()) / DurationKt.MILLIS_IN_DAY);
        r103.setTimeInMillis(r111);
        if (r113 < 0) goto L45;
        r9.setValue(r103.get(12));
        r84.setValue(r103.get(11));
        r12.setValue(r113);
        goto L45
    L37:
        r42 = 0;
        goto L38
    L15:
        if (r52 == r8) goto L30;
        TLRPC.User r212 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r52));
        if (r212 == null) goto L30;
        if (r212.bot == true) goto L30;
        TLRPC.UserStatus r114 = r212.status;
        if (r114 == null) goto L30;
        if (r114.expires <= 0) goto L30;
        String r213 = UserObject.getFirstName(r212);
        if (r213.length() <= 10) goto L28;
        boolean r115 = r21;
        r213 = r213.substring(r115 ? 1 : 0, 10).concat("…");
    L27:
        String r92 = r213;
        r25 = r0;
        r22 = r1;
        r15 = r50;
        r562 = r13;
        r36 = r4;
        r132 = r5;
        r7 = r3;
        r26 = 5;
        r27 = 60;
        r24 = r82;
        r83 = 2;
        ActionBarMenuItem r04 = new ActionBarMenuItem(r15, null, 0, r60.textColor, false, r61);
        r04.setLongClickEnabled(r115);
        r04.setSubMenuOpenSide(2);
        r04.setIcon(R.drawable.ic_ab_other);
        r04.setBackground(Theme.createSelectorDrawable(r60.iconSelectorColor, 1));
        r7.addView(r04, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 8.0f, 5.0f, 0.0f));
        r04.addSubItem(1, LocaleController.formatString(R.string.ScheduleWhenOnline, new Object[]{r92}));
        r04.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        r11 = r04;
        goto L31
    L28:
        r115 = r21;
        goto L27
    L9:
        if (r52 != r8) goto L11;
        r21 = 0;
        r10.setText(LocaleController.getString(R.string.SetReminder));
        goto L12
    L11:
        r21 = 0;
        r10.setText(LocaleController.getString(R.string.ScheduleMessage));
        goto L12
    }

    public static BottomSheet.Builder createScheduleDatePickerDialog(Context r12, String r13, long r14, long r16, boolean r18, ScheduleDatePickerDelegate r19, Runnable r20) {
        return createScheduleDatePickerDialog(r12, r13, r14, r16, 0, r18, r19, r20, new ScheduleDatePickerColors(null), null);
    }

    public static AlertDialog.Builder createSimpleAlert(Context r1, String r2) {
        return createSimpleAlert(r1, null, r2);
    }

    public static AlertDialog.Builder createSimpleAlert(Context r1, String r2, String r3) {
        return createSimpleAlert(r1, r2, r3, null);
    }

    public static AlertDialog.Builder createSimpleAlert(Context r2, String r3, String r4, String r5, Runnable r6, Theme.ResourcesProvider r7) {
        if (r2 == null) goto L15;
        if (r4 == null) goto L15;
        AlertDialog.Builder r1 = new AlertDialog.Builder(r2, r7);
        if (r3 != null) goto L9;
        r3 = LocaleController.getString(R.string.AppName);
    L9:
        r1.setTitle(r3);
        r1.setMessage(r4);
        if (r5 != null) goto L13;
        r1.setPositiveButton(LocaleController.getString(R.string.OK), null);
        return r1;
    L13:
        r1.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r1.setPositiveButton(r5, new AlertsCreator$$ExternalSyntheticLambda9(r6));
        return r1;
    L15:
        return null;
    }

    public static AlertDialog.Builder createSimpleAlert(Context r6, String r7, String r8, Theme.ResourcesProvider r9) {
        return createSimpleAlert(r6, r7, r8, null, null, r9);
    }

    public static AlertDialog createSimpleConfirmAlert(Context r1, Theme.ResourcesProvider r2, String r3, CharSequence r4, String r5, Runnable r6) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r1, r2);
        r0.setTitle(r3);
        r0.setMessage(r4);
        r0.setPositiveButton(r5, new AlertsCreator$$ExternalSyntheticLambda122(r6));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        return r0.create();
    }

    public static ActionBarPopupWindow createSimplePopup(BaseFragment r4, View r5, View r6, float r7, float r8) {
        if (r4 == null) goto L13;
        if (r6 == null) goto L18;
        if (r5 == null) goto L19;
        ActionBarPopupWindow r42 = new ActionBarPopupWindow(r5, -2, -2);
        r42.setPauseNotifications(true);
        r42.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        r42.setOutsideTouchable(true);
        r42.setClippingEnabled(true);
        r42.setAnimationStyle(R.style.PopupContextAnimation);
        r42.setFocusable(true);
        r5.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        r42.setInputMethodMode(2);
        r42.getContentView().setFocusableInTouchMode(true);
        float r0 = 0.0f;
        View r2 = r6;
        float r1 = 0.0f;
    L8:
        if (r2 == r6.getRootView()) goto L11;
        r0 = r0 + r2.getX();
        r1 = r1 + r2.getY();
        r2 = (View) r2.getParent();
        if (r2 != null) goto L8;
    L11:
        r42.showAtLocation(r6.getRootView(), 0, (int) ((r0 + r7) - (r5.getMeasuredWidth() / 2.0f)), (int) ((r1 + r8) - (r5.getMeasuredHeight() / 2.0f)));
        r42.dimBehind();
        return r42;
    L19:
        return null;
    L18:
        return null;
    L13:
        return null;
    }

    public static void createSimpleTextInputAlert(Context r18, BaseFragment r19, String r20, String r21, String r22, String r23, int r24, String r25, Theme.ResourcesProvider r26, MessagesStorage.StringCallback r27) {
        Activity r9 = AndroidUtilities.findActivity(r18);
        if (r9 == null) goto L6;
        View r3 = r9.getCurrentFocus();
    L5:
        View r7 = r3;
        AlertDialog[] r6 = new AlertDialog[1];
        AlertDialog.Builder r11 = new AlertDialog.Builder(r18, r26);
        if (r20 != null) goto L10;
        CharSequence r32 = LocaleController.getString(R.string.AppName);
    L11:
        r11.setTitle(r32);
        r11.setMessage(r21);
        EditTextBoldCursor r33 = new EditTextBoldCursor(r18);
        r33.setTextSize(1, 16.0f);
        int r12 = Theme.key_dialogTextBlack;
        r33.setTextColor(Theme.getColor(r12, r26));
        r33.setHintTextColor(Theme.getColor(Theme.key_groupcreate_hintText, r26));
        r33.setHint(r22);
        r33.setFocusable(true);
        r33.setInputType(147457);
        r33.setImeOptions(6);
        r33.setMaxLines(10);
        r33.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(11.0f));
        r33.setCursorWidth(1.5f);
        r33.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4, r26));
        if (r23 == null) goto L14;
        r33.setText(r23);
    L14:
        r33.setOnEditorActionListener(new AlertsCreator$$ExternalSyntheticLambda200(r33, r24, r27, r6, r7));
        r33.addTextChangedListener(new AnonymousClass2(r24, r33));
        GradientDrawable r2 = new GradientDrawable();
        r2.setCornerRadius(AndroidUtilities.dp(22.0f));
        r2.setColor(Theme.multAlpha(Theme.getColor(r12, r26), 0.06f));
        r33.setBackground(r2);
        LinearLayout r28 = new LinearLayout(r18);
        r28.setOrientation(1);
        r28.addView(r33, LayoutHelper.createLinear(-1, -2, 20.0f, 9.0f, 20.0f, 9.0f));
        r11.makeCustomMaxHeight();
        r11.setView(r28);
        r11.setWidth(Math.min(AndroidUtilities.dp(320.0f), (AndroidUtilities.displaySize.x * 85) / 100));
        r11.setPositiveButton(r25, new AlertsCreator$$ExternalSyntheticLambda201(r33, r24, r27));
        r11.setNegativeButton(LocaleController.getString(R.string.Cancel), new AlertsCreator$$ExternalSyntheticLambda202());
        r6[0] = r11.create();
        if (r19 == null) goto L17;
        AndroidUtilities.requestAdjustNothing(r9, r19.getClassGuid());
    L17:
        r6[0].setDismissDialogByButtons(false);
        r6[0].setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda203(r33, r19, r9));
        r6[0].setOnShowListener(new AlertsCreator$$ExternalSyntheticLambda204(r33));
        r6[0].show();
        r33.setSelection(r33.getText().length());
        return;
    L10:
        r32 = r20;
        goto L11
    L6:
        r3 = null;
        goto L5
    }

    public static Dialog createSingleChoiceDialog(Activity r8, String[] r9, String r10, int r11, DialogInterface.OnClickListener r12) {
        LinearLayout r0 = new LinearLayout(r8);
        r0.setOrientation(1);
        AlertDialog.Builder r2 = new AlertDialog.Builder(r8);
        int r4 = 0;
    L4:
        if (r4 >= r9.length) goto L10;
        RadioColorCell r5 = new RadioColorCell(r8);
        r5.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r5.setTag(Integer.valueOf(r4));
        r5.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        String r6 = r9[r4];
        if (r11 != r4) goto L8;
        boolean r7 = true;
    L9:
        r5.setTextAndValue(r6, r7);
        r0.addView(r5);
        r5.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda143(r2, r12));
        r4 = r4 + 1;
        goto L4
    L8:
        r7 = false;
        goto L9
    L10:
        r2.setTitle(r10);
        r2.setView(r0);
        r2.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        return r2.create();
    }

    public static BottomSheet.Builder createSoundFrequencyPickerDialog(Context r19, int r20, int r21, SoundFrequencyDelegate r22, Theme.ResourcesProvider r23) {
        if (r19 != null) goto L6;
        return null;
    L6:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(r23);
        BottomSheet.Builder r3 = new BottomSheet.Builder(r19, false, r23);
        r3.setApplyBottomPadding(false);
        AnonymousClass48 r5 = new AnonymousClass48(r19, r23);
        r5.setMinValue(0);
        r5.setMaxValue(10);
        r5.setTextColor(r2.textColor);
        r5.setValue(r20 - 1);
        r5.setWrapSelectorWheel(false);
        r5.setFormatter(new AlertsCreator$$ExternalSyntheticLambda231());
        AnonymousClass49 r8 = new AnonymousClass49(r19, r23);
        r8.setMinValue(0);
        r8.setMaxValue(10);
        r8.setTextColor(r2.textColor);
        r8.setValue((r21 / 60) - 1);
        r8.setWrapSelectorWheel(false);
        r8.setFormatter(new AlertsCreator$$ExternalSyntheticLambda232());
        NumberPicker r6 = new NumberPicker(r19, r23);
        r6.setMinValue(0);
        r6.setMaxValue(0);
        r6.setTextColor(r2.textColor);
        r6.setValue(0);
        r6.setWrapSelectorWheel(false);
        r6.setFormatter(new AlertsCreator$$ExternalSyntheticLambda233());
        AnonymousClass50 r1 = new AnonymousClass50(r19, r5, r8, r6);
        r1.setOrientation(1);
        FrameLayout r9 = new FrameLayout(r19);
        r1.addView(r9, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r10 = new TextView(r19);
        r10.setText(LocaleController.getString(R.string.NotfificationsFrequencyTitle));
        r10.setTextColor(r2.textColor);
        r10.setTextSize(1, 20.0f);
        r10.setTypeface(AndroidUtilities.bold());
        r9.addView(r10, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r10.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda234());
        LinearLayout r92 = new LinearLayout(r19);
        r92.setOrientation(0);
        r92.setWeightSum(1.0f);
        r1.addView(r92, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        AnonymousClass51 r102 = new AnonymousClass51(r19);
        r92.addView(r5, LayoutHelper.createLinear(0, 270, 0.4f));
        r92.addView(r6, LayoutHelper.createLinear(0, -2, 0.2f, 16));
        r92.addView(r8, LayoutHelper.createLinear(0, 270, 0.4f));
        r102.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r102.setGravity(17);
        r102.setTextColor(r2.buttonTextColor);
        r102.setTextSize(1, 14.0f);
        r102.setTypeface(AndroidUtilities.bold());
        r102.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), r2.buttonBackgroundColor, r2.buttonBackgroundPressedColor));
        r102.setText(LocaleController.getString(R.string.AutoDeleteConfirm));
        r1.addView(r102, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        AlertsCreator$$ExternalSyntheticLambda235 r0 = new AlertsCreator$$ExternalSyntheticLambda235();
        r5.setOnValueChangedListener(r0);
        r8.setOnValueChangedListener(r0);
        r102.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda236(r5, r8, r22, r3));
        r3.setCustomView(r1);
        BottomSheet r02 = r3.show();
        r02.setBackgroundColor(r2.backgroundColor);
        r02.fixNavigationBar(r2.backgroundColor);
        return r3;
    }

    public static BottomSheet.Builder createStatusUntilDatePickerDialog(Context r25, long r26, StatusUntilDatePickerDelegate r28) {
        if (r25 != null) goto L5;
        return null;
    L5:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(null);
        BottomSheet.Builder r9 = new BottomSheet.Builder(r25, false);
        r9.setApplyBottomPadding(false);
        NumberPicker r4 = new NumberPicker(r25);
        r4.setTextColor(r2.textColor);
        r4.setTextOffset(AndroidUtilities.dp(10.0f));
        r4.setItemCount(5);
        AnonymousClass41 r7 = new AnonymousClass41(r25);
        r7.setItemCount(5);
        r7.setTextColor(r2.textColor);
        r7.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass42 r6 = new AnonymousClass42(r25);
        r6.setItemCount(5);
        r6.setTextColor(r2.textColor);
        r6.setTextOffset(-AndroidUtilities.dp(34.0f));
        AnonymousClass43 r11 = new AnonymousClass43(r25, r4, r7, r6);
        r11.setOrientation(1);
        FrameLayout r3 = new FrameLayout(r25);
        r11.addView(r3, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        TextView r5 = new TextView(r25);
        r5.setText(LocaleController.getString(R.string.SetEmojiStatusUntilTitle));
        r5.setTextColor(r2.textColor);
        r5.setTextSize(1, 20.0f);
        r5.setTypeface(AndroidUtilities.bold());
        r3.addView(r5, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r5.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda212());
        LinearLayout r32 = new LinearLayout(r25);
        r32.setOrientation(0);
        r32.setWeightSum(1.0f);
        r11.addView(r32, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        Calendar r13 = Calendar.getInstance();
        AnonymousClass44 r14 = new AnonymousClass44(r25);
        r32.addView(r4, LayoutHelper.createLinear(0, 270, 0.5f));
        r4.setMinValue(0);
        r4.setMaxValue(365);
        r4.setWrapSelectorWheel(false);
        r4.setFormatter(new AlertsCreator$$ExternalSyntheticLambda213());
        AlertsCreator$$ExternalSyntheticLambda214 r0 = new AlertsCreator$$ExternalSyntheticLambda214(r4, r7, r6);
        r4.setOnValueChangedListener(r0);
        r7.setMinValue(0);
        r7.setMaxValue(23);
        r32.addView(r7, LayoutHelper.createLinear(0, 270, 0.2f));
        r7.setFormatter(new AlertsCreator$$ExternalSyntheticLambda215());
        r7.setOnValueChangedListener(r0);
        r6.setMinValue(0);
        r6.setMaxValue(59);
        r6.setValue(0);
        r6.setFormatter(new AlertsCreator$$ExternalSyntheticLambda216());
        r32.addView(r6, LayoutHelper.createLinear(0, 270, 0.3f));
        r6.setOnValueChangedListener(r0);
        if (r26 > 0) goto L8;
    L13:
        ScheduleDatePickerColors r16 = r2;
        float r252 = 34.0f;
        AnonymousClass43 r17 = r11;
    L14:
        checkScheduleDate(null, null, 0, r4, r7, r6);
        r14.setPadding(AndroidUtilities.dp(r252), 0, AndroidUtilities.dp(r252), 0);
        r14.setGravity(17);
        ScheduleDatePickerColors r02 = r16;
        r14.setTextColor(r02.buttonTextColor);
        r14.setTextSize(1, 14.0f);
        r14.setTypeface(AndroidUtilities.bold());
        r14.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), r02.buttonBackgroundColor, r02.buttonBackgroundPressedColor));
        r14.setText(LocaleController.getString(R.string.SetEmojiStatusUntilButton));
        AnonymousClass43 r22 = r17;
        r22.addView(r14, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 16));
        r14.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda217(r4, r7, r6, r13, r28, r9));
        r9.setCustomView(r22);
        BottomSheet r1 = r9.show();
        r1.setBackgroundColor(r02.backgroundColor);
        r1.fixNavigationBar(r02.backgroundColor);
        return r9;
    L8:
        if (r26 == 2147483646) goto L13;
        r17 = r11;
        long r10 = r26 * 1000;
        r252 = 34.0f;
        r13.setTimeInMillis(System.currentTimeMillis());
        r13.set(12, 0);
        r13.set(13, 0);
        r13.set(14, 0);
        r13.set(11, 0);
        r16 = r2;
        int r12 = (int) ((r10 - r13.getTimeInMillis()) / DurationKt.MILLIS_IN_DAY);
        r13.setTimeInMillis(r10);
        if (r12 < 0) goto L14;
        r6.setValue(r13.get(12));
        r7.setValue(r13.get(11));
        r4.setValue(r12);
        goto L14
    }

    public static void createStoriesAlbumEnterName(Context r10, BaseFragment r11, String r12, String r13, String r14, String r15, String r16, Theme.ResourcesProvider r17, MessagesStorage.StringCallback r18) {
        createSimpleTextInputAlert(r10, r11, r12, r13, r14, r15, 12, r16, r17, r18);
    }

    public static void createStoriesAlbumEnterNameForCreate(Context r10, BaseFragment r11, Theme.ResourcesProvider r12, MessagesStorage.StringCallback r13) {
        createStoriesAlbumEnterName(r10, r11, LocaleController.getString(R.string.StoriesAlbumCreateNew), LocaleController.getString(R.string.StoriesAlbumAddHint), LocaleController.getString(R.string.StoriesAlbumTitleInputHint), null, LocaleController.getString(R.string.Create), r12, r13);
    }

    public static void createStoriesAlbumEnterNameForRename(Context r10, BaseFragment r11, String r12, Theme.ResourcesProvider r13, MessagesStorage.StringCallback r14) {
        createStoriesAlbumEnterName(r10, r11, LocaleController.getString(R.string.StoriesAlbumRename), LocaleController.getString(R.string.StoriesAlbumRenameHint), LocaleController.getString(R.string.StoriesAlbumTitleInputHint), r12, LocaleController.getString(R.string.Rename), r13, r14);
    }

    public static BottomSheet.Builder createSuggestedMessageDatePickerDialog(Context r30, long r31, ScheduleDatePickerDelegate r33, Runnable r34, ScheduleDatePickerColors r35, Theme.ResourcesProvider r36, int r37) {
        if (r30 != null) goto L6;
        return null;
    L6:
        BottomSheet.Builder r4 = new BottomSheet.Builder(r30, false, r36);
        r4.setApplyBottomPadding(false);
        NumberPicker r13 = new NumberPicker(r30, r36);
        r13.setTextColor(r35.textColor);
        r13.setTextOffset(AndroidUtilities.dp(10.0f));
        r13.setItemCount(5);
        AnonymousClass64 r14 = new AnonymousClass64(r30, r36);
        r14.setWrapSelectorWheel(true);
        r14.setAllItemsCount(24);
        r14.setItemCount(5);
        r14.setTextColor(r35.textColor);
        r14.setTextOffset(-AndroidUtilities.dp(10.0f));
        AnonymousClass65 r15 = new AnonymousClass65(r30, r36);
        r15.setWrapSelectorWheel(true);
        r15.setAllItemsCount(60);
        r15.setItemCount(5);
        r15.setTextColor(r35.textColor);
        r15.setTextOffset(-AndroidUtilities.dp(34.0f));
        AnonymousClass66 r6 = new AnonymousClass66(r30, r13, r14, r15);
        r6.setOrientation(1);
        LinearLayout r9 = new LinearLayout(r30);
        r9.setOrientation(1);
        r6.addView(r9, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 22, 4));
        TextView r10 = new TextView(r30);
        if (r37 != 1) goto L9;
        int r11 = R.string.SuggestedPostAcceptTitle;
    L10:
        r10.setText(LocaleController.getString(r11));
        r10.setTextColor(r35.textColor);
        r10.setTextSize(1, 20.0f);
        r10.setTypeface(AndroidUtilities.bold());
        r9.addView(r10, LayoutHelper.createLinear(-2, -2, 51, 0, 12, 0, 0));
        r10.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda220());
        TextView r102 = new TextView(r30);
        r102.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r36));
        r102.setTextSize(1, 14.0f);
        r102.setText(LocaleController.getString(R.string.PostSuggestionsAddTimeHint));
        r9.addView(r102, LayoutHelper.createLinear(-2, -2, 51, 0, 2, 0, 0));
        r102.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda221());
        LinearLayout r92 = new LinearLayout(r30);
        r92.setOrientation(0);
        r92.setWeightSum(1.0f);
        r6.addView(r92, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        long r103 = System.currentTimeMillis();
        Calendar r12 = Calendar.getInstance();
        r12.setTimeInMillis(r103);
        int r104 = r12.get(1);
        AppGlobalConfig.ConfigTime r112 = MessagesController.getInstance(UserConfig.selectedAccount).config.starsSuggestedPostFutureMin;
        TimeUnit r7 = TimeUnit.SECONDS;
        long r18 = r112.get(r7) * 2;
        long r20 = MessagesController.getInstance(UserConfig.selectedAccount).config.starsSuggestedPostFutureMax.get(r7) - 86400;
        AnonymousClass67 r72 = new AnonymousClass67(r30);
        r92.addView(r13, LayoutHelper.createLinear(0, 270, 0.5f));
        r13.setMinValue(0);
        r13.setMaxValue(365);
        r13.setWrapSelectorWheel(false);
        r13.setFormatter(new AlertsCreator$$ExternalSyntheticLambda222(r104));
        if (r37 != 1) goto L14;
        int r105 = 5;
    L15:
        int r122 = r105;
        AlertsCreator$$ExternalSyntheticLambda223 r62 = new AlertsCreator$$ExternalSyntheticLambda223(r72, r18, r20, r122, r13, r14, r15);
        r13.setOnValueChangedListener(r62);
        r14.setMinValue(0);
        r14.setMaxValue(23);
        r92.addView(r14, LayoutHelper.createLinear(0, 270, 0.2f));
        r14.setFormatter(new AlertsCreator$$ExternalSyntheticLambda224());
        r14.setOnValueChangedListener(r62);
        r15.setMinValue(0);
        r15.setMaxValue(59);
        r15.setValue(0);
        r15.setFormatter(new AlertsCreator$$ExternalSyntheticLambda225());
        r92.addView(r15, LayoutHelper.createLinear(0, 270, 0.3f));
        r15.setOnValueChangedListener(r62);
        if (r31 > 0) goto L18;
    L23:
        long r202 = r18;
        Calendar r42 = r12;
    L24:
        boolean[] r5 = {true};
        long r8 = r202;
        checkScheduleDate(r72, null, r8, r20, r122, r13, r14, r15);
        r72.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r72.setGravity(17);
        r72.setTextColor(r35.buttonTextColor);
        r72.setTextSize(1, 14.0f);
        r72.setTypeface(AndroidUtilities.bold());
        r72.setBackground(Theme.AdaptiveRipple.filledRect(r35.buttonBackgroundColor, new float[]{8.0f}));
        r6.addView(r72, LayoutHelper.createLinear(-1, 48, 83, 16, 15, 16, 4));
        r72.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda226(r5, r8, r20, r122, r13, r14, r15, r42, r33, r4));
        ScaleStateListAnimator.apply(r72, 0.02f, 1.2f);
        AnonymousClass68 r3 = new AnonymousClass68(r30);
        r3.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r3.setGravity(17);
        if (r37 != 1) goto L27;
        int r0 = R.string.MessageSuggestionPublishNow;
    L28:
        r3.setText(LocaleController.getString(r0));
        r3.setTextColor(r35.buttonBackgroundColor);
        r3.setTextSize(1, 14.0f);
        r3.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_windowBackgroundWhite), Theme.getColor(Theme.key_listSelector)));
        r6.addView(r3, LayoutHelper.createLinear(-1, 48, 83, 16, 0, 16, 16));
        r3.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda227(r5, r33, r4));
        ScaleStateListAnimator.apply(r3, 0.02f, 1.2f);
        r4.setCustomView(r6);
        BottomSheet r02 = r4.show();
        r02.setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda228(r34, r5));
        r02.setBackgroundColor(r35.backgroundColor);
        r02.fixNavigationBar(r35.backgroundColor);
        return r4;
    L27:
        r0 = R.string.PostSuggestionsAnytime;
        goto L28
    L18:
        if (r31 == 2147483646) goto L23;
        long r63 = 1000 * r31;
        r42 = r12;
        r42.setTimeInMillis(System.currentTimeMillis());
        r42.set(12, 0);
        r42.set(13, 0);
        r42.set(14, 0);
        r42.set(11, 0);
        int r2 = (int) ((r63 - r42.getTimeInMillis()) / DurationKt.MILLIS_IN_DAY);
        r202 = r18;
        r42.setTimeInMillis(r63);
        if (r2 < 0) goto L24;
        r15.setValue(r42.get(12));
        r14.setValue(r42.get(11));
        r13.setValue(r2);
        goto L24
    L14:
        r105 = 3;
        goto L15
    L9:
        r11 = R.string.PostSuggestionsAddTime;
        goto L10
    }

    public static BottomSheet.Builder createSuggestedMessageDatePickerDialog(Context r8, long r9, ScheduleDatePickerDelegate r11, Theme.ResourcesProvider r12, int r13) {
        return createSuggestedMessageDatePickerDialog(r8, r9, r11, null, new ScheduleDatePickerColors(null), r12, r13);
    }

    public static AlertDialog createSupportAlert(BaseFragment r10, Theme.ResourcesProvider r11) {
        if (r10 != null) goto L5;
    L13:
        return null;
    L5:
        if (r10.getParentActivity() == null) goto L13;
        LinkSpanDrawable.LinksTextView r1 = new LinkSpanDrawable.LinksTextView(r10.getParentActivity(), r10.getResourceProvider());
        SpannableString r2 = new SpannableString(Html.fromHtml(LocaleController.getString(R.string.AskAQuestionInfo).replace("\n", "<br>")));
        URLSpan[] r3 = (URLSpan[]) r2.getSpans(0, r2.length(), URLSpan.class);
        int r4 = 0;
    L9:
        if (r4 >= r3.length) goto L11;
        URLSpan r6 = r3[r4];
        int r7 = r2.getSpanStart(r6);
        int r8 = r2.getSpanEnd(r6);
        r2.removeSpan(r6);
        r2.setSpan(new AnonymousClass4(r6.getURL(), r10), r7, r8, 0);
        r4 = r4 + 1;
        goto L9
    L11:
        r1.setText(r2);
        r1.setTextSize(1, 16.0f);
        r1.setLinkTextColor(Theme.getColor(Theme.key_dialogTextLink, r11));
        r1.setHighlightColor(Theme.getColor(Theme.key_dialogLinkSelection, r11));
        r1.setPadding(AndroidUtilities.dp(23.0f), 0, AndroidUtilities.dp(23.0f), 0);
        r1.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r11));
        AlertDialog.Builder r22 = new AlertDialog.Builder(r10.getParentActivity(), r11);
        r22.setView(r1);
        r22.setTitle(LocaleController.getString(R.string.AskAQuestion));
        r22.setPositiveButton(LocaleController.getString(R.string.AskButton), new AlertsCreator$$ExternalSyntheticLambda77(r10));
        r22.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        return r22.create();
    }

    public static AlertDialog.Builder createTTLAlert(Context r5, TLRPC.EncryptedChat r6, Theme.ResourcesProvider r7) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(r5, r7);
        r0.setTitle(LocaleController.getString(R.string.MessageLifetime));
        NumberPicker r72 = new NumberPicker(r5);
        r72.setMinValue(0);
        r72.setMaxValue(20);
        int r2 = r6.ttl;
        if (r2 <= 0) goto L7;
        if (r2 >= 16) goto L7;
        r72.setValue(r2);
    L23:
        r72.setFormatter(new AlertsCreator$$ExternalSyntheticLambda47());
        r0.setView(r72);
        r0.setNegativeButton(LocaleController.getString(R.string.Done), new AlertsCreator$$ExternalSyntheticLambda48(r6, r72));
        return r0;
    L7:
        if (r2 != 30) goto L10;
        r72.setValue(16);
        goto L23
    L10:
        if (r2 != 60) goto L13;
        r72.setValue(17);
        goto L23
    L13:
        if (r2 != 3600) goto L16;
        r72.setValue(18);
        goto L23
    L16:
        if (r2 != 86400) goto L19;
        r72.setValue(19);
        goto L23
    L19:
        if (r2 != 604800) goto L21;
        r72.setValue(20);
        goto L23
    L21:
        if (r2 != 0) goto L23;
        r72.setValue(0);
        goto L23
    }

    public static void createThemeCreateDialog(BaseFragment r17, int r18, Theme.ThemeInfo r19, Theme.ThemeAccent r20) {
        if (r17 != null) goto L5;
        return;
    L5:
        if (r17.getParentActivity() == null) goto L14;
        Activity r0 = r17.getParentActivity();
        EditTextBoldCursor r2 = new EditTextBoldCursor(r0);
        r2.setBackground(null);
        r2.setLineColors(Theme.getColor(Theme.key_dialogInputField), Theme.getColor(Theme.key_dialogInputFieldActivated), Theme.getColor(Theme.key_text_RedBold));
        AlertDialog.Builder r4 = new AlertDialog.Builder(r0);
        r4.setTitle(LocaleController.getString(R.string.NewTheme));
        r4.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r4.setPositiveButton(LocaleController.getString(R.string.Create), new AlertsCreator$$ExternalSyntheticLambda123());
        LinearLayout r3 = new LinearLayout(r0);
        r3.setOrientation(1);
        r4.setView(r3);
        TextView r6 = new TextView(r0);
        if (r18 == 0) goto L10;
        r6.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EnterThemeNameEdit)));
    L11:
        r6.setTextSize(1, 16.0f);
        r6.setPadding(AndroidUtilities.dp(23.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(23.0f), AndroidUtilities.dp(6.0f));
        int r7 = Theme.key_dialogTextBlack;
        r6.setTextColor(Theme.getColor(r7));
        r3.addView(r6, LayoutHelper.createLinear(-1, -2));
        r2.setTextSize(1, 16.0f);
        r2.setTextColor(Theme.getColor(r7));
        r2.setMaxLines(1);
        r2.setLines(1);
        r2.setInputType(16385);
        r2.setGravity(51);
        r2.setSingleLine(true);
        r2.setImeOptions(6);
        r2.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r2.setCursorSize(AndroidUtilities.dp(20.0f));
        r2.setCursorWidth(1.5f);
        r2.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
        r3.addView(r2, LayoutHelper.createLinear(-1, 36, 51, 24, 6, 24, 0));
        r2.setOnEditorActionListener(new AlertsCreator$$ExternalSyntheticLambda124());
        r2.setText(generateThemeName(r20));
        r2.setSelection(r2.length());
        AlertDialog r5 = r4.create();
        r5.setOnShowListener(new AlertsCreator$$ExternalSyntheticLambda125(r2));
        r17.showDialog(r5);
        r2.requestFocus();
        r5.getButton(-1).setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda126(r17, r2, r20, r19, r5));
        return;
    L10:
        r6.setText(LocaleController.getString(R.string.EnterThemeName));
        goto L11
    }

    public static BottomSheet createTimePickerDialog(Context r21, String r22, int r23, int r24, int r25, Utilities.Callback<Integer> r26) {
        if (r21 != null) goto L5;
        return null;
    L5:
        ScheduleDatePickerColors r2 = new ScheduleDatePickerColors(null);
        BottomSheet.Builder r3 = new BottomSheet.Builder(r21, false, null);
        r3.setApplyBottomPadding(false);
        AnonymousClass21 r8 = new AnonymousClass21(r21);
        AnonymousClass22 r11 = new AnonymousClass22(r21, r8);
        r11.setOrientation(0);
        r11.setWeightSum(1.0f);
        r8.setAllItemsCount(24);
        r8.setItemCount(5);
        r8.setTextColor(r2.textColor);
        r8.setGravity(5);
        r8.setTextOffset(-AndroidUtilities.dp(12.0f));
        AnonymousClass23 r9 = new AnonymousClass23(r21);
        r9.setWrapSelectorWheel(true);
        r9.setAllItemsCount(60);
        r9.setItemCount(5);
        r9.setTextColor(r2.textColor);
        r9.setGravity(3);
        r9.setTextOffset(AndroidUtilities.dp(12.0f));
        AlertsCreator$$ExternalSyntheticLambda247 r5 = new AlertsCreator$$ExternalSyntheticLambda247(r24, r25, r8, r9, r23, r11);
        r11.addView(r8, LayoutHelper.createLinear(0, 270, 0.5f));
        r8.setFormatter(new AlertsCreator$$ExternalSyntheticLambda248());
        r8.setOnValueChangedListener(new AlertsCreator$$ExternalSyntheticLambda249(r5));
        r11.addView(r9, LayoutHelper.createLinear(0, 270, 0.5f));
        r9.setFormatter(new AlertsCreator$$ExternalSyntheticLambda250());
        r9.setOnValueChangedListener(new AlertsCreator$$ExternalSyntheticLambda251(r5));
        r5.run(Boolean.FALSE);
        AnonymousClass24 r52 = new AnonymousClass24(r21, r8, r9);
        r52.setOrientation(1);
        FrameLayout r6 = new FrameLayout(r21);
        TextView r7 = new TextView(r21);
        r7.setText(r22);
        r7.setTextColor(r2.textColor);
        r7.setTextSize(1, 20.0f);
        r7.setTypeface(AndroidUtilities.bold());
        r6.addView(r7, LayoutHelper.createFrame(-2, -2.0f, 51, 0.0f, 12.0f, 0.0f, 0.0f));
        r7.setOnTouchListener(new AlertsCreator$$ExternalSyntheticLambda252());
        r52.addView(r6, LayoutHelper.createLinear(-1, -2, 51, 22, 0, 0, 4));
        r52.addView(r11, LayoutHelper.createLinear(-1, -2, 1.0f, 0, 0, 12, 0, 12));
        ButtonWithCounterView r0 = new ButtonWithCounterView(r21, null).setRound();
        r0.setText(LocaleController.getString(R.string.Select), false);
        r0.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda253(r6));
        r52.addView(r0, LayoutHelper.createLinear(-1, 48, 0, 16, 12, 16, 12));
        r3.setCustomView(r52);
        BottomSheet r02 = r3.show();
        r02.setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda254(r26, r8, r9));
        r02.setBackgroundColor(r2.backgroundColor);
        r02.fixNavigationBar(r2.backgroundColor);
        BottomSheet r03 = r3.create();
        BottomSheet[] r62 = {r03};
        return r03;
    }

    public static Dialog createVibrationSelectDialog(Activity r8, long r9, long r11, String r13, Runnable r14) {
        return createVibrationSelectDialog(r8, r9, r11, r13, r14, null);
    }

    public static Dialog createVibrationSelectDialog(Activity r16, long r17, long r19, String r21, Runnable r22, Theme.ResourcesProvider r23) {
        String r5 = r21;
        SharedPreferences r1 = MessagesController.getNotificationsSettings(UserConfig.selectedAccount);
        boolean r11 = true;
        int[] r2 = new int[1];
        if (r17 == 0) goto L11;
        int r12 = r1.getInt(r5, 0);
        r2[0] = r12;
        if (r12 != 3) goto L7;
        r2[0] = 2;
    L9:
        String[] r13 = {LocaleController.getString(R.string.VibrationDefault), LocaleController.getString(R.string.Short), LocaleController.getString(R.string.Long), LocaleController.getString(R.string.VibrationDisabled)};
    L10:
        String[] r132 = r13;
        LinearLayout r14 = new LinearLayout(r16);
        r14.setOrientation(1);
        AlertDialog.Builder r8 = new AlertDialog.Builder(r16, r23);
        int r15 = 0;
    L21:
        if (r15 >= r132.length) goto L27;
        RadioColorCell r18 = new RadioColorCell(r16, r23);
        r18.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r18.setTag(Integer.valueOf(r15));
        r18.setCheckColor(Theme.getColor(Theme.key_radioBackground, r23), Theme.getColor(Theme.key_dialogRadioBackgroundChecked, r23));
        String r3 = r132[r15];
        if (r2[0] != r15) goto L25;
        boolean r4 = r11;
    L26:
        r18.setTextAndValue(r3, r4);
        r14.addView(r18);
        r18.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda153(r2, r17, r5, r19, r8, r22));
        r15 = r15 + 1;
        r5 = r21;
        r11 = true;
        goto L21
    L25:
        r4 = false;
        goto L26
    L27:
        r8.setTitle(LocaleController.getString(R.string.Vibrate));
        r8.setView(r14);
        r8.setPositiveButton(LocaleController.getString(R.string.Cancel), null);
        return r8.create();
    L7:
        if (r12 != 2) goto L9;
        r2[0] = 3;
        goto L9
    L11:
        int r110 = r1.getInt(r5, 0);
        r2[0] = r110;
        if (r110 != 0) goto L14;
        r2[0] = 1;
    L18:
        r13 = new String[]{LocaleController.getString(R.string.VibrationDisabled), LocaleController.getString(R.string.VibrationDefault), LocaleController.getString(R.string.Short), LocaleController.getString(R.string.Long), LocaleController.getString(R.string.OnlyIfSilent)};
        goto L10
    L14:
        if (r110 != 1) goto L16;
        r2[0] = 2;
        goto L18
    L16:
        if (r110 != 2) goto L18;
        r2[0] = 0;
        goto L18
    }

    public static Dialog createVibrationSelectDialog(Activity r8, long r9, long r11, boolean r13, boolean r14, Runnable r15, Theme.ResourcesProvider r16) {
        if (r9 == 0) goto L6;
        String r132 = "vibrate_" + r9;
    L10:
        return createVibrationSelectDialog(r8, r9, r11, r132, r15, r16);
    L6:
        if (r13 == false) goto L8;
        r132 = "vibrate_group";
        goto L10
    L8:
        r132 = "vibrate_messages";
        goto L10
    }

    public static Dialog createWebViewPermissionsRequestDialog(Context r6, Theme.ResourcesProvider r7, String[] r8, int r9, String r10, String r11, Consumer<Boolean> r12) {
        if (r8 != null) goto L5;
    L14:
        boolean r82 = false;
    L15:
        AtomicBoolean r1 = new AtomicBoolean();
        AlertDialog.Builder r72 = new AlertDialog.Builder(r6, r7).setTopAnimation(r9, 72, false, Theme.getColor(Theme.key_dialogTopBackground));
        if (r82 == false) goto L18;
        r10 = r11;
    L18:
        AlertDialog.Builder r73 = r72.setMessage(AndroidUtilities.replaceTags(r10));
        if (r82 == false) goto L21;
        int r92 = R.string.PermissionOpenSettings;
    L23:
        return r73.setPositiveButton(LocaleController.getString(r92), new AlertsCreator$$ExternalSyntheticLambda162(r82, r6, r1, r12)).setNegativeButton(LocaleController.getString(R.string.BotWebViewRequestDontAllow), new AlertsCreator$$ExternalSyntheticLambda163(r1, r12)).setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda164(r1, r12)).create();
    L21:
        r92 = R.string.BotWebViewRequestAllow;
        goto L23
    L5:
        if ((r6 instanceof Activity) == false) goto L14;
        Activity r13 = (Activity) r6;
        int r2 = r8.length;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L14;
        String r4 = r8[r3];
        if (r13.checkSelfPermission(r4) == 0) goto L13;
        if (r13.shouldShowRequestPermissionRationale(r4) == false) goto L13;
        r82 = true;
    L13:
        r3 = r3 + 1;
        goto L7
    }

    public static boolean ensurePaidMessageConfirmation(int r7, long r8, int r10, Utilities.Callback<Long> r11) {
        return ensurePaidMessageConfirmation(r7, r8, r10, r11, 0);
    }

    public static boolean ensurePaidMessageConfirmation(int r15, long r16, int r18, Utilities.Callback<Long> r19, long r20) {
        if (r19 != null) goto L5;
        return false;
    L5:
        long r3 = MessagesController.getInstance(r15).getSendPaidMessagesStars(r16);
        if (r3 <= 0) goto L8;
    L10:
        long r7 = r18 * r3;
        StarsController.getInstance(r15).sendingMessagesCount.put(Long.valueOf(r16), Integer.valueOf(r18));
        if (r7 <= 0) goto L17;
        if (r20 == r7) goto L17;
        long r13 = r3;
        showPayForMessageAlert(r15, r16, r13, r18, new AlertsCreator$$ExternalSyntheticLambda6(r15, r7, r16, r19, r13));
        return true;
    L17:
        r19.run(Long.valueOf(r7));
        return false;
    L8:
        if (r16 <= 0) goto L10;
        r3 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(r15).isUserContactBlocked(r16));
        goto L10
    }

    public static boolean ensurePaidMessagesMultiConfirmation(int r27, ArrayList<Long> r28, int r29, Utilities.Callback<HashMap<Long, Long>> r30) {
        boolean r1 = false;
        if (r30 != null) goto L5;
        return false;
    L5:
        if (r28 == null) goto L45;
        if (r28.isEmpty() == true) goto L45;
        HashMap<Long, Long> r8 = new HashMap();
        int r3 = r28.size();
        int r10 = 0;
        int r12 = 0;
        long r6 = 0;
        boolean r11 = true;
    L10:
        if (r12 >= r3) goto L25;
        Long r13 = r28.get(r12);
        r12 = r12 + 1;
        Long r132 = r13;
        long r14 = r132.longValue();
        boolean r16 = r1;
        long r17 = MessagesController.getInstance(r27).getSendPaidMessagesStars(r14);
        if (r17 <= 0) goto L14;
    L16:
        r8.put(r132, Long.valueOf(r17));
        r6 = r6 + r17;
        StarsController.getInstance(r27).sendingMessagesCount.put(r132, Integer.valueOf(r29));
        if (r17 <= 0) goto L19;
        r10 = r10 + 1;
    L19:
        if (r17 <= 0) goto L24;
        if (r11 == false) goto L24;
        if (MessagesController.getInstance(r27).getMainSettings().getLong("ask_paid_message_" + r14 + "_price", 0) >= r17) goto L24;
        r11 = r16;
    L24:
        r1 = r16;
        goto L10
    L14:
        if (r14 <= 0) goto L16;
        r17 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(r27).isUserContactBlocked(r14));
        goto L16
    L25:
        boolean r162 = r1;
        long r62 = r6 * Math.max(1, r29);
        if (r11 == true) goto L43;
        if (r62 <= 0) goto L43;
        Activity r20 = AndroidUtilities.getActivity();
        BaseFragment r15 = LaunchActivity.getSafeLastFragment();
        if (PhotoViewer.getInstance().isVisible() == true) goto L40;
        if (r15 != null) goto L34;
    L36:
        if (r15 == null) goto L39;
        Theme.ResourcesProvider r18 = r15.getResourceProvider();
    L38:
        Theme.ResourcesProvider r26 = r18;
        SpannableStringBuilder r9 = new SpannableStringBuilder();
        r9.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessageMulti1", r10)));
        r9.append(" ");
        r9.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessageMulti2", (int) r62, new Object[]{LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessageMulti2Messages", r29 * Math.max(1, r10))})));
        showAlertWithCheckboxWithBalance(r20, LocaleController.getString(R.string.MessageLockedStarsConfirmTitle), r9, LocaleController.getString(R.string.MessageLockedStarsConfirmMessageDontAsk), LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessagePay", r29), new AlertsCreator$$ExternalSyntheticLambda82(r27, r28, r62, r20, r26, r30, r8), r26);
        return true;
    L39:
        r18 = null;
        goto L38
    L34:
        if (r15.hasShownSheet() == false) goto L36;
    L40:
        r18 = new DarkThemeResourceProvider();
    L43:
        r30.run(r8);
        return r162;
    L45:
        r30.run(new HashMap());
        return false;
    }

    public static boolean ensurePaidMessagesMultiConfirmationTopicKeys(int r5, ArrayList<MessagesStorage.TopicKey> r6, int r7, Utilities.Callback<HashMap<Long, Long>> r8) {
        HashSet r0 = new HashSet();
        if (r6 == null) goto L8;
        int r1 = r6.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L8;
        MessagesStorage.TopicKey r3 = r6.get(r2);
        r2 = r2 + 1;
        r0.add(Long.valueOf(r3.dialogId));
    L8:
        return ensurePaidMessagesMultiConfirmation(r5, new ArrayList(r0), r7, r8);
    }

    private static String formatPollCloseCustomDeadline(int r6) {
        int r1 = r6 / 86400;
        int r0 = (r6 % 86400) / 3600;
        int r62 = (r6 % 3600) / 60;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 <= 0) goto L5;
        String r12 = LocaleController.formatPluralString("Days", r1, new Object[0]);
    L6:
        if (r0 <= 0) goto L8;
        String r02 = LocaleController.formatPluralString("Hours", r0, new Object[0]);
    L9:
        if (r62 <= 0) goto L11;
        r2 = LocaleController.formatPluralString("Minutes", r62, new Object[0]);
    L11:
        String r63 = LocaleController.formatString(R.string.PollCustomDeadlineClosesInFmt, new Object[]{r12, r02, r2}).trim();
        return LocaleController.formatString(R.string.PollCustomDeadlineClosesIn, new Object[]{r63});
    L8:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private static String generateThemeName(Theme.ThemeAccent r108) {
        List r0 = Arrays.asList(new String[]{"Ancient", "Antique", "Autumn", "Baby", "Barely", "Baroque", "Blazing", "Blushing", "Bohemian", "Bubbly", "Burning", "Buttered", "Classic", "Clear", "Cool", "Cosmic", "Cotton", "Cozy", "Crystal", "Dark", "Daring", "Darling", "Dawn", "Dazzling", "Deep", "Deepest", "Delicate", "Delightful", "Divine", "Double", "Downtown", "Dreamy", "Dusky", "Dusty", "Electric", "Enchanted", "Endless", "Evening", "Fantastic", "Flirty", "Forever", "Frigid", "Frosty", "Frozen", "Gentle", "Heavenly", "Hyper", "Icy", "Infinite", "Innocent", "Instant", "Luscious", "Lunar", "Lustrous", "Magic", "Majestic", "Mambo", "Midnight", "Millenium", "Morning", "Mystic", "Natural", "Neon", "Night", "Opaque", "Paradise", "Perfect", "Perky", "Polished", "Powerful", "Rich", "Royal", "Sheer", "Simply", "Sizzling", "Solar", "Sparkling", "Splendid", "Spicy", "Spring", "Stellar", "Sugared", "Summer", "Sunny", "Super", "Sweet", "Tender", "Tenacious", "Tidal", "Toasted", "Totally", "Tranquil", "Tropical", "True", "Twilight", "Twinkling", "Ultimate", "Ultra", "Velvety", "Vibrant", "Vintage", "Virtual", "Warm", "Warmest", "Whipped", "Wild", "Winsome"});
        List r1 = Arrays.asList(new String[]{"Ambrosia", "Attack", "Avalanche", "Blast", "Bliss", "Blossom", "Blush", "Burst", "Butter", "Candy", "Carnival", "Charm", "Chiffon", "Cloud", "Comet", "Delight", "Dream", "Dust", "Fantasy", "Flame", "Flash", "Fire", "Freeze", "Frost", "Glade", "Glaze", "Gleam", "Glimmer", "Glitter", "Glow", "Grande", "Haze", "Highlight", "Ice", "Illusion", "Intrigue", "Jewel", "Jubilee", "Kiss", "Lights", "Lollypop", "Love", "Luster", "Madness", "Matte", "Mirage", "Mist", "Moon", "Muse", "Myth", "Nectar", "Nova", "Parfait", "Passion", "Pop", "Rain", "Reflection", "Rhapsody", "Romance", "Satin", "Sensation", "Silk", "Shine", "Shadow", "Shimmer", "Sky", "Spice", "Star", "Sugar", "Sunrise", "Sunset", "Sun", "Twist", "Unbound", "Velvet", "Vibrant", "Waters", "Wine", "Wink", "Wonder", "Zone"});
        HashMap r2 = new HashMap();
        r2.put(9306112, "Berry");
        r2.put(14598550, "Brandy");
        r2.put(8391495, "Cherry");
        r2.put(16744272, "Coral");
        r2.put(14372985, "Cranberry");
        r2.put(14423100, "Crimson");
        r2.put(14725375, "Mauve");
        r2.put(16761035, "Pink");
        r2.put(16711680, "Red");
        r2.put(16711807, "Rose");
        r2.put(8406555, "Russet");
        r2.put(16720896, "Scarlet");
        r2.put(15856113, "Seashell");
        r2.put(16724889, "Strawberry");
        r2.put(16760576, "Amber");
        r2.put(15438707, "Apricot");
        r2.put(16508850, "Banana");
        r2.put(10601738, "Citrus");
        r2.put(11560192, "Ginger");
        r2.put(16766720, "Gold");
        r2.put(16640272, "Lemon");
        r2.put(16753920, "Orange");
        r2.put(16770484, "Peach");
        r2.put(16739155, "Persimmon");
        r2.put(14996514, "Sunflower");
        r2.put(15893760, "Tangerine");
        r2.put(16763004, "Topaz");
        r2.put(16776960, "Yellow");
        r2.put(3688720, "Clover");
        r2.put(8628829, "Cucumber");
        r2.put(5294200, "Emerald");
        r2.put(11907932, "Olive");
        r2.put(65280, "Green");
        r2.put(43115, "Jade");
        r2.put(2730887, "Jungle");
        r2.put(12582656, "Lime");
        r2.put(776785, "Malachite");
        r2.put(10026904, "Mint");
        r2.put(11394989, "Moss");
        r2.put(3234721, "Azure");
        r2.put(255, "Blue");
        r2.put(18347, "Cobalt");
        r2.put(5204422, "Indigo");
        r2.put(96647, "Lagoon");
        r2.put(7461346, "Aquamarine");
        r2.put(1182351, "Ultramarine");
        r2.put(128, "Navy");
        r2.put(3101086, "Sapphire");
        r2.put(7788522, "Sky");
        r2.put(32896, "Teal");
        r2.put(4251856, "Turquoise");
        r2.put(10053324, "Amethyst");
        r2.put(5046581, "Blackberry");
        r2.put(6373457, "Eggplant");
        r2.put(13148872, "Lilac");
        r2.put(11894492, "Lavender");
        r2.put(13421823, "Periwinkle");
        r2.put(8663417, "Plum");
        r2.put(6684825, "Purple");
        r2.put(14204888, "Thistle");
        r2.put(14315734, "Orchid");
        r2.put(2361920, "Violet");
        r2.put(4137225, "Bronze");
        r2.put(3604994, "Chocolate");
        r2.put(8077056, "Cinnamon");
        r2.put(3153694, "Cocoa");
        r2.put(7365973, "Coffee");
        r2.put(7956873, "Rum");
        r2.put(5113350, "Mahogany");
        r2.put(7875865, "Mocha");
        r2.put(12759680, "Sand");
        r2.put(8924439, "Sienna");
        r2.put(7864585, "Maple");
        r2.put(15787660, "Khaki");
        r2.put(12088115, "Copper");
        r2.put(12144200, "Chestnut");
        r2.put(15653316, "Almond");
        r2.put(16776656, "Cream");
        r2.put(12186367, "Diamond");
        r2.put(11109127, "Honey");
        r2.put(16777200, "Ivory");
        r2.put(15392968, "Pearl");
        r2.put(15725299, "Porcelain");
        r2.put(13745832, "Vanilla");
        r2.put(16777215, "White");
        r2.put(8421504, "Gray");
        r2.put(0, "Black");
        r2.put(15266260, "Chrome");
        r2.put(3556687, "Charcoal");
        r2.put(789277, "Ebony");
        r2.put(12632256, "Silver");
        r2.put(16119285, "Smoke");
        r2.put(2499381, "Steel");
        r2.put(5220413, "Apple");
        r2.put(8434628, "Glacier");
        r2.put(16693933, "Melon");
        r2.put(12929932, "Mulberry");
        r2.put(11126466, "Opal");
        r2.put(5547512, "Blue");
        if (r108 != null) goto L5;
        Theme.ThemeAccent r4 = Theme.getCurrentTheme().getAccent(false);
    L6:
        if (r4 == null) goto L10;
        int r42 = r4.accentColor;
        if (r42 == 0) goto L10;
    L11:
        int r3 = Color.red(r42);
        int r5 = Color.green(r42);
        int r43 = Color.blue(r42);
        Iterator r22 = r2.entrySet().iterator();
        String r6 = null;
        int r7 = Integer.MAX_VALUE;
    L13:
        if (r22.hasNext() == false) goto L18;
        Map.Entry r8 = (Map.Entry) r22.next();
        Integer r9 = (Integer) r8.getKey();
        int r10 = Color.red(r9.intValue());
        int r12 = (r3 + r10) / 2;
        int r102 = r3 - r10;
        int r11 = r5 - Color.green(r9.intValue());
        int r92 = r43 - Color.blue(r9.intValue());
        int r103 = (((((r12 + 512) * r102) * r102) >> 8) + ((r11 * 4) * r11)) + ((((767 - r12) * r92) * r92) >> 8);
        if (r103 >= r7) goto L13;
        r6 = (String) r8.getValue();
        r7 = r103;
        goto L13
    L18:
        if ((Utilities.random.nextInt() % 2) != 0) goto L22;
        return ((String) r0.get(Utilities.random.nextInt(r0.size()))) + " " + r6;
    L22:
        return r6 + " " + ((String) r1.get(Utilities.random.nextInt(r1.size())));
    L10:
        r42 = AndroidUtilities.calcDrawableColor(Theme.getCachedWallpaper())[0];
        goto L11
    L5:
        r4 = r108;
        goto L6
    }

    private static String getFloodWaitString(String r2) {
        int r22 = Utilities.parseInt(r2);
        if (r22 >= 60) goto L5;
        String r23 = LocaleController.formatPluralString("Seconds", r22, new Object[0]);
    L7:
        return LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r23});
    L5:
        r23 = LocaleController.formatPluralString("Minutes", r22 / 60, new Object[0]);
        goto L7
    }

    private static boolean isValidUrl(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return false;
    L7:
        return URL_PATTERN.matcher(r1.trim()).matches();
    }

    public static boolean needsPaidMessageAlert(int r6, long r7) {
        long r0 = MessagesController.getInstance(r6).getSendPaidMessagesStars(r7);
        if (r0 > 0) goto L8;
        if (r7 <= 0) goto L8;
        r0 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(r6).isUserContactBlocked(r7));
    L8:
        if (r0 > 0) goto L10;
        return false;
    L10:
        if (r0 <= MessagesController.getInstance(r6).getMainSettings().getLong("ask_paid_message_" + r7 + "_price", 0)) goto L15;
        return true;
    L15:
        return false;
    }

    private static void performAskAQuestion(BaseFragment r10) {
        int r0 = r10.getCurrentAccount();
        SharedPreferences r1 = MessagesController.getMainSettings(r0);
        long r5 = AndroidUtilities.getPrefIntOrLong(r1, "support_id2", 0);
        TLRPC.User r4 = null;
        if (r5 == 0) goto L21;
        TLRPC.User r2 = MessagesController.getInstance(r0).getUser(Long.valueOf(r5));
        if (r2 != null) goto L20;
        String r52 = r1.getString("support_user", null);
        if (r52 == null) goto L20;
        byte[] r53 = Base64.decode(r52, 0);     // Catch: Exception -> L15
        if (r53 == null) goto L20;
        SerializedData r22 = new SerializedData(r53);     // Catch: Exception -> L15
        TLRPC.User r54 = TLRPC.User.TLdeserialize(r22, r22.readInt32(false), false);     // Catch: Exception -> L15
        if (r54 != null) goto L13;
    L17:
        r22.cleanup();     // Catch: Exception -> L15
        r4 = r54;
        goto L21
    L13:
        if (r54.id != 333000) goto L17;
        r54 = null;
    L15:
        e = move-exception;
        FileLog.e(e);
    L20:
        r4 = r2;
    L21:
        if (r4 != null) goto L23;
        AlertDialog r23 = new AlertDialog(r10.getParentActivity(), 3);
        r23.setCanCancel(false);
        r23.show();
        ConnectionsManager.getInstance(r0).sendRequest(new TLRPC.TL_help_getSupport(), new AlertsCreator$$ExternalSyntheticLambda115(r1, r23, r0, r10));
        return;
    L23:
        MessagesController.getInstance(r0).putUser(r4, true);
        Bundle r02 = new Bundle();
        r02.putLong("user_id", r4.id);
        r10.presentFragment(new ChatActivity(r02));
    }

    private static void processCreate(EditTextBoldCursor r4, AlertDialog r5, BaseFragment r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.getParentActivity() == null) goto L18;
        AndroidUtilities.hideKeyboard(r4);
        Theme.ThemeInfo r42 = Theme.createNewTheme(r4.getText().toString());
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.themeListUpdated, new Object[0]);
        new ThemeEditorView().show(r6.getParentActivity(), r42);
        r5.dismiss();
        SharedPreferences r43 = MessagesController.getGlobalMainSettings();
        if (r43.getBoolean("themehint", false) == true) goto L19;
        r43.edit().putBoolean("themehint", true).apply();
        Toast.makeText(r6.getParentActivity(), LocaleController.getString(R.string.CreateNewThemeHelp), 1).show();     // Catch: Exception -> L12
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        return;
    L19:
        return;
    }

    public static Dialog processError(int r18, TLRPC.TL_error r19, BaseFragment r20, TLObject r21, Object... r22) {
        BaseFragment r1 = r20;
        char r4 = 1;
        if (r19 != null) goto L5;
    L484:
        return null;
    L5:
        if (r19.code == 406) goto L484;
        String r7 = r19.text;
        if (r7 == null) goto L484;
        if ("BALANCE_TOO_LOW".equalsIgnoreCase(r7) == false) goto L15;
        long r11 = StarsController.getAllowedPaidStars(r21);
        long r14 = StarsController.getPeer(r21);
        if (r11 <= 0) goto L484;
        StarsController.getInstance(r18).getBalance(true, new AlertsCreator$$ExternalSyntheticLambda5(r11, r18, r14), true);
        goto L484
    L15:
        if (r19.text.equals("JOIN_GUARD_TIMEOUT") == false) goto L17;
        showSimpleAlert(r1, LocaleController.getString(R.string.GuardBotTimeoutTitle), LocaleController.getString(R.string.GuardBotTimeout));
        goto L484
    L17:
        boolean r72 = r21 instanceof TLRPC.TL_messages_sendMessage;
        boolean r10 = false;
        if (r72 == true) goto L20;
    L31:
        boolean r112 = r21 instanceof TLRPC.TL_messages_initHistoryImport;
        if (r112 == false) goto L34;
    L445:
        if (r112 == false) goto L448;
        TLRPC.InputPeer r2 = ((TLRPC.TL_messages_initHistoryImport) r21).peer;
    L451:
        if (r1 != null) goto L454;
        r1 = LaunchActivity.getLastFragment();
    L454:
        if (r19.text.contains("USER_IS_BLOCKED") == false) goto L457;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorUserBlocked));
        goto L484
    L457:
        if (r19.text.contains("USER_NOT_MUTUAL_CONTACT") == false) goto L460;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportMutualError));
        goto L484
    L460:
        if (r19.text.contains("IMPORT_PEER_TYPE_INVALID") == false) goto L466;
        if ((r2 instanceof TLRPC.TL_inputPeerUser) == false) goto L464;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorChatInvalidUser));
        goto L484
    L464:
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorChatInvalidGroup));
        goto L484
    L466:
        if (r19.text.contains("CHAT_ADMIN_REQUIRED") == false) goto L469;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorNotAdmin));
        goto L484
    L469:
        if (r19.text.startsWith("IMPORT_FORMAT") == false) goto L472;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorFileFormatInvalid));
        goto L484
    L472:
        if (r19.text.startsWith("PEER_ID_INVALID") == false) goto L475;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorPeerInvalid));
        goto L484
    L475:
        if (r19.text.contains("IMPORT_LANG_NOT_FOUND") == false) goto L478;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportErrorFileLang));
        goto L484
    L478:
        if (r19.text.contains("IMPORT_UPLOAD_FAILED") == false) goto L481;
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ImportFailedToUpload));
        goto L484
    L481:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L483;
        showFloodWaitAlert(r19.text, r1);
        goto L484
    L483:
        showSimpleAlert(r1, LocaleController.getString(R.string.ImportErrorTitle), LocaleController.getString(R.string.ErrorOccurred) + "\n" + r19.text);
        goto L484
    L448:
        if ((r21 instanceof TLRPC.TL_messages_startHistoryImport) == false) goto L450;
        r2 = ((TLRPC.TL_messages_startHistoryImport) r21).peer;
        goto L451
    L450:
        r2 = null;
        goto L451
    L34:
        if ((r21 instanceof TLRPC.TL_messages_checkHistoryImportPeer) == true) goto L445;
        if ((r21 instanceof TLRPC.TL_messages_checkHistoryImport) == true) goto L445;
        if ((r21 instanceof TLRPC.TL_messages_startHistoryImport) == true) goto L445;
        if ((r21 instanceof TL_account.saveSecureValue) == false) goto L43;
    L433:
        if (r1 != null) goto L436;
        r1 = LaunchActivity.getLastFragment();
    L436:
        if (r19.text.contains("PHONE_NUMBER_INVALID") == false) goto L439;
        showSimpleAlert(r1, LocaleController.getString(R.string.InvalidPhoneNumber));
        goto L484
    L439:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L442;
        showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
        goto L484
    L442:
        if ("APP_VERSION_OUTDATED".equals(r19.text) == false) goto L444;
        showUpdateAppAlert(r1.getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        goto L484
    L444:
        showSimpleAlert(r1, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r19.text);
        goto L484
    L43:
        if ((r21 instanceof TL_account.getAuthorizationForm) == true) goto L433;
        boolean r113 = r21 instanceof TLRPC.TL_channels_joinChannel;
        if (r113 == false) goto L48;
    L406:
        if (r1 != null) goto L408;
    L419:
        if (r1 == null) goto L431;
        if (r22 != null) goto L422;
    L424:
        boolean r5 = false;
    L425:
        if (r22 != null) goto L427;
    L429:
        showAddUserAlert(r19, r1, r5, r10, r21);
        goto L484
    L427:
        if (r22.length <= 1) goto L429;
        r10 = ((Boolean) r22[1]).booleanValue();
        goto L429
    L422:
        if (r22.length <= 0) goto L424;
        r5 = ((Boolean) r22[0]).booleanValue();
        goto L425
    L431:
        if (r19.text.equals("PEER_FLOOD") == false) goto L484;
        NotificationCenter.getInstance(r18).postNotificationName(NotificationCenter.needShowAlert, new Object[]{1});
        goto L484
    L408:
        if (r19.text.equals("CHANNELS_TOO_MUCH") == false) goto L419;
        if (r1.getParentActivity() == null) goto L412;
        r1.showDialog(new LimitReachedBottomSheet(r1, r1.getParentActivity(), 5, r18, null));
    L418:
        return null;
    L412:
        if (r113 == false) goto L414;
    L417:
        r1.presentFragment(new TooManyCommunitiesActivity(0));
        goto L418
    L414:
        if ((r21 instanceof TLRPC.TL_channels_inviteToChannel) == true) goto L417;
        r1.presentFragment(new TooManyCommunitiesActivity(1));
        goto L418
    L48:
        if ((r21 instanceof TLRPC.TL_channels_editAdmin) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_channels_inviteToChannel) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_messages_addChatUser) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_messages_startBot) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_channels_editBanned) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_messages_editChatDefaultBannedRights) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_messages_editChatAdmin) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_messages_migrateChat) == true) goto L406;
        if ((r21 instanceof TL_phone.inviteToGroupCall) == true) goto L406;
        if ((r21 instanceof TLRPC.TL_messages_createChat) == false) goto L82;
        if (r1 != null) goto L70;
        r1 = LaunchActivity.getLastFragment();
    L70:
        BaseFragment r12 = r1;
        if (r19.text.equals("CHANNELS_TOO_MUCH") == false) goto L78;
        if (r12.getParentActivity() == null) goto L75;
        r12.showDialog(new LimitReachedBottomSheet(r12, r12.getParentActivity(), 5, r18, null));
    L76:
        return null;
    L75:
        r12.presentFragment(new TooManyCommunitiesActivity(2));
        goto L76
    L78:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L80;
        showFloodWaitAlert(r19.text, r12);
        goto L484
    L80:
        showAddUserAlert(r19, r12, false, false, r21);
        goto L484
    L82:
        if ((r21 instanceof TLRPC.TL_channels_createChannel) == false) goto L97;
        if (r1 != null) goto L85;
        r1 = LaunchActivity.getLastFragment();
    L85:
        BaseFragment r122 = r1;
        if (r19.text.equals("CHANNELS_TOO_MUCH") == false) goto L93;
        if (r122.getParentActivity() == null) goto L90;
        r122.showDialog(new LimitReachedBottomSheet(r122, r122.getParentActivity(), 5, r18, null));
    L91:
        return null;
    L90:
        r122.presentFragment(new TooManyCommunitiesActivity(2));
        goto L91
    L93:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L95;
        showFloodWaitAlert(r19.text, r122);
        goto L484
    L95:
        showAddUserAlert(r19, r122, false, false, r21);
        goto L484
    L97:
        if ((r21 instanceof TLRPC.TL_messages_editMessage) == true) goto L99;
        if (r72 == false) goto L105;
    L301:
        if (r72 == false) goto L304;
        long r23 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMessage) r21).peer);
    L322:
        String r73 = r19.text;
        if (r73 != null) goto L325;
    L387:
        String r0 = r19.text;
        r0.getClass();
        switch(r0.hashCode()) {
            case -1809401834: goto L398;
            case -454039871: goto L395;
            case 1169786080: goto L391;
            default: goto L389;
        };
    L389:
        r4 = 65535;
    L401:
        switch(r4) {
            case 0: goto L405;
            case 1: goto L404;
            case 2: goto L403;
            default: goto L484;
        };
    L403:
        showSimpleToast(r1, LocaleController.getString(R.string.MessageScheduledLimitReached));
        goto L484
    L404:
        NotificationCenter.getInstance(r18).postNotificationName(NotificationCenter.needShowAlert, new Object[]{0});
        goto L484
    L405:
        NotificationCenter.getInstance(r18).postNotificationName(NotificationCenter.needShowAlert, new Object[]{5});
        goto L484
    L391:
        if (r0.equals("SCHEDULE_TOO_MUCH") == false) goto L389;
        r4 = 2;
        goto L401
    L395:
        if (r0.equals("PEER_FLOOD") == true) goto L401;
    L398:
        if (r0.equals("USER_BANNED_IN_CHANNEL") == false) goto L389;
        r4 = 0;
        goto L401
    L325:
        if (r73.startsWith("CHAT_SEND_") == false) goto L387;
        if (r19.text.endsWith("FORBIDDEN") == false) goto L387;
        String r13 = r19.text;
        if (r23 >= 0) goto L331;
        TLRPC.Chat r24 = MessagesController.getInstance(r18).getChat(Long.valueOf(-r23));
    L332:
        String r02 = r19.text;
        r02.getClass();
        switch(r02.hashCode()) {
            case -1813346101: goto L371;
            case -1755013292: goto L368;
            case -1463451737: goto L364;
            case -446466679: goto L360;
            case 469767429: goto L356;
            case 788688112: goto L352;
            case 963091938: goto L348;
            case 1100757753: goto L344;
            case 1146489803: goto L340;
            case 1701620704: goto L336;
            default: goto L334;
        };
    L334:
        r4 = 65535;
    L374:
        switch(r4) {
            case 0: goto L385;
            case 1: goto L384;
            case 2: goto L383;
            case 3: goto L382;
            case 4: goto L381;
            case 5: goto L380;
            case 6: goto L379;
            case 7: goto L378;
            case 8: goto L377;
            case 9: goto L376;
            default: goto L386;
        };
    L376:
        r13 = ChatObject.getRestrictedErrorText(r24, 8);
        goto L386
    L377:
        r13 = ChatObject.getRestrictedErrorText(r24, 16);
        goto L386
    L378:
        r13 = ChatObject.getRestrictedErrorText(r24, 23);
        goto L386
    L379:
        r13 = ChatObject.getRestrictedErrorText(r24, 17);
        goto L386
    L380:
        r13 = ChatObject.getRestrictedErrorText(r24, 21);
        goto L386
    L381:
        r13 = ChatObject.getRestrictedErrorText(r24, 19);
        goto L386
    L382:
        r13 = ChatObject.getRestrictedErrorText(r24, 10);
        goto L386
    L383:
        r13 = ChatObject.getRestrictedErrorText(r24, 18);
        goto L386
    L384:
        r13 = ChatObject.getRestrictedErrorText(r24, 22);
        goto L386
    L385:
        r13 = ChatObject.getRestrictedErrorText(r24, 20);
    L386:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, r13});
        goto L484
    L336:
        if (r02.equals("CHAT_SEND_STICKERS_FORBIDDEN") == false) goto L334;
        r4 = '\t';
        goto L374
    L340:
        if (r02.equals("CHAT_SEND_PHOTOS_FORBIDDEN") == false) goto L334;
        r4 = '\b';
        goto L374
    L344:
        if (r02.equals("CHAT_SEND_GIFS_FORBIDDEN") == false) goto L334;
        r4 = 7;
        goto L374
    L348:
        if (r02.equals("CHAT_SEND_VIDEOS_FORBIDDEN") == false) goto L334;
        r4 = 6;
        goto L374
    L352:
        if (r02.equals("CHAT_SEND_ROUNDVIDEOS_FORBIDDEN") == false) goto L334;
        r4 = 5;
        goto L374
    L356:
        if (r02.equals("CHAT_SEND_DOCS_FORBIDDEN") == false) goto L334;
        r4 = 4;
        goto L374
    L360:
        if (r02.equals("CHAT_SEND_POLL_FORBIDDEN") == false) goto L334;
        r4 = 3;
        goto L374
    L364:
        if (r02.equals("CHAT_SEND_AUDIOS_FORBIDDEN") == false) goto L334;
        r4 = 2;
        goto L374
    L368:
        if (r02.equals("CHAT_SEND_PLAIN_FORBIDDEN") == true) goto L374;
    L371:
        if (r02.equals("CHAT_SEND_VOICES_FORBIDDEN") == false) goto L334;
        r4 = 0;
        goto L374
    L331:
        r24 = null;
        goto L332
    L304:
        if ((r21 instanceof TLRPC.TL_messages_sendMedia) == false) goto L307;
        r23 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMedia) r21).peer);
        goto L322
    L307:
        if ((r21 instanceof TL_ephemeral.TL_sendMessage) == false) goto L310;
        r23 = DialogObject.getPeerDialogId(((TL_ephemeral.TL_sendMessage) r21).peer);
        goto L322
    L310:
        if ((r21 instanceof TLRPC.TL_messages_sendInlineBotResult) == false) goto L313;
        r23 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendInlineBotResult) r21).peer);
        goto L322
    L313:
        if ((r21 instanceof TLRPC.TL_messages_forwardMessages) == false) goto L316;
        r23 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_forwardMessages) r21).to_peer);
        goto L322
    L316:
        if ((r21 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L319;
        r23 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMultiMedia) r21).peer);
        goto L322
    L319:
        if ((r21 instanceof TLRPC.TL_messages_sendScheduledMessages) == false) goto L321;
        r23 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendScheduledMessages) r21).peer);
        goto L322
    L321:
        r23 = 0;
        goto L322
    L105:
        if ((r21 instanceof TL_ephemeral.TL_sendMessage) == true) goto L301;
        if ((r21 instanceof TLRPC.TL_messages_sendMedia) == true) goto L301;
        if ((r21 instanceof TLRPC.TL_messages_sendInlineBotResult) == true) goto L301;
        if ((r21 instanceof TLRPC.TL_messages_forwardMessages) == true) goto L301;
        if ((r21 instanceof TLRPC.TL_messages_sendMultiMedia) == true) goto L301;
        if ((r21 instanceof TLRPC.TL_messages_sendScheduledMessages) == true) goto L301;
        if ((r21 instanceof TLRPC.TL_messages_importChatInvite) == false) goto L138;
        if (r1 != null) goto L121;
        r1 = LaunchActivity.getLastFragment();
    L121:
        BaseFragment r123 = r1;
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L125;
        showSimpleAlert(r123, LocaleController.getString(R.string.FloodWait));
        goto L484
    L125:
        if (r19.text.equals("USERS_TOO_MUCH") == false) goto L128;
        showSimpleAlert(r123, LocaleController.getString(R.string.JoinToGroupErrorFull));
        goto L484
    L128:
        if (r19.text.equals("CHANNELS_TOO_MUCH") == false) goto L134;
        if (r123.getParentActivity() == null) goto L132;
        r123.showDialog(new LimitReachedBottomSheet(r123, r123.getParentActivity(), 5, r18, null));
        goto L484
    L132:
        r123.presentFragment(new TooManyCommunitiesActivity(0));
        goto L484
    L134:
        if (r19.text.equals("INVITE_HASH_EXPIRED") == false) goto L136;
        showSimpleAlert(r123, LocaleController.getString(R.string.ExpiredLink), LocaleController.getString(R.string.InviteExpired));
        goto L484
    L136:
        showSimpleAlert(r123, LocaleController.getString(R.string.JoinToGroupErrorNotExist));
        goto L484
    L138:
        if ((r21 instanceof TLRPC.TL_messages_getAttachedStickers) == false) goto L147;
        if (r1 == null) goto L484;
        if (r1.getParentActivity() == null) goto L484;
        Toast.makeText(r1.getParentActivity(), LocaleController.getString(R.string.ErrorOccurred) + "\n" + r19.text, 0).show();     // Catch: Exception -> L144
    L144:
        e = move-exception;
        FileLog.e(e);
        goto L484
    L147:
        if ((r21 instanceof TL_account.confirmPhone) == true) goto L278;
        if ((r21 instanceof TL_account.verifyPhone) == true) goto L278;
        if ((r21 instanceof TL_account.verifyEmail) == true) goto L278;
        if ((r21 instanceof TLRPC.TL_auth_resendCode) == false) goto L179;
        if (r19.text.contains("PHONE_NUMBER_INVALID") == false) goto L160;
        return showSimpleAlert(r1, LocaleController.getString(R.string.InvalidPhoneNumber));
    L160:
        if (r19.text.contains("PHONE_CODE_EMPTY") == true) goto L177;
        if (r19.text.contains("PHONE_CODE_INVALID") == true) goto L177;
        if (r19.text.contains("PHONE_CODE_EXPIRED") == false) goto L169;
        return showSimpleAlert(r1, LocaleController.getString(R.string.CodeExpired));
    L169:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L173;
        return showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
    L173:
        if (r19.code == (-1000)) goto L484;
        return showSimpleAlert(r1, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r19.text);
    L177:
        return showSimpleAlert(r1, LocaleController.getString(R.string.InvalidCode));
    L179:
        if ((r21 instanceof TL_account.sendConfirmPhoneCode) == false) goto L191;
        if (r19.code != 400) goto L185;
        return showSimpleAlert(r1, LocaleController.getString(R.string.CancelLinkExpired));
    L185:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L189;
        return showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
    L189:
        return showSimpleAlert(r1, LocaleController.getString(R.string.ErrorOccurred));
    L191:
        if ((r21 instanceof TL_account.changePhone) == false) goto L212;
        if (r19.text.contains("PHONE_NUMBER_INVALID") == false) goto L196;
        showSimpleAlert(r1, LocaleController.getString(R.string.InvalidPhoneNumber));
        goto L484
    L196:
        if (r19.text.contains("PHONE_CODE_EMPTY") == false) goto L198;
    L210:
        showSimpleAlert(r1, LocaleController.getString(R.string.InvalidCode));
        goto L484
    L198:
        if (r19.text.contains("PHONE_CODE_INVALID") == true) goto L210;
        if (r19.text.contains("PHONE_CODE_EXPIRED") == false) goto L204;
        showSimpleAlert(r1, LocaleController.getString(R.string.CodeExpired));
        goto L484
    L204:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L207;
        showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
        goto L484
    L207:
        if (r19.text.contains("FRESH_CHANGE_PHONE_FORBIDDEN") == false) goto L209;
        showSimpleAlert(r1, LocaleController.getString(R.string.FreshChangePhoneForbiddenTitle), LocaleController.getString(R.string.FreshChangePhoneForbidden));
        goto L484
    L209:
        showSimpleAlert(r1, r19.text);
        goto L484
    L212:
        if ((r21 instanceof TL_account.sendChangePhoneCode) == false) goto L236;
        if (r19.text.contains("PHONE_NUMBER_INVALID") == false) goto L217;
        LoginActivity.needShowInvalidAlert(r1, (String) r22[0], false);
        goto L484
    L217:
        if (r19.text.contains("PHONE_CODE_EMPTY") == false) goto L219;
    L234:
        showSimpleAlert(r1, LocaleController.getString(R.string.InvalidCode));
        goto L484
    L219:
        if (r19.text.contains("PHONE_CODE_INVALID") == true) goto L234;
        if (r19.text.contains("PHONE_CODE_EXPIRED") == false) goto L225;
        showSimpleAlert(r1, LocaleController.getString(R.string.CodeExpired));
        goto L484
    L225:
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L228;
        showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
        goto L484
    L228:
        if (r19.text.startsWith("PHONE_NUMBER_OCCUPIED") == false) goto L231;
        showSimpleAlert(r1, LocaleController.formatString("ChangePhoneNumberOccupied", R.string.ChangePhoneNumberOccupied, new Object[]{r22[0]}));
        goto L484
    L231:
        if (r19.text.startsWith("PHONE_NUMBER_BANNED") == false) goto L233;
        LoginActivity.needShowInvalidAlert(r1, (String) r22[0], true);
        goto L484
    L233:
        showSimpleAlert(r1, LocaleController.getString(R.string.ErrorOccurred));
        goto L484
    L236:
        if ((r21 instanceof TL_account.updateUsername) == false) goto L245;
        String r03 = r19.text;
        r03.getClass();
        if (r03.equals("USERNAME_INVALID") == false) goto L240;
        showSimpleAlert(r1, LocaleController.getString(R.string.UsernameInvalid));
        goto L484
    L240:
        if (r03.equals("USERNAME_OCCUPIED") == true) goto L242;
        showSimpleAlert(r1, LocaleController.getString(R.string.ErrorOccurred));
        goto L484
    L242:
        showSimpleAlert(r1, LocaleController.getString(R.string.UsernameInUse));
        goto L484
    L245:
        if ((r21 instanceof TLRPC.TL_contacts_importContacts) == false) goto L251;
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L249;
        showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
        goto L484
    L249:
        showSimpleAlert(r1, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r19.text);
        goto L484
    L251:
        if ((r21 instanceof TL_account.getPassword) == false) goto L253;
    L273:
        boolean r25 = r19.text.startsWith("FLOOD_WAIT");
        String r04 = r19.text;
        if (r25 == false) goto L276;
        showSimpleToast(r1, getFloodWaitString(r04));
        goto L484
    L276:
        showSimpleToast(r1, r04);
        goto L484
    L253:
        if ((r21 instanceof TL_account.getTmpPassword) == true) goto L273;
        if ((r21 instanceof TLRPC.TL_payments_sendPaymentForm) == false) goto L265;
        String r26 = r19.text;
        r26.getClass();
        if (r26.equals("BOT_PRECHECKOUT_FAILED") == false) goto L260;
        showSimpleToast(r1, LocaleController.getString(R.string.PaymentPrecheckoutFailed));
        goto L484
    L260:
        if (r26.equals("PAYMENT_FAILED") == true) goto L262;
        showSimpleToast(r1, r19.text);
        goto L484
    L262:
        showSimpleToast(r1, LocaleController.getString(R.string.PaymentFailed));
        goto L484
    L265:
        if ((r21 instanceof TLRPC.TL_payments_validateRequestedInfo) == false) goto L271;
        String r27 = r19.text;
        r27.getClass();
        if (r27.equals("SHIPPING_NOT_AVAILABLE") == true) goto L269;
        showSimpleToast(r1, r19.text);
        goto L484
    L269:
        showSimpleToast(r1, LocaleController.getString(R.string.PaymentNoShippingMethod));
        goto L484
    L271:
        if ((r21 instanceof TLRPC.TL_payments_assignPlayMarketTransaction) == false) goto L484;
        showSimpleAlert(r1, LocaleController.getString(R.string.PaymentConfirmationError) + "\n" + r19.text);
    L278:
        if (r19.text.contains("PHONE_CODE_EMPTY") == true) goto L300;
        if (r19.text.contains("PHONE_CODE_INVALID") == true) goto L300;
        if (r19.text.contains("CODE_INVALID") == true) goto L300;
        if (r19.text.contains("CODE_EMPTY") == true) goto L300;
        if (r19.text.contains("PHONE_CODE_EXPIRED") == true) goto L298;
        if (r19.text.contains("EMAIL_VERIFY_EXPIRED") == true) goto L298;
        if (r19.text.startsWith("FLOOD_WAIT") == false) goto L296;
        return showSimpleAlert(r1, LocaleController.getString(R.string.FloodWait));
    L296:
        return showSimpleAlert(r1, r19.text);
    L298:
        return showSimpleAlert(r1, LocaleController.getString(R.string.CodeExpired));
    L300:
        return showSimpleAlert(r1, LocaleController.getString(R.string.InvalidCode));
    L99:
        if (r19.text.equals("MESSAGE_NOT_MODIFIED") == true) goto L484;
        if (r1 == null) goto L102;
        showSimpleAlert(r1, LocaleController.getString(R.string.EditMessageError));
        goto L484
    L102:
        showSimpleToast(null, LocaleController.getString(R.string.EditMessageError));
        goto L484
    L20:
        if (r19.text.contains("PRIVACY_PREMIUM_REQUIRED") == false) goto L31;
        long r28 = DialogObject.getPeerDialogId(((TLRPC.TL_messages_sendMessage) r21).peer);
        if (r28 < 0) goto L24;
        String r05 = UserObject.getFirstName(MessagesController.getInstance(r18).getUser(Long.valueOf(r28)));
    L28:
        if (r1 != null) goto L30;
        r1 = LaunchActivity.getLastFragment();
    L30:
        showSimpleAlert(r1, LocaleController.getString(R.string.MessagePremiumErrorTitle), LocaleController.formatString(R.string.MessagePremiumErrorMessage, new Object[]{r05}));
        MessagesController.getInstance(r18).invalidateUserPremiumBlocked(r28, 0);
        goto L484
    L24:
        TLRPC.Chat r06 = MessagesController.getInstance(r18).getChat(Long.valueOf(-r28));
        if (r06 == null) goto L27;
        r05 = r06.title;
        goto L28
    L27:
        r05 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L28
    }

    public static void showAddBrowserException(Context r10, Theme.ResourcesProvider r11, boolean r12, Utilities.Callback<String> r13) {
        Activity r0 = AndroidUtilities.findActivity(r10);
        if (r0 == null) goto L5;
        View r02 = r0.getCurrentFocus();
    L6:
        AlertDialog[] r2 = new AlertDialog[1];
        AlertDialog.Builder r3 = new AlertDialog.Builder(r10, r11);
        if (r12 == false) goto L9;
        int r4 = R.string.BrowserSettingsAddTitle;
    L10:
        r3.setTitle(LocaleController.getString(r4));
        if (r12 == false) goto L13;
        int r122 = R.string.BrowserSettingsAddText;
    L14:
        r3.setMessage(LocaleController.getString(r122));
        EditTextBoldCursor r123 = new EditTextBoldCursor(r10);
        r123.setTextSize(1, 16.0f);
        int r5 = Theme.key_dialogTextBlack;
        r123.setTextColor(Theme.getColor(r5, r11));
        r123.setHintTextColor(Theme.getColor(Theme.key_groupcreate_hintText, r11));
        r123.setHint(LocaleController.getString(R.string.BrowserSettingsAddHint));
        r123.setInputType(17);
        r123.setImeOptions(6);
        r123.setSingleLine(true);
        r123.setFocusable(true);
        r123.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(13.0f));
        r123.setCursorWidth(1.5f);
        r123.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4, r11));
        GradientDrawable r42 = new GradientDrawable();
        r42.setCornerRadius(AndroidUtilities.dp(22.0f));
        r42.setColor(Theme.multAlpha(Theme.getColor(r5, r11), 0.06f));
        r123.setBackground(r42);
        AlertsCreator$$ExternalSyntheticLambda116 r112 = new AlertsCreator$$ExternalSyntheticLambda116(r123, r13, r2, r02);
        r123.setOnEditorActionListener(new AlertsCreator$$ExternalSyntheticLambda117(r112));
        LinearLayout r132 = new LinearLayout(r10);
        r132.setOrientation(1);
        r132.addView(r123, LayoutHelper.createLinear(-1, -2, 24.0f, 4.0f, 24.0f, 9.0f));
        r3.makeCustomMaxHeight();
        r3.setView(r132);
        r3.setWidth(Math.min(AndroidUtilities.dp(320.0f), (AndroidUtilities.displaySize.x * 85) / 100));
        r3.setPositiveButton(LocaleController.getString(R.string.Done), new AlertsCreator$$ExternalSyntheticLambda118(r112));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), new AlertsCreator$$ExternalSyntheticLambda119());
        AlertDialog r102 = r3.create();
        r2[0] = r102;
        r102.setDismissDialogByButtons(false);
        r2[0].setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda120(r123));
        r2[0].setOnShowListener(new AlertsCreator$$ExternalSyntheticLambda121(r123));
        r2[0].show();
        return;
    L13:
        r122 = R.string.BrowserSettingsAddTextExternal;
        goto L14
    L9:
        r4 = R.string.BrowserSettingsAddTitleExternal;
        goto L10
    L5:
        r02 = null;
        goto L6
    }

    public static void showAddLinkToPoll(Context r19, Theme.ResourcesProvider r20, String r21, TLRPC.WebPage r22, Utilities.Callback<String> r23, Runnable r24) {
        Activity r5 = AndroidUtilities.findActivity(r19);
        if (r5 == null) goto L5;
        View r52 = r5.getCurrentFocus();
    L6:
        AlertDialog[] r7 = new AlertDialog[1];
        AlertDialog.Builder r8 = new AlertDialog.Builder(r19, r20);
        r8.setTitle(LocaleController.getString(R.string.PollV2AddLinkTitle));
        r8.setMessage(LocaleController.getString(R.string.PollV2AddLinkMessage));
        AnonymousClass3 r9 = new AnonymousClass3(r19);
        r9.setTextSize(1, 16.0f);
        int r11 = Theme.key_dialogTextBlack;
        r9.setTextColor(Theme.getColor(r11, r20));
        r9.setHintTextColor(Theme.getColor(Theme.key_groupcreate_hintText, r20));
        r9.setHint(LocaleController.getString(R.string.PollV2AddLinkUrlHint));
        r9.setInputType(17);
        r9.setImeOptions(6);
        r9.setMaxLines(10);
        r9.setSingleLine(false);
        r9.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(13.0f));
        r9.setCursorWidth(1.5f);
        r9.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4, r20));
        if (r21 == null) goto L9;
        r9.setText(r21);
        r9.setSelection(r21.length());
    L9:
        r9.setOnEditorActionListener(new AlertsCreator$$ExternalSyntheticLambda255(r9, r23, r7, r52));
        GradientDrawable r2 = new GradientDrawable();
        r2.setCornerRadius(AndroidUtilities.dp(22.0f));
        r2.setColor(Theme.multAlpha(Theme.getColor(r11, r20), 0.06f));
        r9.setBackground(r2);
        LinearLayout r25 = new LinearLayout(r19);
        r25.setOrientation(1);
        r25.addView(r9, LayoutHelper.createLinear(-1, -2, 24.0f, 4.0f, 24.0f, 9.0f));
        if (WebPagePreviewView.hasPreview(r22) == false) goto L12;
        WebPagePreviewView r53 = new WebPagePreviewView(r19, r20, UserConfig.selectedAccount);
        r53.setWebPage(r22);
        r25.addView(r53, LayoutHelper.createLinear(-1, -2, 22.0f, 3.0f, 22.0f, 7.0f));
    L12:
        r8.makeCustomMaxHeight();
        r8.setView(r25);
        r8.setWidth(Math.min(AndroidUtilities.dp(320.0f), (AndroidUtilities.displaySize.x * 85) / 100));
        r8.setPositiveButton(LocaleController.getString(R.string.Done), new AlertsCreator$$ExternalSyntheticLambda256(r9, r23));
        r8.setNegativeButton(LocaleController.getString(R.string.Cancel), new AlertsCreator$$ExternalSyntheticLambda257());
        if (r24 == null) goto L15;
        r8.setNeutralButton(LocaleController.getString(R.string.Delete), new AlertsCreator$$ExternalSyntheticLambda258(r24));
    L15:
        AlertDialog r0 = r8.create();
        r7[0] = r0;
        r0.setDismissDialogByButtons(false);
        r7[0].setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda259(r9));
        r7[0].setOnShowListener(new AlertsCreator$$ExternalSyntheticLambda260(r9));
        r7[0].show();
        TextView r02 = (TextView) r7[0].getButton(-3);
        if (r02 == null) goto L19;
        r02.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L19:
        return;
    L5:
        r52 = null;
        goto L6
    }

    public static void showAddUserAlert(TLRPC.TL_error r4, BaseFragment r5, boolean r6, boolean r7, TLObject r8) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.code != 406) goto L6;
        return;
    L6:
        if (r4.text == null) goto L142;
        if (r5 != null) goto L9;
        return;
    L9:
        if (r5.getParentActivity() == null) goto L144;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r5.getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        String r1 = r4.text;
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -2120721660: goto L95;
            case -2012133105: goto L91;
            case -1763467626: goto L87;
            case -538116776: goto L83;
            case -512775857: goto L79;
            case -454039871: goto L75;
            case -420079733: goto L71;
            case 98635865: goto L67;
            case 517420851: goto L63;
            case 845559454: goto L59;
            case 916342611: goto L55;
            case 1047173446: goto L51;
            case 1167301807: goto L47;
            case 1227003815: goto L43;
            case 1253103379: goto L39;
            case 1355367367: goto L35;
            case 1377621075: goto L31;
            case 1623167701: goto L27;
            case 1754587486: goto L23;
            case 1916725894: goto L19;
            case 1965565720: goto L15;
            default: goto L98;
        };
    L98:
        switch(r3) {
            case 0: goto L137;
            case 1: goto L136;
            case 2: goto L135;
            case 3: goto L132;
            case 4: goto L131;
            case 5: goto L130;
            case 6: goto L127;
            case 7: goto L124;
            case 8: goto L132;
            case 9: goto L122;
            case 10: goto L121;
            case 11: goto L124;
            case 12: goto L118;
            case 13: goto L132;
            case 14: goto L115;
            case 15: goto L111;
            case 16: goto L110;
            case 17: goto L107;
            case 18: goto L106;
            case 19: goto L101;
            case 20: goto L100;
            default: goto L99;
        };
    L99:
        r0.setMessage(LocaleController.getString(R.string.ErrorOccurred) + "\n" + r4.text);
    L138:
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r0.show();
        return;
    L100:
        r0.setTitle(LocaleController.getString(R.string.VoipGroupVoiceChat));
        r0.setMessage(LocaleController.getString(R.string.VoipGroupInviteAlreadyParticipant));
        goto L138
    L101:
        if (r7 == false) goto L103;
        r0.setMessage(LocaleController.getString(R.string.InviteToCommunityError));
        goto L138
    L103:
        if (r6 == false) goto L105;
        r0.setMessage(LocaleController.getString(R.string.InviteToChannelError));
        goto L138
    L105:
        r0.setMessage(LocaleController.getString(R.string.InviteToGroupError));
        goto L138
    L106:
        r0.setMessage(LocaleController.getString(R.string.AddAdminErrorNotAMember));
        goto L138
    L107:
        if (r6 == false) goto L109;
        r0.setMessage(LocaleController.getString(R.string.ChannelUserLeftError));
        goto L138
    L109:
        r0.setMessage(LocaleController.getString(R.string.GroupUserLeftError));
        goto L138
    L110:
        r0.setTitle(LocaleController.getString(R.string.ChannelTooMuchTitle));
        r0.setMessage(LocaleController.getString(R.string.UserChannelTooMuchJoin));
        goto L138
    L111:
        r0.setTitle(LocaleController.getString(R.string.ChannelTooMuchTitle));
        if ((r8 instanceof TLRPC.TL_channels_createChannel) == false) goto L114;
        r0.setMessage(LocaleController.getString(R.string.ChannelTooMuch));
        goto L138
    L114:
        r0.setMessage(LocaleController.getString(R.string.ChannelTooMuchJoin));
        goto L138
    L115:
        if (r6 == false) goto L117;
        r0.setMessage(LocaleController.getString(R.string.ChannelUserCantAdmin));
        goto L138
    L117:
        r0.setMessage(LocaleController.getString(R.string.GroupUserCantAdmin));
        goto L138
    L118:
        if (r6 == false) goto L120;
        r0.setMessage(LocaleController.getString(R.string.ChannelUserAddLimit));
        goto L138
    L120:
        r0.setMessage(LocaleController.getString(R.string.GroupUserAddLimit));
        goto L138
    L121:
        r0.setMessage(LocaleController.getString(R.string.AddBannedErrorAdmin));
        goto L138
    L122:
        r0.setMessage(LocaleController.getString(R.string.YouBlockedUser));
        goto L138
    L127:
        if (r6 == false) goto L129;
        r0.setMessage(LocaleController.getString(R.string.ChannelUserCantBot));
        goto L138
    L129:
        r0.setMessage(LocaleController.getString(R.string.GroupUserCantBot));
        goto L138
    L130:
        r0.setMessage(LocaleController.getString(R.string.NobodyLikesSpam2));
        r0.setNegativeButton(LocaleController.getString(R.string.MoreInfo), new AlertsCreator$$ExternalSyntheticLambda45(r5));
        goto L138
    L131:
        r0.setMessage(LocaleController.getString(R.string.UserRestricted));
        goto L138
    L132:
        if (r6 == false) goto L134;
        r0.setMessage(LocaleController.getString(R.string.ChannelUserCantAdd));
        goto L138
    L134:
        r0.setMessage(LocaleController.getString(R.string.GroupUserCantAdd));
        goto L138
    L135:
        r0.setMessage(LocaleController.getString(R.string.CreateGroupError));
        goto L138
    L136:
        r0.setMessage(LocaleController.getString(R.string.PublicChannelsTooMuch));
        goto L138
    L137:
        r0.setMessage(LocaleController.getString(R.string.LocatedChannelsTooMuch));
        goto L138
    L124:
        if ((r8 instanceof TLRPC.TL_channels_inviteToChannel) == false) goto L126;
        r0.setMessage(LocaleController.getString(R.string.AddUserErrorBlacklisted));
        goto L138
    L126:
        r0.setMessage(LocaleController.getString(R.string.AddAdminErrorBlacklisted));
        goto L138
    L15:
        if (r1.equals("USER_ALREADY_PARTICIPANT") == false) goto L98;
        r3 = 20;
        goto L98
    L19:
        if (r1.equals("USER_PRIVACY_RESTRICTED") == false) goto L98;
        r3 = 19;
        goto L98
    L23:
        if (r1.equals("CHAT_ADMIN_INVITE_REQUIRED") == false) goto L98;
        r3 = 18;
        goto L98
    L27:
        if (r1.equals("USER_NOT_MUTUAL_CONTACT") == false) goto L98;
        r3 = 17;
        goto L98
    L31:
        if (r1.equals("USER_CHANNELS_TOO_MUCH") == false) goto L98;
        r3 = 16;
        goto L98
    L35:
        if (r1.equals("CHANNELS_TOO_MUCH") == false) goto L98;
        r3 = 15;
        goto L98
    L39:
        if (r1.equals("ADMINS_TOO_MUCH") == false) goto L98;
        r3 = 14;
        goto L98
    L43:
        if (r1.equals("USER_ID_INVALID") == false) goto L98;
        r3 = '\r';
        goto L98
    L47:
        if (r1.equals("USERS_TOO_MUCH") == false) goto L98;
        r3 = '\f';
        goto L98
    L51:
        if (r1.equals("CHAT_ADMIN_BAN_REQUIRED") == false) goto L98;
        r3 = 11;
        goto L98
    L55:
        if (r1.equals("USER_ADMIN_INVALID") == false) goto L98;
        r3 = '\n';
        goto L98
    L59:
        if (r1.equals("YOU_BLOCKED_USER") == false) goto L98;
        r3 = '\t';
        goto L98
    L63:
        if (r1.equals("USER_BOT") == false) goto L98;
        r3 = '\b';
        goto L98
    L67:
        if (r1.equals("USER_KICKED") == false) goto L98;
        r3 = 7;
        goto L98
    L71:
        if (r1.equals("BOTS_TOO_MUCH") == false) goto L98;
        r3 = 6;
        goto L98
    L75:
        if (r1.equals("PEER_FLOOD") == false) goto L98;
        r3 = 5;
        goto L98
    L79:
        if (r1.equals("USER_RESTRICTED") == false) goto L98;
        r3 = 4;
        goto L98
    L83:
        if (r1.equals("USER_BLOCKED") == false) goto L98;
        r3 = 3;
        goto L98
    L87:
        if (r1.equals("USERS_TOO_FEW") == false) goto L98;
        r3 = 2;
        goto L98
    L91:
        if (r1.equals("CHANNELS_ADMIN_PUBLIC_TOO_MUCH") == false) goto L98;
        r3 = 1;
        goto L98
    L95:
        if (r1.equals("CHANNELS_ADMIN_LOCATED_TOO_MUCH") == false) goto L98;
        r3 = 0;
        goto L98
    L144:
        return;
    }

    public static AlertDialog showAlertWithCheckbox(Context r8, CharSequence r9, CharSequence r10, CharSequence r11, CharSequence r12, Utilities.Callback<Boolean> r13, Theme.ResourcesProvider r14) {
        return showAlertWithCheckbox(r8, r9, r10, r11, r12, r13, r14, false);
    }

    public static AlertDialog showAlertWithCheckbox(Context r21, CharSequence r22, CharSequence r23, CharSequence r24, CharSequence r25, Utilities.Callback<Boolean> r26, Theme.ResourcesProvider r27, boolean r28) {
        if (r21 != null) goto L6;
        r26.run(Boolean.FALSE);
        return null;
    L6:
        AlertDialog.Builder r4 = new AlertDialog.Builder(r21, r27);
        CheckBoxCell[] r6 = new CheckBoxCell[1];
        boolean[] r7 = new boolean[1];
        AnonymousClass8 r8 = new AnonymousClass8(r21);
        NotificationCenter.listenEmojiLoading(r8);
        r8.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r27));
        r8.setTextSize(1, 16.0f);
        int r10 = 3;
        if (LocaleController.isRTL == false) goto L9;
        int r9 = 5;
    L10:
        r8.setGravity(r9 | 48);
        r8.setText(r23);
        AnonymousClass9 r92 = new AnonymousClass9(r21, r6);
        r4.setCustomViewOffset(6);
        r4.setView(r92);
        TextView r12 = new TextView(r21);
        r12.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r27));
        r12.setTextSize(1, 20.0f);
        r12.setTypeface(AndroidUtilities.bold());
        r12.setLines(1);
        r12.setMaxLines(1);
        r12.setSingleLine(true);
        if (LocaleController.isRTL == false) goto L13;
        int r13 = 5;
    L14:
        r12.setGravity(r13 | 16);
        r12.setEllipsize(TextUtils.TruncateAt.END);
        r12.setText(r22);
        if (LocaleController.isRTL == false) goto L17;
        int r132 = 5;
    L18:
        r92.addView(r12, LayoutHelper.createFrame(-1, -2.0f, r132 | 48, 24.0f, 8.0f, 24.0f, 0.0f));
        if (LocaleController.isRTL == false) goto L21;
        int r122 = 5;
    L22:
        r92.addView(r8, LayoutHelper.createFrame(-2, -2.0f, r122 | 48, 24.0f, 48.0f, 24.0f, 6.0f));
        if (TextUtils.isEmpty(r24) == true) goto L36;
        CheckBoxCell r82 = new CheckBoxCell(r21, 1, r27);
        r6[0] = r82;
        r82.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r27), 7, AndroidUtilities.dp(12.0f)));
        r6[0].setMultiline(true);
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) r6[0].getCheckBoxView().getLayoutParams();
        r2.topMargin = 0;
        if (LocaleController.isRTL == false) goto L27;
        r10 = 5;
    L27:
        r2.gravity = r10 | 16;
        r6[0].getCheckBoxView().setLayoutParams(r2);
        r6[0].setText(r24, _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        CheckBoxCell r29 = r6[0];
        if (LocaleController.isRTL == false) goto L30;
        int r83 = AndroidUtilities.dp(4.0f);
    L31:
        int r11 = AndroidUtilities.dp(12.0f);
        if (LocaleController.isRTL == false) goto L34;
        int r102 = 0;
    L35:
        r29.setPadding(r83, r11, r102, AndroidUtilities.dp(12.0f));
        r92.addView(r6[0], LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 0.0f));
        r6[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda74(r7));
        goto L36
    L34:
        r102 = AndroidUtilities.dp(4.0f);
        goto L35
    L30:
        r83 = 0;
    L36:
        r4.setPositiveButton(r25, new AlertsCreator$$ExternalSyntheticLambda75(r26, r7));
        r4.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r0 = r4.create();
        if (r28 == false) goto L39;
        r0.setShowStarsBalance(true);
    L39:
        r0.show();
        return r0;
    L21:
        r122 = 3;
        goto L22
    L17:
        r132 = 3;
        goto L18
    L13:
        r13 = 3;
        goto L14
    L9:
        r9 = 3;
        goto L10
    }

    public static AlertDialog showAlertWithCheckboxWithBalance(Context r8, CharSequence r9, CharSequence r10, CharSequence r11, CharSequence r12, Utilities.Callback<Boolean> r13, Theme.ResourcesProvider r14) {
        return showAlertWithCheckbox(r8, r9, r10, r11, r12, r13, r14, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AlertDialog showBanGroupCreatorFromCommunityConfirmAlert(Context r16, Theme.ResourcesProvider r17, int r18, long r19, ArrayList<Long> r21, MessagesStorage.LongCallback r22, Runnable r23) {
        LinearLayout r2 = new LinearLayout(r16);
        r2.setOrientation(1);
        AlertDialog[] r3 = new AlertDialog[1];
        AlertDialog.Builder r4 = new AlertDialog.Builder(r16, r17);
        r4.setTitle(LocaleController.getString(R.string.CommunityBanWarningTitle));
        r4.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("CommunityBanWarningMessage", r21.size(), new Object[]{DialogObject.getShortName(r18, r19)})));
        r4.setPositiveButton(LocaleController.getString(R.string.Ban), new AlertsCreator$$ExternalSyntheticLambda237(r23));
        r4.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r4.setView(r2);
        int r5 = r21.size();
        int r7 = 0;
        int r8 = 0;
    L3:
        if (r8 >= r5) goto L12;
        Long r11 = r21.get(r8);
        r8 = r8 + 1;
        Long r112 = r11;
        long r12 = r112.longValue();
        TLRPC.Chat r113 = MessagesController.getInstance(r18).getChat(r112);
        TLRPC.ChatFull r14 = MessagesController.getInstance(r18).getChatFull(r12);
        if (r113 == null) goto L3;
        CommunityBanGroupConfirmCell r15 = new CommunityBanGroupConfirmCell(r16, r17);
        r15.titleView.setText(DialogObject.getName(r113));
        TextView r6 = r15.subtitleView;
        if (r14 == null) goto L10;
        boolean r232 = r7;
        String r72 = LocaleController.formatPluralString("Members", r14.participants_count, new Object[r7]);
    L11:
        r6.setText(r72);
        r15.avatarView.setForUserOrChat(r113, new AvatarDrawable(r113));
        r15.setBackground(Theme.getSelectorDrawable(r232));
        r15.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda238(r3, r22, r12));
        r2.addView(r15, LayoutHelper.createLinear(-1, -2));
        r7 = r232;
        goto L3
    L10:
        r232 = r7;
        r72 = null;
        goto L11
    L12:
        AlertDialog r0 = r4.create();
        r3[r7] = r0;
        r0.show();
        TextView r1 = (TextView) r0.getButton(-1);
        if (r1 == null) goto L15;
        r1.setTextColor(Theme.getColor(Theme.key_text_RedBold));
    L15:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v8 */
    public static AlertDialog showBanGroupCreatorFromCommunityJoinedChatsAlert(Context r16, Theme.ResourcesProvider r17, int r18, long r19, ArrayList<Long> r21, MessagesStorage.LongCallback r22) {
        LinearLayout r2 = new LinearLayout(r16);
        r2.setOrientation(1);
        AlertDialog[] r3 = new AlertDialog[1];
        AlertDialog.Builder r4 = new AlertDialog.Builder(r16, r17);
        r4.setTitle(LocaleController.getString(R.string.CommunityBanUserTitle));
        r4.setMessage(AndroidUtilities.replaceTags(LocaleController.formatPluralString("CommunityBanWillRemoveFromChats", r21.size(), new Object[]{DialogObject.getShortName(r18, r19)})));
        r4.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r4.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r4.setView(r2);
        int r5 = r21.size();
        int r7 = 0;
        int r8 = 0;
    L3:
        if (r8 >= r5) goto L12;
        Long r10 = r21.get(r8);
        r8 = r8 + 1;
        Long r102 = r10;
        long r11 = r102.longValue();
        TLRPC.Chat r103 = MessagesController.getInstance(r18).getChat(r102);
        TLRPC.ChatFull r13 = MessagesController.getInstance(r18).getChatFull(r11);
        if (r103 == null) goto L3;
        CommunityBanGroupConfirmCell r14 = new CommunityBanGroupConfirmCell(r16, r17, r7);
        r14.titleView.setText(DialogObject.getName(r103));
        TextView r6 = r14.subtitleView;
        if (r13 == null) goto L10;
        boolean r20 = r7;
        String r72 = LocaleController.formatPluralString("Members", r13.participants_count, new Object[r7]);
    L11:
        r6.setText(r72);
        r14.avatarView.setForUserOrChat(r103, new AvatarDrawable(r103));
        r14.setBackground(Theme.getSelectorDrawable(r20));
        r14.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda261(r3, r22, r11));
        r2.addView(r14, LayoutHelper.createLinear(-1, -2));
        r7 = r20;
        goto L3
    L10:
        r20 = r7;
        r72 = null;
        goto L11
    L12:
        AlertDialog r0 = r4.create();
        r3[r7] = r0;
        r0.show();
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void showBlockReportSpamAlert(BaseFragment r18, long r19, TLRPC.User r21, TLRPC.Chat r22, TLRPC.EncryptedChat r23, boolean r24, TLRPC.ChatFull r25, MessagesStorage.IntCallback r26, Theme.ResourcesProvider r27) {
        Theme.ResourcesProvider r2 = r27;
        if (r18 != null) goto L5;
        return;
    L5:
        if (r18.getParentActivity() == null) goto L60;
        AccountInstance r3 = r18.getAccountInstance();
        AlertDialog.Builder r11 = new AlertDialog.Builder(r18.getParentActivity(), r2);
        SharedPreferences r4 = MessagesController.getNotificationsSettings(r18.getCurrentAccount());
        boolean r5 = true;
        if (r23 != null) goto L13;
        StringBuilder r8 = new StringBuilder("dialog_bar_report");
        long r9 = r19;
        r8.append(r9);
        if (r4.getBoolean(r8.toString(), false) == true) goto L14;
        Object[] r42 = false;
    L15:
        if (r21 == null) goto L36;
        r11.setTitle(LocaleController.formatString("BlockUserTitle", R.string.BlockUserTitle, new Object[]{UserObject.getFirstName(r21)}));
        r11.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("BlockUserAlert", R.string.BlockUserAlert, new Object[]{UserObject.getFirstName(r21)})));
        CharSequence r1 = LocaleController.getString(R.string.BlockContact);
        int r82 = 2;
        CheckBoxCell[] r14 = new CheckBoxCell[2];
        LinearLayout r15 = new LinearLayout(r18.getParentActivity());
        r15.setOrientation(1);
        int r12 = 0;
    L17:
        if (r12 >= r82) goto L35;
        if (r12 != 0) goto L21;
        if (r42 == true) goto L21;
        CharSequence r17 = r1;
    L34:
        r12 = r12 + 1;
        r2 = r27;
        r1 = r17;
        r82 = 2;
    L21:
        CheckBoxCell r83 = new CheckBoxCell(r18.getParentActivity(), r5 ? 1 : 0, r2);
        r14[r12] = r83;
        r83.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r14[r12].setTag(Integer.valueOf(r12));
        if (r12 != 0) goto L24;
        r17 = r1;
        r14[r12].setText(LocaleController.getString(R.string.DeleteReportSpam), _UrlKt.FRAGMENT_ENCODE_SET, r5, false);
    L25:
        CheckBoxCell r13 = r14[r12];
        if (LocaleController.isRTL == false) goto L28;
        int r28 = AndroidUtilities.dp(16.0f);
    L30:
        if (LocaleController.isRTL == false) goto L32;
        int r84 = AndroidUtilities.dp(8.0f);
    L33:
        r13.setPadding(r28, 0, r84, 0);
        r15.addView(r14[r12], LayoutHelper.createLinear(-1, -2));
        r14[r12].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda80(r14));
        goto L34
    L32:
        r84 = AndroidUtilities.dp(16.0f);
        goto L33
    L28:
        r28 = AndroidUtilities.dp(8.0f);
        goto L30
    L24:
        r17 = r1;
        r5 = true;
        r14[r12].setText(LocaleController.formatString("DeleteThisChat", R.string.DeleteThisChat, new Object[0]), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
        goto L25
    L35:
        r11.setView(r15);
        CheckBoxCell[] r43 = r14;
        CharSequence r122 = r1;
    L51:
        r11.setPositiveButton(r122, new AlertsCreator$$ExternalSyntheticLambda81(r21, r3, r43, r9, r22, r23, r24, r26));
        r11.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r16 = r11.create();
        r18.showDialog(r16);
        TextView r0 = (TextView) r16.getButton(-1);
        if (r0 == null) goto L59;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L59:
        return;
    L36:
        if (r22 == null) goto L44;
        if (r24 == false) goto L44;
        r11.setTitle(LocaleController.getString(R.string.ReportUnrelatedGroup));
        if (r25 == null) goto L43;
        TLRPC.ChannelLocation r110 = r25.location;
        if ((r110 instanceof TLRPC.TL_channelLocation) == false) goto L43;
        r11.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("ReportUnrelatedGroupText", R.string.ReportUnrelatedGroupText, new Object[]{((TLRPC.TL_channelLocation) r110).address})));
    L50:
        r122 = LocaleController.getString(R.string.ReportChat);
        r43 = null;
    L43:
        r11.setMessage(LocaleController.getString(R.string.ReportUnrelatedGroupTextNoAddress));
    L44:
        r11.setTitle(LocaleController.getString(R.string.ReportSpamTitle));
        if (ChatObject.isChannel(r22) == true) goto L47;
    L49:
        r11.setMessage(LocaleController.getString(R.string.ReportSpamAlertGroup));
        goto L50
    L47:
        if (r22.megagroup == true) goto L49;
        r11.setMessage(LocaleController.getString(R.string.ReportSpamAlertChannel));
    L14:
        r42 = true;
        goto L15
    L13:
        r9 = r19;
        goto L14
    }

    public static void showBlockReportSpamReplyAlert(ChatActivity r14, MessageObject r15, long r16, Theme.ResourcesProvider r18, Runnable r19) {
        if (r14 != null) goto L5;
        return;
    L5:
        if (r14.getParentActivity() == null) goto L39;
        if (r15 == null) goto L41;
        AccountInstance r3 = r14.getAccountInstance();
        if (r16 <= 0) goto L11;
        TLRPC.User r5 = r3.getMessagesController().getUser(Long.valueOf(r16));
    L12:
        if (r16 >= 0) goto L14;
        TLRPC.Chat r4 = r3.getMessagesController().getChat(Long.valueOf(-r16));
    L15:
        if (r5 != null) goto L18;
        if (r4 != null) goto L18;
        return;
    L18:
        AlertDialog.Builder r9 = new AlertDialog.Builder(r14.getParentActivity(), r18);
        if (r19 != null) goto L21;
        boolean r6 = true;
    L22:
        r9.setDimEnabled(r6);
        r9.setOnPreDismissListener(new AlertsCreator$$ExternalSyntheticLambda112(r19));
        r9.setTitle(LocaleController.getString(R.string.BlockUser));
        if (r5 == null) goto L25;
        r9.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("BlockUserReplyAlert", R.string.BlockUserReplyAlert, new Object[]{UserObject.getFirstName(r5)})));
    L26:
        LinearLayout r2 = new LinearLayout(r14.getParentActivity());
        r2.setOrientation(1);
        CheckBoxCell[] r62 = {new CheckBoxCell(r14.getParentActivity(), 1, r18)};
        r62[0].setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r62[0].setTag(0);
        r62[0].setText(LocaleController.getString(R.string.DeleteReportSpam), _UrlKt.FRAGMENT_ENCODE_SET, true, false);
        CheckBoxCell r0 = r62[0];
        if (LocaleController.isRTL == false) goto L29;
        int r10 = AndroidUtilities.dp(16.0f);
    L31:
        if (LocaleController.isRTL == false) goto L33;
        int r11 = AndroidUtilities.dp(8.0f);
    L34:
        r0.setPadding(r10, 0, r11, 0);
        r2.addView(r62[0], LayoutHelper.createLinear(-1, -2));
        r62[0].setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda113(r62));
        r9.setView(r2);
        r9.setPositiveButton(LocaleController.getString(R.string.BlockAndDeleteReplies), new AlertsCreator$$ExternalSyntheticLambda114(r5, r3, r14, r4, r15, r62, r18));
        r9.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r152 = r9.create();
        r14.showDialog(r152);
        TextView r142 = (TextView) r152.getButton(-1);
        if (r142 == null) goto L40;
        r142.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L40:
        return;
    L33:
        r11 = AndroidUtilities.dp(16.0f);
        goto L34
    L29:
        r10 = AndroidUtilities.dp(8.0f);
        goto L31
    L25:
        r9.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("BlockUserReplyAlert", R.string.BlockUserReplyAlert, new Object[]{r4.title})));
        goto L26
    L21:
        r6 = false;
        goto L22
    L14:
        r4 = null;
        goto L15
    L11:
        r5 = null;
        goto L12
    L41:
        return;
    }

    public static void showCallsForbidden(Context r18, int r19, long r20, Theme.ResourcesProvider r22) {
        BottomSheet.Builder r1 = new BottomSheet.Builder(r18, false, r22);
        LinearLayout r3 = new LinearLayout(r18);
        r3.setOrientation(1);
        r3.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        FrameLayout r5 = new FrameLayout(r18);
        r5.setClipChildren(false);
        r5.setClipToPadding(false);
        r3.addView(r5, LayoutHelper.createLinear(-1, 92, 17, 0, 0, 0, 0));
        FrameLayout r7 = new FrameLayout(r18);
        ImageView r8 = new ImageView(r18);
        r8.setScaleType(ImageView.ScaleType.CENTER);
        r8.setImageResource(R.drawable.story_link);
        r8.setScaleX(2.0f);
        r8.setScaleY(2.0f);
        r7.addView(r8, LayoutHelper.createFrame(-1, -1, 17));
        r7.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r22)));
        r5.addView(r7, LayoutHelper.createFrame(80, 80.0f, 1, 0.0f, 12.0f, 0.0f, 0.0f));
        TextView r52 = new TextView(r18);
        int r72 = Theme.key_windowBackgroundWhiteBlackText;
        r52.setTextColor(Theme.getColor(r72, r22));
        r52.setTextSize(1, 20.0f);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setText(LocaleController.getString(R.string.CallForbiddenInviteLinkTitle));
        r52.setGravity(17);
        r3.addView(r52, LayoutHelper.createLinear(-1, -2, 32.0f, 16.0f, 32.0f, 8.0f));
        TextView r53 = new TextView(r18);
        r53.setTextColor(Theme.getColor(r72, r22));
        r53.setTextSize(1, 14.0f);
        r53.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.CallForbiddenInviteLinkText, new Object[]{DialogObject.getName(r19, r20)})));
        r53.setGravity(17);
        r3.addView(r53, LayoutHelper.createLinear(-1, -2, 32.0f, 0.0f, 32.0f, 18.0f));
        ButtonWithCounterView r4 = new ButtonWithCounterView(r18, r22);
        r4.setText(LocaleController.getString(R.string.CallForbiddenInviteLinkButton), false);
        r3.addView(r4, LayoutHelper.createLinear(-1, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        r1.setCustomView(r3);
        BottomSheet r32 = r1.create();
        r4.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda17(r19, r4, r32, r20, r22));
        r32.fixNavigationBar();
        r32.show();
    }

    public static void showChatWithAdmin(BaseFragment r10, TLRPC.User r11, String r12, boolean r13, int r14) {
        if (r10.getParentActivity() != null) goto L5;
        return;
    L5:
        BottomSheet.Builder r112 = new BottomSheet.Builder(r10.getParentActivity());
        if (r13 == false) goto L9;
        int r132 = R.string.ChatWithAdminChannelTitle;
    L10:
        r112.setTitle(LocaleController.getString(r132), true);
        LinearLayout r133 = new LinearLayout(r10.getParentActivity());
        r133.setOrientation(1);
        TextView r1 = new TextView(r10.getParentActivity());
        r133.addView(r1, LayoutHelper.createLinear(-1, -1, 0, 21, 0, 21, 8));
        r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r1.setTextSize(1, 16.0f);
        r1.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ChatWithAdminMessage", R.string.ChatWithAdminMessage, new Object[]{r12, LocaleController.formatDateAudio(r14, false)})));
        TextView r122 = new TextView(r10.getParentActivity());
        r122.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r122.setGravity(17);
        r122.setTextSize(1, 14.0f);
        r122.setTypeface(AndroidUtilities.bold());
        r122.setText(LocaleController.getString(R.string.IUnderstand));
        r122.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r122.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.getColor(Theme.key_featuredStickers_addButton), Theme.getColor(Theme.key_featuredStickers_addButtonPressed)));
        r133.addView(r122, LayoutHelper.createLinear(-1, 48, 0, 16, 12, 16, 8));
        r112.setCustomView(r133);
        r122.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda79(r112.show()));
        return;
    L9:
        r132 = R.string.ChatWithAdminGroupTitle;
        goto L10
    }

    public static void showCustomNotificationsDialog(BaseFragment r10, long r11, int r13, int r14, ArrayList<NotificationsSettingsActivity.NotificationException> r15, ArrayList<NotificationsSettingsActivity.NotificationException> r16, int r17, MessagesStorage.IntCallback r18) {
        showCustomNotificationsDialog(r10, r11, r13, r14, r15, r16, r17, r18, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.view.View, android.widget.TextView] */
    public static void showCustomNotificationsDialog(BaseFragment r19, long r20, int r22, int r23, ArrayList<NotificationsSettingsActivity.NotificationException> r24, ArrayList<NotificationsSettingsActivity.NotificationException> r25, int r26, MessagesStorage.IntCallback r27, MessagesStorage.IntCallback r28) {
        long r1 = r20;
        if (r19 != null) goto L5;
        return;
    L5:
        if (r19.getParentActivity() == null) goto L31;
        ?? r13 = 0;
        boolean r4 = NotificationsController.getInstance(r26).isGlobalNotificationsEnabled(r1, false, false);
        String r0 = LocaleController.getString(R.string.NotificationsTurnOn);
        ?? r14 = 1;
        String r3 = LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Hours", 1, new Object[0])});
        String r5 = LocaleController.formatString("MuteFor", R.string.MuteFor, new Object[]{LocaleController.formatPluralString("Days", 2, new Object[0])});
        if (r1 == 0) goto L10;
    L12:
        String r6 = LocaleController.getString(R.string.NotificationsCustomize);
    L13:
        String[] r16 = {r0, r3, r5, r6, LocaleController.getString(R.string.NotificationsTurnOff)};
        int[] r17 = {R.drawable.notifications_on, R.drawable.notifications_mute1h, R.drawable.notifications_mute2d, R.drawable.notifications_settings, R.drawable.notifications_off};
        LinearLayout r02 = new LinearLayout(r19.getParentActivity());
        r02.setOrientation(1);
        AlertDialog.Builder r12 = new AlertDialog.Builder(r19.getParentActivity());
        int r32 = 0;
        Object r03 = r02;
    L15:
        if (r32 >= 5) goto L25;
        if (r16[r32] != null) goto L19;
        Object r132 = r03;
        int r18 = r32;
    L24:
        r32 = r18 + 1;
        r1 = r20;
        r03 = r132;
        r13 = 0;
        r14 = 1;
        goto L15
    L19:
        ?? r52 = new TextView(r19.getParentActivity());
        Drawable r62 = r19.getParentActivity().getResources().getDrawable(r17[r32]);
        if (r32 != 4) goto L22;
        r52.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        r62.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_text_RedRegular), PorterDuff.Mode.MULTIPLY));
    L23:
        r52.setTextSize(r14, 16.0f);
        r52.setLines(r14);
        r52.setMaxLines(r14);
        r52.setCompoundDrawablesWithIntrinsicBounds(r62, null, null, null);
        r52.setTag(Integer.valueOf(r32));
        r52.setBackgroundDrawable(Theme.getSelectorDrawable(r13));
        r52.setPadding(AndroidUtilities.dp(24.0f), r13, AndroidUtilities.dp(24.0f), r13);
        r52.setSingleLine(r14);
        r52.setGravity(19);
        r52.setCompoundDrawablePadding(AndroidUtilities.dp(26.0f));
        r52.setText(r16[r32]);
        r03.addView(r52, LayoutHelper.createLinear(-1, 48, 51));
        r18 = r32;
        r132 = r03;
        r52.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda137(r1, r26, r4, r22, r28, r23, r19, r24, r25, r27, r12));
        goto L24
    L22:
        r52.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r62.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogIcon), PorterDuff.Mode.MULTIPLY));
        goto L23
    L25:
        r12.setTitle(LocaleController.getString(R.string.Notifications));
        r12.setView(r03);
        r19.showDialog(r12.create());
        return;
    L10:
        if ((r19 instanceof NotificationsCustomSettingsActivity) == false) goto L12;
        r6 = null;
        goto L13
    }

    public static void showDeclineSuggestedPostDialog(BaseFragment r9, long r10, boolean r12, Utilities.Callback<String> r13) {
        Context r0 = r9.getContext();
        if (r12 == false) goto L5;
        AlertDialog.Builder r122 = new AlertDialogDecor.Builder(r0);
    L6:
        r122.setTitle(LocaleController.getString(R.string.SuggestedMessageDecline));
        r122.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.SuggestedMessageDeclineInfo, new Object[]{MessagesController.getInstance(UserConfig.selectedAccount).getPeerName(r10)})));
        FrameLayout r102 = new FrameLayout(r0);
        int r11 = 0;
        r102.setClipChildren(false);
        EditText r1 = new EditText(r0);
        r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r1.setHint(LocaleController.getString(R.string.SuggestedMessageDeclineReasonHint));
        r1.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        r1.setTextSize(1, 16.0f);
        r1.setBackground(Theme.createEditTextDrawable(r0, true));
        r1.setMaxLines(4);
        r1.setRawInputType(147457);
        r1.setImeOptions(6);
        r1.setFilters(new InputFilter[]{new AnonymousClass63(255, r0)});
        if (LocaleController.isRTL == false) goto L9;
        int r02 = AndroidUtilities.dp(24.0f);
    L10:
        int r4 = AndroidUtilities.dp(8.0f);
        if (LocaleController.isRTL == true) goto L14;
        r11 = AndroidUtilities.dp(24.0f);
    L14:
        r1.setPadding(r02, r4, r11, AndroidUtilities.dp(8.0f));
        r1.setSelection(r1.getText().toString().length());
        r122.setView(r102);
        r122.setPositiveButton(LocaleController.getString(R.string.Decline), new AlertsCreator$$ExternalSyntheticLambda194(r13, r1));
        r122.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r122.setOnPreDismissListener(new AlertsCreator$$ExternalSyntheticLambda195(r1));
        r102.addView(r1, LayoutHelper.createFrame(-1, -2.0f, 0, 23.0f, 0.0f, 23.0f, 21.0f));
        r1.requestFocus();
        AndroidUtilities.runOnUIThread(new AlertsCreator$$ExternalSyntheticLambda196(r1), 100);
        AlertDialog r103 = r122.create();
        r9.showDialog(r103);
        TextView r92 = (TextView) r103.getButton(-1);
        if (r92 == null) goto L18;
        r92.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L18:
        return;
    L9:
        r02 = 0;
        goto L10
    L5:
        r122 = new AlertDialog.Builder(r0);
        goto L6
    }

    public static void showDisableSharingInfo(Context r18, Theme.ResourcesProvider r19, Runnable r20) {
        if (r18 != null) goto L5;
        return;
    L5:
        boolean[] r3 = new boolean[1];
        BottomSheet.Builder r4 = new BottomSheet.Builder(r18);
        Runnable r5 = r4.getDismissRunnable();
        LinearLayout r6 = new LinearLayout(r18);
        r6.setOrientation(1);
        r6.setClipChildren(false);
        r6.setClipToPadding(false);
        RLottieImageView r8 = new RLottieImageView(r18);
        r6.addView(r8, LayoutHelper.createLinear(110, 110, 17, 0, 21, 0, 11));
        r8.setAnimation(R.raw.raised_hand, 110, 110);
        r8.setAutoRepeat(false);
        r8.playAnimation();
        TextView r82 = new TextView(r18);
        r82.setTypeface(AndroidUtilities.bold());
        r82.setGravity(17);
        r82.setText(LocaleController.getString(R.string.DisableSharingInfoHeader));
        r82.setTextSize(1, 20.0f);
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r82.setTextColor(Theme.getColor(r2, r19));
        r6.addView(r82, LayoutHelper.createLinear(-1, -2, 17, 20, 0, 20, 14));
        PremiumFeatureCell r83 = new PremiumFeatureCell(r18, r19);
        r83.title.setText(LocaleController.getString(R.string.DisableSharingInfoHeader1));
        r83.description.setText(LocaleController.getString(R.string.DisableSharingInfoText1));
        r83.nextIcon.setVisibility(8);
        r83.imageView.setImageResource(R.drawable.menu_photo_off_24);
        r83.imageView.setColorFilter(Theme.getColor(r2, r19));
        r6.addView(r83, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, -2.0f));
        PremiumFeatureCell r84 = new PremiumFeatureCell(r18, r19);
        r84.title.setText(LocaleController.getString(R.string.DisableSharingInfoHeader2));
        r84.description.setText(LocaleController.getString(R.string.DisableSharingInfoText2));
        r84.nextIcon.setVisibility(8);
        r84.imageView.setImageResource(R.drawable.menu_share_off_24);
        r84.imageView.setColorFilter(Theme.getColor(r2, r19));
        r6.addView(r84, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, -2.0f));
        PremiumFeatureCell r85 = new PremiumFeatureCell(r18, r19);
        r85.title.setText(LocaleController.getString(R.string.DisableSharingInfoHeader3));
        r85.description.setText(LocaleController.getString(R.string.DisableSharingInfoText3));
        r85.nextIcon.setVisibility(8);
        r85.imageView.setImageResource(R.drawable.menu_download_off_24);
        r85.imageView.setColorFilter(Theme.getColor(r2, r19));
        r6.addView(r85, LayoutHelper.createLinear(-1, -2, 6.0f, 0.0f, 6.0f, 8.0f));
        ButtonWithCounterView r22 = new ButtonWithCounterView(r18, r19);
        r22.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda70(r3, r5));
        r22.setRound();
        r22.setText(LocaleController.getString(R.string.DisableSharingInfoButton), false);
        r6.addView(r22, LayoutHelper.createLinear(-1, 48, 16.0f, 10.0f, 16.0f, 8.0f));
        r4.setCustomView(r6);
        r4.show().setOnDismissListener(new AlertsCreator$$ExternalSyntheticLambda71(r3, r20));
    }

    public static void showFloodWaitAlert(String r3, BaseFragment r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.startsWith("FLOOD_WAIT") == false) goto L16;
        if (r4 != null) goto L7;
        return;
    L7:
        if (r4.getParentActivity() == null) goto L18;
        int r32 = Utilities.parseInt(r3);
        if (r32 >= 60) goto L12;
        String r33 = LocaleController.formatPluralString("Seconds", r32, new Object[0]);
    L13:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r4.getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        r0.setMessage(LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r33}));
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r4.showDialog(r0.create(), true, null);
        return;
    L12:
        r33 = LocaleController.formatPluralString("Minutes", r32 / 60, new Object[0]);
        goto L13
    L18:
        return;
    }

    public static void showGiftThemeApplyConfirm(Context r8, Theme.ResourcesProvider r9, int r10, TL_stars.StarGift r11, long r12, Runnable r14) {
        TLObject r102 = MessagesController.getInstance(r10).getUserOrChat(r12);
        LinearLayout r122 = new LinearLayout(r8);
        r122.setOrientation(1);
        r122.addView(new StarGiftSheet.GiftThemeReuseTopView(r8, r11, r102), LayoutHelper.createLinear(-1, -2, 48, 0, -4, 0, 0));
        TextView r112 = new TextView(r8);
        r112.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r9));
        r112.setTextSize(1, 16.0f);
        r112.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GiftThemesSetInReuseInfo, new Object[]{DialogObject.getDialogTitle(r102)})));
        r122.addView(r112, LayoutHelper.createLinear(-1, -2, 48, 24, 0, 24, 4));
        new AlertDialog.Builder(r8, r9).setView(r122).setPositiveButton(LocaleController.getString(R.string.GiftThemesSetInReuseConfirm), new AlertsCreator$$ExternalSyntheticLambda205(r14)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
    }

    public static void showLeaveGroupWithFutureOwner(BaseFragment r18, TLRPC.Chat r19, TLRPC.User r20, Runnable r21, Runnable r22) {
        if (r18 != null) goto L5;
        return;
    L5:
        if (r18.getParentActivity() == null) goto L25;
        if (r19 == null) goto L27;
        Context r3 = r18.getContext();
        TLRPC.User r4 = UserConfig.getInstance(r18.getCurrentAccount()).getCurrentUser();
        boolean r5 = ChatObject.isChannelAndNotMegaGroup(r19);
        FrameLayout r6 = new FrameLayout(r3);
        r6.setClipToPadding(false);
        r6.setClipChildren(false);
        BackupImageView r7 = new BackupImageView(r3);
        AvatarDrawable r8 = new AvatarDrawable();
        r8.setInfo(r4);
        r7.setRoundRadius(AndroidUtilities.dp(30.0f));
        r7.setForUserOrChat(r4, r8);
        r6.addView(r7, LayoutHelper.createFrame(60, 60.0f, 17, -48.0f, 15.0f, 0.0f, 12.0f));
        ImageView r42 = new ImageView(r3);
        r42.setImageResource(R.drawable.msg_arrow_avatar);
        r42.setColorFilter(new PorterDuffColorFilter(r18.getThemedColor(Theme.key_divider), PorterDuff.Mode.SRC_IN));
        r6.addView(r42, LayoutHelper.createFrame(24, 24.0f, 17, 0.0f, 15.0f, 0.0f, 12.0f));
        AnonymousClass12 r0 = new AnonymousClass12(r3);
        AvatarDrawable r43 = new AvatarDrawable();
        r43.setInfo(r20);
        r0.setRoundRadius(AndroidUtilities.dp(30.0f));
        r0.setForUserOrChat(r20, r43);
        r6.addView(r0, LayoutHelper.createFrame(60, 60.0f, 17, 48.0f, 15.0f, 0.0f, 12.0f));
        BackupImageView r02 = new BackupImageView(r3);
        AvatarDrawable r44 = new AvatarDrawable();
        r44.setInfo(r19);
        r02.setRoundRadius(AndroidUtilities.dp(12.0f));
        r02.setForUserOrChat(r19, r44);
        r6.addView(r02, LayoutHelper.createFrame(24, 24.0f, 17, 72.0f, 26.0f, 0.0f, 0.0f));
        AlertDialog.Builder r03 = new AlertDialog.Builder(r3);
        r03.setTopViewAspectRatio(-1.0f);
        r03.setTopView(r6);
        if (r5 == false) goto L11;
        int r32 = R.string.LeaveChannelTitle;
    L12:
        r03.setTitle(LocaleController.getString(r32));
        if (r5 == false) goto L15;
        int r33 = R.string.LeaveChannelNewOwnerText;
    L16:
        r03.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(r33, new Object[]{UserObject.getUserName(r20), r19.title})));
        r03.setNegativeButton(LocaleController.getString(R.string.AppointNewOwner), new AlertsCreator$$ExternalSyntheticLambda229(r21));
        r03.setNeutralButton(LocaleController.getString(R.string.Cancel), null);
        if (r5 == false) goto L19;
        int r1 = R.string.LeaveChannel;
    L20:
        r03.setPositiveButton(LocaleController.getString(r1), new AlertsCreator$$ExternalSyntheticLambda230(r22));
        AlertDialog r04 = r03.create();
        r04.show();
        View r05 = r04.getButton(-1);
        if ((r05 instanceof TextView) == false) goto L26;
        ((TextView) r05).setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L26:
        return;
    L19:
        r1 = R.string.LeaveMegaMenu;
        goto L20
    L15:
        r33 = R.string.LeaveGroupNewOwnerText;
        goto L16
    L11:
        r32 = R.string.LeaveGroupTitle;
        goto L12
    L27:
        return;
    }

    public static void showOpenExternalBrowserAlert(Context r16, Theme.ResourcesProvider r17, String r18, boolean r19, boolean r20, Utilities.Callback2<Boolean, Boolean> r21) {
        if (AndroidUtilities.isContextSafe(r16) == true) goto L5;
        return;
    L5:
        AlertDialog[] r4 = new AlertDialog[1];
        AlertDialog.Builder r5 = new AlertDialog.Builder(r16, r17);
        r5.setTitle(LocaleController.getString(R.string.OpenUrlTitle));
        TextView r6 = new TextView(r16);
        r6.setText(r18);
        r6.setTextSize(1, 14.0f);
        int r8 = Theme.key_dialogTextBlack;
        r6.setTextColor(Theme.getColor(r8, r17));
        r6.setGravity(17);
        r6.setMaxLines(5);
        r6.setEllipsize(TextUtils.TruncateAt.END);
        r6.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(12.0f));
        GradientDrawable r7 = new GradientDrawable();
        r7.setCornerRadius(AndroidUtilities.dp(22.0f));
        r7.setColor(Theme.multAlpha(Theme.getColor(r8, r17), 0.06f));
        r6.setBackground(r7);
        CheckBoxCell r72 = new CheckBoxCell(r16, 1, r17);
        r72.setMultiline(true);
        r72.getTextView().getLayoutParams().width = -1;
        r72.getTextView().setSingleLine(false);
        r72.getTextView().setMaxLines(3);
        r72.getTextView().setTextSize(1, 16.0f);
        if (r19 == false) goto L8;
        int r1 = R.string.BrowserAlwaysOpenExternal;
    L9:
        r72.setText(LocaleController.getString(r1), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        r72.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda138(r72));
        LinearLayout r12 = new LinearLayout(r16);
        r12.setOrientation(1);
        r12.addView(r6, LayoutHelper.createLinear(-1, -2, 22.0f, 4.0f, 22.0f, 9.0f));
        if (r20 == false) goto L12;
        r12.addView(r72, LayoutHelper.createLinear(-1, -2, 3, 8, 6, 8, 4));
    L12:
        r5.setView(r12);
        r5.setWidth(Math.min(AndroidUtilities.dp(320.0f), (AndroidUtilities.displaySize.x * 85) / 100));
        r5.setPositiveButton(LocaleController.getString(R.string.Open), new AlertsCreator$$ExternalSyntheticLambda139(r21, r72, r4));
        r5.setNegativeButton(LocaleController.getString(R.string.Cancel), new AlertsCreator$$ExternalSyntheticLambda140(r21, r72, r4));
        r4[0] = r5.show();
        return;
    L8:
        r1 = R.string.BrowserAlwaysOpenInApp;
        goto L9
    }

    public static void showOpenUrlAlert(Context r12, String r13, boolean r14, boolean r15, boolean r16, boolean r17, long r18, Browser.Progress r20, TLRPC.WebPage r21, Theme.ResourcesProvider r22) {
        showOpenUrlAlert(null, r12, r13, r14, r15, r16, r17, r18, r20, r21, r22);
    }

    public static void showOpenUrlAlert(Context r11, String r12, boolean r13, boolean r14, boolean r15, boolean r16, long r17, Browser.Progress r19, Theme.ResourcesProvider r20) {
        showOpenUrlAlert(r11, r12, r13, r14, r15, r16, r17, r19, null, r20);
    }

    private static void showOpenUrlAlert(BaseFragment r17, Context r18, String r19, boolean r20, boolean r21, boolean r22, boolean r23, long r24, Browser.Progress r26, TLRPC.WebPage r27, Theme.ResourcesProvider r28) {
        if (AndroidUtilities.isContextSafe(r18) == true) goto L6;
        return;
    L6:
        if (r19 != null) goto L8;
        String r0 = null;
    L10:
        if (Browser.isInternalUrl(r19, null) == true) goto L26;
        if (r22 == false) goto L26;
        if ("mailto".equalsIgnoreCase(r0) == true) goto L26;
        if (r20 == true) goto L37;
    L20:
        String r11 = r19;
    L21:
        AlertsCreator$$ExternalSyntheticLambda1 r02 = new AlertsCreator$$ExternalSyntheticLambda1(r18, r19, r24, r21, r26);
        AlertDialog[] r3 = new AlertDialog[1];
        AlertDialog.Builder r4 = new AlertDialog.Builder(r18, r28);
        r4.setTitle(LocaleController.getString(R.string.OpenUrlTitle));
        TextView r5 = new TextView(r18);
        r5.setText(r11);
        r5.setTextSize(1, 14.0f);
        int r112 = Theme.key_dialogTextBlack;
        r5.setTextColor(Theme.getColor(r112, r28));
        r5.setGravity(17);
        r5.setMaxLines(5);
        r5.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout r13 = new LinearLayout(r18);
        r13.setOrientation(1);
        r13.setGravity(17);
        r13.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(10.0f));
        GradientDrawable r6 = new GradientDrawable();
        r6.setCornerRadius(AndroidUtilities.dp(22.0f));
        r6.setColor(Theme.multAlpha(Theme.getColor(r112, r28), 0.06f));
        r13.setBackground(r6);
        ScaleStateListAnimator.apply(r13, 0.025f, 1.2f);
        r13.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda2(r3, r19, r17));
        r13.addView(r5, LayoutHelper.createLinear(-1, -2));
        SimpleTextView r52 = new SimpleTextView(r18);
        r52.setText(LocaleController.getString(R.string.TapToCopy));
        r52.setTextSize(12);
        r52.setTextColor(Theme.multAlpha(Theme.getColor(r112, r28), 0.45f));
        r52.setGravity(17);
        r52.setMaxLines(1);
        r52.setPadding(0, AndroidUtilities.dp(6.0f), 0, 0);
        r13.addView(r52, LayoutHelper.createLinear(-1, -2));
        LinearLayout r2 = new LinearLayout(r18);
        r2.setOrientation(1);
        r2.addView(r13, LayoutHelper.createLinear(-1, -2, 22.0f, 4.0f, 22.0f, 9.0f));
        if (WebPagePreviewView.hasPreview(r27) == false) goto L24;
        WebPagePreviewView r53 = new WebPagePreviewView(r18, r28, UserConfig.selectedAccount);
        r53.setWebPage(r27);
        r2.addView(r53, LayoutHelper.createLinear(-1, -2, 22.0f, 3.0f, 22.0f, 7.0f));
    L24:
        r4.setView(r2);
        r4.setWidth(Math.min(AndroidUtilities.dp(320.0f), (AndroidUtilities.displaySize.x * 85) / 100));
        r4.setPositiveButton(LocaleController.getString(R.string.Open), new AlertsCreator$$ExternalSyntheticLambda3(r02));
        r4.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r3[0] = r4.show();
        return;
    L37:
        Uri r03 = Uri.parse(r19);     // Catch: Exception -> L18
        r11 = Browser.replaceHostname(r03, Browser.IDN_toUnicode(r03.getHost()), null);     // Catch: Exception -> L18
    L18:
        e = move-exception;
        FileLog.e(e);
    L26:
        Uri r25 = Uri.parse(r19);
        if (r24 != 0) goto L29;
        boolean r32 = true;
    L30:
        if (r23 == true) goto L32;
    L34:
        boolean r54 = false;
    L35:
        Browser.openUrl(r18, r25, r32, r21, r54, r26, null, false, true, false);
        return;
    L32:
        if (checkInternalBotApp(r19) == false) goto L34;
        r54 = true;
        goto L35
    L29:
        r32 = false;
        goto L30
    L8:
        r0 = Uri.parse(r19).getScheme();
        goto L10
    }

    public static void showOpenUrlAlert(BaseFragment r8, String r9, boolean r10, boolean r11) {
        showOpenUrlAlert(r8, r9, r10, true, r11, false, null, null);
    }

    public static void showOpenUrlAlert(BaseFragment r8, String r9, boolean r10, boolean r11, boolean r12, Browser.Progress r13, Theme.ResourcesProvider r14) {
        showOpenUrlAlert(r8, r9, r10, r11, r12, false, r13, r14);
    }

    public static void showOpenUrlAlert(BaseFragment r12, String r13, boolean r14, boolean r15, boolean r16, boolean r17, Browser.Progress r18, TLRPC.WebPage r19, Theme.ResourcesProvider r20) {
        if (r12 != null) goto L4;
        return;
    L4:
        if (r12.getParentActivity() != null) goto L7;
        return;
    L7:
        if ((r12 instanceof ChatActivity) == false) goto L10;
        long r1 = ((ChatActivity) r12).getInlineReturn();
    L9:
        long r7 = r1;
        showOpenUrlAlert(r12, r12.getParentActivity(), r13, r14, r15, r16, r17, r7, r18, r19, r20);
        return;
    L10:
        r1 = 0;
        goto L9
    }

    public static void showOpenUrlAlert(BaseFragment r9, String r10, boolean r11, boolean r12, boolean r13, boolean r14, Browser.Progress r15, Theme.ResourcesProvider r16) {
        showOpenUrlAlert(r9, r10, r11, r12, r13, r14, r15, null, r16);
    }

    public static void showPayForMessageAlert(int r9, long r10, long r12, int r14, Runnable r15) {
        if (r15 != null) goto L5;
        return;
    L5:
        if (r12 > MessagesController.getInstance(r9).getMainSettings().getLong("ask_paid_message_" + r10 + "_price", 0)) goto L8;
        r15.run();
        return;
    L8:
        Activity r92 = AndroidUtilities.getActivity();
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (PhotoViewer.getInstance().isVisible() == true) goto L18;
        if (r0 != null) goto L12;
    L14:
        if (r0 == null) goto L17;
        Theme.ResourcesProvider r2 = r0.getResourceProvider();
    L16:
        Theme.ResourcesProvider r7 = r2;
        String r22 = DialogObject.getShortName(r9, r10);
        if (ChatObject.isMonoForum(r9, r10) == false) goto L24;
        r22 = ForumUtilities.getMonoForumTitle(r9, r10, true);
    L32:
        SpannableStringBuilder r11 = new SpannableStringBuilder();
        int r102 = (int) r12;
        r11.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessage1", r102, new Object[]{r22})));
        r11.append(" ");
        if (r14 != 1) goto L35;
        r11.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessage2One", r102)));
    L36:
        showAlertWithCheckboxWithBalance(r92, LocaleController.getString(R.string.MessageLockedStarsConfirmTitle), r11, LocaleController.getString(R.string.MessageLockedStarsConfirmMessageDontAsk), LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessagePay", r14), new AlertsCreator$$ExternalSyntheticLambda92(r9, r10, r12, r15), r7);
        return;
    L35:
        r11.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessage2Many1", (int) (r14 * r12))));
        r11.append(" ");
        r11.append(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("MessageLockedStarsConfirmMessage2Many2", r14)));
        goto L36
    L24:
        if ((r0 instanceof ChatActivity) == false) goto L32;
        ChatActivity r02 = (ChatActivity) r0;
        if (r02.isComments == false) goto L32;
        if (r02.getDialogId() != r10) goto L32;
        TLRPC.Chat r03 = r02.replyOriginalChat;
        if (r03 == null) goto L32;
        r22 = DialogObject.getShortName(r9, -r03.id);
        goto L32
    L17:
        r2 = null;
        goto L16
    L12:
        if (r0.hasShownSheet() == false) goto L14;
    L18:
        r2 = new DarkThemeResourceProvider();
        goto L16
    }

    public static AlertDialog showSecretLocationAlert(Context r11, int r12, Runnable r13, boolean r14, Theme.ResourcesProvider r15) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        int r122 = MessagesController.getInstance(r12).availableMapProviders;
        if ((r122 & 1) == 0) goto L6;
        r0.add(LocaleController.getString(R.string.MapPreviewProviderTelegram));
        r1.add(0);
    L6:
        if ((r122 & 2) == 0) goto L9;
        r0.add(LocaleController.getString(R.string.MapPreviewProviderGoogle));
        r1.add(1);
    L9:
        if ((r122 & 4) == 0) goto L11;
        r0.add(LocaleController.getString(R.string.MapPreviewProviderYandex));
        r1.add(3);
    L11:
        r0.add(LocaleController.getString(R.string.MapPreviewProviderNobody));
        r1.add(2);
        AlertDialog.Builder r2 = new AlertDialog.Builder(r11, r15);
        r2.setTitle(LocaleController.getString(R.string.MapPreviewProviderTitle));
        LinearLayout r5 = new LinearLayout(r11);
        r5.setOrientation(1);
        r2.setView(r5);
        int r6 = 0;
    L13:
        if (r6 >= r0.size()) goto L19;
        RadioColorCell r7 = new RadioColorCell(r11, r15);
        r7.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        r7.setTag(Integer.valueOf(r6));
        r7.setCheckColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_dialogRadioBackgroundChecked));
        CharSequence r8 = (CharSequence) r0.get(r6);
        if (SharedConfig.mapPreviewType != ((Integer) r1.get(r6)).intValue()) goto L17;
        boolean r9 = true;
    L18:
        r7.setTextAndValue(r8, r9);
        r7.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        r5.addView(r7);
        r7.setOnClickListener(new AlertsCreator$$ExternalSyntheticLambda78(r1, r13, r2));
        r6 = r6 + 1;
        goto L13
    L17:
        r9 = false;
        goto L18
    L19:
        if (r14 == true) goto L21;
        r2.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L21:
        AlertDialog r112 = r2.show();
        if (r14 == false) goto L24;
        r112.setCanceledOnTouchOutside(false);
    L24:
        return r112;
    }

    public static void showSendMediaAlert(int r2, BaseFragment r3, Theme.ResourcesProvider r4) {
        if (r2 == 0) goto L75;
        if (r3 != null) goto L5;
        return;
    L5:
        if (r3.getParentActivity() == null) goto L77;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3.getParentActivity(), r4);
        r0.setTitle(LocaleController.getString(R.string.UnableForward));
        if (r2 != 1) goto L11;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedStickers));
    L73:
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r3.showDialog(r0.create(), true, null);
        return;
    L11:
        if (r2 != 2) goto L14;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedMedia));
        goto L73
    L14:
        if (r2 != 3) goto L17;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPolls));
        goto L73
    L17:
        if (r2 != 4) goto L20;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedStickersAll));
        goto L73
    L20:
        if (r2 != 5) goto L23;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedMediaAll));
        goto L73
    L23:
        if (r2 != 6) goto L26;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPollsAll));
        goto L73
    L26:
        if (r2 != 7) goto L29;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPrivacyVoiceMessages));
        goto L73
    L29:
        if (r2 != 8) goto L32;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPrivacyVideoMessages));
        goto L73
    L32:
        if (r2 != 9) goto L35;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedVideoAll));
        goto L73
    L35:
        if (r2 != 10) goto L38;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPhotoAll));
        goto L73
    L38:
        if (r2 != 11) goto L41;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedVideo));
        goto L73
    L41:
        if (r2 != 12) goto L44;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPhoto));
        goto L73
    L44:
        if (r2 != 13) goto L47;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedVoiceAll));
        goto L73
    L47:
        if (r2 != 14) goto L50;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedVoice));
        goto L73
    L50:
        if (r2 != 15) goto L53;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedRoundAll));
        goto L73
    L53:
        if (r2 != 16) goto L56;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedRound));
        goto L73
    L56:
        if (r2 != 17) goto L59;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedDocumentsAll));
        goto L73
    L59:
        if (r2 != 18) goto L62;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedDocuments));
        goto L73
    L62:
        if (r2 != 19) goto L65;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedMusicAll));
        goto L73
    L65:
        if (r2 != 20) goto L68;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedMusic));
        goto L73
    L68:
        if (r2 != 21) goto L71;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedTodoAll));
        goto L73
    L71:
        if (r2 != 22) goto L73;
        r0.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedTodo));
        goto L73
    L77:
        return;
    }

    public static Dialog showSimpleAlert(BaseFragment r1, String r2) {
        return showSimpleAlert(r1, null, r2);
    }

    public static Dialog showSimpleAlert(BaseFragment r1, String r2, String r3) {
        return showSimpleAlert(r1, r2, r3, null);
    }

    public static Dialog showSimpleAlert(BaseFragment r1, String r2, String r3, Theme.ResourcesProvider r4) {
        if (r1 != null) goto L4;
        r1 = LaunchActivity.getSafeLastFragment();
    L4:
        if (r3 == null) goto L11;
        if (r1 != null) goto L7;
        return null;
    L7:
        if (r1.getParentActivity() == null) goto L14;
        AlertDialog r22 = createSimpleAlert(r1.getParentActivity(), r2, r3, r4).create();
        r1.showDialog(r22);
        return r22;
    L14:
        return null;
    L11:
        return null;
    }

    public static AlertDialog showSimpleConfirmAlert(Context r6, Theme.ResourcesProvider r7, String r8, CharSequence r9, String r10, boolean r11, Runnable r12) {
        AlertDialog r62 = createSimpleConfirmAlert(r6, r7, r8, r9, r10, r12);
        r62.show();
        if (r11 == false) goto L7;
        TextView r72 = (TextView) r62.getButton(-1);
        if (r72 == null) goto L7;
        r72.setTextColor(Theme.getColor(Theme.key_text_RedBold));
    L7:
        return r62;
    }

    public static AlertDialog showSimpleConfirmAlert(BaseFragment r6, String r7, CharSequence r8, String r9, boolean r10, Runnable r11) {
        AlertDialog r72 = createSimpleConfirmAlert(r6.getContext(), r6.getResourceProvider(), r7, r8, r9, r11);
        r6.showDialog(r72);
        if (r10 == false) goto L7;
        TextView r62 = (TextView) r72.getButton(-1);
        if (r62 == null) goto L7;
        r62.setTextColor(Theme.getColor(Theme.key_text_RedBold));
    L7:
        return r72;
    }

    public static Toast showSimpleToast(BaseFragment r1, String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        if (r1 != null) goto L7;
    L9:
        Context r12 = ApplicationLoader.applicationContext;
    L10:
        Toast r13 = Toast.makeText(r12, r2, 1);
        r13.show();     // Catch: Exception -> L13
        return r13;
    L13:
        e = move-exception;
        FileLog.e(e);
        return r13;
    L7:
        if (r1.getParentActivity() == null) goto L9;
        r12 = r1.getParentActivity();
        goto L10
    }

    public static AlertDialog showUpdateAppAlert(Context r3, String r4, boolean r5) {
        if (r3 == null) goto L11;
        if (r4 == null) goto L11;
        AlertDialog.Builder r1 = new AlertDialog.Builder(r3);
        r1.setTitle(LocaleController.getString(R.string.AppName));
        r1.setMessage(r4);
        r1.setPositiveButton(LocaleController.getString(R.string.OK), null);
        if (r5 == false) goto L10;
        r1.setNegativeButton(LocaleController.getString(R.string.UpdateApp), new AlertsCreator$$ExternalSyntheticLambda32(r3));
    L10:
        return r1.show();
    L11:
        return null;
    }

    private static void updateDayPicker(NumberPicker r2, NumberPicker r3, NumberPicker r4) {
        Calendar r0 = Calendar.getInstance();
        r0.set(2, r3.getValue());
        r0.set(1, r4.getValue());
        r2.setMinValue(1);
        r2.setMaxValue(r0.getActualMaximum(5));
    }
}
