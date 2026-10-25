package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.google.android.gms.cast.framework.R;

@Keep
/* loaded from: classes4.dex */
public class HelpTextView extends LinearLayout {
    TextView bodyTextView;
    TextView headerTextView;

    @Keep
    public HelpTextView(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    private void setTextAndVisibility(TextView r1, CharSequence r2) {
        r1.setText(r2);
        if (true == TextUtils.isEmpty(r2)) goto L5;
        int r0 = 0;
    L6:
        r1.setVisibility(r0);
        return;
    L5:
        r0 = 8;
        goto L6
    }

    @Keep
    public View asView() {
        return this;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        TextView r0 = (TextView) findViewById(R.id.cast_featurehighlight_help_text_header_view);
        r0.getClass();
        this.headerTextView = r0;
        TextView r02 = (TextView) findViewById(R.id.cast_featurehighlight_help_text_body_view);
        r02.getClass();
        this.bodyTextView = r02;
    }

    @Keep
    public void setText(CharSequence r2, CharSequence r3) {
        setTextAndVisibility(this.headerTextView, r2);
        setTextAndVisibility(this.bodyTextView, r3);
    }
}
