.class public final Lcom/google/android/recaptcha/internal/zzay;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:I

.field private final zzb:J

.field private final zzc:Lcom/google/android/recaptcha/internal/zzax;

.field private zzd:J


# direct methods
.method public constructor <init>(Landroid/content/Context;IJZF)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzay;->zza:I

    iput-wide p3, p0, Lcom/google/android/recaptcha/internal/zzay;->zzb:J

    new-instance p2, Lcom/google/android/recaptcha/internal/zzax;

    invoke-direct {p2, p1}, Lcom/google/android/recaptcha/internal/zzax;-><init>(Landroid/content/Context;)V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzay;->zzc:Lcom/google/android/recaptcha/internal/zzax;

    .line 2
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzay;->zzd()V

    return-void
.end method

.method private final zzd()V
    .locals 10

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzay;->zzc:Lcom/google/android/recaptcha/internal/zzax;

    invoke-virtual {v0}, Landroid/database/sqlite/SQLiteOpenHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaju;->zzb:Lcom/google/android/recaptcha/internal/zzaju;

    .line 2
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object v2

    .line 3
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object v1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzay;->zza:I

    int-to-float v4, p0

    const v5, 0x3e4ccccd    # 0.2f

    mul-float/2addr v4, v5

    float-to-int v4, v4

    const/4 v5, 0x1

    invoke-static {v5, v4}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I

    move-result v4

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->length()I

    move-result v5

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v6

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v7

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v8

    invoke-static {v4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->length()I

    move-result v9

    add-int/lit16 v5, v5, 0xa5

    add-int/2addr v5, v6

    add-int/lit8 v5, v5, 0x19

    add-int/2addr v5, v7

    add-int/lit8 v5, v5, 0x18

    add-int/2addr v5, v8

    add-int/lit8 v5, v5, 0x7

    add-int/2addr v5, v9

    new-instance v6, Ljava/lang/StringBuilder;

    add-int/lit8 v5, v5, 0x1f

    invoke-direct {v6, v5}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v5, "\n        CREATE TRIGGER tgr48932\n        AFTER INSERT ON tbl64158\n        WHEN (SELECT COUNT(*) FROM tbl64158) > "

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "\n        BEGIN\n          DELETE FROM tbl64158 WHERE "

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " IN (\n            SELECT "

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " FROM tbl64158 ORDER BY "

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " LIMIT "

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, "\n          );\n        END;\n    "

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 4
    invoke-static {p0}, Lkotlin/text/StringsKt;->trimIndent(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 6
    invoke-virtual {v0}, Landroid/database/sqlite/SQLiteDatabase;->beginTransaction()V

    const-string v1, "DROP TRIGGER IF EXISTS tgr48932"

    .line 7
    :try_start_0
    invoke-virtual {v0, v1}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 8
    invoke-virtual {v0, p0}, Landroid/database/sqlite/SQLiteDatabase;->execSQL(Ljava/lang/String;)V

    .line 9
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 10
    invoke-virtual {v0}, Landroid/database/sqlite/SQLiteDatabase;->setTransactionSuccessful()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 11
    invoke-virtual {v0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    return-void

    :catchall_0
    move-exception p0

    invoke-virtual {v0}, Landroid/database/sqlite/SQLiteDatabase;->endTransaction()V

    throw p0
.end method


# virtual methods
.method public final zza(Ljava/lang/String;Landroid/view/MotionEvent;)V
    .locals 6

    .line 1
    invoke-virtual {p2}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/google/android/recaptcha/internal/zzay;->zzd:J

    sub-long v2, v0, v2

    iget-wide v4, p0, Lcom/google/android/recaptcha/internal/zzay;->zzb:J

    cmp-long v2, v2, v4

    if-gez v2, :cond_0

    return-void

    :cond_0
    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzay;->zzc:Lcom/google/android/recaptcha/internal/zzax;

    .line 2
    invoke-virtual {v2}, Landroid/database/sqlite/SQLiteOpenHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v2

    new-instance v3, Landroid/content/ContentValues;

    .line 3
    invoke-direct {v3}, Landroid/content/ContentValues;-><init>()V

    sget-object v4, Lcom/google/android/recaptcha/internal/zzaju;->zzc:Lcom/google/android/recaptcha/internal/zzaju;

    .line 4
    invoke-static {v4}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object v4

    sget-object v5, Lkotlin/text/Charsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p1, v5}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p1

    invoke-virtual {v3, v4, p1}, Landroid/content/ContentValues;->put(Ljava/lang/String;[B)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzf:Lcom/google/android/recaptcha/internal/zzaju;

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getDownTime()J

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zze:Lcom/google/android/recaptcha/internal/zzaju;

    .line 6
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzd:Lcom/google/android/recaptcha/internal/zzaju;

    .line 7
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getAction()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzg:Lcom/google/android/recaptcha/internal/zzaju;

    .line 8
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzh:Lcom/google/android/recaptcha/internal/zzaju;

    .line 9
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getMetaState()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzi:Lcom/google/android/recaptcha/internal/zzaju;

    .line 10
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getButtonState()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzj:Lcom/google/android/recaptcha/internal/zzaju;

    .line 11
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getRawX()F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Float;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzk:Lcom/google/android/recaptcha/internal/zzaju;

    .line 12
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getRawY()F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Float;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzl:Lcom/google/android/recaptcha/internal/zzaju;

    .line 13
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getDeviceId()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzm:Lcom/google/android/recaptcha/internal/zzaju;

    .line 14
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v4

    invoke-virtual {p2, v4}, Landroid/view/MotionEvent;->getToolType(I)I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzn:Lcom/google/android/recaptcha/internal/zzaju;

    .line 15
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getEdgeFlags()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzo:Lcom/google/android/recaptcha/internal/zzaju;

    .line 16
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getSource()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzp:Lcom/google/android/recaptcha/internal/zzaju;

    .line 17
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getPressure()F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Float;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzq:Lcom/google/android/recaptcha/internal/zzaju;

    .line 18
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getSize()F

    move-result v4

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    invoke-virtual {v3, p1, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Float;)V

    sget-object p1, Lcom/google/android/recaptcha/internal/zzaju;->zzr:Lcom/google/android/recaptcha/internal/zzaju;

    .line 19
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzau;->zza(Lcom/google/android/recaptcha/internal/zzaju;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getFlags()I

    move-result p2

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {v3, p1, p2}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    const-string/jumbo p1, "tbl64158"

    const/4 p2, 0x0

    .line 20
    invoke-virtual {v2, p1, p2, v3}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J

    iput-wide v0, p0, Lcom/google/android/recaptcha/internal/zzay;->zzd:J

    return-void
.end method

.method public final zzb()V
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzay;->zzc:Lcom/google/android/recaptcha/internal/zzax;

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteOpenHelper;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object p0

    const-string/jumbo v0, "tbl64158"

    const/4 v1, 0x0

    .line 2
    invoke-virtual {p0, v0, v1, v1}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I

    return-void
.end method

.method public final zzc(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzay;->zzc:Lcom/google/android/recaptcha/internal/zzax;

    invoke-virtual {p0}, Landroid/database/sqlite/SQLiteOpenHelper;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object p0

    .line 2
    invoke-virtual {p0, p1, p2}, Landroid/database/sqlite/SQLiteDatabase;->rawQuery(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p0

    return-object p0
.end method
