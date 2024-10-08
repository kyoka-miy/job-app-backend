/*
 * This file is generated by jOOQ.
 */
package com.example.job_app.infra.jooq.tables;


import com.example.job_app.infra.jooq.Cqq2l0ixquavsq6l;
import com.example.job_app.infra.jooq.Keys;
import com.example.job_app.infra.jooq.tables.records.InterviewsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
public class Interviews extends TableImpl<InterviewsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>cqq2l0ixquavsq6l.interviews</code>
     */
    public static final Interviews INTERVIEWS = new Interviews();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InterviewsRecord> getRecordType() {
        return InterviewsRecord.class;
    }

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.interview_id</code>.
     */
    public final TableField<InterviewsRecord, String> INTERVIEW_ID = createField(DSL.name("interview_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.interview_datetime</code>.
     */
    public final TableField<InterviewsRecord, LocalDateTime> INTERVIEW_DATETIME = createField(DSL.name("interview_datetime"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.stage</code>.
     */
    public final TableField<InterviewsRecord, String> STAGE = createField(DSL.name("stage"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.type</code>.
     */
    public final TableField<InterviewsRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.note</code>.
     */
    public final TableField<InterviewsRecord, String> NOTE = createField(DSL.name("note"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.active</code>.
     */
    public final TableField<InterviewsRecord, Boolean> ACTIVE = createField(DSL.name("active"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.job_id</code>.
     */
    public final TableField<InterviewsRecord, String> JOB_ID = createField(DSL.name("job_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>cqq2l0ixquavsq6l.interviews.activity_id</code>.
     */
    public final TableField<InterviewsRecord, String> ACTIVITY_ID = createField(DSL.name("activity_id"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    private Interviews(Name alias, Table<InterviewsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Interviews(Name alias, Table<InterviewsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>cqq2l0ixquavsq6l.interviews</code> table
     * reference
     */
    public Interviews(String alias) {
        this(DSL.name(alias), INTERVIEWS);
    }

    /**
     * Create an aliased <code>cqq2l0ixquavsq6l.interviews</code> table
     * reference
     */
    public Interviews(Name alias) {
        this(alias, INTERVIEWS);
    }

    /**
     * Create a <code>cqq2l0ixquavsq6l.interviews</code> table reference
     */
    public Interviews() {
        this(DSL.name("interviews"), null);
    }

    public <O extends Record> Interviews(Table<O> child, ForeignKey<O, InterviewsRecord> key) {
        super(child, key, INTERVIEWS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Cqq2l0ixquavsq6l.CQQ2L0IXQUAVSQ6L;
    }

    @Override
    public UniqueKey<InterviewsRecord> getPrimaryKey() {
        return Keys.KEY_INTERVIEWS_PRIMARY;
    }

    @Override
    public List<ForeignKey<InterviewsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_INTERVIEWS_JOBS, Keys.FK_INTERVIEWS_ACTIVITIES);
    }

    private transient Jobs _jobs;
    private transient Activities _activities;

    /**
     * Get the implicit join path to the <code>cqq2l0ixquavsq6l.jobs</code>
     * table.
     */
    public Jobs jobs() {
        if (_jobs == null)
            _jobs = new Jobs(this, Keys.FK_INTERVIEWS_JOBS);

        return _jobs;
    }

    /**
     * Get the implicit join path to the
     * <code>cqq2l0ixquavsq6l.activities</code> table.
     */
    public Activities activities() {
        if (_activities == null)
            _activities = new Activities(this, Keys.FK_INTERVIEWS_ACTIVITIES);

        return _activities;
    }

    @Override
    public Interviews as(String alias) {
        return new Interviews(DSL.name(alias), this);
    }

    @Override
    public Interviews as(Name alias) {
        return new Interviews(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Interviews rename(String name) {
        return new Interviews(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Interviews rename(Name name) {
        return new Interviews(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, LocalDateTime, String, String, String, Boolean, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
