.class public final Lorg/telegram/messenger/EmojiPack;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/messenger/EmojiPack$Holder;
    }
.end annotation


# static fields
.field private static final ASSET_NAME:Ljava/lang/String; = "emoji.pack"

.field private static final DEFLATE:I = 0x0

.field private static final ENTRY_SIZE:I = 0x14

.field private static final FULL_IMAGE:I = 0x3

.field private static final HEADER_SIZE:I = 0x20

.field private static final INDEX_WEBP:I = 0x1

.field private static final MAP_REFERENCE:I = 0x4

.field private static final NO_MASK:I = 0xffff

.field private static final PIXELS:I = 0x1000

.field private static final RGBA_WEBP:I = 0x2

.field private static final ROOT_CACHE_SIZE:I = 0x8

.field private static final SIDE:I = 0x40


# instance fields
.field private age:J

.field private final buffer:Ljava/nio/MappedByteBuffer;

.field private final count:I

.field private final decoded:[B

.field private final emojiCount:I

.field private final emojiPixels:[I

.field private encoded:[B

.field private final indices:[B

.field private final inflater:Ljava/util/zip/Inflater;

.field private final options:Landroid/graphics/BitmapFactory$Options;

.field private final palette:[I

.field private final paletteBytes:[B

.field private final pixels:[I

.field private final rootAges:[J

.field private final rootIds:[I

.field private final rootMaps:[[B


# direct methods
.method private constructor <init>()V
    .locals 15

    .line 69
    const-string v0, "Invalid emoji pack length: "

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 40
    new-instance v1, Ljava/util/zip/Inflater;

    const/4 v2, 0x1

    invoke-direct {v1, v2}, Ljava/util/zip/Inflater;-><init>(Z)V

    iput-object v1, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    .line 41
    new-instance v1, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v1}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    iput-object v1, p0, Lorg/telegram/messenger/EmojiPack;->options:Landroid/graphics/BitmapFactory$Options;

    const/16 v3, 0x1000

    .line 42
    new-array v4, v3, [B

    iput-object v4, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    const/16 v4, 0x1200

    .line 43
    new-array v4, v4, [B

    iput-object v4, p0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    .line 44
    new-array v4, v3, [B

    iput-object v4, p0, Lorg/telegram/messenger/EmojiPack;->indices:[B

    .line 45
    new-array v4, v3, [I

    iput-object v4, p0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    .line 46
    new-array v3, v3, [I

    iput-object v3, p0, Lorg/telegram/messenger/EmojiPack;->emojiPixels:[I

    const/16 v3, 0x100

    .line 47
    new-array v3, v3, [I

    iput-object v3, p0, Lorg/telegram/messenger/EmojiPack;->palette:[I

    const/16 v3, 0x400

    .line 48
    new-array v3, v3, [B

    iput-object v3, p0, Lorg/telegram/messenger/EmojiPack;->paletteBytes:[B

    const/16 v3, 0x8

    .line 49
    new-array v4, v3, [I

    iput-object v4, p0, Lorg/telegram/messenger/EmojiPack;->rootIds:[I

    .line 50
    new-array v5, v3, [J

    iput-object v5, p0, Lorg/telegram/messenger/EmojiPack;->rootAges:[J

    .line 51
    new-array v5, v3, [[B

    iput-object v5, p0, Lorg/telegram/messenger/EmojiPack;->rootMaps:[[B

    .line 71
    sget-object v5, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 72
    invoke-virtual {v5}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v5

    const-string v6, "emoji.pack"

    invoke-virtual {v5, v6}, Landroid/content/res/AssetManager;->openFd(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;

    move-result-object v5

    .line 73
    :try_start_0
    invoke-virtual {v5}, Landroid/content/res/AssetFileDescriptor;->createInputStream()Ljava/io/FileInputStream;

    move-result-object v6
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 74
    :try_start_1
    invoke-virtual {v5}, Landroid/content/res/AssetFileDescriptor;->getLength()J

    move-result-wide v11

    const-wide/16 v13, 0x20

    cmp-long v7, v11, v13

    if-ltz v7, :cond_3

    const-wide/32 v7, 0x7fffffff

    cmp-long v7, v11, v7

    if-gtz v7, :cond_3

    .line 78
    invoke-virtual {v6}, Ljava/io/FileInputStream;->getChannel()Ljava/nio/channels/FileChannel;

    move-result-object v7

    sget-object v8, Ljava/nio/channels/FileChannel$MapMode;->READ_ONLY:Ljava/nio/channels/FileChannel$MapMode;

    .line 79
    invoke-virtual {v5}, Landroid/content/res/AssetFileDescriptor;->getStartOffset()J

    move-result-wide v9

    .line 78
    invoke-virtual/range {v7 .. v12}, Ljava/nio/channels/FileChannel;->map(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 80
    :try_start_2
    invoke-virtual {v6}, Ljava/io/FileInputStream;->close()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    invoke-virtual {v5}, Landroid/content/res/AssetFileDescriptor;->close()V

    .line 81
    sget-object v5, Ljava/nio/ByteOrder;->LITTLE_ENDIAN:Ljava/nio/ByteOrder;

    invoke-virtual {v0, v5}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    const/4 v5, 0x0

    .line 82
    invoke-virtual {v0, v5}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v6

    const v7, 0x334b5045

    const/4 v8, 0x0

    if-ne v6, v7, :cond_2

    const/4 v6, 0x4

    invoke-direct {p0, v6}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v7

    const/4 v9, 0x2

    if-eq v7, v9, :cond_0

    invoke-direct {p0, v6}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v6

    const/4 v7, 0x3

    if-ne v6, v7, :cond_2

    :cond_0
    const/4 v6, 0x6

    invoke-direct {p0, v6}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v6

    const/16 v7, 0x14

    if-ne v6, v7, :cond_2

    .line 83
    invoke-virtual {v0, v3}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v3

    const/16 v6, 0x40

    if-ne v3, v6, :cond_2

    const/16 v3, 0xc

    invoke-virtual {v0, v3}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v3

    if-ne v3, v6, :cond_2

    const/16 v3, 0x18

    .line 84
    invoke-virtual {v0, v3}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v3

    invoke-virtual {v0}, Ljava/nio/Buffer;->capacity()I

    move-result v6

    if-ne v3, v6, :cond_2

    const/16 v3, 0x1c

    invoke-virtual {v0, v3}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v3

    if-nez v3, :cond_2

    const/16 v3, 0x10

    .line 87
    invoke-virtual {v0, v3}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v3

    iput v3, p0, Lorg/telegram/messenger/EmojiPack;->count:I

    .line 88
    invoke-virtual {v0, v7}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v6

    iput v6, p0, Lorg/telegram/messenger/EmojiPack;->emojiCount:I

    if-lt v3, v2, :cond_1

    const v7, 0xffff

    if-gt v3, v7, :cond_1

    if-ltz v6, :cond_1

    if-gt v6, v3, :cond_1

    int-to-long v6, v3

    const-wide/16 v9, 0x14

    mul-long/2addr v6, v9

    add-long/2addr v6, v13

    .line 90
    invoke-virtual {v0}, Ljava/nio/Buffer;->capacity()I

    move-result v0

    int-to-long v9, v0

    cmp-long v0, v6, v9

    if-gtz v0, :cond_1

    .line 93
    invoke-direct {p0}, Lorg/telegram/messenger/EmojiPack;->validateMetadata()V

    const/4 p0, -0x1

    .line 94
    invoke-static {v4, p0}, Ljava/util/Arrays;->fill([II)V

    .line 95
    iput-boolean v5, v1, Landroid/graphics/BitmapFactory$Options;->inScaled:Z

    .line 96
    sget-object p0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    iput-object p0, v1, Landroid/graphics/BitmapFactory$Options;->inPreferredConfig:Landroid/graphics/Bitmap$Config;

    .line 97
    iput-boolean v2, v1, Landroid/graphics/BitmapFactory$Options;->inPremultiplied:Z

    return-void

    .line 91
    :cond_1
    const-string p0, "Invalid EPK3 record count"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    throw v8

    .line 85
    :cond_2
    const-string p0, "Unsupported or damaged EPK3 header"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    throw v8

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_2

    :catchall_1
    move-exception v0

    move-object p0, v0

    goto :goto_0

    .line 76
    :cond_3
    :try_start_3
    new-instance p0, Ljava/io/IOException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v11, v12}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :goto_0
    if-eqz v6, :cond_4

    .line 71
    :try_start_4
    invoke-virtual {v6}, Ljava/io/FileInputStream;->close()V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    goto :goto_1

    :catchall_2
    move-exception v0

    :try_start_5
    invoke-virtual {p0, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_4
    :goto_1
    throw p0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    :goto_2
    if-eqz v5, :cond_5

    :try_start_6
    invoke-virtual {v5}, Landroid/content/res/AssetFileDescriptor;->close()V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_3

    goto :goto_3

    :catchall_3
    move-exception v0

    invoke-virtual {p0, v0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_5
    :goto_3
    throw p0
.end method

.method public synthetic constructor <init>(Lorg/telegram/messenger/EmojiPack-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/messenger/EmojiPack;-><init>()V

    return-void
.end method

.method private static damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;
    .locals 3

    .line 480
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Damaged emoji.pack: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    return-object v0
.end method

.method private decode(I)Landroid/graphics/Bitmap;
    .locals 5

    .line 223
    invoke-static {p1}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v0

    .line 224
    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    add-int/lit8 v2, v0, 0x4

    invoke-virtual {v1, v2}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v1

    add-int/lit8 v2, v0, 0x8

    .line 225
    invoke-direct {p0, v2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v2

    add-int/lit8 v0, v0, 0x10

    .line 226
    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v0

    const/4 v3, 0x2

    if-eq v0, v3, :cond_1

    const/4 v4, 0x3

    if-ne v0, v4, :cond_0

    goto :goto_0

    .line 231
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    invoke-direct {p0, p1, v0}, Lorg/telegram/messenger/EmojiPack;->decodePixels(I[I)V

    .line 232
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    sget-object p1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    const/16 v0, 0x40

    invoke-static {p0, v0, v0, p1}, Landroid/graphics/Bitmap;->createBitmap([IIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0

    .line 228
    :cond_1
    :goto_0
    invoke-direct {p0, v1, v2}, Lorg/telegram/messenger/EmojiPack;->readBytes(II)V

    const/16 p1, 0x14

    if-ne v0, v3, :cond_2

    .line 229
    invoke-direct {p0, p1, v2}, Lorg/telegram/messenger/EmojiPack;->decodeWebp(II)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0

    :cond_2
    invoke-direct {p0, p1, v2}, Lorg/telegram/messenger/EmojiPack;->decodeBitmap(II)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method private decodeBitmap(II)Landroid/graphics/Bitmap;
    .locals 1

    .line 449
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->options:Landroid/graphics/BitmapFactory$Options;

    invoke-static {v0, p1, p2, p0}, Landroid/graphics/BitmapFactory;->decodeByteArray([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 451
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result p1

    const/16 p2, 0x40

    if-ne p1, p2, :cond_0

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result p1

    if-ne p1, p2, :cond_0

    return-object p0

    .line 452
    :cond_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->recycle()V

    .line 453
    const-string p0, "Unexpected bitmap dimensions"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0

    .line 450
    :cond_1
    const-string p0, "Bitmap decoder rejected image"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0
.end method

.method private decodeIndices(I[B)V
    .locals 27

    move-object/from16 v0, p0

    move-object/from16 v1, p2

    .line 318
    invoke-static/range {p1 .. p1}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v2

    add-int/lit8 v3, v2, 0x11

    .line 319
    invoke-direct {v0, v3}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v3

    const/4 v4, 0x3

    const/4 v5, 0x5

    const/4 v6, 0x6

    const/4 v7, 0x0

    if-eqz v3, :cond_2

    add-int/lit8 v9, v2, 0xa

    .line 325
    invoke-direct {v0, v9}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v9

    .line 326
    invoke-direct {v0, v9}, Lorg/telegram/messenger/EmojiPack;->rootMap(I)[B

    move-result-object v10

    .line 327
    invoke-static {v9}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v9

    add-int/lit8 v9, v9, 0xe

    invoke-direct {v0, v9}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v9

    if-eq v3, v4, :cond_1

    if-eq v3, v5, :cond_1

    if-ne v3, v6, :cond_0

    goto :goto_0

    :cond_0
    move-object v11, v7

    const/4 v7, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    add-int/lit8 v7, v2, 0xc

    .line 329
    invoke-direct {v0, v7}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v7

    .line 330
    invoke-direct {v0, v7}, Lorg/telegram/messenger/EmojiPack;->rootMap(I)[B

    move-result-object v11

    .line 331
    invoke-static {v7}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v7

    add-int/lit8 v7, v7, 0xe

    invoke-direct {v0, v7}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v7

    goto :goto_1

    :cond_2
    move-object v10, v7

    move-object v11, v10

    const/4 v7, 0x0

    const/4 v9, 0x0

    :goto_1
    add-int/lit8 v12, v2, 0x13

    .line 334
    invoke-direct {v0, v12}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v13

    const/4 v14, 0x1

    ushr-int/2addr v13, v14

    and-int/lit8 v13, v13, 0x7

    .line 335
    invoke-direct {v0, v12}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v12

    const/4 v15, 0x4

    ushr-int/2addr v12, v15

    and-int/lit8 v12, v12, 0x7

    const/16 v4, 0x1000

    if-ne v3, v15, :cond_3

    const/4 v8, 0x0

    :goto_2
    if-ge v8, v4, :cond_15

    .line 337
    invoke-static {v8, v13}, Lorg/telegram/messenger/EmojiPack;->sourceIndex(II)I

    move-result v0

    aget-byte v0, v10, v0

    aput-byte v0, v1, v8

    add-int/lit8 v8, v8, 0x1

    goto :goto_2

    :cond_3
    add-int v15, v9, v7

    .line 341
    invoke-direct {v0, v2}, Lorg/telegram/messenger/EmojiPack;->paletteLength(I)I

    move-result v16

    .line 342
    iget-object v4, v0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    add-int/lit8 v8, v2, 0x4

    invoke-virtual {v4, v8}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v4

    add-int v4, v4, v16

    add-int/lit8 v8, v2, 0x8

    .line 343
    invoke-direct {v0, v8}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v8

    sub-int v8, v8, v16

    const/4 v14, 0x2

    if-eq v3, v5, :cond_5

    if-ne v3, v6, :cond_4

    goto :goto_3

    :cond_4
    const/16 v17, 0x20

    move/from16 v6, v17

    goto :goto_4

    :cond_5
    :goto_3
    if-lt v8, v14, :cond_18

    add-int/lit8 v17, v4, 0x1

    .line 347
    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v4

    add-int/lit8 v8, v8, -0x1

    const/4 v6, 0x1

    if-lt v4, v6, :cond_17

    const/16 v6, 0x40

    if-ge v4, v6, :cond_17

    move v6, v4

    move/from16 v4, v17

    .line 351
    :goto_4
    invoke-direct {v0, v4, v8}, Lorg/telegram/messenger/EmojiPack;->readBytes(II)V

    add-int/lit8 v4, v2, 0x10

    .line 353
    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v4

    if-nez v4, :cond_6

    add-int/lit16 v4, v15, 0x1000

    .line 354
    invoke-direct {v0, v8, v4}, Lorg/telegram/messenger/EmojiPack;->inflate(II)V

    .line 355
    iget-object v4, v0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    const/16 v5, 0x1000

    const/4 v8, 0x0

    .line 356
    invoke-static {v4, v15, v1, v8, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    move/from16 v18, v2

    move-object v2, v4

    move v4, v8

    goto :goto_6

    :cond_6
    const/4 v4, 0x0

    if-le v8, v15, :cond_16

    .line 359
    iget-object v5, v0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    iget-object v14, v0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    move/from16 v18, v2

    const/16 v2, 0x14

    invoke-static {v5, v2, v14, v4, v15}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    add-int/lit8 v2, v15, 0x14

    sub-int/2addr v8, v15

    .line 360
    invoke-direct {v0, v2, v8}, Lorg/telegram/messenger/EmojiPack;->decodeWebp(II)Landroid/graphics/Bitmap;

    move-result-object v19

    .line 362
    :try_start_0
    iget-object v2, v0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    const/16 v25, 0x40

    const/16 v26, 0x40

    const/16 v21, 0x0

    const/16 v22, 0x40

    const/16 v23, 0x0

    const/16 v24, 0x0

    move-object/from16 v20, v2

    invoke-virtual/range {v19 .. v26}, Landroid/graphics/Bitmap;->getPixels([IIIIIII)V

    move v8, v4

    :goto_5
    const/16 v5, 0x1000

    if-ge v8, v5, :cond_7

    .line 363
    iget-object v2, v0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    aget v2, v2, v8

    int-to-byte v2, v2

    aput-byte v2, v1, v8
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    add-int/lit8 v8, v8, 0x1

    goto :goto_5

    :catchall_0
    move-exception v0

    goto/16 :goto_12

    .line 365
    :cond_7
    invoke-virtual/range {v19 .. v19}, Landroid/graphics/Bitmap;->recycle()V

    .line 367
    iget-object v2, v0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    :goto_6
    const/4 v5, 0x1

    if-eq v3, v5, :cond_10

    const/4 v5, 0x2

    if-ne v3, v5, :cond_8

    goto :goto_e

    :cond_8
    const/4 v5, 0x3

    if-eq v3, v5, :cond_9

    const/4 v5, 0x5

    if-eq v3, v5, :cond_9

    const/4 v5, 0x6

    if-ne v3, v5, :cond_13

    goto :goto_7

    :cond_9
    const/4 v5, 0x6

    :goto_7
    move v8, v4

    :goto_8
    const/16 v14, 0x1000

    if-ge v8, v14, :cond_13

    if-ne v3, v5, :cond_b

    ushr-int/lit8 v14, v8, 0x6

    if-ge v14, v6, :cond_a

    :goto_9
    const/4 v14, 0x1

    goto :goto_a

    :cond_a
    move v14, v4

    goto :goto_a

    :cond_b
    and-int/lit8 v14, v8, 0x3f

    if-ge v14, v6, :cond_a

    goto :goto_9

    :goto_a
    if-eqz v14, :cond_c

    .line 381
    invoke-static {v8, v13}, Lorg/telegram/messenger/EmojiPack;->sourceIndex(II)I

    move-result v15

    aget-byte v15, v10, v15

    goto :goto_b

    .line 382
    :cond_c
    invoke-static {v8, v12}, Lorg/telegram/messenger/EmojiPack;->sourceIndex(II)I

    move-result v15

    aget-byte v15, v11, v15

    :goto_b
    and-int/lit16 v15, v15, 0xff

    if-eqz v14, :cond_d

    move v4, v9

    goto :goto_c

    :cond_d
    move v4, v7

    :goto_c
    if-ge v15, v4, :cond_f

    if-eqz v14, :cond_e

    const/4 v4, 0x0

    goto :goto_d

    :cond_e
    move v4, v9

    :goto_d
    add-int/2addr v4, v15

    .line 386
    aget-byte v4, v2, v4

    and-int/lit16 v4, v4, 0xff

    .line 387
    aget-byte v14, v1, v8

    and-int/lit16 v14, v14, 0xff

    add-int/2addr v14, v4

    int-to-byte v4, v14

    aput-byte v4, v1, v8

    add-int/lit8 v8, v8, 0x1

    const/4 v4, 0x0

    goto :goto_8

    .line 384
    :cond_f
    const-string v0, "Half root index out of range"

    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object v0

    throw v0

    :cond_10
    :goto_e
    const/16 v5, 0x1000

    const/4 v8, 0x0

    :goto_f
    if-ge v8, v5, :cond_13

    .line 371
    invoke-static {v8, v13}, Lorg/telegram/messenger/EmojiPack;->sourceIndex(II)I

    move-result v4

    aget-byte v4, v10, v4

    and-int/lit16 v4, v4, 0xff

    if-ge v4, v9, :cond_12

    .line 373
    aget-byte v4, v2, v4

    and-int/lit16 v4, v4, 0xff

    const/4 v6, 0x1

    if-ne v3, v6, :cond_11

    .line 375
    aget-byte v7, v1, v8

    and-int/lit16 v7, v7, 0xff

    xor-int/2addr v4, v7

    int-to-byte v4, v4

    goto :goto_10

    .line 376
    :cond_11
    aget-byte v7, v1, v8

    and-int/lit16 v7, v7, 0xff

    add-int/2addr v7, v4

    int-to-byte v4, v7

    :goto_10
    aput-byte v4, v1, v8

    add-int/lit8 v8, v8, 0x1

    goto :goto_f

    .line 372
    :cond_12
    const-string v0, "Root index out of range"

    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object v0

    throw v0

    :cond_13
    add-int/lit8 v2, v18, 0xe

    .line 390
    invoke-direct {v0, v2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v0

    const/16 v2, 0x100

    if-ge v0, v2, :cond_15

    .line 392
    array-length v2, v1

    const/4 v8, 0x0

    :goto_11
    if-ge v8, v2, :cond_15

    aget-byte v3, v1, v8

    and-int/lit16 v3, v3, 0xff

    if-ge v3, v0, :cond_14

    add-int/lit8 v8, v8, 0x1

    goto :goto_11

    .line 393
    :cond_14
    const-string v0, "Decoded index out of range"

    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object v0

    throw v0

    :cond_15
    return-void

    .line 365
    :goto_12
    invoke-virtual/range {v19 .. v19}, Landroid/graphics/Bitmap;->recycle()V

    .line 366
    throw v0

    .line 358
    :cond_16
    const-string v0, "Truncated index WebP"

    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object v0

    throw v0

    .line 349
    :cond_17
    const-string v0, "Invalid split position"

    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object v0

    throw v0

    .line 346
    :cond_18
    const-string v0, "Truncated split record"

    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object v0

    throw v0
.end method

.method private decodePixels(I[I)V
    .locals 14

    .line 236
    invoke-static {p1}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v0

    .line 237
    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    add-int/lit8 v2, v0, 0x4

    invoke-virtual {v1, v2}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v1

    add-int/lit8 v2, v0, 0x8

    .line 238
    invoke-direct {p0, v2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v2

    add-int/lit8 v3, v0, 0x10

    .line 239
    invoke-direct {p0, v3}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v3

    const/4 v4, 0x0

    const/4 v5, 0x1

    const/4 v6, 0x2

    if-eq v3, v6, :cond_10

    const/4 v7, 0x3

    if-ne v3, v7, :cond_0

    goto/16 :goto_b

    .line 253
    :cond_0
    iget-object v2, p0, Lorg/telegram/messenger/EmojiPack;->indices:[B

    invoke-direct {p0, p1, v2}, Lorg/telegram/messenger/EmojiPack;->decodeIndices(I[B)V

    add-int/lit8 v2, v0, 0xe

    .line 254
    invoke-direct {p0, v2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v2

    add-int/lit8 v3, v0, 0x12

    .line 255
    invoke-direct {p0, v3}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v8

    and-int/lit8 v8, v8, 0x7f

    mul-int v9, v2, v8

    .line 257
    invoke-direct {p0, v3}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v3

    and-int/lit16 v3, v3, 0x80

    if-eqz v3, :cond_1

    move v3, v5

    goto :goto_0

    :cond_1
    move v3, v4

    :goto_0
    if-eqz v3, :cond_3

    .line 259
    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->paletteLength(I)I

    move-result v10

    sub-int/2addr v10, v6

    add-int/lit8 v6, v1, 0x2

    .line 260
    invoke-direct {p0, v6, v10}, Lorg/telegram/messenger/EmojiPack;->readBytes(II)V

    add-int/lit8 v6, v0, 0x13

    .line 261
    invoke-direct {p0, v6}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v6

    and-int/2addr v6, v5

    if-eqz v6, :cond_2

    mul-int/lit8 v6, v9, 0x7

    add-int/lit8 v6, v6, 0x7

    div-int/lit8 v6, v6, 0x8

    goto :goto_1

    :cond_2
    move v6, v9

    .line 262
    :goto_1
    invoke-direct {p0, v10, v6}, Lorg/telegram/messenger/EmojiPack;->inflate(II)V

    :cond_3
    add-int/lit8 v0, v0, 0x13

    .line 264
    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v0

    and-int/2addr v0, v5

    const/16 v6, 0xff

    if-eqz v0, :cond_7

    move v0, v4

    :goto_2
    if-ge v0, v9, :cond_9

    mul-int/lit8 v10, v0, 0x7

    and-int/lit8 v11, v10, 0x7

    ushr-int/2addr v10, v7

    if-eqz v3, :cond_4

    .line 269
    iget-object v12, p0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    aget-byte v12, v12, v10

    and-int/2addr v12, v6

    goto :goto_3

    :cond_4
    add-int v12, v1, v10

    invoke-direct {p0, v12}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v12

    :goto_3
    ushr-int/2addr v12, v11

    if-le v11, v5, :cond_6

    if-eqz v3, :cond_5

    .line 270
    iget-object v13, p0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    add-int/lit8 v10, v10, 0x1

    aget-byte v10, v13, v10

    and-int/2addr v10, v6

    goto :goto_4

    :cond_5
    add-int/2addr v10, v1

    add-int/2addr v10, v5

    invoke-direct {p0, v10}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v10

    :goto_4
    rsub-int/lit8 v11, v11, 0x8

    shl-int/2addr v10, v11

    or-int/2addr v12, v10

    :cond_6
    and-int/lit8 v10, v12, 0x7f

    .line 272
    iget-object v11, p0, Lorg/telegram/messenger/EmojiPack;->paletteBytes:[B

    shl-int/lit8 v12, v10, 0x1

    ushr-int/lit8 v10, v10, 0x6

    or-int/2addr v10, v12

    int-to-byte v10, v10

    aput-byte v10, v11, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_7
    if-eqz v3, :cond_8

    .line 275
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->paletteBytes:[B

    invoke-static {v0, v4, v1, v4, v9}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    goto :goto_6

    :cond_8
    move v0, v4

    :goto_5
    if-ge v0, v9, :cond_9

    .line 276
    iget-object v3, p0, Lorg/telegram/messenger/EmojiPack;->paletteBytes:[B

    iget-object v7, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    add-int v10, v1, v0

    invoke-virtual {v7, v10}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v7

    aput-byte v7, v3, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_5

    :cond_9
    :goto_6
    move v0, v4

    move v1, v0

    :goto_7
    if-ge v0, v2, :cond_d

    const v3, 0x10101

    const/high16 v7, -0x1000000

    if-nez v8, :cond_a

    .line 281
    iget-object v9, p0, Lorg/telegram/messenger/EmojiPack;->palette:[I

    mul-int/2addr v3, v0

    or-int/2addr v3, v7

    aput v3, v9, v0

    goto :goto_9

    :cond_a
    if-ne v8, v5, :cond_b

    .line 283
    iget-object v9, p0, Lorg/telegram/messenger/EmojiPack;->palette:[I

    iget-object v10, p0, Lorg/telegram/messenger/EmojiPack;->paletteBytes:[B

    add-int/lit8 v11, v1, 0x1

    aget-byte v1, v10, v1

    and-int/2addr v1, v6

    mul-int/2addr v1, v3

    or-int/2addr v1, v7

    aput v1, v9, v0

    move v1, v11

    goto :goto_9

    .line 285
    :cond_b
    iget-object v3, p0, Lorg/telegram/messenger/EmojiPack;->paletteBytes:[B

    add-int/lit8 v7, v1, 0x1

    aget-byte v9, v3, v1

    and-int/2addr v9, v6

    add-int/lit8 v10, v1, 0x2

    .line 286
    aget-byte v7, v3, v7

    and-int/2addr v7, v6

    add-int/lit8 v11, v1, 0x3

    .line 287
    aget-byte v10, v3, v10

    and-int/2addr v10, v6

    const/4 v12, 0x4

    if-ne v8, v12, :cond_c

    add-int/lit8 v1, v1, 0x4

    .line 288
    aget-byte v3, v3, v11

    and-int/2addr v3, v6

    goto :goto_8

    :cond_c
    move v3, v6

    move v1, v11

    .line 289
    :goto_8
    iget-object v11, p0, Lorg/telegram/messenger/EmojiPack;->palette:[I

    shl-int/lit8 v3, v3, 0x18

    shl-int/lit8 v9, v9, 0x10

    or-int/2addr v3, v9

    shl-int/lit8 v7, v7, 0x8

    or-int/2addr v3, v7

    or-int/2addr v3, v10

    aput v3, v11, v0

    :goto_9
    add-int/lit8 v0, v0, 0x1

    goto :goto_7

    :cond_d
    :goto_a
    const/16 v0, 0x1000

    if-ge v4, v0, :cond_f

    .line 293
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->indices:[B

    aget-byte v0, v0, v4

    and-int/2addr v0, v6

    if-ge v0, v2, :cond_e

    .line 295
    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->palette:[I

    aget v0, v1, v0

    aput v0, p2, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_a

    .line 294
    :cond_e
    const-string p0, "Palette index out of range"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0

    :cond_f
    return-void

    .line 241
    :cond_10
    :goto_b
    invoke-direct {p0, v1, v2}, Lorg/telegram/messenger/EmojiPack;->readBytes(II)V

    .line 242
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->options:Landroid/graphics/BitmapFactory$Options;

    iput-boolean v4, v0, Landroid/graphics/BitmapFactory$Options;->inPremultiplied:Z

    const/16 v0, 0x14

    const/4 v1, 0x0

    if-ne v3, v6, :cond_11

    .line 245
    :try_start_0
    invoke-direct {p0, v0, v2}, Lorg/telegram/messenger/EmojiPack;->decodeWebp(II)Landroid/graphics/Bitmap;

    move-result-object v0

    :goto_c
    move-object v6, v0

    goto :goto_d

    :catchall_0
    move-exception v0

    goto :goto_e

    :cond_11
    invoke-direct {p0, v0, v2}, Lorg/telegram/messenger/EmojiPack;->decodeBitmap(II)Landroid/graphics/Bitmap;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_c

    :goto_d
    const/16 v12, 0x40

    const/16 v13, 0x40

    const/4 v8, 0x0

    const/16 v9, 0x40

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object/from16 v7, p2

    .line 246
    :try_start_1
    invoke-virtual/range {v6 .. v13}, Landroid/graphics/Bitmap;->getPixels([IIIIIII)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 248
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->options:Landroid/graphics/BitmapFactory$Options;

    iput-boolean v5, p0, Landroid/graphics/BitmapFactory$Options;->inPremultiplied:Z

    .line 249
    invoke-virtual {v6}, Landroid/graphics/Bitmap;->recycle()V

    return-void

    :catchall_1
    move-exception v0

    move-object v1, v6

    .line 248
    :goto_e
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->options:Landroid/graphics/BitmapFactory$Options;

    iput-boolean v5, p0, Landroid/graphics/BitmapFactory$Options;->inPremultiplied:Z

    if-eqz v1, :cond_12

    .line 249
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V

    .line 250
    :cond_12
    throw v0
.end method

.method private decodeWebp(II)Landroid/graphics/Bitmap;
    .locals 4

    add-int/lit8 v0, p1, -0x14

    and-int/lit8 v1, p2, 0x1

    const v2, 0x46464952

    .line 439
    invoke-direct {p0, v0, v2}, Lorg/telegram/messenger/EmojiPack;->putInt(II)V

    add-int/lit8 v2, p1, -0x10

    add-int/lit8 v3, p2, 0xc

    add-int/2addr v3, v1

    .line 440
    invoke-direct {p0, v2, v3}, Lorg/telegram/messenger/EmojiPack;->putInt(II)V

    add-int/lit8 v2, p1, -0xc

    const v3, 0x50424557

    .line 441
    invoke-direct {p0, v2, v3}, Lorg/telegram/messenger/EmojiPack;->putInt(II)V

    add-int/lit8 v2, p1, -0x8

    const v3, 0x4c385056    # 4.831676E7f

    .line 442
    invoke-direct {p0, v2, v3}, Lorg/telegram/messenger/EmojiPack;->putInt(II)V

    add-int/lit8 v2, p1, -0x4

    .line 443
    invoke-direct {p0, v2, p2}, Lorg/telegram/messenger/EmojiPack;->putInt(II)V

    if-eqz v1, :cond_0

    .line 444
    iget-object v2, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    add-int/2addr p1, p2

    const/4 v3, 0x0

    aput-byte v3, v2, p1

    :cond_0
    add-int/lit8 p2, p2, 0x14

    add-int/2addr p2, v1

    .line 445
    invoke-direct {p0, v0, p2}, Lorg/telegram/messenger/EmojiPack;->decodeBitmap(II)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method private static entry(I)I
    .locals 0

    mul-int/lit8 p0, p0, 0x14

    add-int/lit8 p0, p0, 0x20

    return p0
.end method

.method private find(III)I
    .locals 2

    add-int/lit8 p3, p3, -0x1

    :goto_0
    if-gt p2, p3, :cond_2

    add-int v0, p2, p3

    ushr-int/lit8 v0, v0, 0x1

    .line 117
    invoke-static {v0}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v1

    invoke-direct {p0, v1}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v1

    if-ge v1, p1, :cond_0

    add-int/lit8 v0, v0, 0x1

    move p2, v0

    goto :goto_0

    :cond_0
    if-le v1, p1, :cond_1

    add-int/lit8 v0, v0, -0x1

    move p3, v0

    goto :goto_0

    :cond_1
    return v0

    :cond_2
    const/4 p0, -0x1

    return p0
.end method

.method private findEmoji(II)I
    .locals 1

    if-ltz p1, :cond_1

    const/16 v0, 0xf

    if-gt p1, v0, :cond_1

    if-ltz p2, :cond_1

    const/16 v0, 0x1000

    if-lt p2, v0, :cond_0

    goto :goto_0

    :cond_0
    mul-int/2addr p1, v0

    add-int/2addr p1, p2

    const/4 p2, 0x0

    .line 127
    iget v0, p0, Lorg/telegram/messenger/EmojiPack;->emojiCount:I

    invoke-direct {p0, p1, p2, v0}, Lorg/telegram/messenger/EmojiPack;->find(III)I

    move-result p0

    return p0

    :cond_1
    :goto_0
    const/4 p0, -0x1

    return p0
.end method

.method public static getInstance()Lorg/telegram/messenger/EmojiPack;
    .locals 1

    .line 66
    sget-object v0, Lorg/telegram/messenger/EmojiPack$Holder;->INSTANCE:Lorg/telegram/messenger/EmojiPack;

    return-object v0
.end method

.method private inflate(II)V
    .locals 4

    .line 459
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    invoke-virtual {v0}, Ljava/util/zip/Inflater;->reset()V

    .line 460
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    const/16 v2, 0x14

    invoke-virtual {v0, v1, v2, p1}, Ljava/util/zip/Inflater;->setInput([BII)V

    const/4 p1, 0x0

    move v0, p1

    .line 469
    :goto_0
    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    if-ge v0, p2, :cond_1

    .line 464
    :try_start_0
    iget-object v2, p0, Lorg/telegram/messenger/EmojiPack;->decoded:[B

    sub-int v3, p2, v0

    invoke-virtual {v1, v2, v0, v3}, Ljava/util/zip/Inflater;->inflate([BII)I

    move-result v1

    if-eqz v1, :cond_0

    add-int/2addr v0, v1

    goto :goto_0

    .line 465
    :cond_0
    const-string p0, "Truncated DEFLATE stream"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0

    .line 469
    :cond_1
    invoke-virtual {v1}, Ljava/util/zip/Inflater;->finished()Z

    move-result p2

    if-nez p2, :cond_3

    .line 470
    iget-object p2, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->indices:[B

    const/4 v1, 0x1

    invoke-virtual {p2, v0, p1, v1}, Ljava/util/zip/Inflater;->inflate([BII)I

    move-result p1

    if-nez p1, :cond_2

    .line 471
    iget-object p1, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    invoke-virtual {p1}, Ljava/util/zip/Inflater;->finished()Z

    move-result p1

    if-eqz p1, :cond_2

    goto :goto_1

    :cond_2
    const-string p0, "Oversized DEFLATE stream"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0

    .line 473
    :cond_3
    :goto_1
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->inflater:Ljava/util/zip/Inflater;

    invoke-virtual {p0}, Ljava/util/zip/Inflater;->getRemaining()I

    move-result p0

    if-nez p0, :cond_4

    return-void

    :cond_4
    const-string p0, "Trailing DEFLATE bytes"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0
    :try_end_0
    .catch Ljava/util/zip/DataFormatException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    move-exception p0

    .line 475
    const-string p1, "Damaged EPK3 DEFLATE stream"

    invoke-static {p1, p0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private paletteLength(I)I
    .locals 3

    add-int/lit8 v0, p1, 0x12

    .line 418
    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v1

    and-int/lit16 v1, v1, 0x80

    if-eqz v1, :cond_2

    add-int/lit8 v0, p1, 0x8

    .line 419
    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v1

    const/4 v2, 0x3

    if-lt v1, v2, :cond_1

    .line 420
    iget-object v1, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    add-int/lit8 p1, p1, 0x4

    invoke-virtual {v1, p1}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result p1

    invoke-direct {p0, p1}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result p1

    if-eqz p1, :cond_0

    add-int/lit8 p1, p1, 0x2

    .line 421
    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result p0

    if-gt p1, p0, :cond_0

    return p1

    :cond_0
    const-string p0, "Invalid compressed palette"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0

    .line 419
    :cond_1
    const-string p0, "Truncated compressed palette"

    invoke-static {p0}, Lorg/telegram/messenger/EmojiPack;->damaged(Ljava/lang/String;)Ljava/lang/IllegalStateException;

    move-result-object p0

    throw p0

    :cond_2
    add-int/lit8 v1, p1, 0xe

    .line 424
    invoke-direct {p0, v1}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v1

    invoke-direct {p0, v0}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v0

    and-int/lit8 v0, v0, 0x7f

    mul-int/2addr v1, v0

    add-int/lit8 p1, p1, 0x13

    .line 425
    invoke-direct {p0, p1}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result p0

    and-int/lit8 p0, p0, 0x1

    if-eqz p0, :cond_3

    mul-int/lit8 v1, v1, 0x7

    add-int/lit8 v1, v1, 0x7

    div-int/lit8 v1, v1, 0x8

    :cond_3
    return v1
.end method

.method private putInt(II)V
    .locals 2

    .line 429
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    int-to-byte v0, p2

    aput-byte v0, p0, p1

    add-int/lit8 v0, p1, 0x1

    ushr-int/lit8 v1, p2, 0x8

    int-to-byte v1, v1

    .line 430
    aput-byte v1, p0, v0

    add-int/lit8 v0, p1, 0x2

    ushr-int/lit8 v1, p2, 0x10

    int-to-byte v1, v1

    .line 431
    aput-byte v1, p0, v0

    add-int/lit8 p1, p1, 0x3

    ushr-int/lit8 p2, p2, 0x18

    int-to-byte p2, p2

    .line 432
    aput-byte p2, p0, p1

    return-void
.end method

.method private readBytes(II)V
    .locals 3

    .line 407
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    array-length v1, v0

    add-int/lit8 v2, p2, 0x15

    if-ge v1, v2, :cond_1

    .line 408
    array-length v0, v0

    :goto_0
    if-ge v0, v2, :cond_0

    shl-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 410
    :cond_0
    new-array v0, v0, [B

    iput-object v0, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    .line 413
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    invoke-virtual {v0, p1}, Ljava/nio/MappedByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 414
    iget-object p1, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->encoded:[B

    const/16 v0, 0x14

    invoke-virtual {p1, p0, v0, p2}, Ljava/nio/ByteBuffer;->get([BII)Ljava/nio/ByteBuffer;

    return-void
.end method

.method private rootMap(I)[B
    .locals 7

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    const/16 v2, 0x8

    const-wide/16 v3, 0x1

    if-ge v0, v2, :cond_2

    .line 302
    iget-object v2, p0, Lorg/telegram/messenger/EmojiPack;->rootIds:[I

    aget v2, v2, v0

    .line 306
    iget-object v5, p0, Lorg/telegram/messenger/EmojiPack;->rootAges:[J

    if-ne v2, p1, :cond_0

    .line 303
    iget-wide v1, p0, Lorg/telegram/messenger/EmojiPack;->age:J

    add-long/2addr v1, v3

    iput-wide v1, p0, Lorg/telegram/messenger/EmojiPack;->age:J

    aput-wide v1, v5, v0

    .line 304
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->rootMaps:[[B

    aget-object p0, p0, v0

    return-object p0

    .line 306
    :cond_0
    aget-wide v2, v5, v0

    aget-wide v4, v5, v1

    cmp-long v2, v2, v4

    if-gez v2, :cond_1

    move v1, v0

    :cond_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 308
    :cond_2
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->rootMaps:[[B

    aget-object v2, v0, v1

    if-nez v2, :cond_3

    const/16 v2, 0x1000

    new-array v2, v2, [B

    aput-object v2, v0, v1

    .line 310
    :cond_3
    iget-object v2, p0, Lorg/telegram/messenger/EmojiPack;->rootIds:[I

    const/4 v5, -0x1

    aput v5, v2, v1

    .line 311
    aget-object v0, v0, v1

    invoke-direct {p0, p1, v0}, Lorg/telegram/messenger/EmojiPack;->decodeIndices(I[B)V

    .line 312
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->rootIds:[I

    aput p1, v0, v1

    .line 313
    iget-object p1, p0, Lorg/telegram/messenger/EmojiPack;->rootAges:[J

    iget-wide v5, p0, Lorg/telegram/messenger/EmojiPack;->age:J

    add-long/2addr v5, v3

    iput-wide v5, p0, Lorg/telegram/messenger/EmojiPack;->age:J

    aput-wide v5, p1, v1

    .line 314
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->rootMaps:[[B

    aget-object p0, p0, v1

    return-object p0
.end method

.method private static sourceIndex(II)I
    .locals 2

    and-int/lit8 v0, p0, 0x3f

    ushr-int/lit8 p0, p0, 0x6

    and-int/lit8 v1, p1, 0x1

    if-eqz v1, :cond_0

    rsub-int/lit8 v0, v0, 0x3f

    :cond_0
    and-int/lit8 v1, p1, 0x2

    if-eqz v1, :cond_1

    rsub-int/lit8 p0, p0, 0x3f

    :cond_1
    and-int/lit8 p1, p1, 0x4

    if-eqz p1, :cond_2

    shl-int/lit8 p1, v0, 0x6

    or-int/2addr p0, p1

    return p0

    :cond_2
    shl-int/lit8 p0, p0, 0x6

    or-int/2addr p0, v0

    return p0
.end method

.method private u16(I)I
    .locals 0

    .line 101
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    invoke-virtual {p0, p1}, Ljava/nio/ByteBuffer;->getShort(I)S

    move-result p0

    const p1, 0xffff

    and-int/2addr p0, p1

    return p0
.end method

.method private u8(I)I
    .locals 0

    .line 105
    iget-object p0, p0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    invoke-virtual {p0, p1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result p0

    and-int/lit16 p0, p0, 0xff

    return p0
.end method

.method private validateMetadata()V
    .locals 18

    move-object/from16 v0, p0

    .line 131
    iget v1, v0, Lorg/telegram/messenger/EmojiPack;->count:I

    mul-int/lit8 v1, v1, 0x14

    add-int/lit8 v1, v1, 0x20

    const/4 v3, 0x0

    .line 132
    :goto_0
    iget v4, v0, Lorg/telegram/messenger/EmojiPack;->count:I

    if-ge v3, v4, :cond_18

    .line 133
    invoke-static {v3}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v4

    if-eqz v3, :cond_1

    .line 134
    iget v5, v0, Lorg/telegram/messenger/EmojiPack;->emojiCount:I

    if-eq v3, v5, :cond_1

    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v5

    add-int/lit8 v6, v4, -0x14

    invoke-direct {v0, v6}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v6

    if-le v5, v6, :cond_0

    goto :goto_1

    .line 135
    :cond_0
    const-string v0, "Unsorted EPK3 IDs"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    :cond_1
    :goto_1
    add-int/lit8 v5, v4, 0x2

    .line 137
    invoke-direct {v0, v5}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v5

    .line 138
    iget v6, v0, Lorg/telegram/messenger/EmojiPack;->emojiCount:I

    const v7, 0xffff

    if-lt v3, v6, :cond_2

    if-ne v5, v7, :cond_3

    :cond_2
    if-ge v3, v6, :cond_4

    if-eq v5, v7, :cond_4

    iget v8, v0, Lorg/telegram/messenger/EmojiPack;->count:I

    .line 140
    invoke-direct {v0, v5, v6, v8}, Lorg/telegram/messenger/EmojiPack;->find(III)I

    move-result v5

    if-ltz v5, :cond_3

    goto :goto_2

    .line 141
    :cond_3
    const-string v0, "Invalid mask reference at record "

    invoke-static {v0, v3}, Lokio/internal/ZipFilesKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-void

    .line 143
    :cond_4
    :goto_2
    iget-object v5, v0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    add-int/lit8 v6, v4, 0x4

    invoke-virtual {v5, v6}, Ljava/nio/ByteBuffer;->getInt(I)I

    move-result v5

    add-int/lit8 v6, v4, 0x8

    .line 144
    invoke-direct {v0, v6}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v6

    add-int/lit8 v8, v4, 0xa

    .line 145
    invoke-direct {v0, v8}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v8

    add-int/lit8 v9, v4, 0xc

    .line 146
    invoke-direct {v0, v9}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v9

    add-int/lit8 v10, v4, 0xe

    .line 147
    invoke-direct {v0, v10}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v10

    add-int/lit8 v11, v4, 0x10

    .line 148
    invoke-direct {v0, v11}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v11

    add-int/lit8 v12, v4, 0x11

    .line 149
    invoke-direct {v0, v12}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v12

    add-int/lit8 v13, v4, 0x12

    .line 150
    invoke-direct {v0, v13}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v14

    and-int/lit8 v14, v14, 0x7f

    if-lt v5, v1, :cond_16

    if-eqz v6, :cond_16

    move v15, v3

    int-to-long v2, v5

    move/from16 v16, v8

    int-to-long v7, v6

    add-long/2addr v2, v7

    .line 151
    iget-object v7, v0, Lorg/telegram/messenger/EmojiPack;->buffer:Ljava/nio/MappedByteBuffer;

    .line 152
    invoke-virtual {v7}, Ljava/nio/Buffer;->capacity()I

    move-result v7

    int-to-long v7, v7

    cmp-long v2, v2, v7

    if-gtz v2, :cond_17

    const/4 v2, 0x4

    if-gt v11, v2, :cond_17

    const/4 v3, 0x6

    if-gt v12, v3, :cond_17

    const/4 v7, 0x2

    if-eq v11, v7, :cond_5

    const/4 v8, 0x3

    if-ne v11, v8, :cond_6

    :cond_5
    move/from16 v6, v16

    goto/16 :goto_8

    :cond_6
    const/4 v5, 0x1

    if-lt v10, v5, :cond_14

    move/from16 v17, v5

    const/16 v5, 0x100

    if-gt v10, v5, :cond_14

    if-eq v14, v7, :cond_14

    if-gt v14, v2, :cond_14

    if-nez v14, :cond_7

    if-ne v10, v5, :cond_14

    :cond_7
    if-ne v11, v2, :cond_8

    .line 165
    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->paletteLength(I)I

    move-result v5

    if-ne v5, v6, :cond_14

    goto :goto_3

    :cond_8
    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->paletteLength(I)I

    move-result v5

    if-ge v5, v6, :cond_14

    :goto_3
    if-nez v14, :cond_9

    add-int/lit8 v4, v4, 0x13

    .line 166
    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v4

    and-int/lit8 v4, v4, 0x1

    if-nez v4, :cond_14

    invoke-direct {v0, v13}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v4

    and-int/lit16 v4, v4, 0x80

    if-nez v4, :cond_14

    :cond_9
    if-ne v11, v2, :cond_a

    move/from16 v4, v17

    goto :goto_4

    :cond_a
    const/4 v4, 0x0

    :goto_4
    if-ne v12, v2, :cond_b

    move/from16 v5, v17

    goto :goto_5

    :cond_b
    const/4 v5, 0x0

    :goto_5
    if-ne v4, v5, :cond_13

    if-ne v11, v2, :cond_d

    .line 172
    iget v2, v0, Lorg/telegram/messenger/EmojiPack;->count:I

    move/from16 v6, v16

    if-ge v6, v2, :cond_c

    invoke-static {v6}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result v2

    add-int/lit8 v2, v2, 0xe

    invoke-direct {v0, v2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result v2

    if-ne v10, v2, :cond_c

    goto :goto_6

    .line 173
    :cond_c
    const-string v0, "Map reference palette size mismatch"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    :cond_d
    move/from16 v6, v16

    :goto_6
    if-nez v12, :cond_f

    const v5, 0xffff

    if-ne v6, v5, :cond_e

    if-ne v9, v5, :cond_e

    goto :goto_9

    .line 177
    :cond_e
    const-string v0, "Unexpected root reference"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 180
    :cond_f
    invoke-direct {v0, v6, v15}, Lorg/telegram/messenger/EmojiPack;->validateRoot(II)V

    if-eq v12, v8, :cond_12

    const/4 v2, 0x5

    if-eq v12, v2, :cond_12

    if-ne v12, v3, :cond_10

    goto :goto_7

    :cond_10
    const v5, 0xffff

    if-ne v9, v5, :cond_11

    goto :goto_9

    .line 182
    :cond_11
    const-string v0, "Unexpected second root"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 181
    :cond_12
    :goto_7
    invoke-direct {v0, v9, v15}, Lorg/telegram/messenger/EmojiPack;->validateRoot(II)V

    goto :goto_9

    .line 170
    :cond_13
    const-string v0, "Invalid map reference encoding"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 167
    :cond_14
    const-string v0, "Invalid palette at record "

    invoke-static {v0, v15}, Lokio/internal/ZipFilesKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-void

    :goto_8
    if-nez v10, :cond_15

    .line 157
    invoke-direct {v0, v13}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v2

    if-nez v2, :cond_15

    if-nez v12, :cond_15

    add-int/lit8 v4, v4, 0x13

    invoke-direct {v0, v4}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result v2

    and-int/lit8 v2, v2, 0x7f

    if-nez v2, :cond_15

    const v5, 0xffff

    if-ne v6, v5, :cond_15

    if-ne v9, v5, :cond_15

    :goto_9
    add-int/lit8 v3, v15, 0x1

    goto/16 :goto_0

    .line 159
    :cond_15
    const-string v0, "Invalid full image record "

    invoke-static {v0, v15}, Lokio/internal/ZipFilesKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    return-void

    :cond_16
    move v15, v3

    .line 154
    :cond_17
    const-string v0, "Invalid EPK3 record "

    invoke-static {v0, v15}, Lokio/internal/ZipFilesKt$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;I)V

    :cond_18
    return-void
.end method

.method private validateRoot(II)V
    .locals 1

    .line 188
    iget v0, p0, Lorg/telegram/messenger/EmojiPack;->count:I

    if-ge p1, v0, :cond_1

    if-eq p1, p2, :cond_1

    .line 191
    invoke-static {p1}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result p1

    add-int/lit8 p2, p1, 0x10

    .line 192
    invoke-direct {p0, p2}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result p2

    const/4 v0, 0x1

    if-gt p2, v0, :cond_0

    add-int/lit8 p2, p1, 0x11

    invoke-direct {p0, p2}, Lorg/telegram/messenger/EmojiPack;->u8(I)I

    move-result p2

    if-nez p2, :cond_0

    add-int/lit8 p2, p1, 0xa

    .line 193
    invoke-direct {p0, p2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result p2

    const v0, 0xffff

    if-ne p2, v0, :cond_0

    add-int/lit8 p1, p1, 0xc

    invoke-direct {p0, p1}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result p0

    if-ne p0, v0, :cond_0

    return-void

    .line 194
    :cond_0
    const-string p0, "EPK3 dependency chains are forbidden"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 189
    :cond_1
    const-string p0, "Invalid root reference"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public declared-synchronized clearCache()V
    .locals 3

    monitor-enter p0

    .line 216
    :try_start_0
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->rootIds:[I

    const/4 v1, -0x1

    invoke-static {v0, v1}, Ljava/util/Arrays;->fill([II)V

    .line 217
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->rootMaps:[[B

    const/4 v1, 0x0

    invoke-static {v0, v1}, Ljava/util/Arrays;->fill([Ljava/lang/Object;Ljava/lang/Object;)V

    .line 218
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->rootAges:[J

    const-wide/16 v1, 0x0

    invoke-static {v0, v1, v2}, Ljava/util/Arrays;->fill([JJ)V

    .line 219
    iput-wide v1, p0, Lorg/telegram/messenger/EmojiPack;->age:J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 220
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized getEmoji(II)Landroid/graphics/Bitmap;
    .locals 3

    monitor-enter p0

    .line 200
    :try_start_0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/EmojiPack;->findEmoji(II)I

    move-result p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-gez p1, :cond_0

    .line 201
    monitor-exit p0

    const/4 p0, 0x0

    return-object p0

    .line 202
    :cond_0
    :try_start_1
    invoke-static {p1}, Lorg/telegram/messenger/EmojiPack;->entry(I)I

    move-result p2

    add-int/lit8 p2, p2, 0x2

    invoke-direct {p0, p2}, Lorg/telegram/messenger/EmojiPack;->u16(I)I

    move-result p2

    const v0, 0xffff

    if-ne p2, v0, :cond_1

    .line 203
    invoke-direct {p0, p1}, Lorg/telegram/messenger/EmojiPack;->decode(I)Landroid/graphics/Bitmap;

    move-result-object p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 204
    :cond_1
    :try_start_2
    iget-object v0, p0, Lorg/telegram/messenger/EmojiPack;->emojiPixels:[I

    invoke-direct {p0, p1, v0}, Lorg/telegram/messenger/EmojiPack;->decodePixels(I[I)V

    .line 205
    iget p1, p0, Lorg/telegram/messenger/EmojiPack;->emojiCount:I

    iget v0, p0, Lorg/telegram/messenger/EmojiPack;->count:I

    invoke-direct {p0, p2, p1, v0}, Lorg/telegram/messenger/EmojiPack;->find(III)I

    move-result p1

    iget-object p2, p0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/EmojiPack;->decodePixels(I[I)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    const/4 p1, 0x0

    .line 211
    :goto_0
    iget-object p2, p0, Lorg/telegram/messenger/EmojiPack;->emojiPixels:[I

    const/16 v0, 0x1000

    if-ge p1, v0, :cond_2

    .line 207
    :try_start_3
    aget v0, p2, p1

    ushr-int/lit8 v1, v0, 0x18

    .line 208
    iget-object v2, p0, Lorg/telegram/messenger/EmojiPack;->pixels:[I

    aget v2, v2, p1

    and-int/lit16 v2, v2, 0xff

    mul-int/2addr v1, v2

    add-int/lit8 v1, v1, 0x7f

    div-int/lit16 v1, v1, 0xff

    const v2, 0xffffff

    and-int/2addr v0, v2

    shl-int/lit8 v1, v1, 0x18

    or-int/2addr v0, v1

    .line 209
    aput v0, p2, p1

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 211
    :cond_2
    sget-object p1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    const/16 v0, 0x40

    invoke-static {p2, v0, v0, p1}, Landroid/graphics/Bitmap;->createBitmap([IIILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    monitor-exit p0

    return-object p1

    :goto_1
    :try_start_4
    monitor-exit p0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    throw p1
.end method
