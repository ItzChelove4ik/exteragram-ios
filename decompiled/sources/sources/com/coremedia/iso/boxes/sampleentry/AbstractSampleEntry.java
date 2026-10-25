package com.coremedia.iso.boxes.sampleentry;

import com.coremedia.iso.boxes.Box;
import com.googlecode.mp4parser.AbstractContainerBox;

/* loaded from: classes4.dex */
public abstract class AbstractSampleEntry extends AbstractContainerBox implements Box {
    protected int dataReferenceIndex;

    public AbstractSampleEntry(String r1) {
        super(r1);
        this.dataReferenceIndex = 1;
    }

    public void setDataReferenceIndex(int r1) {
        this.dataReferenceIndex = r1;
    }
}
