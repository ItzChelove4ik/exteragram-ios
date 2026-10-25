package org.telegram.ui.Storage;

import android.util.LongSparseArray;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.CacheControlActivity;

/* loaded from: classes7.dex */
public class CacheModel {
    public boolean allDocumentsSelected;
    public boolean allMusicSelected;
    public boolean allPhotosSelected;
    public boolean allStoriesSelected;
    public boolean allVideosSelected;
    public boolean allVoiceSelected;
    private final HashSet<Long> dialogIdsTmp;
    public final ArrayList<FileInfo> documents;
    public long documentsSelectedSize;
    public ArrayList<CacheControlActivity.DialogFileEntities> entities;
    private final LongSparseArray<CacheControlActivity.DialogFileEntities> entitiesByDialogId;
    public final boolean isDialog;
    public final ArrayList<FileInfo> media;
    public final ArrayList<FileInfo> music;
    public long musicSelectedSize;
    public long photosSelectedSize;
    public HashSet<Long> selectedDialogs;
    public HashSet<FileInfo> selectedFiles;
    private long selectedSize;
    public final ArrayList<FileInfo> stories;
    public long storiesSelectedSize;
    public long videosSelectedSize;
    public final ArrayList<FileInfo> voice;
    public long voiceSelectedSize;

    public static class FileInfo {
        public long dialogId;
        public final File file;
        public int messageId;
        public MessageObject messageObject;
        public int messageType;
        public FileMetadata metadata;
        public long size;
        public int type;

        public static class FileMetadata {
            public String author;
            public boolean loading;
            public String title;

            public FileMetadata() {
            }
        }

        public FileInfo(File r1) {
            this.file = r1;
        }
    }

    public static /* synthetic */ int $r8$lambda$5Ho1DEdqeZCt_duz_Wk_ub4AxX4(FileInfo r2, FileInfo r3) {
        long r0 = r3.size;
        long r22 = r2.size;
        if (r0 <= r22) goto L6;
        return 1;
    L6:
        if (r0 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    public CacheModel(boolean r2) {
        this.entities = new ArrayList();
        this.entitiesByDialogId = new LongSparseArray();
        this.media = new ArrayList();
        this.documents = new ArrayList();
        this.music = new ArrayList();
        this.voice = new ArrayList();
        this.stories = new ArrayList();
        this.dialogIdsTmp = new HashSet();
        this.selectedFiles = new HashSet();
        this.selectedDialogs = new HashSet();
        this.isDialog = r2;
    }

    private void checkAllFilesSelected(int r6, boolean r7) {
        if (this.isDialog == true) goto L6;
        return;
    L6:
        if (r7 == false) goto L8;
        if (r6 != 0) goto L29;
        this.allPhotosSelected = checkAllFilesSelectedInArray(r6, this.media);
        return;
    L29:
        if (r6 != 1) goto L32;
        this.allVideosSelected = checkAllFilesSelectedInArray(r6, this.media);
        return;
    L32:
        if (r6 != 2) goto L35;
        this.allDocumentsSelected = checkAllFilesSelectedInArray(r6, this.documents);
        return;
    L35:
        if (r6 != 3) goto L38;
        this.allMusicSelected = checkAllFilesSelectedInArray(r6, this.music);
        return;
    L38:
        if (r6 != 4) goto L41;
        this.allVoiceSelected = checkAllFilesSelectedInArray(r6, this.voice);
        return;
    L41:
        if (r6 != 7) goto L45;
        this.allStoriesSelected = checkAllFilesSelectedInArray(r6, this.stories);
        return;
    L45:
        return;
    L8:
        if (r6 != 0) goto L11;
        this.allPhotosSelected = false;
        return;
    L11:
        if (r6 != 1) goto L14;
        this.allVideosSelected = false;
        return;
    L14:
        if (r6 != 2) goto L17;
        this.allDocumentsSelected = false;
        return;
    L17:
        if (r6 != 3) goto L20;
        this.allMusicSelected = false;
        return;
    L20:
        if (r6 != 4) goto L23;
        this.allVoiceSelected = false;
        return;
    L23:
        if (r6 != 7) goto L44;
        this.allStoriesSelected = false;
        return;
    }

    private boolean checkAllFilesSelectedInArray(int r5, ArrayList<FileInfo> r6) {
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L11;
        if (r6.get(r1).type != r5) goto L10;
        if (this.selectedFiles.contains(r6.get(r1)) == true) goto L10;
        return false;
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        return true;
    }

    private void checkSelectedDialogs() {
        if (this.isDialog == true) goto L26;
        this.dialogIdsTmp.clear();
        Iterator<FileInfo> r0 = this.selectedFiles.iterator();
    L7:
        if (r0.hasNext() == false) goto L11;
        long r1 = r0.next().dialogId;
        if (r1 == 0) goto L7;
        this.dialogIdsTmp.add(Long.valueOf(r1));
        goto L7
    L11:
        this.selectedDialogs.clear();
        Iterator<Long> r02 = this.dialogIdsTmp.iterator();
    L13:
        if (r02.hasNext() == false) goto L41;
        CacheControlActivity.DialogFileEntities r12 = this.entitiesByDialogId.get(r02.next().longValue());
        if (r12 == null) goto L13;
        int r3 = 0;
    L18:
        if (r3 >= r12.entitiesByType.size()) goto L25;
        ArrayList<FileInfo> r4 = r12.entitiesByType.valueAt(r3).files;
        int r5 = r4.size();
        int r6 = 0;
    L20:
        if (r6 >= r5) goto L24;
        FileInfo r7 = r4.get(r6);
        r6 = r6 + 1;
        if (this.selectedFiles.contains(r7) == true) goto L20;
    L24:
        r3 = r3 + 1;
        goto L18
    L25:
        this.selectedDialogs.add(Long.valueOf(r12.dialogId));
        goto L13
    L41:
        return;
    }

    private ArrayList<FileInfo> getListByType(int r2) {
        if (r2 != 0) goto L6;
        return this.media;
    L6:
        if (r2 != 1) goto L10;
        return this.media;
    L10:
        if (r2 != 2) goto L14;
        return this.documents;
    L14:
        if (r2 != 3) goto L18;
        return this.music;
    L18:
        if (r2 != 4) goto L22;
        return this.voice;
    L22:
        if (r2 == 7) goto L24;
        return null;
    L24:
        return this.stories;
    }

    private void incSize(FileInfo r3, boolean r4) {
        long r0 = r3.size;
        if (r4 == true) goto L6;
        r0 = -r0;
    L6:
        int r32 = r3.type;
        if (r32 != 0) goto L11;
        this.photosSelectedSize += r0;
        return;
    L11:
        if (r32 != 1) goto L15;
        this.videosSelectedSize += r0;
        return;
    L15:
        if (r32 != 2) goto L19;
        this.documentsSelectedSize += r0;
        return;
    L19:
        if (r32 != 3) goto L23;
        this.musicSelectedSize += r0;
        return;
    L23:
        if (r32 != 4) goto L27;
        this.voiceSelectedSize += r0;
        return;
    L27:
        if (r32 != 7) goto L30;
        this.storiesSelectedSize += r0;
        return;
    }

    private void remove(int r1, FileInfo r2) {
        ArrayList<FileInfo> r0 = getListByType(r1);
        if (r0 == null) goto L6;
        r0.remove(r2);
        return;
    }

    private void sort(ArrayList<FileInfo> r1) {
        Collections.sort(r1, new CacheModel$$ExternalSyntheticLambda0());
    }

    public void add(int r1, FileInfo r2) {
        getListByType(r1).add(r2);
    }

    public void allFilesSelcetedByType(int r7, boolean r8) {
        if (r7 != 0) goto L5;
        ArrayList<FileInfo> r1 = this.media;
        this.allPhotosSelected = r8;
    L20:
        if (r1 == null) goto L35;
        int r3 = 0;
    L23:
        if (r3 >= r1.size()) goto L42;
        if (r1.get(r3).type != r7) goto L34;
        HashSet<FileInfo> r4 = this.selectedFiles;
        if (r8 == false) goto L32;
        if (r4.contains(r1.get(r3)) == true) goto L34;
        this.selectedFiles.add(r1.get(r3));
        incSize(r1.get(r3), true);
        goto L34
    L32:
        if (r4.contains(r1.get(r3)) == false) goto L34;
        this.selectedFiles.remove(r1.get(r3));
        incSize(r1.get(r3), false);
    L34:
        r3 = r3 + 1;
        goto L23
    L42:
        return;
    L35:
        return;
    L5:
        if (r7 != 1) goto L8;
        r1 = this.media;
        this.allVideosSelected = r8;
        goto L20
    L8:
        if (r7 != 2) goto L11;
        r1 = this.documents;
        this.allDocumentsSelected = r8;
        goto L20
    L11:
        if (r7 != 3) goto L14;
        r1 = this.music;
        this.allMusicSelected = r8;
        goto L20
    L14:
        if (r7 != 4) goto L17;
        r1 = this.voice;
        this.allVoiceSelected = r8;
        goto L20
    L17:
        if (r7 != 7) goto L19;
        r1 = this.stories;
        this.allStoriesSelected = r8;
        goto L20
    L19:
        r1 = null;
        goto L20
    }

    public void clearSelection() {
        this.selectedSize = 0;
        this.selectedFiles.clear();
        this.selectedDialogs.clear();
    }

    public int getSelectedFiles() {
        return this.selectedFiles.size();
    }

    public long getSelectedFilesSize() {
        return this.selectedSize;
    }

    public long getSelectedFilesSize(int r2) {
        if (r2 != 0) goto L6;
        return this.photosSelectedSize;
    L6:
        if (r2 != 1) goto L10;
        return this.videosSelectedSize;
    L10:
        if (r2 != 2) goto L14;
        return this.documentsSelectedSize;
    L14:
        if (r2 != 3) goto L18;
        return this.musicSelectedSize;
    L18:
        if (r2 == 4) goto L20;
        return -1;
    L20:
        return this.voiceSelectedSize;
    }

    public boolean isEmpty() {
        if (this.media.isEmpty() == true) goto L5;
        return false;
    L5:
        if (this.documents.isEmpty() == true) goto L7;
        return false;
    L7:
        if (this.music.isEmpty() == true) goto L9;
        return false;
    L9:
        if (this.isDialog == false) goto L11;
        return true;
    L11:
        if (this.entities.isEmpty() == false) goto L19;
        return true;
    L19:
        return false;
    }

    public boolean isSelected(long r1) {
        return this.selectedDialogs.contains(Long.valueOf(r1));
    }

    public boolean isSelected(FileInfo r1) {
        return this.selectedFiles.contains(r1);
    }

    public void onFileDeleted(FileInfo r5) {
        if (this.selectedFiles.remove(r5) == false) goto L5;
        this.selectedSize -= r5.size;
    L5:
        remove(r5.type, r5);
    }

    public void remove(CacheControlActivity.DialogFileEntities r1) {
        this.entities.remove(r1);
    }

    public CacheControlActivity.DialogFileEntities removeSelectedFiles() {
        CacheControlActivity.DialogFileEntities r0 = new CacheControlActivity.DialogFileEntities(0);
        Iterator<FileInfo> r1 = this.selectedFiles.iterator();
    L4:
        if (r1.hasNext() == false) goto L12;
        FileInfo r2 = r1.next();
        r0.addFile(r2, r2.type);
        CacheControlActivity.DialogFileEntities r3 = this.entitiesByDialogId.get(r2.dialogId);
        if (r3 == null) goto L4;
        r3.removeFile(r2);
        if (r3.isEmpty() == false) goto L11;
        this.entitiesByDialogId.remove(r2.dialogId);
        this.entities.remove(r3);
    L11:
        remove(r2.type, r2);
        goto L4
    L12:
        return r0;
    }

    public void selectAllFiles() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.media.size()) goto L10;
        this.selectedFiles.add(this.media.get(r1));
        if (this.media.get(r1).type != 0) goto L8;
        this.photosSelectedSize += this.media.get(r1).size;
    L9:
        r1 = r1 + 1;
        goto L4
    L8:
        this.videosSelectedSize += this.media.get(r1).size;
        goto L9
    L10:
        int r12 = 0;
    L12:
        if (r12 >= this.documents.size()) goto L14;
        this.selectedFiles.add(this.documents.get(r12));
        this.documentsSelectedSize += this.documents.get(r12).size;
        r12 = r12 + 1;
        goto L12
    L14:
        int r13 = 0;
    L16:
        if (r13 >= this.music.size()) goto L19;
        this.selectedFiles.add(this.music.get(r13));
        this.musicSelectedSize += this.music.get(r13).size;
        r13 = r13 + 1;
    L19:
        if (r0 >= this.voice.size()) goto L21;
        this.selectedFiles.add(this.voice.get(r0));
        this.voiceSelectedSize += this.voice.get(r0).size;
        r0 = r0 + 1;
        goto L19
    L21:
        this.allPhotosSelected = true;
        this.allVideosSelected = true;
        this.allDocumentsSelected = true;
        this.allMusicSelected = true;
        this.allVoiceSelected = true;
    }

    public void setEntities(ArrayList<CacheControlActivity.DialogFileEntities> r7) {
        this.entities = r7;
        this.entitiesByDialogId.clear();
        int r0 = r7.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        CacheControlActivity.DialogFileEntities r2 = r7.get(r1);
        r1 = r1 + 1;
        CacheControlActivity.DialogFileEntities r22 = r2;
        this.entitiesByDialogId.put(r22.dialogId, r22);
        goto L3
    }

    public void sortBySize() {
        sort(this.media);
        sort(this.documents);
        sort(this.music);
        sort(this.voice);
        sort(this.stories);
    }

    public void toggleSelect(CacheControlActivity.DialogFileEntities r11) {
        if (this.selectedDialogs.contains(Long.valueOf(r11.dialogId)) == true) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= r11.entitiesByType.size()) goto L22;
        ArrayList<FileInfo> r2 = r11.entitiesByType.valueAt(r0).files;
        int r3 = r2.size();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L12;
        FileInfo r5 = r2.get(r4);
        r4 = r4 + 1;
        FileInfo r52 = r5;
        if (this.selectedFiles.add(r52) == false) goto L8;
        this.selectedSize += r52.size;
        goto L8
    L12:
        r0 = r0 + 1;
    L22:
        checkSelectedDialogs();
        return;
    L13:
        int r02 = 0;
    L15:
        if (r02 >= r11.entitiesByType.size()) goto L22;
        ArrayList<FileInfo> r22 = r11.entitiesByType.valueAt(r02).files;
        int r32 = r22.size();
        int r42 = 0;
    L17:
        if (r42 >= r32) goto L21;
        FileInfo r53 = r22.get(r42);
        r42 = r42 + 1;
        FileInfo r54 = r53;
        if (this.selectedFiles.remove(r54) == false) goto L17;
        this.selectedSize -= r54.size;
        goto L17
    L21:
        r02 = r02 + 1;
        goto L15
    }

    public void toggleSelect(FileInfo r6) {
        boolean r0 = this.selectedFiles.contains(r6);
        HashSet<FileInfo> r1 = this.selectedFiles;
        if (r0 == false) goto L5;
        r1.remove(r6);
        incSize(r6, false);
        this.selectedSize -= r6.size;
        checkAllFilesSelected(r6.type, false);
    L6:
        checkSelectedDialogs();
        return;
    L5:
        r1.add(r6);
        incSize(r6, true);
        this.selectedSize += r6.size;
        checkAllFilesSelected(r6.type, true);
        goto L6
    }
}
