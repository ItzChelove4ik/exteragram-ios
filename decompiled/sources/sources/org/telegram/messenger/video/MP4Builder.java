package org.telegram.messenger.video;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.coremedia.iso.BoxParser;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.IsoTypeWriter;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.CompositionTimeToSample;
import com.coremedia.iso.boxes.Container;
import com.coremedia.iso.boxes.DataEntryUrlBox;
import com.coremedia.iso.boxes.DataInformationBox;
import com.coremedia.iso.boxes.DataReferenceBox;
import com.coremedia.iso.boxes.FileTypeBox;
import com.coremedia.iso.boxes.HandlerBox;
import com.coremedia.iso.boxes.MediaBox;
import com.coremedia.iso.boxes.MediaHeaderBox;
import com.coremedia.iso.boxes.MediaInformationBox;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.coremedia.iso.boxes.SampleSizeBox;
import com.coremedia.iso.boxes.SampleTableBox;
import com.coremedia.iso.boxes.SampleToChunkBox;
import com.coremedia.iso.boxes.StaticChunkOffsetBox;
import com.coremedia.iso.boxes.SyncSampleBox;
import com.coremedia.iso.boxes.TimeToSampleBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.google.android.gms.cast.MediaStatus;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.util.Matrix;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes.dex */
public class MP4Builder {
    private boolean allowSyncFiles;
    private Mp4Movie currentMp4Movie;
    private long dataOffset;
    private FileChannel fc;
    private FileOutputStream fos;
    private InterleaveChunkMdat mdat;
    private ByteBuffer sizeBuffer;
    private boolean splitMdat;
    private HashMap<Track, long[]> track2SampleSizes;
    private boolean wasFirstVideoFrame;
    private boolean writeNewMdat;
    private long wroteSinceLastMdat;

    /* loaded from: classes6.dex */
    public static class InterleaveChunkMdat implements Box {
        private long contentSize;
        private long dataOffset;
        private Container parent;

        private InterleaveChunkMdat() {
            this.contentSize = 1073741824;
            this.dataOffset = 0;
        }

        public /* synthetic */ InterleaveChunkMdat(MP4BuilderIA r1) {
            this();
        }

        private boolean isSmallBox(long r3) {
            if ((r3 + 8) >= 4294967296L) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.coremedia.iso.boxes.Box
        public void getBox(WritableByteChannel r6) {
            ByteBuffer r0 = ByteBuffer.allocate(16);
            long r1 = getSize();
            if (isSmallBox(r1) == false) goto L5;
            IsoTypeWriter.writeUInt32(r0, r1);
        L6:
            r0.put(IsoFile.fourCCtoBytes("mdat"));
            if (isSmallBox(r1) == false) goto L9;
            r0.put(new byte[8]);
        L10:
            r0.rewind();
            r6.write(r0);
            return;
        L9:
            IsoTypeWriter.writeUInt64(r0, r1);
            goto L10
        L5:
            IsoTypeWriter.writeUInt32(r0, 1);
            goto L6
        }

        public long getContentSize() {
            return this.contentSize;
        }

        public long getOffset() {
            return this.dataOffset;
        }

        @Override // com.coremedia.iso.boxes.Box
        public Container getParent() {
            return this.parent;
        }

        @Override // com.coremedia.iso.boxes.Box
        public long getSize() {
            return this.contentSize + 16;
        }

        @Override // com.coremedia.iso.boxes.Box
        public String getType() {
            return "mdat";
        }

        public void parse(DataSource r1, ByteBuffer r2, long r3, BoxParser r5) {
        }

        public void setContentSize(long r1) {
            this.contentSize = r1;
        }

        public void setDataOffset(long r1) {
            this.dataOffset = r1;
        }

        @Override // com.coremedia.iso.boxes.Box
        public void setParent(Container r1) {
            this.parent = r1;
        }
    }

    public MP4Builder() {
        this.mdat = null;
        this.currentMp4Movie = null;
        this.fos = null;
        this.fc = null;
        this.dataOffset = 0;
        this.wroteSinceLastMdat = 0;
        this.writeNewMdat = true;
        this.track2SampleSizes = new HashMap();
        this.sizeBuffer = null;
        this.allowSyncFiles = true;
    }

    private void flushCurrentMdat() {
        long r0 = this.fc.position();
        this.fc.position(this.mdat.getOffset());
        this.mdat.getBox(this.fc);
        this.fc.position(r0);
        this.mdat.setDataOffset(0);
        this.mdat.setContentSize(0);
        this.fos.flush();
        if (this.allowSyncFiles == false) goto L6;
        this.fos.getFD().sync();
        return;
    }

    public static long gcd(long r2, long r4) {
        if (r4 != 0) goto L6;
        return r2;
    L6:
        return gcd(r4, r2 % r4);
    }

    public int addTrack(MediaFormat r1, boolean r2) {
        return this.currentMp4Movie.addTrack(r1, r2);
    }

    public void createCtts(Track r6, SampleTableBox r7) {
        int[] r5 = r6.getSampleCompositions();
        if (r5 != null) goto L5;
        return;
    L5:
        ArrayList r62 = new ArrayList();
        CompositionTimeToSample.Entry r0 = null;
        int r1 = 0;
    L7:
        if (r1 >= r5.length) goto L15;
        int r2 = r5[r1];
        if (r0 != null) goto L11;
    L13:
        r0 = new CompositionTimeToSample.Entry(1, r2);
        r62.add(r0);
    L14:
        r1 = r1 + 1;
        goto L7
    L11:
        if (r0.getOffset() != r2) goto L13;
        r0.setCount(r0.getCount() + 1);
        goto L14
    L15:
        CompositionTimeToSample r52 = new CompositionTimeToSample();
        r52.setEntries(r62);
        r7.addBox(r52);
    }

    public FileTypeBox createFileTypeBox(boolean r4) {
        LinkedList r3 = new LinkedList();
        r3.add("isom");
        r3.add("iso2");
        if (r4 == false) goto L5;
        String r42 = "hvc1";
    L6:
        r3.add(r42);
        r3.add("mp41");
        return new FileTypeBox("isom", 512, r3);
    L5:
        r42 = "avc1";
        goto L6
    }

    public MP4Builder createMovie(Mp4Movie r5, boolean r6, boolean r7) {
        this.currentMp4Movie = r5;
        FileOutputStream r0 = new FileOutputStream(r5.getCacheFile());
        this.fos = r0;
        this.fc = r0.getChannel();
        FileTypeBox r52 = createFileTypeBox(r7);
        r52.getBox(this.fc);
        long r02 = this.dataOffset + r52.getSize();
        this.dataOffset = r02;
        this.wroteSinceLastMdat += r02;
        this.splitMdat = r6;
        this.mdat = new InterleaveChunkMdat(null);
        this.sizeBuffer = ByteBuffer.allocateDirect(4);
        return this;
    }

    public MovieBox createMovieBox(Mp4Movie r16) {
        MovieBox r0 = new MovieBox();
        MovieHeaderBox r1 = new MovieHeaderBox();
        r1.setCreationTime(new Date());
        r1.setModificationTime(new Date());
        r1.setMatrix(Matrix.ROTATE_0);
        long r2 = getTimescale(r16);
        ArrayList<Track> r4 = r16.getTracks();
        int r5 = r4.size();
        long r6 = 0;
        int r8 = 0;
        int r9 = 0;
    L3:
        if (r9 >= r5) goto L7;
        Track r10 = r4.get(r9);
        r9 = r9 + 1;
        Track r102 = r10;
        r102.prepare();
        long r11 = (r102.getDuration() * r2) / r102.getTimeScale();
        if (r11 <= r6) goto L3;
        r6 = r11;
        goto L3
    L7:
        r1.setDuration(r6);
        r1.setTimescale(r2);
        r1.setNextTrackId(r16.getTracks().size() + 1);
        r0.addBox(r1);
        ArrayList<Track> r12 = r16.getTracks();
        int r22 = r12.size();
    L8:
        if (r8 >= r22) goto L10;
        Track r3 = r12.get(r8);
        r8 = r8 + 1;
        r0.addBox(createTrackBox(r3, r16));
        goto L8
    L10:
        return r0;
    }

    public void createSidx(Track r1, SampleTableBox r2) {
    }

    public Box createStbl(Track r2) {
        SampleTableBox r0 = new SampleTableBox();
        createStsd(r2, r0);
        createStts(r2, r0);
        createCtts(r2, r0);
        createStss(r2, r0);
        createStsc(r2, r0);
        createStsz(r2, r0);
        createStco(r2, r0);
        return r0;
    }

    public void createStco(Track r12, SampleTableBox r13) {
        ArrayList r11 = new ArrayList();
        ArrayList<Sample> r122 = r12.getSamples();
        int r0 = r122.size();
        int r3 = 0;
        long r4 = -1;
        int r6 = 0;
    L3:
        if (r6 >= r0) goto L13;
        Sample r7 = r122.get(r6);
        r6 = r6 + 1;
        Sample r72 = r7;
        long r8 = r72.getOffset();
        if (r4 == (-1)) goto L10;
        if (r4 == r8) goto L10;
        r4 = -1;
    L10:
        if (r4 != (-1)) goto L12;
        r11.add(Long.valueOf(r8));
    L12:
        r4 = r72.getSize() + r8;
        goto L3
    L13:
        long[] r123 = new long[r11.size()];
    L15:
        if (r3 >= r11.size()) goto L17;
        r123[r3] = ((Long) r11.get(r3)).longValue();
        r3 = r3 + 1;
        goto L15
    L17:
        StaticChunkOffsetBox r112 = new StaticChunkOffsetBox();
        r112.setChunkOffsets(r123);
        r13.addBox(r112);
    }

    public void createStsc(Track r15, SampleTableBox r16) {
        SampleToChunkBox r14 = new SampleToChunkBox();
        r14.setEntries(new LinkedList());
        int r0 = r15.getSamples().size();
        int r3 = -1;
        int r6 = 1;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r0) goto L12;
        Sample r7 = r15.getSamples().get(r4);
        long r8 = r7.getOffset() + r7.getSize();
        r5 = r5 + 1;
        if (r4 != (r0 - 1)) goto L7;
    L8:
        if (r3 == r5) goto L10;
        r14.getEntries().add(new SampleToChunkBox.Entry(r6, r5, 1));
        r3 = r5;
    L10:
        r6 = r6 + 1;
        r5 = 0;
    L11:
        r4 = r4 + 1;
        goto L3
    L7:
        if (r8 == r15.getSamples().get(r4 + 1).getOffset()) goto L11;
    L12:
        r16.addBox(r14);
    }

    public void createStsd(Track r1, SampleTableBox r2) {
        r2.addBox(r1.getSampleDescriptionBox());
    }

    public void createStss(Track r1, SampleTableBox r2) {
        long[] r0 = r1.getSyncSamples();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.length <= 0) goto L9;
        SyncSampleBox r12 = new SyncSampleBox();
        r12.setSampleNumber(r0);
        r2.addBox(r12);
        return;
    }

    public void createStsz(Track r2, SampleTableBox r3) {
        SampleSizeBox r0 = new SampleSizeBox();
        r0.setSampleSizes(this.track2SampleSizes.get(r2));
        r3.addBox(r0);
    }

    public void createStts(Track r9, SampleTableBox r10) {
        ArrayList r8 = new ArrayList();
        long[] r92 = r9.getSampleDurations();
        TimeToSampleBox.Entry r0 = null;
        int r1 = 0;
    L4:
        if (r1 >= r92.length) goto L12;
        long r2 = r92[r1];
        if (r0 != null) goto L8;
    L10:
        r0 = new TimeToSampleBox.Entry(1, r2);
        r8.add(r0);
    L11:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r0.getDelta() != r2) goto L10;
        r0.setCount(r0.getCount() + 1);
        goto L11
    L12:
        TimeToSampleBox r93 = new TimeToSampleBox();
        r93.setEntries(r8);
        r10.addBox(r93);
    }

    public TrackBox createTrackBox(Track r9, Mp4Movie r10) {
        TrackBox r0 = new TrackBox();
        TrackHeaderBox r1 = new TrackHeaderBox();
        r1.setEnabled(true);
        r1.setInMovie(true);
        r1.setInPreview(true);
        if (r9.isAudio() == false) goto L5;
        r1.setMatrix(Matrix.ROTATE_0);
    L6:
        r1.setAlternateGroup(0);
        r1.setCreationTime(r9.getCreationTime());
        r1.setDuration((r9.getDuration() * getTimescale(r10)) / r9.getTimeScale());
        r1.setHeight(r9.getHeight());
        r1.setWidth(r9.getWidth());
        r1.setLayer(0);
        r1.setModificationTime(new Date());
        r1.setTrackId(r9.getTrackId() + 1);
        r1.setVolume(r9.getVolume());
        r0.addBox(r1);
        MediaBox r102 = new MediaBox();
        r0.addBox(r102);
        MediaHeaderBox r12 = new MediaHeaderBox();
        r12.setCreationTime(r9.getCreationTime());
        r12.setDuration(r9.getDuration());
        r12.setTimescale(r9.getTimeScale());
        r12.setLanguage("eng");
        r102.addBox(r12);
        HandlerBox r13 = new HandlerBox();
        if (r9.isAudio() == false) goto L9;
        String r3 = "SoundHandle";
    L10:
        r13.setName(r3);
        r13.setHandlerType(r9.getHandler());
        r102.addBox(r13);
        MediaInformationBox r14 = new MediaInformationBox();
        r14.addBox(r9.getMediaHeaderBox());
        DataInformationBox r32 = new DataInformationBox();
        DataReferenceBox r4 = new DataReferenceBox();
        r32.addBox(r4);
        DataEntryUrlBox r5 = new DataEntryUrlBox();
        r5.setFlags(1);
        r4.addBox(r5);
        r14.addBox(r32);
        r14.addBox(createStbl(r9));
        r102.addBox(r14);
        return r0;
    L9:
        r3 = "VideoHandle";
        goto L10
    L5:
        r1.setMatrix(r10.getMatrix());
        goto L6
    }

    public void finishMovie() {
        if (this.mdat.getContentSize() == 0) goto L5;
        flushCurrentMdat();
    L5:
        ArrayList<Track> r0 = this.currentMp4Movie.getTracks();
        int r1 = r0.size();
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L11;
        Track r4 = r0.get(r3);
        r3 = r3 + 1;
        Track r42 = r4;
        ArrayList<Sample> r5 = r42.getSamples();
        int r6 = r5.size();
        long[] r7 = new long[r6];
        int r8 = 0;
    L8:
        if (r8 >= r6) goto L10;
        r7[r8] = r5.get(r8).getSize();
        r8 = r8 + 1;
        goto L8
    L10:
        this.track2SampleSizes.put(r42, r7);
        goto L6
    L11:
        createMovieBox(this.currentMp4Movie).getBox(this.fc);
        this.fos.flush();
        if (this.allowSyncFiles == false) goto L14;
        this.fos.getFD().sync();
    L14:
        this.fc.close();
        this.fos.close();
    }

    public void finishMovie(File r13) {
        if (r13 != null) goto L5;
        finishMovie();
        return;
    L5:
        this.fos.flush();
        long r0 = this.fc.position();
        if (this.allowSyncFiles == false) goto L8;
        this.fos.getFD().sync();
    L8:
        AndroidUtilities.copyFile(this.currentMp4Movie.getCacheFile(), r13);
        RandomAccessFile r2 = new RandomAccessFile(r13, "rw");
        FileChannel r132 = r2.getChannel();     // Catch: Throwable -> L25
        r132.position(r0);     // Catch: Throwable -> L13
        if (this.mdat.getContentSize() == 0) goto L15;
        r132.position(this.mdat.getOffset());     // Catch: Throwable -> L13
        this.mdat.getBox(r132);     // Catch: Throwable -> L13
        r132.position(r0);     // Catch: Throwable -> L13
    L15:
        this.track2SampleSizes.clear();     // Catch: Throwable -> L13
        ArrayList<Track> r02 = this.currentMp4Movie.getTracks();     // Catch: Throwable -> L13
        int r1 = r02.size();     // Catch: Throwable -> L13
        int r4 = 0;
    L16:
        if (r4 >= r1) goto L21;
        Track r5 = r02.get(r4);     // Catch: Throwable -> L13
        r4 = r4 + 1;     // Catch: Throwable -> L13
        Track r52 = r5;     // Catch: Throwable -> L13
        ArrayList<Sample> r6 = r52.getSamples();     // Catch: Throwable -> L13
        int r7 = r6.size();     // Catch: Throwable -> L13
        long[] r8 = new long[r7];     // Catch: Throwable -> L13
        int r9 = 0;
    L18:
        if (r9 >= r7) goto L20;
        r8[r9] = r6.get(r9).getSize();     // Catch: Throwable -> L13
        r9 = r9 + 1;     // Catch: Throwable -> L13
        goto L18
    L20:
        this.track2SampleSizes.put(r52, r8);     // Catch: Throwable -> L13
        goto L16
    L21:
        createMovieBox(this.currentMp4Movie).getBox(r132);     // Catch: Throwable -> L13
        r132.close();     // Catch: Throwable -> L25
        r2.close();
        return;
    L13:
        th = move-exception;
        if (r132 != null) goto L43;
    L32:
        throw th;     // Catch: Throwable -> L25
    L43:
        r132.close();     // Catch: Throwable -> L30
    L30:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L35
    L37:
        throw th;
    L35:
        th = move-exception;
        th.addSuppressed(th);
        goto L37
    }

    public long getLastFrameTimestamp(int r1) {
        return this.currentMp4Movie.getLastFrameTimestamp(r1);
    }

    public long getTimescale(Mp4Movie r6) {
        if (r6.getTracks().isEmpty() == true) goto L5;
        long r0 = r6.getTracks().iterator().next().getTimeScale();
    L6:
        ArrayList<Track> r5 = r6.getTracks();
        int r62 = r5.size();
        int r2 = 0;
    L7:
        if (r2 >= r62) goto L9;
        Track r3 = r5.get(r2);
        r2 = r2 + 1;
        r0 = gcd(r3.getTimeScale(), r0);
        goto L7
    L9:
        return r0;
    L5:
        r0 = 0;
        goto L6
    }

    public void setAllowSyncFiles(boolean r1) {
        this.allowSyncFiles = r1;
    }

    public long writeSampleData(int r9, ByteBuffer r10, MediaCodec.BufferInfo r11, boolean r12) {
        if (this.writeNewMdat == false) goto L5;
        this.mdat.setContentSize(0);
        this.mdat.getBox(this.fc);
        this.mdat.setDataOffset(this.dataOffset);
        this.dataOffset += 16;
        this.wroteSinceLastMdat += 16;
        this.writeNewMdat = false;
    L5:
        InterleaveChunkMdat r0 = this.mdat;
        r0.setContentSize(r0.getContentSize() + r11.size);
        long r4 = this.wroteSinceLastMdat + r11.size;
        this.wroteSinceLastMdat = r4;
        if (r4 < MediaStatus.COMMAND_DISLIKE) goto L11;
        boolean r42 = true;
        if (this.splitMdat == false) goto L10;
        flushCurrentMdat();
        this.writeNewMdat = true;
    L10:
        this.wroteSinceLastMdat = 0;
    L12:
        this.currentMp4Movie.addSample(r9, this.dataOffset, r11);
        if (r12 == false) goto L15;
        this.sizeBuffer.position(0);
        this.sizeBuffer.putInt(r11.size - 4);
        this.sizeBuffer.position(0);
        this.fc.write(this.sizeBuffer);
        r10.position(r11.offset + 4);
    L16:
        r10.limit(r11.offset + r11.size);
        this.fc.write(r10);
        this.dataOffset += r11.size;
        if (r42 == false) goto L23;
        this.fos.flush();
        if (this.allowSyncFiles == false) goto L22;
        this.fos.getFD().sync();
    L22:
        return this.fc.position();
    L23:
        return 0;
    L15:
        r10.position(r11.offset);
        goto L16
    L11:
        r42 = false;
        goto L12
    }
}
