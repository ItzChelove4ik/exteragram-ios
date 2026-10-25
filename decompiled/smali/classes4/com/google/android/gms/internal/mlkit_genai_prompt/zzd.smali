.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxx;


# static fields
.field private static final zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;


# instance fields
.field private zzb:I

.field private zzd:J

.field private zze:J

.field private zzf:I

.field private zzg:J

.field private zzh:I

.field private zzi:J

.field private zzj:I

.field private zzk:J

.field private zzl:I

.field private zzm:J

.field private zzn:I

.field private zzo:J

.field private zzp:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    const-class v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    invoke-static {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzN(Ljava/lang/Class;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;-><init>()V

    return-void
.end method

.method public static synthetic zzC()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    return-object v0
.end method

.method public static zzo([BLcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    invoke-static {v0, p0, p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzR(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;[BLcom/google/android/gms/internal/mlkit_genai_prompt/zzawl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;

    move-result-object p0

    check-cast p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    return-object p0
.end method


# virtual methods
.method public final synthetic zzA(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit16 v0, v0, 0x800

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzo:J

    return-void
.end method

.method public final synthetic zzB(I)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit16 v0, v0, 0x1000

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzp:I

    return-void
.end method

.method public final zza()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzd:J

    return-wide v0
.end method

.method public final zzb(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 14

    add-int/lit8 p0, p1, -0x1

    if-eqz p0, :cond_4

    const/4 v0, 0x2

    if-eq p0, v0, :cond_3

    const/4 v0, 0x3

    if-eq p0, v0, :cond_2

    const/4 v0, 0x4

    const/4 v1, 0x0

    if-eq p0, v0, :cond_1

    const/4 v0, 0x5

    if-ne p0, v0, :cond_0

    .line 1
    sget-object p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    return-object p0

    .line 4
    :cond_0
    throw v1

    .line 2
    :cond_1
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzc;

    .line 3
    invoke-direct {p0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzc;-><init>([B)V

    return-object p0

    :cond_2
    new-instance p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;-><init>()V

    return-object p0

    .line 1
    :cond_3
    const-string v12, "zzo"

    const-string v13, "zzp"

    const-string v0, "zzb"

    const-string v1, "zzd"

    const-string v2, "zze"

    const-string v3, "zzf"

    const-string v4, "zzg"

    const-string v5, "zzh"

    const-string v6, "zzi"

    const-string v7, "zzj"

    const-string v8, "zzk"

    const-string v9, "zzl"

    const-string v10, "zzm"

    const-string v11, "zzn"

    filled-new-array/range {v0 .. v13}, [Ljava/lang/Object;

    move-result-object p0

    sget-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;

    const-string v1, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0000\u0000\u0001\u1002\u0000\u0002\u1002\u0001\u0003\u1004\u0002\u0004\u1002\u0003\u0005\u1004\u0004\u0006\u1002\u0005\u0007\u1004\u0006\u0008\u1002\u0007\t\u1004\u0008\n\u1002\t\u000b\u1004\n\u000c\u1002\u000b\r\u1004\u000c"

    .line 2
    invoke-static {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzawy;->zzO(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaxw;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_4
    const/4 p0, 0x1

    .line 1
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zze:J

    return-wide v0
.end method

.method public final zzd()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzf:I

    return p0
.end method

.method public final zze()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzg:J

    return-wide v0
.end method

.method public final zzf()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzh:I

    return p0
.end method

.method public final zzg()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzi:J

    return-wide v0
.end method

.method public final zzh()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzj:I

    return p0
.end method

.method public final zzi()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzk:J

    return-wide v0
.end method

.method public final zzj()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzl:I

    return p0
.end method

.method public final zzk()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzm:J

    return-wide v0
.end method

.method public final zzl()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzn:I

    return p0
.end method

.method public final zzm()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzo:J

    return-wide v0
.end method

.method public final zzn()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzp:I

    return p0
.end method

.method public final synthetic zzp(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x1

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzd:J

    return-void
.end method

.method public final synthetic zzq(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x2

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zze:J

    return-void
.end method

.method public final synthetic zzr(I)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x4

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzf:I

    return-void
.end method

.method public final synthetic zzs(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x8

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzg:J

    return-void
.end method

.method public final synthetic zzt(I)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x10

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzh:I

    return-void
.end method

.method public final synthetic zzu(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x20

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzi:J

    return-void
.end method

.method public final synthetic zzv(I)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit8 v0, v0, 0x40

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzj:I

    return-void
.end method

.method public final synthetic zzw(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit16 v0, v0, 0x80

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzk:J

    return-void
.end method

.method public final synthetic zzx(I)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit16 v0, v0, 0x100

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzl:I

    return-void
.end method

.method public final synthetic zzy(J)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit16 v0, v0, 0x200

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput-wide p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzm:J

    return-void
.end method

.method public final synthetic zzz(I)V
    .locals 1

    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    or-int/lit16 v0, v0, 0x400

    iput v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzb:I

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzd;->zzn:I

    return-void
.end method
