package org.telegram.ui;

import org.telegram.messenger.MediaController;
import org.telegram.ui.Cells.PhotoPickerAlbumsCell;
import org.telegram.ui.PhotoAlbumPickerActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoAlbumPickerActivity$ListAdapter$$ExternalSyntheticLambda0 implements PhotoPickerAlbumsCell.PhotoPickerAlbumsCellDelegate {
    public final /* synthetic */ PhotoAlbumPickerActivity.ListAdapter f$0;

    public /* synthetic */ PhotoAlbumPickerActivity$ListAdapter$$ExternalSyntheticLambda0(PhotoAlbumPickerActivity.ListAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Cells.PhotoPickerAlbumsCell.PhotoPickerAlbumsCellDelegate
    public final void didSelectAlbum(MediaController.AlbumEntry r1) {
        PhotoAlbumPickerActivity.ListAdapter.m16787$r8$lambda$hu4vJIGLjwbLOkH9f0rDPL98xI(this.f$0, r1);
    }
}
