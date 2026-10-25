package com.coremedia.iso.boxes;

import java.nio.channels.WritableByteChannel;

/* loaded from: classes4.dex */
public interface Box {
    void getBox(WritableByteChannel r1);

    Container getParent();

    long getSize();

    String getType();

    void setParent(Container r1);
}
