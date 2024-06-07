package com.tekartik.sqflite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;
import com.tekartik.sqflite.operation.Operation;
import com.tekartik.sqflite.operation.QueuedOperation;
import com.tekartik.sqflite.operation.SqlErrorInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Database {
    static final boolean WAL_ENABLED_BY_DEFAULT = false;
    private static final String WAL_ENABLED_META_NAME = "com.tekartik.sqflite.wal_enabled";
    private static Boolean walGloballyEnabled;
    final Context context;
    private Integer currentTransactionId;
    public DatabaseWorkerPool databaseWorkerPool;
    final int id;
    final int logLevel;
    final String path;
    final boolean singleInstance;
    SQLiteDatabase sqliteDatabase;
    final List<QueuedOperation> noTransactionOperationQueue = new ArrayList();
    final Map<Integer, SqfliteCursor> cursors = new HashMap();
    private int transactionDepth = 0;
    private int lastTransactionId = 0;
    private int lastCursorId = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Database(Context context, String str, int i, boolean z, int i2) {
        this.context = context;
        this.path = str;
        this.singleInstance = z;
        this.id = i;
        this.logLevel = i2;
    }

    protected static boolean checkWalEnabled(Context context) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(WAL_ENABLED_META_NAME, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteDatabase(String str) {
        SQLiteDatabase.deleteDatabase(new File(str));
    }

    public static boolean existsDatabase(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public void open() {
        if (walGloballyEnabled == null) {
            Boolean valueOf = Boolean.valueOf(checkWalEnabled(this.context));
            walGloballyEnabled = valueOf;
            if (valueOf.booleanValue() && LogLevel.hasVerboseLevel(this.logLevel)) {
                Log.d(Constant.TAG, getThreadLogPrefix() + "[sqflite] WAL enabled");
            }
        }
        this.sqliteDatabase = SQLiteDatabase.openDatabase(this.path, null, walGloballyEnabled.booleanValue() ? 805306368 : 268435456);
    }

    public void openReadOnly() {
        this.sqliteDatabase = SQLiteDatabase.openDatabase(this.path, null, 1, new DatabaseErrorHandler() { // from class: com.tekartik.sqflite.Database.1
            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            }
        });
    }

    public void close() {
        if (!this.cursors.isEmpty() && LogLevel.hasSqlLevel(this.logLevel)) {
            Log.d(Constant.TAG, getThreadLogPrefix() + this.cursors.size() + " cursor(s) are left opened");
        }
        this.sqliteDatabase.close();
    }

    public SQLiteDatabase getWritableDatabase() {
        return this.sqliteDatabase;
    }

    public SQLiteDatabase getReadableDatabase() {
        return this.sqliteDatabase;
    }

    public boolean enableWriteAheadLogging() {
        try {
            return this.sqliteDatabase.enableWriteAheadLogging();
        } catch (Exception e) {
            Log.e(Constant.TAG, getThreadLogPrefix() + "enable WAL error: " + e);
            return false;
        }
    }

    String getThreadLogTag() {
        Thread currentThread = Thread.currentThread();
        return "" + this.id + "," + currentThread.getName() + "(" + currentThread.getId() + ")";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getThreadLogPrefix() {
        return "[" + getThreadLogTag() + "] ";
    }

    private Map<String, Object> cursorToResults(Cursor cursor, Integer num) {
        HashMap hashMap = null;
        ArrayList arrayList = null;
        int i = 0;
        while (cursor.moveToNext()) {
            if (hashMap == null) {
                ArrayList arrayList2 = new ArrayList();
                HashMap hashMap2 = new HashMap();
                i = cursor.getColumnCount();
                hashMap2.put(Constant.PARAM_COLUMNS, Arrays.asList(cursor.getColumnNames()));
                hashMap2.put(Constant.PARAM_ROWS, arrayList2);
                arrayList = arrayList2;
                hashMap = hashMap2;
            }
            arrayList.add(Utils.cursorRowToList(cursor, i));
            if (num != null && arrayList.size() >= num.intValue()) {
                break;
            }
        }
        return hashMap == null ? new HashMap() : hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runQueuedOperations() {
        while (!this.noTransactionOperationQueue.isEmpty() && this.currentTransactionId == null) {
            this.noTransactionOperationQueue.get(0).run();
            this.noTransactionOperationQueue.remove(0);
        }
    }

    private void wrapSqlOperationHandler(Operation operation, Runnable runnable) {
        Integer transactionId = operation.getTransactionId();
        Integer num = this.currentTransactionId;
        if (num == null) {
            runnable.run();
            return;
        }
        if (transactionId != null && (transactionId.equals(num) || transactionId.intValue() == -1)) {
            runnable.run();
            if (this.currentTransactionId != null || this.noTransactionOperationQueue.isEmpty()) {
                return;
            }
            this.databaseWorkerPool.post(this, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Database.this.runQueuedOperations();
                }
            });
            return;
        }
        this.noTransactionOperationQueue.add(new QueuedOperation(operation, runnable));
    }

    public void query(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m260lambda$query$0$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.tekartik.sqflite.operation.Operation] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.tekartik.sqflite.SqlCommand, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.tekartik.sqflite.Database] */
    /* renamed from: doQuery, reason: merged with bridge method [inline-methods] */
    public boolean m260lambda$query$0$comtekartiksqfliteDatabase(Operation operation) {
        Integer num = (Integer) operation.getArgument(Constant.PARAM_CURSOR_PAGE_SIZE);
        final ?? sqlCommand = operation.getSqlCommand();
        if (LogLevel.hasSqlLevel(this.logLevel)) {
            Log.d(Constant.TAG, getThreadLogPrefix() + sqlCommand);
        }
        SqfliteCursor sqfliteCursor = null;
        try {
            try {
                sqlCommand = getReadableDatabase().rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda0
                    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
                    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                        return Database.lambda$doQuery$1(SqlCommand.this, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
                    }
                }, sqlCommand.getSql(), Constant.EMPTY_STRING_ARRAY, null);
                try {
                    Map<String, Object> cursorToResults = cursorToResults(sqlCommand, num);
                    if ((num == null || sqlCommand.isLast() || sqlCommand.isAfterLast()) ? false : true) {
                        int i = this.lastCursorId + 1;
                        this.lastCursorId = i;
                        cursorToResults.put(Constant.PARAM_CURSOR_ID, Integer.valueOf(i));
                        SqfliteCursor sqfliteCursor2 = new SqfliteCursor(i, num.intValue(), sqlCommand);
                        try {
                            this.cursors.put(Integer.valueOf(i), sqfliteCursor2);
                            sqfliteCursor = sqfliteCursor2;
                        } catch (Exception e) {
                            e = e;
                            sqfliteCursor = sqfliteCursor2;
                            handleException(e, operation);
                            if (sqfliteCursor != null) {
                                closeCursor(sqfliteCursor);
                            }
                            if (sqfliteCursor == null && sqlCommand != 0) {
                                sqlCommand.close();
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            sqfliteCursor = sqfliteCursor2;
                            if (sqfliteCursor == null && sqlCommand != 0) {
                                sqlCommand.close();
                            }
                            throw th;
                        }
                    }
                    operation.success(cursorToResults);
                    if (sqfliteCursor == null && sqlCommand != 0) {
                        sqlCommand.close();
                    }
                    return true;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            sqlCommand = 0;
        } catch (Throwable th3) {
            th = th3;
            sqlCommand = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Cursor lambda$doQuery$1(SqlCommand sqlCommand, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        sqlCommand.bindTo(sQLiteQuery);
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }

    public void queryCursorNext(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m261lambda$queryCursorNext$2$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doQueryCursorNext, reason: merged with bridge method [inline-methods] */
    public boolean m261lambda$queryCursorNext$2$comtekartiksqfliteDatabase(Operation operation) {
        boolean z;
        int intValue = ((Integer) operation.getArgument(Constant.PARAM_CURSOR_ID)).intValue();
        boolean equals = Boolean.TRUE.equals(operation.getArgument(Constant.PARAM_CANCEL));
        if (LogLevel.hasVerboseLevel(this.logLevel)) {
            StringBuilder sb = new StringBuilder();
            sb.append(getThreadLogPrefix());
            sb.append("cursor ");
            sb.append(intValue);
            sb.append(equals ? " cancel" : " next");
            Log.d(Constant.TAG, sb.toString());
        }
        SqfliteCursor sqfliteCursor = null;
        if (equals) {
            closeCursor(intValue);
            operation.success(null);
            return true;
        }
        SqfliteCursor sqfliteCursor2 = this.cursors.get(Integer.valueOf(intValue));
        boolean z2 = false;
        try {
            if (sqfliteCursor2 == null) {
                throw new IllegalStateException("Cursor " + intValue + " not found");
            }
            Cursor cursor = sqfliteCursor2.cursor;
            Map<String, Object> cursorToResults = cursorToResults(cursor, Integer.valueOf(sqfliteCursor2.pageSize));
            z = (cursor.isLast() || cursor.isAfterLast()) ? false : true;
            if (z) {
                try {
                    try {
                        cursorToResults.put(Constant.PARAM_CURSOR_ID, Integer.valueOf(intValue));
                    } catch (Exception e) {
                        e = e;
                        handleException(e, operation);
                        if (sqfliteCursor2 != null) {
                            closeCursor(sqfliteCursor2);
                        } else {
                            sqfliteCursor = sqfliteCursor2;
                        }
                        if (!z && sqfliteCursor != null) {
                            closeCursor(sqfliteCursor);
                        }
                        return false;
                    }
                } catch (Throwable th) {
                    th = th;
                    z2 = z;
                    if (!z2 && sqfliteCursor2 != null) {
                        closeCursor(sqfliteCursor2);
                    }
                    throw th;
                }
            }
            operation.success(cursorToResults);
            if (!z && sqfliteCursor2 != null) {
                closeCursor(sqfliteCursor2);
            }
            return true;
        } catch (Exception e2) {
            e = e2;
            z = false;
        } catch (Throwable th2) {
            th = th2;
            if (!z2) {
                closeCursor(sqfliteCursor2);
            }
            throw th;
        }
    }

    private void closeCursor(SqfliteCursor sqfliteCursor) {
        try {
            int i = sqfliteCursor.cursorId;
            if (LogLevel.hasVerboseLevel(this.logLevel)) {
                Log.d(Constant.TAG, getThreadLogPrefix() + "closing cursor " + i);
            }
            this.cursors.remove(Integer.valueOf(i));
            sqfliteCursor.cursor.close();
        } catch (Exception unused) {
        }
    }

    private void closeCursor(int i) {
        SqfliteCursor sqfliteCursor = this.cursors.get(Integer.valueOf(i));
        if (sqfliteCursor != null) {
            closeCursor(sqfliteCursor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleException(Exception exc, Operation operation) {
        if (exc instanceof SQLiteCantOpenDatabaseException) {
            operation.error("sqlite_error", "open_failed " + this.path, null);
            return;
        }
        if (exc instanceof SQLException) {
            operation.error("sqlite_error", exc.getMessage(), SqlErrorInfo.getMap(operation));
        } else {
            operation.error("sqlite_error", exc.getMessage(), SqlErrorInfo.getMap(operation));
        }
    }

    private boolean executeOrError(Operation operation) {
        SqlCommand sqlCommand = operation.getSqlCommand();
        if (LogLevel.hasSqlLevel(this.logLevel)) {
            Log.d(Constant.TAG, getThreadLogPrefix() + sqlCommand);
        }
        Boolean inTransactionChange = operation.getInTransactionChange();
        try {
            getWritableDatabase().execSQL(sqlCommand.getSql(), sqlCommand.getSqlArguments());
            enterOrLeaveInTransaction(inTransactionChange);
            return true;
        } catch (Exception e) {
            handleException(e, operation);
            return false;
        }
    }

    public void execute(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m258lambda$execute$3$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$execute$3$com-tekartik-sqflite-Database, reason: not valid java name */
    public /* synthetic */ void m258lambda$execute$3$comtekartiksqfliteDatabase(Operation operation) {
        Boolean inTransactionChange = operation.getInTransactionChange();
        boolean z = Boolean.TRUE.equals(inTransactionChange) && operation.hasNullTransactionId();
        if (z) {
            int i = this.lastTransactionId + 1;
            this.lastTransactionId = i;
            this.currentTransactionId = Integer.valueOf(i);
        }
        if (!executeOrError(operation)) {
            if (z) {
                this.currentTransactionId = null;
            }
        } else if (z) {
            HashMap hashMap = new HashMap();
            hashMap.put(Constant.PARAM_TRANSACTION_ID, this.currentTransactionId);
            operation.success(hashMap);
        } else {
            if (Boolean.FALSE.equals(inTransactionChange)) {
                this.currentTransactionId = null;
            }
            operation.success(null);
        }
    }

    private boolean doExecute(Operation operation) {
        if (!executeOrError(operation)) {
            return false;
        }
        operation.success(null);
        return true;
    }

    public void insert(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m259lambda$insert$4$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d3  */
    /* renamed from: doInsert, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean m259lambda$insert$4$comtekartiksqfliteDatabase(com.tekartik.sqflite.operation.Operation r10) {
        /*
            r9 = this;
            boolean r0 = r9.executeOrError(r10)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            boolean r0 = r10.getNoResult()
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L14
            r10.success(r2)
            return r3
        L14:
            java.lang.String r0 = "SELECT changes(), last_insert_rowid()"
            android.database.sqlite.SQLiteDatabase r4 = r9.getWritableDatabase()     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            android.database.Cursor r0 = r4.rawQuery(r0, r2)     // Catch: java.lang.Throwable -> Lc0 java.lang.Exception -> Lc2
            java.lang.String r4 = "Sqflite"
            if (r0 == 0) goto L9f
            int r5 = r0.getCount()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r5 <= 0) goto L9f
            boolean r5 = r0.moveToFirst()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r5 == 0) goto L9f
            int r5 = r0.getInt(r1)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r5 != 0) goto L69
            int r5 = r9.logLevel     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            boolean r5 = com.tekartik.sqflite.LogLevel.hasSqlLevel(r5)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r5 == 0) goto L60
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r5.<init>()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r6 = r9.getThreadLogPrefix()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r5.append(r6)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r6 = "no changes (id was "
            r5.append(r6)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            long r6 = r0.getLong(r3)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r5.append(r6)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r6 = ")"
            r5.append(r6)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            android.util.Log.d(r4, r5)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
        L60:
            r10.success(r2)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r0 == 0) goto L68
            r0.close()
        L68:
            return r3
        L69:
            long r5 = r0.getLong(r3)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            int r2 = r9.logLevel     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            boolean r2 = com.tekartik.sqflite.LogLevel.hasSqlLevel(r2)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r2 == 0) goto L90
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r2.<init>()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r7 = r9.getThreadLogPrefix()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r2.append(r7)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r7 = "inserted "
            r2.append(r7)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r2.append(r5)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            android.util.Log.d(r4, r2)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
        L90:
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r10.success(r2)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r0 == 0) goto L9c
            r0.close()
        L9c:
            return r3
        L9d:
            r2 = move-exception
            goto Lc6
        L9f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r5.<init>()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r6 = r9.getThreadLogPrefix()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r5.append(r6)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r6 = "fail to read changes for Insert"
            r5.append(r6)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            android.util.Log.e(r4, r5)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            r10.success(r2)     // Catch: java.lang.Exception -> L9d java.lang.Throwable -> Lcf
            if (r0 == 0) goto Lbf
            r0.close()
        Lbf:
            return r3
        Lc0:
            r10 = move-exception
            goto Ld1
        Lc2:
            r0 = move-exception
            r8 = r2
            r2 = r0
            r0 = r8
        Lc6:
            r9.handleException(r2, r10)     // Catch: java.lang.Throwable -> Lcf
            if (r0 == 0) goto Lce
            r0.close()
        Lce:
            return r1
        Lcf:
            r10 = move-exception
            r2 = r0
        Ld1:
            if (r2 == 0) goto Ld6
            r2.close()
        Ld6:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.Database.m259lambda$insert$4$comtekartiksqfliteDatabase(com.tekartik.sqflite.operation.Operation):boolean");
    }

    public void update(final Operation operation) {
        wrapSqlOperationHandler(operation, new Runnable() { // from class: com.tekartik.sqflite.Database$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                Database.this.m262lambda$update$5$comtekartiksqfliteDatabase(operation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doUpdate, reason: merged with bridge method [inline-methods] */
    public boolean m262lambda$update$5$comtekartiksqfliteDatabase(Operation operation) {
        if (!executeOrError(operation)) {
            return false;
        }
        Cursor cursor = null;
        if (operation.getNoResult()) {
            operation.success(null);
            return true;
        }
        try {
            try {
                Cursor rawQuery = getWritableDatabase().rawQuery("SELECT changes()", null);
                if (rawQuery != null) {
                    try {
                        if (rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
                            int i = rawQuery.getInt(0);
                            if (LogLevel.hasSqlLevel(this.logLevel)) {
                                Log.d(Constant.TAG, getThreadLogPrefix() + "changed " + i);
                            }
                            operation.success(Integer.valueOf(i));
                            if (rawQuery != null) {
                                rawQuery.close();
                            }
                            return true;
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = rawQuery;
                        handleException(e, operation);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        cursor = rawQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                Log.e(Constant.TAG, getThreadLogPrefix() + "fail to read changes for Update/Delete");
                operation.success(null);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return true;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ca A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x008a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void batch(io.flutter.plugin.common.MethodCall r9, io.flutter.plugin.common.MethodChannel.Result r10) {
        /*
            r8 = this;
            com.tekartik.sqflite.operation.MethodCallOperation r0 = new com.tekartik.sqflite.operation.MethodCallOperation
            r0.<init>(r9, r10)
            boolean r9 = r0.getNoResult()
            boolean r1 = r0.getContinueOnError()
            java.lang.String r2 = "operations"
            java.lang.Object r0 = r0.getArgument(r2)
            java.util.List r0 = (java.util.List) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L1e:
            boolean r3 = r0.hasNext()
            r4 = 0
            if (r3 == 0) goto Le0
            java.lang.Object r3 = r0.next()
            java.util.Map r3 = (java.util.Map) r3
            com.tekartik.sqflite.operation.BatchOperation r5 = new com.tekartik.sqflite.operation.BatchOperation
            r5.<init>(r3, r9)
            java.lang.String r3 = r5.getMethod()
            r3.hashCode()
            r6 = -1
            int r7 = r3.hashCode()
            switch(r7) {
                case -1319569547: goto L61;
                case -1183792455: goto L56;
                case -838846263: goto L4b;
                case 107944136: goto L40;
                default: goto L3f;
            }
        L3f:
            goto L6b
        L40:
            java.lang.String r7 = "query"
            boolean r7 = r3.equals(r7)
            if (r7 != 0) goto L49
            goto L6b
        L49:
            r6 = 3
            goto L6b
        L4b:
            java.lang.String r7 = "update"
            boolean r7 = r3.equals(r7)
            if (r7 != 0) goto L54
            goto L6b
        L54:
            r6 = 2
            goto L6b
        L56:
            java.lang.String r7 = "insert"
            boolean r7 = r3.equals(r7)
            if (r7 != 0) goto L5f
            goto L6b
        L5f:
            r6 = 1
            goto L6b
        L61:
            java.lang.String r7 = "execute"
            boolean r7 = r3.equals(r7)
            if (r7 != 0) goto L6a
            goto L6b
        L6a:
            r6 = 0
        L6b:
            switch(r6) {
                case 0: goto Lca;
                case 1: goto Lb4;
                case 2: goto L9e;
                case 3: goto L8a;
                default: goto L6e;
            }
        L6e:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "Batch method '"
            r9.append(r0)
            r9.append(r3)
            java.lang.String r0 = "' not supported"
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            java.lang.String r0 = "bad_param"
            r10.error(r0, r9, r4)
            return
        L8a:
            boolean r3 = r8.m260lambda$query$0$comtekartiksqfliteDatabase(r5)
            if (r3 == 0) goto L94
            r5.handleSuccess(r2)
            goto L1e
        L94:
            if (r1 == 0) goto L9a
            r5.handleErrorContinue(r2)
            goto L1e
        L9a:
            r5.handleError(r10)
            return
        L9e:
            boolean r3 = r8.m262lambda$update$5$comtekartiksqfliteDatabase(r5)
            if (r3 == 0) goto La9
            r5.handleSuccess(r2)
            goto L1e
        La9:
            if (r1 == 0) goto Lb0
            r5.handleErrorContinue(r2)
            goto L1e
        Lb0:
            r5.handleError(r10)
            return
        Lb4:
            boolean r3 = r8.m259lambda$insert$4$comtekartiksqfliteDatabase(r5)
            if (r3 == 0) goto Lbf
            r5.handleSuccess(r2)
            goto L1e
        Lbf:
            if (r1 == 0) goto Lc6
            r5.handleErrorContinue(r2)
            goto L1e
        Lc6:
            r5.handleError(r10)
            return
        Lca:
            boolean r3 = r8.doExecute(r5)
            if (r3 == 0) goto Ld5
            r5.handleSuccess(r2)
            goto L1e
        Ld5:
            if (r1 == 0) goto Ldc
            r5.handleErrorContinue(r2)
            goto L1e
        Ldc:
            r5.handleError(r10)
            return
        Le0:
            if (r9 == 0) goto Le6
            r10.success(r4)
            goto Le9
        Le6:
            r10.success(r2)
        Le9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tekartik.sqflite.Database.batch(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean isInTransaction() {
        return this.transactionDepth > 0;
    }

    synchronized void enterOrLeaveInTransaction(Boolean bool) {
        if (Boolean.TRUE.equals(bool)) {
            this.transactionDepth++;
        } else if (Boolean.FALSE.equals(bool)) {
            this.transactionDepth--;
        }
    }
}
