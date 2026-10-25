package com.exteragram.messenger.components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class ChatActivityEnterViewStaticIconView extends FrameLayout {
    private final ImageView[] buttonViews;
    private AnimatorSet buttonsAnimation;
    private State currentState;
    private final int sizeDp;

    /* renamed from: com.exteragram.messenger.components.ChatActivityEnterViewStaticIconView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatActivityEnterViewStaticIconView this$0;

        public AnonymousClass1(ChatActivityEnterViewStaticIconView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            if (r5.equals(ChatActivityEnterViewStaticIconView.m1196$$Nest$fgetbuttonsAnimation(this.this$0)) == false) goto L6;
            ChatActivityEnterViewStaticIconView.m1197$$Nest$fputbuttonsAnimation(this.this$0, null);
            ImageView r52 = ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[1];
            ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[1] = ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[0];
            ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[0] = r52;
            ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[1].setVisibility(4);
            ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[1].setAlpha(0.0f);
            ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[1].setScaleX(0.1f);
            ChatActivityEnterViewStaticIconView.m1195$$Nest$fgetbuttonViews(this.this$0)[1].setScaleY(0.1f);
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.components.ChatActivityEnterViewStaticIconView$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$components$ChatActivityEnterViewStaticIconView$State = null;

        static {
            int[] r0 = new int[State.values().length];
            $SwitchMap$com$exteragram$messenger$components$ChatActivityEnterViewStaticIconView$State = r0;
            r0[State.VOICE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$com$exteragram$messenger$components$ChatActivityEnterViewStaticIconView$State[State.VIDEO.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State GIF = null;
        public static final State KEYBOARD = null;
        public static final State SMILE = null;
        public static final State STICKER = null;
        public static final State VIDEO = null;
        public static final State VOICE = null;
        final int resource;

        private static /* synthetic */ State[] $values() {
            return new State[]{VOICE, VIDEO, STICKER, KEYBOARD, SMILE, GIF};
        }

        static {
            VOICE = new State("VOICE", 0, R.drawable.input_mic_pressed);
            VIDEO = new State("VIDEO", 1, R.drawable.input_video_pressed);
            STICKER = new State("STICKER", 2, R.drawable.msg_sticker);
            KEYBOARD = new State("KEYBOARD", 3, R.drawable.input_keyboard);
            SMILE = new State("SMILE", 4, R.drawable.input_smile);
            GIF = new State("GIF", 5, R.drawable.msg_gif);
            $VALUES = $values();
        }

        State(String r1, int r2, int r3) {
            this.resource = r3;
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    /* renamed from: -$$Nest$fgetbuttonViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView[] m1195$$Nest$fgetbuttonViews(ChatActivityEnterViewStaticIconView r0) {
        return r0.buttonViews;
    }

    /* renamed from: -$$Nest$fgetbuttonsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m1196$$Nest$fgetbuttonsAnimation(ChatActivityEnterViewStaticIconView r0) {
        return r0.buttonsAnimation;
    }

    /* renamed from: -$$Nest$fputbuttonsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1197$$Nest$fputbuttonsAnimation(ChatActivityEnterViewStaticIconView r0, AnimatorSet r1) {
        r0.buttonsAnimation = r1;
    }

    public ChatActivityEnterViewStaticIconView(Context r2, ChatActivityEnterView r3) {
        this(r2, r3, 32);
    }

    public ChatActivityEnterViewStaticIconView(Context r8, ChatActivityEnterView r9, int r10) {
        super(r8);
        this.buttonViews = new ImageView[2];
        setWillNotDraw(false);
        this.sizeDp = r10;
        int r2 = 0;
    L3:
        ImageView[] r3 = this.buttonViews;
        if (r2 >= 2) goto L6;
        r3[r2] = new ImageView(r8);
        this.buttonViews[r2].setColorFilter(new PorterDuffColorFilter(r9.getThemedColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        this.buttonViews[r2].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        addView(this.buttonViews[r2], LayoutHelper.createFrame(r10, r10, 17));
        r2 = r2 + 1;
        goto L3
    L6:
        r3[0].setVisibility(0);
        this.buttonViews[1].setVisibility(8);
        this.buttonViews[1].setScaleX(0.1f);
        this.buttonViews[1].setScaleY(0.1f);
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setColorFilter(ColorFilter r3) {
        this.buttonViews[0].setColorFilter(r3);
        this.buttonViews[1].setColorFilter(r3);
    }

    public void setState(State r13, boolean r14) {
        if (r14 == true) goto L4;
    L6:
        State r0 = this.currentState;
        this.currentState = r13;
        if (r14 == false) goto L14;
        if (r0 == null) goto L14;
        AnimatorSet r142 = this.buttonsAnimation;
        if (r142 == null) goto L13;
        r142.cancel();
    L13:
        this.buttonViews[1].setVisibility(0);
        this.buttonViews[1].setImageResource(this.currentState.resource);
        this.buttonViews[0].setAlpha(1.0f);
        this.buttonViews[0].setScaleX(1.0f);
        this.buttonViews[0].setScaleY(1.0f);
        this.buttonViews[1].setAlpha(0.0f);
        this.buttonViews[1].setScaleX(0.1f);
        this.buttonViews[1].setScaleY(0.1f);
        AnimatorSet r143 = new AnimatorSet();
        this.buttonsAnimation = r143;
        Property r8 = View.SCALE_X;
        ObjectAnimator r6 = ObjectAnimator.ofFloat(this.buttonViews[0], r8, new float[]{0.1f});
        Property r5 = View.SCALE_Y;
        ObjectAnimator r7 = ObjectAnimator.ofFloat(this.buttonViews[0], r5, new float[]{0.1f});
        Property r02 = View.ALPHA;
        r143.playTogether(new Animator[]{r6, r7, ObjectAnimator.ofFloat(this.buttonViews[0], r02, new float[]{0.0f}), ObjectAnimator.ofFloat(this.buttonViews[1], r8, new float[]{1.0f}), ObjectAnimator.ofFloat(this.buttonViews[1], r5, new float[]{1.0f}), ObjectAnimator.ofFloat(this.buttonViews[1], r02, new float[]{1.0f})});
        this.buttonsAnimation.addListener(new AnonymousClass1(this));
        this.buttonsAnimation.setDuration(200);
        this.buttonsAnimation.start();
    L18:
        int r132 = AnonymousClass2.$SwitchMap$com$exteragram$messenger$components$ChatActivityEnterViewStaticIconView$State[r13.ordinal()];
        if (r132 == 1) goto L24;
        if (r132 == 2) goto L22;
        return;
    L22:
        setContentDescription(LocaleController.getString(R.string.AccDescrVideoMessage));
        return;
    L24:
        setContentDescription(LocaleController.getString(R.string.AccDescrVoiceMessage));
        return;
    L14:
        AnimatorSet r144 = this.buttonsAnimation;
        if (r144 == null) goto L17;
        r144.cancel();
        this.buttonsAnimation = null;
    L17:
        this.buttonViews[0].setImageResource(this.currentState.resource);
        this.buttonViews[0].setAlpha(1.0f);
        this.buttonViews[0].setScaleX(1.0f);
        this.buttonViews[0].setScaleY(1.0f);
        this.buttonViews[0].setVisibility(0);
        this.buttonViews[1].setVisibility(8);
        goto L18
    L4:
        if (r13 != this.currentState) goto L6;
    }
}
