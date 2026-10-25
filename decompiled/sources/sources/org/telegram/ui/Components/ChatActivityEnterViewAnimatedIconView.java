package org.telegram.ui.Components;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Cells.ShareDialogCell$RepostStoryDrawable$$ExternalSyntheticLambda0;

/* loaded from: classes7.dex */
public class ChatActivityEnterViewAnimatedIconView extends RLottieImageView {
    private TransitState animatingState;
    private State currentState;
    private final int sizeDp;
    private final Map<TransitState, RLottieDrawable> stateMap;

    /* renamed from: org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView$1, reason: invalid class name */
    public class AnonymousClass1 extends HashMap<TransitState, RLottieDrawable> {
        final /* synthetic */ ChatActivityEnterViewAnimatedIconView this$0;

        public AnonymousClass1(ChatActivityEnterViewAnimatedIconView r1) {
            this.this$0 = r1;
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public /* bridge */ /* synthetic */ Object get(Object r1) {
            return get(r1);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public RLottieDrawable get(Object r5) {
            RLottieDrawable r0 = (RLottieDrawable) super.get(r5);
            if (r0 != null) goto L6;
            TransitState r52 = (TransitState) r5;
            Objects.requireNonNull(r52);
            RLottieDrawable r1 = new RLottieDrawable(r52.resource, AndroidUtilities.dp(ChatActivityEnterViewAnimatedIconView.m9401$$Nest$fgetsizeDp(this.this$0)), AndroidUtilities.dp(ChatActivityEnterViewAnimatedIconView.m9401$$Nest$fgetsizeDp(this.this$0)));
            put(r52, r1);
            return r1;
        L6:
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Components$ChatActivityEnterViewAnimatedIconView$State = null;

        static {
            int[] r0 = new int[State.values().length];
            $SwitchMap$org$telegram$ui$Components$ChatActivityEnterViewAnimatedIconView$State = r0;
            r0[State.VOICE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$org$telegram$ui$Components$ChatActivityEnterViewAnimatedIconView$State[State.VIDEO.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
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

        private static /* synthetic */ State[] $values() {
            return new State[]{VOICE, VIDEO, STICKER, KEYBOARD, SMILE, GIF};
        }

        static {
            VOICE = new State("VOICE", 0);
            VIDEO = new State("VIDEO", 1);
            STICKER = new State("STICKER", 2);
            KEYBOARD = new State("KEYBOARD", 3);
            SMILE = new State("SMILE", 4);
            GIF = new State("GIF", 5);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public enum TransitState extends Enum<TransitState> {
        private static final /* synthetic */ TransitState[] $VALUES = null;
        public static final TransitState GIF_TO_KEYBOARD = null;
        public static final TransitState GIF_TO_SMILE = null;
        public static final TransitState KEYBOARD_TO_GIF = null;
        public static final TransitState KEYBOARD_TO_SMILE = null;
        public static final TransitState KEYBOARD_TO_STICKER = null;
        public static final TransitState SMILE_TO_GIF = null;
        public static final TransitState SMILE_TO_KEYBOARD = null;
        public static final TransitState SMILE_TO_STICKER = null;
        public static final TransitState STICKER_TO_KEYBOARD = null;
        public static final TransitState STICKER_TO_SMILE = null;
        public static final TransitState VIDEO_TO_VOICE = null;
        public static final TransitState VOICE_TO_VIDEO = null;
        final State firstState;
        final int resource;
        final State secondState;

        private static /* synthetic */ TransitState[] $values() {
            return new TransitState[]{VOICE_TO_VIDEO, STICKER_TO_KEYBOARD, SMILE_TO_KEYBOARD, VIDEO_TO_VOICE, KEYBOARD_TO_STICKER, KEYBOARD_TO_GIF, KEYBOARD_TO_SMILE, GIF_TO_KEYBOARD, GIF_TO_SMILE, SMILE_TO_GIF, SMILE_TO_STICKER, STICKER_TO_SMILE};
        }

        static {
            State r3 = State.VOICE;
            State r4 = State.VIDEO;
            VOICE_TO_VIDEO = new TransitState("VOICE_TO_VIDEO", 0, r3, r4, R.raw.voice_and_video);
            State r10 = State.STICKER;
            State r9 = State.KEYBOARD;
            STICKER_TO_KEYBOARD = new TransitState("STICKER_TO_KEYBOARD", 1, r10, r9, R.raw.sticker_to_keyboard);
            State r15 = State.SMILE;
            SMILE_TO_KEYBOARD = new TransitState("SMILE_TO_KEYBOARD", 2, r15, r9, R.raw.smile_to_keyboard);
            VIDEO_TO_VOICE = new TransitState("VIDEO_TO_VOICE", 3, r4, r3, R.raw.voice_and_video);
            KEYBOARD_TO_STICKER = new TransitState("KEYBOARD_TO_STICKER", 4, r9, r10, R.raw.keyboard_to_sticker);
            State r152 = State.GIF;
            KEYBOARD_TO_GIF = new TransitState("KEYBOARD_TO_GIF", 5, r9, r152, R.raw.keyboard_to_gif);
            KEYBOARD_TO_SMILE = new TransitState("KEYBOARD_TO_SMILE", 6, r9, r15, R.raw.keyboard_to_smile);
            GIF_TO_KEYBOARD = new TransitState("GIF_TO_KEYBOARD", 7, r152, r9, R.raw.gif_to_keyboard);
            GIF_TO_SMILE = new TransitState("GIF_TO_SMILE", 8, r152, r15, R.raw.gif_to_smile);
            SMILE_TO_GIF = new TransitState("SMILE_TO_GIF", 9, r15, r152, R.raw.smile_to_gif);
            SMILE_TO_STICKER = new TransitState("SMILE_TO_STICKER", 10, r15, r10, R.raw.smile_to_sticker);
            STICKER_TO_SMILE = new TransitState("STICKER_TO_SMILE", 11, r10, r15, R.raw.sticker_to_smile);
            $VALUES = $values();
        }

        TransitState(String r1, int r2, State r3, State r4, int r5) {
            this.firstState = r3;
            this.secondState = r4;
            this.resource = r5;
        }

        public static TransitState valueOf(String r1) {
            return (TransitState) Enum.valueOf(TransitState.class, r1);
        }

        public static TransitState[] values() {
            return (TransitState[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$hfs7wppcf2l7fjvSBHeWY_d9tEY(ChatActivityEnterViewAnimatedIconView r0) {
        r0.lambda$setState$0();
    }

    /* renamed from: -$$Nest$fgetsizeDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9401$$Nest$fgetsizeDp(ChatActivityEnterViewAnimatedIconView r0) {
        return r0.sizeDp;
    }

    public ChatActivityEnterViewAnimatedIconView(Context r2) {
        this(r2, 32);
    }

    public ChatActivityEnterViewAnimatedIconView(Context r1, int r2) {
        super(r1);
        this.stateMap = new AnonymousClass1(this);
        this.sizeDp = r2;
    }

    private TransitState getAnyState(State r5) {
        TransitState[] r4 = TransitState.values();
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        TransitState r2 = r4[r1];
        if (r2.firstState == r5) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    private TransitState getState(State r5, State r6) {
        TransitState[] r4 = TransitState.values();
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        TransitState r2 = r4[r1];
        if (r2.firstState != r5) goto L9;
        if (r2.secondState != r6) goto L9;
        return r2;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return null;
    }

    private /* synthetic */ void lambda$setState$0() {
        this.animatingState = null;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setState(State r6, boolean r7) {
        if (r7 == true) goto L4;
    L6:
        State r0 = this.currentState;
        this.currentState = r6;
        float r1 = 0.5f;
        if (r7 == false) goto L23;
        if (r0 == null) goto L23;
        if (getState(r0, r6) == null) goto L23;
        TransitState r72 = getState(r0, this.currentState);
        if (r72 == this.animatingState) goto L38;
        this.animatingState = r72;
        RLottieDrawable r02 = this.stateMap.get(r72);
        Objects.requireNonNull(r02);
        r02.stop();
        if (r72 != TransitState.VIDEO_TO_VOICE) goto L19;
        r02.setCustomEndFrame(30);
        r02.setProgress(0.0f, false);
    L22:
        r02.setAutoRepeat(0);
        r02.setOnAnimationEndListener(new ChatActivityEnterViewAnimatedIconView$$ExternalSyntheticLambda0(this));
        setAnimation(r02);
        AndroidUtilities.runOnUIThread(new ShareDialogCell$RepostStoryDrawable$$ExternalSyntheticLambda0(r02));
    L28:
        int r62 = AnonymousClass2.$SwitchMap$org$telegram$ui$Components$ChatActivityEnterViewAnimatedIconView$State[r6.ordinal()];
        if (r62 != 1) goto L31;
        setContentDescription(LocaleController.getString(R.string.AccDescrVoiceMessage));
        return;
    L31:
        if (r62 == 2) goto L33;
        return;
    L33:
        setContentDescription(LocaleController.getString(R.string.AccDescrVideoMessage));
        return;
    L19:
        if (r72 != TransitState.VOICE_TO_VIDEO) goto L21;
        r02.setCustomEndFrame(60);
        r02.setProgress(0.5f, false);
        goto L22
    L21:
        r02.setProgress(0.0f, false);
        goto L22
    L38:
        return;
    L23:
        RLottieDrawable r73 = this.stateMap.get(getAnyState(this.currentState));
        Objects.requireNonNull(r73);
        r73.stop();
        if (r6 == State.VOICE) goto L27;
        r1 = 0.0f;
    L27:
        r73.setProgress(r1, false);
        setAnimation(r73);
        goto L28
    L4:
        if (r6 != this.currentState) goto L6;
    }
}
