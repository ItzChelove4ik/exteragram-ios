package org.telegram.ui.iv;

/* loaded from: classes7.dex */
public interface RichCaptionHost {
    RichEditText getCaptionEditText();

    BlockRow getRow();

    boolean isPressOnCaption(int r1, int r2);

    void persistCaption();
}
