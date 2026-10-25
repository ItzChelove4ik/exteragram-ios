package com.android.dx.dex.file;

import com.android.dx.rop.cst.Constant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HeaderSection extends UniformItemSection {
    private final List<HeaderItem> list;

    public HeaderSection(DexFile r3) {
        super(null, r3, 4);
        HeaderItem r32 = new HeaderItem();
        r32.setIndex(0);
        this.list = Collections.singletonList(r32);
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public IndexedItem get(Constant r1) {
        return null;
    }

    @Override // com.android.dx.dex.file.Section
    public Collection<? extends Item> items() {
        return this.list;
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
    }
}
