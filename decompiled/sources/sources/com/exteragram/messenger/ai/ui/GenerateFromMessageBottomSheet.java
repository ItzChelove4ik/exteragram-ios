package com.exteragram.messenger.ai.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import com.exteragram.messenger.components.CheckBoxRow;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.util.Objects;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class GenerateFromMessageBottomSheet extends BottomSheet {
    private boolean includeImage;
    BaseFragment parentFragment;
    private final EditTextCell promptCell;
    private boolean useHistory;

    public static final class GenerationData extends RecordTag {
        private final String imagePath;
        private final String prompt;
        private final boolean useHistory;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof GenerationData) == false) goto L12;
            GenerationData r32 = (GenerationData) r3;
            if (this.useHistory == r32.useHistory) goto L7;
            return false;
        L7:
            if (Objects.equals(this.prompt, r32.prompt) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.imagePath, r32.imagePath) == false) goto L16;
            return true;
        L16:
            return false;
        L12:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.prompt, Boolean.valueOf(this.useHistory), this.imagePath};
        }

        public GenerationData(String r1, boolean r2, String r3) {
            this.prompt = r1;
            this.useHistory = r2;
            this.imagePath = r3;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return GenerateFromMessageBottomSheet$GenerationData$$ExternalSyntheticRecord0.m(this.useHistory, this.prompt, this.imagePath);
        }

        public String imagePath() {
            return this.imagePath;
        }

        public String prompt() {
            return this.prompt;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), GenerationData.class, "prompt;useHistory;imagePath");
        }

        public boolean useHistory() {
            return this.useHistory;
        }
    }

    public static /* synthetic */ void $r8$lambda$BYDEYSncZIfkJCn8ozS0OQgvjFQ(GenerateFromMessageBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ Unit $r8$lambda$HZgW2HHdfcC2aKfV676D4zoIiws(GenerateFromMessageBottomSheet r0, Boolean r1) {
        return r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$bHGnMO_DqR8YWTfdFYEnwSJur5U(GenerateFromMessageBottomSheet r0, String r1, Utilities.Callback r2, View r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$laNJ0iHDAsTlRULt4okWVi3GNfs(View r2, MotionEvent r3) {
        r2.getParent().requestDisallowInterceptTouchEvent(true);
        if ((r3.getAction() & 255) != 1) goto L5;
        r2.getParent().requestDisallowInterceptTouchEvent(false);
    L5:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$y7a9dkx1qe6qfr2ry1Uu9Fn6JmM(GenerateFromMessageBottomSheet r0) {
        r0.lambda$show$5();
    }

    /* renamed from: $r8$lambda$yzFJS6MgHV5CP3_XizKVS38e4-0, reason: not valid java name */
    public static /* synthetic */ Unit m1077$r8$lambda$yzFJS6MgHV5CP3_XizKVS38e40(GenerateFromMessageBottomSheet r0, Boolean r1) {
        return r0.lambda$new$3(r1);
    }

    public GenerateFromMessageBottomSheet(String r8, String r9, BaseFragment r10, Context r11, Utilities.Callback<GenerationData> r12) {
        this(r8, r9, r10, r11, r12, AiConfig.getSaveHistory());
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public GenerateFromMessageBottomSheet(String r21, String r22, BaseFragment r23, Context r24, Utilities.Callback<GenerationData> r25, boolean r26) {
        super(r24, true, r23.getResourceProvider());
        int r1 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r1));
        fixNavigationBar(getThemedColor(r1));
        setApplyTopPadding(false);
        this.smoothKeyboardAnimationEnabled = true;
        this.parentFragment = r23;
        this.useHistory = AiConfig.getSaveHistory();
        this.includeImage = AiController.canSendImage(r22);
        OnDeviceAvailability.warmup();
        ScrollView r10 = new ScrollView(r24);
        LinearLayout r11 = new LinearLayout(r24);
        r11.setOrientation(1);
        r11.setClipChildren(false);
        r11.setClipToPadding(false);
        r10.addView(r11, LayoutHelper.createScroll(-1, -2, 51));
        FrameLayout r12 = new FrameLayout(r24);
        TextView r3 = new TextView(r24);
        r3.setText(LocaleController.getString(R.string.Generate));
        r3.setTextSize(1, 20.0f);
        r3.setTypeface(AndroidUtilities.bold());
        int r5 = Theme.key_windowBackgroundWhiteBlackText;
        r3.setTextColor(getThemedColor(r5));
        r3.setGravity(19);
        r12.addView(r3, LayoutHelper.createFrame(-1, 56.0f, 51, 22.0f, 6.0f, 56.0f, 0.0f));
        ImageView r32 = new ImageView(r24);
        r32.setImageResource(R.drawable.ic_close_white);
        r32.setScaleType(ImageView.ScaleType.CENTER);
        r32.setColorFilter(new PorterDuffColorFilter(getThemedColor(r5), PorterDuff.Mode.SRC_IN));
        ScaleStateListAnimator.apply(r32);
        r32.setOnClickListener(new GenerateFromMessageBottomSheet$$ExternalSyntheticLambda0(this));
        r12.addView(r32, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 10.0f, 12.0f, 0.0f));
        r11.addView(r12, LayoutHelper.createLinear(-1, 70));
        EditTextCell r13 = new EditTextCell(r24, LocaleController.getString(R.string.RolePrompt), true, false, -1, this.resourcesProvider);
        this.promptCell = r13;
        r13.editText.setImeOptions(6);
        r13.editText.setMaxLines(8);
        r13.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), getThemedColor(Theme.key_windowBackgroundWhite)));
        if (TextUtils.isEmpty(r21) == true) goto L5;
        r13.setText(r21);
    L5:
        r13.editText.setOnTouchListener(new GenerateFromMessageBottomSheet$$ExternalSyntheticLambda1());
        r11.addView(r13, LayoutHelper.createLinear(-1, -2, 12.0f, 0.0f, 12.0f, 0.0f));
        if (AiConfig.getSaveHistory() == false) goto L9;
        if (r26 == false) goto L9;
        boolean r14 = true;
    L10:
        boolean r33 = AiController.canSendImage(r22);
        if (r14 == true) goto L15;
        if (r33 == true) goto L15;
        LinearLayout r15 = null;
    L21:
        ButtonWithCounterView r2 = new ButtonWithCounterView(r24, this.resourcesProvider).setRound();
        r2.setText(LocaleController.getString(R.string.Proceed), false);
        r2.setOnClickListener(new GenerateFromMessageBottomSheet$$ExternalSyntheticLambda4(this, r22, r25));
        if (r15 == null) goto L25;
        int r34 = 16;
    L26:
        r11.addView(r2, LayoutHelper.createLinear(-1, 48, 0, 12, 12, 12, r34));
        if (r15 == null) goto L29;
        r11.addView(r15, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 8.0f));
    L29:
        setCustomView(r10);
        return;
    L25:
        r34 = 12;
    L15:
        LinearLayout r4 = new LinearLayout(r24);
        r4.setOrientation(0);
        r4.setGravity(1);
        if (r14 == false) goto L18;
        CheckBoxRow r16 = new CheckBoxRow(r24, LocaleController.getString(R.string.GenerateWithHistory), this.useHistory, this.resourcesProvider);
        r16.setOnCheckedChange(new GenerateFromMessageBottomSheet$$ExternalSyntheticLambda2(this));
        r4.addView(r16, LayoutHelper.createLinear(-2, -2, 4.0f, 0.0f, 4.0f, 0.0f));
    L18:
        if (r33 == false) goto L20;
        CheckBoxRow r17 = new CheckBoxRow(r24, LocaleController.getString(R.string.GenerateWithPhoto), this.includeImage, this.resourcesProvider);
        r17.setOnCheckedChange(new GenerateFromMessageBottomSheet$$ExternalSyntheticLambda3(this));
        BackupImageView r35 = new BackupImageView(r24);
        r35.getImageReceiver().setImage(r22, "24_24", null, null, 0);
        r35.setRoundRadius(AndroidUtilities.dp(4.0f));
        r17.addView(r35, LayoutHelper.createLinear(24, 24, 16, 9, 0, 0, 0));
        r4.addView(r17, LayoutHelper.createLinear(-2, -2, 4.0f, 0.0f, 4.0f, 0.0f));
    L20:
        r15 = r4;
    L9:
        r14 = false;
        goto L10
    }

    public GenerateFromMessageBottomSheet(MessageObject r8, MessageObject.GroupedMessages r9, BaseFragment r10, Context r11, Utilities.Callback<GenerationData> r12) {
        this(Objects.toString(ChatUtils.getInstance().getMessageText(r8, r9), _UrlKt.FRAGMENT_ENCODE_SET), ChatUtils.getInstance().getPathToMessage(r8), r10, r11, r12);
    }

    public GenerateFromMessageBottomSheet(BaseFragment r8, Context r9, Utilities.Callback<GenerationData> r10, boolean r11) {
        this(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, r8, r9, r10, r11);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ Unit lambda$new$2(Boolean r1) {
        this.useHistory = r1.booleanValue();
        return Unit.INSTANCE;
    }

    private /* synthetic */ Unit lambda$new$3(Boolean r1) {
        this.includeImage = r1.booleanValue();
        return Unit.INSTANCE;
    }

    private /* synthetic */ void lambda$new$4(String r3, Utilities.Callback r4, View r5) {
        String r0 = this.promptCell.editText.getText().toString();
        if (TextUtils.isEmpty(r0) == true) goto L5;
    L9:
        dismiss();
        if (this.includeImage == true) goto L13;
        r3 = null;
    L13:
        r4.run(new GenerationData(r0, this.useHistory, r3));
        return;
    L5:
        if (this.includeImage == true) goto L8;
        AndroidUtilities.shakeViewSpring(this.promptCell);
        r5.performHapticFeedback(0);
        return;
    L8:
        r0 = LocaleController.getString(R.string.AttachPhoto);
        goto L9
    }

    private /* synthetic */ void lambda$show$5() {
        EditTextCaption r0 = this.promptCell.editText;
        r0.setSelection(r0.length());
        AndroidUtilities.showKeyboard(this.promptCell.editText);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        AndroidUtilities.runOnUIThread(new GenerateFromMessageBottomSheet$$ExternalSyntheticLambda5(this), 200);
    }
}
