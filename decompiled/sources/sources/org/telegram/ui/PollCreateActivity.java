package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.PollEditTextCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.ChatAttachAlertPollLayout;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.HintView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SuggestEmojiView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Stories.recorder.KeyboardNotifier;

/* loaded from: classes6.dex */
public class PollCreateActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate {
    private int addAnswerRow;
    private boolean allowAdding;
    private int allowAddingRow;
    private boolean allowMarking;
    private int allowMarkingRow;
    private boolean anonymousPoll;
    private int anonymousRow;
    private int answerHeaderRow;
    private int[] answerIds;
    private int answerSectionRow;
    private int answerStartRow;
    private final CharSequence[] answers;
    private final boolean[] answersChecks;
    private int answersCount;
    private PollEditTextCell currentCell;
    private PollCreateActivityDelegate delegate;
    private boolean destroyed;
    private ActionBarMenuItem doneItem;
    private TLRPC.MessageMedia editing;
    private int emojiPadding;
    private EmojiView emojiView;
    public boolean emojiViewVisible;
    public boolean emojiViewWasVisible;
    private boolean firstRequestField;
    private boolean hintShowed;
    private HintView hintView;
    private boolean isAnimatePopupClosing;
    boolean isEmojiSearchOpened;
    private boolean isPremium;
    private int keyboardHeight;
    private int keyboardHeightLand;
    private KeyboardNotifier keyboardNotifier;
    private boolean keyboardVisible;
    private int lastSizeChangeValue1;
    private boolean lastSizeChangeValue2;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int maxAnswerId;
    private final int maxAnswersCount;
    private boolean multipleChoise;
    private int multipleRow;
    private int oldAnswersCount;
    private boolean onlyAdding;
    private Runnable openKeyboardRunnable;
    private ChatActivity parentFragment;
    private int questionHeaderRow;
    private int questionRow;
    private int questionSectionRow;
    private CharSequence questionString;
    private int quizOnly;
    private boolean quizPoll;
    private int quizRow;
    private int requestFieldFocusAtPosition;
    private int rowCount;
    private int settingsHeaderRow;
    private int settingsSectionRow;
    private int shiftDp;
    private SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private int solutionInfoRow;
    private int solutionRow;
    private CharSequence solutionString;
    private SuggestEmojiView suggestEmojiPanel;
    private final boolean todo;
    private boolean waitingForKeyboardOpen;
    boolean wasEmojiSearchOpened;

    /* renamed from: org.telegram.ui.PollCreateActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ PollCreateActivity this$0;

        public AnonymousClass1(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PollCreateActivity.m17465$$Nest$fgetcurrentCell(this.this$0) == null) goto L19;
            EditTextBoldCursor r0 = PollCreateActivity.m17465$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (PollCreateActivity.m17467$$Nest$fgetdestroyed(this.this$0) == true) goto L20;
            if (r0 != null) goto L8;
            return;
        L8:
            if (PollCreateActivity.m17502$$Nest$fgetwaitingForKeyboardOpen(this.this$0) == true) goto L10;
            return;
        L10:
            if (PollCreateActivity.m17474$$Nest$fgetkeyboardVisible(this.this$0) == false) goto L12;
            return;
        L12:
            if (AndroidUtilities.usingHardwareInput == false) goto L14;
            return;
        L14:
            if (AndroidUtilities.isInMultiwindow == false) goto L16;
            return;
        L16:
            if (AndroidUtilities.isTablet() == false) goto L26;
            r0.requestFocus();
            AndroidUtilities.showKeyboard(r0);
            AndroidUtilities.cancelRunOnUIThread(PollCreateActivity.m17483$$Nest$fgetopenKeyboardRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(PollCreateActivity.m17483$$Nest$fgetopenKeyboardRunnable(this.this$0), 100);
            return;
        L26:
            return;
        L20:
            return;
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PollCreateActivity this$0;

        public static /* synthetic */ void $r8$lambda$x8oLSvPJK3fDgcB66mVOViQ4Ak8(AnonymousClass2 r0, TLRPC.TL_messageMediaToDo r1, boolean r2, int r3, int r4) {
            r0.lambda$onItemClick$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$yE72CWMhcndsqOdG5pmvZuDFQA0(AnonymousClass2 r0, TLRPC.TL_messageMediaPoll r1, ArrayList r2, boolean r3, int r4, int r5) {
            r0.lambda$onItemClick$1(r1, r2, r3, r4, r5);
        }

        public AnonymousClass2(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(TLRPC.TL_messageMediaToDo r2, boolean r3, int r4, int r5) {
            PollCreateActivity.m17466$$Nest$fgetdelegate(this.this$0).sendPoll(r2, null, r3, r4);
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$onItemClick$1(TLRPC.TL_messageMediaPoll r1, ArrayList r2, boolean r3, int r4, int r5) {
            PollCreateActivity.m17466$$Nest$fgetdelegate(this.this$0).sendPoll(r1, r2, r3, r4);
            this.this$0.finishFragment();
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r12) {
            if (r12 == (-1)) goto L5;
            if (r12 != 1) goto L133;
            boolean r122 = PollCreateActivity.m17501$$Nest$fgettodo(this.this$0);
            PollCreateActivity r0 = this.this$0;
            int r2 = 0;
            if (r122 == false) goto L51;
            CharSequence[] r02 = {ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17488$$Nest$fgetquestionString(r0))};
            ArrayList<TLRPC.MessageEntity> r123 = MediaDataController.getInstance(PollCreateActivity.access$000(this.this$0)).getEntities(r02, true);
            CharSequence r03 = r02[0];
            int r3 = r123.size();
            int r4 = 0;
        L12:
            if (r4 >= r3) goto L17;
            TLRPC.MessageEntity r5 = r123.get(r4);
            if ((r5.offset + r5.length) <= r03.length()) goto L16;
            r5.length = r03.length() - r5.offset;
        L16:
            r4 = r4 + 1;
            goto L12
        L17:
            TLRPC.TL_messageMediaToDo r32 = new TLRPC.TL_messageMediaToDo();
            TLRPC.TodoList r42 = new TLRPC.TodoList();
            r32.todo = r42;
            r42.others_can_append = PollCreateActivity.m17452$$Nest$fgetallowAdding(this.this$0);
            r32.todo.others_can_complete = PollCreateActivity.m17454$$Nest$fgetallowMarking(this.this$0);
            r32.todo.title = new TLRPC.TL_textWithEntities();
            r32.todo.title.text = r03.toString();
            r32.todo.title.entities = r123;
            if (PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0) == null) goto L23;
            int r124 = 0;
            int r04 = 0;
        L21:
            if (r124 >= PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0).length) goto L24;
            r04 = Math.max(r04, PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0)[r124]);
            r124 = r124 + 1;
        L24:
            int r125 = 0;
        L25:
            int r43 = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0).length;
            PollCreateActivity r52 = this.this$0;
            if (r125 >= r43) goto L44;
            if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17462$$Nest$fgetanswers(r52)[r125])) == true) goto L43;
            CharSequence[] r53 = {ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r125])};
            ArrayList<TLRPC.MessageEntity> r44 = MediaDataController.getInstance(PollCreateActivity.access$100(this.this$0)).getEntities(r53, true);
            CharSequence r54 = r53[0];
            int r6 = r44.size();
            int r7 = 0;
        L31:
            if (r7 >= r6) goto L36;
            TLRPC.MessageEntity r8 = r44.get(r7);
            if ((r8.offset + r8.length) <= r54.length()) goto L35;
            r8.length = r54.length() - r8.offset;
        L35:
            r7 = r7 + 1;
            goto L31
        L36:
            TLRPC.TodoItem r62 = new TLRPC.TodoItem();
            TLRPC.TL_textWithEntities r72 = new TLRPC.TL_textWithEntities();
            r62.title = r72;
            r72.text = r54.toString();
            r62.title.entities = r44;
            if (PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0) != null) goto L39;
        L41:
            r04 = r04 + 1;
            r62.id = r04;
        L42:
            r32.todo.list.add(r62);
            goto L43
        L39:
            if (r125 >= PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0).length) goto L41;
            r62.id = PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0)[r125];
        L43:
            r125 = r125 + 1;
            goto L25
        L44:
            boolean r126 = PollCreateActivity.m17484$$Nest$fgetparentFragment(r52).isInScheduleMode();
            PollCreateActivity r05 = this.this$0;
            if (r126 == false) goto L48;
            AlertsCreator.createScheduleDatePickerDialog(PollCreateActivity.m17484$$Nest$fgetparentFragment(r05).getParentActivity(), PollCreateActivity.m17484$$Nest$fgetparentFragment(this.this$0).getDialogId(), new PollCreateActivity$2$$ExternalSyntheticLambda0(this, r32));
            return;
        L48:
            PollCreateActivity.m17466$$Nest$fgetdelegate(r05).sendPoll(r32, null, true, 0);
            this.this$0.finishFragment();
            return;
        L23:
            r04 = 0;
            goto L24
        L51:
            if (PollCreateActivity.m17490$$Nest$fgetquizPoll(r0) == true) goto L53;
        L66:
            CharSequence[] r06 = {ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17488$$Nest$fgetquestionString(this.this$0))};
            ArrayList<TLRPC.MessageEntity> r127 = MediaDataController.getInstance(PollCreateActivity.access$200(this.this$0)).getEntities(r06, true);
            CharSequence r07 = r06[0];
            int r33 = r127.size();
            int r45 = 0;
        L67:
            if (r45 >= r33) goto L72;
            TLRPC.MessageEntity r55 = r127.get(r45);
            if ((r55.offset + r55.length) <= r07.length()) goto L71;
            r55.length = r07.length() - r55.offset;
        L71:
            r45 = r45 + 1;
            goto L67
        L72:
            TLRPC.TL_messageMediaPoll r34 = new TLRPC.TL_messageMediaPoll();
            TLRPC.TL_poll r46 = new TLRPC.TL_poll();
            r34.poll = r46;
            r46.multiple_choice = PollCreateActivity.m17479$$Nest$fgetmultipleChoise(this.this$0);
            r34.poll.quiz = PollCreateActivity.m17490$$Nest$fgetquizPoll(this.this$0);
            r34.poll.public_voters = !PollCreateActivity.m17456$$Nest$fgetanonymousPoll(this.this$0);
            r34.poll.question = new TLRPC.TL_textWithEntities();
            r34.poll.question.text = r07.toString();
            r34.poll.question.entities = r127;
            ArrayList<Integer> r128 = new ArrayList(PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(this.this$0));
            int r08 = 0;
        L74:
            if (r08 >= PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0).length) goto L93;
            if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r08])) == true) goto L92;
            CharSequence[] r56 = {ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r08])};
            ArrayList<TLRPC.MessageEntity> r47 = MediaDataController.getInstance(PollCreateActivity.access$300(this.this$0)).getEntities(r56, true);
            CharSequence r57 = r56[0];
            int r63 = r47.size();
            int r73 = 0;
        L79:
            if (r73 >= r63) goto L84;
            TLRPC.MessageEntity r82 = r47.get(r73);
            if ((r82.offset + r82.length) <= r57.length()) goto L83;
            r82.length = r57.length() - r82.offset;
        L83:
            r73 = r73 + 1;
            goto L79
        L84:
            TLRPC.TL_pollAnswer r64 = new TLRPC.TL_pollAnswer();
            TLRPC.TL_textWithEntities r74 = new TLRPC.TL_textWithEntities();
            r64.text = r74;
            r74.text = r57.toString();
            r64.text.entities = r47;
            r64.option = new byte[]{(byte) (r34.poll.answers.size() + 48)};
            if (PollCreateActivity.m17479$$Nest$fgetmultipleChoise(this.this$0) == true) goto L89;
            if (PollCreateActivity.m17490$$Nest$fgetquizPoll(this.this$0) == true) goto L89;
        L91:
            r34.poll.answers.add(r64);
        L89:
            if (PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0)[r08] == false) goto L91;
            r128.add(Integer.valueOf(r34.poll.answers.size()));
        L92:
            r08 = r08 + 1;
            goto L74
        L93:
            r34.results = new TLRPC.TL_pollResults();
            CharSequence r09 = ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17499$$Nest$fgetsolutionString(this.this$0));
            if (r09 == null) goto L103;
            r34.results.solution = r09.toString();
            ArrayList<TLRPC.MessageEntity> r010 = MediaDataController.getInstance(PollCreateActivity.access$400(this.this$0)).getEntities(new CharSequence[]{r09}, true);
            if (r010 == null) goto L101;
            if (r010.isEmpty() == true) goto L101;
            r34.results.solution_entities = r010;
        L101:
            if (TextUtils.isEmpty(r34.results.solution) == true) goto L103;
            r34.results.flags |= 16;
        L103:
            boolean r011 = PollCreateActivity.m17484$$Nest$fgetparentFragment(this.this$0).isInScheduleMode();
            PollCreateActivity r48 = this.this$0;
            if (r011 == false) goto L107;
            AlertsCreator.createScheduleDatePickerDialog(PollCreateActivity.m17484$$Nest$fgetparentFragment(r48).getParentActivity(), PollCreateActivity.m17484$$Nest$fgetparentFragment(this.this$0).getDialogId(), new PollCreateActivity$2$$ExternalSyntheticLambda1(this, r34, r128));
            return;
        L107:
            PollCreateActivity.m17466$$Nest$fgetdelegate(r48).sendPoll(r34, r128, true, 0);
            this.this$0.finishFragment();
            return;
        L53:
            if (PollCreateActivity.m17468$$Nest$fgetdoneItem(this.this$0).getAlpha() == 1.0f) goto L66;
            int r129 = 0;
        L56:
            if (r2 >= PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0).length) goto L63;
            if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r2])) == true) goto L62;
            if (PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0)[r2] == false) goto L62;
            r129 = r129 + 1;
        L62:
            r2 = r2 + 1;
            goto L56
        L63:
            if (r129 > 0) goto L134;
            PollCreateActivity.m17517$$Nest$mshowQuizHint(this.this$0);
            return;
        L134:
            return;
        L133:
            return;
        L5:
            if (PollCreateActivity.m17511$$Nest$mcheckDiscard(this.this$0, true) == false) goto L132;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ PollCreateActivity this$0;

        public AnonymousClass3(PollCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r2 = 0;
            if (r0 <= AndroidUtilities.dp(20.0f)) goto L5;
        L9:
            int r1 = 0;
        L10:
            setBottomClip(r1);
        L11:
            if (r2 >= r112) goto L48;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L47;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L18;
            r7 = 51;
        L18:
            int r8 = r7 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L21;
            int r73 = (((r14 - r12) - r5) / 2) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L24:
            int r74 = r73 - r9;
        L27:
            if (r8 != 16) goto L29;
            int r82 = ((((r15 - r1) - r13) - r6) / 2) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L34:
            int r43 = r82 - r42;
        L38:
            if (PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0) != null) goto L40;
        L46:
            r3.layout(r74, r43, r5 + r74, r6 + r43);
            goto L47
        L40:
            if (PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0) != r3) goto L46;
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r44 = getMeasuredHeight();
            int r83 = r3.getMeasuredHeight();
        L44:
            r43 = r44 - r83;
            goto L46
        L45:
            r44 = getMeasuredHeight() + r0;
            r83 = r3.getMeasuredHeight();
            goto L44
        L29:
            if (r8 != 48) goto L31;
            r43 = r4.topMargin + getPaddingTop();
            goto L38
        L31:
            if (r8 == 80) goto L33;
            r43 = r4.topMargin;
            goto L38
        L33:
            r82 = ((r15 - r1) - r13) - r6;
            r42 = r4.bottomMargin;
            goto L34
        L21:
            if (r72 == 5) goto L23;
            r74 = r4.leftMargin;
            goto L27
        L23:
            r73 = r14 - r5;
            r9 = r4.rightMargin;
        L47:
            r2 = r2 + 1;
            goto L11
        L48:
            notifyHeightChanged();
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isTablet() == true) goto L9;
            r1 = this.this$0.getEmojiPadding();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r13, int r14) {
            int r6 = View.MeasureSpec.getSize(r13);
            int r1 = View.MeasureSpec.getSize(r14);
            setMeasuredDimension(r6, r1);
            int r7 = r1 - getPaddingTop();
            measureChildWithMargins(PollCreateActivity.access$500(this.this$0), r13, 0, r14, 0);
            int r12 = measureKeyboardHeight();
            if (r12 <= AndroidUtilities.dp(20.0f)) goto L10;
            PollCreateActivity r3 = this.this$0;
            if (r3.emojiViewVisible == true) goto L10;
            if (r3.isEmojiSearchOpened == true) goto L10;
            this.ignoreLayout = true;
            r3.hideEmojiView();
            this.ignoreLayout = false;
        L10:
            if (r12 <= AndroidUtilities.dp(20.0f)) goto L12;
        L16:
            int r32 = 0;
        L18:
            if (r12 > AndroidUtilities.dp(20.0f)) goto L20;
        L22:
            int r8 = r32;
            int r9 = getChildCount();
            int r10 = 0;
        L23:
            if (r10 >= r9) goto L54;
            View r15 = getChildAt(r10);
            if (r15 == null) goto L53;
            if (r15.getVisibility() == 8) goto L53;
            if (r15 == PollCreateActivity.access$600(this.this$0)) goto L53;
            if (PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0) == null) goto L50;
            if (PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0) != r15) goto L50;
            if (AndroidUtilities.isInMultiwindow == true) goto L42;
            if (AndroidUtilities.isTablet() == true) goto L42;
            r15.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r15.getLayoutParams().height, TLObject.FLAG_30));
        L42:
            if (AndroidUtilities.isTablet() == false) goto L48;
            int r2 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L46;
            float r4 = 200.0f;
        L47:
            r15.measure(r2, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r4), (r7 - AndroidUtilities.statusBarHeight) + getPaddingTop()), TLObject.FLAG_30));
            goto L53
        L46:
            r4 = 320.0f;
            goto L47
        L48:
            r15.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r7 - AndroidUtilities.statusBarHeight) + getPaddingTop(), TLObject.FLAG_30));
        L50:
            if (PollCreateActivity.m17477$$Nest$fgetlistView(this.this$0) != r15) goto L52;
            r15.measure(r13, View.MeasureSpec.makeMeasureSpec(r7 - r8, TLObject.FLAG_30));
            goto L53
        L52:
            measureChildWithMargins(r15, r13, 0, r14, 0);
        L53:
            r10 = r10 + 1;
            goto L23
        L54:
            return;
        L20:
            if (this.this$0.isEmojiSearchOpened == false) goto L22;
            r32 = AndroidUtilities.dp(120.0f);
            goto L22
        L12:
            if (AndroidUtilities.isInMultiwindow == true) goto L16;
            if (AndroidUtilities.isTablet() == true) goto L16;
            r32 = this.this$0.getEmojiPadding();
            goto L18
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerListView {
        final /* synthetic */ PollCreateActivity this$0;

        public AnonymousClass4(PollCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void requestChildOnScreen(View r2, View r3) {
            if ((r2 instanceof PollEditTextCell) == true) goto L5;
            return;
        L5:
            super.requestChildOnScreen(r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r3, Rect r4, boolean r5) {
            r4.bottom += AndroidUtilities.dp(60.0f);
            return super.requestChildRectangleOnScreen(r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PollCreateActivity this$0;

        public AnonymousClass5(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            if (r4 == 0) goto L7;
            if (PollCreateActivity.m17472$$Nest$fgethintView(this.this$0) == null) goto L7;
            PollCreateActivity.m17472$$Nest$fgethintView(this.this$0).hide();
        L7:
            if (PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$0) != null) goto L9;
            return;
        L9:
            if (PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$0).isShown() == false) goto L27;
            SuggestEmojiView.AnchorViewDelegate r22 = PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$0).getDelegate();
            boolean r32 = r22 instanceof PollEditTextCell;
            PollCreateActivity r42 = this.this$0;
            if (r32 == false) goto L24;
            RecyclerView.ViewHolder r23 = PollCreateActivity.m17477$$Nest$fgetlistView(r42).findContainingViewHolder((PollEditTextCell) r22);
            PollCreateActivity r33 = this.this$0;
            if (r23 == null) goto L22;
            int r34 = PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r33).getDirection();
            PollCreateActivity r43 = this.this$0;
            if (r34 != 0) goto L17;
            PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r43).setTranslationY((r23.itemView.getY() - AndroidUtilities.dp(166.0f)) + r23.itemView.getMeasuredHeight());
        L19:
            if (PollCreateActivity.m17475$$Nest$fgetlayoutManager(this.this$0).isViewPartiallyVisible(r23.itemView, true, true) == true) goto L28;
            PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$0).forceClose();
            return;
        L28:
            return;
        L17:
            PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r43).setTranslationY(r23.itemView.getY());
            goto L19
        L22:
            PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r33).forceClose();
            return;
        L24:
            PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r42).forceClose();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ PollCreateActivity this$0;
        final /* synthetic */ float val$toY;

        public AnonymousClass6(PollCreateActivity r1, float r2) {
            this.this$0 = r1;
            this.val$toY = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0).setTranslationY(this.val$toY);
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ PollCreateActivity this$0;

        public AnonymousClass7(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0).setTranslationY(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ PollCreateActivity this$0;

        public AnonymousClass8(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PollCreateActivity.m17506$$Nest$fputisAnimatePopupClosing(this.this$0, false);
            PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0).setTranslationY(0.0f);
            this.this$0.hideEmojiView();
        }
    }

    /* renamed from: org.telegram.ui.PollCreateActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements EmojiView.EmojiViewDelegate {
        final /* synthetic */ PollCreateActivity this$0;

        public static /* synthetic */ void $r8$lambda$ncW7q8xYMsIf6hTeUs5J0hDKEOY(AnonymousClass9 r0, AlertDialog r1, int r2) {
            r0.lambda$onClearEmojiRecent$0(r1, r2);
        }

        public AnonymousClass9(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onClearEmojiRecent$0(AlertDialog r1, int r2) {
            PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0).clearRecentEmoji();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isSearchOpened() {
            return this.this$0.isEmojiSearchOpened;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            EditTextBoldCursor r3 = PollCreateActivity.m17465$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (r3 != null) goto L5;
            return false;
        L5:
            r3.dispatchKeyEvent(new KeyEvent(0, 67));
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onClearEmojiRecent() {
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getContext(), PollCreateActivity.access$700(this.this$0));
            r0.setTitle(LocaleController.getString(R.string.ClearRecentEmojiTitle));
            r0.setMessage(LocaleController.getString(R.string.ClearRecentEmojiText));
            r0.setPositiveButton(LocaleController.getString(R.string.ClearButton), new PollCreateActivity$9$$ExternalSyntheticLambda0(this));
            r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            r0.show();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r4, TLRPC.Document r6, String r7, boolean r8) {
            EditTextBoldCursor r82 = PollCreateActivity.m17465$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (r82 != null) goto L5;
            return;
        L5:
            int r0 = r82.getSelectionEnd();
            if (r0 >= 0) goto L17;
            r0 = 0;
        L17:
            SpannableString r2 = new SpannableString(r7);     // Catch: Exception -> L14
            if (r6 == null) goto L11;
            AnimatedEmojiSpan r42 = new AnimatedEmojiSpan(r6, r82.getPaint().getFontMetricsInt());     // Catch: Exception -> L14
        L12:
            r42.cacheType = PollCreateActivity.m17470$$Nest$fgetemojiView(this.this$0).emojiCacheType;     // Catch: Exception -> L14
            r2.setSpan(r42, 0, r2.length(), 33);     // Catch: Exception -> L14
            r82.setText(r82.getText().insert(r0, r2));     // Catch: Exception -> L14
            int r02 = r0 + r2.length();     // Catch: Exception -> L14
            r82.setSelection(r02, r02);     // Catch: Exception -> L14
            return;
        L11:
            r42 = new AnimatedEmojiSpan(r4, r82.getPaint().getFontMetricsInt());     // Catch: Exception -> L14
        L14:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r4) {
            EditTextBoldCursor r3 = PollCreateActivity.m17465$$Nest$fgetcurrentCell(this.this$0).getEditField();
            if (r3 != null) goto L5;
            return;
        L5:
            int r0 = r3.getSelectionEnd();
            if (r0 >= 0) goto L13;
            r0 = 0;
        L13:
            CharSequence r42 = Emoji.replaceEmoji(r4, r3.getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L10
            r3.setText(r3.getText().insert(r0, r42));     // Catch: Exception -> L10
            int r02 = r0 + r42.length();     // Catch: Exception -> L10
            r3.setSelection(r02, r02);     // Catch: Exception -> L10
            return;
        L10:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onSearchOpenClose(int r1) {
            PollCreateActivity r0 = this.this$0;
            if (r1 == 0) goto L5;
            boolean r12 = true;
        L6:
            r0.isEmojiSearchOpened = r12;
            PollCreateActivity.m17496$$Nest$fgetsizeNotifierFrameLayout(r0).requestLayout();
            return;
        L5:
            r12 = false;
            goto L6
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ PollCreateActivity this$0;

        /* renamed from: org.telegram.ui.PollCreateActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends PollEditTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2, boolean r3, int r4, View.OnClickListener r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onActionModeStart(EditTextBoldCursor r1, ActionMode r2) {
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEditTextFocusChanged(boolean r2) {
                PollCreateActivity.m17514$$Nest$monCellFocusChanges(this.this$1.this$0, this, r2);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEmojiButtonClicked(PollEditTextCell r1) {
                PollCreateActivity.m17515$$Nest$monEmojiClicked(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean onPastedMultipleLines(ArrayList<CharSequence> r8) {
                if (r8.isEmpty() == false) goto L5;
                return false;
            L5:
                this.textView.getText().replace(this.textView.getSelectionStart(), this.textView.getSelectionEnd(), r8.remove(0));
                int r0 = 0;
            L7:
                if (r8.isEmpty() == true) goto L15;
                if (r0 >= PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(this.this$1.this$0)) goto L15;
                int r2 = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$1.this$0).length - 1;
            L11:
                ListAdapter r4 = this.this$1;
                if (r2 <= r0) goto L14;
                PollCreateActivity.m17462$$Nest$fgetanswers(r4.this$0)[r2] = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$1.this$0)[r2 - 1];
                r2 = r2 - 1;
                goto L11
            L14:
                PollCreateActivity.m17462$$Nest$fgetanswers(r4.this$0)[r0] = r8.remove(0);
                PollCreateActivity r22 = this.this$1.this$0;
                PollCreateActivity.m17503$$Nest$fputanswersCount(r22, PollCreateActivity.m17464$$Nest$fgetanswersCount(r22) + 1);
                r0 = r0 + 1;
            L15:
                PollCreateActivity.m17518$$Nest$mupdateRows(this.this$1.this$0);
                PollCreateActivity r82 = this.this$1.this$0;
                PollCreateActivity.m17508$$Nest$fputrequestFieldFocusAtPosition(r82, (PollCreateActivity.m17461$$Nest$fgetanswerStartRow(r82) + r0) - 1);
                PollCreateActivity.m17476$$Nest$fgetlistAdapter(this.this$1.this$0).notifyDataSetChanged();
                return true;
            }
        }

        /* renamed from: org.telegram.ui.PollCreateActivity$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ PollEditTextCell val$cell;

            public AnonymousClass2(ListAdapter r1, PollEditTextCell r2) {
                this.this$1 = r1;
                this.val$cell = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r7) {
                if (this.val$cell.getTag() == null) goto L5;
                return;
            L5:
                RecyclerView.ViewHolder r0 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(PollCreateActivity.m17486$$Nest$fgetquestionRow(this.this$1.this$0));
                if (r0 != null) goto L8;
            L13:
                PollCreateActivity.m17507$$Nest$fputquestionString(this.this$1.this$0, r7);
                if (r0 == null) goto L16;
                PollCreateActivity r72 = this.this$1.this$0;
                PollCreateActivity.m17516$$Nest$msetTextLeft(r72, r0.itemView, PollCreateActivity.m17486$$Nest$fgetquestionRow(r72));
            L16:
                PollCreateActivity.m17512$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L8:
                if (PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0) == null) goto L13;
                ImageSpan[] r1 = (ImageSpan[]) r7.getSpans(0, r7.length(), ImageSpan.class);
                int r2 = r1.length;
                int r4 = 0;
            L10:
                if (r4 >= r2) goto L12;
                r7.removeSpan(r1[r4]);
                r4 = r4 + 1;
                goto L10
            L12:
                Emoji.replaceEmoji(r7, this.val$cell.getEditField().getPaint().getFontMetricsInt(), false);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDirection(1);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDelegate(this.val$cell);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r0.itemView.getY());
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).fireUpdate();
                goto L13
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.PollCreateActivity$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends PollEditTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1, Context r2, boolean r3, int r4, View.OnClickListener r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onActionModeStart(EditTextBoldCursor r2, ActionMode r3) {
                if (r2.isFocused() == true) goto L5;
                return;
            L5:
                if (r2.hasSelection() == false) goto L12;
                Menu r22 = r3.getMenu();
                if (r22.findItem(android.R.id.copy) == null) goto L13;
                ChatActivity.fillActionModeMenu(r22, PollCreateActivity.m17484$$Nest$fgetparentFragment(this.this$1.this$0).getCurrentEncryptedChat(), false, true);
                return;
            L13:
                return;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEditTextFocusChanged(boolean r2) {
                PollCreateActivity.m17514$$Nest$monCellFocusChanges(this.this$1.this$0, this, r2);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEmojiButtonClicked(PollEditTextCell r1) {
                PollCreateActivity.m17515$$Nest$monEmojiClicked(this.this$1.this$0, r1);
            }
        }

        /* renamed from: org.telegram.ui.PollCreateActivity$ListAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ PollEditTextCell val$cell;

            public AnonymousClass4(ListAdapter r1, PollEditTextCell r2) {
                this.this$1 = r1;
                this.val$cell = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r7) {
                if (this.val$cell.getTag() == null) goto L5;
                return;
            L5:
                RecyclerView.ViewHolder r0 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(PollCreateActivity.m17486$$Nest$fgetquestionRow(this.this$1.this$0));
                if (r0 != null) goto L8;
            L13:
                PollCreateActivity.m17509$$Nest$fputsolutionString(this.this$1.this$0, r7);
                if (r0 == null) goto L16;
                PollCreateActivity r72 = this.this$1.this$0;
                PollCreateActivity.m17516$$Nest$msetTextLeft(r72, r0.itemView, PollCreateActivity.m17498$$Nest$fgetsolutionRow(r72));
            L16:
                PollCreateActivity.m17512$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L8:
                if (PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0) == null) goto L13;
                ImageSpan[] r1 = (ImageSpan[]) r7.getSpans(0, r7.length(), ImageSpan.class);
                int r2 = r1.length;
                int r4 = 0;
            L10:
                if (r4 >= r2) goto L12;
                r7.removeSpan(r1[r4]);
                r4 = r4 + 1;
                goto L10
            L12:
                Emoji.replaceEmoji(r7, this.val$cell.getEditField().getPaint().getFontMetricsInt(), false);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDirection(1);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDelegate(this.val$cell);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r0.itemView.getY());
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).fireUpdate();
                goto L13
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.PollCreateActivity$ListAdapter$5, reason: invalid class name */
        public class AnonymousClass5 extends PollEditTextCell {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass5(ListAdapter r1, Context r2, boolean r3, int r4, View.OnClickListener r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean drawDivider() {
                RecyclerView.ViewHolder r0 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(this);
                if (r0 == null) goto L10;
                int r02 = r0.getAdapterPosition();
                if (PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$1.this$0) != PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(this.this$1.this$0)) goto L10;
                if (r02 != ((PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0) + PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$1.this$0)) - 1)) goto L10;
                return false;
            L10:
                return true;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean isChecked(PollEditTextCell r2) {
                RecyclerView.ViewHolder r22 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(r2);
                if (r22 == null) goto L8;
                int r23 = r22.getAdapterPosition();
                if (r23 == (-1)) goto L10;
                int r24 = r23 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0);
                return PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$1.this$0)[r24];
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onActionModeStart(EditTextBoldCursor r2, ActionMode r3) {
                if (r2.isFocused() == true) goto L5;
                return;
            L5:
                if (r2.hasSelection() == false) goto L12;
                Menu r22 = r3.getMenu();
                if (r22.findItem(android.R.id.copy) == null) goto L13;
                ChatActivity.fillActionModeMenu(r22, PollCreateActivity.m17484$$Nest$fgetparentFragment(this.this$1.this$0).getCurrentEncryptedChat(), false, true);
                return;
            L13:
                return;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onCheckBoxClick(PollEditTextCell r5, boolean r6) {
                if (r6 == true) goto L4;
            L14:
                super.onCheckBoxClick(r5, r6);
                RecyclerView.ViewHolder r52 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(r5);
                if (r52 == null) goto L19;
                int r53 = r52.getAdapterPosition();
                if (r53 == (-1)) goto L19;
                int r54 = r53 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0);
                PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$1.this$0)[r54] = r6;
            L19:
                PollCreateActivity.m17512$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            L4:
                if (PollCreateActivity.m17490$$Nest$fgetquizPoll(this.this$1.this$0) == false) goto L14;
                Arrays.fill(PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$1.this$0), false);
                PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).getChildCount();
                int r0 = PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0);
            L7:
                if (r0 >= (PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0) + PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$1.this$0))) goto L14;
                RecyclerView.ViewHolder r2 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findViewHolderForAdapterPosition(r0);
                if (r2 == null) goto L13;
                View r22 = r2.itemView;
                if ((r22 instanceof PollEditTextCell) == false) goto L13;
                ((PollEditTextCell) r22).setChecked(false, true);
            L13:
                r0 = r0 + 1;
                goto L7
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEditTextFocusChanged(boolean r2) {
                PollCreateActivity.m17514$$Nest$monCellFocusChanges(this.this$1.this$0, this, r2);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public void onEmojiButtonClicked(PollEditTextCell r1) {
                PollCreateActivity.m17515$$Nest$monEmojiClicked(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean onPastedMultipleLines(ArrayList<CharSequence> r8) {
                if (r8.isEmpty() == false) goto L5;
                return false;
            L5:
                int r0 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).getChildAdapterPosition(this) - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0);
                if (r0 >= 0) goto L8;
                return false;
            L8:
                this.textView.getText().replace(this.textView.getSelectionStart(), this.textView.getSelectionEnd(), r8.remove(0));
                int r02 = r0 + 1;
            L10:
                if (r8.isEmpty() == true) goto L18;
                if (r02 >= PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(this.this$1.this$0)) goto L18;
                int r3 = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$1.this$0).length - 1;
            L14:
                ListAdapter r4 = this.this$1;
                if (r3 <= r02) goto L17;
                PollCreateActivity.m17462$$Nest$fgetanswers(r4.this$0)[r3] = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$1.this$0)[r3 - 1];
                r3 = r3 - 1;
                goto L14
            L17:
                PollCreateActivity.m17462$$Nest$fgetanswers(r4.this$0)[r02] = r8.remove(0);
                PollCreateActivity r32 = this.this$1.this$0;
                PollCreateActivity.m17503$$Nest$fputanswersCount(r32, PollCreateActivity.m17464$$Nest$fgetanswersCount(r32) + 1);
                r02 = r02 + 1;
            L18:
                PollCreateActivity.m17518$$Nest$mupdateRows(this.this$1.this$0);
                PollCreateActivity r82 = this.this$1.this$0;
                PollCreateActivity.m17508$$Nest$fputrequestFieldFocusAtPosition(r82, (PollCreateActivity.m17461$$Nest$fgetanswerStartRow(r82) + r02) - 1);
                PollCreateActivity.m17476$$Nest$fgetlistAdapter(this.this$1.this$0).notifyDataSetChanged();
                return true;
            }

            @Override // org.telegram.ui.Cells.PollEditTextCell
            public boolean shouldShowCheckBox() {
                return PollCreateActivity.m17490$$Nest$fgetquizPoll(this.this$1.this$0);
            }
        }

        /* renamed from: org.telegram.ui.PollCreateActivity$ListAdapter$6, reason: invalid class name */
        public class AnonymousClass6 implements TextWatcher {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ PollEditTextCell val$cell;

            public AnonymousClass6(ListAdapter r1, PollEditTextCell r2) {
                this.this$1 = r1;
                this.val$cell = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r8) {
                RecyclerView.ViewHolder r0 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$1.this$0).findContainingViewHolder(this.val$cell);
                if (r0 == null) goto L22;
                int r1 = r0.getAdapterPosition() - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$1.this$0);
                if (r1 >= 0) goto L7;
                return;
            L7:
                if (r1 < PollCreateActivity.m17462$$Nest$fgetanswers(this.this$1.this$0).length) goto L10;
                return;
            L10:
                if (PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0) == null) goto L19;
                ImageSpan[] r2 = (ImageSpan[]) r8.getSpans(0, r8.length(), ImageSpan.class);
                int r3 = r2.length;
                int r5 = 0;
            L12:
                if (r5 >= r3) goto L14;
                r8.removeSpan(r2[r5]);
                r5 = r5 + 1;
                goto L12
            L14:
                Emoji.replaceEmoji(r8, this.val$cell.getEditField().getPaint().getFontMetricsInt(), false);
                float r22 = (r0.itemView.getY() - AndroidUtilities.dp(166.0f)) + r0.itemView.getMeasuredHeight();
                ListAdapter r52 = this.this$1;
                if (r22 <= 0.0f) goto L17;
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r52.this$0).setDirection(0);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r22);
            L18:
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setDelegate(this.val$cell);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).fireUpdate();
                goto L19
            L17:
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(r52.this$0).setDirection(1);
                PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$1.this$0).setTranslationY(r0.itemView.getY());
            L19:
                PollCreateActivity.m17462$$Nest$fgetanswers(this.this$1.this$0)[r1] = r8;
                PollCreateActivity.m17516$$Nest$msetTextLeft(this.this$1.this$0, this.val$cell, r1);
                PollCreateActivity.m17512$$Nest$mcheckDoneButton(this.this$1.this$0);
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: $r8$lambda$dN-eJEm63DShYxRjXrpacmFzg-4, reason: not valid java name */
        public static /* synthetic */ boolean m17519$r8$lambda$dNeJEm63DShYxRjXrpacmFzg4(PollEditTextCell r1, View r2, int r3, KeyEvent r4) {
            EditTextBoldCursor r22 = (EditTextBoldCursor) r2;
            if (r3 == 67) goto L5;
            return false;
        L5:
            if (r4.getAction() == 0) goto L7;
            return false;
        L7:
            if (r22.length() != 0) goto L13;
            r1.callOnDelete();
            return true;
        L13:
            return false;
        }

        public static /* synthetic */ boolean $r8$lambda$yKPkJ8UNwtQGOLndgh2Wqe3bi1M(ListAdapter r0, PollEditTextCell r1, TextView r2, int r3, KeyEvent r4) {
            return r0.lambda$onCreateViewHolder$0(r1, r2, r3, r4);
        }

        public ListAdapter(PollCreateActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$0(PollEditTextCell r3, TextView r4, int r5, KeyEvent r6) {
            if (r5 != 5) goto L22;
            RecyclerView.ViewHolder r42 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$0).findContainingViewHolder(r3);
            if (r42 == null) goto L21;
            int r43 = r42.getAdapterPosition();
            if (r43 == (-1)) goto L21;
            int r62 = r43 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0);
            if (r62 != (PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0) - 1)) goto L14;
            if (PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0) >= PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(this.this$0)) goto L14;
            PollCreateActivity.m17510$$Nest$maddNewField(this.this$0);
        L14:
            if (r62 != (PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0) - 1)) goto L16;
            AndroidUtilities.hideKeyboard(r3.getTextView());
            goto L21
        L16:
            RecyclerView.ViewHolder r2 = PollCreateActivity.m17477$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(r43 + 1);
            if (r2 == null) goto L21;
            View r22 = r2.itemView;
            if ((r22 instanceof PollEditTextCell) == false) goto L21;
            ((PollEditTextCell) r22).getTextView().requestFocus();
        L21:
            return true;
        L22:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PollCreateActivity.m17493$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != PollCreateActivity.m17485$$Nest$fgetquestionHeaderRow(this.this$0)) goto L5;
            return 0;
        L5:
            if (r2 != PollCreateActivity.m17458$$Nest$fgetanswerHeaderRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 != PollCreateActivity.m17494$$Nest$fgetsettingsHeaderRow(this.this$0)) goto L10;
            return 0;
        L10:
            if (r2 != PollCreateActivity.m17487$$Nest$fgetquestionSectionRow(this.this$0)) goto L14;
            return 1;
        L14:
            if (r2 != PollCreateActivity.m17460$$Nest$fgetanswerSectionRow(this.this$0)) goto L16;
            return 2;
        L16:
            if (r2 != PollCreateActivity.m17495$$Nest$fgetsettingsSectionRow(this.this$0)) goto L18;
            return 2;
        L18:
            if (r2 != PollCreateActivity.m17497$$Nest$fgetsolutionInfoRow(this.this$0)) goto L21;
            return 2;
        L21:
            if (r2 != PollCreateActivity.m17451$$Nest$fgetaddAnswerRow(this.this$0)) goto L25;
            return 3;
        L25:
            if (r2 != PollCreateActivity.m17486$$Nest$fgetquestionRow(this.this$0)) goto L29;
            return 4;
        L29:
            if (r2 != PollCreateActivity.m17498$$Nest$fgetsolutionRow(this.this$0)) goto L33;
            return 7;
        L33:
            if (r2 != PollCreateActivity.m17457$$Nest$fgetanonymousRow(this.this$0)) goto L35;
            return 6;
        L35:
            if (r2 != PollCreateActivity.m17480$$Nest$fgetmultipleRow(this.this$0)) goto L37;
            return 6;
        L37:
            if (r2 != PollCreateActivity.m17491$$Nest$fgetquizRow(this.this$0)) goto L39;
            return 6;
        L39:
            if (r2 != PollCreateActivity.m17453$$Nest$fgetallowAddingRow(this.this$0)) goto L41;
            return 6;
        L41:
            if (r2 == PollCreateActivity.m17455$$Nest$fgetallowMarkingRow(this.this$0)) goto L54;
            return 5;
        L54:
            return 6;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r5) {
            int r52 = r5.getAdapterPosition();
            if (r52 == PollCreateActivity.m17486$$Nest$fgetquestionRow(this.this$0)) goto L33;
            if (r52 == PollCreateActivity.m17453$$Nest$fgetallowAddingRow(this.this$0)) goto L33;
            if (r52 == PollCreateActivity.m17455$$Nest$fgetallowMarkingRow(this.this$0)) goto L33;
            if (PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0) == false) goto L20;
            if (r52 < PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0)) goto L20;
            if (r52 >= (PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0) + PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0))) goto L20;
            if ((r52 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0)) < PollCreateActivity.m17481$$Nest$fgetoldAnswersCount(this.this$0)) goto L18;
            return true;
        L18:
            return false;
        L20:
            if (r52 != PollCreateActivity.m17451$$Nest$fgetaddAnswerRow(this.this$0)) goto L22;
        L31:
            return true;
        L22:
            if (r52 == PollCreateActivity.m17457$$Nest$fgetanonymousRow(this.this$0)) goto L31;
            if (r52 == PollCreateActivity.m17480$$Nest$fgetmultipleRow(this.this$0)) goto L31;
            if (PollCreateActivity.m17489$$Nest$fgetquizOnly(this.this$0) == 0) goto L28;
        L30:
            return false;
        L28:
            if (r52 != PollCreateActivity.m17491$$Nest$fgetquizRow(this.this$0)) goto L30;
        L33:
            return !PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            int r0 = r7.getItemViewType();
            boolean r1 = true;
            if (r0 == 0) goto L75;
            boolean r5 = false;
            if (r0 != 6) goto L7;
            TextCheckCell r72 = (TextCheckCell) r7.itemView;
            r72.setEnabled(!PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0));
            Switch r02 = r72.getCheckBox();
            if (PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0) == true) goto L42;
            float r2 = 1.0f;
        L43:
            r02.setAlpha(r2);
            if (r8 != PollCreateActivity.m17453$$Nest$fgetallowAddingRow(this.this$0)) goto L48;
            r72.setTextAndCheck(LocaleController.getString(R.string.TodoAllowAddingTasks), PollCreateActivity.m17452$$Nest$fgetallowAdding(this.this$0), true);
            r72.setEnabled(true, null);
            return;
        L48:
            if (r8 != PollCreateActivity.m17455$$Nest$fgetallowMarkingRow(this.this$0)) goto L52;
            r72.setTextAndCheck(LocaleController.getString(R.string.TodoAllowMarkingDone), PollCreateActivity.m17454$$Nest$fgetallowMarking(this.this$0), false);
            r72.setEnabled(true, null);
            return;
        L52:
            if (r8 != PollCreateActivity.m17457$$Nest$fgetanonymousRow(this.this$0)) goto L61;
            String r82 = LocaleController.getString(R.string.PollAnonymous);
            boolean r03 = PollCreateActivity.m17456$$Nest$fgetanonymousPoll(this.this$0);
            if (PollCreateActivity.m17480$$Nest$fgetmultipleRow(this.this$0) == (-1)) goto L56;
        L57:
            r5 = true;
        L58:
            r72.setTextAndCheck(r82, r03, r5);
            r72.setEnabled(true, null);
            return;
        L56:
            if (PollCreateActivity.m17491$$Nest$fgetquizRow(this.this$0) == (-1)) goto L58;
        L61:
            if (r8 != PollCreateActivity.m17480$$Nest$fgetmultipleRow(this.this$0)) goto L68;
            String r83 = LocaleController.getString(R.string.PollMultiple);
            boolean r04 = PollCreateActivity.m17479$$Nest$fgetmultipleChoise(this.this$0);
            if (PollCreateActivity.m17491$$Nest$fgetquizRow(this.this$0) == (-1)) goto L65;
            r5 = true;
        L65:
            r72.setTextAndCheck(r83, r04, r5);
            r72.setEnabled(true, null);
            return;
        L68:
            if (r8 != PollCreateActivity.m17491$$Nest$fgetquizRow(this.this$0)) goto L102;
            r72.setTextAndCheck(LocaleController.getString(R.string.PollQuiz), PollCreateActivity.m17490$$Nest$fgetquizPoll(this.this$0), false);
            if (PollCreateActivity.m17489$$Nest$fgetquizOnly(this.this$0) == 0) goto L73;
            r1 = false;
        L73:
            r72.setEnabled(r1, null);
            return;
        L102:
            return;
        L42:
            r2 = 0.6f;
            goto L43
        L7:
            if (r0 != 2) goto L9;
            TextInfoPrivacyCell r73 = (TextInfoPrivacyCell) r7.itemView;
            r73.setFixedSize(0);
            r73.setBackground(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            if (r8 != PollCreateActivity.m17497$$Nest$fgetsolutionInfoRow(this.this$0)) goto L22;
            r73.setText(LocaleController.getString(R.string.AddAnExplanationInfo));
            return;
        L22:
            if (r8 != PollCreateActivity.m17495$$Nest$fgetsettingsSectionRow(this.this$0)) goto L25;
            r73.setFixedSize(12);
            r73.setText(null);
            return;
        L25:
            int r84 = PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(this.this$0) - PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0);
            PollCreateActivity r05 = this.this$0;
            if (r84 <= 0) goto L28;
            boolean r85 = PollCreateActivity.m17501$$Nest$fgettodo(r05);
            PollCreateActivity r06 = this.this$0;
            if (r85 == false) goto L37;
            r73.setText(LocaleController.formatPluralStringComma("TodoNewTaskInfo", PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(r06) - PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0)));
            return;
        L37:
            r73.setText(LocaleController.formatString("AddAnOptionInfo", R.string.AddAnOptionInfo, new Object[]{LocaleController.formatPluralString("Option", PollCreateActivity.m17478$$Nest$fgetmaxAnswersCount(r06) - PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0), new Object[0])}));
            return;
        L28:
            if (PollCreateActivity.m17501$$Nest$fgettodo(r05) == false) goto L30;
            int r6 = R.string.TodoAddTaskInfoMax;
        L31:
            r73.setText(LocaleController.getString(r6));
            return;
        L30:
            r6 = R.string.AddAnOptionInfoMax;
            goto L31
        L9:
            if (r0 != 3) goto L104;
            TextCell r74 = (TextCell) r7.itemView;
            r74.setColors(-1, Theme.key_windowBackgroundWhiteBlueText4);
            Drawable r86 = this.mContext.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r07 = this.mContext.getResources().getDrawable(R.drawable.poll_add_plus);
            int r22 = Theme.getColor(Theme.key_switchTrackChecked);
            PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
            r86.setColorFilter(new PorterDuffColorFilter(r22, r3));
            r07.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck), r3));
            CombinedDrawable r12 = new CombinedDrawable(r86, r07);
            if (PollCreateActivity.m17501$$Nest$fgettodo(this.this$0) == false) goto L14;
            int r62 = R.string.TodoNewTask;
        L15:
            r74.setTextAndIcon(LocaleController.getString(r62), r12, false);
            return;
        L14:
            r62 = R.string.AddAnOption;
            goto L15
        L104:
            return;
        L75:
            HeaderCell r75 = (HeaderCell) r7.itemView;
            int r08 = PollCreateActivity.m17485$$Nest$fgetquestionHeaderRow(this.this$0);
            PollCreateActivity r23 = this.this$0;
            if (r8 == r08) goto L78;
            int r09 = PollCreateActivity.m17458$$Nest$fgetanswerHeaderRow(r23);
            PollCreateActivity r24 = this.this$0;
            if (r8 != r09) goto L99;
            if (PollCreateActivity.m17489$$Nest$fgetquizOnly(r24) != 1) goto L93;
            r75.setText(LocaleController.getString(R.string.QuizAnswers));
            return;
        L93:
            if (PollCreateActivity.m17501$$Nest$fgettodo(this.this$0) == false) goto L95;
            int r63 = R.string.TodoItemsTitle;
        L96:
            r75.setText(LocaleController.getString(r63));
            return;
        L95:
            r63 = R.string.AnswerOptions2;
            goto L96
        L99:
            if (r8 != PollCreateActivity.m17494$$Nest$fgetsettingsHeaderRow(r24)) goto L103;
            r75.setText(LocaleController.getString(R.string.Settings));
            return;
        L103:
            return;
        L78:
            if (PollCreateActivity.m17501$$Nest$fgettodo(r23) == true) goto L80;
            int r64 = R.string.PollQuestion2;
        L84:
            r75.setText(LocaleController.getString(r64));
            return;
        L80:
            if (PollCreateActivity.m17469$$Nest$fgetediting(this.this$0) == null) goto L82;
            r64 = R.string.TodoEditTitle;
            goto L84
        L82:
            r64 = R.string.TodoTitle;
            goto L84
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 0) goto L4;
            View r2 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 15, false);
            r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            View r22 = r2;
        L17:
            View r1 = r22;
        L24:
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r1);
        L4:
            if (r10 != 1) goto L6;
            r1 = new ShadowSectionCell(this.mContext);
            goto L24
        L6:
            if (r10 != 2) goto L8;
            r1 = new TextInfoPrivacyCell(this.mContext);
            goto L24
        L8:
            if (r10 != 3) goto L10;
            View r12 = new TextCell(this.mContext);
            r12.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r1 = r12;
            goto L24
        L10:
            if (r10 != 4) goto L12;
            AnonymousClass1 r23 = new AnonymousClass1(this, this.mContext, false, PollCreateActivity.m17473$$Nest$fgetisPremium(this.this$0) ? 1 : 0, null);
            r23.createErrorTextView();
            r23.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r23.addTextWatcher(new AnonymousClass2(this, r23));
            r22 = r23;
            goto L17
        L12:
            if (r10 != 6) goto L14;
            View r13 = new TextCheckCell(this.mContext);
            r13.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r1 = r13;
            goto L24
        L14:
            if (r10 == 7) goto L16;
            Context r3 = this.mContext;
            boolean r5 = PollCreateActivity.m17473$$Nest$fgetisPremium(this.this$0);
            PollCreateActivity$ListAdapter$$ExternalSyntheticLambda0 r6 = new PollCreateActivity$ListAdapter$$ExternalSyntheticLambda0(this.this$0);
            AnonymousClass5 r14 = new AnonymousClass5(this, r3, false, r5 ? 1 : 0, r6);
            r14.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r14.addTextWatcher(new AnonymousClass6(this, r14));
            r14.setShowNextButton(true);
            EditTextBoldCursor r8 = r14.getTextView();
            r8.setImeOptions(r8.getImeOptions() | 5);
            r8.setOnEditorActionListener(new PollCreateActivity$ListAdapter$$ExternalSyntheticLambda1(this, r14));
            r8.setOnKeyListener(new PollCreateActivity$ListAdapter$$ExternalSyntheticLambda2(r14));
            r1 = r14;
            goto L24
        L16:
            AnonymousClass3 r24 = new AnonymousClass3(this, this.mContext, false, PollCreateActivity.m17473$$Nest$fgetisPremium(this.this$0) ? 1 : 0, null);
            r24.createErrorTextView();
            r24.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r24.addTextWatcher(new AnonymousClass4(this, r24));
            r22 = r24;
            goto L17
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r13) {
            int r2 = r13.getItemViewType();
            float r4 = 0.6f;
            CharSequence r5 = _UrlKt.FRAGMENT_ENCODE_SET;
            float r6 = 1.0f;
            if (r2 != 4) goto L17;
            PollEditTextCell r22 = (PollEditTextCell) r13.itemView;
            r22.setTag(1);
            if (PollCreateActivity.m17488$$Nest$fgetquestionString(this.this$0) == null) goto L8;
            r5 = PollCreateActivity.m17488$$Nest$fgetquestionString(this.this$0);
        L8:
            if (PollCreateActivity.m17501$$Nest$fgettodo(this.this$0) == false) goto L10;
            int r1 = R.string.TodoTitlePlaceholder;
        L11:
            r22.setTextAndHint(r5, LocaleController.getString(r1), false);
            r22.setTag(null);
            r22.setEnabled(!PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0));
            r22.textView.setEnabled(true ^ PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0));
            EditTextBoldCursor r0 = r22.textView;
            int r12 = this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
            if (PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0) == true) goto L14;
            r4 = 1.0f;
        L14:
            r0.setTextColor(Theme.multAlpha(r12, r4));
            PollCreateActivity.m17516$$Nest$msetTextLeft(this.this$0, r13.itemView, r13.getAdapterPosition());
            return;
        L10:
            r1 = R.string.QuestionHint;
            goto L11
        L17:
            if (r2 != 5) goto L52;
            int r23 = r13.getAdapterPosition();
            PollEditTextCell r3 = (PollEditTextCell) r13.itemView;
            r3.setTag(1);
            int r14 = r23 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0);
            if (PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0) == true) goto L21;
        L24:
            boolean r52 = true;
        L25:
            r3.textView.setEnabled(r52);
            EditTextBoldCursor r9 = r3.textView;
            int r10 = this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
            if (r52 == false) goto L28;
            r4 = 1.0f;
        L28:
            r9.setTextColor(Theme.multAlpha(r10, r4));
            CharSequence r15 = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r14];
            if (PollCreateActivity.m17501$$Nest$fgettodo(this.this$0) == false) goto L31;
            int r42 = R.string.TodoTaskPlaceholder;
        L32:
            r3.setTextAndHint(r15, LocaleController.getString(r42), true);
            r3.setTag(null);
            ImageView r02 = r3.deleteImageView;
            if (r02 == null) goto L38;
            if (r52 == false) goto L36;
            int r16 = 0;
        L37:
            r02.setVisibility(r16);
            goto L38
        L36:
            r16 = 8;
        L38:
            ImageView r03 = r3.moveImageView;
            if (r03 == null) goto L45;
            if (r52 == true) goto L43;
            r6 = 0.45f;
        L43:
            r03.setAlpha(r6);
        L45:
            if (PollCreateActivity.m17471$$Nest$fgetfirstRequestField(this.this$0) == false) goto L47;
        L49:
            PollCreateActivity.m17516$$Nest$msetTextLeft(this.this$0, r13.itemView, r23);
            return;
        L47:
            if (PollCreateActivity.m17492$$Nest$fgetrequestFieldFocusAtPosition(this.this$0) != r23) goto L49;
            EditTextBoldCursor r04 = r3.getTextView();
            r04.requestFocus();
            AndroidUtilities.showKeyboard(r04);
            PollCreateActivity.m17505$$Nest$fputfirstRequestField(this.this$0, false);
            PollCreateActivity.m17508$$Nest$fputrequestFieldFocusAtPosition(this.this$0, -1);
            goto L49
        L31:
            r42 = R.string.OptionHint;
            goto L32
        L21:
            if (r14 >= PollCreateActivity.m17481$$Nest$fgetoldAnswersCount(this.this$0)) goto L24;
            r52 = false;
            goto L25
        L52:
            if (r2 != 7) goto L58;
            PollEditTextCell r05 = (PollEditTextCell) r13.itemView;
            r05.setTag(1);
            if (PollCreateActivity.m17499$$Nest$fgetsolutionString(this.this$0) == null) goto L56;
            r5 = PollCreateActivity.m17499$$Nest$fgetsolutionString(this.this$0);
        L56:
            r05.setTextAndHint(r5, LocaleController.getString(R.string.AddAnExplanation), false);
            r05.setTag(null);
            PollCreateActivity.m17516$$Nest$msetTextLeft(this.this$0, r13.itemView, r13.getAdapterPosition());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 4) goto L5;
        L6:
            EditTextBoldCursor r32 = ((PollEditTextCell) r3.itemView).getTextView();
            if (r32.isFocused() == true) goto L9;
            return;
        L9:
            if (PollCreateActivity.m17473$$Nest$fgetisPremium(this.this$0) == true) goto L11;
        L14:
            PollCreateActivity.m17504$$Nest$fputcurrentCell(this.this$0, null);
            r32.clearFocus();
            AndroidUtilities.hideKeyboard(r32);
            return;
        L11:
            if (PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$0) == null) goto L13;
            PollCreateActivity.m17500$$Nest$fgetsuggestEmojiPanel(this.this$0).forceClose();
        L13:
            PollCreateActivity.m17513$$Nest$mhideEmojiPopup(this.this$0, true);
            goto L14
        L5:
            if (r3.getItemViewType() == 5) goto L6;
        }

        public void swapElements(int r6, int r7) {
            int r0 = r6 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0);
            int r1 = r7 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0);
            if (r0 < 0) goto L15;
            if (r1 >= 0) goto L6;
            return;
        L6:
            if (r0 < PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0)) goto L8;
            return;
        L8:
            if (r1 >= PollCreateActivity.m17464$$Nest$fgetanswersCount(this.this$0)) goto L18;
            CharSequence r2 = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r0];
            PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r0] = PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r1];
            PollCreateActivity.m17462$$Nest$fgetanswers(this.this$0)[r1] = r2;
            if (PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0) == null) goto L13;
            int r22 = PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0)[r0];
            PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0)[r0] = PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0)[r1];
            PollCreateActivity.m17459$$Nest$fgetanswerIds(this.this$0)[r1] = r22;
        L13:
            boolean r23 = PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0)[r0];
            PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0)[r0] = PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0)[r1];
            PollCreateActivity.m17463$$Nest$fgetanswersChecks(this.this$0)[r1] = r23;
            notifyItemMoved(r6, r7);
            return;
        L18:
            return;
        }
    }

    public interface PollCreateActivityDelegate {
        void sendPoll(TLRPC.MessageMedia r1, ArrayList<Integer> r2, boolean r3, int r4);
    }

    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        final /* synthetic */ PollCreateActivity this$0;

        public TouchHelperCallback(PollCreateActivity r1) {
            this.this$0 = r1;
        }

        private boolean canMove(int r3) {
            if (PollCreateActivity.m17482$$Nest$fgetonlyAdding(this.this$0) == true) goto L6;
            return true;
        L6:
            if ((r3 - PollCreateActivity.m17461$$Nest$fgetanswerStartRow(this.this$0)) < PollCreateActivity.m17481$$Nest$fgetoldAnswersCount(this.this$0)) goto L8;
            return true;
        L8:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r3, RecyclerView.ViewHolder r4) {
            if (r4.getItemViewType() != 5) goto L10;
            if (canMove(r4.getAdapterPosition()) == false) goto L10;
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        L10:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r2, RecyclerView.ViewHolder r3, RecyclerView.ViewHolder r4) {
            if (r3.getItemViewType() == r4.getItemViewType()) goto L5;
            return false;
        L5:
            if (canMove(r3.getAdapterPosition()) == true) goto L7;
            return false;
        L7:
            if (canMove(r4.getAdapterPosition()) == false) goto L14;
            PollCreateActivity.m17476$$Nest$fgetlistAdapter(this.this$0).swapElements(r3.getAdapterPosition(), r4.getAdapterPosition());
            return true;
        L14:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            if (r4 == 0) goto L4;
            PollCreateActivity.m17477$$Nest$fgetlistView(this.this$0).cancelClickRunnables(false);
            r3.itemView.setPressed(true);
        L4:
            super.onSelectedChanged(r3, r4);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    public static /* synthetic */ void $r8$lambda$EdmxrXuzb7SBQM9xD_eK2M4liAs(EditTextBoldCursor r0) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: $r8$lambda$Epg5O8DYZcKaHnfV0-0XhoQUnzo, reason: not valid java name */
    public static /* synthetic */ void m17447$r8$lambda$Epg5O8DYZcKaHnfV00XhoQUnzo(PollCreateActivity r0, ValueAnimator r1) {
        r0.lambda$showEmojiPopup$4(r1);
    }

    /* renamed from: $r8$lambda$NTmvRhB5KV-RMP2tq3xzW6jCizs, reason: not valid java name */
    public static /* synthetic */ void m17448$r8$lambda$NTmvRhB5KVRMP2tq3xzW6jCizs(PollCreateActivity r0, View r1, int r2) {
        r0.lambda$createView$1(r1, r2);
    }

    /* renamed from: $r8$lambda$UxfYcJ1Wt23ZXrVFOA36-jp26Iw, reason: not valid java name */
    public static /* synthetic */ void m17449$r8$lambda$UxfYcJ1Wt23ZXrVFOA36jp26Iw(PollCreateActivity r0, ValueAnimator r1) {
        r0.lambda$hideEmojiPopup$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$e6dACRUJMP9gkLzv5GAjfc9dywQ(PollCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$2(r1, r2);
    }

    /* renamed from: $r8$lambda$lnfHK5rL3fPIQ5hPpycnnqDSK-o, reason: not valid java name */
    public static /* synthetic */ void m17450$r8$lambda$lnfHK5rL3fPIQ5hPpycnnqDSKo(PollCreateActivity r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$animateEmojiViewTranslationY$3(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetaddAnswerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17451$$Nest$fgetaddAnswerRow(PollCreateActivity r0) {
        return r0.addAnswerRow;
    }

    /* renamed from: -$$Nest$fgetallowAdding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17452$$Nest$fgetallowAdding(PollCreateActivity r0) {
        return r0.allowAdding;
    }

    /* renamed from: -$$Nest$fgetallowAddingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17453$$Nest$fgetallowAddingRow(PollCreateActivity r0) {
        return r0.allowAddingRow;
    }

    /* renamed from: -$$Nest$fgetallowMarking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17454$$Nest$fgetallowMarking(PollCreateActivity r0) {
        return r0.allowMarking;
    }

    /* renamed from: -$$Nest$fgetallowMarkingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17455$$Nest$fgetallowMarkingRow(PollCreateActivity r0) {
        return r0.allowMarkingRow;
    }

    /* renamed from: -$$Nest$fgetanonymousPoll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17456$$Nest$fgetanonymousPoll(PollCreateActivity r0) {
        return r0.anonymousPoll;
    }

    /* renamed from: -$$Nest$fgetanonymousRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17457$$Nest$fgetanonymousRow(PollCreateActivity r0) {
        return r0.anonymousRow;
    }

    /* renamed from: -$$Nest$fgetanswerHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17458$$Nest$fgetanswerHeaderRow(PollCreateActivity r0) {
        return r0.answerHeaderRow;
    }

    /* renamed from: -$$Nest$fgetanswerIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m17459$$Nest$fgetanswerIds(PollCreateActivity r0) {
        return r0.answerIds;
    }

    /* renamed from: -$$Nest$fgetanswerSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17460$$Nest$fgetanswerSectionRow(PollCreateActivity r0) {
        return r0.answerSectionRow;
    }

    /* renamed from: -$$Nest$fgetanswerStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17461$$Nest$fgetanswerStartRow(PollCreateActivity r0) {
        return r0.answerStartRow;
    }

    /* renamed from: -$$Nest$fgetanswers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence[] m17462$$Nest$fgetanswers(PollCreateActivity r0) {
        return r0.answers;
    }

    /* renamed from: -$$Nest$fgetanswersChecks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m17463$$Nest$fgetanswersChecks(PollCreateActivity r0) {
        return r0.answersChecks;
    }

    /* renamed from: -$$Nest$fgetanswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17464$$Nest$fgetanswersCount(PollCreateActivity r0) {
        return r0.answersCount;
    }

    /* renamed from: -$$Nest$fgetcurrentCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PollEditTextCell m17465$$Nest$fgetcurrentCell(PollCreateActivity r0) {
        return r0.currentCell;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PollCreateActivityDelegate m17466$$Nest$fgetdelegate(PollCreateActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdestroyed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17467$$Nest$fgetdestroyed(PollCreateActivity r0) {
        return r0.destroyed;
    }

    /* renamed from: -$$Nest$fgetdoneItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m17468$$Nest$fgetdoneItem(PollCreateActivity r0) {
        return r0.doneItem;
    }

    /* renamed from: -$$Nest$fgetediting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.MessageMedia m17469$$Nest$fgetediting(PollCreateActivity r0) {
        return r0.editing;
    }

    /* renamed from: -$$Nest$fgetemojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiView m17470$$Nest$fgetemojiView(PollCreateActivity r0) {
        return r0.emojiView;
    }

    /* renamed from: -$$Nest$fgetfirstRequestField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17471$$Nest$fgetfirstRequestField(PollCreateActivity r0) {
        return r0.firstRequestField;
    }

    /* renamed from: -$$Nest$fgethintView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView m17472$$Nest$fgethintView(PollCreateActivity r0) {
        return r0.hintView;
    }

    /* renamed from: -$$Nest$fgetisPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17473$$Nest$fgetisPremium(PollCreateActivity r0) {
        return r0.isPremium;
    }

    /* renamed from: -$$Nest$fgetkeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17474$$Nest$fgetkeyboardVisible(PollCreateActivity r0) {
        return r0.keyboardVisible;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView.LayoutManager m17475$$Nest$fgetlayoutManager(PollCreateActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m17476$$Nest$fgetlistAdapter(PollCreateActivity r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m17477$$Nest$fgetlistView(PollCreateActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmaxAnswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17478$$Nest$fgetmaxAnswersCount(PollCreateActivity r0) {
        return r0.maxAnswersCount;
    }

    /* renamed from: -$$Nest$fgetmultipleChoise, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17479$$Nest$fgetmultipleChoise(PollCreateActivity r0) {
        return r0.multipleChoise;
    }

    /* renamed from: -$$Nest$fgetmultipleRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17480$$Nest$fgetmultipleRow(PollCreateActivity r0) {
        return r0.multipleRow;
    }

    /* renamed from: -$$Nest$fgetoldAnswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17481$$Nest$fgetoldAnswersCount(PollCreateActivity r0) {
        return r0.oldAnswersCount;
    }

    /* renamed from: -$$Nest$fgetonlyAdding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17482$$Nest$fgetonlyAdding(PollCreateActivity r0) {
        return r0.onlyAdding;
    }

    /* renamed from: -$$Nest$fgetopenKeyboardRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m17483$$Nest$fgetopenKeyboardRunnable(PollCreateActivity r0) {
        return r0.openKeyboardRunnable;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m17484$$Nest$fgetparentFragment(PollCreateActivity r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetquestionHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17485$$Nest$fgetquestionHeaderRow(PollCreateActivity r0) {
        return r0.questionHeaderRow;
    }

    /* renamed from: -$$Nest$fgetquestionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17486$$Nest$fgetquestionRow(PollCreateActivity r0) {
        return r0.questionRow;
    }

    /* renamed from: -$$Nest$fgetquestionSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17487$$Nest$fgetquestionSectionRow(PollCreateActivity r0) {
        return r0.questionSectionRow;
    }

    /* renamed from: -$$Nest$fgetquestionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m17488$$Nest$fgetquestionString(PollCreateActivity r0) {
        return r0.questionString;
    }

    /* renamed from: -$$Nest$fgetquizOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17489$$Nest$fgetquizOnly(PollCreateActivity r0) {
        return r0.quizOnly;
    }

    /* renamed from: -$$Nest$fgetquizPoll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17490$$Nest$fgetquizPoll(PollCreateActivity r0) {
        return r0.quizPoll;
    }

    /* renamed from: -$$Nest$fgetquizRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17491$$Nest$fgetquizRow(PollCreateActivity r0) {
        return r0.quizRow;
    }

    /* renamed from: -$$Nest$fgetrequestFieldFocusAtPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17492$$Nest$fgetrequestFieldFocusAtPosition(PollCreateActivity r0) {
        return r0.requestFieldFocusAtPosition;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17493$$Nest$fgetrowCount(PollCreateActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsettingsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17494$$Nest$fgetsettingsHeaderRow(PollCreateActivity r0) {
        return r0.settingsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetsettingsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17495$$Nest$fgetsettingsSectionRow(PollCreateActivity r0) {
        return r0.settingsSectionRow;
    }

    /* renamed from: -$$Nest$fgetsizeNotifierFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m17496$$Nest$fgetsizeNotifierFrameLayout(PollCreateActivity r0) {
        return r0.sizeNotifierFrameLayout;
    }

    /* renamed from: -$$Nest$fgetsolutionInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17497$$Nest$fgetsolutionInfoRow(PollCreateActivity r0) {
        return r0.solutionInfoRow;
    }

    /* renamed from: -$$Nest$fgetsolutionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m17498$$Nest$fgetsolutionRow(PollCreateActivity r0) {
        return r0.solutionRow;
    }

    /* renamed from: -$$Nest$fgetsolutionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m17499$$Nest$fgetsolutionString(PollCreateActivity r0) {
        return r0.solutionString;
    }

    /* renamed from: -$$Nest$fgetsuggestEmojiPanel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SuggestEmojiView m17500$$Nest$fgetsuggestEmojiPanel(PollCreateActivity r0) {
        return r0.suggestEmojiPanel;
    }

    /* renamed from: -$$Nest$fgettodo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17501$$Nest$fgettodo(PollCreateActivity r0) {
        return r0.todo;
    }

    /* renamed from: -$$Nest$fgetwaitingForKeyboardOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17502$$Nest$fgetwaitingForKeyboardOpen(PollCreateActivity r0) {
        return r0.waitingForKeyboardOpen;
    }

    /* renamed from: -$$Nest$fputanswersCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17503$$Nest$fputanswersCount(PollCreateActivity r0, int r1) {
        r0.answersCount = r1;
    }

    /* renamed from: -$$Nest$fputcurrentCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17504$$Nest$fputcurrentCell(PollCreateActivity r0, PollEditTextCell r1) {
        r0.currentCell = r1;
    }

    /* renamed from: -$$Nest$fputfirstRequestField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17505$$Nest$fputfirstRequestField(PollCreateActivity r0, boolean r1) {
        r0.firstRequestField = r1;
    }

    /* renamed from: -$$Nest$fputisAnimatePopupClosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17506$$Nest$fputisAnimatePopupClosing(PollCreateActivity r0, boolean r1) {
        r0.isAnimatePopupClosing = r1;
    }

    /* renamed from: -$$Nest$fputquestionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17507$$Nest$fputquestionString(PollCreateActivity r0, CharSequence r1) {
        r0.questionString = r1;
    }

    /* renamed from: -$$Nest$fputrequestFieldFocusAtPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17508$$Nest$fputrequestFieldFocusAtPosition(PollCreateActivity r0, int r1) {
        r0.requestFieldFocusAtPosition = r1;
    }

    /* renamed from: -$$Nest$fputsolutionString, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17509$$Nest$fputsolutionString(PollCreateActivity r0, CharSequence r1) {
        r0.solutionString = r1;
    }

    /* renamed from: -$$Nest$maddNewField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17510$$Nest$maddNewField(PollCreateActivity r0) {
        r0.addNewField();
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17511$$Nest$mcheckDiscard(PollCreateActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mcheckDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17512$$Nest$mcheckDoneButton(PollCreateActivity r0) {
        r0.checkDoneButton();
    }

    /* renamed from: -$$Nest$mhideEmojiPopup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17513$$Nest$mhideEmojiPopup(PollCreateActivity r0, boolean r1) {
        r0.hideEmojiPopup(r1);
    }

    /* renamed from: -$$Nest$monCellFocusChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17514$$Nest$monCellFocusChanges(PollCreateActivity r0, PollEditTextCell r1, boolean r2) {
        r0.onCellFocusChanges(r1, r2);
    }

    /* renamed from: -$$Nest$monEmojiClicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17515$$Nest$monEmojiClicked(PollCreateActivity r0, PollEditTextCell r1) {
        r0.onEmojiClicked(r1);
    }

    /* renamed from: -$$Nest$msetTextLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17516$$Nest$msetTextLeft(PollCreateActivity r0, View r1, int r2) {
        r0.setTextLeft(r1, r2);
    }

    /* renamed from: -$$Nest$mshowQuizHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17517$$Nest$mshowQuizHint(PollCreateActivity r0) {
        r0.showQuizHint();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17518$$Nest$mupdateRows(PollCreateActivity r0) {
        r0.updateRows();
    }

    public PollCreateActivity(ChatActivity r3, boolean r4, Boolean r5) {
        int r0 = 1;
        this.answersCount = 1;
        this.anonymousPoll = true;
        this.allowAdding = false;
        this.allowMarking = true;
        this.shiftDp = AndroidUtilities.dp(3.0f);
        this.requestFieldFocusAtPosition = -1;
        this.openKeyboardRunnable = new AnonymousClass1(this);
        this.todo = r4;
        if (r4 == false) goto L5;
        int r42 = getMessagesController().todoItemsMax;
    L6:
        this.maxAnswersCount = r42;
        this.answers = new CharSequence[r42];
        this.answersChecks = new boolean[r42];
        this.parentFragment = r3;
        this.isPremium = AccountInstance.getInstance(this.currentAccount).getUserConfig().isPremium();
        if (r5 == null) goto L14;
        boolean r32 = r5.booleanValue();
        this.quizPoll = r32;
        if (r32 == true) goto L12;
        r0 = 2;
    L12:
        this.quizOnly = r0;
        return;
    L14:
        return;
    L5:
        r42 = getMessagesController().config.pollAnswersMax.get();
        goto L6
    }

    public static /* synthetic */ int access$000(PollCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(PollCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(PollCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(PollCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(PollCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$500(PollCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(PollCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$700(PollCreateActivity r0) {
        return r0.resourceProvider;
    }

    private void addNewField() {
        resetSuggestEmojiPanel();
        boolean[] r0 = this.answersChecks;
        int r1 = this.answersCount;
        r0[r1] = false;
        int r12 = r1 + 1;
        this.answersCount = r12;
        if (this.answerIds == null) goto L13;
        int[] r02 = new int[r12];
        int r3 = 0;
    L5:
        if (r3 >= r12) goto L11;
        int[] r4 = this.answerIds;
        if (r3 >= r4.length) goto L9;
        int r42 = r4[r3];
    L10:
        r02[r3] = r42;
        r3 = r3 + 1;
        goto L5
    L9:
        r42 = this.maxAnswerId + 1;
        this.maxAnswerId = r42;
        goto L10
    L11:
        this.answerIds = r02;
    L13:
        if (this.answersCount != this.answers.length) goto L15;
        this.listAdapter.notifyItemRemoved(this.addAnswerRow);
    L15:
        this.listAdapter.notifyItemInserted(this.addAnswerRow);
        updateRows();
        this.firstRequestField = false;
        this.requestFieldFocusAtPosition = (this.answerStartRow + this.answersCount) - 1;
        this.listAdapter.notifyItemChanged(this.answerSectionRow);
    }

    private void animateEmojiViewTranslationY(float r3, float r4) {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r0.addUpdateListener(new PollCreateActivity$$ExternalSyntheticLambda2(this, r3, r4));
        r0.addListener(new AnonymousClass6(this, r4));
        r0.setDuration(250);
        r0.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r0.start();
    }

    private boolean checkDiscard(boolean r8) {
        TLRPC.MessageMedia r0 = this.editing;
        int r2 = 0;
        boolean r22 = false;
        if ((r0 instanceof TLRPC.TL_messageMediaToDo) == false) goto L27;
        TLRPC.TodoList r02 = ((TLRPC.TL_messageMediaToDo) r0).todo;
        int r1 = 0;
        int r3 = 0;
    L6:
        if (r1 >= Math.min(this.answersCount, this.answers.length)) goto L12;
        if (TextUtils.isEmpty(this.answers[r1]) == true) goto L10;
        r3 = r3 + 1;
    L10:
        r1 = r1 + 1;
        goto L6
    L12:
        if (this.onlyAdding == true) goto L16;
        if (TextUtils.equals(r02.title.text, ChatAttachAlertPollLayout.getFixedString(this.questionString)) == true) goto L16;
    L17:
        boolean r12 = false;
    L19:
        if (r12 == false) goto L26;
        int r4 = 0;
    L21:
        if (r4 >= r3) goto L26;
        if (TextUtils.equals(this.answers[r4].toString(), r02.list.get(r4).title.text) == false) goto L35;
        r4 = r4 + 1;
    L35:
        if (r8 == false) goto L46;
        if (r22 == true) goto L46;
        AlertDialog.Builder r82 = new AlertDialog.Builder(getParentActivity());
        if (this.todo == false) goto L40;
        int r03 = R.string.CancelTodoAlertTitle;
    L41:
        r82.setTitle(LocaleController.getString(r03));
        if (this.todo == false) goto L44;
        int r04 = R.string.CancelTodoAlertText;
    L45:
        r82.setMessage(LocaleController.getString(r04));
        r82.setPositiveButton(LocaleController.getString(R.string.PassportDiscard), new PollCreateActivity$$ExternalSyntheticLambda3(this));
        r82.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r82.create());
        goto L46
    L44:
        r04 = R.string.CancelPollAlertText;
        goto L45
    L40:
        r03 = R.string.CancelPollAlertTitle;
    L46:
        return r22;
    L26:
        r22 = r12;
    L16:
        if (r3 != r02.list.size()) goto L17;
        r12 = true;
        goto L19
    L27:
        boolean r05 = TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(this.questionString));
        if (r05 == true) goto L30;
    L33:
        r22 = r05;
    L30:
        if (r2 >= this.answersCount) goto L33;
        r05 = TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(this.answers[r2]));
        if (r05 == false) goto L33;
        r2 = r2 + 1;
        goto L30
    }

    private void checkDoneButton() {
        boolean r1 = false;
        if (this.quizPoll == false) goto L13;
        int r0 = 0;
        int r2 = 0;
    L6:
        if (r0 >= this.answersChecks.length) goto L15;
        if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(this.answers[r0])) == true) goto L12;
        if (this.answersChecks[r0] == false) goto L12;
        r2 = r2 + 1;
    L12:
        r0 = r0 + 1;
    L15:
        if (this.todo == false) goto L17;
        int r02 = getMessagesController().todoTitleLengthMax;
    L19:
        if (this.todo == false) goto L21;
        int r3 = getMessagesController().todoItemLengthMax;
    L23:
        if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(this.solutionString)) == true) goto L28;
        if (this.solutionString.length() <= 200) goto L28;
    L26:
        boolean r03 = false;
    L52:
        ActionBarMenuItem r32 = this.doneItem;
        if (this.quizPoll == false) goto L55;
        if (r2 != 0) goto L55;
    L56:
        r1 = true;
    L57:
        r32.setEnabled(r1);
        ActionBarMenuItem r8 = this.doneItem;
        if (r03 == false) goto L60;
        float r04 = 1.0f;
    L61:
        r8.setAlpha(r04);
        return;
    L60:
        r04 = 0.5f;
    L55:
        if (r03 == false) goto L57;
    L28:
        if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(this.questionString)) == true) goto L26;
        if (this.questionString.length() > r02) goto L26;
        int r05 = 0;
        int r4 = 0;
    L33:
        CharSequence[] r6 = this.answers;
        if (r05 >= r6.length) goto L43;
        if (TextUtils.isEmpty(ChatAttachAlertPollLayout.getFixedString(r6[r05])) == true) goto L41;
        if (this.answers[r05].length() > r3) goto L39;
        r4 = r4 + 1;
        goto L41
    L39:
        r4 = 0;
    L41:
        r05 = r05 + 1;
    L43:
        if (this.todo == false) goto L45;
        int r06 = 1;
    L46:
        if (r4 < r06) goto L26;
        if (this.quizPoll == false) goto L51;
        if (r2 < 1) goto L26;
    L51:
        r03 = true;
        goto L52
    L45:
        r06 = 2;
        goto L46
    L21:
        r3 = 100;
        goto L23
    L17:
        r02 = 255;
        goto L19
    L13:
        r2 = 0;
        goto L15
    }

    private void collapseSearchEmojiView() {
        if (this.isEmojiSearchOpened == false) goto L6;
        this.emojiView.closeSearch(false);
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        r0.height -= AndroidUtilities.dp(120.0f);
        this.emojiView.setLayoutParams(r0);
        this.emojiPadding = r0.height;
        this.wasEmojiSearchOpened = this.isEmojiSearchOpened;
        this.isEmojiSearchOpened = false;
        animateEmojiViewTranslationY(-AndroidUtilities.dp(120.0f), 0.0f);
        return;
    }

    private void createEmojiView() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L8;
        if (r0.currentAccount == UserConfig.selectedAccount) goto L8;
        this.sizeNotifierFrameLayout.removeView(r0);
        this.emojiView = null;
    L8:
        if (this.emojiView == null) goto L10;
        return;
    L10:
        EmojiView r1 = new EmojiView(null, true, false, false, getContext(), true, null, null, true, this.resourceProvider, false);
        this.emojiView = r1;
        r1.fixBottomTabContainerTranslation = false;
        r1.allowEmojisForNonPremium(false);
        this.emojiView.setVisibility(8);
        if (AndroidUtilities.isTablet() == false) goto L13;
        this.emojiView.setForseMultiwindowLayout(true);
    L13:
        this.emojiView.setDelegate(new AnonymousClass9(this));
        this.sizeNotifierFrameLayout.addView(this.emojiView);
    }

    private void hideEmojiPopup(boolean r4) {
        if (this.isPremium == true) goto L6;
        return;
    L6:
        if (this.emojiViewVisible == false) goto L11;
        this.emojiView.scrollEmojiToTop();
        this.emojiView.closeSearch(false);
        if (r4 == false) goto L10;
        this.emojiView.hideSearchKeyboard();
    L10:
        this.isEmojiSearchOpened = false;
        showEmojiPopup(0);
    L11:
        if (r4 == false) goto L20;
        EmojiView r42 = this.emojiView;
        if (r42 != null) goto L15;
    L18:
        hideEmojiView();
        return;
    L15:
        if (r42.getVisibility() != 0) goto L18;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{0.0f, this.emojiView.getMeasuredHeight()});
        r43.addUpdateListener(new PollCreateActivity$$ExternalSyntheticLambda1(this));
        this.isAnimatePopupClosing = true;
        r43.addListener(new AnonymousClass8(this));
        r43.setDuration(250);
        r43.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r43.start();
        return;
    }

    private /* synthetic */ void lambda$animateEmojiViewTranslationY$3(float r1, float r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(AndroidUtilities.lerp(r1, r2, r32));
    }

    private /* synthetic */ void lambda$checkDiscard$2(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$1(View r9, int r10) {
        if (r10 != this.addAnswerRow) goto L7;
        addNewField();
        return;
    L7:
        if ((r9 instanceof TextCheckCell) == false) goto L102;
        TextCheckCell r92 = (TextCheckCell) r9;
        boolean r0 = this.quizPoll;
        SuggestEmojiView r1 = this.suggestEmojiPanel;
        if (r1 == null) goto L12;
        r1.forceClose();
    L12:
        if (this.onlyAdding == false) goto L16;
        int r102 = -this.shiftDp;
        this.shiftDp = r102;
        AndroidUtilities.shakeViewSpring(r92, r102);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L16:
        if (r10 != this.anonymousRow) goto L18;
        boolean r12 = !this.anonymousPoll;
        this.anonymousPoll = r12;
    L70:
        if (this.hintShowed == true) goto L72;
    L74:
        this.listView.getChildCount();
        int r3 = this.answerStartRow;
    L76:
        if (r3 >= (this.answerStartRow + this.answersCount)) goto L89;
        RecyclerView.ViewHolder r4 = this.listView.findViewHolderForAdapterPosition(r3);
        if (r4 == null) goto L88;
        View r42 = r4.itemView;
        if ((r42 instanceof PollEditTextCell) == false) goto L88;
        PollEditTextCell r43 = (PollEditTextCell) r42;
        r43.setShowCheckBox(this.quizPoll, true);
        r43.setChecked(this.answersChecks[r3 - this.answerStartRow], r0);
        if (r43.getTop() <= AndroidUtilities.dp(40.0f)) goto L88;
        if (r10 != this.quizRow) goto L88;
        if (this.hintShowed == true) goto L88;
        this.hintView.showForView(r43.getCheckBox(), true);
        this.hintShowed = true;
    L88:
        r3 = r3 + 1;
        goto L76
    L89:
        r92.setChecked(r12);
        checkDoneButton();
        return;
    L72:
        if (this.quizPoll == true) goto L74;
        this.hintView.hide();
        goto L74
    L18:
        int r13 = this.allowAddingRow;
        if (r10 != r13) goto L22;
        r12 = !this.allowAdding;
        this.allowAdding = r12;
        goto L70
    L22:
        if (r10 != this.allowMarkingRow) goto L32;
        boolean r32 = !this.allowMarking;
        this.allowMarking = r32;
        updateRows();
        int r44 = this.allowAddingRow;
        if (r44 < 0) goto L27;
        if (r13 >= 0) goto L27;
        this.listAdapter.notifyItemInserted(r44);
    L30:
        r12 = r32;
    L27:
        if (r13 < 0) goto L30;
        if (r44 >= 0) goto L30;
        this.listAdapter.notifyItemRemoved(r13);
        goto L30
    L32:
        if (r10 != this.multipleRow) goto L44;
        boolean r14 = this.multipleChoise;
        boolean r5 = !r14;
        this.multipleChoise = r5;
        if (r14 == false) goto L36;
    L42:
        r12 = r5;
        goto L70
    L36:
        if (this.quizPoll == false) goto L42;
        int r15 = this.solutionRow;
        this.quizPoll = false;
        updateRows();
        RecyclerView.ViewHolder r6 = this.listView.findViewHolderForAdapterPosition(this.quizRow);
        if (r6 == null) goto L40;
        ((TextCheckCell) r6.itemView).setChecked(false);
    L41:
        this.listAdapter.notifyItemRangeRemoved(r15, 2);
        goto L42
    L40:
        this.listAdapter.notifyItemChanged(this.quizRow);
        goto L41
    L44:
        if (this.quizOnly != 0) goto L103;
        r12 = !this.quizPoll;
        this.quizPoll = r12;
        int r52 = this.solutionRow;
        updateRows();
        boolean r62 = this.quizPoll;
        ListAdapter r7 = this.listAdapter;
        if (r62 == false) goto L49;
        r7.notifyItemRangeInserted(this.solutionRow, 2);
    L51:
        if (this.quizPoll == false) goto L59;
        if (this.multipleChoise == false) goto L59;
        this.multipleChoise = false;
        RecyclerView.ViewHolder r33 = this.listView.findViewHolderForAdapterPosition(this.multipleRow);
        if (r33 == null) goto L57;
        ((TextCheckCell) r33.itemView).setChecked(false);
        goto L59
    L57:
        this.listAdapter.notifyItemChanged(this.multipleRow);
    L59:
        if (this.quizPoll == false) goto L70;
        int r34 = 0;
        boolean r53 = false;
    L61:
        boolean[] r63 = this.answersChecks;
        if (r34 >= r63.length) goto L70;
        if (r53 == false) goto L66;
        r63[r34] = false;
    L68:
        r34 = r34 + 1;
        goto L61
    L66:
        if (r63[r34] == false) goto L68;
        r53 = true;
        goto L68
    L49:
        r7.notifyItemRangeRemoved(r52, 2);
        goto L51
    L103:
        return;
    }

    private /* synthetic */ void lambda$hideEmojiPopup$5(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$showEmojiPopup$4(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private void onCellFocusChanges(PollEditTextCell r4, boolean r5) {
        if (this.isPremium == false) goto L20;
        if (r5 == true) goto L6;
        return;
    L6:
        if (this.currentCell == r4) goto L8;
    L12:
        PollEditTextCell r52 = this.currentCell;
        this.currentCell = r4;
        r4.setEmojiButtonVisibility(true);
        ChatActivityEnterViewAnimatedIconView r1 = r4.getEmojiButton();
        ChatActivityEnterViewAnimatedIconView.State r2 = ChatActivityEnterViewAnimatedIconView.State.SMILE;
        r1.setState(r2, false);
        updateSuggestEmojiPanelDelegate(this.listView.findContainingViewHolder(r4));
        if (r52 == null) goto L22;
        if (r52 != r4) goto L16;
        return;
    L16:
        if (this.emojiViewVisible == false) goto L18;
        collapseSearchEmojiView();
        hideEmojiPopup(false);
        openKeyboardInternal();
    L18:
        r52.setEmojiButtonVisibility(false);
        r52.getEmojiButton().setState(r2, false);
        return;
    L22:
        return;
    L8:
        if (this.emojiViewVisible == false) goto L12;
        if (this.isEmojiSearchOpened == false) goto L12;
        collapseSearchEmojiView();
        this.emojiViewVisible = false;
        goto L12
    }

    private void onEmojiClicked(PollEditTextCell r1) {
        this.currentCell = r1;
        if (this.emojiViewVisible == false) goto L6;
        collapseSearchEmojiView();
        openKeyboardInternal();
        return;
    L6:
        showEmojiPopup(1);
    }

    private void openKeyboardInternal() {
        this.keyboardNotifier.awaitKeyboard();
        EditTextBoldCursor r0 = this.currentCell.getEditField();
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
        if (AndroidUtilities.usingHardwareInput == false) goto L5;
        int r02 = 0;
    L6:
        showEmojiPopup(r02);
        if (AndroidUtilities.usingHardwareInput == false) goto L9;
        return;
    L9:
        if (this.keyboardVisible == false) goto L11;
        return;
    L11:
        if (AndroidUtilities.isInMultiwindow == false) goto L13;
        return;
    L13:
        if (AndroidUtilities.isTablet() == true) goto L19;
        this.waitingForKeyboardOpen = true;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        AndroidUtilities.runOnUIThread(this.openKeyboardRunnable, 100);
        return;
    L19:
        return;
    L5:
        r02 = 2;
        goto L6
    }

    private void resetSuggestEmojiPanel() {
        SuggestEmojiView r0 = this.suggestEmojiPanel;
        if (r0 == null) goto L6;
        r0.setDelegate(null);
        this.suggestEmojiPanel.forceClose();
        return;
    }

    private void setTextLeft(View r4, int r5) {
        if ((r4 instanceof PollEditTextCell) == false) goto L45;
        PollEditTextCell r42 = (PollEditTextCell) r4;
        int r1 = 0;
        if (r5 != this.questionRow) goto L16;
        if (this.todo == false) goto L10;
        int r52 = getMessagesController().todoTitleLengthMax;
    L11:
        CharSequence r3 = this.questionString;
        if (r3 == null) goto L14;
        r1 = r3.length();
    L14:
        int r32 = r52 - r1;
    L33:
        float r53 = r52;
        if (r32 > (r53 - (0.7f * r53))) goto L41;
        r42.setText2(String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r32)}));
        SimpleTextView r43 = r42.getTextView2();
        if (r32 >= 0) goto L38;
        int r33 = Theme.key_text_RedRegular;
    L39:
        r43.setTextColor(Theme.getColor(r33));
        r43.setTag(Integer.valueOf(r33));
        return;
    L38:
        r33 = Theme.key_windowBackgroundWhiteGrayText3;
        goto L39
    L41:
        r42.setText2(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L10:
        r52 = 255;
        goto L11
    L16:
        if (r5 != this.solutionRow) goto L21;
        CharSequence r34 = this.solutionString;
        if (r34 == null) goto L20;
        r1 = r34.length();
    L20:
        r52 = 200;
        r32 = 200 - r1;
        goto L33
    L21:
        int r0 = this.answerStartRow;
        if (r5 >= r0) goto L24;
        return;
    L24:
        if (r5 >= (this.answersCount + r0)) goto L44;
        int r54 = r5 - r0;
        if (this.todo == false) goto L28;
        int r02 = getMessagesController().todoItemLengthMax;
    L29:
        CharSequence r35 = this.answers[r54];
        if (r35 == null) goto L32;
        r1 = r35.length();
    L32:
        r32 = r02 - r1;
        r52 = r02;
        goto L33
    L28:
        r02 = 100;
        goto L29
    L44:
        return;
    }

    private void showEmojiPopup(int r9) {
        if (this.isPremium == true) goto L6;
        return;
    L6:
        if (r9 != 1) goto L44;
        EmojiView r92 = this.emojiView;
        if (r92 != null) goto L10;
    L12:
        boolean r93 = false;
    L13:
        createEmojiView();
        this.emojiView.setVisibility(0);
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = true;
        EmojiView r2 = this.emojiView;
        if (this.keyboardHeight > 0) goto L20;
        if (AndroidUtilities.isTablet() == false) goto L18;
        this.keyboardHeight = AndroidUtilities.dp(150.0f);
        goto L20
    L18:
        this.keyboardHeight = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
    L20:
        if (this.keyboardHeightLand <= 0) goto L22;
    L25:
        Point r3 = AndroidUtilities.displaySize;
        if (r3.x <= r3.y) goto L28;
        int r32 = this.keyboardHeightLand;
    L29:
        FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        r4.height = r32;
        r2.setLayoutParams(r4);
        if (AndroidUtilities.isInMultiwindow == false) goto L32;
    L36:
        this.emojiPadding = r32;
        this.keyboardNotifier.fire();
        this.sizeNotifierFrameLayout.requestLayout();
        ChatActivityEnterViewAnimatedIconView r22 = this.currentCell.getEmojiButton();
        if (r22 == null) goto L39;
        r22.setState(ChatActivityEnterViewAnimatedIconView.State.KEYBOARD, true);
    L39:
        if (r93 == false) goto L41;
        return;
    L41:
        if (this.keyboardVisible == true) goto L59;
        ValueAnimator r94 = ValueAnimator.ofFloat(new float[]{this.emojiPadding, 0.0f});
        r94.addUpdateListener(new PollCreateActivity$$ExternalSyntheticLambda0(this));
        r94.addListener(new AnonymousClass7(this));
        r94.setDuration(250);
        r94.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r94.start();
        return;
    L59:
        return;
    L32:
        if (AndroidUtilities.isTablet() == true) goto L36;
        PollEditTextCell r23 = this.currentCell;
        if (r23 == null) goto L36;
        AndroidUtilities.hideKeyboard(r23.getEditField());
        goto L36
    L28:
        r32 = this.keyboardHeight;
        goto L29
    L22:
        if (AndroidUtilities.isTablet() == false) goto L24;
        this.keyboardHeightLand = AndroidUtilities.dp(150.0f);
        goto L25
    L24:
        this.keyboardHeightLand = MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3", AndroidUtilities.dp(200.0f));
        goto L25
    L10:
        if (r92.getVisibility() != 0) goto L12;
        r93 = true;
        goto L13
    L44:
        ChatActivityEnterViewAnimatedIconView r24 = this.currentCell.getEmojiButton();
        if (r24 == null) goto L47;
        r24.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, true);
    L47:
        EmojiView r1 = this.emojiView;
        if (r1 == null) goto L54;
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = false;
        this.isEmojiSearchOpened = false;
        if (AndroidUtilities.usingHardwareInput == false) goto L52;
    L53:
        r1.setVisibility(8);
        goto L54
    L52:
        if (AndroidUtilities.isInMultiwindow == true) goto L53;
    L54:
        if (r9 != 0) goto L56;
        this.emojiPadding = 0;
    L56:
        this.keyboardNotifier.fire();
        this.sizeNotifierFrameLayout.requestLayout();
    }

    private void showQuizHint() {
        this.listView.getChildCount();
        int r0 = this.answerStartRow;
    L4:
        if (r0 >= (this.answerStartRow + this.answersCount)) goto L14;
        RecyclerView.ViewHolder r1 = this.listView.findViewHolderForAdapterPosition(r0);
        if (r1 == null) goto L13;
        View r12 = r1.itemView;
        if ((r12 instanceof PollEditTextCell) == false) goto L13;
        PollEditTextCell r13 = (PollEditTextCell) r12;
        if (r13.getTop() <= AndroidUtilities.dp(40.0f)) goto L13;
        this.hintView.showForView(r13.getCheckBox(), true);
        return;
    L13:
        r0 = r0 + 1;
        goto L4
    }

    private void updateRows() {
        this.questionHeaderRow = -1;
        this.questionRow = -1;
        this.questionSectionRow = -1;
        this.answerHeaderRow = -1;
        this.solutionRow = -1;
        this.solutionInfoRow = -1;
        this.answerStartRow = -1;
        this.addAnswerRow = -1;
        this.anonymousRow = -1;
        this.multipleRow = -1;
        this.allowAddingRow = -1;
        this.allowMarkingRow = -1;
        this.quizRow = -1;
        this.settingsSectionRow = -1;
        this.settingsHeaderRow = -1;
        this.rowCount = 0;
        boolean r1 = this.todo;
        if (r1 == true) goto L5;
    L6:
        this.questionHeaderRow = 0;
        int r0 = 1 + 1;
        this.questionRow = 1;
        int r3 = r0 + 1;
        this.questionSectionRow = r0;
        this.rowCount = r0 + 2;
        this.answerHeaderRow = r3;
    L7:
        int r02 = this.answersCount;
        if (r02 == 0) goto L11;
        int r32 = this.rowCount;
        this.answerStartRow = r32;
        this.rowCount = r32 + r02;
    L11:
        if (r02 == this.answers.length) goto L13;
        int r03 = this.rowCount;
        this.rowCount = r03 + 1;
        this.addAnswerRow = r03;
    L13:
        int r04 = this.rowCount;
        int r33 = r04 + 1;
        this.rowCount = r33;
        this.answerSectionRow = r04;
        if (r1 == true) goto L16;
    L18:
        int r4 = r04 + 2;
        this.rowCount = r4;
        this.settingsHeaderRow = r33;
        if (r1 == false) goto L24;
        int r12 = r04 + 3;
        this.rowCount = r12;
        this.allowMarkingRow = r4;
        if (this.allowMarking == false) goto L38;
        this.rowCount = r04 + 4;
        this.allowAddingRow = r12;
        return;
    L38:
        return;
    L24:
        TLRPC.Chat r05 = this.parentFragment.getCurrentChat();
        if (ChatObject.isChannel(r05) == true) goto L27;
    L28:
        int r06 = this.rowCount;
        this.rowCount = r06 + 1;
        this.anonymousRow = r06;
    L29:
        int r07 = this.quizOnly;
        if (r07 == 1) goto L32;
        int r13 = this.rowCount;
        this.rowCount = r13 + 1;
        this.multipleRow = r13;
    L32:
        if (r07 != 0) goto L34;
        int r08 = this.rowCount;
        this.rowCount = r08 + 1;
        this.quizRow = r08;
    L34:
        int r09 = this.rowCount;
        int r14 = r09 + 1;
        this.rowCount = r14;
        this.settingsSectionRow = r09;
        if (this.quizPoll == false) goto L39;
        int r2 = r09 + 2;
        this.solutionRow = r14;
        this.rowCount = r09 + 3;
        this.solutionInfoRow = r2;
        return;
    L39:
        return;
    L27:
        if (r05.megagroup == false) goto L29;
    L16:
        if (this.onlyAdding == false) goto L18;
        return;
    L5:
        if (this.onlyAdding == true) goto L7;
        goto L6
    }

    private void updateSuggestEmojiPanelDelegate(RecyclerView.ViewHolder r3) {
        SuggestEmojiView r0 = this.suggestEmojiPanel;
        if (r0 == null) goto L13;
        r0.forceClose();
        SuggestEmojiView r02 = this.suggestEmojiPanel;
        if (r02 == null) goto L14;
        if (r3 != null) goto L8;
        return;
    L8:
        if ((r3.itemView instanceof PollEditTextCell) == false) goto L16;
        SuggestEmojiView.AnchorViewDelegate r03 = r02.getDelegate();
        View r32 = r3.itemView;
        if (r03 == r32) goto L17;
        this.suggestEmojiPanel.setDelegate((PollEditTextCell) r32);
        return;
    L17:
        return;
    L16:
        return;
    L14:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        this.actionBar.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setItemsColor(getThemedColor(r1), false);
        this.actionBar.setItemsColor(getThemedColor(r1), true);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), false);
        this.actionBar.setTitleColor(getThemedColor(r1));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        if (this.todo == false) goto L9;
        ActionBar r02 = this.actionBar;
        if (this.onlyAdding == false) goto L7;
        int r13 = R.string.TodoAddTasksTitle;
    L8:
        r02.setTitle(LocaleController.getString(r13));
    L13:
        INavigationLayout r03 = this.parentLayout;
        if (r03 != null) goto L16;
    L18:
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        ActionBarMenu r04 = this.actionBar.createMenu();
        if (this.todo == true) goto L21;
        String r14 = LocaleController.getString(R.string.Create).toUpperCase();
    L26:
        this.doneItem = r04.addItem(1, r14);
        this.listAdapter = new ListAdapter(this, r12);
        AnonymousClass3 r05 = new AnonymousClass3(this, r12);
        this.sizeNotifierFrameLayout = r05;
        r05.setDelegate(this);
        SizeNotifierFrameLayout r06 = this.sizeNotifierFrameLayout;
        this.fragmentView = r06;
        r06.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r07 = (FrameLayout) this.fragmentView;
        this.listView = new AnonymousClass4(this, r12);
        DefaultItemAnimator r15 = new DefaultItemAnimator();
        r15.setSupportsChangeAnimations(false);
        r15.setDelayAnimations(false);
        r15.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r15.setDurations(350);
        this.listView.setItemAnimator(r15);
        this.listView.setVerticalScrollBarEnabled(false);
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(false);
        LinearLayoutManager r16 = new LinearLayoutManager(r12, 1, false);
        this.layoutManager = r16;
        this.listView.setLayoutManager(r16);
        new ItemTouchHelper(new TouchHelperCallback(this)).attachToRecyclerView(this.listView);
        r07.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(new PollCreateActivity$$ExternalSyntheticLambda5(this));
        this.listView.setOnScrollListener(new AnonymousClass5(this));
        HintView r17 = new HintView(r12, 4);
        this.hintView = r17;
        r17.setText(LocaleController.getString(R.string.PollTapToSelect));
        this.hintView.setAlpha(0.0f);
        this.hintView.setVisibility(4);
        r07.addView(this.hintView, LayoutHelper.createFrame(-2, -2.0f, 51, 19.0f, 0.0f, 19.0f, 0.0f));
        if (this.isPremium == false) goto L29;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        SuggestEmojiView r18 = new SuggestEmojiView(r12, this.currentAccount, null, this.resourceProvider);
        this.suggestEmojiPanel = r18;
        r18.forbidCopy();
        this.suggestEmojiPanel.forbidSetAsStatus();
        this.suggestEmojiPanel.setHorizontalPadding(AndroidUtilities.dp(24.0f));
        r07.addView(this.suggestEmojiPanel, LayoutHelper.createFrame(-2, 160, 51));
    L29:
        this.keyboardNotifier = new KeyboardNotifier(this.sizeNotifierFrameLayout, null);
        checkDoneButton();
        return this.fragmentView;
    L21:
        if (this.onlyAdding == false) goto L23;
        int r19 = R.string.TodoAddTasksButton;
    L24:
        r14 = LocaleController.getString(r19);
        goto L26
    L23:
        r19 = R.string.TodoEditTasksButton;
        goto L24
    L16:
        if (r03.isLayersLayout() == false) goto L18;
        this.actionBar.setOccupyStatusBar(false);
        goto L18
    L7:
        r13 = R.string.TodoEditTitle;
        goto L8
    L9:
        int r08 = this.quizOnly;
        ActionBar r110 = this.actionBar;
        if (r08 != 1) goto L12;
        r110.setTitle(LocaleController.getString(R.string.NewQuiz));
        goto L13
    L12:
        r110.setTitle(LocaleController.getString(R.string.NewPoll));
        goto L13
    }

    public void deleteItem(View r10) {
        if (r10.getTag() != null) goto L49;
        r10.setTag(1);
        PollEditTextCell r102 = (PollEditTextCell) r10.getParent();
        RecyclerView.ViewHolder r1 = this.listView.findContainingViewHolder(r102);
        if (r1 == null) goto L47;
        int r12 = r1.getAdapterPosition();
        if (r12 == (-1)) goto L48;
        int r2 = r12 - this.answerStartRow;
        if (this.onlyAdding == true) goto L12;
    L15:
        this.listAdapter.notifyItemRemoved(r12);
        CharSequence[] r3 = this.answers;
        int r4 = r2 + 1;
        System.arraycopy(r3, r4, r3, r2, (r3.length - 1) - r2);
        boolean[] r32 = this.answersChecks;
        System.arraycopy(r32, r4, r32, r2, (r32.length - 1) - r2);
        CharSequence[] r33 = this.answers;
        r33[r33.length - 1] = null;
        boolean[] r34 = this.answersChecks;
        int r6 = 0;
        r34[r34.length - 1] = false;
        int r35 = this.answersCount - 1;
        this.answersCount = r35;
        if (this.answerIds == null) goto L25;
        int[] r42 = new int[r35];
    L18:
        if (r6 >= r35) goto L24;
        int[] r7 = this.answerIds;
        if (r6 < r2) goto L22;
        int r8 = r6 + 1;
    L23:
        r42[r6] = r7[r8];
        r6 = r6 + 1;
        goto L18
    L22:
        r8 = r6;
        goto L23
    L24:
        this.answerIds = r42;
    L25:
        int r22 = this.answersCount;
        CharSequence[] r36 = this.answers;
        if (r22 != (r36.length - 1)) goto L28;
        this.listAdapter.notifyItemInserted((this.answerStartRow + r36.length) - 1);
    L28:
        RecyclerView.ViewHolder r13 = this.listView.findViewHolderForAdapterPosition(r12 - 1);
        EditTextBoldCursor r103 = r102.getTextView();
        if (r13 == null) goto L34;
        View r14 = r13.itemView;
        if ((r14 instanceof PollEditTextCell) == false) goto L34;
        ((PollEditTextCell) r14).getTextView().requestFocus();
    L39:
        r103.clearFocus();
        checkDoneButton();
        updateRows();
        SuggestEmojiView r104 = this.suggestEmojiPanel;
        if (r104 == null) goto L42;
        r104.forceClose();
        this.suggestEmojiPanel.setDelegate(null);
    L42:
        this.listAdapter.notifyItemChanged(this.answerSectionRow);
        return;
    L34:
        if (r103.isFocused() == false) goto L37;
        AndroidUtilities.hideKeyboard(r103);
        hideEmojiPopup(true);
        goto L39
    L37:
        if (this.isEmojiSearchOpened == false) goto L39;
        hideEmojiPopup(true);
        goto L39
    L12:
        if (r2 >= this.oldAnswersCount) goto L15;
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r102, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L48:
        return;
    L47:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L11;
        EmojiView r12 = this.emojiView;
        if (r12 == null) goto L7;
        r12.invalidateViews();
    L7:
        PollEditTextCell r13 = this.currentCell;
        if (r13 == null) goto L12;
        int r14 = r13.getEditField().getCurrentTextColor();
        this.currentCell.getEditField().setTextColor(-1);
        this.currentCell.getEditField().setTextColor(r14);
        return;
    L12:
        return;
    }

    public int getEmojiPadding() {
        return this.emojiPadding;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, TextCell.class, PollEditTextCell.class, TextCheckCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r3 = this.actionBar;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r21 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r21));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r23 = Theme.key_text_RedRegular;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{HeaderCell.class}, new String[]{"textView2"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        int r32 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        int r41 = Theme.key_windowBackgroundWhiteGrayIcon;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"deleteImageView"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{PollEditTextCell.class}, new String[]{"moveImageView"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{PollEditTextCell.class}, new String[]{"deleteImageView"}, null, null, null, Theme.key_stickers_menuSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{PollEditTextCell.class}, new String[]{"textView2"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{PollEditTextCell.class}, new String[]{"checkBox"}, null, null, null, r41));
        int r22 = Theme.key_checkboxCheck;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{PollEditTextCell.class}, new String[]{"checkBox"}, null, null, null, r22));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        int r31 = Theme.key_switchTrackChecked;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r31));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r22));
        return r1;
    }

    public void hideEmojiView() {
        if (this.emojiViewVisible == true) goto L14;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L14;
        if (r0.getVisibility() == 8) goto L14;
        PollEditTextCell r02 = this.currentCell;
        if (r02 == null) goto L13;
        ChatActivityEnterViewAnimatedIconView r03 = r02.getEmojiButton();
        if (r03 == null) goto L13;
        r03.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, false);
    L13:
        this.emojiView.setVisibility(8);
    L14:
        int r04 = this.emojiPadding;
        this.emojiPadding = 0;
        if (r04 == 0) goto L18;
        this.keyboardNotifier.fire();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hideKeyboardOnShow() {
        if (this.requestFieldFocusAtPosition >= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (getLastStoryViewer() != null) goto L5;
    L7:
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (this.actionBar.isActionModeShowed() == false) goto L11;
        r0 = Theme.getColor(Theme.key_actionBarActionModeDefault);
    L11:
        if (ColorUtils.calculateLuminance(r0) <= 0.699999988079071d) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (getLastStoryViewer().isShown() == false) goto L7;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.emojiViewVisible == false) goto L9;
        if (r2 == false) goto L10;
        hideEmojiPopup(true);
        return false;
    L10:
        return false;
    L9:
        return checkDiscard(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        if (this.firstRequestField == true) goto L5;
        return;
    L5:
        if (this.requestFieldFocusAtPosition < 0) goto L22;
        int r1 = 0;
    L8:
        if (r1 >= this.listView.getChildCount()) goto L13;
        View r2 = this.listView.getChildAt(r1);
        if (this.listView.getChildAdapterPosition(r2) == this.requestFieldFocusAtPosition) goto L15;
        r1 = r1 + 1;
    L15:
        if ((r2 instanceof PollEditTextCell) == false) goto L17;
        AndroidUtilities.runOnUIThread(new PollCreateActivity$$ExternalSyntheticLambda4(((PollEditTextCell) r2).getTextView()), 300);
        this.requestFieldFocusAtPosition = -1;
    L17:
        this.firstRequestField = false;
        return;
    L13:
        r2 = null;
        goto L15
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        updateRows();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        this.destroyed = true;
        if (this.isPremium == false) goto L8;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L9;
        this.sizeNotifierFrameLayout.removeView(r0);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        if (this.isPremium == false) goto L11;
        hideEmojiPopup(false);
        SuggestEmojiView r1 = this.suggestEmojiPanel;
        if (r1 == null) goto L7;
        r1.forceClose();
    L7:
        PollEditTextCell r12 = this.currentCell;
        if (r12 == null) goto L12;
        r12.setEmojiButtonVisibility(false);
        this.currentCell.getTextView().clearFocus();
        AndroidUtilities.hideKeyboard(this.currentCell.getEditField());
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
    public void onSizeChanged(int r7, boolean r8) {
        if (this.isPremium == true) goto L6;
        return;
    L6:
        if (r7 <= AndroidUtilities.dp(50.0f)) goto L17;
        if (this.keyboardVisible == false) goto L17;
        if (AndroidUtilities.isInMultiwindow == true) goto L17;
        if (AndroidUtilities.isTablet() == true) goto L17;
        if (r8 == false) goto L15;
        this.keyboardHeightLand = r7;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3", this.keyboardHeightLand).commit();
        goto L17
    L15:
        this.keyboardHeight = r7;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height", this.keyboardHeight).commit();
    L17:
        if (this.emojiViewVisible == false) goto L39;
        if (r8 == false) goto L20;
        int r0 = this.keyboardHeightLand;
    L22:
        if (this.isEmojiSearchOpened == false) goto L24;
        r0 = r0 + AndroidUtilities.dp(120.0f);
    L24:
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        int r3 = r1.width;
        int r4 = AndroidUtilities.displaySize.x;
        if (r3 == r4) goto L27;
    L30:
        r1.width = r4;
        r1.height = r0;
        this.emojiView.setLayoutParams(r1);
        this.emojiPadding = r1.height;
        this.keyboardNotifier.fire();
        this.sizeNotifierFrameLayout.requestLayout();
        boolean r02 = this.wasEmojiSearchOpened;
        if (r02 == this.isEmojiSearchOpened) goto L37;
        if (r02 == false) goto L35;
        int r03 = -AndroidUtilities.dp(120.0f);
    L36:
        animateEmojiViewTranslationY(r03, 0.0f);
        goto L37
    L35:
        r03 = AndroidUtilities.dp(120.0f);
    L37:
        this.wasEmojiSearchOpened = this.isEmojiSearchOpened;
        goto L39
    L27:
        if (r1.height != r0) goto L30;
        if (this.wasEmojiSearchOpened == this.isEmojiSearchOpened) goto L39;
    L20:
        r0 = this.keyboardHeight;
    L39:
        if (this.lastSizeChangeValue1 == r7) goto L41;
    L43:
        this.lastSizeChangeValue1 = r7;
        this.lastSizeChangeValue2 = r8;
        boolean r82 = this.keyboardVisible;
        PollEditTextCell r04 = this.currentCell;
        if (r04 != null) goto L46;
        this.keyboardVisible = false;
    L55:
        if (this.keyboardVisible == false) goto L60;
        if (this.emojiViewVisible == false) goto L60;
        showEmojiPopup(0);
    L60:
        if (this.emojiPadding == 0) goto L68;
        boolean r72 = this.keyboardVisible;
        if (r72 == true) goto L68;
        if (r72 == r82) goto L68;
        if (this.emojiViewVisible == true) goto L68;
        this.emojiPadding = 0;
        this.keyboardNotifier.fire();
        this.sizeNotifierFrameLayout.requestLayout();
    L68:
        if (this.keyboardVisible == true) goto L70;
        return;
    L70:
        if (this.waitingForKeyboardOpen == false) goto L74;
        this.waitingForKeyboardOpen = false;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        return;
    L74:
        return;
    L46:
        if (r04.getEditField().isFocused() == true) goto L48;
    L51:
        boolean r73 = false;
    L52:
        this.keyboardVisible = r73;
        goto L55
    L48:
        if (this.keyboardNotifier.keyboardVisible() == false) goto L51;
        if (r7 <= 0) goto L51;
        r73 = true;
        goto L52
    L41:
        if (this.lastSizeChangeValue2 != r8) goto L43;
    }

    public void setDelegate(PollCreateActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setEditing(TLRPC.MessageMedia r2, boolean r3) {
        setEditing(r2, r3, -1);
    }

    public void setEditing(TLRPC.MessageMedia r12, boolean r13, int r14) {
        this.editing = r12;
        this.onlyAdding = r13;
        if ((r12 instanceof TLRPC.TL_messageMediaToDo) == false) goto L16;
        TLRPC.TL_messageMediaToDo r122 = (TLRPC.TL_messageMediaToDo) r12;
        TextPaint r132 = new TextPaint(1);
        r132.setTextSize(AndroidUtilities.dp(16.0f));
        SpannableStringBuilder r1 = new SpannableStringBuilder(r122.todo.title.text);
        this.questionString = r1;
        CharSequence r15 = Emoji.replaceEmoji(r1, r132.getFontMetricsInt(), false);
        this.questionString = r15;
        Spannable r5 = MessageObject.replaceAnimatedEmoji(r15, r122.todo.title.entities, r132.getFontMetricsInt());
        this.questionString = r5;
        MessageObject.addEntitiesToText(r5, r122.todo.title.entities, false, false, false, false);
        int r16 = r122.todo.list.size();
        this.answersCount = r16;
        this.oldAnswersCount = r16;
        this.maxAnswerId = 0;
        this.answerIds = new int[r16];
        int r17 = 0;
    L5:
        int r2 = this.answersCount;
        TLRPC.TodoList r4 = r122.todo;
        if (r17 >= r2) goto L8;
        TLRPC.TL_textWithEntities r22 = r4.list.get(r17).title;
        this.answers[r17] = new SpannableStringBuilder(r22.text);
        CharSequence[] r42 = this.answers;
        r42[r17] = Emoji.replaceEmoji(r42[r17], r132.getFontMetricsInt(), false);
        CharSequence[] r43 = this.answers;
        r43[r17] = MessageObject.replaceAnimatedEmoji(r43[r17], r22.entities, r132.getFontMetricsInt());
        MessageObject.addEntitiesToText(this.answers[r17], r22.entities, false, false, false, false);
        this.answerIds[r17] = r122.todo.list.get(r17).id;
        this.maxAnswerId = Math.max(this.maxAnswerId, this.answerIds[r17]);
        r17 = r17 + 1;
        goto L5
    L8:
        this.allowMarking = r4.others_can_complete;
        this.allowAdding = r4.others_can_append;
        if (this.onlyAdding == false) goto L17;
        this.answersCount = r2 + 1;
        updateRows();
        this.firstRequestField = true;
        int r123 = this.answerStartRow;
        if (r14 >= 0) goto L13;
        r14 = this.answersCount - 1;
    L13:
        this.requestFieldFocusAtPosition = r123 + r14;
        return;
    L17:
        return;
    }
}
