/*
 * This file is generated by jOOQ.
 */
package com.example.job_app.infra.jooq.tables;


import com.example.job_app.infra.jooq.Cqq2l0ixquavsq6l;
import com.example.job_app.infra.jooq.Keys;
import com.example.job_app.infra.jooq.tables.records.JobsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row14;
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
public class Jobs extends TableImpl<JobsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>cqq2l0ixquavsq6l.jobs</code>
     */
    public static final Jobs JOBS = new Jobs();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JobsRecord> getRecordType() {
        return JobsRecord.class;
    }

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.job_id</code>.
     */
    public final TableField<JobsRecord, String> JOB_ID = createField(DSL.name("job_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.job_title</code>.
     */
    public final TableField<JobsRecord, String> JOB_TITLE = createField(DSL.name("job_title"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.company_name</code>.
     */
    public final TableField<JobsRecord, String> COMPANY_NAME = createField(DSL.name("company_name"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.url</code>.
     */
    public final TableField<JobsRecord, String> URL = createField(DSL.name("url"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.location</code>.
     */
    public final TableField<JobsRecord, String> LOCATION = createField(DSL.name("location"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.salary</code>.
     */
    public final TableField<JobsRecord, String> SALARY = createField(DSL.name("salary"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.remote</code>.
     */
    public final TableField<JobsRecord, String> REMOTE = createField(DSL.name("remote"), SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.description</code>.
     */
    public final TableField<JobsRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.status</code>.
     */
    public final TableField<JobsRecord, String> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.applied_datetime</code>.
     */
    public final TableField<JobsRecord, LocalDateTime> APPLIED_DATETIME = createField(DSL.name("applied_datetime"), SQLDataType.LOCALDATETIME(0), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.job_board</code>.
     */
    public final TableField<JobsRecord, String> JOB_BOARD = createField(DSL.name("job_board"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.note</code>.
     */
    public final TableField<JobsRecord, String> NOTE = createField(DSL.name("note"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.board_id</code>.
     */
    public final TableField<JobsRecord, String> BOARD_ID = createField(DSL.name("board_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.jobs.added_datetime</code>.
     */
    public final TableField<JobsRecord, LocalDateTime> ADDED_DATETIME = createField(DSL.name("added_datetime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private Jobs(Name alias, Table<JobsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Jobs(Name alias, Table<JobsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>cqq2l0ixquavsq6l.jobs</code> table reference
     */
    public Jobs(String alias) {
        this(DSL.name(alias), JOBS);
    }

    /**
     * Create an aliased <code>cqq2l0ixquavsq6l.jobs</code> table reference
     */
    public Jobs(Name alias) {
        this(alias, JOBS);
    }

    /**
     * Create a <code>cqq2l0ixquavsq6l.jobs</code> table reference
     */
    public Jobs() {
        this(DSL.name("jobs"), null);
    }

    public <O extends Record> Jobs(Table<O> child, ForeignKey<O, JobsRecord> key) {
        super(child, key, JOBS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Cqq2l0ixquavsq6l.CQQ2L0IXQUAVSQ6L;
    }

    @Override
    public UniqueKey<JobsRecord> getPrimaryKey() {
        return Keys.KEY_JOBS_PRIMARY;
    }

    @Override
    public List<ForeignKey<JobsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_JOBS_BOARDS);
    }

    private transient Boards _boards;

    /**
     * Get the implicit join path to the <code>cqq2l0ixquavsq6l.boards</code>
     * table.
     */
    public Boards boards() {
        if (_boards == null)
            _boards = new Boards(this, Keys.FK_JOBS_BOARDS);

        return _boards;
    }

    @Override
    public Jobs as(String alias) {
        return new Jobs(DSL.name(alias), this);
    }

    @Override
    public Jobs as(Name alias) {
        return new Jobs(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Jobs rename(String name) {
        return new Jobs(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Jobs rename(Name name) {
        return new Jobs(name, null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<String, String, String, String, String, String, String, String, String, LocalDateTime, String, String, String, LocalDateTime> fieldsRow() {
        return (Row14) super.fieldsRow();
    }
}
