.class final Lcom/google/android/recaptcha/internal/zzahm;
.super Lcom/google/android/recaptcha/internal/zzaer;
.source "SourceFile"


# static fields
.field static final zzb:[I


# instance fields
.field private final zzc:I

.field private final zzd:Lcom/google/android/recaptcha/internal/zzaer;

.field private final zze:Lcom/google/android/recaptcha/internal/zzaer;

.field private final zzf:I

.field private final zzg:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0x2f

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzahm;->zzb:[I

    return-void

    :array_0
    .array-data 4
        0x1
        0x1
        0x2
        0x3
        0x5
        0x8
        0xd
        0x15
        0x22
        0x37
        0x59
        0x90
        0xe9
        0x179
        0x262
        0x3db
        0x63d
        0xa18
        0x1055
        0x1a6d
        0x2ac2
        0x452f
        0x6ff1
        0xb520
        0x12511
        0x1da31
        0x2ff42
        0x4d973
        0x7d8b5
        0xcb228
        0x148add
        0x213d05
        0x35c7e2
        0x5704e7
        0x8cccc9
        0xe3d1b0
        0x1709e79
        0x2547029
        0x3c50ea2
        0x6197ecb
        0x9de8d6d
        0xff80c38
        0x19d699a5
        0x29cea5dd
        0x43a53f82
        0x6d73e55f
        0x7fffffff
    .end array-data
.end method

.method private constructor <init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V
    .locals 2

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaer;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzf:I

    .line 3
    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    add-int/2addr v0, v1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzc:I

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result p1

    invoke-virtual {p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result p2

    invoke-static {p1, p2}, Ljava/lang/Math;->max(II)I

    move-result p1

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzg:I

    return-void
.end method

.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;[B)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzahm;-><init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V

    return-void
.end method

.method private static zzE(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 4

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    add-int v2, v0, v1

    .line 3
    new-array v2, v2, [B

    const/4 v3, 0x0

    .line 4
    invoke-virtual {p0, v2, v3, v3, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzx([BIII)V

    .line 5
    invoke-virtual {p1, v2, v3, v0, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzx([BIII)V

    .line 6
    invoke-static {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzu([B)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0
.end method

.method public static zzj(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 6

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    if-nez v0, :cond_0

    return-object p0

    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    if-nez v0, :cond_1

    return-object p1

    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v0

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    add-int/2addr v0, v1

    const/16 v1, 0x80

    if-ge v0, v1, :cond_2

    .line 3
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzahm;->zzE(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0

    :cond_2
    instance-of v2, p0, Lcom/google/android/recaptcha/internal/zzahm;

    if-eqz v2, :cond_4

    .line 4
    move-object v2, p0

    check-cast v2, Lcom/google/android/recaptcha/internal/zzahm;

    iget-object v3, v2, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v4

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v5

    add-int/2addr v4, v5

    if-ge v4, v1, :cond_3

    .line 6
    invoke-static {v3, p1}, Lcom/google/android/recaptcha/internal/zzahm;->zzE(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    iget-object p1, v2, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzahm;

    .line 7
    invoke-direct {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzahm;-><init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V

    return-object v0

    :cond_3
    iget-object v1, v2, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    .line 8
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result v4

    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result v5

    if-le v4, v5, :cond_4

    iget v2, v2, Lcom/google/android/recaptcha/internal/zzahm;->zzg:I

    .line 9
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result v4

    if-le v2, v4, :cond_4

    new-instance p0, Lcom/google/android/recaptcha/internal/zzahm;

    .line 10
    invoke-direct {p0, v3, p1}, Lcom/google/android/recaptcha/internal/zzahm;-><init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzahm;

    .line 11
    invoke-direct {p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzahm;-><init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V

    return-object p1

    :cond_4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result v1

    .line 12
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzo()I

    move-result v2

    invoke-static {v1, v2}, Ljava/lang/Math;->max(II)I

    move-result v1

    add-int/lit8 v1, v1, 0x1

    .line 13
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzahm;->zzm(I)I

    move-result v1

    if-lt v0, v1, :cond_5

    new-instance v0, Lcom/google/android/recaptcha/internal/zzahm;

    .line 16
    invoke-direct {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzahm;-><init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V

    return-object v0

    :cond_5
    new-instance v0, Ljava/util/ArrayDeque;

    .line 14
    invoke-direct {v0}, Ljava/util/ArrayDeque;-><init>()V

    .line 15
    invoke-static {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzahk;->zza(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;Ljava/util/ArrayDeque;)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0
.end method

.method public static zzm(I)I
    .locals 2

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzahm;->zzb:[I

    array-length v1, v0

    const/16 v1, 0x2f

    if-lt p0, v1, :cond_0

    const p0, 0x7fffffff

    return p0

    .line 2
    :cond_0
    aget p0, v0, p0

    return p0
.end method


# virtual methods
.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzahj;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzahj;-><init>(Lcom/google/android/recaptcha/internal/zzahm;)V

    return-object v0
.end method

.method public final synthetic zzD()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zza(I)B
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzf:I

    if-ge p1, v0, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zza(I)B

    move-result p0

    return p0

    :cond_0
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    sub-int/2addr p1, v0

    .line 2
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zza(I)B

    move-result p0

    return p0
.end method

.method public final zzb()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzc:I

    return p0
.end method

.method public final zzc(II)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzahm;->zzd(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0
.end method

.method public final zzd(II)Lcom/google/android/recaptcha/internal/zzaer;
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzc:I

    invoke-static {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzB(III)I

    move-result v1

    if-nez v1, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaer;->zza:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0

    :cond_0
    if-ne v1, v0, :cond_1

    return-object p0

    :cond_1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzf:I

    if-gt p2, v0, :cond_2

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    .line 2
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzc(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0

    :cond_2
    sub-int/2addr p2, v0

    if-lt p1, v0, :cond_3

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    sub-int/2addr p1, v0

    .line 3
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzc(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    return-object p0

    :cond_3
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    .line 4
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v1

    .line 5
    invoke-virtual {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzc(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    const/4 v0, 0x0

    .line 6
    invoke-virtual {p0, v0, p2}, Lcom/google/android/recaptcha/internal/zzaer;->zzc(II)Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    new-instance p2, Lcom/google/android/recaptcha/internal/zzahm;

    .line 7
    invoke-direct {p2, p1, p0}, Lcom/google/android/recaptcha/internal/zzahm;-><init>(Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzaer;)V

    return-object p2
.end method

.method public final zze([BIII)V
    .locals 2

    add-int v0, p2, p4

    .line 1
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzf:I

    if-gt v0, v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzaer;->zze([BIII)V

    return-void

    :cond_0
    if-lt p2, v1, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    sub-int/2addr p2, v1

    .line 2
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzaer;->zze([BIII)V

    return-void

    :cond_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    sub-int/2addr v1, p2

    .line 3
    invoke-virtual {v0, p1, p2, p3, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zze([BIII)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    add-int/2addr p3, v1

    sub-int/2addr p4, v1

    const/4 p2, 0x0

    .line 4
    invoke-virtual {p0, p1, p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzaer;->zze([BIII)V

    return-void
.end method

.method public final zzg(Lcom/google/android/recaptcha/internal/zzaej;)V
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzg(Lcom/google/android/recaptcha/internal/zzaej;)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    .line 2
    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzg(Lcom/google/android/recaptcha/internal/zzaej;)V

    return-void
.end method

.method public final zzh(Ljava/nio/charset/Charset;)Ljava/lang/String;
    .locals 1

    .line 1
    new-instance v0, Ljava/lang/String;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzy()[B

    move-result-object p0

    invoke-direct {v0, p0, p1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0
.end method

.method public final zzi(Lcom/google/android/recaptcha/internal/zzaer;)Z
    .locals 11

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzahl;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/google/android/recaptcha/internal/zzahl;-><init>(Lcom/google/android/recaptcha/internal/zzaer;[B)V

    .line 2
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object v2

    .line 3
    new-instance v3, Lcom/google/android/recaptcha/internal/zzahl;

    .line 4
    invoke-direct {v3, p1, v1}, Lcom/google/android/recaptcha/internal/zzahl;-><init>(Lcom/google/android/recaptcha/internal/zzaer;[B)V

    .line 5
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object p1

    const/4 v1, 0x0

    move v4, v1

    move v5, v4

    move v6, v5

    .line 6
    :goto_0
    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v7

    sub-int/2addr v7, v4

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzb()I

    move-result v8

    sub-int/2addr v8, v5

    .line 7
    invoke-static {v7, v8}, Ljava/lang/Math;->min(II)I

    move-result v9

    if-nez v4, :cond_0

    .line 8
    invoke-virtual {v2, p1, v5, v9}, Lcom/google/android/recaptcha/internal/zzaeo;->zzj(Lcom/google/android/recaptcha/internal/zzaer;II)Z

    move-result v10

    goto :goto_1

    .line 9
    :cond_0
    invoke-virtual {p1, v2, v4, v9}, Lcom/google/android/recaptcha/internal/zzaeo;->zzj(Lcom/google/android/recaptcha/internal/zzaer;II)Z

    move-result v10

    :goto_1
    if-nez v10, :cond_1

    return v1

    :cond_1
    add-int/2addr v6, v9

    .line 8
    iget v10, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzc:I

    if-lt v6, v10, :cond_3

    if-ne v6, v10, :cond_2

    const/4 p0, 0x1

    return p0

    .line 14
    :cond_2
    invoke-static {}, Lorg/mvel2/asm/MethodWriter$$ExternalSyntheticBUOutline0;->m()V

    return v1

    :cond_3
    if-ne v9, v7, :cond_4

    .line 10
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object v2

    move v4, v1

    goto :goto_2

    :cond_4
    add-int/2addr v4, v9

    :goto_2
    if-ne v9, v8, :cond_5

    .line 12
    invoke-virtual {v3}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object p1

    move v5, v1

    goto :goto_0

    :cond_5
    add-int/2addr v5, v9

    goto :goto_0
.end method

.method public final zzk(III)I
    .locals 2

    add-int v0, p2, p3

    .line 1
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzf:I

    if-gt v0, v1, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzk(III)I

    move-result p0

    return p0

    :cond_0
    if-lt p2, v1, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    sub-int/2addr p2, v1

    .line 2
    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzk(III)I

    move-result p0

    return p0

    :cond_1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    sub-int/2addr v1, p2

    .line 3
    invoke-virtual {v0, p1, p2, v1}, Lcom/google/android/recaptcha/internal/zzaer;->zzk(III)I

    move-result p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zze:Lcom/google/android/recaptcha/internal/zzaer;

    const/4 p2, 0x0

    sub-int/2addr p3, v1

    .line 4
    invoke-virtual {p0, p1, p2, p3}, Lcom/google/android/recaptcha/internal/zzaer;->zzk(III)I

    move-result p0

    return p0
.end method

.method public final zzl()Lcom/google/android/recaptcha/internal/zzaev;
    .locals 3

    .line 1
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    new-instance v1, Lcom/google/android/recaptcha/internal/zzahl;

    const/4 v2, 0x0

    .line 2
    invoke-direct {v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzahl;-><init>(Lcom/google/android/recaptcha/internal/zzaer;[B)V

    :goto_0
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzahl;->hasNext()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 3
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzahl;->zza()Lcom/google/android/recaptcha/internal/zzaeo;

    move-result-object p0

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaer;->zzf()Ljava/nio/ByteBuffer;

    move-result-object p0

    invoke-interface {v0, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 5
    :cond_0
    sget p0, Lcom/google/android/recaptcha/internal/zzaev;->$r8$clinit:I

    new-instance p0, Lcom/google/android/recaptcha/internal/zzagl;

    .line 6
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzagl;-><init>(Ljava/lang/Iterable;)V

    .line 7
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaeu;

    const/16 v1, 0x1000

    invoke-direct {v0, p0, v1, v2}, Lcom/google/android/recaptcha/internal/zzaeu;-><init>(Ljava/io/InputStream;I[B)V

    return-object v0
.end method

.method public final synthetic zzn()Lcom/google/android/recaptcha/internal/zzaer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzd:Lcom/google/android/recaptcha/internal/zzaer;

    return-object p0
.end method

.method public final zzo()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzg:I

    return p0
.end method

.method public final zzp()Z
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzg:I

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzahm;->zzc:I

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzahm;->zzm(I)I

    move-result v0

    if-lt p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final zzq()Lcom/google/android/recaptcha/internal/zzaen;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzahj;

    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzahj;-><init>(Lcom/google/android/recaptcha/internal/zzahm;)V

    return-object v0
.end method
