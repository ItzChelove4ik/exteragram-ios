.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg<",
        "TT;>;"
    }
.end annotation


# static fields
.field private static final zza:[I

.field private static final zzb:Lsun/misc/Unsafe;


# instance fields
.field private final zzc:[I

.field private final zzd:[Ljava/lang/Object;

.field private final zze:I

.field private final zzf:I

.field private final zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

.field private final zzh:Z

.field private final zzi:[I

.field private final zzj:I

.field private final zzk:I

.field private final zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

.field private final zzm:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    .line 1
    new-array v0, v0, [I

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zza:[I

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzn()Lsun/misc/Unsafe;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    return-void
.end method

.method private constructor <init>([I[Ljava/lang/Object;IILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;Z[IIILcom/google/android/gms/internal/mlkit_genai_prompt/zzayb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxn;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzd:[Ljava/lang/Object;

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zze:I

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzf:I

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    iput-object p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzi:[I

    iput p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzj:I

    iput p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk:I

    iput-object p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzm:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    return-void
.end method

.method private final zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z
    .locals 0

    .line 1
    invoke-direct {p0, p1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result p1

    invoke-direct {p0, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result p0

    if-ne p1, p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final zzB(Ljava/lang/Object;IIII)Z
    .locals 1

    const v0, 0xfffff

    if-ne p3, v0, :cond_0

    .line 1
    invoke-direct {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result p0

    return p0

    :cond_0
    and-int p0, p4, p5

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method private final zzC(Ljava/lang/Object;I)Z
    .locals 7

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzw(I)I

    move-result v0

    const v1, 0xfffff

    and-int v2, v0, v1

    int-to-long v2, v2

    const-wide/32 v4, 0xfffff

    cmp-long v4, v2, v4

    const/4 v5, 0x0

    const/4 v6, 0x1

    if-nez v4, :cond_14

    .line 2
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result p2

    and-int v0, p2, v1

    invoke-static {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result p2

    int-to-long v0, v0

    const-wide/16 v2, 0x0

    packed-switch p2, :pswitch_data_0

    .line 25
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzI()Z

    move-result p0

    return p0

    .line 3
    :pswitch_0
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_0

    return v6

    :cond_0
    return v5

    .line 4
    :pswitch_1
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_1

    return v6

    :cond_1
    return v5

    .line 5
    :pswitch_2
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_2

    return v6

    :cond_2
    return v5

    .line 6
    :pswitch_3
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_3

    return v6

    :cond_3
    return v5

    .line 7
    :pswitch_4
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_4

    return v6

    :cond_4
    return v5

    .line 8
    :pswitch_5
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_5

    return v6

    :cond_5
    return v5

    .line 9
    :pswitch_6
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_6

    return v6

    :cond_6
    return v5

    .line 10
    :pswitch_7
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_7

    return v6

    :cond_7
    return v5

    .line 11
    :pswitch_8
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_8

    return v6

    :cond_8
    return v5

    .line 12
    :pswitch_9
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p1

    .line 13
    instance-of p2, p1, Ljava/lang/String;

    if-eqz p2, :cond_a

    .line 14
    check-cast p1, Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_9

    return v6

    :cond_9
    return v5

    :cond_a
    instance-of p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    if-eqz p2, :cond_c

    .line 15
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    invoke-virtual {p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_b

    return v6

    :cond_b
    return v5

    .line 16
    :cond_c
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzI()Z

    move-result p0

    return p0

    .line 17
    :pswitch_a
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzf(Ljava/lang/Object;J)Z

    move-result p0

    return p0

    .line 18
    :pswitch_b
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_d

    return v6

    :cond_d
    return v5

    .line 19
    :pswitch_c
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_e

    return v6

    :cond_e
    return v5

    .line 20
    :pswitch_d
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-eqz p0, :cond_f

    return v6

    :cond_f
    return v5

    .line 21
    :pswitch_e
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_10

    return v6

    :cond_10
    return v5

    .line 22
    :pswitch_f
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_11

    return v6

    :cond_11
    return v5

    .line 23
    :pswitch_10
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzh(Ljava/lang/Object;J)F

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->floatToRawIntBits(F)I

    move-result p0

    if-eqz p0, :cond_12

    return v6

    :cond_12
    return v5

    .line 24
    :pswitch_11
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzj(Ljava/lang/Object;J)D

    move-result-wide p0

    invoke-static {p0, p1}, Ljava/lang/Double;->doubleToRawLongBits(D)J

    move-result-wide p0

    cmp-long p0, p0, v2

    if-eqz p0, :cond_13

    return v6

    :cond_13
    return v5

    :cond_14
    ushr-int/lit8 p0, v0, 0x14

    shl-int p0, v6, p0

    .line 26
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p1

    and-int/2addr p0, p1

    if-eqz p0, :cond_15

    return v6

    :cond_15
    return v5

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private final zzD(Ljava/lang/Object;I)V
    .locals 4

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzw(I)I

    move-result p0

    const p2, 0xfffff

    and-int/2addr p2, p0

    int-to-long v0, p2

    const-wide/32 v2, 0xfffff

    cmp-long p2, v0, v2

    if-nez p2, :cond_0

    return-void

    :cond_0
    ushr-int/lit8 p0, p0, 0x14

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p2

    const/4 v2, 0x1

    shl-int p0, v2, p0

    or-int/2addr p0, p2

    .line 3
    invoke-static {p1, v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    return-void
.end method

.method private final zzE(Ljava/lang/Object;II)Z
    .locals 2

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzw(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v0, p0

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    if-ne p0, p2, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final zzF(Ljava/lang/Object;Ljava/lang/Object;I)Z
    .locals 2

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzw(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v0, p0

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p0

    .line 3
    invoke-static {p2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result p1

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final zzG(Ljava/lang/Object;II)V
    .locals 2

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzw(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v0, p0

    invoke-static {p1, v0, v1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    return-void
.end method

.method private final zzH(II)I
    .locals 5

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    array-length v0, p0

    div-int/lit8 v0, v0, 0x3

    const/4 v1, -0x1

    add-int/2addr v0, v1

    :goto_0
    if-gt p2, v0, :cond_2

    add-int v2, v0, p2

    ushr-int/lit8 v2, v2, 0x1

    mul-int/lit8 v3, v2, 0x3

    aget v4, p0, v3

    if-ne p1, v4, :cond_0

    return v3

    :cond_0
    if-ge p1, v4, :cond_1

    add-int/lit8 v0, v2, -0x1

    goto :goto_0

    :cond_1
    add-int/lit8 p2, v2, 0x1

    goto :goto_0

    :cond_2
    return v1
.end method

.method private zzI()Z
    .locals 0

    .line 1
    new-instance p0, Ljava/lang/IllegalArgumentException;

    invoke-direct {p0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw p0
.end method

.method public static zze(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;
    .locals 2

    .line 1
    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v1

    if-ne v0, v1, :cond_0

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v0

    .line 2
    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    :cond_0
    return-object v0
.end method

.method public static zzj(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxu;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxn;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;
    .locals 32

    move-object/from16 v0, p1

    .line 1
    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    const/4 v2, 0x0

    if-eqz v1, :cond_37

    instance-of v3, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;

    if-eqz v3, :cond_36

    .line 2
    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;

    .line 3
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;->zzd()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v3

    const/4 v4, 0x0

    .line 4
    invoke-virtual {v2, v4}, Ljava/lang/String;->charAt(I)C

    move-result v5

    const v6, 0xd800

    if-lt v5, v6, :cond_0

    const/4 v5, 0x1

    :goto_0
    add-int/lit8 v8, v5, 0x1

    .line 5
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_1

    move v5, v8

    goto :goto_0

    :cond_0
    const/4 v8, 0x1

    :cond_1
    add-int/lit8 v5, v8, 0x1

    .line 6
    invoke-virtual {v2, v8}, Ljava/lang/String;->charAt(I)C

    move-result v8

    if-lt v8, v6, :cond_3

    and-int/lit16 v8, v8, 0x1fff

    const/16 v10, 0xd

    :goto_1
    add-int/lit8 v11, v5, 0x1

    .line 7
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_2

    and-int/lit16 v5, v5, 0x1fff

    shl-int/2addr v5, v10

    or-int/2addr v8, v5

    add-int/lit8 v10, v10, 0xd

    move v5, v11

    goto :goto_1

    :cond_2
    shl-int/2addr v5, v10

    or-int/2addr v8, v5

    move v5, v11

    :cond_3
    if-nez v8, :cond_4

    sget-object v8, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zza:[I

    move v12, v4

    move v13, v12

    move v14, v13

    move/from16 v16, v14

    move/from16 v18, v16

    move-object/from16 v17, v8

    move/from16 v8, v18

    goto/16 :goto_a

    :cond_4
    add-int/lit8 v8, v5, 0x1

    .line 8
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_6

    and-int/lit16 v5, v5, 0x1fff

    const/16 v10, 0xd

    :goto_2
    add-int/lit8 v11, v8, 0x1

    .line 9
    invoke-virtual {v2, v8}, Ljava/lang/String;->charAt(I)C

    move-result v8

    if-lt v8, v6, :cond_5

    and-int/lit16 v8, v8, 0x1fff

    shl-int/2addr v8, v10

    or-int/2addr v5, v8

    add-int/lit8 v10, v10, 0xd

    move v8, v11

    goto :goto_2

    :cond_5
    shl-int/2addr v8, v10

    or-int/2addr v5, v8

    move v8, v11

    :cond_6
    add-int/lit8 v10, v8, 0x1

    .line 10
    invoke-virtual {v2, v8}, Ljava/lang/String;->charAt(I)C

    move-result v8

    if-lt v8, v6, :cond_8

    and-int/lit16 v8, v8, 0x1fff

    const/16 v11, 0xd

    :goto_3
    add-int/lit8 v12, v10, 0x1

    .line 11
    invoke-virtual {v2, v10}, Ljava/lang/String;->charAt(I)C

    move-result v10

    if-lt v10, v6, :cond_7

    and-int/lit16 v10, v10, 0x1fff

    shl-int/2addr v10, v11

    or-int/2addr v8, v10

    add-int/lit8 v11, v11, 0xd

    move v10, v12

    goto :goto_3

    :cond_7
    shl-int/2addr v10, v11

    or-int/2addr v8, v10

    move v10, v12

    :cond_8
    add-int/lit8 v11, v10, 0x1

    .line 12
    invoke-virtual {v2, v10}, Ljava/lang/String;->charAt(I)C

    move-result v10

    if-lt v10, v6, :cond_a

    and-int/lit16 v10, v10, 0x1fff

    const/16 v12, 0xd

    :goto_4
    add-int/lit8 v13, v11, 0x1

    .line 13
    invoke-virtual {v2, v11}, Ljava/lang/String;->charAt(I)C

    move-result v11

    if-lt v11, v6, :cond_9

    and-int/lit16 v11, v11, 0x1fff

    shl-int/2addr v11, v12

    or-int/2addr v10, v11

    add-int/lit8 v12, v12, 0xd

    move v11, v13

    goto :goto_4

    :cond_9
    shl-int/2addr v11, v12

    or-int/2addr v10, v11

    move v11, v13

    :cond_a
    add-int/lit8 v12, v11, 0x1

    .line 14
    invoke-virtual {v2, v11}, Ljava/lang/String;->charAt(I)C

    move-result v11

    if-lt v11, v6, :cond_c

    and-int/lit16 v11, v11, 0x1fff

    const/16 v13, 0xd

    :goto_5
    add-int/lit8 v14, v12, 0x1

    .line 15
    invoke-virtual {v2, v12}, Ljava/lang/String;->charAt(I)C

    move-result v12

    if-lt v12, v6, :cond_b

    and-int/lit16 v12, v12, 0x1fff

    shl-int/2addr v12, v13

    or-int/2addr v11, v12

    add-int/lit8 v13, v13, 0xd

    move v12, v14

    goto :goto_5

    :cond_b
    shl-int/2addr v12, v13

    or-int/2addr v11, v12

    move v12, v14

    :cond_c
    add-int/lit8 v13, v12, 0x1

    .line 16
    invoke-virtual {v2, v12}, Ljava/lang/String;->charAt(I)C

    move-result v12

    if-lt v12, v6, :cond_e

    and-int/lit16 v12, v12, 0x1fff

    const/16 v14, 0xd

    :goto_6
    add-int/lit8 v15, v13, 0x1

    .line 17
    invoke-virtual {v2, v13}, Ljava/lang/String;->charAt(I)C

    move-result v13

    if-lt v13, v6, :cond_d

    and-int/lit16 v13, v13, 0x1fff

    shl-int/2addr v13, v14

    or-int/2addr v12, v13

    add-int/lit8 v14, v14, 0xd

    move v13, v15

    goto :goto_6

    :cond_d
    shl-int/2addr v13, v14

    or-int/2addr v12, v13

    move v13, v15

    :cond_e
    add-int/lit8 v14, v13, 0x1

    .line 18
    invoke-virtual {v2, v13}, Ljava/lang/String;->charAt(I)C

    move-result v13

    if-lt v13, v6, :cond_10

    and-int/lit16 v13, v13, 0x1fff

    const/16 v15, 0xd

    :goto_7
    add-int/lit8 v16, v14, 0x1

    .line 19
    invoke-virtual {v2, v14}, Ljava/lang/String;->charAt(I)C

    move-result v14

    if-lt v14, v6, :cond_f

    and-int/lit16 v14, v14, 0x1fff

    shl-int/2addr v14, v15

    or-int/2addr v13, v14

    add-int/lit8 v15, v15, 0xd

    move/from16 v14, v16

    goto :goto_7

    :cond_f
    shl-int/2addr v14, v15

    or-int/2addr v13, v14

    move/from16 v14, v16

    :cond_10
    add-int/lit8 v15, v14, 0x1

    .line 20
    invoke-virtual {v2, v14}, Ljava/lang/String;->charAt(I)C

    move-result v14

    if-lt v14, v6, :cond_12

    :goto_8
    add-int/lit8 v14, v15, 0x1

    .line 21
    invoke-virtual {v2, v15}, Ljava/lang/String;->charAt(I)C

    move-result v15

    if-lt v15, v6, :cond_11

    move v15, v14

    goto :goto_8

    :cond_11
    move v15, v14

    :cond_12
    add-int/lit8 v14, v15, 0x1

    .line 22
    invoke-virtual {v2, v15}, Ljava/lang/String;->charAt(I)C

    move-result v15

    if-lt v15, v6, :cond_14

    and-int/lit16 v15, v15, 0x1fff

    const/16 v16, 0xd

    :goto_9
    add-int/lit8 v17, v14, 0x1

    .line 23
    invoke-virtual {v2, v14}, Ljava/lang/String;->charAt(I)C

    move-result v14

    if-lt v14, v6, :cond_13

    and-int/lit16 v14, v14, 0x1fff

    shl-int v14, v14, v16

    or-int/2addr v15, v14

    add-int/lit8 v16, v16, 0xd

    move/from16 v14, v17

    goto :goto_9

    :cond_13
    shl-int v14, v14, v16

    or-int/2addr v15, v14

    move/from16 v14, v17

    :cond_14
    add-int v16, v15, v13

    add-int v4, v16, v5

    add-int v16, v5, v5

    add-int v16, v16, v8

    .line 24
    new-array v8, v4, [I

    move v4, v5

    move-object/from16 v17, v8

    move v8, v13

    move v5, v14

    move/from16 v18, v15

    move v13, v10

    move v14, v11

    .line 25
    :goto_a
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;->zze()[Ljava/lang/Object;

    move-result-object v10

    .line 26
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v11

    add-int v19, v18, v8

    add-int v8, v12, v12

    mul-int/lit8 v12, v12, 0x3

    .line 27
    new-array v12, v12, [I

    .line 28
    new-array v8, v8, [Ljava/lang/Object;

    move/from16 v22, v18

    move/from16 v21, v19

    const/4 v15, 0x0

    const/16 v20, 0x0

    :goto_b
    if-ge v5, v3, :cond_35

    add-int/lit8 v23, v5, 0x1

    .line 29
    invoke-virtual {v2, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    if-lt v5, v6, :cond_16

    and-int/lit16 v5, v5, 0x1fff

    move/from16 v9, v23

    const/16 v23, 0xd

    :goto_c
    add-int/lit8 v24, v9, 0x1

    .line 30
    invoke-virtual {v2, v9}, Ljava/lang/String;->charAt(I)C

    move-result v9

    if-lt v9, v6, :cond_15

    and-int/lit16 v9, v9, 0x1fff

    shl-int v9, v9, v23

    or-int/2addr v5, v9

    add-int/lit8 v23, v23, 0xd

    move/from16 v9, v24

    goto :goto_c

    :cond_15
    shl-int v9, v9, v23

    or-int/2addr v5, v9

    move/from16 v9, v24

    goto :goto_d

    :cond_16
    move/from16 v9, v23

    :goto_d
    add-int/lit8 v23, v9, 0x1

    .line 31
    invoke-virtual {v2, v9}, Ljava/lang/String;->charAt(I)C

    move-result v9

    if-lt v9, v6, :cond_18

    and-int/lit16 v9, v9, 0x1fff

    move/from16 v7, v23

    const/16 v23, 0xd

    :goto_e
    add-int/lit8 v25, v7, 0x1

    .line 32
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    if-lt v7, v6, :cond_17

    and-int/lit16 v7, v7, 0x1fff

    shl-int v7, v7, v23

    or-int/2addr v9, v7

    add-int/lit8 v23, v23, 0xd

    move/from16 v7, v25

    goto :goto_e

    :cond_17
    shl-int v7, v7, v23

    or-int/2addr v9, v7

    move/from16 v7, v25

    goto :goto_f

    :cond_18
    move/from16 v7, v23

    :goto_f
    and-int/lit16 v6, v9, 0x400

    if-eqz v6, :cond_19

    add-int/lit8 v6, v15, 0x1

    .line 33
    aput v20, v17, v15

    move v15, v6

    :cond_19
    and-int/lit16 v6, v9, 0xff

    move-object/from16 v25, v0

    and-int/lit16 v0, v9, 0x800

    move/from16 v26, v0

    const/16 v0, 0x33

    if-lt v6, v0, :cond_23

    add-int/lit8 v0, v7, 0x1

    .line 34
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    move/from16 v27, v0

    const v0, 0xd800

    if-lt v7, v0, :cond_1b

    and-int/lit16 v7, v7, 0x1fff

    move/from16 v30, v27

    move/from16 v27, v7

    move/from16 v7, v30

    const/16 v30, 0xd

    :goto_10
    add-int/lit8 v31, v7, 0x1

    .line 35
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    if-lt v7, v0, :cond_1a

    and-int/lit16 v0, v7, 0x1fff

    shl-int v0, v0, v30

    or-int v27, v27, v0

    add-int/lit8 v30, v30, 0xd

    move/from16 v7, v31

    const v0, 0xd800

    goto :goto_10

    :cond_1a
    shl-int v0, v7, v30

    or-int v7, v27, v0

    move/from16 v0, v31

    goto :goto_11

    :cond_1b
    move/from16 v0, v27

    :goto_11
    move/from16 v27, v0

    add-int/lit8 v0, v6, -0x33

    move/from16 v30, v3

    const/16 v3, 0x9

    if-eq v0, v3, :cond_1c

    const/16 v3, 0x11

    if-ne v0, v3, :cond_1d

    :cond_1c
    const/4 v3, 0x1

    goto :goto_14

    :cond_1d
    const/16 v3, 0xc

    if-ne v0, v3, :cond_20

    .line 37
    invoke-virtual/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;->zzc()I

    move-result v0

    const/4 v3, 0x1

    if-eq v0, v3, :cond_1f

    if-eqz v26, :cond_1e

    goto :goto_12

    :cond_1e
    const/4 v0, 0x0

    goto :goto_15

    :cond_1f
    :goto_12
    add-int/lit8 v0, v16, 0x1

    div-int/lit8 v24, v20, 0x3

    add-int v24, v24, v24

    add-int/lit8 v24, v24, 0x1

    .line 38
    aget-object v16, v10, v16

    aput-object v16, v8, v24

    :goto_13
    move/from16 v16, v0

    :cond_20
    move/from16 v0, v26

    goto :goto_15

    :goto_14
    add-int/lit8 v0, v16, 0x1

    .line 35
    div-int/lit8 v24, v20, 0x3

    add-int v24, v24, v24

    add-int/lit8 v28, v24, 0x1

    .line 36
    aget-object v3, v10, v16

    aput-object v3, v8, v28

    goto :goto_13

    :goto_15
    add-int/2addr v7, v7

    .line 39
    aget-object v3, v10, v7

    move/from16 v26, v0

    .line 40
    instance-of v0, v3, Ljava/lang/reflect/Field;

    if-eqz v0, :cond_21

    .line 41
    check-cast v3, Ljava/lang/reflect/Field;

    :goto_16
    move v0, v4

    goto :goto_17

    .line 42
    :cond_21
    check-cast v3, Ljava/lang/String;

    invoke-static {v11, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    .line 43
    aput-object v3, v10, v7

    add-int/lit8 v0, v21, 0x1

    .line 44
    aput v20, v17, v21

    move/from16 v21, v0

    goto :goto_16

    .line 45
    :goto_17
    invoke-virtual {v1, v3}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    long-to-int v3, v3

    add-int/lit8 v7, v7, 0x1

    .line 46
    aget-object v4, v10, v7

    move/from16 v31, v0

    .line 47
    instance-of v0, v4, Ljava/lang/reflect/Field;

    if-eqz v0, :cond_22

    .line 48
    check-cast v4, Ljava/lang/reflect/Field;

    :goto_18
    move v0, v3

    goto :goto_19

    .line 49
    :cond_22
    check-cast v4, Ljava/lang/String;

    invoke-static {v11, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v4

    .line 50
    aput-object v4, v10, v7

    goto :goto_18

    .line 51
    :goto_19
    invoke-virtual {v1, v4}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    long-to-int v3, v3

    move-object/from16 v28, v2

    move/from16 v7, v27

    const/4 v2, 0x0

    move/from16 v27, v16

    move/from16 v16, v3

    move v3, v0

    move/from16 v0, v26

    goto/16 :goto_23

    :cond_23
    move/from16 v30, v3

    move/from16 v31, v4

    add-int/lit8 v0, v16, 0x1

    .line 52
    aget-object v3, v10, v16

    check-cast v3, Ljava/lang/String;

    invoke-static {v11, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v3

    const/16 v4, 0x9

    if-eq v6, v4, :cond_24

    const/16 v4, 0x11

    if-ne v6, v4, :cond_25

    :cond_24
    move/from16 v27, v0

    const/4 v0, 0x1

    goto/16 :goto_1e

    :cond_25
    const/16 v4, 0x1b

    if-eq v6, v4, :cond_2d

    const/16 v4, 0x31

    if-ne v6, v4, :cond_26

    add-int/lit8 v16, v16, 0x2

    move/from16 v27, v0

    const/4 v0, 0x1

    goto :goto_1d

    :cond_26
    const/16 v4, 0xc

    if-eq v6, v4, :cond_2a

    const/16 v4, 0x1e

    if-eq v6, v4, :cond_2a

    const/16 v4, 0x2c

    if-ne v6, v4, :cond_27

    goto :goto_1a

    :cond_27
    const/16 v4, 0x32

    if-ne v6, v4, :cond_28

    add-int/lit8 v4, v16, 0x2

    add-int/lit8 v27, v22, 0x1

    .line 57
    aput v20, v17, v22

    div-int/lit8 v22, v20, 0x3

    .line 58
    aget-object v0, v10, v0

    add-int v22, v22, v22

    aput-object v0, v8, v22

    if-eqz v26, :cond_29

    add-int/lit8 v22, v22, 0x1

    add-int/lit8 v0, v16, 0x3

    .line 59
    aget-object v4, v10, v4

    aput-object v4, v8, v22

    move/from16 v22, v27

    :cond_28
    move/from16 v27, v0

    const/4 v0, 0x1

    goto :goto_1f

    :cond_29
    move/from16 v22, v27

    const/4 v0, 0x1

    const/16 v26, 0x0

    move/from16 v27, v4

    goto :goto_1f

    .line 55
    :cond_2a
    :goto_1a
    invoke-virtual/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;->zzc()I

    move-result v4

    move/from16 v27, v0

    const/4 v0, 0x1

    if-eq v4, v0, :cond_2c

    if-eqz v26, :cond_2b

    goto :goto_1b

    :cond_2b
    const/16 v26, 0x0

    goto :goto_1f

    :cond_2c
    :goto_1b
    add-int/lit8 v16, v16, 0x2

    div-int/lit8 v4, v20, 0x3

    add-int/2addr v4, v4

    add-int/2addr v4, v0

    .line 56
    aget-object v24, v10, v27

    aput-object v24, v8, v4

    :goto_1c
    move/from16 v27, v16

    goto :goto_1f

    :cond_2d
    move/from16 v27, v0

    const/4 v0, 0x1

    add-int/lit8 v16, v16, 0x2

    .line 67
    :goto_1d
    div-int/lit8 v4, v20, 0x3

    add-int/2addr v4, v4

    add-int/2addr v4, v0

    .line 54
    aget-object v24, v10, v27

    aput-object v24, v8, v4

    goto :goto_1c

    .line 52
    :goto_1e
    div-int/lit8 v4, v20, 0x3

    add-int/2addr v4, v4

    add-int/2addr v4, v0

    .line 53
    invoke-virtual {v3}, Ljava/lang/reflect/Field;->getType()Ljava/lang/Class;

    move-result-object v16

    aput-object v16, v8, v4

    .line 60
    :goto_1f
    invoke-virtual {v1, v3}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v3

    long-to-int v3, v3

    and-int/lit16 v4, v9, 0x1000

    const v16, 0xfffff

    if-eqz v4, :cond_31

    const/16 v4, 0x11

    if-gt v6, v4, :cond_31

    add-int/lit8 v4, v7, 0x1

    .line 61
    invoke-virtual {v2, v7}, Ljava/lang/String;->charAt(I)C

    move-result v7

    const v0, 0xd800

    if-lt v7, v0, :cond_2f

    and-int/lit16 v7, v7, 0x1fff

    const/16 v16, 0xd

    :goto_20
    add-int/lit8 v23, v4, 0x1

    .line 62
    invoke-virtual {v2, v4}, Ljava/lang/String;->charAt(I)C

    move-result v4

    if-lt v4, v0, :cond_2e

    and-int/lit16 v4, v4, 0x1fff

    shl-int v4, v4, v16

    or-int/2addr v7, v4

    add-int/lit8 v16, v16, 0xd

    move/from16 v4, v23

    goto :goto_20

    :cond_2e
    shl-int v4, v4, v16

    or-int/2addr v7, v4

    move/from16 v4, v23

    :cond_2f
    add-int v16, v31, v31

    div-int/lit8 v23, v7, 0x20

    add-int v16, v16, v23

    .line 63
    aget-object v0, v10, v16

    move-object/from16 v28, v2

    .line 64
    instance-of v2, v0, Ljava/lang/reflect/Field;

    if-eqz v2, :cond_30

    .line 65
    check-cast v0, Ljava/lang/reflect/Field;

    :goto_21
    move/from16 v29, v3

    goto :goto_22

    .line 66
    :cond_30
    check-cast v0, Ljava/lang/String;

    invoke-static {v11, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    .line 67
    aput-object v0, v10, v16

    goto :goto_21

    .line 68
    :goto_22
    invoke-virtual {v1, v0}, Lsun/misc/Unsafe;->objectFieldOffset(Ljava/lang/reflect/Field;)J

    move-result-wide v2

    long-to-int v3, v2

    rem-int/lit8 v7, v7, 0x20

    move/from16 v16, v3

    move v2, v7

    move/from16 v0, v26

    move/from16 v3, v29

    move v7, v4

    goto :goto_23

    :cond_31
    move-object/from16 v28, v2

    move/from16 v29, v3

    move/from16 v0, v26

    const/4 v2, 0x0

    :goto_23
    add-int/lit8 v4, v20, 0x1

    .line 69
    aput v5, v12, v20

    add-int/lit8 v5, v20, 0x2

    move/from16 v26, v0

    and-int/lit16 v0, v9, 0x200

    if-eqz v0, :cond_32

    const/high16 v0, 0x20000000

    goto :goto_24

    :cond_32
    const/4 v0, 0x0

    :goto_24
    and-int/lit16 v9, v9, 0x100

    if-eqz v9, :cond_33

    const/high16 v9, 0x10000000

    goto :goto_25

    :cond_33
    const/4 v9, 0x0

    :goto_25
    if-eqz v26, :cond_34

    const/high16 v26, -0x80000000

    goto :goto_26

    :cond_34
    const/16 v26, 0x0

    :goto_26
    shl-int/lit8 v6, v6, 0x14

    or-int/2addr v0, v9

    or-int v0, v0, v26

    or-int/2addr v0, v6

    or-int/2addr v0, v3

    .line 70
    aput v0, v12, v4

    add-int/lit8 v20, v20, 0x3

    shl-int/lit8 v0, v2, 0x14

    or-int v0, v0, v16

    .line 71
    aput v0, v12, v5

    move v5, v7

    move-object/from16 v0, v25

    move/from16 v16, v27

    move-object/from16 v2, v28

    move/from16 v3, v30

    move/from16 v4, v31

    const v6, 0xd800

    goto/16 :goto_b

    :cond_35
    move-object/from16 v25, v0

    .line 59
    new-instance v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;

    .line 72
    invoke-virtual/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayf;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    move-result-object v15

    const/16 v16, 0x0

    move-object/from16 v20, p2

    move-object/from16 v21, p3

    move-object/from16 v22, p4

    move-object/from16 v23, p5

    move-object/from16 v24, p6

    move-object v11, v12

    move-object v12, v8

    invoke-direct/range {v10 .. v24}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;-><init>([I[Ljava/lang/Object;IILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;Z[IIILcom/google/android/gms/internal/mlkit_genai_prompt/zzayb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxn;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;)V

    return-object v10

    .line 73
    :cond_36
    invoke-static {v0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 74
    throw v2

    .line 1
    :cond_37
    const-string v0, "Lite gencode is primarily intended for Android use and uses sun.misc.Unsafe which is not available in the current environment. To run in this environment, you may need to switch to standard gencode."

    invoke-static {v0}, Lorg/webrtc/GlShader$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v2
.end method

.method private static zzk(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    .locals 6

    .line 1
    :try_start_0
    invoke-virtual {p0, p1}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/NoSuchFieldException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    .line 2
    invoke-virtual {p0}, Ljava/lang/Class;->getDeclaredFields()[Ljava/lang/reflect/Field;

    move-result-object v1

    .line 3
    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    .line 4
    invoke-virtual {v4}, Ljava/lang/reflect/Field;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_0

    return-object v4

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 5
    :cond_1
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    .line 6
    invoke-static {v1}, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {p1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, 0xb

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v3

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    add-int/2addr v2, v3

    add-int/lit8 v2, v2, 0x1d

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v3

    add-int/2addr v2, v3

    invoke-static {v2, p1, p0, v1, v0}, Lcom/google/android/gms/internal/cast/zzzl$$ExternalSyntheticBUOutline1;->m(ILjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private final zzl(Ljava/lang/Object;Ljava/lang/Object;I)V
    .locals 5

    .line 1
    invoke-direct {p0, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 2
    :cond_0
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v0

    const v1, 0xfffff

    and-int/2addr v0, v1

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    int-to-long v2, v0

    .line 3
    invoke-virtual {v1, p2, v2, v3}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 6
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object p2

    .line 7
    invoke-direct {p0, p1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v4

    if-nez v4, :cond_2

    .line 8
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 9
    invoke-virtual {v1, p1, v2, v3, v0}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_0

    .line 10
    :cond_1
    invoke-interface {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object v4

    .line 11
    invoke-interface {p2, v4, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 12
    invoke-virtual {v1, p1, v2, v3, v4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 13
    :goto_0
    invoke-direct {p0, p1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    return-void

    .line 14
    :cond_2
    invoke-virtual {v1, p1, v2, v3}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 15
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result p3

    if-nez p3, :cond_3

    .line 16
    invoke-interface {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object p3

    .line 17
    invoke-interface {p2, p3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 18
    invoke-virtual {v1, p1, v2, v3, p3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move-object p0, p3

    .line 19
    :cond_3
    invoke-interface {p2, p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    .line 3
    :cond_4
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    .line 4
    aget p0, p0, p3

    .line 5
    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/lit8 p2, p2, 0x26

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p3

    add-int/2addr p2, p3

    invoke-static {p2, p0, p1}, Lcom/google/android/gms/internal/cast/zzzl$$ExternalSyntheticBUOutline0;->m(IILjava/lang/Object;)V

    return-void
.end method

.method private final zzm(Ljava/lang/Object;Ljava/lang/Object;I)V
    .locals 6

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    aget v1, v0, p3

    .line 2
    invoke-direct {p0, p2, v1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v2

    if-nez v2, :cond_0

    return-void

    .line 3
    :cond_0
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v2

    const v3, 0xfffff

    and-int/2addr v2, v3

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    int-to-long v4, v2

    .line 4
    invoke-virtual {v3, p2, v4, v5}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_4

    .line 7
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object p2

    .line 8
    invoke-direct {p0, p1, v1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v0

    if-nez v0, :cond_2

    .line 9
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 10
    invoke-virtual {v3, p1, v4, v5, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_0

    .line 11
    :cond_1
    invoke-interface {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object v0

    .line 12
    invoke-interface {p2, v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 13
    invoke-virtual {v3, p1, v4, v5, v0}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 14
    :goto_0
    invoke-direct {p0, p1, v1, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzG(Ljava/lang/Object;II)V

    return-void

    .line 15
    :cond_2
    invoke-virtual {v3, p1, v4, v5}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 16
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result p3

    if-nez p3, :cond_3

    .line 17
    invoke-interface {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object p3

    .line 18
    invoke-interface {p2, p3, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 19
    invoke-virtual {v3, p1, v4, v5, p3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move-object p0, p3

    .line 20
    :cond_3
    invoke-interface {p2, p0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    .line 5
    :cond_4
    aget p0, v0, p3

    .line 6
    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result p2

    add-int/lit8 p2, p2, 0x26

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p3

    add-int/2addr p2, p3

    invoke-static {p2, p0, p1}, Lcom/google/android/gms/internal/cast/zzzl$$ExternalSyntheticBUOutline0;->m(IILjava/lang/Object;)V

    return-void
.end method

.method private final zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzd:[Ljava/lang/Object;

    div-int/lit8 p1, p1, 0x3

    add-int/2addr p1, p1

    aget-object v0, p0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    if-eqz v0, :cond_0

    return-object v0

    :cond_0
    add-int/lit8 v0, p1, 0x1

    .line 2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;

    move-result-object v1

    aget-object v0, p0, v0

    check-cast v0, Ljava/lang/Class;

    invoke-virtual {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayd;->zzb(Ljava/lang/Class;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v0

    .line 3
    aput-object v0, p0, p1

    return-object v0
.end method

.method private final zzo(I)Ljava/lang/Object;
    .locals 0

    .line 1
    div-int/lit8 p1, p1, 0x3

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzd:[Ljava/lang/Object;

    add-int/2addr p1, p1

    aget-object p0, p0, p1

    return-object p0
.end method

.method private final zzp(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;
    .locals 0

    .line 1
    div-int/lit8 p1, p1, 0x3

    add-int/2addr p1, p1

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzd:[Ljava/lang/Object;

    add-int/lit8 p1, p1, 0x1

    aget-object p0, p0, p1

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    return-object p0
.end method

.method private final zzq(Ljava/lang/Object;I)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v0

    .line 2
    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v1

    const v2, 0xfffff

    and-int/2addr v1, v2

    .line 3
    invoke-direct {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result p0

    if-nez p0, :cond_0

    .line 4
    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_0
    int-to-long v1, v1

    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    .line 5
    invoke-virtual {p0, p1, v1, v2}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 6
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    return-object p0

    .line 7
    :cond_1
    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object p1

    if-eqz p0, :cond_2

    .line 8
    invoke-interface {v0, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_2
    return-object p1
.end method

.method private final zzr(Ljava/lang/Object;ILjava/lang/Object;)V
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    invoke-direct {p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v1

    const v2, 0xfffff

    and-int/2addr v1, v2

    int-to-long v1, v1

    invoke-virtual {v0, p1, v1, v2, p3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 2
    invoke-direct {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    return-void
.end method

.method private final zzs(Ljava/lang/Object;II)Ljava/lang/Object;
    .locals 3

    .line 1
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v0

    .line 2
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result p2

    if-nez p2, :cond_0

    .line 3
    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_0
    sget-object p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    .line 4
    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result p0

    const p3, 0xfffff

    and-int/2addr p0, p3

    int-to-long v1, p0

    invoke-virtual {p2, p1, v1, v2}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 5
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    return-object p0

    .line 6
    :cond_1
    invoke-interface {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zza()Ljava/lang/Object;

    move-result-object p1

    if-eqz p0, :cond_2

    .line 7
    invoke-interface {v0, p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzd(Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_2
    return-object p1
.end method

.method private final zzt(Ljava/lang/Object;IILjava/lang/Object;)V
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    invoke-direct {p0, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v1

    const v2, 0xfffff

    and-int/2addr v1, v2

    int-to-long v1, v1

    invoke-virtual {v0, p1, v1, v2, p4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 2
    invoke-direct {p0, p1, p2, p3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzG(Ljava/lang/Object;II)V

    return-void
.end method

.method private static zzu(Ljava/lang/Object;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;)Z
    .locals 2

    const v0, 0xfffff

    and-int/2addr p1, v0

    int-to-long v0, p1

    .line 1
    invoke-static {p0, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object p0

    .line 2
    invoke-interface {p2, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzi(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method private final zzv(I)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    add-int/lit8 p1, p1, 0x1

    aget p0, p0, p1

    return p0
.end method

.method private final zzw(I)I
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    add-int/lit8 p1, p1, 0x2

    aget p0, p0, p1

    return p0
.end method

.method private static zzx(I)I
    .locals 0

    ushr-int/lit8 p0, p0, 0x14

    and-int/lit16 p0, p0, 0xff

    return p0
.end method

.method private static zzy(Ljava/lang/Object;)Z
    .locals 1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 1
    :cond_0
    instance-of v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    if-eqz v0, :cond_1

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzE()Z

    move-result p0

    return p0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private static zzz(Ljava/lang/Object;)V
    .locals 1

    .line 1
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 2
    :cond_0
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Mutating immutable message: "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method


# virtual methods
.method public final zza()Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzG()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    return-object p0
.end method

.method public final zzb(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 6

    const/4 v0, 0x0

    move v1, v0

    .line 1
    :goto_0
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    array-length v2, v2

    const v3, 0xfffff

    if-ge v1, v2, :cond_3

    invoke-direct {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v2

    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result v4

    const/16 v5, 0x32

    if-le v4, v5, :cond_0

    const/16 v5, 0x45

    if-ge v4, v5, :cond_0

    goto/16 :goto_2

    :cond_0
    and-int/2addr v2, v3

    int-to-long v2, v2

    packed-switch v4, :pswitch_data_0

    goto/16 :goto_2

    .line 2
    :pswitch_0
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzF(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 3
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 4
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 5
    :pswitch_1
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 6
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    goto :goto_1

    .line 7
    :pswitch_2
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 8
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    :goto_1
    if-nez v2, :cond_1

    goto/16 :goto_3

    .line 9
    :pswitch_3
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 10
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 11
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 12
    :pswitch_4
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 13
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto/16 :goto_2

    .line 14
    :pswitch_5
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 15
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 16
    :pswitch_6
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 17
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto/16 :goto_2

    .line 18
    :pswitch_7
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 19
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 20
    :pswitch_8
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 21
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 22
    :pswitch_9
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 23
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 24
    :pswitch_a
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 25
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 26
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 27
    :pswitch_b
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 28
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 29
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 30
    :pswitch_c
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 31
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 32
    invoke-static {v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    goto/16 :goto_2

    .line 33
    :pswitch_d
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 34
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzf(Ljava/lang/Object;J)Z

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzf(Ljava/lang/Object;J)Z

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 35
    :pswitch_e
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 36
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto/16 :goto_2

    .line 37
    :pswitch_f
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 38
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto :goto_2

    .line 39
    :pswitch_10
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 40
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto :goto_2

    .line 41
    :pswitch_11
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 42
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto :goto_2

    .line 43
    :pswitch_12
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 44
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v4

    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    goto :goto_2

    .line 45
    :pswitch_13
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 46
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzh(Ljava/lang/Object;J)F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v4

    .line 47
    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzh(Ljava/lang/Object;J)F

    move-result v2

    invoke-static {v2}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v2

    if-ne v4, v2, :cond_2

    goto :goto_2

    .line 48
    :pswitch_14
    invoke-direct {p0, p1, p2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzA(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 49
    invoke-static {p1, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzj(Ljava/lang/Object;J)D

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/Double;->doubleToLongBits(D)J

    move-result-wide v4

    .line 50
    invoke-static {p2, v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzj(Ljava/lang/Object;J)D

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Double;->doubleToLongBits(D)J

    move-result-wide v2

    cmp-long v2, v4, v2

    if-nez v2, :cond_2

    :cond_1
    :goto_2
    add-int/lit8 v1, v1, 0x3

    goto/16 :goto_0

    :cond_2
    :goto_3
    return v0

    .line 47
    :cond_3
    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk:I

    :goto_4
    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzi:[I

    array-length v4, v2

    if-ge v1, v4, :cond_7

    .line 51
    aget v2, v2, v1

    .line 52
    invoke-direct {p0, p1, p2, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzF(Ljava/lang/Object;Ljava/lang/Object;I)Z

    move-result v4

    if-nez v4, :cond_4

    return v0

    .line 53
    :cond_4
    invoke-direct {p0, p1, v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v4

    if-eqz v4, :cond_5

    goto :goto_5

    .line 54
    :cond_5
    invoke-direct {p0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v2

    and-int/2addr v2, v3

    int-to-long v4, v2

    .line 55
    invoke-static {p1, v4, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    invoke-static {p2, v4, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v4

    .line 56
    invoke-static {v2, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_6

    return v0

    :cond_6
    :goto_5
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    .line 57
    :cond_7
    move-object v1, p1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object v1, v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 58
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object p2, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 59
    invoke-virtual {v1, p2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_8

    return v0

    :cond_8
    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    if-nez p0, :cond_9

    const/4 p0, 0x1

    return p0

    .line 60
    :cond_9
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method

.method public final zzc(Ljava/lang/Object;)I
    .locals 8

    const/4 v0, 0x0

    move v1, v0

    move v2, v1

    .line 1
    :goto_0
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    array-length v3, v3

    const v4, 0xfffff

    if-ge v1, v3, :cond_3

    invoke-direct {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v3

    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result v5

    const/16 v6, 0x32

    if-le v5, v6, :cond_0

    const/16 v6, 0x45

    if-lt v5, v6, :cond_2

    :cond_0
    and-int/2addr v3, v4

    int-to-long v3, v3

    const/16 v6, 0x25

    const/16 v7, 0x20

    packed-switch v5, :pswitch_data_0

    goto/16 :goto_4

    :pswitch_0
    mul-int/lit8 v2, v2, 0x35

    .line 2
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    :goto_1
    add-int/2addr v2, v3

    goto/16 :goto_4

    :pswitch_1
    mul-int/lit8 v2, v2, 0x35

    .line 3
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    goto :goto_1

    :pswitch_2
    mul-int/lit8 v2, v2, 0x35

    .line 4
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    if-eqz v3, :cond_1

    .line 5
    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v6

    :cond_1
    :goto_2
    add-int/2addr v2, v6

    goto/16 :goto_4

    :pswitch_3
    mul-int/lit8 v2, v2, 0x35

    .line 6
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    :goto_3
    ushr-long v5, v3, v7

    xor-long/2addr v3, v5

    long-to-int v3, v3

    goto :goto_1

    :pswitch_4
    mul-int/lit8 v2, v2, 0x35

    .line 7
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_5
    mul-int/lit8 v2, v2, 0x35

    .line 8
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    goto :goto_3

    :pswitch_6
    mul-int/lit8 v2, v2, 0x35

    .line 9
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_7
    mul-int/lit8 v2, v2, 0x35

    .line 10
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_8
    mul-int/lit8 v2, v2, 0x35

    .line 11
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto :goto_1

    :pswitch_9
    mul-int/lit8 v2, v2, 0x35

    .line 12
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    goto :goto_1

    :pswitch_a
    mul-int/lit8 v2, v2, 0x35

    .line 13
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    if-eqz v3, :cond_1

    .line 14
    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v6

    goto :goto_2

    :pswitch_b
    mul-int/lit8 v2, v2, 0x35

    .line 15
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-virtual {v3}, Ljava/lang/String;->hashCode()I

    move-result v3

    goto :goto_1

    :pswitch_c
    mul-int/lit8 v2, v2, 0x35

    .line 16
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzf(Ljava/lang/Object;J)Z

    move-result v3

    invoke-static {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza(Z)I

    move-result v3

    goto :goto_1

    :pswitch_d
    mul-int/lit8 v2, v2, 0x35

    .line 17
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto/16 :goto_1

    :pswitch_e
    mul-int/lit8 v2, v2, 0x35

    .line 18
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    goto :goto_3

    :pswitch_f
    mul-int/lit8 v2, v2, 0x35

    .line 19
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v3

    goto/16 :goto_1

    :pswitch_10
    mul-int/lit8 v2, v2, 0x35

    .line 20
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    goto :goto_3

    :pswitch_11
    mul-int/lit8 v2, v2, 0x35

    .line 21
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v3

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    goto/16 :goto_3

    :pswitch_12
    mul-int/lit8 v2, v2, 0x35

    .line 22
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzh(Ljava/lang/Object;J)F

    move-result v3

    invoke-static {v3}, Ljava/lang/Float;->floatToIntBits(F)I

    move-result v3

    goto/16 :goto_1

    :pswitch_13
    mul-int/lit8 v2, v2, 0x35

    .line 23
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzj(Ljava/lang/Object;J)D

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Double;->doubleToLongBits(D)J

    move-result-wide v3

    .line 24
    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxi;->zza:[B

    goto/16 :goto_3

    :cond_2
    :goto_4
    add-int/lit8 v1, v1, 0x3

    goto/16 :goto_0

    .line 22
    :cond_3
    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk:I

    :goto_5
    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzi:[I

    array-length v5, v3

    if-ge v1, v5, :cond_5

    .line 25
    aget v3, v3, v1

    .line 26
    invoke-direct {p0, p1, v0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v5

    if-nez v5, :cond_4

    mul-int/lit8 v2, v2, 0x35

    .line 27
    invoke-direct {p0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v3

    and-int/2addr v3, v4

    int-to-long v5, v3

    invoke-static {p1, v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v3

    add-int/2addr v2, v3

    :cond_4
    add-int/lit8 v1, v1, 0x1

    goto :goto_5

    :cond_5
    mul-int/lit8 v2, v2, 0x35

    .line 28
    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 29
    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    add-int/2addr v2, v0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    if-nez p0, :cond_6

    return v2

    .line 30
    :cond_6
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzd(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 8

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzz(Ljava/lang/Object;)V

    .line 2
    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    array-length v2, v1

    if-ge v0, v2, :cond_4

    .line 3
    invoke-direct {p0, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v2

    const v3, 0xfffff

    and-int/2addr v3, v2

    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result v2

    .line 4
    aget v1, v1, v0

    int-to-long v3, v3

    packed-switch v2, :pswitch_data_0

    goto/16 :goto_2

    .line 13
    :pswitch_0
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzm(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 14
    :pswitch_1
    invoke-direct {p0, p2, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 15
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    invoke-static {p1, v3, v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 16
    invoke-direct {p0, p1, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzG(Ljava/lang/Object;II)V

    goto/16 :goto_2

    .line 17
    :pswitch_2
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzm(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 18
    :pswitch_3
    invoke-direct {p0, p2, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 19
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    invoke-static {p1, v3, v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 20
    invoke-direct {p0, p1, v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzG(Ljava/lang/Object;II)V

    goto/16 :goto_2

    .line 21
    :pswitch_4
    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->$r8$clinit:I

    .line 22
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 23
    invoke-static {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;->zza(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    .line 24
    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    goto/16 :goto_2

    .line 5
    :pswitch_5
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    .line 6
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    .line 7
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v5

    .line 8
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v6

    if-lez v5, :cond_1

    if-lez v6, :cond_1

    .line 9
    invoke-interface {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zza()Z

    move-result v7

    if-nez v7, :cond_0

    add-int/2addr v6, v5

    .line 10
    invoke-interface {v1, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    move-result-object v1

    .line 11
    :cond_0
    invoke-interface {v1, v2}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    :cond_1
    if-gtz v5, :cond_2

    goto :goto_1

    :cond_2
    move-object v2, v1

    .line 12
    :goto_1
    invoke-static {p1, v3, v4, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    goto/16 :goto_2

    .line 25
    :pswitch_6
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzl(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 26
    :pswitch_7
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 27
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zze(Ljava/lang/Object;JJ)V

    .line 28
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 29
    :pswitch_8
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 30
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    .line 31
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 32
    :pswitch_9
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 33
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zze(Ljava/lang/Object;JJ)V

    .line 34
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 35
    :pswitch_a
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 36
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    .line 37
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 38
    :pswitch_b
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 39
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    .line 40
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 41
    :pswitch_c
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 42
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    .line 43
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 44
    :pswitch_d
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 45
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 46
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 47
    :pswitch_e
    invoke-direct {p0, p1, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzl(Ljava/lang/Object;Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 48
    :pswitch_f
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 49
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzm(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 50
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 51
    :pswitch_10
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 52
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzf(Ljava/lang/Object;J)Z

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzg(Ljava/lang/Object;JZ)V

    .line 53
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto/16 :goto_2

    .line 54
    :pswitch_11
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 55
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    .line 56
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto :goto_2

    .line 57
    :pswitch_12
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 58
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zze(Ljava/lang/Object;JJ)V

    .line 59
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto :goto_2

    .line 60
    :pswitch_13
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 61
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzb(Ljava/lang/Object;J)I

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzc(Ljava/lang/Object;JI)V

    .line 62
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto :goto_2

    .line 63
    :pswitch_14
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 64
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zze(Ljava/lang/Object;JJ)V

    .line 65
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto :goto_2

    .line 66
    :pswitch_15
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 67
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzd(Ljava/lang/Object;J)J

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zze(Ljava/lang/Object;JJ)V

    .line 68
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto :goto_2

    .line 69
    :pswitch_16
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 70
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzh(Ljava/lang/Object;J)F

    move-result v1

    invoke-static {p1, v3, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzi(Ljava/lang/Object;JF)V

    .line 71
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    goto :goto_2

    .line 72
    :pswitch_17
    invoke-direct {p0, p2, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v1

    if-eqz v1, :cond_3

    .line 73
    invoke-static {p2, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzj(Ljava/lang/Object;J)D

    move-result-wide v1

    invoke-static {p1, v3, v4, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzk(Ljava/lang/Object;JD)V

    .line 74
    invoke-direct {p0, p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzD(Ljava/lang/Object;I)V

    :cond_3
    :goto_2
    add-int/lit8 v0, v0, 0x3

    goto/16 :goto_0

    :cond_4
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    .line 75
    invoke-static {v0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-boolean v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    if-eqz v0, :cond_5

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzm:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;

    .line 76
    invoke-static {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zzc(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawm;Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_5
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzf(Ljava/lang/Object;[BIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I
    .locals 35

    move-object/from16 v0, p0

    move-object/from16 v2, p1

    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    .line 1
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzz(Ljava/lang/Object;)V

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    const/4 v11, 0x0

    move/from16 v5, p3

    move v8, v11

    move v14, v8

    move v15, v14

    const/4 v7, -0x1

    const v9, 0xfffff

    :goto_0
    const/16 v16, 0x0

    if-ge v5, v4, :cond_86

    add-int/lit8 v15, v5, 0x1

    .line 2
    aget-byte v5, v3, v5

    if-gez v5, :cond_0

    .line 3
    invoke-static {v5, v3, v15, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzb(I[BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v15

    iget v5, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    :cond_0
    move v6, v15

    move v15, v5

    ushr-int/lit8 v5, v15, 0x3

    const v17, 0xfffff

    .line 268
    iget v13, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zze:I

    const/4 v12, 0x3

    if-le v5, v7, :cond_2

    .line 3
    div-int/2addr v8, v12

    if-lt v5, v13, :cond_1

    iget v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzf:I

    if-gt v5, v7, :cond_1

    .line 5
    invoke-direct {v0, v5, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzH(II)I

    move-result v7

    goto :goto_1

    :cond_1
    const/4 v7, -0x1

    :goto_1
    move v13, v7

    const/4 v7, -0x1

    goto :goto_2

    :cond_2
    if-lt v5, v13, :cond_3

    .line 268
    iget v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzf:I

    if-gt v5, v7, :cond_3

    .line 4
    invoke-direct {v0, v5, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzH(II)I

    move-result v7

    goto :goto_1

    :cond_3
    const/4 v7, -0x1

    const/4 v13, -0x1

    :goto_2
    if-ne v13, v7, :cond_4

    move-object v10, v0

    move-object v0, v1

    move/from16 v18, v7

    move/from16 v21, v9

    move v8, v11

    move/from16 v27, v14

    move v14, v15

    move/from16 v11, p5

    move-object/from16 v7, p6

    move-object v15, v2

    move v9, v5

    move v5, v6

    goto/16 :goto_43

    :cond_4
    and-int/lit8 v8, v15, 0x7

    .line 279
    iget-object v7, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    add-int/lit8 v19, v13, 0x1

    move/from16 v20, v11

    .line 6
    aget v11, v7, v19

    invoke-static {v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result v12

    and-int v3, v11, v17

    int-to-long v3, v3

    move-wide/from16 v21, v3

    const/16 v3, 0x11

    const-wide/16 v23, 0x0

    const/high16 v19, 0x20000000

    const-string v25, "Protocol message had invalid UTF-8."

    const-string v4, ""

    const-string v26, "CodedInputStream encountered an embedded string or message which claimed to have negative size."

    move/from16 v27, v5

    const/16 v28, 0x1

    if-gt v12, v3, :cond_23

    add-int/lit8 v3, v13, 0x2

    .line 7
    aget v3, v7, v3

    ushr-int/lit8 v7, v3, 0x14

    shl-int v7, v28, v7

    and-int v3, v3, v17

    if-eq v3, v9, :cond_7

    move/from16 v5, v17

    move/from16 v30, v6

    if-eq v9, v5, :cond_5

    int-to-long v5, v9

    .line 8
    invoke-virtual {v1, v2, v5, v6, v14}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    const v5, 0xfffff

    :cond_5
    if-ne v3, v5, :cond_6

    move/from16 v5, v20

    goto :goto_3

    :cond_6
    int-to-long v5, v3

    .line 9
    invoke-virtual {v1, v2, v5, v6}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v5

    :goto_3
    move v14, v3

    goto :goto_4

    :cond_7
    move/from16 v30, v6

    move v5, v14

    move v14, v9

    :goto_4
    packed-switch v12, :pswitch_data_0

    const/4 v3, 0x3

    if-ne v8, v3, :cond_8

    or-int v11, v5, v7

    .line 10
    invoke-direct {v0, v2, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzq(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object v3

    shl-int/lit8 v4, v27, 0x3

    or-int/lit8 v8, v4, 0x4

    .line 11
    invoke-direct {v0, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v4

    move-object/from16 v5, p2

    move/from16 v7, p4

    move-object/from16 v9, p6

    move/from16 v6, v30

    const/16 v18, -0x1

    .line 12
    invoke-static/range {v3 .. v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzj(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;[BIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v4

    move-object v12, v9

    move-object v9, v5

    .line 13
    invoke-direct {v0, v2, v13, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzr(Ljava/lang/Object;ILjava/lang/Object;)V

    move v5, v4

    move-object v3, v9

    move-object v6, v12

    move v8, v13

    move v9, v14

    move/from16 v7, v27

    move/from16 v4, p4

    move v14, v11

    move/from16 v11, v20

    goto/16 :goto_0

    :cond_8
    const/16 v18, -0x1

    move-object/from16 v9, p2

    move-object/from16 v7, p6

    move-object v3, v1

    move-object v1, v2

    move/from16 p3, v5

    move/from16 v22, v14

    move/from16 v2, v30

    goto/16 :goto_1b

    :pswitch_0
    move-object/from16 v9, p2

    move-object/from16 v12, p6

    move/from16 v4, v30

    const/16 v18, -0x1

    if-nez v8, :cond_9

    or-int/2addr v7, v5

    .line 14
    invoke-static {v9, v4, v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget-wide v3, v12, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 15
    invoke-static {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzc(J)J

    move-result-wide v5

    move-wide/from16 v3, v21

    .line 16
    invoke-virtual/range {v1 .. v6}, Lsun/misc/Unsafe;->putLong(Ljava/lang/Object;JJ)V

    move-object/from16 v34, v2

    move-object v2, v1

    move-object/from16 v1, v34

    move-object v3, v2

    move-object v2, v1

    move-object v1, v3

    move/from16 v4, p4

    move v5, v8

    :goto_5
    move-object v3, v9

    move-object v6, v12

    :goto_6
    move v8, v13

    move v9, v14

    move/from16 v11, v20

    move v14, v7

    move/from16 v7, v27

    goto/16 :goto_0

    :cond_9
    move-object/from16 v34, v2

    move-object v2, v1

    move-object/from16 v1, v34

    move-object v3, v2

    move v2, v4

    move/from16 p3, v5

    :goto_7
    move-object v7, v12

    :goto_8
    move/from16 v22, v14

    goto/16 :goto_1b

    :pswitch_1
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p2

    move-object/from16 v12, p6

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v4, v30

    const/16 v18, -0x1

    if-nez v8, :cond_a

    or-int v3, p3, v7

    .line 17
    invoke-static {v9, v4, v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v4

    iget v7, v12, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 18
    invoke-static {v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzb(I)I

    move-result v7

    .line 19
    invoke-virtual {v2, v1, v5, v6, v7}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :goto_9
    move-object v5, v2

    move-object v2, v1

    move-object v1, v5

    move v5, v14

    move v14, v3

    move-object v3, v9

    move v9, v5

    move v5, v4

    move-object v6, v12

    move v8, v13

    move/from16 v11, v20

    move/from16 v7, v27

    :goto_a
    move/from16 v4, p4

    goto/16 :goto_0

    :cond_a
    move-object v3, v2

    move v2, v4

    goto :goto_7

    :pswitch_2
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p2

    move-object/from16 v12, p6

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v4, v30

    const/16 v18, -0x1

    if-nez v8, :cond_a

    .line 20
    invoke-static {v9, v4, v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v3

    iget v4, v12, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 21
    invoke-direct {v0, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzp(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v8

    const/high16 v16, -0x80000000

    and-int v11, v11, v16

    if-eqz v11, :cond_c

    if-eqz v8, :cond_c

    invoke-interface {v8, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;->zza(I)Z

    move-result v8

    if-eqz v8, :cond_b

    goto :goto_b

    .line 23
    :cond_b
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zze(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v5

    int-to-long v6, v4

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v5, v15, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzg(ILjava/lang/Object;)V

    move-object v4, v2

    move-object v2, v1

    move-object v1, v4

    move/from16 v4, p4

    move v5, v3

    move-object v3, v9

    move-object v6, v12

    move v8, v13

    move v9, v14

    move/from16 v11, v20

    move/from16 v7, v27

    move/from16 v14, p3

    goto/16 :goto_0

    :cond_c
    :goto_b
    or-int v7, p3, v7

    .line 22
    invoke-virtual {v2, v1, v5, v6, v4}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move-object v4, v2

    move-object v2, v1

    move-object v1, v4

    move/from16 v4, p4

    move v5, v3

    goto/16 :goto_5

    :pswitch_3
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p2

    move-object/from16 v12, p6

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v4, v30

    const/4 v3, 0x2

    const/16 v18, -0x1

    if-ne v8, v3, :cond_a

    or-int v3, p3, v7

    .line 24
    invoke-static {v9, v4, v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzf([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v4

    iget-object v7, v12, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    .line 25
    invoke-virtual {v2, v1, v5, v6, v7}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto/16 :goto_9

    :pswitch_4
    move-object/from16 p3, v2

    move-object v2, v1

    move-object/from16 v1, p3

    move-object/from16 v9, p2

    move-object/from16 v12, p6

    move/from16 p3, v5

    move/from16 v4, v30

    const/4 v3, 0x2

    const/16 v18, -0x1

    if-ne v8, v3, :cond_d

    or-int v7, p3, v7

    move-object v3, v1

    .line 26
    invoke-direct {v0, v3, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzq(Ljava/lang/Object;I)Ljava/lang/Object;

    move-result-object v1

    move-object v5, v2

    .line 27
    invoke-direct {v0, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    move-object v6, v9

    move-object v9, v3

    move-object v3, v6

    move-object v6, v12

    move-object v12, v5

    move/from16 v5, p4

    .line 28
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzi(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    move-object/from16 v34, v3

    move-object v3, v1

    move-object/from16 v1, v34

    .line 29
    invoke-direct {v0, v9, v13, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzr(Ljava/lang/Object;ILjava/lang/Object;)V

    move/from16 v4, p4

    move-object/from16 v6, p6

    move-object v3, v1

    move v5, v2

    move-object v2, v9

    move-object v1, v12

    goto/16 :goto_6

    :cond_d
    move-object v12, v9

    move-object v9, v1

    move-object v1, v12

    move-object v12, v2

    move v2, v4

    move-object v3, v9

    move-object v9, v1

    move-object v1, v3

    move-object/from16 v7, p6

    move-object v3, v12

    goto/16 :goto_8

    :pswitch_5
    move-object v12, v1

    move-object v9, v2

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v2, v30

    const/4 v3, 0x2

    const/16 v18, -0x1

    move-object/from16 v1, p2

    move/from16 v21, v7

    move-object/from16 v7, p6

    if-ne v8, v3, :cond_1d

    and-int v3, v11, v19

    if-eqz v3, :cond_1a

    .line 32
    invoke-static {v1, v2, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    iget v3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v3, :cond_19

    or-int v8, p3, v21

    if-nez v3, :cond_e

    .line 283
    iput-object v4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    move/from16 p3, v8

    move/from16 v22, v14

    move/from16 v8, v20

    goto/16 :goto_11

    .line 33
    :cond_e
    sget v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayz;->$r8$clinit:I

    .line 34
    array-length v4, v1

    sub-int v11, v4, v2

    or-int v16, v2, v3

    sub-int/2addr v11, v3

    or-int v11, v16, v11

    if-ltz v11, :cond_18

    add-int v4, v2, v3

    .line 284
    new-array v3, v3, [C

    move/from16 v11, v20

    :goto_c
    move/from16 v16, v2

    if-ge v2, v4, :cond_f

    .line 35
    aget-byte v2, v1, v16

    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayy;->zza(B)Z

    move-result v19

    if-eqz v19, :cond_f

    add-int/lit8 v16, v16, 0x1

    add-int/lit8 v19, v11, 0x1

    int-to-char v2, v2

    .line 36
    aput-char v2, v3, v11

    move/from16 v2, v16

    move/from16 v11, v19

    goto :goto_c

    :cond_f
    move/from16 v2, v16

    :goto_d
    if-ge v2, v4, :cond_17

    move/from16 v16, v2

    add-int/lit8 v2, v16, 0x1

    move/from16 p3, v8

    .line 37
    aget-byte v8, v1, v16

    invoke-static {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayy;->zza(B)Z

    move-result v19

    if-eqz v19, :cond_11

    add-int/lit8 v16, v11, 0x1

    int-to-char v8, v8

    .line 41
    aput-char v8, v3, v11

    :goto_e
    move/from16 v11, v16

    if-ge v2, v4, :cond_10

    .line 42
    aget-byte v8, v1, v2

    invoke-static {v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayy;->zza(B)Z

    move-result v16

    if-eqz v16, :cond_10

    add-int/lit8 v2, v2, 0x1

    add-int/lit8 v16, v11, 0x1

    int-to-char v8, v8

    .line 43
    aput-char v8, v3, v11

    goto :goto_e

    :cond_10
    move/from16 v8, p3

    goto :goto_d

    :cond_11
    move/from16 v22, v14

    const/16 v14, -0x20

    if-ge v8, v14, :cond_13

    if-ge v2, v4, :cond_12

    add-int/lit8 v14, v11, 0x1

    add-int/lit8 v16, v16, 0x2

    .line 40
    aget-byte v2, v1, v2

    invoke-static {v8, v2, v3, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayy;->zzb(BB[CI)V

    move/from16 v8, p3

    move v11, v14

    move/from16 v2, v16

    :goto_f
    move/from16 v14, v22

    goto :goto_d

    .line 290
    :cond_12
    invoke-static/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_13
    const/16 v14, -0x10

    if-ge v8, v14, :cond_15

    add-int/lit8 v14, v4, -0x1

    if-ge v2, v14, :cond_14

    add-int/lit8 v14, v11, 0x1

    add-int/lit8 v19, v16, 0x2

    .line 39
    aget-byte v2, v1, v2

    add-int/lit8 v16, v16, 0x3

    move/from16 v21, v4

    aget-byte v4, v1, v19

    invoke-static {v8, v2, v4, v3, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayy;->zzc(BBB[CI)V

    move/from16 v8, p3

    move v11, v14

    move/from16 v2, v16

    :goto_10
    move/from16 v4, v21

    goto :goto_f

    .line 288
    :cond_14
    invoke-static/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_15
    move/from16 v21, v4

    add-int/lit8 v4, v21, -0x2

    if-ge v2, v4, :cond_16

    add-int/lit8 v4, v16, 0x2

    .line 38
    aget-byte v29, v1, v2

    add-int/lit8 v2, v16, 0x3

    aget-byte v30, v1, v4

    add-int/lit8 v4, v16, 0x4

    aget-byte v31, v1, v2

    move-object/from16 v32, v3

    move/from16 v28, v8

    move/from16 v33, v11

    invoke-static/range {v28 .. v33}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayy;->zzd(BBBB[CI)V

    move-object/from16 v2, v32

    add-int/lit8 v11, v11, 0x2

    move/from16 v8, p3

    move-object v3, v2

    move v2, v4

    goto :goto_10

    .line 286
    :cond_16
    invoke-static/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_17
    move-object v2, v3

    move/from16 v21, v4

    move/from16 p3, v8

    move/from16 v22, v14

    .line 38
    new-instance v3, Ljava/lang/String;

    move/from16 v8, v20

    invoke-direct {v3, v2, v8, v11}, Ljava/lang/String;-><init>([CII)V

    iput-object v3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    move/from16 v2, v21

    :goto_11
    move/from16 v14, p3

    goto :goto_13

    :cond_18
    move/from16 v8, v20

    .line 284
    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    filled-new-array {v0, v1, v2}, [Ljava/lang/Object;

    move-result-object v0

    const-string v1, "buffer length=%d, index=%d, size=%d"

    invoke-static {v1, v0}, Lcom/google/protobuf/Utf8$Processor$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;[Ljava/lang/Object;)V

    return v8

    :cond_19
    move/from16 v8, v20

    .line 283
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v8

    :cond_1a
    move/from16 v22, v14

    .line 30
    invoke-static {v1, v2, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    iget v3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v3, :cond_1c

    or-int v8, p3, v21

    if-nez v3, :cond_1b

    .line 281
    iput-object v4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    :goto_12
    move v14, v8

    goto :goto_13

    :cond_1b
    new-instance v4, Ljava/lang/String;

    .line 31
    sget-object v11, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v4, v1, v2, v3, v11}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    iput-object v4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    add-int/2addr v2, v3

    goto :goto_12

    .line 283
    :goto_13
    iget-object v3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    .line 44
    invoke-virtual {v12, v9, v5, v6, v3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    :goto_14
    move/from16 v4, p4

    move-object v3, v1

    move v5, v2

    move-object v6, v7

    move-object v2, v9

    :goto_15
    move-object v1, v12

    :goto_16
    move v8, v13

    move/from16 v9, v22

    move/from16 v7, v27

    const/4 v11, 0x0

    goto/16 :goto_0

    .line 281
    :cond_1c
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    :cond_1d
    move/from16 v22, v14

    :cond_1e
    move-object v3, v9

    move-object v9, v1

    move-object v1, v3

    :cond_1f
    :goto_17
    move-object v3, v12

    goto/16 :goto_1b

    :pswitch_6
    move-object v12, v1

    move-object v9, v2

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v2, v30

    const/16 v18, -0x1

    move-object/from16 v1, p2

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-nez v8, :cond_1e

    or-int v14, p3, v21

    .line 45
    invoke-static {v1, v2, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    iget-wide v3, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    cmp-long v3, v3, v23

    if-eqz v3, :cond_20

    move/from16 v3, v28

    goto :goto_18

    :cond_20
    const/4 v3, 0x0

    .line 46
    :goto_18
    invoke-static {v9, v5, v6, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzg(Ljava/lang/Object;JZ)V

    goto :goto_14

    :pswitch_7
    move-object v12, v1

    move-object v9, v2

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v2, v30

    const/4 v3, 0x5

    const/16 v18, -0x1

    move-object/from16 v1, p2

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-ne v8, v3, :cond_1e

    add-int/lit8 v3, v2, 0x4

    or-int v14, p3, v21

    .line 47
    invoke-static {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzd([BI)I

    move-result v2

    invoke-virtual {v12, v9, v5, v6, v2}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move/from16 v4, p4

    move v5, v3

    move-object v6, v7

    move-object v2, v9

    move v8, v13

    move/from16 v9, v22

    move/from16 v7, v27

    const/4 v11, 0x0

    move-object v3, v1

    move-object v1, v12

    goto/16 :goto_0

    :pswitch_8
    move-object v12, v1

    move-object v9, v2

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v3, v28

    move/from16 v2, v30

    const/16 v18, -0x1

    move-object/from16 v1, p2

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-ne v8, v3, :cond_21

    add-int/lit8 v8, v2, 0x8

    or-int v14, p3, v21

    move-wide v3, v5

    .line 48
    invoke-static {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zze([BI)J

    move-result-wide v5

    move-object v2, v9

    move-object v9, v1

    move-object v1, v12

    invoke-virtual/range {v1 .. v6}, Lsun/misc/Unsafe;->putLong(Ljava/lang/Object;JJ)V

    :goto_19
    move/from16 v4, p4

    move-object v6, v7

    move v5, v8

    move-object v3, v9

    goto/16 :goto_16

    :cond_21
    move-object/from16 v34, v9

    move-object v9, v1

    move-object/from16 v1, v34

    goto/16 :goto_17

    :pswitch_9
    move-object/from16 v9, p2

    move-object v12, v1

    move-object v1, v2

    move/from16 p3, v5

    move-wide/from16 v3, v21

    move/from16 v2, v30

    const/16 v18, -0x1

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-nez v8, :cond_1f

    or-int v14, p3, v21

    .line 49
    invoke-static {v9, v2, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v5

    iget v2, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 50
    invoke-virtual {v12, v1, v3, v4, v2}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move/from16 v4, p4

    move-object v2, v1

    move-object v6, v7

    move-object v3, v9

    goto/16 :goto_15

    :pswitch_a
    move-object/from16 v9, p2

    move-object v12, v1

    move-object v1, v2

    move/from16 p3, v5

    move-wide/from16 v3, v21

    move/from16 v2, v30

    const/16 v18, -0x1

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-nez v8, :cond_1f

    or-int v14, p3, v21

    .line 51
    invoke-static {v9, v2, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget-wide v5, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    move-object v2, v1

    move-object v1, v12

    .line 52
    invoke-virtual/range {v1 .. v6}, Lsun/misc/Unsafe;->putLong(Ljava/lang/Object;JJ)V

    goto :goto_19

    :pswitch_b
    move-object/from16 v9, p2

    move-object v3, v1

    move-object v1, v2

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v2, v30

    const/4 v4, 0x5

    const/16 v18, -0x1

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-ne v8, v4, :cond_22

    add-int/lit8 v4, v2, 0x4

    or-int v14, p3, v21

    .line 53
    invoke-static {v9, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzd([BI)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Float;->intBitsToFloat(I)F

    move-result v2

    .line 54
    invoke-static {v1, v5, v6, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzi(Ljava/lang/Object;JF)V

    :goto_1a
    move-object v2, v1

    move-object v1, v3

    move v5, v4

    move-object v6, v7

    move-object v3, v9

    move v8, v13

    move/from16 v9, v22

    move/from16 v7, v27

    const/4 v11, 0x0

    goto/16 :goto_a

    :pswitch_c
    move-object/from16 v9, p2

    move-object v3, v1

    move-object v1, v2

    move/from16 p3, v5

    move-wide/from16 v5, v21

    move/from16 v4, v28

    move/from16 v2, v30

    const/16 v18, -0x1

    move/from16 v21, v7

    move/from16 v22, v14

    move-object/from16 v7, p6

    if-ne v8, v4, :cond_22

    add-int/lit8 v4, v2, 0x8

    or-int v14, p3, v21

    .line 55
    invoke-static {v9, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zze([BI)J

    move-result-wide v11

    invoke-static {v11, v12}, Ljava/lang/Double;->longBitsToDouble(J)D

    move-result-wide v11

    .line 56
    invoke-static {v1, v5, v6, v11, v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzk(Ljava/lang/Object;JD)V

    goto :goto_1a

    :cond_22
    :goto_1b
    move/from16 v11, p5

    move-object v10, v0

    move v5, v2

    move-object v0, v3

    move-object v3, v9

    move v8, v13

    move v14, v15

    move/from16 v21, v22

    move/from16 v9, v27

    move/from16 v27, p3

    move-object v15, v1

    goto/16 :goto_43

    :cond_23
    move-object v3, v1

    move-object v1, v2

    move/from16 v30, v6

    move-wide/from16 v5, v21

    const/16 v18, -0x1

    move-object/from16 v22, v7

    move/from16 v21, v9

    move/from16 v9, v27

    move-object/from16 v7, p6

    const/16 v2, 0x1b

    if-ne v12, v2, :cond_27

    const/4 v2, 0x2

    if-ne v8, v2, :cond_26

    .line 57
    invoke-virtual {v3, v1, v5, v6}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    .line 58
    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zza()Z

    move-result v4

    if-nez v4, :cond_25

    .line 59
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v4

    if-nez v4, :cond_24

    const/16 v4, 0xa

    goto :goto_1c

    :cond_24
    add-int/2addr v4, v4

    .line 60
    :goto_1c
    invoke-interface {v2, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    move-result-object v2

    .line 61
    invoke-virtual {v3, v1, v5, v6, v2}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    :cond_25
    move-object v6, v2

    .line 62
    invoke-direct {v0, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v1

    move/from16 v5, p4

    move-object v12, v3

    move v2, v15

    move/from16 v4, v30

    move-object/from16 v15, p1

    move-object/from16 v3, p2

    .line 63
    invoke-static/range {v1 .. v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;I[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v1

    move v7, v2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move v5, v1

    move-object v1, v12

    move v8, v13

    move-object v2, v15

    const/4 v11, 0x0

    move v15, v7

    move v7, v9

    move/from16 v9, v21

    goto/16 :goto_0

    :cond_26
    move-object v12, v3

    move v7, v15

    move-object v15, v1

    move-object/from16 v3, p2

    move-object v10, v0

    move-object v0, v12

    move/from16 v27, v14

    move/from16 v1, v30

    move v14, v7

    move-object/from16 v7, p6

    goto/16 :goto_37

    :cond_27
    move v7, v15

    move-object v15, v1

    move-object v1, v3

    move/from16 v3, v30

    const/16 v2, 0x31

    if-gt v12, v2, :cond_75

    move/from16 v30, v3

    int-to-long v2, v11

    .line 64
    invoke-virtual {v1, v15, v5, v6}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    .line 65
    invoke-interface {v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zza()Z

    move-result v19

    if-nez v19, :cond_28

    .line 66
    invoke-interface {v11}, Ljava/util/List;->size()I

    move-result v19

    move-wide/from16 v31, v2

    add-int v2, v19, v19

    .line 67
    invoke-interface {v11, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    move-result-object v11

    .line 68
    invoke-virtual {v1, v15, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_1d

    :cond_28
    move-wide/from16 v31, v2

    :goto_1d
    const-string v2, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    packed-switch v12, :pswitch_data_1

    const/4 v3, 0x3

    if-ne v8, v3, :cond_2b

    and-int/lit8 v2, v7, -0x8

    or-int/lit8 v5, v2, 0x4

    move-object v12, v1

    .line 69
    invoke-direct {v0, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v1

    move-object/from16 v2, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move/from16 v3, v30

    .line 70
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;[BIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    move-object/from16 p3, v1

    iget-object v1, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    .line 71
    invoke-interface {v11, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_1e
    if-ge v8, v4, :cond_2a

    move/from16 v30, v3

    .line 72
    invoke-static {v2, v8, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v3

    iget v1, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ne v7, v1, :cond_29

    move-object/from16 v1, p3

    move/from16 v27, v7

    move/from16 v7, v30

    .line 73
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzh(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;[BIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    move-object v3, v2

    iget-object v2, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    .line 74
    invoke-interface {v11, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move/from16 v4, p4

    move-object v2, v3

    move v3, v7

    move/from16 v7, v27

    goto :goto_1e

    :cond_29
    move/from16 v27, v7

    move/from16 v7, v30

    :goto_1f
    move-object v3, v2

    goto :goto_20

    :cond_2a
    move/from16 v27, v7

    move v7, v3

    goto :goto_1f

    :goto_20
    move/from16 v1, v27

    move/from16 v27, v14

    move v14, v1

    move-object v10, v0

    move v1, v7

    move v5, v8

    :goto_21
    move-object/from16 v19, v12

    :goto_22
    move-object v7, v6

    goto/16 :goto_35

    :cond_2b
    move/from16 v27, v7

    move/from16 v3, v27

    move/from16 v27, v14

    move v14, v3

    move-object/from16 v3, p2

    move-object/from16 v7, p6

    move-object v10, v0

    move-object/from16 v19, v1

    move/from16 v1, v30

    goto/16 :goto_34

    :pswitch_d
    move-object/from16 v3, p2

    move-object/from16 v6, p6

    move-object v12, v1

    move/from16 v27, v7

    move/from16 v7, v30

    const/4 v1, 0x2

    if-ne v8, v1, :cond_30

    .line 75
    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 76
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 77
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v1

    iget v4, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v4, :cond_2f

    .line 78
    array-length v5, v3

    sub-int/2addr v5, v1

    if-gt v4, v5, :cond_2e

    add-int/2addr v4, v1

    if-lt v1, v4, :cond_2d

    if-ne v1, v4, :cond_2c

    :goto_23
    move/from16 v5, v27

    move/from16 v27, v14

    move v14, v5

    move-object v10, v0

    move v5, v1

    move v1, v7

    goto :goto_21

    .line 296
    :cond_2c
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 79
    :cond_2d
    invoke-static {v3, v1, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    iget-wide v0, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 80
    invoke-static {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzc(J)J

    throw v16

    :cond_2e
    const/16 v20, 0x0

    .line 294
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_2f
    const/16 v20, 0x0

    .line 292
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_30
    if-eqz v8, :cond_31

    :goto_24
    move/from16 v1, v27

    move/from16 v27, v14

    move v14, v1

    move-object v10, v0

    move v1, v7

    move-object/from16 v19, v12

    :goto_25
    move-object v7, v6

    goto/16 :goto_34

    .line 81
    :cond_31
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 82
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 83
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    iget-wide v0, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 84
    invoke-static {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzc(J)J

    throw v16

    :pswitch_e
    move-object/from16 v3, p2

    move-object/from16 v6, p6

    move-object v12, v1

    move/from16 v27, v7

    move/from16 v7, v30

    const/4 v1, 0x2

    if-ne v8, v1, :cond_36

    .line 88
    sget v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 89
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 90
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v1

    iget v4, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v4, :cond_35

    .line 91
    array-length v5, v3

    sub-int/2addr v5, v1

    if-gt v4, v5, :cond_34

    add-int/2addr v4, v1

    if-lt v1, v4, :cond_33

    if-ne v1, v4, :cond_32

    goto :goto_23

    .line 302
    :cond_32
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 92
    :cond_33
    invoke-static {v3, v1, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    iget v0, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 93
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzb(I)I

    throw v16

    :cond_34
    const/16 v20, 0x0

    .line 300
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_35
    const/16 v20, 0x0

    .line 298
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_36
    if-eqz v8, :cond_37

    goto :goto_24

    .line 94
    :cond_37
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 95
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 96
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    iget v0, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 97
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzb(I)I

    throw v16

    :pswitch_f
    move-object/from16 v3, p2

    move-object/from16 v6, p6

    move-object v12, v1

    move/from16 v27, v7

    move/from16 v7, v30

    const/4 v1, 0x2

    if-ne v8, v1, :cond_38

    .line 101
    invoke-static {v3, v7, v11, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzl([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v1

    move/from16 v4, p4

    move v5, v7

    move v7, v1

    move/from16 v1, v27

    goto :goto_26

    :cond_38
    if-nez v8, :cond_40

    move/from16 v4, p4

    move-object v2, v3

    move v3, v7

    move-object v5, v11

    move/from16 v1, v27

    .line 102
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzk(I[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v7

    move v5, v3

    move-object v3, v2

    .line 103
    :goto_26
    invoke-direct {v0, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzp(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v2

    iget-object v8, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzl:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;

    .line 104
    sget v19, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->$r8$clinit:I

    if-eqz v2, :cond_3e

    if-eqz v11, :cond_3c

    move/from16 p3, v7

    .line 106
    invoke-interface {v11}, Ljava/util/List;->size()I

    move-result v7

    move-object/from16 v19, v12

    move/from16 v27, v14

    move-object/from16 v10, v16

    const/4 v12, 0x0

    const/4 v14, 0x0

    :goto_27
    if-ge v12, v7, :cond_3b

    .line 107
    invoke-interface {v11, v12}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v22

    move-object/from16 v0, v22

    check-cast v0, Ljava/lang/Integer;

    move/from16 v30, v13

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v13

    invoke-interface {v2, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;->zza(I)Z

    move-result v22

    if-eqz v22, :cond_3a

    if-eq v12, v14, :cond_39

    .line 108
    invoke-interface {v11, v14, v0}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    :cond_39
    add-int/lit8 v14, v14, 0x1

    goto :goto_28

    .line 109
    :cond_3a
    invoke-static {v15, v9, v13, v10, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zze(Ljava/lang/Object;IILjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;)Ljava/lang/Object;

    move-result-object v10

    :goto_28
    add-int/lit8 v12, v12, 0x1

    move-object/from16 v0, p0

    move/from16 v13, v30

    goto :goto_27

    :cond_3b
    move/from16 v30, v13

    if-eq v14, v7, :cond_3f

    .line 110
    invoke-interface {v11, v14, v7}, Ljava/util/List;->subList(II)Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->clear()V

    goto :goto_2a

    :cond_3c
    move/from16 p3, v7

    move-object/from16 v19, v12

    move/from16 v30, v13

    move/from16 v27, v14

    .line 111
    invoke-interface {v11}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    move-object/from16 v7, v16

    :cond_3d
    :goto_29
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v10

    if-eqz v10, :cond_3f

    .line 112
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/lang/Integer;

    invoke-virtual {v10}, Ljava/lang/Integer;->intValue()I

    move-result v10

    invoke-interface {v2, v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;->zza(I)Z

    move-result v11

    if-nez v11, :cond_3d

    .line 113
    invoke-static {v15, v9, v10, v7, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayh;->zze(Ljava/lang/Object;IILjava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayq;)Ljava/lang/Object;

    move-result-object v7

    .line 114
    invoke-interface {v0}, Ljava/util/Iterator;->remove()V

    goto :goto_29

    :cond_3e
    move/from16 p3, v7

    move-object/from16 v19, v12

    move/from16 v30, v13

    move/from16 v27, v14

    :cond_3f
    :goto_2a
    move-object/from16 v10, p0

    move v14, v1

    move v1, v5

    move-object v7, v6

    move/from16 v13, v30

    move/from16 v5, p3

    goto/16 :goto_35

    :cond_40
    move/from16 v4, p4

    move-object/from16 v19, v12

    move/from16 v1, v27

    move/from16 v27, v14

    move-object/from16 v10, p0

    :cond_41
    :goto_2b
    move v14, v1

    move v1, v7

    goto/16 :goto_25

    :pswitch_10
    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v5, v30

    const/4 v0, 0x2

    move/from16 v30, v13

    if-ne v8, v0, :cond_49

    .line 115
    invoke-static {v3, v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v7, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v7, :cond_48

    .line 116
    array-length v8, v3

    sub-int/2addr v8, v0

    if-gt v7, v8, :cond_47

    if-nez v7, :cond_42

    .line 117
    sget-object v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    invoke-interface {v11, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2d

    .line 118
    :cond_42
    invoke-static {v3, v0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzi([BII)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    move-result-object v8

    invoke-interface {v11, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_2c
    add-int/2addr v0, v7

    :goto_2d
    if-ge v0, v4, :cond_46

    .line 119
    invoke-static {v3, v0, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v7

    iget v8, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ne v1, v8, :cond_46

    .line 120
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v7, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v7, :cond_45

    .line 121
    array-length v8, v3

    sub-int/2addr v8, v0

    if-gt v7, v8, :cond_44

    if-nez v7, :cond_43

    .line 310
    sget-object v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    .line 122
    invoke-interface {v11, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2d

    .line 123
    :cond_43
    invoke-static {v3, v0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;->zzi([BII)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawb;

    move-result-object v8

    invoke-interface {v11, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2c

    .line 310
    :cond_44
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    :cond_45
    const/16 v20, 0x0

    .line 308
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_46
    const/16 v20, 0x0

    move-object/from16 v10, p0

    move v14, v1

    move v1, v5

    move-object v7, v6

    move/from16 v13, v30

    :goto_2e
    move v5, v0

    goto/16 :goto_35

    :cond_47
    const/16 v20, 0x0

    .line 306
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_48
    const/16 v20, 0x0

    .line 304
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_49
    move-object/from16 v10, p0

    move v14, v1

    move v1, v5

    move-object v7, v6

    move/from16 v13, v30

    goto/16 :goto_34

    :pswitch_11
    move-object/from16 v3, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v5, v30

    const/4 v0, 0x2

    move/from16 v30, v13

    if-ne v8, v0, :cond_4a

    move-object/from16 v10, p0

    move v2, v1

    move/from16 v13, v30

    .line 124
    invoke-direct {v10, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v1

    move v7, v5

    move v5, v4

    move v4, v7

    move-object v7, v6

    move-object v6, v11

    .line 125
    invoke-static/range {v1 .. v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzm(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;I[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    move-object v6, v7

    move v5, v0

    move v14, v2

    move v1, v4

    goto/16 :goto_35

    :cond_4a
    move-object/from16 v10, p0

    move v7, v5

    move/from16 v13, v30

    move v5, v4

    goto/16 :goto_2b

    :pswitch_12
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move-object v10, v0

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v7, v30

    const/4 v0, 0x2

    if-ne v8, v0, :cond_41

    const-wide/32 v28, 0x20000000

    and-long v28, v31, v28

    cmp-long v0, v28, v23

    if-nez v0, :cond_50

    .line 126
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v2, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v2, :cond_4f

    if-nez v2, :cond_4b

    .line 127
    invoke-interface {v11, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_30

    .line 134
    :cond_4b
    new-instance v8, Ljava/lang/String;

    .line 128
    sget-object v12, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v8, v3, v0, v2, v12}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 129
    invoke-interface {v11, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_2f
    add-int/2addr v0, v2

    :goto_30
    if-ge v0, v5, :cond_4e

    .line 130
    invoke-static {v3, v0, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    iget v8, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ne v1, v8, :cond_4e

    .line 131
    invoke-static {v3, v2, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v2, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v2, :cond_4d

    if-nez v2, :cond_4c

    .line 132
    invoke-interface {v11, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_30

    :cond_4c
    new-instance v8, Ljava/lang/String;

    .line 133
    sget-object v12, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v8, v3, v0, v2, v12}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 134
    invoke-interface {v11, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2f

    .line 314
    :cond_4d
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    :cond_4e
    const/16 v20, 0x0

    :goto_31
    move v5, v0

    move v14, v1

    move v1, v7

    goto/16 :goto_22

    :cond_4f
    const/16 v20, 0x0

    .line 312
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    .line 135
    :cond_50
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v2, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v2, :cond_56

    if-nez v2, :cond_51

    .line 136
    invoke-interface {v11, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_33

    :cond_51
    add-int v8, v0, v2

    .line 137
    invoke-static {v3, v0, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayz;->zza([BII)Z

    move-result v12

    if-eqz v12, :cond_55

    .line 318
    new-instance v12, Ljava/lang/String;

    .line 138
    sget-object v14, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v12, v3, v0, v2, v14}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 139
    invoke-interface {v11, v12}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :goto_32
    move v0, v8

    :goto_33
    if-ge v0, v5, :cond_4e

    .line 140
    invoke-static {v3, v0, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    iget v8, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ne v1, v8, :cond_4e

    .line 141
    invoke-static {v3, v2, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v2, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v2, :cond_54

    if-nez v2, :cond_52

    .line 142
    invoke-interface {v11, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_33

    :cond_52
    add-int v8, v0, v2

    .line 143
    invoke-static {v3, v0, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayz;->zza([BII)Z

    move-result v12

    if-eqz v12, :cond_53

    .line 322
    new-instance v12, Ljava/lang/String;

    .line 144
    sget-object v14, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v12, v3, v0, v2, v14}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 145
    invoke-interface {v11, v12}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_32

    .line 322
    :cond_53
    invoke-static/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    :cond_54
    const/16 v20, 0x0

    .line 320
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_55
    const/16 v20, 0x0

    .line 318
    invoke-static/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_56
    const/16 v20, 0x0

    .line 316
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :pswitch_13
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move-object v10, v0

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v7, v30

    const/4 v0, 0x2

    if-ne v8, v0, :cond_5b

    .line 146
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 147
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 148
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v4, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v4, :cond_5a

    .line 149
    array-length v8, v3

    sub-int/2addr v8, v0

    if-gt v4, v8, :cond_59

    add-int/2addr v4, v0

    if-lt v0, v4, :cond_58

    if-ne v0, v4, :cond_57

    goto/16 :goto_31

    .line 328
    :cond_57
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 150
    :cond_58
    invoke-static {v3, v0, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    .line 151
    throw v16

    :cond_59
    const/16 v20, 0x0

    .line 326
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_5a
    const/16 v20, 0x0

    .line 324
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_5b
    if-eqz v8, :cond_5c

    goto/16 :goto_2b

    .line 152
    :cond_5c
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 153
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 154
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    iget-wide v0, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 155
    throw v16

    :pswitch_14
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move-object v10, v0

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v7, v30

    const/4 v0, 0x2

    if-ne v8, v0, :cond_5f

    .line 159
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 160
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 161
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v1, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v1, :cond_5e

    .line 162
    array-length v3, v3

    sub-int/2addr v3, v0

    if-le v1, v3, :cond_5d

    .line 332
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 163
    :cond_5d
    throw v16

    :cond_5e
    const/16 v20, 0x0

    .line 330
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_5f
    const/4 v4, 0x5

    if-eq v8, v4, :cond_60

    goto/16 :goto_2b

    .line 165
    :cond_60
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 166
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 167
    invoke-static {v3, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzd([BI)I

    throw v16

    :pswitch_15
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move-object v10, v0

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v7, v30

    const/4 v0, 0x2

    if-ne v8, v0, :cond_63

    .line 170
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 171
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 172
    invoke-static {v3, v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v1, v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v1, :cond_62

    .line 173
    array-length v3, v3

    sub-int/2addr v3, v0

    if-le v1, v3, :cond_61

    .line 338
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 174
    :cond_61
    throw v16

    :cond_62
    const/16 v20, 0x0

    .line 336
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_63
    const/4 v4, 0x1

    if-eq v8, v4, :cond_64

    goto/16 :goto_2b

    .line 176
    :cond_64
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 177
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 178
    invoke-static {v3, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zze([BI)J

    throw v16

    :pswitch_16
    move-object/from16 v3, p2

    move/from16 v5, p4

    move-object/from16 v6, p6

    move-object v10, v0

    move-object/from16 v19, v1

    move v1, v7

    move/from16 v27, v14

    move/from16 v7, v30

    const/4 v0, 0x2

    if-ne v8, v0, :cond_65

    .line 181
    invoke-static {v3, v7, v11, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzl([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    goto/16 :goto_31

    :cond_65
    if-nez v8, :cond_41

    move-object v2, v3

    move v4, v5

    move v3, v7

    move-object v5, v11

    .line 182
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzk(I[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    move v14, v1

    move v1, v3

    move-object v7, v6

    move-object v3, v2

    goto/16 :goto_2e

    :pswitch_17
    move-object/from16 v3, p2

    move-object v10, v0

    move-object/from16 v19, v1

    move/from16 v27, v14

    move/from16 v1, v30

    const/4 v0, 0x2

    move v14, v7

    move-object/from16 v7, p6

    if-ne v8, v0, :cond_6a

    .line 183
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 184
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 185
    invoke-static {v3, v1, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v4, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v4, :cond_69

    .line 186
    array-length v5, v3

    sub-int/2addr v5, v0

    if-gt v4, v5, :cond_68

    add-int/2addr v4, v0

    if-lt v0, v4, :cond_67

    if-ne v0, v4, :cond_66

    goto/16 :goto_2e

    .line 346
    :cond_66
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 187
    :cond_67
    invoke-static {v3, v0, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    .line 188
    throw v16

    :cond_68
    const/16 v20, 0x0

    .line 344
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_69
    const/16 v20, 0x0

    .line 342
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_6a
    if-eqz v8, :cond_6b

    goto/16 :goto_34

    .line 189
    :cond_6b
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 190
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 191
    invoke-static {v3, v1, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    iget-wide v0, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 192
    throw v16

    :pswitch_18
    move-object/from16 v3, p2

    move-object v10, v0

    move-object/from16 v19, v1

    move/from16 v27, v14

    move/from16 v1, v30

    const/4 v0, 0x2

    move v14, v7

    move-object/from16 v7, p6

    if-ne v8, v0, :cond_6e

    .line 196
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 197
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 198
    invoke-static {v3, v1, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v1, :cond_6d

    .line 199
    array-length v3, v3

    sub-int/2addr v3, v0

    if-le v1, v3, :cond_6c

    .line 350
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 200
    :cond_6c
    throw v16

    :cond_6d
    const/16 v20, 0x0

    .line 348
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_6e
    const/4 v4, 0x5

    if-eq v8, v4, :cond_6f

    goto :goto_34

    .line 203
    :cond_6f
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 204
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 205
    invoke-static {v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzd([BI)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->intBitsToFloat(I)F

    .line 206
    throw v16

    :pswitch_19
    move-object/from16 v3, p2

    move-object v10, v0

    move-object/from16 v19, v1

    move/from16 v27, v14

    move/from16 v1, v30

    const/4 v0, 0x2

    move v14, v7

    move-object/from16 v7, p6

    if-ne v8, v0, :cond_72

    .line 210
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 211
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 212
    invoke-static {v3, v1, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v0

    iget v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-ltz v1, :cond_71

    .line 213
    array-length v3, v3

    sub-int/2addr v3, v0

    if-le v1, v3, :cond_70

    .line 356
    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    .line 214
    :cond_70
    throw v16

    :cond_71
    const/16 v20, 0x0

    .line 354
    invoke-static/range {v26 .. v26}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :cond_72
    const/4 v4, 0x1

    if-eq v8, v4, :cond_74

    :goto_34
    move v5, v1

    :goto_35
    if-eq v5, v1, :cond_73

    move/from16 v4, p4

    move-object v6, v7

    move v7, v9

    move-object v0, v10

    move v8, v13

    move-object v2, v15

    move-object/from16 v1, v19

    move/from16 v9, v21

    :goto_36
    const/4 v11, 0x0

    move v15, v14

    move/from16 v14, v27

    goto/16 :goto_0

    :cond_73
    move/from16 v11, p5

    move v8, v13

    move-object/from16 v0, v19

    goto/16 :goto_43

    .line 217
    :cond_74
    sget v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 218
    invoke-static {v11}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 219
    invoke-static {v3, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zze([BI)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Double;->longBitsToDouble(J)D

    .line 220
    throw v16

    :cond_75
    move-object v10, v0

    move-object v0, v1

    move v1, v3

    move/from16 v27, v14

    move-object/from16 v3, p2

    move v14, v7

    move-object/from16 v7, p6

    const/16 v2, 0x32

    if-ne v12, v2, :cond_78

    const/4 v2, 0x2

    if-ne v8, v2, :cond_77

    .line 359
    invoke-direct {v10, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzo(I)Ljava/lang/Object;

    move-result-object v1

    .line 360
    invoke-virtual {v0, v15, v5, v6}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    .line 361
    move-object v3, v2

    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zze()Z

    move-result v3

    if-nez v3, :cond_76

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    move-result-object v3

    .line 362
    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    move-result-object v3

    .line 363
    invoke-static {v3, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxs;->zza(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 364
    invoke-virtual {v0, v15, v5, v6, v3}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 365
    :cond_76
    invoke-static {v1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 366
    throw v16

    :cond_77
    :goto_37
    move/from16 v11, p5

    move v5, v1

    move v8, v13

    goto/16 :goto_43

    :cond_78
    add-int/lit8 v2, v13, 0x2

    .line 224
    aget v2, v22, v2

    const v17, 0xfffff

    and-int v2, v2, v17

    move/from16 v30, v1

    int-to-long v1, v2

    packed-switch v12, :pswitch_data_2

    :cond_79
    move/from16 v11, v30

    :cond_7a
    :goto_38
    move/from16 v30, v13

    goto/16 :goto_41

    :pswitch_1a
    const/4 v1, 0x3

    if-ne v8, v1, :cond_79

    and-int/lit8 v1, v14, -0x8

    or-int/lit8 v6, v1, 0x4

    .line 225
    invoke-direct {v10, v15, v9, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzs(Ljava/lang/Object;II)Ljava/lang/Object;

    move-result-object v1

    .line 226
    invoke-direct {v10, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    move/from16 v5, p4

    move/from16 v4, v30

    .line 227
    invoke-static/range {v1 .. v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzj(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;[BIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    .line 228
    invoke-direct {v10, v15, v9, v13, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzt(Ljava/lang/Object;IILjava/lang/Object;)V

    :goto_39
    move v5, v2

    move v11, v4

    :goto_3a
    move/from16 v30, v13

    goto/16 :goto_42

    :pswitch_1b
    move/from16 v4, v30

    if-nez v8, :cond_7b

    .line 229
    invoke-static {v3, v4, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget-wide v11, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 230
    invoke-static {v11, v12}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzc(J)J

    move-result-wide v11

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v11

    invoke-virtual {v0, v15, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 231
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :goto_3b
    move v11, v4

    move v5, v8

    goto :goto_3a

    :cond_7b
    move v11, v4

    goto :goto_38

    :pswitch_1c
    move/from16 v4, v30

    if-nez v8, :cond_7b

    .line 232
    invoke-static {v3, v4, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget v11, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 233
    invoke-static {v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawe;->zzb(I)I

    move-result v11

    invoke-static {v11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v11

    invoke-virtual {v0, v15, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 234
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3b

    :pswitch_1d
    move/from16 v4, v30

    if-nez v8, :cond_7b

    .line 235
    invoke-static {v3, v4, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget v11, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 236
    invoke-direct {v10, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzp(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v12

    if-eqz v12, :cond_7d

    invoke-interface {v12, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;->zza(I)Z

    move-result v12

    if-eqz v12, :cond_7c

    goto :goto_3c

    .line 239
    :cond_7c
    invoke-static {v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zze(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v1

    int-to-long v5, v11

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v1, v14, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzg(ILjava/lang/Object;)V

    goto :goto_3b

    .line 237
    :cond_7d
    :goto_3c
    invoke-static {v11}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v11

    invoke-virtual {v0, v15, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 238
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3b

    :pswitch_1e
    move/from16 v4, v30

    const/4 v12, 0x2

    if-ne v8, v12, :cond_7b

    .line 240
    invoke-static {v3, v4, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzf([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget-object v11, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzc:Ljava/lang/Object;

    .line 241
    invoke-virtual {v0, v15, v5, v6, v11}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 242
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_3b

    :pswitch_1f
    move/from16 v4, v30

    const/4 v12, 0x2

    if-ne v8, v12, :cond_7b

    .line 243
    invoke-direct {v10, v15, v9, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzs(Ljava/lang/Object;II)Ljava/lang/Object;

    move-result-object v1

    .line 244
    invoke-direct {v10, v13}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    move/from16 v5, p4

    move-object v6, v7

    .line 245
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzi(Ljava/lang/Object;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v2

    .line 246
    invoke-direct {v10, v15, v9, v13, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzt(Ljava/lang/Object;IILjava/lang/Object;)V

    goto/16 :goto_39

    :pswitch_20
    move/from16 p3, v11

    move/from16 v11, v30

    const/4 v12, 0x2

    if-ne v8, v12, :cond_7a

    .line 247
    invoke-static {v3, v11, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v8

    iget v12, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    if-nez v12, :cond_7e

    .line 248
    invoke-virtual {v0, v15, v5, v6, v4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move/from16 v30, v13

    goto :goto_3e

    :cond_7e
    and-int v4, p3, v19

    move/from16 p3, v4

    add-int v4, v8, v12

    if-eqz p3, :cond_7f

    .line 249
    invoke-static {v3, v8, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayz;->zza([BII)Z

    move-result v19

    if-eqz v19, :cond_80

    :cond_7f
    move/from16 p3, v4

    goto :goto_3d

    .line 368
    :cond_80
    invoke-static/range {v25 .. v25}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    :goto_3d
    new-instance v4, Ljava/lang/String;

    move/from16 v30, v13

    .line 250
    sget-object v13, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v4, v3, v8, v12, v13}, Ljava/lang/String;-><init>([BIILjava/nio/charset/Charset;)V

    .line 251
    invoke-virtual {v0, v15, v5, v6, v4}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    move/from16 v8, p3

    .line 252
    :goto_3e
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    move v5, v8

    goto/16 :goto_42

    :pswitch_21
    move/from16 v11, v30

    move/from16 v30, v13

    if-nez v8, :cond_82

    .line 253
    invoke-static {v3, v11, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v4

    iget-wide v12, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    cmp-long v8, v12, v23

    if-eqz v8, :cond_81

    const/16 v28, 0x1

    goto :goto_3f

    :cond_81
    const/16 v28, 0x0

    .line 254
    :goto_3f
    invoke-static/range {v28 .. v28}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 255
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :goto_40
    move v5, v4

    goto/16 :goto_42

    :pswitch_22
    move/from16 v11, v30

    const/4 v4, 0x5

    move/from16 v30, v13

    if-ne v8, v4, :cond_82

    add-int/lit8 v4, v11, 0x4

    .line 256
    invoke-static {v3, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzd([BI)I

    move-result v8

    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 257
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_40

    :pswitch_23
    move/from16 v11, v30

    const/4 v4, 0x1

    move/from16 v30, v13

    if-ne v8, v4, :cond_82

    add-int/lit8 v4, v11, 0x8

    .line 258
    invoke-static {v3, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zze([BI)J

    move-result-wide v12

    invoke-static {v12, v13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 259
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_40

    :pswitch_24
    move/from16 v11, v30

    move/from16 v30, v13

    if-nez v8, :cond_82

    .line 260
    invoke-static {v3, v11, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zza([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v4

    iget v8, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zza:I

    .line 261
    invoke-static {v8}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 262
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_40

    :pswitch_25
    move/from16 v11, v30

    move/from16 v30, v13

    if-nez v8, :cond_82

    .line 263
    invoke-static {v3, v11, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzc([BILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v4

    iget-wide v12, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzb:J

    .line 264
    invoke-static {v12, v13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 265
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_40

    :pswitch_26
    move/from16 v11, v30

    const/4 v4, 0x5

    move/from16 v30, v13

    if-ne v8, v4, :cond_82

    add-int/lit8 v4, v11, 0x4

    .line 266
    invoke-static {v3, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzd([BI)I

    move-result v8

    invoke-static {v8}, Ljava/lang/Float;->intBitsToFloat(I)F

    move-result v8

    .line 267
    invoke-static {v8}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 268
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto :goto_40

    :pswitch_27
    move/from16 v11, v30

    const/4 v4, 0x1

    move/from16 v30, v13

    if-ne v8, v4, :cond_82

    add-int/lit8 v4, v11, 0x8

    .line 269
    invoke-static {v3, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zze([BI)J

    move-result-wide v12

    invoke-static {v12, v13}, Ljava/lang/Double;->longBitsToDouble(J)D

    move-result-wide v12

    .line 270
    invoke-static {v12, v13}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object v8

    invoke-virtual {v0, v15, v5, v6, v8}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    .line 271
    invoke-virtual {v0, v15, v1, v2, v9}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    goto/16 :goto_40

    :cond_82
    :goto_41
    move v5, v11

    :goto_42
    if-eq v5, v11, :cond_83

    move/from16 v4, p4

    move-object v1, v0

    move-object v6, v7

    move v7, v9

    move-object v0, v10

    move-object v2, v15

    move/from16 v9, v21

    move/from16 v8, v30

    goto/16 :goto_36

    :cond_83
    move/from16 v11, p5

    move/from16 v8, v30

    :goto_43
    if-ne v14, v11, :cond_84

    if-eqz v11, :cond_84

    move/from16 v4, p4

    move-object v2, v15

    move v15, v14

    move/from16 v9, v21

    const v1, 0xfffff

    move/from16 v14, v27

    goto/16 :goto_46

    .line 385
    :cond_84
    iget-boolean v1, v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    if-eqz v1, :cond_85

    iget-object v1, v7, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;->zzd:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;

    .line 272
    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;

    .line 273
    sget v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavq;->$r8$clinit:I

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;

    if-eq v1, v2, :cond_85

    iget-object v2, v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;

    .line 274
    sget v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->$r8$clinit:I

    .line 275
    invoke-virtual {v1, v2, v9}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;->zzd(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawx;

    move v3, v5

    .line 276
    invoke-static {v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zze(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v5

    move-object/from16 v2, p2

    move/from16 v4, p4

    move-object v6, v7

    move v1, v14

    .line 277
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzn(I[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v3

    :goto_44
    move v5, v3

    goto :goto_45

    :cond_85
    move v3, v5

    move v1, v14

    .line 278
    invoke-static {v15}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zze(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    move-result-object v5

    move-object/from16 v2, p2

    move/from16 v4, p4

    move-object/from16 v6, p6

    .line 279
    invoke-static/range {v1 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs;->zzn(I[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    move-result v3

    goto :goto_44

    :goto_45
    move-object/from16 v3, p2

    move-object/from16 v6, p6

    move v7, v9

    move-object v2, v15

    move/from16 v9, v21

    move/from16 v14, v27

    const/4 v11, 0x0

    move v15, v1

    move-object v1, v0

    move-object v0, v10

    goto/16 :goto_0

    :cond_86
    move/from16 v11, p5

    move-object v10, v0

    move-object v0, v1

    move/from16 v21, v9

    move/from16 v27, v14

    const v1, 0xfffff

    :goto_46
    if-eq v9, v1, :cond_87

    int-to-long v6, v9

    .line 369
    invoke-virtual {v0, v2, v6, v7, v14}, Lsun/misc/Unsafe;->putInt(Ljava/lang/Object;JI)V

    :cond_87
    iget v0, v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzj:I

    :goto_47
    iget v1, v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzk:I

    if-ge v0, v1, :cond_8a

    iget-object v1, v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzi:[I

    iget-object v3, v10, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    .line 370
    aget v1, v1, v0

    .line 371
    aget v3, v3, v1

    .line 372
    invoke-direct {v10, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v3

    const v17, 0xfffff

    and-int v3, v3, v17

    int-to-long v6, v3

    .line 373
    invoke-static {v2, v6, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    if-nez v3, :cond_88

    goto :goto_48

    .line 374
    :cond_88
    invoke-direct {v10, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzp(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxd;

    move-result-object v6

    if-nez v6, :cond_89

    :goto_48
    add-int/lit8 v0, v0, 0x1

    goto :goto_47

    .line 376
    :cond_89
    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    .line 377
    invoke-direct {v10, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzo(I)Ljava/lang/Object;

    move-result-object v0

    .line 378
    invoke-static {v0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 379
    throw v16

    .line 381
    :cond_8a
    const-string v0, "Failed to parse the message."

    if-nez v11, :cond_8c

    if-ne v5, v4, :cond_8b

    goto :goto_49

    .line 383
    :cond_8b
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/16 v20, 0x0

    return v20

    :cond_8c
    const/16 v20, 0x0

    if-gt v5, v4, :cond_8d

    if-ne v15, v11, :cond_8d

    :goto_49
    return v5

    .line 385
    :cond_8d
    invoke-static {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavs$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return v20

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_9
        :pswitch_2
        :pswitch_7
        :pswitch_8
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x12
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_16
        :pswitch_f
        :pswitch_14
        :pswitch_15
        :pswitch_e
        :pswitch_d
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_16
        :pswitch_f
        :pswitch_14
        :pswitch_15
        :pswitch_e
        :pswitch_d
    .end packed-switch

    :pswitch_data_2
    .packed-switch 0x33
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
        :pswitch_1e
        :pswitch_24
        :pswitch_1d
        :pswitch_22
        :pswitch_23
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
    .end packed-switch
.end method

.method public final zzg(Ljava/lang/Object;[BIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)V
    .locals 7

    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move v4, p4

    move-object v6, p5

    .line 1
    invoke-virtual/range {v0 .. v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzf(Ljava/lang/Object;[BIIILcom/google/android/gms/internal/mlkit_genai_prompt/zzavr;)I

    return-void
.end method

.method public final zzh(Ljava/lang/Object;)V
    .locals 7

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzy(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_2

    :cond_0
    instance-of v0, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    .line 2
    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    const v2, 0x7fffffff

    .line 3
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzL(I)V

    iput v1, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzavo;->zza:I

    .line 4
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzF()V

    :cond_1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_5

    .line 5
    invoke-direct {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v2

    const v3, 0xfffff

    and-int/2addr v3, v2

    invoke-static {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result v2

    int-to-long v3, v3

    const/16 v5, 0x9

    if-eq v2, v5, :cond_3

    const/16 v5, 0x3c

    if-eq v2, v5, :cond_2

    const/16 v5, 0x44

    if-eq v2, v5, :cond_2

    packed-switch v2, :pswitch_data_0

    goto :goto_1

    .line 10
    :pswitch_0
    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    .line 11
    invoke-virtual {v2, p1, v3, v4}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    if-eqz v5, :cond_4

    .line 12
    move-object v6, v5

    check-cast v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;->zzd()V

    .line 13
    invoke-virtual {v2, p1, v3, v4, v5}, Lsun/misc/Unsafe;->putObject(Ljava/lang/Object;JLjava/lang/Object;)V

    goto :goto_1

    .line 6
    :pswitch_1
    invoke-static {p1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;

    .line 7
    invoke-interface {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxh;->zzb()V

    goto :goto_1

    .line 8
    :cond_2
    aget v2, v0, v1

    .line 9
    invoke-direct {p0, p1, v2, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 10
    invoke-direct {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    invoke-virtual {v5, p1, v3, v4}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-interface {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzh(Ljava/lang/Object;)V

    goto :goto_1

    .line 14
    :cond_3
    :pswitch_2
    invoke-direct {p0, p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzC(Ljava/lang/Object;I)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 15
    invoke-direct {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    sget-object v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    invoke-virtual {v5, p1, v3, v4}, Lsun/misc/Unsafe;->getObject(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v3

    invoke-interface {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzh(Ljava/lang/Object;)V

    :cond_4
    :goto_1
    add-int/lit8 v1, v1, 0x3

    goto :goto_0

    .line 16
    :cond_5
    move-object v0, p1

    check-cast v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    iget-object v0, v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;

    .line 17
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayr;->zzd()V

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    if-nez p0, :cond_6

    :goto_2
    return-void

    .line 18
    :cond_6
    invoke-static {p1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    throw p0

    nop

    :pswitch_data_0
    .packed-switch 0x11
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzi(Ljava/lang/Object;)Z
    .locals 15

    move-object/from16 v1, p1

    const/4 v6, 0x0

    const v7, 0xfffff

    move v2, v6

    move v8, v2

    move v0, v7

    .line 1
    :goto_0
    iget v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzj:I

    const/4 v9, 0x0

    const/4 v4, 0x1

    if-ge v8, v3, :cond_b

    iget-object v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzi:[I

    aget v3, v3, v8

    .line 2
    invoke-direct {p0, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzv(I)I

    move-result v10

    iget-object v11, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzc:[I

    add-int/lit8 v5, v3, 0x2

    .line 3
    aget v5, v11, v5

    and-int v12, v5, v7

    ushr-int/lit8 v5, v5, 0x14

    shl-int v5, v4, v5

    if-eq v12, v0, :cond_1

    if-eq v12, v7, :cond_0

    int-to-long v13, v12

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzb:Lsun/misc/Unsafe;

    .line 4
    invoke-virtual {v0, v1, v13, v14}, Lsun/misc/Unsafe;->getInt(Ljava/lang/Object;J)I

    move-result v2

    :cond_0
    move v4, v2

    move v0, v12

    goto :goto_1

    :cond_1
    move v4, v2

    :goto_1
    const/high16 v2, 0x10000000

    and-int/2addr v2, v10

    if-eqz v2, :cond_2

    move v2, v3

    move v3, v0

    move-object v0, p0

    .line 5
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzB(Ljava/lang/Object;IIII)Z

    move-result v12

    if-nez v12, :cond_3

    return v6

    :cond_2
    move v2, v3

    move v3, v0

    :cond_3
    invoke-static {v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzx(I)I

    move-result v12

    const/16 v13, 0x9

    if-eq v12, v13, :cond_9

    const/16 v13, 0x11

    if-eq v12, v13, :cond_9

    const/16 v5, 0x1b

    if-eq v12, v5, :cond_7

    const/16 v5, 0x3c

    if-eq v12, v5, :cond_6

    const/16 v5, 0x44

    if-eq v12, v5, :cond_6

    const/16 v5, 0x31

    if-eq v12, v5, :cond_7

    const/16 v5, 0x32

    if-eq v12, v5, :cond_4

    goto :goto_3

    :cond_4
    and-int v5, v10, v7

    int-to-long v10, v5

    .line 12
    invoke-static {v1, v10, v11}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    .line 13
    check-cast v5, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxr;

    .line 14
    invoke-interface {v5}, Ljava/util/Map;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_5

    goto :goto_3

    .line 20
    :cond_5
    invoke-direct {p0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzo(I)Ljava/lang/Object;

    move-result-object p0

    .line 21
    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 22
    throw v9

    .line 15
    :cond_6
    aget v5, v11, v2

    .line 16
    invoke-direct {p0, v1, v5, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzE(Ljava/lang/Object;II)Z

    move-result v5

    if-eqz v5, :cond_a

    .line 17
    invoke-direct {p0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    invoke-static {v1, v10, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzu(Ljava/lang/Object;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;)Z

    move-result v2

    if-nez v2, :cond_a

    return v6

    :cond_7
    and-int v5, v10, v7

    int-to-long v9, v5

    .line 6
    invoke-static {v1, v9, v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayx;->zzl(Ljava/lang/Object;J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/List;

    .line 7
    invoke-interface {v5}, Ljava/util/List;->isEmpty()Z

    move-result v9

    if-nez v9, :cond_a

    .line 8
    invoke-direct {p0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    move v9, v6

    .line 9
    :goto_2
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v10

    if-ge v9, v10, :cond_a

    .line 10
    invoke-interface {v5, v9}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v10

    .line 11
    invoke-interface {v2, v10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;->zzi(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_8

    return v6

    :cond_8
    add-int/lit8 v9, v9, 0x1

    goto :goto_2

    :cond_9
    move-object v0, p0

    .line 18
    invoke-direct/range {v0 .. v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzB(Ljava/lang/Object;IIII)Z

    move-result v5

    if-eqz v5, :cond_a

    .line 19
    invoke-direct {p0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;

    move-result-object v2

    invoke-static {v1, v10, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzu(Ljava/lang/Object;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzayg;)Z

    move-result v2

    if-nez v2, :cond_a

    return v6

    :cond_a
    :goto_3
    add-int/lit8 v8, v8, 0x1

    move v0, v3

    move v2, v4

    goto/16 :goto_0

    :cond_b
    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxz;->zzh:Z

    if-nez p0, :cond_c

    return v4

    .line 23
    :cond_c
    invoke-static {v1}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    throw v9
.end method
