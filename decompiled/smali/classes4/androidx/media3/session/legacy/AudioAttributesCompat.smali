.class public Landroidx/media3/session/legacy/AudioAttributesCompat;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;,
        Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImplApi26;,
        Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImplApi21;,
        Landroidx/media3/session/legacy/AudioAttributesCompat$Builder;
    }
.end annotation


# static fields
.field private static final SDK_USAGES:[I

.field private static final SUPPRESSIBLE_USAGES:Landroid/util/SparseIntArray;


# instance fields
.field private final impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 183
    new-instance v0, Landroid/util/SparseIntArray;

    invoke-direct {v0}, Landroid/util/SparseIntArray;-><init>()V

    sput-object v0, Landroidx/media3/session/legacy/AudioAttributesCompat;->SUPPRESSIBLE_USAGES:Landroid/util/SparseIntArray;

    const/4 v1, 0x5

    const/4 v2, 0x1

    .line 184
    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    const/4 v1, 0x6

    const/4 v3, 0x2

    .line 185
    invoke-virtual {v0, v1, v3}, Landroid/util/SparseIntArray;->put(II)V

    const/4 v1, 0x7

    .line 186
    invoke-virtual {v0, v1, v3}, Landroid/util/SparseIntArray;->put(II)V

    const/16 v1, 0x8

    .line 187
    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    const/16 v1, 0x9

    .line 188
    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    const/16 v1, 0xa

    .line 189
    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    const/16 v0, 0x10

    .line 193
    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Landroidx/media3/session/legacy/AudioAttributesCompat;->SDK_USAGES:[I

    return-void

    :array_0
    .array-data 4
        0x0
        0x1
        0x2
        0x3
        0x4
        0x5
        0x6
        0x7
        0x8
        0x9
        0xa
        0xb
        0xc
        0xd
        0xe
        0x10
    .end array-data
.end method

.method public constructor <init>(Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;)V
    .locals 0

    .line 220
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 221
    iput-object p1, p0, Landroidx/media3/session/legacy/AudioAttributesCompat;->impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    return-void
.end method

.method public static toVolumeStreamType(II)I
    .locals 2

    and-int/lit8 v0, p0, 0x1

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    const/4 p0, 0x7

    return p0

    :cond_0
    const/4 v0, 0x4

    and-int/2addr p0, v0

    if-ne p0, v0, :cond_1

    const/4 p0, 0x6

    return p0

    :cond_1
    packed-switch p1, :pswitch_data_0

    :pswitch_0
    const/4 p0, 0x3

    return p0

    :pswitch_1
    return v1

    :pswitch_2
    const/16 p0, 0xa

    return p0

    :pswitch_3
    const/4 p0, 0x2

    return p0

    :pswitch_4
    const/4 p0, 0x5

    return p0

    :pswitch_5
    return v0

    :pswitch_6
    const/16 p0, 0x8

    return p0

    :pswitch_7
    const/4 p0, 0x0

    return p0

    :pswitch_data_0
    .packed-switch 0x2
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_2
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 1

    .line 480
    instance-of v0, p1, Landroidx/media3/session/legacy/AudioAttributesCompat;

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 483
    :cond_0
    check-cast p1, Landroidx/media3/session/legacy/AudioAttributesCompat;

    .line 484
    iget-object p0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat;->impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    iget-object p1, p1, Landroidx/media3/session/legacy/AudioAttributesCompat;->impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public getLegacyStreamType()I
    .locals 0

    .line 244
    iget-object p0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat;->impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    invoke-interface {p0}, Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;->getLegacyStreamType()I

    move-result p0

    return p0
.end method

.method public hashCode()I
    .locals 0

    .line 428
    iget-object p0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat;->impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result p0

    return p0
.end method

.method public toString()Ljava/lang/String;
    .locals 0

    .line 433
    iget-object p0, p0, Landroidx/media3/session/legacy/AudioAttributesCompat;->impl:Landroidx/media3/session/legacy/AudioAttributesCompat$AudioAttributesImpl;

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
