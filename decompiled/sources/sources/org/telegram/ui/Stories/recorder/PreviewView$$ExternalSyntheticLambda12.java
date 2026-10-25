package org.telegram.ui.Stories.recorder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public final /* synthetic */ class PreviewView$$ExternalSyntheticLambda12 implements StoryEntry.DecodeBitmap {
    public final /* synthetic */ PreviewView f$0;
    public final /* synthetic */ StoryEntry f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ PreviewView$$ExternalSyntheticLambda12(PreviewView r1, StoryEntry r2, long r3, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.ui.Stories.recorder.StoryEntry.DecodeBitmap
    public final Bitmap decode(BitmapFactory.Options r7) {
        return PreviewView.m20152$r8$lambda$vTyITAeWgntpjJfLjvEqgXkFGY(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
