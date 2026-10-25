package org.telegram.ui.Components.chat.buttons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CounterView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatActivityBlurredRoundPageDownButton extends FrameLayout {
    private ChatActivityBlurredRoundButton buttonView;
    private CounterView counterView;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean reversedCounter;

    public ChatActivityBlurredRoundPageDownButton(Context r1, Theme.ResourcesProvider r2) {
        super(r1);
        this.resourcesProvider = r2;
    }

    public static ChatActivityBlurredRoundPageDownButton create(Context r7, int r8, int r9, Theme.ResourcesProvider r10, BlurredBackgroundDrawableViewFactory r11, BlurredBackgroundColorProvider r12, int r13) {
        ChatActivityBlurredRoundPageDownButton r0 = new ChatActivityBlurredRoundPageDownButton(r7, r10);
        r0.addButtonView(ChatActivityBlurredRoundButton.create(r7, r11, r12, r10, r13, r9), r8);
        ScaleStateListAnimator.apply(r0, 0.13f, 2.0f);
        return r0;
    }

    public void addButtonView(ChatActivityBlurredRoundButton r2, int r3) {
        this.buttonView = r2;
        addView(r2, LayoutHelper.createFrame(r3, r3, 80));
        r2.setIconPadding(AndroidUtilities.dp(2.0f));
    }

    public void reverseCounter() {
        this.reversedCounter = true;
        CounterView r1 = this.counterView;
        if (r1 == null) goto L6;
        r1.setReverse(true);
        return;
    }

    public void reverseIconByY() {
        this.buttonView.reverseIconByY();
    }

    public void setCount(int r5, boolean r6) {
        if (this.counterView != null) goto L5;
        CounterView r0 = new CounterView(getContext(), this.resourcesProvider);
        this.counterView = r0;
        r0.setReverse(this.reversedCounter);
        addView(this.counterView, LayoutHelper.createFrame(-1, 28, 48));
    L5:
        this.counterView.setCount(r5, r6);
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        setEnabled(r2, false);
    }

    public void setEnabled(boolean r1, boolean r2) {
        super.setEnabled(r1);
        this.buttonView.setEnabled(r1, r2);
    }

    public void showLoading(boolean r1, boolean r2) {
        this.buttonView.showLoading(r1, r2);
    }

    public void updateColors() {
        ChatActivityBlurredRoundButton r0 = this.buttonView;
        if (r0 == null) goto L6;
        r0.updateColors();
        invalidate();
        return;
    }
}
