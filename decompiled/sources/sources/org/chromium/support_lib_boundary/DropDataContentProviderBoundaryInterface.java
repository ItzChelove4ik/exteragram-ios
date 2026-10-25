package org.chromium.support_lib_boundary;

import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

/* loaded from: classes5.dex */
public interface DropDataContentProviderBoundaryInterface {
    Uri cache(byte[] r1, String r2, String r3);

    Bundle call(String r1, String r2, Bundle r3);

    String[] getStreamTypes(Uri r1, String r2);

    String getType(Uri r1);

    boolean onCreate();

    void onDragEnd(boolean r1);

    ParcelFileDescriptor openFile(ContentProvider r1, Uri r2);

    Cursor query(Uri r1, String[] r2, String r3, String[] r4, String r5);

    void setClearCachedDataIntervalMs(int r1);
}
