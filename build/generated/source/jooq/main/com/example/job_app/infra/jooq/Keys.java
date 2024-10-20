/*
 * This file is generated by jOOQ.
 */
package com.example.job_app.infra.jooq;


import com.example.job_app.infra.jooq.tables.Accounts;
import com.example.job_app.infra.jooq.tables.Activities;
import com.example.job_app.infra.jooq.tables.Boards;
import com.example.job_app.infra.jooq.tables.Interviews;
import com.example.job_app.infra.jooq.tables.JobLists;
import com.example.job_app.infra.jooq.tables.Jobs;
import com.example.job_app.infra.jooq.tables.Offers;
import com.example.job_app.infra.jooq.tables.records.AccountsRecord;
import com.example.job_app.infra.jooq.tables.records.ActivitiesRecord;
import com.example.job_app.infra.jooq.tables.records.BoardsRecord;
import com.example.job_app.infra.jooq.tables.records.InterviewsRecord;
import com.example.job_app.infra.jooq.tables.records.JobListsRecord;
import com.example.job_app.infra.jooq.tables.records.JobsRecord;
import com.example.job_app.infra.jooq.tables.records.OffersRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * cqq2l0ixquavsq6l.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountsRecord> KEY_ACCOUNTS_EMAIL = Internal.createUniqueKey(Accounts.ACCOUNTS, DSL.name("KEY_accounts_email"), new TableField[] { Accounts.ACCOUNTS.EMAIL }, true);
    public static final UniqueKey<AccountsRecord> KEY_ACCOUNTS_PRIMARY = Internal.createUniqueKey(Accounts.ACCOUNTS, DSL.name("KEY_accounts_PRIMARY"), new TableField[] { Accounts.ACCOUNTS.ACCOUNT_ID }, true);
    public static final UniqueKey<ActivitiesRecord> KEY_ACTIVITIES_PRIMARY = Internal.createUniqueKey(Activities.ACTIVITIES, DSL.name("KEY_activities_PRIMARY"), new TableField[] { Activities.ACTIVITIES.ACTIVITY_ID }, true);
    public static final UniqueKey<BoardsRecord> KEY_BOARDS_PRIMARY = Internal.createUniqueKey(Boards.BOARDS, DSL.name("KEY_boards_PRIMARY"), new TableField[] { Boards.BOARDS.BOARD_ID }, true);
    public static final UniqueKey<InterviewsRecord> KEY_INTERVIEWS_PRIMARY = Internal.createUniqueKey(Interviews.INTERVIEWS, DSL.name("KEY_interviews_PRIMARY"), new TableField[] { Interviews.INTERVIEWS.INTERVIEW_ID }, true);
    public static final UniqueKey<JobListsRecord> KEY_JOB_LISTS_PRIMARY = Internal.createUniqueKey(JobLists.JOB_LISTS, DSL.name("KEY_job_lists_PRIMARY"), new TableField[] { JobLists.JOB_LISTS.JOB_LIST_ID }, true);
    public static final UniqueKey<JobsRecord> KEY_JOBS_PRIMARY = Internal.createUniqueKey(Jobs.JOBS, DSL.name("KEY_jobs_PRIMARY"), new TableField[] { Jobs.JOBS.JOB_ID }, true);
    public static final UniqueKey<OffersRecord> KEY_OFFERS_PRIMARY = Internal.createUniqueKey(Offers.OFFERS, DSL.name("KEY_offers_PRIMARY"), new TableField[] { Offers.OFFERS.OFFER_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ActivitiesRecord, JobsRecord> FK_ACTIVITIES_JOBS = Internal.createForeignKey(Activities.ACTIVITIES, DSL.name("fk_activities_jobs"), new TableField[] { Activities.ACTIVITIES.JOB_ID }, Keys.KEY_JOBS_PRIMARY, new TableField[] { Jobs.JOBS.JOB_ID }, true);
    public static final ForeignKey<BoardsRecord, AccountsRecord> FK_BOARDS_ACCOUNTS = Internal.createForeignKey(Boards.BOARDS, DSL.name("fk_boards_accounts"), new TableField[] { Boards.BOARDS.ACCOUNT_ID }, Keys.KEY_ACCOUNTS_PRIMARY, new TableField[] { Accounts.ACCOUNTS.ACCOUNT_ID }, true);
    public static final ForeignKey<InterviewsRecord, ActivitiesRecord> FK_INTERVIEWS_ACTIVITIES = Internal.createForeignKey(Interviews.INTERVIEWS, DSL.name("fk_interviews_activities"), new TableField[] { Interviews.INTERVIEWS.ACTIVITY_ID }, Keys.KEY_ACTIVITIES_PRIMARY, new TableField[] { Activities.ACTIVITIES.ACTIVITY_ID }, true);
    public static final ForeignKey<InterviewsRecord, JobsRecord> FK_INTERVIEWS_JOBS = Internal.createForeignKey(Interviews.INTERVIEWS, DSL.name("fk_interviews_jobs"), new TableField[] { Interviews.INTERVIEWS.JOB_ID }, Keys.KEY_JOBS_PRIMARY, new TableField[] { Jobs.JOBS.JOB_ID }, true);
    public static final ForeignKey<JobsRecord, BoardsRecord> FK_JOBS_BOARDS = Internal.createForeignKey(Jobs.JOBS, DSL.name("fk_jobs_boards"), new TableField[] { Jobs.JOBS.BOARD_ID }, Keys.KEY_BOARDS_PRIMARY, new TableField[] { Boards.BOARDS.BOARD_ID }, true);
    public static final ForeignKey<OffersRecord, ActivitiesRecord> FK_OFFERS_ACTIVITIES = Internal.createForeignKey(Offers.OFFERS, DSL.name("fk_offers_activities"), new TableField[] { Offers.OFFERS.ACTIVITY_ID }, Keys.KEY_ACTIVITIES_PRIMARY, new TableField[] { Activities.ACTIVITIES.ACTIVITY_ID }, true);
    public static final ForeignKey<OffersRecord, BoardsRecord> FK_OFFERS_BOARDS = Internal.createForeignKey(Offers.OFFERS, DSL.name("fk_offers_boards"), new TableField[] { Offers.OFFERS.BOARD_ID }, Keys.KEY_BOARDS_PRIMARY, new TableField[] { Boards.BOARDS.BOARD_ID }, true);
    public static final ForeignKey<OffersRecord, JobsRecord> FK_OFFERS_JOBS = Internal.createForeignKey(Offers.OFFERS, DSL.name("fk_offers_jobs"), new TableField[] { Offers.OFFERS.JOB_ID }, Keys.KEY_JOBS_PRIMARY, new TableField[] { Jobs.JOBS.JOB_ID }, true);
}