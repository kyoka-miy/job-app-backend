/*
 * This file is generated by jOOQ.
 */
package com.example.job_app.infra.jooq.tables.records;


import com.example.job_app.infra.jooq.tables.JobLists;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JobListsRecord extends UpdatableRecordImpl<JobListsRecord> implements Record2<String, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>job_app.job_lists.job_list_id</code>.
     */
    public void setJobListId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>job_app.job_lists.job_list_id</code>.
     */
    public String getJobListId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>job_app.job_lists.active</code>.
     */
    public void setActive(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>job_app.job_lists.active</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, Boolean> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, Boolean> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return JobLists.JOB_LISTS.JOB_LIST_ID;
    }

    @Override
    public Field<Boolean> field2() {
        return JobLists.JOB_LISTS.ACTIVE;
    }

    @Override
    public String component1() {
        return getJobListId();
    }

    @Override
    public Boolean component2() {
        return getActive();
    }

    @Override
    public String value1() {
        return getJobListId();
    }

    @Override
    public Boolean value2() {
        return getActive();
    }

    @Override
    public JobListsRecord value1(String value) {
        setJobListId(value);
        return this;
    }

    @Override
    public JobListsRecord value2(Boolean value) {
        setActive(value);
        return this;
    }

    @Override
    public JobListsRecord values(String value1, Boolean value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JobListsRecord
     */
    public JobListsRecord() {
        super(JobLists.JOB_LISTS);
    }

    /**
     * Create a detached, initialised JobListsRecord
     */
    public JobListsRecord(String jobListId, Boolean active) {
        super(JobLists.JOB_LISTS);

        setJobListId(jobListId);
        setActive(active);
    }
}
