/*
 * This file is generated by jOOQ.
 */
package com.example.job_app.infra.jooq.tables;


import com.example.job_app.infra.jooq.JobApp;
import com.example.job_app.infra.jooq.Keys;
import com.example.job_app.infra.jooq.tables.records.BoardsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Boards extends TableImpl<BoardsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>job_app.boards</code>
     */
    public static final Boards BOARDS = new Boards();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BoardsRecord> getRecordType() {
        return BoardsRecord.class;
    }

    /**
     * The column <code>job_app.boards.board_id</code>.
     */
    public final TableField<BoardsRecord, String> BOARD_ID = createField(DSL.name("board_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>job_app.boards.created_datetime</code>.
     */
    public final TableField<BoardsRecord, LocalDateTime> CREATED_DATETIME = createField(DSL.name("created_datetime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>job_app.boards.name</code>.
     */
    public final TableField<BoardsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>job_app.boards.account_id</code>.
     */
    public final TableField<BoardsRecord, String> ACCOUNT_ID = createField(DSL.name("account_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    private Boards(Name alias, Table<BoardsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Boards(Name alias, Table<BoardsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>job_app.boards</code> table reference
     */
    public Boards(String alias) {
        this(DSL.name(alias), BOARDS);
    }

    /**
     * Create an aliased <code>job_app.boards</code> table reference
     */
    public Boards(Name alias) {
        this(alias, BOARDS);
    }

    /**
     * Create a <code>job_app.boards</code> table reference
     */
    public Boards() {
        this(DSL.name("boards"), null);
    }

    public <O extends Record> Boards(Table<O> child, ForeignKey<O, BoardsRecord> key) {
        super(child, key, BOARDS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JobApp.JOB_APP;
    }

    @Override
    public UniqueKey<BoardsRecord> getPrimaryKey() {
        return Keys.KEY_BOARDS_PRIMARY;
    }

    @Override
    public List<ForeignKey<BoardsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_BOARDS_ACCOUNTS);
    }

    private transient Accounts _accounts;

    /**
     * Get the implicit join path to the <code>job_app.accounts</code> table.
     */
    public Accounts accounts() {
        if (_accounts == null)
            _accounts = new Accounts(this, Keys.FK_BOARDS_ACCOUNTS);

        return _accounts;
    }

    @Override
    public Boards as(String alias) {
        return new Boards(DSL.name(alias), this);
    }

    @Override
    public Boards as(Name alias) {
        return new Boards(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Boards rename(String name) {
        return new Boards(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Boards rename(Name name) {
        return new Boards(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, LocalDateTime, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
