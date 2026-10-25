package com.exteragram.messenger.utils.ui;

import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes.dex */
public class TextPaint extends android.text.TextPaint {
    public TextPaint(int r1) {
        super(r1);
        setTypeface(AndroidUtilities.regular());
    }
}
