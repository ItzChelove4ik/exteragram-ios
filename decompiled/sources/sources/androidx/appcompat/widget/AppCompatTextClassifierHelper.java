package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
final class AppCompatTextClassifierHelper {
    private TextClassifier mTextClassifier;
    private TextView mTextView;

    public static final class Api26Impl {
        public static TextClassifier getTextClassifier(TextView r1) {
            TextClassificationManager r12 = (TextClassificationManager) r1.getContext().getSystemService(TextClassificationManager.class);
            if (r12 == null) goto L7;
            return r12.getTextClassifier();
        L7:
            return TextClassifier.NO_OP;
        }
    }

    public AppCompatTextClassifierHelper(TextView r1) {
        this.mTextView = (TextView) Preconditions.checkNotNull(r1);
    }

    public TextClassifier getTextClassifier() {
        TextClassifier r0 = this.mTextClassifier;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return Api26Impl.getTextClassifier(this.mTextView);
    }

    public void setTextClassifier(TextClassifier r1) {
        this.mTextClassifier = r1;
    }
}
