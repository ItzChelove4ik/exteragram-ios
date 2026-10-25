package io.noties.markwon.ext.latex;

/* loaded from: classes5.dex */
public abstract class JLatexMathTheme {

    public interface BackgroundProvider {
    }

    public static class Builder {
        private boolean blockFitCanvas;
        private int blockHorizontalAlignment;
        private int blockTextColor;
        private final float blockTextSize;
        private int inlineTextColor;
        private final float inlineTextSize;
        private int textColor;
        private final float textSize;

        public Builder(float r2, float r3, float r4) {
            this.blockFitCanvas = true;
            this.blockHorizontalAlignment = 1;
            this.textSize = r2;
            this.inlineTextSize = r3;
            this.blockTextSize = r4;
        }

        public static /* synthetic */ float access$000(Builder r0) {
            return r0.textSize;
        }

        public static /* synthetic */ float access$100(Builder r0) {
            return r0.inlineTextSize;
        }

        public static /* synthetic */ Padding access$1000(Builder r0) {
            r0.getClass();
            return null;
        }

        public static /* synthetic */ int access$1100(Builder r0) {
            return r0.textColor;
        }

        public static /* synthetic */ int access$1200(Builder r0) {
            return r0.inlineTextColor;
        }

        public static /* synthetic */ int access$1300(Builder r0) {
            return r0.blockTextColor;
        }

        public static /* synthetic */ float access$200(Builder r0) {
            return r0.blockTextSize;
        }

        public static /* synthetic */ BackgroundProvider access$300(Builder r0) {
            r0.getClass();
            return null;
        }

        public static /* synthetic */ BackgroundProvider access$400(Builder r0) {
            r0.getClass();
            return null;
        }

        public static /* synthetic */ BackgroundProvider access$500(Builder r0) {
            r0.getClass();
            return null;
        }

        public static /* synthetic */ boolean access$600(Builder r0) {
            return r0.blockFitCanvas;
        }

        public static /* synthetic */ int access$700(Builder r0) {
            return r0.blockHorizontalAlignment;
        }

        public static /* synthetic */ Padding access$800(Builder r0) {
            r0.getClass();
            return null;
        }

        public static /* synthetic */ Padding access$900(Builder r0) {
            r0.getClass();
            return null;
        }

        public JLatexMathTheme build() {
            return new Impl(this);
        }
    }

    public static class Impl extends JLatexMathTheme {
        private final boolean blockFitCanvas;
        private int blockHorizontalAlignment;
        private final int blockTextColor;
        private final float blockTextSize;
        private final int inlineTextColor;
        private final float inlineTextSize;
        private final int textColor;
        private final float textSize;

        public Impl(Builder r2) {
            this.textSize = Builder.access$000(r2);
            this.inlineTextSize = Builder.access$100(r2);
            this.blockTextSize = Builder.access$200(r2);
            Builder.access$300(r2);
            Builder.access$400(r2);
            Builder.access$500(r2);
            this.blockFitCanvas = Builder.access$600(r2);
            this.blockHorizontalAlignment = Builder.access$700(r2);
            Builder.access$800(r2);
            Builder.access$900(r2);
            Builder.access$1000(r2);
            this.textColor = Builder.access$1100(r2);
            this.inlineTextColor = Builder.access$1200(r2);
            this.blockTextColor = Builder.access$1300(r2);
        }

        @Override // io.noties.markwon.ext.latex.JLatexMathTheme
        public boolean blockFitCanvas() {
            return this.blockFitCanvas;
        }
    }

    public static class Padding {
    }

    public JLatexMathTheme() {
    }

    public static Builder builder(float r2) {
        return new Builder(r2, 0.0f, 0.0f);
    }

    public abstract boolean blockFitCanvas();
}
