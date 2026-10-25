package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.TelegramAiReplacement;
import com.exteragram.messenger.translator.TranslatorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TranslateAlert3;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.iv.RichTextStyle;

/* loaded from: classes7.dex */
public class AIEditorAlert extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private final boolean[] accusative;
    private UniversalAdapter adapter;
    private Runnable aiFallbackCancel;
    private boolean aiFallbackFailed;
    private ItemOptions aiIOptions;
    private final ButtonWithCounterView allButton;
    private final FrameLayout bulletinContainer;
    private final ButtonWithCounterView button;
    private final LinearLayout buttonContainer;
    private boolean buttonShowLimit;
    private final ImageView closeView;
    private boolean collapsed;
    private long dialogId;
    private boolean editing;
    private boolean emojify;
    private boolean errored;
    private CharSequence fixedText;
    private boolean fixedTextLoading;
    private TL_iv.RichMessage fixedTextRich;
    private CharSequence fixedTextToCopy;
    private String from_lang;
    private final boolean[] genitive;
    private TLRPC.TL_messages_composeMessageWithAI[] lastRequest;
    private TLRPC.TL_messages_composeRichMessageWithAI[] lastRequestRich;
    private boolean loading;
    private boolean newPrompt;
    private Utilities.Callback4<CharSequence, Integer, Integer, Boolean> onSendListener;
    private Utilities.Callback4<TL_iv.RichMessage, Integer, Integer, Boolean> onSendRichListener;
    private Utilities.Callback<CharSequence> onUseListener;
    private Utilities.Callback<TL_iv.RichMessage> onUseRichListener;
    private final FrameLayout promptBox;
    private final EditTextCell promptCell;
    private String promptText;
    private int requestId;
    private final ButtonWithCounterView sendButton;
    private boolean showLimit;
    private HintView2 styleHint;
    private final Tabs styleTabs;
    private CharSequence styledText;
    private boolean styledTextLoading;
    private TL_iv.RichMessage styledTextRich;
    private final Tabs tabs;
    private final FrameLayout tabsContainer;
    private CharSequence text;
    private TL_iv.RichMessage textRich;
    private CharSequence title;
    private RLottieDrawable titleLoadingDrawable;
    private String to_lang;
    private final AiTonesController tonesController;
    private String translateTone;
    private String translateToneTitle;
    private CharSequence translatedText;
    private boolean translatedTextLoading;
    private TL_iv.RichMessage translatedTextRich;

    /* renamed from: org.telegram.ui.Components.AIEditorAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ AIEditorAlert this$0;

        public AnonymousClass1(AIEditorAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r2) {
            if (AIEditorAlert.m8678$$Nest$fgettabs(this.this$0) == null) goto L5;
            int r22 = AIEditorAlert.m8678$$Nest$fgettabs(this.this$0).getSelectedTab();
        L7:
            if (r22 == 1) goto L10;
            return;
        L10:
            if (AIEditorAlert.m8677$$Nest$fgetstyleTabs(this.this$0) != null) goto L12;
            return;
        L12:
            if ((AIEditorAlert.m8677$$Nest$fgetstyleTabs(this.this$0).getSelectedTone() instanceof PromptTone) == false) goto L18;
            AIEditorAlert.m8679$$Nest$mcancelRequest(this.this$0);
            AIEditorAlert.m8682$$Nest$mupdatePromptEditText(this.this$0);
            AIEditorAlert.m8681$$Nest$mupdateButton(this.this$0);
            return;
        L18:
            return;
        L5:
            r22 = 0;
            goto L7
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.AIEditorAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends ShareAlert {
        final /* synthetic */ AIEditorAlert this$0;

        public AnonymousClass2(AIEditorAlert r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r7, int r8, TLRPC.TL_forumTopic r9, boolean r10) {
            if (r10 == false) goto L26;
            BulletinFactory r82 = BulletinFactory.of(AIEditorAlert.m8676$$Nest$fgetbulletinContainer(this.this$0), this.resourcesProvider);
            if (r82 != null) goto L7;
            return;
        L7:
            if (r7.size() != 1) goto L19;
            long r2 = r7.keyAt(0);
            if (r2 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L11;
            r82.createSimpleBulletin(R.raw.saved_messages, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AIEditorStyleSharedToSavedMessages, new Object[0])), 5000).hideAfterBottomSheet(false).show();
        L23:
            AIEditorAlert.m8676$$Nest$fgetbulletinContainer(this.this$0).performHapticFeedback(3);     // Catch: Exception -> L22
            return;
        L27:
            return;
        L11:
            int r0 = this.currentAccount;
            if (r2 >= 0) goto L18;
            TLRPC.Chat r72 = MessagesController.getInstance(r0).getChat(Long.valueOf(-r2));
            int r02 = R.raw.forward;
            int r22 = R.string.AIEditorStyleSharedTo;
            if (r9 == null) goto L16;
            String r73 = r9.title;
        L17:
            r82.createSimpleBulletin(r02, AndroidUtilities.replaceTags(LocaleController.formatString(r22, new Object[]{r73})), 5000).hideAfterBottomSheet(false).show();
            goto L23
        L16:
            r73 = r72.title;
            goto L17
        L18:
            TLRPC.User r74 = MessagesController.getInstance(r0).getUser(Long.valueOf(r2));
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.AIEditorStyleSharedTo, new Object[]{r74.first_name})), 5000).hideAfterBottomSheet(false).show();
            goto L23
        L19:
            r82.createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(LocaleController.formatPluralString("AIEditorStyleSharedToManyChats", r7.size(), new Object[]{Integer.valueOf(r7.size())}))).hideAfterBottomSheet(false).show();
            goto L23
        }
    }

    /* renamed from: org.telegram.ui.Components.AIEditorAlert$3, reason: invalid class name */
    public class AnonymousClass3 implements AlertsCreator.ScheduleDatePickerDelegate {
        final /* synthetic */ AIEditorAlert this$0;

        public AnonymousClass3(AIEditorAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
        public void didSelectDate(boolean r2, int r3, int r4) {
            AIEditorAlert.m8680$$Nest$mrunSend(this.this$0, r3, r4, r2);
            this.this$0.dismiss();
        }
    }

    /* renamed from: org.telegram.ui.Components.AIEditorAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ AIEditorAlert this$0;

        public AnonymousClass4(AIEditorAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            AIEditorAlert.access$000(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.AIEditorAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ AIEditorAlert this$0;

        public AnonymousClass5(AIEditorAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            AIEditorAlert.m8683$$Nest$mupdateStyleHintY(this.this$0);
        }
    }

    public static class AiStyleAlert extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
        private UniversalAdapter adapter;
        private final FrameLayout bulletinContainer;
        private final ButtonWithCounterView button;
        private final FrameLayout buttonContainer;
        private final ImageView closeView;
        private int exampleIndex;
        private TL_aicompose.aiComposeToneExample[] examples;
        private final BackupImageView icon;
        private final FrameLayout iconButton;
        private final FrameLayout iconCell;
        private final TextView subtitle;
        private final TextView title;
        public final TL_aicompose.AiComposeTone tone;
        private final AiTonesController tonesController;

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$AiStyleAlert$1, reason: invalid class name */
        public class AnonymousClass1 extends DefaultItemAnimator {
            final /* synthetic */ AiStyleAlert this$0;

            public AnonymousClass1(AiStyleAlert r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                AiStyleAlert.access$300(this.this$0).invalidate();
            }
        }

        /* renamed from: $r8$lambda$-7mmTr52PLLQyv3vq2pBUA5EwKg, reason: not valid java name */
        public static /* synthetic */ void m8684$r8$lambda$7mmTr52PLLQyv3vq2pBUA5EwKg(AiStyleAlert r0, Theme.ResourcesProvider r1, TL_aicompose.AiComposeTone r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
            r0.lambda$new$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$7lr5osMAIUttBB5TComEi5vfmwg(AiStyleAlert r0, View r1, int r2) {
            r0.lambda$new$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$SM1CFJTxXilA45ng36Woc3Rnx2I(AiStyleAlert r0, TL_aicompose.TL_aiComposeTone r1) {
            r0.lambda$fillItems$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$eYZjyHoJZhnmKEGBquSyDJVSad4(AiStyleAlert r0, ArrayList r1, UniversalAdapter r2) {
            r0.fillItems(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$fhnq4kilPVwG_moCPtqkp0mqZS0(AiStyleAlert r0, TL_aicompose.AiComposeTone r1, Theme.ResourcesProvider r2, View r3) {
            r0.lambda$new$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$n_I8DsMzIqTqbiypLPpKh4dCBsw(AiStyleAlert r0, int r1, TL_aicompose.aiComposeToneExample r2, TLRPC.TL_error r3) {
            r0.lambda$onAnotherExample$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$rWSjbve2oZQO8AjvgZAf6KjexSQ(AiStyleAlert r0, View r1) {
            r0.onAnotherExample(r1);
        }

        public static /* synthetic */ void $r8$lambda$tPyaTJH485vmziMXv3nIyRwJoj0(AiStyleAlert r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public AiStyleAlert(Context r18, TL_aicompose.AiComposeTone r19, Theme.ResourcesProvider r20) {
            super(r18, null, false, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r20);
            this.exampleIndex = 0;
            AiTonesController r3 = MessagesController.getInstance(this.currentAccount).getTonesController();
            this.tonesController = r3;
            r3.load();
            this.tone = r19;
            TL_aicompose.aiComposeToneExample[] r32 = new TL_aicompose.aiComposeToneExample[MessagesController.getInstance(this.currentAccount).config.aicomposeToneExamplesNum.get()];
            this.examples = r32;
            if ((r19 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L5;
            r32[0] = ((TL_aicompose.TL_aiComposeTone) r19).example_english;
        L5:
            ImageView r33 = new ImageView(r18);
            this.closeView = r33;
            r33.setScaleType(ImageView.ScaleType.CENTER);
            r33.setImageResource(R.drawable.ic_close_white);
            int r4 = Theme.key_windowBackgroundWhiteBlackText;
            r33.setColorFilter(getThemedColor(r4));
            r33.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(getThemedColor(r4), 0.1f)));
            this.containerView.addView(r33, LayoutHelper.createFrame(54, 54.0f, 53, 0.0f, 0.0f, 8.0f, 0.0f));
            ScaleStateListAnimator.apply(r33, 0.1f, 1.5f);
            r33.setOnClickListener(new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda1(this));
            FrameLayout r34 = new FrameLayout(r18);
            this.iconCell = r34;
            r34.setClipToPadding(false);
            r34.setClipChildren(false);
            FrameLayout r5 = new FrameLayout(r18);
            this.iconButton = r5;
            r5.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(100.0f), Theme.getColor(Theme.key_windowBackgroundWhite, r20)));
            r34.addView(r5, LayoutHelper.createFrame(100, 100.0f, 17, 0.0f, 0.0f, 0.0f, 0.0f));
            BackupImageView r35 = new BackupImageView(r18);
            this.icon = r35;
            r35.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(4, this.currentAccount, r19.emoji_id));
            r5.addView(r35, LayoutHelper.createFrame(64, 64, 17));
            TextView r36 = new TextView(r18);
            this.title = r36;
            r36.setTextColor(getThemedColor(r4));
            r36.setTextSize(1, 20.0f);
            r36.setTypeface(AndroidUtilities.bold());
            r36.setGravity(17);
            r36.setText(r19.title);
            TextView r37 = new TextView(r18);
            this.subtitle = r37;
            r37.setTextColor(getThemedColor(r4));
            r37.setTextSize(1, 14.0f);
            r37.setGravity(17);
            r37.setText(LocaleController.getString(R.string.AIEditorStyleText));
            this.actionBar.setTitle(r19.title);
            int r38 = Theme.key_windowBackgroundGray;
            this.behindKeyboardColorKey = r38;
            setBackgroundColor(getThemedColor(r38));
            RecyclerListView r42 = this.recyclerListView;
            int r52 = this.backgroundPaddingLeft;
            r42.setPadding(r52, 0, r52, AndroidUtilities.dp(66.0f));
            this.recyclerListView.setClipToPadding(false);
            this.recyclerListView.setSegmentedSectionsEnabled(false);
            this.recyclerListView.setSections();
            this.recyclerListView.setOnItemClickListener(new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda2(this));
            this.ignoreTouchActionBar = false;
            this.headerMoveTop = AndroidUtilities.dp(36.0f);
            this.topPadding = 0.35f;
            this.takeTranslationIntoAccount = true;
            AnonymousClass1 r43 = new AnonymousClass1(this);
            r43.setSupportsChangeAnimations(false);
            r43.setDelayAnimations(false);
            r43.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r43.setDurations(350);
            this.recyclerListView.setItemAnimator(r43);
            FrameLayout r44 = new FrameLayout(r18);
            this.buttonContainer = r44;
            r44.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f));
            r44.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(getThemedColor(r38), 0.0f), getThemedColor(r38), getThemedColor(r38)}));
            FrameLayout.LayoutParams r39 = LayoutHelper.createFrame(-1, -2, 80);
            int r53 = r39.leftMargin;
            int r7 = this.backgroundPaddingLeft;
            r39.leftMargin = r53 + r7;
            r39.rightMargin += r7;
            this.containerView.addView(r44, r39);
            FrameLayout r310 = new FrameLayout(r18);
            this.bulletinContainer = r310;
            FrameLayout.LayoutParams r54 = LayoutHelper.createFrame(-1, -2.0f, 80, 6.0f, 0.0f, 6.0f, 60.0f);
            int r72 = r54.leftMargin;
            int r10 = this.backgroundPaddingLeft;
            r54.leftMargin = r72 + r10;
            r54.rightMargin += r10;
            this.containerView.addView(r310, r54);
            ButtonWithCounterView r1 = new ButtonWithCounterView(r18, r20).setRound();
            this.button = r1;
            if (isAlreadyAdded() == false) goto L8;
            int r311 = R.string.AIEditorStyleDone;
        L9:
            r1.setText(LocaleController.getString(r311));
            r1.setOnClickListener(new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda3(this, r19, r20));
            r44.addView(r1, LayoutHelper.createFrame(-1, 48, 119));
            this.adapter.update(false);
            return;
        L8:
            r311 = R.string.AIEditorAddStyle;
            goto L9
        }

        public static /* synthetic */ ViewGroup access$300(AiStyleAlert r0) {
            return r0.containerView;
        }

        private void fillItems(ArrayList<UItem> r13, UniversalAdapter r14) {
            r14.itemsOffset = 1;
            TLRPC.User r1 = null;
            r13.add(UItem.asShadow(null));
            r13.add(UItem.asCustomShadow(this.iconCell, true));
            r13.add(UItem.asShadow(null));
            r13.add(UItem.asCustomShadow(this.title));
            r13.add(UItem.asSpace(AndroidUtilities.dp(1.0f)));
            r13.add(UItem.asCustomShadow(this.subtitle));
            r13.add(UItem.asSpace(AndroidUtilities.dp(24.0f)));
            TL_aicompose.AiComposeTone r0 = this.tone;
            if ((r0 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L30;
            TL_aicompose.TL_aiComposeTone r02 = (TL_aicompose.TL_aiComposeTone) r0;
            TL_aicompose.aiComposeToneExample r2 = this.examples[this.exampleIndex];
            r14.whiteSectionStart();
            r13.add(TranslateAlert3.Header.Factory.of(3, LocaleController.getString(R.string.AIEditorBefore), null, null, null, false, null, new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda4(this)));
            if (r2 != null) goto L8;
            CharSequence r3 = loadingText();
        L9:
            r13.add(TranslateAlert3.Text.Factory.of(4, r3, false, false, null, null, null));
            r13.add(TranslateAlert3.Header.Factory.of(5, LocaleController.getString(R.string.AIEditorAfter), null, null, null));
            if (r2 != null) goto L13;
            CharSequence r22 = loadingText();
        L14:
            r13.add(TranslateAlert3.Text.Factory.of(6, r22, false, false, null, null, null));
            r14.whiteSectionEnd();
            if (r02.author_id == 0) goto L17;
            r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r02.author_id));
        L17:
            String r142 = UserObject.getPublicUsername(r1);
            int r23 = r02.installs_count;
            if (r1 != null) goto L21;
            if (r23 <= 0) goto L30;
            r13.add(UItem.asShadow(LocaleController.formatPluralString("AIEditorUsedBy", r23, new Object[0])));
            goto L30
        L21:
            StringBuilder r5 = new StringBuilder();
            if (r23 <= 0) goto L24;
            String r24 = LocaleController.formatPluralString("AIEditorUsedBy", r02.installs_count, new Object[0]) + " ";
        L25:
            r5.append(r24);
            if (TextUtils.isEmpty(r142) == false) goto L28;
            String r143 = LocaleController.formatString(R.string.AIEditorCreatedBy, new Object[]{UserObject.getUserName(r1)});
        L29:
            r5.append(r143);
            r13.add(UItem.asShadow(AndroidUtilities.replaceSingleLink(r5.toString(), getThemedColor(Theme.key_chat_messageLinkIn), new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda5(this, r02))));
            goto L30
        L28:
            r143 = LocaleController.formatString(R.string.AIEditorCreatedBy, new Object[]{"@" + r142});
            goto L29
        L24:
            r24 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L25
        L13:
            r22 = MessageObject.formatTextWithEntities(r2.to);
            goto L14
        L8:
            r3 = MessageObject.formatTextWithEntities(r2.from);
        L30:
            r13.add(UItem.asSpace(AndroidUtilities.dp(32.0f)));
        }

        private boolean isAlreadyAdded() {
            TL_aicompose.AiComposeTone r0 = this.tone;
            if ((r0 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L14;
            TL_aicompose.TL_aiComposeTone r02 = (TL_aicompose.TL_aiComposeTone) r0;
            int r1 = 0;
        L6:
            if (r1 >= this.tonesController.tones.size()) goto L14;
            TL_aicompose.AiComposeTone r3 = this.tonesController.tones.get(r1);
            if ((r3 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L13;
            if (((TL_aicompose.TL_aiComposeTone) r3).id != r02.id) goto L13;
            return true;
        L13:
            r1 = r1 + 1;
        L14:
            return false;
        }

        private /* synthetic */ void lambda$fillItems$5(TL_aicompose.TL_aiComposeTone r4) {
            BaseFragment r0 = LaunchActivity.getSafeLastFragment();
            if (r0 != null) goto L5;
            return;
        L5:
            r0.presentFragment(ProfileActivity.of(r4.author_id));
            dismiss();
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            dismiss();
        }

        private /* synthetic */ void lambda$new$1(View r1, int r2) {
            this.adapter.getItem(r2 - 1);
        }

        private /* synthetic */ void lambda$new$2(Theme.ResourcesProvider r2, TL_aicompose.AiComposeTone r3, TLRPC.Bool r4, TLRPC.TL_error r5) {
            this.button.setLoading(false);
            if (r5 == null) goto L10;
            boolean r32 = "TONES_SAVED_TOO_MANY".equalsIgnoreCase(r5.text);
            FrameLayout r42 = this.bulletinContainer;
            if (r32 == false) goto L8;
            AIEditorAlert.showStylesLimitToast(BulletinFactory.of(r42, r2), this.currentAccount);
            return;
        L8:
            BulletinFactory.of(r42, r2).showForError(r5);
            return;
        L10:
            MessagesController.getInstance(this.currentAccount).getTonesController().add(r3);
            dismiss();
            BaseFragment r1 = LaunchActivity.getSafeLastFragment();
            if (r1 == null) goto L14;
            BulletinFactory.of(r1).createEmojiBulletin(r3.emoji_id, LocaleController.getString(R.string.AIEditorToneAddedTitle), LocaleController.formatString(R.string.AIEditorToneAddedText, new Object[]{r3.title})).show();
            return;
        }

        private /* synthetic */ void lambda$new$3(TL_aicompose.AiComposeTone r4, Theme.ResourcesProvider r5, View r6) {
            if (this.button.isEnabled() == true) goto L5;
            return;
        L5:
            if (this.button.isLoading() == false) goto L8;
            return;
        L8:
            if (isAlreadyAdded() == false) goto L11;
            dismiss();
            return;
        L11:
            this.button.setLoading(true);
            TL_aicompose.saveTone r62 = new TL_aicompose.saveTone();
            r62.tone = TL_aicompose.InputAiComposeTone.from(r4);
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r62, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda6(this, r5, r4));
        }

        private /* synthetic */ void lambda$onAnotherExample$4(int r1, TL_aicompose.aiComposeToneExample r2, TLRPC.TL_error r3) {
            if (r2 == null) goto L5;
            this.examples[r1] = r2;
            this.adapter.update(true);
            return;
        }

        private CharSequence loadingText() {
            return loadingText(5);
        }

        private CharSequence loadingText(int r7) {
            SpannableStringBuilder r6 = new SpannableStringBuilder();
            int r1 = 0;
        L3:
            if (r1 >= r7) goto L7;
            if (r1 <= 0) goto L6;
            r6.append("\n");
        L6:
            int r2 = AndroidUtilities.dp((int) (Math.random() * 50.0d));
            int r3 = r6.length();
            r6.append(LocaleController.getString(R.string.Loading));
            r6.setSpan(new LoadingSpan(null, r2, 0).setHeight(AndroidUtilities.dp(6.0f)).setAlpha(0.5f).setFullWidth(true), r3, r6.length(), 33);
            r1 = r1 + 1;
            goto L3
        L7:
            return r6;
        }

        private void onAnotherExample(View r6) {
            if ((this.tone instanceof TL_aicompose.TL_aiComposeTone) == true) goto L5;
            return;
        L5:
            int r62 = this.exampleIndex + 1;
            this.exampleIndex = r62;
            TL_aicompose.aiComposeToneExample[] r1 = this.examples;
            if (r62 < r1.length) goto L8;
            this.exampleIndex = 0;
        L8:
            int r63 = this.exampleIndex;
            if (r1[r63] != null) goto L11;
            TL_aicompose.getToneExample r12 = new TL_aicompose.getToneExample();
            r12.tone = TL_aicompose.InputAiComposeTone.from(this.tone);
            r12.num = r63;
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r12, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda7(this, r63));
        L11:
            this.adapter.update(true);
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new AIEditorAlert$AiStyleAlert$$ExternalSyntheticLambda0(this), this.resourcesProvider);
            this.adapter = r0;
            r0.setApplyBackground(false);
            return this.adapter;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.loadedAiComposeTones) goto L10;
            ButtonWithCounterView r12 = this.button;
            if (isAlreadyAdded() == false) goto L7;
            int r0 = R.string.AIEditorStyleDone;
        L8:
            r12.setText(LocaleController.getString(r0));
            return;
        L7:
            r0 = R.string.AIEditorAddStyle;
            goto L8
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.loadedAiComposeTones);
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            TL_aicompose.AiComposeTone r0 = this.tone;
            if (r0 != null) goto L7;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            return r0.title;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public void onActionBarAlpha(float r5) {
            SimpleTextView r0 = this.actionBar.getTitleTextView();
            if (r0 == null) goto L5;
            r0.setAlpha(r5);
        L5:
            this.closeView.setTranslationY(((this.actionBar.getTranslationY() + AndroidUtilities.statusBarHeight) + (((this.actionBar.getHeight() - AndroidUtilities.statusBarHeight) - this.closeView.getHeight()) / 2.0f)) + (AndroidUtilities.dp(28.0f) * (1.0f - r5)));
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void show() {
            super.show();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.loadedAiComposeTones);
        }
    }

    public static class CreateAiStyleAlert extends BottomSheetWithRecyclerListView {
        private UniversalAdapter adapter;
        private final FrameLayout bulletinContainer;
        private final ButtonWithCounterView button;
        private final FrameLayout buttonContainer;
        private final CheckBox2 checkbox;
        private final FrameLayout checkboxCell;
        private final ImageView closeView;
        private TL_aicompose.TL_aiComposeTone editing;
        private Long emoji_id;
        private final BackupImageView icon;
        private final FrameLayout iconButton;
        private final FrameLayout iconCell;
        private boolean localEditing;
        private boolean localMode;
        private boolean localPreview;
        private Utilities.Callback3Return<String, String, Long, Boolean> onLocalSaved;
        private Utilities.Callback<TL_aicompose.AiComposeTone> onToneCreated;
        private Utilities.Callback<TL_aicompose.AiComposeTone> onToneEdited;
        private final EditTextCell promptCell;
        private SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow selectAnimatedEmojiDialog;
        private final EditTextCell titleCell;

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$CreateAiStyleAlert$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            final /* synthetic */ CreateAiStyleAlert this$0;

            public AnonymousClass1(CreateAiStyleAlert r1) {
                this.this$0 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r1) {
                CreateAiStyleAlert.m8690$$Nest$mupdateButton(this.this$0);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$CreateAiStyleAlert$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ CreateAiStyleAlert this$0;

            public AnonymousClass2(CreateAiStyleAlert r1) {
                this.this$0 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r1) {
                CreateAiStyleAlert.m8690$$Nest$mupdateButton(this.this$0);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$CreateAiStyleAlert$3, reason: invalid class name */
        public class AnonymousClass3 extends DefaultItemAnimator {
            final /* synthetic */ CreateAiStyleAlert this$0;

            public AnonymousClass3(CreateAiStyleAlert r1) {
                this.this$0 = r1;
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                CreateAiStyleAlert.access$100(this.this$0).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$CreateAiStyleAlert$4, reason: invalid class name */
        public class AnonymousClass4 extends SelectAnimatedEmojiDialog {
            final /* synthetic */ CreateAiStyleAlert this$0;
            final /* synthetic */ SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] val$popup;

            public AnonymousClass4(CreateAiStyleAlert r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, Theme.ResourcesProvider r7, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r8) {
                this.this$0 = r1;
                this.val$popup = r8;
                super(r2, r3, r4, r5, r6, r7);
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog
            public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
                CreateAiStyleAlert.m8688$$Nest$fputemoji_id(this.this$0, r2);
                CreateAiStyleAlert.m8691$$Nest$mupdateIcon(this.this$0);
                CreateAiStyleAlert.m8690$$Nest$mupdateButton(this.this$0);
                if (this.val$popup[0] == null) goto L6;
                CreateAiStyleAlert.m8689$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
                this.val$popup[0].dismiss();
                return;
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog
            public boolean willApplyEmoji(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
                if (r4 != null) goto L5;
            L10:
                return true;
            L5:
                if (StarsController.getInstance(CreateAiStyleAlert.access$200(this.this$0)).findUserStarGift(r4.id) == null) goto L10;
                if (MessagesController.getGlobalMainSettings().getInt("statusgiftpage", 0) >= 2) goto L10;
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$CreateAiStyleAlert$5, reason: invalid class name */
        public class AnonymousClass5 extends SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow {
            final /* synthetic */ CreateAiStyleAlert this$0;

            public AnonymousClass5(CreateAiStyleAlert r1, View r2, int r3, int r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow, android.widget.PopupWindow
            public void dismiss() {
                super.dismiss();
                CreateAiStyleAlert.m8689$$Nest$fputselectAnimatedEmojiDialog(this.this$0, null);
            }
        }

        /* renamed from: $r8$lambda$3xAjngIWdhcsXco8m-uiqLmwzhQ, reason: not valid java name */
        public static /* synthetic */ void m8685$r8$lambda$3xAjngIWdhcsXco8muiqLmwzhQ(CreateAiStyleAlert r0, AlertDialog r1, int r2) {
            r0.lambda$new$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$9dIvKQa2yMg8RgFj4AiWUgmBWpQ(CreateAiStyleAlert r0, Theme.ResourcesProvider r1, View r2) {
            r0.lambda$new$8(r1, r2);
        }

        /* renamed from: $r8$lambda$EiOETSIptY7Cq1h4ZOdDvzG-EVo, reason: not valid java name */
        public static /* synthetic */ void m8686$r8$lambda$EiOETSIptY7Cq1h4ZOdDvzGEVo(CreateAiStyleAlert r0, View r1) {
            r0.lambda$new$2(r1);
        }

        /* renamed from: $r8$lambda$F88XIvFLvGL-S6OIISlq4ypi5Q8, reason: not valid java name */
        public static /* synthetic */ void m8687$r8$lambda$F88XIvFLvGLS6OIISlq4ypi5Q8(CreateAiStyleAlert r0, Browser.Progress r1, AlertDialog r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
            r0.lambda$new$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$IhLIImFHcVAFlTyjlY1IEd6IuHM(CreateAiStyleAlert r0, ArrayList r1, UniversalAdapter r2) {
            r0.fillItems(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Sp7DVeJOqo24B5rDMjE6y6KFIHs(CreateAiStyleAlert r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$UEM6mw50u_46GwbI54KWF2zmHZg(CreateAiStyleAlert r0, Theme.ResourcesProvider r1, View r2, int r3) {
            r0.lambda$new$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$YihBMTKj1r8323668l1smAotWV8(CreateAiStyleAlert r0, Theme.ResourcesProvider r1, TL_aicompose.AiComposeTone r2, TLRPC.TL_error r3) {
            r0.lambda$new$6(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$feLLmp5f3Uo4ZZW9I0MF1VRtk3E(CreateAiStyleAlert r0, Theme.ResourcesProvider r1, TL_aicompose.AiComposeTone r2, TLRPC.TL_error r3) {
            r0.lambda$new$7(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$qNgAGA2Ajl07274gXbXR2YFiEDk(CreateAiStyleAlert r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fputemoji_id, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8688$$Nest$fputemoji_id(CreateAiStyleAlert r0, Long r1) {
            r0.emoji_id = r1;
        }

        /* renamed from: -$$Nest$fputselectAnimatedEmojiDialog, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8689$$Nest$fputselectAnimatedEmojiDialog(CreateAiStyleAlert r0, SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow r1) {
            r0.selectAnimatedEmojiDialog = r1;
        }

        /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8690$$Nest$mupdateButton(CreateAiStyleAlert r0) {
            r0.updateButton();
        }

        /* renamed from: -$$Nest$mupdateIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8691$$Nest$mupdateIcon(CreateAiStyleAlert r0) {
            r0.updateIcon();
        }

        public CreateAiStyleAlert(Context r17, Theme.ResourcesProvider r18) {
            super(r17, null, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r18);
            ImageView r0 = new ImageView(r17);
            this.closeView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            r0.setImageResource(R.drawable.ic_close_white);
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setColorFilter(getThemedColor(r2));
            r0.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(getThemedColor(r2), 0.1f)));
            this.actionBar.addView(r0, LayoutHelper.createFrame(54, 54.0f, 85, 0.0f, 0.0f, 8.0f, 0.0f));
            ScaleStateListAnimator.apply(r0, 0.1f, 1.5f);
            r0.setOnClickListener(new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda1(this));
            FrameLayout r02 = new FrameLayout(r17);
            this.iconCell = r02;
            FrameLayout r22 = new FrameLayout(r17);
            this.iconButton = r22;
            r22.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(100.0f), Theme.getColor(Theme.key_windowBackgroundWhite, r18)));
            ScaleStateListAnimator.apply(r22);
            r02.addView(r22, LayoutHelper.createFrame(100, 100, 17));
            BackupImageView r03 = new BackupImageView(r17);
            this.icon = r03;
            updateIcon();
            r22.addView(r03, LayoutHelper.createFrame(64, 64, 17));
            r22.setOnClickListener(new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda2(this));
            EditTextCell r04 = new EditTextCell(r17, LocaleController.getString(R.string.AIEditorStyleTitleHint), false, false, MessagesController.getInstance(this.currentAccount).config.aicomposeToneTitleLengthMax.get(), r18);
            this.titleCell = r04;
            r04.editText.addTextChangedListener(new AnonymousClass1(this));
            EditTextCell r05 = new EditTextCell(r17, LocaleController.getString(R.string.AIEditorStylePromptHint), true, false, MessagesController.getInstance(this.currentAccount).config.aicomposeTonePromptLengthMax.get(), r18);
            this.promptCell = r05;
            r05.setShowLimitWhenNear(Math.max(100, MessagesController.getInstance(this.currentAccount).config.aicomposeTonePromptLengthMax.get() / 2));
            r05.editText.addTextChangedListener(new AnonymousClass2(this));
            LinearLayout r06 = new LinearLayout(r17);
            r06.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
            r06.setClipToPadding(false);
            r06.setOrientation(0);
            r06.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r18), 24, 24));
            CheckBox2 r4 = new CheckBox2(r17, 24, r18);
            this.checkbox = r4;
            r4.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
            r4.setDrawUnchecked(true);
            r4.setChecked(false, false);
            r4.setDrawBackgroundAsArc(10);
            r06.addView(r4, LayoutHelper.createLinear(26, 26, 16, 0, 0, 0, 0));
            TextView r42 = new TextView(r17);
            r42.setTextColor(Theme.getColor(Theme.key_dialogTextGray2, r18));
            r42.setTextSize(1, 14.0f);
            r42.setText(LocaleController.getString(R.string.AIEditorStyleAddLink));
            r06.addView(r42, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
            r06.setOnClickListener(new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda3(this));
            FrameLayout r43 = new FrameLayout(r17);
            this.checkboxCell = r43;
            r43.addView(r06, LayoutHelper.createFrame(-2, -2.0f, 17, 2.0f, 2.0f, 2.0f, 2.0f));
            int r07 = Theme.key_windowBackgroundGray;
            this.behindKeyboardColorKey = r07;
            setBackgroundColor(getThemedColor(r07));
            RecyclerListView r44 = this.recyclerListView;
            int r8 = this.backgroundPaddingLeft;
            r44.setPadding(r8, 0, r8, AndroidUtilities.dp(66.0f));
            this.recyclerListView.setClipToPadding(false);
            this.recyclerListView.setSegmentedSectionsEnabled(false);
            this.recyclerListView.setSections();
            this.recyclerListView.setOnItemClickListener(new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda4(this, r18));
            this.ignoreTouchActionBar = false;
            this.headerMoveTop = AndroidUtilities.dp(12.0f);
            this.topPadding = 0.35f;
            this.smoothKeyboardAnimationEnabled = true;
            this.takeTranslationIntoAccount = true;
            AnonymousClass3 r45 = new AnonymousClass3(this);
            r45.setSupportsChangeAnimations(false);
            r45.setDelayAnimations(false);
            r45.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r45.setDurations(350);
            this.recyclerListView.setItemAnimator(r45);
            FrameLayout r46 = new FrameLayout(r17);
            this.buttonContainer = r46;
            r46.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
            r46.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(getThemedColor(r07), 0.0f), getThemedColor(r07), getThemedColor(r07)}));
            FrameLayout.LayoutParams r08 = LayoutHelper.createFrame(-1, -2, 80);
            int r23 = r08.leftMargin;
            int r82 = this.backgroundPaddingLeft;
            r08.leftMargin = r23 + r82;
            r08.rightMargin += r82;
            this.containerView.addView(r46, r08);
            FrameLayout r09 = new FrameLayout(r17);
            this.bulletinContainer = r09;
            FrameLayout.LayoutParams r24 = LayoutHelper.createFrame(-1, -2.0f, 80, 6.0f, 0.0f, 6.0f, 60.0f);
            int r83 = r24.leftMargin;
            int r9 = this.backgroundPaddingLeft;
            r24.leftMargin = r83 + r9;
            r24.rightMargin += r9;
            this.containerView.addView(r09, r24);
            ButtonWithCounterView r010 = new ButtonWithCounterView(r17, r18).setRound();
            this.button = r010;
            r010.setText(LocaleController.getString(R.string.AIEditorStyleCreate));
            r010.setOnClickListener(new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda5(this, r18));
            r46.addView(r010, LayoutHelper.createFrame(-1, 48, 119));
            updateButton();
            this.adapter.update(false);
        }

        public static /* synthetic */ ViewGroup access$100(CreateAiStyleAlert r0) {
            return r0.containerView;
        }

        public static /* synthetic */ int access$200(CreateAiStyleAlert r0) {
            return r0.currentAccount;
        }

        private void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
            r3.add(UItem.asShadow(null));
            r3.add(UItem.asCustomShadow(this.iconCell));
            r3.add(UItem.asShadow(null));
            r3.add(UItem.asCustom(this.titleCell));
            r3.add(UItem.asShadow(null));
            r3.add(UItem.asCustom(this.promptCell));
            r3.add(UItem.asShadow(null));
            if (this.editing == null) goto L8;
            if (this.localMode == true) goto L8;
            r3.add(UItem.asButton(1, LocaleController.getString(R.string.AIEditorDeleteStyle)).red());
            r3.add(UItem.asShadow(null));
        L8:
            if (this.localMode == true) goto L11;
            r3.add(UItem.asCustomShadow(this.checkboxCell));
            return;
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            dismiss();
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            openIconDialog();
        }

        private /* synthetic */ void lambda$new$2(View r2) {
            this.checkbox.setChecked(!r1.isChecked(), true);
        }

        private /* synthetic */ void lambda$new$3(Browser.Progress r1, AlertDialog r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
            r1.end();
            r2.dismiss();
            dismiss();
            MessagesController.getInstance(this.currentAccount).getTonesController().remove(this.editing);
        }

        private /* synthetic */ void lambda$new$4(AlertDialog r5, int r6) {
            Browser.Progress r62 = r5.makeButtonLoading(-1);
            r62.init();
            TL_aicompose.deleteTone r0 = new TL_aicompose.deleteTone();
            r0.tone = TL_aicompose.InputAiComposeTone.from(this.editing);
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda9(this, r62, r5));
        }

        private /* synthetic */ void lambda$new$5(Theme.ResourcesProvider r2, View r3, int r4) {
            UItem r32 = this.adapter.getItem(r4 - 1);
            if (r32 != null) goto L6;
            return;
        L6:
            if (r32.id != 1) goto L9;
            new AlertDialog.Builder(getContext(), r2).setTitle(LocaleController.getString(R.string.AIEditorDeleteStyle)).setMessage(LocaleController.getString(R.string.AIEditorDeleteStyleText)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.Delete), new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda6(this)).makeRed(-1).show();
            return;
        }

        private /* synthetic */ void lambda$new$6(Theme.ResourcesProvider r3, TL_aicompose.AiComposeTone r4, TLRPC.TL_error r5) {
            this.button.setLoading(false);
            if (r4 == null) goto L9;
            Utilities.Callback<TL_aicompose.AiComposeTone> r32 = this.onToneEdited;
            if (r32 == null) goto L7;
            r32.run(r4);
        L7:
            dismiss();
            return;
        L9:
            if (r5 == null) goto L12;
            BulletinFactory.of(this.bulletinContainer, r3).showForError(r5);
            return;
        }

        private /* synthetic */ void lambda$new$7(Theme.ResourcesProvider r3, TL_aicompose.AiComposeTone r4, TLRPC.TL_error r5) {
            this.button.setLoading(false);
            if (r4 == null) goto L8;
            dismiss();
            Utilities.Callback<TL_aicompose.AiComposeTone> r2 = this.onToneCreated;
            if (r2 == null) goto L15;
            r2.run(r4);
            return;
        L15:
            return;
        L8:
            if (r5 == null) goto L16;
            boolean r42 = "TONES_SAVED_TOO_MANY".equalsIgnoreCase(r5.text);
            FrameLayout r0 = this.bulletinContainer;
            if (r42 == false) goto L13;
            AIEditorAlert.showStylesLimitToast(BulletinFactory.of(r0, r3), this.currentAccount);
            return;
        L13:
            BulletinFactory.of(r0, r3).showForError(r5);
            return;
        }

        private /* synthetic */ void lambda$new$8(Theme.ResourcesProvider r6, View r7) {
            if (this.button.isLoading() == false) goto L6;
            return;
        L6:
            if (this.localPreview == false) goto L9;
            return;
        L9:
            if (this.button.isEnabled() == false) goto L11;
            boolean r0 = true;
            this.button.setLoading(true);
            if (this.localMode == false) goto L31;
            Utilities.Callback3Return<String, String, Long, Boolean> r62 = this.onLocalSaved;
            if (r62 != null) goto L21;
        L24:
            this.button.setLoading(false);
            if (r0 == false) goto L28;
            dismiss();
            return;
        L28:
            this.titleCell.performHapticFeedback(3, 2);
            AndroidUtilities.shakeView(this.titleCell);
            return;
        L21:
            if (r62.run(this.titleCell.getText().toString(), this.promptCell.getText().toString(), this.emoji_id).booleanValue() == true) goto L24;
            r0 = false;
            goto L24
        L31:
            if (this.editing == null) goto L34;
            TL_aicompose.updateTone r72 = new TL_aicompose.updateTone();
            r72.flags = 1 | r72.flags;
            r72.display_author = this.checkbox.isChecked();
            r72.tone = TL_aicompose.InputAiComposeTone.from(this.editing);
            r72.flags |= 2;
            r72.emoji_id = this.emoji_id.longValue();
            r72.flags |= 4;
            r72.title = this.titleCell.getText().toString();
            r72.flags |= 8;
            r72.prompt = this.promptCell.getText().toString();
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r72, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda7(this, r6));
            return;
        L34:
            TL_aicompose.createTone r73 = new TL_aicompose.createTone();
            r73.display_author = this.checkbox.isChecked();
            r73.emoji_id = this.emoji_id.longValue();
            r73.title = this.titleCell.getText().toString();
            r73.prompt = this.promptCell.getText().toString();
            ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r73, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda8(this, r6));
            return;
        L11:
            if (this.localMode == false) goto L13;
            return;
        L13:
            if (this.emoji_id != null) goto L37;
            openIconDialog();
            return;
        }

        private void openIconDialog() {
            if (this.localPreview == false) goto L6;
            return;
        L6:
            if (this.selectAnimatedEmojiDialog == null) goto L8;
            return;
        L8:
            AnonymousClass4 r1 = new AnonymousClass4(this, null, getContext(), this.localMode, Integer.valueOf(AndroidUtilities.dp(150.0f)), 15, this.resourcesProvider, r9);
            r1.setSelected(this.emoji_id);
            r1.setSaveState(1);
            AnonymousClass5 r11 = new AnonymousClass5(this, r1, -2, -2);
            this.selectAnimatedEmojiDialog = r11;
            SelectAnimatedEmojiDialog.SelectAnimatedEmojiDialogWindow[] r9 = {r11};
            r11.showAsDropDown(this.iconButton, AndroidUtilities.dp(150.0f), -AndroidUtilities.dp(390.0f), 80);
            r9[0].dimBehind();
        }

        private void updateButton() {
            boolean r0 = this.localPreview;
            ButtonWithCounterView r1 = this.button;
            boolean r2 = false;
            if (r0 == false) goto L7;
            r1.setEnabled(false);
            return;
        L7:
            if (this.localMode == true) goto L11;
            if (this.emoji_id != null) goto L11;
        L15:
            r1.setEnabled(r2);
            return;
        L11:
            if (this.titleCell.getText().length() <= 0) goto L15;
            if (this.promptCell.getText().length() <= 0) goto L15;
            r2 = true;
            goto L15
        }

        private void updateIcon() {
            Long r0 = this.emoji_id;
            BackupImageView r1 = this.icon;
            if (r0 != null) goto L6;
            r1.setImageResource(R.drawable.menu_smile_add);
            this.icon.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogEmptyImage, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            return;
        L6:
            r1.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(4, this.currentAccount, this.emoji_id.longValue()));
            this.icon.setColorFilter(null);
            this.icon.setEmojiColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
            UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new AIEditorAlert$CreateAiStyleAlert$$ExternalSyntheticLambda0(this), this.resourcesProvider);
            this.adapter = r0;
            r0.setApplyBackground(false);
            return this.adapter;
        }

        @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
        public CharSequence getTitle() {
            if (this.localMode == false) goto L11;
            if (this.localEditing == false) goto L7;
            int r1 = R.string.EditRole;
        L9:
            return LocaleController.getString(r1);
        L7:
            r1 = R.string.NewRole;
            goto L9
        L11:
            if (this.editing == null) goto L15;
            int r12 = R.string.AIEditorEditStyle;
        L14:
            return LocaleController.getString(r12);
        L15:
            r12 = R.string.AIEditorNewStyle;
            goto L14
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onSmoothContainerViewLayout(float r1) {
            super.onSmoothContainerViewLayout(r1);
            this.buttonContainer.setTranslationY(r1);
        }

        public CreateAiStyleAlert setEditing(TL_aicompose.TL_aiComposeTone r5) {
            this.editing = r5;
            this.emoji_id = Long.valueOf(r5.emoji_id);
            updateIcon();
            this.titleCell.setText(this.editing.title);
            this.promptCell.setText(this.editing.prompt);
            CheckBox2 r52 = this.checkbox;
            if (this.editing.author_id == 0) goto L5;
            boolean r0 = true;
        L6:
            r52.setChecked(r0, false);
            this.actionBar.setTitle(LocaleController.getString(R.string.AIEditorEditStyle));
            this.button.setText(LocaleController.getString(R.string.AIEditorStyleEdit));
            updateButton();
            this.adapter.update(false);
            return this;
        L5:
            r0 = false;
            goto L6
        }

        public CreateAiStyleAlert setLocalStyle(String r4, String r5, long r6, boolean r8, int r9, int r10, Utilities.Callback3Return<String, String, Long, Boolean> r11) {
            this.localMode = true;
            this.localEditing = r8;
            this.localPreview = false;
            this.onLocalSaved = r11;
            if (r6 == 0) goto L5;
            Long r62 = Long.valueOf(r6);
        L6:
            this.emoji_id = r62;
            updateIcon();
            this.titleCell.setMaxLength(r9);
            this.titleCell.editText.setHint(LocaleController.getString(R.string.RoleName));
            this.titleCell.setText(r4);
            this.promptCell.setMaxLength(r10);
            this.promptCell.setShowLimitWhenNear(Math.max(100, r10 / 2));
            this.promptCell.editText.setHint(LocaleController.getString(R.string.RolePrompt));
            this.promptCell.setText(r5);
            ActionBar r42 = this.actionBar;
            if (r8 == false) goto L9;
            int r52 = R.string.EditRole;
        L10:
            r42.setTitle(LocaleController.getString(r52));
            ButtonWithCounterView r43 = this.button;
            if (r8 == false) goto L13;
            int r53 = R.string.AIEditorStyleEdit;
        L14:
            r43.setText(LocaleController.getString(r53));
            updateButton();
            this.adapter.update(false);
            return this;
        L13:
            r53 = R.string.AIEditorStyleCreate;
            goto L14
        L9:
            r52 = R.string.NewRole;
            goto L10
        L5:
            r62 = null;
            goto L6
        }

        public CreateAiStyleAlert setLocalStylePreview(String r10, String r11, long r12, int r14, int r15) {
            setLocalStyle(r10, r11, r12, false, r14, r15, null);
            this.localPreview = true;
            this.iconButton.setEnabled(false);
            this.titleCell.editText.setFocusable(false);
            this.titleCell.editText.setFocusableInTouchMode(false);
            this.titleCell.editText.setCursorVisible(false);
            this.promptCell.editText.setFocusable(false);
            this.promptCell.editText.setFocusableInTouchMode(false);
            this.promptCell.editText.setCursorVisible(false);
            this.promptCell.editText.setMaxLines(Integer.MAX_VALUE);
            RecyclerListView r9 = this.recyclerListView;
            int r112 = this.backgroundPaddingLeft;
            r9.setPadding(r112, 0, r112, AndroidUtilities.dp(6.0f));
            this.buttonContainer.setVisibility(8);
            this.actionBar.setTitle(LocaleController.getString(R.string.Info));
            updateButton();
            this.adapter.update(false);
            return this;
        }

        public CreateAiStyleAlert setOnToneCreated(Utilities.Callback<TL_aicompose.AiComposeTone> r1) {
            this.onToneCreated = r1;
            return this;
        }

        public CreateAiStyleAlert setOnToneEdited(Utilities.Callback<TL_aicompose.AiComposeTone> r1) {
            this.onToneEdited = r1;
            return this;
        }
    }

    public final class LimitSpan extends ReplacementSpan {
        private final Paint paint;
        private final Text text;
        final /* synthetic */ AIEditorAlert this$0;

        public LimitSpan(AIEditorAlert r3, CharSequence r4) {
            this.this$0 = r3;
            this.paint = new Paint(1);
            Text r32 = new Text(r4, 13.0f, AndroidUtilities.getTypeface("fonts/num.otf"));
            this.text = r32;
            r32.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            float r72 = (r6 + r8) / 2.0f;
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(r5, r72 - AndroidUtilities.dp(7.66f), (this.text.getCurrentWidth() + r5) + AndroidUtilities.dp(6.66f), AndroidUtilities.dp(7.66f) + r72);
            r1.saveLayerAlpha(r22, 255, 31);
            this.paint.setColor(r9.getColor());
            r1.drawRoundRect(r22, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), this.paint);
            this.text.draw(r1, r5 + AndroidUtilities.dp(3.33f), r72, -1, 1.0f);
            r1.restore();
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return (int) (this.text.getCurrentWidth() + AndroidUtilities.dp(6.66f));
        }
    }

    public static class PromptTone extends TL_aicompose.AiComposeTone {
        public PromptTone() {
        }
    }

    public static final class Tabs extends FrameLayout {
        private AnimatedFloat animatedSelectedTab;
        private final int currentAccount;
        private boolean divider;
        private final LinearLayout layout;
        private Utilities.CallbackReturn<Tab, Boolean> onLongClick;
        private final Theme.ResourcesProvider resourcesProvider;
        private int roundRadiusDp;
        private final FrameLayout scrollView;
        private int selectedTab;

        /* renamed from: org.telegram.ui.Components.AIEditorAlert$Tabs$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            private final RectF ceilRect;
            private final RectF floorRect;
            private final RectF rect;
            private final Paint selectorPaint;
            final /* synthetic */ Tabs this$0;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass1(Tabs r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$0 = r1;
                this.val$resourcesProvider = r3;
                super(r2);
                this.floorRect = new RectF();
                this.ceilRect = new RectF();
                this.rect = new RectF();
                this.selectorPaint = new Paint(1);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r10) {
                if (Tabs.m8692$$Nest$fgetanimatedSelectedTab(this.this$0) != null) goto L5;
                float r0 = 0.0f;
            L6:
                double r2 = r0;
                int r4 = (int) Math.floor(r2);
                int r22 = (int) Math.ceil(r2);
                float r3 = r0 - r4;
                if (r4 >= 0) goto L9;
            L11:
                if (r22 >= 0) goto L13;
            L15:
                AndroidUtilities.lerp(this.floorRect, this.ceilRect, r3, this.rect);
                this.selectorPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_featuredStickers_addButton, this.val$resourcesProvider), 0.1f));
                r10.drawRoundRect(this.rect, AndroidUtilities.dp(Tabs.m8693$$Nest$fgetroundRadiusDp(this.this$0)), AndroidUtilities.dp(Tabs.m8693$$Nest$fgetroundRadiusDp(this.this$0)), this.selectorPaint);
                int r32 = 0;
            L17:
                if (r32 >= getChildCount()) goto L22;
                View r42 = getChildAt(r32);
                if ((r42 instanceof Tab) == false) goto L21;
                ((Tab) r42).updateSelected(Math.max(0.0f, 1.0f - Math.abs(r32 - r0)), false);
            L21:
                r32 = r32 + 1;
                goto L17
            L22:
                super.dispatchDraw(r10);
                return;
            L13:
                if (r22 >= getChildCount()) goto L15;
                View r23 = getChildAt(r22);
                this.ceilRect.set(r23.getLeft(), r23.getTop(), r23.getRight(), r23.getBottom());
                goto L15
            L9:
                if (r4 >= getChildCount()) goto L11;
                View r43 = getChildAt(r4);
                this.floorRect.set(r43.getLeft(), r43.getTop(), r43.getRight(), r43.getBottom());
                goto L11
            L5:
                r0 = Tabs.m8692$$Nest$fgetanimatedSelectedTab(this.this$0).set(Tabs.m8694$$Nest$fgetselectedTab(this.this$0));
                goto L6
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onMeasure(int r11, int r12) {
                boolean r1 = true;
                if (getOrientation() != 0) goto L5;
                boolean r0 = true;
            L6:
                if (r0 == false) goto L8;
                int r3 = View.MeasureSpec.getSize(r11);
            L9:
                int r4 = 0;
                int r5 = 0;
                int r6 = 0;
            L11:
                if (r4 >= getChildCount()) goto L24;
                View r7 = getChildAt(r4);
                r7.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
                if (r0 == false) goto L15;
                int r8 = View.MeasureSpec.makeMeasureSpec(0, 0);
            L16:
                if (r0 == true) goto L18;
                int r9 = View.MeasureSpec.makeMeasureSpec(0, 0);
            L19:
                r7.measure(r8, r9);
                if (r0 == false) goto L22;
                int r72 = r7.getMeasuredWidth();
            L23:
                r6 = Math.max(r6, r72);
                r5 = r5 + r72;
                r4 = r4 + 1;
                goto L11
            L22:
                r72 = r7.getMeasuredHeight();
                goto L23
            L18:
                r9 = r12;
                goto L19
            L15:
                r8 = r11;
                goto L16
            L24:
                if (r5 <= r3) goto L26;
            L28:
                r1 = false;
            L29:
                int r32 = 0;
            L31:
                if (r32 >= getChildCount()) goto L44;
                View r42 = getChildAt(r32);
                LinearLayout.LayoutParams r52 = (LinearLayout.LayoutParams) r42.getLayoutParams();
                r42.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
                if (r1 == false) goto L39;
                if (r0 == false) goto L36;
                r52.width = 0;
            L37:
                r52.weight = 1.0f;
            L43:
                r32 = r32 + 1;
                goto L31
            L36:
                r52.height = 0;
                goto L37
            L39:
                if (r0 == false) goto L41;
                r52.width = -2;
            L42:
                r52.weight = 0.0f;
                goto L43
            L41:
                r52.height = -2;
                goto L42
            L44:
                super.onMeasure(r11, r12);
                return;
            L26:
                if (r6 >= (r3 / getChildCount())) goto L28;
            L8:
                r3 = View.MeasureSpec.getSize(r12);
                goto L9
            L5:
                r0 = false;
                goto L6
            }
        }

        public static final class Tab extends FrameLayout implements Theme.Colorable {
            public boolean accent;
            private final int currentAccount;
            private final BackupImageView imageView;
            private boolean isEmoji;
            public final LinearLayout layout;
            private final Theme.ResourcesProvider resourcesProvider;
            private int roundRadiusDp;
            private float selected;
            private final TextView textView;
            public TL_aicompose.AiComposeTone tone;

            public static /* synthetic */ void $r8$lambda$VUYK_0QLJxz7vC050ddoBsJdB9E(Tab r0, String r1, TLRPC.TL_messages_stickerSet r2) {
                r0.lambda$set$0(r1, r2);
            }

            /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
            public static /* bridge */ /* synthetic */ BackupImageView m8695$$Nest$fgetimageView(Tab r0) {
                return r0.imageView;
            }

            public Tab(Context r9, int r10, Theme.ResourcesProvider r11) {
                super(r9);
                this.accent = true;
                this.currentAccount = r10;
                this.resourcesProvider = r11;
                LinearLayout r102 = new LinearLayout(r9);
                this.layout = r102;
                r102.setClipToPadding(false);
                r102.setOrientation(1);
                addView(r102, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 2.0f, 0.0f, 2.0f));
                BackupImageView r112 = new BackupImageView(r9);
                this.imageView = r112;
                NotificationCenter.listenEmojiLoading(r112);
                r102.addView(r112, LayoutHelper.createLinear(24, 24, 49, 0, 4, 0, 0));
                TextView r113 = new TextView(r9);
                this.textView = r113;
                r113.setTypeface(AndroidUtilities.bold());
                r113.setTextSize(1, 12.0f);
                r113.setGravity(17);
                r113.setSingleLine();
                r102.addView(r113, LayoutHelper.createLinear(-2, -2, 49, 0, 2, 0, 0));
                ScaleStateListAnimator.apply(this, 0.05f, 1.5f);
                updateSelected(0.0f, true);
            }

            private /* synthetic */ void lambda$set$0(String r10, TLRPC.TL_messages_stickerSet r11) {
                if (r11 != null) goto L4;
                return;
            L4:
                if (r11.set == null) goto L32;
                String r4 = r10.replace("️", _UrlKt.FRAGMENT_ENCODE_SET);
                int r5 = 0;
                int r6 = 0;
            L7:
                TLRPC.Document r8 = null;
                if (r6 >= r11.packs.size()) goto L21;
                if (r11.packs.get(r6).documents.isEmpty() == true) goto L20;
                if (TextUtils.equals(r11.packs.get(r6).emoticon.replace("️", _UrlKt.FRAGMENT_ENCODE_SET), r4) == false) goto L20;
                long r1 = r11.packs.get(r6).documents.get(0).longValue();
            L15:
                if (r5 >= r11.documents.size()) goto L21;
                if (r11.documents.get(r5).id == r1) goto L18;
                r5 = r5 + 1;
                goto L15
            L18:
                r8 = r11.documents.get(r5);
            L20:
                r6 = r6 + 1;
            L21:
                if (r8 == null) goto L31;
                this.imageView.setImage(ImageLocation.getForDocument(r8), "24_24", ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r8.thumbs, 24), r8), "24_24", Emoji.getEmojiDrawable(r10), null);
                return;
            L31:
                return;
            }

            @Override // org.telegram.ui.ActionBar.Theme.Colorable
            public /* bridge */ /* synthetic */ int[] getColorKeys() {
                return super.getColorKeys();
            }

            public void set(int r2, CharSequence r3) {
                this.isEmoji = false;
                this.imageView.setImageResource(r2);
                this.textView.setText(r3);
            }

            public void set(String r5, CharSequence r6, Long r7) {
                this.isEmoji = true;
                this.imageView.setColorFilter(null);
                this.imageView.setImageDrawable(Emoji.getEmojiDrawable(r5));
                this.textView.setText(r6);
                int r62 = this.currentAccount;
                if (ConnectionsManager.getInstance(r62).isTestBackend() == false) goto L13;
                int r0 = 0;
            L6:
                if (r0 >= 16) goto L13;
                if (UserConfig.getInstance(r0).isClientActivated() == false) goto L12;
                if (ConnectionsManager.getInstance(r0).isTestBackend() == true) goto L12;
                r62 = r0;
            L12:
                r0 = r0 + 1;
            L13:
                if (r7 == null) goto L17;
                this.imageView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(9, this.currentAccount, r7.longValue()));
                return;
            L17:
                if (TextUtils.isEmpty(r5) == true) goto L24;
                TLRPC.TL_inputStickerSetShortName r72 = new TLRPC.TL_inputStickerSetShortName();
                r72.short_name = "RestrictedEmoji";
                MediaDataController.getInstance(r62).getStickerSet(r72, null, false, new AIEditorAlert$Tabs$Tab$$ExternalSyntheticLambda0(this, r5));
                return;
            }

            public Tab setRoundRadius(int r1) {
                this.roundRadiusDp = r1;
                updateColors();
                return this;
            }

            @Override // org.telegram.ui.ActionBar.Theme.Colorable
            public void updateColors() {
                updateSelected(this.selected, true);
                if (this.accent == false) goto L5;
                int r0 = Theme.multAlpha(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), 0.1f);
            L6:
                int r1 = this.roundRadiusDp;
                setBackground(Theme.createRadSelectorDrawable(r0, r1, r1));
                return;
            L5:
                r0 = Theme.getColor(Theme.key_listSelector, this.resourcesProvider);
                goto L6
            }

            public void updateSelected(float r4, boolean r5) {
                if (r5 == false) goto L4;
            L6:
                this.selected = r4;
                int r52 = Theme.key_windowBackgroundWhiteBlackText;
                int r0 = Theme.getColor(r52, this.resourcesProvider);
                int r1 = Theme.key_featuredStickers_addButton;
                int r02 = ColorUtils.blendARGB(r0, Theme.getColor(r1, this.resourcesProvider), r4);
                int r42 = ColorUtils.blendARGB(Theme.getColor(r52, this.resourcesProvider), Theme.getColor(r1, this.resourcesProvider), r4);
                BackupImageView r53 = this.imageView;
                if (this.isEmoji == true) goto L9;
                PorterDuffColorFilter r12 = new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN);
            L10:
                r53.setColorFilter(r12);
                this.imageView.setEmojiColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
                this.imageView.invalidate();
                this.textView.setTextColor(r42);
                return;
            L9:
                r12 = null;
                goto L10
            L4:
                if (Math.abs(r4 - this.selected) >= 0.01f) goto L6;
            }
        }

        public static /* synthetic */ boolean $r8$lambda$21TOUn4Njzw3uoc0TQjoCr9Me8g(Tabs r0, Tab r1, View r2) {
            return r0.lambda$addTab$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$6uA4f0ywxY2HiAjUewcvRpB2dCE(Utilities.Callback r0, TL_aicompose.AiComposeTone r1, View r2) {
            r0.run(r1);
        }

        public static /* synthetic */ void $r8$lambda$_O04gmi0fenqyowLqXRHMisLz5g(Utilities.Callback r0, int r1, View r2) {
            r0.run(Integer.valueOf(r1));
        }

        /* renamed from: -$$Nest$fgetanimatedSelectedTab, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedFloat m8692$$Nest$fgetanimatedSelectedTab(Tabs r0) {
            return r0.animatedSelectedTab;
        }

        /* renamed from: -$$Nest$fgetroundRadiusDp, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8693$$Nest$fgetroundRadiusDp(Tabs r0) {
            return r0.roundRadiusDp;
        }

        /* renamed from: -$$Nest$fgetselectedTab, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8694$$Nest$fgetselectedTab(Tabs r0) {
            return r0.selectedTab;
        }

        public Tabs(Context r8, int r9, int r10, boolean r11, Theme.ResourcesProvider r12) {
            super(r8);
            this.currentAccount = r9;
            this.resourcesProvider = r12;
            AnonymousClass1 r1 = new AnonymousClass1(this, r8, r12);
            this.layout = r1;
            r1.setOrientation(r10);
            this.animatedSelectedTab = new AnimatedFloat(r1, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            if (r11 == true) goto L5;
            this.scrollView = null;
            addView(r1, LayoutHelper.createFrame(-1, -1, 119));
            return;
        L5:
            if (r10 != 0) goto L7;
            HorizontalScrollView r102 = new HorizontalScrollView(r8);
            this.scrollView = r102;
            r102.setFillViewport(true);
        L8:
            this.scrollView.addView(r1);
            addView(this.scrollView, LayoutHelper.createFrame(-1, -1, 119));
            return;
        L7:
            ScrollView r103 = new ScrollView(r8);
            this.scrollView = r103;
            r103.setFillViewport(true);
            goto L8
        }

        public Tabs(Context r7, int r8, boolean r9, Theme.ResourcesProvider r10) {
            this(r7, r8, 0, r9, r10);
        }

        private /* synthetic */ boolean lambda$addTab$3(Tab r1, View r2) {
            Utilities.CallbackReturn<Tab, Boolean> r0 = this.onLongClick;
            if (r0 != null) goto L5;
            return false;
        L5:
            return r0.run(r1).booleanValue();
        }

        public Tab addTab(int r6, CharSequence r7, Utilities.Callback<Integer> r8) {
            int r0 = this.layout.getChildCount();
            Tab r1 = new Tab(getContext(), this.currentAccount, this.resourcesProvider);
            r1.setRoundRadius(this.roundRadiusDp);
            r1.set(r6, r7);
            r1.setOnClickListener(new AIEditorAlert$Tabs$$ExternalSyntheticLambda2(r8, r0));
            this.layout.addView(r1, LayoutHelper.createLinear(0, -1, 1.0f, 119));
            return r1;
        }

        public Tab addTab(TL_aicompose.AiComposeTone r6, Utilities.Callback<TL_aicompose.AiComposeTone> r7) {
            Tab r0 = new Tab(getContext(), this.currentAccount, this.resourcesProvider);
            r0.tone = r6;
            r0.setRoundRadius(this.roundRadiusDp);
            int r1 = 0;
            if (r6 != null) goto L6;
            r0.accent = false;
            r0.updateColors();
            r0.set(R.drawable.tone_create, LocaleController.getString(R.string.AIEditorStyleNewCreate));
        L9:
            r0.setOnClickListener(new AIEditorAlert$Tabs$$ExternalSyntheticLambda0(r7, r6));
            r0.setOnLongClickListener(new AIEditorAlert$Tabs$$ExternalSyntheticLambda1(this, r0));
            LinearLayout r62 = this.layout;
            if (r62.getOrientation() != 0) goto L12;
            int r72 = 0;
        L14:
            if (this.layout.getOrientation() == 1) goto L17;
            r1 = -1;
        L17:
            r62.addView(r0, LayoutHelper.createLinear(r72, r1, 1.0f, 119));
            return r0;
        L12:
            r72 = -1;
            goto L14
        L6:
            if ((r6 instanceof PromptTone) == false) goto L8;
            r0.accent = false;
            r0.updateColors();
            r0.set(R.drawable.iv_prompt, LocaleController.getString(R.string.AIEditorStylePrompt));
            goto L9
        L8:
            r0.set(null, r6.title, Long.valueOf(r6.emoji_id));
            goto L9
        }

        public void clearTabs() {
            this.layout.removeAllViews();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.divider == false) goto L9;
            Paint r0 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r0 != null) goto L7;
            r0 = Theme.dividerPaint;
        L7:
            r8.drawRect(AndroidUtilities.dp(10.0f), getHeight() - 1, getWidth() - AndroidUtilities.dp(10.0f), getHeight(), r0);
            return;
        }

        public int getSelectedTab() {
            return this.selectedTab;
        }

        public TL_aicompose.AiComposeTone getSelectedTone() {
            int r0 = this.selectedTab;
            if (r0 >= 0) goto L5;
        L12:
            return null;
        L5:
            if (r0 >= this.layout.getChildCount()) goto L12;
            View r3 = this.layout.getChildAt(this.selectedTab);
            if ((r3 instanceof Tab) == true) goto L11;
            return null;
        L11:
            return ((Tab) r3).tone;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void selectTab(int r2) {
            selectTab(r2, true);
        }

        public void selectTab(int r2, boolean r3) {
            if (this.selectedTab != r2) goto L5;
            return;
        L5:
            this.selectedTab = r2;
            if (r3 == true) goto L8;
            this.animatedSelectedTab.force(r2);
        L8:
            if (r2 >= 0) goto L10;
        L19:
            this.layout.invalidate();
            return;
        L10:
            if (r2 >= this.layout.getChildCount()) goto L19;
            View r22 = this.layout.getChildAt(r2);
            if ((r22 instanceof Tab) == false) goto L19;
            Tab r23 = (Tab) r22;
            if (Tab.m8695$$Nest$fgetimageView(r23).getAnimatedEmojiDrawable() == null) goto L18;
            AnimatedEmojiDrawable r24 = Tab.m8695$$Nest$fgetimageView(r23).getAnimatedEmojiDrawable();
            if (r24.getImageReceiver() == null) goto L19;
            r24.getImageReceiver().startAnimation();
            goto L19
        L18:
            Tab.m8695$$Nest$fgetimageView(r23).getImageReceiver().startAnimation();
            goto L19
        }

        public void selectTone(TL_aicompose.AiComposeTone r2) {
            selectTone(r2, true);
        }

        public void selectTone(TL_aicompose.AiComposeTone r4, boolean r5) {
            int r0 = 0;
        L4:
            if (r0 >= this.layout.getChildCount()) goto L13;
            View r1 = this.layout.getChildAt(r0);
            if ((r1 instanceof Tab) == false) goto L12;
            TL_aicompose.AiComposeTone r12 = ((Tab) r1).tone;
            if (r12 == null) goto L12;
            if (r12 != r4) goto L12;
            selectTab(r0, r5);
            return;
        L12:
            r0 = r0 + 1;
            goto L4
        }

        public void setDivider(boolean r1) {
            this.divider = r1;
        }

        public Tabs setOnItemLongClick(Utilities.CallbackReturn<Tab, Boolean> r1) {
            this.onLongClick = r1;
            return this;
        }

        @Override // android.view.View
        public void setPadding(int r1, int r2, int r3, int r4) {
            this.layout.setPadding(r1, r2, r3, r4);
        }

        public void setRoundRadius(int r1) {
            this.roundRadiusDp = r1;
        }
    }

    /* renamed from: $r8$lambda$-RTRdOS9TSrTl3rzBDYKA-p_geo, reason: not valid java name */
    public static /* synthetic */ void m8659$r8$lambda$RTRdOS9TSrTl3rzBDYKAp_geo(AIEditorAlert r0, View r1) {
        r0.collapse(r1);
    }

    /* renamed from: $r8$lambda$-SfsaydocVa3Q7RLBW8K9CIMPjY, reason: not valid java name */
    public static /* synthetic */ void m8660$r8$lambda$SfsaydocVa3Q7RLBW8K9CIMPjY(AIEditorAlert r0, View r1) {
        r0.lambda$new$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$29i_UsjKc_3nsAQGHw1OaJ_CYTU(AIEditorAlert r0, int r1) {
        r0.lambda$onTabLongClick$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$2chklieGNGsgpKjtJmbl8krNwKk(AIEditorAlert r0, String r1) {
        r0.lambda$onToLangMenu$33(r1);
    }

    public static /* synthetic */ void $r8$lambda$3ALjk6pwRcPoEnSva4DUwvNw1Rc(AIEditorAlert r0, TL_aicompose.TL_aiComposeTone r1, Context r2, Theme.ResourcesProvider r3) {
        r0.lambda$new$3(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$3PkS4W8-tLDxVilIRetp42sV2JY, reason: not valid java name */
    public static /* synthetic */ void m8661$r8$lambda$3PkS4W8tLDxVilIRetp42sV2JY(AIEditorAlert r0, Context r1, Theme.ResourcesProvider r2) {
        r0.lambda$new$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3Wa1YoqvaPJFylVAuRS02K3bhu8(AIEditorAlert r0) {
        r0.lambda$onToLangMenu$34();
    }

    public static /* synthetic */ void $r8$lambda$7VMF8KwKBZbxt8z9Uv4YXbtRQQo(AIEditorAlert r0, TL_aicompose.AiComposeTone r1) {
        r0.lambda$selectStyle$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$8N96IIvnPRI0pjPtnYdUmU_9G68(AIEditorAlert r0, View r1) {
        r0.onToLangMenu(r1);
    }

    public static /* synthetic */ void $r8$lambda$9Qons9AHHVwtDV20_bOY80G61YY(AIEditorAlert r0, String r1) {
        r0.lambda$setText$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$DtdOrXOEeAoIJsh912cSoZuQ2Ls(BulletinFactory r4) {
        new PremiumFeatureBottomSheet(r4.getContext(), 42, true, r4.getResourcesProvider()).show();
    }

    /* renamed from: $r8$lambda$EIct3HD58e-i2Y76xIE4zvRdmhI, reason: not valid java name */
    public static /* synthetic */ void m8662$r8$lambda$EIct3HD58ei2Y76xIE4zvRdmhI(AIEditorAlert r0, View r1) {
        r0.lambda$updateButton$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$Eam3AhAhWxcue2kI1HGwudx8pl0(AIEditorAlert r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FumP5kjUbbNwGzf4fkQqmQw2BzI(AIEditorAlert r0, Browser.Progress r1, TL_aicompose.TL_aiComposeTone r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
        r0.lambda$new$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$HAnm3F_hGtIFnConiWrX2pXP5cw(AIEditorAlert r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$HTfHzDmT0dyTrwboeNH1gbHEPmc(Utilities.Callback2 r1, TLRPC.TL_composedMessageWithAI r2) {
        r1.run(r2, null);
    }

    /* renamed from: $r8$lambda$HX8pc0cT-Pr2U4ACkxGupvnzIvY, reason: not valid java name */
    public static /* synthetic */ void m8663$r8$lambda$HX8pc0cTPr2U4ACkxGupvnzIvY(AIEditorAlert r0, View r1, int r2) {
        r0.lambda$new$15(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$I1cs8GCJUwVSA76tSOVUdNhutxk(AIEditorAlert r0, Theme.ResourcesProvider r1, Context r2, View r3) {
        return r0.lambda$new$13(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Icush4y2xCBcpXB4-fp1S1yIR84, reason: not valid java name */
    public static /* synthetic */ void m8664$r8$lambda$Icush4y2xCBcpXB4fp1S1yIR84(AIEditorAlert r0, TL_aicompose.TL_aiComposeTone r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$J6ZhU7IW7WRp7NmSg4PQ7Yhfvos(AIEditorAlert r0, int r1, View r2) {
        return r0.lambda$addMainTab$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Jj3XwbUE7p2mxWlDfIT13bFcuUQ(AIEditorAlert r0, Utilities.Callback2 r1, Integer r2, String r3) {
        r0.lambda$request$39(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Lg--9guD-yRa3p30V1FBuXMWlbs, reason: not valid java name */
    public static /* synthetic */ void m8665$r8$lambda$Lg9guDyRa3p30V1FBuXMWlbs(AIEditorAlert r0) {
        r0.lambda$updateButton$22();
    }

    public static /* synthetic */ void $r8$lambda$M0BoiwhoJ0E0aRHf6phEkRJi3Io(AIEditorAlert r0, TL_aicompose.TL_aiComposeTone r1, AlertDialog r2, int r3) {
        r0.lambda$new$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MbxsLFSuOuyl2glum4l0IlhlynQ(AIEditorAlert r0, Theme.ResourcesProvider r1, TL_aicompose.TL_aiComposeTone r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$O2mH_eidKnbeZG0-PO4xLUCbAiM, reason: not valid java name */
    public static /* synthetic */ Boolean m8666$r8$lambda$O2mH_eidKnbeZG0PO4xLUCbAiM(AIEditorAlert r0, Theme.ResourcesProvider r1, Context r2, Tabs.Tab r3) {
        return r0.lambda$new$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$OmLDJCuZNOgvloiirlq-PJlZILs, reason: not valid java name */
    public static /* synthetic */ void m8667$r8$lambda$OmLDJCuZNOgvloiirlqPJlZILs(AIEditorAlert r0, int r1) {
        r0.selectTab(r1);
    }

    public static /* synthetic */ void $r8$lambda$OvFV16IYRif0ee2MqFtw76CzHCk(Exception r0) {
        FileLog.e(r0);
    }

    /* renamed from: $r8$lambda$SBVxoyW1frojq-g8mB7MWys__kA, reason: not valid java name */
    public static /* synthetic */ void m8668$r8$lambda$SBVxoyW1frojqg8mB7MWys__kA(AIEditorAlert r0) {
        r0.lambda$selectStyle$27();
    }

    /* renamed from: $r8$lambda$SrbeFlF-HWL25CMiwzPCB9vDeF4, reason: not valid java name */
    public static /* synthetic */ void m8669$r8$lambda$SrbeFlFHWL25CMiwzPCB9vDeF4(AIEditorAlert r0, View r1) {
        r0.lambda$updateButton$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$UviBjNB1ZGE5g6cXZOpsXnqoBIo(AIEditorAlert r0, TranslateController.Language r1) {
        r0.lambda$onToLangMenu$35(r1);
    }

    /* renamed from: $r8$lambda$WNr863j-eCQjbu8TVl7Hqaa0BMY, reason: not valid java name */
    public static /* synthetic */ void m8670$r8$lambda$WNr863jeCQjbu8TVl7Hqaa0BMY(AIEditorAlert r0) {
        r0.lambda$onTabLongClick$25();
    }

    public static /* synthetic */ void $r8$lambda$YFm89Nepx_TgHM81V7Qh_Isx_6k(Exception r0) {
        FileLog.e(r0);
    }

    /* renamed from: $r8$lambda$Z_VS5g_URxl_G4nYiBKp-mwJwT0, reason: not valid java name */
    public static /* synthetic */ void m8671$r8$lambda$Z_VS5g_URxl_G4nYiBKpmwJwT0(AIEditorAlert r0, String r1) {
        r0.lambda$setText$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$_i2vGZ1gDMtK2GcN4_F41aKaKzM(AIEditorAlert r0, View r1) {
        r0.lambda$updateButton$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$axS5zEgWSySU0tICbp0uZQB57iY(AIEditorAlert r0) {
        r0.lambda$updateButton$21();
    }

    public static /* synthetic */ void $r8$lambda$bTUgb9omjHsvb4hghIx_g46A8uk(AIEditorAlert r0, View r1) {
        r0.lambda$updateButton$19(r1);
    }

    /* renamed from: $r8$lambda$eg-YnVZA2dzHgysOo9zH0n_VKrc, reason: not valid java name */
    public static /* synthetic */ void m8672$r8$lambda$egYnVZA2dzHgysOo9zH0n_VKrc(AIEditorAlert r0, Theme.ResourcesProvider r1, View r2) {
        r0.lambda$new$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fK5WDCBcluu1Ma28u89yKxrH9I4(AIEditorAlert r0, SimpleTextView r1, int r2, TLRPC.TL_messages_composeRichMessageWithAI r3, TLRPC.TL_composedRichMessageWithAI r4, TLRPC.TL_error r5) {
        r0.lambda$requestRich$40(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hL9Ihujg2ElScJ3DSpdGaIiPreM(AIEditorAlert r0, SimpleTextView r1, int r2, TLRPC.TL_messages_composeMessageWithAI r3, TLRPC.TL_composedMessageWithAI r4, TLRPC.TL_error r5) {
        r0.lambda$request$37(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ic6YnV5mlBnVTxaU79Z6EbMOv3k(AIEditorAlert r0) {
        r0.showStyleHint();
    }

    /* renamed from: $r8$lambda$ixZ5-XVkDAI7nvBAkrnuYCc7EoE, reason: not valid java name */
    public static /* synthetic */ void m8673$r8$lambda$ixZ5XVkDAI7nvBAkrnuYCc7EoE(AIEditorAlert r0, Theme.ResourcesProvider r1, TL_aicompose.TL_aiComposeTone r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* renamed from: $r8$lambda$kySe79Kq4S-eMJxkSz94Ab_TFeo, reason: not valid java name */
    public static /* synthetic */ void m8674$r8$lambda$kySe79Kq4SeMJxkSz94Ab_TFeo(ItemOptions r0, boolean r1, Runnable r2, View r3) {
        r0.dismiss();
        if (r1 == true) goto L7;
        if (r2 == null) goto L8;
        r2.run();
        return;
    L8:
        return;
    }

    public static /* synthetic */ void $r8$lambda$mJL6MoowTySQfg5vWCUyRXLe1fE(AIEditorAlert r0, View r1) {
        r0.lambda$updateButton$16(r1);
    }

    /* renamed from: $r8$lambda$nqP_d-owvE4NZoPZVfY1Al4iHBA, reason: not valid java name */
    public static /* synthetic */ void m8675$r8$lambda$nqP_dowvE4NZoPZVfY1Al4iHBA(AIEditorAlert r0, View r1) {
        r0.toggleEmojify(r1);
    }

    public static /* synthetic */ void $r8$lambda$tCHn3YJx2sGwTTY1Xy5rRLk7a7I(AIEditorAlert r0) {
        r0.lambda$new$10();
    }

    public static /* synthetic */ void $r8$lambda$vPdszcyOunMmZhaU0ra2a31p4JU(AIEditorAlert r0) {
        r0.lambda$new$12();
    }

    public static /* synthetic */ void $r8$lambda$xNWun4PyWwf49aYzO1ISCHwEV0I(AIEditorAlert r0, TL_aicompose.AiComposeTone r1) {
        r0.selectStyle(r1);
    }

    public static /* synthetic */ void $r8$lambda$zOdzLPvlvvpdvzWvMz0O4z6F2o8(AIEditorAlert r0, TL_aicompose.AiComposeTone r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$zo6j810m5DNYClOtrL5aR0zRZzA(AIEditorAlert r0, View r1) {
        r0.copyResult(r1);
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m8676$$Nest$fgetbulletinContainer(AIEditorAlert r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetstyleTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Tabs m8677$$Nest$fgetstyleTabs(AIEditorAlert r0) {
        return r0.styleTabs;
    }

    /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Tabs m8678$$Nest$fgettabs(AIEditorAlert r0) {
        return r0.tabs;
    }

    /* renamed from: -$$Nest$mcancelRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8679$$Nest$mcancelRequest(AIEditorAlert r0) {
        r0.cancelRequest();
    }

    /* renamed from: -$$Nest$mrunSend, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8680$$Nest$mrunSend(AIEditorAlert r0, int r1, int r2, boolean r3) {
        r0.runSend(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8681$$Nest$mupdateButton(AIEditorAlert r0) {
        r0.updateButton();
    }

    /* renamed from: -$$Nest$mupdatePromptEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8682$$Nest$mupdatePromptEditText(AIEditorAlert r0) {
        r0.updatePromptEditText();
    }

    /* renamed from: -$$Nest$mupdateStyleHintY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8683$$Nest$mupdateStyleHintY(AIEditorAlert r0) {
        r0.updateStyleHintY();
    }

    public AIEditorAlert(Context r21, Theme.ResourcesProvider r22) {
        super(r21, null, true, false, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r22);
        this.accusative = new boolean[1];
        this.genitive = new boolean[1];
        this.collapsed = true;
        this.requestId = -1;
        this.lastRequest = new TLRPC.TL_messages_composeMessageWithAI[3];
        this.lastRequestRich = new TLRPC.TL_messages_composeRichMessageWithAI[3];
        int r10 = Theme.key_windowBackgroundGray;
        fixNavigationBar(getThemedColor(r10));
        AiTonesController r0 = MessagesController.getInstance(this.currentAccount).getTonesController();
        this.tonesController = r0;
        r0.load();
        r0.open = true;
        ImageView r02 = new ImageView(r21);
        this.closeView = r02;
        r02.setScaleType(ImageView.ScaleType.CENTER);
        r02.setImageResource(R.drawable.ic_close_white);
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setColorFilter(getThemedColor(r2));
        r02.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(getThemedColor(r2), 0.1f)));
        this.actionBar.addView(r02, LayoutHelper.createFrame(54, 54.0f, 85, 0.0f, 0.0f, 8.0f, 0.0f));
        ScaleStateListAnimator.apply(r02, 0.1f, 1.5f);
        r02.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda4(this));
        FrameLayout r03 = new FrameLayout(r21);
        this.tabsContainer = r03;
        Tabs r23 = new Tabs(r21, this.currentAccount, false, r22);
        this.tabs = r23;
        r23.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        int r3 = AndroidUtilities.dp(28.0f);
        int r12 = Theme.key_windowBackgroundWhite;
        r23.setBackground(Theme.createRoundRectDrawable(r3, Theme.getColor(r12, r22)));
        r23.setRoundRadius(28);
        addMainTab(R.drawable.outline_ai_translate2, LocaleController.getString(R.string.AIEditorTabTranslate), 0);
        addMainTab(R.drawable.menu_rewrite, LocaleController.getString(R.string.AIEditorTabStyle), 1);
        addMainTab(R.drawable.menu_proofread, LocaleController.getString(R.string.AIEditorTabFix), 2);
        r23.selectTab(getDefaultTab());
        r03.addView(r23, LayoutHelper.createFrame(-1, -1.0f, 119, 12.0f, 0.0f, 12.0f, 0.0f));
        FrameLayout r13 = new FrameLayout(r21);
        this.promptBox = r13;
        EditTextCell r04 = new EditTextCell(r21, LocaleController.getString(R.string.ArticleAIPrompt), true, false, MessagesController.getInstance(this.currentAccount).config.aicomposeTonePromptLengthMax.get(), r22);
        this.promptCell = r04;
        r04.editText.setImeOptions(6);
        r04.editText.setMaxLines(5);
        r04.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), Theme.getColor(r12, r22)));
        r04.editText.addTextChangedListener(new AnonymousClass1(this));
        r13.addView(r04, LayoutHelper.createFrame(-1, -2.0f));
        r13.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
        r04.editText.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(53.0f), AndroidUtilities.dp(15.0f));
        Tabs r05 = new Tabs(r21, this.currentAccount, true, r22);
        this.styleTabs = r05;
        r05.setDivider(true);
        r05.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        r05.setRoundRadius(12);
        r05.setOnItemLongClick(new AIEditorAlert$$ExternalSyntheticLambda5(this, r22, r21));
        updateStyles();
        r05.selectTab(-1);
        String r06 = TranslatorUtils.getResolvedSendTargetLanguageCode();
        this.to_lang = r06;
        if (r06 != null) goto L5;
        this.to_lang = TranslateController.currentLanguage();
    L5:
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(12.0f);
        this.topPadding = 0.35f;
        setBackgroundColor(getThemedColor(r10));
        LinearLayout r32 = new LinearLayout(r21);
        this.buttonContainer = r32;
        r32.setOrientation(0);
        r32.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        r32.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(getThemedColor(r10), 0.0f), getThemedColor(r10), getThemedColor(r10)}));
        ButtonWithCounterView r07 = new ButtonWithCounterView(r21, r22).setRound();
        this.button = r07;
        r32.addView(r07, LayoutHelper.createLinear(-1, 48, 1.0f, 119));
        ButtonWithCounterView r08 = new ButtonWithCounterView(r21, r22).setRound();
        this.sendButton = r08;
        r08.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda6(this));
        r08.setOnLongClickListener(new AIEditorAlert$$ExternalSyntheticLambda7(this, r22, r21));
        r32.addView(r08, LayoutHelper.createLinear(48, 48, 5, 10, 0, 0, 0));
        ButtonWithCounterView r09 = new ButtonWithCounterView(r21, r22).setRound();
        this.allButton = r09;
        SpannableStringBuilder r24 = new SpannableStringBuilder(LocaleController.getString(R.string.AIEditorLimitButton));
        r24.append(" ");
        int r4 = r24.length();
        r24.append("x50");
        r24.setSpan(new LimitSpan(this, "x50"), r4, r24.length(), 33);
        r09.setText(r24);
        r09.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda8(this, r22));
        FrameLayout.LayoutParams r25 = LayoutHelper.createFrame(-1, -2, 80);
        int r42 = r25.leftMargin;
        int r5 = this.backgroundPaddingLeft;
        r25.leftMargin = r42 + r5;
        r25.rightMargin += r5;
        this.containerView.addView(r32, r25);
        FrameLayout.LayoutParams r26 = LayoutHelper.createFrame(-1, 48.0f, 80, 12.0f, 6.0f, 12.0f, 12.0f);
        int r33 = r26.leftMargin;
        int r43 = this.backgroundPaddingLeft;
        r26.leftMargin = r33 + r43;
        r26.rightMargin += r43;
        this.containerView.addView(r09, r26);
        FrameLayout r010 = new FrameLayout(r21);
        this.bulletinContainer = r010;
        FrameLayout.LayoutParams r1 = LayoutHelper.createFrame(-1, 200.0f, 80, 0.0f, 0.0f, 0.0f, 60.0f);
        int r27 = r1.leftMargin;
        int r34 = this.backgroundPaddingLeft;
        r1.leftMargin = r27 + r34;
        r1.rightMargin += r34;
        this.containerView.addView(r010, r1);
        updateButton(false);
        RecyclerListView r011 = this.recyclerListView;
        int r14 = this.backgroundPaddingLeft;
        r011.setPadding(r14, 0, r14, AndroidUtilities.dp(66.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setSegmentedSectionsEnabled(false);
        this.recyclerListView.setSections();
        this.recyclerListView.setOnItemClickListener(new AIEditorAlert$$ExternalSyntheticLambda9(this));
        this.takeTranslationIntoAccount = true;
        AnonymousClass4 r012 = new AnonymousClass4(this);
        r012.setSupportsChangeAnimations(false);
        r012.setDelayAnimations(false);
        r012.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r012.setDurations(350);
        this.recyclerListView.setItemAnimator(r012);
        this.recyclerListView.setOnScrollListener(new AnonymousClass5(this));
        this.adapter.update(false);
        AndroidUtilities.runOnUIThread(new AIEditorAlert$$ExternalSyntheticLambda10(this));
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.loadedAiComposeTones);
    }

    public static /* synthetic */ ViewGroup access$000(AIEditorAlert r0) {
        return r0.containerView;
    }

    private void addChecked(ItemOptions r9, LinearLayout r10, boolean r11, CharSequence r12, Runnable r13) {
        int r0 = Theme.key_actionBarDefaultSubmenuItem;
        int r1 = Theme.key_actionBarDefaultSubmenuItemIcon;
        ActionBarMenuSubItem r2 = new ActionBarMenuSubItem(getContext(), true, false, false, this.resourcesProvider);
        r2.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r2.setText(r12);
        r2.setChecked(r11);
        r2.setColors(Theme.getColor(r0, this.resourcesProvider), Theme.getColor(r1, this.resourcesProvider));
        r2.setSelectorColor(Theme.multAlpha(Theme.getColor(r0, this.resourcesProvider), 0.12f));
        r2.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda48(r9, r11, r13));
        r10.addView(r2, LayoutHelper.createLinear(-1, -2));
    }

    private void addMainTab(int r3, CharSequence r4, int r5) {
        this.tabs.addTab(r3, r4, new AIEditorAlert$$ExternalSyntheticLambda37(this)).setOnLongClickListener(new AIEditorAlert$$ExternalSyntheticLambda38(this, r5));
    }

    private void cancelRequest() {
        if (this.requestId < 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestId, true);
        this.requestId = -1;
    L5:
        Runnable r0 = this.aiFallbackCancel;
        if (r0 == null) goto L8;
        r0.run();
        this.aiFallbackCancel = null;
    L8:
        this.loading = false;
        SimpleTextView r3 = this.actionBar.getTitleTextView();
        if (r3 == null) goto L12;
        r3.setRightDrawable(null);
        return;
    }

    private void collapse(View r2) {
        this.collapsed = false;
        saveScrollPosition();
        this.adapter.update(true);
        applyScrolledPosition(true);
    }

    public static CharSequence copy(CharSequence r13) {
        if ((r13 instanceof Spanned) == false) goto L5;
        Spanned r0 = (Spanned) r13;
        SpannableStringBuilder r1 = new SpannableStringBuilder(r13.toString());
        Class[] r132 = {TextStyleSpan.class, CodeHighlighting.Span.class, SquigglyLinesSpan.class, URLSpanUserMention.class, URLSpanReplacement.class, URLSpanMono.class, URLSpanNoUnderline.class, FormattedDateSpan.class, URLSpanBrowser.class, URLSpanBotCommand.class, AnimatedEmojiSpan.class};
        int r3 = 0;
    L8:
        if (r3 >= 11) goto L13;
        Object[] r4 = r0.getSpans(0, r0.length(), r132[r3]);
        int r5 = r4.length;
        int r6 = 0;
    L10:
        if (r6 >= r5) goto L12;
        Object r7 = r4[r6];
        r1.setSpan(r7, r0.getSpanStart(r7), r0.getSpanEnd(r7), 33);
        r6 = r6 + 1;
        goto L10
    L12:
        r3 = r3 + 1;
        goto L8
    L13:
        return r1;
    L5:
        return r13.toString();
    }

    private void copyResult(View r1) {
        if (this.loading == false) goto L5;
        return;
    L5:
        AndroidUtilities.addToClipboard(getResultText());
    }

    private int estimateLinesCount() {
        int r0 = this.tabs.getSelectedTab();
        CharSequence r1 = this.text;
        if (r0 != 0) goto L8;
        CharSequence r2 = this.translatedText;
        if (r2 == null) goto L8;
        r1 = r2;
    L8:
        if (r0 != 1) goto L13;
        CharSequence r3 = this.styledText;
        if (r3 == null) goto L13;
        r1 = r3;
    L13:
        if (r0 != 2) goto L17;
        CharSequence r02 = this.fixedText;
        if (r02 == null) goto L17;
        CharSequence r4 = r02;
    L18:
        TextPaint r5 = new TextPaint();
        r5.setTextSize(AndroidUtilities.dp(16.0f));
        int r03 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(64.0f);
        int r11 = this.backgroundPaddingLeft;
        return MathUtils.clamp(new StaticLayout(r4, r5, (r03 - r11) - r11, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineCount(), 1, 10);
    L17:
        r4 = r1;
        goto L18
    }

    private void fillItems(ArrayList<UItem> r22, UniversalAdapter r23) {
        r22.add(UItem.asShadow(null));
        r22.add(UItem.asCustomShadow(this.tabsContainer));
        r22.add(UItem.asShadow(null));
        r23.itemsOffset = 1;
        r23.whiteSectionStart();
        Tabs r5 = this.tabs;
        if (r5 == null) goto L5;
        int r52 = r5.getSelectedTab();
    L6:
        int r10 = 7;
        if (r52 != 0) goto L63;
        String r3 = this.from_lang;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r3 != null) goto L11;
    L26:
        r22.add(TranslateAlert3.Header.Factory.of(3, LocaleController.getString(R.string.AIEditorOriginalText), null, null, null));
    L28:
        if (isRich() == false) goto L30;
        UItem r32 = previewItem(4, this.textRich, false);
    L31:
        r22.add(r32);
        String r33 = TranslateAlert2.languageName(this.to_lang, this.accusative);
        boolean[] r7 = this.accusative;
        if (r7 != null) goto L34;
    L36:
        int r72 = R.string.AIEditorToOther;
    L37:
        String r73 = LocaleController.getString(r72);
        int r53 = r73.indexOf("%s");
        if (r53 >= 0) goto L40;
        String r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r16 = r14;
    L42:
        if (TextUtils.isEmpty(r14) == false) goto L44;
        r33 = TranslateAlert2.capitalFirst(r33);
    L44:
        StringBuilder r54 = new StringBuilder();
        r54.append(r33);
        if (this.translateToneTitle == null) goto L47;
        r12 = " (" + this.translateToneTitle + ")";
    L47:
        r54.append(r12);
        r22.add(TranslateAlert3.Header.Factory.of(5, r14, r54.toString(), r16, new AIEditorAlert$$ExternalSyntheticLambda25(this), this.emojify, new AIEditorAlert$$ExternalSyntheticLambda26(this), null));
        boolean r34 = isRich();
        boolean r55 = this.translatedTextLoading;
        if (r34 == false) goto L54;
        if (r55 == true) goto L52;
        TL_iv.RichMessage r35 = this.translatedTextRich;
        if (r35 == null) goto L52;
    L53:
        UItem r0 = previewItem(6, r35, r55);
    L62:
        r22.add(r0);
    L123:
        r23.whiteSectionEnd();
        r22.add(UItem.asShadow(null));
        return;
    L52:
        r35 = this.textRich;
        goto L53
    L54:
        if (r55 == true) goto L57;
        r10 = 6;
    L57:
        CharSequence r6 = this.translatedText;
        if (r55 == true) goto L60;
        AIEditorAlert$$ExternalSyntheticLambda27 r11 = new AIEditorAlert$$ExternalSyntheticLambda27(this);
    L61:
        r0 = TranslateAlert3.Text.Factory.of(r10, r6, false, false, null, null, r11);
        goto L62
    L60:
        r11 = null;
        goto L61
    L40:
        String r62 = r73.substring(0, r53);
        r16 = r73.substring(r53 + 2);
        r14 = r62;
        goto L42
    L34:
        if (r7[0] == false) goto L36;
        r72 = R.string.AIEditorTo;
        goto L37
    L30:
        r32 = TranslateAlert3.Text.Factory.of(4, this.text, this.collapsed, false, new AIEditorAlert$$ExternalSyntheticLambda24(this), null, null);
        goto L31
    L11:
        if (r3.equalsIgnoreCase(TranslateController.UNKNOWN_LANGUAGE) == true) goto L26;
        String r36 = TranslateAlert2.languageName(this.from_lang, null, this.genitive);
        boolean[] r13 = this.genitive;
        if (r13 != null) goto L15;
    L17:
        int r132 = R.string.AIEditorFromOther;
    L18:
        String r133 = LocaleController.getString(r132);
        int r142 = r133.indexOf("%s");
        if (r142 >= 0) goto L21;
        String r134 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r15 = r134;
    L23:
        if (TextUtils.isEmpty(r15) == false) goto L25;
        r36 = TranslateAlert2.capitalFirst(r36);
    L25:
        r22.add(TranslateAlert3.Header.Factory.of(3, r15, r36, r134, null));
        goto L28
    L21:
        r15 = r133.substring(0, r142);
        r134 = r133.substring(r142 + 2);
        goto L23
    L15:
        if (r13[0] == false) goto L17;
        r132 = R.string.AIEditorFrom;
        goto L18
    L63:
        if (r52 != 1) goto L102;
        r22.add(UItem.asCustom(this.styleTabs));
        if ((this.styleTabs.getSelectedTone() instanceof PromptTone) == false) goto L67;
        r22.add(UItem.asCustom(10, this.promptBox));
        r23.whiteSectionEnd();
        r22.add(UItem.asShadow(11, null));
        r23.whiteSectionStart();
    L67:
        Tabs r37 = this.styleTabs;
        if (r37 == null) goto L74;
        if (r37.getSelectedTab() >= 0) goto L74;
        if (this.emojify == true) goto L74;
    L77:
        r22.add(TranslateAlert3.Header.Factory.of(5, LocaleController.getString(R.string.AIEditorOriginal), null, null, null, this.emojify, new AIEditorAlert$$ExternalSyntheticLambda26(this), null));
        if (isRich() == false) goto L81;
        UItem r02 = previewItem(6, this.textRich, false);
    L85:
        r22.add(r02);
        goto L123
    L81:
        if (this.styledTextLoading == false) goto L83;
        int r122 = 7;
    L84:
        r02 = TranslateAlert3.Text.Factory.of(r122, this.text, false, false, null, null, null);
        goto L85
    L83:
        r122 = 6;
    L74:
        if ((this.styleTabs.getSelectedTone() instanceof PromptTone) == true) goto L76;
    L86:
        r22.add(TranslateAlert3.Header.Factory.of(7, LocaleController.getString(R.string.AIEditorResult), null, null, null, this.emojify, new AIEditorAlert$$ExternalSyntheticLambda26(this), null));
        boolean r38 = isRich();
        boolean r56 = this.styledTextLoading;
        if (r38 == false) goto L93;
        if (r56 == true) goto L91;
        TL_iv.RichMessage r39 = this.styledTextRich;
        if (r39 == null) goto L91;
    L92:
        UItem r03 = previewItem(8, r39, r56);
    L101:
        r22.add(r03);
    L91:
        r39 = this.textRich;
        goto L92
    L93:
        if (r56 == true) goto L96;
        r10 = 6;
    L96:
        CharSequence r63 = this.styledText;
        if (r56 == true) goto L99;
        AIEditorAlert$$ExternalSyntheticLambda27 r112 = new AIEditorAlert$$ExternalSyntheticLambda27(this);
    L100:
        r03 = TranslateAlert3.Text.Factory.of(r10, r63, false, false, null, null, r112);
        goto L101
    L99:
        r112 = null;
        goto L100
    L76:
        if (TextUtils.equals(this.promptCell.getText().toString(), this.promptText) == true) goto L86;
    L102:
        if (r52 != 2) goto L123;
        r22.add(TranslateAlert3.Header.Factory.of(3, LocaleController.getString(R.string.AIEditorOriginal), null, null, null));
        if (isRich() == false) goto L106;
        UItem r310 = previewItem(4, this.textRich, false);
    L107:
        r22.add(r310);
        r22.add(TranslateAlert3.Header.Factory.of(5, LocaleController.getString(R.string.AIEditorResult), null, null, null));
        boolean r311 = isRich();
        boolean r57 = this.fixedTextLoading;
        if (r311 == false) goto L114;
        if (r57 == true) goto L112;
        TL_iv.RichMessage r312 = this.fixedTextRich;
        if (r312 == null) goto L112;
    L113:
        UItem r04 = previewItem(6, r312, r57);
    L122:
        r22.add(r04);
    L112:
        r312 = this.textRich;
        goto L113
    L114:
        if (r57 == true) goto L117;
        r10 = 6;
    L117:
        CharSequence r64 = this.fixedText;
        if (r57 == true) goto L120;
        AIEditorAlert$$ExternalSyntheticLambda27 r113 = new AIEditorAlert$$ExternalSyntheticLambda27(this);
    L121:
        r04 = TranslateAlert3.Text.Factory.of(r10, r64, false, false, null, null, r113);
        goto L122
    L120:
        r113 = null;
        goto L121
    L106:
        r310 = TranslateAlert3.Text.Factory.of(4, this.text, this.collapsed, false, new AIEditorAlert$$ExternalSyntheticLambda24(this), null, null);
        goto L107
    L5:
        r52 = 0;
        goto L6
    }

    private static String format(TL_iv.RichMessage r3) {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= r3.blocks.size()) goto L9;
        if (r1 <= 0) goto L7;
        r0.append("\n");
    L7:
        format(r3.blocks.get(r1), r0);
        r1 = r1 + 1;
        goto L4
    L9:
        return r0.toString();
    }

    private static void format(TL_iv.PageBlock r6, StringBuilder r7) {
        if ((r6 instanceof TL_iv.pageBlockHeading1) == false) goto L5;
    L84:
        format(r6.text, r7);
        return;
    L5:
        if ((r6 instanceof TL_iv.pageBlockHeading2) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockHeading3) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockHeading4) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockHeading5) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockHeading6) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockParagraph) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockPreformatted) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockFooter) == true) goto L84;
        if ((r6 instanceof TL_iv.pageBlockMap) == false) goto L24;
    L80:
        TL_iv.PageCaption r62 = r6.caption;
        if (r62 == null) goto L100;
        format(r62.text, r7);
        return;
    L100:
        return;
    L24:
        if ((r6 instanceof TL_iv.pageBlockAudio) == true) goto L80;
        if ((r6 instanceof TL_iv.pageBlockDocument) == true) goto L80;
        if ((r6 instanceof TL_iv.pageBlockVideo) == true) goto L80;
        if ((r6 instanceof TL_iv.pageBlockPhoto) == true) goto L80;
        if ((r6 instanceof TL_iv.pageBlockSlideshow) == true) goto L80;
        if ((r6 instanceof TL_iv.pageBlockCollage) == true) goto L80;
        if ((r6 instanceof TL_iv.pageBlockTable) == false) goto L43;
        TL_iv.RichText r63 = ((TL_iv.pageBlockTable) r6).title;
        if (r63 == null) goto L98;
        format(r63, r7);
        return;
    L98:
        return;
    L43:
        if ((r6 instanceof TL_iv.pageBlockList) == false) goto L62;
        TL_iv.pageBlockList r64 = (TL_iv.pageBlockList) r6;
        int r0 = 0;
    L46:
        if (r0 >= r64.items.size()) goto L101;
        if (r0 <= 0) goto L49;
        r7.append("\n");
    L49:
        TL_iv.PageListItem r3 = r64.items.get(r0);
        if ((r3 instanceof TL_iv.TL_pageListItemText) == false) goto L53;
        format(((TL_iv.TL_pageListItemText) r3).text, r7);
    L60:
        r0 = r0 + 1;
        goto L46
    L53:
        if ((r3 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L60;
        ArrayList<TL_iv.PageBlock> r32 = ((TL_iv.TL_pageListItemBlocks) r3).blocks;
        int r4 = 0;
    L56:
        if (r4 >= r32.size()) goto L60;
        if (r4 <= 0) goto L59;
        r7.append("\n");
    L59:
        format(r32.get(r4), r7);
        r4 = r4 + 1;
        goto L56
    L101:
        return;
    L62:
        if ((r6 instanceof TL_iv.pageBlockOrderedList) == false) goto L99;
        TL_iv.pageBlockOrderedList r65 = (TL_iv.pageBlockOrderedList) r6;
        int r02 = 0;
    L65:
        if (r02 >= r65.items.size()) goto L102;
        if (r02 <= 0) goto L68;
        r7.append("\n");
    L68:
        TL_iv.PageListOrderedItem r33 = r65.items.get(r02);
        if ((r33 instanceof TL_iv.TL_pageListOrderedItemText) == false) goto L72;
        format(((TL_iv.TL_pageListOrderedItemText) r33).text, r7);
    L79:
        r02 = r02 + 1;
        goto L65
    L72:
        if ((r33 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L79;
        ArrayList<TL_iv.PageBlock> r34 = ((TL_iv.TL_pageListOrderedItemBlocks) r33).blocks;
        int r42 = 0;
    L75:
        if (r42 >= r34.size()) goto L79;
        if (r42 <= 0) goto L78;
        r7.append("\n");
    L78:
        format(r34.get(r42), r7);
        r42 = r42 + 1;
        goto L75
    L102:
        return;
    }

    private static void format(TL_iv.RichText r2, StringBuilder r3) {
        if ((r2 instanceof TL_iv.textPlain) == false) goto L7;
        r3.append(((TL_iv.textPlain) r2).text);
        return;
    L7:
        if ((r2 instanceof TL_iv.textConcat) == false) goto L12;
        TL_iv.textConcat r22 = (TL_iv.textConcat) r2;
        int r0 = 0;
    L10:
        if (r0 >= r22.texts.size()) goto L17;
        format(r22.texts.get(r0), r3);
        r0 = r0 + 1;
        goto L10
    L17:
        return;
    L12:
        if (r2 == null) goto L16;
        format(r2.text, r3);
        return;
    }

    public static CharSequence formatStyled(TL_iv.RichMessage r3) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if (r3 == null) goto L11;
        int r1 = 0;
    L7:
        if (r1 >= r3.blocks.size()) goto L11;
        if (r1 <= 0) goto L10;
        r0.append("\n");
    L10:
        formatStyled(r3.blocks.get(r1), r0);
        r1 = r1 + 1;
    L11:
        return r0;
    }

    private static void formatStyled(TL_iv.PageBlock r7, SpannableStringBuilder r8) {
        if ((r7 instanceof TL_iv.pageBlockHeading1) == false) goto L5;
    L84:
        r8.append(RichTextStyle.toSpannable(r7.text, r7));
        return;
    L5:
        if ((r7 instanceof TL_iv.pageBlockHeading2) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockHeading3) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockHeading4) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockHeading5) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockHeading6) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockParagraph) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockPreformatted) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockFooter) == true) goto L84;
        if ((r7 instanceof TL_iv.pageBlockMap) == false) goto L24;
    L80:
        TL_iv.PageCaption r72 = r7.caption;
        if (r72 == null) goto L100;
        r8.append(RichTextStyle.toSpannable(r72.text, null));
        return;
    L100:
        return;
    L24:
        if ((r7 instanceof TL_iv.pageBlockAudio) == true) goto L80;
        if ((r7 instanceof TL_iv.pageBlockDocument) == true) goto L80;
        if ((r7 instanceof TL_iv.pageBlockVideo) == true) goto L80;
        if ((r7 instanceof TL_iv.pageBlockPhoto) == true) goto L80;
        if ((r7 instanceof TL_iv.pageBlockSlideshow) == true) goto L80;
        if ((r7 instanceof TL_iv.pageBlockCollage) == true) goto L80;
        if ((r7 instanceof TL_iv.pageBlockTable) == false) goto L43;
        TL_iv.RichText r73 = ((TL_iv.pageBlockTable) r7).title;
        if (r73 == null) goto L98;
        r8.append(RichTextStyle.toSpannable(r73, null));
        return;
    L98:
        return;
    L43:
        if ((r7 instanceof TL_iv.pageBlockList) == false) goto L62;
        TL_iv.pageBlockList r74 = (TL_iv.pageBlockList) r7;
        int r0 = 0;
    L46:
        if (r0 >= r74.items.size()) goto L101;
        if (r0 <= 0) goto L49;
        r8.append("\n");
    L49:
        TL_iv.PageListItem r4 = r74.items.get(r0);
        if ((r4 instanceof TL_iv.TL_pageListItemText) == false) goto L53;
        r8.append(RichTextStyle.toSpannable(((TL_iv.TL_pageListItemText) r4).text, null));
    L60:
        r0 = r0 + 1;
        goto L46
    L53:
        if ((r4 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L60;
        ArrayList<TL_iv.PageBlock> r42 = ((TL_iv.TL_pageListItemBlocks) r4).blocks;
        int r5 = 0;
    L56:
        if (r5 >= r42.size()) goto L60;
        if (r5 <= 0) goto L59;
        r8.append("\n");
    L59:
        formatStyled(r42.get(r5), r8);
        r5 = r5 + 1;
        goto L56
    L101:
        return;
    L62:
        if ((r7 instanceof TL_iv.pageBlockOrderedList) == false) goto L99;
        TL_iv.pageBlockOrderedList r75 = (TL_iv.pageBlockOrderedList) r7;
        int r02 = 0;
    L65:
        if (r02 >= r75.items.size()) goto L102;
        if (r02 <= 0) goto L68;
        r8.append("\n");
    L68:
        TL_iv.PageListOrderedItem r1 = r75.items.get(r02);
        if ((r1 instanceof TL_iv.TL_pageListOrderedItemText) == false) goto L72;
        r8.append(RichTextStyle.toSpannable(((TL_iv.TL_pageListOrderedItemText) r1).text));
    L79:
        r02 = r02 + 1;
        goto L65
    L72:
        if ((r1 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L79;
        ArrayList<TL_iv.PageBlock> r12 = ((TL_iv.TL_pageListOrderedItemBlocks) r1).blocks;
        int r43 = 0;
    L75:
        if (r43 >= r12.size()) goto L79;
        if (r43 <= 0) goto L78;
        r8.append("\n");
    L78:
        formatStyled(r12.get(r43), r8);
        r43 = r43 + 1;
        goto L75
    L102:
        return;
    }

    private int getDefaultTab() {
        int r2 = MessagesController.getGlobalMainSettings().getInt("aiEditorDefaultTab", 1);
        if (r2 >= 0) goto L5;
    L7:
        return 1;
    L5:
        if (r2 > 2) goto L7;
        return r2;
    }

    private TL_iv.RichMessage getResultRich() {
        if (this.loading == false) goto L5;
        return null;
    L5:
        int r0 = this.tabs.getSelectedTab();
        if (r0 != 0) goto L13;
        if (this.translatedTextLoading == false) goto L11;
        return null;
    L11:
        return this.translatedTextRich;
    L13:
        if (r0 != 2) goto L20;
        if (this.fixedTextLoading == false) goto L18;
        return null;
    L18:
        return this.fixedTextRich;
    L20:
        if (this.styledTextLoading == false) goto L22;
        return null;
    L22:
        TL_iv.RichMessage r02 = this.styledTextRich;
        if (r02 == null) goto L25;
        return r02;
    L25:
        return this.textRich;
    }

    private CharSequence getResultText() {
        if (isRich() == false) goto L10;
        TL_iv.RichMessage r3 = getResultRich();
        if (r3 != null) goto L8;
        return null;
    L8:
        return formatStyled(r3);
    L10:
        if (this.loading == false) goto L12;
        return null;
    L12:
        int r0 = this.tabs.getSelectedTab();
        if (r0 != 0) goto L20;
        if (this.translatedTextLoading == false) goto L18;
        return null;
    L18:
        return this.translatedText;
    L20:
        if (r0 != 2) goto L27;
        if (this.fixedTextLoading == false) goto L25;
        return null;
    L25:
        return this.fixedTextToCopy;
    L27:
        if (this.styledTextLoading == false) goto L29;
        return null;
    L29:
        CharSequence r02 = this.styledText;
        if (r02 == null) goto L32;
        return r02;
    L32:
        return this.text;
    }

    private boolean hasSend() {
        if (this.onSendRichListener == null) goto L5;
        return true;
    L5:
        if (this.onSendListener != null) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean hasSendResult() {
        if (this.onSendRichListener == null) goto L9;
        if (getResultRich() == null) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (this.onSendListener != null) goto L11;
    L13:
        return false;
    L11:
        if (getResultText() == null) goto L13;
        return true;
    }

    private boolean isRich() {
        if (this.textRich == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ boolean lambda$addMainTab$23(int r1, View r2) {
        return onTabLongClick(r1, r2);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(TL_aicompose.AiComposeTone r2) {
        if ((r2 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L5;
        this.tonesController.edit((TL_aicompose.TL_aiComposeTone) r2);
    L5:
        updateStyles();
    }

    private /* synthetic */ void lambda$new$10() {
        runSend(0, 0, false);
        dismiss();
    }

    private /* synthetic */ void lambda$new$11(Context r4, Theme.ResourcesProvider r5) {
        AlertsCreator.createScheduleDatePickerDialog(r4, this.dialogId, new AnonymousClass3(this), r5);
    }

    private /* synthetic */ void lambda$new$12() {
        this.aiIOptions = null;
    }

    private /* synthetic */ boolean lambda$new$13(Theme.ResourcesProvider r7, Context r8, View r9) {
        boolean r0 = false;
        if (this.editing == false) goto L6;
        return false;
    L6:
        if (hasSendResult() == true) goto L9;
        return false;
    L9:
        if (this.dialogId != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L11;
        r0 = true;
    L11:
        ItemOptions r92 = ItemOptions.makeOptions(this.container, r7, this.sendButton).addIf(!r0, R.drawable.input_notify_off, LocaleController.getString(R.string.SendWithoutSound), new AIEditorAlert$$ExternalSyntheticLambda28(this));
        int r2 = R.drawable.msg_calendar2;
        if (r0 == false) goto L14;
        int r02 = R.string.SetReminder;
    L15:
        this.aiIOptions = r92.add(r2, LocaleController.getString(r02), new AIEditorAlert$$ExternalSyntheticLambda29(this, r8, r7)).setOnDismiss(new AIEditorAlert$$ExternalSyntheticLambda30(this)).show();
        return true;
    L14:
        r02 = R.string.ScheduleMessage;
        goto L15
    }

    private /* synthetic */ void lambda$new$14(Theme.ResourcesProvider r3, View r4) {
        new PremiumFeatureBottomSheet(getContext(), 42, true, r3).show();
    }

    private /* synthetic */ void lambda$new$15(View r1, int r2) {
        this.adapter.getItem(r2 - 1);
    }

    private /* synthetic */ void lambda$new$2(Theme.ResourcesProvider r3, TL_aicompose.TL_aiComposeTone r4) {
        new CreateAiStyleAlert(getContext(), r3).setEditing(r4).setOnToneEdited(new AIEditorAlert$$ExternalSyntheticLambda45(this)).show();
    }

    private /* synthetic */ void lambda$new$3(TL_aicompose.TL_aiComposeTone r11, Context r12, Theme.ResourcesProvider r13) {
        String r5 = "https://t.me/addstyle/" + r11.slug;
        new AnonymousClass2(this, r12, null, r5, false, r5, false, r13).show();
    }

    private /* synthetic */ void lambda$new$4(TL_aicompose.TL_aiComposeTone r1) {
        this.tonesController.unsave(r1);
    }

    private /* synthetic */ void lambda$new$5(Browser.Progress r1, TL_aicompose.TL_aiComposeTone r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
        r1.end();
        MessagesController.getInstance(this.currentAccount).getTonesController().remove(r2);
        updateStyles();
    }

    private /* synthetic */ void lambda$new$6(TL_aicompose.TL_aiComposeTone r4, AlertDialog r5, int r6) {
        Browser.Progress r52 = r5.makeButtonLoading(-1);
        r52.init();
        TL_aicompose.deleteTone r62 = new TL_aicompose.deleteTone();
        r62.tone = TL_aicompose.InputAiComposeTone.from(r4);
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r62, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$$ExternalSyntheticLambda47(this, r52, r4));
    }

    private /* synthetic */ void lambda$new$7(Theme.ResourcesProvider r3, TL_aicompose.TL_aiComposeTone r4) {
        new AlertDialog.Builder(getContext(), r3).setTitle(LocaleController.getString(R.string.AIEditorDeleteStyle)).setMessage(LocaleController.getString(R.string.AIEditorDeleteStyleText)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.Delete), new AIEditorAlert$$ExternalSyntheticLambda46(this, r4)).makeRed(-1).show();
    }

    private /* synthetic */ Boolean lambda$new$8(Theme.ResourcesProvider r20, Context r21, Tabs.Tab r22) {
        TL_aicompose.AiComposeTone r3 = r22.tone;
        if ((r3 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L7;
        TL_aicompose.TL_aiComposeTone r32 = (TL_aicompose.TL_aiComposeTone) r3;
        ItemOptions.makeOptions(this.container, r20, r22).setScrimViewBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), Theme.getColor(Theme.key_windowBackgroundWhite, r20))).addIf(r32.creator, R.drawable.msg_edit, LocaleController.getString(R.string.AIEditorEditStyle), new AIEditorAlert$$ExternalSyntheticLambda31(this, r20, r32)).add(R.drawable.msg_share, LocaleController.getString(R.string.AIEditorShareStyle), new AIEditorAlert$$ExternalSyntheticLambda32(this, r32, r21, r20)).addIf(!r32.creator, R.drawable.msg_delete, LocaleController.getString(R.string.AIEditorRemoveStyle), true, new AIEditorAlert$$ExternalSyntheticLambda33(this, r32)).addIf(r32.creator, R.drawable.msg_delete, LocaleController.getString(R.string.AIEditorDeleteStyle), true, new AIEditorAlert$$ExternalSyntheticLambda34(this, r20, r32)).show();
        return Boolean.TRUE;
    L7:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$new$9(View r2) {
        runSend(0, 0, true);
        dismiss();
    }

    private /* synthetic */ void lambda$onTabLongClick$24(int r1) {
        setDefaultTab(r1);
    }

    private /* synthetic */ void lambda$onTabLongClick$25() {
        this.aiIOptions = null;
    }

    private /* synthetic */ void lambda$onToLangMenu$33(String r1) {
        cancelRequest();
        TranslatorUtils.setSendTargetLanguage(r1);
        this.to_lang = TranslatorUtils.getResolvedSendTargetLanguageCode();
        request();
    }

    private /* synthetic */ void lambda$onToLangMenu$34() {
        cancelRequest();
        TranslatorUtils.setSendTargetLanguage(Common.ASSET_APP);
        this.to_lang = TranslatorUtils.getResolvedSendTargetLanguageCode();
        request();
    }

    private /* synthetic */ void lambda$onToLangMenu$35(TranslateController.Language r1) {
        cancelRequest();
        TranslatorUtils.setSendTargetLanguage(r1.code);
        this.to_lang = TranslatorUtils.getResolvedSendTargetLanguageCode();
        request();
    }

    private /* synthetic */ void lambda$request$37(SimpleTextView r6, int r7, TLRPC.TL_messages_composeMessageWithAI r8, TLRPC.TL_composedMessageWithAI r9, TLRPC.TL_error r10) {
        this.requestId = -1;
        this.aiFallbackCancel = null;
        this.loading = false;
        if (r10 != null) goto L5;
    L10:
        if (r10 == null) goto L13;
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).showForError(r10);
        r6.setRightDrawable(null);
        this.errored = true;
        this.showLimit = false;
        updateButton();
        return;
    L13:
        if (r9 != null) goto L16;
        r6.setRightDrawable(null);
        this.errored = true;
        this.showLimit = false;
        updateButton();
        return;
    L16:
        r6.setRightDrawable(null);
        this.showLimit = false;
        updateButton();
        this.lastRequest[r7] = r8;
        if (r7 != 0) goto L19;
        this.translatedTextLoading = false;
        this.translatedText = MessageObject.formatTextWithEntities(r9.result_text);
    L27:
        this.adapter.update(true);
        return;
    L19:
        if (r7 != 1) goto L22;
        this.styledTextLoading = false;
        this.styledText = MessageObject.formatTextWithEntities(r9.result_text);
        goto L27
    L22:
        if (r7 != 2) goto L27;
        this.fixedTextLoading = false;
        TLRPC.TL_textWithEntities r62 = r9.diff_text;
        if (r62 == null) goto L26;
        this.fixedText = MessageObject.formatTextWithEntities(r62);
        this.fixedTextToCopy = MessageObject.formatTextWithEntities(r9.result_text);
        goto L27
    L26:
        CharSequence r63 = MessageObject.formatTextWithEntities(r9.result_text);
        this.fixedTextToCopy = r63;
        this.fixedText = r63;
        goto L27
    L5:
        if ("SUMMARY_FLOOD_PREMIUM".equalsIgnoreCase(r10.text) == false) goto L7;
    L8:
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.AIEditorLimitTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.AIEditorLimitText))).show();
        this.showLimit = true;
        updateButton();
        return;
    L7:
        if ("AICOMPOSE_FLOOD_PREMIUM".equalsIgnoreCase(r10.text) == false) goto L10;
        goto L8
    }

    private /* synthetic */ void lambda$request$39(Utilities.Callback2 r4, Integer r5, String r6) {
        AiController.showErrorBulletin(this.bulletinContainer, this.resourcesProvider, r5.intValue(), r6, null);
        this.aiFallbackFailed = true;
        r4.run(null, null);
    }

    private /* synthetic */ void lambda$requestRich$40(SimpleTextView r5, int r6, TLRPC.TL_messages_composeRichMessageWithAI r7, TLRPC.TL_composedRichMessageWithAI r8, TLRPC.TL_error r9) {
        this.requestId = -1;
        this.loading = false;
        if (r9 == null) goto L11;
        if ("SUMMARY_FLOOD_PREMIUM".equalsIgnoreCase(r9.text) == false) goto L7;
    L8:
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, LocaleController.getString(R.string.AIEditorLimitTitle), AndroidUtilities.replaceTags(LocaleController.getString(R.string.AIEditorLimitText))).show();
        this.showLimit = true;
        updateButton();
        return;
    L7:
        if ("AICOMPOSE_FLOOD_PREMIUM".equalsIgnoreCase(r9.text) == true) goto L8;
    L11:
        if (r9 == null) goto L14;
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).showForError(r9);
        r5.setRightDrawable(null);
        this.errored = true;
        this.showLimit = false;
        updateButton();
        return;
    L14:
        if (r8 != null) goto L17;
        r5.setRightDrawable(null);
        this.errored = true;
        this.showLimit = false;
        updateButton();
        return;
    L17:
        r5.setRightDrawable(null);
        this.showLimit = false;
        updateButton();
        this.lastRequestRich[r6] = r7;
        if (r6 != 0) goto L20;
        this.translatedTextLoading = false;
        this.translatedTextRich = r8.result;
    L25:
        this.adapter.update(true);
        return;
    L20:
        if (r6 != 1) goto L23;
        this.styledTextLoading = false;
        this.styledTextRich = r8.result;
        goto L25
    L23:
        if (r6 != 2) goto L25;
        this.fixedTextLoading = false;
        this.fixedTextRich = r8.result;
        goto L25
    }

    private /* synthetic */ void lambda$selectStyle$27() {
        AndroidUtilities.showKeyboard(this.promptCell.editText);
    }

    private /* synthetic */ void lambda$selectStyle$28(TL_aicompose.AiComposeTone r4) {
        this.tonesController.tones.add(0, r4);
        updateStyles();
        BulletinFactory.of(this.bulletinContainer, this.resourcesProvider).createEmojiBulletin(r4.emoji_id, LocaleController.formatString(R.string.AIEditorToneCreatedTitle, new Object[]{r4.title}), LocaleController.getString(R.string.AIEditorToneCreatedText)).show();
    }

    private /* synthetic */ void lambda$setText$29(String r1) {
        this.from_lang = r1;
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$setText$31(String r1) {
        this.from_lang = r1;
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$updateButton$16(View r1) {
        this.aiFallbackFailed = false;
        this.errored = false;
        updateButton();
        request();
    }

    private /* synthetic */ void lambda$updateButton$17(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$updateButton$18(View r1) {
        AndroidUtilities.hideKeyboard(this.promptCell.editText);
        this.promptText = this.promptCell.getText().toString();
        updatePromptEditText();
        updateButton(true);
        request();
    }

    private /* synthetic */ void lambda$updateButton$19(View r2) {
        if (this.onUseRichListener == null) goto L8;
        TL_iv.RichMessage r22 = getResultRich();
        if (r22 == null) goto L12;
        this.onUseRichListener.run(r22);
    L12:
        dismiss();
        return;
    L8:
        if (this.onUseListener == null) goto L12;
        if (getResultText() == null) goto L12;
        this.onUseListener.run(getResultText());
        goto L12
    }

    private /* synthetic */ void lambda$updateButton$20(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$updateButton$21() {
        if (this.showLimit == true) goto L6;
        this.allButton.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updateButton$22() {
        if (this.showLimit == false) goto L6;
        this.buttonContainer.setVisibility(8);
        return;
    }

    private boolean onTabLongClick(int r5, View r6) {
        if (getDefaultTab() != r5) goto L6;
        return false;
    L6:
        this.aiIOptions = ItemOptions.makeOptions(this.container, this.resourcesProvider, r6).setGravity(1).add(R.drawable.tabs_reorder, LocaleController.getString(R.string.ProfileTabSetAsMain), new AIEditorAlert$$ExternalSyntheticLambda43(this, r5)).setOnDismiss(new AIEditorAlert$$ExternalSyntheticLambda44(this)).show();
        return true;
    }

    private void onToLangMenu(View r13) {
        ItemOptions r3 = ItemOptions.makeOptions(this.container, this.resourcesProvider, r13);
        r3.setMaxHeight(AndroidUtilities.dp(450.0f));
        r3.setDrawScrim(false);
        r3.setOnTopOfScrim();
        ScrollView r0 = new ScrollView(getContext());
        LinearLayout r4 = new LinearLayout(getContext());
        r4.setOrientation(1);
        r0.addView(r4);
        r3.addView(r0);
        boolean r02 = TranslatorUtils.isSendTargetLanguageFollowApp();
        ArrayList<String> r8 = TranslatorUtils.getRecentSendTargetLanguages();
        ArrayList<TranslateController.Language> r9 = TranslateController.getLanguages();
        if (r02 == false) goto L5;
    L7:
        AIEditorAlert r2 = this;
    L8:
        if (r02 == false) goto L10;
        int r12 = 2;
    L11:
        int r10 = r8.size();
        int r5 = 0;
    L12:
        if (r5 >= r10) goto L21;
        int r11 = r5 + 1;
        String r6 = r8.get(r5);
        if (r12 <= 0) goto L20;
        if (r02 == false) goto L17;
    L19:
        r2.addChecked(r3, r4, false, TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r6)), new AIEditorAlert$$ExternalSyntheticLambda40(r2, r6));
        r12 = r12 - 1;
        goto L20
    L17:
        if (TextUtils.equals(r6, r2.to_lang) == false) goto L19;
    L20:
        r5 = r11;
        goto L12
    L21:
        String r62 = LocaleController.getString(R.string.TranslationTargetApp);
        if (r02 == false) goto L25;
        AIEditorAlert$$ExternalSyntheticLambda41 r122 = null;
    L26:
        r2.addChecked(r3, r4, r02, r62, r122);
        ActionBarPopupWindow.GapView r03 = new ActionBarPopupWindow.GapView(r2.getContext(), r2.resourcesProvider);
        r03.setTag(R.id.fit_width_tag, 1);
        r4.addView(r03, LayoutHelper.createLinear(-1, 8));
        int r04 = r9.size();
        int r52 = 0;
    L27:
        if (r52 >= r04) goto L36;
        int r82 = r52 + 1;
        TranslateController.Language r63 = r9.get(r52);
        if (r02 == false) goto L31;
    L34:
        boolean r53 = false;
    L35:
        r2.addChecked(r3, r4, r53, r63.displayName, new AIEditorAlert$$ExternalSyntheticLambda42(r2, r63));
        r52 = r82;
        goto L27
    L31:
        if (TextUtils.equals(r63.code, r2.to_lang) == false) goto L34;
        r53 = true;
        goto L35
    L36:
        r3.show();
        return;
    L25:
        r122 = new AIEditorAlert$$ExternalSyntheticLambda41(r2);
        goto L26
    L10:
        r12 = 1;
        goto L11
    L5:
        if (TextUtils.isEmpty(this.to_lang) == true) goto L7;
        r2 = this;
        r2.addChecked(r3, r4, true, TranslateAlert2.capitalFirst(TranslateAlert2.languageName(this.to_lang)), null);
        goto L8
    }

    private UItem previewItem(int r1, TL_iv.RichMessage r2, boolean r3) {
        if (r2 != null) goto L5;
        r2 = this.textRich;
    L5:
        UItem r0 = RichMessageLayout.PreviewView.Factory.of(r2);
        r0.id = r1;
        r0.checked = r3;
        return r0;
    }

    private void request() {
        if (isRich() == false) goto L6;
        requestRich();
        return;
    L6:
        TLRPC.TL_textWithEntities r0 = new TLRPC.TL_textWithEntities();
        CharSequence[] r3 = {this.text};
        r0.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r3, true);
        CharSequence r1 = r3[0];
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 != null) goto L9;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r0.text = r12;
        int r13 = this.tabs.getSelectedTab();
        TLRPC.TL_messages_composeMessageWithAI r5 = new TLRPC.TL_messages_composeMessageWithAI();
        r5.text = r0;
        if (r13 != 0) goto L13;
        r5.translate_to_lang = TranslateController.normalizeLanguage(this.to_lang);
        r5.tone = TL_aicompose.InputAiComposeTone.fromDefault(this.translateTone);
        r5.emojify = this.emojify;
    L30:
        TLRPC.TL_messages_composeMessageWithAI r33 = this.lastRequest[r13];
        if (r33 == null) goto L42;
        if (r33.proofread != r5.proofread) goto L42;
        if (r33.emojify != r5.emojify) goto L42;
        if (TL_aicompose.InputAiComposeTone.equals(r33.tone, r5.tone) == false) goto L42;
        if (TextUtils.equals(r33.translate_to_lang, r5.translate_to_lang) == false) goto L42;
        return;
    L42:
        if (r5.emojify == false) goto L44;
    L50:
        this.loading = true;
        this.errored = false;
        updateButton();
        SimpleTextView r34 = this.actionBar.getTitleTextView();
        r34.setRightDrawable(this.titleLoadingDrawable);
        this.titleLoadingDrawable.start();
        int r6 = estimateLinesCount();
        SpannableStringBuilder r7 = new SpannableStringBuilder();
        int r8 = 0;
    L51:
        if (r8 >= r6) goto L55;
        if (r8 <= 0) goto L54;
        r7.append("\n");
    L54:
        int r9 = AndroidUtilities.dp((int) (Math.random() * 50.0d));
        int r10 = r7.length();
        r7.append(LocaleController.getString(R.string.Loading));
        r7.setSpan(new LoadingSpan(null, r9, 0).setHeight(AndroidUtilities.dp(6.0f)).setAlpha(0.5f).setFullWidth(true), r10, r7.length(), 33);
        r8 = r8 + 1;
        goto L51
    L55:
        if (r13 != 0) goto L57;
        this.translatedTextLoading = true;
        this.translatedText = r7;
    L61:
        AIEditorAlert$$ExternalSyntheticLambda0 r02 = new AIEditorAlert$$ExternalSyntheticLambda0(this, r34, r13, r5);
        if (TelegramAiReplacement.replacesEditor(this.currentAccount) == false) goto L67;
        Runnable r14 = this.aiFallbackCancel;
        if (r14 == null) goto L66;
        r14.run();
    L66:
        this.aiFallbackCancel = TelegramAiReplacement.compose(r5, this.styleTabs.getSelectedTone(), this.from_lang, new AIEditorAlert$$ExternalSyntheticLambda1(r02), new AIEditorAlert$$ExternalSyntheticLambda2(this, r02));
    L68:
        this.adapter.update(true);
        return;
    L67:
        this.requestId = ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r5, new AiTonesController$$ExternalSyntheticLambda0(), r02);
        goto L68
    L57:
        if (r13 != 1) goto L59;
        this.styledTextLoading = true;
        this.styledText = r7;
        goto L61
    L59:
        if (r13 != 2) goto L61;
        this.fixedTextLoading = true;
        this.fixedText = r7;
        goto L61
    L44:
        if (r5.proofread == true) goto L50;
        if (r5.tone != null) goto L50;
        if (r5.translate_to_lang != null) goto L50;
        return;
    L13:
        if (r13 != 1) goto L28;
        TL_aicompose.AiComposeTone r62 = this.styleTabs.getSelectedTone();
        if ((r62 instanceof PromptTone) == false) goto L22;
        TL_aicompose.inputAiComposeToneSingleUse r63 = new TL_aicompose.inputAiComposeToneSingleUse();
        if (TextUtils.isEmpty(this.promptText) == true) goto L20;
        r32 = this.promptText;
    L20:
        r63.custom_prompt = r32;
        r5.tone = r63;
    L27:
        r5.emojify = this.emojify;
        goto L30
    L22:
        if ((r62 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L25;
        TL_aicompose.inputAiComposeToneID r35 = new TL_aicompose.inputAiComposeToneID();
        TL_aicompose.TL_aiComposeTone r64 = (TL_aicompose.TL_aiComposeTone) r62;
        r35.id = r64.id;
        r35.access_hash = r64.access_hash;
        r5.tone = r35;
        goto L27
    L25:
        if ((r62 instanceof TL_aicompose.TL_aiComposeToneDefault) == false) goto L27;
        TL_aicompose.inputAiComposeToneDefault r36 = new TL_aicompose.inputAiComposeToneDefault();
        r36.tone = ((TL_aicompose.TL_aiComposeToneDefault) r62).tone;
        r5.tone = r36;
        goto L27
    L28:
        if (r13 != 2) goto L30;
        r5.proofread = true;
        goto L30
    L9:
        r12 = r1.toString();
        goto L10
    }

    private void requestRich() {
        int r0 = this.tabs.getSelectedTab();
        TLRPC.TL_messages_composeRichMessageWithAI r1 = new TLRPC.TL_messages_composeRichMessageWithAI();
        r1.flags |= 16;
        r1.text = toInput(this.textRich);
        if (r0 != 0) goto L5;
        r1.translate_to_lang = TranslateController.normalizeLanguage(this.to_lang);
        r1.tone = TL_aicompose.InputAiComposeTone.fromDefault(this.translateTone);
        r1.emojify = this.emojify;
    L22:
        TLRPC.TL_messages_composeRichMessageWithAI r4 = this.lastRequestRich[r0];
        if (r4 == null) goto L34;
        if (r4.proofread != r1.proofread) goto L34;
        if (r4.emojify != r1.emojify) goto L34;
        if (TL_aicompose.InputAiComposeTone.equals(r4.tone, r1.tone) == false) goto L34;
        if (TextUtils.equals(r4.translate_to_lang, r1.translate_to_lang) == false) goto L34;
        return;
    L34:
        if (r1.emojify == false) goto L36;
    L42:
        this.loading = true;
        this.errored = false;
        updateButton();
        SimpleTextView r42 = this.actionBar.getTitleTextView();
        r42.setRightDrawable(this.titleLoadingDrawable);
        this.titleLoadingDrawable.start();
        if (r0 != 0) goto L45;
        this.translatedTextLoading = true;
    L49:
        this.requestId = ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r1, new AiTonesController$$ExternalSyntheticLambda0(), new AIEditorAlert$$ExternalSyntheticLambda39(this, r42, r0, r1));
        this.adapter.update(true);
        return;
    L45:
        if (r0 != 1) goto L47;
        this.styledTextLoading = true;
        goto L49
    L47:
        if (r0 != 2) goto L49;
        this.fixedTextLoading = true;
        goto L49
    L36:
        if (r1.proofread == true) goto L42;
        if (r1.tone != null) goto L42;
        if (r1.translate_to_lang != null) goto L42;
        return;
    L5:
        if (r0 != 1) goto L20;
        TL_aicompose.AiComposeTone r43 = this.styleTabs.getSelectedTone();
        if ((r43 instanceof PromptTone) == false) goto L14;
        TL_aicompose.inputAiComposeToneSingleUse r44 = new TL_aicompose.inputAiComposeToneSingleUse();
        if (TextUtils.isEmpty(this.promptText) == false) goto L11;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        r44.custom_prompt = r5;
        r1.tone = r44;
    L19:
        r1.emojify = this.emojify;
        goto L22
    L11:
        r5 = this.promptText;
        goto L12
    L14:
        if ((r43 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L17;
        TL_aicompose.inputAiComposeToneID r52 = new TL_aicompose.inputAiComposeToneID();
        TL_aicompose.TL_aiComposeTone r45 = (TL_aicompose.TL_aiComposeTone) r43;
        r52.id = r45.id;
        r52.access_hash = r45.access_hash;
        r1.tone = r52;
        goto L19
    L17:
        if ((r43 instanceof TL_aicompose.TL_aiComposeToneDefault) == false) goto L19;
        TL_aicompose.inputAiComposeToneDefault r53 = new TL_aicompose.inputAiComposeToneDefault();
        r53.tone = ((TL_aicompose.TL_aiComposeToneDefault) r43).tone;
        r1.tone = r53;
        goto L19
    L20:
        if (r0 != 2) goto L22;
        r1.proofread = true;
        goto L22
    }

    private void runSend(int r2, int r3, boolean r4) {
        if (this.onSendRichListener == null) goto L9;
        TL_iv.RichMessage r0 = getResultRich();
        if (r0 == null) goto L14;
        this.onSendRichListener.run(r0, Integer.valueOf(r2), Integer.valueOf(r3), Boolean.valueOf(r4));
        return;
    L14:
        return;
    L9:
        if (this.onSendListener != null) goto L11;
        return;
    L11:
        if (getResultText() == null) goto L16;
        this.onSendListener.run(getResultText(), Integer.valueOf(r2), Integer.valueOf(r3), Boolean.valueOf(r4));
        return;
    }

    private void selectStyle(TL_aicompose.AiComposeTone r3) {
        HintView2 r0 = this.styleHint;
        if (r0 == null) goto L6;
        r0.hide();
    L6:
        if ((r3 instanceof PromptTone) == false) goto L9;
        this.styleTabs.selectTone(r3);
        this.adapter.update(true);
        AndroidUtilities.runOnUIThread(new AIEditorAlert$$ExternalSyntheticLambda35(this), 150);
        return;
    L9:
        if (r3 != null) goto L20;
        int r32 = this.tonesController.getSavedTonesCount() + 1;
        boolean r02 = UserConfig.getInstance(this.currentAccount).isPremium();
        int r1 = this.currentAccount;
        if (r02 == false) goto L13;
        int r03 = MessagesController.getInstance(r1).config.aicomposeToneSavedLimitPremium.get();
    L14:
        if (r32 <= r03) goto L17;
        showStylesLimitToast(BulletinFactory.of(this.bulletinContainer, this.resourcesProvider), this.currentAccount);
        return;
    L17:
        new CreateAiStyleAlert(getContext(), this.resourcesProvider).setOnToneCreated(new AIEditorAlert$$ExternalSyntheticLambda36(this)).show();
        return;
    L13:
        r03 = MessagesController.getInstance(r1).config.aicomposeToneSavedLimitDefault.get();
        goto L14
    L20:
        if (this.styleTabs.getSelectedTone() != r3) goto L22;
        return;
    L22:
        this.styleTabs.selectTone(r3);
        request();
        this.adapter.update(true);
    }

    private void selectTab(int r2) {
        selectTab(r2, true);
    }

    private void selectTab(int r2, boolean r3) {
        if (this.tabs.getSelectedTab() != r2) goto L5;
        return;
    L5:
        HintView2 r0 = this.styleHint;
        if (r0 == null) goto L8;
        r0.hide();
    L8:
        this.tabs.selectTab(r2, r3);
        if (r2 != 1) goto L11;
        AndroidUtilities.runOnUIThread(new AIEditorAlert$$ExternalSyntheticLambda10(this));
    L11:
        request();
        this.adapter.update(r3);
    }

    private void setDefaultTab(int r2) {
        if (r2 >= 0) goto L4;
        return;
    L4:
        if (r2 > 2) goto L9;
        MessagesController.getGlobalMainSettings().edit().putInt("aiEditorDefaultTab", r2).apply();
        return;
    }

    private void showStyleHint() {
        Tabs r0 = this.tabs;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getSelectedTab() == 1) goto L8;
        return;
    L8:
        if (MessagesController.getGlobalMainSettings().getBoolean("aiEditorStyleHintShown", false) == true) goto L17;
        HintView2 r02 = this.styleHint;
        if (r02 == null) goto L13;
        r02.hide();
        this.styleHint = null;
    L13:
        HintView2 r03 = new HintView2(getContext(), 1);
        this.styleHint = r03;
        r03.setRoundingWithCornerEffect(false);
        this.styleHint.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.styleHint.setRounding(20.0f);
        this.styleHint.setShadow(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(4.0f), Theme.multAlpha(-16777216, 0.25f));
        this.styleHint.setText(LocaleController.getString(R.string.AIEditorChooseStyle));
        this.styleHint.setJoint(0.5f, 0.0f);
        this.styleHint.setDuration(8000);
        this.containerView.addView(this.styleHint, LayoutHelper.createFrame(-1, 200.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
        MessagesController.getGlobalMainSettings().edit().putBoolean("aiEditorStyleHintShown", true).apply();
        this.styleHint.show();
        updateStyleHintY();
        return;
    }

    public static void showStylesLimitToast(BulletinFactory r4, int r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.getContext() == null) goto L17;
        MessagesController r0 = MessagesController.getInstance(r5);
        boolean r52 = UserConfig.getInstance(r5).isPremium();
        if (r52 == true) goto L9;
        int r1 = R.raw.star_premium_2;
    L10:
        String r2 = LocaleController.getString(R.string.AIEditorStyleLimitTitle);
        if (r52 == true) goto L13;
        String r53 = LocaleController.formatString(R.string.AIEditorStyleLimitTextPremium, new Object[]{Integer.valueOf(r0.config.aicomposeToneSavedLimitDefault.get()), Integer.valueOf(r0.config.aicomposeToneSavedLimitPremium.get())});
    L14:
        r4.createSimpleBulletin(r1, r2, AndroidUtilities.replaceSingleTag(r53, new AIEditorAlert$$ExternalSyntheticLambda23(r4))).show();
        return;
    L13:
        r53 = LocaleController.formatString(R.string.AIEditorStyleLimitText, new Object[]{Integer.valueOf(r0.config.aicomposeToneSavedLimitPremium.get())});
        goto L14
    L9:
        r1 = R.raw.error;
        goto L10
    }

    private static TL_iv.TL_inputRichMessage toInput(TL_iv.RichMessage r9) {
        TL_iv.TL_inputRichMessage r0 = new TL_iv.TL_inputRichMessage();
        if (r9 == null) goto L31;
        r0.rtl = r9.rtl;
        r0.blocks = new ArrayList(r9.blocks.size());
        int r2 = 0;
    L7:
        if (r2 >= r9.blocks.size()) goto L9;
        r0.blocks.add(SendMessagesHelper.toInputPageBlock(r9.blocks.get(r2)));
        r2 = r2 + 1;
        goto L7
    L9:
        ArrayList<TLRPC.Photo> r22 = r9.photos;
        if (r22 != null) goto L12;
    L20:
        ArrayList<TLRPC.Document> r23 = r9.documents;
        if (r23 == null) goto L31;
        if (r23.isEmpty() == true) goto L31;
        r0.flags |= 8;
        ArrayList<TLRPC.Document> r92 = r9.documents;
        int r24 = r92.size();
        int r3 = 0;
    L25:
        if (r3 >= r24) goto L31;
        TLRPC.Document r4 = r92.get(r3);
        r3 = r3 + 1;
        TLRPC.Document r42 = r4;
        TLRPC.TL_inputDocument r5 = new TLRPC.TL_inputDocument();
        r5.id = r42.id;
        r5.access_hash = r42.access_hash;
        byte[] r43 = r42.file_reference;
        if (r43 != null) goto L30;
        r43 = new byte[0];
    L30:
        r5.file_reference = r43;
        r0.documents.add(r5);
        goto L25
    L12:
        if (r22.isEmpty() == true) goto L20;
        r0.flags |= 4;
        ArrayList<TLRPC.Photo> r25 = r9.photos;
        int r32 = r25.size();
        int r44 = 0;
    L14:
        if (r44 >= r32) goto L20;
        TLRPC.Photo r52 = r25.get(r44);
        r44 = r44 + 1;
        TLRPC.Photo r53 = r52;
        TLRPC.TL_inputPhoto r6 = new TLRPC.TL_inputPhoto();
        r6.id = r53.id;
        r6.access_hash = r53.access_hash;
        byte[] r54 = r53.file_reference;
        if (r54 != null) goto L19;
        r54 = new byte[0];
    L19:
        r6.file_reference = r54;
        r0.photos.add(r6);
    L31:
        return r0;
    }

    private void toggleEmojify(View r4) {
        this.emojify = !this.emojify;
        request();
        if ((r4 instanceof LinearLayout) == false) goto L8;
        LinearLayout r42 = (LinearLayout) r4;
        if ((r42.getChildAt(0) instanceof CheckBox2) == false) goto L9;
        ((CheckBox2) r42.getChildAt(0)).setChecked(this.emojify, true);
        return;
    L9:
        return;
    }

    private void updateButton() {
        updateButton(true);
    }

    private void updateButton(boolean r7) {
        boolean r0 = this.errored;
        int r1 = 0;
        if (r0 == true) goto L5;
    L7:
        if (r0 == false) goto L9;
        this.button.setText(LocaleController.getString(R.string.OK));
        this.button.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda14(this));
    L27:
        this.button.setLoading(this.loading);
        if (r7 == true) goto L30;
    L32:
        boolean r02 = this.showLimit;
        this.buttonShowLimit = r02;
        ButtonWithCounterView r2 = this.allButton;
        float r3 = 0.0f;
        if (r7 == false) goto L44;
        r2.setVisibility(0);
        this.buttonContainer.setVisibility(0);
        ViewPropertyAnimator r72 = this.allButton.animate();
        if (this.showLimit == false) goto L37;
        float r03 = 1.0f;
    L38:
        ViewPropertyAnimator r73 = r72.alpha(r03);
        CubicBezierInterpolator r04 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r73.setInterpolator(r04).setDuration(320).withEndAction(new AIEditorAlert$$ExternalSyntheticLambda18(this)).start();
        ViewPropertyAnimator r74 = this.buttonContainer.animate();
        if (this.showLimit == true) goto L41;
        r3 = 1.0f;
    L41:
        r74.alpha(r3).setInterpolator(r04).setDuration(320).withEndAction(new AIEditorAlert$$ExternalSyntheticLambda19(this)).start();
        return;
    L37:
        r03 = 0.0f;
        goto L38
    L44:
        if (r02 == false) goto L46;
        int r05 = 0;
    L47:
        r2.setVisibility(r05);
        ButtonWithCounterView r06 = this.allButton;
        if (this.showLimit == false) goto L50;
        float r22 = 1.0f;
    L51:
        r06.setAlpha(r22);
        LinearLayout r07 = this.buttonContainer;
        if (this.showLimit == false) goto L55;
        r1 = 8;
    L55:
        r07.setVisibility(r1);
        LinearLayout r75 = this.buttonContainer;
        if (this.showLimit == true) goto L58;
        r3 = 1.0f;
    L58:
        r75.setAlpha(r3);
        return;
    L50:
        r22 = 0.0f;
        goto L51
    L46:
        r05 = 8;
        goto L47
    L30:
        if (this.buttonShowLimit != this.showLimit) goto L32;
        return;
    L9:
        Tabs r08 = this.tabs;
        if (r08 == null) goto L12;
        int r09 = r08.getSelectedTab();
    L14:
        if (r09 != 1) goto L21;
        if ((this.styleTabs.getSelectedTone() instanceof PromptTone) == false) goto L21;
        if (TextUtils.equals(this.promptCell.getText().toString(), this.promptText) == true) goto L21;
        this.button.setText(LocaleController.getString(R.string.ArticleAIGenerate));
        this.button.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda15(this));
    L21:
        if (this.onUseRichListener == null) goto L23;
    L26:
        this.button.setText(LocaleController.getString(R.string.AIEditorApply));
        this.button.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda16(this));
        goto L27
    L23:
        if (this.onUseListener != null) goto L26;
        this.button.setText(LocaleController.getString(R.string.OK));
        this.button.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda17(this));
        goto L27
    L12:
        r09 = 0;
        goto L14
    L5:
        if (this.aiFallbackFailed == false) goto L7;
        this.button.setText(LocaleController.getString(R.string.TryAgain));
        this.button.setOnClickListener(new AIEditorAlert$$ExternalSyntheticLambda13(this));
        goto L27
    }

    private void updatePromptEditText() {
        boolean r0 = TextUtils.equals(this.promptText, this.promptCell.editText.getText().toString());
        boolean r1 = !r0;
        if (this.newPrompt != r1) goto L5;
        return;
    L5:
        ViewPropertyAnimator r2 = this.promptCell.editText.animate();
        this.newPrompt = r1;
        if (r0 == true) goto L8;
        float r02 = 1.0f;
    L9:
        r2.alpha(r02).setDuration(320).start();
        this.adapter.update(true);
        return;
    L8:
        r02 = 0.5f;
        goto L9
    }

    private void updateSendButtonIcon() {
        ButtonWithCounterView r0 = this.sendButton;
        if (this.editing == false) goto L5;
    L8:
        int r1 = 8;
    L9:
        r0.setVisibility(r1);
        SpannableStringBuilder r02 = new SpannableStringBuilder(LocaleController.getString(R.string.Send));
        if (this.editing == false) goto L12;
        int r3 = R.drawable.filled_profile_edit_24;
    L13:
        r02.setSpan(new ColoredImageSpan(r3), 0, r02.length(), 33);
        this.sendButton.setText(r02);
        return;
    L12:
        r3 = R.drawable.send_extera_24;
        goto L13
    L5:
        if (hasSend() == false) goto L8;
        r1 = 0;
        goto L9
    }

    private void updateStyleHintY() {
        if (this.styleHint != null) goto L5;
        return;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= this.recyclerListView.getChildCount()) goto L14;
        View r2 = this.recyclerListView.getChildAt(r1);
        UItem r3 = this.adapter.getItem(this.recyclerListView.getChildAdapterPosition(r2) - 1);
        if (r3 == null) goto L13;
        if (r3.view != this.styleTabs) goto L13;
    L15:
        HintView2 r12 = this.styleHint;
        if (r2 == null) goto L19;
        r12.setVisibility(0);
        this.styleHint.setTranslationY((this.recyclerListView.getY() + r2.getY()) + r2.getHeight());
        return;
    L19:
        r12.setVisibility(4);
        this.styleHint.hide();
        return;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        r2 = null;
        goto L15
    }

    private void updateStyles() {
        TL_aicompose.AiComposeTone r0 = this.styleTabs.getSelectedTone();
        this.styleTabs.clearTabs();
        if (isRich() == false) goto L5;
        this.styleTabs.addTab(new PromptTone(), new AIEditorAlert$$ExternalSyntheticLambda3(this));
    L5:
        this.styleTabs.addTab(null, new AIEditorAlert$$ExternalSyntheticLambda3(this));
        ArrayList<TL_aicompose.AiComposeTone> r1 = this.tonesController.tones;
        int r2 = r1.size();
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L9;
        TL_aicompose.AiComposeTone r5 = r1.get(r4);
        r4 = r4 + 1;
        Tabs r6 = this.styleTabs;
        r6.addTab(r5, new AIEditorAlert$$ExternalSyntheticLambda3(this));
        goto L6
    L9:
        if (r0 == this.styleTabs.getSelectedTone()) goto L13;
        this.styleTabs.selectTone(null, true);
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(r9, getContext(), this.currentAccount, 0, true, new AIEditorAlert$$ExternalSyntheticLambda20(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.loadedAiComposeTones) goto L6;
        updateStyles();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        Runnable r0 = this.aiFallbackCancel;
        if (r0 == null) goto L5;
        r0.run();
        this.aiFallbackCancel = null;
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.loadedAiComposeTones);
        AiTonesController r02 = this.tonesController;
        if (r02 == null) goto L8;
        r02.open = false;
    L8:
        super.dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.title != null) goto L6;
        this.title = LocaleController.getString(R.string.AIEditor);
        RLottieDrawable r0 = new RLottieDrawable(R.raw.emoji_stars, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.titleLoadingDrawable = r0;
        r0.setAllowDecodeSingleFrame(true);
        this.titleLoadingDrawable.setAutoRepeat(1);
    L6:
        return this.title;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onActionBarAlpha(float r5) {
        float r52 = 1.0f - r5;
        this.closeView.setAlpha(r52);
        this.closeView.setScaleX(AndroidUtilities.lerp(0.6f, 1.0f, r52));
        this.closeView.setScaleY(AndroidUtilities.lerp(0.6f, 1.0f, r52));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView, org.telegram.ui.ActionBar.BottomSheet
    public void onContainerViewTranslation() {
        super.onContainerViewTranslation();
        ValueAnimator r0 = this.keyboardContentAnimator;
        LinearLayout r1 = this.buttonContainer;
        if (r0 == null) goto L6;
        r1.setTranslationY(-((Float) r0.getAnimatedValue()).floatValue());
        return;
    L6:
        r1.setTranslationY(0.0f);
    }

    public AIEditorAlert setOnSend(long r1, boolean r3, Utilities.Callback4<CharSequence, Integer, Integer, Boolean> r4) {
        this.dialogId = r1;
        this.editing = r3;
        this.onSendListener = r4;
        return this;
    }

    public AIEditorAlert setOnSendRich(long r1, Utilities.Callback4<TL_iv.RichMessage, Integer, Integer, Boolean> r3) {
        this.dialogId = r1;
        this.onSendRichListener = r3;
        return this;
    }

    public AIEditorAlert setOnUse(Utilities.Callback<CharSequence> r1) {
        this.onUseListener = r1;
        return this;
    }

    public AIEditorAlert setOnUseRich(Utilities.Callback<TL_iv.RichMessage> r1) {
        this.onUseRichListener = r1;
        return this;
    }

    public AIEditorAlert setText(CharSequence r3) {
        this.text = copy(r3);
        if (LanguageDetector.hasSupport() == false) goto L5;
        LanguageDetector.detectLanguage(r3.toString(), new AIEditorAlert$$ExternalSyntheticLambda11(this), new AIEditorAlert$$ExternalSyntheticLambda12());
    L5:
        return this;
    }

    public AIEditorAlert setText(TL_iv.RichMessage r3) {
        this.textRich = r3;
        if (LanguageDetector.hasSupport() == false) goto L5;
        LanguageDetector.detectLanguage(format(this.textRich), new AIEditorAlert$$ExternalSyntheticLambda21(this), new AIEditorAlert$$ExternalSyntheticLambda22());
    L5:
        updateStyles();
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L5;
        r0.setTitle(getTitle());
    L5:
        updateSendButtonIcon();
        this.adapter.update(false);
        request();
        updateButton();
    }
}
