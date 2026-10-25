package org.telegram.ui.Components.voip;

import android.view.View;
import androidx.annotation.Keep;

/* loaded from: classes7.dex */
public abstract class CallSwipeView extends View {

    public class ArrowAnimWrapper {
        private int index;

        @Keep
        public int getArrowAlpha() {
            return CallSwipeView.m13810$$Nest$fgetarrowAlphas(null)[this.index];
        }

        @Keep
        public void setArrowAlpha(int r2) {
            CallSwipeView.m13810$$Nest$fgetarrowAlphas(null)[this.index] = r2;
        }
    }

    /* renamed from: -$$Nest$fgetarrowAlphas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m13810$$Nest$fgetarrowAlphas(CallSwipeView r0) {
        throw null;
    }
}
